package org.cmms.modules.report.sgtzgl.yhkywqkb.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.yhkywqkb.entity.SgtzYhkywqkb;
import org.cmms.modules.report.sgtzgl.yhkywqkb.entity.YhkywqkbVO;
import org.cmms.modules.report.sgtzgl.yhkywqkb.service.ISgtzYhkywqkbService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.yhkywqkb.vo.YhkywqkImpVerify;
import org.cmms.modules.system.service.IHrBasOrganizationService;
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
 * @Description: 银行卡业务情况表
 * @Author: jeecg-boot
 * @Date: 2023-05-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "银行卡业务情况表")
@RestController
@RequestMapping("/report/sgtzgl/Yhkywqkb")
public class SgtzYhkywqkbController extends JeecgController<SgtzYhkywqkb, ISgtzYhkywqkbService> {
    @Autowired
    private ISgtzYhkywqkbService sgtzYhkywqkbService;
    @Autowired
    private YhkywqkImpVerify importVerify;
    @Autowired
    private IHrBasOrganizationService organizationService;

    /**
     * 分页列表查询
     *
     * @param sgtzYhkywqkb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "银行卡业务情况表-分页列表查询")
    @ApiOperation(value = "银行卡业务情况表-分页列表查询", notes = "银行卡业务情况表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(SgtzYhkywqkb sgtzYhkywqkb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<SgtzYhkywqkb> queryWrapper = QueryGenerator.initQueryWrapper(sgtzYhkywqkb, req.getParameterMap());
        IPage pageList = PageUtil.toPage(ISgtzYhkywqkbService.class, sgtzYhkywqkbService, pageNo, pageSize, queryWrapper,"fiscal_date");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param sgtzYhkywqkb
     * @return
     */
    @AutoLog(value = "银行卡业务情况表-添加")
    @ApiOperation(value = "银行卡业务情况表-添加", notes = "银行卡业务情况表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody SgtzYhkywqkb sgtzYhkywqkb) {
        sgtzYhkywqkbService.save(sgtzYhkywqkb);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param sgtzYhkywqkb
     * @return
     */
    @AutoLog(value = "银行卡业务情况表-编辑")
    @ApiOperation(value = "银行卡业务情况表-编辑", notes = "银行卡业务情况表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody SgtzYhkywqkb sgtzYhkywqkb) {
        sgtzYhkywqkbService.updateById(sgtzYhkywqkb);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "银行卡业务情况表-通过id删除")
    @ApiOperation(value = "银行卡业务情况表-通过id删除", notes = "银行卡业务情况表-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        sgtzYhkywqkbService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "银行卡业务情况表-批量删除")
    @ApiOperation(value = "银行卡业务情况表-批量删除", notes = "银行卡业务情况表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.sgtzYhkywqkbService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "银行卡业务情况表-通过id查询")
    @ApiOperation(value = "银行卡业务情况表-通过id查询", notes = "银行卡业务情况表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        SgtzYhkywqkb sgtzYhkywqkb = sgtzYhkywqkbService.getById(id);
        return Result.ok(sgtzYhkywqkb);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param sgtzYhkywqkb
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SgtzYhkywqkb sgtzYhkywqkb) {
        return super.exportXls(request, sgtzYhkywqkb, SgtzYhkywqkb.class, "农村商业银行卡业务情况表");
    }

    /**
     * 数据导入模板下载
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
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "银行卡业务情况表_导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, YhkywqkbVO.class);
        ExportParams exportParams = new ExportParams("农村商业银行卡业务情况表", "模板信息");
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
        // return super.importExcel(request, response, SgtzYhkywqkb.class);
        String branchNo = "";
        String fiscalDate = request.getParameter("fiscalDate");
        log.info("当前导入数据日期："+fiscalDate);
        JSONObject obj = new JSONObject();
        String filePaths = jsonObject.getString("filePaths");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
            return Result.error("请先上传文件！");
        }
        String[] filePathList = filePaths.split(",");
        for (String filePath : filePathList) {
            String baseFilePath = uploadpath + File.separator + filePath;
            File file = new File(baseFilePath);
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            params.setHeadRows(1);
            params.setNeedSave(false);
            if (importVerify != null) {
                params.setVerifyHanlder(importVerify);
            }
            FileInputStream fis = null;
            FileOutputStream fos = null;
            try {
                fis = new FileInputStream(file);
                boolean checkResult = ExcelImportCheckUtil.check(fis, YhkywqkbVO.class,params,1.0);
                if (!checkResult) {
                    return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
                }
                ExcelImportResult<YhkywqkbVO> importResult = ExcelImportUtil.importExcelVerify(file,YhkywqkbVO.class,params);
                List<SgtzYhkywqkb> records = new ArrayList<>();
                List<YhkywqkbVO> list = importResult.getList();
                for (YhkywqkbVO yhkywqkbVO : list) {
                    SgtzYhkywqkb record = new SgtzYhkywqkb();
                    BeanUtil.copyPropertiesIgnoreNull(yhkywqkbVO, record);
                    record.setFiscalDate(fiscalDate);
                    log.info("项目数据名称 => "+record.getBranchName().replace(" ",""));
                    branchNo = organizationService.queryYwjgdmByZzjcLike("ywjgdm","zzjc",record.getBranchName().replace(" ",""));
                    if (StringUtils.isEmpty(branchNo)) {
                        if ("合计".equals(record.getBranchName().replace(" ",""))) {
                            branchNo = "020";
                        }
                        if ("结算".equals(record.getBranchName().replace(" ",""))) {
                            branchNo = "02000";
                        }
                    }
                    record.setBranchNo(branchNo);
                    records.add(record);
                }
                if (!records.isEmpty()) {
                    // 保存以前删除当月数据，以防止重复导入
                    UpdateWrapper<SgtzYhkywqkb> deleteWrapper = new UpdateWrapper<>();
                    deleteWrapper.eq("fiscal_date",records.get(0).getFiscalDate());
                    sgtzYhkywqkbService.remove(deleteWrapper);
                    sgtzYhkywqkbService.saveBatch(records);
                }
                obj.put("filePath", filePath);
                fos = new FileOutputStream(baseFilePath);
                importResult.getWorkbook().write(fos);
                fos.flush();
                fos.close();
                return Result.ok("文件导入完成！成功导入数据行数:" + list.size(), obj);
            } catch (Exception e) {
                log.error(e.getMessage(),e);
                return Result.error("文件导入失败:"+e.getMessage());
            } finally {
                IoUtil.close(fis);
                IoUtil.close(fos);
            }
        }
        return Result.ok("文件导入成功！");
    }

}
