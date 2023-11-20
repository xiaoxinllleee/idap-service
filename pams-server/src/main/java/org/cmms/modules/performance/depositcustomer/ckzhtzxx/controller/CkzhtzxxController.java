package org.cmms.modules.performance.depositcustomer.ckzhtzxx.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.activiti.entity.ActBusiness;
import org.cmms.modules.performance.depositcustomer.ckkhghyj.entity.Ckkhghyjxx;
import org.cmms.modules.performance.depositcustomer.ckkhghyj.service.ICkkhghyjxxService;
import org.cmms.modules.performance.depositcustomer.ckkhspxx.entity.Ckkhspxx;
import org.cmms.modules.performance.depositcustomer.ckzhspxx.entity.Ckzhspxx;
import org.cmms.modules.performance.depositcustomer.ckzhspxx.service.ICkzhspxxService;
import org.cmms.modules.performance.depositcustomer.ckzhtzxx.entity.Ckzhtzxx;
import org.cmms.modules.performance.depositcustomer.ckzhtzxx.service.ICkzhtzxxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.ISysUserService;
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
 * @Description: 存款账户拓展信息
 * @Author: jeecg-boot
 * @Date:   2023-03-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存款账户拓展信息")
@RestController
@RequestMapping("/performance/ckzhtzgl")
public class CkzhtzxxController extends JeecgController<Ckzhtzxx, ICkzhtzxxService> {
	 @Autowired
	 private ICkzhtzxxService ckzhtzxxService;
	 @Autowired
	 private ICkkhghyjxxService ckkhghyjxxService;
	 @Autowired
	 private ICkzhspxxService ckzhspxxService;
	 @Autowired
	 private ISysUserService iSysUserService;
	 /**
	 * 分页列表查询
	 *
	 * @param ckzhtzxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "存款账户拓展信息-分页列表查询")
	@ApiOperation(value="存款账户拓展信息-分页列表查询", notes="存款账户拓展信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ckzhtzxx ckzhtzxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Ckzhtzxx> queryWrapper = QueryGenerator.initQueryWrapper(ckzhtzxx, req.getParameterMap());
		Page<Ckzhtzxx> page = new Page<Ckzhtzxx>(pageNo, pageSize);
		//查询本人有拓展权限的账号
		queryWrapper.like("tzr", getWorkNo());
		IPage<Ckzhtzxx> pageList = ckzhtzxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
	  * 分页列表查询
	  *
	  * @param ckzhtzxx
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "存款账户拓展信息-分页列表查询")
	 @ApiOperation(value="存款账户拓展信息-分页列表查询", notes="存款账户拓展信息-分页列表查询")
	 @GetMapping(value = "/listAll")
	 public Result<?> queryPageListAll(Ckzhtzxx ckzhtzxx,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		 QueryWrapper<Ckzhtzxx> queryWrapper = QueryGenerator.initQueryWrapper(ckzhtzxx, req.getParameterMap());
		 Page<Ckzhtzxx> page = new Page<Ckzhtzxx>(pageNo, pageSize);
		 IPage<Ckzhtzxx> pageList = ckzhtzxxService.page(page, queryWrapper);
		 return Result.ok(pageList);
	 }

	 /**
	  * 分页列表查询
	  *
	  * @param ckzhtzxx
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "存款账户拓展信息-分页列表查询")
	 @ApiOperation(value="存款账户拓展信息-分页列表查询", notes="存款账户拓展信息-分页列表查询")
	 @GetMapping(value = "/dyjlist")
	 public Result<?> queryMyPageList(Ckzhtzxx ckzhtzxx,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		 QueryWrapper<Ckzhtzxx> queryWrapper = QueryGenerator.initQueryWrapper(ckzhtzxx, req.getParameterMap());
		 ckzhtzxx.setTzr(getWorkNo());
		 Page<Ckzhtzxx> page = new Page<Ckzhtzxx>(pageNo, pageSize);
		 IPage<Ckzhtzxx> pageList = ckzhtzxxService.page(page, queryWrapper);
		 return Result.ok(pageList);
	 }
	
	/**
	 * 添加
	 *
	 * @param ckzhtzxx
	 * @return
	 */
	@AutoLog(value = "存款账户拓展信息-添加")
	@ApiOperation(value="存款账户拓展信息-添加", notes="存款账户拓展信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ckzhtzxx ckzhtzxx) {
		ckzhtzxxService.save(ckzhtzxx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param ckzhtzxx
	 * @return
	 */
	@AutoLog(value = "存款账户拓展信息-编辑")
	@ApiOperation(value="存款账户拓展信息-编辑", notes="存款账户拓展信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ckzhtzxx ckzhtzxx) {
		ckzhtzxxService.updateById(ckzhtzxx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款账户拓展信息-通过id删除")
	@ApiOperation(value="存款账户拓展信息-通过id删除", notes="存款账户拓展信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ckzhtzxxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "存款账户拓展信息-批量删除")
	@ApiOperation(value="存款账户拓展信息-批量删除", notes="存款账户拓展信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ckzhtzxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款账户拓展信息-通过id查询")
	@ApiOperation(value="存款账户拓展信息-通过id查询", notes="存款账户拓展信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ckzhtzxx ckzhtzxx = ckzhtzxxService.getById(id);
		return Result.ok(ckzhtzxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ckzhtzxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ckzhtzxx ckzhtzxx) {
	  // Step.1 组装查询条件
	  QueryWrapper<Ckzhtzxx> queryWrapper = QueryGenerator.initQueryWrapper(ckzhtzxx, request.getParameterMap());
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
	  //查询本人有拓展权限的账号
	  queryWrapper.like("tzr", getWorkNo());
	  // Step.2 获取导出数据
	  List<Ckzhtzxx> exportList = service.list(queryWrapper);

	  // Step.3 AutoPoi 导出Excel
	  String title = "存款账户拓展信息";
	  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
	  mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
	  mv.addObject(NormalExcelConstants.CLASS, Ckzhtzxx.class);
	  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
	  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
	  return mv;
  }

	 /**
	  * 导出excel
	  *
	  * @param request
	  * @param ckzhtzxx
	  */
	 @RequestMapping(value = "/exportAllXls")
	 public ModelAndView exportAllXls(HttpServletRequest request, Ckzhtzxx ckzhtzxx) {
		 // Step.1 组装查询条件
		 QueryWrapper<Ckzhtzxx> queryWrapper = QueryGenerator.initQueryWrapper(ckzhtzxx, request.getParameterMap());
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
		 // Step.2 获取导出数据
		 List<Ckzhtzxx> exportList = service.list(queryWrapper);

		 // Step.3 AutoPoi 导出Excel
		 String title = "存款账户拓展信息";
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, Ckzhtzxx.class);
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
      return super.importExcel(request, response, Ckzhtzxx.class);
  }

	 /**
	  * @return
	  */
	 @RequestMapping(value = "/extract" , method = RequestMethod.PUT)
	 public Result<?> extract() {
		 try {
			 ckzhtzxxService.extract();
		 } catch (Exception e) {
			 log.error("提取失败",e.getMessage());
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功!");
	 }


}
