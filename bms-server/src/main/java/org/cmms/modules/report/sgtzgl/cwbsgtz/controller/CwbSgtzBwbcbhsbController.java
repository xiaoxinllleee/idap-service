package org.cmms.modules.report.sgtzgl.cwbsgtz.controller;

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
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.cwbsgtz.entity.CwbSgtzBwbcbhsb;
import org.cmms.modules.report.sgtzgl.cwbsgtz.service.ICwbSgtzBwbcbhsbService;
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
 * @Description: 财务部-成本核算表
 * @Author: jeecg-boot
 * @Date:   2023-06-13
 * @Version: V1.0
 */
@Slf4j
@Api(tags="财务部-成本核算表")
@RestController
@RequestMapping("/cwbsgtz/cwbSgtzBwbcbhsb")
public class CwbSgtzBwbcbhsbController extends JeecgController<CwbSgtzBwbcbhsb, ICwbSgtzBwbcbhsbService> {
	@Autowired
	private ICwbSgtzBwbcbhsbService cwbSgtzBwbcbhsbService;
	
	/**
	 * 分页列表查询
	 *
	 * @param cwbSgtzBwbcbhsb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "财务部-成本核算表-分页列表查询")
	@ApiOperation(value="财务部-成本核算表-分页列表查询", notes="财务部-成本核算表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CwbSgtzBwbcbhsb cwbSgtzBwbcbhsb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CwbSgtzBwbcbhsb> queryWrapper = QueryGenerator.initQueryWrapper(cwbSgtzBwbcbhsb, req.getParameterMap());
		IPage pageList = PageUtil.toPage(ICwbSgtzBwbcbhsbService.class, cwbSgtzBwbcbhsbService, pageNo, pageSize, queryWrapper,"sjrq");
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param cwbSgtzBwbcbhsb
	 * @return
	 */
	@AutoLog(value = "财务部-成本核算表-添加")
	@ApiOperation(value="财务部-成本核算表-添加", notes="财务部-成本核算表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CwbSgtzBwbcbhsb cwbSgtzBwbcbhsb) {
		cwbSgtzBwbcbhsbService.save(cwbSgtzBwbcbhsb);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param cwbSgtzBwbcbhsb
	 * @return
	 */
	@AutoLog(value = "财务部-成本核算表-编辑")
	@ApiOperation(value="财务部-成本核算表-编辑", notes="财务部-成本核算表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CwbSgtzBwbcbhsb cwbSgtzBwbcbhsb) {
		cwbSgtzBwbcbhsbService.updateById(cwbSgtzBwbcbhsb);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "财务部-成本核算表-通过id删除")
	@ApiOperation(value="财务部-成本核算表-通过id删除", notes="财务部-成本核算表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		cwbSgtzBwbcbhsbService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "财务部-成本核算表-批量删除")
	@ApiOperation(value="财务部-成本核算表-批量删除", notes="财务部-成本核算表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.cwbSgtzBwbcbhsbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "财务部-成本核算表-通过id查询")
	@ApiOperation(value="财务部-成本核算表-通过id查询", notes="财务部-成本核算表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CwbSgtzBwbcbhsb cwbSgtzBwbcbhsb = cwbSgtzBwbcbhsbService.getById(id);
		return Result.ok(cwbSgtzBwbcbhsb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param cwbSgtzBwbcbhsb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, CwbSgtzBwbcbhsb cwbSgtzBwbcbhsb) {
      return super.exportXls(request, cwbSgtzBwbcbhsb, CwbSgtzBwbcbhsb.class, "财务部-成本核算表");
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
      return super.importExcel(request, response, CwbSgtzBwbcbhsb.class);
  }

}
