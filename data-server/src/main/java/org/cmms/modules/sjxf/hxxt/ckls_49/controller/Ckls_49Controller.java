package org.cmms.modules.sjxf.hxxt.ckls_49.controller;

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
import org.cmms.modules.sjxf.hxxt.ckls_49.entity.Ckls_49;
import org.cmms.modules.sjxf.hxxt.ckls_49.service.ICkls_49Service;
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
 * @Description: 存款流水_49
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存款流水_49")
@RestController
@RequestMapping("/ckls_49/ckls_49")
public class Ckls_49Controller extends JeecgController<Ckls_49, ICkls_49Service> {
	@Autowired
	private ICkls_49Service ckls_49Service;

	/**
	 * 分页列表查询
	 *
	 * @param ckls_49
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "存款流水_49-分页列表查询")
	@ApiOperation(value="存款流水_49-分页列表查询", notes="存款流水_49-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ckls_49 ckls_49,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Ckls_49> queryWrapper = QueryGenerator.initQueryWrapper(ckls_49, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkls_49Service.class,ckls_49Service,pageNo,pageSize,queryWrapper,"acct_No","jrnl_No","trn_Date");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param ckls_49
	 * @return
	 */
	@AutoLog(value = "存款流水_49-添加")
	@ApiOperation(value="存款流水_49-添加", notes="存款流水_49-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ckls_49 ckls_49) {
		ckls_49Service.save(ckls_49);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param ckls_49
	 * @return
	 */
	@AutoLog(value = "存款流水_49-编辑")
	@ApiOperation(value="存款流水_49-编辑", notes="存款流水_49-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ckls_49 ckls_49) {
		ckls_49Service.updateById(ckls_49);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款流水_49-通过id删除")
	@ApiOperation(value="存款流水_49-通过id删除", notes="存款流水_49-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ckls_49Service.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "存款流水_49-批量删除")
	@ApiOperation(value="存款流水_49-批量删除", notes="存款流水_49-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ckls_49Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款流水_49-通过id查询")
	@ApiOperation(value="存款流水_49-通过id查询", notes="存款流水_49-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ckls_49 ckls_49 = ckls_49Service.getById(id);
		return Result.ok(ckls_49);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ckls_49
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ckls_49 ckls_49) {
      return super.exportXls(request, ckls_49, Ckls_49.class, "存款流水_49");
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
      return super.importExcel(request, response, Ckls_49.class);
  }

}
