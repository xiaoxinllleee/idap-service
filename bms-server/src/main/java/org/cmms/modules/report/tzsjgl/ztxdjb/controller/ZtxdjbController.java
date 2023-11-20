package org.cmms.modules.report.tzsjgl.ztxdjb.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.tzsjgl.xttyrrrc.entity.Xttyrrrc;
import org.cmms.modules.report.tzsjgl.ztxdjb.entity.Ztxdjb;
import org.cmms.modules.report.tzsjgl.ztxdjb.service.IZtxdjbService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
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
 * @Description: 转贴现登记簿
 * @Author: jeecg-boot
 * @Date:   2023-05-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="转贴现登记簿")
@RestController
@RequestMapping("/ztxdjb/ztxdjb")
public class ZtxdjbController extends JeecgController<Ztxdjb, IZtxdjbService> {
	@Autowired
	private IZtxdjbService ztxdjbService;

	/**
	 * 分页列表查询
	 *
	 * @param ztxdjb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "转贴现登记簿-分页列表查询")
	@ApiOperation(value="转贴现登记簿-分页列表查询", notes="转贴现登记簿-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ztxdjb ztxdjb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Ztxdjb> queryWrapper = QueryGenerator.initQueryWrapper(ztxdjb, req.getParameterMap());
		Page<Ztxdjb> page = new Page<Ztxdjb>(pageNo, pageSize);
		IPage<Ztxdjb> pageList = ztxdjbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param ztxdjb
	 * @return
	 */
	@AutoLog(value = "转贴现登记簿-添加")
	@ApiOperation(value="转贴现登记簿-添加", notes="转贴现登记簿-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ztxdjb ztxdjb) {
		ztxdjbService.save(ztxdjb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param ztxdjb
	 * @return
	 */
	@AutoLog(value = "转贴现登记簿-编辑")
	@ApiOperation(value="转贴现登记簿-编辑", notes="转贴现登记簿-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ztxdjb ztxdjb) {
		ztxdjbService.updateById(ztxdjb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "转贴现登记簿-通过id删除")
	@ApiOperation(value="转贴现登记簿-通过id删除", notes="转贴现登记簿-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ztxdjbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "转贴现登记簿-批量删除")
	@ApiOperation(value="转贴现登记簿-批量删除", notes="转贴现登记簿-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ztxdjbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	 /**
	  * 批量删除
	  *
	  * @param
	  * @return
	  */
	 @AutoLog(value = "转贴现登记簿-批量删除")
	 @ApiOperation(value = "转贴现登记簿-批量删除", notes = "转贴现登记簿-批量删除")
	 @DeleteMapping(value = "/deleteBatchPl")
	 public Result<?> deleteBatchPl(@Param("sjrq") String sjrq) {
		 QueryWrapper<Ztxdjb> queryWrapper = new QueryWrapper<>();
		 DateTime parse = DateUtil.parse(sjrq);
		 queryWrapper.eq("sjrq",parse);
		 ztxdjbService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "转贴现登记簿-通过id查询")
	@ApiOperation(value="转贴现登记簿-通过id查询", notes="转贴现登记簿-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ztxdjb ztxdjb = ztxdjbService.getById(id);
		return Result.ok(ztxdjb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ztxdjb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ztxdjb ztxdjb) {
      return super.exportXls(request, ztxdjb, Ztxdjb.class, "转贴现登记簿");
  }
	 /**
	  * 导出模板
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "转贴现登记簿导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, Ztxdjb.class);
		 ExportParams exportParams = new ExportParams("转贴现登记簿导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
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
	  String sjrq = request.getParameter("sjrq");
	  System.out.println(sjrq + "----sjrq----");
	  Date parse = DateUtil.parse(sjrq);
	  String filePaths = jsonObject.getString("filePaths");
	  if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
		  return Result.error("请先上传文件！");
	  }
	  String[] filePathList = filePaths.split(",");
	  JSONObject obj = new JSONObject();
	  for (String filePath : filePathList) {
		  String baseFilePath = uploadpath + File.separator + filePath;
//          MultipartFile file = entity.getValue();// 获取上传文件对象
		  File file = new File(baseFilePath);
		  ImportParams params = new ImportParams();
		  params.setTitleRows(1);
		  params.setHeadRows(1);
		  params.setNeedSave(false);

		  FileOutputStream fos = null;
		  try {
			  ExcelImportResult<Ztxdjb> importResult = ExcelImportUtil.importExcelVerify(file, Ztxdjb.class, params);
			  List<Ztxdjb> list = importResult.getList();
			  List<Ztxdjb> qkmbList = new ArrayList<>();
			  for (Ztxdjb ywzkb : list) {
				  ywzkb.setSjrq(parse);
				  qkmbList.add(ywzkb);
			  }
			  service.saveBatch(qkmbList);
			  obj.put("filePath", filePath);
			  fos = new FileOutputStream(baseFilePath);
			  importResult.getWorkbook().write(fos);
			  fos.flush();
			  fos.close();
			  return Result.ok("文件导入完成！成功导入数据行数:" + list.size(), obj);
		  } catch (Exception e) {
			  log.error(e.getMessage(),e);
			  return Result.error("文件导入失败:"+e.getMessage());
		  } finally {
			  IoUtil.close(fos);
		  }
	  }
	  return Result.ok("文件导入失败！");
  }

}
