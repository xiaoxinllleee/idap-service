package org.cmms.modules.ywgl.cdkfx.zhcdktj.controller;

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
import org.cmms.modules.ywgl.cdkfx.wdcdktj.service.IErpYljcWdcdktjService;
import org.cmms.modules.ywgl.cdkfx.zhcdktj.entity.ErpYljcZhcdktj;
import org.cmms.modules.ywgl.cdkfx.zhcdktj.service.IErpYljcZhcdktjService;
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
 * @Description: 支行存贷款统计
 * @Author: jeecg-boot
 * @Date:   2021-06-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行存贷款统计")
@RestController
@RequestMapping("/zhcdktj/erpYljcZhcdktj")
public class ErpYljcZhcdktjController extends JeecgController<ErpYljcZhcdktj, IErpYljcZhcdktjService> {
	 @Autowired
	 private IErpYljcZhcdktjService erpYljcZhcdktjService;
	 @Autowired
	 private IErpYljcWdcdktjService erpYljcWdcdktjService;
	 @Autowired
	 private RedisUtil redisUtil;
	 @Value("${com.etl.sfdsjpt}")
	 private String sfdsjpt;

	/**
	 * 分页列表查询
	 *
	 * @param erpYljcZhcdktj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行存贷款统计-分页列表查询")
	@ApiOperation(value="支行存贷款统计-分页列表查询", notes="支行存贷款统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ErpYljcZhcdktj erpYljcZhcdktj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ErpYljcZhcdktj> queryWrapper = QueryGenerator.initQueryWrapper(erpYljcZhcdktj, req.getParameterMap());
		IPage pageList = PageUtil.toPage(IErpYljcZhcdktjService.class,erpYljcZhcdktjService, pageNo, pageSize, queryWrapper, "jgdm");
		return Result.ok(pageList);
	}

	 /**
	  * 提取
	  */
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
		 tjyf = tjyf.replaceAll("-", "");
		 if ("true".equalsIgnoreCase(sfdsjpt)) {
			 HashMap<String, String> param = new HashMap<>();
			 param.put("i_tjyf", tjyf);
			 param.put("i_jgdm", jgdm);
			 param.put("i_lrczy", loginUser.getUsername()== null ? "system" : loginUser.getUsername());
			 param.put("i_ckyeze", ","+erpYljcWdcdktjService.querySubjectNo("100001002")+",");
			 param.put("i_dkyeze", ","+erpYljcWdcdktjService.querySubjectNo("100001003")+",");
			 param.put("i_qydm", qydm);
			 param.put("etl_task","kiss.domain.application.cdkyw.proc_cdkfx_bankdeposit_loanstat");
			 // count_cdkfx_bankdeposit_loanstat
			 boolean flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
			 result.setSuccess(flag);
		 } else {
			 try {
				 erpYljcZhcdktjService.pWdcdktj(jgdm, tjyf, loginUser.getRealname());
				 result.setMessage("提取成功！");
				 result.setSuccess(true);
			 } catch (Throwable e) {
				 e.printStackTrace();
				 log.error("提取失败！"+e.getMessage());
				 result.setMessage("提取失败，请联系系统管理员！"+e.getMessage());
			 }
		 }
		 return result;
	 }
	/**
	 * 添加
	 *
	 * @param erpYljcZhcdktj
	 * @return
	 */
	@AutoLog(value = "支行存贷款统计-添加")
	@ApiOperation(value="支行存贷款统计-添加", notes="支行存贷款统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ErpYljcZhcdktj erpYljcZhcdktj) {
		erpYljcZhcdktjService.save(erpYljcZhcdktj);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param erpYljcZhcdktj
	 * @return
	 */
	@AutoLog(value = "支行存贷款统计-编辑")
	@ApiOperation(value="支行存贷款统计-编辑", notes="支行存贷款统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ErpYljcZhcdktj erpYljcZhcdktj) {
		erpYljcZhcdktjService.updateById(erpYljcZhcdktj);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行存贷款统计-通过id删除")
	@ApiOperation(value="支行存贷款统计-通过id删除", notes="支行存贷款统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		erpYljcZhcdktjService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "支行存贷款统计-批量删除")
	@ApiOperation(value="支行存贷款统计-批量删除", notes="支行存贷款统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.erpYljcZhcdktjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行存贷款统计-通过id查询")
	@ApiOperation(value="支行存贷款统计-通过id查询", notes="支行存贷款统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ErpYljcZhcdktj erpYljcZhcdktj = erpYljcZhcdktjService.getById(id);
		return Result.ok(erpYljcZhcdktj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param erpYljcZhcdktj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ErpYljcZhcdktj erpYljcZhcdktj) {
      return super.exportXls(request, erpYljcZhcdktj, ErpYljcZhcdktj.class, "支行存贷款统计");
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
      return super.importExcel(request, response, ErpYljcZhcdktj.class);
  }

}
