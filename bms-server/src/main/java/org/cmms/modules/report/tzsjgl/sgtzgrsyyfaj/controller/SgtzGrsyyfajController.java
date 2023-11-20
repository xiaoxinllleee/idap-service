package org.cmms.modules.report.tzsjgl.sgtzgrsyyfaj.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.report.tzsjgl.gtgsh.entity.SgtzGtgsh;
import org.cmms.modules.report.tzsjgl.sgtzgrsyyfaj.entity.SgtzGrsyyfaj;
import org.cmms.modules.report.tzsjgl.sgtzgrsyyfaj.service.ISgtzGrsyyfajService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.tzsjgl.xtdkqmx.entity.XtdkqmxVo;
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
 * @Description: 个人商业用房按揭
 * @Author: jeecg-boot
 * @Date:   2023-03-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="个人商业用房按揭")
@RestController
@RequestMapping("/sgtzgrsyyfaj/sgtzGrsyyfaj")
public class SgtzGrsyyfajController extends JeecgController<SgtzGrsyyfaj, ISgtzGrsyyfajService> {
	@Autowired
	private ISgtzGrsyyfajService sgtzGrsyyfajService;

	/**
	 * 分页列表查询
	 *
	 * @param sgtzGrsyyfaj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "个人商业用房按揭-分页列表查询")
	@ApiOperation(value="个人商业用房按揭-分页列表查询", notes="个人商业用房按揭-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgtzGrsyyfaj sgtzGrsyyfaj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgtzGrsyyfaj> queryWrapper = QueryGenerator.initQueryWrapper(sgtzGrsyyfaj, req.getParameterMap());
		Page<SgtzGrsyyfaj> page = new Page<SgtzGrsyyfaj>(pageNo, pageSize);
		IPage<SgtzGrsyyfaj> pageList = sgtzGrsyyfajService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgtzGrsyyfaj
	 * @return
	 */
	@AutoLog(value = "个人商业用房按揭-添加")
	@ApiOperation(value="个人商业用房按揭-添加", notes="个人商业用房按揭-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgtzGrsyyfaj sgtzGrsyyfaj) {
		sgtzGrsyyfajService.save(sgtzGrsyyfaj);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgtzGrsyyfaj
	 * @return
	 */
	@AutoLog(value = "个人商业用房按揭-编辑")
	@ApiOperation(value="个人商业用房按揭-编辑", notes="个人商业用房按揭-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgtzGrsyyfaj sgtzGrsyyfaj) {
		QueryWrapper<SgtzGrsyyfaj> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("sjrq",sgtzGrsyyfaj.getSjrq());
		queryWrapper.eq("dkzh",sgtzGrsyyfaj.getDkzh());
		sgtzGrsyyfajService.update(sgtzGrsyyfaj,queryWrapper);
		return Result.ok("编辑成功!");
	}
	 /**
	  * 通过id删除
	  *
	  * @param
	  * @return
	  */
	 @AutoLog(value = "个人商业用房按揭-通过id删除")
	 @ApiOperation(value="个人商业用房按揭-通过id删除", notes="个人商业用房按揭-通过id删除")
	 @DeleteMapping(value = "/delete")
	 public Result<?> delete(@Param("sjrq") String sjrq, @Param("dkzh") String dkzh) {
		 QueryWrapper<SgtzGrsyyfaj> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("sjrq",DateUtil.parse(sjrq));
		 queryWrapper.eq("dkzh",dkzh);
		 sgtzGrsyyfajService.remove(queryWrapper);
		 return Result.ok("删除成功!");
	 }
	 /**
	  * 根据日期批量删除
	  * @param sjrq
	  * @return
	  */
	 @AutoLog(value = "个体工商户-批量删除")
	 @ApiOperation(value="个体工商户-批量删除", notes="个体工商户-批量删除")
	 @DeleteMapping(value = "/deleteByBatch")
	 public Result<?> deleteByBatch(@Param("sjrq") String sjrq) {
		 QueryWrapper<SgtzGrsyyfaj> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("sjrq",DateUtil.parse(sjrq));
		 sgtzGrsyyfajService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }


	 /**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "个人商业用房按揭-批量删除")
	@ApiOperation(value="个人商业用房按揭-批量删除", notes="个人商业用房按揭-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgtzGrsyyfajService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "个人商业用房按揭-通过id查询")
	@ApiOperation(value="个人商业用房按揭-通过id查询", notes="个人商业用房按揭-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgtzGrsyyfaj sgtzGrsyyfaj = sgtzGrsyyfajService.getById(id);
		return Result.ok(sgtzGrsyyfaj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgtzGrsyyfaj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgtzGrsyyfaj sgtzGrsyyfaj) {
      return super.exportXls(request, sgtzGrsyyfaj, SgtzGrsyyfaj.class, "个人商业用房按揭");
  }
	 /**
	  * 导入模板
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "个体工商户导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, XtdkqmxVo.class);
		 ExportParams exportParams = new ExportParams("个人商业用房按揭导入模板", "模板信息");
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
	  System.out.println(DateUtil.parse(sjrq) + "----sjrq----");
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
		 /* if (sgtzglGtgshImportVerify != null) {
			  params.setVerifyHanlder(sgtzglGtgshImportVerify);
		  }*/
		  FileOutputStream fos = null;
		  try {
			  ExcelImportResult<SgtzGrsyyfaj> importResult = ExcelImportUtil.importExcelVerify(file, SgtzGrsyyfaj.class,XtdkqmxVo.class, params);
			  List<SgtzGrsyyfaj> list = importResult.getList();
			  List<SgtzGrsyyfaj> qkmbList = new ArrayList<>();
			  for (SgtzGrsyyfaj qkmb : list) {
				  qkmb.setSjrq(DateUtil.parse(sjrq));
				  qkmbList.add(qkmb);
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
