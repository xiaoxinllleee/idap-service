package org.cmms.modules.gzap.gzrl.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import org.cmms.common.api.vo.Result;
import org.cmms.modules.demo.mock.MockController;

import org.cmms.modules.gzap.gzrl.service.IGzrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
* @Description: 工作日志
* @Author: cmms
* @Date:   2019-09-08
* @Version: V1.0
*/
@RestController
@RequestMapping("/gzap/rwrl")
@Slf4j
public class GzapGzrlController {
   @Autowired
   private IGzrlService IGzrlService;




    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public Result<JSONArray> getrwrllist(@RequestParam(name="dx") String dx) {
        Result<JSONArray> result = new Result<JSONArray>();
        System.out.println("@@@@@@@@@@@dx:"+dx);
        JSONArray array =new JSONArray();
        List<Map> list =IGzrlService.getgzrlxx(dx);
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
            JSONObject json= new JSONObject();
            json.put("id",list.get(i).get("ID"));
            json.put("color",list.get(i).get("COLOR"));
            json.put("name",list.get(i).get("LX"));
            json.put("title",list.get(i).get("TITLE"));
            json.put("start",list.get(i).get("STARTS"));
            json.put("end",list.get(i).get("ENDS"));
            json.put("creatby",list.get(i).get("CREATE_BY"));
            json.put("lxqc",list.get(i).get("LXQC"));
            json.put("nr",list.get(i).get("NR"));

            array.add(json);
        }
        result.setResult(array);
        result.setSuccess(true);
        return result;
    }
}
