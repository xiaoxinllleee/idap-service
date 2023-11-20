package org.cmms.modules.workplace.controller;

import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtils;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.entity.VsysUserRole;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.system.service.ISysUserRoleService;
import org.cmms.modules.system.service.ISysUserService;
import org.cmms.modules.workplace.service.*;
import lombok.extern.slf4j.Slf4j;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

/**
 * @Description: 工作台
 * @Author: Penghr
 * @Date:   2020-08-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="工作台")
@RestController
@RequestMapping("/workplace")
public class WorkPlaceController implements Job {
	 @Autowired
	 private IWorkPlaceXendSjmxService iWorkPlaceXendSjmxService;
	 @Autowired
	 private IWorkPlaceGrdkSjmxService iworkPlaceGrdkSjmxService;
	 @Autowired
	 private ISysUserService iSysUserService;
	 @Autowired
	 private ISysUserRoleService iSysUserRoleService;
	 @Autowired
	 private IHrBasOrganizationService iHrBasOrganizationService;
	 @Autowired
	 private IWorkplaceCdkkhsjhzKhjlService iWorkplaceCdkkhsjhzKhjlService;
	 @Autowired
	 private IWorkplaceCdkkhsjhzZhhzService iWorkplaceCdkkhsjhzZhhzService;
	 @Autowired
	 private IWorkPlaceSystemVersionService iWorkPlaceSystemVersionService;
	 @Autowired
	 private ISysDictService sysDictService;
	 @Value("${liuyang.testsystem:false}")
	 private String testsystem;

	 /**
	  * 工作台：根据"用户ID"&"所属组织标识"获取"角色名称"&"组织名称"
	  * @return
	  */
	 @RequestMapping(value = "/getUserRoleNameAndOrgName", method = RequestMethod.GET)
	 public Result<?> getUserRoleNameAndOrgName() {
		 Result<JSONObject> result = new Result<JSONObject>();
		 JSONObject jsonObject = new JSONObject();
		 try {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 SysUser sysUser = iSysUserService.getById(loginUser.getId());
			 List<VsysUserRole> vsysUserRole = iSysUserRoleService.findByUserId(loginUser.getId());
			 HrBasOrganization hrBasOrganization = iHrBasOrganizationService.queryByZzbz(loginUser.getOrgCode());
			 if (vsysUserRole == null || vsysUserRole.size() == 0 || hrBasOrganization == null) {
				 return result.ok("该用户暂无分配角色信息或机构信息！");
			 } else {
			 	 // 员工工号
			 	 jsonObject.put("workNo", sysUser.getWorkNo());
			 	 // 角色名称
				 jsonObject.put("roleName", vsysUserRole.get(0).getRoleName());
				 jsonObject.put("roleId", vsysUserRole.get(0).getRoleId());
				 // 机构代码
				 jsonObject.put("orgCode", loginUser.getOrgCode());
				 // 机构名称
				 jsonObject.put("orgName", hrBasOrganization.getZzjc());
				 return result.ok(jsonObject);
			 }
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 result.error500("操作失败");
		 }
		 return  result;
	 }

	 /**
	  * 根据员工工号获取工作台页面右上角数据
	  * @param yggh
	  * @return
	  */
	 @RequestMapping(value = "/getRightTopDataByYgghForKhjl", method = RequestMethod.GET)
	 public Result<?> getRightTopDataByYgghForKhjl(@RequestParam(value = "yggh", required = true) String yggh) {
		 Result<JSONObject> result = new Result<JSONObject>();
		 JSONObject jsonObject = new JSONObject();
		 Map<String, Object> khjlDataMap = new HashMap<>();
		 try {
			 khjlDataMap = iWorkplaceCdkkhsjhzKhjlService.getCdkkhsjForKhjlByYggh(yggh);
			 if (khjlDataMap != null) {
				 jsonObject.put("ckye", khjlDataMap.get("CKYE"));
				 jsonObject.put("qzdqckye", khjlDataMap.get("QZDQCKYE"));
				 jsonObject.put("dnckrp", khjlDataMap.get("DNCKRP"));
				 jsonObject.put("ckkhs", khjlDataMap.get("CKKHS"));
				 jsonObject.put("dkye", khjlDataMap.get("DKYE"));
				 jsonObject.put("bnbldkye", khjlDataMap.get("BNBLDKYE"));
				 jsonObject.put("bwbldkye", khjlDataMap.get("BWBLDKYE"));
				 jsonObject.put("dkkhs", khjlDataMap.get("DKKHS"));
				 return result.ok(jsonObject);
			 }
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 result.error500("查询数据失败！");
		 }
		 return result;
	 }

	 /**
	  * 根据员工工号获取工作台页面左边区域小额农贷数据
	  * @param yggh
	  * @return
	  */
	 @RequestMapping(value = "/getLeftXendDataForKhjl", method = RequestMethod.GET)
	 public Result<?> getLeftXendDataForKhjl(@RequestParam(value = "yggh", required = true) String yggh) {
		 Result<JSONObject> result = new Result<JSONObject>();
		 JSONObject jsonObject = new JSONObject();
		 try {
			 int cjkhs  = iWorkPlaceXendSjmxService.getXendCjkhsForKhjl(yggh);
			 int sxkhs  = iWorkPlaceXendSjmxService.getXendSxkhsForKhjl(yggh);
			 int yxkhs  = iWorkPlaceXendSjmxService.getXendYxkhsForKhjl(yggh);
			 int wsxkhs = iWorkPlaceXendSjmxService.getXendWsxKhsForKhjl(yggh);
			 double sxje = iWorkPlaceXendSjmxService.getXendSxjeForKhjl(yggh);
			 double yxje = iWorkPlaceXendSjmxService.getXendYxjeForKhjl(yggh);
			 jsonObject.put("cjkhs", cjkhs);
			 jsonObject.put("sxkhs", sxkhs);
			 jsonObject.put("yxkhs", yxkhs);
			 jsonObject.put("wsxkhs", wsxkhs);
			 jsonObject.put("sxje", sxje);
			 jsonObject.put("yxje", yxje);
			 return result.ok(jsonObject);
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 result.error500("查询数据失败！");
		 }
		 return result;
	 }

	 /**
	  * 根据员工工号获取工作台页面左边区域个人贷款数据
	  * @param yggh
	  * @return
	  */
	 @RequestMapping(value = "/getLeftGrdkDataForKhjl", method = RequestMethod.GET)
	 public Result<?> getLeftGrdkDataForKhjl(@RequestParam(value = "yggh", required = true) String yggh) {
		 Result<JSONObject> result = new Result<JSONObject>();
		 JSONObject jsonObject = new JSONObject();
		 try {
			 int cjkhs  = iworkPlaceGrdkSjmxService.getGrdkCjkhsForKhjl(yggh);
			 int sxkhs  = iworkPlaceGrdkSjmxService.getGrdkSxkhsForKhjl(yggh);
			 int yxkhs  = iworkPlaceGrdkSjmxService.getGrdkYxkhsForKhjl(yggh);
			 int wsxkhs = iworkPlaceGrdkSjmxService.getGrdkWsxkhsForKhjl(yggh);
			 double sxje = iworkPlaceGrdkSjmxService.getGrdkSxjeForKhjl(yggh);
			 double yxje = iworkPlaceGrdkSjmxService.getGrdkYxjeForKhjl(yggh);
			 jsonObject.put("cjkhs", cjkhs);
			 jsonObject.put("sxkhs", sxkhs);
			 jsonObject.put("yxkhs", yxkhs);
			 jsonObject.put("wsxkhs", wsxkhs);
			 jsonObject.put("sxje", sxje);
			 jsonObject.put("yxje", yxje);
			 return result.ok(jsonObject);
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 result.error500("查询数据失败！");
		 }
		 return result;
	 }

	 /**
	  * 根据员工工号获取工作台饼状图小额农贷客户等级数据
	  * @param yggh
	  * @return
	  */
	 @RequestMapping(value = "/echartsByXendForKhjl", method = RequestMethod.GET)
	 public Result<JSONArray> getEchartsByXendForKhjl(@RequestParam(value = "yggh", required = true) String yggh) {
		Result<JSONArray> result = new Result<JSONArray>();
		 JSONArray jsonArray = new JSONArray();
		List<Map> list = iWorkPlaceXendSjmxService.queryXendKhPddjForKhjl(yggh);
		for (int i = 0; i < list.size(); i++) {
			JSONObject jsonObject = new JSONObject(new LinkedHashMap());
			jsonObject.put("name", list.get(i).get("SXDJ"));
			jsonObject.put("value", list.get(i).get("DJRS"));
			jsonArray.add(jsonObject);
		}
		result.setSuccess(true);
		result.setResult(jsonArray);
		return result;
	 }

	 /**
	  * 根据员工工号获取工作台饼状图个人贷款客户等级数据
	  * @param yggh
	  * @return
	  */
	 @RequestMapping(value = "/echartsByGrdkForKhjl", method = RequestMethod.GET)
	 public Result<JSONArray> getEchartsByGrdkForKhjl(@RequestParam(value = "yggh", required = true) String yggh) {
		 Result<JSONArray> result = new Result<JSONArray>();
		 JSONArray jsonArray = new JSONArray();
		 List<Map> list = iworkPlaceGrdkSjmxService.queryGrdkKhPddjForKhjl(yggh);
		 for (int i = 0; i < list.size(); i++) {
			 JSONObject jsonObject = new JSONObject(new LinkedHashMap());
			 jsonObject.put("name", list.get(i).get("SXDJ"));
			 jsonObject.put("value", list.get(i).get("DJRS"));
			 jsonArray.add(jsonObject);
		 }
		 result.setSuccess(true);
		 result.setResult(jsonArray);
		 return result;
	 }


	 /**
	  * 根据组织标识获取工作台页面右上角数据
	  * @param zzbz
	  * @return
	  */
	 @RequestMapping(value = "/getRightTopDataByYgghForZhhz", method = RequestMethod.GET)
	 public Result<?> getRightTopDataByYgghForZhhz(@RequestParam(value = "zzbz", required = true) String zzbz) {
		 Result<JSONObject> result = new Result<JSONObject>();
		 JSONObject jsonObject = new JSONObject();
		 Map<String, Object> zhhzDataMap = new HashMap<>();
		 try {
			 zhhzDataMap = iWorkplaceCdkkhsjhzZhhzService.getCdkkhsjForZhhzByZzbz(zzbz);
			 if (zhhzDataMap != null) {
				 jsonObject.put("ckye", zhhzDataMap.get("CKYE"));
				 jsonObject.put("qzdqckye", zhhzDataMap.get("QZDQCKYE"));
				 jsonObject.put("dnckrp", zhhzDataMap.get("DNCKRP"));
				 jsonObject.put("ckkhs", zhhzDataMap.get("CKKHS"));
				 jsonObject.put("dkye", zhhzDataMap.get("DKYE"));
				 jsonObject.put("bnbldkye", zhhzDataMap.get("BNBLDKYE"));
				 jsonObject.put("bwbldkye", zhhzDataMap.get("BWBLDKYE"));
				 jsonObject.put("dkkhs", zhhzDataMap.get("DKKHS"));
				 return result.ok(jsonObject);
			 }
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 result.error500("查询数据失败！");
		 }
		 return result;
	 }

	 /**
	  * 根据组织标识获取工作台页面左边区域小额农贷数据
	  * @param zzbz
	  * @return
	  */
	 @RequestMapping(value = "/getLeftXendDataForZhhz", method = RequestMethod.GET)
	 public Result<?> getLeftXendDataForZhhz(@RequestParam(value = "zzbz", required = true) String zzbz) {
		 Result<JSONObject> result = new Result<JSONObject>();
		 JSONObject jsonObject = new JSONObject();
		 try {
			 int cjkhs  = iWorkPlaceXendSjmxService.getXendCjkhsForZhhz(zzbz);
			 int sxkhs  = iWorkPlaceXendSjmxService.getXendSxkhsForZhhz(zzbz);
			 int yxkhs  = iWorkPlaceXendSjmxService.getXendYxkhsForZhhz(zzbz);
			 int wsxkhs = iWorkPlaceXendSjmxService.getXendWsxKhsForZhhz(zzbz);
			 double sxje = iWorkPlaceXendSjmxService.getXendSxjeForZhhz(zzbz);
			 double yxje = iWorkPlaceXendSjmxService.getXendYxjeForZhhz(zzbz);
			 jsonObject.put("cjkhs", cjkhs);
			 jsonObject.put("sxkhs", sxkhs);
			 jsonObject.put("yxkhs", yxkhs);
			 jsonObject.put("wsxkhs", wsxkhs);
			 jsonObject.put("sxje", sxje);
			 jsonObject.put("yxje", yxje);
			 return result.ok(jsonObject);
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 result.error500("查询数据失败！");
		 }
		 return result;
	 }

	 /**
	  * 根据组织标识获取工作台页面左边区域个人贷款数据
	  * @param zzbz
	  * @return
	  */
	 @RequestMapping(value = "/getLeftGrdkDataForZhhz", method = RequestMethod.GET)
	 public Result<?> getLeftGrdkDataForZhhz(@RequestParam(value = "zzbz", required = true) String zzbz) {
		 Result<JSONObject> result = new Result<JSONObject>();
		 JSONObject jsonObject = new JSONObject();
		 try {
			 int cjkhs  = iworkPlaceGrdkSjmxService.getGrdkCjkhsForZhhz(zzbz);
			 int sxkhs  = iworkPlaceGrdkSjmxService.getGrdkSxkhsForZhhz(zzbz);
			 int yxkhs  = iworkPlaceGrdkSjmxService.getGrdkYxkhsForZhhz(zzbz);
			 int wsxkhs = iworkPlaceGrdkSjmxService.getGrdkWsxkhsForZhhz(zzbz);
			 double sxje = iworkPlaceGrdkSjmxService.getGrdkSxjeForZhhz(zzbz);
			 double yxje = iworkPlaceGrdkSjmxService.getGrdkYxjeForZhhz(zzbz);
			 jsonObject.put("cjkhs", cjkhs);
			 jsonObject.put("sxkhs", sxkhs);
			 jsonObject.put("yxkhs", yxkhs);
			 jsonObject.put("wsxkhs", wsxkhs);
			 jsonObject.put("sxje", sxje);
			 jsonObject.put("yxje", yxje);
			 return result.ok(jsonObject);
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 result.error500("查询数据失败！");
		 }
		 return result;
	 }

	 /**
	  * 根据组织标识获取工作台饼状图小额农贷客户等级数据
	  * @param zzbz
	  * @return
	  */
	 @RequestMapping(value = "/echartsByXendForZhhz", method = RequestMethod.GET)
	 public Result<JSONArray> getEchartsByXendForZhhz(@RequestParam(value = "zzbz", required = true) String zzbz) {
		 Result<JSONArray> result = new Result<JSONArray>();
		 JSONArray jsonArray = new JSONArray();
		 List<Map> list = iWorkPlaceXendSjmxService.queryXendKhPddjForZhhz(zzbz);
		 for (int i = 0; i < list.size(); i++) {
			 JSONObject jsonObject = new JSONObject(new LinkedHashMap());
			 jsonObject.put("name", list.get(i).get("SXDJ"));
			 jsonObject.put("value", list.get(i).get("DJRS"));
			 jsonArray.add(jsonObject);
		 }
		 result.setSuccess(true);
		 result.setResult(jsonArray);
		 return result;
	 }
	 /**
	  * 根据组织标识获取工作台饼状图个人贷款客户等级数据
	  * @param zzbz
	  * @return
	  */
	 @RequestMapping(value = "/echartsByGrdkForZhhz", method = RequestMethod.GET)
	 public Result<JSONArray> getEchartsByGrdkForZhhz(@RequestParam(value = "zzbz", required = true) String zzbz) {
		 Result<JSONArray> result = new Result<JSONArray>();
		 JSONArray jsonArray = new JSONArray();
		 List<Map> list = iworkPlaceGrdkSjmxService.queryGrdkKhPddjForZhhz(zzbz);
		 for (int i = 0; i < list.size(); i ++) {
			 JSONObject jsonObject = new JSONObject(new LinkedHashMap());
			 jsonObject.put("name", list.get(i).get("SXDJ"));
			 jsonObject.put("value", list.get(i).get("DJRS"));
			 jsonArray.add(jsonObject);
		 }
		 result.setSuccess(true);
		 result.setResult(jsonArray);
		 return result;
	 }

	 @Override
	 public void execute(JobExecutionContext context) throws JobExecutionException {
		iWorkPlaceXendSjmxService.WorkPlaceAutoMission();
		log.info(String.format("自动执行工作台数据提取："+ DateUtils.getTimestamp()));
	 }

	/**
	 * 获取系统最新版本号
	 *
	 * @return
	 */
	@GetMapping(value = "/getlatestversion")
	public Result<?> getVersion() {
		String qydm = sysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101001");
		try {
			if ("true".equals(testsystem)) {
				String Version = iWorkPlaceSystemVersionService.getLatestTestVersion(qydm);
				log.info("当前系统版本号：【 " + Version + " 】");
				return Result.ok("版本号",Version);
			}else {
				String Version = iWorkPlaceSystemVersionService.getLatestProdVersion(qydm);
				log.info("当前系统版本号：【 " + Version + " 】");
				return Result.ok("版本号",Version);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统版本号查询失败！", e);
			return Result.error("系统错误，请联系系统管理员！" + e.getMessage());
		}
	}


 }
