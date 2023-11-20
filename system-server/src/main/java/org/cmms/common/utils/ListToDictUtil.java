package org.cmms.common.utils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.system.entity.SysDic;
import org.cmms.modules.system.service.ISysDicService;
import org.cmms.modules.system.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Component
public class ListToDictUtil {
    @Autowired
    private ISysDictService dictService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ISysDicService sysDicService;

    public List parseDictText(List list){
        List<JSONObject> items = new ArrayList<>();
        for (Object list1 : list) {
            ObjectMapper mapper = new ObjectMapper();
            String json="{}";

            try {
                //解决@JsonFormat注解解析不了的问题详见SysAnnouncement类的@JsonFormat
                json = mapper.writeValueAsString(list1);
            } catch (JsonProcessingException e) {
                log.error("json解析失败"+e.getMessage(),e);
            }
            JSONObject item = new JSONObject();
            try {
                item = JSONObject.parseObject(json);
            } catch (JSONException e) {
                log.error("json解析失败"+e.getMessage(),e);
            }
            //update-begin--Author:scott -- Date:20190603 ----for：解决继承实体字段无法翻译问题------
            //for (Field field : record.getClass().getDeclaredFields()) {
            for (Field field : oConvertUtils.getAllFields(list1)) {
                //update-end--Author:scott  -- Date:20190603 ----for：解决继承实体字段无法翻译问题------
                if (field.getAnnotation(Dict.class) != null) {
                    String code = field.getAnnotation(Dict.class).dicCode();
                    String text = field.getAnnotation(Dict.class).dicText();
                    String table = field.getAnnotation(Dict.class).dictTable();
                    String key = String.valueOf(item.get(field.getName()));
                    String ds = field.getAnnotation(Dict.class).ds();

                    //翻译字典值对应的txt
                    String textValue = translateDictValue(code, text, table, key, ds);

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
        return items;
    }


    /**
     *  翻译字典文本
     * @param code
     * @param text
     * @param table
     * @param key
     * @return
     */
    private String translateDictValue(String code, String text, String table, String key, String ds) {
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
                log.debug("翻译字典文本当前使用数据源[DS]："+ds);
                if("master".equals(ds)){
                    tmpValue= dictService.queryTableDictTextByKey(table,text,code,k.trim());
                }else{
                    tmpValue= dictService.queryTableDictTextByKeyAndDs(table,text,code,k.trim(), ds);

                }
            }else {
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


    public Object parseDictText(Object record){
        if (record == null)
            return null;

        SysDic sysDic = sysDicService.queryByCode("101001");
        String qybm = sysDic.getValue();

        ObjectMapper mapper = new ObjectMapper();
        String json="{}";
        try {
            //解决@JsonFormat注解解析不了的问题详见SysAnnouncement类的@JsonFormat
            json = mapper.writeValueAsString(record);
        } catch (JsonProcessingException e) {
            log.error("json解析失败"+e.getMessage(),e);
        }
        JSONObject item = JSONObject.parseObject(json);
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
        return item;
    }


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
