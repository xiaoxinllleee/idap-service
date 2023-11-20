package org.cmms.modules.ywgl.cdkfx.dkqxsz.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.ywgl.cdkfx.dkqxsz.entity.ErpBasDkqxszImortVo;
import org.cmms.modules.ywgl.cdkfx.dkqxsz.service.IErpBasDkqxszService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ywgl.cdkfx.dkqxsz.entity.ErpBasDkqxsz;
import org.cmms.modules.ywgl.cdkfx.dkqxsz.verify.DkqxszImportVerify;
import org.cmms.modules.ywgl.nxt.shpj.glzhxx.entity.Glzhxx;
import org.cmms.modules.ywgl.nxt.shpj.glzhxx.entity.GlzhxxImportVo;
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
 * @Description: Erp_bas_dkqxsz
 * @Author: jeecg-boot
 * @Date:   2021-06-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="Erp_bas_dkqxsz")
@RestController
@RequestMapping("/dkqxsz/erpBasDkqxsz")
public class ErpBasDkqxszController extends JeecgController<ErpBasDkqxsz, IErpBasDkqxszService> {
	@Autowired
	private IErpBasDkqxszService erpBasDkqxszService;


	 @Autowired
	 private DkqxszImportVerify dkqxszImportVerify;
	/**
	 * 分页列表查询
	 *
	 * @param erpBasDkqxsz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "Erp_bas_dkqxsz-分页列表查询")
	@ApiOperation(value="Erp_bas_dkqxsz-分页列表查询", notes="Erp_bas_dkqxsz-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ErpBasDkqxsz erpBasDkqxsz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ErpBasDkqxsz> queryWrapper = QueryGenerator.initQueryWrapper(erpBasDkqxsz, req.getParameterMap());
		IPage pageList = PageUtil.toPage(IErpBasDkqxszService.class,erpBasDkqxszService, pageNo, pageSize, queryWrapper, "ywjgdm","zzbz");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param erpBasDkqxsz
	 * @return
	 */
	@AutoLog(value = "Erp_bas_dkqxsz-添加")
	@ApiOperation(value="Erp_bas_dkqxsz-添加", notes="Erp_bas_dkqxsz-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ErpBasDkqxsz erpBasDkqxsz) {
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq("ZZBZ", erpBasDkqxsz.getZzbz());
		List<ErpBasDkqxsz> list = erpBasDkqxszService.list(queryWrapper);
		if (list.size() > 0)
			return Result.error("组织标志重复!");

		erpBasDkqxsz.setLrbz(1);
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		erpBasDkqxsz.setLrr(sysUser.getRealname());
		erpBasDkqxsz.setLrsj(new Timestamp(System.currentTimeMillis()));
		erpBasDkqxszService.save(erpBasDkqxsz);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param erpBasDkqxsz
	 * @return
	 */
	@AutoLog(value = "Erp_bas_dkqxsz-编辑")
	@ApiOperation(value="Erp_bas_dkqxsz-编辑", notes="Erp_bas_dkqxsz-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ErpBasDkqxsz erpBasDkqxsz) {
		QueryWrapper queryWrapper=new QueryWrapper();
		queryWrapper.eq("zzbz",erpBasDkqxsz.getZzbz());
		erpBasDkqxsz.setZzbz(null);//hive里面分桶键不能改动，设为null
		erpBasDkqxsz.setLrbz(2);
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		erpBasDkqxsz.setLrr(sysUser.getRealname());
		erpBasDkqxsz.setLrsj(new Timestamp(System.currentTimeMillis()));
		erpBasDkqxszService.update(erpBasDkqxsz,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "Erp_bas_dkqxsz-通过组织标识删除")
	@ApiOperation(value="Erp_bas_dkqxsz-通过组织标识删除", notes="Erp_bas_dkqxsz-通过组织标识删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		QueryWrapper queryWrapper=new QueryWrapper();
		queryWrapper.eq("zzbz",id);
		erpBasDkqxszService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "Erp_bas_dkqxsz-批量删除")
	@ApiOperation(value="Erp_bas_dkqxsz-批量删除", notes="Erp_bas_dkqxsz-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.erpBasDkqxszService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "Erp_bas_dkqxsz-通过id查询")
	@ApiOperation(value="Erp_bas_dkqxsz-通过id查询", notes="Erp_bas_dkqxsz-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ErpBasDkqxsz erpBasDkqxsz = erpBasDkqxszService.getById(id);
		return Result.ok(erpBasDkqxsz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param erpBasDkqxsz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ErpBasDkqxsz erpBasDkqxsz) {
      return super.exportXls(request, erpBasDkqxsz, ErpBasDkqxsz.class, "贷款权限设置");
  }

	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {

		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称

		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "贷款权限设置导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, ErpBasDkqxszImortVo.class);
		 ExportParams exportParams = new ExportParams("贷款权限设置导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;

	 }


	 /**
	  * 通过excel导入数据
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 return super.importExcelByTemplate(jsonObject, request, response, ErpBasDkqxsz.class,ErpBasDkqxszImortVo.class, dkqxszImportVerify);
	 }
}
