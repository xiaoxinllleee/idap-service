package org.cmms.modules.ywgl.cdkfx.khjldkkhckhbl.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
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
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.system.service.ISysLogService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.ywgl.cdkfx.khjldkkhckhbl.entity.ModCdkfxKhjldkkhckhbl;
import org.cmms.modules.ywgl.cdkfx.khjldkkhckhbl.service.IModCdkfxKhjldkkhckhblService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ywgl.cdkfx.util.mapper.CallToolMapper;
import org.cmms.modules.ywgl.cdkfx.util.mapper.HrbasStaffToolMapper;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 客户经理贷款客户存款回报率
 * @Author: jeecg-boot
 * @Date:   2021-06-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户经理贷款客户存款回报率")
@RestController
@RequestMapping("/khjldkkhckhbl/modCdkfxKhjldkkhckhbl")
public class ModCdkfxKhjldkkhckhblController extends JeecgController<ModCdkfxKhjldkkhckhbl, IModCdkfxKhjldkkhckhblService> {
	@Autowired
	private IModCdkfxKhjldkkhckhblService modCdkfxKhjldkkhckhblService;
	 @Value("${com.etl.sfdsjpt}")
	 private String sfdsjpt;
	 @Autowired
	 private ISysLogService sysLogService;
	/**
	 * 分页列表查询
	 *
	 * @param modCdkfxKhjldkkhckhbl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户经理贷款客户存款回报率-分页列表查询")
	@ApiOperation(value="客户经理贷款客户存款回报率-分页列表查询", notes="客户经理贷款客户存款回报率-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ModCdkfxKhjldkkhckhbl modCdkfxKhjldkkhckhbl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ModCdkfxKhjldkkhckhbl> queryWrapper = QueryGenerator.initQueryWrapper(modCdkfxKhjldkkhckhbl, req.getParameterMap());
		IPage pageList = PageUtil.toPage(IModCdkfxKhjldkkhckhblService.class,modCdkfxKhjldkkhckhblService, pageNo, pageSize, queryWrapper, "jgdm","khjl","tjyf");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param modCdkfxKhjldkkhckhbl
	 * @return
	 */
	@AutoLog(value = "客户经理贷款客户存款回报率-添加")
	@ApiOperation(value="客户经理贷款客户存款回报率-添加", notes="客户经理贷款客户存款回报率-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ModCdkfxKhjldkkhckhbl modCdkfxKhjldkkhckhbl) {
		modCdkfxKhjldkkhckhblService.save(modCdkfxKhjldkkhckhbl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param modCdkfxKhjldkkhckhbl
	 * @return
	 */
	@AutoLog(value = "客户经理贷款客户存款回报率-编辑")
	@ApiOperation(value="客户经理贷款客户存款回报率-编辑", notes="客户经理贷款客户存款回报率-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ModCdkfxKhjldkkhckhbl modCdkfxKhjldkkhckhbl) {
		modCdkfxKhjldkkhckhblService.updateById(modCdkfxKhjldkkhckhbl);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理贷款客户存款回报率-通过id删除")
	@ApiOperation(value="客户经理贷款客户存款回报率-通过id删除", notes="客户经理贷款客户存款回报率-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		modCdkfxKhjldkkhckhblService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户经理贷款客户存款回报率-批量删除")
	@ApiOperation(value="客户经理贷款客户存款回报率-批量删除", notes="客户经理贷款客户存款回报率-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.modCdkfxKhjldkkhckhblService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理贷款客户存款回报率-通过id查询")
	@ApiOperation(value="客户经理贷款客户存款回报率-通过id查询", notes="客户经理贷款客户存款回报率-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ModCdkfxKhjldkkhckhbl modCdkfxKhjldkkhckhbl = modCdkfxKhjldkkhckhblService.getById(id);
		return Result.ok(modCdkfxKhjldkkhckhbl);
	}

  /**
   * 导出excel明细
   *
   * @param request
   * @param modCdkfxKhjldkkhckhbl
   */
  @RequestMapping(value = "/exp")
  public ModelAndView exportXlsExp(HttpServletRequest request, ModCdkfxKhjldkkhckhbl modCdkfxKhjldkkhckhbl) {
    return super.exportXls(request, modCdkfxKhjldkkhckhbl, ModCdkfxKhjldkkhckhbl.class, "客户经理贷款客户存款回报率");
  }
	 /**
	  * 提取
	  */
	 @RequestMapping(value="/count")
	 public Result<?> count(String tjyf){
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 tjyf = tjyf.replaceAll("-", "");
		 Result result = new Result<>();
		 boolean flag = true;
		 String i_tjyf = tjyf.replaceAll("-", "");
		 String i_lrczy = loginUser.getRealname();
		 Date dksjrq = DateUtil.string2Date(sysLogService.dksjrqBig(),"yyyyMMdd");
		 String tjyf_zxrq = org.cmms.modules.util.DateUtil.getSjQmrq(i_tjyf, dksjrq, "yyyy-MM-dd");
		 HashMap<String, String> param = new HashMap<>();
		 if ("true".equalsIgnoreCase(sfdsjpt)) {
			 //存储调度-1：count_cdkfx_p_n_temp_dkyeb
			 if (flag) {
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
				 //param.put("i_tjyf", tjyf_zxrq);
				 param.put("i_tjyf",i_tjyf);
				 param.put("lv_khzr_tablename", lv_khzr_tablename);
				 param.put("i_ycrq", i_tjyf);
				 param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_p_n_temp_dkyeb");
				 // count_cdkfx_p_n_temp_dkyeb
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
				 modCdkfxKhjldkkhckhblService.pDkfxDkkhckhbl(tjyf);
				 result.setSuccess(true);
				 return result;
			 } catch (Exception e) {
				 System.out.println(e);
				 log.error("提取失败", e.getMessage());
				 result.setSuccess(false);
			 }
		 }
		 return result;
	 }
	 /**
	  * 导出excel
	  *
	  * @param request
	  * @param modCdkfxKhjldkkhckhbl
	  */
	 @RequestMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, ModCdkfxKhjldkkhckhbl modCdkfxKhjldkkhckhbl,String ygxm) {
		 // Step.1 组装查询条件
		 QueryWrapper<ModCdkfxKhjldkkhckhbl> queryWrapper = QueryGenerator.initQueryWrapper(modCdkfxKhjldkkhckhbl, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 if (ygxm!=null){
			 String yggh = modCdkfxKhjldkkhckhblService.getYgghByName(ygxm);
			 if (yggh==null){
				 queryWrapper.eq("yggh",yggh+"null");
			 }else {
				 queryWrapper.eq("yggh",yggh);
			 }
		 }
		 // Step.2 获取导出数据
		 List<ModCdkfxKhjldkkhckhbl> pageList = service.list(queryWrapper);
		 // 过滤选中数据
		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, "客户经理贷款客户存款回报率"); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, ModCdkfxKhjldkkhckhbl.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户经理贷款客户存款回报率" + "报表", "导出人:" + sysUser.getRealname(), "客户经理贷款客户存款回报率"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		 return mv;
		 //return super.exportXls(request, modCdkfxKhjldkkhckhbl, ModCdkfxKhjldkkhckhbl.class, "客户经理贷款客户存款回报率");
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
      return super.importExcel(request, response, ModCdkfxKhjldkkhckhbl.class);
  }

}
