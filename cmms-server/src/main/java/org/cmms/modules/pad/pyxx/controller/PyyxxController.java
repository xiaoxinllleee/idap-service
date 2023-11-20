package org.cmms.modules.pad.pyxx.controller;

import java.math.BigDecimal;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.SecurityUtils;
import org.checkerframework.checker.units.qual.K;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.base.entity.User;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.hr.yggl.ygxxgl.service.IVhrbasstaffpostService;
import org.cmms.modules.khgl.nh.entity.Fjgl;
import org.cmms.modules.khgl.nh.entity.Nhbkbpy;
import org.cmms.modules.khgl.nh.entity.NhbkbpyFictitious;
import org.cmms.modules.khgl.nh.service.IFjglService;
import org.cmms.modules.khgl.nh.service.INhbkbpyService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.KhxxglHnkd;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IKhxxglHnkdService;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.pad.nhczfp.entity.Nhczfp;
import org.cmms.modules.pad.nhczfp.entity.VKhglCzfp;
import org.cmms.modules.pad.nhczfp.service.INhczfpService;
import org.cmms.modules.pad.nhczfp.service.IVKhglCzfpService;
import org.cmms.modules.pad.nhxxgl.entity.*;
import org.cmms.modules.pad.nhxxgl.service.IKhglKhhmcxxPadService;
import org.cmms.modules.pad.nhxxgl.service.IKhglNhhzxxglService;
import org.cmms.modules.pad.nhxxgl.service.IKhglNhhzzllbService;
import org.cmms.modules.pad.nhxxgl.service.IKhglYwhywwlxxPadService;
import org.cmms.modules.pad.nhzhsd.entity.Nhzhsd;
import org.cmms.modules.pad.nhzhsd.service.INhzhsdService;
import org.cmms.modules.pad.pyxx.entity.Nhbkbpyfsxx;
import org.cmms.modules.pad.pyxx.entity.Nhplpy;
import org.cmms.modules.pad.pyxx.entity.Pyfjxx;
import org.cmms.modules.pad.pyxx.entity.Pyyxx;
import org.cmms.modules.pad.pyxx.service.INhbkbpyfsxxService;
import org.cmms.modules.pad.pyxx.service.INhplpyService;
import org.cmms.modules.pad.pyxx.service.IPyyxxService;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.pad.pyxx.service.IVKhglNhplpyService;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.ISysUserService;
import org.cmms.modules.tjfx.jcsjgl.cssz.service.ITjfxCsszService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 评议员信息
 * @Author: jeecg-boot
 * @Date: 2020-07-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "评议员信息")
@RestController
@RequestMapping("/pad/pyxx/pyyxx")
public class PyyxxController extends JeecgController<Pyyxx, IPyyxxService> {
    @Autowired
    private IPyyxxService pyyxxService;
    @Autowired
    private IVhrbasstaffpostService vhrbasstaffpostService;

    @Autowired
    private ISysUserService iSysUserService;
    @Autowired
    private INhbkbpyService nhbkbpyService;

    @Autowired
    private IKhglNhhzxxglService khglNhhzxxglService;

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private INhbkbpyfsxxService nhbkbpyfsxxService;
    @Autowired
    private IPyyxxService iPyyxxService;

    @Autowired
    private INhxqService nhxqService;
    @Autowired
    private INhplpyService nhplpyService;
    @Autowired
    private IKhxxglHnkdService khxxglHnkdService;
    @Autowired
    private IKhglYwhywwlxxPadService khglYwhywwlxxPadService;
    @Autowired
    private IHrBasStaffService hrBasStaffService;
    @Autowired
    private IVKhglCzfpService ivKhglCzfpService;
    @Autowired
    private ITjfxCsszService tjfxCsszService;
    @Autowired
    private INhczfpService nhczfpService;
    @Autowired
    private INhzhsdService nhzhsdService;
    /**
     * 分页列表查询
     *
     * @param pyyxx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "评议员信息-分页列表查询")
    @ApiOperation(value = "评议员信息-分页列表查询", notes = "评议员信息-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Pyyxx pyyxx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Pyyxx> queryWrapper = QueryGenerator.initQueryWrapper(pyyxx, req.getParameterMap());
        Page<Pyyxx> page = new Page<Pyyxx>(pageNo, pageSize);
        IPage<Pyyxx> pageList = pyyxxService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param pyyxx
     * @return
     */
    @AutoLog(value = "评议员信息-添加")
    @ApiOperation(value = "评议员信息-添加", notes = "评议员信息-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Pyyxx pyyxx) {
        pyyxx.setQydm(getRedisQydm());
        pyyxxService.save(pyyxx);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param pyyxx
     * @return
     */
    @AutoLog(value = "评议员信息-编辑")
    @ApiOperation(value = "评议员信息-编辑", notes = "评议员信息-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Pyyxx pyyxx) {
        pyyxxService.updateById(pyyxx);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "评议员信息-通过id删除")
    @ApiOperation(value = "评议员信息-通过id删除", notes = "评议员信息-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        pyyxxService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "评议员信息-批量删除")
    @ApiOperation(value = "评议员信息-批量删除", notes = "评议员信息-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.pyyxxService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "评议员信息-通过id查询")
    @ApiOperation(value = "评议员信息-通过id查询", notes = "评议员信息-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Pyyxx pyyxx = pyyxxService.getById(id);
        return Result.ok(pyyxx);
    }


    /**
     * 导出excel
     *
     * @param request
     * @param pyyxx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Pyyxx pyyxx) {
        return super.exportXls(request, pyyxx, Pyyxx.class, "评议员信息");
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
        return super.importExcel(request, response, Pyyxx.class);
    }


    @RequestMapping(value = "/savePyxx", method = RequestMethod.POST)
    public Result<?> savePyxx(NhbkbpyFictitious nhbkbbkbpy) {
        System.out.println("===========>");
        System.out.println(nhbkbbkbpy);
        //背靠背评议
        try {
            //判断是否批量评议
            if (StringUtils.isEmpty(nhbkbbkbpy.getPlpy())) {
                if (!StringUtils.isEmpty(nhbkbbkbpy.getPyyid())) {
                    //ID不为空，则为选择已有的评议员信息
                    //根据评议员ID查询评议员信息
                    QueryWrapper<Pyyxx> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("pyyid", nhbkbbkbpy.getPyyid());
                    Pyyxx pyyxx = iPyyxxService.getOne(queryWrapper);
                    nhbkbbkbpy.setPyyxm(pyyxx.getPyyxm());
                    nhbkbbkbpy.setPyyzjhm(pyyxx.getPyyzjhm());
                } else {
                    //如果ID为空，判断是否需要增加记录
                    QueryWrapper<Pyyxx> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("pyyzjhm", nhbkbbkbpy.getPyyzjhm());
                    queryWrapper.eq("qydm", nhbkbbkbpy.getQydm());
                    Pyyxx pyyxx = iPyyxxService.getOne(queryWrapper);
                    if (pyyxx == null) {
                        Pyyxx pyyxxAdd = new Pyyxx();
                        pyyxxAdd.setQydm(nhbkbbkbpy.getQydm());
                        pyyxxAdd.setPyyxm(nhbkbbkbpy.getPyyxm());
                        pyyxxAdd.setPyyzjhm(nhbkbbkbpy.getPyyzjhm());
                        pyyxxAdd.setPyyid(IdUtil.simpleUUID());
                        pyyxxAdd.setLrbz(1);
                        pyyxxAdd.setLrr(getUsername());
                        pyyxxAdd.setLrsj(new Date());
                        iPyyxxService.save(pyyxxAdd);
                    }
                }
            } else {
                //如果是批量评议
                if (StringUtils.isNotEmpty(nhbkbbkbpy.getPyyKhid())) {
                    //选择的客户，从客户信息中获取
                    Nhxq nhxq = nhxqService.getById(nhbkbbkbpy.getPyyKhid());
                    nhbkbbkbpy.setPyyzjhm(nhxq.getZjhm());
                } else if (!StringUtils.isEmpty(nhbkbbkbpy.getPyyid())) {
                    // 从批量评议信息表中获取
                    //选择的评议员，从表中获取，否则直接取页面录入的信息
                    Nhplpy nhplpy = nhplpyService.getById(nhbkbbkbpy.getPyyid());
                    nhbkbbkbpy.setPyyxm(nhplpy.getPyyxm());
                    nhbkbbkbpy.setPyyzjhm(nhplpy.getPyyzjhm());
                }
            }
            //通过id查询授信对象证件号码
            QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", StringUtils.isEmpty(nhbkbbkbpy.getZjhm()) ? nhbkbbkbpy.getId() : nhbkbbkbpy.getZjhm());
            Nhxq khhmcxx = nhxqService.getOne(queryWrapper);
            if (StringUtils.isNotEmpty(nhbkbbkbpy.getSjhm())) {
                Nhxq nhxq = new Nhxq();
                nhxq.setSjhm(nhbkbbkbpy.getSjhm());
                nhxqService.update(nhxq, queryWrapper);
            }
            //查询是否已经存在数据  一个评议员只能评议一次
            QueryWrapper<Nhbkbpy> nhbkbpyQueryWrapper = new QueryWrapper<>();
            nhbkbpyQueryWrapper.eq("zjhm", khhmcxx.getZjhm());
            nhbkbpyQueryWrapper.eq("pyyzjhm", nhbkbbkbpy.getPyyzjhm());
            Nhbkbpy nhbkbpyExist = nhbkbpyService.getOne(nhbkbpyQueryWrapper);
            if (nhbkbpyExist != null) {
                return Result.error("此客户已经存在当前评议员的评议记录，添加失败！");
            }

            String id = UUIDGenerator.generate();
            Nhbkbpy bkbpy = new Nhbkbpy();
            BeanUtils.copyProperties(nhbkbbkbpy, bkbpy);
            //        Nhxq khglKhhmcxx = nhxqService.getOne(hmcxxQueryWrapper);
            SysUser user = sysUserService.getUserByName(getUsername());
            bkbpy.setId(id);
            bkbpy.setZjhm(khhmcxx.getZjhm());
            bkbpy.setLrr(user.getUsername());
            bkbpy.setLrsj(new Date());
            //评议轮数
//			 LambdaQueryWrapper<Nhbkbpy> lambdaQueryWrapper = new LambdaQueryWrapper();
//			 lambdaQueryWrapper.eq(Nhbkbpy::getZjhm,khhmcxx.getZjhm());
//			 Integer integer = nhbkbpyService.getBaseMapper().selectCount(lambdaQueryWrapper);
//			 integer++;
//			 bkbpy.setPyls(integer);

            nhbkbpyService.save(bkbpy);
            Nhbkbpyfsxx nhbkbpyfsxx = new Nhbkbpyfsxx();
            nhbkbpyfsxx.setId(id);
            nhbkbpyfsxx.setQydm(nhbkbbkbpy.getQydm());
            nhbkbpyfsxx.setZjhm(khhmcxx.getZjhm());
            nhbkbpyfsxx.setKhmc(nhbkbbkbpy.getKhmc());
            nhbkbpyfsxx.setHhbm(nhbkbbkbpy.getHhbm());
            nhbkbpyfsxx.setPyyxm(nhbkbbkbpy.getPyyxm());
            nhbkbpyfsxx.setPyyzjhm(nhbkbbkbpy.getPyyzjhm());
            nhbkbpyfsxx.setPysj(nhbkbbkbpy.getPysj());
            if (nhbkbbkbpy.getFwjzqk() != null) {
                nhbkbpyfsxx.setFcjz(BigDecimal.valueOf(nhbkbpyfsxxService.fcjz(nhbkbbkbpy.getFwjzqk())));
            }
            if (nhbkbbkbpy.getDznyxfpqk() != null) {
                nhbkbpyfsxx.setDznyxfpqk(BigDecimal.valueOf(nhbkbpyfsxxService.dznyxfpqk(nhbkbbkbpy.getDznyxfpqk())));
            }
            if (nhbkbbkbpy.getJtcsrqk() != null) {
                nhbkbpyfsxx.setJtcsrqk(BigDecimal.valueOf(nhbkbpyfsxxService.jtcsrqk(nhbkbbkbpy.getJtcsrqk())));
            }
            if (nhbkbbkbpy.getNjjqk() != null) {
                nhbkbpyfsxx.setNjjqk(BigDecimal.valueOf(nhbkbpyfsxxService.njjqk(nhbkbbkbpy.getNjjqk())));
            }
            if (nhbkbbkbpy.getJtysgjqk() != null) {
                nhbkbpyfsxx.setJtysgjqk(BigDecimal.valueOf(nhbkbpyfsxxService.jtysgjqk(nhbkbbkbpy.getJtysgjqk())));
            }

            nhbkbpyfsxxService.save(nhbkbpyfsxx);
            return Result.ok("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加评议信息失败", e);
            return Result.error("系统错误，添加失败！");
        }
    }

    /**
     * Pad端-整村授信-背靠背评议-保存并评议下一个本户成员
     * 天易
     * @param nhbkbbkbpy
     * @return
     */
    @AutoLog(value = "Pad端-整村授信-背靠背评议（天易）-保存并评议下一个本户成员")
    @ApiOperation(value = "Pad端-整村授信-背靠背评议（天易）-保存并评议下一个本户成员", notes = "Pad端-整村授信-背靠背评议（天易）-保存并评议下一个本户成员")
    @RequestMapping(value = "/saveTYPyxx", method = RequestMethod.POST)
    public Result<?> saveTYPyxx(NhbkbpyFictitious nhbkbbkbpy) {
        System.out.println("===========>");
        System.out.println(nhbkbbkbpy);
        //背靠背评议
        try {
            //判断是否批量评议
            if (StringUtils.isEmpty(nhbkbbkbpy.getPlpy())) {
                if (!StringUtils.isEmpty(nhbkbbkbpy.getPyyid())) {
                    //ID不为空，则为选择已有的评议员信息
                    //根据评议员ID查询评议员信息
                    QueryWrapper<Pyyxx> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("pyyid", nhbkbbkbpy.getPyyid());
                    Pyyxx pyyxx = iPyyxxService.getOne(queryWrapper);
                    nhbkbbkbpy.setPyyxm(pyyxx.getPyyxm());
                    nhbkbbkbpy.setPyyzjhm(pyyxx.getPyyzjhm());
                } else {
                    //如果ID为空，判断是否需要增加记录
                    QueryWrapper<Pyyxx> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("pyyzjhm", nhbkbbkbpy.getPyyzjhm());
                    queryWrapper.eq("qydm", nhbkbbkbpy.getQydm());
                    Pyyxx pyyxx = iPyyxxService.getOne(queryWrapper);
                    if (pyyxx == null) {
                        Pyyxx pyyxxAdd = new Pyyxx();
                        pyyxxAdd.setQydm(nhbkbbkbpy.getQydm());
                        pyyxxAdd.setPyyxm(nhbkbbkbpy.getPyyxm());
                        pyyxxAdd.setPyyzjhm(nhbkbbkbpy.getPyyzjhm());
                        pyyxxAdd.setPyyid(IdUtil.simpleUUID());
                        pyyxxAdd.setLrbz(1);
                        pyyxxAdd.setLrr(getUsername());
                        pyyxxAdd.setLrsj(new Date());
                        iPyyxxService.save(pyyxxAdd);
                    }
                }
            } else {
                //如果是批量评议
                if (StringUtils.isNotEmpty(nhbkbbkbpy.getPyyKhid())) {
                    //选择的客户，从客户信息中获取
                    Nhxq nhxq = nhxqService.getById(nhbkbbkbpy.getPyyKhid());
                    nhbkbbkbpy.setPyyzjhm(nhxq.getZjhm());
                } else if (!StringUtils.isEmpty(nhbkbbkbpy.getPyyid())) {
                    // 从批量评议信息表中获取
                    //选择的评议员，从表中获取，否则直接取页面录入的信息
                    Nhplpy nhplpy = nhplpyService.getById(nhbkbbkbpy.getPyyid());
                    nhbkbbkbpy.setPyyxm(nhplpy.getPyyxm());
                    nhbkbbkbpy.setPyyzjhm(nhplpy.getPyyzjhm());
                }
            }
            //通过id查询授信对象证件号码
            QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", StringUtils.isEmpty(nhbkbbkbpy.getZjhm()) ? nhbkbbkbpy.getId() : nhbkbbkbpy.getZjhm());
            Nhxq khhmcxx = nhxqService.getOne(queryWrapper);
            if (StringUtils.isNotEmpty(nhbkbbkbpy.getSjhm())) {
                Nhxq nhxq = new Nhxq();
                nhxq.setSjhm(nhbkbbkbpy.getSjhm());
                nhxqService.update(nhxq, queryWrapper);
            }
            //查询是否已经存在数据  一个评议员只能评议一次
            QueryWrapper<Nhbkbpy> nhbkbpyQueryWrapper = new QueryWrapper<>();
            nhbkbpyQueryWrapper.eq("zjhm", khhmcxx.getZjhm());
            nhbkbpyQueryWrapper.eq("pyyzjhm", nhbkbbkbpy.getPyyzjhm());
            Nhbkbpy nhbkbpyExist = nhbkbpyService.getOne(nhbkbpyQueryWrapper);
            if (nhbkbpyExist != null) {
                return Result.error("此客户已经存在当前评议员的评议记录，添加失败！");
            }

            //更新户主表信息
            KhglNhhzxxgl khglNhhzxxgl = new KhglNhhzxxgl();
            khglNhhzxxgl.setSxdxzjh(khhmcxx.getZjhm());
            khglNhhzxxgl.setSxdx(khhmcxx.getKhmc());
            UpdateWrapper<KhglNhhzxxgl> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", nhbkbbkbpy.getHzid());
            khglNhhzxxglService.update(khglNhhzxxgl, updateWrapper);

            String id = UUIDGenerator.generate();
            Nhbkbpy bkbpy = new Nhbkbpy();
            BeanUtils.copyProperties(nhbkbbkbpy, bkbpy);
            //        Nhxq khglKhhmcxx = nhxqService.getOne(hmcxxQueryWrapper);
            SysUser user = sysUserService.getUserByName(getUsername());
            bkbpy.setId(id);
            bkbpy.setZjhm(khhmcxx.getZjhm());
            bkbpy.setLrr(user.getUsername());
            bkbpy.setLrsj(new Date());

            // 基础模型测算-限制额度100,000
            BigDecimal xzed = new BigDecimal("100000");
            // 未婚
            if (!"1".equals(bkbpy.getHyzk()) && bkbpy.getJysxed().compareTo(new BigDecimal("100000")) > 0) {
                BigDecimal[] bigDecimals = bkbpy.getJysxed().multiply(new BigDecimal("0.7")).divideAndRemainder(new BigDecimal("10000"));
                BigDecimal jcmxcs = bigDecimals[0].multiply(new BigDecimal("10000"));
                if (xzed.compareTo(jcmxcs) < 0) {
                    bkbpy.setJcmxcs(xzed);
                } else {
                    bkbpy.setJcmxcs(jcmxcs);
                }
            }
            // 已婚
            if ("1".equals(bkbpy.getHyzk()) && StringUtils.isNotEmpty(bkbpy.getPozjhm()) && "1".equals(nhbkbbkbpy.getKhlx())) {
                if (xzed.compareTo(bkbpy.getJcmxcs()) <= 0) {
                    bkbpy.setJcmxcs(xzed);
                } else {
                    List<String> zjhmList = Stream.of(bkbpy.getZjhm(), bkbpy.getPozjhm()).collect(Collectors.toList());
                    QueryWrapper<KhglYwhywwlxxPad> queryWrapper1 = new QueryWrapper<>();
                    queryWrapper1.in("zjhm", zjhmList);
                    queryWrapper1.isNotNull("ckye");
                    BigDecimal ckyezj = khglYwhywwlxxPadService.list(queryWrapper1).stream().map(KhglYwhywwlxxPad::getCkye).reduce(BigDecimal.ZERO, BigDecimal::add);
                    BigDecimal[] bigDecimals = ckyezj.divide(new BigDecimal("2")).divideAndRemainder(new BigDecimal("10000"));
                    BigDecimal jcmxcs = bkbpy.getJcmxcs().add(bigDecimals[0].multiply(new BigDecimal("10000")));
                    if (xzed.compareTo(jcmxcs) <= 0) {
                        bkbpy.setJcmxcs(xzed);
                    } else {
                        bkbpy.setJcmxcs(jcmxcs);
                    }
                }
            }
            bkbpy.setJysxed(bkbpy.getJcmxcs());

            nhbkbpyService.save(bkbpy);
            Nhbkbpyfsxx nhbkbpyfsxx = new Nhbkbpyfsxx();
            nhbkbpyfsxx.setId(id);
            nhbkbpyfsxx.setQydm(nhbkbbkbpy.getQydm());
            nhbkbpyfsxx.setZjhm(khhmcxx.getZjhm());
            nhbkbpyfsxx.setKhmc(nhbkbbkbpy.getKhmc());
            nhbkbpyfsxx.setHhbm(nhbkbbkbpy.getHhbm());
            nhbkbpyfsxx.setPyyxm(nhbkbbkbpy.getPyyxm());
            nhbkbpyfsxx.setPyyzjhm(nhbkbbkbpy.getPyyzjhm());
            nhbkbpyfsxx.setPysj(nhbkbbkbpy.getPysj());
            if (nhbkbbkbpy.getFwjzqk() != null) {
                nhbkbpyfsxx.setFcjz(BigDecimal.valueOf(nhbkbpyfsxxService.fcjz(nhbkbbkbpy.getFwjzqk())));
            }
            if (nhbkbbkbpy.getDznyxfpqk() != null) {
                nhbkbpyfsxx.setDznyxfpqk(BigDecimal.valueOf(nhbkbpyfsxxService.dznyxfpqk(nhbkbbkbpy.getDznyxfpqk())));
            }
            if (nhbkbbkbpy.getJtcsrqk() != null) {
                nhbkbpyfsxx.setJtcsrqk(BigDecimal.valueOf(nhbkbpyfsxxService.jtcsrqk(nhbkbbkbpy.getJtcsrqk())));
            }
            if (nhbkbbkbpy.getNjjqk() != null) {
                nhbkbpyfsxx.setNjjqk(BigDecimal.valueOf(nhbkbpyfsxxService.njjqk(nhbkbbkbpy.getNjjqk())));
            }
            if (nhbkbbkbpy.getJtysgjqk() != null) {
                nhbkbpyfsxx.setJtysgjqk(BigDecimal.valueOf(nhbkbpyfsxxService.jtysgjqk(nhbkbbkbpy.getJtysgjqk())));
            }

            nhbkbpyfsxxService.save(nhbkbpyfsxx);
            return Result.ok("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加评议信息失败", e);
            return Result.error("系统错误，添加失败！");
        }
    }

    //祁阳地区特殊处理
    @RequestMapping(value = "/saveQyPyxx", method = RequestMethod.POST)
    public Result<?> saveQyPyxx(NhbkbpyFictitious nhbkbbkbpy) {
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (!StringUtils.isEmpty(nhbkbbkbpy.getPyyid())) {
                //ID不为空，则为选择已有的评议员信息
                //根据评议员ID查询评议员信息
                QueryWrapper<Pyyxx> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("pyyid", nhbkbbkbpy.getPyyid());
                Pyyxx pyyxx = iPyyxxService.getOne(queryWrapper);
                nhbkbbkbpy.setPyyxm(pyyxx.getPyyxm());
                nhbkbbkbpy.setPyyzjhm(pyyxx.getPyyzjhm());
            } else {
                //如果ID为空，判断是否需要增加记录
                QueryWrapper<Pyyxx> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("pyyzjhm", nhbkbbkbpy.getPyyzjhm());
                queryWrapper.eq("qydm", nhbkbbkbpy.getQydm());
                Pyyxx pyyxx = iPyyxxService.getOne(queryWrapper);
                if (pyyxx == null) {
                    Pyyxx pyyxxAdd = new Pyyxx();
                    pyyxxAdd.setQydm(nhbkbbkbpy.getQydm());
                    pyyxxAdd.setPyyxm(nhbkbbkbpy.getPyyxm());
                    pyyxxAdd.setPyyzjhm(nhbkbbkbpy.getPyyzjhm());
                    pyyxxAdd.setPyyid(IdUtil.simpleUUID());
                    pyyxxAdd.setLrbz(1);
                    pyyxxAdd.setLrr(loginUser.getUsername());
                    pyyxxAdd.setLrsj(new Date());
                    iPyyxxService.save(pyyxxAdd);
                }
            }
            String id = UUIDGenerator.generate();
            //通过id查询授信对象证件号码
            QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", nhbkbbkbpy.getZjhm());
            Nhxq khhmcxx = nhxqService.getOne(queryWrapper);
            //保存背靠背信息
            Nhbkbpy bkbpy = new Nhbkbpy();
            BeanUtils.copyProperties(nhbkbbkbpy, bkbpy);
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            SysUser user = sysUserService.getUserByName(sysUser.getUsername());
            bkbpy.setId(id);
            bkbpy.setKhmc(khhmcxx.getKhmc());
            bkbpy.setZjhm(khhmcxx.getZjhm());
            bkbpy.setLrr(user.getUsername());
            bkbpy.setLrsj(new Date());
            nhbkbpyService.save(bkbpy);

            //更新户主表授信对象证件号
            KhglNhhzxxgl khglNhhzxxgl = new KhglNhhzxxgl();
            khglNhhzxxgl.setSxdxzjh(khhmcxx.getZjhm());
            khglNhhzxxgl.setSxdx(khhmcxx.getKhmc());
            UpdateWrapper<KhglNhhzxxgl> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", nhbkbbkbpy.getHzid());
            khglNhhzxxglService.update(khglNhhzxxgl, updateWrapper);

            //保存附属信息表数据
            Nhbkbpyfsxx nhbkbpyfsxx = new Nhbkbpyfsxx();
            //BeanUtils.copyProperties(nhbkbbkbpy,nhbkbpyfsxx);
            nhbkbpyfsxx.setId(id);
            nhbkbpyfsxx.setQydm(nhbkbbkbpy.getQydm());
            nhbkbpyfsxx.setZjhm(khhmcxx.getZjhm());
            nhbkbpyfsxx.setKhmc(nhbkbbkbpy.getKhmc());
            nhbkbpyfsxx.setHhbm(nhbkbbkbpy.getHhbm());
            nhbkbpyfsxx.setPyyxm(nhbkbbkbpy.getPyyxm());
            nhbkbpyfsxx.setPyyzjhm(nhbkbbkbpy.getPyyzjhm());
            nhbkbpyfsxx.setPysj(nhbkbbkbpy.getPysj());
            if (nhbkbbkbpy.getFwjzqk() != null) {
                nhbkbpyfsxx.setFcjz(BigDecimal.valueOf(nhbkbpyfsxxService.fcjz(nhbkbbkbpy.getFwjzqk())));
            }
            if (nhbkbbkbpy.getDznyxfpqk() != null) {
                nhbkbpyfsxx.setDznyxfpqk(BigDecimal.valueOf(nhbkbpyfsxxService.dznyxfpqk(nhbkbbkbpy.getDznyxfpqk())));
            }
            if (nhbkbbkbpy.getJtcsrqk() != null) {
                nhbkbpyfsxx.setJtcsrqk(BigDecimal.valueOf(nhbkbpyfsxxService.jtcsrqk(nhbkbbkbpy.getJtcsrqk())));
            }
            if (nhbkbbkbpy.getNjjqk() != null) {
                nhbkbpyfsxx.setNjjqk(BigDecimal.valueOf(nhbkbpyfsxxService.njjqk(nhbkbbkbpy.getNjjqk())));
            }
            if (nhbkbbkbpy.getJtysgjqk() != null) {
                nhbkbpyfsxx.setJtysgjqk(BigDecimal.valueOf(nhbkbpyfsxxService.jtysgjqk(nhbkbbkbpy.getJtysgjqk())));
            }

            nhbkbpyfsxxService.save(nhbkbpyfsxx);
            return Result.ok("添加成功");
        } catch (Exception e) {
            log.error("添加评议信息失败", e);
            return Result.error("系统错误，添加失败！");
        }
    }


    @RequestMapping(value = "/saveQyPyxx2", method = RequestMethod.POST)
    public Result<?> saveQyPyxx2(NhbkbpyFictitious nhbkbbkbpy) {
        try {
            if (!StringUtils.isEmpty(nhbkbbkbpy.getPyyid())) {
                Nhplpy byId = nhplpyService.getById(nhbkbbkbpy.getPyyid());
                nhbkbbkbpy.setPyyxm(byId.getPyyxm());
                nhbkbbkbpy.setPyyzjhm(byId.getPyyzjhm());
            }
            String id = UUIDGenerator.generate();
            //通过id查询授信对象证件号码
            QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", nhbkbbkbpy.getZjhm());
            Nhxq khhmcxx = nhxqService.getOne(queryWrapper);
            //保存背靠背信息
            Nhbkbpy bkbpy = new Nhbkbpy();
            BeanUtils.copyProperties(nhbkbbkbpy, bkbpy);
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            SysUser user = sysUserService.getUserByName(sysUser.getUsername());
            bkbpy.setId(id);
            bkbpy.setKhmc(khhmcxx.getKhmc());
            bkbpy.setZjhm(khhmcxx.getZjhm());
            bkbpy.setLrr(user.getUsername());
            bkbpy.setLrsj(new Date());
            bkbpy.setPysj(new Date());
            nhbkbpyService.save(bkbpy);

            //更新户主表授信对象证件号
            KhglNhhzxxgl khglNhhzxxgl = new KhglNhhzxxgl();
            khglNhhzxxgl.setSxdxzjh(khhmcxx.getZjhm());
            khglNhhzxxgl.setSxdx(khhmcxx.getKhmc());
            UpdateWrapper<KhglNhhzxxgl> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", nhbkbbkbpy.getHzid());
            khglNhhzxxglService.update(khglNhhzxxgl, updateWrapper);

            //保存附属信息表数据
            Nhbkbpyfsxx nhbkbpyfsxx = new Nhbkbpyfsxx();
            //BeanUtils.copyProperties(nhbkbbkbpy,nhbkbpyfsxx);
            nhbkbpyfsxx.setId(id);
            nhbkbpyfsxx.setQydm(nhbkbbkbpy.getQydm());
            nhbkbpyfsxx.setZjhm(khhmcxx.getZjhm());
            nhbkbpyfsxx.setKhmc(nhbkbbkbpy.getKhmc());
            nhbkbpyfsxx.setHhbm(nhbkbbkbpy.getHhbm());
            nhbkbpyfsxx.setPyyxm(nhbkbbkbpy.getPyyxm());
            nhbkbpyfsxx.setPyyzjhm(nhbkbbkbpy.getPyyzjhm());
            nhbkbpyfsxx.setPysj(nhbkbbkbpy.getPysj());
            if (nhbkbbkbpy.getFwjzqk() != null) {
                nhbkbpyfsxx.setFcjz(BigDecimal.valueOf(nhbkbpyfsxxService.fcjz(nhbkbbkbpy.getFwjzqk())));
            }
            if (nhbkbbkbpy.getDznyxfpqk() != null) {
                nhbkbpyfsxx.setDznyxfpqk(BigDecimal.valueOf(nhbkbpyfsxxService.dznyxfpqk(nhbkbbkbpy.getDznyxfpqk())));
            }
            if (nhbkbbkbpy.getJtcsrqk() != null) {
                nhbkbpyfsxx.setJtcsrqk(BigDecimal.valueOf(nhbkbpyfsxxService.jtcsrqk(nhbkbbkbpy.getJtcsrqk())));
            }
            if (nhbkbbkbpy.getNjjqk() != null) {
                nhbkbpyfsxx.setNjjqk(BigDecimal.valueOf(nhbkbpyfsxxService.njjqk(nhbkbbkbpy.getNjjqk())));
            }
            if (nhbkbbkbpy.getJtysgjqk() != null) {
                nhbkbpyfsxx.setJtysgjqk(BigDecimal.valueOf(nhbkbpyfsxxService.jtysgjqk(nhbkbbkbpy.getJtysgjqk())));
            }

            nhbkbpyfsxxService.save(nhbkbpyfsxx);
            return Result.ok("添加成功");
        } catch (Exception e) {
            log.error("添加评议信息失败", e);
            return Result.error("系统错误，添加失败！");
        }
    }


    //    @RequestMapping(value = "/updatePyxx", method = RequestMethod.PUT)
    @PostMapping(value = "/updatePyxx")
    public Result<?> updatePyxx(@RequestBody NhbkbpyFictitious nhbkbbkbpy) {
        try {
            Nhbkbpy bkbpy = new Nhbkbpy();
            BeanUtils.copyProperties(nhbkbbkbpy, bkbpy);
            QueryWrapper<Nhbkbpy> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", bkbpy.getId());
            Nhbkbpy nhbkbpy = nhbkbpyService.getOne(queryWrapper);
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            SysUser user = sysUserService.getUserByName(sysUser.getUsername());
            bkbpy.setLrr(user.getUsername());
            bkbpy.setLrsj(new Date());
            bkbpy.setLrbz("1");
            bkbpy.setPyyzjhm(nhbkbpy.getPyyzjhm());
            bkbpy.setZjhm(nhbkbpy.getZjhm());
//            QueryWrapper<Nhbkbpy> queryWrapper1 = new QueryWrapper<>();
//            queryWrapper1.eq("id", bkbpy.getId());
//            nhbkbpyService.update(bkbpy, queryWrapper1);
            nhbkbpyService.updateById(bkbpy);
            return Result.ok("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("编辑评议信息失败", e);
            return Result.error("系统错误，修改失败！");
        }
    }

    @RequestMapping(value = "/deletePyxx", method = RequestMethod.POST)
    public Result<?> deletePyxx(Nhbkbpy bkbpy) {
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

            QueryWrapper<Nhbkbpy> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", bkbpy.getId());
            Nhbkbpy one = nhbkbpyService.getOne(queryWrapper, false);


            if(getRedisQydm().equals(QydmEnums.QIYANG.getQydmCode())){
                String lrr = one.getLrr();
                Date lrsj = one.getLrsj();
                SysUser userByName = iSysUserService.getUserByName(lrr);
                Vhrbasstaffpost vhrbasstaffpost = vhrbasstaffpostService.selectYgByLrsj(userByName.getWorkNo(), DateUtil.getDateString(lrsj));
                log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$vhrbasstaffpost000000"+vhrbasstaffpost.getZzbz());
                Vhrbasstaffpost vhrbasstaffpost1 = vhrbasstaffpostService.selectByYggh(loginUser.getWorkNo());
                log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$vhrbasstaffpost111111"+vhrbasstaffpost.getZzbz());

                if(vhrbasstaffpost!=null){
                    if(!vhrbasstaffpost.getZzbz().equals(vhrbasstaffpost1.getZzbz())){
                        return Result.error(200,"该信息为["+vhrbasstaffpost.getZzjc()+"]编辑,禁止删除！");

                    }
                }
            }




            //更新户主表授信对象
            QueryWrapper<KhglNhhzxxgl> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("sxdxzjh", one.getZjhm());
            KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getOne(queryWrapper2);
            if (khglNhhzxxgl != null) {
                pyyxxService.updateSxxx(khglNhhzxxgl.getId());
                pyyxxService.updateSxxx(khglNhhzxxgl.getId());
            }
            nhbkbpyService.remove(queryWrapper);

            QueryWrapper<Nhbkbpyfsxx> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("id", bkbpy.getId());
            nhbkbpyfsxxService.remove(queryWrapper1);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除评议信息失败", e);
            return Result.error("删除评议信息失败！");
        }
        return Result.ok("删除成功");
    }

    /**
     * 根据户号查询已经评议的家庭成员
     *
     * @param hhbm 户号编码
     * @return
     */
    @AutoLog(value = "根据户号查询已经评议的家庭成员")
    @ApiOperation(value = "根据户号查询已经评议的家庭成员", notes = "根据户号查询已经评议的家庭成员")
    @GetMapping(value = "/queryYpyJtcyxxByHhbm")
    public Result<?> queryYpyJtcyxxByHhbm(@RequestParam(name = "hhbm", required = true) String hhbm,
                                          @RequestParam(name = "pyyid", required = false) String pyyid,
                                          @RequestParam(name = "pyyzjhm", required = false) String pyyzjhmStr,
                                          @RequestParam(name = "pyyKhid", required = false) String pyyKhid,
                                          @RequestParam(name = "pywg") String pywg) {

        QueryWrapper<Nhplpy> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(pyyid)) {
            wrapper.eq("id", pyyid).eq("pywg", pywg);
        } else if (StringUtils.isNotBlank(pyyKhid)) {
            Nhxq nhxq = nhxqService.getById(pyyKhid);
            wrapper.eq("pywg", pywg).eq("pyyzjhm", nhxq.getZjhm());
        } else {
            wrapper.eq("pywg", pywg).eq("pyyzjhm", pyyzjhmStr);
        }
        Nhplpy nhplpy = nhplpyService.getOne(wrapper);

        //一个评议员已评议的本户成员
        QueryWrapper<Nhbkbpy> nhplpyQueryWrapper = new QueryWrapper<>();
        nhplpyQueryWrapper.eq("hhbm", hhbm).eq("pyyzjhm", nhplpy == null ? pyyzjhmStr : nhplpy.getPyyzjhm());
        List<Nhbkbpy> nhbkbpyList = nhbkbpyService.list(nhplpyQueryWrapper);
        List<String> zjhmList = nhbkbpyList.stream().map(Nhbkbpy::getZjhm).collect(Collectors.toList());
        List<String> bysxList = nhbkbpyList.stream().filter(item -> StringUtils.isNotEmpty(item.getBysxqx())).map(Nhbkbpy::getZjhm).collect(Collectors.toList());

        //天意：一户最多只能评议2个成员,查询所有评议员已经评议过的家庭成员
        QueryWrapper<Nhbkbpy> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hhbm", hhbm);
        List<Nhbkbpy> nhbkbpyList1 = nhbkbpyService.list(queryWrapper);
        List<String> ypyZjhmList = nhbkbpyList1.stream().map(Nhbkbpy::getZjhm).distinct().collect(Collectors.toList());

        //一户成员
        QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
        nhxqQueryWrapper.eq("hhbm", hhbm).between("nl", 18, 60);
        List<Nhxq> nhxqList = nhxqService.list(nhxqQueryWrapper);

        //设置了评议对象的，进入页面时默认为评议对象
        List<Nhxq> mrList = null;
        if (CollUtil.isEmpty(zjhmList)) {
            QueryWrapper<KhglNhhzxxgl> khglNhhzxxglQueryWrapper = new QueryWrapper<>();
            khglNhhzxxglQueryWrapper.eq("hhbm", hhbm).isNotNull("sxdx").isNotNull("sxdxzjh");
            KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getOne(khglNhhzxxglQueryWrapper);
            if (khglNhhzxxgl != null) {
                nhxqQueryWrapper.eq("zjhm", khglNhhzxxgl.getSxdxzjh());
                mrList = nhxqService.list(nhxqQueryWrapper);
            }
        }
        Map<String, List> resultMap = new HashMap<>();
        if (ypyZjhmList.size() == 2) {
            nhxqList = nhxqList.stream().filter(item -> ypyZjhmList.contains(item.getZjhm())).collect(Collectors.toList());
        }
        resultMap.put("ywc", nhxqList.stream().filter(item -> zjhmList.contains(item.getZjhm()) && !bysxList.contains(item.getZjhm())).collect(Collectors.toList()));
        resultMap.put("wwc", nhxqList.stream().filter(item -> !zjhmList.contains(item.getZjhm())).collect(Collectors.toList()));
        resultMap.put("mr", mrList);
        return Result.ok(resultMap);
    }

    /**
     * 更新评议表和评议附属信息表信息
     *
     * @param
     * @return
     */
    @AutoLog(value = "更新评议表和评议附属信息表信息")
    @ApiOperation(value = "更新评议表和评议附属信息表信息", notes = "更新评议表和评议附属信息表信息")
    @PutMapping(value = "/updatePyxxByGr")
    public Result<?> updatePyxxByGr(@RequestBody NhbkbpyFictitious nhbkbbkbpy) {
        Nhbkbpy bkbpy = new Nhbkbpy();
        BeanUtils.copyProperties(nhbkbbkbpy, bkbpy);
        if (StringUtils.isEmpty(nhbkbbkbpy.getBysxqx())) {
            bkbpy.setBysxqx("");
        }
        bkbpy.setJysxed(bkbpy.getJcmxcs());
        nhbkbpyService.updateById(bkbpy);

        QueryWrapper<Nhbkbpyfsxx> nhbkbpyfsxxQueryWrapper = new QueryWrapper<>();
        nhbkbpyfsxxQueryWrapper.eq("hhbm", bkbpy.getHhbm()).eq("zjhm", bkbpy.getZjhm()).eq("pyyzjhm", bkbpy.getPyyzjhm());
        List<Nhbkbpyfsxx> nhbkbpyfsxxList = nhbkbpyfsxxService.list(nhbkbpyfsxxQueryWrapper);
        if (nhbkbpyfsxxList != null && !nhbkbpyfsxxList.isEmpty()) {
            Nhbkbpyfsxx nhbkbpyfsxx = nhbkbpyfsxxList.get(0);
            nhbkbpyfsxx.setFcjz(nhbkbbkbpy.getFwjzqk() == null ? nhbkbpyfsxx.getFcjz() : BigDecimal.valueOf(nhbkbpyfsxxService.fcjz(nhbkbbkbpy.getFwjzqk())));
            nhbkbpyfsxx.setDznyxfpqk(nhbkbbkbpy.getDznyxfpqk() == null ? nhbkbpyfsxx.getDznyxfpqk() : BigDecimal.valueOf(nhbkbpyfsxxService.dznyxfpqk(nhbkbbkbpy.getDznyxfpqk())));
            nhbkbpyfsxx.setJtcsrqk(nhbkbbkbpy.getJtcsrqk() == null ? nhbkbpyfsxx.getJtcsrqk() : BigDecimal.valueOf(nhbkbpyfsxxService.jtcsrqk(nhbkbbkbpy.getJtcsrqk())));
            nhbkbpyfsxx.setNjjqk(nhbkbbkbpy.getNjjqk() == null ? nhbkbpyfsxx.getNjjqk() : BigDecimal.valueOf(nhbkbpyfsxxService.njjqk(nhbkbbkbpy.getNjjqk())));
            nhbkbpyfsxx.setJtysgjqk(nhbkbbkbpy.getJtysgjqk() == null ? nhbkbpyfsxx.getJtysgjqk() : BigDecimal.valueOf(nhbkbpyfsxxService.jtysgjqk(nhbkbbkbpy.getJtysgjqk())));
            nhbkbpyfsxxService.updateById(nhbkbpyfsxx);
        }
        QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zjhm", nhbkbbkbpy.getZjhm());
        Nhxq khhmcxx = nhxqService.getOne(queryWrapper);
        if (StringUtils.isNotEmpty(nhbkbbkbpy.getSjhm())) {
            Nhxq nhxq = new Nhxq();
            nhxq.setSjhm(nhbkbbkbpy.getSjhm());
            nhxqService.update(nhxq, queryWrapper);
        }
        return Result.ok();
    }

    @GetMapping("/getPyxxByHhbmZjhm")
    public Result<?> getPyxxByHhbmZjhm(@RequestParam(name = "zjhm", required = false) String zjhm,
                                       @RequestParam(name = "hhbm", required = true) String hhbm,
                                       @RequestParam(name = "pyyid", required = false) String pyyid,
                                       @RequestParam(name = "pyyKhid", required = false) String pyyKhid,
                                       @RequestParam(name = "pyyZjhm", required = false) String pyyZjhmStr,
                                       @RequestParam(name = "pywg", required = true) String pywg) {
        QueryWrapper<Nhplpy> wrapper = new QueryWrapper<>();
        String pyyzjhm = null;
        if (StringUtils.isNotBlank(pyyid)) {
            wrapper.eq("id", pyyid).eq("pywg", pywg);
        } else if (StringUtils.isNotBlank(pyyKhid)) {
            Nhxq nhxq = nhxqService.getById(pyyKhid);
            wrapper.eq("pywg", pywg).eq("pyyzjhm", nhxq.getZjhm());
        } else {
            pyyzjhm = pyyZjhmStr;
            wrapper.eq("pywg", pywg).eq("pyyzjhm", pyyzjhm);
        }
        Nhplpy nhplpy = nhplpyService.getOne(wrapper);


        QueryWrapper<Nhbkbpy> nhbkbpyQueryWrapper = new QueryWrapper<>();
        nhbkbpyQueryWrapper.eq("zjhm", zjhm).eq("hhbm", hhbm).eq("pyyzjhm", nhplpy == null ? pyyzjhm : nhplpy.getPyyzjhm());
        return Result.ok(nhbkbpyService.getOne(nhbkbpyQueryWrapper));
    }

    @GetMapping("/getPyxxByOne")
    public Result<?> getPyxxByOne(@RequestParam(name = "nhid", required = false) String nhid,
                                  @RequestParam(name = "pyid", required = false) String pyid) {
        if (StringUtils.isNotBlank(nhid)) {
            QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", nhid);
            Nhxq nhxq = nhxqService.getOne(queryWrapper);

            if (StringUtils.isNotBlank(nhxq.getZjhm())) {
                QueryWrapper<Nhbkbpy> wrapper = new QueryWrapper<>();
                wrapper.eq("zjhm", nhxq.getZjhm());
                List<Nhbkbpy> nhbkbpyList = nhbkbpyService.list(wrapper);
                if (CollUtil.isNotEmpty(nhbkbpyList)) {
                    return Result.ok(nhbkbpyList.stream()
                            .sorted(Comparator.comparing(Nhbkbpy::getPysj).reversed())
                            .limit(1)
                            .collect(Collectors.toList())
                            .get(0));
                }
            }
        }
        if (StringUtils.isNotBlank(pyid)) {
            QueryWrapper<Nhbkbpy> wrapper = new QueryWrapper<>();
            wrapper.eq("id", pyid);
            Nhbkbpy nhbkbpy = nhbkbpyService.getOne(wrapper);
            if (nhbkbpy != null) {
                return Result.ok(nhbkbpy);
            }
        }
        return Result.ok();
    }

    /**
     * 批量评议修改配偶信息同步个人信息页面配合信息
     */
    @PutMapping("/gxPoxx")
    public Result<?> gxPoxx(@RequestBody JSONObject jsonObject) {
        String poxm = jsonObject.getString("poxm");
        String pozjhm = jsonObject.getString("pozjhm");
        String nhid = jsonObject.getString("nhid");
        String ypyid = jsonObject.getString("ypyid");
        if (StringUtils.isNotBlank(poxm) && StringUtils.isNotBlank(pozjhm)) {
            Nhxq nhxq = null;
            Nhbkbpy nhbkbpy = null;
            if (StringUtils.isNotBlank(nhid)) {
                nhxq = nhxqService.getById(nhid);
            }
            if (StringUtils.isNotBlank(ypyid)) {
                nhbkbpy = nhbkbpyService.getById(ypyid);
            }
            if (nhxq != null || nhbkbpy != null) {
                QueryWrapper<KhxxglHnkd> queryWrapper = new QueryWrapper<KhxxglHnkd>();
                queryWrapper.eq("zjhm", nhxq != null ? nhxq.getZjhm() : nhbkbpy.getZjhm());
                KhxxglHnkd khxxglHnkd = khxxglHnkdService.getOne(queryWrapper);
                if (khxxglHnkd != null) {
                    khxxglHnkd.setPozjhm(pozjhm);
                    khxxglHnkd.setPoxm(poxm);
                    khxxglHnkdService.updateById(khxxglHnkd);
                }
            }
        }
        return Result.ok();
    }

    /**
     * 获取当前评议人和配偶的存款总和
     */
    @GetMapping(value = "/getPyypoCkzh")
    public Result<?> getPyypoCkzh(@RequestParam(name = "pozjhm", required = false) String pozjhm,
                                  @RequestParam(name = "dxzjhm", required = false) String dxzjhm) {
        QueryWrapper<KhglYwhywwlxxPad> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.in("zjhm", Stream.of(pozjhm, dxzjhm).collect(Collectors.toList()));
        List<BigDecimal> ckyeList = khglYwhywwlxxPadService.list(queryWrapper1).stream().map(KhglYwhywwlxxPad::getCkye).collect(Collectors.toList());
        return Result.ok(ckyeList.get(0).add(ckyeList.get(1)));
    }

    /**
     * 根据户号查询已经评议的家庭成员-天易2
     *
     * @param hhbm 户号编码
     * @return
     */
    @AutoLog(value = "根据户号查询已经评议的家庭成员")
    @ApiOperation(value = "根据户号查询已经评议的家庭成员", notes = "根据户号查询已经评议的家庭成员")
    @GetMapping(value = "/queryYpyJtcyxxByHhbm2")
    public Result<?> queryYpyJtcyxxByHhbm2(@RequestParam(name = "hhbm", required = true) String hhbm,
                                           @RequestParam(name = "pyyid", required = false) String pyyid,
                                           @RequestParam(name = "pyyzjhm", required = false) String pyyzjhmStr,
                                           @RequestParam(name = "pyyKhid", required = false) String pyyKhid,
                                           @RequestParam(name = "bysxqx", required = false) String bysxqx,
                                           @RequestParam(name = "sfhhmd", required = false) String sfhhmd,
                                           @RequestParam(name = "sfsx", required = false) String sfsx,
                                           @RequestParam(name = "khlx", required = false) String khlx,
                                           @RequestParam(name = "pywg") String pywg) {

        QueryWrapper<Nhplpy> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(pyyid)) {
            wrapper.eq("id", pyyid).eq("pywg", pywg);
        } else if (StringUtils.isNotBlank(pyyKhid)) {
            Nhxq nhxq = nhxqService.getById(pyyKhid);
            wrapper.eq("pywg", pywg).eq("pyyzjhm", nhxq.getZjhm());
        } else {
            wrapper.eq("pywg", pywg).eq("pyyzjhm", pyyzjhmStr);
        }
        Nhplpy nhplpy = nhplpyService.getOne(wrapper);

        //一个评议员已评议的本户成员
        QueryWrapper<Nhbkbpy> nhplpyQueryWrapper = new QueryWrapper<>();
        nhplpyQueryWrapper.eq("hhbm", hhbm).eq("pyyzjhm", nhplpy == null ? pyyzjhmStr : nhplpy.getPyyzjhm());
        List<Nhbkbpy> nhbkbpyList = nhbkbpyService.list(nhplpyQueryWrapper);
        List<String> zjhmList = nhbkbpyList.stream().map(Nhbkbpy::getZjhm).collect(Collectors.toList());

        //天意：一户最多只能评议3个成员,查询所有评议员已经评议过的家庭成员
        QueryWrapper<Nhbkbpy> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hhbm", hhbm);
        List<Nhbkbpy> nhbkbpyList1 = nhbkbpyService.list(queryWrapper);
        List<String> ypyZjhmList = nhbkbpyList1.stream().map(Nhbkbpy::getZjhm).distinct().collect(Collectors.toList());
        //配偶证件号码list
        List<String> poZjhmList = nhbkbpyList1.stream().map(Nhbkbpy::getPozjhm).distinct().collect(Collectors.toList());

        //一户成员
        QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
        nhxqQueryWrapper.eq("hhbm", hhbm);
        List<Nhxq> nhxqAllList = nhxqService.list(nhxqQueryWrapper);
        if ("1".equals(khlx) && StringUtils.isEmpty(bysxqx)) {
            int nlq = 18;
            int nlz = 60;
            String value = tjfxCsszService.queryCszByCsbm("CS0021");

            if (StringUtils.isNotEmpty(value)) {
                String[] values = value.split("-");
                nlq = Integer.parseInt(values[0]);
                nlz = Integer.parseInt(values[1]);
            }
            nhxqQueryWrapper.between("nl", nlq, nlz);
        }
        List<Nhxq> nhxqList = nhxqService.list(nhxqQueryWrapper);

        List<Nhxq> list = nhxqAllList.stream().filter(item -> zjhmList.contains(item.getZjhm())).collect(Collectors.toList());
        List<Nhxq> ywcList = list.stream().map(item1 -> {
            return nhbkbpyList.stream().filter(item4 -> {
                return Objects.equals(item4.getZjhm(), item1.getZjhm());
            }).map(item3 -> {
                return item1.setBz(item3.getBz());
            }).collect(Collectors.toList());
        }).flatMap(List::stream).collect(Collectors.toList());

        Map<String, List> resultMap = new HashMap<>();
        if (ypyZjhmList.size() == 3) {
            nhxqList = nhxqList.stream().filter(item -> ypyZjhmList.contains(item.getZjhm())).collect(Collectors.toList());
        }
        resultMap.put("ywc", ywcList);
        resultMap.put("wwc", nhxqList.stream().filter(item -> !zjhmList.contains(item.getZjhm()) && !poZjhmList.contains(item.getZjhm())).collect(Collectors.toList()));
        return Result.ok(resultMap);
    }

    /**
     * 获取当户复评信息
     */
    @GetMapping(value = "getFxxByHhbm")
    public Result<?> getFxxByHhbm(@RequestParam("hhbm") String hhbm) {
        if (StringUtils.isNotEmpty(hhbm)) {
            //获取背靠背评议当户信息
            QueryWrapper<Nhbkbpy> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("hhbm", hhbm);
            Map<String, List<Nhbkbpy>> nhbkbpyMap = nhbkbpyService.list(queryWrapper).stream().collect(Collectors.groupingBy(Nhbkbpy::getPylc));

            Map<String, List> resultMap = new HashMap<>();
            resultMap.put("ywc", nhbkbpyMap.get("1"));
            if (CollUtil.isNotEmpty(nhbkbpyMap.get("1"))) {
                resultMap.put("wwc", nhbkbpyMap.get("2").stream().filter(item -> !nhbkbpyMap.get("1").stream().map(Nhbkbpy::getZjhm).collect(Collectors.toList()).contains(item.getZjhm())).collect(Collectors.toList()));
            } else {
                resultMap.put("wwc", nhbkbpyMap.get("2"));
            }
            return Result.ok(resultMap);
        }
        return Result.ok();
    }

    /**
     * 保存复评信息
     */
    @PostMapping(value = "/saveNhbkbfpInfo")
    public Result<?> saveNhbkbfpInfo(@RequestBody Nhbkbpy nhbkbpy) {
        if (nhbkbpy != null) {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            QueryWrapper<HrBasStaff> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("yggh", loginUser.getWorkNo());
            HrBasStaff hrBasStaff = hrBasStaffService.getOne(queryWrapper);
            String pyyxm = StringUtils.isNotEmpty(hrBasStaff.getYgxm()) ? hrBasStaff.getYgxm() : loginUser.getWorkNo();
            String pyyzjhm = StringUtils.isNotEmpty(hrBasStaff.getSfzh()) ? hrBasStaff.getSfzh() : loginUser.getWorkNo();
            //删除已经复评的数据
            QueryWrapper<Nhbkbpy> deleteWrapper = new QueryWrapper<>();
            deleteWrapper.eq("zjhm", nhbkbpy.getZjhm());
            deleteWrapper.eq("pyyzjhm", pyyzjhm);
            nhbkbpyService.remove(deleteWrapper);

            Nhbkbpy nhbkbpyExist = nhbkbpyService.getById(nhbkbpy.getId());
            //通过id查询授信对象证件号码
            QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
            nhxqQueryWrapper.eq("zjhm", nhbkbpyExist.getZjhm());
            if (StringUtils.isNotEmpty(nhbkbpy.getSjhm())) {
                Nhxq nhxq = new Nhxq();
                nhxq.setSjhm(nhbkbpy.getSjhm());
                nhxqService.update(nhxq, nhxqQueryWrapper);
            }

            nhbkbpy.setId(UUIDGenerator.generate());
            nhbkbpy.setPyyxm(pyyxm);
            nhbkbpy.setPyyzjhm(pyyzjhm);
            nhbkbpy.setPylc("2");
            nhbkbpy.setLrsj(new Date());
            if (StringUtils.isNotEmpty(nhbkbpy.getBysxqx())) {
                nhbkbpy.setJcmxcs(new BigDecimal("0"));
            } else {
                // 基础模型测算-限制额度100,000
                BigDecimal xzed = new BigDecimal("100000");
                // 未婚
                if (!"1".equals(nhbkbpy.getHyzk()) && nhbkbpy.getJysxed().compareTo(new BigDecimal("100000")) > 0) {
                    BigDecimal[] bigDecimals = nhbkbpy.getJysxed().multiply(new BigDecimal("0.7")).divideAndRemainder(new BigDecimal("10000"));
                    BigDecimal jcmxcs = bigDecimals[0].multiply(new BigDecimal("10000"));
                    if (xzed.compareTo(jcmxcs) < 0) {
                        nhbkbpy.setJcmxcs(xzed);
                    } else {
                        nhbkbpy.setJcmxcs(jcmxcs);
                    }
                }
                // 已婚
                if ("1".equals(nhbkbpy.getHyzk()) && StringUtils.isNotEmpty(nhbkbpy.getPozjhm())) {
                    if (xzed.compareTo(nhbkbpy.getJcmxcs()) <= 0) {
                        nhbkbpy.setJcmxcs(xzed);
                    } else {
                        List<String> zjhmList = Stream.of(nhbkbpy.getZjhm(), nhbkbpy.getPozjhm()).collect(Collectors.toList());
                        QueryWrapper<KhglYwhywwlxxPad> queryWrapper1 = new QueryWrapper<>();
                        queryWrapper1.in("zjhm", zjhmList);
                        queryWrapper1.isNotNull("ckye");
                        BigDecimal ckyezj = khglYwhywwlxxPadService.list(queryWrapper1).stream().map(KhglYwhywwlxxPad::getCkye).reduce(BigDecimal.ZERO, BigDecimal::add);
                        BigDecimal[] bigDecimals = ckyezj.divide(new BigDecimal("2")).divideAndRemainder(new BigDecimal("10000"));
                        BigDecimal jcmxcs = nhbkbpy.getJcmxcs().add(bigDecimals[0].multiply(new BigDecimal("10000")));
                        if (xzed.compareTo(jcmxcs) <= 0) {
                            nhbkbpy.setJcmxcs(xzed);
                        } else {
                            nhbkbpy.setJcmxcs(jcmxcs);
                        }
                    }
                }
            }
            nhbkbpy.setJysxed(nhbkbpy.getJcmxcs());
            nhbkbpyService.save(nhbkbpy);
        }
        return Result.ok();
    }

    /**
     * 保存整村复评
     */
    @AutoLog(value = "背靠背评议-保存整村复评")
    @PostMapping(value = "/saveZcNhbkbfpInfo")
    public Result<?> saveZcNhbkbfpInfo(@RequestBody JSONObject jsonObject) {
        List<String> wgbhList = Stream.of(jsonObject.getString("zuinfo").split(",")).collect(Collectors.toList());
        log.info("保存村组复评：{}", wgbhList.toString());
        if (wgbhList == null || wgbhList.isEmpty()) {
            return Result.error("网格编号不能为空");
        }
        QueryWrapper<VKhglCzfp> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("wgbh", wgbhList);
        queryWrapper.in("pylx", "1","2");
        List<String> pyidList = ivKhglCzfpService.list(queryWrapper).stream().filter(item -> item.getFped() == null && item.getSdje() == null).map(VKhglCzfp::getPyid).collect(Collectors.toList());

        if (CollUtil.isNotEmpty(pyidList)) {
            QueryWrapper<Nhbkbpy> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.in("id", pyidList);
            List<Nhbkbpy> nhbkbpyList = nhbkbpyService.list(queryWrapper1);
            log.info("批量保存村组复评记录数{}", nhbkbpyList.size());
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            QueryWrapper<HrBasStaff> queryWrapper3 = new QueryWrapper<>();
            queryWrapper3.eq("yggh", loginUser.getWorkNo());
            HrBasStaff hrBasStaff = hrBasStaffService.getOne(queryWrapper3);
            String ygxm = loginUser.getWorkNo();
            String ygzjhm = loginUser.getWorkNo();
            if (hrBasStaff != null) {
                if (StringUtils.isNotEmpty(hrBasStaff.getYgxm())) {
                    ygxm = hrBasStaff.getYgxm();
                }
                if (StringUtils.isNotEmpty(hrBasStaff.getSfzh())) {
                    ygzjhm = hrBasStaff.getSfzh();
                }
            }

            String pyyxm = ygxm;
            String pyyzjhm = ygzjhm;

            if (CollUtil.isNotEmpty(nhbkbpyList))
                nhbkbpyList.forEach(e -> {
                    e.setId(UUIDGenerator.generate());
                    e.setPyyxm(pyyxm);
                    e.setPyyzjhm(pyyzjhm);
                    e.setPylc("2");
                    if (StringUtils.isNotEmpty(e.getBysxqx())) {
                        e.setJcmxcs(new BigDecimal("0"));
                    }
                    nhbkbpyService.save(e);
                });
        }
        //更新完成复评标志
        String id = jsonObject.getString("id");
        Nhczfp nhczfp = new Nhczfp();
        nhczfp.setId(id);
        nhczfp.setSfwcfp("1");
        nhczfpService.updateById(nhczfp);
        return Result.ok();
    }

    /**
     * 保存整个支行审定
     */
    @AutoLog(value = "背靠背评议-保存支行审定")
    @PostMapping(value = "/saveZgZhsdInfo")
    public Result<?> saveZgZhsdInfo(@RequestBody JSONObject jsonObject) {
        String sdwg = jsonObject.getString("sdwg");
        log.info("保存支行审定：{}", sdwg);
        QueryWrapper<VKhglCzfp> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(sdwg)) {
            queryWrapper.inSql("wgbh", "select wgbh from yxdygl_main where wgbh in ('" + sdwg + "') or parent_id in ('" + sdwg + "')");
        } else {
            return Result.error("审定网格不能为空！");
        }
        queryWrapper.isNotNull("fped").isNull("sdje");
        List<VKhglCzfp> vKhglCzfpList = ivKhglCzfpService.list(queryWrapper);

        if (CollUtil.isNotEmpty(vKhglCzfpList)) {
            log.info("批量保存支行审定记录数{}", vKhglCzfpList.size());
            vKhglCzfpList.forEach(item -> {
                Nhbkbpy nhbkbpy = nhbkbpyService.getById(item.getPyid());
                nhbkbpy.setPylc("1,3");
                nhbkbpy.setZhsded(item.getJcmxcs().compareTo(item.getFped())>0 ? new BigDecimal(item.getJcmxcs()) : new BigDecimal(item.getFped()));
                nhbkbpyService.updateById(nhbkbpy);
            });
        }
        //更新完成审定标志
        String id = jsonObject.getString("id");
        Nhzhsd nhzhsd = new Nhzhsd();
        nhzhsd.setId(id);
        nhzhsd.setSfwcsd("1");
        nhzhsdService.updateById(nhzhsd);
        return Result.ok();
    }

    /**
     * 更新农户表
     */
    @PostMapping("/updateSjhm")
    public Result<?> updateSjhm(@RequestBody VKhglCzfp vKhglCzfp) {
        if (StringUtils.isNotBlank(vKhglCzfp.getPyid()) && StringUtils.isNotBlank(vKhglCzfp.getSjhm())) {
            QueryWrapper<Nhbkbpy> nhbkbpyQueryWrapper = new QueryWrapper<>();
            nhbkbpyQueryWrapper.eq("id", vKhglCzfp.getPyid());
            String zjhm = nhbkbpyService.getOne(nhbkbpyQueryWrapper).getZjhm();

            if (StringUtils.isNotBlank(zjhm)) {
                QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("zjhm", zjhm);
                Nhxq nhxq = nhxqService.getOne(queryWrapper);
                nhxq.setSjhm(vKhglCzfp.getSjhm());
                nhxqService.updateById(nhxq);
                Result.ok("修改成功！");
            }
        }
        return Result.ok();
    }

    /**
     * 保存复评信息
     */
    @PostMapping(value = "/saveZhfpInfo")
    public Result<?> saveZhfpInfo(@RequestBody VKhglCzfp vKhglCzfp) {
        if (vKhglCzfp != null) {
            Nhbkbpy nhbkbpy = nhbkbpyService.getById(vKhglCzfp.getPyid());
            if (nhbkbpy != null) {
                nhbkbpy.setPylc("1,3");
                nhbkbpy.setZhsded(new BigDecimal(vKhglCzfp.getSdje()));
                if (StringUtils.isNotEmpty(vKhglCzfp.getZhsdbz())) {
                    nhbkbpy.setZhsdbz(vKhglCzfp.getZhsdbz());
                }
                nhbkbpyService.updateById(nhbkbpy);
            }
        }
        return Result.ok();
    }

    /**
     * 获取表内/表外不良贷款信息
     */
    @GetMapping("getbldkInfo")
    public Result<?> getBnbldk(@RequestParam("hhbm") String hhbm) {
        if (StringUtils.isNotBlank(hhbm)) {
            return Result.ok(pyyxxService.getbldkInfo(hhbm));
        }
        return Result.ok();
    }
}
