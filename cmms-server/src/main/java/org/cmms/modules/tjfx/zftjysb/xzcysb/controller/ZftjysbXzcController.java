package org.cmms.modules.tjfx.zftjysb.xzcysb.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;

import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.zftjysb.rwsz.entity.ZftjysbRwsz;
import org.cmms.modules.tjfx.zftjysb.rwsz.service.IZftjysbRwszService;
import org.cmms.modules.tjfx.zftjysb.xzcysb.entity.ZftjysbXzc;
import org.cmms.modules.tjfx.zftjysb.xzcysb.entity.ZftjysbYxkh;
import org.cmms.modules.tjfx.zftjysb.xzcysb.service.IZftjysbXzcService;
import org.cmms.modules.tjfx.zftjysb.xzcysb.service.IZftjysbYxkhService;
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
 * @Description: 走访统计验收表-行政村
 * @Author: jeecg-boot
 * @Date:   2021-05-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="走访统计验收表-行政村")
@RestController
@RequestMapping("/tjfx/zftjysb/xzcysb")
public class ZftjysbXzcController extends JeecgController<ZftjysbXzc, IZftjysbXzcService> implements Job {
	@Autowired
	private IZftjysbXzcService zftjysbXzcService;
	@Autowired
	private IZftjysbRwszService zftjysbRwszService;
	@Autowired
	private IZftjysbYxkhService zftjysbYxkhService;
	/**
	 * 分页列表查询
	 *
	 * @param zftjysbXzc
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "走访统计验收表-行政村-分页列表查询")
	@ApiOperation(value="走访统计验收表-行政村-分页列表查询", notes="走访统计验收表-行政村-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZftjysbXzc zftjysbXzc,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ZftjysbXzc> queryWrapper = QueryGenerator.initQueryWrapper(zftjysbXzc, req.getParameterMap());
		Date maxDate = zftjysbXzcService.getMaxDate();
		queryWrapper.eq("tjrq", maxDate);
		Page<ZftjysbXzc> page = new Page<ZftjysbXzc>(pageNo, pageSize);
		IPage<ZftjysbXzc> pageList = zftjysbXzcService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
	  * 分页列表查询
	  *
	  * @param zftjysbXzc
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "走访统计验收表-行政村-分页列表查询")
	 @ApiOperation(value="走访统计验收表-行政村-分页列表查询", notes="走访统计验收表-行政村-分页列表查询")
	 @GetMapping(value = "/tasksetList")
	 public Result<?> tasksetList(ZftjysbXzc zftjysbXzc,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		 QueryWrapper<ZftjysbXzc> queryWrapper = QueryGenerator.initQueryWrapper(zftjysbXzc, req.getParameterMap());
		 Date maxDate = zftjysbXzcService.getMaxDate();
		 //根据登陆用户所在机构查询对应的任务（村）
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 QueryWrapper<ZftjysbRwsz> rwszQueryWrapper = new QueryWrapper<>();
		 rwszQueryWrapper.eq("zzbz", sysUser.getOrgCode());
		 List<ZftjysbRwsz> rwszList = zftjysbRwszService.list(rwszQueryWrapper);
		 if (rwszList.isEmpty()) {
		 	return Result.error("未设置任务数据");
		 }
		 List<String> xzcList = rwszList.stream().map(ZftjysbRwsz::getXzc).collect(Collectors.toList());
		 queryWrapper.eq("tjrq", maxDate);
		 queryWrapper.in("xzc", xzcList);
		 Page<ZftjysbXzc> page = new Page<ZftjysbXzc>(pageNo, pageSize);
		 IPage<ZftjysbXzc> pageList = zftjysbXzcService.page(page, queryWrapper);
		 return Result.ok(pageList);
	 }

	/**
	 * 添加
	 *
	 * @param zftjysbXzc
	 * @return
	 */
	@AutoLog(value = "走访统计验收表-行政村-添加")
	@ApiOperation(value="走访统计验收表-行政村-添加", notes="走访统计验收表-行政村-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZftjysbXzc zftjysbXzc) {
		zftjysbXzcService.save(zftjysbXzc);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param zftjysbXzc
	 * @return
	 */
	@AutoLog(value = "走访统计验收表-行政村-编辑")
	@ApiOperation(value="走访统计验收表-行政村-编辑", notes="走访统计验收表-行政村-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZftjysbXzc zftjysbXzc) {
		zftjysbXzcService.updateById(zftjysbXzc);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "走访统计验收表-行政村-通过id删除")
	@ApiOperation(value="走访统计验收表-行政村-通过id删除", notes="走访统计验收表-行政村-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zftjysbXzcService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "走访统计验收表-行政村-批量删除")
	@ApiOperation(value="走访统计验收表-行政村-批量删除", notes="走访统计验收表-行政村-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zftjysbXzcService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "走访统计验收表-行政村-通过id查询")
	@ApiOperation(value="走访统计验收表-行政村-通过id查询", notes="走访统计验收表-行政村-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZftjysbXzc zftjysbXzc = zftjysbXzcService.getById(id);
		return Result.ok(zftjysbXzc);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zftjysbXzc
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ZftjysbXzc zftjysbXzc) {
	  // Step.1 组装查询条件
	  QueryWrapper<ZftjysbXzc> queryWrapper = QueryGenerator.initQueryWrapper(zftjysbXzc, request.getParameterMap());
	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	  Date maxDate = zftjysbXzcService.getMaxDate();
	  queryWrapper.eq("tjrq", maxDate);
	  // Step.2 获取导出数据
	  List<ZftjysbXzc> pageList = service.list(queryWrapper);
	  List<ZftjysbXzc> exportList = pageList;

	  // Step.3 AutoPoi 导出Excel
	  String title = "走访统计验收表-行政村";
	  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
	  mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
	  mv.addObject(NormalExcelConstants.CLASS, ZftjysbXzc.class);
	  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
	  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
	  return mv;
  }

	 /**
	  * 导出excel
	  *
	  * @param request
	  * @param zftjysbXzc
	  */
	 @RequestMapping(value = "/exportTasksetXls")
	 public ModelAndView exportTasksetXls(HttpServletRequest request, ZftjysbXzc zftjysbXzc) {
		 // Step.1 组装查询条件
		 QueryWrapper<ZftjysbXzc> queryWrapper = QueryGenerator.initQueryWrapper(zftjysbXzc, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 Date maxDate = zftjysbXzcService.getMaxDate();
		 queryWrapper.eq("tjrq", maxDate);
		 //根据登陆用户所在机构查询对应的任务（村）
		 QueryWrapper<ZftjysbRwsz> rwszQueryWrapper = new QueryWrapper<>();
		 rwszQueryWrapper.eq("zzbz", sysUser.getOrgCode());
		 List<ZftjysbRwsz> rwszList = zftjysbRwszService.list(rwszQueryWrapper);
		 List<ZftjysbXzc> exportList = new ArrayList<>();
		 if (!rwszList.isEmpty()) {
			 List<String> xzcList = rwszList.stream().map(ZftjysbRwsz::getXzc).collect(Collectors.toList());
			 queryWrapper.in("xzc", xzcList);
			 // Step.2 获取导出数据
			 exportList = service.list(queryWrapper);
		 }
		 // Step.3 AutoPoi 导出Excel
		 String title = "走访统计验收表-行政村";
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, ZftjysbXzc.class);
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
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, ZftjysbXzc.class);
  }

  	 @GetMapping(value = "/yxkh")
	 public Result<?> yxkh(@RequestParam(name="ssxz") String ssxz,
						   @RequestParam(name="xzc") String xzc) {
  	     QueryWrapper<ZftjysbYxkh> queryWrapper = new QueryWrapper<>();
  	     queryWrapper.eq("ssxz", ssxz);
  	     queryWrapper.eq("xzc", xzc);
		 List<ZftjysbYxkh> list = zftjysbYxkhService.list(queryWrapper);
  	     return Result.ok(list);
	 }

	 /**
	  * 导出excel
	  *
	  * @param request
	  * @param zftjysbYxkh
	  */
	 @RequestMapping(value = "/exportYxkhXls")
	 public ModelAndView exportYxkhXls(HttpServletRequest request, ZftjysbYxkh zftjysbYxkh) {
		 // Step.1 组装查询条件
		 QueryWrapper<ZftjysbYxkh> queryWrapper = QueryGenerator.initQueryWrapper(zftjysbYxkh, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 // Step.2 获取导出数据
		 List<ZftjysbYxkh> pageList = zftjysbYxkhService.list(queryWrapper);

		 // Step.3 AutoPoi 导出Excel
		 String title = "走访统计验收表-用信客户";
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, ZftjysbYxkh.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		 return mv;
	 }

	 /**
	  * 全行商户走访数据统计-提取
	  * @return
	  */
	 @GetMapping(value = "/init")
	 public Result<?> init() {
		 try {
			 zftjysbXzcService.init();
		 } catch (Exception e) {
			 log.error(e.getMessage(), "提取失败！");
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功！");
	 }

	 /**
	  * 走访验收统计表定时任务抽取.
	  * @param context
	  * @throws JobExecutionException
	  */
	 @Override
	 public void execute(JobExecutionContext context) throws JobExecutionException {
		 //System.out.println("统计分析 - 统计报表 - 走访验收统计表:定时任务执行时间 - "+new Date());
		 zftjysbXzcService.init();
	 }
 }
