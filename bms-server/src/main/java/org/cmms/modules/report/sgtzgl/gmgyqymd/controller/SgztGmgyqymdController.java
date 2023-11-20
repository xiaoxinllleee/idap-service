package org.cmms.modules.report.sgtzgl.gmgyqymd.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.dbpgldkdjb.entity.SgztDbpgldkdjb;
import org.cmms.modules.report.sgtzgl.glfmc.service.ISgtzglGlfmcService;
import org.cmms.modules.report.sgtzgl.gmgyqymd.entity.SgztGmgyqymd;
import org.cmms.modules.report.sgtzgl.gmgyqymd.service.ISgztGmgyqymdService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.gmgyqymd.verify.GmgyqymdImportVerify;
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
 * @Description: 规模工业企业名单
 * @Author: jeecg-boot
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="规模工业企业名单")
@RestController
@RequestMapping("/gmgyqymd/sgztGmgyqymd")
public class SgztGmgyqymdController extends JeecgController<SgztGmgyqymd, ISgztGmgyqymdService> {
	@Autowired
	private ISgztGmgyqymdService sgztGmgyqymdService;
	@Autowired
	private GmgyqymdImportVerify gmgyqymdImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param sgztGmgyqymd
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "规模工业企业名单-分页列表查询")
	@ApiOperation(value="规模工业企业名单-分页列表查询", notes="规模工业企业名单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgztGmgyqymd sgztGmgyqymd,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgztGmgyqymd> queryWrapper = QueryGenerator.initQueryWrapper(sgztGmgyqymd, req.getParameterMap());
		Page<SgztGmgyqymd> page = new Page<SgztGmgyqymd>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgztGmgyqymdService.class,sgztGmgyqymdService,pageNo,pageSize,queryWrapper,"qymc");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgztGmgyqymd
	 * @return
	 */
	@AutoLog(value = "规模工业企业名单-添加")
	@ApiOperation(value="规模工业企业名单-添加", notes="规模工业企业名单-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgztGmgyqymd sgztGmgyqymd) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		sgztGmgyqymd.setId(UUIDGenerator.generate());
		sgztGmgyqymd.setCreateBy(sysUser.getUsername());
		sgztGmgyqymd.setCreateTime(new Date());
		sgztGmgyqymdService.save(sgztGmgyqymd);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgztGmgyqymd
	 * @return
	 */
	@AutoLog(value = "规模工业企业名单-编辑")
	@ApiOperation(value="规模工业企业名单-编辑", notes="规模工业企业名单-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgztGmgyqymd sgztGmgyqymd) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		sgztGmgyqymd.setUpdateBy(sysUser.getUsername());
		sgztGmgyqymd.setUpdateTime(new Date());
		sgztGmgyqymdService.updateById(sgztGmgyqymd);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "规模工业企业名单-通过id删除")
	@ApiOperation(value="规模工业企业名单-通过id删除", notes="规模工业企业名单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgztGmgyqymdService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "规模工业企业名单-批量删除")
	@ApiOperation(value="规模工业企业名单-批量删除", notes="规模工业企业名单-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgztGmgyqymdService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "规模工业企业名单-通过id查询")
	@ApiOperation(value="规模工业企业名单-通过id查询", notes="规模工业企业名单-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgztGmgyqymd sgztGmgyqymd = sgztGmgyqymdService.getById(id);
		return Result.ok(sgztGmgyqymd);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgztGmgyqymd
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgztGmgyqymd sgztGmgyqymd) {
      return super.exportXls(request, sgztGmgyqymd, SgztGmgyqymd.class, "规模工业企业名单");
  }

	 /**
	  * 导入模板
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "规模工业企业名单导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgztGmgyqymd.class);
		 ExportParams exportParams = new ExportParams("规模工业企业名单导入模板", "模板信息");
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
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String filePaths = jsonObject.getString("filePaths");
		 if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
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
			 if (gmgyqymdImportVerify != null) {
				 params.setVerifyHanlder(gmgyqymdImportVerify);
			 }
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<SgztGmgyqymd> importResult = ExcelImportUtil.importExcelVerify(file, SgztGmgyqymd.class, params);
				 List<SgztGmgyqymd> list = importResult.getList();
				 List<SgztGmgyqymd> qkmbList = new ArrayList<>();
				 for (SgztGmgyqymd ywzkb : list) {
					 ywzkb.setId(UUIDGenerator.generate());
					 ywzkb.setCreateTime(new Date());
					 ywzkb.setCreateBy(sysUser.getUsername());
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
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败:" + e.getMessage());
			 } finally {
				 IoUtil.close(fos);
			 }
		 }
		 return Result.ok("文件导入失败！");
	 }

}
