package org.cmms.modules.report.tzsjgl.xtpjdjp.controller;

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
import org.cmms.modules.report.tzsjgl.xtpjdjp.entity.XtPjdjp;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.tzsjgl.xtpjdjp.service.IXtPjdjpService;
import org.cmms.modules.report.tzsjgl.xtpjdjp.verify.XtPjdjpImportVerify;
import org.cmms.modules.report.tzsjgl.xtyhcdhptz.entity.XtYhcdhptz;
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
 * @Description: 票据登记蒲
 * @Author: jeecg-boot
 * @Date:   2022-08-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="票据登记蒲")
@RestController
@RequestMapping("/xtpjdjp/xtPjdjp")
public class XtPjdjpController extends JeecgController<XtPjdjp, IXtPjdjpService> {
	@Autowired
	private IXtPjdjpService xtPjdjpService;
	@Autowired
	private XtPjdjpImportVerify xtPjdjpImportVerify;
	
	/**
	 * 分页列表查询
	 *
	 * @param xtPjdjp
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "票据登记蒲-分页列表查询")
	@ApiOperation(value="票据登记蒲-分页列表查询", notes="票据登记蒲-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(XtPjdjp xtPjdjp,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<XtPjdjp> queryWrapper = QueryGenerator.initQueryWrapper(xtPjdjp, req.getParameterMap());
		Page<XtPjdjp> page = new Page<XtPjdjp>(pageNo, pageSize);
		IPage<XtPjdjp> pageList = xtPjdjpService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param xtPjdjp
	 * @return
	 */
	@AutoLog(value = "票据登记蒲-添加")
	@ApiOperation(value="票据登记蒲-添加", notes="票据登记蒲-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody XtPjdjp xtPjdjp) {
		xtPjdjp.setId(UUIDGenerator.generate());
		xtPjdjpService.save(xtPjdjp);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param xtPjdjp
	 * @return
	 */
	@AutoLog(value = "票据登记蒲-编辑")
	@ApiOperation(value="票据登记蒲-编辑", notes="票据登记蒲-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody XtPjdjp xtPjdjp) {
		xtPjdjpService.updateById(xtPjdjp);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "票据登记蒲-通过sjrq删除")
	@ApiOperation(value="票据登记蒲-通过sjrq删除", notes="票据登记蒲-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="sjrq",required=true) String sjrq) {
		QueryWrapper<XtPjdjp> queryWrapper = new QueryWrapper<>();
		DateTime parse = DateUtil.parse(sjrq);
		queryWrapper.eq("sjrq", parse);
		xtPjdjpService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	 /**
	  * 通过id删除
	  */
	 @AutoLog(value = "票据登记蒲-通过id删除")
	 @ApiOperation(value = "票据登记蒲-通过id删除", notes = "票据登记蒲-通过id删除")
	 @DeleteMapping(value = "/deleteById")
	 public Result<?> deleteById(@RequestParam(name = "id",required = true) String id){
		 xtPjdjpService.removeById(id);
		 return Result.ok("删除成功!");
	 }
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "票据登记蒲-批量删除")
	@ApiOperation(value="票据登记蒲-批量删除", notes="票据登记蒲-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.xtPjdjpService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "票据登记蒲-通过id查询")
	@ApiOperation(value="票据登记蒲-通过id查询", notes="票据登记蒲-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		XtPjdjp xtPjdjp = xtPjdjpService.getById(id);
		return Result.ok(xtPjdjp);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param xtPjdjp
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, XtPjdjp xtPjdjp) {
      return super.exportXls(request, xtPjdjp, XtPjdjp.class, "票据登记蒲");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "票据登记蒲导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, XtPjdjp.class);
		 ExportParams exportParams = new ExportParams("票据登记蒲导入模板", "模板信息");
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
			 if (xtPjdjpImportVerify != null) {
				 params.setVerifyHanlder(xtPjdjpImportVerify);
			 }
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<XtPjdjp> importResult = ExcelImportUtil.importExcelVerify(file, XtPjdjp.class, params);
				 List<XtPjdjp> list = importResult.getList();
				 List<XtPjdjp> qkmbList = new ArrayList<>();
				 for (XtPjdjp ywzkb : list) {
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
