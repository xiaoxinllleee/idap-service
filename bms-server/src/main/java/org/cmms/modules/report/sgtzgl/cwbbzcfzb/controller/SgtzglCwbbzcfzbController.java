package org.cmms.modules.report.sgtzgl.cwbbzcfzb.controller;

import java.io.*;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.cwbblrb.entity.SgztCwbblrb;
import org.cmms.modules.report.sgtzgl.cwbbywzkb.entity.SgtzCwbbywzkb;
import org.cmms.modules.report.sgtzgl.cwbbywzkbWzrmb.service.ISgtzCwbbywzkbWzrmbService;
import org.cmms.modules.report.sgtzgl.cwbbzcfzb.entity.SgtzglCwbbzcfzb;
import org.cmms.modules.report.sgtzgl.cwbbzcfzb.entity.SgtzglCwbbzcfzbVO;
import org.cmms.modules.report.sgtzgl.cwbbzcfzb.service.ISgtzglCwbbzcfzbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.cwbbzcfzb.verify.CwbbzcfzbImportVerify;
import org.cmms.modules.report.sgtzgl.cwbbzcfzbRmb.entity.SgtzCwxyszcfzbRmb;
import org.cmms.modules.ywgl.ywl.xjlljymgl.entity.Xjlljymgl;
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
 * @Description: 财务报表资产负债表
 * @Author: jeecg-boot
 * @Date:   2022-08-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="财务报表资产负债表")
@RestController
@RequestMapping("/cwbbzcfzb/sgtzglCwbbzcfzb")
public class SgtzglCwbbzcfzbController extends JeecgController<SgtzglCwbbzcfzb, ISgtzglCwbbzcfzbService> {
	@Autowired
	private ISgtzglCwbbzcfzbService sgtzglCwbbzcfzbService;
	 @Autowired
	 private CwbbzcfzbImportVerify cwbbzcfzbImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param sgtzglCwbbzcfzb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "财务报表资产负债表-分页列表查询")
	@ApiOperation(value="财务报表资产负债表-分页列表查询", notes="财务报表资产负债表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgtzglCwbbzcfzb sgtzglCwbbzcfzb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgtzglCwbbzcfzb> queryWrapper = QueryGenerator.initQueryWrapper(sgtzglCwbbzcfzb, req.getParameterMap());
		Page<SgtzglCwbbzcfzb> page = new Page<SgtzglCwbbzcfzb>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgtzglCwbbzcfzbService.class,sgtzglCwbbzcfzbService,pageNo,pageSize,queryWrapper,"sjrq");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgtzglCwbbzcfzb
	 * @return
	 */
	@AutoLog(value = "财务报表资产负债表-添加")
	@ApiOperation(value="财务报表资产负债表-添加", notes="财务报表资产负债表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgtzglCwbbzcfzb sgtzglCwbbzcfzb) {
		String sjrq = sgtzglCwbbzcfzb.getSjrq().replace("-", "").substring(0, 8);
		sgtzglCwbbzcfzb.setSjrq(sjrq);
		sgtzglCwbbzcfzb.setHc2(sgtzglCwbbzcfzb.getHc1());
		sgtzglCwbbzcfzb.setCreateTime(new Date());
		sgtzglCwbbzcfzbService.save(sgtzglCwbbzcfzb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgtzglCwbbzcfzb
	 * @return
	 */
	@AutoLog(value = "财务报表资产负债表-编辑")
	@ApiOperation(value="财务报表资产负债表-编辑", notes="财务报表资产负债表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgtzglCwbbzcfzb sgtzglCwbbzcfzb) {
		String sjrq = sgtzglCwbbzcfzb.getSjrq().replace("-", "").substring(0, 8);
		sgtzglCwbbzcfzb.setSjrq(sjrq);
		sgtzglCwbbzcfzb.setHc2(sgtzglCwbbzcfzb.getHc1());
		sgtzglCwbbzcfzbService.updateById(sgtzglCwbbzcfzb);
		return Result.ok("编辑成功!");
	}
	 /**
	  * 根据日期批量删除
	  *
	  * @param sjrq
	  * @return
	  */
	 @AutoLog(value = "财务报表资产负债表-批量删除")
	 @ApiOperation(value="财务报表资产负债表-批量删除", notes="财务报表资产负债表-批量删除")
	 @DeleteMapping(value = "/deleteByBatch")
	 public Result<?> deleteByBatch(@Param("sjrq") String sjrq) {
		 QueryWrapper<SgtzglCwbbzcfzb> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("sjrq",sjrq);
		 this.sgtzglCwbbzcfzbService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "财务报表资产负债表-通过id删除")
	@ApiOperation(value="财务报表资产负债表-通过id删除", notes="财务报表资产负债表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgtzglCwbbzcfzbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "财务报表资产负债表-批量删除")
	@ApiOperation(value="财务报表资产负债表-批量删除", notes="财务报表资产负债表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgtzglCwbbzcfzbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "财务报表资产负债表-通过id查询")
	@ApiOperation(value="财务报表资产负债表-通过id查询", notes="财务报表资产负债表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgtzglCwbbzcfzb sgtzglCwbbzcfzb = sgtzglCwbbzcfzbService.getById(id);
		return Result.ok(sgtzglCwbbzcfzb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgtzglCwbbzcfzb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgtzglCwbbzcfzb sgtzglCwbbzcfzb) {
      //return super.exportXls(request, sgtzglCwbbzcfzb, SgtzglCwbbzcfzb.class, "财务报表资产负债表");
	  String title1 = "财务报表资产负债表-本外币";
	  // Step.1 组装查询条件
	  QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(sgtzglCwbbzcfzb, request.getParameterMap());
	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	  String selections = request.getParameter("selections");
	  String rowKey = request.getParameter("rowKey");
	  //20211201 过滤选中数据
	  //20211201 BY LIUXIANGQUN 过滤选择的数据改为执行接sql  in  查询
	  if (oConvertUtils.isNotEmpty(selections)) {
		  List<String> selectionList = Arrays.asList(selections.split(","));
		  if (oConvertUtils.isNotEmpty(rowKey)) {
			  queryWrapper.in(rowKey, selectionList);
		  } else {
			  queryWrapper.in("ID", selectionList);
		  }
	  }

		  List<SgtzglCwbbzcfzb> exportList = service.list(queryWrapper);
		  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		  mv.addObject(NormalExcelConstants.FILE_NAME, title1); //此处设置的filename无效 ,前端会重更新设置一下
		  mv.addObject(NormalExcelConstants.CLASS, SgtzglCwbbzcfzb.class);
		  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title1 + "报表", "导出人:" + sysUser.getRealname(), title1));
		  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		  return mv;

  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   *//*
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, SgtzglCwbbzcfzb.class);
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "财务报表资产负债表导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgtzglCwbbzcfzbVO.class);
		 ExportParams exportParams = new ExportParams("财务报表资产负债表导入模板", "模板信息");
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
			 if (cwbbzcfzbImportVerify != null) {
				 params.setVerifyHanlder(cwbbzcfzbImportVerify);
			 }
			 FileOutputStream fos = null;
			 FileInputStream fis = null;
			 try {
				 fis = new FileInputStream(file);
				 boolean checkResult = ExcelImportCheckUtil.check(fis, SgtzglCwbbzcfzbVO.class, params);
				 ExcelImportResult<SgtzglCwbbzcfzb> importResult = ExcelImportUtil.importExcelVerify(file, SgtzglCwbbzcfzb.class,SgtzglCwbbzcfzbVO.class, params);
				 List<SgtzglCwbbzcfzb> list = importResult.getList();
				 List<SgtzglCwbbzcfzb> qkmbList = new ArrayList<>();
				 for (SgtzglCwbbzcfzb sgtzglCwbbzcfzb : list) {
				 	 sgtzglCwbbzcfzb.setSjrq(sjrq);
				 	 sgtzglCwbbzcfzb.setHc1(sgtzglCwbbzcfzb.getHc2());
					 sgtzglCwbbzcfzb.setCreateTime(new Date());
					 qkmbList.add(sgtzglCwbbzcfzb);
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
				 IoUtil.close(fis);
				 IoUtil.close(fos);
			 }
		 }
		 return Result.ok("文件导入失败！");
	 }

}
