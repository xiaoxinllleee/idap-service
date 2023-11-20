package org.cmms.common.system.controller;

import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import net.sf.saxon.trans.SymbolicName;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.api.ISysBaseAPI;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @Author scott
 * @since 2018-12-20
 */
@Slf4j
@RestController
@RequestMapping("/sys/common")
public class CommonController {

	@Autowired
	private ISysBaseAPI sysBaseAPI;

	@Value(value = "${common.path.upload}")
	private String uploadpath;
	@Value(value = "${common.report.dataPath}")
	private String dataPath;

	/**
	 * 本地：local minio：minio 阿里：alioss
	 */
	@Value(value="${common.uploadType}")
	private String uploadType;

	private enum AllowedFileType {
		PNG("89504E47", "png"), JPG("FFD8FF", "jpg"),
		GIF("47494638", "gif"), BMP("424D", "bmp"),
		ZIP("504B0304", "zip"), XLS("D0CF11E0A1B11AE", "xls"), PDF("255044462D312E", "pdf"),
		MP3("FFF348C4","mp3");

		private String code;
		private String type;

		AllowedFileType(String code, String type) {
			this.code = code;
			this.type = type;
		}
	}

	private enum AllowedFileExt {
		PNG("png"), JPG("jpg"),GIF("gif"), BMP("bmp"),
		DOC("doc"), DOCX("docx"),XLS("xls"), XLSX("xlsx"),
		PPT("ppt"), PPTX("pptx"),APK("apk"), WGT("wgt"), PDF("pdf"),ZIP("zip"),
		MP3("mp3"),AU("au"),WAV("wav"),AIFF("aiff"),AIF("aif");

		private String ext;

		AllowedFileExt(String ext) {
			this.ext = ext;
		}
	}

	/**
	 * @Author 政辉
	 * @return
	 */
	@GetMapping("/403")
	public Result<?> noauth()  {
		return Result.error("没有权限，请联系管理员授权");
	}

	@PostMapping(value = "/upload")
	public Result<?> upload(HttpServletRequest request, HttpServletResponse response) {
		Result<?> result = new Result<>();
		String savePath = "";
		String bizPath = request.getParameter("biz");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("file");// 获取上传文件对象
		if(oConvertUtils.isEmpty(bizPath)){
			if(CommonConstant.UPLOAD_TYPE_OSS.equals(uploadType)){
				result.setMessage("使用阿里云文件上传时，必须添加目录！");
				result.setSuccess(false);
				return result;
			}else{
				bizPath = "";
			}
		}
		if(CommonConstant.UPLOAD_TYPE_LOCAL.equals(uploadType)){
			savePath = this.uploadLocal(file,bizPath);
		}else{
			savePath = sysBaseAPI.upload(file,bizPath,uploadType);
		}
		if(oConvertUtils.isNotEmpty(savePath)){
			result.setMessage(savePath);
			result.setSuccess(true);
		}else {
			result.setMessage("上传失败！");
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 本地文件上传
	 * @param mf 文件
	 * @param bizPath  自定义路径
	 * @return
	 */
	private String uploadLocal(MultipartFile mf,String bizPath){
		try {
			String extName = FileUtil.extName(mf.getOriginalFilename());

			System.out.println("mf.getName" + mf.getOriginalFilename());
			String fileType = IoUtil.readHex28Upper(mf.getInputStream());
			System.out.println("fileType" + fileType);
			boolean allowed = true;
			/*for (AllowedFileExt item : AllowedFileExt.values()) {
				if (!StringUtils.isEmpty(extName) && extName.equalsIgnoreCase(item.ext)) {
					allowed = true;
					break;
				}
			}*/
		/*	if (!allowed) {
				log.info("非法的文件上传扩展名：" + extName);
				return "";
			}*/
			//allowed = false;
			/*for (AllowedFileType item : AllowedFileType.values()) {
				if (!StringUtils.isEmpty(fileType) && fileType.toUpperCase().startsWith(item.code)) {
					allowed = true;
					break;
				}
			}*/
			/*if (!allowed) {
				log.info("非法的文件上传类型：" + fileType);
				return "";
			}*/
			String ctxPath = uploadpath;
			String fileName = null;
			File file = new File(ctxPath + File.separator + bizPath + File.separator );
			if (!file.exists()) {
				file.mkdirs();// 创建文件根目录
			}
			String orgName = mf.getOriginalFilename();// 获取文件名
			fileName = orgName.substring(0, orgName.lastIndexOf(".")) + "_" + System.currentTimeMillis() + orgName.substring(orgName.indexOf("."));
			String savePath = file.getPath() + File.separator + fileName;
			File savefile = new File(savePath);
			FileCopyUtils.copy(mf.getBytes(), savefile);
			String dbpath = null;
			if(oConvertUtils.isNotEmpty(bizPath)){
				dbpath = bizPath + File.separator + fileName;
			}else{
				dbpath = fileName;
			}
			if (dbpath.contains("\\")) {
				dbpath = dbpath.replace("\\", "/");
			}
			return dbpath;
		} catch (Throwable e) {
			log.error("上传文件异常:" + e.getMessage(), e);
		}
		return "";
	}

//	@PostMapping(value = "/upload2")
//	public Result<?> upload2(HttpServletRequest request, HttpServletResponse response) {
//		Result<?> result = new Result<>();
//		try {
//			String ctxPath = uploadpath;
//			String fileName = null;
//			String bizPath = "files";
//			String tempBizPath = request.getParameter("biz");
//			if(oConvertUtils.isNotEmpty(tempBizPath)){
//				bizPath = tempBizPath;
//			}
//			String nowday = new SimpleDateFormat("yyyyMMdd").format(new Date());
//			File file = new File(ctxPath + File.separator + bizPath + File.separator + nowday);
//			if (!file.exists()) {
//				file.mkdirs();// 创建文件根目录
//			}
//			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//			MultipartFile mf = multipartRequest.getFile("file");// 获取上传文件对象
//			String orgName = mf.getOriginalFilename();// 获取文件名
//			fileName = orgName.substring(0, orgName.lastIndexOf(".")) + "_" + System.currentTimeMillis() + orgName.substring(orgName.indexOf("."));
//			String savePath = file.getPath() + File.separator + fileName;
//			File savefile = new File(savePath);
//			FileCopyUtils.copy(mf.getBytes(), savefile);
//			String dbpath = bizPath + File.separator + nowday + File.separator + fileName;
//			if (dbpath.contains("\\")) {
//				dbpath = dbpath.replace("\\", "/");
//			}
//			result.setMessage(dbpath);
//			result.setSuccess(true);
//		} catch (IOException e) {
//			result.setSuccess(false);
//			result.setMessage(e.getMessage());
//			log.error(e.getMessage(), e);
//		}
//		return result;
//	}

	/**
	 * 预览图片
	 * 请求地址：http://localhost:8080/common/view/{user/20190119/e1fe9925bc315c60addea1b98eb1cb1349547719_1547866868179.jpg}
	 *
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/static/**")
	public void view(HttpServletRequest request, HttpServletResponse response) {
		log.info("come in /static/**");
		// ISO-8859-1 ==> UTF-8 进行编码转换
		String imgPath = extractPathFromPattern(request);
		String old = null;
		if (imgPath.contains("jhcredit")){
			old = imgPath;
		}
		// 其余处理略
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			imgPath = imgPath.replace("..", "");

			if (imgPath.endsWith(",")) {
				imgPath = imgPath.substring(0, imgPath.length() - 1);

			}

			//String filePath = op + imgPath;
			String filePath = uploadpath + File.separator + imgPath;
			String downloadFilePath = uploadpath + File.separator + filePath;
			log.info("当前访问文件完整路径{}",downloadFilePath);
			File file = new File(downloadFilePath);

			if (old != null){
				file = new File("/"+old);
				log.info("old = {}",old);
				filePath = "/"+old;
			}
			response.setContentType("application/force-download");// 设置强制下载不打开
			response.addHeader("Content-Disposition", "attachment;fileName=" + new String(file.getName().getBytes("UTF-8"),"iso-8859-1"));
			inputStream = new BufferedInputStream(new FileInputStream(filePath));
			outputStream = response.getOutputStream();
			byte[] buf = new byte[1024];
			int len;
			while ((len = inputStream.read(buf)) > 0) {
				outputStream.write(buf, 0, len);
			}
			response.flushBuffer();
		} catch (IOException e) {
			log.error("预览文件失败" + e.getMessage());
			// e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
		}

	}
	/**
	 * 下载文件
	 * 请求地址：http://localhost:8080/common/download/{user/20190119/e1fe9925bc315c60addea1b98eb1cb1349547719_1547866868179.jpg}
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@GetMapping(value = "/download/**")
	public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String localPath = uploadpath;
		this.doDownload(request, response, localPath);
	}

	@GetMapping(value = "/downloadReport/**")
	public void downloadReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String localPath = dataPath;
		this.doDownload(request, response, localPath);

	}
	/**
	 * 下载文件
	 * 请求地址：http://localhost:8080/common/download/{user/20190119/e1fe9925bc315c60addea1b98eb1cb1349547719_1547866868179.jpg}
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void doDownload(HttpServletRequest request, HttpServletResponse response, String localPath) throws Exception {
		log.info("===开始 下载 文件===");
		// ISO-8859-1 ==> UTF-8 进行编码转换
		String filePath = extractPathFromPattern(request);
		// 其余处理略
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			filePath = filePath.replace("..", "");
			if (filePath.endsWith(",")) {
				filePath = filePath.substring(0, filePath.length() - 1);
			}
			String downloadFilePath = localPath + File.separator + filePath;
			File file = new File(downloadFilePath);
			log.info("当前地址{}",downloadFilePath);
			if (file.exists()) {
				response.setContentType("application/force-download");// 设置强制下载不打开            
				response.addHeader("Content-Disposition", "attachment;fileName=" + new String(file.getName().getBytes("UTF-8"),"iso-8859-1"));
				inputStream = new BufferedInputStream(new FileInputStream(file));
				outputStream = response.getOutputStream();
				byte[] buf = new byte[1024];
				int len;
				while ((len = inputStream.read(buf)) > 0) {
					outputStream.write(buf, 0, len);
				}
				response.flushBuffer();
			}

		} catch (Exception e) {
			log.info("文件下载失败" + e.getMessage());
			// e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}


	/**
	 * 通过完整路径下载
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@GetMapping(value = "/downloadReportByFileName")
	public void downloadReportByFileName(HttpServletRequest request, HttpServletResponse response, String fileName) throws Exception {
		log.info("===开始 下载 文件===");
		// ISO-8859-1 ==> UTF-8 进行编码转换
		// 其余处理略
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			String downloadFilePath = dataPath+ File.separator+fileName;
			File file = new File(downloadFilePath);
			log.info("当前地址{}",downloadFilePath);
			if (file.exists()) {
				response.setContentType("application/force-download");// 设置强制下载不打开            
				response.addHeader("Content-Disposition", "attachment;fileName=" + new String(file.getName().getBytes("UTF-8"),"iso-8859-1"));
				inputStream = new BufferedInputStream(new FileInputStream(file));
				outputStream = response.getOutputStream();
				byte[] buf = new byte[1024];
				int len;
				while ((len = inputStream.read(buf)) > 0) {
					outputStream.write(buf, 0, len);
				}
				response.flushBuffer();
			}

		} catch (Exception e) {
			log.info("文件下载失败" + e.getMessage());
			// e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 预览图片
	 * 请求地址：http://localhost:8080/common/view/{user/20190119/e1fe9925bc315c60addea1b98eb1cb1349547719_1547866868179.jpg}
	 *
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/downLoadJmWj/**")
	public void downLoadJmWj(HttpServletRequest request, HttpServletResponse response,@RequestParam(name = "passWord", defaultValue = "123456") String passWord) {
		log.info("come in /static/**");
		// ISO-8859-1 ==> UTF-8 进行编码转换
		String wjPath = extractPathFromPattern(request);

		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			wjPath = wjPath.replace("..", "");

			if (wjPath.endsWith(",")) {
				wjPath = wjPath.substring(0, wjPath.length() - 1);

			}
			System.out.println("wjPath:"+wjPath);
			//String filePath = uploadpath + File.separator + wjPath;
			String filePath = wjPath;
			String jmhwjlj = "";
			System.out.println("filePath:"+filePath);
			System.out.println("jmqwjlj:"+filePath);
			//String jmhwjlj = uploadpath  + "/xddazlpdf"+"/passWordPdf/"+System.currentTimeMillis()+".pdf";
			if (filePath.endsWith(".png")){
				jmhwjlj = filePath+".png";
				doEncryptPdf(filePath,jmhwjlj,passWord);
				System.out.println("jmhwjlj:"+jmhwjlj);
			}else if (filePath.endsWith(".pdf")){
				jmhwjlj = filePath + ".pdf";
				doEncryptPdf(filePath,jmhwjlj,passWord);
				System.out.println("jmhwjlj:"+jmhwjlj);
			}



			File file = new File(jmhwjlj);

			response.setContentType("application/force-download");// 设置强制下载不打开
			response.addHeader("Content-Disposition", "attachment;fileName=" + new String(file.getName().getBytes("UTF-8"),"iso-8859-1"));
			inputStream = new BufferedInputStream(new FileInputStream(jmhwjlj));
			outputStream = response.getOutputStream();
			byte[] buf = new byte[1024];
			int len;
			while ((len = inputStream.read(buf)) > 0) {
				outputStream.write(buf, 0, len);
			}
			response.flushBuffer();
		} catch (IOException e) {
			log.error("预览文件失败" + e.getMessage());
			// e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
		}

	}

	/**
	 * 预览图片
	 * 请求地址：http://localhost:8080/common/view/{user/20190119/e1fe9925bc315c60addea1b98eb1cb1349547719_1547866868179.jpg}
	 *
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/downLoadJmtqWj/**")
	public Result<?> downLoadJmtqWj(HttpServletRequest request, HttpServletResponse response, @RequestParam(name="ymbegin", defaultValue = "1")Integer ymbegin,@RequestParam(name="ymend", defaultValue = "2")Integer ymend,@RequestParam(name = "wjlj")String wjPath) {
		log.info("come in /static/**");
		//String respdfFile =getRequest().getParameter("pdfFilePath");

//		try {
//			respdfFile = URLDecoder.decode(respdfFile,"UTF-8");
//		} catch (Throwable tx) {
//
//		}
		//int begin=Integer.parseInt(getRequest().getParameter("begin"));
		//int end =Integer.parseInt(getRequest().getParameter("end"));

		String savepathtq = uploadpath + "//" +"tqhpdf";
		//String wjPath = extractPathFromPattern(request);
		String filePath = wjPath;
		String savepath="";
		if (!new File(savepathtq).exists()) {
			new File(savepathtq).mkdirs();
		}
		Document document = null;
		PdfCopy copy = null;
		try {
			PdfReader reader = new PdfReader(wjPath);
			int n = reader.getNumberOfPages();
			if(ymend>n){
				ymend=n;
			}
			//savepath = filePath.substring(0,filePath.lastIndexOf("/"))+"_"+ymbegin+"_"+ymend+".pdf";
			savepath = savepathtq+filePath.substring(filePath.lastIndexOf("/"),filePath.length());
			document = new Document(reader.getPageSize(1));
			copy = new PdfCopy(document, new FileOutputStream(savepath));
			document.open();
			for(int j=ymbegin; j<=ymend; j++) {
				document.newPage();
				PdfImportedPage page = copy.getImportedPage(reader, j);
				copy.addPage(page);
			}
			//downLoadJmWj(password);
			document.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(DocumentException e) {
			e.printStackTrace();
		}
		String data = "{"
				+ "\"savepath\":" + "\"" + savepath + "\"" + "}";
		//return ajaxDone(200, "aaa", "", false, false, data);
		return Result.ok("提取成功", savepath);

	}



	public  void doEncryptPdf(String sFilePath,String tFilePath,String PdfPassword ) {
		FileInputStream fis;
		FileOutputStream fos;
		String glymima=PdfPassword+"admin";
		try {
			fis = new FileInputStream(new File(sFilePath));
			fos = new FileOutputStream(new File(tFilePath));
			PdfReader.unethicalreading = true;
			PdfReader reader = new PdfReader(fis);
			PdfStamper stamper = new PdfStamper(reader, fos);
			if(PdfPassword!=null&&!"".equals(PdfPassword)){
				stamper.setEncryption(PdfPassword.getBytes(), glymima.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
			}
			stamper.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 * @功能：pdf预览Iframe
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/pdf/pdfPreviewIframe")
	public ModelAndView pdfPreviewIframe(ModelAndView modelAndView) {
		modelAndView.setViewName("pdfPreviewIframe");
		return modelAndView;
	}

	/**
	 *  把指定URL后的字符串全部截断当成参数
	 *  这么做是为了防止URL中包含中文或者特殊字符（/等）时，匹配不了的问题
	 * @param request
	 * @return
	 */
	private static String extractPathFromPattern(final HttpServletRequest request) {
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
	}



	@GetMapping("/static/png/{fileName}")
	public void view(@PathVariable String fileName, HttpServletResponse response) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		String pre = "static/";
		try {
			Resource resource = new ClassPathResource(pre+fileName);

			response.setContentType("application/force-download");// 设置强制下载不打开
			response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"),"iso-8859-1"));
			inputStream = resource.getInputStream();
			outputStream = response.getOutputStream();
			byte[] buf = new byte[1024];
			int len;
			while ((len = inputStream.read(buf)) > 0) {
				outputStream.write(buf, 0, len);
			}
			response.flushBuffer();
		} catch (IOException e) {
			log.error("预览文件失败" + e.getMessage());
			// e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
		}

	}
}
