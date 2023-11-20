package org.cmms.common.util;

import com.google.gson.Gson;

import java.util.HashMap;

/**
 * 需要proxy的request请求内容
 * @author John
 *
 */
public class HttpClientConfigJson {
	
	private long id;
	private String url;
	private String charset;
	private String method;
	private HashMap<String, String> headers;
	private String paras;
	private String regx;
	private boolean isGzipSupported=false;
	
	public String toJson(){
		return new Gson().toJson(this);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HashMap<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}

	public String getParas() {
		return paras;
	}

	public void setParas(String paras) {
		this.paras = paras;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRegx() {
		return regx;
	}

	public void setRegx(String regx) {
		this.regx = regx;
	}

	public boolean isGzipSupported() {
		return isGzipSupported;
	}

	public void setGzipSupported(boolean isGzipSupported) {
		this.isGzipSupported = isGzipSupported;
	}
}
