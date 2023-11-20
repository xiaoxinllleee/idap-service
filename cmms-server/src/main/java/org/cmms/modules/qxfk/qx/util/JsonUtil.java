package org.cmms.modules.qxfk.qx.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.cmms.modules.qxfk.qx.constant.AppConstant;

/**
 * <pre>
 * ClssName: JsonUtil 
 * JSON工具类
 * </pre>
 *
 * @author qiongwei.cai
 * @since 1.8
 * @version $ Id:JsonUtil.java 1.0 2017年9月14日下午8:17:19 $
 */
public class JsonUtil {
	public static final MyObjectMapper OBJECT_MAPPER = MyObjectMapper.getInstance();

	/**
	 * 对象转JSON
	 * 
	 * @author qiongwei.cai 2017年9月14日下午2:45:32
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		try {
			return OBJECT_MAPPER.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("对象转json字符串失败", e);
		}
	}

	/**
	 * JSON转对象
	 * 
	 * @author qiongwei.cai 2017年9月14日下午2:44:53
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public static <T> T toObject(String jsonStr, Class<T> clazz) {
		try {
			return OBJECT_MAPPER.readValue(jsonStr, clazz);
		} catch (IOException e) {
			throw new RuntimeException("json字符串转对象失败", e);
		}
	}

	/**
	 * JSON转Collection对象
	 * 
	 * @author qiongwei.cai 2017年9月14日下午8:01:04
	 * @param json JSON字符串
	 * @param typeReference 如：new TypeReference<Map<Integer, Object>>(){}
	 * @return
	 */
	public static <T> T toCollection(String json, TypeReference<T> typeReference) {
		try {
			return OBJECT_MAPPER.readValue(json, typeReference);
		} catch (IOException e) {
			throw new RuntimeException("json转Collection对象失败", e);
		}
	}
	
	public static String getYwdate(String zjhm,String sjhm,String dev,String name,String custType){

		Map<String,Object> stringMap = new HashMap<>();
		String templateId = AppConstant.TEMPLATE_ID_TEST;
		if (AppConstant.PROD.equals(dev)){
			templateId = AppConstant.TEMPLATE_ID_PROD;
		}
		String reportType = "60010001";
		if (StringUtils.isNotBlank(custType) && "2".equals(custType)){
			reportType = "60010002";
		}

		String appId = AppConstant.ACCOUNT_TEST;
		if (AppConstant.PROD.equals(dev)){
			appId = AppConstant.ACCOUNT_PROD;
		}
		String key = AppConstant.API_KEY_TEST;
		if (AppConstant.PROD.equals(dev)){
			key = AppConstant.APU_KEY_PROD;
		}
		String sign = AppConstant.SIGN_SALT_TEST;
		if (AppConstant.PROD.equals(dev)){
			sign = AppConstant.SIGN_SALT_PROD;
		}
		try {
			//先组装业务数据
			Map<String,String> map = new HashMap<>();
			map.put("templateId",templateId);
			map.put("name",name);
			map.put("mobile",sjhm);
			map.put("idCard",zjhm);
			map.put("reportType",reportType);
			String ywDataJosn = JSONUtil.toJsonStr(map);

			stringMap.put("appId",appId);
			stringMap.put("body",AESUtil.encrypt(ywDataJosn,key));
			stringMap.put("timestamp",Long.toString(System.currentTimeMillis()));
			stringMap.put("nonce", BaseUtil.uuid());
			stringMap.put("sign", SignUtil.getSign(stringMap,sign));
		}catch (Exception e){
			e.printStackTrace();
		}
		return JsonUtil.toJson(stringMap);
	}

	public static String getPdfData(String reportCode,String dev){
		Map<String,String> map = new HashMap<>();
		map.put("reportCode",reportCode);
		String ywDataJosn = JSONUtil.toJsonStr(map);
		Map<String,Object> stringMap = new HashMap<>();

		String appId = AppConstant.ACCOUNT_TEST;
		if (AppConstant.PROD.equals(dev)){
			appId = AppConstant.ACCOUNT_PROD;
		}
		String key = AppConstant.API_KEY_TEST;
		if (AppConstant.PROD.equals(dev)){
			key = AppConstant.APU_KEY_PROD;
		}
		String sign = AppConstant.SIGN_SALT_TEST;
		if (AppConstant.PROD.equals(dev)){
			sign = AppConstant.SIGN_SALT_PROD;
		}

		try {
			stringMap.put("appId",appId);
			stringMap.put("body",AESUtil.encrypt(ywDataJosn,key));
			stringMap.put("timestamp",Long.toString(System.currentTimeMillis()));
			stringMap.put("nonce", BaseUtil.uuid());
			stringMap.put("sign", SignUtil.getSign(stringMap,sign));
		}catch (Exception e){
			e.printStackTrace();
		}
		return JsonUtil.toJson(stringMap);
	}
}