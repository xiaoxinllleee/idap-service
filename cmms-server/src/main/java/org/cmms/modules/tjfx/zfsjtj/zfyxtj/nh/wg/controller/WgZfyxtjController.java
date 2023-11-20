package org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.wg.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.util.oConvertUtils;

import org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.wg.entity.WgZfyxtj;
import org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.wg.service.IWgZfyxtjService;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 网格走访营销统计
 * @Author: jeecg-boot
 * @Date:   2022-05-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="网格走访营销统计")
@RestController
@RequestMapping("/zfyxtj/wgZfyxtj")
public class WgZfyxtjController extends JeecgController<WgZfyxtj, IWgZfyxtjService> {
	@Autowired
	private IWgZfyxtjService wgZfyxtjService;
	
	/**
	 * 分页列表查询
	 *
	 * @param wgZfyxtj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "网格走访营销统计-分页列表查询")
	@ApiOperation(value="网格走访营销统计-分页列表查询", notes="网格走访营销统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(WgZfyxtj wgZfyxtj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<WgZfyxtj> queryWrapper = QueryGenerator.initQueryWrapper(wgZfyxtj, req.getParameterMap());
		queryWrapper.orderByAsc("yxzfpm");
		Page<WgZfyxtj> page = new Page<WgZfyxtj>(pageNo, pageSize);
		IPage<WgZfyxtj> pageList = wgZfyxtjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}


	
	/**
	 * 添加
	 *
	 * @param khjlZfyxtj
	 * @return
	 */
	@AutoLog(value = "网格走访营销统计-添加")
	@ApiOperation(value="网格走访营销统计-添加", notes="网格走访营销统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody WgZfyxtj khjlZfyxtj) {
		wgZfyxtjService.save(khjlZfyxtj);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param khjlZfyxtj
	 * @return
	 */
	@AutoLog(value = "网格走访营销统计-编辑")
	@ApiOperation(value="网格走访营销统计-编辑", notes="网格走访营销统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody WgZfyxtj khjlZfyxtj) {
		wgZfyxtjService.updateById(khjlZfyxtj);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "网格走访营销统计-通过id删除")
	@ApiOperation(value="网格走访营销统计-通过id删除", notes="网格走访营销统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		wgZfyxtjService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "网格走访营销统计-批量删除")
	@ApiOperation(value="网格走访营销统计-批量删除", notes="网格走访营销统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.wgZfyxtjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "网格走访营销统计-通过id查询")
	@ApiOperation(value="网格走访营销统计-通过id查询", notes="网格走访营销统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		WgZfyxtj wgZfyxtj = wgZfyxtjService.getById(id);
		return Result.ok(wgZfyxtj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param wgZfyxtj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, WgZfyxtj wgZfyxtj) {
      return super.exportXls(request, wgZfyxtj, WgZfyxtj.class, "网格走访营销统计");
  }

	 @RequestMapping(value = "/exportXlsnhrb")
	 public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
		 // Step.1 组装查询条件
		 QueryWrapper<WgZfyxtj> queryWrapper = null;
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)) {
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 WgZfyxtj zhbndktjXb = JSON.parseObject(deString, WgZfyxtj.class);
				 queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }
		 if (queryWrapper == null) {
		 	queryWrapper = new QueryWrapper<>();
		 }
		 queryWrapper.eq("khlx","1");
		 queryWrapper.eq("tjwd","DD");

		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<WgZfyxtj> pageList = wgZfyxtjService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "农户网格走访(日报)列表");
		 mv.addObject(NormalExcelConstants.CLASS, WgZfyxtj.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("农户网格走访(日报)列表", "导出人:"+getRealname(), "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		 return mv;
	 }

	 @RequestMapping(value = "/exportXlsnhyb")
	 public ModelAndView exportXlsnhyb(HttpServletRequest request, HttpServletResponse response) {
		 // Step.1 组装查询条件
		 QueryWrapper<WgZfyxtj> queryWrapper = null;
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)) {
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 WgZfyxtj zhbndktjXb = JSON.parseObject(deString, WgZfyxtj.class);
				 queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }
		 if (queryWrapper == null) {
			 queryWrapper = new QueryWrapper<>();
		 }
		 queryWrapper.eq("khlx","1");
		 queryWrapper.eq("tjwd","MM");

		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<WgZfyxtj> pageList = wgZfyxtjService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "农户网格走访(月报)列表");
		 mv.addObject(NormalExcelConstants.CLASS, WgZfyxtj.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("农户网格走访(月报)列表", "导出人:"+getRealname(), "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		 return mv;
	 }

	 @RequestMapping(value = "/exportXlsnhjb")
	 public ModelAndView exportXlsnhjb(HttpServletRequest request, HttpServletResponse response) {
		 // Step.1 组装查询条件
		 QueryWrapper<WgZfyxtj> queryWrapper = null;
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)) {
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 WgZfyxtj zhbndktjXb = JSON.parseObject(deString, WgZfyxtj.class);
				 queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }
		 if (queryWrapper == null) {
			 queryWrapper = new QueryWrapper<>();
		 }
		 queryWrapper.eq("khlx","1");
		 queryWrapper.eq("tjwd","Q");

		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<WgZfyxtj> pageList = wgZfyxtjService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "农户网格走访(季报)列表");
		 mv.addObject(NormalExcelConstants.CLASS, WgZfyxtj.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("农户网格走访(季报)列表", "导出人:"+getRealname(), "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		 return mv;
	 }

	 @RequestMapping(value = "/exportXlsnhnb")
	 public ModelAndView exportXlsnhnb(HttpServletRequest request, HttpServletResponse response) {
		 // Step.1 组装查询条件
		 QueryWrapper<WgZfyxtj> queryWrapper = null;
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)) {
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 WgZfyxtj zhbndktjXb = JSON.parseObject(deString, WgZfyxtj.class);
				 queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }
		 if (queryWrapper == null) {
			 queryWrapper = new QueryWrapper<>();
		 }
		 queryWrapper.eq("khlx","1");
		 queryWrapper.eq("tjwd","YYYY");

		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<WgZfyxtj> pageList = wgZfyxtjService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "农户网格走访(年报)列表");
		 mv.addObject(NormalExcelConstants.CLASS, WgZfyxtj.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("农户网格走访(年报)列表", "导出人:"+getRealname(), "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		 return mv;
	 }

	 @RequestMapping(value = "/exportXlsnhlj")
	 public ModelAndView exportXlsnhlj(HttpServletRequest request, HttpServletResponse response) {
		 // Step.1 组装查询条件
		 QueryWrapper<WgZfyxtj> queryWrapper = null;
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)) {
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 WgZfyxtj zhbndktjXb = JSON.parseObject(deString, WgZfyxtj.class);
				 queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }
		 if (queryWrapper == null) {
			 queryWrapper = new QueryWrapper<>();
		 }
		 queryWrapper.eq("khlx","1");
		 queryWrapper.eq("tjwd","T");

		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<WgZfyxtj> pageList = wgZfyxtjService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "农户网格走访(累计)列表");
		 mv.addObject(NormalExcelConstants.CLASS, WgZfyxtj.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("农户网格走访(累计)列表", "导出人:"+getRealname(), "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		 return mv;
	 }

	 @RequestMapping(value = "/exportXlsshrb")
	 public ModelAndView exportXlsshrb(HttpServletRequest request, HttpServletResponse response) {
		 // Step.1 组装查询条件
		 QueryWrapper<WgZfyxtj> queryWrapper = null;
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)) {
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 WgZfyxtj zhbndktjXb = JSON.parseObject(deString, WgZfyxtj.class);
				 queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }
		 if (queryWrapper == null) {
			 queryWrapper = new QueryWrapper<>();
		 }
		 queryWrapper.eq("khlx","2");
		 queryWrapper.eq("tjwd","DD");

		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<WgZfyxtj> pageList = wgZfyxtjService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "商户网格走访(日报)列表");
		 mv.addObject(NormalExcelConstants.CLASS, WgZfyxtj.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("商户网格走访(日报)列表", "导出人:"+getRealname(), "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		 return mv;
	 }


	 @RequestMapping(value = "/exportXlsshyb")
	 public ModelAndView exportXlsshyb(HttpServletRequest request, HttpServletResponse response) {
		 // Step.1 组装查询条件
		 QueryWrapper<WgZfyxtj> queryWrapper = null;
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)) {
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 WgZfyxtj zhbndktjXb = JSON.parseObject(deString, WgZfyxtj.class);
				 queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }
		 if (queryWrapper == null) {
			 queryWrapper = new QueryWrapper<>();
		 }
		 queryWrapper.eq("khlx","2");
		 queryWrapper.eq("tjwd","MM");

		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<WgZfyxtj> pageList = wgZfyxtjService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "商户网格走访(月报)列表");
		 mv.addObject(NormalExcelConstants.CLASS, WgZfyxtj.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("商户网格走访(月报)列表", "导出人:"+getRealname(), "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		 return mv;
	 }

	 @RequestMapping(value = "/exportXlsshjb")
	 public ModelAndView exportXlsshjb(HttpServletRequest request, HttpServletResponse response) {
		 // Step.1 组装查询条件
		 QueryWrapper<WgZfyxtj> queryWrapper = null;
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)) {
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 WgZfyxtj zhbndktjXb = JSON.parseObject(deString, WgZfyxtj.class);
				 queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }
		 if (queryWrapper == null) {
			 queryWrapper = new QueryWrapper<>();
		 }
		 queryWrapper.eq("khlx","2");
		 queryWrapper.eq("tjwd","Q");

		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<WgZfyxtj> pageList = wgZfyxtjService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "商户网格走访(季报)列表");
		 mv.addObject(NormalExcelConstants.CLASS, WgZfyxtj.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("商户网格走访(季报)列表", "导出人:"+getRealname(), "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		 return mv;
	 }

	 @RequestMapping(value = "/exportXlsshnb")
	 public ModelAndView exportXlsshnb(HttpServletRequest request, HttpServletResponse response) {
		 // Step.1 组装查询条件
		 QueryWrapper<WgZfyxtj> queryWrapper = null;
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)) {
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 WgZfyxtj zhbndktjXb = JSON.parseObject(deString, WgZfyxtj.class);
				 queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }
		 queryWrapper.eq("khlx","2");
		 queryWrapper.eq("tjwd","YYYY");

		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<WgZfyxtj> pageList = wgZfyxtjService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "商户网格走访(年报)列表");
		 mv.addObject(NormalExcelConstants.CLASS, WgZfyxtj.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("商户网格走访(年报)列表", "导出人:"+getRealname(), "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		 return mv;
	 }

	 @RequestMapping(value = "/exportXlsshlj")
	 public ModelAndView exportXlsshlj(HttpServletRequest request, HttpServletResponse response) {
		 // Step.1 组装查询条件
		 QueryWrapper<WgZfyxtj> queryWrapper = null;
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)) {
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 WgZfyxtj zhbndktjXb = JSON.parseObject(deString, WgZfyxtj.class);
				 queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }
		 if (queryWrapper == null) {
			 queryWrapper = new QueryWrapper<>();
		 }
		 queryWrapper.eq("khlx","2");
		 queryWrapper.eq("tjwd","T");

		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<WgZfyxtj> pageList = wgZfyxtjService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "商户网格走访(累计)列表");
		 mv.addObject(NormalExcelConstants.CLASS, WgZfyxtj.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("商户网格走访(累计)列表", "导出人:"+getRealname(), "导出信息"));
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
      return super.importExcel(request, response, WgZfyxtj.class);
  }

}
