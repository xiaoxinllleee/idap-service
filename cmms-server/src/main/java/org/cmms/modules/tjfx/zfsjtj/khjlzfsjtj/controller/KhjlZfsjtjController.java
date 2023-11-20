package org.cmms.modules.tjfx.zfsjtj.khjlzfsjtj.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.zfsjtj.khjlzfsjtj.entity.KhjlZfsjtj;
import org.cmms.modules.tjfx.zfsjtj.khjlzfsjtj.service.IKhjlZfsjtjService;
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
 * @Description: 客户经理走访数据统计
 * @Author: jeecg-boot
 * @Date:   2022-03-04
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户经理走访数据统计")
@RestController
@RequestMapping("/zfsjtj/khjlzfsjtj")
public class KhjlZfsjtjController extends JeecgController<KhjlZfsjtj, IKhjlZfsjtjService> {
	@Autowired
	private IKhjlZfsjtjService khjlZfsjtjService;
	
	/**
	 * 分页列表查询
	 *
	 * @param khjlZfsjtj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户经理走访数据统计-分页列表查询")
	@ApiOperation(value="客户经理走访数据统计-分页列表查询", notes="客户经理走访数据统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KhjlZfsjtj khjlZfsjtj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<KhjlZfsjtj> queryWrapper = QueryGenerator.initQueryWrapper(khjlZfsjtj, req.getParameterMap());
		queryWrapper.orderByDesc("ljyxzfs");
		if ("1".equals(khjlZfsjtj.getKhlx())){
			queryWrapper.eq("khlx","1");
			Page<KhjlZfsjtj> page = new Page<KhjlZfsjtj>(pageNo, pageSize);
			IPage<KhjlZfsjtj> pageList = khjlZfsjtjService.page(page, queryWrapper);
			return Result.ok(pageList);
		}else if ("2".equals(khjlZfsjtj.getKhlx())){
			queryWrapper.eq("khlx","2");
			Page<KhjlZfsjtj> page = new Page<KhjlZfsjtj>(pageNo, pageSize);
			IPage<KhjlZfsjtj> pageList = khjlZfsjtjService.page(page, queryWrapper);
			return Result.ok(pageList);
		}
		Page<KhjlZfsjtj> page = new Page<KhjlZfsjtj>(pageNo, pageSize);
		IPage<KhjlZfsjtj> pageList = khjlZfsjtjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param khjlZfsjtj
	 * @return
	 */
	@AutoLog(value = "客户经理走访数据统计-添加")
	@ApiOperation(value="客户经理走访数据统计-添加", notes="客户经理走访数据统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody KhjlZfsjtj khjlZfsjtj) {
		khjlZfsjtjService.save(khjlZfsjtj);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param khjlZfsjtj
	 * @return
	 */
	@AutoLog(value = "客户经理走访数据统计-编辑")
	@ApiOperation(value="客户经理走访数据统计-编辑", notes="客户经理走访数据统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhjlZfsjtj khjlZfsjtj) {
		khjlZfsjtjService.updateById(khjlZfsjtj);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理走访数据统计-通过id删除")
	@ApiOperation(value="客户经理走访数据统计-通过id删除", notes="客户经理走访数据统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khjlZfsjtjService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户经理走访数据统计-批量删除")
	@ApiOperation(value="客户经理走访数据统计-批量删除", notes="客户经理走访数据统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khjlZfsjtjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理走访数据统计-通过id查询")
	@ApiOperation(value="客户经理走访数据统计-通过id查询", notes="客户经理走访数据统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		KhjlZfsjtj khjlZfsjtj = khjlZfsjtjService.getById(id);
		return Result.ok(khjlZfsjtj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khjlZfsjtj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, KhjlZfsjtj khjlZfsjtj) {
      return super.exportXls(request, khjlZfsjtj, KhjlZfsjtj.class, "客户经理走访数据统计");
  }



	 @RequestMapping(value = "/exportXlsnhhz")
	 public ModelAndView exportXlsnhhz(HttpServletRequest request, HttpServletResponse response) {
		 // Step.1 组装查询条件
		 QueryWrapper<KhjlZfsjtj> queryWrapper = null;
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)) {
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 KhjlZfsjtj zhbndktjXb = JSON.parseObject(deString, KhjlZfsjtj.class);
				 queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }
		 queryWrapper.eq("khlx","1");

		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<KhjlZfsjtj> pageList = khjlZfsjtjService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "农户个人走访(汇总)列表");
		 mv.addObject(NormalExcelConstants.CLASS, KhjlZfsjtj.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("农户个人走访(汇总)列表", "导出人:"+getRealname(), "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		 return mv;
	 }

	 @RequestMapping(value = "/exportXlsshhz")
	 public ModelAndView exportXlsshhz(HttpServletRequest request, HttpServletResponse response) {
		 // Step.1 组装查询条件
		 QueryWrapper<KhjlZfsjtj> queryWrapper = null;
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)) {
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 KhjlZfsjtj zhbndktjXb = JSON.parseObject(deString, KhjlZfsjtj.class);
				 queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }
		 queryWrapper.eq("khlx","2");

		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<KhjlZfsjtj> pageList = khjlZfsjtjService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "商户个人走访(汇总)列表");
		 mv.addObject(NormalExcelConstants.CLASS, KhjlZfsjtj.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("商户个人走访(汇总)列表", "导出人:"+getRealname(), "导出信息"));
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
      return super.importExcel(request, response, KhjlZfsjtj.class);
  }

}
