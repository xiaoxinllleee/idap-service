package org.cmms.modules.report.csgl.zhrwszgl.controller;

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
import org.cmms.modules.report.csgl.zhrwszgl.entity.Zhrwszgl;
import org.cmms.modules.report.csgl.zhrwszgl.service.IZhrwszglService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.csgl.zhrwszgl.verify.ZhrwszglImpVerify;
import org.cmms.modules.report.csgl.zhrwszgl.vo.ZhrwszglVO;
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
 * @Description: 支行年度任务设置管理
 * @Author: jeecg-boot
 * @Date: 2023-06-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "支行年度任务设置管理")
@RestController
@RequestMapping("/report/csgl/zhrwszgl")
public class ZhrwszglController extends JeecgController<Zhrwszgl, IZhrwszglService> {
    @Autowired
    private IZhrwszglService zhrwszglService;
    @Autowired
    private ZhrwszglImpVerify importVerify;
    @Autowired
    private IHrBasOrganizationService organizationService;

    /**
     * 分页列表查询
     *
     * @param zhrwszgl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "支行年度任务设置管理-分页列表查询")
    @ApiOperation(value = "支行年度任务设置管理-分页列表查询", notes = "支行年度任务设置管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Zhrwszgl zhrwszgl,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Zhrwszgl> queryWrapper = QueryGenerator.initQueryWrapper(zhrwszgl, req.getParameterMap());
        IPage pageList = PageUtil.toPage(IZhrwszglService.class, zhrwszglService, pageNo, pageSize, queryWrapper,"task_year","branch_no","task_code");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param zhrwszgl
     * @return
     */
    @AutoLog(value = "支行年度任务设置管理-添加")
    @ApiOperation(value = "支行年度任务设置管理-添加", notes = "支行年度任务设置管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Zhrwszgl zhrwszgl) {
        zhrwszglService.save(zhrwszgl);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param zhrwszgl
     * @return
     */
    @AutoLog(value = "支行年度任务设置管理-编辑")
    @ApiOperation(value = "支行年度任务设置管理-编辑", notes = "支行年度任务设置管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Zhrwszgl zhrwszgl) {
//        Zhrwszgl record = new Zhrwszgl();
//        record.setTaskYear(zhrwszgl.getTaskYear());
//        record.setBranchNo(zhrwszgl.getBranchNo());
//        record.setTaskCode(zhrwszgl.getTaskCode());
        UpdateWrapper<Zhrwszgl> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("task_year",zhrwszgl.getTaskYear());
        updateWrapper.eq("branch_no",zhrwszgl.getBranchNo());
        updateWrapper.eq("task_code",zhrwszgl.getTaskCode());
        zhrwszgl.setTaskYear(null);
        zhrwszgl.setBranchNo(null);
        zhrwszgl.setTaskCode(null);
        zhrwszgl.setUpdateBy(getLoginUser().getUsername());
        zhrwszgl.setUpdateTime(new Date());
        zhrwszglService.update(zhrwszgl,updateWrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "支行年度任务设置管理-通过id删除")
    @ApiOperation(value = "支行年度任务设置管理-通过id删除", notes = "支行年度任务设置管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        zhrwszglService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "支行年度任务设置管理-批量删除")
    @ApiOperation(value = "支行年度任务设置管理-批量删除", notes = "支行年度任务设置管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.zhrwszglService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "支行年度任务设置管理-通过id查询")
    @ApiOperation(value = "支行年度任务设置管理-通过id查询", notes = "支行年度任务设置管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Zhrwszgl zhrwszgl = zhrwszglService.getById(id);
        return Result.ok(zhrwszgl);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param zhrwszgl
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Zhrwszgl zhrwszgl) {
        return super.exportXls(request, zhrwszgl, Zhrwszgl.class, "支行年度任务设置");
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
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "信贷计划月报_支行年度任务设置_导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, ZhrwszglVO.class);
        ExportParams exportParams = new ExportParams("支行年度任务设置", "模板信息");
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
        //return super.importExcel(request, response, Zhrwszgl.class);
        String taskYear = request.getParameter("taskYear");
        log.info("当前导入任务年度：" + taskYear);
        String branchNo = "";
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
                boolean checkResult = ExcelImportCheckUtil.check(fis, ZhrwszglVO.class,params,1.0);
                ExcelImportResult<ZhrwszglVO> importResult = ExcelImportUtil.importExcelVerify(file,ZhrwszglVO.class,params);
                List<Zhrwszgl> records = new ArrayList<>();
                List<ZhrwszglVO> list = importResult.getList();
                for (ZhrwszglVO zhrwszglVO : list) {
                    Zhrwszgl record = new Zhrwszgl();
                    BeanUtil.copyPropertiesIgnoreNull(zhrwszglVO, record);
                    record.setTaskYear(taskYear);
                    //机构编码处理
                    branchNo = organizationService.queryYwjgdmByZzjcLike("ywjgdm","zzjc",record.getBranchName().replace(" ",""));
                    if (StringUtils.isEmpty(branchNo)) {
                        if ("合计".equals(record.getBranchName().replace(" ",""))) {
                            branchNo="020";
                        }
                        if ("结算".equals(record.getBranchName().replace(" ",""))) {
                            branchNo="02000";
                        }
                    }
                    record.setBranchNo(branchNo);
                    record.setCreateBy(getLoginUser().getUsername());
                    record.setCreateTime(new Date());
                    records.add(record);
                }
                if (!records.isEmpty()) {
                    // 保存以前删除当年数据，以防止重复导入
                    UpdateWrapper<Zhrwszgl> deleteWrapper = new UpdateWrapper<>();
                    deleteWrapper.eq("task_year",records.get(0).getTaskYear());
                    zhrwszglService.remove(deleteWrapper);
                    zhrwszglService.saveBatch(records);
                }
                obj.put("filePath", filePath);
                fos = new FileOutputStream(baseFilePath);
                importResult.getWorkbook().write(fos);
                fos.flush();
                fos.close();
                return Result.ok("文件导入完成！成功导入数据行数:" + list.size(), obj);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            } finally {
                IoUtil.close(fis);
                IoUtil.close(fos);
            }
        }
        return Result.ok("文件导入成功！");
    }

}
