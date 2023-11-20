package org.cmms.modules.appbase.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.modules.appbase.tbtjfxcssz.entity.TbTjfxCssz;
import org.cmms.modules.appbase.tbtjfxcssz.service.ITbTjfxCsszService;
import org.cmms.modules.appbase.vo.Tabbar;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;
import org.cmms.modules.hr.yggl.ygxxgl.service.IVhrbasstaffpostService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysBasUser;
import org.cmms.modules.system.service.ISysBasUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/mobile/user")
@Api(tags="用户登录")
@Slf4j
public class AppUserInfoController {
    @Autowired
    ISysBasUserService sysBasUserService;
    @Autowired
    IVhrbasstaffpostService vhrbasstaffpostService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ITbTjfxCsszService tbTjfxCsszService;
    @GetMapping(value = "/info")
    public Result<?> info() {
        QueryWrapper<TbTjfxCssz> tbTjfxCsszQueryWrapper = new QueryWrapper<>();
        tbTjfxCsszQueryWrapper.eq("csbm", "P00001");
        TbTjfxCssz tbTjfxCssz = tbTjfxCsszService.getOne(tbTjfxCsszQueryWrapper);
        if (tbTjfxCssz != null) {
            String csz = tbTjfxCssz.getCsz();
            redisUtil.set("app_sjrq", csz);
        }
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        //用户信息
        QueryWrapper<SysBasUser> sysBasUserQueryWrapper = new QueryWrapper<>();
        sysBasUserQueryWrapper.eq("userid", loginUser.getId());
        SysBasUser sysBasUser = sysBasUserService.getOne(sysBasUserQueryWrapper);
        //员工信息
        Vhrbasstaffpost vhrbasstaffpost = vhrbasstaffpostService.selectByYggh(loginUser.getWorkNo());
        //扩展信息
        //违规积分
        //登录次数
        JSONObject obj = new JSONObject();
        obj.put("yhxx", sysBasUser);
        obj.put("ygxx", vhrbasstaffpost);
        JSONObject tzxx = new JSONObject();
        obj.put("kzxx", tzxx);
        return Result.ok("获取用户信息成功！", obj);
    }
}
