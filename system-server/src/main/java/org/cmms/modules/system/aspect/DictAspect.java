package org.cmms.modules.system.aspect;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONException;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.vo.DictModel;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.system.entity.SysDic;
import org.cmms.modules.system.service.ISysDicService;
import org.cmms.modules.system.service.ISysDictService;
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
@Order(2)
public class DictAspect {
    @Autowired
    private ISysDictService dictService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ISysDicService sysDicService;
    // 定义切点Pointcut
    @Pointcut("execution(public * org.cmms.modules..*.*Controller.*(..))")
    public void excudeService() {
    }

    @Around("excudeService()")
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
     * 本方法针对返回对象为Result 的IPage的分页列表数据进行动态字典注入
     * 字典注入实现 通过对实体类添加注解@dict 来标识需要的字典内容,字典分为单字典code即可 ，table字典 code table text配合使用与原来jeecg的用法相同
     * 示例为SysUser   字段为sex 添加了注解@Dict(dicCode = "sex") 会在字典服务立马查出来对应的text 然后在请求list的时候将这个字典text，已字段名称加_dictText形式返回到前端
     * 例输入当前返回值的就会多出一个sex_dictText字段
     * {
     *      sex:1,
     *      sex_dictText:"男"
     * }
     * 前端直接取值sext_dictText在table里面无需再进行前端的字典转换了
     *  customRender:function (text) {
     *               if(text==1){
     *                 return "男";
     *               }else if(text==2){
     *                 return "女";
     *               }else{
     *                 return text;
     *               }
     *             }
     *             目前vue是这么进行字典渲染到table上的多了就很麻烦了 这个直接在服务端渲染完成前端可以直接用
     * @param result
     */
    private void parseDictText(Object result) {

        if (result instanceof Result) {
            SysDic sysDic = sysDicService.queryByCode("101001");
            String qybm = sysDic.getValue();
            if (((Result) result).getResult() instanceof IPage) {
                List<JSONObject> items = new ArrayList<>();
                if(result!=null && ((Result) result).getResult()!=null  && ((IPage) ((Result) result).getResult()).getRecords()!=null){
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
                        //update-begin--Author:scott -- Date:20190603 ----for：解决继承实体字段无法翻译问题------
                        //for (Field field : record.getClass().getDeclaredFields()) {
                        for (Field field : oConvertUtils.getAllFields(record)) {
                            //update-end--Author:scott  -- Date:20190603 ----for：解决继承实体字段无法翻译问题------
                            if (field.getAnnotation(Dict.class) != null) {
                                String code = field.getAnnotation(Dict.class).dicCode();
                                String text = field.getAnnotation(Dict.class).dicText();
                                String table = field.getAnnotation(Dict.class).dictTable();
                                String key = String.valueOf(item.get(field.getName()));
                                String ds = field.getAnnotation(Dict.class).ds();
                                String dicCodeQybm = field.getAnnotation(Dict.class).dicCodeQybm();
                                //翻译字典值对应的txt
                                String textValue = translateDictValue(code, text, table, key, ds, dicCodeQybm, qybm);

                                log.debug(" 字典Val : "+ textValue);
                                log.debug(" __翻译字典字段__ "+field.getName() + CommonConstant.DICT_TEXT_SUFFIX+"： "+ textValue);
                                item.put(field.getName() + CommonConstant.DICT_TEXT_SUFFIX, textValue);
                            }
                            //date类型默认转换string格式化日期
                            if (field.getType().getName().equals("java.util.Date")&&field.getAnnotation(JsonFormat.class)==null&&item.get(field.getName())!=null){
                                SimpleDateFormat aDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                item.put(field.getName(), aDate.format(new Date((Long) item.get(field.getName()))));
                            }
                        }
                        items.add(item);
                    }
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
                    //update-begin--Author:scott -- Date:20190603 ----for：解决继承实体字段无法翻译问题------
                    //for (Field field : record.getClass().getDeclaredFields()) {
                    for (Field field : oConvertUtils.getAllFields(record)) {
                        //update-end--Author:scott  -- Date:20190603 ----for：解决继承实体字段无法翻译问题------
                        if (field.getAnnotation(Dict.class) != null) {
                            String code = field.getAnnotation(Dict.class).dicCode();
                            String text = field.getAnnotation(Dict.class).dicText();
                            String table = field.getAnnotation(Dict.class).dictTable();
                            String key = String.valueOf(item.get(field.getName()));
                            String ds = field.getAnnotation(Dict.class).ds();
                            String dicCodeQybm = field.getAnnotation(Dict.class).dicCodeQybm();
                            //翻译字典值对应的txt
                            String textValue = null;
                            if (org.apache.commons.lang3.StringUtils.isNotBlank(table) &&
                                table.equalsIgnoreCase("KHGXGL_KHZLGL_GRKH")
                            ){
                                //加上机构代码查询
                                LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                                String s = (String) redisUtil.get(CommonConstant.PREFIX_USER_JGDM + sysUser.getUsername());
                                    if (org.apache.commons.lang3.StringUtils.isNotBlank(s)){
                                        List<DictModel> dictModels = dictService.queryTableDictItemsByCodeAndFilter(table, text, code, " jgdm = '" + s + "'" );
                                        if (CollUtil.isNotEmpty(dictModels)){
                                            if (org.apache.commons.lang3.StringUtils.isNotBlank(dictModels.get(0).getText())){
                                                textValue = dictModels.get(0).getText();
                                            }
                                        }

                                    }

                            }else {
                                textValue = translateDictValue(code, text, table, key, ds, dicCodeQybm, qybm);
                            }


                            log.debug(" 字典Val : "+ textValue);
                            log.debug(" __翻译字典字段__ "+field.getName() + CommonConstant.DICT_TEXT_SUFFIX+"： "+ textValue);
                            item.put(field.getName() + CommonConstant.DICT_TEXT_SUFFIX, textValue);
                        }
                        //date类型默认转换string格式化日期
                        if (field.getType().getName().equals("java.util.Date")&&field.getAnnotation(JsonFormat.class)==null&&item.get(field.getName())!=null){
                            SimpleDateFormat aDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            item.put(field.getName(), aDate.format(new Date((Long) item.get(field.getName()))));
                        }
                    }
                    items.add(item);
                }
                ((List) ((Result) result).getResult()).clear();
                ((List) ((Result) result).getResult()).addAll(items);
            }
//            else if (((Result) result).getResult() != null){
//                //返回的单个实体类  用instanceof Object 判断不出来
//                //指定对应的实体类不通用  instanceof Object 都是 true
//                Object o = ((Result) result).getResult();
//                ObjectMapper mapper = new ObjectMapper();
//                String json="{}";
//                if (o instanceof String) {
//                    //如果list中存储的是String数据，不作处理
//                    return;
//                }
//                try {
//                    //解决@JsonFormat注解解析不了的问题详见SysAnnouncement类的@JsonFormat
//                    json = mapper.writeValueAsString(o);
//                } catch (JsonProcessingException e) {
//                    //log.error("json解析失败"+e.getMessage(),e);
//                    return;
//                }
//                JSONObject item = new JSONObject();
//                try {
//                    item = JSONObject.parseObject(json);
//                } catch (JSONException e) {
//                    //log.error("json解析失败"+e.getMessage(),e);
//                    return;
//                }
//                //update-begin--Author:scott -- Date:20190603 ----for：解决继承实体字段无法翻译问题------
//                //for (Field field : record.getClass().getDeclaredFields()) {
//                for (Field field : oConvertUtils.getAllFields(o)) {
//                    //update-end--Author:scott  -- Date:20190603 ----for：解决继承实体字段无法翻译问题------
//                    if (field.getAnnotation(Dict.class) != null) {
//                        String code = field.getAnnotation(Dict.class).dicCode();
//                        String text = field.getAnnotation(Dict.class).dicText();
//                        String table = field.getAnnotation(Dict.class).dictTable();
//                        String key = String.valueOf(item.get(field.getName()));
//                        String ds = field.getAnnotation(Dict.class).ds();
//                        String dicCodeQybm = field.getAnnotation(Dict.class).dicCodeQybm();
//                        //翻译字典值对应的txt
//                        String textValue = null;
//                        if (org.apache.commons.lang3.StringUtils.isNotBlank(table) &&
//                                table.equalsIgnoreCase("KHGXGL_KHZLGL_GRKH")
//                        ){
//                            //加上机构代码查询
//                            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//                            String s = (String) redisUtil.get(CommonConstant.PREFIX_USER_JGDM + sysUser.getUsername());
//                            if (org.apache.commons.lang3.StringUtils.isNotBlank(s)){
//                                List<DictModel> dictModels = dictService.queryTableDictItemsByCodeAndFilter(table, text, code, " jgdm = '" + s + "'" );
//                                if (CollUtil.isNotEmpty(dictModels)){
//                                    if (org.apache.commons.lang3.StringUtils.isNotBlank(dictModels.get(0).getText())){
//                                        textValue = dictModels.get(0).getText();
//                                    }
//                                }
//
//                            }
//
//                        }else {
//                            textValue = translateDictValue(code, text, table, key, ds, dicCodeQybm, qybm);
//                        }
//
//
//                        log.debug(" 字典Val : "+ textValue);
//                        log.debug(" __翻译字典字段__ "+field.getName() + CommonConstant.DICT_TEXT_SUFFIX+"： "+ textValue);
//                        item.put(field.getName() + CommonConstant.DICT_TEXT_SUFFIX, textValue);
//                    }
//                    //date类型默认转换string格式化日期
//                    if (field.getType().getName().equals("java.util.Date")&&field.getAnnotation(JsonFormat.class)==null&&item.get(field.getName())!=null){
//                        SimpleDateFormat aDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        item.put(field.getName(), aDate.format(new Date((Long) item.get(field.getName()))));
//                    }
//                }
//
//                ((Result) result).setResult(item);
//            }
        }
    }

    /**
          *  翻译字典文本
     * @param code
     * @param text
     * @param table
     * @param key
     * @param ds
     * @return
     */
    private String translateDictValue(String code, String text, String table, String key, String ds, String dicCodeQybm, String qybm) {
    	if(oConvertUtils.isEmpty(key)) {
    		return null;
    	}
        StringBuffer textValue=new StringBuffer();
        String[] keys = key.split(",");
        for (String k : keys) {
            String tmpValue = null;
            log.debug(" 字典 key : "+ k);
            if (k.trim().length() == 0) {
                continue; //跳过循环
            }
            if (!StringUtils.isEmpty(table)){
                tmpValue= dictService.queryTableDictTextByKeyAndDs(table,text,code,k.trim(), ds);
            }else {
                if (!StringUtils.isEmpty(dicCodeQybm)) {
                    //不同地区不同的字典
                    List<String> list = Arrays.asList(dicCodeQybm.split("\\|"));
                    for (int i = 0; i < list.size(); i++) {
                        String[] strs = list.get(i).split("=");
                        if (qybm.equals(strs[0])) {
                            code = strs[1];
                            break;
                        }
                    }
                }
                tmpValue = dictService.queryDictTextByKey(code, k.trim());
            }

            if (tmpValue != null) {
                if (!"".equals(textValue.toString())) {
                    textValue.append(",");
                }
                textValue.append(tmpValue);
            }

        }
        return textValue.toString();
    }

}
