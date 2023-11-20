package org.cmms.modules.dkjkpt.dkglqsckqsfx.dkkhglrgl.controller;

import java.io.File;
import java.util.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.dkkhglrgl.entity.DkjkptDkkhglrgl;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.dkkhglrgl.entity.DkjkptDkkhglrglImport;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.dkkhglrgl.service.IDkjkptDkkhglrglService;
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
 * @Description: 贷款客户关联人管理
 * @Author: cmms
 * @Date:   2019-10-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款客户关联人管理")
@RestController
@RequestMapping("/dkjkpt.dkglqsckqsfx.dkkhglrgl/dkjkptDkkhglrgl")
public class DkjkptDkkhglrglController {
	@Autowired
	private IDkjkptDkkhglrglService dkjkptDkkhglrglService;

	 @Value(value = "${common.path.upload}")
	 private String uploadpath;
	
	/**
	  * 分页列表查询
	 * @param dkjkptDkkhglrgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款客户关联人管理-分页列表查询")
	@ApiOperation(value="贷款客户关联人管理-分页列表查询", notes="贷款客户关联人管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<DkjkptDkkhglrgl>> queryPageList(DkjkptDkkhglrgl dkjkptDkkhglrgl,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<DkjkptDkkhglrgl>> result = new Result<IPage<DkjkptDkkhglrgl>>();
		QueryWrapper<DkjkptDkkhglrgl> queryWrapper = QueryGenerator.initQueryWrapper(dkjkptDkkhglrgl, req.getParameterMap());
		Page<DkjkptDkkhglrgl> page = new Page<DkjkptDkkhglrgl>(pageNo, pageSize);
		IPage<DkjkptDkkhglrgl> pageList = dkjkptDkkhglrglService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param dkjkptDkkhglrgl
	 * @return
	 */
	@AutoLog(value = "贷款客户关联人管理-添加")
	@ApiOperation(value="贷款客户关联人管理-添加", notes="贷款客户关联人管理-添加")
	@PostMapping(value = "/add")
	public Result<DkjkptDkkhglrgl> add(@RequestBody DkjkptDkkhglrgl dkjkptDkkhglrgl) {
		Result<DkjkptDkkhglrgl> result = new Result<DkjkptDkkhglrgl>();
		try {
			dkjkptDkkhglrglService.save(dkjkptDkkhglrgl);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param dkjkptDkkhglrgl
	 * @return
	 */
	@AutoLog(value = "贷款客户关联人管理-编辑")
	@ApiOperation(value="贷款客户关联人管理-编辑", notes="贷款客户关联人管理-编辑")
	@PutMapping(value = "/edit")
	public Result<DkjkptDkkhglrgl> edit(@RequestBody DkjkptDkkhglrgl dkjkptDkkhglrgl) {
		Result<DkjkptDkkhglrgl> result = new Result<DkjkptDkkhglrgl>();
		UpdateWrapper<DkjkptDkkhglrgl> userUpdateWrapper = new UpdateWrapper<>();
		userUpdateWrapper.eq("jkrzjhm", dkjkptDkkhglrgl.getJkrzjhm()).eq("glrzjhm",dkjkptDkkhglrgl.getGlrzjhm());
		dkjkptDkkhglrglService.update(dkjkptDkkhglrgl,userUpdateWrapper);
		return result;
	}
	
	/**
	  *   通过id删除
	 * @return
	 */
	@PutMapping(value = "/delete")
	public Result<?> delete(@RequestBody JSONObject jsonObject ) {
		try {
			UpdateWrapper<DkjkptDkkhglrgl> userUpdateWrapper = new UpdateWrapper<>();
			userUpdateWrapper.eq("jkrzjhm", jsonObject.getString("jkrzjhm")).eq("glrzjhm",jsonObject.getString("glrzjhm"));
			dkjkptDkkhglrglService.remove(userUpdateWrapper);
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
	@AutoLog(value = "贷款客户关联人管理-批量删除")
	@ApiOperation(value="贷款客户关联人管理-批量删除", notes="贷款客户关联人管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<DkjkptDkkhglrgl> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<DkjkptDkkhglrgl> result = new Result<DkjkptDkkhglrgl>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.dkjkptDkkhglrglService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款客户关联人管理-通过id查询")
	@ApiOperation(value="贷款客户关联人管理-通过id查询", notes="贷款客户关联人管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<DkjkptDkkhglrgl> queryById(@RequestParam(name="id",required=true) String id) {
		Result<DkjkptDkkhglrgl> result = new Result<DkjkptDkkhglrgl>();
		DkjkptDkkhglrgl dkjkptDkkhglrgl = dkjkptDkkhglrglService.getById(id);
		if(dkjkptDkkhglrgl==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(dkjkptDkkhglrgl);
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
      QueryWrapper<DkjkptDkkhglrgl> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              DkjkptDkkhglrgl dkjkptDkkhglrgl = JSON.parseObject(deString, DkjkptDkkhglrgl.class);
              queryWrapper = QueryGenerator.initQueryWrapper(dkjkptDkkhglrgl, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<DkjkptDkkhglrgl> pageList = dkjkptDkkhglrglService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "贷款客户关联人管理列表");
      mv.addObject(NormalExcelConstants.CLASS, DkjkptDkkhglrgl.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("贷款客户关联人管理列表数据", "导出人:Jeecg", "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
  }

  /**
      * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   *//*
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<DkjkptDkkhglrgl> listDkjkptDkkhglrgls = ExcelImportUtil.importExcel(file.getInputStream(), DkjkptDkkhglrgl.class, params);
              dkjkptDkkhglrglService.saveBatch(listDkjkptDkkhglrgls);
              return Result.ok("文件导入成功！数据行数:" + listDkjkptDkkhglrgls.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
  }*/



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
		 mv.addObject(NormalExcelConstants.FILE_NAME, "贷款客户关联人信息导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, DkjkptDkkhglrglImport.class);
		 ExportParams exportParams = new ExportParams("贷款客户关联人信息导入模板", "模板信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<DkjkptDkkhglrglImport>());
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
		 for (String filePath : filePathList) {
			 filePath = uploadpath + File.separator + filePath;
			 File file = new File(filePath);
			 ImportParams params = new ImportParams();
			 params.setTitleRows(1);
			 params.setHeadRows(1);
			 params.setNeedSave(true);
			 try {
				 List<DkjkptDkkhglrgl> listDkkhglrgl = ExcelImportUtil.importExcel(file, DkjkptDkkhglrgl.class, params);
				 List<String> ids = new ArrayList<String>();

				 for (DkjkptDkkhglrgl dkjkptDkkhglrgl : listDkkhglrgl) {
				 	Map<String,String> parm = new HashMap<>();
					 parm.put("jkrzjhm",dkjkptDkkhglrgl.getJkrzjhm());
					 parm.put("glrzjhm",dkjkptDkkhglrgl.getGlrzjhm());
					 DkjkptDkkhglrgl Dkkhglrgl = dkjkptDkkhglrglService.queryByZjhm(parm);
					 if (Dkkhglrgl != null) {
						 ids.add(Dkkhglrgl.getJkrzjhm()+"-"+Dkkhglrgl.getGlrzjhm());
					 }
				 }
				 //先删除已经存在的数据
				 if (!ids.isEmpty()) {
					 for (String id : ids) {
						 String[] split = id.split("-");
						 String Jkrzjhm=split[0];
						 String Glrzjhm=split[1];
						 QueryWrapper<DkjkptDkkhglrgl> userUpdateWrapper=new QueryWrapper<>();
						 userUpdateWrapper.eq("jkrzjhm", Jkrzjhm).eq("glrzjhm",Glrzjhm);
						 dkjkptDkkhglrglService.remove(userUpdateWrapper);
					 }
				 }
				 dkjkptDkkhglrglService.saveBatch(listDkkhglrgl);

				 return Result.ok("文件导入成功！数据行数:" + listDkkhglrgl.size());
			 } catch (Exception e) {
				 log.error(e.getMessage(),e);
				 return Result.error("文件导入失败:"+e.getMessage());
			 }
		 }
//      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
//          MultipartFile file = entity.getValue();// 获取上传文件对象
//          ImportParams params = new ImportParams();
//          params.setTitleRows(2);
//          params.setHeadRows(1);
//          params.setNeedSave(true);
//          try {
//              List<Khhmc> listKhhmcs = ExcelImportUtil.importExcel(file.getInputStream(), Khhmc.class, params);
//              khhmcService.saveBatch(listKhhmcs);
//              return Result.ok("文件导入成功！数据行数:" + listKhhmcs.size());
//          } catch (Exception e) {
//              log.error(e.getMessage(),e);
//              return Result.error("文件导入失败:"+e.getMessage());
//          } finally {
//              try {
//                  file.getInputStream().close();
//              } catch (IOException e) {
//                  e.printStackTrace();
//              }
//          }
//      }
		 return Result.ok("文件导入失败！");
	 }



	 /**
	  *   提取
	  * @return
	  */
	 @RequestMapping(value = "/extract" , method = RequestMethod.PUT)
	 public Result<?> extract() {
		 try {
			 dkjkptDkkhglrglService.extract();
		 } catch (Exception e) {
			 log.error("提取失败",e.getMessage());
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功!");
	 }

}
