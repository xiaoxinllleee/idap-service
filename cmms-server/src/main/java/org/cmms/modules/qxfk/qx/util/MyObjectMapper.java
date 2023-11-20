package org.cmms.modules.qxfk.qx.util;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * <pre>
 * jackson.自定义objectMapper
 * </pre>
 *
 * @author qiongwei.cai
 * @since 1.8
 * @version 1.0 2017年9月22日下午2:26:10
 */
public class MyObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;

	private static class LazyHolder {
		private static final MyObjectMapper MY_OBJECT_MAPPER = new MyObjectMapper();
	}

	/**
	 * 获取单例的objectMapper
	 * 
	 * @author qiongwei.cai 2017年9月22日下午2:27:52
	 * @return
	 */
	public static MyObjectMapper getInstance() {
		return LazyHolder.MY_OBJECT_MAPPER;
	}

	/**
	 * <pre>
	 * 使用JsonUtil.objectMapper静态变量得到示例,不需要使用该构造方法.<br/>
	 * 若要自定义objectMapper,才用该构造方法
	 * </pre>
	 * 
	 * @author qiongwei.cai 2017年9月22日下午2:24:52
	 */
	public MyObjectMapper() {
		// 去掉默认的时间戳格式
		configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		// 设置为中国上海时区
		setTimeZone(TimeZone.getTimeZone("GMT+8"));
		configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		// 空值不序列化
		setSerializationInclusion(Include.NON_NULL);
		// 反序列化时，属性不存在的兼容处理
		getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		// 序列化时，日期的统一格式
		setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		// 遇到无法处理的未知属性不抛出异常
		configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 因为标准json中,要求键和值其实都是要双引号的，但如果非要设置键值为非双引号不可的话,则需要设置
		configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		// enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);//排序
	}

}