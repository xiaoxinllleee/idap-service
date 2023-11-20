package org.cmms.modules.gr.grjxsj.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.appbase.tbtjfxcssz.service.ITbTjfxCsszService;
import org.cmms.modules.gr.grjxsj.entity.*;
import org.cmms.modules.gr.grjxsj.service.ITbTjfxYgjxgzService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 员工绩效工资表
 * @Author: jeecg-boot
 * @Date:   2021-05-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="员工绩效工资表")
@RestController
@RequestMapping("/mobile/tbTjfxYgjxgzBankPmRest")
public class TbTjfxYgjxgzController extends JeecgController<TbTjfxYgjxgz, ITbTjfxYgjxgzService> {
	@Autowired
	private ITbTjfxYgjxgzService tbTjfxYgjxgzService;

	@Autowired
	private ITbTjfxCsszService iTbTjfxCsszService;



	 @AutoLog(value = "个人绩效数据查询-通过员工工号和提交日期")
	 @ApiOperation(value="个人绩效数据查询-通过员工工号和提交日期", notes="个人绩效数据查询-通过员工工号和提交日期")
	 @GetMapping(value = "/getWqsj")
	 public Result<?> getJxInfo(@RequestParam("yggh") String yggh,
								@RequestParam(value = "tjrq", required = false) Long tjrq) {

		 Date zjrq;
		 if (tjrq == null) {
			 zjrq = iTbTjfxCsszService.getTheMaxDate();
		 } else {
			 zjrq =new Date(tjrq);
		 }
		QueryWrapper<TbTjfxYgjxgz> queryWrapper=new QueryWrapper<TbTjfxYgjxgz>();
		 queryWrapper.eq("TJRQ",zjrq);
		 List<TbTjfxYgjxgz> list = tbTjfxYgjxgzService.list(queryWrapper);
		 TbTjfxYgjxgz ygjxgz =null;
		 ErpWageYgjbgzglYx ygjbgz=null;
		 if (list.size()>0&&list!=null){
			 ygjxgz = list.get(0);
			 ygjbgz = tbTjfxYgjxgzService.getInfoByGzyf(zjrq, yggh);
			 if (ygjbgz == null) {
				 ygjbgz = new ErpWageYgjbgzglYx();
			 }
			 ErpWageYgjx erpWageYgjxInfoByGzrqAndYggh = tbTjfxYgjxgzService.getErpWageYgjxInfoByGzrqAndYggh(zjrq, yggh);
			 if (erpWageYgjxInfoByGzrqAndYggh != null) {
				 ygjbgz.setNzkhgz(erpWageYgjxInfoByGzrqAndYggh.getNzkhgz()==null? BigDecimal.ZERO
						 :erpWageYgjxInfoByGzrqAndYggh.getNzkhgz());
				 ygjbgz.setZxkhgz(erpWageYgjxInfoByGzrqAndYggh.getZxkhgz()==null? BigDecimal.ZERO
						 :erpWageYgjxInfoByGzrqAndYggh.getZxkhgz());
				 ygjbgz.setJxgz(erpWageYgjxInfoByGzrqAndYggh.getGzhj()==null? BigDecimal.ZERO
						 :erpWageYgjxInfoByGzrqAndYggh.getGzhj());
			 }
		 }

		 JSONObject obj = new JSONObject();
		 obj.put("ygjxgz",ygjxgz);
		 obj.put("ygjbgz",ygjbgz);

		 return Result.ok(obj);
	 }

	 @AutoLog(value = "个人绩效饼图数据查询-通过员工工号和提交日期")
	 @ApiOperation(value="个人绩效饼图数据查询-通过员工工号和提交日期", notes="个人绩效饼图数据查询-通过员工工号和提交日期")
	 @GetMapping(value = "/getYgjxPie")
	public Result<?> getYgjxPie(@RequestParam("yggh") String yggh,
								@RequestParam(value = "tjrq", required = false) Long tjrq){

		 Date zjrq;
		 if (tjrq == null) {
			 zjrq = iTbTjfxCsszService.getTheMaxDate();
		 } else {
			 zjrq =new Date(tjrq);
		 }
		 List<TbTjfxYgzblbgz> list = tbTjfxYgjxgzService.getYgjxPie(yggh, zjrq);

		 return Result.ok(list);
	 }

	@AutoLog(value = "个人指定绩效数据查询-通过员工工号和指标类别")
	@ApiOperation(value="个人指定绩效数据查询-通过员工工号和指标类别", notes="个人指定绩效数据查询-通过员工工号和指标类别")
	@GetMapping(value = "/getGrzdjx")
	public Result<?> getGrzdjx(@RequestParam("yggh") String yggh,
							   @RequestParam(value = "zbid") Integer zbid){
		Date tjrq= iTbTjfxCsszService.getTheMaxDate();
		TbTjfxYgzblbgz grzdjxsj = tbTjfxYgjxgzService.getGrzdjxsj(tjrq,yggh, zbid);
		return Result.ok(grzdjxsj);
	}


	@AutoLog(value = "指标明细查询-通过员工工号和指标类别")
	@ApiOperation(value="指标明细查询-通过员工工号和指标类别", notes="指标明细查询-通过员工工号和指标类别")
	@GetMapping(value = "/getJxMx")
	public Result<?> getJxMx( @RequestParam(value = "yggh") String yggh,
							  @RequestParam(value = "zbld") Integer zbld,
							  @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
							  @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize){
		Page<ZbmxDto> page=new Page<ZbmxDto>(pageNo,pageSize);
		IPage<ZbmxDto> page1 = tbTjfxYgjxgzService.getJxMx(page, yggh, zbld);
		return Result.ok(page1);
	}

	@AutoLog(value = "指标明细往期查询-通过员工工号和指标id")
	@ApiOperation(value="指标明细往期查询-通过员工工号和指标id", notes="指标明细查询-通过员工工号和指标id")
	@GetMapping(value = "/getWqzb")
	public Result<?> getWqzb( @RequestParam(value = "yggh") String yggh,
							  @RequestParam(value = "zbid") String zbid,
							  @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
							  @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize){
		Page<TbTjfxYgjxgz> page=new Page<>(pageNo,pageSize);
		IPage<TbTjfxYgjxgz> page1 = tbTjfxYgjxgzService.getWQDateZb(page, yggh, zbid);
		return Result.ok(page1);
	}
	@AutoLog(value = "个人指定绩效历史数据查询-通过员工工号和指标id")
	@ApiOperation(value="个人指定绩效历史数据查询-通过员工工号和指标id", notes="个人指定绩效历史数据查询-通过员工工号和指标id")
	@GetMapping(value = "/getWQDateJx")
	public Result<?> getWQDateJx( @RequestParam(value = "yggh") String yggh,
							  @RequestParam(value = "zblb") String zblb,
							  @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
							  @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize){
		Page<TbTjfxYgzblbgz> page=new Page<>(pageNo,pageSize);
		IPage<TbTjfxYgzblbgz> page1 = tbTjfxYgjxgzService.getWQDateJx(page,yggh,zblb);
		return Result.ok(page1);
	}

	@AutoLog(value = "个人指定绩效历史数据查询-通过员工工号和指标id")
	@ApiOperation(value="个人指定绩效历史数据查询-通过员工工号和指标id", notes="个人指定绩效历史数据查询-通过员工工号和指标id")
	@GetMapping(value = "/getWqJxInfo")
	public Result<?> getWqJxInfo( @RequestParam(value = "yggh") String yggh,
								  @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
								  @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize){
		Page<TbTjfxYgjxgz> page=new Page<>(pageNo,pageSize);
		IPage<TbTjfxYgjxgz> list = tbTjfxYgjxgzService.getWqJxInfo(page, yggh);
			Date dqrq=iTbTjfxCsszService.getTheMaxDate();
			Calendar cal=Calendar.getInstance();
			cal.setTime(dqrq);
			cal.add(Calendar.YEAR,-1);
		Date ncrq = DateUtil.getYearFirst(); //年初
		cal = Calendar.getInstance();
		cal.setTime(dqrq);
		cal.add(Calendar.MONTH, -1);
		String mcrqS = DateUtil.getLastDayOfMonth(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH));//m-1
		Date mrrq=null;
		try {
		mrrq = new SimpleDateFormat("yyyy-MM-dd").parse(mcrqS);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date zrrq = DateUtil.getDateAfter(dqrq, -1);//昨天

		TbTjfxYgjxgz m1 = tbTjfxYgjxgzService.getJXByDateYggh(yggh, mrrq);
		TbTjfxYgjxgz nc = tbTjfxYgjxgzService.getJXByDateYggh(yggh, ncrq);
		TbTjfxYgjxgz dq = tbTjfxYgjxgzService.getJXByDateYggh(yggh, dqrq);
		JSONObject obj=new JSONObject();
		obj.put("list",list);
		if (nc != null) {
			obj.put("nc", nc.getGzhj());
		} else {
			obj.put("nc", 0);
		}

		if (m1 != null) {
			obj.put("m1", m1.getGzhj());
		} else {
			obj.put("m1", 0);
		}
		if (dq!=null){
			obj.put("dqrq",dq.getGzhj());
		}else{
			obj.put("dqrq",0);
		}
		return Result.ok(obj);
	}
}
