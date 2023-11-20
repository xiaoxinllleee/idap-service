package org.cmms.modules.utils;

import com.google.common.base.Strings;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description //TODO Map工具类
 * @Date 2020/5/7 9:54
 * @Author huangwb
 **/
public class MapUtils {
    /**
     * @return void
     * @Author huangwb
     * @Description //TODO 对象转换成map
     * @Date 2020/5/7 9:56
     * @Param [t 对象,ignoreFields 忽略字段]
     **/
    public static <T> Map<String, Object> objectToMap(T t, String... ignoreFields) {
        Map<String, Object> map = new HashMap<>();
        Field[] declaredFields = t.getClass().getDeclaredFields();
        List<String> ignoreFieldList = Arrays.asList(ignoreFields);
        Arrays.stream(declaredFields).forEach(data -> {
            data.setAccessible(true);
            try {
                if (ignoreFieldList.isEmpty() || !ignoreFieldList.contains(data.getName())) {
                    map.put(data.getName(), data.get(t));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return map;
    }

    public static String getMapStringValue(Map<String, Object> map, String key) {
        Object o = map.get(key);
        if (o == null || Strings.isNullOrEmpty(o.toString())) {
            return "";
        }
        return o.toString();
    }

    public static Integer getMapIntValue(Map<String, Object> map, String key) {
        Object o = map.get(key);
        if (o == null || Strings.isNullOrEmpty(o.toString())) {
            return 0;
        }
        return Integer.valueOf(o.toString());
    }

    public static Float getMapFloatValue(Map<String, Object> map, String key) {
        Object o = map.get(key);
        if (o == null || Strings.isNullOrEmpty(o.toString())) {
            return 0f;
        }
        return Float.valueOf(o.toString());
    }

    public static Double getMapDoubleValue(Map<String, Object> map, String key) {
        Object o = map.get(key);
        if (o == null || Strings.isNullOrEmpty(o.toString())) {
            return 0d;
        }
        return Double.valueOf(o.toString());
    }
}
