package org.cmms.modules.ywgl.dkyw.tsdkgl.controller;

import java.io.*;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.oConvertUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ywgl.dkyw.tsdkgl.entity.Tsdkgl;
import org.cmms.modules.ywgl.dkyw.tsdkgl.entity.TsdkglT;
import org.cmms.modules.ywgl.dkyw.tsdkgl.service.ITsdkglService;
import org.cmms.modules.ywgl.dkyw.tsdkgl.service.ITsdkglTService;
import org.cmms.modules.ywgl.dkyw.tsdkgl.verify.TsdkglImportVerify;
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
 * @Description: 特殊贷款管理
 * @Author: jeecg-boot
 * @Date: 2021-09-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "特殊贷款管理")
@RestController
@RequestMapping("/tsdkgl/tsdkgl")
public class TsdkglController extends JeecgController<Tsdkgl, ITsdkglService> {
    @Autowired
    private ITsdkglService tsdkglService;
    @Autowired
    private ITsdkglTService iTsdkglTService;
    @Autowired
    private TsdkglImportVerify importVerify;

    /**
     * 分页列表查询
     *
     * @param tsdkgl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "特殊贷款管理-分页列表查询")
    @ApiOperation(value = "特殊贷款管理-分页列表查询", notes = "特殊贷款管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Tsdkgl tsdkgl,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Tsdkgl> queryWrapper = QueryGenerator.initQueryWrapper(tsdkgl, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(ITsdkglService.class, tsdkglService, pageNo, pageSize, queryWrapper, "jgdm", "dkzh");
        return Result.ok(pageList);
    }

    /**
     * 分页列表查询
     * 特殊贷款管理（绩效考核/初始设置）
     * @param tsdkglT
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "特殊贷款管理（绩效考核/初始设置）-分页列表查询")
    @ApiOperation(value = "特殊贷款管理（绩效考核/初始设置）-分页列表查询", notes = "特殊贷款管理（绩效考核/初始设置）-分页列表查询")
    @GetMapping(value = "/listT")
    public Result<?> queryPageListT(TsdkglT tsdkglT,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<TsdkglT> queryWrapper = QueryGenerator.initQueryWrapper(tsdkglT, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(ITsdkglTService.class, iTsdkglTService, pageNo, pageSize, queryWrapper, "jgdm", "dkzh");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param tsdkgl
     * @return
     */
    @AutoLog(value = "特殊贷款管理-添加")
    @ApiOperation(value = "特殊贷款管理-添加", notes = "特殊贷款管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody TsdkglT tsdkgl) {
        QueryWrapper<Tsdkgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dkzh", tsdkgl.getDkzh());
        List<Tsdkgl> list = tsdkglService.list(queryWrapper);
        if (!list.isEmpty()) {
            return Result.error("贷款账号已经存在！");
        }
        iTsdkglTService.save(tsdkgl);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param tsdkgl
     * @return
     */
    @AutoLog(value = "特殊贷款管理-编辑")
    @ApiOperation(value = "特殊贷款管理-编辑", notes = "特殊贷款管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody TsdkglT tsdkgl) {
        QueryWrapper<TsdkglT> queryWrapper = new QueryWrapper<TsdkglT>();
        queryWrapper.eq("dkzh", tsdkgl.getDkzh());
        iTsdkglTService.update(tsdkgl, queryWrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param
     * @return
     */
    @AutoLog(value = "特殊贷款管理-通过id删除")
    @ApiOperation(value = "特殊贷款管理-通过id删除", notes = "特殊贷款管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam("id") String id) {
        QueryWrapper<TsdkglT> queryWrapper = new QueryWrapper<TsdkglT>();
        queryWrapper.eq("dkzh", id);
        iTsdkglTService.remove(queryWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "特殊贷款管理-批量删除")
    @ApiOperation(value = "特殊贷款管理-批量删除", notes = "特殊贷款管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.tsdkglService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "特殊贷款管理-通过id查询")
    @ApiOperation(value = "特殊贷款管理-通过id查询", notes = "特殊贷款管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Tsdkgl tsdkgl = tsdkglService.getById(id);
        return Result.ok(tsdkgl);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param tsdkgl
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Tsdkgl tsdkgl) {
        return super.exportXls(request, tsdkgl, Tsdkgl.class, "特殊贷款管理");
    }

	/**
	 * 导出excel
	 * 特殊贷款管理（绩效考核/初始设置）
	 * @param request
	 * @param tsdkglT
	 */
	@RequestMapping(value = "/exportXlsT")
    public ModelAndView exportXlsT(HttpServletRequest request, TsdkglT tsdkglT) {
        // Step.1 组装查询条件
        QueryWrapper<TsdkglT> queryWrapper = QueryGenerator.initQueryWrapper(tsdkglT, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String selections = request.getParameter("selections");
        String rowKey = request.getParameter("rowKey");
        if (oConvertUtils.isNotEmpty(selections)) {
            List<String> selectionList = Arrays.asList(selections.split(","));
            if (oConvertUtils.isNotEmpty(rowKey)) {
                queryWrapper.in(rowKey, selectionList);
            } else {
                queryWrapper.in("id", selectionList);
            }
        }
        // Step.2 获取导出数据
        List<TsdkglT> exportList = iTsdkglTService.list(queryWrapper);
        // Step.3 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "特殊贷款管理"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, TsdkglT.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("特殊贷款管理", "导出人:" + sysUser.getRealname(), "特殊贷款管理"));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }

    /**
     * 导出模板Excel
     * 特殊贷款管理（绩效考核/初始设置）
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        // 导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "特殊贷款管理");
        mv.addObject(NormalExcelConstants.CLASS, TsdkglT.class);
        ExportParams exportParams = new ExportParams("特殊贷款管理导入模板", "特殊贷款管理");
        mv.addObject(NormalExcelConstants.PARAMS, exportParams);
        mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
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
        return super.importExcel(request, response, Tsdkgl.class);
    }

    /**
     * 通过excel导入数据
     * 特殊贷款管理（绩效考核/初始设置）
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcelT", method = RequestMethod.POST)
    public Result<?> importExcelT(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        String filePaths = jsonObject.getString("filePaths");
        if (StringUtils.isEmpty(filePaths)) {
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
            if (importVerify != null) {
                params.setVerifyHanlder(importVerify);
            }
            FileInputStream fis = null;
            FileOutputStream fos = null;
            try {
                fis = new FileInputStream(file);
                boolean checkResult = ExcelImportCheckUtil.check(fis, TsdkglT.class, params, 1.0);
                ExcelImportResult<TsdkglT> importResult = ExcelImportUtil.importExcelVerify(file, TsdkglT.class, params);
                List<TsdkglT> list = importResult.getList();
                List<TsdkglT> tsdkglTList = new ArrayList<>();
                for (TsdkglT tsdkglT : list) {
                    TsdkglT tsdkgl = new TsdkglT();
                    BeanUtil.copyPropertiesIgnoreNull(tsdkglT,tsdkgl);
                    tsdkgl.setLrbz(0);
                    tsdkgl.setLrr(getLoginUser().getUsername());
                    tsdkgl.setLrsj(new Date());
                    tsdkglTList.add(tsdkgl);
                    // 相同贷款账号的删除
                    UpdateWrapper<TsdkglT> updateWrapper = new UpdateWrapper<>();
                    updateWrapper.eq("dkzh",tsdkgl.getDkzh());
                    iTsdkglTService.remove(updateWrapper);
                }
                if (!tsdkglTList.isEmpty()) {
                    iTsdkglTService.saveBatch(tsdkglTList);
                }
                obj.put("filePath", filePath);
                fos = new FileOutputStream(baseFilePath);
                importResult.getWorkbook().write(fos);
                fos.flush();
                fos.close();
                return Result.ok("文件导入完成！成功导入数据行数: " + list.size(), obj);
            } catch (Exception e) {
                e.printStackTrace();
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
