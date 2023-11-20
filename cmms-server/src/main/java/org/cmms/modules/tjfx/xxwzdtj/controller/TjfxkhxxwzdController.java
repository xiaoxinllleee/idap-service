package org.cmms.modules.tjfx.xxwzdtj.controller;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.xxwzdtj.entity.Tjfxkhxxwzd;
import org.cmms.modules.tjfx.xxwzdtj.service.ITjfxkhxxwzdService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

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
 * @Description: 客户信息完整度统计
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户信息完整度统计")
@RestController
@RequestMapping("/tjfx.xxwzdtj/tjfxkhxxwzd")
public class TjfxkhxxwzdController {
	 @Autowired
	 private ITjfxkhxxwzdService tjfxkhxxwzdService;

	 @Autowired
	 private  ITjfxkhxxwzdService iTjfxkhxxwzdService;

	 /**
	  * 分页列表查询
	  *
	  * @param tjfxkhxxwzd
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "客户信息完整度统计-分页列表查询")
	 @ApiOperation(value = "客户信息完整度统计-分页列表查询", notes = "客户信息完整度统计-分页列表查询")
	 @GetMapping(value = "/list")
	 public Result<IPage<Tjfxkhxxwzd>> queryPageList(Tjfxkhxxwzd tjfxkhxxwzd,
													 @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
													 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
													 HttpServletRequest req) {
		 Result<IPage<Tjfxkhxxwzd>> result = new Result<IPage<Tjfxkhxxwzd>>();
		 QueryWrapper<Tjfxkhxxwzd> queryWrapper = QueryGenerator.initQueryWrapper(tjfxkhxxwzd, req.getParameterMap());
		 Page<Tjfxkhxxwzd> page = new Page<Tjfxkhxxwzd>(pageNo, pageSize);
		 IPage<Tjfxkhxxwzd> pageList = tjfxkhxxwzdService.page(page, queryWrapper);
		 result.setSuccess(true);
		 result.setResult(pageList);
		 return result;
	 }

	 /**
	  * 添加
	  *
	  * @param tjfxkhxxwzd
	  * @return
	  */
	 @AutoLog(value = "客户信息完整度统计-添加")
	 @ApiOperation(value = "客户信息完整度统计-添加", notes = "客户信息完整度统计-添加")
	 @PostMapping(value = "/add")
	 public Result<Tjfxkhxxwzd> add(@RequestBody Tjfxkhxxwzd tjfxkhxxwzd) {
		 Result<Tjfxkhxxwzd> result = new Result<Tjfxkhxxwzd>();
		 try {
			 tjfxkhxxwzdService.save(tjfxkhxxwzd);
			 result.success("添加成功！");
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 result.error500("操作失败");
		 }
		 return result;
	 }

	 /**
	  * 编辑
	  *
	  * @param tjfxkhxxwzd
	  * @return
	  */
	 @AutoLog(value = "客户信息完整度统计-编辑")
	 @ApiOperation(value = "客户信息完整度统计-编辑", notes = "客户信息完整度统计-编辑")
	 @PutMapping(value = "/edit")
	 public Result<Tjfxkhxxwzd> edit(@RequestBody Tjfxkhxxwzd tjfxkhxxwzd) {
		 Result<Tjfxkhxxwzd> result = new Result<Tjfxkhxxwzd>();
		 Tjfxkhxxwzd tjfxkhxxwzdEntity = tjfxkhxxwzdService.getById(tjfxkhxxwzd.getZhmc());
		 if (tjfxkhxxwzdEntity == null) {
			 result.error500("未找到对应实体");
		 } else {
			 boolean ok = tjfxkhxxwzdService.updateById(tjfxkhxxwzd);
			 //TODO 返回false说明什么？
			 if (ok) {
				 result.success("修改成功!");
			 }
		 }

		 return result;
	 }

	 /**
	  * 通过id删除
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "客户信息完整度统计-通过id删除")
	 @ApiOperation(value = "客户信息完整度统计-通过id删除", notes = "客户信息完整度统计-通过id删除")
	 @DeleteMapping(value = "/delete")
	 public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
		 try {
			 tjfxkhxxwzdService.removeById(id);
		 } catch (Exception e) {
			 log.error("删除失败", e.getMessage());
			 return Result.error("删除失败!");
		 }
		 return Result.ok("删除成功!");
	 }

	 /**
	  * 批量删除
	  *
	  * @param ids
	  * @return
	  */
	 @AutoLog(value = "客户信息完整度统计-批量删除")
	 @ApiOperation(value = "客户信息完整度统计-批量删除", notes = "客户信息完整度统计-批量删除")
	 @DeleteMapping(value = "/deleteBatch")
	 public Result<Tjfxkhxxwzd> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
		 Result<Tjfxkhxxwzd> result = new Result<Tjfxkhxxwzd>();
		 if (ids == null || "".equals(ids.trim())) {
			 result.error500("参数不识别！");
		 } else {
			 this.tjfxkhxxwzdService.removeByIds(Arrays.asList(ids.split(",")));
			 result.success("删除成功!");
		 }
		 return result;
	 }

	 /**
	  * 通过id查询
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "客户信息完整度统计-通过id查询")
	 @ApiOperation(value = "客户信息完整度统计-通过id查询", notes = "客户信息完整度统计-通过id查询")
	 @GetMapping(value = "/queryById")
	 public Result<Tjfxkhxxwzd> queryById(@RequestParam(name = "id", required = true) String id) {
		 Result<Tjfxkhxxwzd> result = new Result<Tjfxkhxxwzd>();
		 Tjfxkhxxwzd tjfxkhxxwzd = tjfxkhxxwzdService.getById(id);
		 if (tjfxkhxxwzd == null) {
			 result.error500("未找到对应实体");
		 } else {
			 result.setResult(tjfxkhxxwzd);
			 result.setSuccess(true);
		 }
		 return result;
	 }

	 /**
	  * 导出excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
		 // Step.1 组装查询条件
		 QueryWrapper<Tjfxkhxxwzd> queryWrapper = null;
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)) {
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 Tjfxkhxxwzd tjfxkhxxwzd = JSON.parseObject(deString, Tjfxkhxxwzd.class);
				 queryWrapper = QueryGenerator.initQueryWrapper(tjfxkhxxwzd, request.getParameterMap());
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }

		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<Tjfxkhxxwzd> pageList = tjfxkhxxwzdService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "客户信息完整度统计列表");
		 mv.addObject(NormalExcelConstants.CLASS, Tjfxkhxxwzd.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户信息完整度统计列表数据", "导出人:Jeecg", "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
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
		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		 Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		 for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			 MultipartFile file = entity.getValue();// 获取上传文件对象
			 ImportParams params = new ImportParams();
			 params.setTitleRows(2);
			 params.setHeadRows(1);
			 params.setNeedSave(true);
			 try {
				 List<Tjfxkhxxwzd> listTjfxkhxxwzds = ExcelImportUtil.importExcel(file.getInputStream(), Tjfxkhxxwzd.class, params);
				 tjfxkhxxwzdService.saveBatch(listTjfxkhxxwzds);
				 return Result.ok("文件导入成功！数据行数:" + listTjfxkhxxwzds.size());
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败:" + e.getMessage());
			 } finally {
				 try {
					 file.getInputStream().close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
		 return Result.ok("文件导入失败！");
	 }




	 @PutMapping(value = "/init")
	 public Result<?> init( @RequestBody JSONObject tjyf){

		 try {
			 iTjfxkhxxwzdService.extract(tjyf.getString("tjyf"));
			 Result.ok().success("添加成功！");
		 } catch (Exception e) {
			 log.error(e.getMessage(), "提取失败");
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功");
	 }



}
