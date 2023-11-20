package org.cmms.common.system.base.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.exception.JeecgBootException;
import org.cmms.common.system.base.entity.AlreadyApprovalYearAuditTable;
import org.cmms.common.system.base.entity.ELoanTable;
import org.cmms.common.system.base.entity.NotApprovalYearAuditTable;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.BrowserUtil;
import org.cmms.common.util.ExcelUtils;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: Controller基类
 * @Author: dangzhenghui@163.com
 * @Date: 2019-4-21 8:13
 * @Version: 1.0
 */
@Slf4j
public class JeecgController<T, S extends IService<T>> {

	@Autowired
	protected S service;
	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	protected HttpSession session;
	@Autowired
	protected Environment environment;

	@Value(value = "${common.path.upload}")
	protected String uploadpath;

	protected ModelAndView exportTemplateXls(Class clazz, String name) {
		// AutoPoi 导出Excel
		ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		// 导出文件名称
		modelAndView.addObject(NormalExcelConstants.FILE_NAME, name);
		modelAndView.addObject(NormalExcelConstants.CLASS, clazz);
		ExportParams exportParams = new ExportParams(name, "模板信息");
		modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		return modelAndView;
	}
	/**
	 * 导出excel
	 *
	 * @param request
	 */
	protected ModelAndView exportXls(HttpServletRequest request, T object, Class<T> clazz, String title) {
		return this.exportXls(request, object, clazz, title, null);
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 */
	protected ModelAndView exportXls(HttpServletRequest request, T object, Class<T> clazz, String title, String exportFields) {
		// Step.1 组装查询条件
		QueryWrapper<T> queryWrapper = QueryGenerator.initQueryWrapper(object, request.getParameterMap());
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		String selections = request.getParameter("selections");
		String rowKey = request.getParameter("rowKey");

		//20211201 过滤选中数据
		//20211201 BY LIUXIANGQUN 过滤选择的数据改为执行接sql  in  查询
		if (oConvertUtils.isNotEmpty(selections)) {
			List<String> selectionList = Arrays.asList(selections.split(","));
			if(oConvertUtils.isNotEmpty(rowKey)){
				queryWrapper.in(rowKey,selectionList);
			}else{
				queryWrapper.in("ID",selectionList);
			}
		}

		// Step.2 获取导出数据
		//List<T> pageList = service.list(queryWrapper);
		//List<T> exportList = null;
		List<T> exportList = service.list(queryWrapper);

		//20211201 BY LIUXIANGQUN 过滤选择的数据改为执行接sql  in  查询
		// 过滤选中数据
		/*if (oConvertUtils.isNotEmpty(selections)) {
			List<String> selectionList = Arrays.asList(selections.split(","));
			exportList = pageList.stream().filter(item -> selectionList.contains(getId(item))).collect(Collectors.toList());
		} else {
			exportList = pageList;
		}*/

		// Step.3 AutoPoi 导出Excel
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
		mv.addObject(NormalExcelConstants.CLASS, clazz);
		mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
		mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		if (StringUtils.isNotEmpty(exportFields)) {
			mv.addObject(NormalExcelConstants.EXPORT_FIELDS, exportFields);
		}
		return mv;
	}


	/**
	 * 获取对象ID
	 *
	 * @return
	 */
	private String getId(T item) {
		try {
			return PropertyUtils.getProperty(item, "id").toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 通过excel导入数据
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	protected Result<?> importExcel(HttpServletRequest request, HttpServletResponse response, Class<T> clazz) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<T> list = ExcelImportUtil.importExcel(file.getInputStream(), clazz, params);
				//update-begin-author:taoyan date:20190528 for:批量插入数据
				long start = System.currentTimeMillis();
				service.saveBatch(list);
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

	public Result<?> importExcelByTemplate(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response, Class<T> clazz, IExcelVerifyHandler verifyHandler) {
		return importExcelByTemplate(jsonObject, request, response, clazz, clazz, verifyHandler);
	}

	public Result<?> importExcelByTemplate(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response, Class<T> clazz, Class<?> checkClazz, IExcelVerifyHandler verifyHandler) {
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
			if (verifyHandler != null) {
				params.setVerifyHanlder(verifyHandler);
			}
			FileOutputStream fos = null;
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
//				boolean checkResult = ExcelImportCheckUtil.check(fis, clazz, params);
//				if (!checkResult) {
//					return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
//				}
				ExcelImportResult<T> importResult = ExcelImportUtil.importExcelVerify(file, clazz, checkClazz, params);
				List<T> list = importResult.getList();
				service.saveBatch(list);
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
				IoUtil.close(fis);
				IoUtil.close(fos);
			}
		}
		return Result.ok("文件导入失败！");
	}

	/**
	 * 未审批年审表导出
	 *
	 * @param request
	 * @param response
	 * @param fileName 文件名称（不需要扩展名，默认为xls格式）
	 * @param townName 乡（镇）名称
	 * @param dataList 数据
	 * @throws IOException
	 */
	protected void notApprovalYearAuditTableExport(HttpServletRequest request,
												   HttpServletResponse response,
												   String fileName,
												   String townName,
												   List<List<String>> dataList) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();

		int couont = 50000;
		int n = dataList.size() / 50000 ;
		for (int i = 0; i < n + 1; i++) {
			HSSFSheet sheet = workbook.createSheet("年审表"+i);
			NotApprovalYearAuditTable notApprovalYearAuditTable = new NotApprovalYearAuditTable(workbook, sheet);
			notApprovalYearAuditTable.setTableName("湖南浏阳农村商业银行农户信用等级评级授信表");
			notApprovalYearAuditTable.setTownName(StringUtils.join("乡（镇）名称：", townName));
			notApprovalYearAuditTable.setTableHeader();

			if (i == n){
				notApprovalYearAuditTable.setTableData(dataList.subList(couont*i,dataList.size()));
			}else {

				notApprovalYearAuditTable.setTableData(dataList.subList(couont*i,(i+1)*couont));
			}
			notApprovalYearAuditTable.setTableFooter();
		}

		this.workbookWrite(request, response, fileName, workbook);
	}

	protected void workbookWrite(HttpServletRequest request, HttpServletResponse response, String fileName, HSSFWorkbook workbook) throws IOException {
		ExcelUtils.Type type = ExcelUtils.Type.XLS;
		this.setHeader(request, response, String.format("%s_%d%s", fileName, System.currentTimeMillis(), type.getExpandedName()));
		this.setExcelContentType(response, type);
		workbook.write(response.getOutputStream());
	}

	protected void setHeader(HttpServletRequest request, HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
		if ("IE".equals(BrowserUtil.getBrowser(request))) {
			fileName = new String(java.net.URLEncoder.encode(fileName, "UTF-8"));
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		}else {
			fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		}
	}

	protected void setExcelContentType(HttpServletResponse response, ExcelUtils.Type type) {
		switch (type) {
			case XLS:
				response.setContentType("application/vnd.ms-excel;charset=UTF-8");
				break;
			case XLSX:
				response.setContentType(
						"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
				break;
			default:
				break;
		}
	}

	/**
	 * 已审批年审表导出
	 *
	 * @param request
	 * @param response
	 * @param fileName 文件名称（不需要扩展名，默认为xls格式）
	 * @param dataList 数据
	 * @throws IOException
	 */
	protected void alreadyApprovalYearAuditTableExport(HttpServletRequest request,
													   HttpServletResponse response,
													   String fileName,
													   List<List<String>> dataList) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();

		int couont = 50000;
		int n = dataList.size() / 50000 ;
		log.info("导出年审表有|{}|条数据",dataList.size());
		log.info("n的值|{}|",n);

		for (int i = 0; i < n + 1; i++) {
			HSSFSheet sheet = workbook.createSheet("年审表"+i);

			AlreadyApprovalYearAuditTable alreadyApprovalYearAuditTable = new AlreadyApprovalYearAuditTable(workbook, sheet);
			alreadyApprovalYearAuditTable.setTableName("湖南浏阳农村商业银行《农户贷款证》年审情况表");
			alreadyApprovalYearAuditTable.setTableHeader();
			alreadyApprovalYearAuditTable.setTableData(dataList);

			if (i == n){
				alreadyApprovalYearAuditTable.setTableData(dataList.subList(couont*i,dataList.size()));
			}else {

				alreadyApprovalYearAuditTable.setTableData(dataList.subList(couont*i,(i+1)*couont));
			}
			alreadyApprovalYearAuditTable.setTableFooter();
		}

		this.workbookWrite(request, response, fileName, workbook);
	}

	protected void eLaonTableExport(HttpServletRequest request,
									HttpServletResponse response,
									String fileName,
									List<List<String>> dataList) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("惠农快贷");

		ELoanTable alreadyApprovalYearAuditTable = new ELoanTable(workbook, sheet);
		//alreadyApprovalYearAuditTable.setTableName("湘农e贷名单");
		alreadyApprovalYearAuditTable.setTableHeader();
		alreadyApprovalYearAuditTable.setTableData(dataList);

		this.workbookWrite(request, response, fileName, workbook);
	}

	protected String getUsername(){
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		return sysUser.getUsername();
	}

	protected LoginUser getLoginUser(){
		return (LoginUser) SecurityUtils.getSubject().getPrincipal();
	}

	protected String getRealname(){
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		return sysUser.getRealname();
	}

	protected String getWorkNo(){
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		return sysUser.getWorkNo();
	}

	protected Object getSysUserBySession(){
		return session.getAttribute("sys_user");
	}
	protected Object getQydmBySession(){
		return session.getAttribute("qydm");
	}
	protected String getRedisQydm(){
		String redisQydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
		if (StringUtils.isEmpty(redisQydm)) {
			throw new JeecgBootException("用户登录失效，请重新登录！");
		}
		return redisQydm;
	}

	protected String getRedisRoleCode(){
		return (String) redisUtil.get(CommonConstant.PREFIX_USER_ROLE_CODE + getLoginUser().getUsername());
	}
	protected String getRedisRoleName(){
		return (String) redisUtil.get(CommonConstant.PREFIX_USER_ROLE_NAME + getLoginUser().getUsername());
	}
	protected String getRedisUserJgdm(){
		return (String) redisUtil.get(CommonConstant.PREFIX_USER_JGDM + getLoginUser().getUsername());
	}


	protected ModelAndView exportXls(HttpServletRequest request, T object, Class<T> clazz, String title, String exportFields,
									 QueryWrapper<T> queryWrapper) {
		// Step.1 组装查询条件
		//QueryWrapper<T> queryWrapper = QueryGenerator.initQueryWrapper(object, request.getParameterMap());
		if (queryWrapper == null){
			queryWrapper = QueryGenerator.initQueryWrapper(object, request.getParameterMap());
		}
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

		List<T> exportList = service.list(queryWrapper);

		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
		mv.addObject(NormalExcelConstants.CLASS, clazz);
		mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
		mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		if (StringUtils.isNotEmpty(exportFields)) {
			mv.addObject(NormalExcelConstants.EXPORT_FIELDS, exportFields);
		}
		return mv;
	}
}
