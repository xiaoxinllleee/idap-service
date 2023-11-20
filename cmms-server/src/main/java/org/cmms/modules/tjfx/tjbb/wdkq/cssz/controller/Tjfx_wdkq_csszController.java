package org.cmms.modules.tjfx.tjbb.wdkq.cssz.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
import org.cmms.modules.tjfx.tjbb.wdkq.cssz.entity.Tjfx_wdkq_cssz;
import org.cmms.modules.tjfx.tjbb.wdkq.cssz.service.ITjfx_wdkq_csszService;
import java.util.Date;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-04-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="1")
@RestController
@RequestMapping("/tjfx_wdkq_cssz/tjfx_wdkq_cssz")
public class Tjfx_wdkq_csszController extends JeecgController<Tjfx_wdkq_cssz, ITjfx_wdkq_csszService> {
	@Autowired
	private ITjfx_wdkq_csszService tjfx_wdkq_csszService;
	
	/**
	 * 分页列表查询
	 *
	 * @param tjfx_wdkq_cssz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "1-分页列表查询")
	@ApiOperation(value="1-分页列表查询", notes="1-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Tjfx_wdkq_cssz tjfx_wdkq_cssz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Tjfx_wdkq_cssz> queryWrapper = QueryGenerator.initQueryWrapper(tjfx_wdkq_cssz, req.getParameterMap());
		Page<Tjfx_wdkq_cssz> page = new Page<Tjfx_wdkq_cssz>(pageNo, pageSize);
		IPage<Tjfx_wdkq_cssz> pageList = tjfx_wdkq_csszService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param tjfx_wdkq_cssz
	 * @return
	 */
	@AutoLog(value = "1-添加")
	@ApiOperation(value="1-添加", notes="1-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Tjfx_wdkq_cssz tjfx_wdkq_cssz) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		tjfx_wdkq_cssz.setLrr(sysUser.getUsername());
		tjfx_wdkq_cssz.setLrbz(0);
		tjfx_wdkq_cssz.setLrsj(new Date());
		tjfx_wdkq_csszService.save(tjfx_wdkq_cssz);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param tjfx_wdkq_cssz
	 * @return
	 */
	@AutoLog(value = "1-编辑")
	@ApiOperation(value="1-编辑", notes="1-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Tjfx_wdkq_cssz tjfx_wdkq_cssz) {
		UpdateWrapper<Tjfx_wdkq_cssz> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("csbm",tjfx_wdkq_cssz.getCsbm());
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		tjfx_wdkq_cssz.setLrr(sysUser.getUsername());
		tjfx_wdkq_cssz.setLrbz(1);
		tjfx_wdkq_cssz.setLrsj(new Date());
		tjfx_wdkq_csszService.update(tjfx_wdkq_cssz,updateWrapper);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "1-通过id删除")
	@ApiOperation(value="1-通过id删除", notes="1-通过id删除")
	@PutMapping(value = "/delete")
	public Result<?> delete(@RequestBody JSONObject jsonObject) {
		UpdateWrapper<Tjfx_wdkq_cssz> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("csbm",jsonObject.getString("csbm"));
		tjfx_wdkq_csszService.remove(updateWrapper);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "1-批量删除")
	@ApiOperation(value="1-批量删除", notes="1-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tjfx_wdkq_csszService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "1-通过id查询")
	@ApiOperation(value="1-通过id查询", notes="1-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Tjfx_wdkq_cssz tjfx_wdkq_cssz = tjfx_wdkq_csszService.getById(id);
		return Result.ok(tjfx_wdkq_cssz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param tjfx_wdkq_cssz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Tjfx_wdkq_cssz tjfx_wdkq_cssz) {
      return super.exportXls(request, tjfx_wdkq_cssz, Tjfx_wdkq_cssz.class, "1");
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
      return super.importExcel(request, response, Tjfx_wdkq_cssz.class);
  }

}
