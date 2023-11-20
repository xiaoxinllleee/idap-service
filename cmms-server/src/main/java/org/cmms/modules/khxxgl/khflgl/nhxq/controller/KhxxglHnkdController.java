package org.cmms.modules.khxxgl.khflgl.nhxq.controller;

import java.math.BigDecimal;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.StopWatch;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.excel.JxlsConstants;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.util.RedisUtil;
import org.cmms.modules.khgl.nh.service.IFjglService;
import org.cmms.modules.khgl.nh.service.INhbkbpyService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.HndkExpVO;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.HnkdConstant;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.KhxxglHnkd;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IKhxxglHnkdService;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.sjxf.qtxt.khxxst.grkhb.entity.Grkhb;
import org.cmms.modules.sjxf.qtxt.khxxst.grkhb.service.IGrkhbService;
import org.cmms.modules.sjxf.qtxt.khxxst.khxxb.entity.Khxxb;
import org.cmms.modules.sjxf.qtxt.khxxst.khxxb.service.IKhxxbService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.tjfx.jcsjgl.cssz.entity.TjfxCssz;
import org.cmms.modules.tjfx.jcsjgl.cssz.service.ITjfxCsszService;
import org.cmms.modules.tjfx.tjfxHnkd.entity.TjfxHnkd;
import org.cmms.modules.tjfx.tjfxHnkd.service.ITjfxHnkdService;
import org.cmms.modules.xdgl.grkhpjsx.utils.ELoandDictUtil;
import org.cmms.modules.ygjx.entity.ErpTdjx;
import org.cmms.modules.yxdygl.pqqxgl.service.IYxdyglPqqxglService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 惠农快贷
 * @Author: jeecg-boot
 * @Date: 2022-01-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "惠农快贷")
@RestController
@RequestMapping("/nh/khxxglHnkd")
public class KhxxglHnkdController extends JeecgController<KhxxglHnkd, IKhxxglHnkdService> {
    @Autowired
    private IKhxxglHnkdService khxxglHnkdService;
    @Autowired
    private INhxqService nhxqService;
    @Autowired
    private ITjfxCsszService tjfxCsszService;
    @Autowired
    INhbkbpyService nhbkbpyService;
    @Autowired
    ISysDictService dictService;
    @Autowired
    IYxdyglPqqxglService yxdyglPqqxglService;
    @Autowired
    RedisUtil redisUtil;

    @Value(value = "${hnkdurl:http://11.0.9.41:9005/zzkd/service}")
    private String hnkdurl;

    @Value(value = "${hnkcssj:20000}")
    private String hnkcssj;

    @Autowired
    IGrkhbService grkhbService;

    @Autowired
    IKhxxbService khxxbService;

    @Autowired
    ITjfxHnkdService tjfxHnkdService;
    /**
     * 分页列表查询
     *
     * @param khxxglHnkd
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "惠农快贷-分页列表查询")
    @ApiOperation(value = "惠农快贷-分页列表查询", notes = "惠农快贷-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(KhxxglHnkd khxxglHnkd,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {

        String wgbh = null;
        if (StringUtils.isNotBlank(khxxglHnkd.getWgbh()))
            wgbh = khxxglHnkd.getWgbh();
        khxxglHnkd.setWgbh(null);

        String hyzk = null;
        if (StringUtils.isNotBlank(khxxglHnkd.getHyzk()))
            hyzk = khxxglHnkd.getHyzk();
        khxxglHnkd.setHyzk(null);

        String sfdrhnkd = null;
        if (StringUtils.isNotBlank(khxxglHnkd.getSfdrhnkd())){
            sfdrhnkd = khxxglHnkd.getSfdrhnkd();
        }
        khxxglHnkd.setSfdrhnkd(null);

        Page<KhxxglHnkd> page = new Page<KhxxglHnkd>(pageNo, pageSize);
        QueryWrapper<KhxxglHnkd> queryWrapper = QueryGenerator.initQueryWrapper(khxxglHnkd, req.getParameterMap());

        if (StringUtils.isNotBlank(hyzk)){
            if ("1".equals(hyzk)){
                queryWrapper.eq("hyzk","1");
            }else {
                queryWrapper.ne("hyzk","1");
            }
        }

        if (StringUtils.isNotBlank(sfdrhnkd)){
            if ("1".equals(sfdrhnkd)){
                queryWrapper.inSql("zjhm"," select distinct khsfzhm from erp_tjfx_hnkd ");
            }else {
                //queryWrapper.notIn("zjhm"," select distinct khsfzhm from erp_tjfx_hnkd ");
                queryWrapper.apply("  zjhm not in  (select distinct khsfzhm from erp_tjfx_hnkd) ");
            }
        }

        if (getRedisQydm().equals(QydmEnums.LIUYANG.getQydmCode())) {
//            if (StringUtils.isNotBlank(wgbh)){
//                queryWrapper.inSql("wgbh","select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id");
//            }
//
//            //queryWrapper.eq("hjsz","1");
//            if (getRedisRoleCode().equalsIgnoreCase("khjl")) {
//                queryWrapper.eq("khjl", getWorkNo());
//            } else if (getRedisRoleCode().equals("ZHHZ") || getRedisRoleCode().equalsIgnoreCase("fxjl")) {
//                queryWrapper.eq("sszh", getLoginUser().getOrgCode());
//            }
            queryWrapper.eq("sszh", getLoginUser().getOrgCode());
        } else {


            if (StringUtils.isNotBlank(wgbh)) {
                queryWrapper.inSql("wgbh", "select menu_id from YXDYGL_PQQXGL where khjl = '" + getLoginUser().getWorkNo() + "' and  " +
                        "menu_id in (" +
                        "select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id )");

            } else {
                queryWrapper.inSql("wgbh", "select menu_id from YXDYGL_PQQXGL where khjl = '" + getLoginUser().getWorkNo() + "'");
            }
        }

        IPage<KhxxglHnkd> pageList = khxxglHnkdService.page(page, queryWrapper);
        List<KhxxglHnkd> records = pageList.getRecords();
        System.out.println(records.size());
        for (int i = 0; i < records.size(); i++) {
            KhxxglHnkd khxxglHnkd1 = records.get(i);
            if (StringUtils.isNotBlank(sfdrhnkd)){
                khxxglHnkd1.setSfdrhnkd(sfdrhnkd);
            }else {
                LambdaQueryWrapper<TjfxHnkd> tjfxHnkdLambdaQueryWrapper = new LambdaQueryWrapper<>();
                tjfxHnkdLambdaQueryWrapper.eq(TjfxHnkd::getKhsfzhm,khxxglHnkd1.getZjhm());
                tjfxHnkdLambdaQueryWrapper.orderByDesc(TjfxHnkd::getSjrq);
                TjfxHnkd one = tjfxHnkdService.getOne(tjfxHnkdLambdaQueryWrapper,false);
                if ( one == null){
                    khxxglHnkd1.setSfdrhnkd("2");
                }else {
                    khxxglHnkd1.setSfdrhnkd("1");
                    if (one.getEy() != null){
                        khxxglHnkd1.setDred(one.getEy());
                    }
                }
            }
        }
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param khxxglHnkd
     * @return
     */
    @AutoLog(value = "惠农快贷-添加")
    @ApiOperation(value = "惠农快贷-添加", notes = "惠农快贷-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody KhxxglHnkd khxxglHnkd) {
        Nhxq nhxq = nhxqService.getById(khxxglHnkd.getId());
        khxxglHnkd.setZjhm(nhxq.getZjhm());
        if (StringUtils.isNotEmpty(khxxglHnkd.getPoid())) {
            Nhxq poXq = nhxqService.getById(khxxglHnkd.getPoid());
            khxxglHnkd.setPozjhm(poXq.getZjhm());
        }
        khxxglHnkdService.save(khxxglHnkd);

        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param khxxglHnkd
     * @return
     */
    @AutoLog(value = "惠农快贷-编辑")
    @ApiOperation(value = "惠农快贷-编辑", notes = "惠农快贷-编辑")
    @PostMapping(value = "/edit")
    public Result<?> edit(@RequestBody KhxxglHnkd khxxglHnkd) {
        String csz = tjfxCsszService.queryCszByCsbm(HnkdConstant.HNDK_PREFIX+"4");
        String sxqx = tjfxCsszService.queryCszByCsbm(HnkdConstant.HNDK_PREFIX+"5");
        BigDecimal jd = new BigDecimal(0);
        if (StringUtils.isNotBlank(csz)){
            jd = new BigDecimal(csz);
        }
        //根据ID查询证件号码，解决如果不编辑任何信息报错的问题
        Nhxq nhxq = nhxqService.getById(khxxglHnkd.getId());
        khxxglHnkd.setZjhm(nhxq.getZjhm());
        khxxglHnkd.setJd(khxxglHnkd.getJd() == null ? jd : khxxglHnkd.getJd());
        khxxglHnkd.setSxqx(khxxglHnkd.getSxqx() == null ? Integer.parseInt(sxqx) : khxxglHnkd.getSxqx());

        if (StringUtils.isNotEmpty(khxxglHnkd.getPoid())) {
            Nhxq poXq = nhxqService.getById(khxxglHnkd.getPoid());
            khxxglHnkd.setPozjhm(poXq.getZjhm());
        }

        UpdateWrapper<KhxxglHnkd> updateWrapper = new UpdateWrapper<KhxxglHnkd>();
        updateWrapper.eq("id", khxxglHnkd.getId());
        khxxglHnkdService.update(khxxglHnkd, updateWrapper);

        return Result.ok("编辑成功!");
    }

    @PostMapping(value = "/editly")
    public Result<?> editly(@RequestBody KhxxglHnkd khxxglHnkd) {
        log.info("==={}---", khxxglHnkd.toString());
        boolean b = service.updateById(khxxglHnkd);
        if (b)
            return Result.ok("编辑成功!");
        return Result.error("编辑失败!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "惠农快贷-通过id删除")
    @ApiOperation(value = "惠农快贷-通过id删除", notes = "惠农快贷-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        khxxglHnkdService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "惠农快贷-批量删除")
    @ApiOperation(value = "惠农快贷-批量删除", notes = "惠农快贷-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.khxxglHnkdService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "惠农快贷-通过id查询")
    @ApiOperation(value = "惠农快贷-通过id查询", notes = "惠农快贷-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        KhxxglHnkd khxxglHnkd = khxxglHnkdService.getById(id);
        if (khxxglHnkd == null) {
            khxxglHnkd = new KhxxglHnkd();
        }
        if (khxxglHnkd != null) {
            if (khxxglHnkd.getDczrr1bl() == null) {
                String cs0015 = tjfxCsszService.queryCszByCsbm(HnkdConstant.HNDK_PREFIX+"9");
                if (StringUtils.isNotBlank(cs0015) && StringUtils.isNumeric(cs0015)) {
                    BigDecimal dczrr1bl = new BigDecimal(cs0015);
                    khxxglHnkd.setDczrr1bl(dczrr1bl);
                }
            }
            if (khxxglHnkd.getDczrr2bl() == null) {
                String value = tjfxCsszService.queryCszByCsbm(HnkdConstant.HNDK_PREFIX+"11");
                if (StringUtils.isNotBlank(value) && StringUtils.isNumeric(value)) {
                    BigDecimal dyzzrbl = new BigDecimal(value);
                    khxxglHnkd.setDczrr2bl(dyzzrbl);
                }
            }

            if (khxxglHnkd.getGlzrrbl() == null) {
                String value = tjfxCsszService.queryCszByCsbm(HnkdConstant.HNDK_PREFIX+"13");
                if (StringUtils.isNotBlank(value) && StringUtils.isNumeric(value)) {
                    BigDecimal dyzzrbl = new BigDecimal(value);
                    khxxglHnkd.setGlzrrbl(dyzzrbl);
                }
            }

            if (khxxglHnkd.getSczrrbl() == null) {
                String value = tjfxCsszService.queryCszByCsbm(HnkdConstant.HNDK_PREFIX+"15");
                if (StringUtils.isNotBlank(value) && StringUtils.isNumeric(value)) {
                    BigDecimal dyzzrbl = new BigDecimal(value);
                    khxxglHnkd.setSczrrbl(dyzzrbl);
                }
            }

            if (khxxglHnkd.getZzspzrrbl() == null) {
                String value = tjfxCsszService.queryCszByCsbm(HnkdConstant.HNDK_PREFIX+"17");
                if (StringUtils.isNotBlank(value) && StringUtils.isNumeric(value)) {
                    BigDecimal dyzzrbl = new BigDecimal(value);
                    khxxglHnkd.setZzspzrrbl(dyzzrbl);
                }
            }

            if (khxxglHnkd.getDyzrrbl() == null) {
                String value = tjfxCsszService.queryCszByCsbm(HnkdConstant.HNDK_PREFIX+"19");
                if (StringUtils.isNotBlank(value) && StringUtils.isNumeric(value)) {
                    BigDecimal dyzzrbl = new BigDecimal(value);
                    khxxglHnkd.setDyzrrbl(dyzzrbl);
                }
            }



//            if (khxxglHnkd.getDyzrrbl() == null) {
//                String cs0016 = tjfxCsszService.queryCszByCsbm("CS0016");
//                if (StringUtils.isNotBlank(cs0016) && StringUtils.isNumeric(cs0016)) {
//                    BigDecimal dyzzrbl = new BigDecimal(cs0016);
//                    khxxglHnkd.setDyzrrbl(dyzzrbl);
//                }
//            }
//            if (khxxglHnkd.getGlzrrbl() == null) {
//                String cs0017 = tjfxCsszService.queryCszByCsbm("CS0017");
//                if (StringUtils.isNotBlank(cs0017) && StringUtils.isNumeric(cs0017)) {
//                    BigDecimal glzrrbl = new BigDecimal(cs0017);
//                    khxxglHnkd.setGlzrrbl(glzrrbl);
//                }
//            }
//            if (khxxglHnkd.getSczrrbl() == null) {
//                String cs0018 = tjfxCsszService.queryCszByCsbm("CS0018");
//                if (StringUtils.isNotBlank(cs0018) && StringUtils.isNumeric(cs0018)) {
//                    BigDecimal sczrrbl = new BigDecimal(cs0018);
//                    khxxglHnkd.setSczrrbl(sczrrbl);
//                }
//            }
//            if (khxxglHnkd.getZzspzrrbl() == null) {
//                String cs0019 = tjfxCsszService.queryCszByCsbm("CS0019");
//                if (StringUtils.isNotBlank(cs0019) && StringUtils.isNumeric(cs0019)) {
//                    BigDecimal zzspzrrbl = new BigDecimal(cs0019);
//                    khxxglHnkd.setZzspzrrbl(zzspzrrbl);
//                }
//            }
//
//            if (khxxglHnkd.getDyzrrbl() == null) {
//                String value = tjfxCsszService.queryCszByCsbm("HNKD11");
//                if (StringUtils.isNotBlank(value) && StringUtils.isNumeric(value)) {
//                    BigDecimal dyzzrbl = new BigDecimal(value);
//                    khxxglHnkd.setDczrr2bl(dyzzrbl);
//                }
//            }
        }

        String csz = tjfxCsszService.queryCszByCsbm(HnkdConstant.HNDK_PREFIX+"4");
        String sxqx = tjfxCsszService.queryCszByCsbm(HnkdConstant.HNDK_PREFIX+"5");
        BigDecimal jd = new BigDecimal("0");
        BigDecimal qx = new BigDecimal("0");
        if (StringUtils.isNotBlank(csz) && StringUtils.isNumeric(csz)) {
            jd = new BigDecimal(csz);
        }
        if (StringUtils.isNotBlank(sxqx) && StringUtils.isNumeric(sxqx)) {
            qx = new BigDecimal(sxqx);
        }

        if (khxxglHnkd != null) {
            khxxglHnkd.setJd(khxxglHnkd.getJd() == null ? jd : khxxglHnkd.getJd());
            khxxglHnkd.setSxqx(khxxglHnkd.getSxqx() == null ? qx.intValue() : khxxglHnkd.getSxqx());
        }
        return Result.ok(khxxglHnkd);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param khxxglHnkd
     */
    /**
     * 导出excel
     *
     * @param khxxglHnkd
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(KhxxglHnkd khxxglHnkd) {
        log.info("===开始惠农快贷导出,当前用户工号{}===", getLoginUser().getWorkNo());
        Map<String, Object> map = new HashMap<>();
        List<HndkExpVO> hndkExpVOS = new ArrayList<>();
        ModelAndView mv = new ModelAndView(new TemplateExcelView());
        String port = environment.getProperty("common.path.export");
        //导出文件名称
        mv.addObject(JxlsConstants.FILE_NAME, "hnkdls.xls");
        mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("hnkdls.xls"));
        mv.addObject(JxlsConstants.SAVE_FILE_NAME, port + "/hnkdls.xls");
        mv.addObject(JxlsConstants.MAP_DATA, map);
        map.put("list", hndkExpVOS);
        //先查农户表
        LambdaQueryWrapper<KhxxglHnkd> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (QydmEnums.LIUYANG.getQydmCode().equals(getRedisQydm())){
            lambdaQueryWrapper.eq(KhxxglHnkd::getHjsz,"1");
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getKhlx())) {
            lambdaQueryWrapper.eq(KhxxglHnkd::getKhlx, khxxglHnkd.getKhlx());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getSszh())) {
            lambdaQueryWrapper.eq(KhxxglHnkd::getSszh, khxxglHnkd.getSszh());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getKhmc())) {
            lambdaQueryWrapper.eq(KhxxglHnkd::getKhmc, khxxglHnkd.getKhmc());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getZjhm())) {
            lambdaQueryWrapper.eq(KhxxglHnkd::getZjhm, khxxglHnkd.getZjhm());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getSfycdg())) {
            lambdaQueryWrapper.eq(KhxxglHnkd::getSfycdg, khxxglHnkd.getSfycdg());
        }
//        if (StringUtils.isNotBlank(khxxglHnkd.getWgbh())) {
//            lambdaQueryWrapper.inSql(KhxxglHnkd::getWgbh, "select menu_id from YXDYGL_PQQXGL where khjl = '" + getLoginUser().getWorkNo() + "' and  " +
//                    "menu_id in (" +
//                    "select wgbh from yxdygl_main start with wgbh='" + khxxglHnkd.getWgbh() + "' connect by prior wgbh=parent_id )");
//        } else {
//            lambdaQueryWrapper.inSql(KhxxglHnkd::getWgbh, "select menu_id from YXDYGL_PQQXGL where khjl = '" + getLoginUser().getWorkNo() + "'");
//        }
        if (getRedisQydm().equals(QydmEnums.LIUYANG.getQydmCode())) {
            //lambdaQueryWrapper.eq(KhxxglHnkd::getKhjl,getWorkNo());
            if (StringUtils.isNotBlank(khxxglHnkd.getWgbh())) {
                lambdaQueryWrapper.inSql(KhxxglHnkd::getWgbh,"select wgbh from yxdygl_main start with wgbh='" + khxxglHnkd.getWgbh() + "' connect by prior wgbh=parent_id");
            }

            if (getRedisRoleCode().equalsIgnoreCase("khjl")) {
                lambdaQueryWrapper.eq(KhxxglHnkd::getKhjl, getWorkNo());
            } else if (getRedisRoleCode().equals("ZHHZ") || getRedisRoleCode().equalsIgnoreCase("fxjl")) {
                lambdaQueryWrapper.eq(KhxxglHnkd::getSszh, getLoginUser().getOrgCode());
            }

        } else {
            if (StringUtils.isNotBlank(khxxglHnkd.getWgbh())) {
                lambdaQueryWrapper.inSql(KhxxglHnkd::getWgbh, "select menu_id from YXDYGL_PQQXGL where khjl = '" + getLoginUser().getWorkNo() + "' and  " +
                        "menu_id in (" +
                        "select wgbh from yxdygl_main start with wgbh='" + khxxglHnkd.getWgbh() + "' connect by prior wgbh=parent_id )");
            } else {
                lambdaQueryWrapper.inSql(KhxxglHnkd::getWgbh, "select menu_id from YXDYGL_PQQXGL where khjl = '" + getLoginUser().getWorkNo() + "'");
            }

            if (getRedisQydm().equals(QydmEnums.TIANYI.getQydmCode())){
                if (StringUtils.isNotBlank(khxxglHnkd.getHyzk())){
                        if ("1".equals(khxxglHnkd.getHyzk())){
                            lambdaQueryWrapper.eq(KhxxglHnkd::getHyzk,"1");
                        }else {
                            lambdaQueryWrapper.ne(KhxxglHnkd::getHyzk,"1");
                        }
                }

                if (StringUtils.isNotBlank(khxxglHnkd.getSfdrhnkd())){
                    if ("1".equals(khxxglHnkd.getSfdrhnkd())){
                        lambdaQueryWrapper.inSql(KhxxglHnkd::getZjhm," select distinct khsfzhm from erp_tjfx_hnkd ");
                    }else {
                        lambdaQueryWrapper.apply("  zjhm not in  (select distinct khsfzhm from erp_tjfx_hnkd) ");
                    }
                }
            }
        }
        List<KhxxglHnkd> list = service.list(lambdaQueryWrapper);
        log.info("===快贷表查询完毕===");
        List<Nhxq> byHnkd = null;
        Map<String, Nhxq> nhxqMap = new HashMap<>();
        if (!getRedisQydm().equals(QydmEnums.LIUYANG.getQydmCode())){
            byHnkd = nhxqService.getByHnkd(khxxglHnkd);
            log.info("===农户表查询完毕===");

            if (CollUtil.isNotEmpty(byHnkd)) {
                if (CollUtil.isNotEmpty(byHnkd)) {
                    log.info("===农户表{}条数据===", byHnkd.size());
                    for (int i = 0; i < byHnkd.size(); i++) {
                        Nhxq o = byHnkd.get(i);
                        if (StringUtils.isNotBlank(o.getZjhm())) {
                            nhxqMap.put(o.getZjhm(), o);
                        }
                    }
                }
            }
            log.info("===map完毕===");
        }


        LambdaQueryWrapper<TjfxCssz> tjfxCsszLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tjfxCsszLambdaQueryWrapper.like(TjfxCssz::getCsbm, HnkdConstant.HNDK_PREFIX);
        List<TjfxCssz> tjfxCsszs = tjfxCsszService.list(tjfxCsszLambdaQueryWrapper);
        Map<String, String> stringMap = new HashMap<>();
        if (CollUtil.isNotEmpty(tjfxCsszs)) {
            for (int i = 0, n = tjfxCsszs.size(); i < n; i++) {
                TjfxCssz tjfxCssz = tjfxCsszs.get(i);
                if (StringUtils.isNotBlank(tjfxCssz.getCsbm()) && StringUtils.isNotBlank(tjfxCssz.getCsz())) {
                    stringMap.put(tjfxCssz.getCsbm(), tjfxCssz.getCsz());
                }
            }
        }

        HndkExpVO hndkExpVO = null;
        if (CollUtil.isNotEmpty(list)) {
            log.info("===快贷表{}条数据可以导出惠农快贷===", list.size());

            int n = list.size();
            for (int i = 0; i < n; i++) {
                if (i > 0 && i % 10 == 0) {
                    log.info("===当前组装到{}===", i);
                }
                if (i == n - 1) {
                    log.info("===组装完成{}===", i);
                }
                hndkExpVO = new HndkExpVO();
                KhxxglHnkd old = list.get(i);
                if (CollUtil.isNotEmpty(map)) {
                    hndkExpVO.initCssz(stringMap);
                }
                if (getRedisQydm().equals(QydmEnums.LIUYANG.getQydmCode())) {
                    //原封不动
                    hndkExpVO.setSysDictService(dictService);
                    hndkExpVO.initOldHnkd(old, getRedisQydm());
                } else
                {
                    hndkExpVO.setSysDictService(dictService);
                    hndkExpVO.hnkdInit(old, getRedisQydm());
                    //hndkExpVO.setFzr(getLoginUser().getWorkNo());

                    Nhxq sxr = null;
                    if (CollUtil.isNotEmpty(nhxqMap)) {
                        sxr = nhxqMap.get(old.getZjhm());
                    }

                    if (sxr != null) {
                        hndkExpVO.init5(sxr,getRedisQydm());
                        if (StringUtils.isNotBlank(sxr.getHylb())) {
                            String hyflqz = ELoandDictUtil.hyflqz(sxr.getHylb());
                            String app_hylb = dictService.queryDictTextByKey("app_hylb", sxr.getHylb());
                            hndkExpVO.setRow43(hyflqz + app_hylb);
                        }
                    }
                }

                hndkExpVOS.add(hndkExpVO);
            }
        }
        map.put("list", hndkExpVOS);
        log.info("===放进list中开始导出===");
        return mv;
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
        return super.importExcel(request, response, KhxxglHnkd.class);
    }

    @RequestMapping("/count")
    public Result<?> updateHnkdType() {
        log.info("=====开始更新惠农快贷客户类型=====");
        if (getRedisQydm().equals(QydmEnums.LIUYANG.getQydmCode())) {
            //updatensb();
            //service.updateHongMingDan();

            LambdaQueryWrapper<TjfxCssz> tbTjfxCsszLambdaQueryWrapper = new LambdaQueryWrapper<>();
            tbTjfxCsszLambdaQueryWrapper.eq(TjfxCssz::getCsmc, "vnhpjsx");
            List<TjfxCssz> list = tjfxCsszService.list(tbTjfxCsszLambdaQueryWrapper);
            boolean b = list.stream().anyMatch(x -> getWorkNo().equals(x.getCsz()));
            if (b){
                nhxqService.drhnkdbmd();
            }else {
                nhxqService.drhnkdbmdsszh(getLoginUser().getOrgCode());
            }
        } else {
            service.updateHongMingDan();
        }
        log.info("==========>名单更新完成");
        return Result.ok();
    }


    @GetMapping(value = "/listLy")
    public Result<?> listLy(KhxxglHnkd khxxglHnkd,
                            HttpServletRequest req) {

        Object glytzjk = redisUtil.get("glytzjk");
        if (glytzjk != null) {
            return Result.error("管理员停止使用接口");
        }

        Object hnkdyc = redisUtil.get("hnkdyc");
        if (hnkdyc != null) {
            return Result.error(hnkdyc.toString());
        }
        //判断当前时间
        if (!isCanUseTime()) {

            int hnkdksint = 8;
            int hnkdjsint = 20;
            Object hnkdks = redisUtil.get("hnkdks");
            Object hnkdjs = redisUtil.get("hnkdjs");
            if (hnkdks != null)
                hnkdksint  = Integer.parseInt(hnkdks.toString());

            if (hnkdjs != null)
                hnkdjsint  = Integer.parseInt(hnkdjs.toString());


            return Result.error("导入惠农快贷只能在"+hnkdksint+"点到"+hnkdjsint+"点使用！");
        }

        //20秒
        int n = Integer.valueOf(hnkcssj);

        //先查询有人使用导入没有
        Object slsHnkdIsUse = redisUtil.get("slsHnkdIsUse");
        log.info("===slsHnkdIsUse={}===", slsHnkdIsUse);
        if (slsHnkdIsUse != null) {
            return Result.error(slsHnkdIsUse.toString() + " 在使用惠农快贷接口，请稍等。");
        } else {
            redisUtil.set("slsHnkdIsUse", getWorkNo());
        }

        String wgbh = null;
        if (StringUtils.isNotBlank(khxxglHnkd.getWgbh()))
            wgbh = khxxglHnkd.getWgbh();
        khxxglHnkd.setWgbh(null);
        QueryWrapper<KhxxglHnkd> queryWrapper = QueryGenerator.initQueryWrapper(khxxglHnkd, req.getParameterMap());

        if (getRedisQydm().equals(QydmEnums.LIUYANG.getQydmCode())) {
            if (StringUtils.isNotBlank(wgbh)){
                queryWrapper.inSql("wgbh","select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id");
            }

            queryWrapper.eq("hjsz","1");
            if (getRedisRoleCode().equalsIgnoreCase("khjl")) {
                queryWrapper.eq("khjl", getWorkNo());
            } else if (getRedisRoleCode().equals("ZHHZ") || getRedisRoleCode().equalsIgnoreCase("fxjl")) {
                queryWrapper.eq("sszh", getLoginUser().getOrgCode());
            }

        } else {

            if (StringUtils.isNotBlank(wgbh)) {
                queryWrapper.inSql("wgbh", "select menu_id from YXDYGL_PQQXGL where khjl = '" + getLoginUser().getWorkNo() + "' and  " +
                        "menu_id in (" +
                        "select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id )");

            } else {
                queryWrapper.inSql("wgbh", "select menu_id from YXDYGL_PQQXGL where khjl = '" + getLoginUser().getWorkNo() + "'");
            }
        }

        List<KhxxglHnkd> list = service.list(queryWrapper);
        if (CollUtil.isNotEmpty(list)) {
            log.info("===当前用户导入惠农快贷{}===", list.size());
            for (int i = 0; i < list.size(); i++) {
                //管理员是否停止接口
                glytzjk = redisUtil.get("glytzjk");
                if (glytzjk != null) {
                    return Result.error("管理员停止使用接口");
                }

                if (!isCanUseTime()) {
                    return Result.error("导入惠农快贷只能在8点到20点使用！");
                }

                KhxxglHnkd old = list.get(i);
                //用惠农快贷去转报文
                String bw = service.hnkdMessage(old);
                Object sfdybw = redisUtil.get("sfdyhnkdbw");
                if (sfdybw != null){
                    log.info("===报文{}===",bw);
                }

                try {
                    HttpRequest post = HttpUtil.createPost(hnkdurl);
                    post.body(bw);
                    long l = System.currentTimeMillis();

                    HttpResponse execute = post.execute();
                    Object sfdyhnkdfhz = redisUtil.get("sfdyhnkdfhz");
                    if (sfdyhnkdfhz != null)
                        log.info("===返回值{}===",execute);

                    String body = execute.body();
                    long l1 = System.currentTimeMillis();
                    //截取结果出来
                    int i1 = body.indexOf("<ReturnMessage>");
                    int i2 = body.indexOf("</ReturnMessage>");
                    if (i1 > 0 && i2 > 0) {
                        String substring = body.substring(i1 + 15, i2);
                        service.updateResult(old.getId(), substring);
                    } else {
                        log.error("===返回结果截取失败{}===", body);
                    }

                    long l2 = l1 - l;
                    if (l2 > n) {
                        log.error("===接口耗时过长，暂停使用接口，所耗费时长 {} 毫秒===", l2);
                        redisUtil.set("hnkdyc", "接口耗时过长，暂停使用接口");
                        redisUtil.del("slsHnkdIsUse");
                        return Result.error("接口耗时过长，暂停使用接口");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("===惠农快贷接口失败，地址为{}===", hnkdurl);
                    //return Result.error("接口网络错误，请联系管理员");
                }

            }
        }
        redisUtil.del("slsHnkdIsUse");
        return Result.ok();
    }

    public boolean isCanUseTime() {
        int hnkdksint = 8;
        int hnkdjsint = 20;
        Object hnkdks = redisUtil.get("hnkdks");
        Object hnkdjs = redisUtil.get("hnkdjs");
        if (hnkdks != null)
            hnkdksint  = Integer.parseInt(hnkdks.toString());

        if (hnkdjs != null)
            hnkdjsint  = Integer.parseInt(hnkdjs.toString());
        //08 - 20
        String hh = DateUtil.format(new Date(), "HH");
        try {
            Integer integer = Integer.valueOf(hh);
            if (integer >= hnkdksint && integer <= hnkdjsint)
                return true;
        } catch (
                Exception e
        ) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping(value = "/stophnkd")
    public Result<?> stophnkd() {
        redisUtil.set("glytzjk", "管理员停止使用惠农快贷接口！");
        return Result.ok();
    }

    @RequestMapping(value = "/starthnkd")
    public Result<?> starthnkd() {
        redisUtil.del("glytzjk");
        redisUtil.del("hnkdyc");
        redisUtil.del("slsHnkdIsUse");
        return Result.ok();
    }

    public Result<?> updatensb() {
        LambdaQueryWrapper<KhxxglHnkd> khxxglHnkdLambdaQueryWrapper = new LambdaQueryWrapper<>();
        khxxglHnkdLambdaQueryWrapper.eq(KhxxglHnkd::getSfgxxfb, "0");
        List<KhxxglHnkd> list = service.list(khxxglHnkdLambdaQueryWrapper);
        if (CollUtil.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                KhxxglHnkd khxxglHnkd = list.get(i);

                LambdaQueryWrapper<Khxxb> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.eq(Khxxb::getIdentNo, khxxglHnkd.getZjhm());
                lambdaQueryWrapper.eq(Khxxb::getIdentType, "01");
                lambdaQueryWrapper.eq(Khxxb::getDataFlag, "0");
                lambdaQueryWrapper.orderByDesc(Khxxb::getLastUpdateTm);
                List<Khxxb> khxxbs = khxxbService.list(lambdaQueryWrapper);
                if (CollUtil.isNotEmpty(khxxbs)) {
                    String custId = khxxbs.get(0).getCustId();
                    //LambdaQueryWrapper<Grkhb> grkhbLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    //grkhbLambdaQueryWrapper.eq(Grkhb::getCustId,custId);
                    Grkhb grkhbHive = grkhbService.getGrkhbHive(custId);

                    if (StringUtils.isNotBlank(grkhbHive.getNationality())) {
                        if (StringUtils.isBlank(khxxglHnkd.getMz())) {
                            khxxglHnkd.setMz(grkhbHive.getNationality());

                            String zdzh = getZdzh(HnkdConstant.hnkdmz_mz, grkhbHive.getNationality());
                            if (StringUtils.isNotBlank(zdzh))
                                khxxglHnkd.setMz(zdzh);
                        }
                    }

                    if (StringUtils.isNotBlank(grkhbHive.getResidence())) {
                        if (StringUtils.isBlank(khxxglHnkd.getJzzk())) {
                            if ("9".equals(grkhbHive.getResidence())){
                                khxxglHnkd.setJzzk("8");
                            }else {
                                khxxglHnkd.setJzzk(grkhbHive.getResidence());
                            }
                        }
                    }

                    if (StringUtils.isNotBlank(grkhbHive.getHealth())) {
                        if (StringUtils.isBlank(khxxglHnkd.getJkzk())) {
                            khxxglHnkd.setJkzk(ELoandDictUtil.jkzhZh(grkhbHive.getResidence()));
                        }
                    }

                    if (StringUtils.isNotBlank(grkhbHive.getHighestSchooling())) {
                        if (StringUtils.isBlank(khxxglHnkd.getZgxl())) {
                            khxxglHnkd.setZgxl(ELoandDictUtil.zgxlZh(grkhbHive.getHighestSchooling()));
                        }
                    }

                    if (StringUtils.isNotBlank(grkhbHive.getHighestDegree())) {
                        if (StringUtils.isBlank(khxxglHnkd.getZgxw())) {
                            if ("9".equals(grkhbHive.getHighestDegree())){
                                khxxglHnkd.setZgxw("0");

                            }else {
                                khxxglHnkd.setZgxw(grkhbHive.getHighestDegree());
                            }
                        }
                    }

                    if (StringUtils.isNotBlank(grkhbHive.getCareerType())) {
                        if (StringUtils.isBlank(khxxglHnkd.getZy())) {
                            khxxglHnkd.setZy(ELoandDictUtil.cszy(grkhbHive.getCareerType()));
                        }
                    }

                    if (StringUtils.isNotBlank(grkhbHive.getUnitName())) {
                        if (StringUtils.isBlank(khxxglHnkd.getXgzdw())) {
                            khxxglHnkd.setXgzdw(grkhbHive.getUnitName());
                        }
                    }

//                    if (StringUtils.isNotBlank(grkhbHive.getDuty())) {
//                        if (StringUtils.isBlank(khxxglHnkd.getXdrzw())) {
//                            khxxglHnkd.setXdrzw(grkhbHive.getDuty());
//                        }
//                    }

                    if (StringUtils.isNotBlank(grkhbHive.getPostAddr())) {
                        if (StringUtils.isBlank(khxxglHnkd.getTxdz())) {
                            khxxglHnkd.setTxdz(grkhbHive.getPostAddr());
                        }
                    }

                    if (StringUtils.isNotBlank(grkhbHive.getPostZipcode())) {
                        if (StringUtils.isBlank(khxxglHnkd.getTxdzyb())) {
                            khxxglHnkd.setTxdzyb(grkhbHive.getPostZipcode());
                        }
                    }

                    if (StringUtils.isNotBlank(grkhbHive.getCareerTitle())) {
                        if (StringUtils.isBlank(khxxglHnkd.getZc())) {
                            khxxglHnkd.setZc(ELoandDictUtil.zcZh(grkhbHive.getCareerTitle()));
                        }
                    }

                    khxxglHnkd.setSfgxxfb("1");
                    boolean b = service.updateById(khxxglHnkd);
                }
            }
        }
        return Result.ok();
    }

    public String getZdzh(String dict,String key){
        String s = dictService.queryDictTextByKey(dict, key);
        if (StringUtils.isNotBlank(s))
            return s;
        return null;
    }
}
