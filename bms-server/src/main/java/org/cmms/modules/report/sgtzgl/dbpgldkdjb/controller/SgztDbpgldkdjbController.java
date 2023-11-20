package org.cmms.modules.report.sgtzgl.dbpgldkdjb.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.checkerframework.checker.units.qual.A;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.cwbbzcfzbWzrmb.service.ISgtzCwxyszcfzbWzrmbService;
import org.cmms.modules.report.sgtzgl.dbpgldkdjb.entity.SgztDbpgldkdjb;
import org.cmms.modules.report.sgtzgl.dbpgldkdjb.entity.SgztDbpgldkdjbVO;
import org.cmms.modules.report.sgtzgl.dbpgldkdjb.service.ISgztDbpgldkdjbService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.dbpgldkdjb.verify.DbpgldkdjbImportVerify;
import org.cmms.modules.report.sgtzgl.dkqmx.entity.SgtzglDkqmx;
import org.cmms.modules.report.tzsjgl.xtbhdjb.entity.XtBhdjb;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 担保品关联贷款登记簿
 * @Author: jeecg-boot
 * @Date: 2022-08-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "担保品关联贷款登记簿")
@RestController
@RequestMapping("/dbpgldkdjb/sgztDbpgldkdjb")
public class SgztDbpgldkdjbController extends JeecgController<SgztDbpgldkdjb, ISgztDbpgldkdjbService> {
    @Autowired
    private ISgztDbpgldkdjbService sgztDbpgldkdjbService;
    @Autowired
    private DbpgldkdjbImportVerify dbpgldkdjbImportVerify;

    /**
     * 分页列表查询
     *
     * @param sgztDbpgldkdjb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "担保品关联贷款登记簿-分页列表查询")
    @ApiOperation(value = "担保品关联贷款登记簿-分页列表查询", notes = "担保品关联贷款登记簿-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(SgztDbpgldkdjb sgztDbpgldkdjb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<SgztDbpgldkdjb> queryWrapper = QueryGenerator.initQueryWrapper(sgztDbpgldkdjb, req.getParameterMap());
        Page<SgztDbpgldkdjb> page = new Page<SgztDbpgldkdjb>(pageNo, pageSize);
        IPage pageList= PageUtil.toPage(ISgztDbpgldkdjbService.class,sgztDbpgldkdjbService,pageNo,pageSize,queryWrapper,"fiscal_date","xh");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param sgztDbpgldkdjb
     * @return
     */
    @AutoLog(value = "担保品关联贷款登记簿-添加")
    @ApiOperation(value = "担保品关联贷款登记簿-添加", notes = "担保品关联贷款登记簿-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody SgztDbpgldkdjb sgztDbpgldkdjb) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String fiscalDate = sgztDbpgldkdjb.getFiscalDate().replace("-", "").substring(0,8);
        String htksrq = sgztDbpgldkdjb.getHtksrq().replace("-", "").substring(0,8);
        String htzzrq = sgztDbpgldkdjb.getHtzzrq().replace("-", "").substring(0,8);
        sgztDbpgldkdjb.setFiscalDate(fiscalDate);
        sgztDbpgldkdjb.setHtksrq(htksrq);
        sgztDbpgldkdjb.setHtzzrq(htzzrq);
        sgztDbpgldkdjb.setCreateTime(new Date());
        sgztDbpgldkdjbService.save(sgztDbpgldkdjb);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param sgztDbpgldkdjb
     * @return
     */
    @AutoLog(value = "担保品关联贷款登记簿-编辑")
    @ApiOperation(value = "担保品关联贷款登记簿-编辑", notes = "担保品关联贷款登记簿-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody SgztDbpgldkdjb sgztDbpgldkdjb) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String fiscalDate = sgztDbpgldkdjb.getFiscalDate().replace("-", "").substring(0,8);
        String htksrq = sgztDbpgldkdjb.getHtksrq().replace("-", "").substring(0,8);
        String htzzrq = sgztDbpgldkdjb.getHtzzrq().replace("-", "").substring(0,8);
        sgztDbpgldkdjb.setFiscalDate(fiscalDate);
        sgztDbpgldkdjb.setHtksrq(htksrq);
        sgztDbpgldkdjb.setHtzzrq(htzzrq);
        sgztDbpgldkdjbService.updateById(sgztDbpgldkdjb);
        return Result.ok("编辑成功!");
    }
    /**
     * 根据日期批量删除
     * @param fiscalDate
     * @return
     */
    @AutoLog(value = "担保品关联贷款登记簿-批量删除")
    @ApiOperation(value="担保品关联贷款登记簿-批量删除", notes="担保品关联贷款登记簿-批量删除")
    @DeleteMapping(value = "/deleteByBatch")
    public Result<?> deleteByBatch(@Param("fiscalDate") String fiscalDate) {
        QueryWrapper<SgztDbpgldkdjb> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fiscal_date",fiscalDate);
        sgztDbpgldkdjbService.remove(queryWrapper);
        return Result.ok("批量删除成功！");
    }
    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "担保品关联贷款登记簿-通过id删除")
    @ApiOperation(value = "担保品关联贷款登记簿-通过id删除", notes = "担保品关联贷款登记簿-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        sgztDbpgldkdjbService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "担保品关联贷款登记簿-批量删除")
    @ApiOperation(value = "担保品关联贷款登记簿-批量删除", notes = "担保品关联贷款登记簿-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.sgztDbpgldkdjbService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "担保品关联贷款登记簿-通过id查询")
    @ApiOperation(value = "担保品关联贷款登记簿-通过id查询", notes = "担保品关联贷款登记簿-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        SgztDbpgldkdjb sgztDbpgldkdjb = sgztDbpgldkdjbService.getById(id);
        return Result.ok(sgztDbpgldkdjb);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param sgztDbpgldkdjb
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SgztDbpgldkdjb sgztDbpgldkdjb) {
        return super.exportXls(request, sgztDbpgldkdjb, SgztDbpgldkdjb.class, "担保品关联贷款登记簿");
    }

    /**
     * 导入模板
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        // AutoPoi 导出Excel
        ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
        // 导出文件名称
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "担保品关联贷款登记簿导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, SgztDbpgldkdjbVO.class);
        ExportParams exportParams = new ExportParams("担保品关联贷款登记簿导入模板", "模板信息");
        modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
        modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
        return modelAndView;
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        String fiscalDate = request.getParameter("fiscalDate");
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String filePaths = jsonObject.getString("filePaths");
        if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
            return Result.error("请先上传文件！");
        }
        String[] filePathList = filePaths.split(",");
        JSONObject obj = new JSONObject();
        for (String filePath : filePathList) {
            String baseFilePath = uploadpath + File.separator + filePath;
            File file = new File(baseFilePath);
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            params.setHeadRows(1);
            params.setNeedSave(false);
            if (dbpgldkdjbImportVerify != null) {
                params.setVerifyHanlder(dbpgldkdjbImportVerify);
            }
            FileOutputStream fos = null;
            try {
                ExcelImportResult<SgztDbpgldkdjb> importResult = ExcelImportUtil.importExcelVerify(file, SgztDbpgldkdjb.class, SgztDbpgldkdjbVO.class,params);
                List<SgztDbpgldkdjb> list = importResult.getList();
                List<SgztDbpgldkdjb> qkmbList = new ArrayList<>();
                for (SgztDbpgldkdjb ywzkb : list) {
                    Boolean con1= StringUtils.isBlank(ywzkb.getXh());
                    Boolean con2= StringUtils.isNotBlank(ywzkb.getXh()) && (ywzkb.getXh().contains("合计") || ywzkb.getXh().contains("条数"));
                    if (con1 || con2 ) {
                        continue;
                    }
                    ywzkb.setFiscalDate(fiscalDate);
                    ywzkb.setCreateTime(new Date());
                    qkmbList.add(ywzkb);
                }
                service.saveBatch(qkmbList);
                obj.put("filePath", filePath);
                fos = new FileOutputStream(baseFilePath);
                importResult.getWorkbook().write(fos);
                fos.flush();
                fos.close();
                return Result.ok("文件导入完成！成功导入数据行数:" + qkmbList.size(), obj);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            } finally {
                IoUtil.close(fos);
            }
        }
        return Result.ok("文件导入失败！");
    }

}
