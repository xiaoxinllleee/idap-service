package org.cmms.modules.rwzx.rwwcmx.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.rwzx.rwwcmx.entity.TjfxTaskWcmx;
import org.cmms.modules.rwzx.rwwcmx.entity.TjfxTaskWcmxLs;
import org.cmms.modules.rwzx.rwwcmx.service.ITjfxTaskWcmxLsService;

import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.rwzx.zfyxmx.entity.TaskZfyxmx;
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
 * @Description: 统计分析-任务完成明细_蓝山
 * @Author: jeecg-boot
 * @Date: 2023-11-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "统计分析-任务完成明细_蓝山")
@RestController
@RequestMapping("/rwwcmx/tjfxTaskWcmxLs")
public class TjfxTaskWcmxLsController extends JeecgController<TjfxTaskWcmxLs, ITjfxTaskWcmxLsService> {
    @Autowired
    private ITjfxTaskWcmxLsService tjfxTaskWcmxLsService;
    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * 分页列表查询
     *
     * @param tjfxTaskWcmxLs
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "统计分析-任务完成明细_蓝山-分页列表查询")
    @ApiOperation(value = "统计分析-任务完成明细_蓝山-分页列表查询", notes = "统计分析-任务完成明细_蓝山-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(TjfxTaskWcmxLs tjfxTaskWcmxLs, String startDay, String endDay,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        String ygxm = tjfxTaskWcmxLs.getYggh();
        String rwmc = tjfxTaskWcmxLs.getRwmc();
        tjfxTaskWcmxLs.setSszh(null);
        tjfxTaskWcmxLs.setYggh(null);
        tjfxTaskWcmxLs.setRwmc(null);
        QueryWrapper<TjfxTaskWcmxLs> queryWrapper = QueryGenerator.initQueryWrapper(tjfxTaskWcmxLs, req.getParameterMap());
        if (StringUtils.isNotBlank(ygxm)) {
            queryWrapper.inSql("yggh", "select yggh from Hr_bas_staff where ygxm like '%" + ygxm + "%'");
        }
        if (StringUtils.isNotBlank(startDay) && StringUtils.isNotBlank(endDay)) {
            queryWrapper.between("to_char(tjrq,'YYYYMMDD')", startDay, endDay);
        }
        if (StringUtils.isNotBlank(rwmc)) {
            queryWrapper.like("rwmc", rwmc);
        }
        Page<TjfxTaskWcmxLs> page = new Page<TjfxTaskWcmxLs>(pageNo, pageSize);
        IPage<TjfxTaskWcmxLs> pageList = tjfxTaskWcmxLsService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @GetMapping("/getMaxDate")
    public Result<?> getMaxDate() {
        return Result.ok(tjfxTaskWcmxLsService.getMaxDate());
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
			IPage<TaskZfyxmx> list = tjfxTaskWcmxLsService.getRwwcmx(page, lx, yggh, sszh, rwlx, tjrq.replace("-", ""),tjwd);
			return Result.ok(list);
		}
		return Result.ok();
	}

    /**
     * 添加
     *
     * @param tjfxTaskWcmxLs
     * @return
     */
    @AutoLog(value = "统计分析-任务完成明细_蓝山-添加")
    @ApiOperation(value = "统计分析-任务完成明细_蓝山-添加", notes = "统计分析-任务完成明细_蓝山-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody TjfxTaskWcmxLs tjfxTaskWcmxLs) {
        tjfxTaskWcmxLsService.save(tjfxTaskWcmxLs);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param tjfxTaskWcmxLs
     * @return
     */
    @AutoLog(value = "统计分析-任务完成明细_蓝山-编辑")
    @ApiOperation(value = "统计分析-任务完成明细_蓝山-编辑", notes = "统计分析-任务完成明细_蓝山-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody TjfxTaskWcmxLs tjfxTaskWcmxLs) {
        tjfxTaskWcmxLsService.updateById(tjfxTaskWcmxLs);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "统计分析-任务完成明细_蓝山-通过id删除")
    @ApiOperation(value = "统计分析-任务完成明细_蓝山-通过id删除", notes = "统计分析-任务完成明细_蓝山-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        tjfxTaskWcmxLsService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "统计分析-任务完成明细_蓝山-批量删除")
    @ApiOperation(value = "统计分析-任务完成明细_蓝山-批量删除", notes = "统计分析-任务完成明细_蓝山-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.tjfxTaskWcmxLsService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "统计分析-任务完成明细_蓝山-通过id查询")
    @ApiOperation(value = "统计分析-任务完成明细_蓝山-通过id查询", notes = "统计分析-任务完成明细_蓝山-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        TjfxTaskWcmxLs tjfxTaskWcmxLs = tjfxTaskWcmxLsService.getById(id);
        return Result.ok(tjfxTaskWcmxLs);
    }

    /**
     * 导出excel
     *
     * @param req
     * @param tjfxTaskWcmxLs
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest req,
                                  TjfxTaskWcmxLs tjfxTaskWcmxLs,
                                  String startDay,
                                  String endDay) {
        String ygxm = tjfxTaskWcmxLs.getYggh();
        String rwmc = tjfxTaskWcmxLs.getRwmc();
        tjfxTaskWcmxLs.setRwmc(null);
        tjfxTaskWcmxLs.setSszh(null);
        tjfxTaskWcmxLs.setYggh(null);
        QueryWrapper<TjfxTaskWcmxLs> queryWrapper = QueryGenerator.initQueryWrapper(tjfxTaskWcmxLs, req.getParameterMap());
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        if (StringUtils.isNotBlank(ygxm)) {
            queryWrapper.inSql("yggh", "select yggh from Hr_bas_staff where ygxm like '%" + ygxm + "%'");
        }
        if (StringUtils.isNotBlank(startDay) && StringUtils.isNotBlank(endDay)) {
            queryWrapper.between("to_char(tjrq,'YYYYMMDD')", startDay, endDay);
        }
        if (StringUtils.isNotBlank(rwmc)) {
            queryWrapper.like("rwmc", rwmc);
        }

        List<TjfxTaskWcmxLs> exportList = tjfxTaskWcmxLsService.list(queryWrapper);
        String title = "客户经理任务完成情况统计表";
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, TjfxTaskWcmxLs.class);
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
        return super.importExcel(request, response, TjfxTaskWcmxLs.class);
    }

    /**
     * 数据提取
     *
     * @param jsonObject
     * @return
     */
    @PutMapping(value = "/initData")
    public Result<?> initData(@RequestBody JSONObject jsonObject) {
        String tjrq = jsonObject.getString("tjrq");
        if (StringUtils.isBlank(tjrq)) {
            return Result.error("参数错误！！");
        }
        try {
            tjfxTaskWcmxLsService.initRwwcqk(tjrq.replace("-", ""));
        } catch (Exception e) {
            log.error(e.getMessage(), "提取失败！");
            return Result.error(e.getMessage());
        }
        return Result.ok("提取成功！");
    }

}
