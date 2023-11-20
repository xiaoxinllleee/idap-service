package org.cmms.modules.ywgl.cdkfx.wdcdktj.controller;

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
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.ywgl.cdkfx.util.mapper.CallToolMapper;
import org.cmms.modules.ywgl.cdkfx.util.mapper.HrbasStaffToolMapper;
import org.cmms.modules.ywgl.cdkfx.util.service.HrbasStaffToolService;
import org.cmms.modules.ywgl.cdkfx.wdcdktj.entity.ErpYljcWdcdktj;
import org.cmms.modules.ywgl.cdkfx.wdcdktj.service.IErpYljcWdcdktjService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
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
 * @Description: 网点存贷款统计
 * @Author: jeecg-boot
 * @Date:   2021-06-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="网点存贷款统计")
@RestController
@RequestMapping("/wdcdktj/erpYljcWdcdktj")
public class ErpYljcWdcdktjController extends JeecgController<ErpYljcWdcdktj, IErpYljcWdcdktjService> {
	 @Autowired
	 private IErpYljcWdcdktjService erpYljcWdcdktjService;
	 @Autowired
	 private RedisUtil redisUtil;
	 @Value("${com.etl.sfdsjpt}")
	 private String sfdsjpt;
	@Autowired
	private HrbasStaffToolService hrbasStaffToolService;
	/**
	 * 分页列表查询
	 *
	 * @param erpYljcWdcdktj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "网点存贷款统计-分页列表查询")
	@ApiOperation(value="网点存贷款统计-分页列表查询", notes="网点存贷款统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ErpYljcWdcdktj erpYljcWdcdktj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ErpYljcWdcdktj> queryWrapper = QueryGenerator.initQueryWrapper(erpYljcWdcdktj, req.getParameterMap());
		IPage pageList = PageUtil.toPage(IErpYljcWdcdktjService.class,erpYljcWdcdktjService, pageNo, pageSize, queryWrapper, "jgdm");
		return Result.ok(pageList);
	}

	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/count")
	 public Result<?> count(@RequestParam(name = "jgdm", required = false) String jgdm,
							@RequestParam(name = "tjyf", required = false) String tjyf) {
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
		 //提取要素校验
		 if (-1 == DateUtil.getCurrentTS().compareTo(DateUtil.parseDateFormat(tjyf, "yyyy-MM-dd"))) {
			 return Result.error("选择月份必须小于当前系统月份！");
		 }
		 Result result = new Result<>();
		 tjyf = tjyf.replaceAll("-", "");
		 if ("true".equalsIgnoreCase(sfdsjpt)) {
			 HashMap<String, String> param = new HashMap<>();
			 param.put("i_tjyf", tjyf);
			 param.put("i_lrczy", loginUser.getUsername() == null ? "system" : loginUser.getUsername());
			 param.put("i_ckyeze", ","+erpYljcWdcdktjService.querySubjectNo("100001002")+",");
			 param.put("i_dkyeze", ","+erpYljcWdcdktjService.querySubjectNo("100001003")+",");
			 param.put("i_qydm", qydm);
			 param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_bankdeposit_loanstat");
			 // count_cdkfx_bankdeposit_loanstat
			 boolean flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
			 result.setSuccess(flag);
		 } else {
			 try {
				 erpYljcWdcdktjService.pWdcdktj(jgdm, tjyf, loginUser.getRealname());
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
	  * Hive建表脚本 Oracle To Hive
	  */
//	 @RequestMapping(value = "/dsjScript")
//	 public Result<?> dsjScript() {
//		 String[] tableNames = {
//				 "erp_bas_dkyeb_yhx",
//		 };
//		 for (int i = 0; i < tableNames.length; i++) {
//			 tableNames[i] = tableNames[i].toUpperCase();
//		 }
//		 for (int i = 0; i < tableNames.length; i++) {
//			 String tableComments = hrbasStaffToolService.getTableComments(tableNames[i]);
//			 System.out.println(tableComments);
//			 List<TableComments> list = hrbasStaffToolService.getTableAll(tableNames[i]);
//			 Collections.reverse(list);
//			 System.out.println(list.get(0).getTableName().toLowerCase());
//			 for (TableComments table : list) {
//				 if (table.getDataType().equals("VARCHAR2")) table.setDataType("NVARCHAR");
//				 if (table.getDataType().equals("NUMBER")) table.setDataType("NUMERIC");
//				 if (table.getComments() == null) table.setComments("");
//				 String s = "";
//				 if (table.getDataType().equals("DATE")) {
//					 s += "Column('" + table.getColumnName() + "'," + table.getDataType() + ",doc='" + table.getComments() + "'),";
//				 } else if (table.getDataScale() != null && table.getDataScale() == "0") {
//					 s += "Column('" + table.getColumnName() + "'," + table.getDataType() + "(" + table.getDataPrecision() + ","
//							 + table.getDataScale() + "),doc='" + table.getComments() + "'),";
//				 } else if (table.getDataPrecision() != null) {
//					 s += "Column('" + table.getColumnName() + "'," + table.getDataType() + "(" + table.getDataPrecision() + "),doc='" + table.getComments() + "'),";
//				 } else {
//					 s += "Column('" + table.getColumnName() + "'," + table.getDataType() + "(" + table.getDataLength() + "),doc='" + table.getComments() + "'),";
//				 }
//				 System.out.println(s);
//			 }
//			 System.out.println("下一个表" + (i + 1));
//		 }
//		 return Result.ok();
//	 }

	/**
	 * 添加
	 *
	 * @param erpYljcWdcdktj
	 * @return
	 */
	@AutoLog(value = "网点存贷款统计-添加")
	@ApiOperation(value="网点存贷款统计-添加", notes="网点存贷款统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ErpYljcWdcdktj erpYljcWdcdktj) {
		erpYljcWdcdktjService.save(erpYljcWdcdktj);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param erpYljcWdcdktj
	 * @return
	 */
	@AutoLog(value = "网点存贷款统计-编辑")
	@ApiOperation(value="网点存贷款统计-编辑", notes="网点存贷款统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ErpYljcWdcdktj erpYljcWdcdktj) {
		erpYljcWdcdktjService.updateById(erpYljcWdcdktj);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "网点存贷款统计-通过id删除")
	@ApiOperation(value="网点存贷款统计-通过id删除", notes="网点存贷款统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		erpYljcWdcdktjService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "网点存贷款统计-批量删除")
	@ApiOperation(value="网点存贷款统计-批量删除", notes="网点存贷款统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.erpYljcWdcdktjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "网点存贷款统计-通过id查询")
	@ApiOperation(value="网点存贷款统计-通过id查询", notes="网点存贷款统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ErpYljcWdcdktj erpYljcWdcdktj = erpYljcWdcdktjService.getById(id);
		return Result.ok(erpYljcWdcdktj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param erpYljcWdcdktj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ErpYljcWdcdktj erpYljcWdcdktj) {
      return super.exportXls(request, erpYljcWdcdktj, ErpYljcWdcdktj.class, "网点存贷款统计");
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
      return super.importExcel(request, response, ErpYljcWdcdktj.class);
  }

}
