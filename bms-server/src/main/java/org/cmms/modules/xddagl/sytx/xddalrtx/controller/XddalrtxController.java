package org.cmms.modules.xddagl.sytx.xddalrtx.controller;

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
import org.cmms.modules.xddagl.sytx.xddalrtx.entity.Xddalrtx;
import org.cmms.modules.xddagl.sytx.xddalrtx.service.IXddalrtxService;
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
 * @Description: 信贷档案录入提醒
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="信贷档案录入提醒")
@RestController
@RequestMapping("/xddalrtx/xddalrtx")
public class XddalrtxController extends JeecgController<Xddalrtx, IXddalrtxService> {
	@Autowired
	private IXddalrtxService xddalrtxService;
	
	/**
	 * 分页列表查询
	 *
	 * @param xddalrtx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "信贷档案录入提醒-分页列表查询")
	@ApiOperation(value="信贷档案录入提醒-分页列表查询", notes="信贷档案录入提醒-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Xddalrtx xddalrtx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Xddalrtx> queryWrapper = QueryGenerator.initQueryWrapper(xddalrtx, req.getParameterMap());
		Page<Xddalrtx> page = new Page<Xddalrtx>(pageNo, pageSize);
		IPage<Xddalrtx> pageList = xddalrtxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param xddalrtx
	 * @return
	 */
	@AutoLog(value = "信贷档案录入提醒-添加")
	@ApiOperation(value="信贷档案录入提醒-添加", notes="信贷档案录入提醒-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Xddalrtx xddalrtx) {
		xddalrtxService.save(xddalrtx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param xddalrtx
	 * @return
	 */
	@AutoLog(value = "信贷档案录入提醒-编辑")
	@ApiOperation(value="信贷档案录入提醒-编辑", notes="信贷档案录入提醒-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Xddalrtx xddalrtx) {
		xddalrtxService.updateById(xddalrtx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "信贷档案录入提醒-通过id删除")
	@ApiOperation(value="信贷档案录入提醒-通过id删除", notes="信贷档案录入提醒-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		xddalrtxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "信贷档案录入提醒-批量删除")
	@ApiOperation(value="信贷档案录入提醒-批量删除", notes="信贷档案录入提醒-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.xddalrtxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "信贷档案录入提醒-通过id查询")
	@ApiOperation(value="信贷档案录入提醒-通过id查询", notes="信贷档案录入提醒-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Xddalrtx xddalrtx = xddalrtxService.getById(id);
		return Result.ok(xddalrtx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param xddalrtx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Xddalrtx xddalrtx) {
      return super.exportXls(request, xddalrtx, Xddalrtx.class, "信贷档案录入提醒");
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
      return super.importExcel(request, response, Xddalrtx.class);
  }

}
