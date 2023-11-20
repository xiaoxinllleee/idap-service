package org.cmms.modules.performance.loancustomer.dkkhyj.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.sf.saxon.trans.SymbolicName;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.activiti.entity.ActBusiness;
import org.cmms.modules.activiti.service.ActBusinessService;
import org.cmms.modules.performance.depositcustomer.ckkhghyj.entity.Ckkhghyjxx;
import org.cmms.modules.performance.loancustomer.dkkhspxx.entity.Dkkhspxx;
import org.cmms.modules.performance.loancustomer.dkkhspxx.service.IDkkhspxxService;
import org.cmms.modules.performance.loancustomer.dkkhyj.entity.Dkkhyjxx;
import org.cmms.modules.performance.loancustomer.dkkhyj.service.IDkkhyjxxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 贷款客户移交
 * @Author: jeecg-boot
 * @Date:   2023-04-03
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款客户移交")
@RestController
@RequestMapping("/dkkhyj/dkkhyjxx")
public class DkkhyjxxController extends JeecgController<Dkkhyjxx, IDkkhyjxxService> {
	 @Autowired
	 private IDkkhyjxxService dkkhyjxxService;
	 @Autowired
	 private ActBusinessService actBusinessService;
	 @Autowired
	 private ISysUserService iSysUserService;
	 @Autowired
	 private IDkkhspxxService dkkhspxxService;

	 /**
	 * 分页列表查询
	 *
	 * @param dkkhyjxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款客户移交-分页列表查询")
	@ApiOperation(value="贷款客户移交-分页列表查询", notes="贷款客户移交-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dkkhyjxx dkkhyjxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dkkhyjxx> queryWrapper = QueryGenerator.initQueryWrapper(dkkhyjxx, req.getParameterMap());
		Page<Dkkhyjxx> page = new Page<Dkkhyjxx>(pageNo, pageSize);
		IPage<Dkkhyjxx> pageList = dkkhyjxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param dkkhyjxx
	 * @return
	 */
	@AutoLog(value = "贷款客户移交-添加")
	@ApiOperation(value="贷款客户移交-添加", notes="贷款客户移交-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dkkhyjxx dkkhyjxx) {
		dkkhyjxxService.save(dkkhyjxx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param dkkhyjxx
	 * @return
	 */
	@AutoLog(value = "贷款客户移交-编辑")
	@ApiOperation(value="贷款客户移交-编辑", notes="贷款客户移交-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dkkhyjxx dkkhyjxx) {
		dkkhyjxxService.updateById(dkkhyjxx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款客户移交-通过id删除")
	@ApiOperation(value="贷款客户移交-通过id删除", notes="贷款客户移交-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkkhyjxxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款客户移交-批量删除")
	@ApiOperation(value="贷款客户移交-批量删除", notes="贷款客户移交-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkkhyjxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款客户移交-通过id查询")
	@ApiOperation(value="贷款客户移交-通过id查询", notes="贷款客户移交-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dkkhyjxx dkkhyjxx = dkkhyjxxService.getById(id);
		return Result.ok(dkkhyjxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkkhyjxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dkkhyjxx dkkhyjxx) {
      return super.exportXls(request, dkkhyjxx, Dkkhyjxx.class, "贷款客户移交");
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
      return super.importExcel(request, response, Dkkhyjxx.class);
  }



	 /**
	  * 贷款客户管户移交
	  * @param jsonObject
	  * @return
	  */
	 @AutoLog(value = "管户移交")
	 @ApiOperation(value="管户移交", notes="管户移交")
	 @PostMapping(value = "/ghTransfer")
	 public Result<?> ghTransfer(@RequestBody JSONObject jsonObject) {
		 String uuid = IdUtil.simpleUUID();
		 String processId = dkkhyjxxService.getProcessIdByProcessKey("dkkhyj");
		 // 接收人
		 String receiver = jsonObject.getString("yggh");
		 // 申请说明
		 String remark   = jsonObject.getString("remark");
		 // 移交日期
		 String transferDateStr = jsonObject.getString("transferDate");
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 // 移交日期
		 Date transferDate = null;
		 try {
			 transferDate = simpleDateFormat.parse(transferDateStr);
		 } catch (ParseException parseException) {
			 parseException.printStackTrace();
		 }
		 // 移交类型
		 String yjfs = jsonObject.getString("yjfs");
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 SysUser sysUser = iSysUserService.getUserByName(loginUser.getUsername());
		 List<Dkkhyjxx> dkkhghyjxxList = new ArrayList<>();
		 try {
			 if ("1".equals(yjfs)) {
				 //批量移交
				 //查询出移交清单
				 QueryWrapper<Dkkhyjxx> queryWrapper = new QueryWrapper<>();
				 queryWrapper.eq("ghr", getWorkNo());
				 dkkhghyjxxList = dkkhyjxxService.list(queryWrapper);

			 } else if ("2".equals(yjfs)) {
				 //选择移交
				 //获取选择的列表
				 JSONArray jsonArray = jsonObject.getJSONArray("ids");
				 if (jsonArray == null || jsonArray.isEmpty()) {
					 return Result.error("未选择移交的客户信息！");
				 }
				 List<String> ids = jsonArray.toJavaList(String.class);

				 //根据id获取所有的客户清单
				 dkkhghyjxxList = dkkhyjxxService.getListByIds(ids);
			 }
			 List<Dkkhspxx> DkkhspxxList = new ArrayList<>();
			 for (int i = 0; i < dkkhghyjxxList.size(); i++) {
				 Dkkhyjxx form = dkkhghyjxxList.get(i);
				 // Step1. 审批信息
				 Dkkhspxx spxx = new Dkkhspxx();
				 spxx.setId(form.getId());
				 spxx.setBusinessNumber("dkkhyj");
				 spxx.setTableId(uuid);
				 spxx.setProcessId(processId);
				 // 流程状态(0 未提交 1 处理中 2 已结束)
				 spxx.setProcessStatus("0");
				 spxx.setJgdm(form.getJgdm());
				 spxx.setKhbh(form.getKhbh());
				 spxx.setKhmc(form.getKhmc());
				 spxx.setZjhm(form.getZjhm());
				 spxx.setKhlx(form.getKhlx());
				 spxx.setHth(form.getHth());
				 spxx.setHtje(form.getHtje());
				 spxx.setHtye(form.getHtye());
				 spxx.setCpxx(form.getCpxx());
				 spxx.setZzhtffrq(form.getZzhtffrq());
				 spxx.setZzhtdqrq(form.getZzhtdqrq());
				 // 业务类型(2管户移交 4包收移交)
				 spxx.setYwlx(2);

				 spxx.setYghr(sysUser.getWorkNo());
				 spxx.setYghbl(form.getGhbl());
				 spxx.setGhr(receiver);
				 spxx.setGhbl(form.getGhbl());
				 spxx.setYjrq(transferDate);
				 spxx.setSqsm(remark);
				 // 录入标识(0 导入 1 录入 2 修改)
				 spxx.setLrbz("1");
				 DkkhspxxList.add(spxx);
			 }
			 dkkhspxxService.saveBatch(DkkhspxxList);
			 // Step2. 申请流程
			 ActBusiness actBusiness = new ActBusiness();
			 // 删除标识(0 正常 1 已删除)
			 actBusiness.setDelFlag(0);
			 actBusiness.setApplyTime(new Timestamp(System.currentTimeMillis()));
			 actBusiness.setProcDefId(processId);
			 // 结果状态(0 默认未提交 1 处理中 2 通过 3 驳回)
			 actBusiness.setResult(0);
			 // 处理状态(0 默认草稿 1 处理中 2 结束)
			 actBusiness.setStatus(0);
			 actBusiness.setTableId(uuid);
			 actBusiness.setTitle(sysUser.getRealname()+"发起的贷款客户管户移交申请.");
			 actBusiness.setUserId(sysUser.getId());
			 actBusiness.setCreateBy(sysUser.getUsername());
			 actBusiness.setCreateTime(new Timestamp(System.currentTimeMillis()));
			 actBusinessService.save(actBusiness);
		 } catch (Exception exception) {
			 log.error("移交失败！", exception);
			 return Result.error("移交失败！请联系管理员处理！");
		 }

		 return Result.ok("移交成功，进入待审批状态，请到\"我的申请\"提交审批申请！");
	 }



	 /**
	  * 管户待移交客户信息-分页列表查询
	  * @param
	  * @param pageNo
	  * @param pageSize
	  * @param request
	  * @return
	  */
	 @AutoLog(value = "待移交客户信息-分页列表查询")
	 @ApiOperation(value = "待移交客户信息-分页列表查询", notes = "待移交客户信息-分页列表查询")
	 @GetMapping(value = "/ghTransferList")
	 public Result<?> ghTransferList(Dkkhyjxx dkkhyjxx,
									   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
									   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
									   HttpServletRequest request) {
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 dkkhyjxx.setGhr(getWorkNo());
		 dkkhyjxx.setGhlx(2);
		 QueryWrapper<Dkkhyjxx> queryWrapper = QueryGenerator.initQueryWrapper(dkkhyjxx, request.getParameterMap());
		 queryWrapper.orderByAsc("jgdm");
		 Page<Dkkhyjxx> page = new Page<Dkkhyjxx>(pageNo, pageSize);
		 IPage<Dkkhyjxx> pageList = dkkhyjxxService.page(page, queryWrapper);
		 return Result.ok(pageList);
	 }


	 /**
	  * 管户待移交客户信息-分页列表查询
	  * @param
	  * @param pageNo
	  * @param pageSize
	  * @param request
	  * @return
	  */
	 @AutoLog(value = "待移交客户信息-分页列表查询")
	 @ApiOperation(value = "待移交客户信息-分页列表查询", notes = "待移交客户信息-分页列表查询")
	 @GetMapping(value = "/bsTransferList")
	 public Result<?> bsTransferList(Dkkhyjxx dkkhyjxx,
									   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
									   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
									   HttpServletRequest request) {
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 dkkhyjxx.setGhr(getWorkNo());
		 dkkhyjxx.setGhlx(3);
		 QueryWrapper<Dkkhyjxx> queryWrapper = QueryGenerator.initQueryWrapper(dkkhyjxx, request.getParameterMap());
		 queryWrapper.orderByAsc("jgdm");
		 Page<Dkkhyjxx> page = new Page<Dkkhyjxx>(pageNo, pageSize);
		 IPage<Dkkhyjxx> pageList = dkkhyjxxService.page(page, queryWrapper);
		 return Result.ok(pageList);
	 }


	 /**
	  * 贷款客户包收移交
	  * @param jsonObject
	  * @return
	  */
	 @AutoLog(value = "管户移交")
	 @ApiOperation(value="管户移交", notes="管户移交")
	 @PostMapping(value = "/bsTransfer")
	 public Result<?> bsTransfer(@RequestBody JSONObject jsonObject) {
		 String uuid = IdUtil.simpleUUID();
		 String processId = dkkhyjxxService.getProcessIdByProcessKey("dkkhyj");
		 // 接收人
		 String receiver = jsonObject.getString("yggh");
		 // 申请说明
		 String remark   = jsonObject.getString("remark");
		 // 移交日期
		 String transferDateStr = jsonObject.getString("transferDate");
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 // 移交日期
		 Date transferDate = null;
		 try {
			 transferDate = simpleDateFormat.parse(transferDateStr);
		 } catch (ParseException parseException) {
			 parseException.printStackTrace();
		 }
		 // 移交类型
		 String yjfs = jsonObject.getString("yjfs");
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 SysUser sysUser = iSysUserService.getUserByName(loginUser.getUsername());
		 List<Dkkhyjxx> dkkhghyjxxList = new ArrayList<>();
		 try {
			 if ("1".equals(yjfs)) {
				 //批量移交
				 //查询出移交清单
				 QueryWrapper<Dkkhyjxx> queryWrapper = new QueryWrapper<>();
				 queryWrapper.eq("ghr", getWorkNo());
				 dkkhghyjxxList = dkkhyjxxService.list(queryWrapper);

			 } else if ("2".equals(yjfs)) {
				 //选择移交
				 //获取选择的列表
				 JSONArray jsonArray = jsonObject.getJSONArray("ids");
				 if (jsonArray == null || jsonArray.isEmpty()) {
					 return Result.error("未选择移交的客户信息！");
				 }
				 List<String> ids = jsonArray.toJavaList(String.class);

				 //根据id获取所有的客户清单
				 dkkhghyjxxList = dkkhyjxxService.getListByIds(ids);
			 }
			 List<Dkkhspxx> DkkhspxxList = new ArrayList<>();
			 for (int i = 0; i < dkkhghyjxxList.size(); i++) {
				 Dkkhyjxx form = dkkhghyjxxList.get(i);
				 // Step1. 审批信息
				 Dkkhspxx spxx = new Dkkhspxx();
				 spxx.setId(form.getId());
				 spxx.setBusinessNumber("dkkhyj");
				 spxx.setTableId(uuid);
				 spxx.setProcessId(processId);
				 // 流程状态(0 未提交 1 处理中 2 已结束)
				 spxx.setProcessStatus("0");
				 spxx.setJgdm(form.getJgdm());
				 spxx.setKhbh(form.getKhbh());
				 spxx.setKhmc(form.getKhmc());
				 spxx.setZjhm(form.getZjhm());
				 spxx.setKhlx(form.getKhlx());
				 spxx.setHth(form.getHth());
				 spxx.setHtje(form.getHtje());
				 spxx.setHtye(form.getHtye());
				 spxx.setCpxx(form.getCpxx());
				 spxx.setZzhtffrq(form.getZzhtffrq());
				 spxx.setZzhtdqrq(form.getZzhtdqrq());
				 // 业务类型(2管户移交 4包收移交)
				 spxx.setYwlx(4);

				 spxx.setYghr(sysUser.getWorkNo());
				 spxx.setYghbl(form.getGhbl());
				 spxx.setGhr(receiver);
				 spxx.setGhbl(form.getGhbl());
				 spxx.setYjrq(transferDate);
				 spxx.setSqsm(remark);
				 // 录入标识(0 导入 1 录入 2 修改)
				 spxx.setLrbz("1");
				 DkkhspxxList.add(spxx);
			 }
			 dkkhspxxService.saveBatch(DkkhspxxList);
			 // Step2. 申请流程
			 ActBusiness actBusiness = new ActBusiness();
			 // 删除标识(0 正常 1 已删除)
			 actBusiness.setDelFlag(0);
			 actBusiness.setApplyTime(new Timestamp(System.currentTimeMillis()));
			 actBusiness.setProcDefId(processId);
			 // 结果状态(0 默认未提交 1 处理中 2 通过 3 驳回)
			 actBusiness.setResult(0);
			 // 处理状态(0 默认草稿 1 处理中 2 结束)
			 actBusiness.setStatus(0);
			 actBusiness.setTableId(uuid);
			 actBusiness.setTitle(sysUser.getRealname()+"发起的贷款客户包收移交申请.");
			 actBusiness.setUserId(sysUser.getId());
			 actBusiness.setCreateBy(sysUser.getUsername());
			 actBusiness.setCreateTime(new Timestamp(System.currentTimeMillis()));
			 actBusinessService.save(actBusiness);
		 } catch (Exception exception) {
			 log.error("移交失败！", exception);
			 return Result.error("移交失败！请联系管理员处理！");
		 }

		 return Result.ok("移交成功，进入待审批状态，请到\"我的申请\"提交审批申请！");
	 }
}
