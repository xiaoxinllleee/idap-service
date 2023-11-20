package org.cmms.modules.ckjkpt.jcyj.tdryckgl.jkryzhgl.controller;

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
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.jkryzhgl.entity.CkjkptJkryzhgl;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.jkryzhgl.entity.CkjkptJkryzhglImport;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.jkryzhgl.service.ICkjkptJkryzhglService;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.jkryzhgl.verify.CkjkptJkryzhglVerify;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqckzhgl.entity.CkjkptTdrqckzhgl;
import org.cmms.modules.util.PageUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * @Description: 监控人员账户管理
 * @Author: cmms
 * @Date: 2019-10-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "监控人员账户管理")
@RestController
@RequestMapping("/CkjkptJkryzhgl/ckjkptJkryzhgl")
public class CkjkptJkryzhglController extends JeecgController<CkjkptJkryzhgl, ICkjkptJkryzhglService> {
    @Autowired
    private ICkjkptJkryzhglService ckjkptJkryzhglService;
    @Autowired
    private CkjkptJkryzhglVerify ckjkptJkryzhglVerify;
    @Value(value = "${common.path.upload}")
    private String uploadpath;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 分页列表查询
     *
     * @param ckjkptJkryzhgl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "监控人员账户管理-分页列表查询")
    @ApiOperation(value = "监控人员账户管理-分页列表查询", notes = "监控人员账户管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CkjkptJkryzhgl ckjkptJkryzhgl,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
//		QueryWrapper<CkjkptJkryzhgl> queryWrapper = QueryGenerator.initQueryWrapper(ckjkptJkryzhgl, req.getParameterMap());
//		List<CkjkptJkryzhgl> list = ckjkptJkryzhglService.list(queryWrapper);
//		IPage<CkjkptJkryzhgl> pageList = PageUtil.getPages(list, list == null ? 0 : list.size(), pageSize, pageNo);
//		return Result.ok(pageList);
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(ckjkptJkryzhgl, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(ICkjkptJkryzhglService.class, ckjkptJkryzhglService, pageNo, pageSize, queryWrapper, "sub_acct_no", "ident_no", "branch_no");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param ckjkptJkryzhgl
     * @return
     */
    @AutoLog(value = "监控人员账户管理-添加")
    @ApiOperation(value = "监控人员账户管理-添加", notes = "监控人员账户管理-添加")
    @PostMapping(value = "/add")
    public Result<CkjkptJkryzhgl> add(@RequestBody CkjkptJkryzhgl ckjkptJkryzhgl) {
        Result<CkjkptJkryzhgl> result = new Result<CkjkptJkryzhgl>();
        try {
            ckjkptJkryzhglService.save(ckjkptJkryzhgl);
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
     * @param ckjkptJkryzhgl
     * @return
     */
    @AutoLog(value = "监控人员账户管理-编辑")
    @ApiOperation(value = "监控人员账户管理-编辑", notes = "监控人员账户管理-编辑")
    @PutMapping(value = "/edit")
    public Result<CkjkptJkryzhgl> edit(@RequestBody CkjkptJkryzhgl ckjkptJkryzhgl) {
        Result<CkjkptJkryzhgl> result = new Result<CkjkptJkryzhgl>();
        CkjkptJkryzhgl ckjkptJkryzhglEntity = ckjkptJkryzhglService.getById(ckjkptJkryzhgl.getIdentNo());
        if (ckjkptJkryzhglEntity == null) {
            result.error500("未找到对应实体");
        } else {
            boolean ok = ckjkptJkryzhglService.updateById(ckjkptJkryzhgl);
            //TODO 返回false说明什么？
            if (ok) {
                result.success("修改成功!");
            }
        }

        return result;
    }

    /**
     * 通过id删除
     *
     * @param
     * @return
     */
    @AutoLog(value = "监控人员账户管理-通过id删除")
    @ApiOperation(value = "监控人员账户管理-通过id删除", notes = "监控人员账户管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@Param("subAcctNo") String subAcctNo, @Param("identNo") String identNo) {
        QueryWrapper<CkjkptJkryzhgl> queryWrapper = new QueryWrapper<CkjkptJkryzhgl>();
        queryWrapper.eq("sub_acct_no", subAcctNo);
        queryWrapper.eq("ident_no", identNo);
        ckjkptJkryzhglService.remove(queryWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "监控人员账户管理-批量删除")
    @ApiOperation(value = "监控人员账户管理-批量删除", notes = "监控人员账户管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<CkjkptJkryzhgl> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<CkjkptJkryzhgl> result = new Result<CkjkptJkryzhgl>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.ckjkptJkryzhglService.removeByIds(Arrays.asList(ids.split(",")));
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
    @AutoLog(value = "监控人员账户管理-通过id查询")
    @ApiOperation(value = "监控人员账户管理-通过id查询", notes = "监控人员账户管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<CkjkptJkryzhgl> queryById(@RequestParam(name = "id", required = true) String id) {
        Result<CkjkptJkryzhgl> result = new Result<CkjkptJkryzhgl>();
        CkjkptJkryzhgl ckjkptJkryzhgl = ckjkptJkryzhglService.getById(id);
        if (ckjkptJkryzhgl == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(ckjkptJkryzhgl);
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
        QueryWrapper<CkjkptJkryzhgl> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                CkjkptJkryzhgl ckjkptJkryzhgl = JSON.parseObject(deString, CkjkptJkryzhgl.class);
                queryWrapper = QueryGenerator.initQueryWrapper(ckjkptJkryzhgl, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<CkjkptJkryzhgl> pageList = ckjkptJkryzhglService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "监控人员账户管理列表");
        mv.addObject(NormalExcelConstants.CLASS, CkjkptJkryzhgl.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("监控人员账户管理列表数据", "导出人:" + sysUser.getRealname(), "导出信息"));
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
        return super.importExcelByTemplate(jsonObject, request, response, CkjkptJkryzhgl.class,CkjkptJkryzhglImport.class, ckjkptJkryzhglVerify);
    }

    /**
     * 导出模板excel
     *
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls() {
        return super.exportTemplateXls(CkjkptJkryzhglImport.class, "监控人员账户管理导入模板");
    }
}
