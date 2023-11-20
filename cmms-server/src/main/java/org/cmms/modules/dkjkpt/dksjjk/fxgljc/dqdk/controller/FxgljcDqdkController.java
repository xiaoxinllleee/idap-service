package org.cmms.modules.dkjkpt.dksjjk.fxgljc.dqdk.controller;

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
import org.cmms.modules.dkjkpt.dksjjk.fxgljc.dqdk.entity.FxgljcDqdk;
import org.cmms.modules.dkjkpt.dksjjk.fxgljc.dqdk.service.IFxgljcDqdkService;
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
 * @Description: 到期贷款
 * @Author: jeecg-boot
 * @Date:   2023-08-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags="到期贷款")
@RestController
@RequestMapping("/dqdk/fxgljcDqdk")
public class FxgljcDqdkController extends JeecgController<FxgljcDqdk, IFxgljcDqdkService> {
	@Autowired
	private IFxgljcDqdkService fxgljcDqdkService;

	/**
	 * 分页列表查询
	 *
	 * @param fxgljcDqdk
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "到期贷款-分页列表查询")
	@ApiOperation(value="到期贷款-分页列表查询", notes="到期贷款-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(FxgljcDqdk fxgljcDqdk,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<FxgljcDqdk> queryWrapper = QueryGenerator.initQueryWrapper(fxgljcDqdk, req.getParameterMap());
		Page<FxgljcDqdk> page = new Page<FxgljcDqdk>(pageNo, pageSize);
		IPage<FxgljcDqdk> pageList = fxgljcDqdkService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param fxgljcDqdk
	 * @return
	 */
	@AutoLog(value = "到期贷款-添加")
	@ApiOperation(value="到期贷款-添加", notes="到期贷款-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody FxgljcDqdk fxgljcDqdk) {
		fxgljcDqdkService.save(fxgljcDqdk);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param fxgljcDqdk
	 * @return
	 */
	@AutoLog(value = "到期贷款-编辑")
	@ApiOperation(value="到期贷款-编辑", notes="到期贷款-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody FxgljcDqdk fxgljcDqdk) {
		fxgljcDqdkService.updateById(fxgljcDqdk);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "到期贷款-通过id删除")
	@ApiOperation(value="到期贷款-通过id删除", notes="到期贷款-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		fxgljcDqdkService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "到期贷款-批量删除")
	@ApiOperation(value="到期贷款-批量删除", notes="到期贷款-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.fxgljcDqdkService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "到期贷款-通过id查询")
	@ApiOperation(value="到期贷款-通过id查询", notes="到期贷款-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		FxgljcDqdk fxgljcDqdk = fxgljcDqdkService.getById(id);
		return Result.ok(fxgljcDqdk);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param fxgljcDqdk
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, FxgljcDqdk fxgljcDqdk) {
      return super.exportXls(request, fxgljcDqdk, FxgljcDqdk.class, "到期贷款");
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
      return super.importExcel(request, response, FxgljcDqdk.class);
  }

}
