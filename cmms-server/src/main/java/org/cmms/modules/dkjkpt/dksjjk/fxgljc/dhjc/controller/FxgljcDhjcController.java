package org.cmms.modules.dkjkpt.dksjjk.fxgljc.dhjc.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.entity.DkjkptBndksjjktz;
import org.cmms.modules.dkjkpt.dksjjk.fxgljc.dhjc.entity.FxgljcDhjc;
import org.cmms.modules.dkjkpt.dksjjk.fxgljc.dhjc.service.IFxgljcDhjcService;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;
import org.cmms.modules.hr.yggl.ygxxgl.service.IVhrbasstaffpostService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysDic;
import org.cmms.modules.system.service.IHrBasOrganizationService;
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
 * @Description: 贷后检查
 * @Author: jeecg-boot
 * @Date:   2023-08-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷后检查")
@RestController
@RequestMapping("/dhjc/fxgljcDhjc")
public class FxgljcDhjcController extends JeecgController<FxgljcDhjc, IFxgljcDhjcService> {
	@Autowired
	private IFxgljcDhjcService fxgljcDhjcService;
	 @Autowired
	 IVhrbasstaffpostService vhrbasstaffpostService;
	 @Autowired
	 private IHrBasOrganizationService hrBasOrganizationService;
	/**
	 * 分页列表查询
	 *
	 * @param fxgljcDhjc
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷后检查-分页列表查询")
	@ApiOperation(value="贷后检查-分页列表查询", notes="贷后检查-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(FxgljcDhjc fxgljcDhjc,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		//获取组织标识
	/*	HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByZzbz(loginUser.getOrgCode());
		//获取岗位标识
		Vhrbasstaffpost vhrbasstaffpost = vhrbasstaffpostService.selectByYggh(loginUser.getWorkNo());
		if (vhrbasstaffpost != null) {
			Integer gwbz = vhrbasstaffpost.getGwbz();
			String zzbz = vhrbasstaffpost.getZzbz();
		}*/
		QueryWrapper<FxgljcDhjc> queryWrapper = QueryGenerator.initQueryWrapper(fxgljcDhjc, req.getParameterMap());
		queryWrapper.ge("jkrq", DateUtil.parse("2023-07-01"));
		queryWrapper.eq("bcnjsfywc","2");
		Page<FxgljcDhjc> page = new Page<FxgljcDhjc>(pageNo, pageSize);
		IPage<FxgljcDhjc> pageList = fxgljcDhjcService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param fxgljcDhjc
	 * @return
	 */
	@AutoLog(value = "贷后检查-添加")
	@ApiOperation(value="贷后检查-添加", notes="贷后检查-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody FxgljcDhjc fxgljcDhjc) {
		fxgljcDhjcService.save(fxgljcDhjc);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param fxgljcDhjc
	 * @return
	 */
	@AutoLog(value = "贷后检查-编辑")
	@ApiOperation(value="贷后检查-编辑", notes="贷后检查-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody FxgljcDhjc fxgljcDhjc) {
		fxgljcDhjcService.updateById(fxgljcDhjc);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷后检查-通过id删除")
	@ApiOperation(value="贷后检查-通过id删除", notes="贷后检查-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		fxgljcDhjcService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷后检查-批量删除")
	@ApiOperation(value="贷后检查-批量删除", notes="贷后检查-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.fxgljcDhjcService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷后检查-通过id查询")
	@ApiOperation(value="贷后检查-通过id查询", notes="贷后检查-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		FxgljcDhjc fxgljcDhjc = fxgljcDhjcService.getById(id);
		return Result.ok(fxgljcDhjc);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param fxgljcDhjc
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, FxgljcDhjc fxgljcDhjc) {
      return super.exportXls(request, fxgljcDhjc, FxgljcDhjc.class, "贷后检查");
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
      return super.importExcel(request, response, FxgljcDhjc.class);
  }


	 /**
	  * 提取
	  * @return
	  */
	 @AutoLog(value = "提取")
	 @ApiOperation(value="提取", notes="提取")
	 @GetMapping(value = "/init")
	 public Result<DkjkptBndksjjktz> init(HttpServletRequest request, HttpServletResponse response) {
		 Result<DkjkptBndksjjktz> result = new Result<DkjkptBndksjjktz>();
		 try {
			 fxgljcDhjcService.init();
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


}
