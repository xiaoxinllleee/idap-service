package org.cmms.modules.xdgl.grkhpjsx.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import freemarker.template.TemplateException;
import lombok.val;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.checkerframework.checker.units.qual.C;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.common.aspect.annotation.PermissionData;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.constant.DictConstant;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.DictModel;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.FileUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.activiti.dao.ActBusinessDao;
import org.cmms.modules.activiti.entity.ActBusiness;
import org.cmms.modules.activiti.entity.ActXendSpls;
import org.cmms.modules.activiti.entity.ActXendSplsXend;
import org.cmms.modules.activiti.mapper.ActXendSplsMapper;
import org.cmms.modules.activiti.service.ActBusinessService;
import org.cmms.modules.activiti.service.IActXendSplsService;
import org.cmms.modules.activiti.service.IActXendSplsXendService;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.khgl.grkhgl.entity.CamsZcsxGrpjsxxx;
import org.cmms.modules.khgl.grkhgl.entity.CamsZcsxGrpjsxxxBc;
import org.cmms.modules.khgl.grkhgl.entity.Khhmcxx;
import org.cmms.modules.khgl.grkhgl.entity.ZcsxNhcjxx;
import org.cmms.modules.khgl.grkhgl.mapper.CamsZcsxGrpjsxxxBcMapper;
import org.cmms.modules.khgl.grkhgl.mapper.CamsZcsxGrpjsxxxMapper;
import org.cmms.modules.khgl.grkhgl.mapper.KhhmcxxMapper;
import org.cmms.modules.khgl.grkhgl.mapper.ZcsxNhcjxxMapper;
import org.cmms.modules.khgl.grkhgl.service.*;
import org.cmms.modules.khgl.nh.entity.CamsZcsxNhcjxx;
import org.cmms.modules.khgl.nh.mapper.CamsZcsxNhcjxxMapper;
import org.cmms.modules.khgl.nh.service.ICamsZcsxNhcjxxService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.SlSHnkdVO;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.VNhPjsx;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IVNhPjsxService;
import org.cmms.modules.system.aspect.DictAspect;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysDictItem;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.mapper.SysDictItemMapper;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.system.service.ISysUserService;
import org.cmms.modules.util.FileUtils;
import org.cmms.modules.util.WordUtils;
import org.cmms.modules.xdgl.grkhpjsx.entity.*;
import org.cmms.modules.xdgl.grkhpjsx.mapper.EcifPersonMapper;
import org.cmms.modules.xdgl.grkhpjsx.service.IGrkhpjsxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.xdgl.grkhpjsx.service.IGrpjsxspjlService;

import org.cmms.modules.xdgl.grkhpjsx.service.IVgrpjsxspjlService;
import org.cmms.modules.xdgl.grkhpjsx.utils.ELoandDictUtil;
import org.cmms.modules.xdgl.pjsxspjl.entity.CamsZcsxGrpjsxxxSpjl;
import org.cmms.modules.xdgl.pjsxspjl.mapper.CamsZcsxGrpjsxxxSpjlMapper;
import org.cmms.modules.xdgl.pjsxspjl.service.ICamsZcsxGrpjsxxxSpjlService;
import org.cmms.modules.yxdygl.ejyxdygl.entity.Ejyxdygl;
import org.cmms.modules.yxdygl.ejyxdygl.mapper.EjyxdyglMapper;
import org.cmms.modules.yxdygl.sjyxdygl.entity.Sjyxdygl;
import org.cmms.modules.yxdygl.sjyxdygl.mapper.SjyxdyglMapper;
import org.cmms.modules.yxdygl.yjyxdygl.entity.Yjyxdygl;
import org.cmms.modules.yxdygl.yjyxdygl.mapper.YjyxdyglMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 个人客户评级授信
 * @Author: jeecg-boot
 * @Date: 2020-07-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "个人客户评级授信")
@RestController
@RequestMapping("/xdgl/grkhpjsx")
public class GrkhpjsxController extends JeecgController<Grkhpjsx, IGrkhpjsxService> {
    @Autowired
    private IGrkhpjsxService grkhpjsxService;
    @Autowired
    private ICamsZcsxGrpjsxxxBcService grkhpjsxbcService;
    @Autowired
    private IGrpjsxspjlService grkhspjlService;
    @Autowired
    private ISysDictService dictService;
    @Autowired
    private IKhhmcxxService khhmcxxService;
    @Autowired
    private ActBusinessService actBusinessService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IvKhglKrkhglService ivKhglKrkhglService;
    @Autowired
    private IVKhglGrkhglService iVKhglGrkhglService;
    @Autowired
    private ISysDictService iSysDictService;
    @Autowired
    private ICamsZcsxGrpjsxxxSpjlService iCamsZcsxGrpjsxxxSpjlService;
    @Autowired
    private IActXendSplsService iActXendSplsService;
    @Autowired
    private IActXendSplsXendService iActXendSplsXendService;
    @Autowired
    private IZcsxNhcjxxService iZcsxNhcjxxService;
    @Autowired
    ICamsZcsxGrpjsxxxService camsZcsxGrpjsxxxService;

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    private INhxqService nhxqService;
    @Autowired
    private IVNhPjsxService vNhPjsxService;
    /**
     * 分页列表查询
     *
     * @param grkhpjsx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "个人客户评级授信-分页列表查询")
    @ApiOperation(value = "个人客户评级授信-分页列表查询", notes = "个人客户评级授信-分页列表查询")
    @GetMapping(value = "/list")
    @PermissionData(pageComponent = "xdgl/grkhpjsx/GrkhpjsxList")
    public Result<?> queryPageList(Grkhpjsx grkhpjsx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Grkhpjsx> queryWrapper = QueryGenerator.initQueryWrapper(grkhpjsx, req.getParameterMap());
        queryWrapper.orderByDesc("create_time");
        Page<Grkhpjsx> page = new Page<Grkhpjsx>(pageNo, pageSize);
        IPage<Grkhpjsx> pageList = grkhpjsxService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param grkhpjsx
     * @return
     */
    @AutoLog(value = "个人客户评级授信-添加")
    @ApiOperation(value = "个人客户评级授信-添加", notes = "个人客户评级授信-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Grkhpjsx grkhpjsx) {

        grkhpjsxService.save(grkhpjsx);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @return
     */
    @AutoLog(value = "个人客户评级授信-编辑")
    @ApiOperation(value = "个人客户评级授信-编辑", notes = "个人客户评级授信-编辑")
    @PutMapping(value = "/edit")
//    public Result<?> edit2(@RequestBody Grpjsxspjl data) {
    public Result<?> edit2(@RequestBody VNhPjsx data) {
        //为了兼容精慧老版本 目前直接把 KHXXGL_KHXQ_NH 和 CAMS_ZCSX_GRPJSXXX 组装到 Grpjsxspjl
        //保证zjhm无重复数据
        //再进入审批的时候就会第一次记录
        if (StringUtils.isBlank(data.getId()))
            return Result.ok();
        VNhPjsx grkhpjsx = vNhPjsxService.getById(data.getId());

        /*if (StringUtils.isBlank(grpjsxspjl.getZjhm()))
            return Result.ok();
        VNhPjsx byId = vNhPjsxService.getById(grpjsxspjl.getId());*/

//        if (!(data.getStatus() == 3 || data.getStatus() == -2)) {
//
//        }
        if (grkhpjsx.getStatus() == -1){
            ActBusiness actBusiness = new ActBusiness();
            actBusiness.setUserId(getLoginUser().getId());
            actBusiness.setTableId(grkhpjsx.getId());
            String sxsplc = grkhpjsxService.querySplcProcessId("sxsplc");
            actBusiness.setProcDefId(sxsplc);
            actBusiness.setTitle(grkhpjsx.getKhmc() + "的授信申请");
            actBusiness.setCreateBy(getWorkNo());
            actBusiness.setCreateTime(new Date());
            actBusiness.setApplyTime(new Date());
            actBusinessService.save(actBusiness);
            grkhpjsxService.updateGrpjsxxxZjhm(actBusiness.getId(), data.getZzpddj(), data.getZzsxed(), data.getYj(), data.getZjhm());

            ActXendSpls spls = new ActXendSpls();
            spls.setSpid(actBusiness.getId());
            spls.setHhbm(grkhpjsx.getHhbm());
            spls.setZjhm(grkhpjsx.getZjhm());
            spls.setPddj(grkhpjsx.getZzpddj());
            spls.setJyed(grkhpjsx.getZzsxed());
            spls.setSpyj(grkhpjsx.getYj());
            spls.setUserid(getLoginUser().getId());
            spls.setYggh(getWorkNo());
            iActXendSplsService.save(spls);


        }



        /*if (!(grkhpjsx.getStatus() == 3 || grkhpjsx.getStatus() == -2)) {



        }
        else
        {
            String spid = grkhpjsx.getBussinessId();
            grkhspjlService.deleteByspid(spid);
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("hhbm", grkhpjsx.getHhbm());
            CamsZcsxGrpjsxxxBc camsZcsxGrpjsxxxBc = new CamsZcsxGrpjsxxxBc();
            camsZcsxGrpjsxxxBc = grkhpjsxbcService.getOne(queryWrapper);
            if (camsZcsxGrpjsxxxBc != null) {
                grkhpjsx.setCkrp(camsZcsxGrpjsxxxBc.getCkrp());
                grkhpjsx.setBwdk(camsZcsxGrpjsxxxBc.getBwdk());
                grkhpjsx.setCrjye(camsZcsxGrpjsxxxBc.getCrjye());
                grkhpjsx.setDhqye(camsZcsxGrpjsxxxBc.getDhqye());
                grkhpjsx.setZcjye(camsZcsxGrpjsxxxBc.getZcjye());
                grkhpjsx.setWjfl(camsZcsxGrpjsxxxBc.getWjfl());
                grkhpjsx.setSxed(camsZcsxGrpjsxxxBc.getSxed());
                grkhpjsx.setZqje(camsZcsxGrpjsxxxBc.getZqje());
                grkhpjsx.setSfxd(camsZcsxGrpjsxxxBc.getSfxd());
                grkhpjsx.setKhsj(camsZcsxGrpjsxxxBc.getKhsj());
                grkhpjsx.setCkzhs(camsZcsxGrpjsxxxBc.getCkzhs());
                grkhpjsx.setCrjycs(camsZcsxGrpjsxxxBc.getCrjycs().intValue());
                grkhpjsx.setYqcs(camsZcsxGrpjsxxxBc.getYqcs());
                grkhpjsx.setZcjycs(camsZcsxGrpjsxxxBc.getZcjycs().intValue());
                grkhpjsx.setEtcjls(camsZcsxGrpjsxxxBc.getEtcjls());
                grkhpjsx.setSjyhjls(camsZcsxGrpjsxxxBc.getSjyhjls());
                grkhpjsx.setKdlqjls(camsZcsxGrpjsxxxBc.getKdlqjls());
                grkhpjsx.setWyjls(camsZcsxGrpjsxxxBc.getWyjls());
                grkhpjsx.setNxyjls(camsZcsxGrpjsxxxBc.getNxyjls());
            }
            grkhpjsx.setSpid(spid);
            grkhpjsx.setBussinessId(spid);
            grkhpjsx.setSqr(sysUser.getRealname());
            grkhpjsx.setUserId(userId);
            grkhpjsx.setSqrq(new Date());
            grkhspjlService.save(grkhpjsx);
            grkhpjsxService.updateGrpjsxxx(spid, grkhpjsx.getZzpddj(), grkhpjsx.getZzsxed(), grkhpjsx.getYj(), grkhpjsx.getHhbm());
            ActXendSpls spls = new ActXendSpls();
            spls.setSpid(spid);
            spls.setHhbm(grkhpjsx.getHhbm());
            spls.setZjhm(grkhpjsx.getZjhm());
            spls.setPddj(grkhpjsx.getZzpddj());
            spls.setJyed(grkhpjsx.getZzsxed());
            spls.setSpyj(grkhpjsx.getYj());
            spls.setUserid(userId);
            spls.setYggh(user.getWorkNo());
            iActXendSplsService.save(spls);
        }*/

        return Result.ok(grkhpjsx);
    }
/*
    public Result<?> edit(@RequestBody Grpjsxspjl grkhpjsx) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        SysUser user = sysUserService.getUserByName(sysUser.getUsername());
        String userId = user.getId();
        if (!(grkhpjsx.getStatus() == 3 || grkhpjsx.getStatus() == -2)) {
            ActBusiness actBusiness = new ActBusiness();
            actBusiness.setUserId(userId);
            actBusiness.setTableId(grkhpjsx.getHhbm());
            actBusiness.setProcDefId(grkhpjsxService.querySplcProcessId("sxsplc"));
            actBusiness.setTitle(grkhpjsx.getKhmc() + "的授信申请");
            actBusiness.setCreateBy(sysUser.getRealname());
            actBusiness.setCreateTime(new Date());
            actBusiness.setApplyTime(new Date());
            actBusinessService.save(actBusiness);
            grkhpjsxService.updateGrpjsxxx(actBusiness.getId(), grkhpjsx.getZzpddj(), grkhpjsx.getZzsxed(), grkhpjsx.getYj(), grkhpjsx.getHhbm());
            ActXendSpls spls = new ActXendSpls();
            spls.setSpid(actBusiness.getId());
            spls.setHhbm(grkhpjsx.getHhbm());
            spls.setZjhm(grkhpjsx.getZjhm());
            spls.setPddj(grkhpjsx.getZzpddj());
            spls.setJyed(grkhpjsx.getZzsxed());
            spls.setSpyj(grkhpjsx.getYj());
            spls.setUserid(userId);
            spls.setYggh(user.getWorkNo());
            iActXendSplsService.save(spls);
            grkhpjsx.setSpid(actBusiness.getId());
            grkhpjsx.setBussinessId(actBusiness.getId());
            grkhpjsx.setSqr(sysUser.getRealname());
            grkhpjsx.setUserId(userId);
            grkhpjsx.setSqrq(new Date());
            grkhpjsx.setProcDefId(grkhpjsxService.querySplcProcessId("sxsplc"));
            CamsZcsxGrpjsxxxBc camsZcsxGrpjsxxxBc = new CamsZcsxGrpjsxxxBc();
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("hhbm", grkhpjsx.getHhbm());
            camsZcsxGrpjsxxxBc = grkhpjsxbcService.getOne(queryWrapper);
            if (camsZcsxGrpjsxxxBc != null) {
                grkhpjsx.setCkrp(camsZcsxGrpjsxxxBc.getCkrp());
                grkhpjsx.setBwdk(camsZcsxGrpjsxxxBc.getBwdk());
                grkhpjsx.setCrjye(camsZcsxGrpjsxxxBc.getCrjye());
                grkhpjsx.setDhqye(camsZcsxGrpjsxxxBc.getDhqye());
                grkhpjsx.setZcjye(camsZcsxGrpjsxxxBc.getZcjye());
                grkhpjsx.setWjfl(camsZcsxGrpjsxxxBc.getWjfl());
                grkhpjsx.setSxed(camsZcsxGrpjsxxxBc.getSxed());
                grkhpjsx.setZqje(camsZcsxGrpjsxxxBc.getZqje());
                grkhpjsx.setSfxd(camsZcsxGrpjsxxxBc.getSfxd());
                grkhpjsx.setKhsj(camsZcsxGrpjsxxxBc.getKhsj());
                grkhpjsx.setCkzhs(camsZcsxGrpjsxxxBc.getCkzhs());
                grkhpjsx.setCrjycs(camsZcsxGrpjsxxxBc.getCrjycs().intValue());
                grkhpjsx.setYqcs(camsZcsxGrpjsxxxBc.getYqcs());
                grkhpjsx.setZcjycs(camsZcsxGrpjsxxxBc.getZcjycs().intValue());
                grkhpjsx.setEtcjls(camsZcsxGrpjsxxxBc.getEtcjls());
                grkhpjsx.setSjyhjls(camsZcsxGrpjsxxxBc.getSjyhjls());
                grkhpjsx.setKdlqjls(camsZcsxGrpjsxxxBc.getKdlqjls());
                grkhpjsx.setWyjls(camsZcsxGrpjsxxxBc.getWyjls());
                grkhpjsx.setNxyjls(camsZcsxGrpjsxxxBc.getNxyjls());
            }
            grkhspjlService.save(grkhpjsx);
        } else {
            String spid = grkhpjsx.getBussinessId();
            grkhspjlService.deleteByspid(spid);
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("hhbm", grkhpjsx.getHhbm());
            CamsZcsxGrpjsxxxBc camsZcsxGrpjsxxxBc = new CamsZcsxGrpjsxxxBc();
            camsZcsxGrpjsxxxBc = grkhpjsxbcService.getOne(queryWrapper);
            if (camsZcsxGrpjsxxxBc != null) {
                grkhpjsx.setCkrp(camsZcsxGrpjsxxxBc.getCkrp());
                grkhpjsx.setBwdk(camsZcsxGrpjsxxxBc.getBwdk());
                grkhpjsx.setCrjye(camsZcsxGrpjsxxxBc.getCrjye());
                grkhpjsx.setDhqye(camsZcsxGrpjsxxxBc.getDhqye());
                grkhpjsx.setZcjye(camsZcsxGrpjsxxxBc.getZcjye());
                grkhpjsx.setWjfl(camsZcsxGrpjsxxxBc.getWjfl());
                grkhpjsx.setSxed(camsZcsxGrpjsxxxBc.getSxed());
                grkhpjsx.setZqje(camsZcsxGrpjsxxxBc.getZqje());
                grkhpjsx.setSfxd(camsZcsxGrpjsxxxBc.getSfxd());
                grkhpjsx.setKhsj(camsZcsxGrpjsxxxBc.getKhsj());
                grkhpjsx.setCkzhs(camsZcsxGrpjsxxxBc.getCkzhs());
                grkhpjsx.setCrjycs(camsZcsxGrpjsxxxBc.getCrjycs().intValue());
                grkhpjsx.setYqcs(camsZcsxGrpjsxxxBc.getYqcs());
                grkhpjsx.setZcjycs(camsZcsxGrpjsxxxBc.getZcjycs().intValue());
                grkhpjsx.setEtcjls(camsZcsxGrpjsxxxBc.getEtcjls());
                grkhpjsx.setSjyhjls(camsZcsxGrpjsxxxBc.getSjyhjls());
                grkhpjsx.setKdlqjls(camsZcsxGrpjsxxxBc.getKdlqjls());
                grkhpjsx.setWyjls(camsZcsxGrpjsxxxBc.getWyjls());
                grkhpjsx.setNxyjls(camsZcsxGrpjsxxxBc.getNxyjls());
            }
            grkhpjsx.setSpid(spid);
            grkhpjsx.setBussinessId(spid);
            grkhpjsx.setSqr(sysUser.getRealname());
            grkhpjsx.setUserId(userId);
            grkhpjsx.setSqrq(new Date());
            grkhspjlService.save(grkhpjsx);
            grkhpjsxService.updateGrpjsxxx(spid, grkhpjsx.getZzpddj(), grkhpjsx.getZzsxed(), grkhpjsx.getYj(), grkhpjsx.getHhbm());
            ActXendSpls spls = new ActXendSpls();
            spls.setSpid(spid);
            spls.setHhbm(grkhpjsx.getHhbm());
            spls.setZjhm(grkhpjsx.getZjhm());
            spls.setPddj(grkhpjsx.getZzpddj());
            spls.setJyed(grkhpjsx.getZzsxed());
            spls.setSpyj(grkhpjsx.getYj());
            spls.setUserid(userId);
            spls.setYggh(user.getWorkNo());
            iActXendSplsService.save(spls);
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("hhbm", grkhpjsx.getHhbm());
        Grkhpjsx grpjsxxx = grkhpjsxService.getOne(queryWrapper);
        return Result.ok(grpjsxxx);
    }
*/

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "个人客户评级授信-通过id删除")
    @ApiOperation(value = "个人客户评级授信-通过id删除", notes = "个人客户评级授信-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        grkhpjsxService.removeById(id);
        return Result.ok("删除成功!");
    }


    /**
     * 通过id删除
     *
     * @return
     */
    @AutoLog(value = "个人客户评级授信-模型计算")
    @ApiOperation(value = "个人客户评级授信-", notes = "个人客户评级授信-模型计算")
    @PutMapping(value = "/calculation")
    public Result<?> calculation(@RequestBody JSONObject jsonObject) {
        String zjhm = "";
        if (jsonObject.getString("id") == null) {
            zjhm = jsonObject.getString("zjhm");
        } else {
            ZcsxNhcjxx xendCjxx = iZcsxNhcjxxService.getById(jsonObject.getString("id"));
            zjhm = xendCjxx.getZjhm();
        }
        ivKhglKrkhglService.prepare(zjhm, jsonObject.getString("hhbm"));
        iVKhglGrkhglService.calculateModel(jsonObject.getString("hhbm"), zjhm);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("ZJHM", zjhm);
        queryWrapper.eq("HHBM", jsonObject.getString("hhbm"));
        Grkhpjsx grpjsxxx = grkhpjsxService.getOne(queryWrapper);
        if (grpjsxxx != null) {
            grpjsxxx.setXtpddj(iSysDictService.queryDictTextByKey("pjsx_pddj", grpjsxxx.getXtpddj()));
        }
        return Result.ok(grpjsxxx);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "个人客户评级授信-批量删除")
    @ApiOperation(value = "个人客户评级授信-批量删除", notes = "个人客户评级授信-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.grkhpjsxService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "个人客户评级授信-通过id查询")
    @ApiOperation(value = "个人客户评级授信-通过id查询", notes = "个人客户评级授信-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Grkhpjsx grkhpjsx = grkhpjsxService.getById(id);
        return Result.ok(grkhpjsx);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param grkhpjsx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Grkhpjsx grkhpjsx) {
        return super.exportXls(request, grkhpjsx, Grkhpjsx.class, "小额农贷授信");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Grkhpjsx.class);
    }


    /**
     * 家庭成员信息查看
     *
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/jtcyxx", method = RequestMethod.PUT)
    public Result<?> jtcyxx(@RequestBody JSONObject jsonObject) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            Map<String, String[]> map = new HashMap<>();
            Khhmcxx khhmcxx = new Khhmcxx();
            khhmcxx.setZjhm(jsonObject.getString("zjhm"));
            QueryWrapper<Khhmcxx> queryWrapper = QueryGenerator.initQueryWrapper(khhmcxx, map);
            List<Khhmcxx> khhmcxxList = khhmcxxService.list(queryWrapper);
            if (khhmcxxList.size() != 0) {
                List<Khhmcxx> khhmcjtcyxxList = khhmcxxService.selectByhhbm(khhmcxxList.get(0).getHhbm(), jsonObject.getString("zjhm"));
                khhmcjtcyxxList = parseDictText(khhmcjtcyxxList);
                jsonObject1.put("jtcylist", khhmcjtcyxxList);
                jsonObject1.put("jbxx", khhmcxxList.get(0));
            }
            DictAspect dict = new DictAspect();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return Result.ok(jsonObject1);
    }

    @RequestMapping(value = "/spls", method = RequestMethod.PUT)
    public Result<?> spls(@RequestBody JSONObject jsonObject) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            Map<String, String[]> map = new HashMap<>();
            ActXendSpls spls = new ActXendSpls();
            spls.setSpid(jsonObject.getString("spid"));
            QueryWrapper<ActXendSpls> queryWrapper = QueryGenerator.initQueryWrapper(spls, map);
            queryWrapper.orderByAsc("create_time");
            List<ActXendSpls> splsList = iActXendSplsService.list(queryWrapper);
            if (splsList.size() != 0) {
                splsList = parseDictText(splsList);
                jsonObject1.put("spls", splsList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok(jsonObject1);
    }

    @RequestMapping(value = "/splsXend", method = RequestMethod.PUT)
    public Result<?> splsXend(@RequestBody JSONObject jsonObject) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            Map<String, String[]> map = new HashMap<>();
            ActXendSplsXend spls = new ActXendSplsXend();
            spls.setSpid(jsonObject.getString("spid"));
            QueryWrapper<ActXendSplsXend> queryWrapper = QueryGenerator.initQueryWrapper(spls, map);
            queryWrapper.orderByAsc("create_time");
            List<ActXendSplsXend> splsList = iActXendSplsXendService.list(queryWrapper);
            if (splsList.size() != 0) {
                splsList = parseDictText(splsList);
                jsonObject1.put("spls", splsList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok(jsonObject1);
    }

    public List parseDictText(List list) {
        List<JSONObject> items = new ArrayList<>();
        for (Object list1 : list) {
            ObjectMapper mapper = new ObjectMapper();
            String json = "{}";

            try {
                //解决@JsonFormat注解解析不了的问题详见SysAnnouncement类的@JsonFormat
                json = mapper.writeValueAsString(list1);
            } catch (JsonProcessingException e) {
                log.error("json解析失败" + e.getMessage(), e);
            }
            JSONObject item = new JSONObject();
            try {
                item = JSONObject.parseObject(json);
            } catch (JSONException e) {
                log.error("json解析失败" + e.getMessage(), e);
            }
            //update-begin--Author:scott -- Date:20190603 ----for：解决继承实体字段无法翻译问题------
            //for (Field field : record.getClass().getDeclaredFields()) {
            for (Field field : oConvertUtils.getAllFields(list1)) {
                //update-end--Author:scott  -- Date:20190603 ----for：解决继承实体字段无法翻译问题------
                if (field.getAnnotation(Dict.class) != null) {
                    String code = field.getAnnotation(Dict.class).dicCode();
                    String text = field.getAnnotation(Dict.class).dicText();
                    String table = field.getAnnotation(Dict.class).dictTable();
                    String key = String.valueOf(item.get(field.getName()));

                    //翻译字典值对应的txt
                    String textValue = translateDictValue(code, text, table, key);

                    log.debug(" 字典Val : " + textValue);
                    log.debug(" __翻译字典字段__ " + field.getName() + CommonConstant.DICT_TEXT_SUFFIX + "： " + textValue);
                    item.put(field.getName() + CommonConstant.DICT_TEXT_SUFFIX, textValue);
                }
                //date类型默认转换string格式化日期
                if (field.getType().getName().equals("java.util.Date") && field.getAnnotation(JsonFormat.class) == null && item.get(field.getName()) != null) {
                    SimpleDateFormat aDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    item.put(field.getName(), aDate.format(new Date((Long) item.get(field.getName()))));
                }
            }
            items.add(item);
        }
        return items;
    }

    /**
     * 翻译字典文本
     *
     * @param code
     * @param text
     * @param table
     * @param key
     * @return
     */
    private String translateDictValue(String code, String text, String table, String key) {
        if (oConvertUtils.isEmpty(key)) {
            return null;
        }
        StringBuffer textValue = new StringBuffer();
        String[] keys = key.split(",");
        for (String k : keys) {
            String tmpValue = null;
            log.debug(" 字典 key : " + k);
            if (k.trim().length() == 0) {
                continue; //跳过循环
            }
            if (!StringUtils.isEmpty(table)) {
                tmpValue = dictService.queryTableDictTextByKey(table, text, code, k.trim());
            } else {
                tmpValue = dictService.queryDictTextByKey(code, k.trim());
            }

            if (tmpValue != null) {
                if (!"".equals(textValue.toString())) {
                    textValue.append(",");
                }
                textValue.append(tmpValue);
            }

        }
        return textValue.toString();
    }


    List<Grkhpjsx> commonList = null;
    List<Vgrpjsxspjl> commonJlList = null;
    List<String> selectionList = null;
    List<String> selectionJlList = null;

    StringBuffer selectJson = null;

    HashMap<String, String> yjmap = null;
    HashMap<String, String> ejmap = null;
    HashMap<String, String> sjmap = null;
    HashMap<String, String> cszyMap = null;
    HashMap<String, String> pjMap = null;
    @Autowired
    YjyxdyglMapper yjyxdyglMapper;
    @Autowired
    EjyxdyglMapper ejyxdyglMapper;
    @Autowired
    SjyxdyglMapper sjyxdyglMapper;
    @Autowired
    SysDictItemMapper sysDictItemMapper;


    public void initMap() {
        if (yjmap == null) {
            List<Yjyxdygl> yjyxdygls = yjyxdyglMapper.selectList(null);
            if (yjyxdygls != null && yjyxdygls.size() > 0) {
                yjmap = new HashMap<>();
                for (int i = 0; i < yjyxdygls.size(); i++) {
                    yjmap.put(yjyxdygls.get(i).getDybh(), yjyxdygls.get(i).getDymc());
                }
            }
        }

        if (ejmap == null) {
            List<Ejyxdygl> ejyxdygls = ejyxdyglMapper.selectList(null);
            if (ejyxdygls != null && ejyxdygls.size() > 0) {
                ejmap = new HashMap<>();
                for (int i = 0; i < ejyxdygls.size(); i++) {
                    ejmap.put(ejyxdygls.get(i).getDybh(), ejyxdygls.get(i).getDymc());
                }
            }
        }

        if (sjmap == null) {
            List<Sjyxdygl> sjyxdygls = sjyxdyglMapper.selectList(null);
            if (sjyxdygls != null && sjyxdygls.size() > 0) {
                sjmap = new HashMap<>();
                for (int i = 0; i < sjyxdygls.size(); i++) {
                    sjmap.put(sjyxdygls.get(i).getDybh(), sjyxdygls.get(i).getDymc());
                }
            }
        }

        if (cszyMap == null) {
            List<SysDictItem> cszy = sysDictItemMapper.selectItemsByDictCode("cszy");
            if (cszy != null && cszy.size() > 0) {
                cszyMap = new HashMap<>();
                for (int i = 0; i < cszy.size(); i++) {
                    cszyMap.put(cszy.get(i).getItemValue(), cszy.get(i).getItemText());
                }
            }

        }
        if (pjMap == null) {
            pjMap = new HashMap<>();
            pjMap.put("A", "特级");
            pjMap.put("B", "优秀");
            pjMap.put("C", "较好");
            pjMap.put("D", "一般");
            pjMap.put("E", "级外");
        }


    }


    /**
     * 小额农贷（未审批） -> 年审表下载
     */
    @RequestMapping(value = "downloadNotApprovalYearAuditParams")
    @PermissionData(pageComponent = "xdgl/grkhpjsx/GrkhpjsxList")
    public Result<?> downloadNotApprovalYearAuditParams(Grkhpjsx grkhpjsx,
                                                        HttpServletRequest request) {
        if (yjmap == null || ejmap == null || sjmap == null || cszyMap == null || pjMap == null) {
            yjmap = null;
            ejmap = null;
            sjmap = null;
            cszyMap = null;
            pjMap = null;
            initMap();
        }
        selectionList = new ArrayList<>();
        commonList = new ArrayList<>();

        String selections = request.getParameter("selections");
        if (oConvertUtils.isNotEmpty(selections)) {
            selectionList = Arrays.asList(selections.split(","));
            commonList = grkhpjsxService.getByListZjhm(selectionList);
        } else {
            QueryWrapper<Grkhpjsx> queryWrapper = QueryGenerator.initQueryWrapper(grkhpjsx, request.getParameterMap());
            List<Grkhpjsx> pageList = grkhpjsxService.list(queryWrapper);
            if (pageList != null && pageList.size() > 0)
                commonList = pageList;
        }

        return Result.ok();
    }


    @Autowired
    IVgrpjsxspjlService iVgrpjsxspjlService;

    /**
     * 小额农贷（已审批） -> 年审表下载
     */
    @RequestMapping(value = "/downloadAlreadyApprovalYearAuditParams")
    @PermissionData(pageComponent = "xdgl/pjsxspjl/CamsZcsxGrpjsxxxSpjlList")
    public Result<?> downloadAlreadyApprovalYearAuditParams(Vgrpjsxspjl camsZcsxGrpjsxxxSpjl,
                                                            HttpServletRequest request) {
        camsZcsxGrpjsxxxSpjl.setStatus(2);
        log.info("小额农贷已审批-当前查询条件入参-开始");
        if (yjmap == null || ejmap == null || sjmap == null || cszyMap == null || pjMap == null) {
            yjmap = null;
            ejmap = null;
            sjmap = null;
            cszyMap = null;
            pjMap = null;
            initMap();
        }
        selectionJlList = new ArrayList<>();
        commonJlList = new ArrayList<>();
        selectJson = new StringBuffer();
        String idKey = IdUtil.fastUUID();

        String selections = request.getParameter("selections");
        if (oConvertUtils.isNotEmpty(selections)) {
            selectJson.append(selections);
            selectionJlList = Arrays.asList(selections.split(","));
            commonJlList = camsZcsxGrpjsxxxSpjlMapper.getListByZjhms(selectionJlList);
            redisUtil.set(idKey, selections, 60);

        } else {
            String nf = null;
            if (StringUtils.isNoneBlank(camsZcsxGrpjsxxxSpjl.getYj())) {
                nf = camsZcsxGrpjsxxxSpjl.getYj();
                camsZcsxGrpjsxxxSpjl.setYj(null);
            }
            camsZcsxGrpjsxxxSpjl.setStatus(2);
            String paramString = "";
            if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getSszh())) {
                paramString = paramString + camsZcsxGrpjsxxxSpjl.getSszh() + "|";
            } else {
                paramString = paramString + "not" + "|";
            }
            if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getZjhm())) {
                paramString = paramString + camsZcsxGrpjsxxxSpjl.getZjhm() + "|";
                ;
            } else {
                paramString = paramString + "not" + "|";
            }
            if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getKhmc())) {
                paramString = paramString + camsZcsxGrpjsxxxSpjl.getKhmc() + "|";
                ;
            } else {
                paramString = paramString + "not" + "|";
            }

            redisUtil.set(idKey, paramString, 60);
            /*QueryWrapper<Vgrpjsxspjl> queryWrapper = QueryGenerator.initQueryWrapper(camsZcsxGrpjsxxxSpjl, request.getParameterMap());
            if (nf != null)
                queryWrapper.apply("sqrq > to_date("+nf+",'yyyy')");
            List<Vgrpjsxspjl> list = iVgrpjsxspjlService.list(queryWrapper);
            if (list != null && list.size() > 0){
                commonJlList = list;
                for (int i = 0; i < list.size(); i++) {
                    selectJson.append(list.get(i).getSpid()).append(",");
                }
                log.info("本次列表条件查询一共有{}条数据",commonJlList.size());
            }*/
        }
        log.info("小额农贷已审批-当前查询条件入参{}-结束", idKey);
        return Result.ok(idKey);
    }


    /**
     * 小额农贷（未审批） -> 年审表下载
     */
    @RequestMapping(value = "downloadNotApprovalYearAudit")
    public void downloadNotApprovalYearAudit(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        String townName = StringUtils.EMPTY;
        List<List<String>> dataList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(commonList)) {
            for (Grkhpjsx grkhpjsx : commonList) {
                List<String> colList = Lists.newArrayList();

                String addr = StringUtils.EMPTY;
                if (StringUtils.isNotBlank(grkhpjsx.getEjyxdybh())) {
                    String ej = ejmap.get(grkhpjsx.getEjyxdybh());
                    if (StringUtils.isNotBlank(ej))
                        addr += ej;
                }

                if (StringUtils.isNotBlank(grkhpjsx.getSjyxdybh())) {
                    String sj = ejmap.get(grkhpjsx.getSjyxdybh());
                    if (StringUtils.isNotBlank(sj))
                        addr += sj;
                }
                colList.add(addr);

                colList.add(StringUtils.isNotBlank(grkhpjsx.getKhmc()) ? grkhpjsx.getKhmc() : StringUtils.EMPTY);

                colList.add(StringUtils.isNotBlank(grkhpjsx.getZjhm()) ? grkhpjsx.getZjhm() : StringUtils.EMPTY);

                colList.add(StringUtils.isNotBlank(grkhpjsx.getSjhm()) ? grkhpjsx.getSjhm() : StringUtils.EMPTY);

                colList.add(StringUtils.isNotBlank(grkhpjsx.getJtrs()) ? grkhpjsx.getJtrs() : "1");

                if (grkhpjsx.getJtjzc() != null) {
                    colList.add(StringUtils.isNotBlank(grkhpjsx.getJtjzc().toString()) ? grkhpjsx.getJtjzc().toString() : StringUtils.EMPTY);
                } else {
                    colList.add(StringUtils.EMPTY);
                }

                if (grkhpjsx.getJtjsr() != null) {
                    colList.add(StringUtils.isNotBlank(grkhpjsx.getJtjsr().toString()) ? grkhpjsx.getJtjsr().toString() : StringUtils.EMPTY);
                } else {
                    colList.add(StringUtils.EMPTY);
                }

                //从事职业
                String zszy = StringUtils.EMPTY;
                if (StringUtils.isNotBlank(grkhpjsx.getCszy())) {
                    String zy = cszyMap.get(grkhpjsx.getCszy());
                    if (StringUtils.isNotBlank(zy))
                        zszy = zy;
                }
                colList.add(zszy);

                //初评等级
                String cpdj = StringUtils.EMPTY;
                if (StringUtils.isNotBlank(grkhpjsx.getCpdj())) {
                    String dj = pjMap.get(grkhpjsx.getCpdj());
                    if (StringUtils.isNotBlank(dj))
                        cpdj = dj;
                }
                colList.add(cpdj);


                //模型评定等级
                String aidj = StringUtils.EMPTY;
                if (StringUtils.isNotBlank(grkhpjsx.getXtpddj())) {
                    String dj = pjMap.get(grkhpjsx.getXtpddj());
                    if (StringUtils.isNotBlank(dj))
                        aidj = dj;
                }
                colList.add(aidj);

                //
                if (grkhpjsx.getXtsxed() != null) {
                    colList.add(StringUtils.isNotBlank(grkhpjsx.getXtsxed().toString()) ? grkhpjsx.getXtsxed().toString() : StringUtils.EMPTY);
                } else {
                    colList.add(StringUtils.EMPTY);
                }

                String zzdj = StringUtils.EMPTY;
                if (StringUtils.isNotBlank(grkhpjsx.getZzpddj())) {
                    String dj = pjMap.get(grkhpjsx.getZzpddj());
                    if (StringUtils.isNotBlank(dj))
                        zzdj = dj;
                }
                colList.add(zzdj);
                if (grkhpjsx.getZzsxed() != null) {
                    colList.add(StringUtils.isNotBlank(grkhpjsx.getZzsxed().toString()) ? grkhpjsx.getZzsxed().toString() : StringUtils.EMPTY);
                } else {
                    colList.add(StringUtils.EMPTY);
                }

                colList.addAll(Lists.newArrayList(StringUtils.EMPTY, StringUtils.EMPTY));

                dataList.add(colList);
            }
        }

        super.notApprovalYearAuditTableExport(request, response, "农户授信等级表", townName, dataList);
    }


    @Autowired
    KhhmcxxMapper khhmcxxMapper;

    /**
     * 小额农贷（已审批） -> 年审表下载
     */
    @RequestMapping(value = "downloadAlreadyApprovalYearAudit")
    public void downloadAlreadyApprovalYearAudit(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException, ParseException {
        //todo 获得前期用户的查询权限
        List<List<String>> dataList = Lists.newArrayList();

        if (CollectionUtils.isNotEmpty(commonJlList)) {
            for (Vgrpjsxspjl camsZcsxGrpjsxxxSpjl : commonJlList) {
                List<String> colList = Lists.newArrayList();
                /*
                 * 乡镇	村组名称	姓名	身份证号	联系电话	家庭人口	资产总额	负债	年收入	主要从事项目	信用等级	授信金额	审批时间
                 * */
                String town = StringUtils.EMPTY;
                if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getYjyxdybh())) {
                    String yj = yjmap.get(camsZcsxGrpjsxxxSpjl.getYjyxdybh());
                    if (StringUtils.isNotBlank(yj))
                        town = yj;
                }
                colList.add(town);

                String addr = StringUtils.EMPTY;
                if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getEjyxdybh())) {
                    String ej = ejmap.get(camsZcsxGrpjsxxxSpjl.getEjyxdybh());
                    if (StringUtils.isNotBlank(ej))
                        addr += ej;
                }

                if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getSjyxdybh())) {
                    String sj = ejmap.get(camsZcsxGrpjsxxxSpjl.getSjyxdybh());
                    if (StringUtils.isNotBlank(sj))
                        addr += sj;
                }
                colList.add(addr);
                colList.add(camsZcsxGrpjsxxxSpjl.getKhmc());
                colList.add(camsZcsxGrpjsxxxSpjl.getZjhm());
                colList.add(StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getSjhm()) ? camsZcsxGrpjsxxxSpjl.getSjhm() : StringUtils.EMPTY);
                //家庭人数
                if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getHhbm())) {
                    QueryWrapper queryWrapper = new QueryWrapper();
                    queryWrapper.eq("HHBM", camsZcsxGrpjsxxxSpjl.getHhbm());
                    int result = khhmcxxMapper.selectCount(queryWrapper).intValue();
                    colList.add(result > 1 ? String.valueOf(result) : String.valueOf(1));
                } else {
                    colList.add("1");
                }
                /*
                 * 资产总额	负债	年收入	主要从事项目	信用等级	授信金额	审批时间
                 * */
                if (camsZcsxGrpjsxxxSpjl.getJtjzc() != null) {
                    colList.add(StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getJtjzc().toString()) ? camsZcsxGrpjsxxxSpjl.getJtjzc().toString() : StringUtils.EMPTY);
                } else {
                    colList.add(StringUtils.EMPTY);
                }

                if (camsZcsxGrpjsxxxSpjl.getFzHj() != null) {
                    colList.add(StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getFzHj().toString()) ? camsZcsxGrpjsxxxSpjl.getFzHj().toString() : StringUtils.EMPTY);
                } else {
                    colList.add(StringUtils.EMPTY);
                }

                if (camsZcsxGrpjsxxxSpjl.getJtjsr() != null) {
                    colList.add(StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getJtjsr().toString()) ? camsZcsxGrpjsxxxSpjl.getJtjsr().toString() : StringUtils.EMPTY);
                } else {
                    colList.add(StringUtils.EMPTY);
                }
                //从事职业
                String zszy = StringUtils.EMPTY;
                if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getCszy())) {
                    String zy = cszyMap.get(camsZcsxGrpjsxxxSpjl.getCszy());
                    if (StringUtils.isNotBlank(zy))
                        zszy = zy;
                }
                colList.add(zszy);

                String zzdj = StringUtils.EMPTY;
                if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getZzpddj())) {
                    String dj = pjMap.get(camsZcsxGrpjsxxxSpjl.getZzpddj());
                    if (StringUtils.isNotBlank(dj))
                        zzdj = dj;
                }
                colList.add(zzdj);
                if (camsZcsxGrpjsxxxSpjl.getZzsxed() != null) {
                    colList.add(StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getZzsxed().toString()) ? camsZcsxGrpjsxxxSpjl.getZzsxed().toString() : StringUtils.EMPTY);
                } else {
                    colList.add(StringUtils.EMPTY);
                }
                if (camsZcsxGrpjsxxxSpjl.getUpdateTime() != null) {
                    colList.add(DateUtil.format(camsZcsxGrpjsxxxSpjl.getUpdateTime(), "yyyy-MM-dd"));
                } else {
                    colList.add(StringUtils.EMPTY);
                }

                //
                colList.add(StringUtils.EMPTY);
                colList.add(StringUtils.EMPTY);
                dataList.add(colList);
            }
        }

        super.alreadyApprovalYearAuditTableExport(request, response, "年审表", dataList);
    }

    @Autowired
    CamsZcsxGrpjsxxxMapper camsZcsxGrpjsxxxMapper;
    @Autowired
    CamsZcsxGrpjsxxxBcMapper camsZcsxGrpjsxxxBcMapper;
    @Autowired
    ActBusinessDao actBusinessDao;
    @Autowired
    CamsZcsxNhcjxxMapper camsZcsxNhcjxxMapper;
    @Autowired
    CamsZcsxGrpjsxxxSpjlMapper camsZcsxGrpjsxxxSpjlMapper;
    @Autowired
    ISysDictService sysDictService;

    @PutMapping(value = "/approval")
    public Result<?> approval(@RequestBody Grpjsxspjl grkhpjsx) {
        String uid = IdUtil.simpleUUID();
        CamsZcsxGrpjsxxxSpjl camsZcsxGrpjsxxxSpjl = new CamsZcsxGrpjsxxxSpjl();
        BeanUtils.copyProperties(grkhpjsx, camsZcsxGrpjsxxxSpjl);
        ActBusiness actBusiness = new ActBusiness();
        actBusiness.setId(uid);
        actBusiness.setCreateBy("systemAuto");
        actBusiness.setCreateTime(new Date());
        actBusiness.setDelFlag(0);
        actBusiness.setApplyTime(new Date());
        actBusiness.setProcDefId("sxsplc:5:65036");
        actBusiness.setResult(2);
        actBusiness.setStatus(2);
        actBusiness.setTableId(grkhpjsx.getHhbm());
        actBusiness.setTitle("系统自动审批");
        actBusiness.setUserId("1");
        actBusinessDao.save(actBusiness);
        camsZcsxGrpjsxxxSpjl.setStatus(2);
        camsZcsxGrpjsxxxSpjl.setProcDefId("sxsplc:5:65036");
        camsZcsxGrpjsxxxSpjl.setTitle("系统自动审批");
        camsZcsxGrpjsxxxSpjl.setSpid(uid);
        camsZcsxGrpjsxxxSpjl.setSqrq(new Date());
        grkhpjsxService.updateGrpjsxxx(uid, grkhpjsx.getXtpddj() == null ? "" : grkhpjsx.getXtpddj(), grkhpjsx.getXtsxed() == null ? new BigDecimal(0) : grkhpjsx.getXtsxed(), grkhpjsx.getYj() == null ? "" : grkhpjsx.getYj(), grkhpjsx.getHhbm());
        iCamsZcsxGrpjsxxxSpjlService.save(camsZcsxGrpjsxxxSpjl);
        return Result.ok(camsZcsxGrpjsxxxSpjl);

    }

    public CamsZcsxGrpjsxxxSpjl getAutoApproval(String uid, Khhmcxx khhmcxx, CamsZcsxGrpjsxxx camsZcsxGrpjsxxx, CamsZcsxNhcjxx camsZcsxNhcjxx, CamsZcsxGrpjsxxxBc camsZcsxGrpjsxxxBc) {
        CamsZcsxGrpjsxxxSpjl camsZcsxGrpjsxxxSpjl = new CamsZcsxGrpjsxxxSpjl();
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        if (StringUtils.isNotBlank(khhmcxx.getSszh()))
            camsZcsxGrpjsxxxSpjl.setSszh(khhmcxx.getSszh());
        if (StringUtils.isNotBlank(khhmcxx.getHhbm()))
            camsZcsxGrpjsxxx.setHhbm(khhmcxx.getHhbm());
        if (StringUtils.isNotBlank(khhmcxx.getSsyxdy())) {
            camsZcsxGrpjsxxxSpjl.setQydm(khhmcxx.getSsyxdy());
            //根据三级营销单元去找前两集
            if (khhmcxx.getSsyxdy().length() == 14) {
                camsZcsxGrpjsxxxSpjl.setYjyxdybh(khhmcxx.getSsyxdy());
                camsZcsxGrpjsxxxSpjl.setEjyxdybh(khhmcxx.getSsyxdy().substring(0, 12));
                camsZcsxGrpjsxxxSpjl.setSjyxdybh(khhmcxx.getSsyxdy().substring(0, 9) + "0");
            }
        }
        camsZcsxGrpjsxxxSpjl.setSpid(uid);
        camsZcsxGrpjsxxxSpjl.setSskhjl(sysUser.getWorkNo());
        camsZcsxGrpjsxxxSpjl.setKhmc(camsZcsxNhcjxx.getKhmc());
        camsZcsxGrpjsxxxSpjl.setZjhm(camsZcsxNhcjxx.getZjhm());
        if (StringUtils.isNotBlank(khhmcxx.getYhzgx()))
            camsZcsxGrpjsxxxSpjl.setYhzgx(khhmcxx.getYhzgx());
        if (StringUtils.isNotBlank(khhmcxx.getKhlx()))
            camsZcsxGrpjsxxxSpjl.setKhlx(khhmcxx.getKhlx());
        if (StringUtils.isNotBlank(camsZcsxNhcjxx.getSjhm()))
            camsZcsxGrpjsxxxSpjl.setSjhm(camsZcsxNhcjxx.getSjhm());
        if (StringUtils.isNotBlank(khhmcxx.getXb()))
            camsZcsxGrpjsxxxSpjl.setXb(khhmcxx.getXb());
        if (StringUtils.isNotBlank(camsZcsxNhcjxx.getZz()))
            camsZcsxGrpjsxxxSpjl.setZz(camsZcsxNhcjxx.getZz());
        if (StringUtils.isNotBlank(khhmcxx.getHyzk()))
            camsZcsxGrpjsxxxSpjl.setHyzk(khhmcxx.getHyzk());
        if (StringUtils.isNotBlank(camsZcsxNhcjxx.getCshygz()))
            camsZcsxGrpjsxxxSpjl.setCszy(camsZcsxNhcjxx.getCshygz());
        if (camsZcsxGrpjsxxx.getGdzcZfts() != null)
            camsZcsxGrpjsxxxSpjl.setGdzcZfts(camsZcsxGrpjsxxx.getGdzcZfts());
        if (camsZcsxGrpjsxxx.getGdzcZfmj() != null)
            camsZcsxGrpjsxxxSpjl.setGdzcZfmj(camsZcsxGrpjsxxx.getGdzcZfmj());
        if (camsZcsxGrpjsxxx.getGdzcZfjz() != null)
            camsZcsxGrpjsxxxSpjl.setGdzcZfjz(camsZcsxGrpjsxxx.getGdzcZfjz());
        if (camsZcsxGrpjsxxx.getGdzcQt() != null)
            camsZcsxGrpjsxxxSpjl.setGdzcQt(camsZcsxGrpjsxxx.getGdzcQt());
        if (camsZcsxGrpjsxxx.getGdzcHj() != null)
            camsZcsxGrpjsxxxSpjl.setGdzcHj(camsZcsxGrpjsxxx.getGdzcHj());

        if (camsZcsxGrpjsxxx.getLdzcXjjwhck() != null)
            camsZcsxGrpjsxxxSpjl.setLdzcXjjwhck(camsZcsxGrpjsxxx.getLdzcXjjwhck());
        if (camsZcsxGrpjsxxx.getLdzcQt() != null)
            camsZcsxGrpjsxxxSpjl.setLdzcQt(camsZcsxGrpjsxxx.getLdzcQt());
        if (camsZcsxGrpjsxxx.getLdzcYsk() != null)
            camsZcsxGrpjsxxxSpjl.setLdzcYsk(camsZcsxGrpjsxxx.getLdzcYsk());
        if (camsZcsxGrpjsxxx.getLdzcSfthkh() != null)
            camsZcsxGrpjsxxxSpjl.setLdzcSfthkh(camsZcsxGrpjsxxx.getLdzcSfthkh());
        if (camsZcsxGrpjsxxx.getLdzcSfthyck() != null)
            camsZcsxGrpjsxxxSpjl.setLdzcSfthyck(camsZcsxGrpjsxxx.getLdzcSfthyck());
        if (camsZcsxGrpjsxxx.getLdzcThckje() != null)
            camsZcsxGrpjsxxxSpjl.setLdzcThckje(camsZcsxGrpjsxxx.getLdzcThckje());
        if (camsZcsxGrpjsxxx.getLdzcHj() != null)
            camsZcsxGrpjsxxxSpjl.setLdzcHj(camsZcsxGrpjsxxx.getLdzcHj());
        if (camsZcsxGrpjsxxx.getZzchj() != null)
            camsZcsxGrpjsxxxSpjl.setZzchj(camsZcsxGrpjsxxx.getZzchj());

        if (camsZcsxGrpjsxxx.getFzWhjk() != null)
            camsZcsxGrpjsxxxSpjl.setFzWhjk(camsZcsxGrpjsxxx.getFzWhjk());
        if (camsZcsxGrpjsxxx.getFzQtyhjk() != null)
            camsZcsxGrpjsxxxSpjl.setFzQtyhjk(camsZcsxGrpjsxxx.getFzQtyhjk());
        if (camsZcsxGrpjsxxx.getFzSrjkhqtjk() != null)
            camsZcsxGrpjsxxxSpjl.setFzSrjkhqtjk(camsZcsxGrpjsxxx.getFzSrjkhqtjk());
        if (camsZcsxGrpjsxxx.getFzYfk() != null)
            camsZcsxGrpjsxxxSpjl.setFzYfk(camsZcsxGrpjsxxx.getFzYfk());
        if (camsZcsxGrpjsxxx.getFzQtfz() != null)
            camsZcsxGrpjsxxxSpjl.setFzQtfz(camsZcsxGrpjsxxx.getFzQtfz());
        if (camsZcsxGrpjsxxx.getFzWtrdb() != null)
            camsZcsxGrpjsxxxSpjl.setFzWtrdb(camsZcsxGrpjsxxx.getFzWtrdb());
        if (camsZcsxGrpjsxxx.getFzHj() != null)
            camsZcsxGrpjsxxxSpjl.setFzHj(camsZcsxGrpjsxxx.getFzHj());

        if (camsZcsxGrpjsxxx.getSrZz() != null)
            camsZcsxGrpjsxxxSpjl.setSrZz(camsZcsxGrpjsxxx.getSrZz());
        if (camsZcsxGrpjsxxx.getSrYz() != null)
            camsZcsxGrpjsxxxSpjl.setSrYz(camsZcsxGrpjsxxx.getSrYz());
        if (camsZcsxGrpjsxxx.getSrLw() != null)
            camsZcsxGrpjsxxxSpjl.setSrLw(camsZcsxGrpjsxxx.getSrLw());
        if (camsZcsxGrpjsxxx.getSrGsy() != null)
            camsZcsxGrpjsxxxSpjl.setSrGsy(camsZcsxGrpjsxxx.getSrGsy());
        if (camsZcsxGrpjsxxx.getSrQtsr() != null)
            camsZcsxGrpjsxxxSpjl.setSrQtsr(camsZcsxGrpjsxxx.getSrQtsr());
        if (camsZcsxGrpjsxxx.getSrHj() != null)
            camsZcsxGrpjsxxxSpjl.setSrHj(camsZcsxGrpjsxxx.getSrHj());

        if (camsZcsxGrpjsxxx.getZcJy() != null)
            camsZcsxGrpjsxxxSpjl.setZcJy(camsZcsxGrpjsxxx.getZcJy());
        if (camsZcsxGrpjsxxx.getZcYl() != null)
            camsZcsxGrpjsxxxSpjl.setZcYl(camsZcsxGrpjsxxx.getZcYl());
        if (camsZcsxGrpjsxxx.getZcYlao() != null)
            camsZcsxGrpjsxxxSpjl.setZcYlao(camsZcsxGrpjsxxx.getZcYlao());
        if (camsZcsxGrpjsxxx.getZcSccb() != null)
            camsZcsxGrpjsxxxSpjl.setZcSccb(camsZcsxGrpjsxxx.getZcSccb());
        if (camsZcsxGrpjsxxx.getZcRcsh() != null)
            camsZcsxGrpjsxxxSpjl.setZcRcsh(camsZcsxGrpjsxxx.getZcRcsh());
        if (camsZcsxGrpjsxxx.getZcQtzc() != null)
            camsZcsxGrpjsxxxSpjl.setZcQtzc(camsZcsxGrpjsxxx.getZcQtzc());
        if (camsZcsxGrpjsxxx.getZcHj() != null)
            camsZcsxGrpjsxxxSpjl.setZcHj(camsZcsxGrpjsxxx.getZcHj());

        if (camsZcsxGrpjsxxx.getJtjsr() != null)
            camsZcsxGrpjsxxxSpjl.setJtjsr(camsZcsxGrpjsxxx.getJtjsr());

        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getShsyBlsh()))
            camsZcsxGrpjsxxxSpjl.setShsyBlsh(camsZcsxGrpjsxxx.getShsyBlsh());
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getShsySfqk()))
            camsZcsxGrpjsxxxSpjl.setShsySfqk(camsZcsxGrpjsxxx.getShsySfqk());
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getShsySfygld()))
            camsZcsxGrpjsxxxSpjl.setShsySfygld(camsZcsxGrpjsxxx.getShsySfygld());
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getShsySfdjns()))
            camsZcsxGrpjsxxxSpjl.setShsySfdjns(camsZcsxGrpjsxxx.getShsySfdjns());
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getShsySflqbz()))
            camsZcsxGrpjsxxxSpjl.setShsySflqbz(camsZcsxGrpjsxxx.getShsySflqbz());
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getShsySfxsfz()))
            camsZcsxGrpjsxxxSpjl.setShsySfxsfz(camsZcsxGrpjsxxx.getShsySfxsfz());
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getShsySfss()))
            camsZcsxGrpjsxxxSpjl.setShsySfss(camsZcsxGrpjsxxx.getShsySfss());


        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getDhzpjPxpj()))
            camsZcsxGrpjsxxxSpjl.setDhzpjPxpj(camsZcsxGrpjsxxx.getDhzpjPxpj());
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getDhzpjXypj()))
            camsZcsxGrpjsxxxSpjl.setDhzpjXypj(camsZcsxGrpjsxxx.getDhzpjXypj());
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getCpdj()))
            camsZcsxGrpjsxxxSpjl.setCpdj(camsZcsxGrpjsxxx.getCpdj());

        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getQtbzqk()))
            camsZcsxGrpjsxxxSpjl.setQtbzqk(camsZcsxGrpjsxxx.getQtbzqk());
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getFzlx()))
            camsZcsxGrpjsxxxSpjl.setFzlx(camsZcsxGrpjsxxx.getFzlx());
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getSfjdlkpkh()))
            camsZcsxGrpjsxxxSpjl.setSfjdlkpkh(camsZcsxGrpjsxxx.getSfjdlkpkh());
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getBz()))
            camsZcsxGrpjsxxxSpjl.setBz(camsZcsxGrpjsxxx.getBz());
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getYj()))
            camsZcsxGrpjsxxxSpjl.setYj(camsZcsxGrpjsxxx.getYj());

        camsZcsxGrpjsxxxSpjl.setCreateBy(sysUser.getWorkNo());
        camsZcsxGrpjsxxxSpjl.setCreateTime(new Date());
        camsZcsxGrpjsxxxSpjl.setUpdateBy(sysUser.getWorkNo());
        camsZcsxGrpjsxxxSpjl.setUpdateTime(new Date());

        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getXtpddj()))
            camsZcsxGrpjsxxxSpjl.setXtpddj(camsZcsxGrpjsxxx.getXtpddj());
        if (camsZcsxGrpjsxxx.getXtpddf() != null)
            camsZcsxGrpjsxxxSpjl.setXtpddf(camsZcsxGrpjsxxx.getXtpddf());
        if (camsZcsxGrpjsxxx.getXtsxed() != null)
            camsZcsxGrpjsxxxSpjl.setXtsxed(camsZcsxGrpjsxxx.getXtsxed());

        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getZzpddj()))
            camsZcsxGrpjsxxxSpjl.setZzpddj(camsZcsxGrpjsxxx.getZzpddj());
        if (camsZcsxGrpjsxxx.getZzsxed() != null)
            camsZcsxGrpjsxxxSpjl.setZzsxed(camsZcsxGrpjsxxx.getZzsxed());


        if (camsZcsxGrpjsxxxBc != null) {
            if (camsZcsxGrpjsxxxBc.getDhqye() != null)
                camsZcsxGrpjsxxxSpjl.setDhqye(camsZcsxGrpjsxxxBc.getDhqye());
            if (camsZcsxGrpjsxxxBc.getCrjye() != null)
                camsZcsxGrpjsxxxSpjl.setCrjye(camsZcsxGrpjsxxxBc.getCrjye());
            if (camsZcsxGrpjsxxxBc.getZcjye() != null)
                camsZcsxGrpjsxxxSpjl.setZcjye(camsZcsxGrpjsxxxBc.getZcjye());
            if (camsZcsxGrpjsxxxBc.getCkzhs() != null)
                camsZcsxGrpjsxxxSpjl.setCkzhs(camsZcsxGrpjsxxxBc.getCkzhs());
            if (camsZcsxGrpjsxxxBc.getKdlqjls() != null)
                camsZcsxGrpjsxxxSpjl.setKdlqjls(camsZcsxGrpjsxxxBc.getKdlqjls());
            if (camsZcsxGrpjsxxxBc.getWyjls() != null)
                camsZcsxGrpjsxxxSpjl.setWyjls(camsZcsxGrpjsxxxBc.getWyjls());
            if (camsZcsxGrpjsxxxBc.getSjyhjls() != null)
                camsZcsxGrpjsxxxSpjl.setSjyhjls(camsZcsxGrpjsxxxBc.getSjyhjls());
            if (camsZcsxGrpjsxxxBc.getEtcjls() != null)
                camsZcsxGrpjsxxxSpjl.setEtcjls(camsZcsxGrpjsxxxBc.getEtcjls());
            if (camsZcsxGrpjsxxxBc.getNxyjls() != null)
                camsZcsxGrpjsxxxSpjl.setNxyjls(camsZcsxGrpjsxxxBc.getNxyjls());
            if (StringUtils.isNotBlank(camsZcsxGrpjsxxxBc.getWjfl()))
                camsZcsxGrpjsxxxSpjl.setWjfl(camsZcsxGrpjsxxxBc.getWjfl());
            if (camsZcsxGrpjsxxxBc.getBwdk() != null)
                camsZcsxGrpjsxxxSpjl.setBwdk(camsZcsxGrpjsxxxBc.getBwdk());
            if (camsZcsxGrpjsxxxBc.getYqcs() != null)
                camsZcsxGrpjsxxxSpjl.setYqcs(camsZcsxGrpjsxxxBc.getYqcs());
            if (camsZcsxGrpjsxxxBc.getSxed() != null)
                camsZcsxGrpjsxxxSpjl.setSxed(camsZcsxGrpjsxxxBc.getSxed());
            if (camsZcsxGrpjsxxxBc.getZqje() != null)
                camsZcsxGrpjsxxxSpjl.setZqje(camsZcsxGrpjsxxxBc.getZqje());
            if (StringUtils.isNotBlank(camsZcsxGrpjsxxxBc.getSfxd()))
                camsZcsxGrpjsxxxSpjl.setSfxd(camsZcsxGrpjsxxxBc.getSfxd());
            if (camsZcsxGrpjsxxxBc.getKhsj() != null)
                camsZcsxGrpjsxxxSpjl.setKhsj(camsZcsxGrpjsxxxBc.getKhsj());
            if (camsZcsxGrpjsxxxBc.getCkrp() != null)
                camsZcsxGrpjsxxxSpjl.setCkrp(camsZcsxGrpjsxxxBc.getCkrp());
            if (camsZcsxGrpjsxxxBc.getCrjycs() != null)
                camsZcsxGrpjsxxxSpjl.setCrjycs(camsZcsxGrpjsxxxBc.getCrjycs().intValue());
            if (camsZcsxGrpjsxxxBc.getZcjycs() != null)
                camsZcsxGrpjsxxxSpjl.setZcjycs(camsZcsxGrpjsxxxBc.getZcjycs().intValue());
            if (camsZcsxGrpjsxxxBc.getSfds() != null)
                camsZcsxGrpjsxxxSpjl.setSfds(camsZcsxGrpjsxxxBc.getSfds().intValue());
        }
        camsZcsxGrpjsxxxSpjl.setStatus(2);
        camsZcsxGrpjsxxxSpjl.setProcDefId("sxsplc:5:65036");
        camsZcsxGrpjsxxxSpjl.setTitle("系统自动审批");
        camsZcsxGrpjsxxxSpjl.setSqrq(new Date());
        return camsZcsxGrpjsxxxSpjl;
    }

    @Value(value = "${common.path.upload}")
    private String uploadpath;

    @RequestMapping(value = "/farmerRankInfo", method = RequestMethod.GET)
    public void getFarmerRankInfoById(HttpServletResponse response)
            throws Exception {
        if (CollectionUtils.isNotEmpty(commonJlList)) {

            long begin = System.currentTimeMillis();
            List<FamerExportWord> list = new ArrayList<>();
            WordUtils wordUtils = new WordUtils("infoCollect.docx", "infoCollect.ftl");
            String responseFileName = StringUtils.join("信息采集表_", System.currentTimeMillis(), ".zip");
            wordUtils.setZipHeader(response, responseFileName, wordUtils.utf8().name());

            List<File> wordFileList = new ArrayList<>();
            for (int i = 0; i < commonJlList.size(); i++) {
                Vgrpjsxspjl camsZcsxGrpjsxxxSpjl = commonJlList.get(i);
                FamerExportWord word = iCamsZcsxGrpjsxxxSpjlService.getWord(camsZcsxGrpjsxxxSpjl.getSpid(), camsZcsxGrpjsxxxSpjl.getHhbm());
                if (word != null) {
                    list.add(word);
                    File tempDirectory = wordUtils.generateDirectory(uploadpath + File.separator + "cjxxzip" + File.separator);
                    File wordFile = new File(tempDirectory, StringUtils.join(
                            word.getName() + IdUtil.fastUUID(),
                            "_",
                            "等级评定信息采集表",
                            ".docx"
                    ));
                    /*log.info("生成农户信用等级评定信息采集表 位置在 |{}|",wordFile.getAbsolutePath());
                    if (wordFile.exists()) {
                        log.info("第{}个Word文件是重复文件,暂不生成，剩余{}个未生成，存在文件路径为：{}", (i + 1), (commonJlList.size() - (i + 1)), wordFile.getAbsolutePath());
                        continue;
                    }*/
                    /*if (!wordFile.exists())
                        wordFileList.add(wordFile);*/
                    wordFileList.add(wordFile);
                    try {
                        wordUtils.generateDocxFile(new FileOutputStream(wordFile), word);
                        log.info("生成第{}个Word文件，剩余{}个未生成，路径为：{}", (i + 1), (commonJlList.size() - (i + 1)), wordFile.getAbsolutePath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (TemplateException e) {
                        e.printStackTrace();
                    }
                }
            }//循环结束位置
            wordUtils.generateZipFileByFiles(wordFileList, response.getOutputStream());
            //删除目录下所有文件
            File file = new File(uploadpath + File.separator + "cjxxzip" + File.separator);
            FileUtils.deleteFolder(file);
            log.info("生成Zip文件耗时：{}秒", (System.currentTimeMillis() - begin) / 1000.0);
        }

    }

    @Value(value = "${common.path.ip}")
    private String ip;

    @RequestMapping("/infoWord")
    public Result<?> infoWord(String idKey) {
        Map<String, Object> map = new HashMap<>();
        map.put("idKey", idKey);
        System.out.println("selectJson----==" + selectJson);
        String result = HttpUtil.get(ip + "word/infoWord", map);
        System.out.println("infoWord----==" + result);
        return JSONObject.parseObject(result, Result.class);
    }


    @Autowired
    IGrpjsxspjlService grpjsxspjlService;
    @Autowired
    ZcsxNhcjxxMapper zcsxNhcjxxMapper;
    @Autowired
    ActXendSplsMapper actXendSplsMapper;
    @Autowired
    EcifPersonMapper ecifPersonMapper;
    @Autowired
    IDictValueQuery iDictValueQuery;


    List<Vgrpjsxspjl> eLoanDatas = null;

    /**
     * 小额农贷（已审批） -> 年审表下载
     */
    @RequestMapping(value = "/downloadELoanParams")
    @PermissionData(pageComponent = "xdgl/pjsxspjl/CamsZcsxGrpjsxxxSpjlList")
    public Result<?> downloadELoanParams(Vgrpjsxspjl camsZcsxGrpjsxxxSpjl,
                                         HttpServletRequest request) {
        camsZcsxGrpjsxxxSpjl.setStatus(2);
        log.info("小额农贷已审批-当前查询条件入参-开始{}", camsZcsxGrpjsxxxSpjl.toString());
        eLoanDatas = null;

        String selections = request.getParameter("selections");
        if (oConvertUtils.isNotEmpty(selections)) {
            List<String> list = Arrays.asList(selections.split(","));
            List<Vgrpjsxspjl> listBySpids = camsZcsxGrpjsxxxSpjlMapper.getListBySpids(list);
            if (CollUtil.isNotEmpty(listBySpids))
                eLoanDatas = listBySpids;

        } else {
            String nf = null;
            if (StringUtils.isNoneBlank(camsZcsxGrpjsxxxSpjl.getYj())) {
                nf = camsZcsxGrpjsxxxSpjl.getYj();
                camsZcsxGrpjsxxxSpjl.setYj(null);
            }
            camsZcsxGrpjsxxxSpjl.setStatus(2);

            QueryWrapper<Vgrpjsxspjl> queryWrapper = QueryGenerator.initQueryWrapper(camsZcsxGrpjsxxxSpjl, request.getParameterMap());
            if (nf != null)
                queryWrapper.apply("sqrq > to_date(" + nf + ",'yyyy')");
            List<Vgrpjsxspjl> list = iVgrpjsxspjlService.list(queryWrapper);
            if (CollUtil.isNotEmpty(list)) {
                eLoanDatas = list;
                System.out.println(list.size());
            }
        }
        log.info("湘农e贷-当前查询条件入参-结束");
        return Result.ok();
    }


    @GetMapping(value = "/eLoan")
    //@PermissionData(pageComponent = "xdgl/pjsxspjl/CamsZcsxGrpjsxxxSpjlList")
    public ModelAndView queryPageList2() {
        Map<String, Object> map = new HashMap<>();
        List<ELoanVO> eLoanVOS = new ArrayList<>();
        if (CollUtil.isNotEmpty(eLoanDatas)) {
            //获取基点
            String jd = "267.5";
            List<DictModel> dictModels = sysDictService.queryDictItemsByCode(DictConstant.ELOAN_JD);
            if (CollUtil.isNotEmpty(dictModels)) {
                jd = dictModels.get(0).getValue();
            }

            int sxdqr = 36;
            List<DictModel> dictModels2 = sysDictService.queryDictItemsByCode(DictConstant.ELOAN_SXDQR);
            if (CollUtil.isNotEmpty(dictModels2)) {
                sxdqr = Integer.valueOf(dictModels2.get(0).getValue());
            }

            String zrbl1 = "10";
            String zrbl2 = "10";
            String zrbl3 = "10";
            String zrbl4 = "10";
            String zrbl5 = "10";
            String zrbl6 = "10";

            List<DictModel> dictModels3 = sysDictService.queryDictItemsByCode(DictConstant.ELOAN_ZRBL);
            if (CollUtil.isNotEmpty(dictModels3)) {
                for (int i = 0; i < dictModels3.size(); i++) {
                    if ("1".equals(dictModels3.get(i).getText()))
                        zrbl1 = dictModels3.get(i).getValue();
                    else if ("2".equals(dictModels3.get(i).getText()))
                        zrbl2 = dictModels3.get(i).getValue();
                    else if ("3".equals(dictModels3.get(i).getText()))
                        zrbl3 = dictModels3.get(i).getValue();
                    else if ("4".equals(dictModels3.get(i).getText()))
                        zrbl4 = dictModels3.get(i).getValue();
                    else if ("5".equals(dictModels3.get(i).getText()))
                        zrbl5 = dictModels3.get(i).getValue();
                    else if ("6".equals(dictModels3.get(i).getText()))
                        zrbl6 = dictModels3.get(i).getValue();
                }
            }

            //获取所有用户
            List<SysUser> sysUsers = sysUserService.list();
            List<SysUser> fxjl = sysUserService.findUsersByRoleId("fxjl");

            if (eLoanDatas != null) {
                for (int i = 0; i < eLoanDatas.size(); i++) {
                    ELoanVO eLoanVO = new ELoanVO();
                    Vgrpjsxspjl g = eLoanDatas.get(i);

                    if (StringUtils.isNotBlank(g.getKhmc()))
                        eLoanVO.setRow(g.getKhmc());
                    if (StringUtils.isNotBlank(g.getZjhm())) {
                        eLoanVO.setRow2(g.getZjhm());
                        int age = 0;
                        String zjhm = g.getZjhm();
                        if (IdcardUtil.isValidCard(zjhm)) {
                            age = IdcardUtil.getAgeByIdCard(zjhm);
                        }
                        if (age > 0 && age <= 30) {
                            eLoanVO.setRow34("30-大学专科和专科学校");
                        } else {
                            int i1 = RandomUtil.randomInt(0, 3);
                            if (i1 == 0) {
                                eLoanVO.setRow34("60-高中");
                            } else if (i1 == 1) {
                                eLoanVO.setRow34("70-初中");
                            } else {
                                eLoanVO.setRow34("80-小学");
                            }
                        }
                    }
                    if (g.getZzsxed() != null) {
                        eLoanVO.setRow3(g.getZzsxed().toString());
                        int i1 = RandomUtil.randomInt(0, 6);
                        if (i1 == 0) {
                            eLoanVO.setRow44("A0111-稻谷种植");
                        } else if (i1 == 2) {
                            eLoanVO.setRow44("A0143-花卉种植");
                        } else if (i1 == 3) {
                            eLoanVO.setRow44("A0159-其他水果种植");
                        } else if (i1 == 4) {
                            eLoanVO.setRow44("A0122-油料种植");
                        } else if (i1 == 5) {
                            eLoanVO.setRow44("A0313-猪的饲养");
                        } else {
                            eLoanVO.setRow44("A0412-内陆养殖");
                        }

                    }
                    eLoanVO.setRow4(jd);

                    eLoanVO.setRow5(sxdqr + "");

                    /* 浏阳版本默认为 04-城乡居民
                    if (StringUtils.isNotBlank(g.getKhlx()))
                        eLoanVO.setRow6(khfq(g.getKhlx()));*/
                    if (StringUtils.isNotBlank(g.getSskhjl())) {

                        eLoanVO.setRow7(g.getSskhjl());
                        eLoanVO.setRow8(g.getSskhjl());
                        eLoanVO.setRow9(zrbl1);
                        eLoanVO.setRow12(g.getSskhjl());
                        eLoanVO.setRow13(zrbl3);
                    }

                    /* 浏阳版本调查责任人为客户惊恐
                    QueryWrapper queryWrapper1 = new QueryWrapper();
                    queryWrapper1.eq("zjhm",g.getZjhm());
                    List<ZcsxNhcjxx> list1 = zcsxNhcjxxMapper.selectList(queryWrapper1);
                    if (CollUtil.isNotEmpty(list1)){
                        ZcsxNhcjxx zcsxNhcjxx = list1.get(0);
                        if (StringUtils.isNotBlank(zcsxNhcjxx.getLrr()))
                            eLoanVO.setRow8(zcsxNhcjxx.getLrr());
                        eLoanVO.setRow9(zrbl1);
                    }else {
                        continue;
                    }*/

                    //调查责任2 空值
                    //eLoanVO.setRow11(zrbl2);

                    /*if (StringUtils.isNotBlank(g.getSskhjl()))
                        eLoanVO.setRow12(g.getSskhjl());
                    eLoanVO.setRow13(zrbl3);*/
                    //风险经理
                    //获取该支行的风险经理  根据sskhjl获取该客户的支行
                    if (CollUtil.isNotEmpty(sysUsers)) {
                        log.info("==={}===", sysUsers.size());
                        for (int j = 0; j < sysUsers.size(); j++) {
                            SysUser sysUser = sysUsers.get(j);
                            if (sysUser.getUsername().equals(g.getSskhjl())) {
                                if (StringUtils.isNoneBlank(sysUser.getOrgCode()) && CollUtil.isNotEmpty(fxjl)) {
                                    for (int k = 0; k < fxjl.size(); k++) {
                                        SysUser sysUser1 = fxjl.get(k);
                                        if (StringUtils.isNoneBlank(sysUser1.getOrgCode()) && sysUser1.getOrgCode().equals(sysUser.getOrgCode())) {
                                            if (StringUtils.isNotBlank(sysUser1.getUsername()))
                                                eLoanVO.setRow14(sysUser1.getUsername());
                                        }
                                    }
                                }
                            }

                        }
                    }

                    eLoanVO.setRow15(zrbl4);


                    QueryWrapper queryWrapper2 = new QueryWrapper();
                    queryWrapper2.eq("spid", g.getSpid());
                    List<ActXendSpls> list2 = actXendSplsMapper.selectList(queryWrapper2);
                    if (CollUtil.isNotEmpty(list2)) {
                        for (int j = 0; j < list2.size(); j++) {
                            ActXendSpls actXendSpls = list2.get(j);
                            if (!g.getSskhjl().equals(actXendSpls.getCreateBy())) {
                            /*colList.add(actXendSpls.getCreateBy());
                            colList.add(zrbl4);*/
                                if (StringUtils.isNotBlank(actXendSpls.getCreateBy()))
                                    eLoanVO.setRow16(actXendSpls.getCreateBy());
                                eLoanVO.setRow17(zrbl5);

                                //DateTime spsj = DateUtil.offsetMonth(g.getCreateTime(), sxdqr);
                                //eLoanVO.setRow5(DateUtil.format(spsj,"yyyy-MM-dd"));
                            }
                        }
                    }

                    if (StringUtils.isNotBlank(g.getSskhjl()))
                        eLoanVO.setRow18(g.getSskhjl());
                    eLoanVO.setRow19(zrbl6);

                    List<EcifPerson> byZjhms = ecifPersonMapper.getByZjhm(g.getZjhm());
                    EcifPerson byZjhm = null;
                    if (CollUtil.isNotEmpty(byZjhms)) {
                        byZjhm = byZjhms.get(byZjhms.size() - 1);
                    }

                    if (byZjhm != null) {
                        //民族
                        if (StringUtils.isNoneBlank(byZjhm.getNationality())) {
                            //eLoanVO.setRow20(byZjhm.getNationality());
                            eLoanVO.setRow20(ELoandDictUtil.getMz(byZjhm.getNationality()));
                        }

                    }
                    /* 浏阳版本默认为 01-农户 11-一般农户
                    if (StringUtils.isNotBlank(g.getKhlx()))
                        eLoanVO.setRow21(khlx1(g.getKhlx()));
                    if (StringUtils.isNotBlank(g.getKhlx()))
                        eLoanVO.setRow22(khlx2(g.getKhlx()));*/

                    if (StringUtils.isNoneBlank(g.getYjyxdybh())) {
                        String s = iDictValueQuery.getyjValue(g.getYjyxdybh());
                        if (StringUtils.isNoneBlank(s)) {
                            eLoanVO.setRow23(ELoandDictUtil.getXz(s));
                        }
                    }

                    //有无子女
                    QueryWrapper jt = new QueryWrapper();
                    jt.eq("hhbm", g.getHhbm());
                    jt.orderByDesc("yhzgx");
                    List<Khhmcxx> list3 = khhmcxxMapper.selectList(jt);

                    if (CollUtil.isNotEmpty(list3)) {
                        for (int j = 0; j < list3.size(); j++) {
                            if ("3".equals(list3.get(j).getYhzgx()) || "4".equals(list3.get(j).getYhzgx())) {
                                eLoanVO.setRow24("1-有");
                            }

                            if ("2".equals(list3.get(j).getYhzgx())) {
                                if (StringUtils.isNoneBlank(list3.get(j).getLxfs()))
                                    eLoanVO.setRow29(list3.get(j).getLxfs());

                                eLoanVO.setRow27(list3.get(j).getKhmc());
                                eLoanVO.setRow28(list3.get(j).getZjhm());
                            }

                        }

                        eLoanVO.setRow30(list3.size() + "");
                    }

                    if (byZjhm != null && byZjhm.getHealth() != null) {
                        eLoanVO.setRow25(ELoandDictUtil.getJK(byZjhm.getHealth()));
                    }

                    if (StringUtils.isNoneBlank(g.getHyzk())) {
                        eLoanVO.setRow26(hyzk(g.getHyzk()));
                    }

                    if (byZjhm != null && byZjhm.getResidence() != null) {
                        eLoanVO.setRow33(ELoandDictUtil.getJzzk(byZjhm.getResidence()));
                    }

                    if (byZjhm != null && byZjhm.getHighestSchooling() != null) {
                        eLoanVO.setRow34(ELoandDictUtil.getZgxl(byZjhm.getHighestSchooling()));
                    }

                    if (StringUtils.isNotBlank(g.getZz()))
                        eLoanVO.setRow35(g.getZz());
                    if (StringUtils.isNotBlank(g.getZz()))
                        eLoanVO.setRow36(g.getZz());

                    if (StringUtils.isNoneBlank(g.getSjhm())) {
                        eLoanVO.setRow39(g.getSjhm());
                    }

                    if (StringUtils.isNotBlank(g.getYhzgx()))
                        eLoanVO.setRow40(g.getYhzgx().equals("1") ? "1-是" : "2-否");

                    if (byZjhm != null && byZjhm.getUnitName() != null) {
                        eLoanVO.setRow42(byZjhm.getUnitName());
                    }

                    if (byZjhm != null && byZjhm.getDuty() != null) {
                        eLoanVO.setRow43(ELoandDictUtil.getZw(byZjhm.getDuty()));
                    }

                    if (StringUtils.isNoneBlank(g.getCszy())) {
                        eLoanVO.setRow48(setCszy(g.getCszy()));
                    }

                    if (byZjhm != null && byZjhm.getCareerTitle() != null) {
                        eLoanVO.setRow49(zc(byZjhm.getCareerTitle()));
                    }

                    eLoanVOS.add(eLoanVO);

                }
            }


        }
        map.put("list", eLoanVOS);

        ModelAndView mv = new ModelAndView(new TemplateExcelView());
        String port = environment.getProperty("common.path.export");
        //导出文件名称
        mv.addObject(JxlsConstants.FILE_NAME, "newhnkd.xls");
        mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("newhnkd.xls"));
        mv.addObject(JxlsConstants.SAVE_FILE_NAME, port + "/newhnkd.xls");
        mv.addObject(JxlsConstants.MAP_DATA, map);
        return mv;

    }

    @Autowired
    private Environment environment;

    public String khfq(String khlx) {
        if ("2".equals(khlx))
            return "02-个体工商户";
        if ("1".equals(khlx) || "3".equals(khlx) || "4".equals(khlx) || "5".equals(khlx))
            return "03-私企人员";
        if ("6".equals(khlx) || "7".equals(khlx))
            return "04-城乡居民";
        return "05-其他";
    }

    public String khlx1(String khlx) {
        if ("6".equals(khlx))
            return "01-农户";
        if ("7".equals(khlx))
            return "02-非农个体工商户";
        return "03-自然人";
    }

    public String khlx2(String khlx) {
        if ("6".equals(khlx))
            return "11-一般农户";
        if ("3".equals(khlx) || "4".equals(khlx))
            return "16-小微企业主";
        if ("7".equals(khlx))
            return "32-其他自然人";
        return "19-其他";
    }

    public String hyzk(String hyzk) {
        // 1wei 2yi 3li 4qita
        if ("10".equals(hyzk))
            return "1";
        if ("20".equals(hyzk))
            return "2";
        if ("40".equals(hyzk))
            return "3";
        return "4";
    }

    public String zc(String zc) {
        // 1wei 2yi 3li 4qita
        if ("1".equals(zc))
            return "1-高级";
        if ("2".equals(zc))
            return "2-中级";
        if ("3".equals(zc))
            return "3-初级";
        if ("9".equals(zc))
            return "9-未知";
        return "0-无";
    }

    public String setCszy(String s) {
        if (StringUtils.isNotBlank(s)) {
            if (s.startsWith("0"))
                return "0-国家机关、党群组织、企业、事业单位负责人、公务员、本行员工";
            if (s.startsWith("1"))
                return "1-专业技术人员";
            if (s.startsWith("3"))
                return "3-办事人员和有关人员";
            if (s.startsWith("4"))
                return "4-商业、服务业人员";
            if (s.startsWith("5"))
                return "5-农、林、牧、渔、水利业生产人员";
            if (s.startsWith("6"))
                return "6-生产、运输设备操作人员及有关人员";
            if (s.startsWith("x"))
                return "x-军人";
            if (s.startsWith("y"))
                return "y-不便分类的其他从业人员";
            return "z-未知";
        }
        return "z";
    }


    @RequestMapping(value = "/xdcjbParams")
    @PermissionData(pageComponent = "xdgl/pjsxspjl/CamsZcsxGrpjsxxxSpjlList")
    public Result<?> xdcjbParams(Vgrpjsxspjl camsZcsxGrpjsxxxSpjl, HttpServletRequest request) {


        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isNoneBlank(request.getParameter("selections")))
            map.put("selections", request.getParameter("selections"));
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getSszh()))
            map.put("sszh", camsZcsxGrpjsxxxSpjl.getSszh());
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getKhmc()))
            map.put("khmc", camsZcsxGrpjsxxxSpjl.getKhmc());
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getZjhm()))
            map.put("spid", camsZcsxGrpjsxxxSpjl.getZjhm());
        map.put("username", getUsername());
        String result = HttpUtil.get(ip + "word/xdcjbWord", map);
        return JSONObject.parseObject(result, Result.class);
    }


//    @GetMapping(value = "/batchQueryQxfk")
//    @PermissionData(pageComponent = "xdgl/grkhpjsx/GrkhpjsxList")
//    public Result<?> batchQueryQxfk(Grkhpjsx grkhpjsx,
//                                   HttpServletRequest req) {
//        QueryWrapper<Grkhpjsx> queryWrapper = QueryGenerator.initQueryWrapper(grkhpjsx, req.getParameterMap());
//        queryWrapper.orderByDesc("create_time");
//        List<Grkhpjsx> list = service.list(queryWrapper);
//        if (CollUtil.isNotEmpty(list)){
//            log.info("==={}===",list.size());
//        }
//        return Result.ok();
//    }


    @GetMapping(value = "/hnkd")
    public Result<?> hnkd(String spid) {
        if (StringUtils.isBlank(spid))
            return Result.error("未查询到相关时间");
        CamsZcsxGrpjsxxxSpjl g = iCamsZcsxGrpjsxxxSpjlService.getById(spid);
        SlSHnkdVO slSHnkdVO = new SlSHnkdVO();
        if (g != null) {
            //获取基点
            String jd = "267.5";
            List<DictModel> dictModels = sysDictService.queryDictItemsByCode(DictConstant.ELOAN_JD);
            if (CollUtil.isNotEmpty(dictModels)) {
                jd = dictModels.get(0).getValue();
            }

            int sxdqr = 36;
            List<DictModel> dictModels2 = sysDictService.queryDictItemsByCode(DictConstant.ELOAN_SXDQR);
            if (CollUtil.isNotEmpty(dictModels2)) {
                sxdqr = Integer.valueOf(dictModels2.get(0).getValue());
            }

            String zrbl1 = "10";
            String zrbl2 = "10";
            String zrbl3 = "10";
            String zrbl4 = "10";
            String zrbl5 = "10";
            String zrbl6 = "10";

            List<DictModel> dictModels3 = sysDictService.queryDictItemsByCode(DictConstant.ELOAN_ZRBL);
            if (CollUtil.isNotEmpty(dictModels3)) {
                for (int i = 0; i < dictModels3.size(); i++) {
                    if ("1".equals(dictModels3.get(i).getText()))
                        zrbl1 = dictModels3.get(i).getValue();
                    else if ("2".equals(dictModels3.get(i).getText()))
                        zrbl2 = dictModels3.get(i).getValue();
                    else if ("3".equals(dictModels3.get(i).getText()))
                        zrbl3 = dictModels3.get(i).getValue();
                    else if ("4".equals(dictModels3.get(i).getText()))
                        zrbl4 = dictModels3.get(i).getValue();
                    else if ("5".equals(dictModels3.get(i).getText()))
                        zrbl5 = dictModels3.get(i).getValue();
                    else if ("6".equals(dictModels3.get(i).getText()))
                        zrbl6 = dictModels3.get(i).getValue();
                }
            }

            //根据所属支行获取客户经理和风险经理
            if (StringUtils.isNotBlank(g.getSszh())){
                List<SysUser> fxjl = sysUserService.findUserByRoleId("fxjl", g.getSszh());
            }

            //获取所有用户
            List<SysUser> sysUsers = sysUserService.list();
            List<SysUser> fxjl = sysUserService.findUsersByRoleId("fxjl");


            if (StringUtils.isNotBlank(g.getKhmc()))
                slSHnkdVO.setCustName(g.getKhmc());
            if (StringUtils.isNotBlank(g.getZjhm())) {
                slSHnkdVO.setCertNo(g.getZjhm());
                int age = 0;
                String zjhm = g.getZjhm();
                if (IdcardUtil.isValidCard(zjhm)) {
                    age = IdcardUtil.getAgeByIdCard(zjhm);
                }
                if (age > 0 && age <= 30) {
                    slSHnkdVO.setHighEdctDgrCd("30");
                } else {
                    int i1 = RandomUtil.randomInt(0, 3);
                    if (i1 == 0) {
                        slSHnkdVO.setHighEdctDgrCd("60");
                    } else if (i1 == 1) {
                        slSHnkdVO.setHighEdctDgrCd("70");
                    } else {
                        slSHnkdVO.setHighEdctDgrCd("80");
                    }
                }
            }
            if (g.getZzsxed() != null) {
                slSHnkdVO.setCreditValue(g.getZzsxed().multiply(new BigDecimal("10000")) + "");
                int i1 = RandomUtil.randomInt(0, 6);
                if (i1 == 0) {
                    slSHnkdVO.setIdyClTp("A0111");
                } else if (i1 == 2) {
                    slSHnkdVO.setIdyClTp("A0143");
                } else if (i1 == 3) {
                    slSHnkdVO.setIdyClTp("A0159");
                } else if (i1 == 4) {
                    slSHnkdVO.setIdyClTp("A0122");
                } else if (i1 == 5) {
                    slSHnkdVO.setIdyClTp("A0313");
                } else {
                    slSHnkdVO.setIdyClTp("A0412");
                }


            }
            slSHnkdVO.setAppRate(jd);

            slSHnkdVO.setAppTerm(sxdqr + "");

            List<EcifPerson> byZjhms = ecifPersonMapper.getByZjhm(g.getZjhm());
            EcifPerson byZjhm = null;
            if (CollUtil.isNotEmpty(byZjhms)) {
                byZjhm = byZjhms.get(byZjhms.size() - 1);
            }

            if (byZjhm != null) {
                //民族
                if (StringUtils.isNoneBlank(byZjhm.getNationality())) {
                    slSHnkdVO.setNationality(ELoandDictUtil.getMz(byZjhm.getNationality()));
                }

            }

            if (StringUtils.isNoneBlank(g.getYjyxdybh())) {
                String s = iDictValueQuery.getyjValue(g.getYjyxdybh());
                if (StringUtils.isNoneBlank(s)) {
                    slSHnkdVO.setTwnNm(ELoandDictUtil.getXz(s));
                }
            }

            //有无子女
            QueryWrapper jt = new QueryWrapper();
            jt.eq("hhbm", g.getHhbm());
            jt.orderByDesc("yhzgx");
            List<Khhmcxx> list3 = khhmcxxMapper.selectList(jt);

            if (CollUtil.isNotEmpty(list3)) {
                for (int j = 0; j < list3.size(); j++) {
                    if ("3".equals(list3.get(j).getYhzgx()) || "4".equals(list3.get(j).getYhzgx())) {
                        slSHnkdVO.setChlInd("1");
                    }

                    if ("2".equals(list3.get(j).getYhzgx())) {
                        slSHnkdVO.setAuthorizeCustName(list3.get(j).getKhmc());
                        slSHnkdVO.setAuthorizeCertType(list3.get(j).getZjhm());
                        slSHnkdVO.setFamNum("2");
                    }

                }

            }

            if (byZjhm != null && byZjhm.getHealth() != null) {
                slSHnkdVO.setHltSt(byZjhm.getHealth());
            }

            if (StringUtils.isNoneBlank(g.getHyzk())) {
                slSHnkdVO.setMrrgSt(hyzk(g.getHyzk()));
            }

            if (byZjhm != null && byZjhm.getResidence() != null) {
                slSHnkdVO.setRsdnSt(byZjhm.getResidence());
            }

            if (byZjhm != null && byZjhm.getHighestSchooling() != null) {
                slSHnkdVO.setHighEdctDgrCd(byZjhm.getHighestSchooling());
            }

            if (byZjhm != null && byZjhm.getHighestDegree() != null) {
                slSHnkdVO.setHighDgrCd(byZjhm.getHighestDegree());
            }

            if (StringUtils.isNotBlank(g.getZz())) {
                slSHnkdVO.setComAdr(g.getZz());
                slSHnkdVO.setCtcAdr(g.getZz());
                slSHnkdVO.setHouseAdr(g.getZz());
            }


            if (StringUtils.isNoneBlank(g.getSjhm())) {
                slSHnkdVO.setMblNo(g.getSjhm());
                slSHnkdVO.setMsgNtcInd("1");
            }

            if (StringUtils.isNotBlank(g.getYhzgx()))
                slSHnkdVO.setHsHldrInd(g.getYhzgx().equals("1") ? "1" : "2");

            if (byZjhm != null && byZjhm.getUnitName() != null) {
                slSHnkdVO.setWrkUnitNm(byZjhm.getUnitName());
            }

            if (byZjhm != null && byZjhm.getDuty() != null) {
                slSHnkdVO.setDutyCd(byZjhm.getDuty());
            }

            if (StringUtils.isNoneBlank(g.getCszy())) {
                slSHnkdVO.setOcpCd(CszySls(g.getCszy()));
            }

            if (byZjhm != null && byZjhm.getCareerTitle() != null) {
                slSHnkdVO.setProfTtlCd(zc(byZjhm.getCareerTitle()));
            }

            //todo 家庭年收入 个人年收入

        }
        return Result.ok();

    }


    public String CszySls(String s) {
        if (StringUtils.isNotBlank(s)) {
            if (s.startsWith("1"))
                return "0";
            if (s.startsWith("2"))
                return "1";
            if (s.startsWith("3"))
                return "3";
            if (s.startsWith("4"))
                return "4";
            if (s.startsWith("5"))
                return "5";
            if (s.startsWith("6"))
                return "6";
            if (s.startsWith("7"))
                return "x";
            if (s.startsWith("8"))
                return "y";
            return "z";
        }
        return "z";
    }
}
