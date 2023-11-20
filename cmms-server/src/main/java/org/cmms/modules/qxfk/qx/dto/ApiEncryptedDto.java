/*
 * Copyright (c) , ssit-xm Ltd. All rights reserved.
 * Use is subject to license terms.
 */
package org.cmms.modules.qxfk.qx.dto;

/**
 * <pre>
 * 用于API接口安全传输的DTO
 * </pre>
 *
 * @author qiongwei.cai 2018年10月19日
 */
public class ApiEncryptedDto {

	/** appId */
	private String appId;

	/** 业务数据,转为JSON字符串 */
	private String body;

	/** 当前时间戳,默认允许10分钟的误差,超过十分钟则该请求无效 */
	private String timestamp;

	/** 每次请求时随机生成一个uuid字符串 */
	private String nonce;

	/** 接口签名 */
	private String sign;

	/**
	 * 获取appId
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * 设置appId
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * 获取body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * 设置body
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * 获取sign
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * 设置sign
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}

	/**
	 * 获取timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * 设置timestamp
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * 获取nonce
	 */
	public String getNonce() {
		return nonce;
	}

	/**
	 * 设置nonce
	 */
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

}
