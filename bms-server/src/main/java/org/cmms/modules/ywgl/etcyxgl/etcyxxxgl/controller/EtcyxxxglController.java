package org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;

import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.activiti.entity.ActBusiness;
import org.cmms.modules.activiti.service.ActBusinessService;
import org.cmms.modules.activiti.service.ActProcessService;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.entity.Etcyxxxgl;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.entity.EtcyxxxglSpxx;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.entity.EtcyxxxglTmp;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.entity.VEtcyxxxgl;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.service.IEtcyxxxglService;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.service.IEtcyxxxglSpxxService;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.service.IEtcyxxxglTmpService;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.service.IVEtcyxxxglService;

import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.verify.EtcyxxxglImportVerify;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.verify.EtcyxxxglYxrImportVerify;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.vo.EtcyxxxglImport;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.vo.EtcyxxxglTransfer;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.vo.EtcyxxxglYxrImport;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: ETC营销信息管理
 * @Author: jeecg-boot
 * @Date:   2021-09-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="ETC营销信息管理")
@RestController
@RequestMapping("/etcyxxxgl/etcyxxxgl")
public class EtcyxxxglController extends JeecgController<VEtcyxxxgl, IVEtcyxxxglService> {
	@Autowired
	private IVEtcyxxxglService vEtcyxxxglService;
	@Autowired
	private IEtcyxxxglService etcyxxxglService;
	@Autowired
	private IEtcyxxxglTmpService etcyxxxglTmpService;
	@Autowired
	private IEtcyxxxglSpxxService etcyxxxglSpxxService;
	@Autowired
	private EtcyxxxglImportVerify etcyxxxglImportVerify;
	@Autowired
	private EtcyxxxglYxrImportVerify etcyxxxglYxrImportVerify;
	 @Autowired
	 private IHrBasOrganizationService hrBasOrganizationService;
	 @Autowired
	 private IHrBasStaffPostService hrBasStaffPostService;
	 @Autowired
	 private ActBusinessService actBusinessService;
	 @Autowired
	 private ActProcessService actProcessService;

	 @Value(value = "${common.path.upload}")
	 private String uploadpath;
	
	/**
	 * 分页列表查询
	 *
	 * @param etcyxxxgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "ETC营销信息管理-分页列表查询")
	@ApiOperation(value="ETC营销信息管理-分页列表查询", notes="ETC营销信息管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VEtcyxxxgl etcyxxxgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VEtcyxxxgl> queryWrapper = QueryGenerator.initQueryWrapper(etcyxxxgl, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IVEtcyxxxglService.class,vEtcyxxxglService,pageNo,pageSize,queryWrapper,"tjyf","cphm","bdzkh");
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param etcyxxxgl
	 * @return
	 */
	@AutoLog(value = "ETC营销信息管理-添加")
	@ApiOperation(value="ETC营销信息管理-添加", notes="ETC营销信息管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Etcyxxxgl etcyxxxgl) {
		etcyxxxglService.save(etcyxxxgl);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param etcyxxxgl
	 * @return
	 */
	@AutoLog(value = "ETC营销信息管理-编辑")
	@ApiOperation(value="ETC营销信息管理-编辑", notes="ETC营销信息管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Etcyxxxgl etcyxxxgl) {
		etcyxxxgl.setXgr(getLoginUser().getRealname());
		etcyxxxgl.setXgsj(new Date());
		QueryWrapper<Etcyxxxgl> queryWrapper = new QueryWrapper<Etcyxxxgl>();
		queryWrapper.eq("khsfzh",etcyxxxgl.getKhsfzh());
		queryWrapper.eq("khsj",etcyxxxgl.getKhsj());
		queryWrapper.eq("khrq",etcyxxxgl.getKhrq());
		etcyxxxglService.update(etcyxxxgl,queryWrapper);
		return Result.ok("编辑成功!");
	}

	 @PostMapping(value = "/editCheck")
	 public Result<?> editCheck(@RequestBody Etcyxxxgl etcyxxxgl) {
		 QueryWrapper<Etcyxxxgl> queryWrapper = new QueryWrapper<Etcyxxxgl>();
		 queryWrapper.eq("khsfzh",etcyxxxgl.getKhsfzh());
		 queryWrapper.eq("khsj",etcyxxxgl.getKhsj());
		 queryWrapper.eq("khrq",etcyxxxgl.getKhrq());
		 List<Etcyxxxgl> etcyxxxglList = etcyxxxglService.list(queryWrapper);
		 if (etcyxxxglList == null || etcyxxxglList.isEmpty()) {
			 return Result.error("信息不存在！");
		 }
		 Etcyxxxgl check = etcyxxxglList.get(0);
		 if ("线上".equals(check.getBlqd())) {
			 return Result.error("线上办理渠道，不能修改！");
		 }
		 Date tjyf = etcyxxxglService.getMaxTjyf();
		 if (tjyf != null) {
			 if (!"1".equals(getLoginUser().getOrgCode())) {
			 	try {
					Date ncrq = DateUtil.getFirstMonth_Year(tjyf, 0);
					Date ymrq = DateUtil.getLastday_Month(tjyf, 0);
					if (!(etcyxxxgl.getKhrq().getTime() >= ncrq.getTime() && etcyxxxgl.getKhrq().getTime() <= ymrq.getTime())) {
						return Result.error("只能修改本年开户的营销人！");
					}
				} catch (Exception e) {
			 		e.printStackTrace();
				}
			 }
		 }
		 return Result.ok();
	 }

	 /**
	  * 编辑
	  *
	  * @param etcyxxxgl
	  * @return
	  */
	 @AutoLog(value = "ETC营销信息管理-编辑")
	 @ApiOperation(value="ETC营销信息管理-编辑", notes="ETC营销信息管理-编辑")
	 @PutMapping(value = "/editYxr")
	 public Result<?> editYxr(@RequestBody Etcyxxxgl etcyxxxgl) {
		 QueryWrapper<Etcyxxxgl> queryWrapper = new QueryWrapper<Etcyxxxgl>();
		 queryWrapper.eq("khsfzh",etcyxxxgl.getKhsfzh());
		 queryWrapper.eq("khsj",etcyxxxgl.getKhsj());
		 queryWrapper.eq("khrq",etcyxxxgl.getKhrq());
		 List<Etcyxxxgl> etcyxxxglList = etcyxxxglService.list(queryWrapper);
		 if (etcyxxxglList == null || etcyxxxglList.isEmpty()) {
			 return Result.error("信息不存在！");
		 }
		 Etcyxxxgl check = etcyxxxglList.get(0);
		 try {
		 	 Date tjyf = etcyxxxglService.getMaxTjyf();
		 	 Date ycrq = DateUtil.getFirstday_Month(tjyf, 0);
		 	 Date ymrq = DateUtil.getLastday_Month(tjyf, 0);
			 HrBasOrganization loginOrg = hrBasOrganizationService.queryByZzbz(getLoginUser().getOrgCode());
			 if (!("无".equals(etcyxxxgl.getYxrgh()) && "无".equals(etcyxxxgl.getYxjgdm()))) {
				 //查询营销人是否存在
				 QueryWrapper<HrBasStaffPost> staffPostQueryWrapper = new QueryWrapper<>();
				 staffPostQueryWrapper.eq("yggh", etcyxxxgl.getYxrgh());
				 staffPostQueryWrapper.le("rgrq", DateUtil.getFirstday_Month(new Date(), 0));
				 staffPostQueryWrapper.apply("  (lgrq is null or lgrq >= {0} )", DateUtil.getLastday_Month(new Date(), 0));
				 List<HrBasStaffPost> hrBasStaffPostList = hrBasStaffPostService.list(staffPostQueryWrapper);
				 if (!hrBasStaffPostList.isEmpty()) {
					 HrBasOrganization basOrganization = hrBasOrganizationService.queryByYwjgdm(etcyxxxgl.getYxjgdm());
					 if (basOrganization == null) {
						 return Result.error("营销机构代码不正确！");
					 }
				 } else {
					 return Result.error("营销人工号不正确，或营销人不存在岗位信息！");
				 }
				 //查询是否夸机构移交，只有总行可以夸机构移交 本机构判断需要包括支行下辖分理处
				 HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByYwjgdm(etcyxxxgl.getYywdjgm());
				 if (hrBasOrganization != null) {
					 if("1".equals(hrBasOrganization.getSjzzbz())) {
						 if(!("1".equals(getLoginUser().getOrgCode()) && etcyxxxgl.getYywdjgm().equals(loginOrg.getYwjgdm()))) {
							 return Result.error("只能移交给自己机构");
						 }
					 } else {
						 HrBasOrganization sjzz = hrBasOrganizationService.queryByZzbz(hrBasOrganization.getSjzzbz());
						 if (sjzz != null) {
							 if(!("1".equals(getLoginUser().getOrgCode()) && loginOrg.getYwjgdm().equals(sjzz.getYwjgdm()))) {
								 return Result.error("只能移交给自己机构");
							 }
						 }
					 }
				 } else {
					 return Result.error("营销机构代码不正确！");
				 }
			 }

			 if(etcyxxxgl.getYxrgh().equals(check.getYxrgh())&&etcyxxxgl.getYxjgdm().equals(check.getYxjgdm())){
				 return Result.ok("修改成功");
			 }
			 //总部管理员不做限制，可以直接修改
			 if (!"1".equals(getLoginUser().getOrgCode())) {
				 //营销信息--为空，并且办理机构--本支行所属机构 ，办理时间--系统内ETC数据最近一个月度 ，可以不走审批流程
				 if ((StringUtils.isEmpty(check.getYxrgh()) || "无".equals(check.getYxrgh()) || "无".equals(check.getYxjgdm()) )
						 && check.getYywdjgm().equalsIgnoreCase(loginOrg.getYwjgdm())
						 && check.getKhrq().getTime()>=ycrq.getTime() && check.getKhrq().getTime()<=ymrq.getTime()) {
					 LambdaUpdateWrapper<Etcyxxxgl> updateWrapper = new LambdaUpdateWrapper<>();
					 updateWrapper.eq(Etcyxxxgl::getKhsfzh, check.getKhsfzh());
					 updateWrapper.eq(Etcyxxxgl::getKhrq, check.getKhrq());
					 updateWrapper.eq(Etcyxxxgl::getKhsj, check.getKhsj());
					 updateWrapper.set(Etcyxxxgl::getYxrgh, etcyxxxgl.getYxrgh());
					 updateWrapper.set(Etcyxxxgl::getYxjgdm, etcyxxxgl.getYxjgdm());
					 updateWrapper.set(Etcyxxxgl::getXgr, getLoginUser().getUsername());
					 updateWrapper.set(Etcyxxxgl::getXgsj, new Date());
					 etcyxxxglService.update(updateWrapper);
					 return Result.ok("修改成功");
				 } else {
				 	//审批流程
					 List<EtcyxxxglSpxx> spxxList = new ArrayList<>();
					 String businessNumber = "etcyxxxProcess";
					 String processId = actProcessService.findByProcessKeyAndLatest(businessNumber, true).getId();
					 EtcyxxxglSpxx spxx = new EtcyxxxglSpxx();
					 BeanUtils.copyProperties(check, spxx);
					 spxx.setYyxrgh(check.getYxrgh());
					 spxx.setYyxjgdm(check.getYxjgdm());
					 spxx.setXyxrgh(etcyxxxgl.getYxrgh());
					 spxx.setXyxjgdm(etcyxxxgl.getYxjgdm());
					 spxx.setSqsm("修改数据：修改营销人，进入审批流程");
					 spxx.setYjrq(check.getTjyf());
					 spxx.setLrsj(new Date());
					 spxx.setLrr(getUsername());
					 spxx.setLrbz(1);
					 spxx.setBusinessnumber(businessNumber);
					 spxx.setProcessid(processId);
					 spxxList.add(spxx);
					 etcyxxxglSpxxService.saveBatchNewTrans(spxxList);
					 // 提交申请流程
					 ActBusiness actBusiness = new ActBusiness();
					 // 删除标识(0 正常 1 已删除)
					 actBusiness.setDelFlag(0);
					 actBusiness.setApplyTime(new Timestamp(System.currentTimeMillis()));
					 actBusiness.setProcDefId(processId);
					 actBusiness.setTableId(businessNumber + ";" + processId);
					 // 结果状态(0 默认未提交 1 处理中 2 通过 3 驳回)
					 actBusiness.setResult(0);
					 // 处理状态(0 默认草稿 1 处理中 2 结束)
					 actBusiness.setStatus(0);
					 actBusiness.setTitle(getLoginUser().getRealname()+"发起的ETC营销人移交申请.");
					 actBusiness.setUserId(getLoginUser().getId());
					 actBusiness.setCreateBy(getLoginUser().getUsername());
					 actBusiness.setCreateTime(new Timestamp(System.currentTimeMillis()));
					 actBusinessService.save(actBusiness);
				 }
			 } else {
				 LambdaUpdateWrapper<Etcyxxxgl> updateWrapper = new LambdaUpdateWrapper<>();
				 updateWrapper.eq(Etcyxxxgl::getKhsfzh, check.getKhsfzh());
				 updateWrapper.eq(Etcyxxxgl::getKhrq, check.getKhrq());
				 updateWrapper.eq(Etcyxxxgl::getKhsj, check.getKhsj());
				 updateWrapper.set(Etcyxxxgl::getYxrgh, etcyxxxgl.getYxrgh());
				 updateWrapper.set(Etcyxxxgl::getYxjgdm, etcyxxxgl.getYxjgdm());
				 updateWrapper.set(Etcyxxxgl::getXgr, getLoginUser().getUsername());
				 updateWrapper.set(Etcyxxxgl::getXgsj, new Date());
				 etcyxxxglService.update(updateWrapper);
				 return Result.ok("修改成功");
			 }
		 } catch (Exception e) {
		 	e.printStackTrace();

		 }
		 return Result.ok("编辑成功!");
	 }
	
	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "ETC营销信息管理-通过id删除")
	@ApiOperation(value="ETC营销信息管理-通过id删除", notes="ETC营销信息管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("khsfzh")String khsfzh,@Param("khsj")String khsj,@Param("khrq")String khrq) {
		QueryWrapper<Etcyxxxgl> queryWrapper = new QueryWrapper<Etcyxxxgl>();
		queryWrapper.eq("khsfzh",khsfzh);
		queryWrapper.eq("khsj",khsj);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			queryWrapper.eq("khrq",sdf.parse(khrq));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		etcyxxxglService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "ETC营销信息管理-批量删除")
	@ApiOperation(value="ETC营销信息管理-批量删除", notes="ETC营销信息管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.etcyxxxglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	 /**
	  * 批量删除
	  *
	  * @param tjyfStr
	  * @return
	  */
	 @AutoLog(value = "ETC营销信息管理-批量删除")
	 @ApiOperation(value="ETC营销信息管理-批量删除", notes="ETC营销信息管理-批量删除")
	 @DeleteMapping(value = "/deleteBatchByTjyf")
	 public Result<?> deleteBatchByTjyf(@RequestParam(name="tjyf",required=true) String tjyfStr) {
	 	 Date tjyf = DateUtil.parseDateFormat(tjyfStr, "yyyy-MM-dd");
		 //先删除临时表的数据
		 QueryWrapper<EtcyxxxglTmp> deleteTmpWrapper = new QueryWrapper<>();
		 deleteTmpWrapper.eq("tjyf", tjyf);
		 etcyxxxglTmpService.remove(deleteTmpWrapper);
		 //将数据保存到临时表
		 QueryWrapper<Etcyxxxgl> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("tjyf", tjyf);
		 List<Etcyxxxgl> etcyxxxglList = etcyxxxglService.list(queryWrapper);
		 List<EtcyxxxglTmp> etcyxxxglTmpList = etcyxxxglList.stream().map(e -> {
		 	EtcyxxxglTmp etcyxxxglTmp = new EtcyxxxglTmp();
		 	BeanUtils.copyProperties(e, etcyxxxglTmp);
		 	etcyxxxglTmp.setXgr(getUsername());
		 	etcyxxxglTmp.setXgsj(new Date());
		 	return etcyxxxglTmp;
		 }).collect(Collectors.toList());
		 etcyxxxglTmpService.saveBatch(etcyxxxglTmpList);
		 //删除正式表的数据
		 QueryWrapper<Etcyxxxgl> deleteWrapper = new QueryWrapper<>();
		 deleteWrapper.eq("tjyf", tjyf);
		 etcyxxxglService.remove(deleteWrapper);
		 return Result.ok("批量删除成功！");
	 }
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ETC营销信息管理-通过id查询")
	@ApiOperation(value="ETC营销信息管理-通过id查询", notes="ETC营销信息管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VEtcyxxxgl etcyxxxgl = vEtcyxxxglService.getById(id);
		return Result.ok(etcyxxxgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param etcyxxxgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, VEtcyxxxgl etcyxxxgl) {
      return super.exportXls(request, etcyxxxgl, VEtcyxxxgl.class, "ETC营销信息管理");
  }

	 /**
	  * 导出excel
	  *
	  * @param request
	  * @param etcyxxxglTmp
	  */
	 @RequestMapping(value = "/exportDeleteDataXls")
	 public ModelAndView exportDeleteDataXls(HttpServletRequest request, EtcyxxxglTmp etcyxxxglTmp) {
		 // Step.1 组装查询条件
		 QueryWrapper<EtcyxxxglTmp> queryWrapper = QueryGenerator.initQueryWrapper(etcyxxxglTmp, request.getParameterMap());
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
		 //List<T> pageList = service.list(queryWrapper);
		 //List<T> exportList = null;
		 List<EtcyxxxglTmp> exportList = etcyxxxglTmpService.list(queryWrapper);

		 // Step.3 AutoPoi 导出Excel
		 String title = "ETC营销批量删除数据";
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, EtcyxxxglTmp.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
	 }

	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 return super.exportTemplateXls(EtcyxxxglImport.class, "ETC营销信息导入模板");
	 }

	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportYxrTemplateXls")
	 public ModelAndView exportYxrTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 return super.exportTemplateXls(EtcyxxxglYxrImport.class, "ETC营销人信息导入模板");
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
			 params.setVerifyHanlder(etcyxxxglImportVerify);
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<EtcyxxxglImport> importResult = ExcelImportUtil.importExcelVerify(file, EtcyxxxglImport.class, params);
				 List<EtcyxxxglImport> list = importResult.getList();
				 List<Etcyxxxgl> insertList = list.stream().map(e -> {
					 Etcyxxxgl etcyxxxgl = new Etcyxxxgl();
					 BeanUtils.copyProperties(e, etcyxxxgl);
					 etcyxxxgl.setTjyf(DateUtil.parseDateFormat(e.getTjyfStr() + "-01", "yyyy-MM-dd"));
					 return etcyxxxgl;
				 }).collect(Collectors.toList());
				 etcyxxxglService.saveBatch(insertList);
				 obj.put("filePath", filePath);
				 fos = new FileOutputStream(baseFilePath);
				 importResult.getWorkbook().write(fos);
				 fos.flush();
				 fos.close();

				 //调用存储过程提取数据
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

	 /**
	  * 通过excel导入数据
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/importYxrExcel", method = RequestMethod.POST)
	 public Result<?> importYxrExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
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
			 params.setVerifyHanlder(etcyxxxglYxrImportVerify);
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<EtcyxxxglYxrImport> importResult = ExcelImportUtil.importExcelVerify(file, EtcyxxxglYxrImport.class, params);
				 List<EtcyxxxglYxrImport> list = importResult.getList();
				 obj.put("filePath", filePath);
				 fos = new FileOutputStream(baseFilePath);
				 importResult.getWorkbook().write(fos);
				 fos.flush();
				 fos.close();

				 //调用存储过程提取数据
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

	 /**
	  * 获取待移交的数据
	  * @param etcyxxxgl
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @RequestMapping(value = "/transfer", method = RequestMethod.GET)
	 public Result<?> transfer(VEtcyxxxgl etcyxxxgl,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		 if (StringUtils.isEmpty(etcyxxxgl.getYxrgh())) {
		 	 return Result.error("请输入营销人工号");
		 }
		 QueryWrapper<VEtcyxxxgl> queryWrapper = QueryGenerator.initQueryWrapper(etcyxxxgl, req.getParameterMap());
		 IPage pageList=org.cmms.common.utils.PageUtil.toPage(IVEtcyxxxglService.class,vEtcyxxxglService,pageNo,pageSize,queryWrapper,"tjyf","cphm","bdzkh");

		 return Result.ok(pageList);
	 }

	 @RequestMapping(value = "/transfer/save", method = RequestMethod.POST)
	 public Result<?> transferSave(@RequestBody EtcyxxxglTransfer etcyxxxglTransfer) {
		 VEtcyxxxgl vetcyxxxgl = new VEtcyxxxgl();
		 BeanUtils.copyProperties(etcyxxxglTransfer, vetcyxxxgl);
		 String sqsm = etcyxxxglTransfer.getSqsm();
		 String jgdm = etcyxxxglTransfer.getZzbz();
		 String yggh = etcyxxxglTransfer.getYggh();
		 String yjrq = etcyxxxglTransfer.getYjrq();
		 String yjlx = etcyxxxglTransfer.getYjlx();
		 if (StringUtils.isEmpty(yggh)) {
			 return Result.error("接收人工号不能为空！");
		 }
		 if (StringUtils.isEmpty(yjrq)) {
			 return Result.error("移交日期不能为空！");
		 }
		 if (StringUtils.isEmpty(yjlx)) {
			 return Result.error("移交类型不能为空！");
		 }
		 List<VEtcyxxxgl> etcyxxxglList = new ArrayList<>();
		 //将数据保存到审批信息表
		 if("1".equals(yjlx)) {
		 	//全部移交
			//查询出所有数据存入审批信息表
			 Map<String, String> map = JSON.parseObject(JSON.toJSONString(etcyxxxglTransfer), Map.class);
			 Map<String, String[]> queryMap = new HashMap<>();
			 for (String key : map.keySet()) {
			 	 if(map.get(key) instanceof String) {
					 queryMap.put(key, new String[]{map.get(key)});
				 }
			 }
			 QueryWrapper<VEtcyxxxgl> queryWrapper = QueryGenerator.initQueryWrapper(vetcyxxxgl, queryMap);
			 etcyxxxglList = vEtcyxxxglService.list(queryWrapper);
		 } else {
		 	 //部分移交
			 //根据选择的数据进行移交
			 JSONArray jsonArray = etcyxxxglTransfer.getSelectionRows();
			 for (int i = 0; i < jsonArray.size(); i++) {
			 	 JSONObject selectRow = jsonArray.getJSONObject(i);
			 	 selectRow.put("tjyf", selectRow.getString("tjyf") + "-01");
				 VEtcyxxxgl etcyxxxgl = JSONObject.toJavaObject(selectRow, VEtcyxxxgl.class);
			 	 BeanUtils.copyProperties(selectRow, etcyxxxgl);
				 etcyxxxglList.add(etcyxxxgl);
			 }
		 }
		 if (etcyxxxglList.isEmpty()) {
			 return Result.error("未找到需要移交的数据！");
		 }
		 List<EtcyxxxglSpxx> spxxList = new ArrayList<>();
		 String businessNumber = "etcyxxxProcess";
		 String processId = actProcessService.findByProcessKeyAndLatest(businessNumber, true).getId();

		 etcyxxxglList.forEach(e -> {
			 EtcyxxxglSpxx spxx = new EtcyxxxglSpxx();
			 BeanUtils.copyProperties(e, spxx);
			 spxx.setYyxrgh(e.getYxrgh());
			 spxx.setYyxjgdm(e.getYxjgdm());
			 spxx.setXyxrgh(yggh);
			 spxx.setXyxjgdm(jgdm);
			 spxx.setSqsm(sqsm);
			 spxx.setYjrq(DateUtil.parseDateFormat(yjrq, "yyyy-MM-dd"));
			 spxx.setLrsj(new Date());
			 spxx.setLrr(getUsername());
			 spxx.setLrbz(1);
			 spxx.setBusinessnumber(businessNumber);
			 spxx.setProcessid(processId);
			 spxxList.add(spxx);
		 });
		 etcyxxxglSpxxService.saveBatchNewTrans(spxxList);
		 // 提交申请流程
		 ActBusiness actBusiness = new ActBusiness();
		 // 删除标识(0 正常 1 已删除)
		 actBusiness.setDelFlag(0);
		 actBusiness.setApplyTime(new Timestamp(System.currentTimeMillis()));
		 actBusiness.setProcDefId(processId);
		 actBusiness.setTableId(businessNumber + ";" + processId);
		 // 结果状态(0 默认未提交 1 处理中 2 通过 3 驳回)
		 actBusiness.setResult(0);
		 // 处理状态(0 默认草稿 1 处理中 2 结束)
		 actBusiness.setStatus(0);
		 actBusiness.setTitle(getLoginUser().getRealname()+"发起的ETC营销人移交申请.");
		 actBusiness.setUserId(getLoginUser().getId());
		 actBusiness.setCreateBy(getLoginUser().getUsername());
		 actBusiness.setCreateTime(new Timestamp(System.currentTimeMillis()));
		 actBusinessService.save(actBusiness);
		 return Result.ok();
	 }

	 @RequestMapping(value = "/transfer/detail", method = RequestMethod.GET)
	 public Result<?> transferSave(@RequestParam(name="tableId") String tableId,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
	 	 String[] strs = tableId.split(";");
		 QueryWrapper<EtcyxxxglSpxx> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("businessnumber", strs[0]);
		 queryWrapper.eq("processid", strs[1]);
		 IPage pageList=org.cmms.common.utils.PageUtil.toPage(IEtcyxxxglSpxxService.class,etcyxxxglSpxxService,pageNo,pageSize,queryWrapper,"khsfzh","cphm","bdzkh");
		 return Result.ok(pageList);
	 }
}
