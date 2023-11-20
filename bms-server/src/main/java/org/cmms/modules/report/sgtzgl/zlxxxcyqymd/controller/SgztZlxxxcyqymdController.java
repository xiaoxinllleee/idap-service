package org.cmms.modules.report.sgtzgl.zlxxxcyqymd.controller;

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
import org.checkerframework.checker.units.qual.A;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.gmgyqymd.entity.SgztGmgyqymd;
import org.cmms.modules.report.sgtzgl.zhsdhqktjb.service.ISgztZhsdhqktjbService;
import org.cmms.modules.report.sgtzgl.zlxxxcyqymd.entity.SgztZlxxxcyqymd;
import org.cmms.modules.report.sgtzgl.zlxxxcyqymd.service.ISgztZlxxxcyqymdService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.zlxxxcyqymd.verify.ZlxxxcyqymdImportVerify;
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
 * @Description: 战略性新兴产业企业名单
 * @Author: jeecg-boot
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="战略性新兴产业企业名单 ")
@RestController
@RequestMapping("/zlxxxcyqymd/sgztZlxxxcyqymd")
public class SgztZlxxxcyqymdController extends JeecgController<SgztZlxxxcyqymd, ISgztZlxxxcyqymdService> {
	@Autowired
	private ISgztZlxxxcyqymdService sgztZlxxxcyqymdService;
	@Autowired
	 private ZlxxxcyqymdImportVerify zlxxxcyqymdImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param sgztZlxxxcyqymd
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "战略性新兴产业企业名单 -分页列表查询")
	@ApiOperation(value="战略性新兴产业企业名单 -分页列表查询", notes="战略性新兴产业企业名单 -分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgztZlxxxcyqymd sgztZlxxxcyqymd,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgztZlxxxcyqymd> queryWrapper = QueryGenerator.initQueryWrapper(sgztZlxxxcyqymd, req.getParameterMap());
		Page<SgztZlxxxcyqymd> page = new Page<SgztZlxxxcyqymd>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgztZlxxxcyqymdService.class,sgztZlxxxcyqymdService,pageNo,pageSize,queryWrapper,"id");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgztZlxxxcyqymd
	 * @return
	 */
	@AutoLog(value = "战略性新兴产业企业名单 -添加")
	@ApiOperation(value="战略性新兴产业企业名单 -添加", notes="战略性新兴产业企业名单 -添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgztZlxxxcyqymd sgztZlxxxcyqymd) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		sgztZlxxxcyqymd.setId(UUIDGenerator.generate());
		sgztZlxxxcyqymd.setCreateBy(sysUser.getUsername());
		sgztZlxxxcyqymd.setCreateTime(new Date());
		sgztZlxxxcyqymdService.save(sgztZlxxxcyqymd);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgztZlxxxcyqymd
	 * @return
	 */
	@AutoLog(value = "战略性新兴产业企业名单 -编辑")
	@ApiOperation(value="战略性新兴产业企业名单 -编辑", notes="战略性新兴产业企业名单 -编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgztZlxxxcyqymd sgztZlxxxcyqymd) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		sgztZlxxxcyqymd.setUpdateBy(sysUser.getUsername());
		sgztZlxxxcyqymd.setUpdateTime(new Date());
		sgztZlxxxcyqymdService.updateById(sgztZlxxxcyqymd);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "战略性新兴产业企业名单 -通过id删除")
	@ApiOperation(value="战略性新兴产业企业名单 -通过id删除", notes="战略性新兴产业企业名单 -通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgztZlxxxcyqymdService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "战略性新兴产业企业名单 -批量删除")
	@ApiOperation(value="战略性新兴产业企业名单 -批量删除", notes="战略性新兴产业企业名单 -批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgztZlxxxcyqymdService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "战略性新兴产业企业名单 -通过id查询")
	@ApiOperation(value="战略性新兴产业企业名单 -通过id查询", notes="战略性新兴产业企业名单 -通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgztZlxxxcyqymd sgztZlxxxcyqymd = sgztZlxxxcyqymdService.getById(id);
		return Result.ok(sgztZlxxxcyqymd);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgztZlxxxcyqymd
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgztZlxxxcyqymd sgztZlxxxcyqymd) {
      return super.exportXls(request, sgztZlxxxcyqymd, SgztZlxxxcyqymd.class, "战略性新兴产业企业名单 ");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "战略性新兴产业企业名单导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgztZlxxxcyqymd.class);
		 ExportParams exportParams = new ExportParams("战略性新兴产业企业名单导入模板", "模板信息");
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
			 if (zlxxxcyqymdImportVerify != null) {
				 params.setVerifyHanlder(zlxxxcyqymdImportVerify);
			 }
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<SgztZlxxxcyqymd> importResult = ExcelImportUtil.importExcelVerify(file, SgztZlxxxcyqymd.class, params);
				 List<SgztZlxxxcyqymd> list = importResult.getList();
				 List<SgztZlxxxcyqymd> qkmbList = new ArrayList<>();
				 for (SgztZlxxxcyqymd ywzkb : list) {
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
