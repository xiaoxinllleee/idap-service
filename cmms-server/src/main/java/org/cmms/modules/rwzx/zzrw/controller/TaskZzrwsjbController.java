package org.cmms.modules.rwzx.zzrw.controller;

import java.io.*;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.rwzx.zzrw.entity.TaskYwghb;
import org.cmms.modules.rwzx.zzrw.entity.TaskZzrwsjb;
import org.cmms.modules.rwzx.zzrw.service.ITaskZzrwsjbService;

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
 * @Description: 自主任务数据表
 * @Author: jeecg-boot
 * @Date: 2023-08-04
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "自主任务数据表")
@RestController
@RequestMapping("/zzrw/taskZzrwsjb")
public class TaskZzrwsjbController extends JeecgController<TaskZzrwsjb, ITaskZzrwsjbService> {
    @Autowired
    private ITaskZzrwsjbService taskZzrwsjbService;

    /**
     * 分页列表查询
     *
     * @param taskZzrwsjb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "自主任务数据表-分页列表查询")
    @ApiOperation(value = "自主任务数据表-分页列表查询", notes = "自主任务数据表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(TaskZzrwsjb taskZzrwsjb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<TaskZzrwsjb> queryWrapper = QueryGenerator.initQueryWrapper(taskZzrwsjb, req.getParameterMap());
        Page<TaskZzrwsjb> page = new Page<TaskZzrwsjb>(pageNo, pageSize);
        IPage<TaskZzrwsjb> pageList = taskZzrwsjbService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param taskZzrwsjb
     * @return
     */
    @AutoLog(value = "自主任务数据表-添加")
    @ApiOperation(value = "自主任务数据表-添加", notes = "自主任务数据表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody TaskZzrwsjb taskZzrwsjb) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        taskZzrwsjb.setCreateTime(new Date());
        taskZzrwsjb.setCreateBy(loginUser.getWorkNo());
        taskZzrwsjbService.save(taskZzrwsjb);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param taskZzrwsjb
     * @return
     */
    @AutoLog(value = "自主任务数据表-编辑")
    @ApiOperation(value = "自主任务数据表-编辑", notes = "自主任务数据表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody TaskZzrwsjb taskZzrwsjb) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        taskZzrwsjb.setUpdateTime(new Date());
        taskZzrwsjb.setUpdateBy(loginUser.getWorkNo());
        taskZzrwsjbService.updateById(taskZzrwsjb);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "自主任务数据表-通过id删除")
    @ApiOperation(value = "自主任务数据表-通过id删除", notes = "自主任务数据表-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        taskZzrwsjbService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "自主任务数据表-批量删除")
    @ApiOperation(value = "自主任务数据表-批量删除", notes = "自主任务数据表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.taskZzrwsjbService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "自主任务数据表-通过id查询")
    @ApiOperation(value = "自主任务数据表-通过id查询", notes = "自主任务数据表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        TaskZzrwsjb taskZzrwsjb = taskZzrwsjbService.getById(id);
        return Result.ok(taskZzrwsjb);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param taskZzrwsjb
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TaskZzrwsjb taskZzrwsjb) {
        return super.exportXls(request, taskZzrwsjb, TaskZzrwsjb.class, "自主任务数据表");
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
		modelAndView.addObject(NormalExcelConstants.FILE_NAME, "自主任务数据表");
		modelAndView.addObject(NormalExcelConstants.CLASS, TaskZzrwsjb.class);
		ExportParams exportParams = new ExportParams("自主任务数据表导入模板", "模板信息");
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
                ExcelImportResult<TaskZzrwsjb> importResult = ExcelImportUtil.importExcelVerify(file, TaskZzrwsjb.class, params);
                List<TaskZzrwsjb> list = importResult.getList();
                list.forEach(e->{
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
                log.error(e.getMessage(),e);
                return Result.error("文件导入失败:"+e.getMessage());
            } finally {
                IoUtil.close(fis);
                IoUtil.close(fos);
            }
        }
        return Result.ok("文件导入失败！");
    }

}
