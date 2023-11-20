package org.cmms.modules.performance.depositcustomer.ckzhtzyj.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.activiti.entity.ActBusiness;
import org.cmms.modules.activiti.service.ActBusinessService;
import org.cmms.modules.performance.depositcustomer.ckkhghyj.entity.Ckkhghyjxx;
import org.cmms.modules.performance.depositcustomer.ckzhspxx.entity.Ckzhspxx;
import org.cmms.modules.performance.depositcustomer.ckzhspxx.service.ICkzhspxxService;
import org.cmms.modules.performance.depositcustomer.ckzhtzyj.entity.Ckzhtzyjxx;
import org.cmms.modules.performance.depositcustomer.ckzhtzyj.service.ICkzhtzyjxxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.ISysUserService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
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
 * @Description: 存款账号拓展移交信息
 * @Author: jeecg-boot
 * @Date:   2023-03-31
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存款账号拓展移交信息")
@RestController
@RequestMapping("/ckzhtzyj/ckzhtzyjxx")
public class CkzhtzyjxxController extends JeecgController<Ckzhtzyjxx, ICkzhtzyjxxService> {
	 @Autowired
	 private ICkzhtzyjxxService ckzhtzyjxxService;

	 @Autowired
	 private ActBusinessService actBusinessService;
	 @Autowired
	 private ISysUserService iSysUserService;

	 @Autowired
	 private ICkzhspxxService ckzhspxxService;
	/**
	 * 分页列表查询
	 *
	 * @param ckzhtzyjxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "存款账号拓展移交信息-分页列表查询")
	@ApiOperation(value="存款账号拓展移交信息-分页列表查询", notes="存款账号拓展移交信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ckzhtzyjxx ckzhtzyjxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Ckzhtzyjxx> queryWrapper = QueryGenerator.initQueryWrapper(ckzhtzyjxx, req.getParameterMap());
		Page<Ckzhtzyjxx> page = new Page<Ckzhtzyjxx>(pageNo, pageSize);
		IPage<Ckzhtzyjxx> pageList = ckzhtzyjxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param ckzhtzyjxx
	 * @return
	 */
	@AutoLog(value = "存款账号拓展移交信息-添加")
	@ApiOperation(value="存款账号拓展移交信息-添加", notes="存款账号拓展移交信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ckzhtzyjxx ckzhtzyjxx) {
		ckzhtzyjxxService.save(ckzhtzyjxx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param ckzhtzyjxx
	 * @return
	 */
	@AutoLog(value = "存款账号拓展移交信息-编辑")
	@ApiOperation(value="存款账号拓展移交信息-编辑", notes="存款账号拓展移交信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ckzhtzyjxx ckzhtzyjxx) {
		ckzhtzyjxxService.updateById(ckzhtzyjxx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款账号拓展移交信息-通过id删除")
	@ApiOperation(value="存款账号拓展移交信息-通过id删除", notes="存款账号拓展移交信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ckzhtzyjxxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "存款账号拓展移交信息-批量删除")
	@ApiOperation(value="存款账号拓展移交信息-批量删除", notes="存款账号拓展移交信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ckzhtzyjxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款账号拓展移交信息-通过id查询")
	@ApiOperation(value="存款账号拓展移交信息-通过id查询", notes="存款账号拓展移交信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ckzhtzyjxx ckzhtzyjxx = ckzhtzyjxxService.getById(id);
		return Result.ok(ckzhtzyjxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ckzhtzyjxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ckzhtzyjxx ckzhtzyjxx) {
      return super.exportXls(request, ckzhtzyjxx, Ckzhtzyjxx.class, "存款账号拓展移交信息");
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
      return super.importExcel(request, response, Ckzhtzyjxx.class);
  }
	 /**
	  * 待移交客户信息-分页列表查询
	  * @param pageNo
	  * @param pageSize
	  * @param request
	  * @return
	  */
	 @AutoLog(value = "待移交客户信息-分页列表查询")
	 @ApiOperation(value = "待移交客户信息-分页列表查询", notes = "待移交客户信息-分页列表查询")
	 @GetMapping(value = "/transferList")
	 public Result<?> transferPageList(Ckzhtzyjxx ckzhtzyjxx,
									   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
									   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
									   HttpServletRequest request) {
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 ckzhtzyjxx.setGhr(getWorkNo());
		 QueryWrapper<Ckzhtzyjxx> queryWrapper = QueryGenerator.initQueryWrapper(ckzhtzyjxx, request.getParameterMap());
		 queryWrapper.orderByAsc("jgdm");
		 Page<Ckzhtzyjxx> page = new Page<Ckzhtzyjxx>(pageNo, pageSize);
		 IPage<Ckzhtzyjxx> pageList = ckzhtzyjxxService.page(page, queryWrapper);
		 return Result.ok(pageList);
	 }


	 /**
	  * 存款客户管户移交
	  * @param jsonObject
	  * @return
	  */
	 @AutoLog(value = "管户移交")
	 @ApiOperation(value="管户移交", notes="管户移交")
	 @PostMapping(value = "/transfer")
	 public Result<?> handleTransfer(@RequestBody JSONObject jsonObject) {
		 String uuid = IdUtil.simpleUUID();
		 String processId = ckzhtzyjxxService.getProcessIdByProcessKey("ckzhtzyj");
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
		 List<Ckzhtzyjxx> ckzhtzyjxxList = new ArrayList<>();
		 try {
			 if ("1".equals(yjfs)) {
				 //批量移交
				 //查询出移交清单
				 String yxlx = jsonObject.getString("yxlx");
				 QueryWrapper<Ckzhtzyjxx> queryWrapper = new QueryWrapper<>();
				 queryWrapper.eq("ghr", getWorkNo());
				 if (StringUtils.isNotEmpty(yxlx)) {
					 String[] yxlxList = yxlx.split(",");
					 queryWrapper.in("yxlx", yxlxList);
				 }
				 ckzhtzyjxxList = ckzhtzyjxxService.list(queryWrapper);

			 } else if ("2".equals(yjfs)) {
				 //选择移交
				 //获取选择的列表
				 JSONArray jsonArray = jsonObject.getJSONArray("ids");
				 if (jsonArray == null || jsonArray.isEmpty()) {
					 return Result.error("未选择移交的客户信息！");
				 }
				 List<String> ids = jsonArray.toJavaList(String.class);

				 //根据id获取所有的客户清单
				 ckzhtzyjxxList = ckzhtzyjxxService.getListByIds(ids);
			 }

			 if(ckzhtzyjxxList.size()==0){
				 return Result.error("移交失败！未找需要移交的数据！");
			 }

			 List<Ckzhspxx> ckzhspxxList = new ArrayList<>();
			 for (int i = 0; i < ckzhtzyjxxList.size(); i++) {
				 Ckzhtzyjxx form = ckzhtzyjxxList.get(i);
				 // Step1. 审批信息
				 Ckzhspxx spxx = new Ckzhspxx();
				 spxx.setId(form.getId());
				 spxx.setBusinessNumber("ckzhtzyj");
				 spxx.setTableId(uuid);
				 spxx.setProcessId(processId);
				 // 流程状态(0 未提交 1 处理中 2 已结束)
				 spxx.setProcessStatus("0");
				 spxx.setJgdm(form.getJgdm());
				 spxx.setKhbh(form.getKhbh());
				 spxx.setKhmc(form.getKhmc());
				 spxx.setZjhm(form.getZjhm());
				 spxx.setCkzh(form.getCkzh());
				 spxx.setZhlx(form.getZhlx());
				 spxx.setYxlx(form.getYxlx());
				 spxx.setKhrq(form.getKhrq());
				 spxx.setDqrq(form.getDqrq());
				 // 业务类型(1 认领 2 移交)
				 spxx.setYwlx("2");
				 spxx.setYtzr(sysUser.getWorkNo());
				 spxx.setYtzbl(form.getGhbl());
				 spxx.setTzr(receiver);
				 spxx.setTzbl(form.getGhbl());
				 spxx.setYjrq(transferDate);
				 spxx.setSqsm(remark);
				 // 录入标识(0 导入 1 录入 2 修改)
				 spxx.setLrbz("1");
				 ckzhspxxList.add(spxx);
			 }
			 ckzhspxxService.saveBatch(ckzhspxxList);
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
			 actBusiness.setTitle(sysUser.getRealname()+"发起的存款账户拓展移交申请.");
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
