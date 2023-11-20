package org.cmms.modules.khlc.khfajg.controller;

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
import org.cmms.modules.khlc.khfajg.entity.PmaFEngineSchemeLog;
import org.cmms.modules.khlc.khfajg.service.IPmaFEngineSchemeLogService;
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
 * @Description: 引擎加工详细日志表
 * @Author: jeecg-boot
 * @Date:   2021-03-02
 * @Version: V1.0
 */
@Slf4j
@Api(tags="引擎加工详细日志表")
@RestController
@RequestMapping("/khfajg/pmaFEngineSchemeLog")
public class PmaFEngineSchemeLogController extends JeecgController<PmaFEngineSchemeLog, IPmaFEngineSchemeLogService> {
	@Autowired
	private IPmaFEngineSchemeLogService pmaFEngineSchemeLogService;
	
	/**
	 * 分页列表查询
	 *
	 * @param pmaFEngineSchemeLog
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "引擎加工详细日志表-分页列表查询")
	@ApiOperation(value="引擎加工详细日志表-分页列表查询", notes="引擎加工详细日志表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PmaFEngineSchemeLog pmaFEngineSchemeLog,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PmaFEngineSchemeLog> queryWrapper = QueryGenerator.initQueryWrapper(pmaFEngineSchemeLog, req.getParameterMap());
		Page<PmaFEngineSchemeLog> page = new Page<PmaFEngineSchemeLog>(pageNo, pageSize);
		IPage<PmaFEngineSchemeLog> pageList = pmaFEngineSchemeLogService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param pmaFEngineSchemeLog
	 * @return
	 */
	@AutoLog(value = "引擎加工详细日志表-添加")
	@ApiOperation(value="引擎加工详细日志表-添加", notes="引擎加工详细日志表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PmaFEngineSchemeLog pmaFEngineSchemeLog) {
		pmaFEngineSchemeLogService.save(pmaFEngineSchemeLog);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param pmaFEngineSchemeLog
	 * @return
	 */
	@AutoLog(value = "引擎加工详细日志表-编辑")
	@ApiOperation(value="引擎加工详细日志表-编辑", notes="引擎加工详细日志表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PmaFEngineSchemeLog pmaFEngineSchemeLog) {
		pmaFEngineSchemeLogService.updateById(pmaFEngineSchemeLog);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "引擎加工详细日志表-通过id删除")
	@ApiOperation(value="引擎加工详细日志表-通过id删除", notes="引擎加工详细日志表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		pmaFEngineSchemeLogService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "引擎加工详细日志表-批量删除")
	@ApiOperation(value="引擎加工详细日志表-批量删除", notes="引擎加工详细日志表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pmaFEngineSchemeLogService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "引擎加工详细日志表-通过id查询")
	@ApiOperation(value="引擎加工详细日志表-通过id查询", notes="引擎加工详细日志表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PmaFEngineSchemeLog pmaFEngineSchemeLog = pmaFEngineSchemeLogService.getById(id);
		return Result.ok(pmaFEngineSchemeLog);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param pmaFEngineSchemeLog
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, PmaFEngineSchemeLog pmaFEngineSchemeLog) {
      return super.exportXls(request, pmaFEngineSchemeLog, PmaFEngineSchemeLog.class, "引擎加工详细日志表");
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
      return super.importExcel(request, response, PmaFEngineSchemeLog.class);
  }

}
