package org.cmms.modules.dkjkpt.dksjjk.fxgljc.yqjjbll.controller;

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
import org.cmms.modules.dkjkpt.dksjjk.fxgljc.yqjjbll.entity.FxgljcYqjjbll;
import org.cmms.modules.dkjkpt.dksjjk.fxgljc.yqjjbll.service.IFxgljcYqjjbllService;
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
 * @Description: 利息、本金逾期将进不良类
 * @Author: jeecg-boot
 * @Date:   2023-08-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags="利息、本金逾期将进不良类")
@RestController
@RequestMapping("/yqjjbll/fxgljcYqjjbll")
public class FxgljcYqjjbllController extends JeecgController<FxgljcYqjjbll, IFxgljcYqjjbllService> {
	@Autowired
	private IFxgljcYqjjbllService fxgljcYqjjbllService;

	/**
	 * 分页列表查询
	 *
	 * @param fxgljcYqjjbll
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "利息、本金逾期将进不良类-分页列表查询")
	@ApiOperation(value="利息、本金逾期将进不良类-分页列表查询", notes="利息、本金逾期将进不良类-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(FxgljcYqjjbll fxgljcYqjjbll,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<FxgljcYqjjbll> queryWrapper = QueryGenerator.initQueryWrapper(fxgljcYqjjbll, req.getParameterMap());
		Page<FxgljcYqjjbll> page = new Page<FxgljcYqjjbll>(pageNo, pageSize);
		IPage<FxgljcYqjjbll> pageList = fxgljcYqjjbllService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param fxgljcYqjjbll
	 * @return
	 */
	@AutoLog(value = "利息、本金逾期将进不良类-添加")
	@ApiOperation(value="利息、本金逾期将进不良类-添加", notes="利息、本金逾期将进不良类-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody FxgljcYqjjbll fxgljcYqjjbll) {
		fxgljcYqjjbllService.save(fxgljcYqjjbll);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param fxgljcYqjjbll
	 * @return
	 */
	@AutoLog(value = "利息、本金逾期将进不良类-编辑")
	@ApiOperation(value="利息、本金逾期将进不良类-编辑", notes="利息、本金逾期将进不良类-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody FxgljcYqjjbll fxgljcYqjjbll) {
		fxgljcYqjjbllService.updateById(fxgljcYqjjbll);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "利息、本金逾期将进不良类-通过id删除")
	@ApiOperation(value="利息、本金逾期将进不良类-通过id删除", notes="利息、本金逾期将进不良类-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		fxgljcYqjjbllService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "利息、本金逾期将进不良类-批量删除")
	@ApiOperation(value="利息、本金逾期将进不良类-批量删除", notes="利息、本金逾期将进不良类-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.fxgljcYqjjbllService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "利息、本金逾期将进不良类-通过id查询")
	@ApiOperation(value="利息、本金逾期将进不良类-通过id查询", notes="利息、本金逾期将进不良类-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		FxgljcYqjjbll fxgljcYqjjbll = fxgljcYqjjbllService.getById(id);
		return Result.ok(fxgljcYqjjbll);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param fxgljcYqjjbll
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, FxgljcYqjjbll fxgljcYqjjbll) {
      return super.exportXls(request, fxgljcYqjjbll, FxgljcYqjjbll.class, "利息、本金逾期将进不良类");
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
      return super.importExcel(request, response, FxgljcYqjjbll.class);
  }

}
