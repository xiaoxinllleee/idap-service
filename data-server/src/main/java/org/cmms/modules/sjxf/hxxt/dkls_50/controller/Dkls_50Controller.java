package org.cmms.modules.sjxf.hxxt.dkls_50.controller;

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
import org.cmms.modules.sjxf.hxxt.dkls_50.entity.Dkls_50;
import org.cmms.modules.sjxf.hxxt.dkls_50.service.IDkls_50Service;
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
 * @Description: 贷款流水_50
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款流水_50")
@RestController
@RequestMapping("/dkls_50/dkls_50")
public class Dkls_50Controller extends JeecgController<Dkls_50, IDkls_50Service> {
	@Autowired
	private IDkls_50Service dkls_50Service;

	/**
	 * 分页列表查询
	 *
	 * @param dkls_50
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款流水_50-分页列表查询")
	@ApiOperation(value="贷款流水_50-分页列表查询", notes="贷款流水_50-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dkls_50 dkls_50,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dkls_50> queryWrapper = QueryGenerator.initQueryWrapper(dkls_50, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IDkls_50Service.class,dkls_50Service,pageNo,pageSize,queryWrapper,"acct_No","trn_Date","jrnl_No");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dkls_50
	 * @return
	 */
	@AutoLog(value = "贷款流水_50-添加")
	@ApiOperation(value="贷款流水_50-添加", notes="贷款流水_50-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dkls_50 dkls_50) {
		dkls_50Service.save(dkls_50);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dkls_50
	 * @return
	 */
	@AutoLog(value = "贷款流水_50-编辑")
	@ApiOperation(value="贷款流水_50-编辑", notes="贷款流水_50-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dkls_50 dkls_50) {
		dkls_50Service.updateById(dkls_50);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款流水_50-通过id删除")
	@ApiOperation(value="贷款流水_50-通过id删除", notes="贷款流水_50-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkls_50Service.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款流水_50-批量删除")
	@ApiOperation(value="贷款流水_50-批量删除", notes="贷款流水_50-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkls_50Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款流水_50-通过id查询")
	@ApiOperation(value="贷款流水_50-通过id查询", notes="贷款流水_50-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dkls_50 dkls_50 = dkls_50Service.getById(id);
		return Result.ok(dkls_50);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkls_50
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dkls_50 dkls_50) {
      return super.exportXls(request, dkls_50, Dkls_50.class, "贷款流水_50");
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
      return super.importExcel(request, response, Dkls_50.class);
  }

}
