package org.cmms.modules.tjfx.jcsjgl.gsxxdr.controller;

import java.io.*;
import java.text.SimpleDateFormat;
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
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.jcsjgl.dkye.entity.TjfxXddkyebXdxtsj;
import org.cmms.modules.tjfx.jcsjgl.gsxxdr.entity.TjfxGsxxdr;
import org.cmms.modules.tjfx.jcsjgl.gsxxdr.service.ITjfxGsxxdrService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.jcsjgl.rwsz.entity.Tjfx_rwsz;
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
 * @Date:   2020-08-12
 * @Version: V1.0
 */
@Slf4j
@Api(tags="1")
@RestController
@RequestMapping("/TjfxGsxxdr/tjfxGsxxdr")
public class TjfxGsxxdrController extends JeecgController<TjfxGsxxdr, ITjfxGsxxdrService> {
	@Autowired
	private ITjfxGsxxdrService tjfxGsxxdrService;
	 @Value(value = "${common.path.upload}")
	 private String uploadpath;
	/**
	 * 分页列表查询
	 *
	 * @param tjfxGsxxdr
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "1-分页列表查询")
	@ApiOperation(value="1-分页列表查询", notes="1-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TjfxGsxxdr tjfxGsxxdr,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TjfxGsxxdr> queryWrapper = QueryGenerator.initQueryWrapper(tjfxGsxxdr, req.getParameterMap());
		Page<TjfxGsxxdr> page = new Page<TjfxGsxxdr>(pageNo, pageSize);
		IPage<TjfxGsxxdr> pageList = tjfxGsxxdrService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param tjfxGsxxdr
	 * @return
	 */
	@AutoLog(value = "1-添加")
	@ApiOperation(value="1-添加", notes="1-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TjfxGsxxdr tjfxGsxxdr) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		TjfxGsxxdr t = tjfxGsxxdrService.queryByMsg(tjfxGsxxdr.getSszh(),tjfxGsxxdr.getZjhm(),tjfxGsxxdr.getGsrq());
		if (t==null){
			tjfxGsxxdrService.save(tjfxGsxxdr);
			return Result.ok("添加成功！");
		}else {
			return Result.error("数据重复! 添加失败");
		}

	}

	/**
	 * 编辑
	 *
	 * @param tjfxGsxxdr
	 * @return
	 */
	@AutoLog(value = "1-编辑")
	@ApiOperation(value="1-编辑", notes="1-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TjfxGsxxdr tjfxGsxxdr) {
		try {
				UpdateWrapper<TjfxGsxxdr> updateWrapper=new UpdateWrapper<>();
				updateWrapper.eq("jgdm",tjfxGsxxdr.getSszh()).eq("zjhm",tjfxGsxxdr.getZjhm()).eq("gsrq",tjfxGsxxdr.getGsrq());
				tjfxGsxxdrService.update(tjfxGsxxdr,updateWrapper);
				return Result.ok("修改成功！");

		}catch (Exception e){
			System.out.println(e);
			return Result.error("修改失败");
		}
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
		tjfxGsxxdrService.removeById(id);
		return Result.ok("删除成功!");
	}

	 /**
	  * 通过id删除
	  *
	  * @param
	  * @return
	  */
	 @AutoLog(value = "1-通过id删除")
	 @ApiOperation(value="1-通过id删除", notes="1-通过id删除")
	 @PutMapping(value = "/delete2")
	 public Result<?> delete2(@RequestBody TjfxGsxxdr tjfxGsxxdr) {
		 try {
			 UpdateWrapper<TjfxGsxxdr> updateWrapper=new UpdateWrapper<>();
			 updateWrapper.eq("sszh",tjfxGsxxdr.getSszh()).eq("zjhm",tjfxGsxxdr.getZjhm()).eq("gsrq",tjfxGsxxdr.getGsrq());
			 tjfxGsxxdrService.remove(updateWrapper);
			 return Result.ok("删除成功！");
		 }catch (Exception e){
			 System.out.println(e);
			 return Result.error("删除失败");
		 }
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
		this.tjfxGsxxdrService.removeByIds(Arrays.asList(ids.split(",")));
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
		TjfxGsxxdr tjfxGsxxdr = tjfxGsxxdrService.getById(id);
		return Result.ok(tjfxGsxxdr);
	}

	 /**
	  * 导出excel
	  *
	  * @param request
	  * @param tjfxGsxxdr
	  */
	 @RequestMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, TjfxGsxxdr tjfxGsxxdr) {
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 // Step.1 组装查询条件
		 QueryWrapper<TjfxGsxxdr> queryWrapper = null;
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)) {
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 TjfxGsxxdr dkjkpt_zhbldkftjk_bsy = JSON.parseObject(deString, TjfxGsxxdr.class);
				 queryWrapper = QueryGenerator.initQueryWrapper(dkjkpt_zhbldkftjk_bsy, request.getParameterMap());
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }

		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<TjfxGsxxdr> pageList = tjfxGsxxdrService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "公示信息导出");
		 mv.addObject(NormalExcelConstants.CLASS, TjfxGsxxdr.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("公示信息导出", "导出人:"+sysUser.getRealname(), "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
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
				 List<TjfxGsxxdr> listKhhmcs = ExcelImportUtil.importExcel(file, TjfxGsxxdr.class, params);
				 List<String> ids = new ArrayList<String>();
				 List<TjfxGsxxdr> insertList = new ArrayList<TjfxGsxxdr>();
				 fis = new FileInputStream(baseFilePath);
				 newBook = new HSSFWorkbook(new POIFSFileSystem(fis));
				 HSSFSheet sheet = newBook.getSheetAt(0);
				 HSSFRow hssfRow = null;
				 int rCi = 0, rCii = 0;
				 int i = 2;
				 for (TjfxGsxxdr khhmc : listKhhmcs) {
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
					 if (StringUtils.isEmpty(khhmc.getSszh())) {
						 result = "导入失败";
						 resultInfo = "所属支行不能为空！";
						 resultCell.setCellValue(result);
						 resultCellInfo.setCellValue(resultInfo);
						 continue;
					 }
					 if (StringUtils.isEmpty(khhmc.getZjhm())) {
						 result = "导入失败";
						 resultInfo = "证件号码不能为空！";
						 resultCell.setCellValue(result);
						 resultCellInfo.setCellValue(resultInfo);
						 continue;
					 }
//					 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
//					 String date = simpleDateFormat.format(khhmc.getGsrq());
					 /*TjfxGsxxdr t = tjfxGsxxdrService.queryByMsg(khhmc.getSszh(),khhmc.getZjhm(),khhmc.getGsrq());
					 UpdateWrapper<TjfxGsxxdr> updateWrapper  = new UpdateWrapper<>();
					 updateWrapper.eq(khhmc.getSszh())
					 if (t!=null){
					 	//String gsrq = simpleDateFormat.format(t.getGsrq());
						 ids.add(t.getZjhm()+"-"+t.getSszh()+"-"+t.getGsrq());
					 }*/
					 QueryWrapper<TjfxGsxxdr> queryWrapper = new QueryWrapper<>();
					 resultCell.setCellValue(result);
					 resultCellInfo.setCellValue(resultInfo);
					 khhmc.setGscs(tjfxGsxxdrService.querygscs(khhmc.getSszh(),khhmc.getZjhm()));
					 insertList.add(khhmc);

					 TjfxGsxxdr t = tjfxGsxxdrService.queryByMsg(khhmc.getSszh(),khhmc.getZjhm(),khhmc.getGsrq());
					 if (t !=null ) {
						 UpdateWrapper<TjfxGsxxdr> updateWrapper = new UpdateWrapper<>();
						 updateWrapper.eq("sszh", khhmc.getSszh()).eq("zjhm", khhmc.getZjhm()).eq("gsrq", khhmc.getGsrq());
						 tjfxGsxxdrService.remove(updateWrapper);
					 }
				 }
				 //先删除已经存在的数据
//				 if (!ids.isEmpty()) {
//					 for (String id : ids) {
//						 try{
//							 String[] split = id.split("-");
//							 UpdateWrapper<TjfxGsxxdr> userUpdateWrapper = new UpdateWrapper<>();
//							 userUpdateWrapper.eq("zjhm",split[0]).eq("sszh",split[1]).eq("gsrq",split[2]);
//							 tjfxGsxxdrService.delete2(split[0],split[1],split[2]);
//						 } catch (Exception e) {
//							 log.error("删除失败",e.getMessage());
//							 return Result.error("删除失败!");
//						 }
//					 }
//				 }
				 obj.put("filePath", filePath);
				 tjfxGsxxdrService.saveBatch(insertList);
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
		 mv.addObject(NormalExcelConstants.FILE_NAME, "公示信息导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, TjfxGsxxdr.class);
		 ExportParams exportParams = new ExportParams("公示信息导入模板", "模板信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<TjfxGsxxdr>());
		 return mv;
	 }

}
