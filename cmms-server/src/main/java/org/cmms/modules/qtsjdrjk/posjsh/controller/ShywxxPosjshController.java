package org.cmms.modules.qtsjdrjk.posjsh.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
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
import org.cmms.modules.qtsjdrjk.posjsh.entity.ShywxxPosjsh;
import org.cmms.modules.qtsjdrjk.posjsh.service.IShywxxPosjshService;
import org.cmms.modules.qtsjdrjk.posjsh.verify.PosjshImportVerify;
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
 * @Description: POS机商户
 * @Author: jeecg-boot
 * @Date:   2022-07-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="POS机商户")
@RestController
@RequestMapping("/posjsh/shywxxPosjsh")
public class ShywxxPosjshController extends JeecgController<ShywxxPosjsh, IShywxxPosjshService> {
	@Autowired
	private IShywxxPosjshService shywxxPosjshService;
	 @Autowired
	 private PosjshImportVerify posjshImportVerify;
	
	/**
	 * 分页列表查询
	 *
	 * @param shywxxPosjsh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "POS机商户-分页列表查询")
	@ApiOperation(value="POS机商户-分页列表查询", notes="POS机商户-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ShywxxPosjsh shywxxPosjsh,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ShywxxPosjsh> queryWrapper = QueryGenerator.initQueryWrapper(shywxxPosjsh, req.getParameterMap());
		Page<ShywxxPosjsh> page = new Page<ShywxxPosjsh>(pageNo, pageSize);
		IPage<ShywxxPosjsh> pageList = shywxxPosjshService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param shywxxPosjsh
	 * @return
	 */
	@AutoLog(value = "POS机商户-添加")
	@ApiOperation(value="POS机商户-添加", notes="POS机商户-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ShywxxPosjsh shywxxPosjsh) {
		shywxxPosjshService.save(shywxxPosjsh);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param shywxxPosjsh
	 * @return
	 */
	@AutoLog(value = "POS机商户-编辑")
	@ApiOperation(value="POS机商户-编辑", notes="POS机商户-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ShywxxPosjsh shywxxPosjsh) {
		shywxxPosjshService.updateById(shywxxPosjsh);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "POS机商户-通过id删除")
	@ApiOperation(value="POS机商户-通过id删除", notes="POS机商户-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		shywxxPosjshService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "POS机商户-批量删除")
	@ApiOperation(value="POS机商户-批量删除", notes="POS机商户-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.shywxxPosjshService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "POS机商户-通过id查询")
	@ApiOperation(value="POS机商户-通过id查询", notes="POS机商户-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ShywxxPosjsh shywxxPosjsh = shywxxPosjshService.getById(id);
		return Result.ok(shywxxPosjsh);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param shywxxPosjsh
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ShywxxPosjsh shywxxPosjsh) {
      return super.exportXls(request, shywxxPosjsh, ShywxxPosjsh.class, "POS机商户");
  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
//  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
//  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
//      return super.importExcel(request, response, ShywxxPosjsh.class);
//  }

	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 return super.exportTemplateXls(ShywxxPosjsh.class, "POS机商户导入模板");
	 }

	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 return super.importExcelByTemplate(jsonObject, request, response, ShywxxPosjsh.class, posjshImportVerify);
	 }

}
