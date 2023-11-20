package org.cmms.modules.yxdygl.yxdyglmain.controller;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.wggl.wgzbxx.entity.Wgzbxx;
import org.cmms.modules.wggl.wgzbxx.service.IWgzbxxService;
import org.cmms.modules.yxdygl.ejyxdygl.entity.Ejyxdygl;
import org.cmms.modules.yxdygl.ejyxdygl.vo.EjyxdyglPage;
import org.cmms.modules.yxdygl.yxdyfjxx.entity.Yxdyfjxx;
import org.cmms.modules.yxdygl.yxdyfjxx.service.IYxdyfjxxService;
import org.cmms.modules.yxdygl.yxdyglmain.entity.AppYxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.entity.AppYxdyglMainVO;
import org.cmms.modules.yxdygl.yxdyglmain.entity.WgxxFjVO;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.mapper.YxdyglMainMapper;
import org.cmms.modules.yxdygl.yxdyglmain.service.IYxdyglMainService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Date 2022/1/4
 * @Created by eran
 */
@RestController
@RequestMapping("/app/yxdyglmain/yxdyglMain")
public class AppYxdyglMainController extends JeecgController<YxdyglMain, IYxdyglMainService> {
    @Autowired
    private IWgzbxxService wgzbxxService;
    @Autowired
    YxdyglMainMapper yxdyglMainMapper;

    @Autowired
    IYxdyfjxxService yxdyfjxxService;

    @RequestMapping("/list")
    public Result<?> queryPageList(AppYxdyglMain yxdyglMain,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   @RequestParam(name = "queryOrganize", required = false, defaultValue = "1") String queryOrganize,
                                   HttpServletRequest req) {
        Page<YxdyglMain> page = new Page<YxdyglMain>(pageNo, pageSize);
        IPage<AppYxdyglMain> appList = yxdyglMainMapper.getAppList(page, yxdyglMain, getLoginUser().getWorkNo(), queryOrganize);
        return Result.ok(appList);

    }


    @RequestMapping(value = "/edit")
    public Result<?> edit(AppYxdyglMainVO appYxdyglMainVO) {
        System.out.println(appYxdyglMainVO.toString());
        YxdyglMain yxdyglMain = new YxdyglMain();
        BeanUtils.copyProperties(appYxdyglMainVO, yxdyglMain);
        yxdyglMainMapper.updateById(yxdyglMain);
        if("2".equals(yxdyglMain.getWgxz())){
            QueryWrapper queryWrapper =new QueryWrapper();
            queryWrapper.eq("wgbh",yxdyglMain.getWgbh());
            Wgzbxx one = wgzbxxService.getOne(queryWrapper);
            if(one!=null){
                if(StringUtils.isNotEmpty(yxdyglMain.getLongitude())&&StringUtils.isNotEmpty(yxdyglMain.getLatitude())){
                    one.setLongitude(yxdyglMain.getLongitude());
                    one.setLatitude(yxdyglMain.getLatitude());
                    wgzbxxService.updateById(one);
                }
            }else{
                Wgzbxx wgzbxx=new Wgzbxx();
                wgzbxx.setWgbh(yxdyglMain.getWgbh());
                wgzbxx.setWgmc(yxdyglMain.getWgmc());
                wgzbxx.setWglx("4");
                wgzbxx.setLongitude(yxdyglMain.getLongitude());
                wgzbxx.setLatitude(yxdyglMain.getLatitude());
                wgzbxxService.save(wgzbxx);
            }
        }
        //附件有变化时 才会传值进来 当有值 先删除 后新增即可
        if (CollUtil.isNotEmpty(appYxdyglMainVO.getYxdyfjxxList())) {
            //清空附件信息
            LambdaQueryWrapper<Yxdyfjxx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Yxdyfjxx::getDybh, appYxdyglMainVO.getWgbh());
            yxdyfjxxService.remove(lambdaQueryWrapper);

            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            List<WgxxFjVO> yxdyfjxxList = appYxdyglMainVO.getYxdyfjxxList();
            List<Yxdyfjxx> list = new ArrayList<>();
            for (int i = 0; i < yxdyfjxxList.size(); i++) {
                WgxxFjVO wgxxFjVO = yxdyfjxxList.get(i);
                Yxdyfjxx yxdyfjxx = new Yxdyfjxx();
                yxdyfjxx.setDybh(appYxdyglMainVO.getWgbh());
                yxdyfjxx.setFjlx(wgxxFjVO.getZllx() + "");
                yxdyfjxx.setFjmc(wgxxFjVO.getName());
                yxdyfjxx.setScsj(new Date());
                yxdyfjxx.setScr(loginUser.getUsername());
                yxdyfjxx.setFjdx(new BigDecimal(wgxxFjVO.getSize()));
                yxdyfjxx.setFjlj(wgxxFjVO.getFwlj());
                yxdyfjxx.setFwlj(wgxxFjVO.getFwlj());
                yxdyfjxx.setCreateBy(loginUser.getUsername());
                yxdyfjxx.setUpdateBy(loginUser.getUsername());
                list.add(yxdyfjxx);
            }
            yxdyfjxxService.saveBatch(list);
        }
        return Result.ok();
    }
}
