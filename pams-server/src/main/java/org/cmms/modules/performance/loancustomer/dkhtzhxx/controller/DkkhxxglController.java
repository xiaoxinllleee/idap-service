package org.cmms.modules.performance.loancustomer.dkhtzhxx.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.BeanUtil;
import org.cmms.modules.csgl.entity.KhgxglCsgl;
import org.cmms.modules.csgl.service.IKhgxglCsglService;
import org.cmms.modules.performance.loancustomer.dkhtzhxx.entity.Dkkhxxgl;
import org.cmms.modules.performance.loancustomer.dkhtzhxx.service.ICldkkhxxImpService;
import org.cmms.modules.performance.loancustomer.dkhtzhxx.service.ICldkkhxxService;
import org.cmms.modules.performance.loancustomer.dkhtzhxx.service.IDkkhxxglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.performance.loancustomer.dkhtzhxx.verify.DkkhClrlImportVerify;
import org.cmms.modules.performance.loancustomer.dkhtzhxx.vo.Cldkkhxx;
import org.cmms.modules.performance.loancustomer.dkhtzhxx.vo.CldkkhxxImp;
import org.cmms.modules.performance.loancustomer.dkhtzhxx.vo.CldkkhxxImpVO;
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
 * @Description: 贷款客户综合信息
 * @Author: jeecg-boot
 * @Date:   2023-04-03
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款客户综合信息")
@RestController
@RequestMapping("/dkkhzhxx/dkkhxxgl")
public class DkkhxxglController extends JeecgController<Dkkhxxgl, IDkkhxxglService> {
	@Autowired
	private IDkkhxxglService dkkhxxglService;
	@Autowired
	private ICldkkhxxService cldkkhxxService;
	 @Autowired
	 private IKhgxglCsglService khgxglCsglService;
	@Autowired
	private ICldkkhxxImpService cldkkhxxImpService;
	@Autowired
	private DkkhClrlImportVerify dkkhClrlImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param dkkhxxgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款客户综合信息-分页列表查询")
	@ApiOperation(value="贷款客户综合信息-分页列表查询", notes="贷款客户综合信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dkkhxxgl dkkhxxgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dkkhxxgl> queryWrapper = QueryGenerator.initQueryWrapper(dkkhxxgl, req.getParameterMap());
		Page<Dkkhxxgl> page = new Page<Dkkhxxgl>(pageNo, pageSize);
		IPage<Dkkhxxgl> pageList = dkkhxxglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dkkhxxgl
	 * @return
	 */
	@AutoLog(value = "贷款客户综合信息-添加")
	@ApiOperation(value="贷款客户综合信息-添加", notes="贷款客户综合信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dkkhxxgl dkkhxxgl) {
		dkkhxxglService.save(dkkhxxgl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dkkhxxgl
	 * @return
	 */
	@AutoLog(value = "贷款客户综合信息-编辑")
	@ApiOperation(value="贷款客户综合信息-编辑", notes="贷款客户综合信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dkkhxxgl dkkhxxgl) {
		dkkhxxglService.updateById(dkkhxxgl);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款客户综合信息-通过id删除")
	@ApiOperation(value="贷款客户综合信息-通过id删除", notes="贷款客户综合信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkkhxxglService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款客户综合信息-批量删除")
	@ApiOperation(value="贷款客户综合信息-批量删除", notes="贷款客户综合信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkkhxxglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款客户综合信息-通过id查询")
	@ApiOperation(value="贷款客户综合信息-通过id查询", notes="贷款客户综合信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dkkhxxgl dkkhxxgl = dkkhxxglService.getById(id);
		return Result.ok(dkkhxxgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkkhxxgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dkkhxxgl dkkhxxgl) {
      return super.exportXls(request, dkkhxxgl, Dkkhxxgl.class, "贷款客户综合信息");
  }

	 /**
	  * @return
	  */
	 @RequestMapping(value = "/extract" , method = RequestMethod.PUT)
	 public Result<?> extract() {
		 try {
			 dkkhxxglService.extract();
		 } catch (Exception e) {
			 log.error("提取失败",e.getMessage());
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功!");
	 }

	 /**
	  * 认领贷款客户信息（提取）
	  *
	  * @param jsonObject
	  * @return
	  */
	 @AutoLog(value = "贷款合同综合信息-认领贷款客户信息（提取）")
	 @ApiOperation(value = "贷款合同综合信息-认领贷款客户信息（提取）", notes = "贷款合同综合信息-认领贷款客户信息（提取）")
	 @RequestMapping(value = "/rldkkhxx")
	 public Result<?> rldkkhxx(@RequestBody JSONObject jsonObject) {
		 try {
			 String jgdm = jsonObject.getString("jgdm");
			 log.info("认领贷款客户信息（机构代码）："+jgdm);
			 dkkhxxglService.rldkkhxx(jgdm);
			 return Result.ok("提取成功！");
		 } catch (Exception e) {
			 log.error("认领贷款客户信息提取失败！");
			 e.printStackTrace();
			 return Result.error("提取失败！");
		 }
	 }

	 /**
	  * 贷款客户存量认领导出
	  *
	  * @param request
	  * @param clckkhxx
	  */
	 @AutoLog(value = "贷款合同综合信息-贷款客户存量认领导出")
	 @ApiOperation(value = "贷款合同综合信息-贷款客户存量认领导出", notes = "贷款合同综合信息-贷款客户存量认领导出")
	 @RequestMapping(value = "/exportClrlXls")
	 public ModelAndView exportClrlXls(HttpServletRequest request, Cldkkhxx clckkhxx) {
		 // Step.1 组装查询条件
		 QueryWrapper<Cldkkhxx> queryWrapper = QueryGenerator.initQueryWrapper(clckkhxx, request.getParameterMap());
		 // Step.2 获取导出数据
		 List<Cldkkhxx> exportList = cldkkhxxService.list(queryWrapper);
		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.FILE_NAME, "贷款客户存量认领信息");
		 mv.addObject(NormalExcelConstants.CLASS, Cldkkhxx.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("贷款客户存量认领信息", null, "贷款客户存量认领"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
	 }

	 /**
	  * 贷款客户存量认领导出
	  *
	  * @param request
	  * @param clckkhxxImp
	  */
	 @AutoLog(value = "贷款合同综合信息-贷款客户存量认领导出")
	 @ApiOperation(value = "贷款合同综合信息-贷款客户存量认领导出", notes = "贷款合同综合信息-贷款客户存量认领导出")
	 @RequestMapping(value = "/exportClrlResultXls")
	 public ModelAndView exportClrlResultXls(HttpServletRequest request, CldkkhxxImp clckkhxxImp) {
		 // Step.1 组装查询条件
		 QueryWrapper<CldkkhxxImp> queryWrapper = QueryGenerator.initQueryWrapper(clckkhxxImp, request.getParameterMap());
		 // Step.2 获取导出数据
		 List<CldkkhxxImp> exportList = cldkkhxxImpService.list(queryWrapper);
		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.FILE_NAME, "贷款客户存量认领导入结果");
		 mv.addObject(NormalExcelConstants.CLASS, CldkkhxxImp.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("贷款客户存量认领导入结果", null, "贷款客户存量认领导入结果"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
	 }

	 /**
	  * 存量认领导入-模板下载
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @AutoLog(value = "贷款合同综合信息-存量认领导入-模板下载")
	 @ApiOperation(value = "贷款合同综合信息-存量认领导入-模板下载", notes = "贷款合同综合信息-存量认领导入-模板下载")
	 @RequestMapping(value = "/exportClrlTemplateUrl")
	 public ModelAndView exportClrlTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "贷款客户存量认领导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, CldkkhxxImpVO.class);
		 ExportParams exportParams = new ExportParams("贷款客户存量认领导入模板", "存量认领信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return mv;
	 }

	 /**
	  * 通过excel导入数据
	  * 贷款客户存量认领导入
	  * @param request
	  * @param response
	  * @return
	  */
	 @AutoLog(value = "贷款合同综合信息-存量认领导入")
	 @ApiOperation(value = "贷款合同综合信息-存量认领导入", notes = "贷款合同综合信息-存量认领导入")
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
//		 return super.importExcel(request, response, Dkkhxxgl.class);
		 String filePaths = jsonObject.getString("filePaths");
		 if (StringUtils.isEmpty(filePaths)) {
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
			 if (dkkhClrlImportVerify != null) {
				 params.setVerifyHanlder(dkkhClrlImportVerify);
			 }
			 FileInputStream fis = null;
			 FileOutputStream fos = null;
			 try {
				 fis = new FileInputStream(file);
				 ExcelImportResult<CldkkhxxImp> importResult = ExcelImportUtil.importExcelVerify(file,CldkkhxxImp.class, CldkkhxxImpVO.class, params);
				 List<CldkkhxxImp> list = importResult.getList();
				 QueryWrapper<KhgxglCsgl> csglQueryWrapper = new QueryWrapper<>();
				 csglQueryWrapper.eq("csbm","CS0012");
				 KhgxglCsgl csgl = khgxglCsglService.getOne(csglQueryWrapper,false);
				 if (csgl == null) {
					 return Result.error("参数管理中未找到设置的存量日期（CS0012）！");
				 } else {
					 if (csgl.getCsz().isEmpty()) {
						 return Result.error("参数管理中设置的存量日期为空（CS0012）！");
					 }
				 }

				 if (!list.isEmpty()) {
					 // 全量删除
					 UpdateWrapper<CldkkhxxImp> updateWrapper = new UpdateWrapper<>();
					 cldkkhxxImpService.remove(updateWrapper);
					 // 插入
					 cldkkhxxImpService.saveBatch(list);
				 }

				 // 导入完成后，从存量贷款客户信息导入（数据导入临时表）提取数据到正式表
				 try {
					 cldkkhxxImpService.pRldkkhxxImp(getLoginUser().getUsername());
				 } catch (Exception e) {
					 e.printStackTrace();
					 log.error("贷款客户-存量认领-数据导入处理失败！" + e.getMessage());
					 return Result.error("存量认领导入数据处理失败！" + e.getMessage());
				 }
				 //处理完成之后直接导出临时表的数据
				 QueryWrapper<CldkkhxxImp> queryWrapper = new QueryWrapper<>();
				 queryWrapper.eq("is_valid", "1");
				 int count = (int)cldkkhxxImpService.count(queryWrapper);
//				 obj.put("filePath", filePath);
//				 fos = new FileOutputStream(baseFilePath);
//				 importResult.getWorkbook().write(fos);
//				 fos.flush();
//				 fos.close();
				 return Result.ok("导入完成！成功导入数据行数: " + count, obj);
			 } catch (Exception e) {
				 e.printStackTrace();
				 log.error(e.getMessage(), e);
				 return Result.error("导入失败:" + e.getMessage());
			 } finally {
				 IoUtil.close(fis);
				 IoUtil.close(fos);
			 }
		 }
		 return Result.ok("导入失败！");
	 }
}
