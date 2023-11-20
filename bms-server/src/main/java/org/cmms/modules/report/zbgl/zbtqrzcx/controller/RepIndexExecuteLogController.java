package org.cmms.modules.report.zbgl.zbtqrzcx.controller;

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
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.zbgl.zbtqrzcx.entity.RepIndexExecuteLog;
import org.cmms.modules.report.zbgl.zbtqrzcx.service.IRepIndexExecuteLogService;
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
 * @Description: 指标提取日志查询
 * @Author: jeecg-boot
 * @Date:   2023-01-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="指标提取日志查询")
@RestController
@RequestMapping("/report/zbgl/zbtqrzcx/repIndexExecuteLog")
public class RepIndexExecuteLogController extends JeecgController<RepIndexExecuteLog, IRepIndexExecuteLogService> {
	@Autowired
	private IRepIndexExecuteLogService repIndexExecuteLogService;

	/**
	 * 分页列表查询
	 *
	 * @param repIndexExecuteLog
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "指标提取日志查询-分页列表查询")
	@ApiOperation(value="指标提取日志查询-分页列表查询", notes="指标提取日志查询-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(RepIndexExecuteLog repIndexExecuteLog,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RepIndexExecuteLog> queryWrapper = QueryGenerator.initQueryWrapper(repIndexExecuteLog, req.getParameterMap());
		queryWrapper.orderByDesc("sjrq");
		Page<RepIndexExecuteLog> page = new Page<RepIndexExecuteLog>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(IRepIndexExecuteLogService.class,repIndexExecuteLogService,pageNo,pageSize,queryWrapper,"sjrq");
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param repIndexExecuteLog
	 * @return
	 */
	@AutoLog(value = "指标提取日志查询-添加")
	@ApiOperation(value="指标提取日志查询-添加", notes="指标提取日志查询-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody RepIndexExecuteLog repIndexExecuteLog) {
		repIndexExecuteLogService.save(repIndexExecuteLog);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param repIndexExecuteLog
	 * @return
	 */
	@AutoLog(value = "指标提取日志查询-编辑")
	@ApiOperation(value="指标提取日志查询-编辑", notes="指标提取日志查询-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody RepIndexExecuteLog repIndexExecuteLog) {
		repIndexExecuteLogService.updateById(repIndexExecuteLog);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "指标提取日志查询-通过id删除")
	@ApiOperation(value="指标提取日志查询-通过id删除", notes="指标提取日志查询-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		repIndexExecuteLogService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "指标提取日志查询-批量删除")
	@ApiOperation(value="指标提取日志查询-批量删除", notes="指标提取日志查询-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.repIndexExecuteLogService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "指标提取日志查询-通过id查询")
	@ApiOperation(value="指标提取日志查询-通过id查询", notes="指标提取日志查询-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		RepIndexExecuteLog repIndexExecuteLog = repIndexExecuteLogService.getById(id);
		return Result.ok(repIndexExecuteLog);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param repIndexExecuteLog
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, RepIndexExecuteLog repIndexExecuteLog) {
      return super.exportXls(request, repIndexExecuteLog, RepIndexExecuteLog.class, "指标提取日志查询");
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
      return super.importExcel(request, response, RepIndexExecuteLog.class);
  }

}
