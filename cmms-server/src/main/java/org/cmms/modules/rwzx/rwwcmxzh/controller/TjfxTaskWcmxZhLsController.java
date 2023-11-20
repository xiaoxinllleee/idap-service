package org.cmms.modules.rwzx.rwwcmxzh.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.rwzx.rwwcmxzh.entity.TjfxTaskWcmxZh;
import org.cmms.modules.rwzx.rwwcmxzh.entity.TjfxTaskWcmxZhLs;
import org.cmms.modules.rwzx.rwwcmxzh.service.ITjfxTaskWcmxZhLsService;

import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.entity.SysRole;
import org.cmms.modules.system.service.ISysRoleService;
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
 * @Description: 统计分析-任务完成明细_支行_蓝山
 * @Author: jeecg-boot
 * @Date: 2023-11-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "统计分析-任务完成明细_支行_蓝山")
@RestController
@RequestMapping("/rwwcmxzh/tjfxTaskWcmxZhLs")
public class TjfxTaskWcmxZhLsController extends JeecgController<TjfxTaskWcmxZhLs, ITjfxTaskWcmxZhLsService> {
    @Autowired
    private ITjfxTaskWcmxZhLsService tjfxTaskWcmxZhLsService;
	@Autowired
	private ISysRoleService sysRoleService;

    /**
     * 分页列表查询
     *
     * @param tjfxTaskWcmxZhLs
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "统计分析-任务完成明细_支行_蓝山-分页列表查询")
    @ApiOperation(value = "统计分析-任务完成明细_支行_蓝山-分页列表查询", notes = "统计分析-任务完成明细_支行_蓝山-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(TjfxTaskWcmxZhLs tjfxTaskWcmxZhLs,String startDay, String endDay,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        String rwmc = tjfxTaskWcmxZhLs.getRwmc();
        tjfxTaskWcmxZhLs.setRwmc(null);
        tjfxTaskWcmxZhLs.setSszh(null);
        QueryWrapper<TjfxTaskWcmxZhLs> queryWrapper = QueryGenerator.initQueryWrapper(tjfxTaskWcmxZhLs, req.getParameterMap());
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		if (StringUtils.isNotBlank(startDay) && StringUtils.isNotBlank(endDay)) {
			queryWrapper.between("to_char(tjrq,'YYYYMMDD')", startDay, endDay);
		}
		if (StringUtils.isNotBlank(rwmc)){
			queryWrapper.like("rwmc",rwmc);
		}
        Page<TjfxTaskWcmxZhLs> page = new Page<TjfxTaskWcmxZhLs>(pageNo, pageSize);
        IPage<TjfxTaskWcmxZhLs> pageList = tjfxTaskWcmxZhLsService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @GetMapping("/getMaxDate")
    public Result<?> getMaxDate() {
        return Result.ok(tjfxTaskWcmxZhLsService.getMaxDate());
    }

    /**
     * 添加
     *
     * @param tjfxTaskWcmxZhLs
     * @return
     */
    @AutoLog(value = "统计分析-任务完成明细_支行_蓝山-添加")
    @ApiOperation(value = "统计分析-任务完成明细_支行_蓝山-添加", notes = "统计分析-任务完成明细_支行_蓝山-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody TjfxTaskWcmxZhLs tjfxTaskWcmxZhLs) {
        tjfxTaskWcmxZhLsService.save(tjfxTaskWcmxZhLs);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param tjfxTaskWcmxZhLs
     * @return
     */
    @AutoLog(value = "统计分析-任务完成明细_支行_蓝山-编辑")
    @ApiOperation(value = "统计分析-任务完成明细_支行_蓝山-编辑", notes = "统计分析-任务完成明细_支行_蓝山-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody TjfxTaskWcmxZhLs tjfxTaskWcmxZhLs) {
        tjfxTaskWcmxZhLsService.updateById(tjfxTaskWcmxZhLs);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "统计分析-任务完成明细_支行_蓝山-通过id删除")
    @ApiOperation(value = "统计分析-任务完成明细_支行_蓝山-通过id删除", notes = "统计分析-任务完成明细_支行_蓝山-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        tjfxTaskWcmxZhLsService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "统计分析-任务完成明细_支行_蓝山-批量删除")
    @ApiOperation(value = "统计分析-任务完成明细_支行_蓝山-批量删除", notes = "统计分析-任务完成明细_支行_蓝山-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.tjfxTaskWcmxZhLsService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "统计分析-任务完成明细_支行_蓝山-通过id查询")
    @ApiOperation(value = "统计分析-任务完成明细_支行_蓝山-通过id查询", notes = "统计分析-任务完成明细_支行_蓝山-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        TjfxTaskWcmxZhLs tjfxTaskWcmxZhLs = tjfxTaskWcmxZhLsService.getById(id);
        return Result.ok(tjfxTaskWcmxZhLs);
    }

    /**
     * 导出excel
     *
     * @param req
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest req,
                                  TjfxTaskWcmxZhLs tjfxTaskWcmxZhLs,
                                  String startDay,
                                  String endDay) {
        String rwmc = tjfxTaskWcmxZhLs.getRwmc();
        tjfxTaskWcmxZhLs.setRwmc(null);
        tjfxTaskWcmxZhLs.setSszh(null);
        QueryWrapper<TjfxTaskWcmxZhLs> queryWrapper = QueryGenerator.initQueryWrapper(tjfxTaskWcmxZhLs, req.getParameterMap());
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        if (StringUtils.isNotBlank(startDay) && StringUtils.isNotBlank(endDay)) {
            queryWrapper.between("to_char(tjrq,'YYYYMMDD')", startDay, endDay);
        }
        if (StringUtils.isNotBlank(rwmc)){
            queryWrapper.like("rwmc",rwmc);
        }

        List<TjfxTaskWcmxZhLs> exportList = tjfxTaskWcmxZhLsService.list(queryWrapper);

        String title = "支行任务完成情况统计表";
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, TjfxTaskWcmxZhLs.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + loginUser.getRealname(), title));
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
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, TjfxTaskWcmxZhLs.class);
    }

}
