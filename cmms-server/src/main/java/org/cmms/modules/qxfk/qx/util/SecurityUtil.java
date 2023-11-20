package org.cmms.modules.qxfk.qx.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import org.cmms.modules.qxfk.qx.dto.ApiEncryptedDto;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 签名生成与校验 和 加密工具类
 * </pre>
 *
 * @author qiongwei.cai
 * @since 1.8
 * @version 1.0 2017年9月16日下午3:33:27
 */
public class SecurityUtil {

	/** 客户端和服务器允许的误差时间:10分钟的毫秒数 */
	public static long DEF_ALLOW_TIME_MARGIN = 600000;

	/** 生成签名盐 */
	public static String ENCRYPT_SIGN_SALT = "b41f9a3920df481d8362f11e100652d4";
	/** aesKey,应为16位.若使用了更长的key,则需要替换AES无限策略文件 */
	public static String ENCRYPT_AES_KEY = "259beb9c6c8b4453";

	/**
	 * 示例代码 DEMO
	 *
	 * @author qiongwei.cai 2019年1月16日
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// 模拟创建一个业务数据,可以是java bean或map
		String ywDate ="{\"templateId\":\"M000110\",\"name\":\"苗晓燕\",\"mobile\":\"18390525636\",\"idCard\":\"341424202010010630\",\"reportType\":\"60010001}\"";
		System.out.println(ywDate);
		Map<String,Object> stringMap = new HashMap<>();
		stringMap.put("appId","hnlynsh0080");
		stringMap.put("body", AESUtil.encrypt(ywDate,"259beb9c6c8b4453"));
		stringMap.put("timestamp",Long.toString(System.currentTimeMillis()));
		stringMap.put("nonce", BaseUtil.uuid());
		stringMap.put("sign", SignUtil.getSign(stringMap,"b41f9a3920df481d8362f11e100652d4"));


		// 示例:发起请求-创建加密dto,将这个dto作为http请求的body发送即可
		ApiEncryptedDto dto = SecurityUtil.createEncryptDto(stringMap, "hnlynsh0080", ENCRYPT_SIGN_SALT, ENCRYPT_AES_KEY);
		System.out.println(dto.getBody());

		String post1 = HttpUtil.post("http://uatapi.7starlab.com/api/product/proApply.do", dto.getBody());
		System.out.println(post1);

		// 示例:服务提供者解密和验证. body为实际的业务数据JSON格式
		String body = SecurityUtil.validateEncryptDto(dto, ENCRYPT_SIGN_SALT, ENCRYPT_AES_KEY);
		System.out.println("解密后的body:" + body);




	}

	/**
	 * 创建用于HTTP接口安全传输的DTO
	 * 
	 * @author qiongwei.cai 2018年10月22日
	 * @param appId appId
	 * @param salt 加密盐
	 * @param aesKey 长度需为16/32位,对body进行aes加密时的值,若为空则不加密,规则:先生成签名,再加密内容
	 * @return
	 */
	public static ApiEncryptedDto createEncryptDto(Object body, String appId, String salt, String aesKey) {
		ApiEncryptedDto dto = new ApiEncryptedDto();
		dto.setAppId(appId);
		String bodyStr = JsonUtil.toJson(body);
		if (BaseUtil.isNotEmpty(aesKey)) {// 加密body内容,如果aesKey为空则不加密
			try {
				bodyStr = AESUtil.encrypt(bodyStr, aesKey);
			} catch (Exception e) {
				throw new RuntimeException("body加密失败", e);
			}
		}
		dto.setBody(bodyStr);
		dto.setNonce(BaseUtil.uuid());
		dto.setTimestamp(Long.toString(System.currentTimeMillis()));
		String sign = SignUtil.getSign(BaseUtil.convertBeanToStringMap(dto), salt);
		dto.setSign(sign);
		return dto;
	}

	/**
	 * 校验接口合法性,并解密,非法时会抛出异常
	 * 
	 * @author qiongwei.cai 2018年10月22日
	 * @param encryptDto
	 * @param salt 加密盐
	 * @param aesKey 长度需为16/32位,对body进行aes加密时的值,若为空则不加密,规则:先生成签名,再加密内容
	 * @return
	 */
	public static String validateEncryptDto(ApiEncryptedDto encryptDto, String salt, String aesKey) {
		return validateEncryptDto(encryptDto, salt, aesKey, null);
	}

	/**
	 * 校验接口合法性,并解密,非法时会抛出异常
	 * 
	 * @author qiongwei.cai 2018年10月22日
	 * @param encryptDto
	 * @param salt 加密盐
	 * @param aesKey 长度需为16/32位,对body进行aes加密时的值,若为空则不加密,规则:先生成签名,再加密内容
	 * @param allowTimeMargin 允许的时间误差毫秒数
	 * @return
	 */
	public static String validateEncryptDto(ApiEncryptedDto encryptDto, String salt, String aesKey,
			Long allowTimeMargin) {
		if (BaseUtil.isEmpty(encryptDto.getTimestamp()) || BaseUtil.isEmpty(encryptDto.getNonce())
				|| BaseUtil.isEmpty(encryptDto.getSign()) || encryptDto.getTimestamp().length() < 13
				|| encryptDto.getNonce().length() < 32 || encryptDto.getSign().length() < 32) {
			throw new RuntimeException("数据非法,为空或长度不正确!");
		}

		long reqTime = Long.parseLong(encryptDto.getTimestamp());
		long interval = Math.abs(System.currentTimeMillis() - reqTime);// 得到时间差并转为正数
		if (interval > (allowTimeMargin != null ? allowTimeMargin : DEF_ALLOW_TIME_MARGIN)) {// 如果大于10分钟,则拒绝该请求
			throw new RuntimeException("调用方请求时间与服务器当前时间误差过大,拒绝该请求!");
		}
		String sign = SignUtil.getSign(BaseUtil.convertBeanToStringMap(encryptDto), salt);
		if (!sign.equals(encryptDto.getSign())) {
			throw new RuntimeException("签名不一致!可能是伪造的请求");
		}

		String bodyStr = encryptDto.getBody();
		// 解密body内容
		if (BaseUtil.isNotEmpty(aesKey) && BaseUtil.isNotEmpty(bodyStr)) {
			try {
				encryptDto.setBody(AESUtil.decrypt(bodyStr, aesKey));
			} catch (Exception e) {
				throw new RuntimeException("body解密失败,请检查aesKey是否正确!", e);
			}
		}

		return encryptDto.getBody();
	}

}
