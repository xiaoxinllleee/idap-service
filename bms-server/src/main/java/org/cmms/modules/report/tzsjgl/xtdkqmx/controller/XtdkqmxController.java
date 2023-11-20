package org.cmms.modules.report.tzsjgl.xtdkqmx.controller;

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
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.report.cwbb.qkmb.entity.Qkmb;
import org.cmms.modules.report.cwbb.ywzkb.entity.Ywzkb;
import org.cmms.modules.report.cwbb.ywzkb.entity.YwzkbVo;
import org.cmms.modules.report.tzsjgl.xtdkqmx.entity.Xtdkqmx;
import org.cmms.modules.report.tzsjgl.xtdkqmx.entity.XtdkqmxVo;
import org.cmms.modules.report.tzsjgl.xtdkqmx.service.IXtdkqmxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.tzsjgl.xtdkqmx.verify.DkqmxImportVerify;
import org.cmms.modules.system.service.ISysDictService;
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
 * @Description: 湘潭贷款全明细
 * @Author: jeecg-boot
 * @Date:   2022-05-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="湘潭贷款全明细")
@RestController
@RequestMapping("/xtdkqmx/xtdkqmx")
public class XtdkqmxController extends JeecgController<Xtdkqmx, IXtdkqmxService> {
	@Autowired
	private IXtdkqmxService xtdkqmxService;
	 @Autowired
	 private DkqmxImportVerify dkqmxImportVerify;
	 @Autowired
	 private ISysDictService sysDictService;
	/**
	 * 分页列表查询
	 *
	 * @param xtdkqmx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "湘潭贷款全明细-分页列表查询")
	@ApiOperation(value="湘潭贷款全明细-分页列表查询", notes="湘潭贷款全明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Xtdkqmx xtdkqmx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Xtdkqmx> queryWrapper = QueryGenerator.initQueryWrapper(xtdkqmx, req.getParameterMap());
		Page<Xtdkqmx> page = new Page<Xtdkqmx>(pageNo, pageSize);
		IPage<Xtdkqmx> pageList = xtdkqmxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param xtdkqmx
	 * @return
	 */
	@AutoLog(value = "湘潭贷款全明细-添加")
	@ApiOperation(value="湘潭贷款全明细-添加", notes="湘潭贷款全明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Xtdkqmx xtdkqmx) {
		xtdkqmxService.save(xtdkqmx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param xtdkqmx
	 * @return
	 */
	@AutoLog(value = "湘潭贷款全明细-编辑")
	@ApiOperation(value="湘潭贷款全明细-编辑", notes="湘潭贷款全明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Xtdkqmx xtdkqmx) {
		xtdkqmxService.updateById(xtdkqmx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "湘潭贷款全明细-通过id删除")
	@ApiOperation(value="湘潭贷款全明细-通过id删除", notes="湘潭贷款全明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		xtdkqmxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "湘潭贷款全明细-批量删除")
	@ApiOperation(value="湘潭贷款全明细-批量删除", notes="湘潭贷款全明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@Param("sjrq") String sjrq) {
		QueryWrapper<Xtdkqmx> queryWrapper = new QueryWrapper<>();
		DateTime parse = DateUtil.parse(sjrq);
		queryWrapper.eq("sjrq",parse);
		xtdkqmxService.remove(queryWrapper);
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "湘潭贷款全明细-通过id查询")
	@ApiOperation(value="湘潭贷款全明细-通过id查询", notes="湘潭贷款全明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Xtdkqmx xtdkqmx = xtdkqmxService.getById(id);
		return Result.ok(xtdkqmx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param xtdkqmx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Xtdkqmx xtdkqmx) {
      return super.exportXls(request, xtdkqmx, Xtdkqmx.class, "湘潭贷款全明细");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "贷款全明细导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, XtdkqmxVo.class);
		 ExportParams exportParams = new ExportParams("贷款全明细导入模板", "模板信息");
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
 /* @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
	  String sjrq = request.getParameter("sjrq");
	  DateTime parse = DateUtil.parse(sjrq);
	  MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	  Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
	  for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
		  MultipartFile file = entity.getValue();// 获取上传文件对象
		  ImportParams params = new ImportParams();
		  params.setTitleRows(0);
		  params.setHeadRows(1);
		  params.setLastOfInvalidRow(0);
		  params.setNeedSave(true);
		  try {
			  List<Xtdkqmx> list = ExcelImportUtil.importExcel(file.getInputStream(), Xtdkqmx.class, params);
			  List<Xtdkqmx> xtdkqmxList=new ArrayList<>();
			  for(Xtdkqmx xtdkqmx:list){
				  xtdkqmx.setSjrq(parse);
				  xtdkqmxList.add(xtdkqmx);
			  }
			  //update-begin-author:taoyan date:20190528 for:批量插入数据
			  long start = System.currentTimeMillis();
			  service.saveBatch(xtdkqmxList);
			  //400条 saveBatch消耗时间1592毫秒  循环插入消耗时间1947毫秒
			  //1200条  saveBatch消耗时间3687毫秒 循环插入消耗时间5212毫秒
			  log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
			  //update-end-author:taoyan date:20190528 for:批量插入数据
			  return Result.ok("文件导入成功！数据行数：" + list.size());
		  } catch (Exception e) {
			  log.error(e.getMessage(), e);
			  return Result.error("文件导入失败:" + e.getMessage());
		  } finally {
			  try {
				  file.getInputStream().close();
			  } catch (IOException e) {
				  e.printStackTrace();
			  }
		  }
	  }
	  return Result.error("文件导入失败！");
  }
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
		  if (dkqmxImportVerify != null) {
			  params.setVerifyHanlder(dkqmxImportVerify);
		  }
		  FileOutputStream fos = null;
		  try {
			  ExcelImportResult<Xtdkqmx> importResult = ExcelImportUtil.importExcelVerify(file, Xtdkqmx.class,XtdkqmxVo.class, params);
			  List<Xtdkqmx> list = importResult.getList();
			  List<Xtdkqmx> qkmbList = new ArrayList<>();
			  for (Xtdkqmx ywzkb : list) {
				  ywzkb.setSjrq(parse);
				  if (StringUtils.isNotBlank(ywzkb.getJgdm())) {
					  ywzkb.setJgdm(ywzkb.getJgdm().trim());
				  }
				  if (StringUtils.isNotBlank(ywzkb.getDkzh())){
					  ywzkb.setDkzh(ywzkb.getDkzh().trim());
				  }
				  if (StringUtils.isNotBlank(ywzkb.getKhmz())){
					  ywzkb.setKhmz(ywzkb.getKhmz().trim());
				  }
				  if (StringUtils.isNotBlank(ywzkb.getZczjhm())){
					  ywzkb.setZczjhm(ywzkb.getZczjhm().trim());
				  }
				  if (StringUtils.isNotBlank(ywzkb.getWjfl())){
					  ywzkb.setWjfl(ywzkb.getWjfl().trim());
				  }
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
