package org.cmms.common.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


public class ViewBrowers implements Serializable {

	/**
	 * 版本变化，serialVersionUID也要变化
	 */
	private static final long serialVersionUID = 3282193006976975999L;

	private static Log log = LogFactory.getLog(ViewBrowers.class);

	private List<String> cookies = new ArrayList<String>(); // 保存这个网页

	private String cookieStr = "";

	private String runningUrl = "";

	private int minTimeout = 30000;// 小时间timeout，用于建立连接和小form读取传输。

	private int maxTimeout = 30000;// 大时间timeout，用于图片读取和传输。

	private int lastProxyPeerId = 0;// 保留当前使用的proxyPeerId

	private Map<String, List<String>> headers = new HashMap<String, List<String>>();

	private String userAgent = "";

	private boolean followRedirect=false;

	private ViewBrowersBean bean = new ViewBrowersBean();

	public ViewBrowers(ViewBrowersBean bean){
		if(bean != null){
			this.setBean(bean);
		}
	}

	public StringBuffer sendForm(String url, Map paramMap, String charset) throws Exception {
		return this.sendForm(url, paramMap, new HashMap<String,String>(), charset);
	}


	public static String post(HttpClientConfigJson configJson) throws Exception {
		HttpURLConnection conn = null;
		InputStream in = null;
		OutputStream out = null;
		InputStream gin = null;
		try {
			URLConnection urlC = null;
			URL url = new URL(configJson.getUrl());// 来自siteJson
			urlC = url.openConnection();
			conn = (HttpURLConnection) urlC;
			conn.setUseCaches(false);
			conn.setDoOutput(true);
			conn.setRequestMethod(configJson.getMethod());// 来自siteJson

			// 来自siteJson的header
			if (configJson.getHeaders() != null) {
				HashMap<String, String> headers = configJson.getHeaders();
				for (String key : headers.keySet()) {
					conn.setRequestProperty(key, headers.get(key));
				}
			}
			log.info("http post请求内容:"+configJson.getParas());
			in = new ByteArrayInputStream(configJson.getParas().getBytes(configJson.getCharset()));
			// 写入请求参数，如果服务器能解析gzip的请求，用gzip写入提高效率
			if (configJson.isGzipSupported()) {
				conn.addRequestProperty("Accept-Encoding", "gzip");
				conn.addRequestProperty("Content-Encoding", "gzip");
				out = conn.getOutputStream();
				compress(in, out);
			} else {
				out = conn.getOutputStream();
				IOUtils.copy(in, out);
			}

			// 读取服务器返回的结果，这里不能依据httpJson.isGzipSupported()，因为服务器可能选择5k以上的流才会gzip
			if (conn.getContentEncoding() != null && conn.getContentEncoding().indexOf("gzip") != -1) {
				gin = new GZIPInputStream(conn.getInputStream());
			} else {
				gin = conn.getInputStream();
			}
			String result = IOUtils.toString(gin, configJson.getCharset());
			log.info("http post返回结果:"+result);
			if (configJson.getRegx() != null && !configJson.getRegx().trim().equals("")) {
				// 用正则匹配内容后，返回
				return RegxUtil.getGroupString(result, configJson.getRegx(), 0);
			} else {
				return result;
			}

		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(gin);
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	/**
	 * 压缩流
	 */
	public static void compress(InputStream is, OutputStream os) throws Exception {
		GZIPOutputStream gos = new GZIPOutputStream(os);
		int count;
		byte data[] = new byte[2048];
		while ((count = is.read(data, 0, 2048)) != -1) {
			gos.write(data, 0, count);
		}
		gos.finish();
		gos.flush();
		gos.close();
	}

	/**
	 * 发送简单form，不包括图片等
	 *
	 * @param addrUrl
	 * @param paramMap
	 * @param headerMap
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public StringBuffer sendForm(String addrUrl, Map paramMap, Map headerMap, String charset) throws Exception {
		return sendSimpleForm(addrUrl, paramMap, headerMap, charset);
	}

	/**
	 * 发送简单jason form，不包括图片等
	 *
	 * @param addrUrl
	 * @param paramList
	 * @param headerMap
	 * @param charset
	 * @return
	 * @deprecated
	 * @throws Exception
	 */
	public StringBuffer sendFormJason(String addrUrl, List paramList, Map headerMap, String charset) throws Exception {
		return sendSimpleForm(addrUrl, paramList, headerMap, charset);
	}

	/**
	 * 发送一个json数据或普通form表单encode之后的数据
	 * @param addrUrl
	 * @param headerMap
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public StringBuffer sendForm(String addrUrl, String content, Map headerMap, String charset) throws Exception {
		List paramList = new ArrayList();
		paramList.add(content);
		return sendSimpleForm(addrUrl, paramList, headerMap, charset);
	}

	/**
	 * 发送简单form，不包括图片，文件等steam流
	 *
	 * @param addrUrl
	 * @param parameterObj
	 * @param headerMap
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	private StringBuffer sendSimpleForm(String addrUrl, Object parameterObj, Map headerMap, String charset) throws Exception {
		return this.sendProxyForm(addrUrl, parameterObj, headerMap, charset);
	}

	/**
	 * 发送简单form，不包括图片，文件等steam流，用某个proxy
	 *
	 * @param addrUrl
	 * @param parameterObj
	 * @param headerMap
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public StringBuffer sendProxyForm(String addrUrl, Object parameterObj, Map headerMap, String charset) throws Exception {

		log.info(" =============================== parameterObj: " + parameterObj.toString());
		long start = System.currentTimeMillis();
		log.info(" ==== addrUrl: " + addrUrl);
		if (addrUrl != null && !addrUrl.trim().equals("")) {
			this.runningUrl = addrUrl;

			HttpURLConnection conn = null;
			try {
				boolean isJson = parameterObj instanceof List;
				boolean isMap = parameterObj instanceof Map;

				StringBuffer parameterBuffer = new StringBuffer();
				if (isJson) {
					parameterBuffer = this.prepareJasonParameters((List) parameterObj, charset);
				} else if(isMap) {
					parameterBuffer = this.prepareParameters((Map) parameterObj, charset);
				}else{
					parameterBuffer = parameterBuffer.append(parameterObj.toString().trim());
					log.info("######################请求参数为字符串######################" + parameterObj.toString());
					log.info("######################请求参数为字符串URLEncoder.encode######################" + parameterBuffer.toString());
				}

				if(parameterBuffer.length()<3*1024){
					log.info("上传的参数:" + parameterBuffer.toString());
				}else{
					log.info("上传的参数为:" + parameterBuffer.toString());
					log.info("上传的参数长度为:" + parameterBuffer.length());
				}
				conn = this.prepareUrlConnection(addrUrl, headerMap, parameterBuffer.length() > 0);
				if (conn != null) {
					if(addrUrl!=null && (addrUrl.indexOf("ajkbroker/combo/broker/manage/")>-1|| addrUrl.indexOf("user/broker/brokerhome")>-1
							|| addrUrl.indexOf("vip.anjuke.com/combo/broker/manage/")>-1)){
						conn.setInstanceFollowRedirects(true);
					}
					if (conn.getRequestProperty("Content-Type") == null || conn.getRequestProperty("Content-Type").trim().equals("")) {
						if (isJson) {
							conn.setRequestProperty("Content-Type", "application/json");
						} else {
							conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
						}
					}
					return this.getResponse(conn, parameterBuffer, charset);
				} else {
					// proxyRule.disablePeer(proxyPeer, "503");
					// return this.getActionProxyResponse(addrUrl, headerMap,
					// parameterBuffer, charset, proxyPeer);
				}
			} catch (Exception e) {
				this.handleError(e, addrUrl);
				throw new SimpleException(e);
			} finally {
				long end = System.currentTimeMillis();
				log.info("★★★★★★★★当前普通form操作耗时:" + (end - start));
				if (conn != null) {
					conn.disconnect();
				}
			}
		}
		return new StringBuffer();
	}

	/**
	 * 获取actionproxy的response
	 *
	 * @param addrUrl
	 * @param headerMap
	 * @param parameterBuffer
	 * @param charset
	 * @param proxyPeer
	 * @return
	 * @throws Exception
	 */
	/*
	 * private StringBuffer getActionProxyResponse(String addrUrl, Map
	 * headerMap, StringBuffer parameterBuffer, String charset, ProxyPeer
	 * proxyPeer) throws Exception { // ★★★★★★★注意参数有两种格式 普通和jason String referer
	 * = (String) headerMap.get("Referer");
	 *
	 * HashMap actionMap = new HashMap(); actionMap.put("url", addrUrl);
	 * actionMap.put("paras", parameterBuffer.toString());
	 * actionMap.put("cookie", this.getCookieString()); if (referer != null) {
	 * actionMap.put("referer", referer); } actionMap.put("charset", charset);
	 *
	 * // log.info(vb.prepareParameters(actionMap, "utf-8")); //
	 * log.info("之前:"+this.getCookieString()); //
	 * log.info("正在准备发送处理的url:" + addrUrl); // log.info("向action proxy发送的参数列表:"
	 * + actionMap); // log.info("向action proxy发送的cookie列表:" +
	 * this.getCookieString()); // ViewBrowers vb = new ViewBrowers(); //
	 * vb.setCookieStr(this.cookieStr); StringBuffer sb =
	 * this.sendForm(((ActionPeer) proxyPeer).getPeerUrl(), actionMap, "utf-8");
	 * // log.info("来自action proxy的cookie结果:" + vb.getCookieString()); //
	 * this.setCookieStr(vb.getCookieString()); //
	 * log.info(sb.toString()); //
	 * log.info("之后:"+this.getCookieString()); // return null;
	 *
	 * return sb; }
	 */

	/**
	 * 类似58同城的图片上传,采用flash模式
	 *
	 * @param addrUrl
	 * @param paraMap
	 * @param httpHeaderMap
	 * @param charset
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public StringBuffer sendPicFormLikeFlash(String addrUrl, Map paraMap, Map httpHeaderMap, String charset, File file) throws Exception {
		HttpURLConnection httpcon = null;
		URLConnection urlC = null;
		StringBuffer debugBuffer = new StringBuffer("");// 调试用
		StringBuffer rsb = new StringBuffer("");
		long start = System.currentTimeMillis();

		try {
			URL url = new URL(addrUrl);
			this.runningUrl = addrUrl;

			urlC = url.openConnection();
			log.info("Mult 无代理");

			// 修改timeout时间，避免图片上传出错。
			urlC.setConnectTimeout(minTimeout);
			urlC.setReadTimeout(maxTimeout);
			httpcon = (HttpURLConnection) urlC;

			// 发送POST请求必须设置如下两行
			httpcon.setInstanceFollowRedirects(followRedirect);
			httpcon.setDoOutput(true);
			httpcon.setDoInput(true);
			httpcon.setUseCaches(false);
			httpcon.setRequestMethod("POST");
			httpcon.setRequestProperty("Connection", "Keep-Alive");
			httpcon.setRequestProperty("Charset", charset);
			httpcon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			// 模拟同一个浏览器
			this.loadHeaders(httpcon, httpHeaderMap);

			OutputStream out = new DataOutputStream(httpcon.getOutputStream());

			debugBuffer.append(this.printHttpHeaders(httpcon) + "\r\n");

			if (file != null && file.exists()) {
				StringBuffer sb = new StringBuffer("");
				byte[] data = sb.toString().getBytes(charset);
				out.write(data);
				DataInputStream in = new DataInputStream(new FileInputStream(file));
				int bytes = 0;
				byte[] bufferOut = new byte[1024];
				while ((bytes = in.read(bufferOut)) != -1) {
					out.write(bufferOut, 0, bytes);
				}

				// 加入随机，区别每个图片
				out.write(generateUid(3).getBytes());

				out.write("\r\n".getBytes()); // 多个文件时，二个文件之间加入这个
				in.close();
			}
			out.flush();
			out.close();
			this.saveCookies(httpcon);

			// 定义BufferedReader输入流来读取URL的响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpcon.getInputStream(), charset));
			char[] cbuffer = new char[1024 * 4];
			int length;
			while ((length = reader.read(cbuffer)) > -1) {
				rsb.append(cbuffer, 0, length);
			}
			reader.close();
		} catch (IOException e) {
			log.error("当前执行的提交文件url出现IO方面的异常: " + this.runningUrl, new SimpleException(e));
		} catch (Exception e) {
			this.handleError(e, addrUrl);
			throw new SimpleException(e);
		} finally {
			long end = System.currentTimeMillis();
			log.info("★★★★★★★★当前文件操作耗时:" + (end - start));
			if (httpcon != null) {
				httpcon.disconnect();
			}
		}
		return rsb;
	}

	/**
	 * 提交复杂的form，即multipart的form，例如上传文件等
	 *
	 * @param addrUrl
	 * @param paraMap
	 * @param httpHeaderMap
	 * @param charset
	 * @param fileMap
	 * @return
	 * @throws Exception
	 */
	public StringBuffer sendMultipartForm(String addrUrl, Map paraMap, Map httpHeaderMap, String charset, Map fileMap) throws Exception {
		HttpURLConnection httpcon = null;
		URLConnection urlC = null;
		StringBuffer debugBuffer = new StringBuffer("");// 调试用
		StringBuffer rsb = new StringBuffer("");
		long start = System.currentTimeMillis();

		try {
			log.info(" ==== addrUrl: " + addrUrl);
			String BOUNDARY = "---------------------------7da196221e0934"; // 定义数据分隔线
			URL url = new URL(addrUrl);
			this.runningUrl = addrUrl;

			// 考虑网通代理的情况

			urlC = url.openConnection();
			log.info("Mult 无代理");

			// 修改timeout时间，避免图片上传出错。
			urlC.setConnectTimeout(minTimeout);
			urlC.setReadTimeout(maxTimeout);
			httpcon = (HttpURLConnection) urlC;

			// 发送POST请求必须设置如下两行
			httpcon.setInstanceFollowRedirects(followRedirect);
			httpcon.setDoOutput(true);
			httpcon.setDoInput(true);
			httpcon.setUseCaches(false);
			httpcon.setRequestMethod("POST");
			httpcon.setRequestProperty("Connection", "Keep-Alive");
			// conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;
			// MSIE 6.0; Windows NT 5.1; SV1)");
			httpcon.setRequestProperty("Charset", charset);
			httpcon.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

			// 模拟同一个浏览器
			this.loadHeaders(httpcon, httpHeaderMap);

			OutputStream out = new DataOutputStream(httpcon.getOutputStream());

			debugBuffer.append(this.printHttpHeaders(httpcon) + "\r\n");

			// 第一步，处理input等form-data
			Iterator it = paraMap.entrySet().iterator();
			while (it.hasNext()) {
				Entry element = (Entry) it.next();
				StringBuffer sb = new StringBuffer("");
				if (element.getValue() instanceof String[]) { // 处理数组情况
					String[] values = (String[]) element.getValue();
					if (values.length > 0) {
						for (int i = 0; i < values.length; i++) {
							sb.append("--").append(BOUNDARY).append("\r\n");
							sb.append("Content-Disposition: form-data; name=\"" + element.getKey() + "\"\r\n");
							sb.append("\r\n");// 空白行
							sb.append(values[i]);
							sb.append("\r\n");
						}
					}
				} else {
					sb.append("--").append(BOUNDARY).append("\r\n");
					sb.append("Content-Disposition: form-data; name=\"" + element.getKey() + "\"\r\n");
					sb.append("\r\n");// 空白行
					sb.append(element.getValue());
					sb.append("\r\n");
				}
				out.write(sb.toString().getBytes(charset));
				debugBuffer.append(sb.toString());
			}

			// log.info("url参数为:"+buffer);

			// 第二步，开始处理文件
			if (fileMap != null) {

				// 2011-03-08新增，参数fileNameParam默认为 filename,
				// 但如果有值的话，就用参数值，如有的网站是 File Name，而不是filename
				String fileNameParam = (String) fileMap.get("fileNameParam");
				if (fileNameParam == null || fileNameParam.length() <= 0) {
					fileNameParam = "filename";
				}
				if (fileMap.containsKey("fileNameParam")) {
					fileMap.remove("fileNameParam");
				}

				Iterator fileIt = fileMap.keySet().iterator();
				while (fileIt.hasNext()) {
					String inputName = (String) fileIt.next();
					if (fileMap.get(inputName) instanceof File[]) {
						File[] files = (File[]) fileMap.get(inputName);
						if (files != null && files.length > 0) {
							for (int i = 0; i < files.length; i++) {
								File file = files[i];
								this.uploadFileToSite(file, BOUNDARY, fileNameParam, inputName, charset, debugBuffer, out);
							}
						}
					} else {
						File file = (File) fileMap.get(inputName);
						this.uploadFileToSite(file, BOUNDARY, fileNameParam, inputName, charset, debugBuffer, out);
					}
				}
			}
			String endLineData = "--" + BOUNDARY + "--\r\n";// 定义最后数据分隔线
			debugBuffer.append(endLineData);
			out.write(endLineData.getBytes());
			out.flush();
			out.close();
			this.saveCookies(httpcon);

			log.info("输入的http请求内容\n★★★★★开始★★★★★\n"+debugBuffer.toString()+"\n■■■■■■■■结束■■■■■■■■");

			// 定义BufferedReader输入流来读取URL的响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpcon.getInputStream(), charset));
			char[] cbuffer = new char[1024 * 4];
			int length;
			while ((length = reader.read(cbuffer)) > -1) {
				rsb.append(cbuffer, 0, length);
			}
			reader.close();
		} catch (IOException e) {
			log.error("当前执行的提交文件url出现IO方面的异常: " + this.runningUrl, new SimpleException(e));
		} catch (Exception e) {
			this.handleError(e, addrUrl);
			throw new SimpleException(e);
		} finally {
			long end = System.currentTimeMillis();
			log.info("★★★★★★★★当前文件操作耗时:" + (end - start));
			if (httpcon != null) {
				httpcon.disconnect();
			}
		}
		return rsb;
	}

	/**
	 * 上传图片文件流到网站(由sendMultipartForm调用)
	 *
	 * @param file
	 * @param BOUNDARY
	 * @param fileNameParam
	 * @param inputName
	 * @param charset
	 * @param debugBuffer
	 * @param out
	 * @throws Exception
	 */
	private void uploadFileToSite(File file, String BOUNDARY, String fileNameParam, String inputName, String charset, StringBuffer debugBuffer, OutputStream out) throws Exception {
		StringBuffer sb = new StringBuffer("");
		sb.append("--").append(BOUNDARY).append("\r\n");
		if (file != null && file.exists()) {
			String fileName = file.getName();
			sb.append("Content-Disposition: form-data; name=\"" + inputName + "\"; " + fileNameParam + "=\"" + fileName + "\"\r\n");
			sb.append("Content-Type: image/pjpeg\r\n");
			sb.append("\r\n");// 空白行
			byte[] data = sb.toString().getBytes(charset);
			out.write(data);
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}

			// 加入随机，区别每个图片
			out.write(generateUid(3).getBytes());

			out.write("\r\n".getBytes()); // 多个文件时，二个文件之间加入这个
			in.close();
			debugBuffer.append(sb.append("[文件字节流(略)]").append("\r\n").toString());
		} else {
			sb.append("Content-Disposition: form-data;name=\"" + inputName + "\"; " + fileNameParam + "=\"\"\r\n");
			sb.append("Content-Type:application/octet-stream\r\n");
			sb.append("\r\n");
			sb.append("\r\n");
			out.write(sb.toString().getBytes(charset));
			debugBuffer.append(sb.toString());
		}
	}

	/**
	 * 验证码的sendForm
	 *
	 * @param addrUrl
	 * @param paramMap
	 * @param httpHeaderMap
	 * @param charset
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public StringBuffer sendPicForm(String addrUrl, Map paramMap, Map httpHeaderMap, String charset, String fileName) throws Exception {
		StringBuffer buffer = new StringBuffer();

		Iterator it = paramMap.entrySet().iterator();

		while (it.hasNext()) {
			Entry ent = (Entry) it.next(); // 一个Map元素，包含Key和Value
			if (ent.getValue() == null) { // 值为空时，则略过
				continue;
			}
			buffer.append(URLEncoder.encode((String) ent.getKey(), charset));
			buffer.append("=");

			if (ent.getValue() instanceof String[]) { // 处理数组情况
				String[] values = (String[]) ent.getValue();
				if (values.length > 0) {
					for (int i = 0; i < values.length; i++) {
						if (i == 0)
							buffer.append(URLEncoder.encode(values[i], charset));
						else {
							buffer.append("&");
							buffer.append(URLEncoder.encode((String) ent.getKey(), charset));
							buffer.append("=");
							buffer.append(URLEncoder.encode(values[i], charset));
						}
					}
				}

			} else {
				buffer.append(URLEncoder.encode((String) ent.getValue(), charset)); // 单个值的情况
			}
			buffer.append("&");
		}

		// 删除buffer中的最后一个字符: &
		if (buffer.length() > 0) {
			buffer.deleteCharAt(buffer.length() - 1);
		}

		boolean isPost = false; // 默认为Get
		if (buffer.length() > 0) { // 如果 buffer 中有数据，即有数据需要传递到网站，则数据传递为 post
			isPost = true;
		}

		HttpURLConnection urlConn = null;
		URLConnection urlC = null;

		StringBuffer rsb = new StringBuffer();

		try {
			log.info(" ==== addrUrl: " + addrUrl);
			URL url = new URL(addrUrl);

			// 考虑网通代理的情况
			// Proxy proxy = NetProxy.getProxy(addrUrl);
			urlC = url.openConnection();


			// 修改timeout时间，避免图片上传出错。
			urlC.setConnectTimeout(minTimeout);
			urlC.setReadTimeout(maxTimeout);
			urlConn = (HttpURLConnection) urlC;

			if (isPost) {
				urlConn.setDoOutput(true);
				urlConn.setRequestMethod("POST");
			} else
				urlConn.setRequestMethod("GET");

			urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // 这个是根据浏览器的分析器分析后得到的数据
			urlConn.setInstanceFollowRedirects(followRedirect); // 应该是不能返回或返回无效吧

			urlConn.setUseCaches(false);

			this.loadHeaders(urlConn, httpHeaderMap);

			// 据网上资料，对于urlConn的set操作，必须在 openConnection 和这个 connect 之间完成。
			// 由于getOutputStream会隐含的进行connect，故此处可以不调用此句(测试一下)
			urlConn.connect();

			if (isPost) { // 如果是传递数据到网站
				OutputStreamWriter out = new OutputStreamWriter(urlConn.getOutputStream(), charset);
				out.write(buffer.toString());

				out.flush();
				out.close();

				log.info("上传参数: " + buffer.toString());
			}

			// 传递完成后，如果正确，应该会得到相应的返回信息
			this.saveCookies(urlConn);

			BufferedInputStream bis = new BufferedInputStream(urlConn.getInputStream());
			File file = new File(fileName);

			FileOutputStream fos = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			byte data[] = new byte[4096];//
			int size = bis.read(data);
			while (size != -1) {
				bos.write(data, 0, size);
				size = bis.read(data);
			}
			bis.close();
			bos.flush();
			bos.close();
			fos.close();

			/*
			 * BufferedReader reader = new BufferedReader(new
			 * InputStreamReader(urlConn.getInputStream(),charset)); String line
			 * = "";
			 *
			 * while((line = reader.readLine()) != null) rsb.append(line+" \n");
			 *
			 * reader.close();
			 */
		} catch (Exception e) {
			this.handleError(e, addrUrl);
			throw new SimpleException(e);
		} finally {
			if (urlConn != null)
				urlConn.disconnect();
		}

		return rsb;
	}

	/**
	 * 获取location
	 *
	 * @return
	 */
	public String getLocation() {
		String location = "";

		Map headers = this.getHeaders();
		if (headers != null && headers.size() > 0) {
			Iterator it = headers.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				if ("Location".equalsIgnoreCase(key)) {
					Collection valCol = (Collection) headers.get(key);
					String[] values = (String[]) valCol.toArray(new String[0]);
					location = values[0];
				}
			}
		}

		return location;
	}

	public String getRunningUrl() {
		return runningUrl;
	}

	public void setRunningUrl(String runningUrl) {
		this.runningUrl = runningUrl;
	}

	/**
	 * @return Returns the maxTimeout.
	 */
	public int getMaxTimeout() {
		return maxTimeout;
	}

	/**
	 * @param maxTimeout
	 *            The maxTimeout to set.
	 */
	public void setMaxTimeout(int maxTimeout) {
		this.maxTimeout = maxTimeout;
	}

	/**
	 * @return Returns the minTimeout.
	 */
	public int getMinTimeout() {
		return minTimeout;
	}

	/**
	 * @param minTimeout
	 *            The minTimeout to set.
	 */
	public void setMinTimeout(int minTimeout) {
		this.minTimeout = minTimeout;
	}


	public String getCookieStr() {
		return cookieStr;
	}

	public void setCookieStr(String cookieStr) {
		this.cookies = getCookieListFromStr(cookieStr);
		this.cookieStr = cookieStr;
	}

	private List<String> getCookieListFromStr(String cookieStr2) {
		List<String> cokieList = new ArrayList<String>();
		//__trackId=147494668774126; __sense_session_pv=3; __city=shenzhen;
		if(cookieStr2!=null && cookieStr2.length()>0){
			String[] cookieMaps = cookieStr2.split(";");
			if(cookieMaps!=null && cookieMaps.length>0){
				for(int i=0;i<cookieMaps.length;i++){
					String currCookieStr = cookieMaps[i];
					if(currCookieStr!=null && currCookieStr.length()>0 && currCookieStr.indexOf("=")>-1){
						cokieList.add(currCookieStr);
					}
				}
			}
		}
		return cokieList;
	}


	public void setCookies(List<String> cookies) {
		this.cookies = cookies;
		this.cookieStr = this.getCookieString();
	}

	public List<String> getCookies() {
		return this.cookies;
	}

	public void setHeaders(Map<String, List<String>> headers) {
		this.headers = headers;
	}

	public Map<String, List<String>> getHeaders() {
		return this.headers;
	}

	/**
	 * 保存urlConnection中Set-Cookie部分到context中，为下文url的用
	 *
	 * @param urlConn
	 */
	public void saveCookies(HttpURLConnection urlConn) {
		Map<String, List<String>> headers = urlConn.getHeaderFields();
		this.headers = headers;
		Iterator<String> it = headers.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			if (key != null) {
				List<String> headerValues = headers.get(key);
				if ("SET-COOKIE".equals(key.toUpperCase())) {
					this.appendCookies(headerValues);
				}
			}
		}
		log.info("执行之后cookie:" + this.getCookieString());
	}

	public List<String> getResponseHeader(String mKey) {

		Iterator<String> it = headers.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			if (key != null) {
				List<String> headerValues = headers.get(key);
				if (mKey.toUpperCase().equals(key.toUpperCase())) {
					return headerValues;
				}
			}
		}
		return null;
	}

	/**
	 * 手工添加一个cookie到context
	 *
	 * @param key
	 * @param value
	 */
	public void appendCookies(String key, String value) {
		List<String> cookies = this.getCookies();
		cookies.add(key + "=" + value + "");
		this.setCookies(cookies);
	}

	/**
	 * 将获取的 cookie 信息保存到 context 中，为下文url使用。
	 */
	private void appendCookies(List<String> setCookies) {
		if (setCookies != null && setCookies.size() > 0) {
			List<String> cookie = new ArrayList<String>();
			Iterator<String> oldCookieIt = this.getCookies().iterator();
			if (this.getCookies().size() > 0) {
				while (oldCookieIt.hasNext()) {
					String existCookie = oldCookieIt.next();
					// log.info("★★★★★ExistCookie:" + existCookie);
					if (existCookie != null && existCookie.length() > 0) {
						String existCookieKey = existCookie.split("=")[0].trim();
						boolean findExisted = false;
						Iterator<String> newCookieIt = setCookies.iterator();
						while (newCookieIt.hasNext()) {
							String newCookie = newCookieIt.next();
							String newCookieKey = (newCookie.split("=")[0]).trim();
							if (existCookieKey.equals(newCookieKey)) {
								// log.info("★★★★★找到老 Cookie在新的里面有,\n老:"+existCookie+"\n新:"+newCookie);
								findExisted = true;
							}
						}
						if (!findExisted) {
							cookie.add(existCookie);
							// log.info("★★★★★加老Cookie:"+existCookie);
						}
					}
				}
				cookie.addAll(setCookies);
				this.setCookies(cookie);
			} else {
				this.setCookies(setCookies);
			}
			log.info("获得url:" + this.runningUrl + "的返回stream,包含cookie一共【" + this.getCookies().size() + "】个:" + this.getThinCookieString(this.getCookies().toString()));
		}

	}

	/**
	 * 根据key移除cookie
	 *
	 * @param key
	 */
	public void removeCookie(String key) {
		List<String> newC = new ArrayList<String>();
		Iterator<String> it = this.getCookies().iterator();
		while (it.hasNext()) {
			String newObj = it.next();
			if (!newObj.toUpperCase().startsWith(key.toUpperCase() + "=")) {
				newC.add(newObj);
			}
		}
		log.info("移除cookie，长度从[" + this.getCookies().size() + "] -> [" + newC.size() + "]");
		this.setCookies(newC);
	}

	/**
	 * 根据value移除cookie
	 *
	 * @param value
	 */
	public void removeCookieByValue(String value) {
		List<String> newC = new ArrayList<String>();

		Iterator<String> it = this.getCookies().iterator();
		while (it.hasNext()) {
			String newObj = (String) it.next();
			if (newObj.toUpperCase().indexOf("=" + value.toUpperCase()) == -1) {
				newC.add(newObj);
			}
		}
		log.info("移除cookie，长度从[" + this.getCookies().size() + "] -> [" + newC.size() + "]");
		this.setCookies(newC);
	}

	/**
	 * 获取某一Cookie值
	 *
	 * @param key
	 * @return
	 */
	public String getCookieByKey(String key) {
		String value = "";
		Iterator<String> it = this.getCookies().iterator();
		while (it.hasNext()) {
			String theObj = it.next();
			String str = key + "=";
			if (theObj.startsWith(str)) {
				value = theObj.replaceAll(str, "");
				break;
			}
		}
		return value;
	}

	/*
	 * 向context加载header信息，User-Agent，Refer等等
	 */
	public void loadHeaders(HttpURLConnection urlConn, Map httpHeaderMap) {
		this.loadCookies(urlConn); // 先给连接中设置Cookie属性

		// 设置连接中的其他属性值
		if (httpHeaderMap != null && httpHeaderMap.size() > 0) {
			Iterator it = httpHeaderMap.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				String value = (String) httpHeaderMap.get(key);

				if (key != null && key.length() > 0) {
					urlConn.setRequestProperty(key, value);
				}
			}
		}

		// 固定加入模拟浏览器
		if (this.getUserAgent() != null && this.getUserAgent().length() > 0) {
			urlConn.setRequestProperty("User-Agent", this.getUserAgent());
		} else {
			urlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; .NET CLR 2.0.50727; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
		}
	}

	/**
	 * 向context中载入cookie信息。
	 *
	 * @param urlConn
	 */
	public void loadCookies(HttpURLConnection urlConn) {
		// ★★★★测试专用位置★★★★★★
		// cookieStr="www58com=\"AutoLogin=false&UserID=38302520&UserName=wjb_569&CityID=1811&Email=469907275%40qq.com&AllMsgTotal=1&CommentReadTotal=0&CommentUnReadTotal=0&MsgReadTotal=1&MsgUnReadTotal=0&RequireFriendReadTotal=0&RequireFriendUnReadTotal=0&SystemReadTotal=0&SystemUnReadTotal=0&UserCredit=60&UserScore=64&PurviewID=F&IsAgency=false&Agencys=&SiteKey=53115061EEDA8F62C05292FB8E60FDD99DE8E289AEA710FAD&Phone=&WltUrl=\"; 58cooper=\"userid=38302520&username=wjb_569&cooperkey=4731b857bb411ef09239db7192fe0c76\";Version=1;58Wlt=\"\";";
		urlConn.setRequestProperty("Cookie", this.cookieStr); // 设置连接中的 Cookie

		log.info("向即将提交的url:" + this.runningUrl + "中已加载Cookie信息: " + urlConn.getRequestProperty("Cookie"));

	}

	/**
	 * 获取http stream中的cookie字符串
	 * 第一个;表示以;开头，\\s表示空格[^;]*表示[p|P]ath=/[^;]*表示为path=/
	 * 或Path=/,但包含;(因为开头已经有了一个;号了,这个;是要用来隔开其他字符的),*表示;可以有，也可以没有
	 *
	 * @return
	 */
	public String getCookieString() {
		Iterator<String> it = this.getCookies().iterator();
		StringBuffer cookieSb = new StringBuffer();

		while (it.hasNext()) {
			String value = (String) it.next();
			value = value.replaceAll("(?i);\\s*path=/[^;]*", "");
			value = value.replaceAll("(?i);\\s*domain=[^;]*", "");
			value = value.replaceAll("(?i);\\s*expires=[^;]*", "");
			cookieSb.append(value).append("; ");
		}
		return cookieSb.toString();
	}

	/**
	 * 简化java中默认的cookie内容
	 *
	 * @param cookieStr
	 * @return
	 */
	private String getThinCookieString(String cookieStr) {
		cookieStr = cookieStr.replaceAll("(?i);\\s*path=/[^;]*", "");
		cookieStr = cookieStr.replaceAll("(?i);\\s*domain=[^;]*", "");
		cookieStr = cookieStr.replaceAll("(?i);\\s*expires=[^;]*", "");
		return cookieStr;
	}

	/**
	 * 打印http connection的属性，调试用
	 *
	 * @param httpConn
	 * @throws Exception
	 */
	private void printHttpConnProp(HttpURLConnection httpConn) {
		Map<String, List<String>> map = httpConn.getRequestProperties();
		if (map != null && map.size() > 0) {
			Iterator<String> it = map.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				if (key != null) {
					// String value = (String)map.get(key);
					log.info("HttpConn 属性: " + key + " = " + map.get(key).toString());
				}
			}
		}
	}

	/**
	 * 打印header
	 * @return
	 */
	private String printHttpHeaders(HttpURLConnection httpcon) {
		StringBuffer sb = new StringBuffer("");
		if (this.headers == null) {
			this.headers = httpcon.getHeaderFields();
		}
		Iterator it = headers.keySet().iterator();
		sb.append("[HTTP Header]:");
		while (it.hasNext()) {
			String key = (String) it.next();
			Collection value = (Collection) headers.get(key);
			sb.append(key + "=" + value + "\r\n");
		}
		return sb.toString();
	}

	/**
	 * 准备url connection各项属性
	 * @throws IOException
	 */
	private HttpURLConnection prepareUrlConnection(String addrUrl, Map headerMap , boolean isPost) throws IOException {
		HttpURLConnection conn = null;
		URLConnection urlC = null;

		URL url = new URL(addrUrl);
		log.info(addrUrl+":无代理");
		urlC = url.openConnection();


		urlC.setConnectTimeout(minTimeout);
		urlC.setReadTimeout(minTimeout);
		conn = (HttpURLConnection) urlC;

		this.loadHeaders(conn, headerMap);
		// log.info("url执行前cookieL:"+this.getCookieString());
		conn.setInstanceFollowRedirects(followRedirect);
		conn.setUseCaches(false);
		if (isPost) {
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
		} else {
			conn.setRequestMethod("GET");
		}

		return conn;
	}

	/**
	 * 在view Browers内不检查https的合法性
	 */
	public static void ignoreHttpsCheck() {
		try {
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					// TODO Auto-generated method stub
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					// TODO Auto-generated method stub
				}
			} };

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};
			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		} catch (Exception e) {
			log.error("无法跳过https的安全验证",e);
		}
	}

	/**
	 * 准备参数
	 * @param paramMap
	 * @param charset
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public StringBuffer prepareParameters(Map paramMap, String charset) throws UnsupportedEncodingException {
		StringBuffer buffer = new StringBuffer();
		Iterator it = paramMap.entrySet().iterator();

		while (it.hasNext()) {
			Entry ent = (Entry) it.next(); // 一个Map元素，包含Key和Value
			if (ent.getKey() != null && ent.getValue() != null) {
				buffer.append(URLEncoder.encode((String) ent.getKey(), charset));
				buffer.append("=");

				if (ent.getValue() instanceof String[]) { // 处理数组情况
					String[] values = (String[]) ent.getValue();
					if (values.length > 0) {
						for (int i = 0; i < values.length; i++) {
							if (i == 0)
								buffer.append(URLEncoder.encode(values[i], charset));
							else {
								buffer.append("&");
								buffer.append(URLEncoder.encode((String) ent.getKey(), charset));
								buffer.append("=");
								buffer.append(URLEncoder.encode(values[i], charset));
							}
						}
					}

				} else {
					buffer.append(URLEncoder.encode((String) ent.getValue(), charset)); // 单个值的情况
				}
				buffer.append("&");
			}
		}

		// 删除buffer中的最后一个字符: &
		if (buffer.length() > 0) {
			buffer.deleteCharAt(buffer.length() - 1);
		}
		return buffer;
	}

	/**
	 * 准备multipart的字节流，仅用于某些小字节流图片上传，否则系统高达1M的对象无法被及时回收导致资源耗尽。
	 *
	 * @param paraMap
	 * @param fileMap
	 * @return
	 */
	public StringBuffer prepareMultipartParameters(Map paraMap, Map fileMap) {
		StringBuffer sb = new StringBuffer();
		StringBuffer debugBuffer = new StringBuffer();
		String BOUNDARY = "---------------------------7da196221e0934"; // 定义数据分隔线

		// 第一步，处理input等form-data
		Iterator it = paraMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry element = (Entry) it.next();
			if (element.getValue() instanceof String[]) { // 处理数组情况
				String[] values = (String[]) element.getValue();
				if (values.length > 0) {
					for (int i = 0; i < values.length; i++) {
						sb.append("--").append(BOUNDARY).append("\r\n");
						sb.append("Content-Disposition: form-data; name=\"" + element.getKey() + "\"\r\n");
						sb.append("\r\n");// 空白行
						sb.append(values[i]);
						sb.append("\r\n");
					}
				}
			} else {
				sb.append("--").append(BOUNDARY).append("\r\n");
				sb.append("Content-Disposition: form-data; name=\"" + element.getKey() + "\"\r\n");
				sb.append("\r\n");// 空白行
				sb.append(element.getValue());
				sb.append("\r\n");
			}
			debugBuffer.append(sb.toString());
		}

		// log.info("url参数为:"+buffer);

		// 第二步，开始处理文件
		if (fileMap != null) {

			// 2011-03-08新增，参数fileNameParam默认为 filename,
			// 但如果有值的话，就用参数值，如有的网站是 File Name，而不是filename
			String fileNameParam = (String) fileMap.get("fileNameParam");
			if (fileNameParam == null || fileNameParam.length() <= 0) {
				fileNameParam = "filename";
			}
			if (fileMap.containsKey("fileNameParam")) {
				fileMap.remove("fileNameParam");
			}

			Iterator fileIt = fileMap.keySet().iterator();
			while (fileIt.hasNext()) {
				String inputName = (String) fileIt.next();
				File file = (File) fileMap.get(inputName);
				sb.append("--").append(BOUNDARY).append("\r\n");
				if (file != null && file.exists()) {
					sb.append("Content-Disposition: form-data; name=\"" + inputName + "\"; " + fileNameParam + "=\"" + file.getName() + "\"\r\n");
					sb.append("Content-Type: image/pjpeg\r\n");
					sb.append("\r\n");// 空白行
					debugBuffer.append(sb.append("[文件字节流(略)]").append("\r\n").toString());
				} else {
					sb.append("Content-Disposition: form-data;name=\"" + inputName + "\"; " + fileNameParam + "=\"\"\r\n");
					sb.append("Content-Type:application/octet-stream\r\n");
					sb.append("\r\n");
					sb.append("\r\n");
					debugBuffer.append(sb.toString());
				}
			}
		}
		String endLineData = "--" + BOUNDARY + "--\r\n";// 定义最后数据分隔线
		debugBuffer.append(endLineData);
		// log.info("当前multipart的字节流:"+debugBuffer.toString());
		return sb;
	}

	/**
	 * 准备jason的参数格式
	 * @param paramList
	 * @param charset
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public StringBuffer prepareJasonParameters(List paramList, String charset) throws UnsupportedEncodingException {
		StringBuffer buffer = new StringBuffer();
		if (paramList != null && paramList.size() > 0) {
			for (int i = 0; i < paramList.size(); i++) {
				String param = (String) paramList.get(i);
				// buffer.append(URLEncoder.encode(param,charset));
				buffer.append(param);
			}
		}
		return buffer;
	}

	/**
	 * 排序参数表，前提是必须linkedHashMap
	 *
	 * @param map
	 * @return
	 */
	public void toSortedMap(LinkedHashMap map) {
		List sortedKeys = new ArrayList(map.keySet());
		Collections.sort(sortedKeys);
		HashMap newMap = new LinkedHashMap();
		for (Object key : sortedKeys) {
			newMap.put(key, map.get(key));
		}
		map.clear();
		map.putAll(newMap);
	}

	/**
	 * 获取提交返回
	 *
	 * @param conn
	 * @param parameterBuffer
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	private StringBuffer getResponse(HttpURLConnection conn, StringBuffer parameterBuffer, String charset) throws IOException {

		StringBuffer returnBuffer = new StringBuffer();
		// 打印http request头
		// this.printHttpConnProp(conn);

		conn.connect();

		if (parameterBuffer.length() > 0) { // 如果是传递数据到网站
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), charset);
			out.write(parameterBuffer.toString());
			out.flush();
			out.close();
		}
		this.saveCookies(conn);
		String contentEncode = conn.getContentEncoding();
		InputStream inStream = conn.getInputStream();
		if (contentEncode != null && contentEncode.toLowerCase().indexOf("gzip") >= 0) {
			GZIPInputStream gzin = new GZIPInputStream(inStream);
			inStream = gzin;
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, charset));
		char[] cbuffer = new char[1024 * 4];
		int length;
		while ((length = reader.read(cbuffer)) > -1) {
			returnBuffer.append(cbuffer, 0, length);
		}
		inStream.close();
		reader.close();

		return returnBuffer;
	}

	/**
	 * 错误处理
	 *
	 * @param e
	 * @param addrUrl
	 */
	private void handleError(Exception e, String addrUrl) {
		if (this.isTimeOutErr(e)) {
			String ipInfo = "";
			log.error(" 当前执行的url超时了: " + addrUrl+ ipInfo);
		} else {
			if (addrUrl == null || addrUrl.length() <= 0) {
				log.error("当前执行的 url 为空,前一条url=[" + this.runningUrl + "]", e);
			} else {
				log.error("当前执行的 url=[" + this.runningUrl + "]", e);
			}

			if (e != null && (e.getMessage() != null && e.getMessage().indexOf("response code: 405 for URL") != -1) || (e.getCause() != null && e.getCause().getMessage() != null && e.getCause().getMessage().indexOf("response code: 405 for URL") != -1)) {
				log.error("不能识别的方式:" + addrUrl);
			}
		}
	}

	/**
	 * 判断是否为连接超时的错误
	 *
	 * @param e
	 * @return
	 */
	private boolean isTimeOutErr(Exception e) {
		boolean flag = false;
		if (e.getMessage() != null) {
			if (e.getMessage().indexOf(" timed out") != -1 || e.getMessage().indexOf(" unreachable") != -1) {
				flag = true;
			} else if (e.getCause() != null && e.getCause().getMessage() != null && (e.getCause().getMessage().indexOf(" time out") != -1 || e.getCause().getMessage().indexOf(" unreachable") != -1)) {
				flag = true;
			}
		} else if (e.getCause() != null && e.getCause().getMessage() != null) {
			if (e.getCause().getMessage().indexOf("405 for URL") != -1) {
				flag = true;
			} else if (e.getCause().getMessage().indexOf(" timed out") != -1 || e.getCause().getMessage().indexOf(" unreachable") != -1) {
				flag = true;
			}
		}

		return flag;
	}

	/**
	 * 检查http状态
	 * @param addrUrl
	 * @return
	 */
	public boolean isHttpExist(String addrUrl) {
		HttpURLConnection conn = null;
		URLConnection urlC = null;
		InputStream inStream = null;
		BufferedReader reader = null;
		try {

			URL url = new URL(addrUrl);
			log.info("无代理");
			urlC = url.openConnection();

			urlC.setConnectTimeout(3000);
			urlC.setReadTimeout(3000);
			conn = (HttpURLConnection) urlC;

			// log.info("url执行前cookieL:"+this.getCookieString());
			conn.setInstanceFollowRedirects(followRedirect);
			conn.setUseCaches(false);
			conn.setRequestMethod("GET");
			conn.connect();
			inStream = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inStream));
			char[] cbuffer = new char[16];
			if (reader.read(cbuffer) > -1) {
				return true;
			}
		} catch (Exception e) {
			log.error("在判断url=" + addrUrl + "是否存在的时候报错，忽略详细错误");
		} finally {
			IOUtils.closeQuietly(inStream);
			IOUtils.closeQuietly(reader);
			if (conn != null) {
				conn.disconnect();
			}
		}
		return false;
	}

	public int getLastProxyPeerId() {
		return lastProxyPeerId;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setBean(ViewBrowersBean bean) {
		this.bean = bean;
	}

	public ViewBrowersBean getBean() {
		return bean;
	}

	public boolean isFollowRedirect() {
		return followRedirect;
	}

	public void setFollowRedirect(boolean followRedirect) {
		this.followRedirect = followRedirect;
	}


	public String generateUid(int length) {
		String characters = "abcdefghijklmnopqrstuvwzyz0123456789";
		Random rng = new Random();
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rng.nextInt(characters.length()));
		}
		return new String(text);
	}

	public static void main(String[] args) {
		ViewBrowers vb = new ViewBrowers(new ViewBrowersBean());
		long start = System.currentTimeMillis();
		try {
			HashMap paraMap = new HashMap();
			//paraMap.put("", "");
			StringBuffer sb = vb.sendForm("http://edqpcsapp.hnrcc.bank:17011/QPCS/logon.action", paraMap, "utf-8");
			//log.info(sb);
			List<String> cookies=vb.getCookies();
			String cookiestr=vb.getCookieStr();
			log.info(cookiestr);


			long end = System.currentTimeMillis();
			log.info("成功:" + (end - start));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			long end = System.currentTimeMillis();
			log.info("失败:" + (end - start));
		}
	}

}


