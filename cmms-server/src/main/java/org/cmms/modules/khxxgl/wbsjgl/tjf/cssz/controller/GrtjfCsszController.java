package org.cmms.modules.khxxgl.wbsjgl.tjf.cssz.controller;

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
import org.cmms.modules.khxxgl.wbsjgl.tjf.cssz.entity.GrtjfCssz;
import org.cmms.modules.khxxgl.wbsjgl.tjf.cssz.service.IGrtjfCsszService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khxxgl.wbsjgl.tjf.grtjf.entity.Grtjf;
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
 * @Description: 个人碳积分参数设置
 * @Author: jeecg-boot
 * @Date:   2022-11-21
 * @Version: V1.0
 */
@Slf4j
@Api(tags="个人碳积分参数设置")
@RestController
@RequestMapping("/tjf/grtjfCssz")
public class GrtjfCsszController extends JeecgController<GrtjfCssz, IGrtjfCsszService> {
	@Autowired
	private IGrtjfCsszService grtjfCsszService;
	
	/**
	 * 分页列表查询
	 *
	 * @param grtjfCssz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "个人碳积分参数设置-分页列表查询")
	@ApiOperation(value="个人碳积分参数设置-分页列表查询", notes="个人碳积分参数设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(GrtjfCssz grtjfCssz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<GrtjfCssz> queryWrapper = QueryGenerator.initQueryWrapper(grtjfCssz, req.getParameterMap());
		Page<GrtjfCssz> page = new Page<GrtjfCssz>(pageNo, pageSize);
		IPage<GrtjfCssz> pageList = grtjfCsszService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param grtjfCssz
	 * @return
	 */
	@AutoLog(value = "个人碳积分参数设置-添加")
	@ApiOperation(value="个人碳积分参数设置-添加", notes="个人碳积分参数设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody GrtjfCssz grtjfCssz) {
		grtjfCsszService.save(grtjfCssz);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param grtjfCssz
	 * @return
	 */
	@AutoLog(value = "个人碳积分参数设置-编辑")
	@ApiOperation(value="个人碳积分参数设置-编辑", notes="个人碳积分参数设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody GrtjfCssz grtjfCssz) {
		grtjfCsszService.updateById(grtjfCssz);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "个人碳积分参数设置-通过id删除")
	@ApiOperation(value="个人碳积分参数设置-通过id删除", notes="个人碳积分参数设置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		grtjfCsszService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "个人碳积分参数设置-批量删除")
	@ApiOperation(value="个人碳积分参数设置-批量删除", notes="个人碳积分参数设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.grtjfCsszService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "个人碳积分参数设置-通过id查询")
	@ApiOperation(value="个人碳积分参数设置-通过id查询", notes="个人碳积分参数设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		GrtjfCssz grtjfCssz = grtjfCsszService.getById(id);
		return Result.ok(grtjfCssz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param grtjfCssz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, GrtjfCssz grtjfCssz) {
      return super.exportXls(request, grtjfCssz, GrtjfCssz.class, "个人碳积分参数设置");
  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
	  return super.importExcelByTemplate(jsonObject, request, response, GrtjfCssz.class, null);
  }

	 /**
	  * 导入模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 return super.exportTemplateXls(GrtjfCssz.class, "个人碳积分导入模板");
	 }

}
