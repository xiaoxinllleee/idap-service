package org.cmms.modules.report.tzsjgl.xtnchzjrjgsyb.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import org.cmms.modules.report.tzsjgl.xtnchzjrjgsyb.entity.RepTzglXtS44;
import org.cmms.modules.report.tzsjgl.xtnchzjrjgsyb.entity.RepTzglXtS44Vo;
import org.cmms.modules.report.tzsjgl.xtnchzjrjgsyb.service.IrepTzglXtS44Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.report.tzsjgl.xtnchzjrjgywzkb.entity.RepTzglXtS41;
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
 * @Description: 湘潭-S44农村合作金融机构损益表
 * @Author: jeecg-boot
 * @Date:   2023-02-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="湘潭-S44农村合作金融机构损益表")
@RestController
@RequestMapping("/xtnchzjrjgsyb/repTzglXtS44")
public class RepTzglXtS44Controller extends JeecgController<RepTzglXtS44, IrepTzglXtS44Service> {
	@Autowired
	private IrepTzglXtS44Service repTzglXtS44Service;

	/**
	 * 分页列表查询
	 *
	 * @param repTzglXtS44
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "湘潭-S44农村合作金融机构损益表-分页列表查询")
	@ApiOperation(value="湘潭-S44农村合作金融机构损益表-分页列表查询", notes="湘潭-S44农村合作金融机构损益表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(RepTzglXtS44 repTzglXtS44,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RepTzglXtS44> queryWrapper = QueryGenerator.initQueryWrapper(repTzglXtS44, req.getParameterMap());
		Page<RepTzglXtS44> page = new Page<RepTzglXtS44>(pageNo, pageSize);
		IPage<RepTzglXtS44> pageList = repTzglXtS44Service.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param repTzglXtS44
	 * @return
	 */
	@AutoLog(value = "湘潭-S44农村合作金融机构损益表-添加")
	@ApiOperation(value="湘潭-S44农村合作金融机构损益表-添加", notes="湘潭-S44农村合作金融机构损益表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody RepTzglXtS44 repTzglXtS44) {
		repTzglXtS44Service.save(repTzglXtS44);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param repTzglXtS44
	 * @return
	 */
	@AutoLog(value = "湘潭-S44农村合作金融机构损益表-编辑")
	@ApiOperation(value="湘潭-S44农村合作金融机构损益表-编辑", notes="湘潭-S44农村合作金融机构损益表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody RepTzglXtS44 repTzglXtS44) {
		repTzglXtS44Service.updateById(repTzglXtS44);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "湘潭-S44农村合作金融机构损益表-通过id删除")
	@ApiOperation(value="湘潭-S44农村合作金融机构损益表-通过id删除", notes="湘潭-S44农村合作金融机构损益表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		repTzglXtS44Service.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "湘潭-S44农村合作金融机构损益表-批量删除")
	@ApiOperation(value="湘潭-S44农村合作金融机构损益表-批量删除", notes="湘潭-S44农村合作金融机构损益表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@Param("sjrq") String sjrq) {
		QueryWrapper<RepTzglXtS44> queryWrapper = new QueryWrapper<>();
		String rq = sjrq.replaceAll("-", "");
		queryWrapper.eq("sjrq",rq);
		this.repTzglXtS44Service.remove(queryWrapper);
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "湘潭-S44农村合作金融机构损益表-通过id查询")
	@ApiOperation(value="湘潭-S44农村合作金融机构损益表-通过id查询", notes="湘潭-S44农村合作金融机构损益表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		RepTzglXtS44 repTzglXtS44 = repTzglXtS44Service.getById(id);
		return Result.ok(repTzglXtS44);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param repTzglXtS44
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, RepTzglXtS44 repTzglXtS44) {
      return super.exportXls(request, repTzglXtS44, RepTzglXtS44.class, "S44农村合作金融机构损益表");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "S44农村合作金融机构损益表导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, RepTzglXtS44Vo.class);
		 ExportParams exportParams = new ExportParams("S44农村合作金融机构损益表导入模板", "模板信息");
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
		 String sjrq = request.getParameter("sjrq");
		 System.out.println(sjrq + "----sjrq----");
		 QueryWrapper<RepTzglXtS44> queryWrapper=new QueryWrapper<>();
		 queryWrapper.eq("sjrq",sjrq);
		 repTzglXtS44Service.remove(queryWrapper);
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
				 ExcelImportResult<RepTzglXtS44> importResult = ExcelImportUtil.importExcelVerify(file, RepTzglXtS44.class,RepTzglXtS44Vo.class, params);
				 List<RepTzglXtS44> list = importResult.getList();
				 List<RepTzglXtS44> repTzglXtS44List = new ArrayList<>();
				 for (RepTzglXtS44 repTzglXtS44 : list) {
					 repTzglXtS44.setSjrq(sjrq);
					 repTzglXtS44List.add(repTzglXtS44);
				 }
				 service.saveBatch(repTzglXtS44List);
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
