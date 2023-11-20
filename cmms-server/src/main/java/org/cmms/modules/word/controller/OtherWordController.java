package org.cmms.modules.word.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/word")
public class OtherWordController {
    @Value(value = "${common.path.ip}")
    private String ip;

    @RequestMapping("/creditWord")
    public Result<?> creditWord(@RequestParam(name="zjhm",required=true) String zjhm, @RequestParam(name="hhbm") String hhbm,
    @RequestParam(name="spid") String spid, @RequestParam(name="type") String type){
        Map<String,Object> map = new HashMap<>();
        map.put("zjhm",zjhm);
        map.put("hhbm",hhbm);
        map.put("spid",spid);
        map.put("type",type);
        String result = HttpUtil.get(ip+"word/creditWord",map);
        return JSONObject.parseObject(result,Result.class);
    }

    @RequestMapping("/bmkWord")
    public Result<?> bmkWord(@RequestParam(name="zjhm",required=true) String zjhm,
                          @RequestParam(name="hhbm") String hhbm,
                          @RequestParam(name="spid") String spid){
        Map<String,Object> map = new HashMap<>();
        map.put("zjhm",zjhm);
        map.put("hhbm",hhbm);
        map.put("spid",spid);
        String result = HttpUtil.get(ip+"word/bmkWord",map);
        return JSONObject.parseObject(result,Result.class);
    }

    @RequestMapping("/bmkInfo")
    public Result<?> bmkInfo(@RequestParam(name="zjhm",required=true) String zjhm,
                             @RequestParam(name="hhbm") String hhbm,
                             @RequestParam(name="spid") String spid){
        Map<String,Object> map = new HashMap<>();
        map.put("zjhm",zjhm);
        map.put("hhbm",hhbm);
        map.put("spid",spid);
        String result = HttpUtil.get(ip+"word/bmkInfo",map);
        return JSONObject.parseObject(result,Result.class);
    }


    @RequestMapping("/nhxeWord")
    public Result<?> nhxeWord(@RequestParam(name="zjhm",required=true) String zjhm,
                             @RequestParam(name="hhbm") String hhbm,
                             @RequestParam(name="spid") String spid){
        Map<String,Object> map = new HashMap<>();
        map.put("zjhm",zjhm);
        map.put("hhbm",hhbm);
        map.put("spid",spid);
        String result = HttpUtil.get(ip+"word/nhxeWord",map);
        return JSONObject.parseObject(result,Result.class);
    }

    @RequestMapping("/grdkWord")
    public Result grdkWord(@RequestParam(name="zjhm",required=true) String id){
        Map<String,Object> map = new HashMap<>();
        String result = HttpUtil.get(ip+"word/nhxeWord",map);
        return JSONObject.parseObject(result,Result.class);
    }

    @RequestMapping("/jtzcdWord")
    public Result jtzcdWord(@RequestParam(name="zjhm",required=true) String zjhm){
        Map<String,Object> map = new HashMap<>();
        map.put("zjhm",zjhm);
        String result = HttpUtil.get(ip+"word/jtzcdWord",map);
        return JSONObject.parseObject(result,Result.class);
    }
}
