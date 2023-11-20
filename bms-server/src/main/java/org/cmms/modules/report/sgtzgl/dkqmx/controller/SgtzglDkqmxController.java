package org.cmms.modules.report.sgtzgl.dkqmx.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
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
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.cwbb.qkmb.entity.Qkmb;
import org.cmms.modules.report.sgtzgl.dkhxdjb.service.ISgtzglDkhxdjbService;
import org.cmms.modules.report.sgtzgl.dkqmx.entity.SgtzglDkqmx;
import org.cmms.modules.report.sgtzgl.dkqmx.entity.SgtzglDkqmxVO;
import org.cmms.modules.report.sgtzgl.dkqmx.service.ISgtzglDkqmxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.dkqmx.verify.SgtzglDkqmxImportVerify;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 贷款全明细
 * @Author: jeecg-boot
 * @Date:   2022-09-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款全明细")
@RestController
@RequestMapping("/dkqmx/sgtzglDkqmx")
public class SgtzglDkqmxController extends JeecgController<SgtzglDkqmx, ISgtzglDkqmxService> {
	@Autowired
	private ISgtzglDkqmxService sgtzglDkqmxService;
	@Autowired
	private SgtzglDkqmxImportVerify dkqmxImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param sgtzglDkqmx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款全明细-分页列表查询")
	@ApiOperation(value="贷款全明细-分页列表查询", notes="贷款全明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgtzglDkqmx sgtzglDkqmx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgtzglDkqmx> queryWrapper = QueryGenerator.initQueryWrapper(sgtzglDkqmx, req.getParameterMap());
		Page<SgtzglDkqmx> page = new Page<SgtzglDkqmx>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgtzglDkqmxService.class,sgtzglDkqmxService,pageNo,pageSize,queryWrapper,"fiscal_date","dkzh");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgtzglDkqmx
	 * @return
	 */
	@AutoLog(value = "贷款全明细-添加")
	@ApiOperation(value="贷款全明细-添加", notes="贷款全明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgtzglDkqmx sgtzglDkqmx) {
		String fiscalDate = sgtzglDkqmx.getFiscalDate().replace("-", "").substring(0,8);
		String dkrq = sgtzglDkqmx.getDkrq().replace("-", "").substring(0,8);
		String zqrq = sgtzglDkqmx.getZqrq().replace("-", "").substring(0,8);
		String dkdqr = sgtzglDkqmx.getDkdqr().replace("-", "").substring(0,8);
		String scjxr = sgtzglDkqmx.getScjxr().replace("-", "").substring(0,8);
		String yqrq = sgtzglDkqmx.getYqrq().replace("-", "").substring(0,8);
		sgtzglDkqmx.setFiscalDate(fiscalDate);
		sgtzglDkqmx.setDkrq(dkrq);
		sgtzglDkqmx.setZqrq(zqrq);
		sgtzglDkqmx.setDkdqr(dkdqr);
		sgtzglDkqmx.setScjxr(scjxr);
		sgtzglDkqmx.setYqrq(yqrq);
		sgtzglDkqmxService.save(sgtzglDkqmx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgtzglDkqmx
	 * @return
	 */
	@AutoLog(value = "贷款全明细-编辑")
	@ApiOperation(value="贷款全明细-编辑", notes="贷款全明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgtzglDkqmx sgtzglDkqmx) {
		String fiscalDate = sgtzglDkqmx.getFiscalDate().replace("-", "").substring(0,8);
		String dkrq = sgtzglDkqmx.getDkrq().replace("-", "").substring(0,8);
		String zqrq = sgtzglDkqmx.getZqrq().replace("-", "").substring(0,8);
		String dkdqr = sgtzglDkqmx.getDkdqr().replace("-", "").substring(0,8);
		String scjxr = sgtzglDkqmx.getScjxr().replace("-", "").substring(0,8);
		String yqrq = sgtzglDkqmx.getYqrq().replace("-", "").substring(0,8);
		sgtzglDkqmx.setFiscalDate(fiscalDate);
		sgtzglDkqmx.setDkrq(dkrq);
		sgtzglDkqmx.setZqrq(zqrq);
		sgtzglDkqmx.setDkdqr(dkdqr);
		sgtzglDkqmx.setScjxr(scjxr);
		sgtzglDkqmx.setYqrq(yqrq);
		sgtzglDkqmxService.updateById(sgtzglDkqmx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款全明细-通过id删除")
	@ApiOperation(value="贷款全明细-通过id删除", notes="贷款全明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgtzglDkqmxService.removeById(id);
		return Result.ok("删除成功!");
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
		 QueryWrapper<SgtzglDkqmx> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("fiscal_date",fiscalDate);
		 sgtzglDkqmxService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款全明细-批量删除")
	@ApiOperation(value="贷款全明细-批量删除", notes="贷款全明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgtzglDkqmxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款全明细-通过id查询")
	@ApiOperation(value="贷款全明细-通过id查询", notes="贷款全明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgtzglDkqmx sgtzglDkqmx = sgtzglDkqmxService.getById(id);
		return Result.ok(sgtzglDkqmx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgtzglDkqmx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgtzglDkqmx sgtzglDkqmx) {
      return super.exportXls(request, sgtzglDkqmx, SgtzglDkqmx.class, "贷款全明细");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "贷款全明细导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgtzglDkqmxVO.class);
		 ExportParams exportParams = new ExportParams("贷款全明细导入模板", "模板信息");
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
			 if (dkqmxImportVerify != null) {
				 params.setVerifyHanlder(dkqmxImportVerify);
			 }
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<SgtzglDkqmx> importResult = ExcelImportUtil.importExcelVerify(file, SgtzglDkqmx.class,SgtzglDkqmxVO.class, params);
				 List<SgtzglDkqmx> list = importResult.getList();
				 List<SgtzglDkqmx> qkmbList = new ArrayList<>();
				 for (SgtzglDkqmx qkmb : list) {
				 	Boolean con1=StringUtils.isBlank(qkmb.getDkzh());
				 	Boolean con2=StringUtils.isNotBlank(qkmb.getWdmc()) && (qkmb.getWdmc().contains("合计") || qkmb.getWdmc().contains("条数"));
				 	if (con1 || con2){
				 		continue;
					}
					 qkmb.setFiscalDate(fiscalDate);
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

