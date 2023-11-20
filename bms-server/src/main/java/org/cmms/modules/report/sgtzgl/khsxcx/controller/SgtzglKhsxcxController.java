package org.cmms.modules.report.sgtzgl.khsxcx.controller;

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
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.report.sgtzgl.bhjymxb.entity.SgtzglBhjymxbVO;
import org.cmms.modules.report.sgtzgl.dkqmx.entity.SgtzglDkqmx;
import org.cmms.modules.report.sgtzgl.glfmc.entity.SgtzglGlfmc;
import org.cmms.modules.report.sgtzgl.khsxcx.entity.SgtzglKhsxcx;
import org.cmms.modules.report.sgtzgl.khsxcx.entity.SgtzglKhsxcxVO;
import org.cmms.modules.report.sgtzgl.khsxcx.service.ISgtzglKhsxcxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.khsxcx.verify.KhsxcxImportVerify;
import org.cmms.modules.sjxf.qtxt.wsyhxt.qykhjylsxx2.service.IQykhjylsxx2Service;
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
 * @Description: 客户授信查询
 * @Author: jeecg-boot
 * @Date:   2022-08-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户授信查询")
@RestController
@RequestMapping("/khsxcx/sgtzglKhsxcx")
public class SgtzglKhsxcxController extends JeecgController<SgtzglKhsxcx, ISgtzglKhsxcxService> {
	@Autowired
	private ISgtzglKhsxcxService sgtzglKhsxcxService;
	 @Autowired
	 private KhsxcxImportVerify khsxcxImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param sgtzglKhsxcx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户授信查询-分页列表查询")
	@ApiOperation(value="客户授信查询-分页列表查询", notes="客户授信查询-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgtzglKhsxcx sgtzglKhsxcx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgtzglKhsxcx> queryWrapper = QueryGenerator.initQueryWrapper(sgtzglKhsxcx, req.getParameterMap());
		//Page<SgtzglKhsxcx> page = new Page<SgtzglKhsxcx>(pageNo, pageSize);
		//IPage<SgtzglKhsxcx> pageList = sgtzglKhsxcxService.page(page, queryWrapper);
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(ISgtzglKhsxcxService.class,sgtzglKhsxcxService,pageNo,pageSize,queryWrapper,"fiscal_date","khh");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgtzglKhsxcx
	 * @return
	 */
	@AutoLog(value = "客户授信查询-添加")
	@ApiOperation(value="客户授信查询-添加", notes="客户授信查询-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgtzglKhsxcx sgtzglKhsxcx) {
		String fiscalDate = sgtzglKhsxcx.getFiscalDate().replace("-", "").substring(0,8);
		String sxksrq = sgtzglKhsxcx.getSxksrq().replace("-", "").substring(0,6)+"01";
		String sxdqrq = sgtzglKhsxcx.getSxdqrq().replace("-", "").substring(0,6)+"01";
		sgtzglKhsxcx.setFiscalDate(fiscalDate);
		sgtzglKhsxcx.setSxksrq(sxksrq);
		sgtzglKhsxcx.setSxdqrq(sxdqrq);
		//sgtzglKhsxcx.setId(UUIDGenerator.generate());
		//sgtzglKhsxcx.setCreateBy(getUsername());
		sgtzglKhsxcx.setCreateTime(new Date());
		sgtzglKhsxcxService.save(sgtzglKhsxcx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgtzglKhsxcx
	 * @return
	 */
	@AutoLog(value = "客户授信查询-编辑")
	@ApiOperation(value="客户授信查询-编辑", notes="客户授信查询-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgtzglKhsxcx sgtzglKhsxcx) {
		String fiscalDate = sgtzglKhsxcx.getFiscalDate().replace("-", "").substring(0,8);
		String sxksrq = sgtzglKhsxcx.getSxksrq().replace("-", "").substring(0,6)+"01";
		String sxdqrq = sgtzglKhsxcx.getSxdqrq().replace("-", "").substring(0,6)+"01";
		sgtzglKhsxcx.setFiscalDate(fiscalDate);
		sgtzglKhsxcx.setSxksrq(sxksrq);
		sgtzglKhsxcx.setSxdqrq(sxdqrq);
		//sgtzglKhsxcx.setCreateBy(getUsername());
		//sgtzglKhsxcx.setUpdateTime(new Date());
		sgtzglKhsxcxService.updateById(sgtzglKhsxcx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户授信查询-通过id删除")
	@ApiOperation(value="客户授信查询-通过id删除", notes="客户授信查询-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgtzglKhsxcxService.removeById(id);
		return Result.ok("删除成功!");
	}

	 /**
	  * 根据日期批量删除
	  * @param fiscalDate
	  * @return
	  */
	 @AutoLog(value = "客户授信查询-批量删除")
	 @ApiOperation(value="客户授信查询-批量删除", notes="客户授信查询-批量删除")
	 @DeleteMapping(value = "/deleteByBatch")
	 public Result<?> deleteByBatch(@Param("fiscalDate") String fiscalDate) {
		 QueryWrapper<SgtzglKhsxcx> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("fiscal_date",fiscalDate);
		 sgtzglKhsxcxService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户授信查询-批量删除")
	@ApiOperation(value="客户授信查询-批量删除", notes="客户授信查询-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgtzglKhsxcxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户授信查询-通过id查询")
	@ApiOperation(value="客户授信查询-通过id查询", notes="客户授信查询-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgtzglKhsxcx sgtzglKhsxcx = sgtzglKhsxcxService.getById(id);
		return Result.ok(sgtzglKhsxcx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgtzglKhsxcx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgtzglKhsxcx sgtzglKhsxcx) {
      return super.exportXls(request, sgtzglKhsxcx, SgtzglKhsxcx.class, "客户授信查询");
  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
/*  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, SgtzglKhsxcx.class);
  }*/


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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "客户授信查询导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgtzglKhsxcxVO.class);
		 ExportParams exportParams = new ExportParams("客户授信查询导入模板", "模板信息");
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
		 String filePaths = jsonObject.getString("filePaths");
		 if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
			 return Result.error("请先上传文件！");
		 }
		 String[] filePathList = filePaths.split(",");
		 JSONObject obj = new JSONObject();
		 for (String filePath : filePathList) {
			 String baseFilePath = uploadpath + File.separator + filePath;
//          MultipartFile file = entity.getValue();// 获取上传文件对象
			 File file = new File(baseFilePath);
			 ImportParams params = new ImportParams();
			 params.setTitleRows(1);
			 params.setHeadRows(1);
			 params.setNeedSave(false);
			 if (khsxcxImportVerify != null) {
				 params.setVerifyHanlder(khsxcxImportVerify);
			 }
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<SgtzglKhsxcx> importResult = ExcelImportUtil.importExcelVerify(file, SgtzglKhsxcx.class,SgtzglKhsxcxVO.class, params);
				 List<SgtzglKhsxcx> list = importResult.getList();
				 List<SgtzglKhsxcx> qkmbList = new ArrayList<>();
				 for (SgtzglKhsxcx qkmb : list) {
					 Boolean con1= StringUtils.isBlank(qkmb.getKhh()) || StringUtils.isBlank(qkmb.getSxdqrq()) || StringUtils.isBlank(qkmb.getSxksrq());
					 Boolean con2=StringUtils.isNotBlank(qkmb.getXh()) && (qkmb.getXh().contains("合计") || qkmb.getXh().contains("条数"));
					 if (con1 || con2 ) {
						 continue;
					 }
					 qkmb.setFiscalDate(fiscalDate);
					 //qkmb.setId(UUIDGenerator.generate());
					 //qkmb.setCreateBy(getUsername());
					 qkmb.setCreateTime(new Date());
					 qkmbList.add(qkmb);
				 }
				 service.saveBatch(qkmbList);
				 obj.put("filePath", filePath);
				 fos = new FileOutputStream(baseFilePath);
				 importResult.getWorkbook().write(fos);
				 fos.flush();
				 fos.close();
				 return Result.ok("文件导入完成！成功导入数据行数:" + qkmbList.size(), obj);
			 } catch (Exception e) {
				 log.error(e.getMessage(),e);
				 return Result.error("文件导入失败:"+e.getMessage());
			 } finally {
				 IoUtil.close(fos);
			 }
		 }
		 return Result.ok("文件导入失败！");
	 }

}
