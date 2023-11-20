package org.cmms.modules.qxfk.qx.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.cmms.modules.qxfk.qx.constant.AppConstant;


/**
 * <pre>
 * ClssName: BaseUtil 
 * 提供空值判断. 获取当前登录者,request,session. 类型转换,类型判断. double运算,四舍五入等方法
 * </pre>
 *
 * @author qiongwei.cai
 * @since 1.8
 * @version $ Id:BaseUtil.java 1.0 2017年7月12日下午5:05:29 $
 */
public class BaseUtil {

	/**
	 * 字符串返回,若obj为空,则返回 ""(空字符串)
	 *
	 * @author qiongwei.cai 2017年7月12日下午5:08:08
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		return toString(obj, "");
	}

	/**
	 * 字符串返回
	 *
	 * @author qiongwei.cai 2017年7月12日下午5:07:01
	 * @param obj
	 * @param str 若obj为空,则返回该参数的值
	 * @return
	 */
	public static String toString(Object obj, String str) {
		if (obj == null || AppConstant.NULL.equals(obj) || obj.toString().isEmpty()) {
			return str;
		} else {
			return obj.toString().trim();
		}
	}

	/**
	 * java bean 转为 map
	 * 
	 * @author qiongwei.cai 2017年8月2日上午11:36:05
	 * @param bean
	 * @return
	 */
	public static Map<String, Object> convertBeanToMap(Object bean) {
		Map<String, Object> params = new HashMap<String, Object>(16);
		PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
		PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(bean);
		for (int i = 0; i < descriptors.length; i++) {
			String name = descriptors[i].getName();
			if (!"class".equals(name)) {
				try {
					params.put(name, propertyUtilsBean.getNestedProperty(bean, name));
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				} catch (InvocationTargetException e) {
					throw new RuntimeException(e);
				} catch (NoSuchMethodException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return params;
	}

	/**
	 * java bean 转为 map<String,String>
	 * 
	 * @author qiongwei.cai 2017年8月24日下午4:35:13
	 * @param bean
	 * @return
	 */
	public static Map<String, String> convertBeanToStringMap(Object bean) {
		Map<String, String> params = new HashMap<String, String>(16);
		PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
		PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(bean);
		for (int i = 0; i < descriptors.length; i++) {
			String name = descriptors[i].getName();
			if (!"class".equals(name)) {
				try {
					Object obj;
					try {
						obj = propertyUtilsBean.getNestedProperty(bean, name);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					} catch (InvocationTargetException e) {
						throw new RuntimeException(e);
					}
					params.put(name, obj == null ? null : obj.toString());
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		}
		return params;
	}

	/**
	 * 生成32位uuid
	 * 
	 * @author qiongwei.cai 2017年10月20日下午2:05:04
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 判断对象是否Empty(null或元素为0) <br/>
	 * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
	 * 
	 * @param pObj 待检查对象
	 * @return boolean 返回的布尔值
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object pObj) {
		if (pObj == null) {
			return true;
		}
		if (pObj instanceof String) {
			return ((String) pObj).trim().length() < 1;
		} else if (pObj instanceof Collection) {
			return ((Collection) pObj).size() < 1;
		} else if (pObj instanceof Map) {
			return ((Map) pObj).size() < 1;
		} else if (pObj.getClass().isArray()) {
			return Array.getLength(pObj) < 1;
		}
		return false;
	}

	/**
	 * 判断 是非空
	 * 
	 * @author qiongwei.cai 2017年7月12日下午5:09:17
	 * @param pObj
	 * @return
	 */
	public static boolean isNotEmpty(Object pObj) {
		return !isEmpty(pObj);
	}

}
