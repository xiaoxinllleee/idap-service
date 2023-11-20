package org.cmms.modules.khgl.sbklq.controller;

import java.io.File;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khgl.sbklq.entity.Sbklq;
import org.cmms.modules.khgl.sbklq.entity.SbklqImport;
import org.cmms.modules.khgl.sbklq.service.ISbklqService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-11-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags="1")
@RestController
@RequestMapping("/sbklq/sbklq")
public class SbklqController {
	@Autowired
	private ISbklqService sbklqService;

	 @Value(value = "${common.path.upload}")
	 private String uploadpath;
	/**
	  * 分页列表查询
	 * @param sbklq
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "1-分页列表查询")
	@ApiOperation(value="1-分页列表查询", notes="1-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Sbklq>> queryPageList(Sbklq sbklq,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Sbklq>> result = new Result<IPage<Sbklq>>();
		QueryWrapper<Sbklq> queryWrapper = QueryGenerator.initQueryWrapper(sbklq, req.getParameterMap());
		Page<Sbklq> page = new Page<Sbklq>(pageNo, pageSize);
		IPage<Sbklq> pageList = sbklqService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param sbklq
	 * @return
	 */
	@AutoLog(value = "1-添加")
	@ApiOperation(value="1-添加", notes="1-添加")
	@PostMapping(value = "/add")
	public Result<Sbklq> add(@RequestBody Sbklq sbklq) {
		Result<Sbklq> result = new Result<Sbklq>();
		try {
			sbklqService.save(sbklq);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param sbklq
	 * @return
	 */
	@AutoLog(value = "1-编辑")
	@ApiOperation(value="1-编辑", notes="1-编辑")
	@PutMapping(value = "/edit")
	public Result<Sbklq> edit(@RequestBody Sbklq sbklq) {
		Result<Sbklq> result = new Result<Sbklq>();
		QueryWrapper<Sbklq> userUpdateWrapper = new QueryWrapper<>();
		userUpdateWrapper.eq("zjhm", sbklq.getZjhm());
		Sbklq sbklqEntity = sbklqService.getOne(userUpdateWrapper);
		if(sbklqEntity==null) {
			result.error500("未找到对应实体");
		}else {
			UpdateWrapper<Sbklq> userUpdateWrapper1 = new UpdateWrapper<>();
			userUpdateWrapper1.eq("zjhm",sbklq.getZjhm());
			boolean ok = sbklqService.update(sbklq,userUpdateWrapper1);
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
	@AutoLog(value = "1-通过id删除")
	@ApiOperation(value="1-通过id删除", notes="1-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			UpdateWrapper<Sbklq> userUpdateWrapper = new UpdateWrapper<>();
			userUpdateWrapper.eq("zjhm", id);
			sbklqService.remove(userUpdateWrapper);
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
	@AutoLog(value = "1-批量删除")
	@ApiOperation(value="1-批量删除", notes="1-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<Sbklq> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Sbklq> result = new Result<Sbklq>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.sbklqService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "1-通过id查询")
	@ApiOperation(value="1-通过id查询", notes="1-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Sbklq> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Sbklq> result = new Result<Sbklq>();
		Sbklq sbklq = sbklqService.getById(id);
		if(sbklq==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(sbklq);
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
	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
      // Step.1 组装查询条件
      QueryWrapper<Sbklq> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Sbklq sbklq = JSON.parseObject(deString, Sbklq.class);
              queryWrapper = QueryGenerator.initQueryWrapper(sbklq, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Sbklq> pageList = sbklqService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "社保卡领取信息");
      mv.addObject(NormalExcelConstants.CLASS, Sbklq.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("社保卡领取信息数据", "导出人:"+sysUser.getRealname(), "导出社保卡领取信息信息"));
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
		 for (String filePath : filePathList) {
			 filePath = uploadpath + File.separator + filePath;
			 File file = new File(filePath);
			 ImportParams params = new ImportParams();
			 params.setTitleRows(1);
			 params.setHeadRows(1);
			 params.setNeedSave(true);
			 try {
				 List<Sbklq> listKhhmcs = ExcelImportUtil.importExcel(file, Sbklq.class, params);
				 List<String> ids = new ArrayList<String>();
				 List<Sbklq> insertList = new ArrayList<Sbklq>();
				 for (Sbklq khhmc : listKhhmcs) {
					 QueryWrapper<Sbklq> queryWrapper = new QueryWrapper<>();
					 queryWrapper.eq("zjhm", khhmc.getZjhm());
					 Sbklq user = sbklqService.getOne(queryWrapper);
					 if (user != null) {
						 ids.add(user.getZjhm());
					 }
				 }
				 //先删除已经存在的数据
				 if (!ids.isEmpty()) {
					 UpdateWrapper<Sbklq> userUpdateWrapper = new UpdateWrapper<>();
					 for (int i = 0; i < ids.size(); i++) {
						 userUpdateWrapper.eq("zjhm", ids.get(i));
						 sbklqService.remove(userUpdateWrapper);
					 }
				 }
				 for (Sbklq khhmc : listKhhmcs) {
				 	khhmc.setLqzt("1");
				 }
				 sbklqService.saveBatch(listKhhmcs);
				 return Result.ok("文件导入成功！数据行数:" + listKhhmcs.size());
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
		 mv.addObject(NormalExcelConstants.FILE_NAME, "社保卡领取信息导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, SbklqImport.class);
		 ExportParams exportParams = new ExportParams("社保卡领取信息导入模板", "模板信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<SbklqImport>());
		 return mv;
	 }
}
