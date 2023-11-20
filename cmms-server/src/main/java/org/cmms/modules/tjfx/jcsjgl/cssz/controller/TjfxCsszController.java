package org.cmms.modules.tjfx.jcsjgl.cssz.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.jcsjgl.cssz.entity.TjfxCssz;
import org.cmms.modules.tjfx.jcsjgl.cssz.entity.TjfxCsszVO;
import org.cmms.modules.tjfx.jcsjgl.cssz.service.ITjfxCsszService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.jcsjgl.cssz.verify.TjfxCszzImportVerify;
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
 * @Description: 参数设置
 * @Author: jeecg-boot
 * @Date:   2020-03-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="参数设置")
@RestController
@RequestMapping("/tjfx.jcsjgl.cssz/tjfxCssz")

public class TjfxCsszController extends JeecgController<TjfxCssz, ITjfxCsszService> {
	@Autowired
	private ITjfxCsszService tjfxCsszService;
	@Autowired
	private TjfxCszzImportVerify tjfxCszzImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param tjfxCssz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "参数设置-分页列表查询")
	@ApiOperation(value="参数设置-分页列表查询", notes="参数设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TjfxCssz tjfxCssz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TjfxCssz> queryWrapper = QueryGenerator.initQueryWrapper(tjfxCssz, req.getParameterMap());
		Page<TjfxCssz> page = new Page<TjfxCssz>(pageNo, pageSize);
		IPage<TjfxCssz> pageList = tjfxCsszService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param tjfxCssz
	 * @return
	 */
	@AutoLog(value = "参数设置-添加")
	@ApiOperation(value="参数设置-添加", notes="参数设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TjfxCssz tjfxCssz) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		tjfxCssz.setLrr(sysUser.getUsername());
		tjfxCssz.setLrbz(0);
		tjfxCssz.setLrsj(new Date());
		tjfxCsszService.save(tjfxCssz);
		return Result.ok("添加成功！");
	}

	 /**
	  * 通过多个id查询
	  *
	  * @param csbmList
	  * @return
	  */
	 @AutoLog(value = "参数设置-通过多个id查询")
	 @ApiOperation(value="参数设置-通过多个id查询", notes="参数设置-通过多个id查询")
	 @PostMapping(value = "/queryInfoByIds")
	 public Result<?> add(@RequestParam String csbmList) {
	 	List<String> list=Arrays.asList(csbmList.split(","));
	 	 QueryWrapper<TjfxCssz> tjfxCsszQueryWrapper=new QueryWrapper<>();
	 	 tjfxCsszQueryWrapper.in("csbm",list);
		 return Result.ok(tjfxCsszService.list(tjfxCsszQueryWrapper));
	 }

	/**
	 * 编辑
	 *
	 * @param tjfxCssz
	 * @return
	 */
	@AutoLog(value = "参数设置-编辑")
	@ApiOperation(value="参数设置-编辑", notes="参数设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TjfxCssz tjfxCssz) {
		UpdateWrapper<TjfxCssz> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("csbm",tjfxCssz.getCsbm());
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		tjfxCssz.setLrr(sysUser.getUsername());
		tjfxCssz.setLrbz(1);
		tjfxCssz.setLrsj(new Date());
		tjfxCsszService.update(tjfxCssz,updateWrapper);
		return Result.ok("编辑成功!");
	}

	 /**
	  * 通过id删除
	  *
	  * @param jsonObject
	  * @return
	  */
	 @PutMapping(value = "/delete")
	 public Result<?> delete(@RequestBody JSONObject jsonObject ) {
	 	UpdateWrapper<TjfxCssz> updateWrapper = new UpdateWrapper<>();
		 updateWrapper.eq("csbm",jsonObject.getString("csbm"));
		 tjfxCsszService.remove(updateWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "参数设置-批量删除")
	@ApiOperation(value="参数设置-批量删除", notes="参数设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tjfxCsszService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "参数设置-通过id查询")
	@ApiOperation(value="参数设置-通过id查询", notes="参数设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TjfxCssz tjfxCssz = tjfxCsszService.getById(id);
		return Result.ok(tjfxCssz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param tjfxCssz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, TjfxCssz tjfxCssz) {
      return super.exportXls(request, tjfxCssz, TjfxCssz.class, "参数设置");
  }
	 /**
	  * 导出模板
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "参数设置导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, TjfxCsszVO.class);
		 ExportParams exportParams = new ExportParams("参数设置导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
	 }
  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
 /* @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, TjfxCssz.class);
  }*/
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 return super.importExcelByTemplate(jsonObject, request, response, TjfxCssz.class,TjfxCsszVO.class,tjfxCszzImportVerify);
	 }
}
