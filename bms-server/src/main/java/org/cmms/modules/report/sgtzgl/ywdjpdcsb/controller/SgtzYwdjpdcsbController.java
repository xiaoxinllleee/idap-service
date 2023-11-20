package org.cmms.modules.report.sgtzgl.ywdjpdcsb.controller;

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
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.dkqmxQydk.entity.SgtzglDkqmxQydk;
import org.cmms.modules.report.sgtzgl.dkqmxQydk.service.ISgtzglDkqmxQydkService;
import org.cmms.modules.report.sgtzgl.ywdjpdcsb.entity.SgtzYwdjpdcsb;
import org.cmms.modules.report.sgtzgl.ywdjpdcsb.entity.SgtzYwdjpdcsbVo;
import org.cmms.modules.report.sgtzgl.ywdjpdcsb.service.ISgtzYwdjpdcsbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.ywdjpdcsb.verify.SgtzglYwdjpdcsbImportVerify;
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
 * @Description: 业务等级评定参数表
 * @Author: jeecg-boot
 * @Date:   2022-12-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="业务等级评定参数表")
@RestController
@RequestMapping("/ywdjpdcsb/sgtzYwdjpdcsb")
public class SgtzYwdjpdcsbController extends JeecgController<SgtzYwdjpdcsb, ISgtzYwdjpdcsbService> {
	@Autowired
	private ISgtzYwdjpdcsbService sgtzYwdjpdcsbService;
	@Autowired
	private SgtzglYwdjpdcsbImportVerify sgtzglYwdjpdcsbImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param sgtzYwdjpdcsb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "业务等级评定参数表-分页列表查询")
	@ApiOperation(value="业务等级评定参数表-分页列表查询", notes="业务等级评定参数表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgtzYwdjpdcsb sgtzYwdjpdcsb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgtzYwdjpdcsb> queryWrapper = QueryGenerator.initQueryWrapper(sgtzYwdjpdcsb, req.getParameterMap());
		Page<SgtzYwdjpdcsb> page = new Page<SgtzYwdjpdcsb>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgtzYwdjpdcsbService.class,sgtzYwdjpdcsbService,pageNo,pageSize,queryWrapper,"sjrq","xmdh");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgtzYwdjpdcsb
	 * @return
	 */
	@AutoLog(value = "业务等级评定参数表-添加")
	@ApiOperation(value="业务等级评定参数表-添加", notes="业务等级评定参数表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgtzYwdjpdcsb sgtzYwdjpdcsb) {
		sgtzYwdjpdcsbService.save(sgtzYwdjpdcsb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgtzYwdjpdcsb
	 * @return
	 */
	@AutoLog(value = "业务等级评定参数表-编辑")
	@ApiOperation(value="业务等级评定参数表-编辑", notes="业务等级评定参数表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgtzYwdjpdcsb sgtzYwdjpdcsb) {
		QueryWrapper<SgtzYwdjpdcsb> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("sjrq",sgtzYwdjpdcsb.getSjrq()).eq("xmdh",sgtzYwdjpdcsb.getXmdh());
		sgtzYwdjpdcsbService.update(sgtzYwdjpdcsb,queryWrapper);
		return Result.ok("编辑成功!");
	}
	 /**
	  * 通过id删除
	  *
	  * @param
	  * @return
	  */
	 @AutoLog(value = "业务等级评定参数表-通过id删除")
	 @ApiOperation(value="业务等级评定参数表-通过id删除", notes="业务等级评定参数表-通过id删除")
	 @DeleteMapping(value = "/deleteByBatch")
	 public Result<?> deleteByBatch(@Param("sjrq") String sjrq) {
		 QueryWrapper<SgtzYwdjpdcsb> queryWrapper=new QueryWrapper<>();
		 queryWrapper.eq("sjrq",sjrq);
		 sgtzYwdjpdcsbService.remove(queryWrapper);
		 return Result.ok("删除成功!");
	 }
	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "业务等级评定参数表-通过id删除")
	@ApiOperation(value="业务等级评定参数表-通过id删除", notes="业务等级评定参数表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="sjrq",required=true) String sjrq,@RequestParam(name = "xmdh",required = true) String xmdh) {
		QueryWrapper<SgtzYwdjpdcsb> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("sjrq",sjrq).eq("xmdh",xmdh);
		sgtzYwdjpdcsbService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "业务等级评定参数表-批量删除")
	@ApiOperation(value="业务等级评定参数表-批量删除", notes="业务等级评定参数表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgtzYwdjpdcsbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "业务等级评定参数表-通过id查询")
	@ApiOperation(value="业务等级评定参数表-通过id查询", notes="业务等级评定参数表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgtzYwdjpdcsb sgtzYwdjpdcsb = sgtzYwdjpdcsbService.getById(id);
		return Result.ok(sgtzYwdjpdcsb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgtzYwdjpdcsb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgtzYwdjpdcsb sgtzYwdjpdcsb) {
      return super.exportXls(request, sgtzYwdjpdcsb, SgtzYwdjpdcsb.class, "业务等级评定参数表");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "业务等级评定参数表导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgtzYwdjpdcsbVo.class);
		 ExportParams exportParams = new ExportParams("业务等级评定参数表导入模板", "模板信息");
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
		  if (sgtzglYwdjpdcsbImportVerify != null) {
			  params.setVerifyHanlder(sgtzglYwdjpdcsbImportVerify);
		  }
		  FileOutputStream fos = null;
		  try {
			  ExcelImportResult<SgtzYwdjpdcsb> importResult = ExcelImportUtil.importExcelVerify(file, SgtzYwdjpdcsb.class,SgtzYwdjpdcsbVo.class, params);
			  List<SgtzYwdjpdcsb> list = importResult.getList();
			  List<SgtzYwdjpdcsb> qkmbList = new ArrayList<>();
			  for (SgtzYwdjpdcsb qkmb : list) {
			  	log.info(qkmb.getSjrq()+"===业务等级评定参数表导入数据日期===");
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


  /*@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, SgtzYwdjpdcsb.class);
  }*/

}
