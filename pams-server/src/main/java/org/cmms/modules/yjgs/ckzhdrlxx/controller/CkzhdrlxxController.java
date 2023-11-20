package org.cmms.modules.yjgs.ckzhdrlxx.controller;

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
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.activiti.entity.ActBusiness;
import org.cmms.modules.activiti.service.ActBusinessService;
import org.cmms.modules.activiti.service.ActProcessService;
import org.cmms.modules.performance.depositcustomer.ckzhspxx.entity.Ckzhspxx;
import org.cmms.modules.performance.depositcustomer.ckzhspxx.service.ICkzhspxxService;
import org.cmms.modules.yjgs.ckzhdrlxx.entity.Ckzhdrlxx;
import org.cmms.modules.yjgs.ckzhdrlxx.service.ICkzhdrlxxService;
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
 * @Description: 存款账号待认领
 * @Author: jeecg-boot
 * @Date:   2023-04-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存款账号待认领")
@RestController
@RequestMapping("/yjgs/ckzhdrlxx")
public class CkzhdrlxxController extends JeecgController<Ckzhdrlxx, ICkzhdrlxxService> {
	@Autowired
	private ICkzhdrlxxService ckzhdrlxxService;
	@Autowired
	private ICkzhspxxService ckzhspxxService;
	 @Autowired
	 private ActBusinessService actBusinessService;
	 @Autowired
	 private ActProcessService actProcessService;
	/**
	 * 分页列表查询
	 *
	 * @param ckzhdrlxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "存款账号待认领-分页列表查询")
	@ApiOperation(value="存款账号待认领-分页列表查询", notes="存款账号待认领-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ckzhdrlxx ckzhdrlxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Ckzhdrlxx> queryWrapper = QueryGenerator.initQueryWrapper(ckzhdrlxx, req.getParameterMap());
		queryWrapper.notExists("(select 1 from KHGXGL_CKZHSPXX t2 where khgxgl_drlckzh.jgdm=t2.jgdm and khgxgl_drlckzh.khbh=t2.khbh " +
				" and khgxgl_drlckzh.ckzh=t2.ckzh and t2.process_status='1')");
		Page<Ckzhdrlxx> page = new Page<Ckzhdrlxx>(pageNo, pageSize);
		IPage<Ckzhdrlxx> pageList = ckzhdrlxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param ckzhdrlxx
	 * @return
	 */
	@AutoLog(value = "存款账号待认领-添加")
	@ApiOperation(value="存款账号待认领-添加", notes="存款账号待认领-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ckzhdrlxx ckzhdrlxx) {
		ckzhdrlxxService.save(ckzhdrlxx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param ckzhdrlxx
	 * @return
	 */
	@AutoLog(value = "存款账号待认领-编辑")
	@ApiOperation(value="存款账号待认领-编辑", notes="存款账号待认领-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ckzhdrlxx ckzhdrlxx) {
		ckzhdrlxxService.updateById(ckzhdrlxx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款账号待认领-通过id删除")
	@ApiOperation(value="存款账号待认领-通过id删除", notes="存款账号待认领-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ckzhdrlxxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "存款账号待认领-批量删除")
	@ApiOperation(value="存款账号待认领-批量删除", notes="存款账号待认领-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ckzhdrlxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款账号待认领-通过id查询")
	@ApiOperation(value="存款账号待认领-通过id查询", notes="存款账号待认领-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ckzhdrlxx ckzhdrlxx = ckzhdrlxxService.getById(id);
		return Result.ok(ckzhdrlxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ckzhdrlxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ckzhdrlxx ckzhdrlxx) {
      return super.exportXls(request, ckzhdrlxx, Ckzhdrlxx.class, "存款账号待认领");
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
      return super.importExcel(request, response, Ckzhdrlxx.class);
  }

	 /**
	  * 提取待分配客户信息
	  *
	  * @return
	  */
	 @GetMapping(value = "/init")
	 public Result<?> init(HttpServletRequest request, HttpServletResponse response) {
		 try{
			 ckzhdrlxxService.init();
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
	 @AutoLog(value = "存款账号待分配信息-添加认领信息")
	 @ApiOperation(value="存款账号待分配信息-添加认领信息", notes="存款账号待分配信息-添加认领信息")
	 @PutMapping(value = "/addDrl")
	 public Result<?> addDrl(@RequestBody JSONObject jsonObject) {
		 String uuid = IdUtil.simpleUUID();
		 LoginUser loginUser = getLoginUser();
		 String processId = actProcessService.findByProcessKeyAndLatest("ckzhrl", true).getId();
		 // 申请说明
		 String remark   = jsonObject.getString("remark");
		 // 认领日期
		 String rlrqStr = jsonObject.getString("rlrq");
		 Date rlrq = DateUtil.parseDateFormat(rlrqStr, "yyyy-MM-dd");
		 BigDecimal rlbl = jsonObject.getBigDecimal("rlbl");
		 //选择的客户列表
		 JSONArray jsonArray = jsonObject.getJSONArray("ids");
		 if (jsonArray == null || jsonArray.isEmpty()) {
			 return Result.error("未选择认领的账号信息！");
		 }
		 List<Ckzhspxx> spxxList = new ArrayList<>();
		 List<String> ids = jsonArray.toJavaList(String.class);
		 List<Ckzhdrlxx> ckzhdrlxxList = ckzhdrlxxService.getListByIds(ids);
		 for (Ckzhdrlxx ckzhdrlxx : ckzhdrlxxList) {
			 ckzhdrlxx.setFpzt("2");
			 UpdateWrapper<Ckzhdrlxx> updateWrapper = new UpdateWrapper<>();
			 updateWrapper.eq("id", ckzhdrlxx.getId());
			 ckzhdrlxxService.update(ckzhdrlxx, updateWrapper);

			 //添加审批信息
			 Ckzhspxx spxx = new Ckzhspxx();

			 spxx.setId(ckzhdrlxx.getId());
			 spxx.setBusinessNumber("ckzhrl");
			 spxx.setTableId(uuid);
			 spxx.setProcessId(processId);
			 // 流程状态(0 未提交 1 处理中 2 已结束)
			 spxx.setProcessStatus("0");
			 spxx.setJgdm(ckzhdrlxx.getJgdm());
			 spxx.setKhbh(ckzhdrlxx.getKhbh());
			 spxx.setKhmc(ckzhdrlxx.getKhmc());
			 spxx.setZjlx(ckzhdrlxx.getZjlx());
			 spxx.setZjhm(ckzhdrlxx.getZjhm());
			 spxx.setYxlx(ckzhdrlxx.getYxlx());
			 spxx.setCkzh(ckzhdrlxx.getCkzh());
			 spxx.setZhlx(ckzhdrlxx.getZhlx());
			 spxx.setKhrq(ckzhdrlxx.getKhrq());
			 spxx.setDqrq(ckzhdrlxx.getDqrq());
			 // 业务类型(1 认领 2 移交)
			 spxx.setYwlx("1");
			 spxx.setTzr(getWorkNo());
			 spxx.setTzbl(rlbl);
			 spxx.setYjrq(rlrq);
			 spxx.setSqsm(remark);
			 // 录入标识(0 导入 1 录入 2 修改)
			 spxx.setLrbz("1");
			 spxxList.add(spxx);
		 }
		 ckzhspxxService.saveBatch(spxxList);
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
		 actBusiness.setTitle(loginUser.getRealname()+"的存款账号认领申请.");
		 actBusiness.setUserId(loginUser.getId());
		 actBusiness.setCreateBy(loginUser.getUsername());
		 actBusiness.setCreateTime(new Timestamp(System.currentTimeMillis()));

		 actBusinessService.save(actBusiness);

		 return Result.ok("认领成功,进入审批流程!");
	 }
}
