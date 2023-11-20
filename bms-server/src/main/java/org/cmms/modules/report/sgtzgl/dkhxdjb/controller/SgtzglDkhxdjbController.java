package org.cmms.modules.report.sgtzgl.dkhxdjb.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.dkhsdjb.entity.SgtzglDkhsdjb;
import org.cmms.modules.report.sgtzgl.dkhsdjb.entity.SgtzglDkhsdjbVO;
import org.cmms.modules.report.sgtzgl.dkhsdjb.service.ISgtzglDkhsdjbService;
import org.cmms.modules.report.sgtzgl.dkhxdjb.entity.SgtzglDkhxdjb;
import org.cmms.modules.report.sgtzgl.dkhxdjb.entity.SgtzglDkhxdjbVO;
import org.cmms.modules.report.sgtzgl.dkhxdjb.service.ISgtzglDkhxdjbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.report.sgtzgl.dkhxdjb.verify.DkhxdjbImportVerify;
import org.cmms.modules.report.sgtzgl.dkqmx.entity.SgtzglDkqmx;
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
 * @Description: 贷款核销登记簿
 * @Author: jeecg-boot
 * @Date:   2022-09-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款核销登记簿")
@RestController
@RequestMapping("/dkhxdjb/sgtzglDkhxdjb")
public class SgtzglDkhxdjbController extends JeecgController<SgtzglDkhxdjb, ISgtzglDkhxdjbService> {
	@Autowired
	private ISgtzglDkhxdjbService sgtzglDkhxdjbService;
	@Autowired
	private DkhxdjbImportVerify dkhsdjbImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param sgtzglDkhxdjb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款核销登记簿-分页列表查询")
	@ApiOperation(value="贷款核销登记簿-分页列表查询", notes="贷款核销登记簿-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgtzglDkhxdjb sgtzglDkhxdjb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgtzglDkhxdjb> queryWrapper = QueryGenerator.initQueryWrapper(sgtzglDkhxdjb, req.getParameterMap());
		Page<SgtzglDkhxdjb> page = new Page<SgtzglDkhxdjb>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgtzglDkhxdjbService.class,sgtzglDkhxdjbService,pageNo,pageSize,queryWrapper,"fiscal_date","xh");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgtzglDkhxdjb
	 * @return
	 */
	@AutoLog(value = "贷款核销登记簿-添加")
	@ApiOperation(value="贷款核销登记簿-添加", notes="贷款核销登记簿-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgtzglDkhxdjb sgtzglDkhxdjb) {
		String fiscalDate = sgtzglDkhxdjb.getFiscalDate().replace("-", "").substring(0,8);
		String jkrq = sgtzglDkhxdjb.getJkrq().replace("-", "").substring(0,8);
		String dqrq = sgtzglDkhxdjb.getDqrq().replace("-", "").substring(0,8);
		String hxrq = sgtzglDkhxdjb.getHxrq().replace("-", "").substring(0,8);
		sgtzglDkhxdjb.setFiscalDate(fiscalDate);
		sgtzglDkhxdjb.setJkrq(jkrq);
		sgtzglDkhxdjb.setDqrq(dqrq);
		sgtzglDkhxdjb.setHxrq(hxrq);
		sgtzglDkhxdjb.setCreateTime(new Date());
		sgtzglDkhxdjbService.save(sgtzglDkhxdjb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgtzglDkhxdjb
	 * @return
	 */
	@AutoLog(value = "贷款核销登记簿-编辑")
	@ApiOperation(value="贷款核销登记簿-编辑", notes="贷款核销登记簿-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgtzglDkhxdjb sgtzglDkhxdjb) {
		String fiscalDate = sgtzglDkhxdjb.getFiscalDate().replace("-", "").substring(0,8);
		String jkrq = sgtzglDkhxdjb.getJkrq().replace("-", "").substring(0,8);
		String dqrq = sgtzglDkhxdjb.getDqrq().replace("-", "").substring(0,8);
		String hxrq = sgtzglDkhxdjb.getHxrq().replace("-", "").substring(0,8);
		sgtzglDkhxdjb.setFiscalDate(fiscalDate);
		sgtzglDkhxdjb.setJkrq(jkrq);
		sgtzglDkhxdjb.setDqrq(dqrq);
		sgtzglDkhxdjb.setHxrq(hxrq);
		sgtzglDkhxdjbService.updateById(sgtzglDkhxdjb);
		return Result.ok("编辑成功!");
	}

	 /**
	  * 根据日期批量删除
	  * @param fiscalDate
	  * @return
	  */
	 @AutoLog(value = "贷款核销登记簿-批量删除")
	 @ApiOperation(value="贷款核销登记簿-批量删除", notes="贷款核销登记簿-批量删除")
	 @DeleteMapping(value = "/deleteByBatch")
	 public Result<?> deleteByBatch(@Param("fiscalDate") String fiscalDate) {
		 QueryWrapper<SgtzglDkhxdjb> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("fiscal_date",fiscalDate);
		 sgtzglDkhxdjbService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款核销登记簿-通过id删除")
	@ApiOperation(value="贷款核销登记簿-通过id删除", notes="贷款核销登记簿-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgtzglDkhxdjbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款核销登记簿-批量删除")
	@ApiOperation(value="贷款核销登记簿-批量删除", notes="贷款核销登记簿-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgtzglDkhxdjbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款核销登记簿-通过id查询")
	@ApiOperation(value="贷款核销登记簿-通过id查询", notes="贷款核销登记簿-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgtzglDkhxdjb sgtzglDkhxdjb = sgtzglDkhxdjbService.getById(id);
		return Result.ok(sgtzglDkhxdjb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgtzglDkhxdjb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgtzglDkhxdjb sgtzglDkhxdjb) {
      return super.exportXls(request, sgtzglDkhxdjb, SgtzglDkhxdjb.class, "贷款核销登记簿");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "贷款核销登记簿导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgtzglDkhxdjbVO.class);
		 ExportParams exportParams = new ExportParams("贷款核销登记簿导入模板", "模板信息");
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
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<SgtzglDkhxdjb> importResult = ExcelImportUtil.importExcelVerify(file, SgtzglDkhxdjb.class,SgtzglDkhxdjbVO.class, params);
				 List<SgtzglDkhxdjb> list = importResult.getList();
				 List<SgtzglDkhxdjb> qkmbList = new ArrayList<>();
				 for (SgtzglDkhxdjb qkmb : list) {
					 Boolean con1= StringUtils.isBlank(qkmb.getDkzh());
					 Boolean con2=StringUtils.isNotBlank(qkmb.getXh()) && (qkmb.getXh().contains("合计") || qkmb.getXh().contains("条数"));
					 if (con1 || con2 ) {
						 continue;
					 }
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
