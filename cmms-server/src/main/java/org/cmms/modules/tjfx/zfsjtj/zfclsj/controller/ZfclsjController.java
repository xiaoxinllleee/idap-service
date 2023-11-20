package org.cmms.modules.tjfx.zfsjtj.zfclsj.controller;

import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.tjfx.zfsjtj.zfclsj.entity.Zfclsj;
import org.cmms.modules.tjfx.zfsjtj.zfclsj.service.IZfclsjService;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 走访存量数据
 * @Author: jeecg-boot
 * @Date:   2023-02-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="走访存量数据")
@RestController
@RequestMapping("/zfsjtj/zfclsj")
public class ZfclsjController extends JeecgController<Zfclsj, IZfclsjService> {
	@Autowired
	private IZfclsjService zfclsjService;
	
	/**
	 * 分页列表查询
	 *
	 * @param zfclsj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "走访存量数据-分页列表查询")
	@ApiOperation(value="走访存量数据-分页列表查询", notes="走访存量数据-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zfclsj zfclsj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Zfclsj> queryWrapper = QueryGenerator.initQueryWrapper(zfclsj, req.getParameterMap());
		Page<Zfclsj> page = new Page<Zfclsj>(pageNo, pageSize);
		IPage<Zfclsj> pageList = zfclsjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param zfclsj
	 * @return
	 */
	@AutoLog(value = "走访存量数据-添加")
	@ApiOperation(value="走访存量数据-添加", notes="走访存量数据-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zfclsj zfclsj) {
		zfclsjService.save(zfclsj);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param zfclsj
	 * @return
	 */
	@AutoLog(value = "走访存量数据-编辑")
	@ApiOperation(value="走访存量数据-编辑", notes="走访存量数据-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zfclsj zfclsj) {
		zfclsjService.updateById(zfclsj);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "走访存量数据-通过id删除")
	@ApiOperation(value="走访存量数据-通过id删除", notes="走访存量数据-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zfclsjService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "走访存量数据-批量删除")
	@ApiOperation(value="走访存量数据-批量删除", notes="走访存量数据-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zfclsjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "走访存量数据-通过id查询")
	@ApiOperation(value="走访存量数据-通过id查询", notes="走访存量数据-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zfclsj zfclsj = zfclsjService.getById(id);
		return Result.ok(zfclsj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zfclsj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zfclsj zfclsj) {
      return super.exportXls(request, zfclsj, Zfclsj.class, "走访存量数据");
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
	  return super.importExcelByTemplate(jsonObject, request, response, Zfclsj.class, null);
  }

	 /**
	  * 导出模板Excel
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "走访存量数据导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, Zfclsj.class);
		 ExportParams exportParams = new ExportParams("走访存量数据导入模板", "走访存量数据");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return mv;
	 }

}
