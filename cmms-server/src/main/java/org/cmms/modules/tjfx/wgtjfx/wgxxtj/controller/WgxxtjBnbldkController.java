package org.cmms.modules.tjfx.wgtjfx.wgxxtj.controller;

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
import org.cmms.modules.tjfx.wgtjfx.wgxxtj.entity.WgxxtjBnbldk;
import org.cmms.modules.tjfx.wgtjfx.wgxxtj.service.IWgxxtjBnbldkService;
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
 * @Description: 表内不良贷款
 * @Author: jeecg-boot
 * @Date:   2023-06-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="表内不良贷款")
@RestController
@RequestMapping("/wgxxtj/wgxxtjBnbldk")
public class WgxxtjBnbldkController extends JeecgController<WgxxtjBnbldk, IWgxxtjBnbldkService> {
	@Autowired
	private IWgxxtjBnbldkService wgxxtjBnbldkService;
	
	/**
	 * 分页列表查询
	 *
	 * @param wgxxtjBnbldk
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "表内不良贷款-分页列表查询")
	@ApiOperation(value="表内不良贷款-分页列表查询", notes="表内不良贷款-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(WgxxtjBnbldk wgxxtjBnbldk,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<WgxxtjBnbldk> queryWrapper = QueryGenerator.initQueryWrapper(wgxxtjBnbldk, req.getParameterMap());
		Page<WgxxtjBnbldk> page = new Page<WgxxtjBnbldk>(pageNo, pageSize);
		IPage<WgxxtjBnbldk> pageList = wgxxtjBnbldkService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param wgxxtjBnbldk
	 * @return
	 */
	@AutoLog(value = "表内不良贷款-添加")
	@ApiOperation(value="表内不良贷款-添加", notes="表内不良贷款-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody WgxxtjBnbldk wgxxtjBnbldk) {
		wgxxtjBnbldkService.save(wgxxtjBnbldk);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param wgxxtjBnbldk
	 * @return
	 */
	@AutoLog(value = "表内不良贷款-编辑")
	@ApiOperation(value="表内不良贷款-编辑", notes="表内不良贷款-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody WgxxtjBnbldk wgxxtjBnbldk) {
		wgxxtjBnbldkService.updateById(wgxxtjBnbldk);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "表内不良贷款-通过id删除")
	@ApiOperation(value="表内不良贷款-通过id删除", notes="表内不良贷款-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		wgxxtjBnbldkService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "表内不良贷款-批量删除")
	@ApiOperation(value="表内不良贷款-批量删除", notes="表内不良贷款-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.wgxxtjBnbldkService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "表内不良贷款-通过id查询")
	@ApiOperation(value="表内不良贷款-通过id查询", notes="表内不良贷款-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		WgxxtjBnbldk wgxxtjBnbldk = wgxxtjBnbldkService.getById(id);
		return Result.ok(wgxxtjBnbldk);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param wgxxtjBnbldk
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, WgxxtjBnbldk wgxxtjBnbldk) {
      return super.exportXls(request, wgxxtjBnbldk, WgxxtjBnbldk.class, "表内不良贷款");
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
      return super.importExcel(request, response, WgxxtjBnbldk.class);
  }

}
