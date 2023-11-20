package org.cmms.modules.report.sgtzgl.qszlxxxcyqymd.controller;

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
import org.cmms.modules.report.sgtzgl.jtkhtz.service.ISgtzglJtkhtzService;
import org.cmms.modules.report.sgtzgl.khsxcx.entity.SgtzglKhsxcx;
import org.cmms.modules.report.sgtzgl.khsxcx.entity.SgtzglKhsxcxVO;
import org.cmms.modules.report.sgtzgl.qszlxxxcyqymd.entity.SgtzglQszlxxxcyqymd;
import org.cmms.modules.report.sgtzgl.qszlxxxcyqymd.entity.SgtzglQszlxxxcyqymdVO;
import org.cmms.modules.report.sgtzgl.qszlxxxcyqymd.service.ISgtzglQszlxxxcyqymdService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.qszlxxxcyqymd.verify.QszlxxxcyqymdImportVerify;
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
 * @Description: 全省战略性新兴产业企业名单
 * @Author: jeecg-boot
 * @Date:   2022-08-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="全省战略性新兴产业企业名单")
@RestController
@RequestMapping("/qszlxxxcyqymd/sgtzglQszlxxxcyqymd")
public class SgtzglQszlxxxcyqymdController extends JeecgController<SgtzglQszlxxxcyqymd, ISgtzglQszlxxxcyqymdService> {
	@Autowired
	private ISgtzglQszlxxxcyqymdService sgtzglQszlxxxcyqymdService;
	 @Autowired
	 private QszlxxxcyqymdImportVerify qszlxxxcyqymdImportVerify;
	/**
	 * 分页列表查询
	 *
	 * @param sgtzglQszlxxxcyqymd
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "全省战略性新兴产业企业名单-分页列表查询")
	@ApiOperation(value="全省战略性新兴产业企业名单-分页列表查询", notes="全省战略性新兴产业企业名单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgtzglQszlxxxcyqymd sgtzglQszlxxxcyqymd,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgtzglQszlxxxcyqymd> queryWrapper = QueryGenerator.initQueryWrapper(sgtzglQszlxxxcyqymd, req.getParameterMap());
		Page<SgtzglQszlxxxcyqymd> page = new Page<SgtzglQszlxxxcyqymd>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgtzglQszlxxxcyqymdService.class,sgtzglQszlxxxcyqymdService,pageNo,pageSize,queryWrapper,"id","frm");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgtzglQszlxxxcyqymd
	 * @return
	 */
	@AutoLog(value = "全省战略性新兴产业企业名单-添加")
	@ApiOperation(value="全省战略性新兴产业企业名单-添加", notes="全省战略性新兴产业企业名单-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgtzglQszlxxxcyqymd sgtzglQszlxxxcyqymd) {
		sgtzglQszlxxxcyqymd.setId(UUIDGenerator.generate());
		sgtzglQszlxxxcyqymd.setCreateBy(getUsername());
		sgtzglQszlxxxcyqymd.setCreateTime(new Date());
		sgtzglQszlxxxcyqymdService.save(sgtzglQszlxxxcyqymd);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgtzglQszlxxxcyqymd
	 * @return
	 */
	@AutoLog(value = "全省战略性新兴产业企业名单-编辑")
	@ApiOperation(value="全省战略性新兴产业企业名单-编辑", notes="全省战略性新兴产业企业名单-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgtzglQszlxxxcyqymd sgtzglQszlxxxcyqymd) {
		sgtzglQszlxxxcyqymd.setCreateBy(getUsername());
		sgtzglQszlxxxcyqymd.setUpdateTime(new Date());
		sgtzglQszlxxxcyqymdService.updateById(sgtzglQszlxxxcyqymd);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全省战略性新兴产业企业名单-通过id删除")
	@ApiOperation(value="全省战略性新兴产业企业名单-通过id删除", notes="全省战略性新兴产业企业名单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgtzglQszlxxxcyqymdService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "全省战略性新兴产业企业名单-批量删除")
	@ApiOperation(value="全省战略性新兴产业企业名单-批量删除", notes="全省战略性新兴产业企业名单-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgtzglQszlxxxcyqymdService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全省战略性新兴产业企业名单-通过id查询")
	@ApiOperation(value="全省战略性新兴产业企业名单-通过id查询", notes="全省战略性新兴产业企业名单-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgtzglQszlxxxcyqymd sgtzglQszlxxxcyqymd = sgtzglQszlxxxcyqymdService.getById(id);
		return Result.ok(sgtzglQszlxxxcyqymd);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgtzglQszlxxxcyqymd
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgtzglQszlxxxcyqymd sgtzglQszlxxxcyqymd) {
      return super.exportXls(request, sgtzglQszlxxxcyqymd, SgtzglQszlxxxcyqymd.class, "全省战略性新兴产业企业名单");
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
      return super.importExcel(request, response, SgtzglQszlxxxcyqymd.class);
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "全省战略性新兴产业企业名单导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgtzglQszlxxxcyqymdVO.class);
		 ExportParams exportParams = new ExportParams("全省战略性新兴产业企业名单导入模板", "模板信息");
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
		 return super.importExcelByTemplate(jsonObject, request, response, SgtzglQszlxxxcyqymd.class,SgtzglQszlxxxcyqymdVO.class,qszlxxxcyqymdImportVerify);
	 }

}
