package org.cmms.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 判断客户端浏览器类型
 */
public class BrowserUtil {
    /**
     * 判断客户端浏览器类型
     * @param request
     * @return
     */
    public static String getBrowser(HttpServletRequest request) {
        String UserAgent = request.getHeader("User-Agent").toLowerCase();
        if (UserAgent.indexOf("firefox") >= 0){
            return "FF";
        }else if(UserAgent.indexOf("safari") >= 0 ){
            return "Chrome";
        }else{
            return "IE";
        }
    }
}
