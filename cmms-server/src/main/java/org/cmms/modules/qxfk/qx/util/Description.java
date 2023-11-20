/*
 * Copyright (c) , ssit-xm Ltd. All rights reserved.
 * Use is subject to license terms.
 */
package org.cmms.modules.qxfk.qx.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 签名的伪代码实现
 * </pre>
 *
 * @author qiongwei.cai 2019年1月18日
 */
public class Description {

	/**
	 * 代码描述如何使用,不要使用该方法,该方法只是为了说明实现原理写的伪代码
	 *
	 * @author qiongwei.cai 2019年1月18日
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void description() throws Exception {
		String bizData = "...业务数据,JSON字符串...";// 模拟的业务数据,格式为JSON字符串

		Map<String, String> reqParm = new HashMap<>();
		reqParm.put("appId", "appId,由七星提供");// appId,由七星提供
		reqParm.put("body", AESUtil.encrypt(bizData, "aesKey,由七星提供"));// 将业务数据加密
		reqParm.put("timestamp", Long.toString(System.currentTimeMillis()));// 时间戳字符串格式
		reqParm.put("nonce", BaseUtil.uuid());// 生成一个32位uuid
		reqParm.put("sign", SignUtil.getSign(reqParm, "签名混淆盐,由七星提供"));// 生成签名
		// 将reqParm发送给七星即可
	}

	/**
	 * 代码描述生成签名,不要使用该方法,该方法只是为了说明实现原理写的伪代码
	 *
	 * @author qiongwei.cai 2019年1月18日
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void descriptionGetSign() throws Exception {
		Map<String, String> reqParm = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		List<String> keys = new ArrayList<String>(reqParm.keySet());
		Collections.sort(keys);// 按照参数名称进行字母序排序
		for (String key : keys) {
			String value = reqParm.get(key);
			// 只拼接value不为空并且不为"sign"的数据
			if (BaseUtil.isNotEmpty(value) && !"sign".equals(key)) {
				sb.append(key + value + "|");
			}
		}
		sb.append("salt,签名混淆盐,该值由七星提供");
		String sign = EncryptUtil.md5(sb.toString());
		// sign即是签名
	}
}
