package org.cmms.modules.word.entity;

import cn.hutool.core.convert.Convert;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.cmms.common.vo.Result;
import org.cmms.modules.system.util.MD5Util;
import org.cmms.modules.util.DocxUtil2;
import org.cmms.modules.util.PathUtils;
import org.cmms.modules.util.WordUtils2;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Test2 {
    public static void main(String[] args) throws Exception{
        //String result = HttpUtil.get("127.0.0.1:8013/word/test");
       // System.out.println(result);
        //Result result1 = JSONObject.parseObject(result, Result.class);
       // System.out.println(result1.toString());

        String url="http://192.168.0.157:38029/api/weps_web/login";
		/*发送post请求并接收响应数据
		 * 采用的是一种叫链式编程的方式):
			header对应的是请求头。
			body对应的是请求体(包含参数和参数值)。
			HttpRequest里面包含Post、GET、Delete、Put等常用的RestFul方式。*/

        JSONObject json = new JSONObject();
        json.put("login_name", "admin");
        json.put("login_password",MD5Util.MD5("admin123").toLowerCase() );

        json.put("login_type", "PASSWORD");

        String post = HttpRequest.post(url)
                .header("content-type","application/json")
                //.header("x-session-token","token")
                .body(json.toJSONString())
                .execute().body();
        System.out.println(post);



    }
}
