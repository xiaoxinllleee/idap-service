package org.cmms.modules.report.sgtzgl.bhjymxb.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.bhjymxb.entity.SgtzglBhjymxb;
import org.cmms.modules.report.sgtzgl.bhjymxb.entity.SgtzglBhjymxbVO;
import org.cmms.modules.report.sgtzgl.bhjymxb.service.ISgtzglBhjymxbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.bhjymxb.verify.BhjymxbImportVerify;
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
 * @Description: 保函结余明细表
 * @Author: jeecg-boot
 * @Date:   2022-08-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="保函结余明细表")
@RestController
@RequestMapping("/bhjymxb/sgtzglBhjymxb")
public class SgtzglBhjymxbController extends JeecgController<SgtzglBhjymxb, ISgtzglBhjymxbService> {
	@Autowired
	private ISgtzglBhjymxbService sgtzglBhjymxbService;
	 @Autowired
	 private BhjymxbImportVerify bhjymxbImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param sgtzglBhjymxb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "保函结余明细表-分页列表查询")
	@ApiOperation(value="保函结余明细表-分页列表查询", notes="保函结余明细表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgtzglBhjymxb sgtzglBhjymxb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgtzglBhjymxb> queryWrapper = QueryGenerator.initQueryWrapper(sgtzglBhjymxb, req.getParameterMap());
		Page<SgtzglBhjymxb> page = new Page<SgtzglBhjymxb>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgtzglBhjymxbService.class,sgtzglBhjymxbService,pageNo,pageSize,queryWrapper,"fiscal_date","xh");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgtzglBhjymxb
	 * @return
	 */
	@AutoLog(value = "保函结余明细表-添加")
	@ApiOperation(value="保函结余明细表-添加", notes="保函结余明细表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgtzglBhjymxb sgtzglBhjymxb) {
		String fiscalDate = sgtzglBhjymxb.getFiscalDate().replace("-", "").substring(0,8);
		String cjrq = sgtzglBhjymxb.getCjrq().replace("-", "").substring(0,8);
		String sxrq = sgtzglBhjymxb.getSxrq().replace("-", "").substring(0,8);
		String dqrq = sgtzglBhjymxb.getDqrq().replace("-", "").substring(0,8);
		String jcrq = sgtzglBhjymxb.getJcrq().replace("-", "").substring(0,8);
		String thrq = sgtzglBhjymxb.getThrq().replace("-", "").substring(0,8);
		sgtzglBhjymxb.setFiscalDate(fiscalDate);
		sgtzglBhjymxb.setCjrq(cjrq);
		sgtzglBhjymxb.setSxrq(sxrq);
		sgtzglBhjymxb.setDqrq(dqrq);
		sgtzglBhjymxb.setJcrq(jcrq);
		sgtzglBhjymxb.setThrq(thrq);
		sgtzglBhjymxb.setCreateTime(new Date());
		sgtzglBhjymxbService.save(sgtzglBhjymxb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgtzglBhjymxb
	 * @return
	 */
	@AutoLog(value = "保函结余明细表-编辑")
	@ApiOperation(value="保函结余明细表-编辑", notes="保函结余明细表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgtzglBhjymxb sgtzglBhjymxb) {
		String fiscalDate = sgtzglBhjymxb.getFiscalDate().replace("-", "").substring(0,8);
		String cjrq = sgtzglBhjymxb.getCjrq().replace("-", "").substring(0,8);
		String sxrq = sgtzglBhjymxb.getSxrq().replace("-", "").substring(0,8);
		String dqrq = sgtzglBhjymxb.getDqrq().replace("-", "").substring(0,8);
		String jcrq = sgtzglBhjymxb.getJcrq().replace("-", "").substring(0,8);
		String thrq = sgtzglBhjymxb.getThrq().replace("-", "").substring(0,8);
		sgtzglBhjymxb.setFiscalDate(fiscalDate);
		sgtzglBhjymxb.setCjrq(cjrq);
		sgtzglBhjymxb.setSxrq(sxrq);
		sgtzglBhjymxb.setDqrq(dqrq);
		sgtzglBhjymxb.setJcrq(jcrq);
		sgtzglBhjymxb.setThrq(thrq);
		sgtzglBhjymxbService.updateById(sgtzglBhjymxb);
		return Result.ok("编辑成功!");
	}
	 /**
	  * 根据日期批量删除
	  * @param fiscalDate
	  * @return
	  */
	 @AutoLog(value = "保函结余明细表-批量删除")
	 @ApiOperation(value="保函结余明细表-批量删除", notes="保函结余明细表-批量删除")
	 @DeleteMapping(value = "/deleteByBatch")
	 public Result<?> deleteByBatch(@Param("fiscalDate") String fiscalDate) {
		 QueryWrapper<SgtzglBhjymxb> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("fiscal_date",fiscalDate);
		 sgtzglBhjymxbService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "保函结余明细表-通过id删除")
	@ApiOperation(value="保函结余明细表-通过id删除", notes="保函结余明细表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgtzglBhjymxbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "保函结余明细表-批量删除")
	@ApiOperation(value="保函结余明细表-批量删除", notes="保函结余明细表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgtzglBhjymxbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "保函结余明细表-通过id查询")
	@ApiOperation(value="保函结余明细表-通过id查询", notes="保函结余明细表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgtzglBhjymxb sgtzglBhjymxb = sgtzglBhjymxbService.getById(id);
		return Result.ok(sgtzglBhjymxb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgtzglBhjymxb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgtzglBhjymxb sgtzglBhjymxb) {
      return super.exportXls(request, sgtzglBhjymxb, SgtzglBhjymxb.class, "保函结余明细表");
  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
 /* @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, SgtzglBhjymxb.class);
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "保函结余明细表导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgtzglBhjymxbVO.class);
		 ExportParams exportParams = new ExportParams("保函结余明细表导入模板", "模板信息");
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
			 if (bhjymxbImportVerify != null) {
				 params.setVerifyHanlder(bhjymxbImportVerify);
			 }
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<SgtzglBhjymxb> importResult = ExcelImportUtil.importExcelVerify(file, SgtzglBhjymxb.class,SgtzglBhjymxbVO.class, params);
				 List<SgtzglBhjymxb> list = importResult.getList();
				 List<SgtzglBhjymxb> qkmbList = new ArrayList<>();
				 for (SgtzglBhjymxb qkmb : list) {
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
