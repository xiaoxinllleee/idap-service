package org.cmms.modules.khjg.yggzhz.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khjg.yggzhz.entity.Yggzhz;
import org.cmms.modules.khjg.yggzhz.service.IYggzhzService;

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
 * @Description: 员工工资汇总
 * @Author: penghr
 * @Date: 2023-03-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "员工工资汇总")
@RestController
@RequestMapping("/yggzhz")
public class YggzhzController extends JeecgController<Yggzhz, IYggzhzService> {
    @Autowired
    private IYggzhzService yggzhzService;

    /**
     * 分页列表查询
     *
     * @param yggzhz
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "员工工资汇总-分页列表查询")
    @ApiOperation(value = "员工工资汇总-分页列表查询", notes = "员工工资汇总-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Yggzhz yggzhz,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        return this.queryPageList(yggzhz, pageNo, pageSize, req, false);
    }

    /**
     * 分页列表查询
     *
     * @param yggzhz
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "员工工资汇总-分页列表查询")
    @ApiOperation(value = "员工工资汇总-分页列表查询", notes = "员工工资汇总-分页列表查询")
    @GetMapping(value = "/listAll")
    public Result<?> queryPageListAll(Yggzhz yggzhz,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        return this.queryPageList(yggzhz, pageNo, pageSize, req, true);
    }

    private Result<?> queryPageList(Yggzhz yggzhz, Integer pageNo, Integer pageSize, HttpServletRequest req, boolean queryAll) {
        Date gzrq = yggzhz.getGzrq();
        yggzhz.setGzrq(null);
        QueryWrapper<Yggzhz> queryWrapper = QueryGenerator.initQueryWrapper(yggzhz, req.getParameterMap());
        if (gzrq != null) {
            Date ymrq = DateUtil.getMonthEndDay(gzrq);
            queryWrapper.ge("gzrq", gzrq);
            queryWrapper.le("gzrq", ymrq);
        }
        if (!queryAll) {
            queryWrapper.eq("yggh", getWorkNo());
        }
        Page<Yggzhz> page = new Page<Yggzhz>(pageNo, pageSize);
        queryWrapper.orderByDesc("gzrq");
        queryWrapper.orderByAsc("zzbz", "gwbz",  "yggh");
        IPage<Yggzhz> pageList = yggzhzService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param yggzhz
     * @return
     */
    /*@AutoLog(value = "员工工资汇总-添加")
    @ApiOperation(value = "员工工资汇总-添加", notes = "员工工资汇总-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Yggzhz yggzhz) {
        yggzhzService.save(yggzhz);
        return Result.ok("添加成功！");
    }*/

    /**
     * 编辑
     *
     * @param yggzhz
     * @return
     */
    /*@AutoLog(value = "员工工资汇总-编辑")
    @ApiOperation(value = "员工工资汇总-编辑", notes = "员工工资汇总-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Yggzhz yggzhz) {
        yggzhzService.updateById(yggzhz);
        return Result.ok("编辑成功!");
    }*/

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    /*@AutoLog(value = "员工工资汇总-通过id删除")
    @ApiOperation(value = "员工工资汇总-通过id删除", notes = "员工工资汇总-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        yggzhzService.removeById(id);
        return Result.ok("删除成功!");
    }*/

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    /*@AutoLog(value = "员工工资汇总-批量删除")
    @ApiOperation(value = "员工工资汇总-批量删除", notes = "员工工资汇总-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.yggzhzService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }*/

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    /*@AutoLog(value = "员工工资汇总-通过id查询")
    @ApiOperation(value = "员工工资汇总-通过id查询", notes = "员工工资汇总-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Yggzhz yggzhz = yggzhzService.getById(id);
        return Result.ok(yggzhz);
    }*/

    /**
     * 导出excel
     *
     * @param request
     * @param yggzhz
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Yggzhz yggzhz) {
        return this.exportXls(request, yggzhz, false);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param yggzhz
     */
    @RequestMapping(value = "/exportXlsAll")
    public ModelAndView exportXlsAll(HttpServletRequest request, Yggzhz yggzhz) {
        return this.exportXls(request, yggzhz, true);
    }

    private ModelAndView exportXls(HttpServletRequest request, Yggzhz yggzhz, boolean exportAll) {
        String title = "员工工资汇总";
        // Step.1 组装查询条件
        QueryWrapper<Yggzhz> queryWrapper = QueryGenerator.initQueryWrapper(yggzhz, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String selections = request.getParameter("selections");
        String rowKey = request.getParameter("rowKey");

        //20211201 过滤选中数据
        //20211201 BY LIUXIANGQUN 过滤选择的数据改为执行接sql  in  查询
        if (oConvertUtils.isNotEmpty(selections)) {
            List<String> selectionList = Arrays.asList(selections.split(","));
            if(oConvertUtils.isNotEmpty(rowKey)){
                queryWrapper.in(rowKey,selectionList);
            }else{
                queryWrapper.in("ID",selectionList);
            }
        }

        if (!exportAll) {
            queryWrapper.eq("yggh", getWorkNo());
        }
        // Step.2 获取导出数据

        List<Yggzhz> exportList = service.list(queryWrapper);

        // Step.3 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, Yggzhz.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    /*@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Yggzhz.class);
    }*/

}
