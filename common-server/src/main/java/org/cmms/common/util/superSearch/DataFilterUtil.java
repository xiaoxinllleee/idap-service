package org.cmms.common.util.superSearch;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.util.ReflectHelper;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class DataFilterUtil {
    private static String zjhmFields = "ZJHM,HZZJHM,PYYZJHM,FRZJHM,SXDXZJH,GTJKRZJHM,DBRZJHM,DBRPOZJHM,";

    public static String filterZjhmValue(String zjhm) {
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

    public static void filterZjhmValue(List list) {
        for (Object item : list) {
            ObjectMapper mapper = new ObjectMapper();
            String json="{}";
            try {
                //解决@JsonFormat注解解析不了的问题详见SysAnnouncement类的@JsonFormat
                json = mapper.writeValueAsString(item);
            } catch (JsonProcessingException e) {
                log.error("json解析失败"+e.getMessage(),e);
            }
            Map<String, Object> data = new HashMap<>();
            JSONObject jsonObject = JSONObject.parseObject(json);
            for (String key : jsonObject.keySet()) {
                if (zjhmFields.indexOf(key.toUpperCase() + ",") >= 0) {
                    //对证件号码信息进行脱敏
                    String showValue = filterZjhmValue(jsonObject.getString(key));
                    data.put(key, showValue);
                }
            }
            ReflectHelper.setAll(item, data);
        }
    }
}
