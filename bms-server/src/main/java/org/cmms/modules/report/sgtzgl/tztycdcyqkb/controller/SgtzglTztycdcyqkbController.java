package org.cmms.modules.report.sgtzgl.tztycdcyqkb.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.cfywmxb.entity.SgtzglCfywmxb;
import org.cmms.modules.report.sgtzgl.cfywmxb.entity.SgtzglCfywmxbVO;
import org.cmms.modules.report.sgtzgl.gmgyqymd.service.ISgztGmgyqymdService;
import org.cmms.modules.report.sgtzgl.tztycdcyqkb.entity.SgtzglTztycdcyqkb;
import org.cmms.modules.report.sgtzgl.tztycdcyqkb.entity.SgtzglTztycdcyqkbVO;
import org.cmms.modules.report.sgtzgl.tztycdcyqkb.service.ISgtzglTztycdcyqkbService;
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
 * @Description: 手工台账-投资同业存单持有情况表
 * @Author: jeecg-boot
 * @Date:   2023-08-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="手工台账-投资同业存单持有情况表")
@RestController
@RequestMapping("/Tztycdcyqkb/sgtzglTztycdcyqkb")
public class SgtzglTztycdcyqkbController extends JeecgController<SgtzglTztycdcyqkb, ISgtzglTztycdcyqkbService> {
	@Autowired
	private ISgtzglTztycdcyqkbService sgtzglTztycdcyqkbService;

	/**
	 * 分页列表查询
	 *
	 * @param sgtzglTztycdcyqkb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "手工台账-投资同业存单持有情况表-分页列表查询")
	@ApiOperation(value="手工台账-投资同业存单持有情况表-分页列表查询", notes="手工台账-投资同业存单持有情况表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgtzglTztycdcyqkb sgtzglTztycdcyqkb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgtzglTztycdcyqkb> queryWrapper = QueryGenerator.initQueryWrapper(sgtzglTztycdcyqkb, req.getParameterMap());
		Page<SgtzglTztycdcyqkb> page = new Page<SgtzglTztycdcyqkb>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgztGmgyqymdService.class,sgtzglTztycdcyqkbService,pageNo,pageSize,queryWrapper,"fiscal_date");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgtzglTztycdcyqkb
	 * @return
	 */
	@AutoLog(value = "手工台账-投资同业存单持有情况表-添加")
	@ApiOperation(value="手工台账-投资同业存单持有情况表-添加", notes="手工台账-投资同业存单持有情况表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgtzglTztycdcyqkb sgtzglTztycdcyqkb) {
		sgtzglTztycdcyqkbService.save(sgtzglTztycdcyqkb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgtzglTztycdcyqkb
	 * @return
	 */
	@AutoLog(value = "手工台账-投资同业存单持有情况表-编辑")
	@ApiOperation(value="手工台账-投资同业存单持有情况表-编辑", notes="手工台账-投资同业存单持有情况表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgtzglTztycdcyqkb sgtzglTztycdcyqkb) {
		sgtzglTztycdcyqkbService.updateById(sgtzglTztycdcyqkb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "手工台账-投资同业存单持有情况表-通过id删除")
	@ApiOperation(value="手工台账-投资同业存单持有情况表-通过id删除", notes="手工台账-投资同业存单持有情况表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgtzglTztycdcyqkbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "手工台账-投资同业存单持有情况表-批量删除")
	@ApiOperation(value="手工台账-投资同业存单持有情况表-批量删除", notes="手工台账-投资同业存单持有情况表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgtzglTztycdcyqkbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "手工台账-投资同业存单持有情况表-通过id查询")
	@ApiOperation(value="手工台账-投资同业存单持有情况表-通过id查询", notes="手工台账-投资同业存单持有情况表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgtzglTztycdcyqkb sgtzglTztycdcyqkb = sgtzglTztycdcyqkbService.getById(id);
		return Result.ok(sgtzglTztycdcyqkb);
	}

	 /**
	  * 根据日期批量删除
	  * @param fiscalDate
	  * @return
	  */
	 @DeleteMapping(value = "/deleteByBatch")
	 public Result<?> deleteByBatch(@Param("fiscalDate") String fiscalDate) {
		 QueryWrapper<SgtzglTztycdcyqkb> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("fiscal_date",fiscalDate);
		 sgtzglTztycdcyqkbService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "投资同业存单持有情况导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgtzglTztycdcyqkbVO.class);
		 ExportParams exportParams = new ExportParams("投资同业存单持有情况导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
	 }
  /**
   * 导出excel
   *
   * @param request
   * @param sgtzglTztycdcyqkb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgtzglTztycdcyqkb sgtzglTztycdcyqkb) {
      return super.exportXls(request, sgtzglTztycdcyqkb, SgtzglTztycdcyqkb.class, "手工台账-投资同业存单持有情况表");
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
			  ExcelImportResult<SgtzglTztycdcyqkb> importResult = ExcelImportUtil.importExcelVerify(file, SgtzglTztycdcyqkb.class, SgtzglTztycdcyqkbVO.class, params);
			  List<SgtzglTztycdcyqkb> list = importResult.getList();
			  List<SgtzglTztycdcyqkb> qkmbList = new ArrayList<>();
			  for (SgtzglTztycdcyqkb qkmb : list) {
				  Boolean con1= StringUtils.isBlank(qkmb.getXh()) || StringUtils.isBlank(qkmb.getZztz()) || StringUtils.isBlank(qkmb.getCpmc());
				  Boolean con2= StringUtils.isNotBlank(qkmb.getXh()) && (qkmb.getXh().contains("合计") || qkmb.getXh().contains("条数"));
				  if (con1 || con2 ) {
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
