package org.cmms.modules.system.aspect;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.system.service.ISysDictService;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 字典aop类
 * @Author: dangzhenghui
 * @Date: 2019-3-17 21:50
 * @Version: 1.0
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class DataFilterAspect {
	@Autowired
	private ISysDictService dictService;
	// 定义切点Pointcut
	@Pointcut("execution(public * org.cmms.modules.pad..*.*Controller.*(..)))")
	public void excudeService() {}
	// 浏阳精慧信贷:小额农贷
	@Pointcut("execution(public * org.cmms.modules.khgl.grkhgl.controller.XendPadController.*(..)))")
	public void excudeXendService() {}
	// 浏阳精慧信贷:个人贷款
	@Pointcut("execution(public * org.cmms.modules.xdgl.grdkgl.controller.GrdkPadController.*(..)))")
	public void excudeGrdkService() {}
	// 浏阳精慧信贷:贷款审批
	@Pointcut("execution(public * org.cmms.modules.xdgl.grdkgl.controller.GrdkspjlPadController.*(..)))")
	public void excudeDkspService() {}

	@Pointcut("execution(public * org.cmms.modules.ckjkpt..*.*Controller.queryIpadList(..)))")
	public void excudeCkjkptService() {}

	@Pointcut("execution(public * org.cmms.modules.dkjkpt..*.*Controller.queryIpadList(..)))")
	public void excudeDkjkptService() {}

	// 新增脱敏字段后需加上逗号，否则无法检测到
	private String zjhmFields = "ZJHM,HZZJHM,PYYZJHM,FRZJHM,SXDXZJH,GTJKRZJHM,DBRZJHM,DBRPOZJHM,";

	@Around("excudeService() || excudeXendService() || excudeGrdkService() || excudeDkspService() || excudeCkjkptService() || excudeDkjkptService()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		long time1=System.currentTimeMillis();
		Object result = pjp.proceed();
		long time2=System.currentTimeMillis();
		log.debug("获取JSON数据 耗时："+(time2-time1)+"ms");
		long start=System.currentTimeMillis();
		this.parseDictText(result);
		long end=System.currentTimeMillis();
		log.debug("解析注入JSON数据  耗时"+(end-start)+"ms");
		return result;
	}

	/**
	 * 对返回的数据进行脱敏处理
	 * @param result
	 */
	private void parseDictText(Object result) {
		if (result instanceof Result) {
			if (((Result) result).getResult() instanceof IPage) {

				List<JSONObject> items = new ArrayList<>();
				for (Object record : ((IPage) ((Result) result).getResult()).getRecords()) {
					ObjectMapper mapper = new ObjectMapper();
					String json="{}";
					try {
						//解决@JsonFormat注解解析不了的问题详见SysAnnouncement类的@JsonFormat
						json = mapper.writeValueAsString(record);
					} catch (JsonProcessingException e) {
						log.error("json解析失败"+e.getMessage(),e);
					}
					JSONObject item = JSONObject.parseObject(json);
					for (String key : item.keySet()) {
						if (zjhmFields.indexOf(key.toUpperCase() + ",") >= 0) {
							//对证件号码信息进行脱敏
							String showValue = filterZjhmValue(item.getString(key));
							item.put(key, showValue);
						}
					}
					items.add(item);
				}
				((IPage) ((Result) result).getResult()).setRecords(items);

			} else if (((Result) result).getResult() instanceof List) {

				//modify by liuwei 2019年9月29日15:09:15
				//增加对list的支持
				List<JSONObject> items = new ArrayList<>();
				for (Object record : ((List) ((Result) result).getResult())) {
					ObjectMapper mapper = new ObjectMapper();
					String json="{}";
					if (record instanceof String) {
						//如果list中存储的是String数据，不作处理
						return;
					}
					try {
						//解决@JsonFormat注解解析不了的问题详见SysAnnouncement类的@JsonFormat
						json = mapper.writeValueAsString(record);
					} catch (JsonProcessingException e) {
						log.error("json解析失败"+e.getMessage(),e);
						return;
					}
					JSONObject item = new JSONObject();
					try {
						item = JSONObject.parseObject(json);
					} catch (JSONException e) {
						log.error("json解析失败"+e.getMessage(),e);
						return;
					}
					for (String key : item.keySet()) {
						if (zjhmFields.indexOf(key.toUpperCase() + ",") >= 0) {
							//对证件号码信息进行脱敏
							String showValue = filterZjhmValue(item.getString(key));
							item.put(key, showValue);
						}
					}
					items.add(item);
				}
				((List) ((Result) result).getResult()).clear();
				((List) ((Result) result).getResult()).addAll(items);

			} else if (((Result) result).getResult() instanceof JSONObject) {

				// Modify By Penghr At 2020年12月2日 10:59:31
				// 增加对 JSONObject 的支持
				JSONObject item = (JSONObject) ((Result) result).getResult();
				/*System.out.println("1 - item - "+item);*/
				for (String keys : item.keySet()) {
					/*System.out.println("2 - keys - "+keys);*/
					Object records = item.get(keys);
					/*System.out.println("3 - records - "+records);*/
					if (records instanceof String) {
						/*System.out.println("※※※※※※※※※※※※※※ String");*/
						if (zjhmFields.indexOf(keys.toUpperCase() + ",") >= 0) {
							// 对证件号码信息进行脱敏
							String showValue = filterZjhmValue(item.getString(keys));
							item.put(keys, showValue);
//							((Result) result).setResult(item);
						}

					} else if (records instanceof List) {
						/*System.out.println("♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥ List");*/
//						List<JSONObject> items = new ArrayList<>();
						for (Object record : (List) records) {
							ObjectMapper mapper = new ObjectMapper();
							String json = "{}";
							if (record instanceof String) {
								return;
							}
							try {
								json = mapper.writeValueAsString(record);
							} catch (JsonProcessingException e) {
								log.error("json解析失败，"+e.getMessage(),e);
								return;
							}
							JSONObject jsonObject = new JSONObject();
							try {
								jsonObject = JSONObject.parseObject(json);
							} catch (JSONException e) {
								log.error("json解析失败，"+e.getMessage(),e);
								return;
							}
							for (String key : jsonObject.keySet()) {
								if (zjhmFields.indexOf(key.toUpperCase() + ",") >= 0) {
									// 对证件号码信息进行脱敏
									String showValue = filterZjhmValue(jsonObject.getString(key));
									jsonObject.put(key, showValue);
								}
							}
//							items.add(jsonObject);
						}
						((Result) result).setResult(item);
					}
				}

			}

		}
	}

	private String filterZjhmValue(String zjhm) {
		if (StringUtils.isEmpty(zjhm)) {
			return "";
		}
		int length = zjhm.length();
		if (length >  10) {
			zjhm = zjhm.replaceAll("(\\w{12})\\w*(\\w{3})", "$1****$2");
		} else {
			zjhm = zjhm + "******";
		}
		return zjhm;
	}

}
