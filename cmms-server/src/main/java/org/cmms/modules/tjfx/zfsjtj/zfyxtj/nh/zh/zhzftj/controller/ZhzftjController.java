package org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.zh.zhzftj.controller;

import java.util.Arrays;
import java.util.List;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.zh.zhzftj.entity.Zhzftj;
import org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.zh.zhzftj.service.IZhzftjService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 支行走访统计
 * @Author: jeecg-boot
 * @Date:   2022-05-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行走访统计")
@RestController
@RequestMapping("/zhzftj/zhzftj")
public class ZhzftjController extends JeecgController<Zhzftj, IZhzftjService> {
	@Autowired
	private IZhzftjService zhzftjService;

	/**
	 * 分页列表查询
	 *
	 * @param zhzftj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行走访统计-分页列表查询")
	@ApiOperation(value="支行走访统计-分页列表查询", notes="支行走访统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zhzftj zhzftj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Zhzftj> queryWrapper = QueryGenerator.initQueryWrapper(zhzftj, req.getParameterMap());
		Page<Zhzftj> page = new Page<Zhzftj>(pageNo, pageSize);
		IPage<Zhzftj> pageList = zhzftjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
@GetMapping(value = "/listRb")
	public Result<?> queryPageListRb(Zhzftj zhzftj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Zhzftj> queryWrapper = QueryGenerator.initQueryWrapper(zhzftj, req.getParameterMap());
		System.out.println(zhzftj);
			queryWrapper.eq("tjwd",zhzftj.getTjwd());
			queryWrapper.eq("khlx",zhzftj.getKhlx());
			queryWrapper.orderByAsc("yxzfpm");
			Page<Zhzftj> page = new Page<Zhzftj>(pageNo, pageSize);
			IPage<Zhzftj> pageList = zhzftjService.page(page, queryWrapper);
			return Result.ok(pageList);


	}
	/**
	 * 添加
	 *
	 * @param zhzftj
	 * @return
	 */
	@AutoLog(value = "支行走访统计-添加")
	@ApiOperation(value="支行走访统计-添加", notes="支行走访统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zhzftj zhzftj) {
		zhzftjService.save(zhzftj);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param zhzftj
	 * @return
	 */
	@AutoLog(value = "支行走访统计-编辑")
	@ApiOperation(value="支行走访统计-编辑", notes="支行走访统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zhzftj zhzftj) {
		zhzftjService.updateById(zhzftj);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行走访统计-通过id删除")
	@ApiOperation(value="支行走访统计-通过id删除", notes="支行走访统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zhzftjService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "支行走访统计-批量删除")
	@ApiOperation(value="支行走访统计-批量删除", notes="支行走访统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zhzftjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行走访统计-通过id查询")
	@ApiOperation(value="支行走访统计-通过id查询", notes="支行走访统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zhzftj zhzftj = zhzftjService.getById(id);
		return Result.ok(zhzftj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zhzftj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zhzftj zhzftj) {
      return super.exportXls(request, zhzftj, Zhzftj.class, "支行走访统计");
  }

  @RequestMapping(value = "/exportXlsnh")
  public ModelAndView exportXlsnh(HttpServletRequest request, HttpServletResponse response,Zhzftj zhzftj) {
  	QueryWrapper<Zhzftj> queryWrapper = new QueryWrapper<>();
	  System.out.println(zhzftj);
	  try {
		  String paramsStr = request.getParameter("paramsStr");
		  if (oConvertUtils.isNotEmpty(paramsStr)){
			  String deString = URLDecoder.decode(paramsStr, "UTF-8");
			  zhzftj = JSON.parseObject(deString, Zhzftj.class);
			  queryWrapper =QueryGenerator.initQueryWrapper(zhzftj,request.getParameterMap());
		  }
	  } catch (UnsupportedEncodingException e) {
		  e.printStackTrace();
	  }
	  queryWrapper.eq("khlx",zhzftj.getKhlx());
	  queryWrapper.eq("tjwd",zhzftj.getTjwd());
	  if ("DD".equals(zhzftj.getTjwd())){
		  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		  List<Zhzftj> list = service.list(queryWrapper);
		  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		  mv.addObject(NormalExcelConstants.FILE_NAME,"农户支行走访(日报)列表");
		  mv.addObject(NormalExcelConstants.CLASS,Zhzftj.class);
		  mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("农户支行走访(日报)列表","导出人"+sysUser.getRealname(),"导出信息"));
		  mv.addObject(NormalExcelConstants.DATA_LIST,list);
		  return mv;
	  }else if ("MM".equals(zhzftj.getTjwd())){
		  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		  List<Zhzftj> list = service.list(queryWrapper);
		  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		  mv.addObject(NormalExcelConstants.FILE_NAME,"农户支行走访(月报)列表");
		  mv.addObject(NormalExcelConstants.CLASS,Zhzftj.class);
		  mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("农户支行走访(月报)列表","导出人"+sysUser.getRealname(),"导出信息"));
		  mv.addObject(NormalExcelConstants.DATA_LIST,list);
		  return mv;
	  }else if ("Q".equals(zhzftj.getTjwd())){
		  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		  List<Zhzftj> list = service.list(queryWrapper);
		  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		  mv.addObject(NormalExcelConstants.FILE_NAME,"农户支行走访(季报)列表");
		  mv.addObject(NormalExcelConstants.CLASS,Zhzftj.class);
		  mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("农户支行走访(季报)列表","导出人"+sysUser.getRealname(),"导出信息"));
		  mv.addObject(NormalExcelConstants.DATA_LIST,list);
		  return mv;
	  }else if ("YYYY".equals(zhzftj.getTjwd())){
		  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		  List<Zhzftj> list = service.list(queryWrapper);
		  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		  mv.addObject(NormalExcelConstants.FILE_NAME,"农户支行走访(年报)列表");
		  mv.addObject(NormalExcelConstants.CLASS,Zhzftj.class);
		  mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("农户支行走访(年报)列表","导出人"+sysUser.getRealname(),"导出信息"));
		  mv.addObject(NormalExcelConstants.DATA_LIST,list);
		  return mv;
	  }else {
		  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		  List<Zhzftj> list = service.list(queryWrapper);
		  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		  mv.addObject(NormalExcelConstants.FILE_NAME,"农户支行走访(累计)列表");
		  mv.addObject(NormalExcelConstants.CLASS,Zhzftj.class);
		  mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("农户支行走访(累计)列表","导出人"+sysUser.getRealname(),"导出信息"));
		  mv.addObject(NormalExcelConstants.DATA_LIST,list);
		  return mv;
	  }
  }

	 @RequestMapping(value = "/exportXlssh")
	 public ModelAndView exportXlssh(HttpServletRequest request, HttpServletResponse response,Zhzftj zhzftj) {
		 QueryWrapper<Zhzftj> queryWrapper = new QueryWrapper<>();
		 System.out.println(zhzftj);
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)){
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 zhzftj = JSON.parseObject(deString, Zhzftj.class);
				 queryWrapper =QueryGenerator.initQueryWrapper(zhzftj,request.getParameterMap());
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }
		 queryWrapper.eq("khlx",zhzftj.getKhlx());
		 queryWrapper.eq("tjwd",zhzftj.getTjwd());
		 if ("DD".equals(zhzftj.getTjwd())){
			 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
			 List<Zhzftj> list = service.list(queryWrapper);
			 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 mv.addObject(NormalExcelConstants.FILE_NAME,"商户支行走访(日报)列表");
			 mv.addObject(NormalExcelConstants.CLASS,Zhzftj.class);
			 mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("商户支行走访(日报)列表","导出人"+sysUser.getRealname(),"导出信息"));
			 mv.addObject(NormalExcelConstants.DATA_LIST,list);
			 return mv;
		 }else if ("MM".equals(zhzftj.getTjwd())){
			 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
			 List<Zhzftj> list = service.list(queryWrapper);
			 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 mv.addObject(NormalExcelConstants.FILE_NAME,"商户支行走访(月报)列表");
			 mv.addObject(NormalExcelConstants.CLASS,Zhzftj.class);
			 mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("商户支行走访(月报)列表","导出人"+sysUser.getRealname(),"导出信息"));
			 mv.addObject(NormalExcelConstants.DATA_LIST,list);
			 return mv;
		 }else if ("Q".equals(zhzftj.getTjwd())){
			 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
			 List<Zhzftj> list = service.list(queryWrapper);
			 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 mv.addObject(NormalExcelConstants.FILE_NAME,"商户支行走访(季报)列表");
			 mv.addObject(NormalExcelConstants.CLASS,Zhzftj.class);
			 mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("商户支行走访(季报)列表","导出人"+sysUser.getRealname(),"导出信息"));
			 mv.addObject(NormalExcelConstants.DATA_LIST,list);
			 return mv;
		 }else if ("YYYY".equals(zhzftj.getTjwd())){
			 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
			 List<Zhzftj> list = service.list(queryWrapper);
			 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 mv.addObject(NormalExcelConstants.FILE_NAME,"商户支行走访(年报)列表");
			 mv.addObject(NormalExcelConstants.CLASS,Zhzftj.class);
			 mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("商户支行走访(年报)列表","导出人"+sysUser.getRealname(),"导出信息"));
			 mv.addObject(NormalExcelConstants.DATA_LIST,list);
			 return mv;
		 }else {
			 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
			 List<Zhzftj> list = service.list(queryWrapper);
			 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 mv.addObject(NormalExcelConstants.FILE_NAME,"商户支行走访(累计)列表");
			 mv.addObject(NormalExcelConstants.CLASS,Zhzftj.class);
			 mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("商户支行走访(累计)列表","导出人"+sysUser.getRealname(),"导出信息"));
			 mv.addObject(NormalExcelConstants.DATA_LIST,list);
			 return mv;
		 }

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
      return super.importExcel(request, response, Zhzftj.class);
  }

}
