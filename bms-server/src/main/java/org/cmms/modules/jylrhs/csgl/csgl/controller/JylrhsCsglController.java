package org.cmms.modules.jylrhs.csgl.csgl.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.jylrhs.csgl.csgl.entity.JylrhsCsgl;
import org.cmms.modules.jylrhs.csgl.csgl.service.IJylrhsCsglService;

import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
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

/**
 * @Description: 经营利润核算（参数管理）
 * @Author: jeecg-boot
 * @Date: 2023-06-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "经营利润核算（参数管理）")
@RestController
@RequestMapping("/jylrhs/csgl/csgl")
public class JylrhsCsglController extends JeecgController<JylrhsCsgl, IJylrhsCsglService> {
    @Autowired
    private IJylrhsCsglService jylrhsCsglService;

    /**
     * 分页列表查询
     *
     * @param jylrhsCsgl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "经营利润核算（参数管理）-分页列表查询")
    @ApiOperation(value = "经营利润核算（参数管理）-分页列表查询", notes = "经营利润核算（参数管理）-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(JylrhsCsgl jylrhsCsgl,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<JylrhsCsgl> queryWrapper = QueryGenerator.initQueryWrapper(jylrhsCsgl, req.getParameterMap());
        IPage pageList = PageUtil.toPage(IJylrhsCsglService.class, jylrhsCsglService, pageNo, pageSize, queryWrapper, "param_no");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param jylrhsCsgl
     * @return
     */
    @AutoLog(value = "经营利润核算（参数管理）-添加")
    @ApiOperation(value = "经营利润核算（参数管理）-添加", notes = "经营利润核算（参数管理）-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody JylrhsCsgl jylrhsCsgl) {
        try {
            //log.info("----经营利润核算 参数管理 主键重复校验----"+jylrhsCsgl.getParamNo());
            QueryWrapper<JylrhsCsgl> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("param_no",jylrhsCsgl.getParamNo());
            JylrhsCsgl record = jylrhsCsglService.getOne(queryWrapper,false);
            if (record == null) {
                jylrhsCsgl.setOprationType("1");
                jylrhsCsgl.setOperator(getLoginUser().getUsername());
                jylrhsCsgl.setOprationTime(new Date());
                jylrhsCsglService.save(jylrhsCsgl);
                return Result.ok("添加成功！");
            } else {
                return Result.error("该参数已存在，请核实！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加失败！"+e.getMessage());
            return Result.error("添加失败！");
        }
    }

    /**
     * 编辑
     *
     * @param jylrhsCsgl
     * @return
     */
    @AutoLog(value = "经营利润核算（参数管理）-编辑")
    @ApiOperation(value = "经营利润核算（参数管理）-编辑", notes = "经营利润核算（参数管理）-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody JylrhsCsgl jylrhsCsgl) {
        UpdateWrapper<JylrhsCsgl> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("param_no",jylrhsCsgl.getParamNo());
        jylrhsCsgl.setParamNo(null);
        jylrhsCsgl.setOprationType("2");
        jylrhsCsgl.setOprationTime(new Date());
        jylrhsCsgl.setOperator(getLoginUser().getUsername());
        jylrhsCsglService.update(jylrhsCsgl,updateWrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 删除
     *
     * @param paramNo
     * @return
     */
    @AutoLog(value = "经营利润核算（参数管理）-删除")
    @ApiOperation(value = "经营利润核算（参数管理）-删除", notes = "经营利润核算（参数管理）-删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "paramNo", required = true) String paramNo) {
        UpdateWrapper<JylrhsCsgl> deleteWrapper = new UpdateWrapper();
        deleteWrapper.eq("param_no",paramNo);
        jylrhsCsglService.remove(deleteWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "经营利润核算（参数管理）-批量删除")
    @ApiOperation(value = "经营利润核算（参数管理）-批量删除", notes = "经营利润核算（参数管理）-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.jylrhsCsglService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "经营利润核算（参数管理）-通过id查询")
    @ApiOperation(value = "经营利润核算（参数管理）-通过id查询", notes = "经营利润核算（参数管理）-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        JylrhsCsgl jylrhsCsgl = jylrhsCsglService.getById(id);
        return Result.ok(jylrhsCsgl);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param jylrhsCsgl
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, JylrhsCsgl jylrhsCsgl) {
        return super.exportXls(request, jylrhsCsgl, JylrhsCsgl.class, "经营利润核算（参数管理）");
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
        return super.importExcel(request, response, JylrhsCsgl.class);
    }

}
