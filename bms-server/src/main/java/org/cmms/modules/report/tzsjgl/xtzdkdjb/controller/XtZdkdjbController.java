package org.cmms.modules.report.tzsjgl.xtzdkdjb.controller;

import java.io.File;
import java.io.FileOutputStream;
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
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.report.tzsjgl.xtyhcdhptz.entity.XtYhcdhptz;
import org.cmms.modules.report.tzsjgl.xtzdkdjb.entity.XtZdkdjb;
import org.cmms.modules.report.tzsjgl.xtzdkdjb.service.IXtZdkdjbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.tzsjgl.xtzdkdjb.verify.XtZdkdjbImportVerify;
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
 * @Description: 再贷款登记簿
 * @Author: jeecg-boot
 * @Date:   2022-08-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="再贷款登记簿")
@RestController
@RequestMapping("/xtzdkdjb/xtZdkdjb")
public class XtZdkdjbController extends JeecgController<XtZdkdjb, IXtZdkdjbService> {
	@Autowired
	private IXtZdkdjbService xtZdkdjbService;
	@Autowired
	private XtZdkdjbImportVerify xtZdkdjbImportVerify;
	
	/**
	 * 分页列表查询
	 *
	 * @param xtZdkdjb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "再贷款登记簿-分页列表查询")
	@ApiOperation(value="再贷款登记簿-分页列表查询", notes="再贷款登记簿-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(XtZdkdjb xtZdkdjb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<XtZdkdjb> queryWrapper = QueryGenerator.initQueryWrapper(xtZdkdjb, req.getParameterMap());
		Page<XtZdkdjb> page = new Page<XtZdkdjb>(pageNo, pageSize);
		IPage<XtZdkdjb> pageList = xtZdkdjbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param xtZdkdjb
	 * @return
	 */
	@AutoLog(value = "再贷款登记簿-添加")
	@ApiOperation(value="再贷款登记簿-添加", notes="再贷款登记簿-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody XtZdkdjb xtZdkdjb) {
		xtZdkdjb.setId(UUIDGenerator.generate());
		xtZdkdjbService.save(xtZdkdjb);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param xtZdkdjb
	 * @return
	 */
	@AutoLog(value = "再贷款登记簿-编辑")
	@ApiOperation(value="再贷款登记簿-编辑", notes="再贷款登记簿-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody XtZdkdjb xtZdkdjb) {
		xtZdkdjbService.updateById(xtZdkdjb);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "再贷款登记簿-通过sjrq删除")
	@ApiOperation(value="再贷款登记簿-通过sjrq删除", notes="再贷款登记簿-通过sjrq删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="sjrq",required=true) String sjrq) {
		QueryWrapper<XtZdkdjb> queryWrapper = new QueryWrapper<>();
		DateTime parse = DateUtil.parse(sjrq);
		queryWrapper.eq("sjrq", parse);
		xtZdkdjbService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	 /**
	  * 通过id删除
	  */
	 @AutoLog(value = "再贷款登记簿-通过id删除")
	 @ApiOperation(value = "再贷款登记簿-通过id删除", notes = "再贷款登记簿-通过id删除")
	 @DeleteMapping(value = "/deleteById")
	 public Result<?> deleteById(@RequestParam(name = "id",required = true) String id){
		 xtZdkdjbService.removeById(id);
		 return Result.ok("删除成功!");
	 }
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "再贷款登记簿-批量删除")
	@ApiOperation(value="再贷款登记簿-批量删除", notes="再贷款登记簿-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.xtZdkdjbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "再贷款登记簿-通过id查询")
	@ApiOperation(value="再贷款登记簿-通过id查询", notes="再贷款登记簿-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		XtZdkdjb xtZdkdjb = xtZdkdjbService.getById(id);
		return Result.ok(xtZdkdjb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param xtZdkdjb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, XtZdkdjb xtZdkdjb) {
      return super.exportXls(request, xtZdkdjb, XtZdkdjb.class, "再贷款登记簿");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "再贷款登记簿导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, XtZdkdjb.class);
		 ExportParams exportParams = new ExportParams("再贷款登记簿导入模板", "模板信息");
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
		 Date parse = DateUtil.parse(sjrq);
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
			 if (xtZdkdjbImportVerify != null) {
				 params.setVerifyHanlder(xtZdkdjbImportVerify);
			 }
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<XtZdkdjb> importResult = ExcelImportUtil.importExcelVerify(file, XtZdkdjb.class, params);
				 List<XtZdkdjb> list = importResult.getList();
				 List<XtZdkdjb> qkmbList = new ArrayList<>();
				 for (XtZdkdjb ywzkb : list) {
					 ywzkb.setSjrq(parse);
					 ywzkb.setId(UUIDGenerator.generate());
					 qkmbList.add(ywzkb);
				 }
				 service.saveBatch(qkmbList);
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
