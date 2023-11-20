package org.cmms.modules.lydp.zbgl.log.controller;

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
import org.cmms.modules.lydp.zbgl.log.entity.LydpLog;
import org.cmms.modules.lydp.zbgl.log.service.ILydpLogService;
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
 * @Description: 浏阳大屏日期记录
 * @Author: jeecg-boot
 * @Date:   2023-02-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="浏阳大屏日期记录")
@RestController
@RequestMapping("/log/lydpLog")
public class LydpLogController extends JeecgController<LydpLog, ILydpLogService> {
	@Autowired
	private ILydpLogService lydpLogService;

	/**
	 * 分页列表查询
	 *
	 * @param lydpLog
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "浏阳大屏日期记录-分页列表查询")
	@ApiOperation(value="浏阳大屏日期记录-分页列表查询", notes="浏阳大屏日期记录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(LydpLog lydpLog,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<LydpLog> queryWrapper = QueryGenerator.initQueryWrapper(lydpLog, req.getParameterMap());
		Page<LydpLog> page = new Page<LydpLog>(pageNo, pageSize);
		IPage<LydpLog> pageList = lydpLogService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param lydpLog
	 * @return
	 */
	@AutoLog(value = "浏阳大屏日期记录-添加")
	@ApiOperation(value="浏阳大屏日期记录-添加", notes="浏阳大屏日期记录-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody LydpLog lydpLog) {
		lydpLogService.save(lydpLog);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param lydpLog
	 * @return
	 */
	@AutoLog(value = "浏阳大屏日期记录-编辑")
	@ApiOperation(value="浏阳大屏日期记录-编辑", notes="浏阳大屏日期记录-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody LydpLog lydpLog) {
		lydpLogService.updateById(lydpLog);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "浏阳大屏日期记录-通过id删除")
	@ApiOperation(value="浏阳大屏日期记录-通过id删除", notes="浏阳大屏日期记录-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		lydpLogService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "浏阳大屏日期记录-批量删除")
	@ApiOperation(value="浏阳大屏日期记录-批量删除", notes="浏阳大屏日期记录-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.lydpLogService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "浏阳大屏日期记录-通过id查询")
	@ApiOperation(value="浏阳大屏日期记录-通过id查询", notes="浏阳大屏日期记录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		LydpLog lydpLog = lydpLogService.getById(id);
		return Result.ok(lydpLog);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param lydpLog
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, LydpLog lydpLog) {
      return super.exportXls(request, lydpLog, LydpLog.class, "浏阳大屏日期记录");
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
      return super.importExcel(request, response, LydpLog.class);
  }

}
