package org.cmms.config;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import net.sf.saxon.expr.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求参数传递辅助类
 */
public class RequestDataHelper {
    /**
     * 请求参数存取
     */
    private static final ThreadLocal<Map<String, Object>> REQUEST_DATA = new ThreadLocal<>();

    /**
     * 设置请求参数
     *
     * @param requestData 请求参数 MAP 对象
     */
    public static void setRequestData(Map<String, Object> requestData) {
        REQUEST_DATA.set(requestData);
    }


    /**
     * 单条数据使用
     * */
    public static void setRequestData(String key,String value) {
        Map<String,Object> map = new HashMap();
        map.put(key,value);
        REQUEST_DATA.set(map);
    }

    /**
     * 获取请求参数
     *
     * @param param 请求参数
     * @return 请求参数 MAP 对象
     */
    public static <T> T getRequestData(String param) {
        Map<String, Object> dataMap = getRequestData();
        if (CollectionUtils.isNotEmpty(dataMap)) {
            return (T) dataMap.get(param);
        }
        return null;
    }

    /**
     * 获取请求参数
     *
     * @return 请求参数 MAP 对象
     */
    public static Map<String, Object> getRequestData() {
        return REQUEST_DATA.get();
    }
}
