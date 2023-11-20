package org.cmms.modules.khxxgl.wbsjgl.bwtzgl.controller;

import java.io.*;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khxxgl.wbsjgl.bwtzgl.entity.LoanBwdkSjmx;
import org.cmms.modules.khxxgl.wbsjgl.bwtzgl.service.ILoanBwdkSjmxService;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 表外台账管理
 * @Author: jeecg-boot
 * @Date:   2020-09-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags="表外台账管理 ")
@RestController
@RequestMapping("/LoanBwdkSjmx/loanBwdkSjmx")
public class LoanBwdkSjmxController extends JeecgController<LoanBwdkSjmx, ILoanBwdkSjmxService> {
	@Autowired
	private ILoanBwdkSjmxService loanBwdkSjmxService;
	 @Value(value = "${common.path.upload}")
	 private String uploadpath;
	/**
	 * 分页列表查询
	 *
	 * @param loanBwdkSjmx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "表外台账管理 -分页列表查询")
	@ApiOperation(value="表外台账管理 -分页列表查询", notes="表外台账管理 -分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(LoanBwdkSjmx loanBwdkSjmx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<LoanBwdkSjmx> queryWrapper = QueryGenerator.initQueryWrapper(loanBwdkSjmx, req.getParameterMap());
		Page<LoanBwdkSjmx> page = new Page<LoanBwdkSjmx>(pageNo, pageSize);
		IPage<LoanBwdkSjmx> pageList = loanBwdkSjmxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param loanBwdkSjmx
	 * @return
	 */
	@AutoLog(value = "表外台账管理 -添加")
	@ApiOperation(value="表外台账管理 -添加", notes="表外台账管理 -添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody LoanBwdkSjmx loanBwdkSjmx) {
			Integer xh = loanBwdkSjmxService.queryXh();
			if (xh == null)
				xh = 1;
			loanBwdkSjmx.setXh(xh+1);
			loanBwdkSjmxService.save(loanBwdkSjmx);
			return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param loanBwdkSjmx
	 * @return
	 */
	@AutoLog(value = "表外台账管理 -编辑")
	@ApiOperation(value="表外台账管理 -编辑", notes="表外台账管理 -编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody LoanBwdkSjmx loanBwdkSjmx) {
		UpdateWrapper<LoanBwdkSjmx> updateWrapper=new UpdateWrapper<>();
		updateWrapper.eq("xh",loanBwdkSjmx.getXh());
		loanBwdkSjmxService.update(loanBwdkSjmx,updateWrapper);
		return Result.ok("编辑成功!");

	}
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "表外台账管理 -通过id删除")
	@ApiOperation(value="表外台账管理 -通过id删除", notes="表外台账管理 -通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) Integer id) {
		loanBwdkSjmxService.deleteByDkzh(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "表外台账管理 -批量删除")
	@ApiOperation(value="表外台账管理 -批量删除", notes="表外台账管理 -批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.loanBwdkSjmxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "表外台账管理 -通过id查询")
	@ApiOperation(value="表外台账管理 -通过id查询", notes="表外台账管理 -通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		LoanBwdkSjmx loanBwdkSjmx = loanBwdkSjmxService.getById(id);
		return Result.ok(loanBwdkSjmx);
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
			 HSSFWorkbook newBook = null;
			 try {
				 List<LoanBwdkSjmx> listBwdk = ExcelImportUtil.importExcel(file, LoanBwdkSjmx.class, params);
				 List<LoanBwdkSjmx> insertList = new ArrayList<LoanBwdkSjmx>();

				 fis = new FileInputStream(baseFilePath);
				 newBook = new HSSFWorkbook(new POIFSFileSystem(fis));
				 HSSFSheet sheet = newBook.getSheetAt(0);
				 HSSFRow hssfRow = null;
				 int rCi = 0, rCii = 0;
				 int i = 2;
				 for (LoanBwdkSjmx bwdk : listBwdk) {
					 hssfRow = sheet.getRow(i++);
					 if (rCi == 0) {
						 rCi = hssfRow.getLastCellNum();
						 rCii = rCi + 1;
					 }
					 HSSFCell resultCell = hssfRow.getCell(rCi);
					 if (resultCell == null) resultCell = hssfRow.createCell(rCi);
					 HSSFCell resultCellInfo = hssfRow.getCell(rCii);
					 if (resultCellInfo == null) resultCellInfo = hssfRow.createCell(rCii);

					 String result = "导入成功";
					 String resultInfo = "";

					 if (StringUtils.isEmpty(bwdk.getDkzh())) {
						 result = "导入失败";
						 resultInfo = "贷款账号不能为空！";
						 resultCell.setCellValue(result);
						 resultCellInfo.setCellValue(resultInfo);
						 continue;
					 }
					 if (bwdk.getXh()<0 || bwdk.getXh()==null) {
						 result = "导入失败";
						 resultInfo = "序号不能为空！";
						 resultCell.setCellValue(result);
						 resultCellInfo.setCellValue(resultInfo);
						 continue;
					 }

					 QueryWrapper<LoanBwdkSjmx> queryWrapper = new QueryWrapper<>();
					 queryWrapper.eq("dkzh", bwdk.getDkzh());
					 LoanBwdkSjmx bwdkSjmx = loanBwdkSjmxService.getOne(queryWrapper, false);
					 if (bwdkSjmx != null) {
						 UpdateWrapper<LoanBwdkSjmx> updateWrapper = new UpdateWrapper<>();
						 updateWrapper.eq("dkzh", bwdk.getDkzh());
						 loanBwdkSjmxService.remove(updateWrapper);
					 }
					 resultCell.setCellValue(result);
					 resultCellInfo.setCellValue(resultInfo);
					 insertList.add(bwdk);

					 LoanBwdkSjmx t = loanBwdkSjmxService.queryByDkzh(bwdk.getXh());
					 if (t !=null ) {
						 UpdateWrapper<LoanBwdkSjmx> updateWrapper = new UpdateWrapper<>();
						 updateWrapper.eq("xh", bwdk.getXh());
						 loanBwdkSjmxService.remove(updateWrapper);
					 }


				 }

				 obj.put("filePath", filePath);
				 loanBwdkSjmxService.saveBatch(insertList);

				 FileOutputStream fos = new FileOutputStream(baseFilePath);
				 newBook.write(fos);
				 fos.flush();
				 fos.close();
				 return Result.ok("文件导入成功！数据行数:" + listBwdk.size() + "，导入成功行数：" + insertList.size() + "，失败行数：" + (listBwdk.size()-insertList.size()), obj);
			 } catch (Exception e) {
				 log.error(e.getMessage(),e);
				 return Result.error("文件导入失败:"+e.getMessage());
			 }
		 }
		 return Result.ok("文件导入失败！");
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
		 mv.addObject(NormalExcelConstants.FILE_NAME, "表外台账导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, LoanBwdkSjmx.class);
		 ExportParams exportParams = new ExportParams("表外台账导入模板", "模板信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<LoanBwdkSjmx>());
		 return mv;
	 }
	 /**
	  * 导出excel
	  *
	  * @param request
	  * @param loanBwdkSjmx
	  */
	 @RequestMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, LoanBwdkSjmx loanBwdkSjmx) {
		 // Step.1 组装查询条件
		 QueryWrapper<LoanBwdkSjmx> queryWrapper = null;
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)) {
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 LoanBwdkSjmx dkjkpt_zhbldkftjk_bsy = JSON.parseObject(deString, LoanBwdkSjmx.class);
				 queryWrapper = QueryGenerator.initQueryWrapper(dkjkpt_zhbldkftjk_bsy, request.getParameterMap());
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }

		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<LoanBwdkSjmx> pageList = loanBwdkSjmxService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "表外台账导出");
		 mv.addObject(NormalExcelConstants.CLASS, LoanBwdkSjmx.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("表外台账导出", "导出人:Jeecg", "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		 return mv;
	 }
}
