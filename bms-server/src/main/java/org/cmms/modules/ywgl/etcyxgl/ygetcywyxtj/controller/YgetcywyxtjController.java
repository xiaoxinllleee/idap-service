package org.cmms.modules.ywgl.etcyxgl.ygetcywyxtj.controller;

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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.ywgl.etcyxgl.ygetcywyxtj.entity.Ygetcywyxtj;
import org.cmms.modules.ywgl.etcyxgl.ygetcywyxtj.mapper.YgetcywyxtjMapper;
import org.cmms.modules.ywgl.etcyxgl.ygetcywyxtj.service.IYgetcywyxtjService;
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
 * @Description: 员工etc业务营销统计
 * @Author: jeecg-boot
 * @Date:   2021-09-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="员工etc业务营销统计")
@RestController
@RequestMapping("/ygetcywyxtj/ygetcywyxtj")
public class YgetcywyxtjController extends JeecgController<Ygetcywyxtj, IYgetcywyxtjService> {
	 @Autowired
	 private IYgetcywyxtjService ygetcywyxtjService;
	 @Autowired
	 private RedisUtil redisUtil;
	 @Value("${com.etl.sfdsjpt}")
	 private String sfdsjpt;

	/**
	 * 分页列表查询
	 *
	 * @param ygetcywyxtj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "员工etc业务营销统计-分页列表查询")
	@ApiOperation(value="员工etc业务营销统计-分页列表查询", notes="员工etc业务营销统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ygetcywyxtj ygetcywyxtj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Ygetcywyxtj> queryWrapper = QueryGenerator.initQueryWrapper(ygetcywyxtj, req.getParameterMap());

		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IYgetcywyxtjService.class,ygetcywyxtjService,pageNo,pageSize,queryWrapper,"tjyf","jgdm","yggh");
		return Result.ok(pageList);
	}

	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/init")
	 public Result<?> init(String tjyf){
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
		 tjyf = tjyf.replaceAll("-","");
		 Result result = new Result<>();
		 if ("true".equalsIgnoreCase(sfdsjpt)) {
			 HashMap<String, String> parm = new HashMap<>();
			 parm.put("i_day",tjyf);
			 parm.put("etl_task","kiss.domain.application.cdkyw.proc_ywgl_etcyxgl_etcywyxtj");
			 // count_ywgl_etcyxgl_etcywyxtj
			 boolean falg = EtlUtil.callEtl("cdkyw_common_init", parm, 10);
			 result.setSuccess(falg);
		 } else {
			 try {
				 ygetcywyxtjService.pYgetcywyxtj(tjyf);
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
	 * 添加
	 *
	 * @param ygetcywyxtj
	 * @return
	 */
	@AutoLog(value = "员工etc业务营销统计-添加")
	@ApiOperation(value="员工etc业务营销统计-添加", notes="员工etc业务营销统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ygetcywyxtj ygetcywyxtj) {
		ygetcywyxtjService.save(ygetcywyxtj);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param ygetcywyxtj
	 * @return
	 */
	@AutoLog(value = "员工etc业务营销统计-编辑")
	@ApiOperation(value="员工etc业务营销统计-编辑", notes="员工etc业务营销统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ygetcywyxtj ygetcywyxtj) {
		ygetcywyxtjService.updateById(ygetcywyxtj);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工etc业务营销统计-通过id删除")
	@ApiOperation(value="员工etc业务营销统计-通过id删除", notes="员工etc业务营销统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ygetcywyxtjService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "员工etc业务营销统计-批量删除")
	@ApiOperation(value="员工etc业务营销统计-批量删除", notes="员工etc业务营销统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ygetcywyxtjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工etc业务营销统计-通过id查询")
	@ApiOperation(value="员工etc业务营销统计-通过id查询", notes="员工etc业务营销统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ygetcywyxtj ygetcywyxtj = ygetcywyxtjService.getById(id);
		return Result.ok(ygetcywyxtj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ygetcywyxtj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ygetcywyxtj ygetcywyxtj) {
      return super.exportXls(request, ygetcywyxtj, Ygetcywyxtj.class, "员工etc业务营销统计");
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
      return super.importExcel(request, response, Ygetcywyxtj.class);
  }

}
