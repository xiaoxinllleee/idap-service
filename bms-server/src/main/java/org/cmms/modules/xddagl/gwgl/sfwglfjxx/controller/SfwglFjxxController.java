package org.cmms.modules.xddagl.gwgl.sfwglfjxx.controller;

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
import org.cmms.modules.xddagl.gwgl.sfwglfjxx.entity.SfwglFjxx;
import org.cmms.modules.xddagl.gwgl.sfwglfjxx.service.ISfwglFjxxService;
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
 * @Description: 收发文管理附件信息
 * @Author: jeecg-boot
 * @Date:   2022-01-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags="收发文管理附件信息")
@RestController
@RequestMapping("/sfwglfjxx/sfwglFjxx")
public class SfwglFjxxController extends JeecgController<SfwglFjxx, ISfwglFjxxService> {
	@Autowired
	private ISfwglFjxxService sfwglFjxxService;
	
	/**
	 * 分页列表查询
	 *
	 * @param sfwglFjxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "收发文管理附件信息-分页列表查询")
	@ApiOperation(value="收发文管理附件信息-分页列表查询", notes="收发文管理附件信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SfwglFjxx sfwglFjxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SfwglFjxx> queryWrapper = QueryGenerator.initQueryWrapper(sfwglFjxx, req.getParameterMap());
		Page<SfwglFjxx> page = new Page<SfwglFjxx>(pageNo, pageSize);
		IPage<SfwglFjxx> pageList = sfwglFjxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
	  * 查询附件
	  */
	 @RequestMapping(value = "/queryFjxx",method = RequestMethod.GET)
	 public Result<?> queryFjxx(@RequestParam(name = "id",required = true)String id){
		 QueryWrapper<SfwglFjxx> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("fwbhid",id);
		 List<SfwglFjxx> list = sfwglFjxxService.list(queryWrapper);
		 return Result.ok(list);
	 }

	 @RequestMapping(value = "/queryXdhcFjxx",method = RequestMethod.GET)
	 public Result<?> queryXdhcFjxx(@RequestParam(name = "hth",required = true)String hth){
		 QueryWrapper<SfwglFjxx> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("fwbhid",hth);
		 List<SfwglFjxx> list = sfwglFjxxService.list(queryWrapper);
		 return Result.ok(list);
	 }
	
	/**
	 * 添加
	 *
	 * @param sfwglFjxx
	 * @return
	 */
	@AutoLog(value = "收发文管理附件信息-添加")
	@ApiOperation(value="收发文管理附件信息-添加", notes="收发文管理附件信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SfwglFjxx sfwglFjxx) {
		sfwglFjxxService.save(sfwglFjxx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param sfwglFjxx
	 * @return
	 */
	@AutoLog(value = "收发文管理附件信息-编辑")
	@ApiOperation(value="收发文管理附件信息-编辑", notes="收发文管理附件信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SfwglFjxx sfwglFjxx) {
		sfwglFjxxService.updateById(sfwglFjxx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "收发文管理附件信息-通过id删除")
	@ApiOperation(value="收发文管理附件信息-通过id删除", notes="收发文管理附件信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="wjid",required=true) String wjid) {
		QueryWrapper<SfwglFjxx> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("wjid",wjid);
		sfwglFjxxService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "收发文管理附件信息-批量删除")
	@ApiOperation(value="收发文管理附件信息-批量删除", notes="收发文管理附件信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sfwglFjxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "收发文管理附件信息-通过id查询")
	@ApiOperation(value="收发文管理附件信息-通过id查询", notes="收发文管理附件信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SfwglFjxx sfwglFjxx = sfwglFjxxService.getById(id);
		return Result.ok(sfwglFjxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sfwglFjxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SfwglFjxx sfwglFjxx) {
      return super.exportXls(request, sfwglFjxx, SfwglFjxx.class, "收发文管理附件信息");
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
      return super.importExcel(request, response, SfwglFjxx.class);
  }

}
