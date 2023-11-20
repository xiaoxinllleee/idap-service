package org.cmms.modules.rwzx.rwwcmx.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtils;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.rwzx.rwmxsj.entity.EczfSjGlmx;
import org.cmms.modules.rwzx.rwwcmx.entity.TjfxTaskWcmx;
import org.cmms.modules.rwzx.rwwcmx.service.ITjfxTaskWcmxService;
import org.cmms.modules.rwzx.zfyxmx.entity.TaskZfyxmx;
import org.cmms.modules.system.entity.SysRole;
import org.cmms.modules.system.service.ISysRoleService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 统计分析-任务完成明细
 * @Author: jeecg-boot
 * @Date: 2023-08-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "统计分析-任务完成明细")
@RestController
@RequestMapping("/tjfx/taskWcmx")
public class TjfxTaskWcmxController implements Job {
    @Autowired
    private ITjfxTaskWcmxService tjfxTaskWcmxService;
    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * 分页列表查询
     *
     * @param tjfxTaskWcmx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "统计分析-任务完成明细-分页列表查询")
    @ApiOperation(value = "统计分析-任务完成明细-分页列表查询", notes = "统计分析-任务完成明细-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(TjfxTaskWcmx tjfxTaskWcmx, String startDay, String endDay,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        String sszh = tjfxTaskWcmx.getSszh();
        String ygxm = tjfxTaskWcmx.getYgxm();
        tjfxTaskWcmx.setSszh(null);
        tjfxTaskWcmx.setYgxm(null);
        QueryWrapper<TjfxTaskWcmx> queryWrapper = QueryGenerator.initQueryWrapper(tjfxTaskWcmx, req.getParameterMap());
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        SysRole sysRole = sysRoleService.getById(loginUser.getRoles());
        String qxsszh = null;
        if (sysRole != null && StringUtils.isNotBlank(sysRole.getRoleCode()) && !"admin".equals(sysRole.getRoleCode())) {
            qxsszh = loginUser.getOrgCode();
            if (StringUtils.isNotBlank(sszh) && !sszh.equals(loginUser.getOrgCode())) {
                qxsszh = "-1";
            }
        } else {
            qxsszh = sszh;
        }
        if (StringUtils.isNotBlank(qxsszh)) {
            queryWrapper.eq("sszh", qxsszh);
        }
        if (StringUtils.isNotBlank(ygxm)) {
            queryWrapper.inSql("ygxm", "select yggh from Hr_bas_staff where ygxm like '%" + ygxm + "%'");
        }
        if (StringUtils.isNotBlank(startDay) && StringUtils.isNotBlank(endDay)) {
            queryWrapper.between("to_char(tjrq,'YYYYMMDD')", startDay, endDay);
        }
        Page<TjfxTaskWcmx> page = new Page<TjfxTaskWcmx>(pageNo, pageSize);
        IPage<TjfxTaskWcmx> pageList = tjfxTaskWcmxService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @GetMapping("/getMaxDate")
    public Result<?> getMaxDate() {
        return Result.ok(tjfxTaskWcmxService.getMaxDate());
    }


    /**
     * @param lx   明细类型(1-当日走访 2-当日营销成功 3-当日营销失败 4-累计走访 5-累计营销成功 6-累计营销失败)
     * @param yggh 员工工号
     * @param sszh 所属支行
     * @param rwlx 任务类型
     * @param tjrq 统计日期
     * @return
     */
    @GetMapping("/getRwwcmx")
    public Result<?> getRwwcmx(@RequestParam(name = "lx", required = false) String lx,
                               @RequestParam(name = "yggh", required = false) String yggh,
                               @RequestParam(name = "sszh", required = false) String sszh,
                               @RequestParam(name = "rwlx", required = false) String rwlx,
                               @RequestParam(name = "tjrq", required = false) String tjrq,
                               @RequestParam(name = "tjwd", required = false) String tjwd,
                               @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        if (StringUtils.isNotBlank(lx)) {
            Page<TaskZfyxmx> page = new Page<>(pageNo, pageSize);
            IPage<TaskZfyxmx> list = tjfxTaskWcmxService.getRwwcmx(page, lx, yggh, sszh, rwlx, tjrq.replace("-", ""),tjwd);
            return Result.ok(list);
        }
        return Result.ok();
    }

    /**
     * 添加
     *
     * @param tjfxTaskWcmx
     * @return
     */
    @AutoLog(value = "统计分析-任务完成明细-添加")
    @ApiOperation(value = "统计分析-任务完成明细-添加", notes = "统计分析-任务完成明细-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody TjfxTaskWcmx tjfxTaskWcmx) {
        tjfxTaskWcmxService.save(tjfxTaskWcmx);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param tjfxTaskWcmx
     * @return
     */
    @AutoLog(value = "统计分析-任务完成明细-编辑")
    @ApiOperation(value = "统计分析-任务完成明细-编辑", notes = "统计分析-任务完成明细-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody TjfxTaskWcmx tjfxTaskWcmx) {
        tjfxTaskWcmxService.updateById(tjfxTaskWcmx);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "统计分析-任务完成明细-通过id删除")
    @ApiOperation(value = "统计分析-任务完成明细-通过id删除", notes = "统计分析-任务完成明细-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        tjfxTaskWcmxService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "统计分析-任务完成明细-批量删除")
    @ApiOperation(value = "统计分析-任务完成明细-批量删除", notes = "统计分析-任务完成明细-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.tjfxTaskWcmxService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "统计分析-任务完成明细-通过id查询")
    @ApiOperation(value = "统计分析-任务完成明细-通过id查询", notes = "统计分析-任务完成明细-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        TjfxTaskWcmx tjfxTaskWcmx = tjfxTaskWcmxService.getById(id);
        return Result.ok(tjfxTaskWcmx);
    }

    /**
     * 导出excel
     *
     * @param req
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest req,
                                  TjfxTaskWcmx tjfxTaskWcmx,
                                  String startDay,
                                  String endDay) {
        // Step.1 组装查询条件
        String sszh = tjfxTaskWcmx.getSszh();
        String ygxm = tjfxTaskWcmx.getYgxm();
        tjfxTaskWcmx.setSszh(null);
        tjfxTaskWcmx.setYgxm(null);
        QueryWrapper<TjfxTaskWcmx> queryWrapper = QueryGenerator.initQueryWrapper(tjfxTaskWcmx, req.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        SysRole sysRole = sysRoleService.getById(sysUser.getRoles());
        String qxsszh = null;
        if (sysRole != null && StringUtils.isNotBlank(sysRole.getRoleCode()) && !"admin".equals(sysRole.getRoleCode())) {
            qxsszh = sysUser.getOrgCode();
            if (StringUtils.isNotBlank(sszh) && !sszh.equals(sysUser.getOrgCode())) {
                qxsszh = "-1";
            }
        } else {
            qxsszh = sszh;
        }
        if (StringUtils.isNotBlank(qxsszh)) {
            queryWrapper.eq("sszh", qxsszh);
        }
        if (StringUtils.isNotBlank(ygxm)) {
            queryWrapper.inSql("ygxm", "select yggh from Hr_bas_staff where ygxm like '%" + ygxm + "%'");
        }
        if (StringUtils.isNotBlank(startDay) && StringUtils.isNotBlank(endDay)) {
            queryWrapper.between("to_char(tjrq,'YYYYMMDD')", startDay, endDay);
        }

        // Step.2 获取导出数据
        List<TjfxTaskWcmx> exportList = tjfxTaskWcmxService.list(queryWrapper);

        // Step.3 AutoPoi 导出Excel
        String title = "客户经理任务完成情况统计表";
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, TjfxTaskWcmx.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }

    /**
     * 永兴定时任务-任务完成情况统计
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(String.format("开始执行任务完成情况统计-" + DateUtils.getTimestamp()));
        tjfxTaskWcmxService.initRwwcqk(null);
        log.info(String.format("开始执行任务完成情况统计结束end-" + DateUtils.getTimestamp()));
    }

    /**
     * 数据提取
     * @param jsonObject
     * @return
     */
    @PutMapping(value = "/initData")
    public Result<?> initData(@RequestBody JSONObject jsonObject) {
        String tjrq=jsonObject.getString("tjrq");
        if (StringUtils.isBlank(tjrq)){
            return Result.error("参数错误！！");
        }
        try {
            tjfxTaskWcmxService.initRwwcqk(tjrq.replace("-",""));
        } catch (Exception e) {
            log.error(e.getMessage(), "提取失败！");
            return Result.error(e.getMessage());
        }
        return Result.ok("提取成功！");
    }

}
