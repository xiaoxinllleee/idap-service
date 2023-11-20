package org.cmms.modules.khjg.jgkhjg.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khjg.jgkhjg.entity.GwkhjgVO;
import org.cmms.modules.khjg.jgkhjg.entity.Jgkhjg;
import org.cmms.modules.khjg.jgkhjg.entity.JgkhjgVO;
import org.cmms.modules.khjg.jgkhjg.service.IJgkhjgService;
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
 * @Description: 机构考核结果
 * @Author: jeecg-boot
 * @Date:   2023-03-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="机构考核结果")
@RestController
@RequestMapping("/jgkhjg/jgkhjg")
public class JgkhjgController extends JeecgController<Jgkhjg, IJgkhjgService> {
	@Autowired
	private IJgkhjgService jgkhjgService;

	@GetMapping(value = "/list")
	public Result<?> queryPageList(Jgkhjg jgkhjg,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		Date gzrq = jgkhjg.getGzrq();
		jgkhjg.setGzrq(null);
		QueryWrapper<Jgkhjg> queryWrapper = QueryGenerator.initQueryWrapper(jgkhjg, req.getParameterMap());
		if (gzrq != null) {
			Date ymrq = DateUtil.getMonthEndDay(gzrq);
			queryWrapper.ge("gzrq", gzrq);
			queryWrapper.le("gzrq", ymrq);
		}
		Page<Jgkhjg> page = new Page<Jgkhjg>(pageNo, pageSize);
		queryWrapper.isNull("yggh");
		queryWrapper.orderByDesc("gzrq");
		IPage<Jgkhjg> pageList = jgkhjgService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
/**
	 * 分页列表查询
	 *
	 * @param jgkhjg
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "岗位考核结果-分页列表查询")
	@ApiOperation(value="岗位考核结果-分页列表查询", notes="岗位考核结果-分页列表查询")
	@GetMapping(value = "/listGw")
	public Result<?> queryPageListGw(Jgkhjg jgkhjg, @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		return this.queryPageListGw(jgkhjg, pageNo, pageSize, req, false);
	}

	 /**
	  * 分页列表查询
	  *
	  * @param jgkhjg
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "岗位考核结果-分页列表查询")
	 @ApiOperation(value="岗位考核结果-分页列表查询", notes="岗位考核结果-分页列表查询")
	 @GetMapping(value = "/listGwAll")
	 public Result<?> queryPageListGwAll(Jgkhjg jgkhjg, @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
										 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
										 HttpServletRequest req) {
		 return this.queryPageListGw(jgkhjg, pageNo, pageSize, req, true);
	 }

	 private Result<?> queryPageListGw(Jgkhjg jgkhjg, Integer pageNo,
									  Integer pageSize,
									  HttpServletRequest req, boolean queryAll) {
		 Date gzrq = jgkhjg.getGzrq();
		 jgkhjg.setGzrq(null);
		 QueryWrapper<Jgkhjg> queryWrapper = QueryGenerator.initQueryWrapper(jgkhjg, req.getParameterMap());
		 if (gzrq != null) {
			 Date ymrq = DateUtil.getMonthEndDay(gzrq);
			 queryWrapper.ge("gzrq", gzrq);
			 queryWrapper.le("gzrq", ymrq);
		 }

		 Page<Jgkhjg> page = new Page<Jgkhjg>(pageNo, pageSize);
		 queryWrapper.isNotNull("yggh");
		 if (!queryAll) {
			 queryWrapper.eq("yggh", getWorkNo());
		 }
		 queryWrapper.orderByDesc("gzrq");
		 IPage<Jgkhjg> pageList = jgkhjgService.page(page, queryWrapper);
		 return Result.ok(pageList);
	 }

	/**
	 * 添加
	 *
	 * @param jgkhjg
	 * @return
	 */
	@AutoLog(value = "机构考核结果-添加")
	@ApiOperation(value="机构考核结果-添加", notes="机构考核结果-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Jgkhjg jgkhjg) {
		jgkhjgService.save(jgkhjg);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param jgkhjg
	 * @return
	 */
	@AutoLog(value = "机构考核结果-编辑")
	@ApiOperation(value="机构考核结果-编辑", notes="机构考核结果-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Jgkhjg jgkhjg) {
		jgkhjgService.updateById(jgkhjg);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "机构考核结果-通过id删除")
	@ApiOperation(value="机构考核结果-通过id删除", notes="机构考核结果-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		jgkhjgService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "机构考核结果-批量删除")
	@ApiOperation(value="机构考核结果-批量删除", notes="机构考核结果-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.jgkhjgService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "机构考核结果-通过id查询")
	@ApiOperation(value="机构考核结果-通过id查询", notes="机构考核结果-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Jgkhjg jgkhjg = jgkhjgService.getById(id);
		return Result.ok(jgkhjg);
	}

  /**
   * 机构考核结果导出excel
   *
   * @param request
   * @param jgkhjg
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Jgkhjg jgkhjg) {
	  QueryWrapper<Jgkhjg> queryWrapper = QueryGenerator.initQueryWrapper(jgkhjg, request.getParameterMap());
	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	  String selections = request.getParameter("selections");
	  String rowKey = request.getParameter("rowKey");
	  if (oConvertUtils.isNotEmpty(selections)) {
		  List<String> selectionList = Arrays.asList(selections.split(","));
		  if(oConvertUtils.isNotEmpty(rowKey)){
			  queryWrapper.in(rowKey,selectionList);
		  }else{
			  queryWrapper.in("ID",selectionList);
		  }
	  }
	      queryWrapper.isNull("yggh");
		  List<Jgkhjg> exp = service.list(queryWrapper);
		  List<JgkhjgVO> exportList = new ArrayList<>();
		  exp.forEach(e -> {
			  JgkhjgVO jgkhjgVO = new JgkhjgVO();
			  BeanUtil.copyProperties(e, jgkhjgVO);
			  exportList.add(jgkhjgVO);
		  });
		  String title = "机构考核结果";
		  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		  mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
		  mv.addObject(NormalExcelConstants.CLASS, JgkhjgVO.class);
		  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
		  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		  return mv;
	  }

	 /**
	  * 岗位导出excel
	  *
	  * @param request
	  * @param jgkhjg
	  */
	 @RequestMapping(value = "/exportXlsGw")
	 public ModelAndView exportXlsGw(HttpServletRequest request, Jgkhjg jgkhjg) {
	 	return this.exportXlsGw(request, jgkhjg, false);
	 }

	 /**
	  * 岗位导出excel
	  *
	  * @param request
	  * @param jgkhjg
	  */
	 @RequestMapping(value = "/exportXlsGwAll")
	 public ModelAndView exportXlsGwAll(HttpServletRequest request, Jgkhjg jgkhjg) {
		 return this.exportXlsGw(request, jgkhjg, true);
	 }

	 /**
	  * 岗位导出excel
	  *
	  * @param request
	  * @param jgkhjg
	  */
	 private ModelAndView exportXlsGw(HttpServletRequest request, Jgkhjg jgkhjg, boolean exportAll) {
		 QueryWrapper<Jgkhjg> queryWrapper = QueryGenerator.initQueryWrapper(jgkhjg, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String selections = request.getParameter("selections");
		 String rowKey = request.getParameter("rowKey");
		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 if(oConvertUtils.isNotEmpty(rowKey)){
				 queryWrapper.in(rowKey,selectionList);
			 }else{
				 queryWrapper.in("ID",selectionList);
			 }
		 }
		 queryWrapper.isNotNull("yggh");
		 if (!exportAll) {
			 queryWrapper.eq("yggh", getWorkNo());
		 }
		 List<Jgkhjg> exp = service.list(queryWrapper);
		 List<GwkhjgVO> exportList = new ArrayList<>();
		 exp.forEach(e -> {
			 GwkhjgVO gwkhjgVO = new GwkhjgVO();
			 BeanUtil.copyProperties(e, gwkhjgVO);
			 exportList.add(gwkhjgVO);
		 });
		 String title = "岗位考核结果";
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, GwkhjgVO.class);
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
      return super.importExcel(request, response, Jgkhjg.class);
  }

}
