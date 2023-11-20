package org.cmms.modules.yxdygl.czxxgl.controller;

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

import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;
import org.cmms.modules.yxdygl.czxxgl.entity.KhglFjxxtjXzc;
import org.cmms.modules.yxdygl.czxxgl.entity.TjfxKhywhbbXzc;
import org.cmms.modules.yxdygl.czxxgl.entity.Yxdygl_czxxgl;
import org.cmms.modules.yxdygl.czxxgl.service.IKhglFjxxtjXzcService;
import org.cmms.modules.yxdygl.czxxgl.service.ITjfxKhywhbbXzcService;
import org.cmms.modules.yxdygl.czxxgl.service.IYxdygl_czxxglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

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
 * @Description: 村信息管理
 * @Author: cmms
 * @Date:   2019-11-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="村信息管理")
@RestController
@RequestMapping("/yxdygl_czxxgl/yxdygl_czxxgl")
public class Yxdygl_czxxglController {
	@Autowired
	private IYxdygl_czxxglService yxdygl_czxxglService;
	 @Autowired
	 private IKhglFjxxtjXzcService khglFjxxtjXzcService;
	 @Autowired
	 private ITjfxKhywhbbXzcService tjfxKhywhbbXzcService;
	 @Autowired
	 private ITjfxZhbyService tjfxZhbyService;

	 @Value(value = "${common.path.upload}")
	 private String uploadpath;
	/**
	  * 分页列表查询
	 * @param yxdygl_czxxgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "村信息管理-分页列表查询")
	@ApiOperation(value="村信息管理-分页列表查询", notes="村信息管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Yxdygl_czxxgl>> queryPageList(Yxdygl_czxxgl yxdygl_czxxgl,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Yxdygl_czxxgl>> result = new Result<IPage<Yxdygl_czxxgl>>();
		QueryWrapper<Yxdygl_czxxgl> queryWrapper = QueryGenerator.initQueryWrapper(yxdygl_czxxgl, req.getParameterMap());
		Page<Yxdygl_czxxgl> page = new Page<Yxdygl_czxxgl>(pageNo, pageSize);
		IPage<Yxdygl_czxxgl> pageList = yxdygl_czxxglService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param yxdygl_czxxgl
	 * @return
	 */
	@AutoLog(value = "村信息管理-添加")
	@ApiOperation(value="村信息管理-添加", notes="村信息管理-添加")
	@PostMapping(value = "/add")
	public Result<Yxdygl_czxxgl> add(@RequestBody Yxdygl_czxxgl yxdygl_czxxgl) {
		Result<Yxdygl_czxxgl> result = new Result<Yxdygl_czxxgl>();
		try {
			yxdygl_czxxglService.save(yxdygl_czxxgl);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param yxdygl_czxxgl
	 * @return
	 */
	@AutoLog(value = "村信息管理-编辑")
	@ApiOperation(value="村信息管理-编辑", notes="村信息管理-编辑")
	@PutMapping(value = "/edit")
	public Result<Yxdygl_czxxgl> edit(@RequestBody Yxdygl_czxxgl yxdygl_czxxgl) {
		Result<Yxdygl_czxxgl> result = new Result<Yxdygl_czxxgl>();
		//Yxdygl_czxxgl yxdygl_czxxglEntity = yxdygl_czxxglService.getById(yxdygl_czxxgl.getQybm());
		QueryWrapper<Yxdygl_czxxgl> userUpdateWrapper = new QueryWrapper<>();
		userUpdateWrapper.eq("qybm", yxdygl_czxxgl.getQybm());
		Yxdygl_czxxgl yxdygl_czxxglEntity = yxdygl_czxxglService.getOne(userUpdateWrapper);
		if(yxdygl_czxxglEntity==null) {
			result.error500("未找到对应实体");
		}else {
			UpdateWrapper<Yxdygl_czxxgl> userUpdateWrapper1 = new UpdateWrapper<>();
			userUpdateWrapper1.eq("qybm",yxdygl_czxxgl.getQybm());
			boolean ok = yxdygl_czxxglService.update(yxdygl_czxxgl,userUpdateWrapper1);
			//boolean ok = yxdygl_czxxglService.updateById(yxdygl_czxxgl);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}

		return result;
	}

	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "村信息管理-通过id删除")
	@ApiOperation(value="村信息管理-通过id删除", notes="村信息管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			UpdateWrapper<Yxdygl_czxxgl> userUpdateWrapper = new UpdateWrapper<>();
			userUpdateWrapper.eq("qybm", id);
			yxdygl_czxxglService.remove(userUpdateWrapper);
			//yxdygl_czxxglService.removeById(id);
		} catch (Exception e) {
			log.error("删除失败",e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}

	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "村信息管理-批量删除")
	@ApiOperation(value="村信息管理-批量删除", notes="村信息管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<Yxdygl_czxxgl> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Yxdygl_czxxgl> result = new Result<Yxdygl_czxxgl>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.yxdygl_czxxglService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "村信息管理-通过id查询")
	@ApiOperation(value="村信息管理-通过id查询", notes="村信息管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Yxdygl_czxxgl> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Yxdygl_czxxgl> result = new Result<Yxdygl_czxxgl>();
		Yxdygl_czxxgl yxdygl_czxxgl = yxdygl_czxxglService.getById(id);
		if(yxdygl_czxxgl==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(yxdygl_czxxgl);
			result.setSuccess(true);
		}
		return result;
	}

  /**
      * 导出excel
   *
   * @param request
   * @param response
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<Yxdygl_czxxgl> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Yxdygl_czxxgl yxdygl_czxxgl = JSON.parseObject(deString, Yxdygl_czxxgl.class);
              queryWrapper = QueryGenerator.initQueryWrapper(yxdygl_czxxgl, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Yxdygl_czxxgl> pageList = yxdygl_czxxglService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "村组信息管理列表");
      mv.addObject(NormalExcelConstants.CLASS, Yxdygl_czxxgl.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("村组信息管理列表数据", "导出人:Jeecg", "导出信息"));
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
				 List<Yxdygl_czxxgl> listKhhmcs = ExcelImportUtil.importExcel(file, Yxdygl_czxxgl.class, params);
				 List<String> ids = new ArrayList<String>();
				 List<Yxdygl_czxxgl> insertList = new ArrayList<Yxdygl_czxxgl>();
				 fis = new FileInputStream(baseFilePath);
				 newBook = new HSSFWorkbook(new POIFSFileSystem(fis));
				 HSSFSheet sheet = newBook.getSheetAt(0);
				 HSSFRow hssfRow = null;
				 int rCi = 0, rCii = 0;
				 int i = 2;
				 for (Yxdygl_czxxgl khhmc : listKhhmcs) {
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
					 if (StringUtils.isEmpty(khhmc.getQybm())) {
						 result = "导入失败";
						 resultInfo = "区域编码不能为空！";
						 resultCell.setCellValue(result);
						 resultCellInfo.setCellValue(resultInfo);
						 continue;
					 }
					 if (StringUtils.isEmpty(khhmc.getVillage())) {
						 result = "导入失败";
						 resultInfo = "村（街道/居委会）不能为空！";
						 resultCell.setCellValue(result);
						 resultCellInfo.setCellValue(resultInfo);
						 continue;
					 }
					 if (StringUtils.isEmpty(khhmc.getSsjgdm())) {
						 result = "导入失败";
						 resultInfo = "所属机构代码不能为空！";
						 resultCell.setCellValue(result);
						 resultCellInfo.setCellValue(resultInfo);
						 continue;
					 }
					 if (StringUtils.isEmpty(khhmc.getZkhjl())) {
						 result = "导入失败";
						 resultInfo = "主客户经理不能为空！";
						 resultCell.setCellValue(result);
						 resultCellInfo.setCellValue(resultInfo);
						 continue;
					 }

					 QueryWrapper<Yxdygl_czxxgl> queryWrapper = new QueryWrapper<>();
					 queryWrapper.eq("qybm", khhmc.getQybm()).eq("zkhjl",khhmc.getZkhjl());

					 Yxdygl_czxxgl user = yxdygl_czxxglService.getOne(queryWrapper);
					 if (user != null) {
						 ids.add(user.getQybm()+"-"+user.getZkhjl());
					 }
					 resultCell.setCellValue(result);
					 resultCellInfo.setCellValue(resultInfo);
					 insertList.add(khhmc);
				 }
				 //先删除已经存在的数据
				 if (!ids.isEmpty()) {
					 for (int j = 0; j < ids.size(); j++) {
						 try{
							 UpdateWrapper<Yxdygl_czxxgl> userUpdateWrapper = new UpdateWrapper<>();
							 userUpdateWrapper.eq("qybm", ids.get(j).split("-")[0]).eq("zkhjl",ids.get(j).split("-")[1]);
							 yxdygl_czxxglService.remove(userUpdateWrapper);
						 } catch (Exception e) {
							 log.error("删除失败",e.getMessage());
							 return Result.error("删除失败!");
						 }
					 }
				 }


				 obj.put("filePath", filePath);
				 yxdygl_czxxglService.saveBatch(insertList);
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
		 mv.addObject(NormalExcelConstants.FILE_NAME, "村组信息导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, Yxdygl_czxxgl.class);
		 ExportParams exportParams = new ExportParams("村组信息导入模板", "模板信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<Yxdygl_czxxgl>());
		 return mv;
	 }



	 /**
	  * 查看附加信息
	  *
	  * @param khglFjxxtjXzc
	  * @return
	  */
	 @RequestMapping(value = "/khfjxx", method = RequestMethod.PUT)
	 public Result<KhglFjxxtjXzc> querykhfjxx(@RequestBody KhglFjxxtjXzc khglFjxxtjXzc) {
	 	  Result<KhglFjxxtjXzc> result = new Result<KhglFjxxtjXzc>();
		 try {
		 	Map<String,String[]> map = new HashMap<>();
			 khglFjxxtjXzc.setSszh(tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization", "zzbz", "ywjgdm", khglFjxxtjXzc.getSszh()));
			 QueryWrapper<KhglFjxxtjXzc> queryWrapper = QueryGenerator.initQueryWrapper(khglFjxxtjXzc,map);
		 	List<KhglFjxxtjXzc> fjxxlist = khglFjxxtjXzcService.list(queryWrapper);
			if (fjxxlist.size()!= 0 ) {
				result.setResult(fjxxlist.get(0));
			}
			 result.setSuccess(true);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 return result;
	 }
	 /**
	  * 查看附加信息
	  *
	  * @param tjfxKhywhbbXzc
	  * @return
	  */
	 @RequestMapping(value = "/khywxx", method = RequestMethod.PUT)
	 public Result<List<TjfxKhywhbbXzc>> querykhywxx(@RequestBody TjfxKhywhbbXzc tjfxKhywhbbXzc) {
		 Result<List<TjfxKhywhbbXzc>> result = new Result<List<TjfxKhywhbbXzc>>();
		 try {
			 Map<String,String[]> map = new HashMap<>();
			 tjfxKhywhbbXzc.setSszh(tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization", "zzbz", "ywjgdm", tjfxKhywhbbXzc.getSszh()));
			 QueryWrapper<TjfxKhywhbbXzc> queryWrapper = QueryGenerator.initQueryWrapper(tjfxKhywhbbXzc,map);
			 List<TjfxKhywhbbXzc> fjxxlist = tjfxKhywhbbXzcService.list(queryWrapper);
			 result.setResult(fjxxlist);
			 result.setSuccess(true);

		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 return result;
	 }


}
