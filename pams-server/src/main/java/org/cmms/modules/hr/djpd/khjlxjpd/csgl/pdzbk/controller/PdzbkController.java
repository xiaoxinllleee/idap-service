package org.cmms.modules.hr.djpd.khjlxjpd.csgl.pdzbk.controller;

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
import org.cmms.modules.hr.djpd.khjlxjpd.csgl.pdzbk.entity.Pdzbk;
import org.cmms.modules.hr.djpd.khjlxjpd.csgl.pdzbk.service.IPdzbkService;
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
 * @Description: 评定指标库
 * @Author: jeecg-boot
 * @Date:   2021-09-02
 * @Version: V1.0
 */
@Slf4j
@Api(tags="评定指标库")
@RestController
@RequestMapping("/pdzbk/pdzbk")
public class PdzbkController extends JeecgController<Pdzbk, IPdzbkService> {
	@Autowired
	private IPdzbkService pdzbkService;
	
	/**
	 * 分页列表查询
	 *
	 * @param pdzbk
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "评定指标库-分页列表查询")
	@ApiOperation(value="评定指标库-分页列表查询", notes="评定指标库-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Pdzbk pdzbk,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Pdzbk> queryWrapper = QueryGenerator.initQueryWrapper(pdzbk, req.getParameterMap());
		Page<Pdzbk> page = new Page<Pdzbk>(pageNo, pageSize);
		IPage<Pdzbk> pageList = pdzbkService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
	  * 列表查询
	  *
	  * @param pdzbk
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "评定指标库-列表查询")
	 @ApiOperation(value="评定指标库-列表查询", notes="评定指标库-列表查询")
	 @GetMapping(value = "/listall")
	 public Result<?> queryList(Pdzbk pdzbk,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		 QueryWrapper<Pdzbk> queryWrapper = QueryGenerator.initQueryWrapper(pdzbk, req.getParameterMap());
		 List<Pdzbk> list = pdzbkService.list(queryWrapper);
		 return Result.ok(list);
	 }
	
	/**
	 * 添加
	 *
	 * @param pdzbk
	 * @return
	 */
	@AutoLog(value = "评定指标库-添加")
	@ApiOperation(value="评定指标库-添加", notes="评定指标库-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Pdzbk pdzbk) {
		pdzbkService.save(pdzbk);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param pdzbk
	 * @return
	 */
	@AutoLog(value = "评定指标库-编辑")
	@ApiOperation(value="评定指标库-编辑", notes="评定指标库-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Pdzbk pdzbk) {
		pdzbkService.updateById(pdzbk);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评定指标库-通过id删除")
	@ApiOperation(value="评定指标库-通过id删除", notes="评定指标库-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		pdzbkService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "评定指标库-批量删除")
	@ApiOperation(value="评定指标库-批量删除", notes="评定指标库-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pdzbkService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评定指标库-通过id查询")
	@ApiOperation(value="评定指标库-通过id查询", notes="评定指标库-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Pdzbk pdzbk = pdzbkService.getById(id);
		return Result.ok(pdzbk);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param pdzbk
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Pdzbk pdzbk) {
      return super.exportXls(request, pdzbk, Pdzbk.class, "评定指标库");
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
      return super.importExcel(request, response, Pdzbk.class);
  }

}
