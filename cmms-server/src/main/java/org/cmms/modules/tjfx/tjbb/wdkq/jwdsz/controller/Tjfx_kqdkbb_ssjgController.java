package org.cmms.modules.tjfx.tjbb.wdkq.jwdsz.controller;

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
import org.cmms.modules.tjfx.tjbb.wdkq.jwdsz.entity.Tjfx_kqdkbb_ssjg;
import org.cmms.modules.tjfx.tjbb.wdkq.jwdsz.service.ITjfx_kqdkbb_ssjgService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-04-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags="1")
@RestController
@RequestMapping("/tjfx_kqdkbb_ssjg/tjfx_kqdkbb_ssjg")
public class Tjfx_kqdkbb_ssjgController extends JeecgController<Tjfx_kqdkbb_ssjg, ITjfx_kqdkbb_ssjgService> {
	@Autowired
	private ITjfx_kqdkbb_ssjgService tjfx_kqdkbb_ssjgService;
	 @Value(value = "${common.path.upload}")
	 private String uploadpath;
	/**
	 * 分页列表查询
	 *
	 * @param tjfx_kqdkbb_ssjg
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "1-分页列表查询")
	@ApiOperation(value="1-分页列表查询", notes="1-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Tjfx_kqdkbb_ssjg tjfx_kqdkbb_ssjg,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Tjfx_kqdkbb_ssjg> queryWrapper = QueryGenerator.initQueryWrapper(tjfx_kqdkbb_ssjg, req.getParameterMap());
		Page<Tjfx_kqdkbb_ssjg> page = new Page<Tjfx_kqdkbb_ssjg>(pageNo, pageSize);
		IPage<Tjfx_kqdkbb_ssjg> pageList = tjfx_kqdkbb_ssjgService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param tjfx_kqdkbb_ssjg
	 * @return
	 */
	@AutoLog(value = "1-添加")
	@ApiOperation(value="1-添加", notes="1-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Tjfx_kqdkbb_ssjg tjfx_kqdkbb_ssjg) {
		tjfx_kqdkbb_ssjgService.save(tjfx_kqdkbb_ssjg);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param tjfx_kqdkbb_ssjg
	 * @return
	 */
	@AutoLog(value = "1-编辑")
	@ApiOperation(value="1-编辑", notes="1-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Tjfx_kqdkbb_ssjg tjfx_kqdkbb_ssjg) {
		tjfx_kqdkbb_ssjgService.updateById(tjfx_kqdkbb_ssjg);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "1-通过id删除")
	@ApiOperation(value="1-通过id删除", notes="1-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tjfx_kqdkbb_ssjgService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "1-批量删除")
	@ApiOperation(value="1-批量删除", notes="1-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tjfx_kqdkbb_ssjgService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "1-通过id查询")
	@ApiOperation(value="1-通过id查询", notes="1-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Tjfx_kqdkbb_ssjg tjfx_kqdkbb_ssjg = tjfx_kqdkbb_ssjgService.getById(id);
		return Result.ok(tjfx_kqdkbb_ssjg);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param tjfx_kqdkbb_ssjg
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Tjfx_kqdkbb_ssjg tjfx_kqdkbb_ssjg) {
      return super.exportXls(request, tjfx_kqdkbb_ssjg, Tjfx_kqdkbb_ssjg.class, "1");
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
		 mv.addObject(NormalExcelConstants.FILE_NAME, "经纬度设置导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, Tjfx_kqdkbb_ssjg.class);
		 ExportParams exportParams = new ExportParams("经纬度设置导入模板", "模板信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<Tjfx_kqdkbb_ssjg>());
		 return mv;
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
				 List<Tjfx_kqdkbb_ssjg> listKhhmcs = ExcelImportUtil.importExcel(file, Tjfx_kqdkbb_ssjg.class, params);
				 List<String> ids = new ArrayList<String>();
				 List<Tjfx_kqdkbb_ssjg> insertList = new ArrayList<Tjfx_kqdkbb_ssjg>();
				 fis = new FileInputStream(baseFilePath);
				 newBook = new HSSFWorkbook(new POIFSFileSystem(fis));
				 HSSFSheet sheet = newBook.getSheetAt(0);
				 HSSFRow hssfRow = null;
				 int rCi = 0, rCii = 0;
				 int i = 2;
				 for (Tjfx_kqdkbb_ssjg khhmc : listKhhmcs) {

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
					 if (StringUtils.isEmpty(khhmc.getSsjgdm())) {
						 result = "导入失败";
						 resultInfo = "所属支行不能为空！";
						 resultCell.setCellValue(result);
						 resultCellInfo.setCellValue(resultInfo);
						 continue;
					 }

					 QueryWrapper<Tjfx_kqdkbb_ssjg> queryWrapper = new QueryWrapper<>();
					 queryWrapper.eq("ssjgdm", khhmc.getSsjgdm()).eq("ssjgdm",khhmc.getSsjgdm());

					 Tjfx_kqdkbb_ssjg user = tjfx_kqdkbb_ssjgService.getOne(queryWrapper);
					 if (user != null) {
						 ids.add(user.getSsjgdm());
					 }
					 resultCell.setCellValue(result);
					 resultCellInfo.setCellValue(resultInfo);
					 insertList.add(khhmc);

				 }
				 //先删除已经存在的数据
				 if (!ids.isEmpty()) {
					 for (int j = 0; j < ids.size(); j++) {
						 try{
							 UpdateWrapper<Tjfx_kqdkbb_ssjg> userUpdateWrapper = new UpdateWrapper<>();
							 userUpdateWrapper.eq("ssjgdm", ids.get(j).split("-")[0]);
							 tjfx_kqdkbb_ssjgService.remove(userUpdateWrapper);
						 } catch (Exception e) {
							 log.error("删除失败",e.getMessage());
							 return Result.error("删除失败!");
						 }
					 }
				 }


				 obj.put("filePath", filePath);
				 tjfx_kqdkbb_ssjgService.saveBatch(insertList);
				 FileOutputStream fos = new FileOutputStream(baseFilePath);
				 newBook.write(fos);
				 fos.flush();
				 fos.close();
				 return Result.ok("文件导入成功！数据行数:" + listKhhmcs.size() + "，导入成功行数：" + insertList.size() + "，失败行数：" + (listKhhmcs.size()-insertList.size()), obj);
			 } catch (Exception e) {
				 log.error(e.getMessage(),e);
				 return Result.error("文件导入失败:"+e.getMessage());
			 }
		 }
		 return Result.ok("文件导入失败！");
	 }
}
