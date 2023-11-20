/*
 * Copyright (c) , ssit-xm Ltd. All rights reserved.
 * Use is subject to license terms.
 */
package org.cmms.modules.qxfk.qx.util;



import org.cmms.modules.qxfk.qx.constant.AppConstant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 签名工具类
 * </pre>
 *
 * @author qiongwei.cai 2019年1月16日
 */
public class SignUtil {

	/**
	 * 校验签名,使用参数map里面sign进行比较
	 * 
	 * @author qiongwei.cai 2017年9月16日下午3:29:07
	 * @param map 要加密的map.建议使用Map<String,String> 实际是将map里的value
	 *            toString()拼接,若使用复杂对象将会导致加密的结果不一致!
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean verifySign(Map map) {
		return verifySign(map, null);
	}

	/**
	 * 校验签名,使用参数map里面sign进行比较
	 * 
	 * @author qiongwei.cai 2017年9月16日下午3:29:07
	 * @param map 要加密的map.建议使用Map<String,String> 实际是将map里的value
	 *            toString()拼接,若使用复杂对象将会导致加密的结果不一致!
	 * @param sign 传过来的签名
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean verifySign(Map map, String sign) {
		return verifySign(map, sign, null);
	}

	/**
	 * 校验签名
	 * 
	 * @author qiongwei.cai 2017年9月16日下午3:29:07
	 * @param map 要加密的map.建议使用Map<String,String> 实际是将map里的value
	 *            toString()拼接,若使用复杂对象将会导致加密的结果不一致!
	 * @param sign 传过来的签名,可为空,若为空则使用参数map里面sign进行比较
	 * @param salt 加密盐
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean verifySign(Map map, String sign, String salt) {
		if (BaseUtil.isEmpty(sign) && BaseUtil.isNotEmpty(map)) {
			sign = BaseUtil.toString(map.get("sign"));
		}
		String newSign = getSign(map, salt);
		return newSign.equals(sign);
	}

	/**
	 * 获取签名
	 * 
	 * @author qiongwei.cai 2017年9月16日下午3:11:44
	 * @param map 要加密的map.建议使用Map<String,String> 实际是将map里的value
	 *            toString()拼接,若使用复杂对象将会导致加密的结果不一致!
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getSign(Map map) {
		return getSign(map, null);
	}

	/**
	 * 获取签名
	 * 
	 * @author qiongwei.cai 2017年9月16日下午3:11:44
	 * @param map 要加密的map.建议使用Map<String,String> 实际是将map里的value
	 *            toString()拼接,若使用复杂对象将会导致加密的结果不一致!
	 * @param salt 加密盐
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getSign(Map map, String salt) {
		StringBuilder sb = prepareMap(map);
		if (BaseUtil.isNotEmpty(salt)) {
			sb.append(BaseUtil.toString(salt));
		}
		return EncryptUtil.md5(sb.toString());
	}

	/**
	 * 对map进行排序,并把key+value拼接到字符串中(只拼接value不为空并且不为"sign"的数据)
	 * 
	 * @author qiongwei.cai 2017年9月16日下午2:40:28
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static StringBuilder prepareMap(Map map) {
		StringBuilder sb = new StringBuilder();
		if (BaseUtil.isNotEmpty(map)) {
			List<String> keys = new ArrayList<String>(map.keySet());
			Collections.sort(keys);

			for (String key : keys) {
				Object value = map.get(key);
				// 只拼接value不为空并且不为"sign"的数据
				if (BaseUtil.isNotEmpty(value) && !AppConstant.SIGN.equals(key)) {
					sb.append(key + value + AppConstant.SPLIT_VERTICAL);
				}
			}
		}
		return sb;
	}

}
