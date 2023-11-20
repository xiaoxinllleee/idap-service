package org.cmms.modules.performance.depositcustomer.ckkhxxgl.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.csgl.entity.KhgxglCsgl;
import org.cmms.modules.csgl.service.IKhgxglCsglService;
import org.cmms.modules.performance.depositcustomer.ckkhghlsb.entity.Ckkhghlsb;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.entity.Ckkhxxgl;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.service.ICkkhghlsbImpService;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.service.ICkkhxxglService;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.service.IClckkhxxImpService;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.service.IClckkhxxService;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.verify.CkkhClrlImportVerify;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.verify.CkkhGhxxImportVerify;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.vo.CkkhghlsbImp;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.vo.Clckkhxx;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.vo.ClckkhxxImp;
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
 * @Description: 存款客户信息管理
 * @Author: jeecg-boot
 * @Date:   2023-02-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存款客户信息管理")
@RestController
@RequestMapping("/performance/ckkhxxgl")
public class CkkhxxglController extends JeecgController<Ckkhxxgl, ICkkhxxglService> {
	@Autowired
	private ICkkhxxglService ckkhxxglService;
	@Autowired
	private IClckkhxxService clckkhxxService;
	@Autowired
	private IClckkhxxImpService clckkhxxImpService;
	@Autowired
	private ICkkhghlsbImpService ckkhghlsbImpService;
	@Autowired
	private IKhgxglCsglService khgxglCsglService;
	@Autowired
	private CkkhClrlImportVerify clrlImportVerify;
	@Autowired
	private CkkhGhxxImportVerify ghxxImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param ckkhxxgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "存款客户信息管理-分页列表查询")
	@ApiOperation(value="存款客户信息管理-分页列表查询", notes="存款客户信息管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ckkhxxgl ckkhxxgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Ckkhxxgl> queryWrapper = QueryGenerator.initQueryWrapper(ckkhxxgl, req.getParameterMap());
		Page<Ckkhxxgl> page = new Page<Ckkhxxgl>(pageNo, pageSize);
		//查询本人有管户权限或者拓展权限的客户
		queryWrapper.and((wrapper) -> {
			wrapper.like("ghr", getWorkNo()).or().like("yxr", getWorkNo());
		});
		IPage<Ckkhxxgl> pageList = ckkhxxglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
	  * 分页列表查询
	  *
	  * @param ckkhxxgl
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "存款客户信息管理-分页列表查询")
	 @ApiOperation(value="存款客户信息管理-分页列表查询", notes="存款客户信息管理-分页列表查询")
	 @GetMapping(value = "/listAll")
	 public Result<?> queryPageListAll(Ckkhxxgl ckkhxxgl,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		 QueryWrapper<Ckkhxxgl> queryWrapper = QueryGenerator.initQueryWrapper(ckkhxxgl, req.getParameterMap());
		 Page<Ckkhxxgl> page = new Page<Ckkhxxgl>(pageNo, pageSize);
		 IPage<Ckkhxxgl> pageList = ckkhxxglService.page(page, queryWrapper);
		 return Result.ok(pageList);
	 }

	/**
	 * 添加
	 *
	 * @param ckkhxxgl
	 * @return
	 */
	@AutoLog(value = "存款客户信息管理-添加")
	@ApiOperation(value="存款客户信息管理-添加", notes="存款客户信息管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ckkhxxgl ckkhxxgl) {
		ckkhxxglService.save(ckkhxxgl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param ckkhxxgl
	 * @return
	 */
	@AutoLog(value = "存款客户信息管理-编辑")
	@ApiOperation(value="存款客户信息管理-编辑", notes="存款客户信息管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ckkhxxgl ckkhxxgl) {
		ckkhxxglService.updateById(ckkhxxgl);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款客户信息管理-通过id删除")
	@ApiOperation(value="存款客户信息管理-通过id删除", notes="存款客户信息管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ckkhxxglService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "存款客户信息管理-批量删除")
	@ApiOperation(value="存款客户信息管理-批量删除", notes="存款客户信息管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ckkhxxglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款客户信息管理-通过id查询")
	@ApiOperation(value="存款客户信息管理-通过id查询", notes="存款客户信息管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ckkhxxgl ckkhxxgl = ckkhxxglService.getById(id);
		return Result.ok(ckkhxxgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ckkhxxgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ckkhxxgl ckkhxxgl) {
	  // Step.1 组装查询条件
	  QueryWrapper<Ckkhxxgl> queryWrapper = QueryGenerator.initQueryWrapper(ckkhxxgl, request.getParameterMap());
	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	  String selections = request.getParameter("selections");
	  String rowKey = request.getParameter("rowKey");

	  //20211201 过滤选中数据
	  //20211201 BY LIUXIANGQUN 过滤选择的数据改为执行接sql  in  查询
	  if (oConvertUtils.isNotEmpty(selections)) {
		  List<String> selectionList = Arrays.asList(selections.split(","));
		  if(oConvertUtils.isNotEmpty(rowKey)){
			  queryWrapper.in(rowKey,selectionList);
		  }else{
			  queryWrapper.in("ID",selectionList);
		  }
	  }
	  //查询本人有管户权限或者拓展权限的客户
	  queryWrapper.and((wrapper) -> {
		  wrapper.like("ghr", getWorkNo()).or().like("yxr", getWorkNo());
	  });
	  // Step.2 获取导出数据
	  List<Ckkhxxgl> exportList = service.list(queryWrapper);

	  // Step.3 AutoPoi 导出Excel
	  String title = "存款客户信息管理";
	  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
	  mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
	  mv.addObject(NormalExcelConstants.CLASS, Ckkhxxgl.class);
	  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
	  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
	  return mv;
  }

	 /**
	  * 导出excel
	  *
	  * @param request
	  * @param ckkhxxgl
	  */
	 @RequestMapping(value = "/exportAllXls")
	 public ModelAndView exportAllXls(HttpServletRequest request, Ckkhxxgl ckkhxxgl) {
		 // Step.1 组装查询条件
		 QueryWrapper<Ckkhxxgl> queryWrapper = QueryGenerator.initQueryWrapper(ckkhxxgl, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String selections = request.getParameter("selections");
		 String rowKey = request.getParameter("rowKey");

		 //20211201 过滤选中数据
		 //20211201 BY LIUXIANGQUN 过滤选择的数据改为执行接sql  in  查询
		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 if(oConvertUtils.isNotEmpty(rowKey)){
				 queryWrapper.in(rowKey,selectionList);
			 }else{
				 queryWrapper.in("ID",selectionList);
			 }
		 }
		 // Step.2 获取导出数据
		 List<Ckkhxxgl> exportList = service.list(queryWrapper);

		 // Step.3 AutoPoi 导出Excel
		 String title = "存款客户信息管理";
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, Ckkhxxgl.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
	 }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, Ckkhxxgl.class);
  }

	 /**
	  * 客户信息（提取）
	  *
	  * @param jsonObject
	  * @return
	  */
	 @AutoLog(value = "存款客户信息管理-客户信息（提取）")
	 @ApiOperation(value = "存款客户信息管理-客户信息（提取）", notes = "存款客户信息管理-客户信息（提取）")
	 @RequestMapping(value = "/init")
	 public Result<?> init(@RequestBody JSONObject jsonObject) {
		 try {
			 ckkhxxglService.init();
			 return Result.ok("提取成功！");
		 } catch (Exception e) {
			 log.error("存款客户信息管理提取失败！", e.getMessage());
			 return Result.error("提取失败！");
		 }
	 }

	 /**
	  * 认领存款客户信息（提取）
	  *
	  * @param jsonObject
	  * @return
	  */
	 @AutoLog(value = "存款客户信息管理-认领存款客户信息（提取）")
	 @ApiOperation(value = "存款客户信息管理-认领存款客户信息（提取）", notes = "存款客户信息管理-认领存款客户信息（提取）")
	 @RequestMapping(value = "/rlckkhxx")
	 public Result<?> rlckkhxx(@RequestBody JSONObject jsonObject) {
		try {
			String jgdm = jsonObject.getString("jgdm");
			log.info("认领存款客户信息（机构代码）："+jgdm);
			ckkhxxglService.rlckkhxx(jgdm);
			return Result.ok("提取成功！");
		} catch (Exception e) {
			log.error("认领存款客户信息提取失败！");
			e.printStackTrace();
			return Result.error("提取失败！");
		}
	 }

	 /**
	  * 存量认领导出
	  *
	  * @param request
	  * @param clckkhxx
	  */
	 @AutoLog(value = "存款客户信息管理-存量认领导出")
	 @ApiOperation(value = "存款客户信息管理-存量认领导出", notes = "存款客户信息管理-存量认领导出")
	 @RequestMapping(value = "/exportClrlXls")
	 public ModelAndView exportClrlXls(HttpServletRequest request, Clckkhxx clckkhxx) {
		 // Step.1 组装查询条件
		 QueryWrapper<Clckkhxx> queryWrapper = QueryGenerator.initQueryWrapper(clckkhxx, request.getParameterMap());
		 // Step.2 获取导出数据
		 List<Clckkhxx> exportList = clckkhxxService.list(queryWrapper);
		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.FILE_NAME, "存款客户存量认领信息");
		 mv.addObject(NormalExcelConstants.CLASS, Clckkhxx.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("存款客户存量认领信息", "导出人:" + getLoginUser().getUsername(), "存款客户存量认领信息"));
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
	 @AutoLog(value = "存款客户信息管理-存量认领导入-模板下载")
	 @ApiOperation(value = "存款客户信息管理-存量认领导入-模板下载", notes = "存款客户信息管理-存量认领导入-模板下载")
	 @RequestMapping(value = "/exportClrlTemplateXls")
	 public ModelAndView exportClrlTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "存款客户存量认领导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, Clckkhxx.class);
		 ExportParams exportParams = new ExportParams("存款客户存量认领导入模板", "存量认领信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return mv;
	 }

	 /**
	  * 存量认领导入
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @AutoLog(value = "存款客户信息管理-存量认领导入")
	 @ApiOperation(value = "存款客户信息管理-存量认领导入", notes = "存款客户信息管理-存量认领导入")
	 @RequestMapping(value = "/importClrlExcel", method = RequestMethod.POST)
	 public Result<?> importClrlExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
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
			 if (clrlImportVerify != null) {
				 params.setVerifyHanlder(clrlImportVerify);
			 }
			 FileInputStream fis = null;
			 FileOutputStream fos = null;
			 try {
				 fis = new FileInputStream(file);
				 boolean checkResult = ExcelImportCheckUtil.check(fis,ClckkhxxImp.class, params,1.0);
				 if (!checkResult) {
					 return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
				 }
				 ExcelImportResult<ClckkhxxImp> importResult = ExcelImportUtil.importExcelVerify(file,ClckkhxxImp.class,params);
				 List<ClckkhxxImp> list = importResult.getList();
				 List<ClckkhxxImp> clckkhxxImpList = new ArrayList<>();
				 for (ClckkhxxImp clckkh : list) {
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
					 String[] ghrList = clckkh.getGhrxm().split("\\|");
					 String[] ghblList = clckkh.getGhbl().split("\\|");
					 if (ghrList.length != ghblList.length) {
						 return Result.error("管户人员与比例数量不一致，请确认！");
					 }
					 String[] tzrList = clckkh.getTzrxm().split("\\|");
					 String[] tzblList = clckkh.getTzbl().split("\\|");
					 if (tzrList.length != tzblList.length) {
						 return Result.error("拓展人员与比例数量不一致，请确认！");
					 }
					 ClckkhxxImp clckkhxxImp = new ClckkhxxImp();
					 BeanUtil.copyPropertiesIgnoreNull(clckkh, clckkhxxImp);
					 // 导入数据默认有效 存储过程中再做数据无效筛选
					 clckkhxxImp.setIsValid(1);
					 clckkhxxImpList.add(clckkhxxImp);
				 }
				 if (!clckkhxxImpList.isEmpty()) {
					 // 全量删除
					 UpdateWrapper<ClckkhxxImp> updateWrapper = new UpdateWrapper<>();
					 clckkhxxImpService.remove(updateWrapper);
					 // 插入
					 clckkhxxImpService.saveBatch(clckkhxxImpList);
				 }

				 // 导入完成后，从存量存款客户信息（数据导入临时表）提取数据到正式表
				 try {
					 clckkhxxService.pRlckkhxxImp(getLoginUser().getUsername());
				 } catch (Exception e) {
					 e.printStackTrace();
					 log.error("存款客户-存量认领-数据导入处理失败！" + e.getMessage());
					 return Result.error("存量认领导入数据处理失败！" + e.getMessage());
				 }

				 obj.put("filePath", filePath);
				 fos = new FileOutputStream(baseFilePath);
				 importResult.getWorkbook().write(fos);
				 fos.flush();
				 fos.close();
				 return Result.ok("导入完成！成功导入数据行数: " + list.size(), obj);
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


	 /**
	  * 管户信息导入-模板下载
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @AutoLog(value = "存款客户信息管理-管户信息导入-模板下载")
	 @ApiOperation(value = "存款客户信息管理-管户信息导入-模板下载", notes = "存款客户信息管理-管户信息导入-模板下载")
	 @RequestMapping(value = "/exportGhxxTemplateXls")
	 public ModelAndView exportGhxxTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "存款客户管户信息导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, CkkhghlsbImp.class);
		 ExportParams exportParams = new ExportParams("存款客户管户信息导入模板", "管户信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return mv;
	 }

	 /**
	  * 管户信息导入
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @AutoLog(value = "存款客户信息管理-管户信息导入")
	 @ApiOperation(value = "存款客户信息管理-管户信息导入", notes = "存款客户信息管理-管户信息导入")
	 @RequestMapping(value = "/importGhxxExcel", method = RequestMethod.POST)
	 public Result<?> importGhxxExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
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
			 if (ghxxImportVerify != null) {
				 params.setVerifyHanlder(ghxxImportVerify);
			 }
			 FileInputStream fis = null;
			 FileOutputStream fos = null;
			 try {
				 fis = new FileInputStream(file);
				 boolean checkResult = ExcelImportCheckUtil.check(fis, CkkhghlsbImp.class, params, 1.0);
				 if (!checkResult) {
					 return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
				 }
				 ExcelImportResult<CkkhghlsbImp> importResult = ExcelImportUtil.importExcelVerify(file,CkkhghlsbImp.class,params);
				 List<CkkhghlsbImp> list = importResult.getList();
				 List<CkkhghlsbImp> ckkhghlsbImpList = new ArrayList<>();
				 for (CkkhghlsbImp ckkhghlsb : list) {
					 CkkhghlsbImp ckkhghlsbImp = new CkkhghlsbImp();
					 BeanUtil.copyPropertiesIgnoreNull(ckkhghlsb, ckkhghlsbImp);
					 //TODO 设置管户类型为管户 表内字段设置默认值 2
					 //ckkhghlsbImp.setGhlx(2);
					 // 导入数据默认有效 存储过程中再做数据无效筛选
					 ckkhghlsbImp.setIsValid(1);
					 ckkhghlsbImpList.add(ckkhghlsbImp);
				 }
				 if (!ckkhghlsbImpList.isEmpty()) {
					 // 全量删除
					 UpdateWrapper<CkkhghlsbImp> updateWrapper = new UpdateWrapper<>();
					 ckkhghlsbImpService.remove(updateWrapper);
					 // 插入
					 ckkhghlsbImpService.saveBatch(ckkhghlsbImpList);
				 }

				 // 导入完成后，从存款客户管户历史表（数据导入临时表）提取数据到正式表
				 try {
					 ckkhghlsbImpService.pCkkhGhxxImp(getLoginUser().getUsername());
				 } catch (Exception e) {
					 e.printStackTrace();
					 log.error("存款客户-管户信息-数据导入处理失败！" + e.getMessage());
					 return Result.error("管户信息导入数据处理失败！" + e.getMessage());
				 }

				 obj.put("filePath", filePath);
				 fos = new FileOutputStream(baseFilePath);
				 importResult.getWorkbook().write(fos);
				 fos.flush();
				 fos.close();
				 return Result.ok("管户信息导入完成！成功导入数据行数: " + list.size(), obj);
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
