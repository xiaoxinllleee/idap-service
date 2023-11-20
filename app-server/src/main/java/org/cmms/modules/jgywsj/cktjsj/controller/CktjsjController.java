package org.cmms.modules.jgywsj.cktjsj.controller;

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
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.jgywsj.cktjsj.entity.Ckkhs;
import org.cmms.modules.jgywsj.cktjsj.entity.CkkhsData;
import org.cmms.modules.jgywsj.cktjsj.entity.TbTjfxCktjsj;
import org.cmms.modules.jgywsj.cktjsj.service.ICktjsjService;
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
 * @Description: 存款统计数据
 * @Author: jeecg-boot
 * @Date:   2021-05-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存款统计数据")
@RestController
@RequestMapping("/mobile/cktjsj")
public class CktjsjController extends JeecgController<TbTjfxCktjsj, ICktjsjService> {
	@Autowired
	private ICktjsjService cktjsjService;
	
	/**
	 * 分页列表查询
	 *
	 * @param cktjsj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "存款统计数据-分页列表查询")
	@ApiOperation(value="存款统计数据-分页列表查询", notes="存款统计数据-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TbTjfxCktjsj cktjsj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TbTjfxCktjsj> queryWrapper = QueryGenerator.initQueryWrapper(cktjsj, req.getParameterMap());
		Page<TbTjfxCktjsj> page = new Page<TbTjfxCktjsj>(pageNo, pageSize);
		IPage<TbTjfxCktjsj> pageList = cktjsjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param cktjsj
	 * @return
	 */
	@AutoLog(value = "存款统计数据-添加")
	@ApiOperation(value="存款统计数据-添加", notes="存款统计数据-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TbTjfxCktjsj cktjsj) {
		cktjsjService.save(cktjsj);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param cktjsj
	 * @return
	 */
	@AutoLog(value = "存款统计数据-编辑")
	@ApiOperation(value="存款统计数据-编辑", notes="存款统计数据-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TbTjfxCktjsj cktjsj) {
		cktjsjService.updateById(cktjsj);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款统计数据-通过id删除")
	@ApiOperation(value="存款统计数据-通过id删除", notes="存款统计数据-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		cktjsjService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "存款统计数据-批量删除")
	@ApiOperation(value="存款统计数据-批量删除", notes="存款统计数据-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.cktjsjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款统计数据-通过id查询")
	@ApiOperation(value="存款统计数据-通过id查询", notes="存款统计数据-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TbTjfxCktjsj cktjsj = cktjsjService.getById(id);
		return Result.ok(cktjsj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param cktjsj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, TbTjfxCktjsj cktjsj) {
      return super.exportXls(request, cktjsj, TbTjfxCktjsj.class, "存款统计数据");
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
      return super.importExcel(request, response, TbTjfxCktjsj.class);
  }

  /**
   * 获取首页存款内页数据
   * @return
   */
  @RequestMapping(value = "/getCkInnerData", method = RequestMethod.GET)
  public Result<?> getCkInnerData(@RequestParam(name="zzbz") String zzbz, HttpServletRequest request, HttpServletResponse response) {
      Result<?> result = new Result<>();
	  List<Date> latestTjrq = cktjsjService.getLatestTjrq(zzbz);
	  if (latestTjrq.isEmpty()) {
		  return Result.error("未获取到任何数据");
	  }
	  Date maxDate = latestTjrq.get(0);
	  CkkhsData ckkhsData = new CkkhsData();
	  //获取年初数据
	  String ncrq = DateUtil.formatDateTime("yyyy-MM-dd", DateUtil.addDays(DateUtil.getYBeginDay(maxDate), -1));
	  TbTjfxCktjsj ncsj = cktjsjService.queryCktjsjByTjrqAndZzbz(ncrq, zzbz);

	  //获取月初数据
	  String ycrq = DateUtil.formatDateTime("yyyy-MM-dd", DateUtil.addDays(DateUtil.getMonthBeginDay(maxDate), -1));
	  TbTjfxCktjsj ycsj = cktjsjService.queryCktjsjByTjrqAndZzbz(ycrq, zzbz);



	  return result;
  }

  private void ckkhsData(String date, TbTjfxCktjsj cktjsj) {
	  Ckkhs ckkhs = new Ckkhs();
	  ckkhs.setDate(date);
	  ckkhs.setTjrq(cktjsj.getTjrq());
//	  ckkhs.setYxkh(cktjsj.getYxkhs());
//	  ckkhs.setZdkh(cktjsj.getZdkhs());
//	  ckkhs.setYzkh(cktjsj.getYzkhs());
  }
}
