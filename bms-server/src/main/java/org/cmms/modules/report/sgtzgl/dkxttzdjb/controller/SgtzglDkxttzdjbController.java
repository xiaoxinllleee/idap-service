package org.cmms.modules.report.sgtzgl.dkxttzdjb.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.dkhsdjb.entity.SgtzglDkhsdjb;
import org.cmms.modules.report.sgtzgl.dkhsdjb.entity.SgtzglDkhsdjbVO;
import org.cmms.modules.report.sgtzgl.dkqmx.entity.SgtzglDkqmx;
import org.cmms.modules.report.sgtzgl.dkqmx.service.ISgtzglDkqmxService;
import org.cmms.modules.report.sgtzgl.dkxttzdjb.entity.SgtzglDkxttzdjb;
import org.cmms.modules.report.sgtzgl.dkxttzdjb.entity.SgtzglDkxttzdjbVO;
import org.cmms.modules.report.sgtzgl.dkxttzdjb.service.ISgtzglDkxttzdjbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.dkxttzdjb.verify.DkxxtzdjbImportVerify;
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
 * @Description: 贷款形态调整登记簿
 * @Author: jeecg-boot
 * @Date:   2022-09-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款形态调整登记簿")
@RestController
@RequestMapping("/dkxttzdjb/sgtzglDkxttzdjb")
public class SgtzglDkxttzdjbController extends JeecgController<SgtzglDkxttzdjb, ISgtzglDkxttzdjbService> {
	@Autowired
	private ISgtzglDkxttzdjbService sgtzglDkxttzdjbService;
	@Autowired
	private DkxxtzdjbImportVerify dkxxtzdjbImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param sgtzglDkxttzdjb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款形态调整登记簿-分页列表查询")
	@ApiOperation(value="贷款形态调整登记簿-分页列表查询", notes="贷款形态调整登记簿-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgtzglDkxttzdjb sgtzglDkxttzdjb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgtzglDkxttzdjb> queryWrapper = QueryGenerator.initQueryWrapper(sgtzglDkxttzdjb, req.getParameterMap());
		Page<SgtzglDkxttzdjb> page = new Page<SgtzglDkxttzdjb>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgtzglDkxttzdjbService.class,sgtzglDkxttzdjbService,pageNo,pageSize,queryWrapper,"fiscal_date","xh");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgtzglDkxttzdjb
	 * @return
	 */
	@AutoLog(value = "贷款形态调整登记簿-添加")
	@ApiOperation(value="贷款形态调整登记簿-添加", notes="贷款形态调整登记簿-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgtzglDkxttzdjb sgtzglDkxttzdjb) {
		String fiscalDate = sgtzglDkxttzdjb.getFiscalDate().replace("-", "").substring(0,8);
		String jkrq = sgtzglDkxttzdjb.getJkrq().replace("-", "").substring(0,8);
		String dqrq = sgtzglDkxttzdjb.getDqrq().replace("-", "").substring(0,8);
		String tzrq = sgtzglDkxttzdjb.getTzrq().replace("-", "").substring(0,8);
		sgtzglDkxttzdjb.setFiscalDate(fiscalDate);
		sgtzglDkxttzdjb.setJkrq(jkrq);
		sgtzglDkxttzdjb.setDqrq(dqrq);
		sgtzglDkxttzdjb.setTzrq(tzrq);
		sgtzglDkxttzdjb.setCreateTime(new Date());
		sgtzglDkxttzdjbService.save(sgtzglDkxttzdjb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgtzglDkxttzdjb
	 * @return
	 */
	@AutoLog(value = "贷款形态调整登记簿-编辑")
	@ApiOperation(value="贷款形态调整登记簿-编辑", notes="贷款形态调整登记簿-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgtzglDkxttzdjb sgtzglDkxttzdjb) {
		String fiscalDate = sgtzglDkxttzdjb.getFiscalDate().replace("-", "").substring(0,8);
		String jkrq = sgtzglDkxttzdjb.getJkrq().replace("-", "").substring(0,8);
		String dqrq = sgtzglDkxttzdjb.getDqrq().replace("-", "").substring(0,8);
		String szrq = sgtzglDkxttzdjb.getTzrq().replace("-", "").substring(0,8);
		sgtzglDkxttzdjb.setFiscalDate(fiscalDate);
		sgtzglDkxttzdjb.setJkrq(jkrq);
		sgtzglDkxttzdjb.setDqrq(dqrq);
		sgtzglDkxttzdjb.setTzrq(szrq);
		sgtzglDkxttzdjbService.updateById(sgtzglDkxttzdjb);
		return Result.ok("编辑成功!");
	}
	 /**
	  * 根据日期批量删除
	  * @param fiscalDate
	  * @return
	  */
	 @AutoLog(value = "贷款形态调整登记簿-批量删除")
	 @ApiOperation(value="贷款形态调整登记簿-批量删除", notes="贷款形态调整登记簿-批量删除")
	 @DeleteMapping(value = "/deleteByBatch")
	 public Result<?> deleteByBatch(@Param("fiscalDate") String fiscalDate) {
		 QueryWrapper<SgtzglDkxttzdjb> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("fiscal_date",fiscalDate);
		 sgtzglDkxttzdjbService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款形态调整登记簿-通过id删除")
	@ApiOperation(value="贷款形态调整登记簿-通过id删除", notes="贷款形态调整登记簿-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgtzglDkxttzdjbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款形态调整登记簿-批量删除")
	@ApiOperation(value="贷款形态调整登记簿-批量删除", notes="贷款形态调整登记簿-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgtzglDkxttzdjbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款形态调整登记簿-通过id查询")
	@ApiOperation(value="贷款形态调整登记簿-通过id查询", notes="贷款形态调整登记簿-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgtzglDkxttzdjb sgtzglDkxttzdjb = sgtzglDkxttzdjbService.getById(id);
		return Result.ok(sgtzglDkxttzdjb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgtzglDkxttzdjb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgtzglDkxttzdjb sgtzglDkxttzdjb) {
      return super.exportXls(request, sgtzglDkxttzdjb, SgtzglDkxttzdjb.class, "贷款形态调整登记簿");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "贷款回收登记簿导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgtzglDkxttzdjbVO.class);
		 ExportParams exportParams = new ExportParams("贷款回收登记簿导入模板", "模板信息");
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
		 //return super.importExcelByTemplate(jsonObject, request, response, SgtzglDkxttzdjb.class,dkxxtzdjbImportVerify);
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
			 if (dkxxtzdjbImportVerify != null) {
				 params.setVerifyHanlder(dkxxtzdjbImportVerify);
			 }
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<SgtzglDkxttzdjb> importResult = ExcelImportUtil.importExcelVerify(file, SgtzglDkxttzdjb.class,SgtzglDkxttzdjbVO.class, params);
				 List<SgtzglDkxttzdjb> list = importResult.getList();
				 List<SgtzglDkxttzdjb> qkmbList = new ArrayList<>();
				 for (SgtzglDkxttzdjb qkmb : list) {
					 qkmb.setFiscalDate(fiscalDate);
					 qkmb.setCreateTime(new Date());
					 qkmbList.add(qkmb);
				 }
				 service.saveBatch(qkmbList);
				 obj.put("filePath", filePath);
				 fos = new FileOutputStream(baseFilePath);
				 importResult.getWorkbook().write(fos);
				 fos.flush();
				 fos.close();
				 return Result.ok("文件导入完成！成功导入数据行数:" + list.size(), obj);
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
