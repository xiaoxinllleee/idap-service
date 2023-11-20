package org.cmms.modules.ywgl.cdkfx.khjlcdktjzh.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.system.service.ISysLogService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.ywgl.cdkfx.dkdyrp.entity.ErpBasWyxcssz;
import org.cmms.modules.ywgl.cdkfx.dkdyrp.service.IErpBasWyxcsszService;
import org.cmms.modules.ywgl.cdkfx.khjlcdktj.service.IErpYljcKhjldkkhzbtjService;
import org.cmms.modules.ywgl.cdkfx.khjlcdktjjg.entity.ErpYljcJgkhjldkkhzbtj;
import org.cmms.modules.ywgl.cdkfx.khjlcdktjzh.entity.ErpYljcZhkhjldkkhzbtj;
import org.cmms.modules.ywgl.cdkfx.khjlcdktjzh.service.IErpYljcZhkhjldkkhzbtjService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ywgl.cdkfx.sysbascfg.service.ISysBasCfgService;
import org.cmms.modules.ywgl.cdkfx.util.mapper.CallToolMapper;
import org.cmms.modules.ywgl.cdkfx.wdcdktj.service.IErpYljcWdcdktjService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 客户经理存贷款分析（支行）
 * @Author: jeecg-boot
 * @Date:   2021-06-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户经理存贷款分析（支行）")
@RestController
@RequestMapping("/khjlcdktjzh/erpYljcZhkhjldkkhzbtj")
public class ErpYljcZhkhjldkkhzbtjController extends JeecgController<ErpYljcZhkhjldkkhzbtj, IErpYljcZhkhjldkkhzbtjService> {
	 @Autowired
	 private IErpYljcZhkhjldkkhzbtjService erpYljcZhkhjldkkhzbtjService;
	 @Autowired
	 private IErpYljcKhjldkkhzbtjService iErpYljcKhjldkkhzbtjService;
	 @Autowired
	 private IErpBasWyxcsszService iErpBasWyxcsszService;
	 @Autowired
	 private IErpYljcWdcdktjService iErpYljcWdcdktjService;
	 @Autowired
	 private RedisUtil redisUtil;
	 @Value("${com.etl.sfdsjpt}")
	 private String sfdsjpt;
	 @Autowired
	 private ISysLogService sysLogService;
	 @Autowired
	 private ISysBasCfgService iSysBasCfgService;

	/**
	 * 分页列表查询
	 *
	 * @param erpYljcZhkhjldkkhzbtj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户经理存贷款分析（支行）-分页列表查询")
	@ApiOperation(value="客户经理存贷款分析（支行）-分页列表查询", notes="客户经理存贷款分析（支行）-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ErpYljcZhkhjldkkhzbtj erpYljcZhkhjldkkhzbtj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ErpYljcZhkhjldkkhzbtj> queryWrapper = QueryGenerator.initQueryWrapper(erpYljcZhkhjldkkhzbtj, req.getParameterMap());
		queryWrapper.and(i -> i.gt("ckye",0)
				.or().gt("dkye",0)
				.or().gt("cknrpye",0)
				.or().gt("dkrpye",0)
				.or().gt("byshlx",0));
		queryWrapper.isNotNull("CUSTID");
		IPage pageList = PageUtil.toPage(IErpYljcZhkhjldkkhzbtjService.class,erpYljcZhkhjldkkhzbtjService, pageNo, pageSize, queryWrapper, "YWJGDM","JGDM");
		return Result.ok(pageList);
	}

	 /**
	  * 提取
	  *
	  * @param jgdm
	  * @param tjyf
	  * @return
	  */
	 @AutoLog(value = "客户经理存贷款分析（支行）-提取")
	 @ApiOperation(value="客户经理存贷款分析（支行）-提取", notes="客户经理存贷款分析（支行）-提取")
	 @RequestMapping(value = "/count")
	 public Result<?> count(@RequestParam(name = "jgdm", required = false) String jgdm,
							@RequestParam(name = "tjyf", required = true) String tjyf) {
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
		 //提取要素校验
		 if (-1 == DateUtil.getCurrentTS().compareTo(DateUtil.parseDateFormat(tjyf, "yyyy-MM-dd"))) {
			 return Result.error("选择月份必须小于当前系统月份！");
		 }
		 Result result = new Result<>();
		 boolean flag = true;
		 String i_tjyf = tjyf.replaceAll("-", "");
		 String i_lrczy = loginUser.getUsername();
		 Date dksjrq = DateUtil.string2Date(sysLogService.dksjrqBig(),"yyyyMMdd");
		 String tjyf_zxrq = org.cmms.modules.util.DateUtil.getSjQmrq(i_tjyf, dksjrq, "yyyyMMdd");
		 HashMap<String, String> param = new HashMap<>();
		 if ("true".equalsIgnoreCase(sfdsjpt)) {
		 	 //客户经理存贷款分析（支行）
			 //存储调度-1：count_cdkfx_p_n_temp_dkyeb
			 if (flag) {
				 System.out.println("存储调度-11 ==== 结束调度 ==== 存储调度-12 ==== 开始调度 ====");
				 String lv_khzr_tablename = "";
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				 String format = sdf.format(new Date());
				 if (format.substring(0, 6).equals(i_tjyf.substring(0, 6))) {
					 lv_khzr_tablename = "ERP_BAS_DKZHKHZR WHERE 1=1";
				 }else{
					 lv_khzr_tablename = "ERP_BAS_DKZHKHZR_HIS WHERE fiscal_date = '"+i_tjyf+"'";
				 }
				 try {
					 lv_khzr_tablename = new String(lv_khzr_tablename.getBytes(), "UTF-8");
				 } catch (UnsupportedEncodingException e) {
					 e.printStackTrace();
				 }
				 param.put("i_tjyf", tjyf_zxrq);
				 param.put("lv_khzr_tablename", lv_khzr_tablename);
				 param.put("i_ycrq", i_tjyf);
				 param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_n_temp_dkyeb");
				 // count_cdkfx_p_n_temp_dkyeb
				 flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
				 if (!flag) {
					 return Result.error("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
				 }
			 }
			 //存储调度-2：count_cdkfx_p_n_dkyeb
			 if (flag) {
				 param = new HashMap<>();
				 param.put("i_tjyf", i_tjyf);
				 param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_n_dkyeb");
				 // count_cdkfx_p_n_dkyeb
				 flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
				 if (!flag) {
					 return Result.error("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
				 }
			 }
			 //存储调度-3：count_cdkfx_p_n_dkffmx
			 if (flag) {
				 param = new HashMap<>();
				 param.put("i_tjyf", i_tjyf);
				 param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_n_dkffmx");
				 // count_cdkfx_p_n_dkffmx
				 flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
				 if (!flag) {
					 return Result.error("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
				 }
			 }
			 //存储调度-4：count_cdkfx_p_n_dkshmx
			 if (flag) {
				 param = new HashMap<>();
				 param.put("i_tjyf", i_tjyf);
				 param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_n_dkshmx");
				 // count_cdkfx_p_n_dkshmx
				 flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
				 if (!flag) {
					 return Result.error("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
				 }
			 }
			 //存储调度-5：count_cdkfx_p_n_dkyeb_dqshl
			 if (flag) {
				 QueryWrapper queryWrapper=new QueryWrapper();
				 queryWrapper.eq("cfgcode", "200000002");
				 String startday = iSysBasCfgService.getOne(queryWrapper).getCfgvalue();
				 param = new HashMap<>();
				 param.put("i_tjyf", i_tjyf);
				 param.put("i_startday", startday == null ? "20100701" : startday);
				 param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_n_dkyeb_dqshl");
				 // count_cdkfx_p_n_dkyeb_dqshl
				 flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
				 if (!flag) {
					 return Result.error("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
				 }
			 }
			 //存储调度-6：count_cdkfx_p_dkfx_khjlkhsjtj
			 if (flag) {
                 QueryWrapper queryWrapper=new QueryWrapper();
                 queryWrapper.eq("cfgcode", "200000003");
                 String dkqxdb = iSysBasCfgService.getOne(queryWrapper).getCfgvalue();
                 queryWrapper=new QueryWrapper();
                 queryWrapper.eq("cfgcode", "200000004");
                 String dkqxdh = iSysBasCfgService.getOne(queryWrapper).getCfgvalue();
				 param = new HashMap<>();
				 param.put("i_tjyf", i_tjyf);
				 param.put("ln_default_dkqx_db", dkqxdb == null ? "300000" : dkqxdb);
				 param.put("ln_default_dkqx_dh", dkqxdh == null ? "300000" : dkqxdh);
				 param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_dkfx_khjlkhsjtj");
				 // count_cdkfx_p_dkfx_khjlkhsjtj
				 flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
				 if (!flag) {
					 return Result.error("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
				 }
			 }
			 //存储调度-7：count_cdkfx_p_dkdyrpjs
			 if (flag) {
				 String ld_beginDay = "";        //开始日期
				 String ld_endDay = "";        //结束日期
				 //每月开始日期，例如：1
				 String kssjd = "";
				 //每月结束日期，例如：31
				 String jssjd = "";
				 //是否跨月，例如：1 否 0 是
				 String sfky = "";
				 QueryWrapper<ErpBasWyxcssz> queryWrapper = new QueryWrapper<>();
				 queryWrapper.eq("qybz", 1);
				 ErpBasWyxcssz record = iErpBasWyxcsszService.getOne(queryWrapper);
				 if (record != null) {
					 kssjd = record.getKsrq();
					 jssjd = record.getJsrq();
					 sfky = record.getSfky().toString();
				 }
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				 try {
					 Date date = sdf.parse(i_tjyf);
					 if ("0".equals(sfky)) {
						 ld_beginDay = DateUtil.addDay(DateUtil.addMonth(date, -1), (Integer.valueOf(kssjd) - 1));
						 ld_endDay = DateUtil.addDay(date, (Integer.valueOf(jssjd) - 1));
					 } else {
						 ld_beginDay = DateUtil.addDay(date, (Integer.valueOf(kssjd) - 1));
						 ld_endDay = DateUtil.addDay(date, (Integer.valueOf(jssjd) - 1));
					 }
					 if (!ld_beginDay.substring(4, 6).equals(ld_endDay.substring(4, 6))) {
						 ld_endDay = DateUtil.getLastDayString(ld_endDay);
					 }
					 System.out.println(ld_endDay);
				 } catch (ParseException e) {
					 e.printStackTrace();
				 }
				 param = new HashMap<>();
				 param.put("ld_beginDay", ld_beginDay);
				 param.put("ld_endDay", ld_endDay);
				 param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_dkdyrpjs");
				 // count_cdkfx_p_dkdyrpjs
				 flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
				 if (!flag) {
					 return Result.error("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
				 }
			 }
			 //存储调度-8：count_cdkfx_p_dkfx_khjldktj
			 if (flag) {
				 System.out.println("存储调度-11 ==== 结束调度 ==== 存储调度-12 ==== 开始调度 ====");
				 String lv_tablename = "";
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				 String format = sdf.format(new Date());
				 if (format.substring(0, 6).equals(i_tjyf.substring(0, 6))) {
					 lv_tablename = "ERP_BAS_CKZHGLXX WHERE 1=1";

				 }else{
					 lv_tablename = "ERP_BAS_CKZHGLXX_HIS WHERE fiscal_date = '"+i_tjyf+"'";
				 }
				 try {
					 lv_tablename = new String(lv_tablename.getBytes(), "UTF-8");
				 } catch (UnsupportedEncodingException e) {
					 e.printStackTrace();
				 }
				 param = new HashMap<>();
				 param.put("i_tjyf", i_tjyf);
				 param.put("i_qmrq", tjyf_zxrq);
				 param.put("lv_tablename", lv_tablename);
				 param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_dkfx_khjldktj");
				 // count_cdkfx_p_dkfx_khjldktj
				 flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
				 if (!flag) {
					 return Result.error("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
				 }
			 }
			 //存储调度-9：count_ywgl_cdkfx_yljc_khjldkkhzbtj
			 if (flag) {
				 param = new HashMap<>();
				 param.put("i_qmrq", tjyf_zxrq);
				 param.put("i_tjyf", i_tjyf);
				 param.put("ls_lrczy", i_lrczy);
				 param.put("etl_task","kiss.domain.application.cdkyw.proc_ywgl_cdkfx_yljc_khjldkkhzbtj");
				 // count_ywgl_cdkfx_yljc_khjldkkhzbtj
				 flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
				 if (!flag) {
					 return Result.error("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
				 }
			 }
			 //存储调度-10：count_ywgl_cdkfx_custstar_dktj
			 if (flag) {
				 String csz = iErpYljcKhjldkkhzbtjService.getDailyLoanLimit("CS0001");
				 String csz0002 = iErpYljcKhjldkkhzbtjService.getDailyLoanLimit("CS0002");
				 QueryWrapper queryWrapper=new QueryWrapper();
				 queryWrapper.eq("cfgcode", "200000002");
				 String cfgvalue200000002 = iSysBasCfgService.getOne(queryWrapper).getCfgvalue();
				 queryWrapper=new QueryWrapper();
				 queryWrapper.eq("cfgcode", "200000004");
				 String cfgvalue200000004 = iSysBasCfgService.getOne(queryWrapper).getCfgvalue();
				 param = new HashMap<>();
				 param.put("cfgvalue200000002", cfgvalue200000002);
				 param.put("cfgvalue200000004", cfgvalue200000004);
				 param.put("csz",csz);
				 param.put("csz0002",csz0002);
				 param.put("i_qmrq", tjyf_zxrq);
				 param.put("i_tjyf", i_tjyf);
				 param.put("etl_task","kiss.domain.application.cdkyw.proc_ywgl_cdkfx_custstar_dktj");
				 // count_ywgl_cdkfx_custstar_dktj
				 flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
				 if (!flag) {
					 return Result.error("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
				 }
			 }
			 //存储调度-11：count_cdkfx_bankdeposit_loanstat
			 if (flag) {
				 QueryWrapper queryWrapper=new QueryWrapper();
				 queryWrapper.eq("cfgcode", "100001002");
				 String ckyeze=",";
				 ckyeze += iSysBasCfgService.getOne(queryWrapper).getCfgvalue()+",";
				 queryWrapper=new QueryWrapper();
				 queryWrapper.eq("cfgcode", "100001003");
				 String dkyeze=",";
				 dkyeze = iSysBasCfgService.getOne(queryWrapper).getCfgvalue()+",";
				 param = new HashMap<>();
				 param.put("i_qmrq", tjyf_zxrq);
				 param.put("i_lrczy", i_lrczy);
				 param.put("i_ckyeze", ckyeze);
				 param.put("i_dkyeze", dkyeze);
				 param.put("i_qydm", qydm);
				 param.put("i_tjyf", i_tjyf);
				 param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_bankdeposit_loanstat");
				 // count_cdkfx_bankdeposit_loanstat
				 flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
				 if (!flag) {
					 return Result.error("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
				 }
			 }
			 //存储调度-12：count_cdkfx_p_dkfx_dkkhckhbl
			 if (flag) {
				 param = new HashMap<>();
				 param.put("i_qmrq", tjyf_zxrq);
				 param.put("i_tjyf", i_tjyf);
				 param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_dkfx_dkkhckhbl");
				 // count_cdkfx_p_dkfx_dkkhckhbl
				 flag = EtlUtil.callEtl("cdkyw_common_init", param, 30);
				 if (!flag) {
					 return Result.error("存储调度`cdkyw_common_init`失败，请联系系统管理员查看！");
				 }
			 }
			 result.setSuccess(flag);
		 } else {
			 try {
				 erpYljcZhkhjldkkhzbtjService.pAutoExec(jgdm, i_tjyf, loginUser.getRealname());
				 result.setSuccess(true);
				 return result;
			 } catch (Throwable e) {
				 System.out.println(e);
				 log.error("提取失败", e.getMessage());
				 result.setSuccess(false);
			 }
		 }
		 return result;
	 }

	 /**
	 * 添加
	 *
	 * @param erpYljcZhkhjldkkhzbtj
	 * @return
	 */
	@AutoLog(value = "客户经理指标统计(支行)-添加")
	@ApiOperation(value="客户经理指标统计(支行)-添加", notes="客户经理指标统计(支行)-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ErpYljcZhkhjldkkhzbtj erpYljcZhkhjldkkhzbtj) {
		erpYljcZhkhjldkkhzbtjService.save(erpYljcZhkhjldkkhzbtj);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param erpYljcZhkhjldkkhzbtj
	 * @return
	 */
	@AutoLog(value = "客户经理指标统计(支行)-编辑")
	@ApiOperation(value="客户经理指标统计(支行)-编辑", notes="客户经理指标统计(支行)-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ErpYljcZhkhjldkkhzbtj erpYljcZhkhjldkkhzbtj) {
		erpYljcZhkhjldkkhzbtjService.updateById(erpYljcZhkhjldkkhzbtj);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理指标统计(支行)-通过id删除")
	@ApiOperation(value="客户经理指标统计(支行)-通过id删除", notes="客户经理指标统计(支行)-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		erpYljcZhkhjldkkhzbtjService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户经理指标统计(支行)-批量删除")
	@ApiOperation(value="客户经理指标统计(支行)-批量删除", notes="客户经理指标统计(支行)-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.erpYljcZhkhjldkkhzbtjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理指标统计(支行)-通过id查询")
	@ApiOperation(value="客户经理指标统计(支行)-通过id查询", notes="客户经理指标统计(支行)-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ErpYljcZhkhjldkkhzbtj erpYljcZhkhjldkkhzbtj = erpYljcZhkhjldkkhzbtjService.getById(id);
		return Result.ok(erpYljcZhkhjldkkhzbtj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param erpYljcJgkhjldkkhzbtj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ErpYljcZhkhjldkkhzbtj erpYljcJgkhjldkkhzbtj) {
	  // Step.1 组装查询条件
	  QueryWrapper<ErpYljcZhkhjldkkhzbtj> queryWrapper = QueryGenerator.initQueryWrapper(erpYljcJgkhjldkkhzbtj, request.getParameterMap());
	  queryWrapper.and(i -> i.gt("ckye",0)
			  .or().gt("dkye",0)
			  .or().gt("cknrpye",0)
			  .or().gt("dkrpye",0)
			  .or().gt("byshlx",0));
	  queryWrapper.isNotNull("CUSTID");
	  queryWrapper.orderByAsc("YWJGDM").orderByAsc("JGDM");
	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	  // Step.2 获取导出数据
	  List<ErpYljcZhkhjldkkhzbtj> pageList = service.list(queryWrapper);
	  // 过滤选中数据
	  // Step.3 AutoPoi 导出Excel
	  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
	  mv.addObject(NormalExcelConstants.FILE_NAME, "客户经理存贷款统计(支行)"); //此处设置的filename无效 ,前端会重更新设置一下
	  mv.addObject(NormalExcelConstants.CLASS, ErpYljcZhkhjldkkhzbtj.class);
	  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户经理存贷款统计(支行)" + "报表", "导出人:" + sysUser.getRealname(), "客户经理指标统计(支行)"));
	  mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
	  return mv;
      //return super.exportXls(request, erpYljcZhkhjldkkhzbtj, ErpYljcZhkhjldkkhzbtj.class, "客户经理指标统计(支行)");
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
      return super.importExcel(request, response, ErpYljcZhkhjldkkhzbtj.class);
  }

}
