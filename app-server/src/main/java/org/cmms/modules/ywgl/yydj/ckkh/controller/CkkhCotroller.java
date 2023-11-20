package org.cmms.modules.ywgl.yydj.ckkh.controller;

import org.cmms.common.api.vo.Result;
import org.cmms.modules.ywgl.yydj.ckkh.entity.CkkhDTO;
import org.cmms.modules.ywgl.yydj.ckkh.entity.DkkhDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Date 2022/3/2
 * @Created by eran
 */
@RequestMapping("/ywgl/yydj/ckkh")
@RestController
public class CkkhCotroller {

    @RequestMapping("/add")
    public Result<?> add(@RequestBody CkkhDTO ckkhDTO){
        System.out.println("===================>");
        System.out.println(ckkhDTO.toString());
        return Result.ok("提交成功！");
    }


    @RequestMapping("/adddkkh")
    public Result<?> xyk(@RequestBody DkkhDTO dkkhDTO){
        System.out.println("===================>");
        System.out.println(dkkhDTO.toString());
        return Result.ok();
    }




}
