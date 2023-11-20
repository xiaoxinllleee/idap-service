package org.cmms.modules.report.sgtzgl.dkqmxgtgsh.controller;

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
import org.cmms.modules.report.sgtzgl.dkqmxgtgsh.entity.SgtzglDkqmxGtgsh;
import org.cmms.modules.report.sgtzgl.dkqmxgtgsh.entity.SgtzglDkqmxGtgshVo;
import org.cmms.modules.report.sgtzgl.dkqmxgtgsh.service.ISgtzglDkqmxGtgshService;
import org.cmms.modules.report.sgtzgl.dkqmxgtgsh.verify.SgtzglDkqmxGtgshImportVerify;
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
@Api(tags="贷款全明细-个体工商户")
@RestController
@RequestMapping("/dkqmx/sgtzglDkqmxGtgsh")
public class SgtzglDkqmxGtgshController extends JeecgController<SgtzglDkqmxGtgsh, ISgtzglDkqmxGtgshService> {
	@Autowired
	private ISgtzglDkqmxGtgshService sgtzglDkqmxGtgshService;
	@Autowired
	private SgtzglDkqmxGtgshImportVerify dkqmxGtgshImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param sgtzglDkqmxGtgsh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款全明细-个体工商户-分页列表查询")
	@ApiOperation(value="贷款全明细-个体工商户-分页列表查询", notes="贷款全明细-个体工商户-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgtzglDkqmxGtgsh sgtzglDkqmxGtgsh,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgtzglDkqmxGtgsh> queryWrapper = QueryGenerator.initQueryWrapper(sgtzglDkqmxGtgsh, req.getParameterMap());
		Page<SgtzglDkqmxGtgsh> page = new Page<SgtzglDkqmxGtgsh>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgtzglDkqmxGtgshService.class,sgtzglDkqmxGtgshService,pageNo,pageSize,queryWrapper,"fiscal_date","dkzh");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgtzglDkqmxGtgsh
	 * @return
	 */
	@AutoLog(value = "贷款全明细-个体工商户-添加")
	@ApiOperation(value="贷款全明细-个体工商户-添加", notes="贷款全明细-个体工商户-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgtzglDkqmxGtgsh sgtzglDkqmxGtgsh) {
		String fiscalDate = sgtzglDkqmxGtgsh.getFiscalDate().replace("-", "").substring(0,8);
		String dkrq = sgtzglDkqmxGtgsh.getDkrq().replace("-", "").substring(0,8);
		String zqrq = sgtzglDkqmxGtgsh.getZqrq().replace("-", "").substring(0,8);
		String dkdqr = sgtzglDkqmxGtgsh.getDkdqr().replace("-", "").substring(0,8);
		String scjxr = sgtzglDkqmxGtgsh.getScjxr().replace("-", "").substring(0,8);
		String yqrq = sgtzglDkqmxGtgsh.getYqrq().replace("-", "").substring(0,8);
		sgtzglDkqmxGtgsh.setFiscalDate(fiscalDate);
		sgtzglDkqmxGtgsh.setDkrq(dkrq);
		sgtzglDkqmxGtgsh.setZqrq(zqrq);
		sgtzglDkqmxGtgsh.setDkdqr(dkdqr);
		sgtzglDkqmxGtgsh.setScjxr(scjxr);
		sgtzglDkqmxGtgsh.setYqrq(yqrq);
		sgtzglDkqmxGtgshService.save(sgtzglDkqmxGtgsh);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgtzglDkqmxGtgsh
	 * @return
	 */
	@AutoLog(value = "贷款全明细-个体工商户-编辑")
	@ApiOperation(value="贷款全明细-个体工商户-编辑", notes="贷款全明细-个体工商户-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgtzglDkqmxGtgsh sgtzglDkqmxGtgsh) {
		String fiscalDate = sgtzglDkqmxGtgsh.getFiscalDate().replace("-", "").substring(0,8);
		String dkrq = sgtzglDkqmxGtgsh.getDkrq().replace("-", "").substring(0,8);
		String zqrq = sgtzglDkqmxGtgsh.getZqrq().replace("-", "").substring(0,8);
		String dkdqr = sgtzglDkqmxGtgsh.getDkdqr().replace("-", "").substring(0,8);
		String scjxr = sgtzglDkqmxGtgsh.getScjxr().replace("-", "").substring(0,8);
		String yqrq = sgtzglDkqmxGtgsh.getYqrq().replace("-", "").substring(0,8);
		sgtzglDkqmxGtgsh.setFiscalDate(fiscalDate);
		sgtzglDkqmxGtgsh.setDkrq(dkrq);
		sgtzglDkqmxGtgsh.setZqrq(zqrq);
		sgtzglDkqmxGtgsh.setDkdqr(dkdqr);
		sgtzglDkqmxGtgsh.setScjxr(scjxr);
		sgtzglDkqmxGtgsh.setYqrq(yqrq);
		sgtzglDkqmxGtgshService.updateById(sgtzglDkqmxGtgsh);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款全明细-个体工商户-通过id删除")
	@ApiOperation(value="贷款全明细-个体工商户-通过id删除", notes="贷款全明细-个体工商户-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgtzglDkqmxGtgshService.removeById(id);
		return Result.ok("删除成功!");
	}

	 /**
	  * 根据日期批量删除
	  * @param fiscalDate
	  * @return
	  */
	 @AutoLog(value = "贷款全明细-个体工商户-批量删除")
	 @ApiOperation(value="贷款全明细-个体工商户-批量删除", notes="贷款全明细-个体工商户-批量删除")
	 @DeleteMapping(value = "/deleteByBatch")
	 public Result<?> deleteByBatch(@Param("fiscalDate") String fiscalDate) {
		 QueryWrapper<SgtzglDkqmxGtgsh> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("fiscal_date",fiscalDate);
		 sgtzglDkqmxGtgshService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款全明细-个体工商户-批量删除")
	@ApiOperation(value="贷款全明-个体工商户-批量删除", notes="贷款全明细-个体工商户-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgtzglDkqmxGtgshService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款全明细-个体工商户-通过id查询")
	@ApiOperation(value="贷款全明细-个体工商户-通过id查询", notes="贷款全明细-个体工商户-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgtzglDkqmxGtgsh sgtzglDkqmxGtgsh = sgtzglDkqmxGtgshService.getById(id);
		return Result.ok(sgtzglDkqmxGtgsh);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgtzglDkqmxGtgsh
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgtzglDkqmxGtgsh sgtzglDkqmxGtgsh) {
      return super.exportXls(request, sgtzglDkqmxGtgsh, SgtzglDkqmxGtgsh.class, "贷款全明细-个体工商户");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "贷款全明细-个体工商户导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgtzglDkqmxGtgshVo.class);
		 ExportParams exportParams = new ExportParams("贷款全明细-个体工商户导入模板", "模板信息");
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
			 if (dkqmxGtgshImportVerify != null) {
				 params.setVerifyHanlder(dkqmxGtgshImportVerify);
			 }
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<SgtzglDkqmxGtgsh> importResult = ExcelImportUtil.importExcelVerify(file, SgtzglDkqmxGtgsh.class,SgtzglDkqmxGtgshVo.class, params);
				 List<SgtzglDkqmxGtgsh> list = importResult.getList();
				 List<SgtzglDkqmxGtgsh> qkmbList = new ArrayList<>();
				 for (SgtzglDkqmxGtgsh qkmb : list) {
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

