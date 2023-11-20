package org.cmms.modules.tjfx.zfsjtj.wgzfsjtj.controller;

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
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.zfsjtj.wgzfsjtj.entity.WgZfsjtj;
import org.cmms.modules.tjfx.zfsjtj.wgzfsjtj.service.IWgZfsjtjService;
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
 * @Description: 网格走访数据统计
 * @Author: jeecg-boot
 * @Date:   2022-03-04
 * @Version: V1.0
 */
@Slf4j
@Api(tags="网格走访数据统计")
@RestController
@RequestMapping("/zfsjtj/wgzfsjtj")
public class WgZfsjtjController extends JeecgController<WgZfsjtj, IWgZfsjtjService> {
	@Autowired
	private IWgZfsjtjService wgZfsjtjService;
	
	/**
	 * 分页列表查询
	 *
	 * @param wgZfsjtj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "网格走访数据统计-分页列表查询")
	@ApiOperation(value="网格走访数据统计-分页列表查询", notes="网格走访数据统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(WgZfsjtj wgZfsjtj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String wgbh = wgZfsjtj.getWgbh();
		wgZfsjtj.setWgbh(null);
		QueryWrapper<WgZfsjtj> queryWrapper = QueryGenerator.initQueryWrapper(wgZfsjtj, req.getParameterMap());
		queryWrapper.orderByDesc("ljyxzfs");
		if (StringUtils.isNotEmpty(wgbh)) {
			//查询下级网格
			queryWrapper.inSql("wgbh", "select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id ");
		}
		queryWrapper.orderByAsc("wgbh");
		Page<WgZfsjtj> page = new Page<WgZfsjtj>(pageNo, pageSize);
		IPage<WgZfsjtj> pageList = wgZfsjtjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param wgZfsjtj
	 * @return
	 */
	@AutoLog(value = "网格走访数据统计-添加")
	@ApiOperation(value="网格走访数据统计-添加", notes="网格走访数据统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody WgZfsjtj wgZfsjtj) {
		wgZfsjtjService.save(wgZfsjtj);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param wgZfsjtj
	 * @return
	 */
	@AutoLog(value = "网格走访数据统计-编辑")
	@ApiOperation(value="网格走访数据统计-编辑", notes="客户经理走访数据统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody WgZfsjtj wgZfsjtj) {
		wgZfsjtjService.updateById(wgZfsjtj);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "网格走访数据统计-通过id删除")
	@ApiOperation(value="网格走访数据统计-通过id删除", notes="网格走访数据统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		wgZfsjtjService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "网格走访数据统计-批量删除")
	@ApiOperation(value="网格走访数据统计-批量删除", notes="网格走访数据统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.wgZfsjtjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "网格走访数据统计-通过id查询")
	@ApiOperation(value="网格走访数据统计-通过id查询", notes="网格走访数据统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		WgZfsjtj wgZfsjtj = wgZfsjtjService.getById(id);
		return Result.ok(wgZfsjtj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param wgZfsjtj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, WgZfsjtj wgZfsjtj) {
      return super.exportXls(request, wgZfsjtj, WgZfsjtj.class, "网格走访数据统计");
  }



	 @RequestMapping(value = "/exportXlsnhhz")
	 public ModelAndView exportXlsnhhz(HttpServletRequest request, HttpServletResponse response) {
		 // Step.1 组装查询条件
		 QueryWrapper<WgZfsjtj> queryWrapper = null;
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)) {
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 WgZfsjtj zhbndktjXb = JSON.parseObject(deString, WgZfsjtj.class);
				 queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }
		 queryWrapper.eq("khlx","1");

		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<WgZfsjtj> pageList = wgZfsjtjService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "农户网格走访(汇总)列表");
		 mv.addObject(NormalExcelConstants.CLASS, WgZfsjtj.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("农户网格走访(汇总)列表", "导出人:"+getRealname(), "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		 return mv;
	 }

	 @RequestMapping(value = "/exportXlsshhz")
	 public ModelAndView exportXlsshhz(HttpServletRequest request, HttpServletResponse response) {
		 // Step.1 组装查询条件
		 QueryWrapper<WgZfsjtj> queryWrapper = null;
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)) {
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 WgZfsjtj zhbndktjXb = JSON.parseObject(deString, WgZfsjtj.class);
				 queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }
		 queryWrapper.eq("khlx","2");

		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<WgZfsjtj> pageList = wgZfsjtjService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "商户网格走访(汇总)列表");
		 mv.addObject(NormalExcelConstants.CLASS, WgZfsjtj.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("商户网格走访(汇总)列表", "导出人:"+getRealname(), "导出信息"));
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
      return super.importExcel(request, response, WgZfsjtj.class);
  }

}
