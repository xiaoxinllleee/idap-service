package org.cmms.modules.report.sgtzgl.hngxjsqymd.controller;

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
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.checkerframework.checker.units.qual.A;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.dkqmx.entity.SgtzglDkqmx;
import org.cmms.modules.report.sgtzgl.gmgyqymd.entity.SgztGmgyqymd;
import org.cmms.modules.report.sgtzgl.gmgyqymd.service.ISgztGmgyqymdService;
import org.cmms.modules.report.sgtzgl.hngxjsqymd.entity.SgztHngxjsqymd;
import org.cmms.modules.report.sgtzgl.hngxjsqymd.entity.SgztHngxjsqymdVO;
import org.cmms.modules.report.sgtzgl.hngxjsqymd.service.ISgztHngxjsqymdService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.hngxjsqymd.verify.HngxjsqymdImportVerify;
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
 * @Description: 湖南高新技术企业名单
 * @Author: jeecg-boot
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="湖南高新技术企业名单")
@RestController
@RequestMapping("/hngxjsqymd/sgztHngxjsqymd")
public class SgztHngxjsqymdController extends JeecgController<SgztHngxjsqymd, ISgztHngxjsqymdService> {
	@Autowired
	private ISgztHngxjsqymdService sgztHngxjsqymdService;
	@Autowired
	private HngxjsqymdImportVerify hngxjsqymdImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param sgztHngxjsqymd
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "湖南高新技术企业名单-分页列表查询")
	@ApiOperation(value="湖南高新技术企业名单-分页列表查询", notes="湖南高新技术企业名单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgztHngxjsqymd sgztHngxjsqymd,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgztHngxjsqymd> queryWrapper = QueryGenerator.initQueryWrapper(sgztHngxjsqymd, req.getParameterMap());
		Page<SgztHngxjsqymd> page = new Page<SgztHngxjsqymd>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgztHngxjsqymdService.class,sgztHngxjsqymdService,pageNo,pageSize,queryWrapper,"fiscal_date","qymc");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgztHngxjsqymd
	 * @return
	 */
	@AutoLog(value = "湖南高新技术企业名单-添加")
	@ApiOperation(value="湖南高新技术企业名单-添加", notes="湖南高新技术企业名单-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgztHngxjsqymd sgztHngxjsqymd) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		String fiscalDate = sgztHngxjsqymd.getFiscalDate().replace("-", "").substring(0,8);
		sgztHngxjsqymd.setFiscalDate(fiscalDate);
		sgztHngxjsqymd.setCreateTime(new Date());
		sgztHngxjsqymdService.save(sgztHngxjsqymd);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgztHngxjsqymd
	 * @return
	 */
	@AutoLog(value = "湖南高新技术企业名单-编辑")
	@ApiOperation(value="湖南高新技术企业名单-编辑", notes="湖南高新技术企业名单-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgztHngxjsqymd sgztHngxjsqymd) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		String fiscalDate = sgztHngxjsqymd.getFiscalDate().replace("-", "").substring(0,8);
		sgztHngxjsqymd.setFiscalDate(fiscalDate);
		sgztHngxjsqymdService.updateById(sgztHngxjsqymd);
		return Result.ok("编辑成功!");
	}
	 /**
	  * 根据日期批量删除
	  * @param fiscalDate
	  * @return
	  */
	 @AutoLog(value = "贷款全明细-批量删除")
	 @ApiOperation(value="贷款全明细-批量删除", notes="贷款全明细-批量删除")
	 @DeleteMapping(value = "/deleteByBatch")
	 public Result<?> deleteByBatch(@Param("fiscalDate") String fiscalDate) {
		 QueryWrapper<SgztHngxjsqymd> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("fiscal_date",fiscalDate);
		 sgztHngxjsqymdService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "湖南高新技术企业名单-通过id删除")
	@ApiOperation(value="湖南高新技术企业名单-通过id删除", notes="湖南高新技术企业名单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgztHngxjsqymdService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "湖南高新技术企业名单-批量删除")
	@ApiOperation(value="湖南高新技术企业名单-批量删除", notes="湖南高新技术企业名单-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgztHngxjsqymdService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "湖南高新技术企业名单-通过id查询")
	@ApiOperation(value="湖南高新技术企业名单-通过id查询", notes="湖南高新技术企业名单-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgztHngxjsqymd sgztHngxjsqymd = sgztHngxjsqymdService.getById(id);
		return Result.ok(sgztHngxjsqymd);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgztHngxjsqymd
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgztHngxjsqymd sgztHngxjsqymd) {
      return super.exportXls(request, sgztHngxjsqymd, SgztHngxjsqymd.class, "湖南高新技术企业名单");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "湖南高新技术企业名单导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgztHngxjsqymdVO.class);
		 ExportParams exportParams = new ExportParams("湖南高新技术企业名单导入模板", "模板信息");
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
		 String fiscalDate = request.getParameter("fiscalDate");
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
			 if (hngxjsqymdImportVerify != null) {
				 params.setVerifyHanlder(hngxjsqymdImportVerify);
			 }
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<SgztHngxjsqymd> importResult = ExcelImportUtil.importExcelVerify(file, SgztHngxjsqymd.class,SgztHngxjsqymdVO.class, params);
				 List<SgztHngxjsqymd> list = importResult.getList();
				 List<SgztHngxjsqymd> qkmbList = new ArrayList<>();
				 for (SgztHngxjsqymd ywzkb : list) {
					 Boolean con1= StringUtils.isBlank(ywzkb.getQymc());
					 Boolean con2= StringUtils.isNotBlank(ywzkb.getXh()) && (ywzkb.getXh().contains("合计") || ywzkb.getXh().contains("条数"));
					 if (con1 || con2 ) {
						 continue;
					 }
					 ywzkb.setFiscalDate(fiscalDate);
					 ywzkb.setCreateTime(new Date());
					 qkmbList.add(ywzkb);
				 }
				 service.saveBatch(qkmbList);
				 obj.put("filePath", filePath);
				 fos = new FileOutputStream(baseFilePath);
				 importResult.getWorkbook().write(fos);
				 fos.flush();
				 fos.close();
				 return Result.ok("文件导入完成！成功导入数据行数:" + qkmbList.size(), obj);
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
