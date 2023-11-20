package org.cmms.modules.ywgl.dkyw.dksjzrgl.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.DateUtil;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.sjxf.hxxt.dkzdkb.entity.Dkzdkb;
import org.cmms.modules.sjxf.hxxt.dkzdkb.service.IDkzdkbService;
import org.cmms.modules.sjxf.xdxt.dkywjjb.entity.Dkywjjb;
import org.cmms.modules.sjxf.xdxt.dkywjjb.service.IDkywjjbService;
import org.cmms.modules.sjxf.xdxt.txywyeb.entity.Txywyeb;
import org.cmms.modules.sjxf.xdxt.txywyeb.service.ITxywyebService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.entity.Dksjzrgl;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.entity.DksjzrglHistory;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.entity.DksjzrglImport;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.entity.DksjzrglVo;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.service.IDksjzrglService;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.service.IDksjzrglServiceHistory;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.verify.DksjzrglImportVerify;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 贷款数据责任管理
 * @Author: jeecg-boot
 * @Date:   2021-09-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款数据责任管理")
@RestController
@RequestMapping("/dksjzrgl/dksjzrgl")
public class DksjzrglController extends JeecgController<Dksjzrgl, IDksjzrglService> {
	 @Autowired
	 private IDksjzrglService dksjzrglService;
	 @Autowired
	 private IDkzdkbService dkzdkbService;
	 @Autowired
	 private IDksjzrglServiceHistory iDksjzrglServiceHistory;
	 @Autowired
	 private DksjzrglImportVerify dKsjzrglImportVerify;
	 @Autowired
	 private ITxywyebService txywyebService;
	 @Autowired
	 private IDkywjjbService dkywjjbService;
	 @Autowired
	 private IHrBasStaffPostService hrBasStaffPostService;
	 @Autowired
	 private IHrBasOrganizationService hrBasOrganizationService;
	 @Autowired
	 private IHrBasStaffService hrBasStaffService;
	 @Value(value = "${common.path.upload}")
	 private String uploadpath;

	 /**
	  * 贷款数据责任管理 / 分页列表查询
	  *
	  * @param
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "贷款数据责任管理-分页列表查询")
	 @ApiOperation(value = "贷款数据责任管理-分页列表查询", notes = "贷款数据责任管理-分页列表查询")
	 @GetMapping(value = "/list")
	 public Result<?> queryPageList(DksjzrglHistory dksjzrglHistory,
									HttpServletRequest req,
									@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
									@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
									@RequestParam(name = "tjyf", required = false) String tjyf) {
		 //如果查询的不是本月的数据，则查询历史数据
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		 String SysMonths = dateFormat.format(new Date()).concat("-01");
		 if (tjyf.equals(SysMonths) || StringUtils.isEmpty(tjyf)) {
			 QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(dksjzrglHistory, req.getParameterMap());
			 IPage pageList = org.cmms.common.utils.PageUtil.toPage(IDksjzrglService.class, dksjzrglService, pageNo, pageSize, queryWrapper, "dkzh");
			 return Result.ok(pageList);
		 } else {
			 //fiscal_date数据格式为yyyyMMdd，此处需去除横杠
			 tjyf = tjyf.replace("-", "");
			 QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(dksjzrglHistory, req.getParameterMap());
			 queryWrapper.eq("fiscal_date", tjyf);
			 IPage pageList = org.cmms.common.utils.PageUtil.toPage(IDksjzrglServiceHistory.class, iDksjzrglServiceHistory, pageNo, pageSize, queryWrapper, "dkzh");
			 return Result.ok(pageList);
		 }
	 }

	 /**
	  * 贷款数据责任管理 / 贷款责任数据重复校验
	  *
	  * @param dksjzrgl
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "贷款数据责任管理-贷款责任数据重复校验")
	 @ApiOperation(value="贷款数据责任管理-贷款责任数据重复校验", notes="贷款数据责任管理-贷款责任数据重复校验")
	 @GetMapping(value = "/getByDkzh")
	 public Result<?> getByDkzh(Dksjzrgl dksjzrgl, HttpServletRequest req) {
		 QueryWrapper<Dksjzrgl> queryWrapper = QueryGenerator.initQueryWrapper(dksjzrgl, req.getParameterMap());
		 Dksjzrgl one = dksjzrglService.getOne(queryWrapper);
		 if (one != null) {
			 return Result.error("数据已存在");
		 } else {
			 QueryWrapper queryWrapper1 = new QueryWrapper();
			 queryWrapper1.eq("acct_no", dksjzrgl.getDkzh());
			 Dkzdkb base = dkzdkbService.getOne(queryWrapper1);
			 QueryWrapper queryWrapperTyew = new QueryWrapper();
			 queryWrapperTyew.eq("acct_no", dksjzrgl.getDkzh());
			 Txywyeb txxx = txywyebService.getOne(queryWrapperTyew);

			 int flag = 0;//0没有/1贷款余额表/2贴现信息表
			 if (txxx != null) {
				 flag = 2;
			 }
			 if (base != null) {
				 flag = 1;
			 }
			 if (flag == 0) {
				 return Result.error("账号不存在");
			 }
			 if (flag == 1) {
				 dksjzrgl.setKhmc(base.getCustName());
				 dksjzrgl.setJgdm(base.getBrNo());
				 dksjzrgl.setZjhm(base.getIdentNo());
				 QueryWrapper queryWrapperDkywjjb = new QueryWrapper();
				 queryWrapperDkywjjb.eq("acct_no", dksjzrgl.getDkzh());
				 Dkywjjb due = dkywjjbService.getOne(queryWrapperDkywjjb);
				 if (due != null) {
					 dksjzrgl.setCustid(due.getUserId());
				 }
				 if (!StringUtils.isEmpty(dksjzrgl.getCustid())) {
					 QueryWrapper queryWrapperYgxx = new QueryWrapper();
					 queryWrapperYgxx.eq("khjlbz", dksjzrgl.getCustid());
					 queryWrapperYgxx.eq("rglx", 1);
					 HrBasStaffPost hr_bas_staff_post = hrBasStaffPostService.getOne(queryWrapperYgxx);
					 if (hr_bas_staff_post != null) {
						 dksjzrgl.setJobnumber(hr_bas_staff_post.getYggh());
					 }
				 }

			 } else {
				 dksjzrgl.setKhmc(txxx.getCustName());
				 dksjzrgl.setJgdm(txxx.getOrg());
				 //设置组织名称
				 dksjzrgl.setZjhm("");
				 dksjzrgl.setCustid("");
				 dksjzrgl.setJobnumber("");
				 dksjzrgl.setCustidzr("");
				 dksjzrgl.setJobnumberzr("");
			 }
			 /*if (dksjzrgl.getJgdm() != null) {
				 HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByYwjgdm(dksjzrgl.getJgdm());
				 if (hrBasOrganization != null) {
					 dksjzrgl.setZzbz(hrBasOrganization.getZzbz());
				 }
			 }*/
			 return Result.ok(dksjzrgl);
		 }
	 }


	 /**
	  * 分页列表查询
	  *
	  * @param dksjzrgl
	  * @return
	  */
	 @AutoLog(value = "贷款数据责任管理-分页列表查询")
	 @ApiOperation(value="贷款数据责任管理-分页列表查询", notes="贷款数据责任管理-分页列表查询")
	 @GetMapping(value = "/getYgxx")
	 public Result<?> getYgxx(Dksjzrgl dksjzrgl) {
		 QueryWrapper<HrBasStaff> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("yggh",dksjzrgl.getJobnumber());
		 HrBasStaff one = hrBasStaffService.getOne(queryWrapper);
		 return Result.ok(one);

	 }

	/**
	 * 添加
	 *
	 * @param dksjzrgl
	 * @return
	 */
	@AutoLog(value = "贷款数据责任管理-添加")
	@ApiOperation(value="贷款数据责任管理-添加", notes="贷款数据责任管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dksjzrgl dksjzrgl) {
		dksjzrgl.setLrbz(1);
		dksjzrgl.setLrczy(getLoginUser().getUsername());
		dksjzrgl.setLrsj(new Timestamp(System.currentTimeMillis()));
		dksjzrgl.setDflag(0);
		dksjzrgl.setGlzrbl(new BigDecimal(100));
		dksjzrgl.setZbks(2);
		dksjzrglService.save(dksjzrgl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dksjzrgl
	 * @return0
	 */
	@AutoLog(value = "贷款数据责任管理-编辑")
	@ApiOperation(value="贷款数据责任管理-编辑", notes="贷款数据责任管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dksjzrgl dksjzrgl) {
		QueryWrapper<Dksjzrgl> queryWrapper = new QueryWrapper<Dksjzrgl>();
		queryWrapper.eq("dkzh",dksjzrgl.getDkzh());
		//主键不能更新
		dksjzrgl.setDkzh(null);
		dksjzrgl.setLrbz(2);
		dksjzrgl.setLrczy(getLoginUser().getUsername());
		dksjzrgl.setDkzh(null);
		dksjzrglService.update(dksjzrgl,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "贷款数据责任管理-通过id删除")
	@ApiOperation(value="贷款数据责任管理-通过id删除", notes="贷款数据责任管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("dkzh")String dkzh) {
		QueryWrapper<Dksjzrgl> queryWrapper = new QueryWrapper<Dksjzrgl>();
		queryWrapper.eq("dkzh",dkzh);
		dksjzrglService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款数据责任管理-批量删除")
	@ApiOperation(value="贷款数据责任管理-批量删除", notes="贷款数据责任管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dksjzrglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款数据责任管理-通过id查询")
	@ApiOperation(value="贷款数据责任管理-通过id查询", notes="贷款数据责任管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dksjzrgl dksjzrgl = dksjzrglService.getById(id);
		return Result.ok(dksjzrgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dksjzrglHistory
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DksjzrglHistory dksjzrglHistory, String tjyf) {
      String title = "贷款数据责任管理";

	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	  String selections = request.getParameter("selections");
	  String rowKey = request.getParameter("rowKey");

	  //如果查询的不是本月的数据，则查询历史数据
	  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
	  String SysMonths = dateFormat.format(new Date()).concat("-01");
	  if (tjyf.equals(SysMonths) || StringUtils.isEmpty(tjyf)) {
	  	  Dksjzrgl dksjzrgl = new Dksjzrgl();
		  BeanUtil.copyPropertiesIgnoreNull(dksjzrglHistory, dksjzrgl);
		  QueryWrapper<Dksjzrgl> queryWrapper = QueryGenerator.initQueryWrapper(dksjzrgl, request.getParameterMap());

		  List<Dksjzrgl> exportList = dksjzrglService.list(queryWrapper);
		  // Step.3 AutoPoi 导出Excel
		  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		  mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
		  mv.addObject(NormalExcelConstants.CLASS, Dksjzrgl.class);
		  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
		  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		  return mv;
	  } else {
		  QueryWrapper<DksjzrglHistory> queryWrapper = QueryGenerator.initQueryWrapper(dksjzrglHistory, request.getParameterMap());
		  //fiscal_date数据格式为yyyyMMdd，此处需去除横杠
		  tjyf = tjyf.replace("-", "");
		  queryWrapper.eq("fiscal_date", tjyf);
		  List<DksjzrglHistory> exportList = iDksjzrglServiceHistory.list(queryWrapper);
		  // Step.3 AutoPoi 导出Excel
		  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		  mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
		  mv.addObject(NormalExcelConstants.CLASS, DksjzrglHistory.class);
		  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
		  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		  return mv;

	  }

	  // Step.1 组装查询条件

	  // Step.2 获取导出数据




  }

	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "贷款数据责任管理导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, DksjzrglVo.class);
		 ExportParams exportParams = new ExportParams("贷款数据责任管理导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;

	 }


	 /**
	  * 通过excel导入数据
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String filePaths = jsonObject.getString("filePaths");
		 if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
			 return Result.error("请先上传文件！");
		 }

		 String[] filePathList = filePaths.split(",");
		 JSONObject obj = new JSONObject();
		 for (String filePath : filePathList) {
			 System.out.println(filePath);
			 String baseFilePath = uploadpath + File.separator + filePath;
//          MultipartFile file = entity.getValue();// 获取上传文件对象
			 File file = new File(baseFilePath);
			 ImportParams params = new ImportParams();
			 params.setTitleRows(1);
			 params.setHeadRows(1);
			 params.setNeedSave(false);
			 if (dKsjzrglImportVerify != null) {
				 params.setVerifyHanlder(dKsjzrglImportVerify);
			 }
			 FileInputStream fis = null;
			 FileOutputStream fos = null;

			 try {
				 fis = new FileInputStream(file);
				 boolean checkResult = ExcelImportCheckUtil.check(fis, DksjzrglVo.class, params, 1.0);
				 ExcelImportResult<DksjzrglImport> importResult = ExcelImportUtil.importExcelVerify(file, DksjzrglImport.class,DksjzrglVo.class, params, false);
				 List<DksjzrglImport> list = importResult.getList();

				 List<Dksjzrgl> dksjzrglList = new ArrayList<>();
				 List<DksjzrglHistory> dksjzrglHistoryList = new ArrayList<>();
				 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
				 String SysMonths = dateFormat.format(new Date()).concat("01");
				 for (DksjzrglImport dksjzrglImport : list) {
					 Date tjyf = dksjzrglImport.getTjyf();
					 String tjyfStr = DateUtil.format(tjyf, "yyyyMM") + "01";
					 if (StringUtils.isEmpty(tjyfStr) || tjyfStr.equals(SysMonths)) {
						 Dksjzrgl dksjzrgl = new Dksjzrgl();
						 BeanUtil.copyPropertiesIgnoreNull(dksjzrglImport, dksjzrgl);
						 log.info("@@@@@@@@@@@@@@@@@@@zzbs="+dksjzrglImport.getZzbz());
						 dksjzrglList.add(dksjzrgl);
					 } else {
						 DksjzrglHistory dksjzrglHistory = new DksjzrglHistory();
						 BeanUtil.copyPropertiesIgnoreNull(dksjzrglImport, dksjzrglHistory);
						 dksjzrglHistory.setFiscal_date(tjyfStr);
						 dksjzrglHistoryList.add(dksjzrglHistory);
					 }
				 }
				 if (!dksjzrglList.isEmpty()) {
					 dksjzrglService.saveBatch(dksjzrglList);
				 }

				 if (!dksjzrglHistoryList.isEmpty()) {
					 iDksjzrglServiceHistory.saveBatch(dksjzrglHistoryList);
				 }

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
