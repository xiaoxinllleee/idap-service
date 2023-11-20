package org.cmms.modules.ywgl.ywl.ywlhzcx.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
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
import org.cmms.modules.ywgl.ywl.ywlhzcx.entity.Ywlhzcx;
import org.cmms.modules.ywgl.ywl.ywlhzcx.service.IYwlhzcxService;
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
 * @Description: 业务量汇总查询
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="业务量汇总查询")
@RestController
@RequestMapping("/ywlhzcx/ywlhzcx")
public class YwlhzcxController extends JeecgController<Ywlhzcx, IYwlhzcxService> {
	 @Autowired
	 private IYwlhzcxService ywlhzcxService;
	 @Autowired
	 private RedisUtil redisUtil;
	 @Value("${com.etl.sfdsjpt}")
	 private String sfdsjpt;

	/**
	 * 分页列表查询
	 *
	 * @param ywlhzcx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "业务量汇总查询-分页列表查询")
	@ApiOperation(value="业务量汇总查询-分页列表查询", notes="业务量汇总查询-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ywlhzcx ywlhzcx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Ywlhzcx> queryWrapper = QueryGenerator.initQueryWrapper(ywlhzcx, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IYwlhzcxService.class,ywlhzcxService,pageNo,pageSize,queryWrapper,"tjyf","zzbz","yggh");
		return Result.ok(pageList);
	}

	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/init")
	 public Result<?> init(@RequestBody JSONObject jsonObject){
		 String tjyf = jsonObject.getString("tjyf");
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
			 param.put("etl_task","kiss.domain.application.cdkyw.proc_business_volume_summary_extraction");
			 // count_business_volume_summary_extraction
			 boolean flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
			 result.setSuccess(flag);
		 } else {
			 try {
				 ywlhzcxService.pYwlhzcx(tjyf);
				 result.setSuccess(true);
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
	 * @param ywlhzcx
	 * @return
	 */
	@AutoLog(value = "业务量汇总查询-添加")
	@ApiOperation(value="业务量汇总查询-添加", notes="业务量汇总查询-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ywlhzcx ywlhzcx) {
		ywlhzcxService.save(ywlhzcx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param ywlhzcx
	 * @return
	 */
	@AutoLog(value = "业务量汇总查询-编辑")
	@ApiOperation(value="业务量汇总查询-编辑", notes="业务量汇总查询-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ywlhzcx ywlhzcx) {
		ywlhzcxService.updateById(ywlhzcx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "业务量汇总查询-通过id删除")
	@ApiOperation(value="业务量汇总查询-通过id删除", notes="业务量汇总查询-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ywlhzcxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "业务量汇总查询-批量删除")
	@ApiOperation(value="业务量汇总查询-批量删除", notes="业务量汇总查询-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ywlhzcxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "业务量汇总查询-通过id查询")
	@ApiOperation(value="业务量汇总查询-通过id查询", notes="业务量汇总查询-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ywlhzcx ywlhzcx = ywlhzcxService.getById(id);
		return Result.ok(ywlhzcx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ywlhzcx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ywlhzcx ywlhzcx) {
      return super.exportXls(request, ywlhzcx, Ywlhzcx.class, "业务量汇总查询");
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
      return super.importExcel(request, response, Ywlhzcx.class);
  }

}
