package org.cmms.modules.hr.djpd.khjlxjpd.djpd.khjldjpdMx.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.hr.djpd.khjlxjpd.djpd.khjldjpd.entity.Khjldjpd;
import org.cmms.modules.hr.djpd.khjlxjpd.djpd.khjldjpdMx.entity.KhjldjpdMx;
import org.cmms.modules.hr.djpd.khjlxjpd.djpd.khjldjpdMx.service.IKhjldjpdMxService;
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
 * @Description: 客户经理等级评定明细
 * @Author: jeecg-boot
 * @Date:   2023-01-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户经理等级评定明细")
@RestController
@RequestMapping("/khjldjpdMx/khjldjpdMx")
public class KhjldjpdMxController extends JeecgController<KhjldjpdMx, IKhjldjpdMxService> {
	@Autowired
	private IKhjldjpdMxService khjldjpdMxService;

	/**
	 * 分页列表查询
	 *
	 * @param khjldjpdMx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户经理等级评定明细-分页列表查询")
	@ApiOperation(value="客户经理等级评定明细-分页列表查询", notes="客户经理等级评定明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KhjldjpdMx khjldjpdMx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<KhjldjpdMx> queryWrapper = QueryGenerator.initQueryWrapper(khjldjpdMx, req.getParameterMap());
		Page<KhjldjpdMx> page = new Page<KhjldjpdMx>(pageNo, pageSize);
		IPage<KhjldjpdMx> pageList = khjldjpdMxService.page(page, queryWrapper);
		List<KhjldjpdMx> records = pageList.getRecords();
		if (CollUtil.isNotEmpty(records)){
			for (int i = 0; i < records.size(); i++) {
				records.get(i).setZhmc(records.get(i).getZzbz());
			}
		}
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param khjldjpdMx
	 * @return
	 */
	@AutoLog(value = "客户经理等级评定明细-添加")
	@ApiOperation(value="客户经理等级评定明细-添加", notes="客户经理等级评定明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody KhjldjpdMx khjldjpdMx) {
		khjldjpdMxService.save(khjldjpdMx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param khjldjpdMx
	 * @return
	 */
	@AutoLog(value = "客户经理等级评定明细-编辑")
	@ApiOperation(value="客户经理等级评定明细-编辑", notes="客户经理等级评定明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhjldjpdMx khjldjpdMx) {
		khjldjpdMxService.updateById(khjldjpdMx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理等级评定明细-通过id删除")
	@ApiOperation(value="客户经理等级评定明细-通过id删除", notes="客户经理等级评定明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khjldjpdMxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户经理等级评定明细-批量删除")
	@ApiOperation(value="客户经理等级评定明细-批量删除", notes="客户经理等级评定明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khjldjpdMxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理等级评定明细-通过id查询")
	@ApiOperation(value="客户经理等级评定明细-通过id查询", notes="客户经理等级评定明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		KhjldjpdMx khjldjpdMx = khjldjpdMxService.getById(id);
		return Result.ok(khjldjpdMx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khjldjpdMx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, KhjldjpdMx khjldjpdMx) {
     // return super.exportXls(request, khjldjpdMx, KhjldjpdMx.class, "客户经理等级评定明细");
	  // Step.1 组装查询条件
	  QueryWrapper<KhjldjpdMx> queryWrapper = QueryGenerator.initQueryWrapper(khjldjpdMx, request.getParameterMap());
	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	  String selections = request.getParameter("selections");
	  String rowKey = request.getParameter("rowKey");

	  if (oConvertUtils.isNotEmpty(selections)) {
		  List<String> selectionList = Arrays.asList(selections.split(","));
		  if(oConvertUtils.isNotEmpty(rowKey)){
			  queryWrapper.in(rowKey,selectionList);
		  }else{
			  queryWrapper.in("ID",selectionList);
		  }
	  }

	  List<KhjldjpdMx> exportList = service.list(queryWrapper);
	  if (CollUtil.isNotEmpty(exportList)){
		  for (int i = 0; i < exportList.size(); i++) {
			  exportList.get(i).setZhmc(exportList.get(i).getZzbz());
		  }
	  }
	  // Step.3 AutoPoi 导出Excel
	  String title= "客户经理等级评定明细";
	  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
	  mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
	  mv.addObject(NormalExcelConstants.CLASS, KhjldjpdMx.class);
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
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, KhjldjpdMx.class);
  }

}
