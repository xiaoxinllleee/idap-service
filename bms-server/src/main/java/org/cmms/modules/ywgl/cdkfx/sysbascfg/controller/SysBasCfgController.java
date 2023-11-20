package org.cmms.modules.ywgl.cdkfx.sysbascfg.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ywgl.cdkfx.sysbascfg.entity.SysBasCfg;
import org.cmms.modules.ywgl.cdkfx.sysbascfg.service.ISysBasCfgService;
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
 * @Description: 系统配置参数表
 * @Author: jeecg-boot
 * @Date:   2022-02-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="系统配置参数表")
@RestController
@RequestMapping("/sysbascfg/sysBasCfg")
public class SysBasCfgController extends JeecgController<SysBasCfg, ISysBasCfgService> {
	@Autowired
	private ISysBasCfgService sysBasCfgService;

	/**
	 * 分页列表查询
	 *
	 * @param sysBasCfg
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "系统配置参数表-分页列表查询")
	@ApiOperation(value="系统配置参数表-分页列表查询", notes="系统配置参数表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SysBasCfg sysBasCfg,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SysBasCfg> queryWrapper = QueryGenerator.initQueryWrapper(sysBasCfg, req.getParameterMap());
		Page<SysBasCfg> page = new Page<SysBasCfg>(pageNo, pageSize);
		IPage<SysBasCfg> pageList = sysBasCfgService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sysBasCfg
	 * @return
	 */
	@AutoLog(value = "系统配置参数表-添加")
	@ApiOperation(value="系统配置参数表-添加", notes="系统配置参数表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SysBasCfg sysBasCfg) {
		sysBasCfgService.save(sysBasCfg);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sysBasCfg
	 * @return
	 */
	@AutoLog(value = "系统配置参数表-编辑")
	@ApiOperation(value="系统配置参数表-编辑", notes="系统配置参数表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SysBasCfg sysBasCfg) {
		sysBasCfgService.updateById(sysBasCfg);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "系统配置参数表-通过id删除")
	@ApiOperation(value="系统配置参数表-通过id删除", notes="系统配置参数表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sysBasCfgService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "系统配置参数表-批量删除")
	@ApiOperation(value="系统配置参数表-批量删除", notes="系统配置参数表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sysBasCfgService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "系统配置参数表-通过id查询")
	@ApiOperation(value="系统配置参数表-通过id查询", notes="系统配置参数表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SysBasCfg sysBasCfg = sysBasCfgService.getById(id);
		return Result.ok(sysBasCfg);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sysBasCfg
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SysBasCfg sysBasCfg) {
      return super.exportXls(request, sysBasCfg, SysBasCfg.class, "系统配置参数表");
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
      return super.importExcel(request, response, SysBasCfg.class);
  }

}
