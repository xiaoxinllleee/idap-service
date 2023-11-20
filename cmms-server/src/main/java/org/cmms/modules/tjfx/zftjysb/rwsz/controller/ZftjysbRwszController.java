package org.cmms.modules.tjfx.zftjysb.rwsz.controller;

import java.io.File;
import java.io.InputStream;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.zftjysb.rwsz.entity.ZftjysbRwsz;
import org.cmms.modules.tjfx.zftjysb.rwsz.entity.ZftjysbRwszImport;
import org.cmms.modules.tjfx.zftjysb.rwsz.service.IZftjysbRwszService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 走访统计验收表-任务设置
 * @Author: jeecg-boot
 * @Date:   2021-09-01
 * @Version: V1.0
 */
@Slf4j
@Api(tags="走访统计验收表-任务设置")
@RestController
@RequestMapping("/rwsz/zftjysbRwsz")
public class ZftjysbRwszController extends JeecgController<ZftjysbRwsz, IZftjysbRwszService> {
	@Autowired
	private IZftjysbRwszService zftjysbRwszService;
	 @Value(value = "${common.path.upload}")
	 private String uploadpath;
	/**
	 * 分页列表查询
	 *
	 * @param zftjysbRwsz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "走访统计验收表-任务设置-分页列表查询")
	@ApiOperation(value="走访统计验收表-任务设置-分页列表查询", notes="走访统计验收表-任务设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZftjysbRwsz zftjysbRwsz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ZftjysbRwsz> queryWrapper = QueryGenerator.initQueryWrapper(zftjysbRwsz, req.getParameterMap());
		Page<ZftjysbRwsz> page = new Page<ZftjysbRwsz>(pageNo, pageSize);
		IPage<ZftjysbRwsz> pageList = zftjysbRwszService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param zftjysbRwsz
	 * @return
	 */
	@AutoLog(value = "走访统计验收表-任务设置-添加")
	@ApiOperation(value="走访统计验收表-任务设置-添加", notes="走访统计验收表-任务设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZftjysbRwsz zftjysbRwsz) {
		QueryWrapper<ZftjysbRwsz> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("zzbz", zftjysbRwsz.getZzbz());
		queryWrapper.eq("xzc", zftjysbRwsz.getXzc());
		List<ZftjysbRwsz> list = zftjysbRwszService.list(queryWrapper);
		if (!list.isEmpty()) {
			return Result.error("已经存在的数据！");
		}
		zftjysbRwszService.save(zftjysbRwsz);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param zftjysbRwsz
	 * @return
	 */
	@AutoLog(value = "走访统计验收表-任务设置-编辑")
	@ApiOperation(value="走访统计验收表-任务设置-编辑", notes="走访统计验收表-任务设置-编辑")
	@PostMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZftjysbRwsz zftjysbRwsz) {
		QueryWrapper<ZftjysbRwsz> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("zzbz", zftjysbRwsz.getZzbz());
		queryWrapper.eq("xzc", zftjysbRwsz.getXzc());
		List<ZftjysbRwsz> list = zftjysbRwszService.list(queryWrapper);
		if (!list.isEmpty()) {
			return Result.error("已经存在的数据！");
		}
		zftjysbRwszService.updateById(zftjysbRwsz);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "走访统计验收表-任务设置-通过id删除")
	@ApiOperation(value="走访统计验收表-任务设置-通过id删除", notes="走访统计验收表-任务设置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zftjysbRwszService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "走访统计验收表-任务设置-批量删除")
	@ApiOperation(value="走访统计验收表-任务设置-批量删除", notes="走访统计验收表-任务设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zftjysbRwszService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "走访统计验收表-任务设置-通过id查询")
	@ApiOperation(value="走访统计验收表-任务设置-通过id查询", notes="走访统计验收表-任务设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZftjysbRwsz zftjysbRwsz = zftjysbRwszService.getById(id);
		return Result.ok(zftjysbRwsz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zftjysbRwsz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ZftjysbRwsz zftjysbRwsz) {
      return super.exportXls(request, zftjysbRwsz, ZftjysbRwsz.class, "走访统计验收表-任务设置");
  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(@RequestBody JSONObject jsonObject,  HttpServletRequest request, HttpServletResponse response) {
	  String filePaths = jsonObject.getString("filePaths");
	  if (StringUtils.isEmpty(filePaths)) {
		  return Result.error("请先上传文件！");
	  }
	  String[] filePathList = filePaths.split(",");
	  JSONObject obj = new JSONObject();
	  for (String filePath : filePathList) {
		  String baseFilePath = uploadpath + File.separator + filePath;
		  File file = new File(baseFilePath);
		  ImportParams params = new ImportParams();
		  params.setTitleRows(1);
		  params.setHeadRows(1);
		  params.setNeedSave(true);

		  InputStream fis = null;
		  Workbook workbook = null;
		  HSSFWorkbook newBook = null;
		  try {
			  List<ZftjysbRwsz> rwszList = ExcelImportUtil.importExcel(file, ZftjysbRwsz.class, params);
			  List<String> ids = new ArrayList<String>();
			  for (ZftjysbRwsz zftjysbRwsz : rwszList) {
				  QueryWrapper<ZftjysbRwsz> queryWrapper = new QueryWrapper<>();
				  queryWrapper.eq("zzbz", zftjysbRwsz.getZzbz());
				  queryWrapper.eq("xzc", zftjysbRwsz.getXzc());
				  List<ZftjysbRwsz> list = zftjysbRwszService.list(queryWrapper);
				  ids.addAll(list.stream().map(ZftjysbRwsz::getId).collect(Collectors.toList()));
			  }
			  if (!ids.isEmpty()) {
			  	zftjysbRwszService.removeByIds(ids);
			  }
			  zftjysbRwszService.saveBatch(rwszList);
		  } catch (Exception e) {
			  log.error(e.getMessage(), e);
			  return Result.error("文件导入失败:" + e.getMessage());
		  } finally {
			  if (fis != null) {
				  try {
					  fis.close();
				  } catch (Throwable ignored) {
				  }
			  }
		  }
	  }
	  return Result.ok("文件导入成功！");
  }

	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "走访统计验收表任务设置导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, ZftjysbRwszImport.class);
		 ExportParams exportParams = new ExportParams("走访统计验收表任务设置导入模板", "模板信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<ZftjysbRwszImport>());
		 return mv;
	 }

}
