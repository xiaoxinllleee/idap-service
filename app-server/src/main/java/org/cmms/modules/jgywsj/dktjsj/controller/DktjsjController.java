package org.cmms.modules.jgywsj.dktjsj.controller;

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
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.MoneyUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.jgywsj.dktjsj.entity.Dktjsj;
import org.cmms.modules.jgywsj.dktjsj.entity.DktjsjResult;
import org.cmms.modules.jgywsj.dktjsj.entity.TbTjfxDktjsj;
import org.cmms.modules.jgywsj.dktjsj.service.IDktjsjService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
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
 * @Description: 贷款统计数据
 * @Author: jeecg-boot
 * @Date:   2021-05-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款统计数据")
@RestController
@RequestMapping("/mobile/dktjsj")
public class DktjsjController extends JeecgController<TbTjfxDktjsj, IDktjsjService> {
	@Autowired
	private IDktjsjService dktjsjService;
	
	/**
	 * 分页列表查询
	 *
	 * @param dktjsj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款统计数据-分页列表查询")
	@ApiOperation(value="贷款统计数据-分页列表查询", notes="贷款统计数据-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TbTjfxDktjsj dktjsj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TbTjfxDktjsj> queryWrapper = QueryGenerator.initQueryWrapper(dktjsj, req.getParameterMap());
		Page<TbTjfxDktjsj> page = new Page<TbTjfxDktjsj>(pageNo, pageSize);
		IPage<TbTjfxDktjsj> pageList = dktjsjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param dktjsj
	 * @return
	 */
	@AutoLog(value = "贷款统计数据-添加")
	@ApiOperation(value="贷款统计数据-添加", notes="贷款统计数据-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TbTjfxDktjsj dktjsj) {
		dktjsjService.save(dktjsj);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param dktjsj
	 * @return
	 */
	@AutoLog(value = "贷款统计数据-编辑")
	@ApiOperation(value="贷款统计数据-编辑", notes="贷款统计数据-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TbTjfxDktjsj dktjsj) {
		dktjsjService.updateById(dktjsj);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款统计数据-通过id删除")
	@ApiOperation(value="贷款统计数据-通过id删除", notes="贷款统计数据-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dktjsjService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款统计数据-批量删除")
	@ApiOperation(value="贷款统计数据-批量删除", notes="贷款统计数据-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dktjsjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款统计数据-通过id查询")
	@ApiOperation(value="贷款统计数据-通过id查询", notes="贷款统计数据-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TbTjfxDktjsj dktjsj = dktjsjService.getById(id);
		return Result.ok(dktjsj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dktjsj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, TbTjfxDktjsj dktjsj) {
      return super.exportXls(request, dktjsj, TbTjfxDktjsj.class, "贷款统计数据");
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
      return super.importExcel(request, response, TbTjfxDktjsj.class);
  }

  @RequestMapping(value = "/getJgdktjsj", method = RequestMethod.GET)
  public Result<?> getJgckkmxx(@RequestParam(name="zzbz") String zzbz, HttpServletRequest request, HttpServletResponse response) {
      List<Date> latestTjrq = dktjsjService.getLatestTjrq(zzbz);
	  if (latestTjrq.isEmpty()) {
		  return Result.error("未获取到任何数据");
	  }
	  Date maxDate = latestTjrq.get(0);

	  //获取年初数据
	  String ncrq = DateUtil.formatDateTime("yyyy-MM-dd", DateUtil.addDays(DateUtil.getYBeginDay(maxDate), -1));
	  Dktjsj ncsj = dktjsjService.queryDkxxByTjrqAndZzbz(ncrq, zzbz);

	  //获取月初数据
	  String ycrq = DateUtil.formatDateTime("yyyy-MM-dd", DateUtil.addDays(DateUtil.getMonthBeginDay(maxDate), -1));
	  Dktjsj ycsj = dktjsjService.queryDkxxByTjrqAndZzbz(ycrq, zzbz);
	  DktjsjResult dktjsjResult = new DktjsjResult();
	  dktjsjResult.setZzbz(zzbz);
	  dktjsjResult.setDkywNc(ncsj);
	  dktjsjResult.setDkywYc(ycsj);
	  //获取最新日期前五天的数据
	  for (int i = 0; i < latestTjrq.size(); i++) {
		  Date date = latestTjrq.get(i);
		  Dktjsj dktjsj = dktjsjService.queryDkxxByTjrqAndZzbz(DateUtil.format(date, "yyyy-MM-dd"), zzbz);
		  switch(i) {
			  case 0 :
				  dktjsjResult.setDkywTheDay(dktjsj);
				  dktjsjResult.setDkyj(MoneyUtil.format(dktjsj.getDkye()));
				  break;
			  case 1 :
				  dktjsjResult.setDkywFrontOneDay(dktjsj);
				  break;
			  case 2 :
				  dktjsjResult.setDkywFrontTwoDay(dktjsj);
				  break;
			  case 3 :
				  dktjsjResult.setDkywFrontThreeDay(dktjsj);
				  break;
			  case 4 :
				  dktjsjResult.setDkywFrontFourDay(dktjsj);
				  break;
			  default:
				  break;
		  }
	  }
	  return Result.ok(dktjsjResult);
  }

}
