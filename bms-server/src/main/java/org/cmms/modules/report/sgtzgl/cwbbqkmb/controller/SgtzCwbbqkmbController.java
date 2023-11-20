package org.cmms.modules.report.sgtzgl.cwbbqkmb.controller;

import java.io.*;
import java.util.*;
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
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.cwbbqkmb.entity.SgtzCwbbqkmb;
import org.cmms.modules.report.sgtzgl.cwbbqkmb.entity.SgtzCwbbqkmbVO;
import org.cmms.modules.report.sgtzgl.cwbbqkmb.service.ISgtzCwbbqkmbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.cwbbqkmb.verify.CwbbqkmbImportVerify;
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
 * @Description: 财务报表全科目表
 * @Author: jeecg-boot
 * @Date:   2022-10-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="财务报表全科目表")
@RestController
@RequestMapping("/cwbbqkmb/sgtzCwbbqkmb")
public class SgtzCwbbqkmbController extends JeecgController<SgtzCwbbqkmb, ISgtzCwbbqkmbService> {
	@Autowired
	private ISgtzCwbbqkmbService sgtzCwbbqkmbService;
	@Autowired
	private CwbbqkmbImportVerify cwbbqkmbImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param sgtzCwbbqkmb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "财务报表全科目表-分页列表查询")
	@ApiOperation(value="财务报表全科目表-分页列表查询", notes="财务报表全科目表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgtzCwbbqkmb sgtzCwbbqkmb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgtzCwbbqkmb> queryWrapper = QueryGenerator.initQueryWrapper(sgtzCwbbqkmb, req.getParameterMap());
		Page<SgtzCwbbqkmb> page = new Page<SgtzCwbbqkmb>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgtzCwbbqkmbService.class,sgtzCwbbqkmbService,pageNo,pageSize,queryWrapper,"sjrq","xmdh");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgtzCwbbqkmb
	 * @return
	 */
	@AutoLog(value = "财务报表全科目表-添加")
	@ApiOperation(value="财务报表全科目表-添加", notes="财务报表全科目表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgtzCwbbqkmb sgtzCwbbqkmb) {
		String sjrq = sgtzCwbbqkmb.getSjrq().replace("-", "").substring(0, 8);
		sgtzCwbbqkmb.setSjrq(sjrq);
		sgtzCwbbqkmb.setXmdh1(sgtzCwbbqkmb.getXmdh());
		sgtzCwbbqkmb.setId(UUIDGenerator.generate());
		sgtzCwbbqkmb.setCreateTime(new Date());
		sgtzCwbbqkmbService.save(sgtzCwbbqkmb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgtzCwbbqkmb
	 * @return
	 */
	@AutoLog(value = "财务报表全科目表-编辑")
	@ApiOperation(value="财务报表全科目表-编辑", notes="财务报表全科目表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgtzCwbbqkmb sgtzCwbbqkmb) {
		String sjrq = sgtzCwbbqkmb.getSjrq().replace("-", "").substring(0, 8);
		sgtzCwbbqkmb.setSjrq(sjrq);
		sgtzCwbbqkmb.setXmdh1(sgtzCwbbqkmb.getXmdh());
		sgtzCwbbqkmbService.updateById(sgtzCwbbqkmb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "财务报表全科目表-通过id删除")
	@ApiOperation(value="财务报表全科目表-通过id删除", notes="财务报表全科目表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgtzCwbbqkmbService.removeById(id);
		return Result.ok("删除成功!");
	}

	 /**
	  * 根据日期批量删除
	  *
	  * @param sjrq
	  * @return
	  */
	 @AutoLog(value = "财务报表全科目表-批量删除")
	 @ApiOperation(value="财务报表全科目表-批量删除", notes="财务报表全科目表-批量删除")
	 @DeleteMapping(value = "/deleteByBatch")
	 public Result<?> deleteByBatch(@Param("sjrq") String sjrq) {
		 QueryWrapper<SgtzCwbbqkmb> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("sjrq",sjrq);
		 this.sgtzCwbbqkmbService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "财务报表全科目表-批量删除")
	@ApiOperation(value="财务报表全科目表-批量删除", notes="财务报表全科目表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgtzCwbbqkmbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "财务报表全科目表-通过id查询")
	@ApiOperation(value="财务报表全科目表-通过id查询", notes="财务报表全科目表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgtzCwbbqkmb sgtzCwbbqkmb = sgtzCwbbqkmbService.getById(id);
		return Result.ok(sgtzCwbbqkmb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgtzCwbbqkmb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgtzCwbbqkmb sgtzCwbbqkmb) {
	  String title1 = "财务报表全科目表-本外币";
	  // Step.1 组装查询条件
	  QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(sgtzCwbbqkmb, request.getParameterMap());
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
		  List<SgtzCwbbqkmb> exportList = service.list(queryWrapper);
		  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		  mv.addObject(NormalExcelConstants.FILE_NAME, title1); //此处设置的filename无效 ,前端会重更新设置一下
		  mv.addObject(NormalExcelConstants.CLASS, SgtzCwbbqkmb.class);
		  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title1 + "报表", "导出人:" + sysUser.getRealname(), title1));
		  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		  return mv;
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "财务报表全科目表导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgtzCwbbqkmbVO.class);
		 ExportParams exportParams = new ExportParams("财务报表全科目表导入模板", "模板信息");
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
	 public Object importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 String sjrq = request.getParameter("sjrq");
		 //先删除原来数据，防止重复
		 QueryWrapper<SgtzCwbbqkmb> wrapper=new QueryWrapper<>();
		 wrapper.eq("sjrq",sjrq);
		 sgtzCwbbqkmbService.remove(wrapper);
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
			 if (cwbbqkmbImportVerify != null) {
				 params.setVerifyHanlder(cwbbqkmbImportVerify);
			 }
			 FileOutputStream fos = null;
			 FileInputStream fis = null;
			 try {
				 fis = new FileInputStream(file);
				 boolean checkResult = ExcelImportCheckUtil.check(fis, SgtzCwbbqkmbVO.class, params);
				 if (!checkResult) {
					 return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
				 }
				 ExcelImportResult<SgtzCwbbqkmb> importResult = ExcelImportUtil.importExcelVerify(file, SgtzCwbbqkmb.class,SgtzCwbbqkmbVO.class, params);
				 List<SgtzCwbbqkmb> list = importResult.getList();
				 List<SgtzCwbbqkmb> qkmbList = new ArrayList<>();
				 for (SgtzCwbbqkmb sgtzCwbbqkmb : list) {
					 sgtzCwbbqkmb.setXmdh(sgtzCwbbqkmb.getXmdh1());
					 Boolean con1= StringUtils.isBlank(sgtzCwbbqkmb.getXmdh());
					 Boolean con2=StringUtils.isNotBlank(sgtzCwbbqkmb.getXmdh()) && (sgtzCwbbqkmb.getXmdh().contains("合计") || sgtzCwbbqkmb.getXmdh().contains("条数"));
					 if (con1 || con2 ) {
						 continue;
					 }
					 sgtzCwbbqkmb.setId(UUIDGenerator.generate());
					 sgtzCwbbqkmb.setCreateTime(new Date());
					 sgtzCwbbqkmb.setSjrq(sjrq);
					 qkmbList.add(sgtzCwbbqkmb);
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
