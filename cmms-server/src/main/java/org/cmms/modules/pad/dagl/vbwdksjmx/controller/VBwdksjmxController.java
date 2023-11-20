package org.cmms.modules.pad.dagl.vbwdksjmx.controller;

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
import org.cmms.modules.pad.dagl.vbwdksjmx.entity.VBwdksjmx;
import org.cmms.modules.pad.dagl.vbwdksjmx.service.IVBwdksjmxService;
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
 * @Description: 表外贷款数据明细视图
 * @Author: jeecg-boot
 * @Date:   2023-07-20
 * @Version: V1.0
 */
@Slf4j
@Api(tags="表外贷款数据明细视图")
@RestController
@RequestMapping("/bwdkgl/vBwdksjmx")
public class VBwdksjmxController extends JeecgController<VBwdksjmx, IVBwdksjmxService> {
	@Autowired
	private IVBwdksjmxService vBwdksjmxService;
	
	/**
	 * 分页列表查询
	 *
	 * @param vBwdksjmx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "表外贷款数据明细视图-分页列表查询")
	@ApiOperation(value="表外贷款数据明细视图-分页列表查询", notes="表外贷款数据明细视图-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VBwdksjmx vBwdksjmx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VBwdksjmx> queryWrapper = QueryGenerator.initQueryWrapper(vBwdksjmx, req.getParameterMap());
		Page<VBwdksjmx> page = new Page<VBwdksjmx>(pageNo, pageSize);
		IPage<VBwdksjmx> pageList = vBwdksjmxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param vBwdksjmx
	 * @return
	 */
	@AutoLog(value = "表外贷款数据明细视图-添加")
	@ApiOperation(value="表外贷款数据明细视图-添加", notes="表外贷款数据明细视图-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody VBwdksjmx vBwdksjmx) {
		vBwdksjmxService.save(vBwdksjmx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param vBwdksjmx
	 * @return
	 */
	@AutoLog(value = "表外贷款数据明细视图-编辑")
	@ApiOperation(value="表外贷款数据明细视图-编辑", notes="表外贷款数据明细视图-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody VBwdksjmx vBwdksjmx) {
		vBwdksjmxService.updateById(vBwdksjmx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "表外贷款数据明细视图-通过id删除")
	@ApiOperation(value="表外贷款数据明细视图-通过id删除", notes="表外贷款数据明细视图-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		vBwdksjmxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "表外贷款数据明细视图-批量删除")
	@ApiOperation(value="表外贷款数据明细视图-批量删除", notes="表外贷款数据明细视图-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.vBwdksjmxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "表外贷款数据明细视图-通过id查询")
	@ApiOperation(value="表外贷款数据明细视图-通过id查询", notes="表外贷款数据明细视图-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VBwdksjmx vBwdksjmx = vBwdksjmxService.getById(id);
		return Result.ok(vBwdksjmx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param vBwdksjmx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, VBwdksjmx vBwdksjmx) {
      return super.exportXls(request, vBwdksjmx, VBwdksjmx.class, "表外贷款数据明细视图");
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
      return super.importExcel(request, response, VBwdksjmx.class);
  }

}
