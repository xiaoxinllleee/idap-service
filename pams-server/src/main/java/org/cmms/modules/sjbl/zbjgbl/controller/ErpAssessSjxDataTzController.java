package org.cmms.modules.sjbl.zbjgbl.controller;

import java.io.*;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khlc.jczbgl.entity.ErpBasZbk;
import org.cmms.modules.khlc.jczbgl.service.IErpBasZbkService;
import org.cmms.modules.khlc.zbljgl.entity.ErpBasSjxArea;
import org.cmms.modules.khlc.zbljgl.service.IErpBasSjxAreaService;
import org.cmms.modules.sjbl.zbjgbl.entity.*;
import org.cmms.modules.sjbl.zbjgbl.service.IErpAssessSjxDataTzService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 指标结果补录
 * @Author: jeecg-boot
 * @Date:   2023-03-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="指标结果补录")
@RestController
@RequestMapping("/zbjgbl/erpAssessSjxDataTz")
public class ErpAssessSjxDataTzController extends JeecgController<ErpAssessSjxDataTz, IErpAssessSjxDataTzService> {
	@Autowired
	private IErpAssessSjxDataTzService erpAssessSjxDataTzService;
	@Autowired
	private IErpBasSjxAreaService erpBasSjxAreaService;
	 @Autowired
	 private IErpBasZbkService erpBasZbkService;
	/**
	 * 分页列表查询
	 *
	 * @param erpAssessSjxDataTz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "指标结果补录-分页列表查询")
	@ApiOperation(value="指标结果补录-分页列表查询", notes="指标结果补录-分页列表查询")
	@GetMapping(value = "/listJG")
	public Result<?> queryPageListJG(ErpAssessSjxDataTz erpAssessSjxDataTz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ErpAssessSjxDataTz> queryWrapper = QueryGenerator.initQueryWrapper(erpAssessSjxDataTz, req.getParameterMap());
		queryWrapper.isNull("gwbz");
		Page<ErpAssessSjxDataTz> page = new Page<ErpAssessSjxDataTz>(pageNo, pageSize);
		IPage<ErpAssessSjxDataTz> pageList = erpAssessSjxDataTzService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 @GetMapping(value = "/zbidJg")
	 public Result<?> zbidJg(ErpBasSjxArea erpBasSjxArea,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									@RequestParam(name = "zbmc",required = false) String zbmc,
									HttpServletRequest req) {

		 QueryWrapper<ErpBasSjxArea> queryWrapper = QueryGenerator.initQueryWrapper(erpBasSjxArea, req.getParameterMap());
		 QueryWrapper<ErpBasZbk> zbk = new QueryWrapper<>();
		 if (StringUtils.isNotBlank(zbmc)){
			 zbk.like("zbmc",zbmc);
			 List<ErpBasZbk> list = erpBasZbkService.list(zbk);
			 List<String> list1 = new ArrayList<>();
			 if (CollUtil.isNotEmpty(list)){
				 for (ErpBasZbk erpBasZbk : list) {
					 list1.add(erpBasZbk.getZbid());
				 }
				 queryWrapper.in("zbid",list1);
			 }
		 }
		 queryWrapper.eq("zblx",1);//机构
		 queryWrapper.eq("sfqy",1);
		 queryWrapper.orderByDesc("zbid");
		 Page<ErpBasSjxArea> page = new Page<ErpBasSjxArea>(pageNo, pageSize);
		 IPage<ErpBasSjxArea> pageList = erpBasSjxAreaService.page(page, queryWrapper);
		 return Result.ok(pageList);
	 }
	 @GetMapping(value = "/zbidGw")
	 public Result<?> zbidGw(ErpBasSjxArea erpBasSjxArea,
							 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
							 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
							 @RequestParam(name = "zbmc",required = false) String zbmc,
							 HttpServletRequest req) {

		 QueryWrapper<ErpBasSjxArea> queryWrapper = QueryGenerator.initQueryWrapper(erpBasSjxArea, req.getParameterMap());
		 QueryWrapper<ErpBasZbk> zbk = new QueryWrapper<>();
		 if (StringUtils.isNotBlank(zbmc)){
			 zbk.like("zbmc",zbmc);
			 List<ErpBasZbk> list = erpBasZbkService.list(zbk);
			 List<String> list1 = new ArrayList<>();
			 if (CollUtil.isNotEmpty(list)){
				 for (ErpBasZbk erpBasZbk : list) {
					 list1.add(erpBasZbk.getZbid());
				 }
				 queryWrapper.in("zbid",list1);
			 }
		 }
		 queryWrapper.eq("zblx",3);//岗位
		 queryWrapper.eq("sfqy",1);
		 queryWrapper.orderByDesc("zbid");
		 Page<ErpBasSjxArea> page = new Page<ErpBasSjxArea>(pageNo, pageSize);
		 IPage<ErpBasSjxArea> pageList = erpBasSjxAreaService.page(page, queryWrapper);
		 return Result.ok(pageList);
	 }


	 @AutoLog(value = "指标结果补录-分页列表查询")
	 @ApiOperation(value="指标结果补录-分页列表查询", notes="指标结果补录-分页列表查询")
	 @GetMapping(value = "/listGW")
	 public Result<?> queryPageListGW(ErpAssessSjxDataTz erpAssessSjxDataTz,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		 QueryWrapper<ErpAssessSjxDataTz> queryWrapper = QueryGenerator.initQueryWrapper(erpAssessSjxDataTz, req.getParameterMap());
		 queryWrapper.isNotNull("gwbz");
		 Page<ErpAssessSjxDataTz> page = new Page<ErpAssessSjxDataTz>(pageNo, pageSize);
		 IPage<ErpAssessSjxDataTz> pageList = erpAssessSjxDataTzService.page(page, queryWrapper);
		 return Result.ok(pageList);
	 }

	/**
	 * 添加
	 *
	 * @param erpAssessSjxDataTz
	 * @return
	 */
	@AutoLog(value = "指标结果补录-添加")
	@ApiOperation(value="指标结果补录-添加", notes="指标结果补录-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ErpAssessSjxDataTz erpAssessSjxDataTz) {
		erpAssessSjxDataTzService.save(erpAssessSjxDataTz);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param erpAssessSjxDataTz
	 * @return
	 */
	@AutoLog(value = "指标结果补录-编辑")
	@ApiOperation(value="指标结果补录-编辑", notes="指标结果补录-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ErpAssessSjxDataTz erpAssessSjxDataTz) {
		erpAssessSjxDataTzService.updateById(erpAssessSjxDataTz);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "指标结果补录-通过id删除")
	@ApiOperation(value="指标结果补录-通过id删除", notes="指标结果补录-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		erpAssessSjxDataTzService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "指标结果补录-批量删除")
	@ApiOperation(value="指标结果补录-批量删除", notes="指标结果补录-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.erpAssessSjxDataTzService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "指标结果补录-通过id查询")
	@ApiOperation(value="指标结果补录-通过id查询", notes="指标结果补录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ErpAssessSjxDataTz erpAssessSjxDataTz = erpAssessSjxDataTzService.getById(id);
		return Result.ok(erpAssessSjxDataTz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param erpAssessSjxDataTz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ErpAssessSjxDataTz erpAssessSjxDataTz) {
      return super.exportXls(request, erpAssessSjxDataTz, ErpAssessSjxDataTz.class, "指标结果补录");
  }



	 /**
	  * 导出机构excel
	  *
	  * @param request
	  * @param erpAssessSjxDataTz
	  */
	 @RequestMapping(value = "/exportXlsJG")
	 public ModelAndView exportXlsJG(HttpServletRequest request, ErpAssessSjxDataTz erpAssessSjxDataTz) {
		 QueryWrapper<ErpAssessSjxDataTz> queryWrapper = QueryGenerator.initQueryWrapper(erpAssessSjxDataTz, request.getParameterMap());
		 queryWrapper.isNull("gwbz");
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String selections = request.getParameter("selections");
		 String rowKey = request.getParameter("rowKey");

		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 if(oConvertUtils.isNotEmpty(rowKey)){
				 queryWrapper.in(rowKey,selectionList);
			 }else{
				 queryWrapper.in("ID",selectionList);
			 }
		 }

		 List<ErpAssessSjxDataTz> list = service.list(queryWrapper);
		 List<ErpAssessSjxDataTzExportJGVO> exportListJg = new ArrayList<>();
		 for (ErpAssessSjxDataTz erpAssessSjxDataTz1 : list){
			 ErpAssessSjxDataTzExportJGVO exportJGVO = new ErpAssessSjxDataTzExportJGVO();
			 BeanUtils.copyProperties(erpAssessSjxDataTz1,exportJGVO);
			 exportListJg.add(exportJGVO);
		 }
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, "机构指标数据调整");
		 mv.addObject(NormalExcelConstants.CLASS, ErpAssessSjxDataTzExportJGVO.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("机构指标数据调整" + "报表", "导出人:" + sysUser.getRealname(), "机构指标数据调整"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportListJg);
		 return mv;
	 }


	 /**
	  * 导出岗位excel
	  *
	  * @param request
	  * @param erpAssessSjxDataTz
	  */
	 @RequestMapping(value = "/exportXlsGW")
	 public ModelAndView exportXlsGW(HttpServletRequest request, ErpAssessSjxDataTz erpAssessSjxDataTz) {
		 QueryWrapper<ErpAssessSjxDataTz> queryWrapper = QueryGenerator.initQueryWrapper(erpAssessSjxDataTz, request.getParameterMap());
		 queryWrapper.isNotNull("gwbz");
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String selections = request.getParameter("selections");
		 String rowKey = request.getParameter("rowKey");

		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 if(oConvertUtils.isNotEmpty(rowKey)){
				 queryWrapper.in(rowKey,selectionList);
			 }else{
				 queryWrapper.in("ID",selectionList);
			 }
		 }

		 List<ErpAssessSjxDataTz> list = service.list(queryWrapper);
		 List<ErpAssessSjxDataTzExportGWVO> exportListGw = new ArrayList<>();
		 for (ErpAssessSjxDataTz erpAssessSjxDataTz1 : list){
			 ErpAssessSjxDataTzExportGWVO exportGWVO = new ErpAssessSjxDataTzExportGWVO();
			 BeanUtils.copyProperties(erpAssessSjxDataTz1,exportGWVO);
			 exportListGw.add(exportGWVO);
		 }
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, "岗位指标数据调整");
		 mv.addObject(NormalExcelConstants.CLASS, ErpAssessSjxDataTzExportGWVO.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("岗位指标数据调整" + "报表", "导出人:" + sysUser.getRealname(), "岗位指标数据调整"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportListGw);
		 return mv;
	 }



	 /**
	  * 导出机构模板
	  *
	  */
	 @RequestMapping(value = "/exportTemplateXlsJG")
	 public ModelAndView exportTemplateXlsJG(HttpServletRequest request, HttpServletResponse response){
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME,"机构指标数据调整导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS,ErpAssessSjxDataTzImportJGVO.class);
		 ExportParams exportParams = new ExportParams("机构指标数据调整导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
	 }


	 /**
	  * 导出岗位模板
	  *
	  */
	 @RequestMapping(value = "/exportTemplateXlsGW")
	 public ModelAndView exportTemplateXlsGW(HttpServletRequest request, HttpServletResponse response){
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME,"岗位指标数据调整导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS,ErpAssessSjxDataTzImportGWVO.class);
		 ExportParams exportParams = new ExportParams("岗位指标数据调整导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
	 }


  /**
   * 通过excel导入机构数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcelJG", method = RequestMethod.POST)
  public Result<?> importExcelJG(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
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
		  params.setNeedSave(false);

		  FileOutputStream fos = null;
		  FileInputStream fis = null;
		  try {
			  fis = new FileInputStream(file);
			  ExcelImportResult<ErpAssessSjxDataTzImportJGVO> importResult = ExcelImportUtil.importExcelVerify(file, ErpAssessSjxDataTzImportJGVO.class, params);
			  List<ErpAssessSjxDataTzImportJGVO> list = importResult.getList();
			  List<ErpAssessSjxDataTz> list1 = new ArrayList<>();
			  for (ErpAssessSjxDataTzImportJGVO erpAssessSjxDataTzJgVO : list) {
				  ErpAssessSjxDataTz erpAssessSjxDataTz = new ErpAssessSjxDataTz();
				  BeanUtils.copyProperties(erpAssessSjxDataTzJgVO, erpAssessSjxDataTz);
				  QueryWrapper<ErpAssessSjxDataTz> queryWrapper = new QueryWrapper();
				  queryWrapper.eq("scheme_id", erpAssessSjxDataTz.getSchemeId());
				  queryWrapper.eq("zzbz", erpAssessSjxDataTz.getZzbz());
				  queryWrapper.eq("zbid", erpAssessSjxDataTz.getZbid());
				  queryWrapper.isNull("gwbz");
				  service.remove(queryWrapper);
				  list1.add(erpAssessSjxDataTz);
			  }
			  service.saveBatch(list1);
			  obj.put("filePath", filePath);
			  fos = new FileOutputStream(baseFilePath);
			  importResult.getWorkbook().write(fos);
			  fos.flush();
			  fos.close();
			  return Result.ok("文件导入完成！成功导入数据行数:" + list.size(), obj);
		  } catch (Exception e) {
			  log.error(e.getMessage(), e);
			  return Result.error("文件导入失败:" + e.getMessage());
		  } finally {
			  IoUtil.close(fis);
			  IoUtil.close(fos);
		  }
	  }
	  return Result.ok("文件导入失败！");
  }



	 /**
	  * 通过excel导入岗位数据
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/importExcelGW", method = RequestMethod.POST)
	 public Result<?> importExcelGW(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
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
			 params.setNeedSave(false);

			 FileOutputStream fos = null;
			 FileInputStream fis = null;
			 try {
				 fis = new FileInputStream(file);
				 ExcelImportResult<ErpAssessSjxDataTzImportGWVO> importResult = ExcelImportUtil.importExcelVerify(file, ErpAssessSjxDataTzImportGWVO.class, params);
				 List<ErpAssessSjxDataTzImportGWVO> list = importResult.getList();
				 List<ErpAssessSjxDataTz> list1 = new ArrayList<>();
				 for (ErpAssessSjxDataTzImportGWVO erpAssessSjxDataTzGwVO : list) {
					 ErpAssessSjxDataTz erpAssessSjxDataTz = new ErpAssessSjxDataTz();
					 BeanUtils.copyProperties(erpAssessSjxDataTzGwVO, erpAssessSjxDataTz);
					 QueryWrapper<ErpAssessSjxDataTz> queryWrapper = new QueryWrapper();
					 queryWrapper.eq("scheme_id", erpAssessSjxDataTz.getSchemeId());
					 queryWrapper.eq("zzbz", erpAssessSjxDataTz.getZzbz());
					 queryWrapper.eq("zbid", erpAssessSjxDataTz.getZbid());
					 queryWrapper.eq("gwbz", erpAssessSjxDataTz.getGwbz());
					 service.remove(queryWrapper);
					 list1.add(erpAssessSjxDataTz);
				 }
				 service.saveBatch(list1);
				 obj.put("filePath", filePath);
				 fos = new FileOutputStream(baseFilePath);
				 importResult.getWorkbook().write(fos);
				 fos.flush();
				 fos.close();
				 return Result.ok("文件导入完成！成功导入数据行数:" + list.size(), obj);
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败:" + e.getMessage());
			 } finally {
				 IoUtil.close(fis);
				 IoUtil.close(fos);
			 }
		 }
		 return Result.ok("文件导入失败！");
	 }

  }
