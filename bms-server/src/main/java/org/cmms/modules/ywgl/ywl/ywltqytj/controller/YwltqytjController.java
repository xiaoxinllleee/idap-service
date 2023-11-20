package org.cmms.modules.ywgl.ywl.ywltqytj.controller;

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
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.ywgl.ywl.ywltqytj.entity.Ywltqytj;
import org.cmms.modules.ywgl.ywl.ywltqytj.mapper.YwltqytjMapper;
import org.cmms.modules.ywgl.ywl.ywltqytj.service.IYwltqytjService;
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
 * @Description: 业务量提取与统计
 * @Author: jeecg-boot
 * @Date:   2021-09-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="业务量提取与统计")
@RestController
@RequestMapping("/ywltqytj/ywltqytj")
public class YwltqytjController extends JeecgController<Ywltqytj, IYwltqytjService> {
	 @Autowired
	 private IYwltqytjService ywltqytjService;
	 @Autowired
	 private ISysDictService iSysDictService;
	 @Autowired
	 private RedisUtil redisUtil;
	 @Value("${com.etl.sfdsjpt}")
	 private String sfdsjpt;

	/**
	 * 分页列表查询
	 *
	 * @param ywltqytj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "业务量提取与统计-分页列表查询")
	@ApiOperation(value="业务量提取与统计-分页列表查询", notes="业务量提取与统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ywltqytj ywltqytj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Ywltqytj> queryWrapper = QueryGenerator.initQueryWrapper(ywltqytj, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IYwltqytjService.class,ywltqytjService,pageNo,pageSize,queryWrapper,"tjyf","zzbz","yggw","yggh");
		return Result.ok(pageList);
	}

	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/init")
	 public Result<?> init(@RequestParam(name = "tjyf", required = true) String tjyf,
						   @RequestParam(name = "zzbz", required = false) String zzbz) throws ParseException {
		 //统计日期
		 String tjyfparam = tjyf;
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
		 //if (tjyf == null || "".equalsIgnoreCase(tjyf)) {
		 //	 return Result.error("统计月份不能为空");
		 //}
		 if (-1 == DateUtil.getCurrentTS().compareTo(DateUtil.parseDateFormat(tjyf, "yyyy-MM-dd"))) {
			 return Result.error("选择月份必须小于当前系统月份！");
		 }
		 Result result = new Result<>();
		 //统计日期
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 Date statisticalDate = simpleDateFormat.parse(tjyf);
		 //统计日期月末日期
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(simpleDateFormat.parse(tjyf));
		 calendar.add(Calendar.MONTH, 1);
		 calendar.set(Calendar.DAY_OF_MONTH, 0);
		 Date endOfMonthsDate = simpleDateFormat.parse(simpleDateFormat.format(calendar.getTime()));

		 calendar = Calendar.getInstance();
		 calendar.add(Calendar.MONTH, 0);
		 calendar.set(Calendar.DAY_OF_MONTH, 1);
		 //当前系统日期
		 String nowDate = simpleDateFormat.format(calendar.getTime());

		 tjyf = tjyf.replaceAll("-", "");
		 if ("true".equalsIgnoreCase(sfdsjpt)) {
			 boolean flag = true;
			 HashMap<String, String> param = new HashMap<>();
			 // Step1. 页面调用员工原始业务量统计 每天统计 P_AUTO_YSYWL_TJ(I_DAY,I_ZZBZ) - P_YSYWL_TJ_DAY(I_DAY, I_ISRECONFIG`DEFAULT：1`, I_ZZBZ)
			 while (statisticalDate.compareTo(endOfMonthsDate) <= 0) {
				 tjyf = simpleDateFormat.format(statisticalDate).replace("-","");
				 param = new HashMap<>();
				 param.put("i_day", tjyf);
				 param.put("i_zzbz", zzbz);
				 //P90025-机构业务量分配方式(0 手工分配 1 自动平均分配)
				 String iAllocation = ywltqytjService.getAssessParamValue("P90025");
				 if ("1".equalsIgnoreCase(iAllocation)) {
					 //目前为自动分配模式，直接删除所有的分配明细
					 param.put("etl_task","kiss.domain.application.cdkyw.proc_busvolume_org_autoallocated");
					 // count_busvolume_org_autoallocated
					 flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
					 if (flag) {
						 param = new HashMap<>();
						 param.put("i_day", tjyf);
						 //如果已经开启自动分配，则自动完成原始业务量分配
						 param.put("etl_task","kiss.domain.application.cdkyw.proc_business_volume_autoallocation");
						 // count_business_volume_autoallocation
						 flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
					 }
				 } else {
				 	param.put("etl_task","kiss.domain.application.cdkyw.proc_busvolume_org_manualallocated");
				 	// count_busvolume_org_manualallocated
					 flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
				 }
				 if (flag) {
					 param = new HashMap<>();
					 param.put("i_day", tjyf);
					 param.put("i_zzbz", zzbz);
					 if (!StringUtils.isEmpty(zzbz)) {
						 String i_jgdm = iSysDictService.queryTableDictTextByKey("hr_bas_organization", "ywjgdm", "zzbz", zzbz);
						 param.put("i_jgdm", i_jgdm);
					 }
					 //P90050-业务量统计时是否统计ATM业务笔数(0 是 1 否)
					 String ATMYwbs = ywltqytjService.getAssessParamValue("P90050");
					 param.put("p_atm_ywbs", ATMYwbs);
					 String ltn1 = ywltqytjService.conversionConfigInfo(simpleDateFormat.parse(tjyfparam));
					 param.put("lt_n1", ltn1);
					 //若统计月份为当前月份且`i_isreconfig`为`1`，则重新配置当月的折算信息
					 if (simpleDateFormat.format(statisticalDate).equalsIgnoreCase(nowDate) && Integer.parseInt(ltn1) < 1) {
						 param.put("etl_task","kiss.domain.application.cdkyw.proc_business_volume_ysywl_reconfig");
					 	// count_business_volume_ysywl_reconfig
						 flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
					 } else {
						 param.put("etl_task","kiss.domain.application.cdkyw.proc_business_volume_ysywl");
					 	// count_business_volume_ysywl
						 flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
					 }
				 }
				 //增加一天
				 Calendar cal = Calendar.getInstance();
				 cal.setTime(statisticalDate);
				 cal.add(Calendar.DATE,1);
				 statisticalDate = simpleDateFormat.parse(simpleDateFormat.format(cal.getTime()));
			 }
			 // Step2. 员工业务量统计-取员工业务量明细，员工分配业务量明细，机构分配业务量明细 proc_ywltq_p_tj_ygywl
			 if (flag) {
				 param = new HashMap<>();
				 param.put("i_tjyf", tjyf);
				 param.put("i_zzbz", zzbz);
				 param.put("etl_task","kiss.domain.application.cdkyw.proc_ywltq_p_tj_ygywl");
				 // count_ywltq_p_tj_ygywl
				 flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
			 }
			 // Step3. 提取员工业务量明细-提取员工待分配记录-如果需要分配的记录已经被执行过分配，则不再重新提取 proc_business_volume_statistical
			 if (flag) {
				 param = new HashMap<>();
				 param.put("i_tjyf", tjyf);
				 param.put("i_zzbz", zzbz);
				 param.put("etl_task","kiss.domain.application.cdkyw.proc_business_volume_statistical");
				 // count_business_volume_statistical
				 flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
			 }
			 result.setSuccess(flag);
		 } else {
			 try {
				 if (zzbz == null) {
				 	zzbz = "";
				 }
				 ywltqytjService.pYwltqytj(tjyf, zzbz);
				 result.setMessage("提取成功！");
				 result.setSuccess(true);
			 } catch (Throwable e) {
				 e.printStackTrace();
				 log.error("提取失败，请联系系统管理员！", e.getMessage());
				 result.setSuccess(false);
			 }
		 }
		 return result;
	 }

	/**
	 * 添加
	 *
	 * @param ywltqytj
	 * @return
	 */
	@AutoLog(value = "业务量提取与统计-添加")
	@ApiOperation(value="业务量提取与统计-添加", notes="业务量提取与统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ywltqytj ywltqytj) {
		ywltqytjService.save(ywltqytj);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param ywltqytj
	 * @return
	 */
	@AutoLog(value = "业务量提取与统计-编辑")
	@ApiOperation(value="业务量提取与统计-编辑", notes="业务量提取与统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ywltqytj ywltqytj) {
		ywltqytjService.updateById(ywltqytj);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "业务量提取与统计-通过id删除")
	@ApiOperation(value="业务量提取与统计-通过id删除", notes="业务量提取与统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ywltqytjService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "业务量提取与统计-批量删除")
	@ApiOperation(value="业务量提取与统计-批量删除", notes="业务量提取与统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ywltqytjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "业务量提取与统计-通过id查询")
	@ApiOperation(value="业务量提取与统计-通过id查询", notes="业务量提取与统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ywltqytj ywltqytj = ywltqytjService.getById(id);
		return Result.ok(ywltqytj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ywltqytj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ywltqytj ywltqytj) {
      return super.exportXls(request, ywltqytj, Ywltqytj.class, "业务量提取与统计");
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
      return super.importExcel(request, response, Ywltqytj.class);
  }

}
