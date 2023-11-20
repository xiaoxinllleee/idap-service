package org.cmms.modules.common.appXykFjxx.controller;

import java.io.*;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.common.appXykFjxx.entity.AppXykFjxx;
import org.cmms.modules.common.appXykFjxx.service.IAppXykFjxxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.common.appfjxx.controller.AppFjxxController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 信用卡附件信息_慈利
 * @Author: jeecg-boot
 * @Date:   2022-11-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags="信用卡附件信息_慈利")
@RestController
@RequestMapping("/appXykFjxx/appXykFjxx")
public class AppXykFjxxController extends JeecgController<AppXykFjxx, IAppXykFjxxService> {
	@Autowired
	private IAppXykFjxxService appXykFjxxService;


	 private enum AllowedImageType {
		 PNG("89504E47", "png"), JPG("FFD8FF", "jpg"),
		 GIF("47494638", "gif"), BMP("424D", "bmp");
		 private String code;
		 private String type;

		 AllowedImageType(String code, String type) {
			 this.code = code;
			 this.type = type;
		 }
	 }

	 private enum AllowedImageExt {
		 PNG("png"), JPG("jpg"),GIF("gif"), BMP("bmp");
		 private String ext;

		 AllowedImageExt(String ext) {
			 this.ext = ext;
		 }
	 }

	/**
	 * 分页列表查询
	 *
	 * @param appXykFjxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "信用卡附件信息_慈利-分页列表查询")
	@ApiOperation(value="信用卡附件信息_慈利-分页列表查询", notes="信用卡附件信息_慈利-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(AppXykFjxx appXykFjxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AppXykFjxx> queryWrapper = QueryGenerator.initQueryWrapper(appXykFjxx, req.getParameterMap());
		Page<AppXykFjxx> page = new Page<AppXykFjxx>(pageNo, pageSize);
		IPage<AppXykFjxx> pageList = appXykFjxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}


	/**
	 * 添加
	 *
	 * @param appXykFjxx
	 * @return
	 */
	@AutoLog(value = "信用卡附件信息_慈利-添加")
	@ApiOperation(value="信用卡附件信息_慈利-添加", notes="信用卡附件信息_慈利-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody AppXykFjxx appXykFjxx) {
		appXykFjxxService.save(appXykFjxx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param appXykFjxx
	 * @return
	 */
	@AutoLog(value = "信用卡附件信息_慈利-编辑")
	@ApiOperation(value="信用卡附件信息_慈利-编辑", notes="信用卡附件信息_慈利-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AppXykFjxx appXykFjxx) {
		appXykFjxxService.updateById(appXykFjxx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "信用卡附件信息_慈利-通过id删除")
	@ApiOperation(value="信用卡附件信息_慈利-通过id删除", notes="信用卡附件信息_慈利-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		appXykFjxxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "信用卡附件信息_慈利-批量删除")
	@ApiOperation(value="信用卡附件信息_慈利-批量删除", notes="信用卡附件信息_慈利-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.appXykFjxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "信用卡附件信息_慈利-通过id查询")
	@ApiOperation(value="信用卡附件信息_慈利-通过id查询", notes="信用卡附件信息_慈利-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AppXykFjxx appXykFjxx = appXykFjxxService.getById(id);
		return Result.ok(appXykFjxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param appXykFjxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, AppXykFjxx appXykFjxx) {
      return super.exportXls(request, appXykFjxx, AppXykFjxx.class, "信用卡附件信息_慈利");
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
      return super.importExcel(request, response, AppXykFjxx.class);
  }

}
