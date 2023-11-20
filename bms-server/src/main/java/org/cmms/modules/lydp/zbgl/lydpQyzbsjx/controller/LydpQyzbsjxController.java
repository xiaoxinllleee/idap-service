package org.cmms.modules.lydp.zbgl.lydpQyzbsjx.controller;

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
import org.cmms.modules.lydp.zbgl.lydpQyzbsjx.entity.LydpQyzbsjx;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.lydp.zbgl.lydpQyzbsjx.service.ILydpQyzbsjxService;
import org.cmms.modules.report.zbgl.qyzbsjx.service.IQyzbsjxglService;
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
 * @Description: 浏阳大屏指标区域数据项
 * @Author: jeecg-boot
 * @Date:   2023-02-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags="浏阳大屏指标区域数据项")
@RestController
@RequestMapping("/lydpQyzbsjx/lydpQyzbsjx")
public class LydpQyzbsjxController extends JeecgController<LydpQyzbsjx, ILydpQyzbsjxService> {
	@Autowired
	private ILydpQyzbsjxService lydpQyzbsjxService;

	/**
	 * 分页列表查询
	 *
	 * @param lydpQyzbsjx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "浏阳大屏指标区域数据项-分页列表查询")
	@ApiOperation(value="浏阳大屏指标区域数据项-分页列表查询", notes="浏阳大屏指标区域数据项-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(LydpQyzbsjx lydpQyzbsjx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<LydpQyzbsjx> queryWrapper = QueryGenerator.initQueryWrapper(lydpQyzbsjx, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(ILydpQyzbsjxService.class,lydpQyzbsjxService,pageNo,pageSize,queryWrapper,"zbid");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param lydpQyzbsjx
	 * @return
	 */
	@AutoLog(value = "浏阳大屏指标区域数据项-添加")
	@ApiOperation(value="浏阳大屏指标区域数据项-添加", notes="浏阳大屏指标区域数据项-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody LydpQyzbsjx lydpQyzbsjx) {
		lydpQyzbsjxService.save(lydpQyzbsjx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param lydpQyzbsjx
	 * @return
	 */
	@AutoLog(value = "浏阳大屏指标区域数据项-编辑")
	@ApiOperation(value="浏阳大屏指标区域数据项-编辑", notes="浏阳大屏指标区域数据项-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody LydpQyzbsjx lydpQyzbsjx) {
		lydpQyzbsjxService.updateById(lydpQyzbsjx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "浏阳大屏指标区域数据项-通过id删除")
	@ApiOperation(value="浏阳大屏指标区域数据项-通过id删除", notes="浏阳大屏指标区域数据项-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		lydpQyzbsjxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "浏阳大屏指标区域数据项-批量删除")
	@ApiOperation(value="浏阳大屏指标区域数据项-批量删除", notes="浏阳大屏指标区域数据项-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.lydpQyzbsjxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "浏阳大屏指标区域数据项-通过id查询")
	@ApiOperation(value="浏阳大屏指标区域数据项-通过id查询", notes="浏阳大屏指标区域数据项-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		LydpQyzbsjx lydpQyzbsjx = lydpQyzbsjxService.getById(id);
		return Result.ok(lydpQyzbsjx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param lydpQyzbsjx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, LydpQyzbsjx lydpQyzbsjx) {
      return super.exportXls(request, lydpQyzbsjx, LydpQyzbsjx.class, "浏阳大屏指标区域数据项");
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
      return super.importExcel(request, response, LydpQyzbsjx.class);
  }

}
