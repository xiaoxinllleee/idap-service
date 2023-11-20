package org.cmms.modules.activiti.dynamic;

import org.cmms.common.util.StringUtils;

public class DynamicUtil {
    public static DynamicHandler getHandler(String className) {
        if (StringUtils.isEmpty(className)) {
            return null;
        }
        try {
            if (StringUtils.isEmpty(className)) {
                return null;
            }
            Class cls = Class.forName(className);
            Object obj = cls.newInstance();
            if (!(obj instanceof DynamicHandler)) {
                return null;
            }
            return (DynamicHandler)obj;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
}
