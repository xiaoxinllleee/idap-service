package org.cmms.modules.rwzx.zzrw.controller;

import java.io.*;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khgl.nh.vo.ZcsxVo;
import org.cmms.modules.rwzx.zzrw.entity.TaskYwghb;
import org.cmms.modules.rwzx.zzrw.service.ITaskYwghbService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
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
 * @Description: 业务管户表
 * @Author: jeecg-boot
 * @Date: 2023-08-04
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "业务管户表")
@RestController
@RequestMapping("/zzrw/taskYwghb")
public class TaskYwghbController extends JeecgController<TaskYwghb, ITaskYwghbService> {
    @Autowired
    private ITaskYwghbService taskYwghbService;

    /**
     * 分页列表查询
     *
     * @param taskYwghb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "业务管户表-分页列表查询")
    @ApiOperation(value = "业务管户表-分页列表查询", notes = "业务管户表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(TaskYwghb taskYwghb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<TaskYwghb> queryWrapper = QueryGenerator.initQueryWrapper(taskYwghb, req.getParameterMap());
        Page<TaskYwghb> page = new Page<TaskYwghb>(pageNo, pageSize);
        IPage<TaskYwghb> pageList = taskYwghbService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param taskYwghb
     * @return
     */
    @AutoLog(value = "业务管户表-添加")
    @ApiOperation(value = "业务管户表-添加", notes = "业务管户表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody TaskYwghb taskYwghb) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        taskYwghb.setCreateTime(new Date());
        taskYwghb.setCreateBy(loginUser.getWorkNo());
        taskYwghbService.save(taskYwghb);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param taskYwghb
     * @return
     */
    @AutoLog(value = "业务管户表-编辑")
    @ApiOperation(value = "业务管户表-编辑", notes = "业务管户表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody TaskYwghb taskYwghb) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        taskYwghb.setUpdateTime(new Date());
        taskYwghb.setUpdateBy(loginUser.getWorkNo());
        taskYwghbService.updateById(taskYwghb);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "业务管户表-通过id删除")
    @ApiOperation(value = "业务管户表-通过id删除", notes = "业务管户表-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        taskYwghbService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "业务管户表-批量删除")
    @ApiOperation(value = "业务管户表-批量删除", notes = "业务管户表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.taskYwghbService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 批量删除
     */
    @AutoLog(value = "业务管户表-批量删除")
    @ApiOperation(value = "业务管户表-批量删除", notes = "业务管户表-批量删除")
    @DeleteMapping(value = "/deleteBatchOther")
    public Result<?> deleteBatchOther(@RequestParam(name = "ywmc", required = false) String ywmc,
                                      @RequestParam(name = "khmc", required = false) String khmc,
                                      @RequestParam(name = "zjhm", required = false) String zjhm,
                                      @RequestParam(name = "ghr", required = false) String ghr) {
        QueryWrapper<TaskYwghb> queryWrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(ywmc)) {
            queryWrapper.eq("ywmc", ywmc);
        }
        if (StringUtils.isNotBlank(khmc)) {
            queryWrapper.eq("khmc", khmc);
        }
        if (StringUtils.isNotBlank(zjhm)) {
            queryWrapper.eq("zjhm", zjhm);
        }
        if (StringUtils.isNotBlank(ghr)) {
            queryWrapper.eq("ghr", ghr);
        }
        taskYwghbService.remove(queryWrapper);
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "业务管户表-通过id查询")
    @ApiOperation(value = "业务管户表-通过id查询", notes = "业务管户表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        TaskYwghb taskYwghb = taskYwghbService.getById(id);
        return Result.ok(taskYwghb);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param taskYwghb
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TaskYwghb taskYwghb) {
        return super.exportXls(request, taskYwghb, TaskYwghb.class, "业务管户表");
    }

    /**
     * 导入模板
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importTemplateXls")
    public ModelAndView importTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        // AutoPoi 导出Excel
        ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
        // 导出文件名称
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "业务管户表");
        modelAndView.addObject(NormalExcelConstants.CLASS, TaskYwghb.class);
        ExportParams exportParams = new ExportParams("业务管户表导入模板", "模板信息");
        modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
        modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
        return modelAndView;
    }

    /**
     * 通过excel导入数据
     *
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(@RequestBody JSONObject jsonObject) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
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
//			if (verifyHandler != null) {
//				params.setVerifyHanlder(verifyHandler);
//			}
            FileOutputStream fos = null;
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                ExcelImportResult<TaskYwghb> importResult = ExcelImportUtil.importExcelVerify(file, TaskYwghb.class, params);
                List<TaskYwghb> list = importResult.getList();
                list.forEach(e -> {
                    e.setCreateTime(new Date());
                    e.setCreateBy(loginUser.getWorkNo());
                });
                service.saveBatch(list);
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
        return Result.ok("文件导入失败！");
    }
}
