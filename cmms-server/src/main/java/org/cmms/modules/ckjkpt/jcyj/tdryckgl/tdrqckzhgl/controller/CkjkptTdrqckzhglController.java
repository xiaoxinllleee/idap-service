package org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqckzhgl.controller;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqckzhgl.entity.CkjkptTdrqckzhgl;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqckzhgl.entity.CkjkptTdrqckzhglImport;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqckzhgl.service.ICkjkptTdrqckzhglService;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqckzhgl.verify.CkjkptTdrqckzhglVerify;
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.wggl.wgzbxx.entity.Wgzbxx;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.util.*;

/**
 * @Description: 特定人群存款账号管理
 * @Author: cmms
 * @Date: 2019-10-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "特定人群存款账号管理")
@RestController
@RequestMapping("/CkjkptTdrqckzhgl/ckjkptTdrqckzhgl")
public class CkjkptTdrqckzhglController extends JeecgController<CkjkptTdrqckzhgl, ICkjkptTdrqckzhglService> {
    @Autowired
    private ICkjkptTdrqckzhglService ckjkptTdrqckzhglService;
    @Autowired
    private CkjkptTdrqckzhglVerify tdrqckzhglVerify;
    @Value(value = "${common.path.upload}")
    private String uploadpath;

    /**
     * 分页列表查询
     *
     * @param ckjkptTdrqckzhgl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "特定人群存款账号管理-分页列表查询")
    @ApiOperation(value = "特定人群存款账号管理-分页列表查询", notes = "特定人群存款账号管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CkjkptTdrqckzhgl ckjkptTdrqckzhgl,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
//		QueryWrapper<CkjkptTdrqckzhgl> queryWrapper = QueryGenerator.initQueryWrapper(ckjkptTdrqckzhgl, req.getParameterMap());
//		queryWrapper.orderByDesc("tjyf").orderByDesc("jgdm").orderByDesc("zjhm");
//		List<CkjkptTdrqckzhgl> list = ckjkptTdrqckzhglService.list(queryWrapper);
//		IPage<CkjkptTdrqckzhgl> pageList = PageUtil.getPages(list, list == null ? 0 : list.size(), pageSize, pageNo);
//		return Result.ok(pageList);
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(ckjkptTdrqckzhgl, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(ICkjkptTdrqckzhglService.class, ckjkptTdrqckzhglService, pageNo, pageSize, queryWrapper, "branch_no", "sub_acct_no", "ident_no");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param ckjkptTdrqckzhgl
     * @return
     */
    @AutoLog(value = "特定人群存款账号管理-添加")
    @ApiOperation(value = "特定人群存款账号管理-添加", notes = "特定人群存款账号管理-添加")
    @PostMapping(value = "/add")
    public Result<CkjkptTdrqckzhgl> add(@RequestBody CkjkptTdrqckzhgl ckjkptTdrqckzhgl) {
        Result<CkjkptTdrqckzhgl> result = new Result<CkjkptTdrqckzhgl>();
        try {
            ckjkptTdrqckzhglService.save(ckjkptTdrqckzhgl);
            result.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }

    /**
     * 编辑
     *
     * @param ckjkptTdrqckzhgl
     * @return
     */
    @AutoLog(value = "特定人群存款账号管理-编辑")
    @ApiOperation(value = "特定人群存款账号管理-编辑", notes = "特定人群存款账号管理-编辑")
    @PutMapping(value = "/edit")
    public Result<CkjkptTdrqckzhgl> edit(@RequestBody CkjkptTdrqckzhgl ckjkptTdrqckzhgl) {
        Result<CkjkptTdrqckzhgl> result = new Result<CkjkptTdrqckzhgl>();
        CkjkptTdrqckzhgl ckjkptTdrqckzhglEntity = ckjkptTdrqckzhglService.getById(ckjkptTdrqckzhgl.getIdentNo());
        if (ckjkptTdrqckzhglEntity == null) {
            result.error500("未找到对应实体");
        } else {
            boolean ok = ckjkptTdrqckzhglService.updateById(ckjkptTdrqckzhgl);
            //TODO 返回false说明什么？
            if (ok) {
                result.success("修改成功!");
            }
        }

        return result;
    }

    /**
     * 通过账号/证件号码删除
     *
     * @param
     * @return
     */
    @AutoLog(value = "特定人群存款账号管理-通过账号/证件号码删除")
    @ApiOperation(value = "特定人群存款账号管理-通过账号/证件号码删除", notes = "特定人群存款账号管理-通过账号/证件号码删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@Param("subAcctNo") String subAcctNo,
                            @Param("identNo") String identNo) {
        try {
            QueryWrapper<CkjkptTdrqckzhgl> queryWrapper = new QueryWrapper<CkjkptTdrqckzhgl>();
            queryWrapper.eq("sub_acct_no", subAcctNo);
            queryWrapper.eq("ident_no", identNo);
            ckjkptTdrqckzhglService.remove(queryWrapper);
            return Result.ok("删除成功!");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return Result.error("删除失败!"+throwable.getMessage());
        }
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "特定人群存款账号管理-批量删除")
    @ApiOperation(value = "特定人群存款账号管理-批量删除", notes = "特定人群存款账号管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<CkjkptTdrqckzhgl> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<CkjkptTdrqckzhgl> result = new Result<CkjkptTdrqckzhgl>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.ckjkptTdrqckzhglService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "特定人群存款账号管理-通过id查询")
    @ApiOperation(value = "特定人群存款账号管理-通过id查询", notes = "特定人群存款账号管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<CkjkptTdrqckzhgl> queryById(@RequestParam(name = "id", required = true) String id) {
        Result<CkjkptTdrqckzhgl> result = new Result<CkjkptTdrqckzhgl>();
        CkjkptTdrqckzhgl ckjkptTdrqckzhgl = ckjkptTdrqckzhglService.getById(id);
        if (ckjkptTdrqckzhgl == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(ckjkptTdrqckzhgl);
            result.setSuccess(true);
        }
        return result;
    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        // Step.1 组装查询条件
        QueryWrapper<CkjkptTdrqckzhgl> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                CkjkptTdrqckzhgl ckjkptTdrqckzhgl = JSON.parseObject(deString, CkjkptTdrqckzhgl.class);
                queryWrapper = QueryGenerator.initQueryWrapper(ckjkptTdrqckzhgl, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<CkjkptTdrqckzhgl> pageList = ckjkptTdrqckzhglService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "特定人群存款账号管理列表");
        mv.addObject(NormalExcelConstants.CLASS, CkjkptTdrqckzhgl.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("特定人群存款账号管理列表数据", "导出人:" + sysUser.getRealname(), "导出信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
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
    public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        return super.importExcelByTemplate(jsonObject, request, response, CkjkptTdrqckzhgl.class, tdrqckzhglVerify);
    }

    /**
     * 导出模板excel
     *
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls() {
        return super.exportTemplateXls(CkjkptTdrqckzhgl.class, "特定人员存款管理导入模板");
    }
}
