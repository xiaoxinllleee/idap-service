package org.cmms.modules.report.sgtzgl.xdckmxb.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.khsxcx.entity.SgtzglKhsxcx;
import org.cmms.modules.report.sgtzgl.khsxcx.entity.SgtzglKhsxcxVO;
import org.cmms.modules.report.sgtzgl.txdjb.service.ISgtzglTxdjbService;
import org.cmms.modules.report.sgtzgl.xdckmxb.entity.SgtzglXdckmxb;
import org.cmms.modules.report.sgtzgl.xdckmxb.entity.SgtzglXdckmxbVO;
import org.cmms.modules.report.sgtzgl.xdckmxb.service.ISgtzglXdckmxbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.xdckmxb.verify.XdckmxbImportVerify;
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
 * @Description: 协定存款明细表
 * @Author: jeecg-boot
 * @Date:   2022-08-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="协定存款明细表")
@RestController
@RequestMapping("/xdckmxb/sgtzglXdckmxb")
public class SgtzglXdckmxbController extends JeecgController<SgtzglXdckmxb, ISgtzglXdckmxbService> {
	@Autowired
	private ISgtzglXdckmxbService sgtzglXdckmxbService;
	 @Autowired
	 private XdckmxbImportVerify xdckmxbImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param sgtzglXdckmxb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "协定存款明细表-分页列表查询")
	@ApiOperation(value="协定存款明细表-分页列表查询", notes="协定存款明细表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgtzglXdckmxb sgtzglXdckmxb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgtzglXdckmxb> queryWrapper = QueryGenerator.initQueryWrapper(sgtzglXdckmxb, req.getParameterMap());
		Page<SgtzglXdckmxb> page = new Page<SgtzglXdckmxb>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgtzglXdckmxbService.class,sgtzglXdckmxbService,pageNo,pageSize,queryWrapper,"xh");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgtzglXdckmxb
	 * @return
	 */
	@AutoLog(value = "协定存款明细表-添加")
	@ApiOperation(value="协定存款明细表-添加", notes="协定存款明细表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgtzglXdckmxb sgtzglXdckmxb) {
		sgtzglXdckmxb.setId(UUIDGenerator.generate());
		sgtzglXdckmxb.setCreateBy(getUsername());
		sgtzglXdckmxb.setCreateTime(new Date());
		sgtzglXdckmxbService.save(sgtzglXdckmxb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgtzglXdckmxb
	 * @return
	 */
	@AutoLog(value = "协定存款明细表-编辑")
	@ApiOperation(value="协定存款明细表-编辑", notes="协定存款明细表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgtzglXdckmxb sgtzglXdckmxb) {
		sgtzglXdckmxb.setCreateBy(getUsername());
		sgtzglXdckmxb.setUpdateTime(new Date());
		sgtzglXdckmxbService.updateById(sgtzglXdckmxb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "协定存款明细表-通过id删除")
	@ApiOperation(value="协定存款明细表-通过id删除", notes="协定存款明细表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgtzglXdckmxbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "协定存款明细表-批量删除")
	@ApiOperation(value="协定存款明细表-批量删除", notes="协定存款明细表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgtzglXdckmxbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "协定存款明细表-通过id查询")
	@ApiOperation(value="协定存款明细表-通过id查询", notes="协定存款明细表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgtzglXdckmxb sgtzglXdckmxb = sgtzglXdckmxbService.getById(id);
		return Result.ok(sgtzglXdckmxb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgtzglXdckmxb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgtzglXdckmxb sgtzglXdckmxb) {
      return super.exportXls(request, sgtzglXdckmxb, SgtzglXdckmxb.class, "协定存款明细表");
  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
 /* @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, SgtzglXdckmxb.class);
  }*/
	 /**
	  * 导入模板
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "协定存款明细表导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgtzglXdckmxbVO.class);
		 ExportParams exportParams = new ExportParams("协定存款明细表导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
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
		 return super.importExcelByTemplate(jsonObject, request, response, SgtzglXdckmxb.class,SgtzglXdckmxbVO.class,xdckmxbImportVerify);
	 }
}
