package org.cmms.modules.report.sgtzgl.dkffdjb.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.*;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.dgckzhyeb.service.ISgtzglDgckzhyebService;
import org.cmms.modules.report.sgtzgl.dkffdjb.entity.SgtzglDkffdjb;
import org.cmms.modules.report.sgtzgl.dkffdjb.entity.SgtzglDkffdjbVO;
import org.cmms.modules.report.sgtzgl.dkffdjb.service.ISgtzglDkffdjbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.dkffdjb.verify.DkffdjbImportVerify;
import org.cmms.modules.report.sgtzgl.dkffdjbw.entity.DkffdjbW;
import org.cmms.modules.report.sgtzgl.dkqmx.entity.SgtzglDkqmx;
import org.cmms.modules.report.sgtzgl.txdjb.entity.SgtzglTxdjb;
import org.cmms.modules.report.sgtzgl.txdjb.entity.SgtzglTxdjbVO;
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
 * @Description: 贷款发放登记簿
 * @Author: jeecg-boot
 * @Date: 2022-09-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "贷款发放登记簿")
@RestController
@RequestMapping("/Dkffdjb/sgtzglDkffdjb")
public class SgtzglDkffdjbController extends JeecgController<SgtzglDkffdjb, ISgtzglDkffdjbService> {
    @Autowired
    private ISgtzglDkffdjbService sgtzglDkffdjbService;
    @Autowired
    private DkffdjbImportVerify dkffdjbImportVerify;

    /**
     * 分页列表查询
     *
     * @param sgtzglDkffdjb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "贷款发放登记簿-分页列表查询")
    @ApiOperation(value = "贷款发放登记簿-分页列表查询", notes = "贷款发放登记簿-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(SgtzglDkffdjb sgtzglDkffdjb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        try {
            if (StringUtils.isNotBlank(sgtzglDkffdjb.getFiscalDate())) {
                sgtzglDkffdjb.setFiscalDate(DateUtil.getLastDayString(sgtzglDkffdjb.getFiscalDate().replace("-", "")));
            }
            QueryWrapper<SgtzglDkffdjb> queryWrapper = QueryGenerator.initQueryWrapper(sgtzglDkffdjb, req.getParameterMap());
            Page<SgtzglDkffdjb> page = new Page<SgtzglDkffdjb>(pageNo, pageSize);
            IPage pageList = PageUtil.toPage(ISgtzglDkffdjbService.class, sgtzglDkffdjbService, pageNo, pageSize, queryWrapper, "fiscal_date", "xh");
            return Result.ok(pageList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }

    /**
     * 添加
     *
     * @param sgtzglDkffdjb
     * @return
     */
    @AutoLog(value = "贷款发放登记簿-添加")
    @ApiOperation(value = "贷款发放登记簿-添加", notes = "贷款发放登记簿-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody SgtzglDkffdjb sgtzglDkffdjb) {
        sgtzglDkffdjb.setFiscalDate(sgtzglDkffdjb.getFiscalDate() == null ? null : sgtzglDkffdjb.getFiscalDate().replace("-", "").substring(0, 8));
        sgtzglDkffdjb.setJkrq(sgtzglDkffdjb.getJkrq() == null ? null : sgtzglDkffdjb.getJkrq().replace("-", "").substring(0, 8));
        sgtzglDkffdjb.setDqrq(sgtzglDkffdjb.getDqrq() == null ? null : sgtzglDkffdjb.getDqrq().replace("-", "").substring(0, 8));
        sgtzglDkffdjbService.save(sgtzglDkffdjb);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param sgtzglDkffdjb
     * @return
     */
    @AutoLog(value = "贷款发放登记簿-编辑")
    @ApiOperation(value = "贷款发放登记簿-编辑", notes = "贷款发放登记簿-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody SgtzglDkffdjb sgtzglDkffdjb) {
        //sgtzglDkffdjb.setFiscalDate(sgtzglDkffdjb.getFiscalDate() == null ? null : sgtzglDkffdjb.getFiscalDate().replace("-", "").substring(0, 8));
        sgtzglDkffdjb.setJkrq(sgtzglDkffdjb.getJkrq() == null ? null : sgtzglDkffdjb.getJkrq().replace("-", "").substring(0, 8));
        sgtzglDkffdjb.setDqrq(sgtzglDkffdjb.getDqrq() == null ? null : sgtzglDkffdjb.getDqrq().replace("-", "").substring(0, 8));
        sgtzglDkffdjb.setQygm(sgtzglDkffdjb.getQygm() == null ? "" : sgtzglDkffdjb.getQygm());
        log.info("@@@@@@@@@@@修改企业规模"+sgtzglDkffdjb.getQygm());
        QueryWrapper<SgtzglDkffdjb> queryWrapper = new QueryWrapper<>();
        log.info("@@@@@@@@@@@getFiscalDate"+sgtzglDkffdjb.getFiscalDate());
        log.info("@@@@@@@@@@@getDkzh"+sgtzglDkffdjb.getDkzh());
        queryWrapper.eq("fiscal_date", sgtzglDkffdjb.getFiscalDate().replaceAll("-",""));
        queryWrapper.eq("dkzh", sgtzglDkffdjb.getDkzh());
        sgtzglDkffdjb.setFiscalDate(null);
        sgtzglDkffdjb.setDkzh(null);
        sgtzglDkffdjbService.update(sgtzglDkffdjb, queryWrapper);
        log.info("@@@@@@@@@@@修改成功！"+sgtzglDkffdjb.getQygm());
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param
     * @return
     */
    @AutoLog(value = "贷款发放登记簿-通过数据日期和贷款账号删除")
    @ApiOperation(value = "贷款发放登记簿-通过数据日期和贷款账号删除", notes = "贷款发放登记簿-通过数据日期和贷款账号删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "fiscalDate", required = true) String fiscalDate, @RequestParam(name = "dkzh", required = true) String dkzh) {
        QueryWrapper<SgtzglDkffdjb> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("FISCAL_DATE", fiscalDate).eq("dkzh", dkzh);
        sgtzglDkffdjbService.remove(queryWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "贷款发放登记簿-批量删除")
    @ApiOperation(value = "贷款发放登记簿-批量删除", notes = "贷款发放登记簿-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.sgtzglDkffdjbService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 根据日期批量删除
     *
     * @param
     * @return
     */
    @AutoLog(value = "财务报表全科目表-批量删除")
    @ApiOperation(value = "财务报表全科目表-批量删除", notes = "财务报表全科目表-批量删除")
    @DeleteMapping(value = "/deleteByBatch")
    public Result<?> deleteByBatch(@Param("fiscalDate") String fiscalDate) {
        QueryWrapper<SgtzglDkffdjb> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("FISCAL_DATE", fiscalDate);
        this.sgtzglDkffdjbService.remove(queryWrapper);
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "贷款发放登记簿-通过id查询")
    @ApiOperation(value = "贷款发放登记簿-通过id查询", notes = "贷款发放登记簿-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        SgtzglDkffdjb sgtzglDkffdjb = sgtzglDkffdjbService.getById(id);
        return Result.ok(sgtzglDkffdjb);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param sgtzglDkffdjb
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SgtzglDkffdjb sgtzglDkffdjb) {
        try {
            if (StringUtils.isNotBlank(sgtzglDkffdjb.getFiscalDate())) {
                sgtzglDkffdjb.setFiscalDate(DateUtil.getLastDayString(sgtzglDkffdjb.getFiscalDate().replace("-", "")));
            }
            return super.exportXls(request, sgtzglDkffdjb, SgtzglDkffdjb.class, "贷款发放登记簿");
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
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
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "贷款发放登记簿（月）导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, SgtzglDkffdjbVO.class);
        ExportParams exportParams = new ExportParams("贷款发放登记簿（月）导入模板", "模板信息");
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
        try {
            String fiscalDate = DateUtil.getLastDayString(request.getParameter("fiscalDate").replace("-", ""));
            QueryWrapper<SgtzglDkffdjb> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("fiscal_date", fiscalDate);
            sgtzglDkffdjbService.remove(queryWrapper);
            System.out.println(fiscalDate + "----sjrq----");
            String filePaths = jsonObject.getString("filePaths");
            if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
                return Result.error("请先上传文件！");
            }
            String[] filePathList = filePaths.split(",");
            JSONObject obj = new JSONObject();
            for (String filePath : filePathList) {
                String baseFilePath = uploadpath + File.separator + filePath;
//          MultipartFile file = entity.getValue();// 获取上传文件对象
                File file = new File(baseFilePath);
                ImportParams params = new ImportParams();
                params.setTitleRows(1);
                params.setHeadRows(1);
                params.setNeedSave(false);
                if (dkffdjbImportVerify != null) {
                    params.setVerifyHanlder(dkffdjbImportVerify);
                }
                FileOutputStream fos = null;
                try {
                    ExcelImportResult<SgtzglDkffdjbVO> importResult = ExcelImportUtil.importExcelVerify(file, SgtzglDkffdjbVO.class, params);
                    List<SgtzglDkffdjbVO> list = importResult.getList();
                    List<SgtzglDkffdjb> qkmbList = new ArrayList<>();
                    for (SgtzglDkffdjbVO qkmb : list) {
                        SgtzglDkffdjb sgtzglDkffdjb = new SgtzglDkffdjb();
                        BeanUtil.copyPropertiesIgnoreNull(qkmb,sgtzglDkffdjb);
                        if (sgtzglDkffdjb.getFiscalDate() == null || sgtzglDkffdjb.getDkzh() == null) {
                            sgtzglDkffdjb.setFiscalDate(fiscalDate);
                            sgtzglDkffdjb.setDkzh(qkmb.getDkzh());
                        }
                        Boolean con1=StringUtils.isBlank(sgtzglDkffdjb.getDkzh());
                        Boolean con2=StringUtils.isNotBlank(sgtzglDkffdjb.getXh()) && (sgtzglDkffdjb.getXh().contains("合计") || sgtzglDkffdjb.getXh().contains("条数"));
                        if (con1 || con2 ) {
                            continue;
                        }
                        qkmbList.add(sgtzglDkffdjb);
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
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return Result.ok("文件导入失败！");
    }
}
