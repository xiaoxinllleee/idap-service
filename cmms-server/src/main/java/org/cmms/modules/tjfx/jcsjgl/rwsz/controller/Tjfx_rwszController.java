package org.cmms.modules.tjfx.jcsjgl.rwsz.controller;

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
import org.cmms.common.utils.ListToDictUtil;
import org.cmms.modules.dkjkpt.dhgzfjxx.tsxxtzs.entity.DkjkptDhgzfjxx;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.tjfx.jcsjgl.rwsz.entity.Tjfx_rwsz;
import org.cmms.modules.tjfx.jcsjgl.rwsz.service.ITjfx_rwszService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;
import org.cmms.modules.yxdygl.ejyxdygl.entity.VEjyxdygl;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
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
 * @Date:   2020-05-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags="1")
@RestController
@RequestMapping("/tjfx_rwsz/tjfx_rwsz")
public class Tjfx_rwszController extends JeecgController<Tjfx_rwsz, ITjfx_rwszService> implements Job {
	@Autowired
	private ITjfx_rwszService iTjfxRwszService;
	@Value(value = "${common.path.upload}")
	private String uploadpath;

	/**
	 * 分页列表查询
	 * @param tjfx_rwsz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "任务设置分页列表查询")
	@ApiOperation(value="任务设置分页列表查询", notes="任务设置分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Tjfx_rwsz tjfx_rwsz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Tjfx_rwsz> queryWrapper = QueryGenerator.initQueryWrapper(tjfx_rwsz, req.getParameterMap());
		Page<Tjfx_rwsz> page = new Page<Tjfx_rwsz>(pageNo, pageSize);
		IPage<Tjfx_rwsz> pageList = iTjfxRwszService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 * @param tjfx_rwsz
	 * @return
	 */
	@AutoLog(value = "任务设置添加")
	@ApiOperation(value="任务设置添加", notes="任务设置添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Tjfx_rwsz tjfx_rwsz) {
		iTjfxRwszService.save(tjfx_rwsz);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 * @param tjfxRwsz
	 * @return
	 */
	@AutoLog(value = "任务设置编辑")
	@ApiOperation(value="任务设置编辑", notes="任务设置编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Tjfx_rwsz tjfxRwsz) {
		try {
			/*UpdateWrapper<Tjfx_rwsz> updateWrapper = new UpdateWrapper<>();
			updateWrapper.eq("zzbz", tjfxRwsz.getZzbz());
			updateWrapper.eq("rwnf", tjfxRwsz.getRwnf());
			iTjfxRwszService.update(tjfxRwsz, updateWrapper);*/
			iTjfxRwszService.updateById(tjfxRwsz);
			return Result.ok("修改成功！");
		} catch (Exception e) {
			System.out.println(e);
			return Result.error("修改失败");
		}
	}
	
	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "任务设置通过id删除")
	@ApiOperation(value="任务设置通过id删除", notes="任务设置通过id删除")
	@PutMapping(value = "/delete")
	public Result<?> delete(@RequestBody Tjfx_rwsz tjfx_rwsz) {
		try {
			UpdateWrapper<Tjfx_rwsz> updateWrapper=new UpdateWrapper<>();
			updateWrapper.eq("zzbz",tjfx_rwsz.getZzbz()).eq("rwnf",tjfx_rwsz.getRwnf());
			iTjfxRwszService.remove(updateWrapper);
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
	@AutoLog(value = "任务设置批量删除")
	@ApiOperation(value="任务设置批量删除", notes="任务设置批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.iTjfxRwszService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "任务设置通过id查询")
	@ApiOperation(value="任务设置通过id查询", notes="任务设置通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Tjfx_rwsz tjfx_rwsz = iTjfxRwszService.getById(id);
		return Result.ok(tjfx_rwsz);
	}

	 /**
	  * 导出excel
	  * @param request
	  * @param
	  */
	 @RequestMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request,Tjfx_rwsz tjfx_rwsz) {
		 return super.exportXls(request, tjfx_rwsz, Tjfx_rwsz.class, "任务设置");
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
				 List<Tjfx_rwsz> tjfxRwszList = ExcelImportUtil.importExcel(file, Tjfx_rwsz.class, params);
				 List<String> ids = new ArrayList<String>();
				 List<Tjfx_rwsz> insertList = new ArrayList<Tjfx_rwsz>();
				 fis = new FileInputStream(baseFilePath);
				 newBook = new HSSFWorkbook(new POIFSFileSystem(fis));
				 HSSFSheet sheet = newBook.getSheetAt(0);
				 HSSFRow hssfRow = null;
				 int rCi = 0, rCii = 0;
				 int i = 2;
				 for (Tjfx_rwsz rwsz : tjfxRwszList) {
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
					 if (StringUtils.isEmpty(rwsz.getZzbz())) {
						 result = "导入失败";
						 resultInfo = "组织标识不能为空！";
						 resultCell.setCellValue(result);
						 resultCellInfo.setCellValue(resultInfo);
						 continue;
					 }
					 QueryWrapper<Tjfx_rwsz> queryWrapper = new QueryWrapper<>();
					 queryWrapper.eq("zzbz", rwsz.getZzbz()).eq("rwnf",rwsz.getRwnf());

					 Tjfx_rwsz user = iTjfxRwszService.getOne(queryWrapper);
					 if (user != null) {
						 ids.add(user.getZzbz()+"-"+user.getRwnf());
					 }
					 resultCell.setCellValue(result);
					 resultCellInfo.setCellValue(resultInfo);
					 insertList.add(rwsz);
				 }
				 //先删除已经存在的数据
				 if (!ids.isEmpty()) {
					 for (int j = 0; j < ids.size(); j++) {
						 try{
							 UpdateWrapper<Tjfx_rwsz> userUpdateWrapper = new UpdateWrapper<>();
							 userUpdateWrapper.eq("zzbz", ids.get(j).split("-")[0]).eq("rwnf",ids.get(j).split("-")[1]);
							 iTjfxRwszService.remove(userUpdateWrapper);
						 } catch (Exception e) {
							 log.error("删除失败",e.getMessage());
							 return Result.error("删除失败!");
						 }
					 }
				 }

				 obj.put("filePath", filePath);
				 iTjfxRwszService.saveBatch(insertList);
				 FileOutputStream fos = new FileOutputStream(baseFilePath);
				 newBook.write(fos);
				 fos.flush();
				 fos.close();
				 return Result.ok("文件导入成功！数据行数:" + tjfxRwszList.size() + "，导入成功行数：" + insertList.size() + "，失败行数：" + (tjfxRwszList.size()-insertList.size()), obj);
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
		 mv.addObject(NormalExcelConstants.FILE_NAME, "任务设置导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, Tjfx_rwsz.class);
		 ExportParams exportParams = new ExportParams("任务设置导入模板", "模板信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<Tjfx_rwsz>());
		 return mv;
	 }


	 @Override
	 public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

	 }
 }
