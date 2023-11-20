package org.cmms.modules.xdgl.nsb.controller;

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
import org.cmms.modules.xdgl.nsb.entity.NsbTjfx;
import org.cmms.modules.xdgl.nsb.service.INsbTjfxService;
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
 * @Description: 年审统计分析
 * @Author: jeecg-boot
 * @Date:   2023-07-04
 * @Version: V1.0
 */
@Slf4j
@Api(tags="年审统计分析")
@RestController
@RequestMapping("/nsb/nsbTjfx")
public class NsbTjfxController extends JeecgController<NsbTjfx, INsbTjfxService> {
	@Autowired
	private INsbTjfxService nsbTjfxService;
	
	/**
	 * 分页列表查询
	 *
	 * @param nsbTjfx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "年审统计分析-分页列表查询")
	@ApiOperation(value="年审统计分析-分页列表查询", notes="年审统计分析-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(NsbTjfx nsbTjfx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<NsbTjfx> queryWrapper = QueryGenerator.initQueryWrapper(nsbTjfx, req.getParameterMap());
		Page<NsbTjfx> page = new Page<NsbTjfx>(pageNo, pageSize);
		IPage<NsbTjfx> pageList = nsbTjfxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param nsbTjfx
	 * @return
	 */
	@AutoLog(value = "年审统计分析-添加")
	@ApiOperation(value="年审统计分析-添加", notes="年审统计分析-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody NsbTjfx nsbTjfx) {
		nsbTjfxService.save(nsbTjfx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param nsbTjfx
	 * @return
	 */
	@AutoLog(value = "年审统计分析-编辑")
	@ApiOperation(value="年审统计分析-编辑", notes="年审统计分析-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody NsbTjfx nsbTjfx) {
		nsbTjfxService.updateById(nsbTjfx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "年审统计分析-通过id删除")
	@ApiOperation(value="年审统计分析-通过id删除", notes="年审统计分析-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		nsbTjfxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "年审统计分析-批量删除")
	@ApiOperation(value="年审统计分析-批量删除", notes="年审统计分析-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.nsbTjfxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "年审统计分析-通过id查询")
	@ApiOperation(value="年审统计分析-通过id查询", notes="年审统计分析-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		NsbTjfx nsbTjfx = nsbTjfxService.getById(id);
		return Result.ok(nsbTjfx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param nsbTjfx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, NsbTjfx nsbTjfx) {
      return super.exportXls(request, nsbTjfx, NsbTjfx.class, "年审统计分析");
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
      return super.importExcel(request, response, NsbTjfx.class);
  }

  @RequestMapping("/tq")
  public Result<?> tq(){
	  service.tq();
	  return Result.ok();
  }

}
