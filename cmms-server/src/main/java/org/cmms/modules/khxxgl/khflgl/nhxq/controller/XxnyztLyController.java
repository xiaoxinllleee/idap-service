package org.cmms.modules.khxxgl.khflgl.nhxq.controller;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.net.URLDecoder;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ZipUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.base.entity.SimpleStandardTable;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.DictModel;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.utils.Base64Util;
import org.cmms.modules.appbase.tbtjfxcssz.entity.TbTjfxCssz;
import org.cmms.modules.appbase.tbtjfxcssz.service.ITbTjfxCsszService;
import org.cmms.modules.dklldj.csszgl.fdcsdzb.service.IFdcsdzbService;
import org.cmms.modules.khgl.grkhgl.entity.CamsZcsxGrpjsxxx;
import org.cmms.modules.khgl.grkhgl.service.ICamsZcsxGrpjsxxxService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.*;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IXxnyztLyCjqkService;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IXxnyztLyService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.system.service.ISysUserService;
import org.cmms.modules.tjfx.jcsjgl.cssz.entity.TjfxCssz;
import org.cmms.modules.tjfx.jcsjgl.cssz.service.ITjfxCsszService;
import org.cmms.modules.util.WordUtils;
import org.cmms.modules.xdgl.nsb.entity.CamsNhpjsxNsb;
import org.cmms.modules.yxdygl.pqzrrgl.entity.Tjfxcssz;
import org.cmms.modules.yxdygl.yxdyglmain.entity.VYxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.service.IVYxdyglMainService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import sun.misc.BASE64Encoder;

/**
 * @Description: 新型农业主体浏阳
 * @Author: jeecg-boot
 * @Date: 2023-06-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "新型农业主体浏阳")
@RestController
@RequestMapping("/khxxgl/xxnyztLy")
public class XxnyztLyController extends JeecgController<XxnyztLy, IXxnyztLyService> {
    @Autowired
    private IXxnyztLyService xxnyztLyService;
    @Autowired
    IVYxdyglMainService ivYxdyglMainService;
    @Autowired
    ISysDictService sysDictService;
    @Autowired
    IXxnyztLyCjqkService xxnyztLyCjqkService;
    @Autowired
    IHrBasOrganizationService hrBasOrganizationService;
    @Autowired
    ICamsZcsxGrpjsxxxService camsZcsxGrpjsxxxService;
    @Autowired
    ITjfxCsszService tjfxCsszService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    ISysUserService sysUserService;

    /**
     * 分页列表查询
     *
     * @param xxnyztLy
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "新型农业主体浏阳-分页列表查询")
    @ApiOperation(value = "新型农业主体浏阳-分页列表查询", notes = "新型农业主体浏阳-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(XxnyztLy xxnyztLy,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        //每天提取一次 数据库出问题 先注释一下
        /*String yyyyMMdd = DateUtil.format(new Date(), "yyyyMMdd");
        Object o = redisUtil.get("xxnyzt" + yyyyMMdd);
        if (o == null){
            log.info("===新型农业主体存贷款提取开始===");
            try{
                service.tq();
            }catch (Exception e){
                e.printStackTrace();
            }
            log.info("===新型农业主体存贷款提取结束===");
            redisUtil.set("xxnyzt"+yyyyMMdd,1,60*60*24);
        }*/

        //没有修改人的就是没有被采集的
        String xgr = null;
        String cjr = null;
        String sped = null;
        String yjyzzhy = null;
        if (StringUtils.isNotBlank(xxnyztLy.getUpdatot())) {
            xgr = xxnyztLy.getUpdatot();
            xxnyztLy.setUpdatot(null);
        }

        if (StringUtils.isNotBlank(xxnyztLy.getCreator())) {
            cjr = xxnyztLy.getCreator();
            xxnyztLy.setCreator(null);
        }

        if (xxnyztLy.getSped() != null) {
            sped = xxnyztLy.getSped().toString();
            xxnyztLy.setSped(null);
        }

        String sszh = null;
        if (StringUtils.isNotBlank(xxnyztLy.getSszh())) {
            sszh = xxnyztLy.getSszh();
            xxnyztLy.setSszh(null);
        }

        if (StringUtils.isNotBlank(xxnyztLy.getYjyzzhy())) {
            yjyzzhy = xxnyztLy.getYjyzzhy();
            xxnyztLy.setYjyzzhy(null);
        }

        QueryWrapper<XxnyztLy> queryWrapper = QueryGenerator.initQueryWrapper(xxnyztLy, req.getParameterMap());
        //要求给金融普惠部的人查全行
        LambdaQueryWrapper<TjfxCssz> tbTjfxCsszLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tbTjfxCsszLambdaQueryWrapper.eq(TjfxCssz::getCsmc, "xxnyztly");
        List<TjfxCssz> list = tjfxCsszService.list(tbTjfxCsszLambdaQueryWrapper);
        boolean b = list.stream().anyMatch(x -> getWorkNo().equals(x.getCsz()));
        if (b) {
            if (StringUtils.isNotBlank(sszh)) {
                queryWrapper.eq("sszh", sszh);
            }
        } else {
            HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByZzbz(getLoginUser().getOrgCode());
            if (StringUtils.isNotBlank(hrBasOrganization.getSjzzbz()) && "1".equals(hrBasOrganization.getSjzzbz())) {
                queryWrapper.eq("sszh", getLoginUser().getOrgCode());
            } else {
                queryWrapper.eq("sszh", hrBasOrganization.getSjzzbz());
            }

            if ("khjl".equals(getRedisRoleCode())) {
                //queryWrapper.apply(" ( updatot  = '"+getWorkNo()+"'  or  updatot is null )");
                if (StringUtils.isNotBlank(cjr)) {
                    queryWrapper.eq("updatot", cjr);
                } else {
                    queryWrapper.apply(" ( updatot  = '" + getWorkNo() + "'  or  updatot is null )");

                }
            } else {
                if (StringUtils.isNotBlank(cjr)) {
                    queryWrapper.eq("updatot", cjr);
                }
            }
        }
        if ("1".equals(xgr)) {
            queryWrapper.isNotNull("updatot");
        } else if ("2".equals(xgr)) {
            queryWrapper.isNull("updatot");
        }

        if ("1".equals(sped)) {
            queryWrapper.isNotNull("sped");
        } else if ("2".equals(sped)) {
            queryWrapper.isNull("sped");
        }


        //if ()

//        if ("khjl".equalsIgnoreCase(getRedisRoleCode())) {
//            queryWrapper.eq("sszh", getLoginUser().getOrgCode());
//            if (StringUtils.isNotBlank(xgr)){
//                if ("1".equals(xgr)){
//                    queryWrapper.eq("updatot", getWorkNo());
//                }   else if ("2".equals(xgr)){
//                    queryWrapper.isNull("updatot");
//                }
//            }else {
//                queryWrapper.eq("updatot", getWorkNo()).or().isNull("updatot");
//            }
//        } else if ("ZHHZ".equalsIgnoreCase(getRedisRoleCode()) || "khjl".equalsIgnoreCase(getRedisRoleCode()) || "zhfhz".equalsIgnoreCase(getRedisRoleCode())) {
//            queryWrapper.eq("sszh", getLoginUser().getOrgCode());
//            if (StringUtils.isNotBlank(xgr)){
//                if ("1".equals(xgr)){
//                    queryWrapper.isNotNull("updatot");
//                }else if ("2".equals(xgr)){
//                    queryWrapper.isNull("updatot");
//                }
//            }
//        }
//        if ("1".equals(xgr)){
//            queryWrapper.isNotNull("updatot");
//        }else if ("2".equals(xgr)){
//            queryWrapper.isNull("updatot");
//        }
        queryWrapper.orderByDesc("update_time");
        if (StringUtils.isNotBlank(yjyzzhy)){
            if ("1".equals(yjyzzhy)){
                queryWrapper.apply("  id in ( select distinct ztid from XXNYZT_LY_CJQK where jylb = '4' ) ");
            }else if ("2".equals(yjyzzhy)){
                queryWrapper.apply("  id not in ( select distinct ztid from XXNYZT_LY_CJQK where jylb = '4' ) ");
            }
        }

        Page<XxnyztLy> page = new Page<XxnyztLy>(pageNo, pageSize);
        IPage<XxnyztLy> pageList = xxnyztLyService.page(page, queryWrapper);
        List<XxnyztLy> records = pageList.getRecords();
        for (int i = 0; i < records.size(); i++) {
            XxnyztLy xxnyztLy1 = records.get(i);

            LambdaQueryWrapper<XxnyztLyCjqk> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(XxnyztLyCjqk::getZtid, xxnyztLy1.getId());
            lambdaQueryWrapper.eq(XxnyztLyCjqk::getJylb, "4");
            XxnyztLyCjqk one = xxnyztLyCjqkService.getOne(lambdaQueryWrapper, false);
            if (one != null && StringUtils.isNotBlank(one.getZzpz())) {
                xxnyztLy1.setYjyzzhy(one.getZzpz());
            }

        }
        return Result.ok(pageList);
    }

    @RequestMapping(value = "/addbef")
    public Result<?> addbef(String shxydm, String ztmc) {
        if (StringUtils.isBlank(shxydm))
            return Result.error("社会信用代码不能为空");
        LambdaQueryWrapper<XxnyztLy> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(XxnyztLy::getShxydm, shxydm);
        long count = service.count(lambdaQueryWrapper);
        if (count > 0) {
            return Result.error("社会信用代码已存在!");
        }

        LambdaQueryWrapper<XxnyztLy> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper1.eq(XxnyztLy::getZtmc, ztmc);
        long count1 = service.count(lambdaQueryWrapper1);
        if (count1 > 0) {
            return Result.error("主体名称已存在！");
        }
        return Result.ok();
    }


    /**
     * 添加
     *
     * @param xxnyztLy
     * @return
     */
    @AutoLog(value = "新型农业主体浏阳-添加")
    @ApiOperation(value = "新型农业主体浏阳-添加", notes = "新型农业主体浏阳-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody XxnyztLy xxnyztLy) {
        if (StringUtils.isBlank(xxnyztLy.getShxydm())) {
            return Result.error("社会信用代码不能为空!");
        } else {
            LambdaQueryWrapper<XxnyztLy> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(XxnyztLy::getShxydm, xxnyztLy.getShxydm());
            long count = service.count(lambdaQueryWrapper);
            if (count > 0)
                return Result.error("社会信用代码已存在!");
        }

        if (StringUtils.isBlank(xxnyztLy.getZtmc())) {
            return Result.error("主体名称不能为空!");
        } else {
            LambdaQueryWrapper<XxnyztLy> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(XxnyztLy::getZtmc, xxnyztLy.getZtmc());
            long count = service.count(lambdaQueryWrapper);
            if (count > 0)
                return Result.error("主体名称已存在!");
        }


        xxnyztLy.setCreateTime(new Date());
        xxnyztLy.setCreator(getWorkNo());
        xxnyztLy.setUpdatot(getWorkNo());
        xxnyztLy.setUpdateTime(new Date());
        String fwlj = "/sign/xxnyzt/";
        String qz = "data:image/png;base64,";

        if (!FileUtil.isDirectory(uploadpath + fwlj)) {
            FileUtil.mkdir(uploadpath + fwlj);
        }
        //客户 客户经理 陪访人 签名  BASE64转图片  为什么转 因为数据库不好存BASE64
        if (StringUtils.isNotBlank(xxnyztLy.getKhqm())) {
            if (xxnyztLy.getKhqm().startsWith(qz)) {
                String fileName = IdUtil.fastSimpleUUID() + ".png";
                String wjlj = uploadpath + fwlj + fileName;
                Base64Util.toImage(xxnyztLy.getKhqm(), wjlj);
                xxnyztLy.setKhqm(fwlj + fileName);
            } else {
                xxnyztLy.setKhqm(null);
            }
        }
        if (StringUtils.isNotBlank(xxnyztLy.getKhjlqm())) {
            if (xxnyztLy.getKhjlqm().startsWith(qz)) {
                String fileName = IdUtil.fastSimpleUUID() + ".png";
                String wjlj = uploadpath + fwlj + fileName;
                Base64Util.toImage(xxnyztLy.getKhjlqm(), wjlj);
                xxnyztLy.setKhjlqm(fwlj + fileName);
            } else {
                xxnyztLy.setKhjlqm(null);
            }
        }
        if (StringUtils.isNotBlank(xxnyztLy.getPfrqm())) {
            if (xxnyztLy.getPfrqm().startsWith(qz)) {
                String fileName = IdUtil.fastSimpleUUID() + ".png";
                String wjlj = uploadpath + fwlj + fileName;
                Base64Util.toImage(xxnyztLy.getPfrqm(), wjlj);
                xxnyztLy.setPfrqm(fwlj + fileName);
            } else {
                xxnyztLy.setPfrqm(null);
            }
        }
        BigDecimal bigDecimal = new BigDecimal(0);
        if (bigDecimal.equals(xxnyztLy.getKhjlsxyx())) {
            xxnyztLy.setSped(bigDecimal);
        }
        if ("1".equals(xxnyztLy.getPdlx())) {
            xxnyztLy.setDdly("");
            xxnyztLy.setQtly("");
        }

        xxnyztLyService.save(xxnyztLy);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param xxnyztLy
     * @return
     */
    @AutoLog(value = "新型农业主体浏阳-编辑")
    @ApiOperation(value = "新型农业主体浏阳-编辑", notes = "新型农业主体浏阳-编辑")
    @PostMapping(value = "/edit")
    public Result<?> edit(@RequestBody XxnyztLy xxnyztLy) {
        System.out.println(xxnyztLy.toString());
        QueryWrapper<XxnyztLy> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", xxnyztLy.getId());
        XxnyztLy xxnyztLy1 = xxnyztLyService.getOne(queryWrapper);
        if (StringUtils.isBlank(xxnyztLy1.getUpdatot())) {
            xxnyztLy.setUpdatot(getWorkNo());
        }
        xxnyztLy.setUpdateTime(new Date());

        String fwlj = "/sign/xxnyzt/";
        String qz = "data:image/png;base64,";

        if (!FileUtil.isDirectory(uploadpath + fwlj)) {
            FileUtil.mkdir(uploadpath + fwlj);
        }
        //客户 客户经理 陪访人 签名  BASE64转图片  为什么转 因为数据库不好存BASE64
        if (StringUtils.isNotBlank(xxnyztLy.getKhqm())) {
            if (xxnyztLy.getKhqm().startsWith(qz)) {
                String fileName = IdUtil.fastSimpleUUID() + ".png";
                String wjlj = uploadpath + fwlj + fileName;
                Base64Util.toImage(xxnyztLy.getKhqm(), wjlj);
                xxnyztLy.setKhqm(fwlj + fileName);
            } else {
                xxnyztLy.setKhqm(null);
            }
        }
        if (StringUtils.isNotBlank(xxnyztLy.getKhjlqm())) {
            if (xxnyztLy.getKhjlqm().startsWith(qz)) {
                String fileName = IdUtil.fastSimpleUUID() + ".png";
                String wjlj = uploadpath + fwlj + fileName;
                Base64Util.toImage(xxnyztLy.getKhjlqm(), wjlj);
                xxnyztLy.setKhjlqm(fwlj + fileName);
            } else {
                xxnyztLy.setKhjlqm(null);
            }
        }
        if (StringUtils.isNotBlank(xxnyztLy.getPfrqm())) {
            if (xxnyztLy.getPfrqm().startsWith(qz)) {
                String fileName = IdUtil.fastSimpleUUID() + ".png";
                String wjlj = uploadpath + fwlj + fileName;
                Base64Util.toImage(xxnyztLy.getPfrqm(), wjlj);
                xxnyztLy.setPfrqm(fwlj + fileName);
            } else {
                xxnyztLy.setPfrqm(null);
            }
        }

        BigDecimal bigDecimal = new BigDecimal(0);
        if (bigDecimal.equals(xxnyztLy.getKhjlsxyx())) {
            xxnyztLy.setSped(bigDecimal);
        }

        if ("1".equals(xxnyztLy.getPdlx())) {
            xxnyztLy.setDdly("");
            xxnyztLy.setQtly("");
        }

        xxnyztLyService.updateById(xxnyztLy);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "新型农业主体浏阳-通过id删除")
    @ApiOperation(value = "新型农业主体浏阳-通过id删除", notes = "新型农业主体浏阳-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        xxnyztLyService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "新型农业主体浏阳-批量删除")
    @ApiOperation(value = "新型农业主体浏阳-批量删除", notes = "新型农业主体浏阳-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.xxnyztLyService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "新型农业主体浏阳-通过id查询")
    @ApiOperation(value = "新型农业主体浏阳-通过id查询", notes = "新型农业主体浏阳-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        XxnyztLy xxnyztLy = xxnyztLyService.getById(id);
        return Result.ok(xxnyztLy);
    }

    /**
     * 导出excel
     *
     * @param req
     * @param xxnyztLy
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest req, XxnyztLy xxnyztLy) {
        String xgr = null;
        String sped = null;
        if (StringUtils.isNotBlank(xxnyztLy.getUpdatot())) {
            xgr = xxnyztLy.getUpdatot();
            xxnyztLy.setUpdatot(null);
        }

        if (xxnyztLy.getSped() != null) {
            sped = xxnyztLy.getSped().toString();
            xxnyztLy.setSped(null);
        }

        String sszh = null;
        if (StringUtils.isNotBlank(xxnyztLy.getSszh())) {
            sszh = xxnyztLy.getSszh();
            xxnyztLy.setSszh(null);
        }

        QueryWrapper<XxnyztLy> queryWrapper = QueryGenerator.initQueryWrapper(xxnyztLy, req.getParameterMap());
        //要求给金融普惠部的人查全行
        LambdaQueryWrapper<TjfxCssz> tbTjfxCsszLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tbTjfxCsszLambdaQueryWrapper.eq(TjfxCssz::getCsmc, "xxnyztly");
        List<TjfxCssz> list = tjfxCsszService.list(tbTjfxCsszLambdaQueryWrapper);
        boolean b = list.stream().anyMatch(x -> getWorkNo().equals(x.getCsz()));
        if (b) {
            if (StringUtils.isNotBlank(sszh)) {
                queryWrapper.eq("sszh", sszh);
            }
        } else {
            HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByZzbz(getLoginUser().getOrgCode());
            if (StringUtils.isNotBlank(hrBasOrganization.getSjzzbz()) && "1".equals(hrBasOrganization.getSjzzbz())) {
                queryWrapper.eq("sszh", getLoginUser().getOrgCode());
            } else {
                queryWrapper.eq("sszh", hrBasOrganization.getSjzzbz());
            }

            if ("khjl".equals(getWorkNo())) {

                queryWrapper.apply(" ( updatot  = '" + getWorkNo() + "'  or  updatot is null )");
            }
        }
        if ("1".equals(xgr)) {
            queryWrapper.isNotNull("updatot");
        } else if ("2".equals(xgr)) {
            queryWrapper.isNull("updatot");
        }
        if ("1".equals(sped)) {
            queryWrapper.isNotNull("sped");
        } else if ("2".equals(sped)) {
            queryWrapper.isNull("sped");
        }
        queryWrapper.orderByDesc("update_time");

        return super.exportXls(req, xxnyztLy, XxnyztLy.class, "新型农业主体浏阳", null, queryWrapper);
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
        return super.importExcel(request, response, XxnyztLy.class);
    }

    @RequestMapping(value = "/importExcel2", method = RequestMethod.POST)
    public Result<?> importExcel2(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        int count = 0;
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            String absolutePath = file.getOriginalFilename();
            log.info("===当前导入文件{}===", absolutePath);
            if (!absolutePath.endsWith(".xlsx") && !absolutePath.endsWith(".xls")) {
                continue;
            }
            String path = uploadpath + File.separator + "del" + File.separator + file.getOriginalFilename();
            if (!FileUtil.exist(path))
                FileUtil.touch(path);
            try {
                file.transferTo(new File(path));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (FileUtil.isFile(path)) {
                File tempFile = new File(path);
                Workbook wb = null;
                boolean isExcel2003 = true;
                try {

                    if (file.getName().matches("^.+\\.(?i)(xlsx)$")) {
                        isExcel2003 = false;
                    }
                    if (isExcel2003) {
                        wb = new HSSFWorkbook(new FileInputStream(tempFile));
                    } else {
                        wb = new XSSFWorkbook(new FileInputStream(tempFile));
                    }


                } catch (Exception e) {
                    try {
                        if (isExcel2003) {
                            wb = new XSSFWorkbook(new FileInputStream(tempFile));
                        } else {
                            wb = new HSSFWorkbook(new FileInputStream(tempFile));
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                try {
                    Sheet sheet = wb.getSheetAt(0);
                    int lastRowNum = sheet.getLastRowNum();
                    log.info("===当前文件有{}行数据===", lastRowNum);
                    if (lastRowNum >= 2) {
                        LambdaQueryWrapper<HrBasOrganization> hrBasOrganizationLambdaQueryWrapper = new LambdaQueryWrapper<>();
                        hrBasOrganizationLambdaQueryWrapper.isNotNull(HrBasOrganization::getZzjc);
                        List<HrBasOrganization> hrBasOrganizations = hrBasOrganizationService.list(hrBasOrganizationLambdaQueryWrapper);
                        Map<String, String> orgMap =
                                hrBasOrganizations.stream().collect(Collectors.toMap(
                                        HrBasOrganization::getZzjc,
                                        HrBasOrganization::getZzbz,
                                        (key1, key2) -> key1  //（map的键重复不会报错，下面已经处理）
                                ));


                        Row bt = sheet.getRow(1);
                        Map<String, Integer> map = new HashMap<>();
                        short lastCellNum = bt.getLastCellNum();
                        for (int j = 0; j < lastCellNum; j++) {
                            if (bt.getCell(j) != null) {
                                bt.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                                String biaotou = bt.getCell(j).getStringCellValue().trim();
                                map.put(biaotou, j);
                            }
                        }
                        map.forEach((key, value) -> System.out.println(key + " = " + value));
                        for (int j = 2; j < lastRowNum; j++) {
                            try {
                                Row row = sheet.getRow(j);
                                XxnyztLy xxnyztLy = new XxnyztLy();

                                Integer shyydmxh = map.get("社会信用代码");
                                if (shyydmxh != null) {
                                    if (row.getCell(shyydmxh) != null) {
                                        row.getCell(shyydmxh).setCellType(Cell.CELL_TYPE_STRING);
                                        String zhmc = row.getCell(shyydmxh).getStringCellValue().trim();

                                        LambdaQueryWrapper<XxnyztLy> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                                        lambdaQueryWrapper.eq(XxnyztLy::getShxydm, zhmc);
                                        XxnyztLy one = service.getOne(lambdaQueryWrapper, false);
                                        if (one != null) {
                                            xxnyztLy = one;
                                        }
                                        xxnyztLy.setShxydm(zhmc);
                                    }
                                }

                                Integer sszhxh = map.get("所属支行");
                                if (sszhxh != null) {
                                    if (row.getCell(sszhxh) != null) {
                                        row.getCell(sszhxh).setCellType(Cell.CELL_TYPE_STRING);
                                        String zhmc = row.getCell(sszhxh).getStringCellValue().trim();

                                        String s = orgMap.get(zhmc);
                                        if (StringUtils.isNotBlank(s)) {
                                            xxnyztLy.setSszh(s);
                                        }

                                    }
                                }
                                Integer ztmcxh = map.get("经营主体名称");
                                if (ztmcxh != null) {
                                    if (row.getCell(ztmcxh) != null) {
                                        row.getCell(ztmcxh).setCellType(Cell.CELL_TYPE_STRING);
                                        String zhmc = row.getCell(ztmcxh).getStringCellValue().trim();
                                        xxnyztLy.setZtmc(zhmc);
                                    }
                                }
                                Integer ztlbxh = map.get("主体类别");
                                if (ztlbxh != null) {
                                    if (row.getCell(ztlbxh) != null) {
                                        row.getCell(ztlbxh).setCellType(Cell.CELL_TYPE_STRING);
                                        String zhmc = row.getCell(ztlbxh).getStringCellValue().trim();

                                        String ly_xxnyzt_ztlb = sysDictService.queryDictValueByKey("ly_xxnyzt_ztlb", zhmc);
                                        if (StringUtils.isNotBlank(ly_xxnyzt_ztlb)) {
                                            xxnyztLy.setZtlb(ly_xxnyzt_ztlb);
                                        }
                                    }
                                }
                                Integer jydzxh = map.get("经营地址");
                                if (jydzxh != null) {
                                    if (row.getCell(jydzxh) != null) {
                                        row.getCell(jydzxh).setCellType(Cell.CELL_TYPE_STRING);
                                        String zhmc = row.getCell(jydzxh).getStringCellValue().trim();
                                        xxnyztLy.setJydz(zhmc);
                                    }
                                }

                                Integer fzrmcxh = map.get("负责人名称");
                                if (fzrmcxh != null) {
                                    if (row.getCell(fzrmcxh) != null) {
                                        row.getCell(fzrmcxh).setCellType(Cell.CELL_TYPE_STRING);
                                        String zhmc = row.getCell(fzrmcxh).getStringCellValue().trim();
                                        xxnyztLy.setKhmc(zhmc);
                                    }
                                }

                                Integer zjhmxh = map.get("身份证号码");
                                if (zjhmxh != null) {
                                    if (row.getCell(zjhmxh) != null) {
                                        row.getCell(zjhmxh).setCellType(Cell.CELL_TYPE_STRING);
                                        String zhmc = row.getCell(zjhmxh).getStringCellValue().trim();
                                        xxnyztLy.setZjhm(zhmc);
                                    }
                                }

                                Integer lxfsxh = map.get("联系方式");
                                if (lxfsxh != null) {
                                    if (row.getCell(lxfsxh) != null) {
                                        row.getCell(lxfsxh).setCellType(Cell.CELL_TYPE_STRING);
                                        String zhmc = row.getCell(lxfsxh).getStringCellValue().trim();
                                        xxnyztLy.setSjhm(zhmc);
                                    }
                                }

                                Integer rdqkxh = map.get("认定情况");
                                if (rdqkxh != null) {
                                    if (row.getCell(rdqkxh) != null) {
                                        row.getCell(rdqkxh).setCellType(Cell.CELL_TYPE_STRING);
                                        String zhmc = row.getCell(rdqkxh).getStringCellValue().trim();

                                        String ly_xxnyzt_ztlb = sysDictService.queryDictValueByKey("ly_xxnyzt_rdqk", zhmc);
                                        if (StringUtils.isNotBlank(ly_xxnyzt_ztlb)) {
                                            xxnyztLy.setRdqk(ly_xxnyzt_ztlb);
                                        }
                                    }
                                }

                                xxnyztLy.setCreator(getWorkNo());
                                xxnyztLy.setCreateTime(new Date());
                                if (StringUtils.isNotBlank(xxnyztLy.getId())) {
                                    log.info("===更新新型农业主体{}===", xxnyztLy.toString());
                                    boolean b = service.updateById(xxnyztLy);
                                    if (b)
                                        count++;
                                } else {
                                    log.info("===保存新型农业主体{}===", xxnyztLy.toString());
                                    boolean save = service.save(xxnyztLy);
                                    if (save)
                                        count++;
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    } else {
                        log.info("===表的行数过少，不导入数据===");
                    }
                    wb.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

        }
        return Result.ok("文件导入成功！数据行数：" + count);
    }

    @RequestMapping("/xxnyztlyFtl")
    public Result<?> download(String id) {
        Map<String, Object> data = new HashMap<>();
        XxnyztLy obj = service.getById(id);
        String name = "";
        data.put("zhen", "      ");
        data.put("cun", "      ");
        data.put("zu", "      ");
        if (obj != null) {
            if (StringUtils.isNotBlank(obj.getZtmc())) {
                name = obj.getZtmc();
            }

            if (obj.getUpdateTime() != null) {
                String format = DateUtil.format(obj.getUpdateTime(), "yyyy-MM-dd");
                data.put("lrsj", format);
            }
            if (StringUtils.isNotBlank(obj.getZtlb())) {
                String dxkcl = dxkcl(obj.getZtlb(), "ly_xxnyzt_ztlb");
                obj.setZtlb(dxkcl);
            }
            if (StringUtils.isNotBlank(obj.getRdqk())) {
                String dxkcl = dxkcl(obj.getRdqk(), "ly_xxnyzt_rdqk");
                obj.setRdqk(dxkcl);
            }
            if (StringUtils.isNotBlank(obj.getJxzt())) {
                String dxkcl = dxkcl(obj.getJxzt(), "ly_xxnyzt_jyzt");
                obj.setJxzt(dxkcl);
            }
            if (StringUtils.isNotBlank(obj.getJyydgy())) {
                String dxkcl = dxkcl(obj.getJyydgy(), "ly_xxnyzt_jyydgy");
                obj.setJyydgy(dxkcl);
            }
            if (StringUtils.isNotBlank(obj.getJyhy())) {
                String dxkcl = dxkcl(obj.getJyhy(), "ly_xxnyzt_jyhy");
                obj.setJyhy(dxkcl);
            }
            if (StringUtils.isNotBlank(obj.getHypj())) {
                String dxkcl = dxkcl(obj.getHypj(), "ly_xxnyzt_hypj");
                obj.setHypj(dxkcl);
            }
            if (StringUtils.isNotBlank(obj.getKhmc())) {
                data.put("khmc", obj.getKhmc());
            } else {
                data.put("khmc", "");
            }

            if (StringUtils.isNotBlank(obj.getHyzk())) {
                String hyzk = sysDictService.queryDictTextByKey("sfbz", obj.getHyzk());
                if (StringUtils.isNotBlank(hyzk)) {
                    data.put("hy", hyzk);
                } else {
                    data.put("hy", "");
                }
            } else {
                data.put("hy", "");
            }

            if (StringUtils.isNotBlank(obj.getHyzk())) {
                String hyzk = sysDictService.queryDictTextByKey("sfbz", obj.getHyzk());
                if (StringUtils.isNotBlank(hyzk)) {
                    data.put("hy", hyzk);
                } else {
                    data.put("hy", "");
                }
            } else {
                data.put("hy", "");
            }

            if (StringUtils.isNotBlank(obj.getDbfs())) {
                String dbfs = obj.getDbfs();
                String[] split = dbfs.split(",");
                String s = "";
                for (int i = 0; i < split.length; i++) {
                    String db = sysDictService.queryDictTextByKey("dbfs", split[i]);
                    if (i == split.length - 1) {
                        s += db;
                    } else {
                        s += db + ",";
                    }
                }
                obj.setDbfs(s);
            }
            data.put("xend", "");
            data.put("pddj", "");
            data.put("sxje", "");
            if (StringUtils.isNotBlank(obj.getZjhm())) {
                LambdaQueryWrapper<CamsZcsxGrpjsxxx> grpjsxxxLambdaQueryWrapper = new LambdaQueryWrapper<>();
                grpjsxxxLambdaQueryWrapper.eq(CamsZcsxGrpjsxxx::getZjhm, obj.getZjhm());
                CamsZcsxGrpjsxxx one1 = camsZcsxGrpjsxxxService.getOne(grpjsxxxLambdaQueryWrapper, false);
                if (one1 != null) {
                    if (StringUtils.isNotBlank(one1.getZzpddj())) {
                        data.put("pddj", one1.getZzpddj());
                    }
                    if (one1.getZzsxed() != null)
                        data.put("sxje", one1.getZzsxed());
                    data.put("xend", "是");
                }

            }

            if (StringUtils.isNotBlank(obj.getSjhm())) {
                data.put("sjhm", obj.getSjhm());
            } else {
                data.put("sjhm", "");
            }

            if (obj.getLztdmj() != null) {
                data.put("lzmj", obj.getLztdmj());
            } else {
                data.put("lzmj", "");
            }

            if (obj.getQtldzc() != null || obj.getQtgdzc() != null) {
                BigDecimal bigDecimal = new BigDecimal("0");
                if (obj.getQtldzc() != null) {
                    bigDecimal = bigDecimal.add(obj.getQtldzc());
                }
                if (obj.getQtgdzc() != null) {
                    bigDecimal = bigDecimal.add(obj.getQtgdzc());
                }
                obj.setQtldzc(bigDecimal);
            }

            if (obj.getThjk() != null || obj.getQtjk() != null) {
                BigDecimal bigDecimal = new BigDecimal("0");
                if (obj.getThjk() != null)
                    bigDecimal = bigDecimal.add(obj.getThjk());
                if (obj.getQtjk() != null)
                    bigDecimal = bigDecimal.add(obj.getQtjk());

                obj.setThjk(bigDecimal);

            }

            if (obj.getLrze() != null) {
                data.put("lrze", obj.getLrze());
            }

            if (obj.getLztdqxq() != null && obj.getLztdqxz() != null) {
                String s = DateUtil.format(obj.getLztdqxq(), "yyyy年MM月");
                String s2 = DateUtil.format(obj.getLztdqxz(), "yyyy年MM月");
                data.put("tdqx", s + "至" + s2);
            } else {
                data.put("tdqx", "    年    月至    年   月");
            }
            data.put("a1", "");
            data.put("a2", "");
            data.put("a3", "");
            data.put("b1", "");
            data.put("b2", "");
            data.put("b3", "");
            data.put("c1", "");
            data.put("c2", "");
            data.put("c3", "");
            data.put("d1", "");
            data.put("d2", "");
            data.put("d3", "");
            data.put("e1", "");
            data.put("e2", "");
            data.put("e3", "");
            data.put("f1", "");
            data.put("f2", "");
            data.put("f3", "");
            data.put("g1", "");
            data.put("g2", "");
            data.put("g3", "");
            data.put("h1", "");
            data.put("h2", "");
            data.put("h3", "");
            data.put("i1", "");
            data.put("i2", "");
            data.put("i3", "");
            data.put("j1", "");
            data.put("j2", "");
            data.put("j3", "");
            data.put("k1", "");
            data.put("k2", "");
            data.put("k3", "");
            data.put("o1", "");
            data.put("o2", "");
            data.put("o3", "");
            data.put("xj", "");
            if (obj.getXjjck() != null) {
                data.put("xj", obj.getXjjck());
            }
            data.put("yszk", "");
            if (obj.getYszk() != null) {
                data.put("yszk", obj.getYszk());
            }
            data.put("qtldzc", "");
            if (obj.getQtldzc() != null) {
                data.put("qtldzc", obj.getQtldzc());
            }
            data.put("zchj", "");
            if (obj.getZchj() != null) {
                data.put("zchj", obj.getZchj());
            }
            data.put("bhjk", "");
            if (obj.getBhjk() != null) {
                data.put("bhjk", obj.getBhjk());
            }
            data.put("thjk", "");
            if (obj.getThjk() != null) {
                data.put("thjk", obj.getThjk());
            }
            data.put("qtjk", "");
            if (obj.getQtjk() != null) {
                data.put("qtjk", obj.getQtjk());
            }
            data.put("yfzk", "");
            if (obj.getYfzk() != null) {
                data.put("yfzk", obj.getYfzk());
            }
            data.put("qtfz", "");
            if (obj.getQtfz() != null) {
                data.put("qtfz", obj.getQtfz());
            }
            data.put("wtrdb", "");
            if (obj.getWtrdb() != null) {
                data.put("wtrdb", obj.getWtrdb());
            }
            data.put("fzhj", "");
            if (obj.getFzhj() != null) {
                data.put("fzhj", obj.getFzhj());
            }
            data.put("ywwfx", "");
            if (StringUtils.isNotBlank(obj.getYwwfxw())) {
                if ("1".equals(obj.getYwwfxw())) {
                    data.put("ywwfx", "有");
                }
                if ("2".equals(obj.getYwwfxw())) {
                    data.put("ywwfx", "无");
                }
            }
            data.put("xq", "");
            if (obj.getKhrzxq() != null) {
                data.put("xq", obj.getKhrzxq());
            }
            data.put("khjlsxyx", "");
            if (obj.getKhjlsxyx() != null) {
                data.put("khjlsxyx", obj.getKhjlsxyx());
            }

            if (obj.getSped() != null) {
                if (obj.getSped().compareTo(new BigDecimal("0")) > 0){
                    data.put("zhyj","同意授信"+obj.getSped()+"万元");
                }else {
                    data.put("zhyj","不同意");
                }
            }



            LambdaQueryWrapper<XxnyztLyCjqk> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(XxnyztLyCjqk::getZtid, id);
            lambdaQueryWrapper.in(XxnyztLyCjqk::getJylb, "1", "2", "3");
            lambdaQueryWrapper.orderByAsc(XxnyztLyCjqk::getJylb);
            List<XxnyztLyCjqk> list = xxnyztLyCjqkService.list(lambdaQueryWrapper);
            if (CollUtil.isNotEmpty(list)) {
                int a = 1;
                int b = 1;
                int c = 1;
                BigDecimal hjjz = new BigDecimal("0");
                BigDecimal hjjz2 = new BigDecimal("0");
                for (int i = 0; i < list.size(); i++) {
                    XxnyztLyCjqk xxnyztLyCjqk = list.get(i);
                    if (StringUtils.isNotBlank(xxnyztLyCjqk.getJylb())) {
                        if ("1".equals(xxnyztLyCjqk.getJylb())) {
                            if (a > 3)
                                continue;
                            if (StringUtils.isNotBlank(xxnyztLyCjqk.getZzpz())) {
                                data.put("a" + a, xxnyztLyCjqk.getZzpz());
                            }
                            if (xxnyztLyCjqk.getZzmj() != null) {
                                data.put("b" + a, xxnyztLyCjqk.getZzmj().toString());
                            }
                            if (StringUtils.isNotBlank(xxnyztLyCjqk.getYzpz())) {
                                data.put("c" + a, xxnyztLyCjqk.getYzpz().toString());
                            }
                            if (xxnyztLyCjqk.getYzsl() != null) {
                                if (xxnyztLyCjqk.getYzsl().compareTo(new BigDecimal("1")) == 0) {
                                    data.put("d" + a, "100以下");
                                }
                                if (xxnyztLyCjqk.getYzsl().compareTo(new BigDecimal("2")) == 0) {
                                    data.put("d" + a, "100-500");
                                }
                                if (xxnyztLyCjqk.getYzsl().compareTo(new BigDecimal("3")) == 0) {
                                    data.put("d" + a, "500-1000");
                                }
                                if (xxnyztLyCjqk.getYzsl().compareTo(new BigDecimal("4")) == 0) {
                                    data.put("d" + a, "1000-5000");
                                }
                                if (xxnyztLyCjqk.getYzsl().compareTo(new BigDecimal("5")) == 0) {
                                    data.put("d" + a, "5000以上");
                                }
                            }
                            if (StringUtils.isNotBlank(xxnyztLyCjqk.getJgcppz())) {
                                data.put("e" + a, xxnyztLyCjqk.getJgcppz());
                            }
                            if (xxnyztLyCjqk.getJgcz() != null) {
                                data.put("f" + a, xxnyztLyCjqk.getJgcz());
                            }

                            a++;
                        } else if ("2".equals(xxnyztLyCjqk.getJylb())) {
                            if (b > 3)
                                continue;
                            if (StringUtils.isNotBlank(xxnyztLyCjqk.getZzpz())) {
                                data.put("g" + b, xxnyztLyCjqk.getZzpz());
                            }
                            if (xxnyztLyCjqk.getZzmj() != null) {
                                data.put("h" + b, xxnyztLyCjqk.getZzmj());
                            }
                            if (xxnyztLyCjqk.getJgcz() != null) {
                                data.put("i" + b, xxnyztLyCjqk.getJgcz());
                                hjjz = hjjz.add(xxnyztLyCjqk.getJgcz());
                            }
                            b++;
                        } else if ("3".equals(xxnyztLyCjqk.getJylb())) {
                            if (c > 3)
                                continue;
                            if (StringUtils.isNotBlank(xxnyztLyCjqk.getZzpz())) {
                                data.put("j" + c, xxnyztLyCjqk.getZzpz());
                            }
                            if (xxnyztLyCjqk.getZzmj() != null) {
                                data.put("k" + c, xxnyztLyCjqk.getZzmj());
                            }
                            if (xxnyztLyCjqk.getJgcz() != null) {
                                data.put("o" + c, xxnyztLyCjqk.getJgcz());
                                hjjz2 = hjjz2.add(xxnyztLyCjqk.getJgcz());
                            }
                            c++;
                        }
                    }
                }
                if (hjjz.compareTo(new BigDecimal("0")) > 0)
                    data.put("hjjz", hjjz);
                if (hjjz2.compareTo(new BigDecimal("0")) > 0)
                    data.put("hjjz2", hjjz2);
            }

            if (StringUtils.isNotBlank(obj.getKhqm())) {
                String imageStr = getImageStr(uploadpath + obj.getKhqm());
                obj.setKhqm(imageStr);
                data.put("png1", "1");
            }
            if (StringUtils.isNotBlank(obj.getKhjlqm())) {
                String imageStr = getImageStr(uploadpath + obj.getKhjlqm());
                obj.setKhjlqm(imageStr);
                data.put("png2", "2");
            }
        }
        data.put("dao", obj);
        try {
            String fileName = name + "的主体信息采集表.docx";
            String path = uploadpath + File.separator + "xxnyzt" + File.separator + fileName;
            String resourceName = "xxnyztlt.ftl";
            WordUtils.generateWord(data, path, resourceName);
            service.updateSxbscsj(id);
            return Result.ok(File.separator + "xxnyzt" + File.separator + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        return Result.error("");
    }

    @RequestMapping("/batchXxnyztlyFtl")
    public Result<?> download2(XxnyztLy xxnyztLy, HttpServletRequest req) {
        String delpath = uploadpath + File.separator + "xxnyzt" + File.separator + getWorkNo();
        FileUtil.del(delpath);

        String xgr = null;
        String cjr = null;
        if (StringUtils.isNotBlank(xxnyztLy.getUpdatot())) {
            xgr = xxnyztLy.getUpdatot();
            xxnyztLy.setUpdatot(null);
        }

        if (StringUtils.isNotBlank(xxnyztLy.getCreator())) {
            cjr = xxnyztLy.getCreator();
            xxnyztLy.setCreator(null);
        }

        String sszh = null;
        if (StringUtils.isNotBlank(xxnyztLy.getSszh())) {
            sszh = xxnyztLy.getSszh();
            xxnyztLy.setSszh(null);
        }

        QueryWrapper<XxnyztLy> queryWrapper = QueryGenerator.initQueryWrapper(xxnyztLy, req.getParameterMap());
        //浏阳角色混乱 这里要求给金融普惠部的人查全行
        LambdaQueryWrapper<TjfxCssz> tbTjfxCsszLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tbTjfxCsszLambdaQueryWrapper.eq(TjfxCssz::getCsmc, "xxnyztly");
        List<TjfxCssz> list = tjfxCsszService.list(tbTjfxCsszLambdaQueryWrapper);
        boolean b = list.stream().anyMatch(x -> getWorkNo().equals(x.getCsz()));
        if (b) {
            if (StringUtils.isNotBlank(sszh)) {
                queryWrapper.eq("sszh", sszh);
            }
        } else {
            HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByZzbz(getLoginUser().getOrgCode());
            if (StringUtils.isNotBlank(hrBasOrganization.getSjzzbz()) && "1".equals(hrBasOrganization.getSjzzbz())) {
                queryWrapper.eq("sszh", getLoginUser().getOrgCode());
            } else {
                queryWrapper.eq("sszh", hrBasOrganization.getSjzzbz());
            }

            if ("khjl".equals(getRedisRoleCode())) {
                //queryWrapper.apply(" ( updatot  = '"+getWorkNo()+"'  or  updatot is null )");
                if (StringUtils.isNotBlank(cjr)) {
                    queryWrapper.eq("updatot", cjr);
                } else {
                    queryWrapper.apply(" ( updatot  = '" + getWorkNo() + "'  or  updatot is null )");

                }
            } else {
                if (StringUtils.isNotBlank(cjr)) {
                    queryWrapper.eq("updatot", cjr);
                }
            }
        }
        if ("1".equals(xgr)) {
            queryWrapper.isNotNull("updatot");
        } else if ("2".equals(xgr)) {
            queryWrapper.isNull("updatot");
        }

        List<XxnyztLy> batList = service.list(queryWrapper);
        List<String> files = new ArrayList<>();
        // 1 先判断文件是否存在  不存在生成文件
        for (int i = 0; i < batList.size(); i++) {
            XxnyztLy dao = batList.get(i);

            String fileName = dao.getZtmc() + "的主体信息采集表.docx";
            String path = uploadpath + File.separator + "xxnyzt" + File.separator + fileName;

            if (dao.getSxbscsj() != null) {
                if (dao.getUpdateTime() != null) {
                    if (dao.getSxbscsj().compareTo(dao.getUpdateTime()) > 0) {
                        download(dao.getId());
                    } else {
                        if (!FileUtil.isFile(path)) {
                            download(dao.getId());
                        }
                    }
                }
            }else {
                download(dao.getId());
            }
            FileUtil.copy(path, uploadpath + File.separator + "xxnyzt" + File.separator + getWorkNo() + File.separator + fileName, true);
        }

        ZipUtil.zip(uploadpath + File.separator + "xxnyzt" + File.separator + getWorkNo());
        return Result.ok(File.separator + "xxnyzt" + File.separator + getWorkNo() + ".zip");
    }

    //多选框处理
    public String dxkcl(String val, String type) {
        List<DictModel> dictModels = sysDictService.queryDictItemsByCode(type);
        if (CollUtil.isNotEmpty(dictModels)) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < dictModels.size(); i++) {
                DictModel dictModel = dictModels.get(i);
                if (val.equals(dictModel.getValue())) {
                    stringBuffer.append(" ").append("☑").append(dictModel.getText());
                } else {
                    stringBuffer.append(" ").append("□").append(dictModel.getText());
                }
            }
            return stringBuffer.toString();
        }

        return "";
    }


    public static String getImageStr(String imgFile) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }


    @RequestMapping("/tjfx")
    public Result<?> tjfx(LyXxnyztTjfxVO lyXxnyztTjfxVO, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        Page<LyXxnyztTjfxVO> page = new Page<LyXxnyztTjfxVO>(pageNo, pageSize);
        String sszh = null;
        String cxlx = null;
        if (StringUtils.isNotBlank(lyXxnyztTjfxVO.getCxlx())) {
            cxlx = lyXxnyztTjfxVO.getCxlx();
        }
        LambdaQueryWrapper<TjfxCssz> tbTjfxCsszLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tbTjfxCsszLambdaQueryWrapper.eq(TjfxCssz::getCsmc, "xxnyztly");
        List<TjfxCssz> list = tjfxCsszService.list(tbTjfxCsszLambdaQueryWrapper);
        boolean b = list.stream().anyMatch(x -> getWorkNo().equals(x.getCsz()));
        if (b) {
            if (StringUtils.isNotBlank(lyXxnyztTjfxVO.getSszh())) {
                sszh = lyXxnyztTjfxVO.getSszh();
            }
        } else {
            sszh = getLoginUser().getOrgCode();
        }
        IPage<LyXxnyztTjfxVO> tjfx = service.tjfx(page, sszh, cxlx);
        return Result.ok(tjfx);
    }

    @RequestMapping("/tjfxexp")
    public Result<?> tjfxexp(LyXxnyztTjfxVO lyXxnyztTjfxVO
    ) {
        String sszh = null;
        String cxlx = null;
        if (StringUtils.isNotBlank(lyXxnyztTjfxVO.getCxlx())) {
            cxlx = lyXxnyztTjfxVO.getCxlx();
        }
        LambdaQueryWrapper<TjfxCssz> tbTjfxCsszLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tbTjfxCsszLambdaQueryWrapper.eq(TjfxCssz::getCsmc, "xxnyztly");
        List<TjfxCssz> list = tjfxCsszService.list(tbTjfxCsszLambdaQueryWrapper);
        boolean b = list.stream().anyMatch(x -> getWorkNo().equals(x.getCsz()));
        if (b) {
            if (StringUtils.isNotBlank(lyXxnyztTjfxVO.getSszh())) {
                sszh = lyXxnyztTjfxVO.getSszh();
            }
        } else {
            sszh = getLoginUser().getOrgCode();
        }
        List<String> rowList = null;
        rowList = Lists.newArrayList(
                "所属支行", "客户经理", "管户数", "走访数", "参与农贷评级负责人数",
                "负责人已授信额(万)", "评级数", "待定数", "客户经理授信意向(万)", "审批金额(万)", "授信户数", "走访率", "建档评级率", "授信率"
        );

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("新型农业主体统计分析");
        SimpleStandardTable simpleStandardTable = new SimpleStandardTable(workbook, sheet);
        simpleStandardTable.setTableName("新型农业主体统计分析", rowList.size());
        simpleStandardTable.setTableHeader(rowList);


        List<LyXxnyztTjfxVO> lyXxnyztTjfxVOS = service.tjfxList(sszh, cxlx);
        if (CollUtil.isNotEmpty(lyXxnyztTjfxVOS)) {
            List<List<String>> tjfx2List = getTjfx2List(lyXxnyztTjfxVOS);
            simpleStandardTable.setTableData(tjfx2List);
        }
        try {
            String fileName = "新型农业主体统计分析" + System.currentTimeMillis() + ".xls";
            String file = uploadpath + File.separator + fileName;
            OutputStream out = new FileOutputStream(file);
            workbook.write(out);
            workbook.close();
            out.flush();
            out.close();
            return Result.ok(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    public List<List<String>> getTjfx2List(List<LyXxnyztTjfxVO> list) {
        List<List<String>> listArrayList = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            LyXxnyztTjfxVO entity = list.get(i);
            List<String> colList = Lists.newArrayList();

            if (StringUtils.isNotBlank(entity.getSszh())) {
                String s = sysDictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION", "zzjc", "zzbz", entity.getSszh());
                colList.add(s);
            } else {
                colList.add("");
            }

            colList.add(entity.getUpdatot() + "");
            colList.add(entity.getGhs() + "");
            colList.add(entity.getZfs() + "");
            colList.add(entity.getXends().toString());
            colList.add(entity.getFzrysxs() + "");

            colList.add(entity.getPjs() + "");
            colList.add(entity.getDds() + "");

            colList.add(entity.getKhjlsxyx().toString());
            colList.add(entity.getSped().toString());
            colList.add(entity.getSps() + "");
            colList.add(entity.getZfl() + "");
            colList.add(entity.getJdpjl() + "");
            colList.add(entity.getSpl() + "");
            listArrayList.add(colList);
        }
        return listArrayList;
    }

    @RequestMapping("/tjfx2")
    public Result<?> tjfx(LyXxnyztTjfxJCBVO jcbvovo, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        Page<LyXxnyztTjfxJCBVO> page = new Page<LyXxnyztTjfxJCBVO>(pageNo, pageSize);
        return Result.ok(service.tjfx2(page, jcbvovo));
    }


    @RequestMapping("/drmb")
    public Result<?> drmb(String id, HttpServletRequest request, HttpServletResponse response) {
        List<String> rowList = null;
        rowList = Lists.newArrayList(
                "所属支行", "经营主体名称", "主体类别", "经营地址", "社会信用代码",
                "负责人名称", "身份证号码", "联系方式", "认定情况"
        );

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("新型农业主体导入模版");
        SimpleStandardTable simpleStandardTable = new SimpleStandardTable(workbook, sheet);
        simpleStandardTable.setTableName("新型农业主体导入模版", rowList.size());
        simpleStandardTable.setTableHeader(rowList);

        try {
            String fileName = "新型农业主体导入模版" + System.currentTimeMillis() + ".xls";
            String file = uploadpath + File.separator + fileName;
            //workbookWrite(request, response, "浏阳农商行年审表", workbook);
            OutputStream out = new FileOutputStream(file);
            workbook.write(out);
            workbook.close();
            out.flush();
            out.close();
            return Result.ok(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }

    }

    @RequestMapping("/tq")
    public Result<?> tq() {
        try {
            service.tq();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok();
    }


    @RequestMapping("/khjl")
    public Result<?> khjl() {
        //浏阳角色混乱 这里要求给金融普惠部的人查全行
        LambdaQueryWrapper<TjfxCssz> tbTjfxCsszLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tbTjfxCsszLambdaQueryWrapper.eq(TjfxCssz::getCsmc, "xxnyztly");
        List<TjfxCssz> list = tjfxCsszService.list(tbTjfxCsszLambdaQueryWrapper);
        boolean b = list.stream().anyMatch(x -> getWorkNo().equals(x.getCsz()));
        if (b) {
            LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            List<SysUser> sysUsers = sysUserService.list(lambdaQueryWrapper);
            return Result.ok(sysUsers);
        } else {
            LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(SysUser::getOrgCode, getLoginUser().getOrgCode());
            List<SysUser> sysUsers = sysUserService.list(lambdaQueryWrapper);
            return Result.ok(sysUsers);
        }
    }

    @RequestMapping("/tjfxexp2")
    public Result<?> tjfxexp2(LyXxnyztTjfxJCBVO lyXxnyztTjfxJCBVO
    ) {
        List<LyXxnyztTjfxJCBVO> lyXxnyztTjfxJCBVOS = service.tjfx2List(lyXxnyztTjfxJCBVO);

        List<String> rowList = null;
        rowList = Lists.newArrayList(
                "主体类别", "辖区内主体户数"
                ,"有合理信贷需求主体户数","信用建档评级户数","信用建档评级占比"
                ,"授信户主","授信占比","授信额度","贷款余额","其中：信用贷款余额","贷款户数","其中：首贷户","信用贷款户数","当年累计贷款金额"
                ,"其中：当年累计放信用贷款金额","其他需要说明事项"
        );

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("建档评级跟踪检测表");
        SimpleStandardTable simpleStandardTable = new SimpleStandardTable(workbook, sheet);
        simpleStandardTable.setTableName("建档评级跟踪检测表", rowList.size());
        simpleStandardTable.setTableHeader(rowList);


        if (CollUtil.isNotEmpty(lyXxnyztTjfxJCBVOS)) {
            List<List<String>> tjfx2List = tjfxexp2List(lyXxnyztTjfxJCBVOS);
            simpleStandardTable.setTableData(tjfx2List);
        }
        try {
            String fileName = "建档评级跟踪检测表" + System.currentTimeMillis() + ".xls";
            String file = uploadpath + File.separator + fileName;
            OutputStream out = new FileOutputStream(file);
            workbook.write(out);
            workbook.close();
            out.flush();
            out.close();
            return Result.ok(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    public List<List<String>> tjfxexp2List(List<LyXxnyztTjfxJCBVO> list) {
        List<List<String>> listArrayList = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            LyXxnyztTjfxJCBVO entity = list.get(i);
            List<String> colList = Lists.newArrayList();

            if (StringUtils.isNotBlank(entity.getZtlb())) {
                String s = sysDictService.queryDictTextByKey("ly_xxnyzt_ztlb",entity.getZtlb());
                colList.add(s);
            } else {
                colList.add("");
            }

            colList.add(entity.getHs() + "");
            colList.add(entity.getXqhs() + "");
            colList.add(entity.getJdhs() + "");
            colList.add(entity.getJdhszb());
            colList.add(entity.getSxhs() + "");
            colList.add(entity.getSxhszb() + "");

            colList.add(entity.getSxed() + "");
            colList.add(entity.getDkye() + "");

            colList.add(entity.getXydkye() + "");
            colList.add(entity.getDkhs() + "");
            colList.add(entity.getSdh() + "");
            colList.add(entity.getXydkhs() + "");
            colList.add(entity.getDnljdkje() + "");
            colList.add(entity.getDnljxydkje() + "");
            colList.add("");
            listArrayList.add(colList);
        }
        return listArrayList;
    }
}
