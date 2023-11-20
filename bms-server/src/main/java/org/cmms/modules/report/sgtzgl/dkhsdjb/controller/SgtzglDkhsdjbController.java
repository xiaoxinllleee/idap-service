package org.cmms.modules.report.sgtzgl.dkhsdjb.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.dkffdjb.service.ISgtzglDkffdjbService;
import org.cmms.modules.report.sgtzgl.dkhsdjb.entity.SgtzglDkhsdjb;
import org.cmms.modules.report.sgtzgl.dkhsdjb.entity.SgtzglDkhsdjbVO;
import org.cmms.modules.report.sgtzgl.dkhsdjb.service.ISgtzglDkhsdjbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.dkhsdjb.verify.DkhsdjbImportVerify;
import org.cmms.modules.report.sgtzgl.dkqmx.entity.SgtzglDkqmx;
import org.cmms.modules.report.sgtzgl.txdjb.entity.SgtzglTxdjb;
import org.cmms.modules.report.sgtzgl.txdjb.entity.SgtzglTxdjbVO;
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
 * @Description: 贷款回收登记簿
 * @Author: jeecg-boot
 * @Date:   2022-09-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款回收登记簿")
@RestController
@RequestMapping("/dkhsdjb/sgtzglDkhsdjb")
public class SgtzglDkhsdjbController extends JeecgController<SgtzglDkhsdjb, ISgtzglDkhsdjbService> {
	@Autowired
	private ISgtzglDkhsdjbService sgtzglDkhsdjbService;
	@Autowired
	private DkhsdjbImportVerify dkhsdjbImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param sgtzglDkhsdjb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款回收登记簿-分页列表查询")
	@ApiOperation(value="贷款回收登记簿-分页列表查询", notes="贷款回收登记簿-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgtzglDkhsdjb sgtzglDkhsdjb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgtzglDkhsdjb> queryWrapper = QueryGenerator.initQueryWrapper(sgtzglDkhsdjb, req.getParameterMap());
		Page<SgtzglDkhsdjb> page = new Page<SgtzglDkhsdjb>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgtzglDkhsdjbService.class,sgtzglDkhsdjbService,pageNo,pageSize,queryWrapper,"fiscal_date","xh");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgtzglDkhsdjb
	 * @return
	 */
	@AutoLog(value = "贷款回收登记簿-添加")
	@ApiOperation(value="贷款回收登记簿-添加", notes="贷款回收登记簿-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgtzglDkhsdjb sgtzglDkhsdjb) {
		String fiscalDate = sgtzglDkhsdjb.getFiscalDate().replace("-", "").substring(0,8);
		String dqrq = sgtzglDkhsdjb.getDqrq().replace("-", "").substring(0,8);
		String jkrq = sgtzglDkhsdjb.getJkrq().replace("-", "").substring(0,8);
		String shrq = sgtzglDkhsdjb.getShrq().replace("-", "").substring(0,8);
		sgtzglDkhsdjb.setFiscalDate(fiscalDate);
		sgtzglDkhsdjb.setDqrq(dqrq);
		sgtzglDkhsdjb.setJkrq(jkrq);
		sgtzglDkhsdjb.setShrq(shrq);
		sgtzglDkhsdjb.setId(UUIDGenerator.generate());
		sgtzglDkhsdjb.setCreateTime(new Date());
		sgtzglDkhsdjbService.save(sgtzglDkhsdjb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgtzglDkhsdjb
	 * @return
	 */
	@AutoLog(value = "贷款回收登记簿-编辑")
	@ApiOperation(value="贷款回收登记簿-编辑", notes="贷款回收登记簿-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgtzglDkhsdjb sgtzglDkhsdjb) {
		String fiscalDate = sgtzglDkhsdjb.getFiscalDate().replace("-", "").substring(0,8);
		String dqrq = sgtzglDkhsdjb.getDqrq().replace("-", "").substring(0,8);
		String jkrq = sgtzglDkhsdjb.getJkrq().replace("-", "").substring(0,8);
		String shrq = sgtzglDkhsdjb.getShrq().replace("-", "").substring(0,8);
		sgtzglDkhsdjb.setFiscalDate(fiscalDate);
		sgtzglDkhsdjb.setDqrq(dqrq);
		sgtzglDkhsdjb.setJkrq(jkrq);
		sgtzglDkhsdjb.setShrq(shrq);
		sgtzglDkhsdjbService.updateById(sgtzglDkhsdjb);
		return Result.ok("编辑成功!");
	}

	 /**
	  * 根据日期批量删除
	  * @param fiscalDate
	  * @return
	  */
	 @AutoLog(value = "贷款回收登记簿-批量删除")
	 @ApiOperation(value="贷款回收登记簿-批量删除", notes="贷款回收登记簿-批量删除")
	 @DeleteMapping(value = "/deleteByBatch")
	 public Result<?> deleteByBatch(@Param("fiscalDate") String fiscalDate) {
		 QueryWrapper<SgtzglDkhsdjb> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("fiscal_date",fiscalDate);
		 sgtzglDkhsdjbService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款回收登记簿-通过id删除")
	@ApiOperation(value="贷款回收登记簿-通过id删除", notes="贷款回收登记簿-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgtzglDkhsdjbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款回收登记簿-批量删除")
	@ApiOperation(value="贷款回收登记簿-批量删除", notes="贷款回收登记簿-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgtzglDkhsdjbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款回收登记簿-通过id查询")
	@ApiOperation(value="贷款回收登记簿-通过id查询", notes="贷款回收登记簿-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgtzglDkhsdjb sgtzglDkhsdjb = sgtzglDkhsdjbService.getById(id);
		return Result.ok(sgtzglDkhsdjb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgtzglDkhsdjb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgtzglDkhsdjb sgtzglDkhsdjb) {
      return super.exportXls(request, sgtzglDkhsdjb, SgtzglDkhsdjb.class, "贷款回收登记簿");
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
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgtzglDkhsdjbVO.class);
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
		 //return super.importExcelByTemplate(jsonObject, request, response, SgtzglDkhsdjb.class,dkhsdjbImportVerify);
		 String fiscalDate = request.getParameter("fiscalDate");
		 System.out.println(fiscalDate + "----sjrq----");
		 Date parse = DateUtil.parse(fiscalDate);
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
			 if (dkhsdjbImportVerify != null) {
				 params.setVerifyHanlder(dkhsdjbImportVerify);
			 }
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<SgtzglDkhsdjb> importResult = ExcelImportUtil.importExcelVerify(file, SgtzglDkhsdjb.class,SgtzglDkhsdjbVO.class, params);
				 List<SgtzglDkhsdjb> list = importResult.getList();
				 List<SgtzglDkhsdjb> qkmbList = new ArrayList<>();
				 for (SgtzglDkhsdjb qkmb : list) {
					 Boolean con1= StringUtils.isBlank(qkmb.getXh());
					 Boolean con2=StringUtils.isNotBlank(qkmb.getXh()) && (qkmb.getXh().contains("合计") || qkmb.getXh().contains("条数"));
					 if (con1 || con2 ) {
						 continue;
					 }
					 qkmb.setFiscalDate(fiscalDate);
					 qkmb.setId(UUIDGenerator.generate());
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
