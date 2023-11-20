package org.cmms.modules.report.tzsjgl.xtnchzjrjgywzkb.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
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
import org.cmms.modules.report.tzsjgl.xtnchzjrjgywzkb.entity.RepTzglXtS41;
import org.cmms.modules.report.tzsjgl.xtnchzjrjgywzkb.entity.RepTzglXtS41Vo;
import org.cmms.modules.report.tzsjgl.xtnchzjrjgywzkb.service.IrepTzglXtS41Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.tzsjgl.xtpjdjp.entity.XtPjdjp;
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
 * @Description: 湘潭-S41农村合作金融机构业务状况表
 * @Author: jeecg-boot
 * @Date:   2023-02-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="湘潭-S41农村合作金融机构业务状况表")
@RestController
@RequestMapping("/xtnchzjrjgywzkb/repTzglXtS41")
public class RepTzglXtS41Controller extends JeecgController<RepTzglXtS41, IrepTzglXtS41Service> {
	@Autowired
	private IrepTzglXtS41Service repTzglXtS41Service;

	/**
	 * 分页列表查询
	 *
	 * @param repTzglXtS41
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "湘潭-S41农村合作金融机构业务状况表-分页列表查询")
	@ApiOperation(value="湘潭-S41农村合作金融机构业务状况表-分页列表查询", notes="湘潭-S41农村合作金融机构业务状况表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(RepTzglXtS41 repTzglXtS41,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RepTzglXtS41> queryWrapper = QueryGenerator.initQueryWrapper(repTzglXtS41, req.getParameterMap());
		Page<RepTzglXtS41> page = new Page<RepTzglXtS41>(pageNo, pageSize);
		IPage<RepTzglXtS41> pageList = repTzglXtS41Service.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param repTzglXtS41
	 * @return
	 */
	@AutoLog(value = "湘潭-S41农村合作金融机构业务状况表-添加")
	@ApiOperation(value="湘潭-S41农村合作金融机构业务状况表-添加", notes="湘潭-S41农村合作金融机构业务状况表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody RepTzglXtS41 repTzglXtS41) {
		repTzglXtS41Service.save(repTzglXtS41);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param repTzglXtS41
	 * @return
	 */
	@AutoLog(value = "湘潭-S41农村合作金融机构业务状况表-编辑")
	@ApiOperation(value="湘潭-S41农村合作金融机构业务状况表-编辑", notes="湘潭-S41农村合作金融机构业务状况表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody RepTzglXtS41 repTzglXtS41) {
		repTzglXtS41Service.updateById(repTzglXtS41);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "湘潭-S41农村合作金融机构业务状况表-通过id删除")
	@ApiOperation(value="湘潭-S41农村合作金融机构业务状况表-通过id删除", notes="湘潭-S41农村合作金融机构业务状况表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		repTzglXtS41Service.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "湘潭-S41农村合作金融机构业务状况表-批量删除")
	@ApiOperation(value="湘潭-S41农村合作金融机构业务状况表-批量删除", notes="湘潭-S41农村合作金融机构业务状况表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@Param("sjrq") String sjrq) {
		QueryWrapper<RepTzglXtS41> queryWrapper = new QueryWrapper<>();
		String rq = sjrq.replaceAll("-", "");
		queryWrapper.eq("sjrq",rq);
		this.repTzglXtS41Service.remove(queryWrapper);
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "湘潭-S41农村合作金融机构业务状况表-通过id查询")
	@ApiOperation(value="湘潭-S41农村合作金融机构业务状况表-通过id查询", notes="湘潭-S41农村合作金融机构业务状况表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		RepTzglXtS41 repTzglXtS41 = repTzglXtS41Service.getById(id);
		return Result.ok(repTzglXtS41);
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "S41农村合作金融机构业务状况表导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, RepTzglXtS41Vo.class);
		 ExportParams exportParams = new ExportParams("S41农村合作金融机构业务状况表导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
	 }

  /**
   * 导出excel
   *
   * @param request
   * @param repTzglXtS41
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, RepTzglXtS41 repTzglXtS41) {
      return super.exportXls(request, repTzglXtS41, RepTzglXtS41.class, "S41农村合作金融机构业务状况表");
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
		 String sjrq = request.getParameter("sjrq");
		 System.out.println(sjrq + "----sjrq----");
		 QueryWrapper<RepTzglXtS41> queryWrapper=new QueryWrapper<>();
		 queryWrapper.eq("sjrq",sjrq);
		 repTzglXtS41Service.remove(queryWrapper);
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
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<RepTzglXtS41> importResult = ExcelImportUtil.importExcelVerify(file, RepTzglXtS41.class,RepTzglXtS41Vo.class, params);
				 List<RepTzglXtS41> list = importResult.getList();
				 List<RepTzglXtS41> repTzglXtS41List = new ArrayList<>();
				 for (RepTzglXtS41 repTzglXtS41 : list) {
					 repTzglXtS41.setSjrq(sjrq);
					 repTzglXtS41List.add(repTzglXtS41);
				 }
				 service.saveBatch(repTzglXtS41List);
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
