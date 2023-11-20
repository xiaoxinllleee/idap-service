package org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.zh.zhkhjlzftj.controller;

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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.zh.zhkhjlzftj.entity.Zhkhjlzftj;
import org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.zh.zhkhjlzftj.service.IZhkhjlzftjService;
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
 * @Description: 支行客户经理走访统计
 * @Author: jeecg-boot
 * @Date:   2022-05-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行客户经理走访统计")
@RestController
@RequestMapping("/zhzftj/zhkhjlzftj")
public class ZhkhjlzftjController extends JeecgController<Zhkhjlzftj, IZhkhjlzftjService> {
	@Autowired
	private IZhkhjlzftjService zhkhjlzftjService;

	/**
	 * 分页列表查询
	 *
	 * @param zhkhjlzftj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行客户经理走访统计-分页列表查询")
	@ApiOperation(value="支行客户经理走访统计-分页列表查询", notes="支行客户经理走访统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zhkhjlzftj zhkhjlzftj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Zhkhjlzftj> queryWrapper = QueryGenerator.initQueryWrapper(zhkhjlzftj, req.getParameterMap());
		Page<Zhkhjlzftj> page = new Page<Zhkhjlzftj>(pageNo, pageSize);
		IPage<Zhkhjlzftj> pageList = zhkhjlzftjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
@GetMapping(value = "/listRb")
	public Result<?> queryPageListRb(Zhkhjlzftj zhkhjlzftj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Zhkhjlzftj> queryWrapper = QueryGenerator.initQueryWrapper(zhkhjlzftj, req.getParameterMap());
			queryWrapper.eq("tjwd",zhkhjlzftj.getTjwd());
			queryWrapper.eq("khlx",zhkhjlzftj.getKhlx());
			Page<Zhkhjlzftj> page = new Page<Zhkhjlzftj>(pageNo, pageSize);
			IPage<Zhkhjlzftj> pageList = zhkhjlzftjService.page(page, queryWrapper);
			return Result.ok(pageList);


	}
	/**
	 * 添加
	 *
	 * @param zhkhjlzftj
	 * @return
	 */
	@AutoLog(value = "支行客户经理走访统计-添加")
	@ApiOperation(value="支行客户经理走访统计-添加", notes="支行客户经理走访统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zhkhjlzftj zhkhjlzftj) {
		zhkhjlzftjService.save(zhkhjlzftj);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param zhkhjlzftj
	 * @return
	 */
	@AutoLog(value = "支行客户经理走访统计-编辑")
	@ApiOperation(value="支行客户经理走访统计-编辑", notes="支行客户经理走访统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zhkhjlzftj zhkhjlzftj) {
		zhkhjlzftjService.updateById(zhkhjlzftj);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行客户经理走访统计-通过id删除")
	@ApiOperation(value="支行客户经理走访统计-通过id删除", notes="支行客户经理走访统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zhkhjlzftjService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "支行客户经理走访统计-批量删除")
	@ApiOperation(value="支行客户经理走访统计-批量删除", notes="支行客户经理走访统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zhkhjlzftjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行客户经理走访统计-通过id查询")
	@ApiOperation(value="支行客户经理走访统计-通过id查询", notes="支行客户经理走访统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zhkhjlzftj zhzftj = zhkhjlzftjService.getById(id);
		return Result.ok(zhzftj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zhkhjlzftj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zhkhjlzftj zhkhjlzftj) {
      return super.exportXls(request, zhkhjlzftj, Zhkhjlzftj.class, "支行客户经理走访统计");
  }

  @RequestMapping(value = "/exportXlszhzf")
  public ModelAndView exportXlsnh(HttpServletRequest request, HttpServletResponse response,Zhkhjlzftj zhkhjlzftj) {
  	QueryWrapper<Zhkhjlzftj> queryWrapper = new QueryWrapper<>();
	  try {
		  String paramsStr = request.getParameter("paramsStr");
		  if (oConvertUtils.isNotEmpty(paramsStr)){
			  String deString = URLDecoder.decode(paramsStr, "UTF-8");
			  zhkhjlzftj = JSON.parseObject(deString, Zhkhjlzftj.class);
			  queryWrapper =QueryGenerator.initQueryWrapper(zhkhjlzftj,request.getParameterMap());
		  }
	  } catch (UnsupportedEncodingException e) {
		  e.printStackTrace();
	  }
	  if (queryWrapper == null) {
		  queryWrapper = new QueryWrapper<>();
	  }
	  queryWrapper.eq("khlx",zhkhjlzftj.getKhlx());
	  queryWrapper.eq("tjwd",zhkhjlzftj.getTjwd());
	  if ("DD".equals(zhkhjlzftj.getTjwd())){
		  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		  List<Zhkhjlzftj> list = service.list(queryWrapper);
		  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		  mv.addObject(NormalExcelConstants.FILE_NAME,"农户支行客户经理走访(日报)列表");
		  mv.addObject(NormalExcelConstants.CLASS,Zhkhjlzftj.class);
		  mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("农户支行客户经理走访(日报)列表","导出人"+sysUser.getRealname(),"导出信息"));
		  mv.addObject(NormalExcelConstants.DATA_LIST,list);
		  return mv;
	  }else if ("MM".equals(zhkhjlzftj.getTjwd())){
		  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		  List<Zhkhjlzftj> list = service.list(queryWrapper);
		  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		  mv.addObject(NormalExcelConstants.FILE_NAME,"农户支行客户经理走访(月报)列表");
		  mv.addObject(NormalExcelConstants.CLASS,Zhkhjlzftj.class);
		  mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("农户支行客户经理走访(月报)列表","导出人"+sysUser.getRealname(),"导出信息"));
		  mv.addObject(NormalExcelConstants.DATA_LIST,list);
		  return mv;
	  }else if ("Q".equals(zhkhjlzftj.getTjwd())){
		  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		  List<Zhkhjlzftj> list = service.list(queryWrapper);
		  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		  mv.addObject(NormalExcelConstants.FILE_NAME,"农户支行客户经理走访(季报)列表");
		  mv.addObject(NormalExcelConstants.CLASS,Zhkhjlzftj.class);
		  mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("农户支行客户经理走访(季报)列表","导出人"+sysUser.getRealname(),"导出信息"));
		  mv.addObject(NormalExcelConstants.DATA_LIST,list);
		  return mv;
	  }else if ("YYYY".equals(zhkhjlzftj.getTjwd())){
		  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		  List<Zhkhjlzftj> list = service.list(queryWrapper);
		  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		  mv.addObject(NormalExcelConstants.FILE_NAME,"农户支行客户经理走访(年报)列表");
		  mv.addObject(NormalExcelConstants.CLASS,Zhkhjlzftj.class);
		  mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("农户支行客户经理走访(年报)列表","导出人"+sysUser.getRealname(),"导出信息"));
		  mv.addObject(NormalExcelConstants.DATA_LIST,list);
		  return mv;
	  }else {
		  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		  List<Zhkhjlzftj> list = service.list(queryWrapper);
		  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		  mv.addObject(NormalExcelConstants.FILE_NAME,"农户支行客户经理走访(累计)列表");
		  mv.addObject(NormalExcelConstants.CLASS,Zhkhjlzftj.class);
		  mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("农户支行客户经理走访(累计)列表","导出人"+sysUser.getRealname(),"导出信息"));
		  mv.addObject(NormalExcelConstants.DATA_LIST,list);
		  return mv;
	  }
  }

	 @RequestMapping(value = "/exportXlssh")
	 public ModelAndView exportXlssh(HttpServletRequest request, HttpServletResponse response,Zhkhjlzftj zhkhjlzftj) {
		 QueryWrapper<Zhkhjlzftj> queryWrapper = new QueryWrapper<>();
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)){
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 zhkhjlzftj = JSON.parseObject(deString, Zhkhjlzftj.class);
				 queryWrapper =QueryGenerator.initQueryWrapper(zhkhjlzftj,request.getParameterMap());
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }
		 if (queryWrapper == null) {
			 queryWrapper = new QueryWrapper<>();
		 }
		 queryWrapper.eq("khlx",zhkhjlzftj.getKhlx());
		 queryWrapper.eq("tjwd",zhkhjlzftj.getTjwd());
		 if ("DD".equals(zhkhjlzftj.getTjwd())){
			 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
			 List<Zhkhjlzftj> list = service.list(queryWrapper);
			 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 mv.addObject(NormalExcelConstants.FILE_NAME,"商户支行客户经理走访(日报)列表");
			 mv.addObject(NormalExcelConstants.CLASS,Zhkhjlzftj.class);
			 mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("商户支行客户经理走访(日报)列表","导出人"+sysUser.getRealname(),"导出信息"));
			 mv.addObject(NormalExcelConstants.DATA_LIST,list);
			 return mv;
		 }else if ("MM".equals(zhkhjlzftj.getTjwd())){
			 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
			 List<Zhkhjlzftj> list = service.list(queryWrapper);
			 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 mv.addObject(NormalExcelConstants.FILE_NAME,"商户支行客户经理走访(月报)列表");
			 mv.addObject(NormalExcelConstants.CLASS,Zhkhjlzftj.class);
			 mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("商户支行客户经理走访(月报)列表","导出人"+sysUser.getRealname(),"导出信息"));
			 mv.addObject(NormalExcelConstants.DATA_LIST,list);
			 return mv;
		 }else if ("Q".equals(zhkhjlzftj.getTjwd())){
			 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
			 List<Zhkhjlzftj> list = service.list(queryWrapper);
			 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 mv.addObject(NormalExcelConstants.FILE_NAME,"商户支行客户经理走访(季报)列表");
			 mv.addObject(NormalExcelConstants.CLASS,Zhkhjlzftj.class);
			 mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("商户支行客户经理走访(季报)列表","导出人"+sysUser.getRealname(),"导出信息"));
			 mv.addObject(NormalExcelConstants.DATA_LIST,list);
			 return mv;
		 }else if ("YYYY".equals(zhkhjlzftj.getTjwd())){
			 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
			 List<Zhkhjlzftj> list = service.list(queryWrapper);
			 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 mv.addObject(NormalExcelConstants.FILE_NAME,"商户支行客户经理走访(年报)列表");
			 mv.addObject(NormalExcelConstants.CLASS,Zhkhjlzftj.class);
			 mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("商户支行客户经理走访(年报)列表","导出人"+sysUser.getRealname(),"导出信息"));
			 mv.addObject(NormalExcelConstants.DATA_LIST,list);
			 return mv;
		 }else {
			 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
			 List<Zhkhjlzftj> list = service.list(queryWrapper);
			 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 mv.addObject(NormalExcelConstants.FILE_NAME,"商户支行客户经理走访(累计)列表");
			 mv.addObject(NormalExcelConstants.CLASS,Zhkhjlzftj.class);
			 mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("商户支行客户经理走访(累计)列表","导出人"+sysUser.getRealname(),"导出信息"));
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
      return super.importExcel(request, response, Zhkhjlzftj.class);
  }

}
