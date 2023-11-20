package org.cmms.modules.dkjkpt.dksjjk.dksjjktz.controller;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.net.URLDecoder;
import java.util.concurrent.CompletableFuture;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.util.DateUtils;
import org.cmms.modules.system.entity.SysDic;
import org.cmms.modules.system.service.ISysDicService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.dkkhglrgl.entity.DkjkptDkkhglrgl;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.dkkhglrgl.service.IDkjkptDkkhglrglService;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.entity.*;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.verify.BnblImportVerify;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.verify.BwblImportVerify;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.verify.GzlImportVerify;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.verify.SszcImportVerify;
import org.cmms.modules.system.service.ISysLogService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 贷款数据监控台账
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款数据监控台账")
@RestController
@RequestMapping("/bndksjjktz/dndksjjktz")
public class DkjkptBndksjjktzController implements Job {
	@Autowired
	private IDkjkptBndksjjktzService dkjkptBndksjjktzService;
	@Autowired
	private IDkjkptDkkhglrglService dkjkptDkkhglrglService;
	@Autowired
	private IDkjlptgzlxtService dkjlptgzlxtService;
	@Autowired
	private ISysDicService sysDicService;
	@Autowired
	private IDkjlptbnblxtService dkjlptbnblxtService;
	@Autowired
	private IDkjkptbwblxtService dkjkptbwblxtService;
	@Autowired
	private IDkjkptsszcxtService dkjkptsszcxtService;
	@Autowired
	private GzlImportVerify gzlImportVerify;
	@Autowired
	private BnblImportVerify bnblImportVerify;

	@Autowired
	private SszcImportVerify sszcImportVerify;
	@Autowired
	ISysLogService sysLogService;
	 @Value(value = "${common.path.upload}")
	 private String uploadpath;
	/**
	  * 分页列表查询
	 * @param dkjkptBndksjjktz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款数据监控台账-分页列表查询")
	@ApiOperation(value="贷款数据监控台账-分页列表查询", notes="贷款数据监控台账-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<DkjkptBndksjjktz>> queryPageList(DkjkptBndksjjktz dkjkptBndksjjktz,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<DkjkptBndksjjktz>> result = new Result<IPage<DkjkptBndksjjktz>>();
		QueryWrapper<DkjkptBndksjjktz> queryWrapper = QueryGenerator.initQueryWrapper(dkjkptBndksjjktz, req.getParameterMap());
		Page<DkjkptBndksjjktz> page = new Page<DkjkptBndksjjktz>(pageNo, pageSize);
		IPage<DkjkptBndksjjktz> pageList = dkjkptBndksjjktzService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param dkjkptBndksjjktz
	 * @return
	 */
	@AutoLog(value = "贷款数据监控台账-添加")
	@ApiOperation(value="贷款数据监控台账-添加", notes="贷款数据监控台账-添加")
	@PostMapping(value = "/add")
	public Result<DkjkptBndksjjktz> add(@RequestBody DkjkptBndksjjktz dkjkptBndksjjktz) {
		Result<DkjkptBndksjjktz> result = new Result<DkjkptBndksjjktz>();
		try {
			dkjkptBndksjjktzService.save(dkjkptBndksjjktz);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param dkjkptBndksjjktz
	 * @return
	 */
	@AutoLog(value = "贷款数据监控台账-编辑")
	@ApiOperation(value="贷款数据监控台账-编辑", notes="贷款数据监控台账-编辑")
	@PutMapping(value = "/edit")
	public Result<DkjkptBndksjjktz> edit(@RequestBody DkjkptBndksjjktz dkjkptBndksjjktz) {
		Result<DkjkptBndksjjktz> result = new Result<DkjkptBndksjjktz>();
		DkjkptBndksjjktz dkjkptBndksjjktzEntity = dkjkptBndksjjktzService.getById(dkjkptBndksjjktz.getDkzh());
		if(dkjkptBndksjjktzEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dkjkptBndksjjktzService.updateById(dkjkptBndksjjktz);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}

		return result;
	}
	 /**
	  *  湘潭编辑
	  * @param
	  * @return
	  */
	 @AutoLog(value = "贷款数据监控台账-编辑关注类")
	 @ApiOperation(value="贷款数据监控台账-编辑关注类", notes="贷款数据监控台账-编辑关注类")
	 @PostMapping(value = "/edit110")
	 public Result<?> edit110(@RequestBody JSONObject jsonObject) {
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String tjnf = jsonObject.getString("tjnf");
		 Result<DkjkptBndksjjktz> result = new Result<DkjkptBndksjjktz>();
		 JSONObject  formData = JSONObject.parseObject(JSONObject.toJSONString(jsonObject.get("formData")));
		 DkjkptBndksjjktz dkjkptBndksjjktz = JSONObject.parseObject(formData.toJSONString(), DkjkptBndksjjktz.class);
		 DkjkptBndksjjktz dkjkptBndksjjktzEntity = dkjkptBndksjjktzService.getById(dkjkptBndksjjktz.getDkzh());
		 if(dkjkptBndksjjktzEntity==null) {
			 result.error500("未找到对应实体");
		 }else {
			 boolean ok = dkjkptBndksjjktzService.updateById(dkjkptBndksjjktz);
			 if(ok) {
				 result.success("修改成功!");
			 }
		 }
		 //获取关注类
		 JSONArray jsonArray = jsonObject.getJSONArray("gzl");
		 String gzl = JSONObject.toJSONString(jsonArray);
		 List<Dkjlptgzlxt> dkjlptgzlxts = JSONObject.parseArray(gzl, Dkjlptgzlxt.class);
		 if (CollUtil.isNotEmpty(dkjlptgzlxts)){
			 for (Dkjlptgzlxt dkjlptgzlxt : dkjlptgzlxts) {
				 QueryWrapper<Dkjlptgzlxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptgzlxt.getDkzh());
				 dkjlptgzlxt.setXgr(sysUser.getUsername());
				 dkjlptgzlxt.setXgsj(new Date());
				 dkjlptgzlxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptgzlxtService.update(dkjlptgzlxt,wrapper);
			 }
		 }
		 //获取关注类进度
		 JSONArray  gzljd = jsonObject.getJSONArray("gzljdqs");
		 List<Dkjlptgzlxt> dkjlptgzljdxts2 = JSONObject.parseArray(gzljd.toJSONString(), Dkjlptgzlxt.class);
		 if (CollUtil.isNotEmpty(dkjlptgzljdxts2)){
			 for (Dkjlptgzlxt dkjlptgzlxt : dkjlptgzljdxts2) {
				 QueryWrapper<Dkjlptgzlxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptgzlxt.getDkzh());
				 dkjlptgzlxt.setXgr(sysUser.getUsername());
				 dkjlptgzlxt.setXgsj(new Date());
				 dkjlptgzlxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptgzlxtService.update(dkjlptgzlxt,wrapper);
			 }
		 }

		 //获取关注类进度
		 JSONArray  gzljd1 = jsonObject.getJSONArray("gzljd1");
		 List<Dkjlptgzlxt> dkjlptgzlxts1 = JSONObject.parseArray(gzljd1.toJSONString(), Dkjlptgzlxt.class);
		 if (CollUtil.isNotEmpty(dkjlptgzlxts1)){
			 for (Dkjlptgzlxt dkjlptgzlxt : dkjlptgzlxts1) {
				 QueryWrapper<Dkjlptgzlxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptgzlxt.getDkzh());
				 dkjlptgzlxt.setXgr(sysUser.getUsername());
				 dkjlptgzlxt.setXgsj(new Date());
				 dkjlptgzlxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptgzlxtService.update(dkjlptgzlxt,wrapper);
			 }
		 }
		 //获取关注类进度
		 JSONArray  gzljd2 = jsonObject.getJSONArray("gzljd2");
		 List<Dkjlptgzlxt> dkjlptgzlxts2 = JSONObject.parseArray(gzljd2.toJSONString(), Dkjlptgzlxt.class);
		 if (CollUtil.isNotEmpty(dkjlptgzlxts2)){
			 for (Dkjlptgzlxt dkjlptgzlxt : dkjlptgzlxts2) {
				 QueryWrapper<Dkjlptgzlxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptgzlxt.getDkzh());
				 dkjlptgzlxt.setXgr(sysUser.getUsername());
				 dkjlptgzlxt.setXgsj(new Date());
				 dkjlptgzlxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptgzlxtService.update(dkjlptgzlxt,wrapper);
			 }
		 }
		 //获取关注类进度
		 JSONArray  gzljd3 = jsonObject.getJSONArray("gzljd3");
		 List<Dkjlptgzlxt> dkjlptgzlxts3 = JSONObject.parseArray(gzljd3.toJSONString(), Dkjlptgzlxt.class);
		 if (CollUtil.isNotEmpty(dkjlptgzlxts3)){
			 for (Dkjlptgzlxt dkjlptgzlxt : dkjlptgzlxts3) {
				 QueryWrapper<Dkjlptgzlxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptgzlxt.getDkzh());
				 dkjlptgzlxt.setXgr(sysUser.getUsername());
				 dkjlptgzlxt.setXgsj(new Date());
				 dkjlptgzlxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptgzlxtService.update(dkjlptgzlxt,wrapper);
			 }
		 }

		 //获取关注类进度
		 JSONArray  gzljd4 = jsonObject.getJSONArray("gzljd4");
		 List<Dkjlptgzlxt> dkjlptgzlxts4 = JSONObject.parseArray(gzljd4.toJSONString(), Dkjlptgzlxt.class);
		 if (CollUtil.isNotEmpty(dkjlptgzlxts4)){
			 for (Dkjlptgzlxt dkjlptgzlxt : dkjlptgzlxts4) {
				 QueryWrapper<Dkjlptgzlxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptgzlxt.getDkzh());
				 dkjlptgzlxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptgzlxt.setXgr(sysUser.getUsername());
				 dkjlptgzlxt.setXgsj(new Date());
				 dkjlptgzlxtService.update(dkjlptgzlxt,wrapper);
			 }
		 }

		 //获取关注类进度
		 JSONArray  gzljd5 = jsonObject.getJSONArray("gzljd5");
		 List<Dkjlptgzlxt> dkjlptgzlxts5 = JSONObject.parseArray(gzljd5.toJSONString(), Dkjlptgzlxt.class);
		 if (CollUtil.isNotEmpty(dkjlptgzlxts5)){
			 for (Dkjlptgzlxt dkjlptgzlxt : dkjlptgzlxts5) {
				 QueryWrapper<Dkjlptgzlxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptgzlxt.getDkzh());
				 dkjlptgzlxt.setXgr(sysUser.getUsername());
				 dkjlptgzlxt.setXgsj(new Date());
				 dkjlptgzlxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptgzlxtService.update(dkjlptgzlxt,wrapper);
			 }
		 }

		 //获取关注类进度
		 JSONArray  gzljd6 = jsonObject.getJSONArray("gzljd6");
		 List<Dkjlptgzlxt> dkjlptgzlxts6 = JSONObject.parseArray(gzljd6.toJSONString(), Dkjlptgzlxt.class);
		 if (CollUtil.isNotEmpty(dkjlptgzlxts6)){
		 for (Dkjlptgzlxt dkjlptgzlxt : dkjlptgzlxts6) {
			 QueryWrapper<Dkjlptgzlxt> wrapper = new QueryWrapper<>();
			 wrapper.eq("dkzh",dkjlptgzlxt.getDkzh());
			 dkjlptgzlxt.setZjhm(dkjkptBndksjjktz.getZjhm());
			 dkjlptgzlxt.setXgr(sysUser.getUsername());
			 dkjlptgzlxt.setXgsj(new Date());
			 dkjlptgzlxtService.update(dkjlptgzlxt,wrapper);
		 	}
		 }
		 //获取关注类进度
		 JSONArray  gzljd7 = jsonObject.getJSONArray("gzljd7");
		 List<Dkjlptgzlxt> dkjlptgzlxts7 = JSONObject.parseArray(gzljd7.toJSONString(), Dkjlptgzlxt.class);
		 if (CollUtil.isNotEmpty(dkjlptgzlxts7)){
		 for (Dkjlptgzlxt dkjlptgzlxt : dkjlptgzlxts7) {
			 QueryWrapper<Dkjlptgzlxt> wrapper = new QueryWrapper<>();
			 wrapper.eq("dkzh",dkjlptgzlxt.getDkzh());
			 dkjlptgzlxt.setZjhm(dkjkptBndksjjktz.getZjhm());
			 dkjlptgzlxt.setXgr(sysUser.getUsername());
			 dkjlptgzlxt.setXgsj(new Date());
			 dkjlptgzlxtService.update(dkjlptgzlxt,wrapper);
		 	}
		 }
 		//获取关注类进度
		 JSONArray  gzljd8 = jsonObject.getJSONArray("gzljd8");
		 List<Dkjlptgzlxt> dkjlptgzlxts8 = JSONObject.parseArray(gzljd8.toJSONString(), Dkjlptgzlxt.class);
		 if (CollUtil.isNotEmpty(dkjlptgzlxts8)){
		 for (Dkjlptgzlxt dkjlptgzlxt : dkjlptgzlxts8) {
			 QueryWrapper<Dkjlptgzlxt> wrapper = new QueryWrapper<>();
			 wrapper.eq("dkzh",dkjlptgzlxt.getDkzh());
			 dkjlptgzlxt.setZjhm(dkjkptBndksjjktz.getZjhm());
			 dkjlptgzlxt.setXgr(sysUser.getUsername());
			 dkjlptgzlxt.setXgsj(new Date());
			 dkjlptgzlxtService.update(dkjlptgzlxt,wrapper);
		 	}
		 }
		 //获取关注类进度
		 JSONArray  gzljd9 = jsonObject.getJSONArray("gzljd9");
		 List<Dkjlptgzlxt> dkjlptgzlxts9 = JSONObject.parseArray(gzljd9.toJSONString(), Dkjlptgzlxt.class);
		 if (CollUtil.isNotEmpty(dkjlptgzlxts9)){
			 for (Dkjlptgzlxt dkjlptgzlxt : dkjlptgzlxts9) {
				 QueryWrapper<Dkjlptgzlxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptgzlxt.getDkzh());
				 dkjlptgzlxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptgzlxt.setXgr(sysUser.getUsername());
				 dkjlptgzlxt.setXgsj(new Date());
				 dkjlptgzlxtService.update(dkjlptgzlxt,wrapper);
			 }
		 }
 		//获取关注类进度
		 JSONArray  gzljd10 = jsonObject.getJSONArray("gzljd10");
		 List<Dkjlptgzlxt> dkjlptgzlxts10 = JSONObject.parseArray(gzljd10.toJSONString(), Dkjlptgzlxt.class);
		 if (CollUtil.isNotEmpty(dkjlptgzlxts10)){
			 for (Dkjlptgzlxt dkjlptgzlxt : dkjlptgzlxts10) {
				 QueryWrapper<Dkjlptgzlxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptgzlxt.getDkzh());
				 dkjlptgzlxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptgzlxt.setXgr(sysUser.getUsername());
				 dkjlptgzlxt.setXgsj(new Date());
				 dkjlptgzlxtService.update(dkjlptgzlxt,wrapper);
			 }
		 }
		 //获取关注类进度
		 JSONArray  gzljd11 = jsonObject.getJSONArray("gzljd11");
		 List<Dkjlptgzlxt> dkjlptgzlxts11 = JSONObject.parseArray(gzljd11.toJSONString(), Dkjlptgzlxt.class);
		 if (CollUtil.isNotEmpty(dkjlptgzlxts11)){
			 for (Dkjlptgzlxt dkjlptgzlxt : dkjlptgzlxts11) {
				 QueryWrapper<Dkjlptgzlxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptgzlxt.getDkzh());
				 dkjlptgzlxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptgzlxt.setXgr(sysUser.getUsername());
				 dkjlptgzlxt.setXgsj(new Date());
				 dkjlptgzlxtService.update(dkjlptgzlxt,wrapper);
			 }
		 }
		 //获取关注类进度
		 JSONArray  gzljd12 = jsonObject.getJSONArray("gzljd12");
		 List<Dkjlptgzlxt> dkjlptgzlxts12 = JSONObject.parseArray(gzljd12.toJSONString(), Dkjlptgzlxt.class);
		 if (CollUtil.isNotEmpty(dkjlptgzlxts12)){
			 for (Dkjlptgzlxt dkjlptgzlxt : dkjlptgzlxts12) {
				 QueryWrapper<Dkjlptgzlxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptgzlxt.getDkzh());
				 dkjlptgzlxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptgzlxt.setXgr(sysUser.getUsername());
				 dkjlptgzlxt.setXgsj(new Date());
				 dkjlptgzlxtService.update(dkjlptgzlxt,wrapper);
			 }
		 }



		 //获取表内不良

		 JSONArray bnbl = jsonObject.getJSONArray("bnbl");
		 List<Dkjlptbnblxt> dkjlptbnbljdxtList = JSONObject.parseArray(bnbl.toJSONString(), Dkjlptbnblxt.class);
		 System.out.println("========================"+dkjlptbnbljdxtList);
		 if (CollUtil.isNotEmpty(dkjlptbnbljdxtList)){
			 for (Dkjlptbnblxt dkjlptbnblxt : dkjlptbnbljdxtList) {
				 QueryWrapper<Dkjlptbnblxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptbnblxt.getDkzh());
				 dkjlptbnblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptbnblxt.setXgr(sysUser.getUsername());
				 dkjlptbnblxt.setXgsj(new Date());
				 dkjlptbnblxtService.update(dkjlptbnblxt,wrapper);
			 }
		 }
		 //获取表内不良
		 JSONArray bnblqs = jsonObject.getJSONArray("bnblqs");
		 List<Dkjlptbnblxt> dkjlptbnblxtsqs = JSONObject.parseArray(bnblqs.toJSONString(), Dkjlptbnblxt.class);
		 if (CollUtil.isNotEmpty(dkjlptbnblxtsqs)){
			 for (Dkjlptbnblxt dkjlptbnblxt : dkjlptbnblxtsqs) {
				 QueryWrapper<Dkjlptbnblxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptbnblxt.getDkzh());
				 dkjlptbnblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptbnblxt.setXgr(sysUser.getUsername());
				 dkjlptbnblxt.setXgsj(new Date());
				 dkjlptbnblxtService.update(dkjlptbnblxt,wrapper);
			 }
		 }
		 //获取表内不良
		 JSONArray bnbl1 = jsonObject.getJSONArray("bnbl1");
		 List<Dkjlptbnblxt> dkjlptbnblxts1 = JSONObject.parseArray(bnbl1.toJSONString(), Dkjlptbnblxt.class);
		 if (CollUtil.isNotEmpty(dkjlptbnblxts1)){
			 for (Dkjlptbnblxt dkjlptbnblxt : dkjlptbnblxts1) {
				 QueryWrapper<Dkjlptbnblxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptbnblxt.getDkzh());
				 dkjlptbnblxtService.update(dkjlptbnblxt,wrapper);
			 }
		 }
		 //获取表内不良
		 JSONArray bnbl2 = jsonObject.getJSONArray("bnbl2");
		 List<Dkjlptbnblxt> dkjlptbnblxts2 = JSONObject.parseArray(bnbl2.toJSONString(), Dkjlptbnblxt.class);
		 if (CollUtil.isNotEmpty(dkjlptbnblxts2)){
			 for (Dkjlptbnblxt dkjlptbnblxt : dkjlptbnblxts2) {
				 QueryWrapper<Dkjlptbnblxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptbnblxt.getDkzh());
				 dkjlptbnblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptbnblxtService.update(dkjlptbnblxt,wrapper);
			 }
		 }
		 //获取表内不良
		 JSONArray bnbl3 = jsonObject.getJSONArray("bnbl3");
		 List<Dkjlptbnblxt> dkjlptbnblxts3 = JSONObject.parseArray(bnbl3.toJSONString(), Dkjlptbnblxt.class);
		 if (CollUtil.isNotEmpty(dkjlptbnblxts3)){
			 for (Dkjlptbnblxt dkjlptbnblxt : dkjlptbnblxts3) {
				 QueryWrapper<Dkjlptbnblxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptbnblxt.getDkzh());
				 dkjlptbnblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptbnblxt.setXgr(sysUser.getUsername());
				 dkjlptbnblxt.setXgsj(new Date());
				 dkjlptbnblxtService.update(dkjlptbnblxt,wrapper);
			 }
		 }
		 //获取表内不良
		 JSONArray bnbl4 = jsonObject.getJSONArray("bnbl4");
		 List<Dkjlptbnblxt> dkjlptbnblxts4 = JSONObject.parseArray(bnbl4.toJSONString(), Dkjlptbnblxt.class);
		 if (CollUtil.isNotEmpty(dkjlptbnblxts4)){
			 for (Dkjlptbnblxt dkjlptbnblxt : dkjlptbnblxts4) {
				 QueryWrapper<Dkjlptbnblxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptbnblxt.getDkzh());
				 dkjlptbnblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptbnblxt.setXgr(sysUser.getUsername());
				 dkjlptbnblxt.setXgsj(new Date());
				 dkjlptbnblxtService.update(dkjlptbnblxt,wrapper);
			 }
		 }
		 //获取表内不良
		 JSONArray bnbl5 = jsonObject.getJSONArray("bnbl5");
		 List<Dkjlptbnblxt> dkjlptbnblxts5 = JSONObject.parseArray(bnbl5.toJSONString(), Dkjlptbnblxt.class);
		 if (CollUtil.isNotEmpty(dkjlptbnblxts5)){
			 for (Dkjlptbnblxt dkjlptbnblxt : dkjlptbnblxts5) {
				 QueryWrapper<Dkjlptbnblxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptbnblxt.getDkzh());
				 dkjlptbnblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptbnblxt.setXgr(sysUser.getUsername());
				 dkjlptbnblxt.setXgsj(new Date());
				 dkjlptbnblxtService.update(dkjlptbnblxt,wrapper);
			 }
		 }
		 //获取表内不良
		 JSONArray bnbl6 = jsonObject.getJSONArray("bnbl6");
		 List<Dkjlptbnblxt> dkjlptbnblxts6 = JSONObject.parseArray(bnbl6.toJSONString(), Dkjlptbnblxt.class);
		 if (CollUtil.isNotEmpty(dkjlptbnblxts6)){
			 for (Dkjlptbnblxt dkjlptbnblxt : dkjlptbnblxts6) {
				 QueryWrapper<Dkjlptbnblxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptbnblxt.getDkzh());
				 dkjlptbnblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptbnblxt.setXgr(sysUser.getUsername());
				 dkjlptbnblxt.setXgsj(new Date());
				 dkjlptbnblxtService.update(dkjlptbnblxt,wrapper);
			 }
		 }
		 //获取表内不良
		 JSONArray bnbl7 = jsonObject.getJSONArray("bnbl7");
		 List<Dkjlptbnblxt> dkjlptbnblxts7 = JSONObject.parseArray(bnbl7.toJSONString(), Dkjlptbnblxt.class);
		 if (CollUtil.isNotEmpty(dkjlptbnblxts7)){
			 for (Dkjlptbnblxt dkjlptbnblxt : dkjlptbnblxts7) {
				 QueryWrapper<Dkjlptbnblxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptbnblxt.getDkzh());
				 dkjlptbnblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptbnblxt.setXgr(sysUser.getUsername());
				 dkjlptbnblxt.setXgsj(new Date());
				 dkjlptbnblxtService.update(dkjlptbnblxt,wrapper);
			 }
		 }
		 //获取表内不良
		 JSONArray bnbl8 = jsonObject.getJSONArray("bnbl8");
		 List<Dkjlptbnblxt> dkjlptbnblxts8 = JSONObject.parseArray(bnbl8.toJSONString(), Dkjlptbnblxt.class);
		 if (CollUtil.isNotEmpty(dkjlptbnblxts8)){
			 for (Dkjlptbnblxt dkjlptbnblxt : dkjlptbnblxts8) {
			 	QueryWrapper<Dkjlptbnblxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptbnblxt.getDkzh());
				 dkjlptbnblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptbnblxt.setXgr(sysUser.getUsername());
				 dkjlptbnblxt.setXgsj(new Date());
				 dkjlptbnblxtService.update(dkjlptbnblxt,wrapper);
			 }
		 }
		 //获取表内不良
		 JSONArray bnbl9 = jsonObject.getJSONArray("bnbl9");
		 List<Dkjlptbnblxt> dkjlptbnblxts9 = JSONObject.parseArray(bnbl9.toJSONString(), Dkjlptbnblxt.class);
		 if (CollUtil.isNotEmpty(dkjlptbnblxts9)){
			 for (Dkjlptbnblxt dkjlptbnblxt : dkjlptbnblxts9) {
				 QueryWrapper<Dkjlptbnblxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptbnblxt.getDkzh());
				 dkjlptbnblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptbnblxt.setXgr(sysUser.getUsername());
				 dkjlptbnblxt.setXgsj(new Date());
				 dkjlptbnblxtService.update(dkjlptbnblxt,wrapper);
			 }
		 }
		 //获取表内不良
		 JSONArray bnbl10 = jsonObject.getJSONArray("bnbl10");
		 List<Dkjlptbnblxt> dkjlptbnblxts10 = JSONObject.parseArray(bnbl10.toJSONString(), Dkjlptbnblxt.class);
		 if (CollUtil.isNotEmpty(dkjlptbnblxts10)){
			 for (Dkjlptbnblxt dkjlptbnblxt : dkjlptbnblxts10) {
				 QueryWrapper<Dkjlptbnblxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptbnblxt.getDkzh());
				 dkjlptbnblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptbnblxt.setXgr(sysUser.getUsername());
				 dkjlptbnblxt.setXgsj(new Date());
				 dkjlptbnblxtService.update(dkjlptbnblxt,wrapper);
			 }
		 }
		 //获取表内不良
		 JSONArray bnbl11 = jsonObject.getJSONArray("bnbl11");
		 List<Dkjlptbnblxt> dkjlptbnblxts11 = JSONObject.parseArray(bnbl11.toJSONString(), Dkjlptbnblxt.class);
		 if (CollUtil.isNotEmpty(dkjlptbnblxts11)){
			 for (Dkjlptbnblxt dkjlptbnblxt : dkjlptbnblxts11) {
				 QueryWrapper<Dkjlptbnblxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptbnblxt.getDkzh());
				 dkjlptbnblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptbnblxt.setXgr(sysUser.getUsername());
				 dkjlptbnblxt.setXgsj(new Date());
				 dkjlptbnblxtService.update(dkjlptbnblxt,wrapper);
			 }
		 }
		 //获取表内不良
		 JSONArray bnbl12 = jsonObject.getJSONArray("bnbl12");
		 List<Dkjlptbnblxt> dkjlptbnblxts12 = JSONObject.parseArray(bnbl12.toJSONString(), Dkjlptbnblxt.class);
		 if (CollUtil.isNotEmpty(dkjlptbnblxts12)){
			 for (Dkjlptbnblxt dkjlptbnblxt : dkjlptbnblxts12) {
				 QueryWrapper<Dkjlptbnblxt> wrapper = new QueryWrapper<>();
				 wrapper.eq("dkzh",dkjlptbnblxt.getDkzh());
				 dkjlptbnblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjlptbnblxt.setXgr(sysUser.getUsername());
				 dkjlptbnblxt.setXgsj(new Date());
				 dkjlptbnblxtService.update(dkjlptbnblxt,wrapper);
			 }
		 }


		 //获取表外不良
		/* JSONArray bwbl = jsonObject.getJSONArray("bwbl");
		 List<Dkjkptbwblxt> dkjkptbwblxts = JSONObject.parseArray(bwbl.toJSONString(), Dkjkptbwblxt.class);
		 if (CollUtil.isNotEmpty(dkjkptbwblxts)){
			 for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts) {
				 QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
				 queryWrapperBwbl.eq("dkzh",dkjkptbwblxt.getDkzh());
				 dkjkptbwblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjkptbwblxt.setXgr(sysUser.getUsername());
				 dkjkptbwblxt.setXgsj(new Date());
				 dkjkptbwblxtService.update(dkjkptbwblxt,queryWrapperBwbl);
			 }
		 }
		 //获取表外不良
		 JSONArray bwblqs = jsonObject.getJSONArray("bwblqs");
		 List<Dkjkptbwblxt> dkjkptbwblxtsqs = JSONObject.parseArray(bwblqs.toJSONString(), Dkjkptbwblxt.class);
		 if (CollUtil.isNotEmpty(dkjkptbwblxtsqs)){
			 for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxtsqs) {
				 QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
				 queryWrapperBwbl.eq("dkzh",dkjkptbwblxt.getDkzh());
				 dkjkptbwblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjkptbwblxt.setXgr(sysUser.getUsername());
				 dkjkptbwblxt.setXgsj(new Date());
				 dkjkptbwblxtService.update(dkjkptbwblxt,queryWrapperBwbl);
			 }
		 }
		 //获取表外不良
		 JSONArray bwbl1 = jsonObject.getJSONArray("bwbl1");
		 List<Dkjkptbwblxt> dkjkptbwblxts1 = JSONObject.parseArray(bwbl1.toJSONString(), Dkjkptbwblxt.class);
		 if (CollUtil.isNotEmpty(dkjkptbwblxts1)) {
			 for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts1) {
				 QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
				 queryWrapperBwbl.eq("dkzh", dkjkptbwblxt.getDkzh());
				 dkjkptbwblxt.setMonth1(dkjkptbwblxt.getMonth1());
				 dkjkptbwblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjkptbwblxt.setXgr(sysUser.getUsername());
				 dkjkptbwblxt.setXgsj(new Date());
				 dkjkptbwblxtService.update(dkjkptbwblxt, queryWrapperBwbl);
			 }
			 JSONArray bwbl2 = jsonObject.getJSONArray("bwbl2");
			 List<Dkjkptbwblxt> dkjkptbwblxts2 = JSONObject.parseArray(bwbl2.toJSONString(), Dkjkptbwblxt.class);
			 if (CollUtil.isNotEmpty(dkjkptbwblxts2)) {
				 for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts2) {
					 QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
					 System.out.println("dkjkptbwblxt2==" + dkjkptbwblxt);
					 queryWrapperBwbl.eq("dkzh", dkjkptbwblxt.getDkzh());
					 dkjkptbwblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
					 dkjkptbwblxt.setXgr(sysUser.getUsername());
					 dkjkptbwblxt.setXgsj(new Date());
					 dkjkptbwblxtService.update(dkjkptbwblxt, queryWrapperBwbl);
				 }
			 }
			 JSONArray bwbl3 = jsonObject.getJSONArray("bwbl3");
			 List<Dkjkptbwblxt> dkjkptbwblxts3 = JSONObject.parseArray(bwbl3.toJSONString(), Dkjkptbwblxt.class);
			 if (CollUtil.isNotEmpty(dkjkptbwblxts3)) {
				 for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts3) {
					 QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
					 System.out.println("dkjkptbwblxts3==" + dkjkptbwblxt);
					 queryWrapperBwbl.eq("dkzh", dkjkptbwblxt.getDkzh());
					 dkjkptbwblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
					 dkjkptbwblxt.setXgr(sysUser.getUsername());
					 dkjkptbwblxt.setXgsj(new Date());
					 dkjkptbwblxtService.update(dkjkptbwblxt, queryWrapperBwbl);
				 }
			 }
			 JSONArray bwbl4 = jsonObject.getJSONArray("bwbl4");
			 List<Dkjkptbwblxt> dkjkptbwblxts4 = JSONObject.parseArray(bwbl4.toJSONString(), Dkjkptbwblxt.class);
			 if (CollUtil.isNotEmpty(dkjkptbwblxts4)) {
				 for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts4) {
					 QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
					 queryWrapperBwbl.eq("dkzh", dkjkptbwblxt.getDkzh());
					 dkjkptbwblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
					 dkjkptbwblxt.setXgr(sysUser.getUsername());
					 dkjkptbwblxt.setXgsj(new Date());
					 dkjkptbwblxtService.update(dkjkptbwblxt, queryWrapperBwbl);
				 }
			 }
			 JSONArray bwbl5 = jsonObject.getJSONArray("bwbl5");
			 List<Dkjkptbwblxt> dkjkptbwblxts5 = JSONObject.parseArray(bwbl5.toJSONString(), Dkjkptbwblxt.class);
			 if (CollUtil.isNotEmpty(dkjkptbwblxts5)) {
				 for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts5) {
					 QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
					 queryWrapperBwbl.eq("dkzh", dkjkptbwblxt.getDkzh());
					 dkjkptbwblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
					 dkjkptbwblxt.setXgr(sysUser.getUsername());
					 dkjkptbwblxt.setXgsj(new Date());
					 dkjkptbwblxtService.update(dkjkptbwblxt, queryWrapperBwbl);
				 }
			 }
			 JSONArray bwbl6 = jsonObject.getJSONArray("bwbl6");
			 List<Dkjkptbwblxt> dkjkptbwblxts6 = JSONObject.parseArray(bwbl6.toJSONString(), Dkjkptbwblxt.class);
			 if (CollUtil.isNotEmpty(dkjkptbwblxts6)) {
				 for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts6) {
					 QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
					 queryWrapperBwbl.eq("dkzh", dkjkptbwblxt.getDkzh());
					 dkjkptbwblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
					 dkjkptbwblxt.setXgr(sysUser.getUsername());
					 dkjkptbwblxt.setXgsj(new Date());
					 dkjkptbwblxtService.update(dkjkptbwblxt, queryWrapperBwbl);
				 }
			 }
			 JSONArray bwbl7 = jsonObject.getJSONArray("bwbl7");
			 List<Dkjkptbwblxt> dkjkptbwblxts7 = JSONObject.parseArray(bwbl7.toJSONString(), Dkjkptbwblxt.class);
			 if (CollUtil.isNotEmpty(dkjkptbwblxts7)) {
				 for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts7) {
					 QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
					 queryWrapperBwbl.eq("dkzh", dkjkptbwblxt.getDkzh());
					 dkjkptbwblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
					 dkjkptbwblxt.setXgr(sysUser.getUsername());
					 dkjkptbwblxt.setXgsj(new Date());
					 dkjkptbwblxtService.update(dkjkptbwblxt, queryWrapperBwbl);
				 }
			 }
			 JSONArray bwbl8 = jsonObject.getJSONArray("bwbl8");
			 List<Dkjkptbwblxt> dkjkptbwblxts8 = JSONObject.parseArray(bwbl8.toJSONString(), Dkjkptbwblxt.class);
			 if (CollUtil.isNotEmpty(dkjkptbwblxts8)) {
				 for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts8) {
					 QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
					 queryWrapperBwbl.eq("dkzh", dkjkptbwblxt.getDkzh());
					 dkjkptbwblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
					 dkjkptbwblxt.setXgr(sysUser.getUsername());
					 dkjkptbwblxt.setXgsj(new Date());
					 dkjkptbwblxtService.update(dkjkptbwblxt, queryWrapperBwbl);
				 }
			 }
			 JSONArray bwbl9 = jsonObject.getJSONArray("bwbl9");
			 List<Dkjkptbwblxt> dkjkptbwblxts9 = JSONObject.parseArray(bwbl9.toJSONString(), Dkjkptbwblxt.class);
			 if (CollUtil.isNotEmpty(dkjkptbwblxts9)) {
				 for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts9) {
					 QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
					 queryWrapperBwbl.eq("dkzh", dkjkptbwblxt.getDkzh());
					 dkjkptbwblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
					 dkjkptbwblxt.setXgr(sysUser.getUsername());
					 dkjkptbwblxt.setXgsj(new Date());
					 dkjkptbwblxtService.update(dkjkptbwblxt, queryWrapperBwbl);
				 }
			 }
		 }
		JSONArray bwbl10 = jsonObject.getJSONArray("bwbl10");
			 List<Dkjkptbwblxt> dkjkptbwblxts10 = JSONObject.parseArray(bwbl10.toJSONString(), Dkjkptbwblxt.class);
			 if (CollUtil.isNotEmpty(dkjkptbwblxts10)){
				 for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts10) {
					 QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
					 queryWrapperBwbl.eq("dkzh",dkjkptbwblxt.getDkzh());
					 dkjkptbwblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
					 dkjkptbwblxt.setXgr(sysUser.getUsername());
					 dkjkptbwblxt.setXgsj(new Date());
					 dkjkptbwblxtService.update(dkjkptbwblxt,queryWrapperBwbl);
				 }
			 }
		JSONArray bwbl11 = jsonObject.getJSONArray("bwbl11");
				 List<Dkjkptbwblxt> dkjkptbwblxts11 = JSONObject.parseArray(bwbl11.toJSONString(), Dkjkptbwblxt.class);
				 if (CollUtil.isNotEmpty(dkjkptbwblxts11)){
					 for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts11) {
						 QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
						 queryWrapperBwbl.eq("dkzh",dkjkptbwblxt.getDkzh());
						 dkjkptbwblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
						 dkjkptbwblxt.setXgr(sysUser.getUsername());
						 dkjkptbwblxt.setXgsj(new Date());
						 dkjkptbwblxtService.update(dkjkptbwblxt,queryWrapperBwbl);
					 }
				 }
		 JSONArray bwbl12 = jsonObject.getJSONArray("bwbl12");
		 List<Dkjkptbwblxt> dkjkptbwblxts12 = JSONObject.parseArray(bwbl12.toJSONString(), Dkjkptbwblxt.class);
		 if (CollUtil.isNotEmpty(dkjkptbwblxts12)){
			 for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts12) {
				 QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
				 queryWrapperBwbl.eq("dkzh",dkjkptbwblxt.getDkzh());
				 dkjkptbwblxt.setZjhm(dkjkptBndksjjktz.getZjhm());
				 dkjkptbwblxt.setXgr(sysUser.getUsername());
				 dkjkptbwblxt.setXgsj(new Date());
				 dkjkptbwblxtService.update(dkjkptbwblxt,queryWrapperBwbl);
			 }
		 }*/


		 //获取诉讼资产
		 JSONObject  sszc = JSONObject.parseObject(JSONObject.toJSONString(jsonObject.get("sszc")));
		 Dkjkptsszcxt sszcb = JSONObject.parseObject(sszc.toJSONString(), Dkjkptsszcxt.class);
		 QueryWrapper<Dkjkptsszcxt> dkjkptsszcxtQueryWrapper = new QueryWrapper<>();
		 dkjkptsszcxtQueryWrapper.eq("zjhm",dkjkptBndksjjktzEntity.getZjhm());
		 dkjkptsszcxtQueryWrapper.eq("dkzh",dkjkptBndksjjktzEntity.getDkzh());
		 Dkjkptsszcxt dkjkptsszcxt = dkjkptsszcxtService.getOne(dkjkptsszcxtQueryWrapper);
		 if (dkjkptsszcxt != null){
			 sszcb.setJgdm(dkjkptBndksjjktzEntity.getJgdm());
			 sszcb.setXgr(sysUser.getUsername());
			 sszcb.setXgsj(new Date());
			 dkjkptsszcxtService.update(sszcb,dkjkptsszcxtQueryWrapper);
		 }else {
		 	 sszcb.setJgdm(dkjkptBndksjjktzEntity.getJgdm());
		 	 sszcb.setZjhm(dkjkptBndksjjktzEntity.getZjhm());
		 	 sszcb.setDkzh(dkjkptBndksjjktzEntity.getDkzh());
			 sszcb.setXgr(sysUser.getUsername());
			 sszcb.setXgsj(new Date());
			 dkjkptsszcxtService.save(sszcb);
		 }


		 return Result.ok("修改成功");
	 }



	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款数据监控台账-通过id删除")
	@ApiOperation(value="贷款数据监控台账-通过id删除", notes="贷款数据监控台账-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			dkjkptBndksjjktzService.removeById(id);
		} catch (Exception e) {
			log.error("删除失败",e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}
	 /**
	  *  查询数据
	  * @param
	  * @return
	  */
	 @RequestMapping(value = "/query", method = RequestMethod.GET)
	 public Result<?> query(@RequestParam(name="dkzh",required=true)String zjhm ) {
QueryWrapper<DkjkptBndksjjktz> dkjkptBndksjjktzQueryWrapper=new QueryWrapper<>();
dkjkptBndksjjktzQueryWrapper.eq("dkzh",zjhm);
		 List<DkjkptBndksjjktz> list = dkjkptBndksjjktzService.list(dkjkptBndksjjktzQueryWrapper);
		 if (list.size()>0) {
			 return Result.ok(list.get(0));
		 }else {
		 	return Result.ok(new DkjkptBndksjjktz());
		 }
	 }


	 /**
	  *  查询数据关联人信息
	  * @param
	  * @return
	  */
	 @RequestMapping(value = "/queryGlrxx", method = RequestMethod.GET)
	 public Result<?> queryGlrxx(@RequestParam(name="zjhm",required=true)String zjhm ) {
		 QueryWrapper<DkjkptDkkhglrgl> dkkhglrglQueryWrapper=new QueryWrapper<>();
		 dkkhglrglQueryWrapper.eq("jkrzjhm",zjhm);
		 List<DkjkptDkkhglrgl> list = dkjkptDkkhglrglService.list(dkkhglrglQueryWrapper);
			 return Result.ok(list);
	 }

	 /**
	  *  查询数据湘潭关注类
	  * @param
	  * @return
	  */
	 @RequestMapping(value = "/queryGzl", method = RequestMethod.GET)
	 public Result<?> queryGzl(@RequestParam(name="tjnf",required=true)String tjnf,
							   @RequestParam(name="zjhm",required=true)String zjhm ) {
		 log.info("关注类查询年份======"+tjnf);
		 try {
			 dkjlptgzlxtService.gzl(tjnf.replaceAll("-",""),zjhm);
		 }catch (Throwable e){
			 System.out.println(e);
			 log.error("提取失败",e.getMessage());
		 }

		 QueryWrapper<Dkjlptgzlxt> wrapper=new QueryWrapper<>();
		 	/* if (tjnf.substring(0,4).equals(DateUtil.format(new Date(),"yyyy-MM-dd").substring(0,4))){
				 wrapper.eq("tjrq",DateUtil.parse(tjnf));
			 }else {
				 wrapper.between("tjrq",DateUtil.beginOfYear(DateUtil.parse(tjnf)),DateUtil.endOfYear(DateUtil.parse(tjnf)));
			 }*/
			 wrapper.eq("zjhm",zjhm);
			 List<Dkjlptgzlxt> list2 = dkjlptgzlxtService.list(wrapper);
			 if (CollUtil.isNotEmpty(list2)){
				 BigDecimal khdkzye = list2.stream().map(Dkjlptgzlxt::getDqgzye).reduce(BigDecimal.ZERO, BigDecimal::add);//BigDecimal类型
				 list2.forEach(e -> e.setKhdkzye(khdkzye));
			 }
		 return Result.ok(list2);



	 }

	 /**
	  *  查询数据湘潭表内不良
	  * @param
	  * @return
	  */
	 @RequestMapping(value = "/queryBnbl", method = RequestMethod.GET)
	 public Result<?> queryBnbl(@RequestParam(name="zjhm",required=true)String zjhm ) {

	 	QueryWrapper<Dkjlptbnblxt> wrapper=new QueryWrapper<>();
		/* if (tjnf.substring(0,4).equals(DateUtil.format(new Date(),"yyyy-MM-dd").substring(0,4))){
			 wrapper.eq("tjrq",DateUtil.parse(tjnf));
		 }else {
			 wrapper.eq("tjrq",DateUtil.beginOfYear(DateUtil.parse(tjnf)));
		 }*/
		 wrapper.eq("zjhm",zjhm);
		 List<Dkjlptbnblxt> list2 = dkjlptbnblxtService.list(wrapper);
		 if (CollUtil.isNotEmpty(list2)){
			 BigDecimal khdkzye = list2.stream().map(Dkjlptbnblxt::getDqblye).reduce(BigDecimal.ZERO, BigDecimal::add);//BigDecimal类型
			 list2.forEach(e -> e.setKhdkzye(khdkzye));
		 }
		 return Result.ok(list2);

	 }
	 /**
	  *  查询数据湘潭表外不良
	  * @param
	  * @return
	  */
	 @RequestMapping(value = "/queryBwbl", method = RequestMethod.GET)
	 public Result<?> queryBwbl(
							   @RequestParam(name="zjhm",required=true)String zjhm ) {
			 QueryWrapper<Dkjkptbwblxt> wrapper=new QueryWrapper<>();
			/* if (tjnf.substring(0,4).equals(DateUtil.format(new Date(),"yyyy-MM-dd").substring(0,4))){
				 wrapper.eq("tjrq",DateUtil.parse(tjnf));
			 }else {
				 wrapper.eq("tjrq",DateUtil.beginOfYear(DateUtil.parse(tjnf)));
			 }*/
			 wrapper.eq("zjhm",zjhm);
			 List<Dkjkptbwblxt> list2 = dkjkptbwblxtService.list(wrapper);
			 return Result.ok(list2);

	 }

	 /**
	  *  查询数据湘潭诉讼资产
	  * @param
	  * @return
	  */
	 @RequestMapping(value = "/querySszc", method = RequestMethod.GET)
	 public Result<?> querySszc(@RequestParam(name="zjhm",required=true)String zjhm,@RequestParam(name="dkzh",required=true)String dkzh ) {
		 QueryWrapper<Dkjkptsszcxt> queryWrapper=new QueryWrapper<>();
		 queryWrapper.eq("zjhm",zjhm);
		 queryWrapper.eq("dkzh",dkzh);
		 Dkjkptsszcxt one = dkjkptsszcxtService.getOne(queryWrapper);
		 if (one != null) {
			 return Result.ok(one);
		 }else {
			 return Result.ok(new Dkjkptsszcxt());
		 }

	 }




		 /**
           *  批量删除
          * @param ids
          * @return
          */
	@AutoLog(value = "贷款数据监控台账-批量删除")
	@ApiOperation(value="贷款数据监控台账-批量删除", notes="贷款数据监控台账-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<DkjkptBndksjjktz> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<DkjkptBndksjjktz> result = new Result<DkjkptBndksjjktz>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.dkjkptBndksjjktzService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款数据监控台账-通过id查询")
	@ApiOperation(value="贷款数据监控台账-通过id查询", notes="贷款数据监控台账-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<DkjkptBndksjjktz> queryById(@RequestParam(name="id",required=true) String id) {
		Result<DkjkptBndksjjktz> result = new Result<DkjkptBndksjjktz>();
		DkjkptBndksjjktz dkjkptBndksjjktz = dkjkptBndksjjktzService.getById(id);
		if(dkjkptBndksjjktz==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(dkjkptBndksjjktz);
			result.setSuccess(true);
		}
		return result;
	}



  /**
      * 导出excel
   *
   * @param request
   * @param response
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<DkjkptBndksjjktz> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              DkjkptBndksjjktz dkjkptBndksjjktz = JSON.parseObject(deString, DkjkptBndksjjktz.class);
              queryWrapper = QueryGenerator.initQueryWrapper(dkjkptBndksjjktz, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<DkjkptBndksjjktz> pageList = dkjkptBndksjjktzService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "贷款数据监控台账列表");
      mv.addObject(NormalExcelConstants.CLASS, DkjkptBndksjjktz.class);
      LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("贷款数据监控台账列表数据", "导出人:"+loginUser.getRealname(), "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
  }


	 /**
	  * 导出excel关注类
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportXlsGzl")
	 public ModelAndView exportXlsGzl(HttpServletRequest request, HttpServletResponse response) {
		 // Step.1 组装查询条件
		 QueryWrapper<Dkjlptgzlxt> queryWrapper = null;
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)) {
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 Dkjlptgzlxt dkjkptBndksjjktz = JSON.parseObject(deString, Dkjlptgzlxt.class);
				 queryWrapper = QueryGenerator.initQueryWrapper(dkjkptBndksjjktz, request.getParameterMap());
				 queryWrapper.eq("dkxt","2");
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }

		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<Dkjlptgzlxt> pageList = dkjlptgzlxtService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "贷款数据监控台账关注类");
		 mv.addObject(NormalExcelConstants.CLASS, Dkjlptgzlxt.class);
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("贷款数据监控台账关注类列表数据", "导出人:"+loginUser.getRealname(), "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		 return mv;
	 }
	 /**
	  * 导出excel表内不良
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportXlsBnbl")
	 public ModelAndView exportXlsBnbl(HttpServletRequest request, HttpServletResponse response) {
		 // Step.1 组装查询条件
		 QueryWrapper<Dkjlptbnblxt> queryWrapper = null;
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)) {
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 Dkjlptbnblxt dkjkptBndksjjktz = JSON.parseObject(deString, Dkjlptbnblxt.class);
				 queryWrapper = QueryGenerator.initQueryWrapper(dkjkptBndksjjktz, request.getParameterMap());
				 queryWrapper.inSql("dkxt","'3','4','5'");
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }

		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<Dkjlptbnblxt> pageList = dkjlptbnblxtService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "贷款数据监控台账表内不良");
		 mv.addObject(NormalExcelConstants.CLASS, Dkjlptbnblxt.class);
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("贷款数据监控台账表内不良", "导出人:"+loginUser.getRealname(), "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		 return mv;
	 }
	 /**
	  * 导出excel表外不良
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportXlsBwbl")
	 public ModelAndView exportXlsBwbl(HttpServletRequest request, HttpServletResponse response) {
		 // Step.1 组装查询条件
		 QueryWrapper<Dkjkptbwblxt> queryWrapper = null;
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)) {
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 Dkjkptbwblxt dkjkptBndksjjktz = JSON.parseObject(deString, Dkjkptbwblxt.class);
				 queryWrapper = QueryGenerator.initQueryWrapper(dkjkptBndksjjktz, request.getParameterMap());
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }

		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<Dkjkptbwblxt> pageList = dkjkptbwblxtService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "贷款数据监控台账表外不良");
		 mv.addObject(NormalExcelConstants.CLASS, Dkjkptbwblxt.class);
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("贷款数据监控台账表外不良", "导出人:"+loginUser.getRealname(), "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		 return mv;
	 }
	 /**
	  * 导出excel诉讼资产
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportXlsSszc")
	 public ModelAndView exportXlsSszc(HttpServletRequest request, HttpServletResponse response) {
		 // Step.1 组装查询条件
		 QueryWrapper<Dkjkptsszcxt> queryWrapper = null;
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)) {
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 Dkjkptsszcxt dkjkptBndksjjktz = JSON.parseObject(deString, Dkjkptsszcxt.class);
				 queryWrapper = QueryGenerator.initQueryWrapper(dkjkptBndksjjktz, request.getParameterMap());
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }

		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<Dkjkptsszcxt> pageList = dkjkptsszcxtService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "贷款数据监控台账诉讼资产");
		 mv.addObject(NormalExcelConstants.CLASS, Dkjkptsszcxt.class);
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("贷款数据监控台账诉讼资产", "导出人:"+loginUser.getRealname(), "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
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
		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "贷款数据监控台账导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, DkjkptBndksjjktzImport.class);
		 ExportParams exportParams = new ExportParams("贷款数据监控台账导入模板", "模板信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<DkjkptBndksjjktzImport>());
		 return mv;
	 }

	 /**
	  * 导出模板excel关注类
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXlsGzl")
	 public ModelAndView exportTemplateXlsGzl(HttpServletRequest request, HttpServletResponse response) {
		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "贷款数据监控台账关注类导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, DkjlptgzlxtImport.class);
		 ExportParams exportParams = new ExportParams("贷款数据监控台账关注类导入模板", "模板信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return mv;
	 }
 	/**
	  * 导出模板excel表内不良
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXlsBnbl")
	 public ModelAndView exportTemplateXlsBnbl(HttpServletRequest request, HttpServletResponse response) {
		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "贷款数据监控台账表内不良导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, DkjlptbnblxtImport.class);
		 ExportParams exportParams = new ExportParams("贷款数据监控台账表内不良导入模板", "模板信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return mv;
	 }

	 /**
	  * 导出模板excel表外不良
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXlsBwbl")
	 public ModelAndView exportTemplateXlsBwbl(HttpServletRequest request, HttpServletResponse response) {
		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "贷款数据监控台账表外不良导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, DkjkptbwblxtImport.class);
		 ExportParams exportParams = new ExportParams("贷款数据监控台账表外不良导入模板", "模板信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return mv;
	 }
	 /**
	  * 导出模板excel诉讼资产
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXlsSszc")
	 public ModelAndView exportTemplateXlsSszc(HttpServletRequest request, HttpServletResponse response) {
		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "贷款数据监控台账讼资产导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, DkjkptsszcxtImport.class);
		 ExportParams exportParams = new ExportParams("贷款数据监控台账讼资产导入模板", "模板信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
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
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 String filePaths = jsonObject.getString("filePaths");
		 if (StringUtils.isEmpty(filePaths)) {
			 return Result.error("请先上传文件！");
		 }
		 String[] filePathList = filePaths.split(",");
		 for (String filePath : filePathList) {
			 filePath = uploadpath + File.separator + filePath;
			 File file = new File(filePath);
			 ImportParams params = new ImportParams();
			 params.setTitleRows(1);
			 params.setHeadRows(1);
			 params.setNeedSave(true);
			 try {
				 List<DkjkptBndksjjktz> listdksjjktz = ExcelImportUtil.importExcel(file,DkjkptBndksjjktz.class, params);
				 List<String> ids = new ArrayList<String>();
				 for (DkjkptBndksjjktz dksjjktz : listdksjjktz) {
					 DkjkptBndksjjktz sjtzsjk = dkjkptBndksjjktzService.queryByDkzh(dksjjktz.getDkzh());
					 if (sjtzsjk != null) {
					 	 if(dksjjktz.getBlxcyy()!=""&&dksjjktz.getBlxcyy()!=null){
							 sjtzsjk.setBlxcyy(dksjjktz.getBlxcyy());
						 }
						 if(dksjjktz.getZrjd()!=""&&dksjjktz.getZrjd()!=null){
							 sjtzsjk.setZrjd(dksjjktz.getZrjd());
						 }
						 if(dksjjktz.getQsczcs()!=""&&dksjjktz.getQsczcs()!=null){
							 sjtzsjk.setQsczcs(dksjjktz.getQsczcs());
						 }
						 if(dksjjktz.getQsczsx()!=""&&dksjjktz.getQsczsx()!=null){
							 sjtzsjk.setQsczsx(dksjjktz.getQsczsx());
						 }
						 if(dksjjktz.getZyzrr()!=""&&dksjjktz.getZyzrr()!=null){
							 sjtzsjk.setZyzrr(dksjjktz.getZyzrr());
						 }
						 if(dksjjktz.getCyzrr()!=""&&dksjjktz.getCyzrr()!=null){
							 sjtzsjk.setCyzrr(dksjjktz.getCyzrr());
						 }
						 if(dksjjktz.getDkzrr()!=""&&dksjjktz.getDkzrr()!=null){
							 sjtzsjk.setDkzrr(dksjjktz.getDkzrr());
						 }
						 if(dksjjktz.getQszrr()!=""&&dksjjktz.getQszrr()!=null){
							 sjtzsjk.setQszrr(dksjjktz.getQszrr());
						 }
					 }
					 dkjkptBndksjjktzService.saveOrUpdate(sjtzsjk);
				 }
				 return Result.ok("文件导入成功！数据行数:" + listdksjjktz.size());
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败:" + e.getMessage());
			 }
		 }
		 return Result.ok("文件导入失败！");
	 }

	 /**
	  * 通过excel导入数据关注类
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/importExcelGzl", method = RequestMethod.POST)
	 public Result<?> importExcelGzl(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
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
			 if (gzlImportVerify != null) {
				 params.setVerifyHanlder(gzlImportVerify);
			 }
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<Dkjlptgzlxt> importResult = ExcelImportUtil.importExcelVerify(file,Dkjlptgzlxt.class, DkjlptgzlxtImport.class, params);
				 List<Dkjlptgzlxt> list = importResult.getList();
				 List<Dkjlptgzlxt> dkjlptgzlxts = new ArrayList<>();
				 for (Dkjlptgzlxt zzsfpxx : list) {
					 zzsfpxx.setLrr(sysUser.getUsername());
					 zzsfpxx.setLrsj(new Date());
					 dkjlptgzlxts.add(zzsfpxx);
				 }
				 dkjlptgzlxtService.saveBatch(dkjlptgzlxts);
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
				 IoUtil.close(fos);
			 }
		 }
		 return Result.ok("文件导入失败！");
	 }

 /**
	  * 通过excel导入数据表内不良
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/importExcelBnbl", method = RequestMethod.POST)
	 public Result<?> importExcelBnbl(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
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
			 if (bnblImportVerify != null) {
				 params.setVerifyHanlder(bnblImportVerify);
			 }
			 FileInputStream fis = null;
			 FileOutputStream fos = null;
			 try {
				 fis = new FileInputStream(file);
				 ExcelImportResult<Dkjlptbnblxt> importResult = ExcelImportUtil.importExcelVerify(file, Dkjlptbnblxt.class,DkjlptbnblxtImport.class, params);
				 List<Dkjlptbnblxt> list = importResult.getList();
				 List<Dkjlptbnblxt> dkjlptgzlxts = new ArrayList<>();
				 for (Dkjlptbnblxt zzsfpxx : list) {
				 	if (zzsfpxx.getKhdkzye()==null){
				 		zzsfpxx.setKhdkzye(new BigDecimal(0));
					}
					 zzsfpxx.setLrr(sysUser.getUsername());
					 zzsfpxx.setLrsj(new Date());
					 dkjlptgzlxts.add(zzsfpxx);
				 }
				 dkjlptbnblxtService.saveBatch(dkjlptgzlxts);
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


	 /**
	  * 通过excel导入数据诉讼资产
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/importExcelSszc", method = RequestMethod.POST)
	 public Result<?> importExcelSszc(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
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
			 if (sszcImportVerify != null) {
				 params.setVerifyHanlder(sszcImportVerify);
			 }
			 FileInputStream fis = null;
			 FileOutputStream fos = null;
			 try {
				 fis = new FileInputStream(file);

				 ExcelImportResult<Dkjkptsszcxt> importResult = ExcelImportUtil.importExcelVerify(file, Dkjkptsszcxt.class,DkjkptsszcxtImport.class, params);
				 List<Dkjkptsszcxt> list = importResult.getList();
				 List<Dkjkptsszcxt> dkjlptgzlxts = new ArrayList<>();
				 for (Dkjkptsszcxt zzsfpxx : list) {
					 zzsfpxx.setLrr(sysUser.getUsername());
					 zzsfpxx.setLrsj(new Date());
					 dkjlptgzlxts.add(zzsfpxx);
				 }
				 dkjkptsszcxtService.saveBatch(dkjlptgzlxts);
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




	 /**
	  * 提取花名册客户信息
	  * @return
	  */
	 @AutoLog(value = "提取")
	 @ApiOperation(value="提取", notes="提取")
	 @GetMapping(value = "/init")
	 public Result<DkjkptBndksjjktz> init(HttpServletRequest request, HttpServletResponse response) {
		 Result<DkjkptBndksjjktz> result = new Result<DkjkptBndksjjktz>();
		 SysDic sysDic = sysDicService.queryByCode("101001");
		 String qybm = sysDic.getValue();
		 try {
			 System.out.println(qybm);
				dkjkptBndksjjktzService.init();
			 result.setSuccess(true);
			 result.setMessage("提取成功");
			 return result;
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 result.setSuccess(false);
			 result.setMessage("提取失败");
			 return result;
		 }

	 }

	 @Override
	 public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		 SysDic sysDic = sysDicService.queryByCode("101001");
		 String qybm = sysDic.getValue();
		 if (qybm.equals("110")){
			 try {
				 dkjkptBndksjjktzService.init();
				 log.info("=====执行了贷款监控平台定时任务=====");
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
			 }
		 }


	 }



	 /**
	  * 不良留痕
	  * @param dkzh
	  * @return
	  */
	 @RequestMapping(value = "/queryBllh", method = RequestMethod.GET)
	 public Result<?> queryBllh(@RequestParam(name="dkzh",required=true)String dkzh) {
		 Date ckrkrq = org.cmms.common.util.DateUtil.string2Date(sysLogService.cksjrkrq(),"yyyyMMdd");
		 Date dkrkrq = org.cmms.common.util.DateUtil.string2Date(sysLogService.dksjrkrq(),"yyyyMMdd");
		 Date rkrq = (ckrkrq.before(dkrkrq)?ckrkrq:dkrkrq);
		 log.info("最大入库日期："+DateUtil.parseCST(rkrq.toString()));
		 DkjkptBndksjjktz dkrqq = dkjkptBndksjjktzService.queryDkrqq(dkzh);
		 log.info(DateUtil.parseCST(dkrqq.getJkrq().toString())+"==第一次不良留痕借款日期");
		 log.info(dkrqq.getDkxt()+"==第一次不良留痕贷款形态");
		 ArrayList<DkjkptBllh> list = new ArrayList<>();
		 long timeInMillis1 = dkrqq.getJkrq().getTime(); // 获取第一个时间的毫秒数
		 long timeInMillis2 = rkrq.getTime(); // 获取第二个时间的毫秒数
		 long diffInMillis = timeInMillis2 - timeInMillis1; // 计算两个时间的毫秒数差值
		 long diffInDays = diffInMillis / (1000 * 60 * 60 * 24); // 将毫秒数差值转换为天数差值
		 String dkxt=dkrqq.getDkxt();
		 Date rqq=dkrqq.getJkrq();
		 for (int i = 0; i <= diffInDays; i++) {
		 	//循环天表
			 Date jkrq=DateUtil.offset(dkrqq.getJkrq(), DateField.DAY_OF_MONTH, i);
			 String yyMMdd = jkrq.toString().replaceAll("-", "").substring(2, 8);
			 System.out.println(yyMMdd);
			 String zdTable = "zdcbsbormbase" + yyMMdd;
			 String	 dkxt2 = dkjkptBndksjjktzService.queryDkxt(zdTable, dkzh);
			 if (!dkxt.equals(dkxt2)){
				 DkjkptBllh dkjkptBllh = new DkjkptBllh();
				 dkjkptBllh.setDkrqq(rqq);
				 dkjkptBllh.setDkrqz(DateUtil.offset(jkrq, DateField.DAY_OF_MONTH, 0));
				 dkjkptBllh.setDkxt(dkxt);
				 rqq=DateUtil.offset(jkrq, DateField.DAY_OF_MONTH, 0);
				 dkxt=dkxt2;
				 list.add(dkjkptBllh);
			 }
		 }
		 return Result.ok(list);
	 }
 }

