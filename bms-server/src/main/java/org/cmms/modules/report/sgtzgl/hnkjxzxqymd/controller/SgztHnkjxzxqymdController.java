package org.cmms.modules.report.sgtzgl.hnkjxzxqymd.controller;

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
import org.cmms.modules.report.sgtzgl.hngxjsqymd.service.ISgztHngxjsqymdService;
import org.cmms.modules.report.sgtzgl.hnkjxzxqymd.entity.SgztHnkjxzxqymd;
import org.cmms.modules.report.sgtzgl.hnkjxzxqymd.entity.SgztHnkjxzxqymdVO;
import org.cmms.modules.report.sgtzgl.hnkjxzxqymd.service.ISgztHnkjxzxqymdService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.hnkjxzxqymd.verify.HnkjxzxqymdImportVerify;
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
 * @Description: 湖南科技型中小企业名单
 * @Author: jeecg-boot
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="湖南科技型中小企业名单")
@RestController
@RequestMapping("/hnkjxzxqymd/sgztHnkjxzxqymd")
public class SgztHnkjxzxqymdController extends JeecgController<SgztHnkjxzxqymd, ISgztHnkjxzxqymdService> {
	@Autowired
	private ISgztHnkjxzxqymdService sgztHnkjxzxqymdService;

	@Autowired
	private HnkjxzxqymdImportVerify hnkjxzxqymdImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param sgztHnkjxzxqymd
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "湖南科技型中小企业名单-分页列表查询")
	@ApiOperation(value="湖南科技型中小企业名单-分页列表查询", notes="湖南科技型中小企业名单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgztHnkjxzxqymd sgztHnkjxzxqymd,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgztHnkjxzxqymd> queryWrapper = QueryGenerator.initQueryWrapper(sgztHnkjxzxqymd, req.getParameterMap());
		Page<SgztHnkjxzxqymd> page = new Page<SgztHnkjxzxqymd>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgztHnkjxzxqymdService.class,sgztHnkjxzxqymdService,pageNo,pageSize,queryWrapper,"fiscal_date","qymc");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgztHnkjxzxqymd
	 * @return
	 */
	@AutoLog(value = "湖南科技型中小企业名单-添加")
	@ApiOperation(value="湖南科技型中小企业名单-添加", notes="湖南科技型中小企业名单-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgztHnkjxzxqymd sgztHnkjxzxqymd) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		String fiscalDate = sgztHnkjxzxqymd.getFiscalDate().replace("-", "").substring(0,8);
		sgztHnkjxzxqymd.setFiscalDate(fiscalDate);
		sgztHnkjxzxqymd.setCreateTime(new Date());
		sgztHnkjxzxqymdService.save(sgztHnkjxzxqymd);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgztHnkjxzxqymd
	 * @return
	 */
	@AutoLog(value = "湖南科技型中小企业名单-编辑")
	@ApiOperation(value="湖南科技型中小企业名单-编辑", notes="湖南科技型中小企业名单-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgztHnkjxzxqymd sgztHnkjxzxqymd) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		String fiscalDate = sgztHnkjxzxqymd.getFiscalDate().replace("-", "").substring(0,8);
		sgztHnkjxzxqymd.setFiscalDate(fiscalDate);
		sgztHnkjxzxqymdService.updateById(sgztHnkjxzxqymd);
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
		 QueryWrapper<SgztHnkjxzxqymd> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("fiscal_date",fiscalDate);
		 sgztHnkjxzxqymdService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "湖南科技型中小企业名单-通过id删除")
	@ApiOperation(value="湖南科技型中小企业名单-通过id删除", notes="湖南科技型中小企业名单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgztHnkjxzxqymdService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "湖南科技型中小企业名单-批量删除")
	@ApiOperation(value="湖南科技型中小企业名单-批量删除", notes="湖南科技型中小企业名单-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgztHnkjxzxqymdService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "湖南科技型中小企业名单-通过id查询")
	@ApiOperation(value="湖南科技型中小企业名单-通过id查询", notes="湖南科技型中小企业名单-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgztHnkjxzxqymd sgztHnkjxzxqymd = sgztHnkjxzxqymdService.getById(id);
		return Result.ok(sgztHnkjxzxqymd);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgztHnkjxzxqymd
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgztHnkjxzxqymd sgztHnkjxzxqymd) {
      return super.exportXls(request, sgztHnkjxzxqymd, SgztHnkjxzxqymd.class, "湖南科技型中小企业名单");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "湖南科技型中小企业名单导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgztHnkjxzxqymdVO.class);
		 ExportParams exportParams = new ExportParams("湖南科技型中小企业名单导入模板", "模板信息");
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
		 System.out.println(fiscalDate + "----sjrq----");
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
			 if (hnkjxzxqymdImportVerify != null) {
				 params.setVerifyHanlder(hnkjxzxqymdImportVerify);
			 }
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<SgztHnkjxzxqymd> importResult = ExcelImportUtil.importExcelVerify(file, SgztHnkjxzxqymd.class,SgztHnkjxzxqymdVO.class, params);
				 List<SgztHnkjxzxqymd> list = importResult.getList();
				 List<SgztHnkjxzxqymd> qkmbList = new ArrayList<>();
				 for (SgztHnkjxzxqymd ywzkb : list) {
					 Boolean con1= StringUtils.isBlank(ywzkb.getQymc());
					 Boolean con2=StringUtils.isNotBlank(ywzkb.getQymc()) && (ywzkb.getQymc().contains("合计") || ywzkb.getQymc().contains("条数"));
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
