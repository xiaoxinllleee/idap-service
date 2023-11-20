package org.cmms.modules.hr.xsgl.grtjxs.controller;

import java.util.*;
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
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.hr.xsgl.grtjxs.entity.ErpPersonalTjxs;
import org.cmms.modules.hr.xsgl.grtjxs.service.IErpPersonalTjxsService;
import org.cmms.modules.hr.xsgl.grtjxs.verify.ErpPersonalTjxsImportVerify;
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
 * @Description: 个人调节系数
 * @Author: jeecg-boot
 * @Date:   2023-10-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="个人调节系数")
@RestController
@RequestMapping("/grtjxs/erpPersonalTjxs")
public class ErpPersonalTjxsController extends JeecgController<ErpPersonalTjxs, IErpPersonalTjxsService> {
	@Autowired
	private IErpPersonalTjxsService erpPersonalTjxsService;
	@Autowired
	private ErpPersonalTjxsImportVerify erpPersonalTjxsImportVerify;
	
	/**
	 * 分页列表查询
	 *
	 * @param erpPersonalTjxs
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "个人调节系数-分页列表查询")
	@ApiOperation(value="个人调节系数-分页列表查询", notes="个人调节系数-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ErpPersonalTjxs erpPersonalTjxs, String ygxm,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ErpPersonalTjxs> queryWrapper = QueryGenerator.initQueryWrapper(erpPersonalTjxs, req.getParameterMap());
		if (StringUtils.isNotEmpty(ygxm)) {
			queryWrapper.inSql("yggh", "select yggh from hr_bas_staff where ygxm like '%" + ygxm + "%'");
		}
		Page<ErpPersonalTjxs> page = new Page<ErpPersonalTjxs>(pageNo, pageSize);
		IPage<ErpPersonalTjxs> pageList = erpPersonalTjxsService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param erpPersonalTjxs
	 * @return
	 */
	@AutoLog(value = "个人调节系数-添加")
	@ApiOperation(value="个人调节系数-添加", notes="个人调节系数-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ErpPersonalTjxs erpPersonalTjxs) {
		//生效日期判断 同一区间只能有一条生效记录
		if (!this.checkExist(erpPersonalTjxs)) {
			return Result.error("此生效日期区间已存在此员工有效的系数调节信息");
		}
		erpPersonalTjxsService.save(erpPersonalTjxs);
		return Result.ok("添加成功！");
	}

	 private boolean checkExist(ErpPersonalTjxs erpPersonalTjxs) {
		 QueryWrapper<ErpPersonalTjxs> check = new QueryWrapper<>();
		 check.eq("yggh", erpPersonalTjxs.getYggh());
		 check.eq("tjlx", erpPersonalTjxs.getTjlx());
		 if (StringUtils.isNotEmpty(erpPersonalTjxs.getId())) {
		 	check.ne("id", erpPersonalTjxs.getId());
		 }
		 Date sxrqEnd = erpPersonalTjxs.getSxrqEnd();
		 if (sxrqEnd == null) {
			 sxrqEnd = DateUtil.parseDateFormat("20991231", "YYYYMMDD");
		 }
		 check.le("sxrq_begin", sxrqEnd);
		 check.and((wrapper) -> {
			 wrapper.isNull("sxrq_end").or().ge("sxrq_end", erpPersonalTjxs.getSxrqBegin());
		 });
		 List<ErpPersonalTjxs> list = erpPersonalTjxsService.list(check);
		 if (!list.isEmpty()) {
			 return false;
		 }
		 return true;
	 }
	
	/**
	 * 编辑
	 *
	 * @param erpPersonalTjxs
	 * @return
	 */
	@AutoLog(value = "个人调节系数-编辑")
	@ApiOperation(value="个人调节系数-编辑", notes="个人调节系数-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ErpPersonalTjxs erpPersonalTjxs) {
		//生效日期判断 同一区间只能有一条生效记录
		if (!this.checkExist(erpPersonalTjxs)) {
			return Result.error("此生效日期区间已存在此员工有效的系数调节信息");
		}
		erpPersonalTjxsService.updateById(erpPersonalTjxs);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "个人调节系数-通过id删除")
	@ApiOperation(value="个人调节系数-通过id删除", notes="个人调节系数-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		erpPersonalTjxsService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "个人调节系数-批量删除")
	@ApiOperation(value="个人调节系数-批量删除", notes="个人调节系数-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.erpPersonalTjxsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "个人调节系数-通过id查询")
	@ApiOperation(value="个人调节系数-通过id查询", notes="个人调节系数-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ErpPersonalTjxs erpPersonalTjxs = erpPersonalTjxsService.getById(id);
		return Result.ok(erpPersonalTjxs);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param erpPersonalTjxs
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ErpPersonalTjxs erpPersonalTjxs, String ygxm) {
      String title = "个人调节系数";
	  // Step.1 组装查询条件
	  QueryWrapper<ErpPersonalTjxs> queryWrapper = QueryGenerator.initQueryWrapper(erpPersonalTjxs, request.getParameterMap());
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
	  if (StringUtils.isNotEmpty(ygxm)) {
		  queryWrapper.inSql("yggh", "select yggh from hr_bas_staff where ygxm like '%" + ygxm + "%'");
	  }
	  // Step.2 获取导出数据
	  List<ErpPersonalTjxs> exportList = service.list(queryWrapper);

	  // Step.3 AutoPoi 导出Excel
	  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
	  mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
	  mv.addObject(NormalExcelConstants.CLASS, ErpPersonalTjxs.class);
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
  public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
	  return super.importExcelByTemplate(jsonObject, request, response, ErpPersonalTjxs.class, erpPersonalTjxsImportVerify);
  }

	 /**
	  * 导出模板Excel
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "个人调节系数导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, ErpPersonalTjxs.class);
		 ExportParams exportParams = new ExportParams("个人调节系数导入模板", "个人调节系数");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return mv;
	 }

}
