package org.cmms.modules.khjg.gwgzhz.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.DictModel;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khjg.gwgzhz.entity.Gwgzhz;
import org.cmms.modules.khjg.gwgzhz.service.IGwgzhzService;
import org.cmms.modules.system.service.ISysDictService;
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
 * @Description: 岗位工资汇总
 * @Author: jeecg-boot
 * @Date:   2023-03-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="岗位工资汇总")
@RestController
@RequestMapping("/gwgzhz/gwgzhz")
public class GwgzhzController extends JeecgController<Gwgzhz, IGwgzhzService> {
	@Autowired
	private IGwgzhzService gwgzhzService;
	@Autowired
	private ISysDictService sysDictService;

	 /**
	  * 分页列表查询
	  *
	  * @param gwgzhz
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "岗位工资汇总-分页列表查询")
	 @ApiOperation(value="岗位工资汇总-分页列表查询", notes="岗位工资汇总-分页列表查询")
	 @GetMapping(value = "/list")
	 public Result<?> queryPageList(Gwgzhz gwgzhz,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
	 	 return this.queryPageList(gwgzhz, pageNo, pageSize, req, false);
	 }

	 /**
	  * 分页列表查询
	  *
	  * @param gwgzhz
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "岗位工资汇总-分页列表查询")
	 @ApiOperation(value="岗位工资汇总-分页列表查询", notes="岗位工资汇总-分页列表查询")
	 @GetMapping(value = "/listAll")
	 public Result<?> queryPageListAll(Gwgzhz gwgzhz,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		 return this.queryPageList(gwgzhz, pageNo, pageSize, req, true);
	 }

	private Result<?> queryPageList(Gwgzhz gwgzhz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req, boolean  queryAll) {
		Date gzrq = gwgzhz.getGzrq();
		gwgzhz.setGzrq(null);
		QueryWrapper<Gwgzhz> queryWrapper = QueryGenerator.initQueryWrapper(gwgzhz, req.getParameterMap());
		if (gzrq != null) {
			Date ymrq = DateUtil.getMonthEndDay(gzrq);
			queryWrapper.ge("gzrq", gzrq);
			queryWrapper.le("gzrq", ymrq);
		}
		Page<Gwgzhz> page = new Page<Gwgzhz>(pageNo, pageSize);
		queryWrapper.isNotNull("yggh");
		if (!queryAll) {
			queryWrapper.eq("yggh", getWorkNo());
		}
		queryWrapper.orderByDesc("gzrq");
		queryWrapper.orderByAsc("zzbz", "gwbz",  "yggh");
		IPage<Gwgzhz> pageList = gwgzhzService.page(page, queryWrapper);
		return Result.ok(pageList);
	}



	/**
	 * 添加
	 *
	 * @param gwgzhz
	 * @return
	 */
	@AutoLog(value = "岗位工资汇总-添加")
	@ApiOperation(value="岗位工资汇总-添加", notes="岗位工资汇总-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Gwgzhz gwgzhz) {
		gwgzhzService.save(gwgzhz);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param gwgzhz
	 * @return
	 */
	@AutoLog(value = "岗位工资汇总-编辑")
	@ApiOperation(value="岗位工资汇总-编辑", notes="岗位工资汇总-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Gwgzhz gwgzhz) {
		gwgzhzService.updateById(gwgzhz);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "岗位工资汇总-通过id删除")
	@ApiOperation(value="岗位工资汇总-通过id删除", notes="岗位工资汇总-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		gwgzhzService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "岗位工资汇总-批量删除")
	@ApiOperation(value="岗位工资汇总-批量删除", notes="岗位工资汇总-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.gwgzhzService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "岗位工资汇总-通过id查询")
	@ApiOperation(value="岗位工资汇总-通过id查询", notes="岗位工资汇总-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Gwgzhz gwgzhz = gwgzhzService.getById(id);
		return Result.ok(gwgzhz);
	}

	 /**
	  * 导出excel
	  *
	  * @param request
	  * @param gwgzhz
	  */
	 @RequestMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, Gwgzhz gwgzhz) {
	     return this.exportXls(request, gwgzhz, false);
	 }

	 /**
	  * 导出excel
	  *
	  * @param request
	  * @param gwgzhz
	  */
	 @RequestMapping(value = "/exportXlsAll")
	 public ModelAndView exportXlsAll(HttpServletRequest request, Gwgzhz gwgzhz) {
		 return this.exportXls(request, gwgzhz, true);
	 }

  /**
   * 导出excel
   *
   * @param request
   * @param gwgzhz
   */
  private ModelAndView exportXls(HttpServletRequest request, Gwgzhz gwgzhz, boolean exportAll) {
  	  //获取导出的表头
	  StringBuffer exportFields = new StringBuffer("gzrq,schemeId,zzbz,gwbz,yggh,");
	  List<DictModel> dictModelList = sysDictService.queryDictItemsByCode("zblb");
	  Map<String, String> gzlbFileds = new HashMap<>();
	  gzlbFileds.put("1", "ckgz");
	  gzlbFileds.put("2", "dkgz");
	  gzlbFileds.put("3", "dzyhgz");
	  gzlbFileds.put("4", "ywlgz");
	  gzlbFileds.put("5", "gljxgz");
	  gzlbFileds.put("6", "dqbcgz");
	  gzlbFileds.put("8", "jymbgz");
	  gzlbFileds.put("7", "qtgz");
	  dictModelList.forEach(dictModel -> {
	  	String field = gzlbFileds.get(dictModel.getValue());
		  if (StringUtils.isNotEmpty(field)) {
		  	exportFields.append(field).append(",");
		  }
	  });
	  exportFields.append("gzhj,zhgzpm,qhgzpm,createTime");
	  String title = "岗位工资汇总";
	  // Step.1 组装查询条件
	  QueryWrapper<Gwgzhz> queryWrapper = QueryGenerator.initQueryWrapper(gwgzhz, request.getParameterMap());
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
	  queryWrapper.isNotNull("yggh");
	  if (!exportAll) {
	  	queryWrapper.eq("yggh", getWorkNo());
	  }
	  // Step.2 获取导出数据
	  //List<T> pageList = service.list(queryWrapper);
	  //List<T> exportList = null;
	  List<Gwgzhz> exportList = service.list(queryWrapper);

	  // Step.3 AutoPoi 导出Excel
	  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
	  mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
	  mv.addObject(NormalExcelConstants.CLASS, Gwgzhz.class);
	  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
	  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
	  if (org.apache.commons.lang3.StringUtils.isNotEmpty(exportFields)) {
		  mv.addObject(NormalExcelConstants.EXPORT_FIELDS, exportFields);
	  }
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
      return super.importExcel(request, response, Gwgzhz.class);
  }

}
