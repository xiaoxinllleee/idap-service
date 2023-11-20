package org.cmms.modules.yjgs.ckkhdfpxx.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.activiti.entity.ActBusiness;
import org.cmms.modules.activiti.service.ActBusinessService;
import org.cmms.modules.activiti.service.ActProcessService;
import org.cmms.modules.performance.depositcustomer.ckkhspxx.entity.Ckkhspxx;
import org.cmms.modules.performance.depositcustomer.ckkhspxx.service.ICkkhspxxService;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.yjgs.ckkhdfpxx.entity.Ckkhdfpxx;
import org.cmms.modules.yjgs.ckkhdfpxx.service.ICkkhdfpxxService;
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
 * @Description: 待认领存款客户信息
 * @Author: jeecg-boot
 * @Date:   2023-03-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="待认领存款客户信息")
@RestController
@RequestMapping("/yjgs/ckkhdfpxx")
public class CkkhdfpxxController extends JeecgController<Ckkhdfpxx, ICkkhdfpxxService> {
	@Autowired
	private ICkkhdfpxxService ckkhdfpxxService;
	 @Autowired
	 private ICkkhspxxService iCkkhspxxService;
	 @Autowired
	 private ActBusinessService actBusinessService;
	 @Autowired
	 private ActProcessService actProcessService;
	
	/**
	 * 分页列表查询
	 *
	 * @param ckkhdfpxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "待认领存款客户信息-分页列表查询")
	@ApiOperation(value="待认领存款客户信息-分页列表查询", notes="待认领存款客户信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ckkhdfpxx ckkhdfpxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Ckkhdfpxx> queryWrapper = QueryGenerator.initQueryWrapper(ckkhdfpxx, req.getParameterMap());
		queryWrapper.notExists("(select 1 from KHGXGL_CKKHSPXX t2 where khgxgl_drlckkh.jgdm=t2.jgdm and khgxgl_drlckkh.khbh=t2.khbh " +
				"and t2.process_status='1')");
		Page<Ckkhdfpxx> page = new Page<Ckkhdfpxx>(pageNo, pageSize);
		IPage<Ckkhdfpxx> pageList = ckkhdfpxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param ckkhdfpxx
	 * @return
	 */
	@AutoLog(value = "待认领存款客户信息-添加")
	@ApiOperation(value="待认领存款客户信息-添加", notes="待认领存款客户信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ckkhdfpxx ckkhdfpxx) {
		ckkhdfpxxService.save(ckkhdfpxx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param ckkhdfpxx
	 * @return
	 */
	@AutoLog(value = "待认领存款客户信息-编辑")
	@ApiOperation(value="待认领存款客户信息-编辑", notes="待认领存款客户信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ckkhdfpxx ckkhdfpxx) {
		ckkhdfpxxService.updateById(ckkhdfpxx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "待认领存款客户信息-通过id删除")
	@ApiOperation(value="待认领存款客户信息-通过id删除", notes="待认领存款客户信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ckkhdfpxxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "待认领存款客户信息-批量删除")
	@ApiOperation(value="待认领存款客户信息-批量删除", notes="待认领存款客户信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ckkhdfpxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "待认领存款客户信息-通过id查询")
	@ApiOperation(value="待认领存款客户信息-通过id查询", notes="待认领存款客户信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ckkhdfpxx ckkhdfpxx = ckkhdfpxxService.getById(id);
		return Result.ok(ckkhdfpxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ckkhdfpxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ckkhdfpxx ckkhdfpxx) {
      return super.exportXls(request, ckkhdfpxx, Ckkhdfpxx.class, "待认领存款客户信息");
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
      return super.importExcel(request, response, Ckkhdfpxx.class);
  }

	 /**
	  * 提取待分配客户信息
	  *
	  * @return
	  */
	 @GetMapping(value = "/init")
	 public Result<?> init(HttpServletRequest request, HttpServletResponse response) {
		 try{
			 ckkhdfpxxService.init();
		 } catch (Exception e) {
			 log.error(e.getMessage(), "提取失败");
			 return Result.error(e.getMessage());
		 }

		 return Result.ok("提取成功");
	 }

	 /**
	  * 添加认领信息
	  *
	  * @param jsonObject
	  * @return
	  */
	 @AutoLog(value = "存款客户待分配信息-添加认领信息")
	 @ApiOperation(value="存款客户待分配信息-添加认领信息", notes="存款客户待分配信息-添加认领信息")
	 @PutMapping(value = "/addDrl")
	 public Result<?> addDrl(@RequestBody JSONObject jsonObject) {
		 String uuid = IdUtil.simpleUUID();
		 LoginUser loginUser = getLoginUser();
		 String processId = actProcessService.findByProcessKeyAndLatest("ckkhrl", true).getId();
		 // 申请说明
		 String remark   = jsonObject.getString("remark");
		 // 认领日期
		 String rlrqStr = jsonObject.getString("rlrq");
		 Date rlrq = DateUtil.parseDateFormat(rlrqStr, "yyyy-MM-dd");
		 BigDecimal rlbl = jsonObject.getBigDecimal("rlbl");
		 //选择的客户列表
		 JSONArray jsonArray = jsonObject.getJSONArray("ids");
		 if (jsonArray == null || jsonArray.isEmpty()) {
			 return Result.error("未选择认领的客户信息！");
		 }
		 List<Ckkhspxx> spxxList = new ArrayList<>();
		 List<String> ids = jsonArray.toJavaList(String.class);
		 List<Ckkhdfpxx> ckkhdfpxxList = ckkhdfpxxService.getListByIds(ids);
		 for (Ckkhdfpxx ckkhdfpxxb : ckkhdfpxxList) {
			 ckkhdfpxxb.setFpzt("2");
			 UpdateWrapper<Ckkhdfpxx> updateWrapper = new UpdateWrapper<>();
			 updateWrapper.eq("jgdm",ckkhdfpxxb.getJgdm()).eq("khbh",ckkhdfpxxb.getKhbh());
			 ckkhdfpxxService.update(ckkhdfpxxb,updateWrapper);

			 //添加审批信息
			 Ckkhspxx spxx = new Ckkhspxx();

			 spxx.setId(ckkhdfpxxb.getId());
			 spxx.setBusinessNumber("ckkhrl");
			 spxx.setTableId(uuid);
			 spxx.setProcessId(processId);
			 // 流程状态(0 未提交 1 处理中 2 已结束)
			 spxx.setProcessStatus("0");
			 spxx.setJgdm(ckkhdfpxxb.getJgdm());
			 spxx.setKhbh(ckkhdfpxxb.getKhbh());
			 spxx.setKhmc(ckkhdfpxxb.getKhmc());
			 spxx.setZjlx(ckkhdfpxxb.getZjlx());
			 spxx.setZjhm(ckkhdfpxxb.getZjhm());
			 spxx.setKhlx(ckkhdfpxxb.getKhlx());
			 spxx.setYxlx(ckkhdfpxxb.getYxlx());
			 spxx.setCpxx(ckkhdfpxxb.getCpxx());
			 spxx.setZzkhrq(ckkhdfpxxb.getZzkhrq());
			 // 业务类型(1 认领 2 移交)
			 spxx.setYwlx("1");
			 spxx.setGhr(getWorkNo());
			 spxx.setGhbl(rlbl);
			 spxx.setYjrq(rlrq);
			 spxx.setSqsm(remark);
			 // 录入标识(0 导入 1 录入 2 修改)
			 spxx.setLrbz("1");
			 spxxList.add(spxx);
		 }
		 iCkkhspxxService.saveBatch(spxxList);
		 //添加进入流程申请
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
		 actBusiness.setTitle(loginUser.getRealname()+"的存款客户认领申请.");
		 actBusiness.setUserId(loginUser.getId());
		 actBusiness.setCreateBy(loginUser.getUsername());
		 actBusiness.setCreateTime(new Timestamp(System.currentTimeMillis()));

		 actBusinessService.save(actBusiness);

		 return Result.ok("认领成功,进入审批流程!");
	 }

	 /**
	  * 添加分配信息
	  *
	  * @param jsonArray
	  * @return
	  */
	 @AutoLog(value = "存款客户待分配信息-添加分配信息")
	 @ApiOperation(value="存款客户待分配信息-添加分配信息", notes="存款客户待分配信息-添加分配信息")
	 @PutMapping(value = "/addDfp")
	 public Result<?> addDfp(@RequestBody JSONArray jsonArray) {
		 LoginUser loginUser = getLoginUser();
		 String id = UUIDGenerator.generate();
		 String uuid = IdUtil.simpleUUID();
		 String processId = actProcessService.findByProcessKeyAndLatest("ckkhdfpxx", true).getId();
		 //修改分配状态为 待审批
		 for (int i = 0; i < jsonArray.size(); i++) {
			 Ckkhdfpxx ckkhdfpxxb = JSONObject.parseObject(JSON.toJSONString(jsonArray.get(i)),Ckkhdfpxx.class);
			 ckkhdfpxxb.setFpzt("2");
			 UpdateWrapper<Ckkhdfpxx> updateWrapper = new UpdateWrapper<>();
			 updateWrapper.eq("jgdm",ckkhdfpxxb.getJgdm()).eq("khbh",ckkhdfpxxb.getKhbh());
			 ckkhdfpxxService.update(ckkhdfpxxb,updateWrapper);

			 //添加审批信息
			 Ckkhspxx spxx = new Ckkhspxx();

			 spxx.setId(id);
			 spxx.setBusinessNumber("transferInfo");
			 spxx.setTableId(uuid);
			 spxx.setProcessId(processId);
			 // 流程状态(0 未提交 1 处理中 2 已结束)
			 spxx.setProcessStatus("0");
			 spxx.setJgdm(ckkhdfpxxb.getJgdm());
			 spxx.setKhbh(ckkhdfpxxb.getKhbh());
			 spxx.setKhmc(ckkhdfpxxb.getKhmc());
			 spxx.setZjlx(ckkhdfpxxb.getZjlx());
			 spxx.setZjhm(ckkhdfpxxb.getZjhm());
			 spxx.setKhlx(ckkhdfpxxb.getKhlx());
			 spxx.setYxlx(ckkhdfpxxb.getYxlx());
			 spxx.setCpxx(ckkhdfpxxb.getCpxx());
			 spxx.setZzkhrq(ckkhdfpxxb.getZzkhrq());
			 // 业务类型(1 认领 2 移交)
			 spxx.setYwlx("1");
//			 spxx.setGhr(receiver);
//			 spxx.setGhbl(form.getGhbl());
//			 spxx.setYjrq(transferDate);
//			 spxx.setSqsm(remark);
			 // 录入标识(0 导入 1 录入 2 修改)
			 spxx.setLrbz("1");

			 iCkkhspxxService.save(spxx);
		 }
		 //添加进入流程申请
		 ActBusiness actBusiness = new ActBusiness();
		 actBusiness.setUserId(loginUser.getId());
		 actBusiness.setTableId(id);
		 actBusiness.setProcDefId(processId);
		 actBusiness.setTitle(loginUser.getRealname() + "的存款客户分配申请");
		 actBusiness.setCreateBy(loginUser.getRealname());
		 actBusiness.setCreateTime(new Date());
		 actBusiness.setApplyTime(new Date());
		 actBusinessService.save(actBusiness);


		 return Result.ok("分配成功,进入审批流程!");
	 }

}
