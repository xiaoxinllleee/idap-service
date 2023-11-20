package org.cmms.modules.tjfx.tjbb.shpjtj.sxyxmx_zh.controller;

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
import org.cmms.modules.tjfx.tjbb.shpjtj.sxyxmx_zh.entity.Tjfx_shsxyxtj_zhmx;
import org.cmms.modules.tjfx.tjbb.shpjtj.sxyxmx_zh.service.ITjfx_shsxyxtj_zhmxService;
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
 * @Description: 支行授信用信统计
 * @Author: jeecg-boot
 * @Date:   2020-11-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行授信用信统计")
@RestController
@RequestMapping("/tjbb.shpjtj.sxyxmx_zh/tjfx_shsxyxtj_zhmx")
public class Tjfx_shsxyxtj_zhmxController extends JeecgController<Tjfx_shsxyxtj_zhmx, ITjfx_shsxyxtj_zhmxService> {
	@Autowired
	private ITjfx_shsxyxtj_zhmxService tjfx_shsxyxtj_zhmxService;

	/**
	 * 分页列表查询
	 *
	 * @param tjfx_shsxyxtj_zhmx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行授信用信统计-分页列表查询")
	@ApiOperation(value="支行授信用信统计-分页列表查询", notes="支行授信用信统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Tjfx_shsxyxtj_zhmx tjfx_shsxyxtj_zhmx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Tjfx_shsxyxtj_zhmx> queryWrapper = QueryGenerator.initQueryWrapper(tjfx_shsxyxtj_zhmx, req.getParameterMap());
		Page<Tjfx_shsxyxtj_zhmx> page = new Page<Tjfx_shsxyxtj_zhmx>(pageNo, pageSize);
		IPage<Tjfx_shsxyxtj_zhmx> pageList = tjfx_shsxyxtj_zhmxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param tjfx_shsxyxtj_zhmx
	 * @return
	 */
	@AutoLog(value = "支行授信用信统计-添加")
	@ApiOperation(value="支行授信用信统计-添加", notes="支行授信用信统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Tjfx_shsxyxtj_zhmx tjfx_shsxyxtj_zhmx) {
		tjfx_shsxyxtj_zhmxService.save(tjfx_shsxyxtj_zhmx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param tjfx_shsxyxtj_zhmx
	 * @return
	 */
	@AutoLog(value = "支行授信用信统计-编辑")
	@ApiOperation(value="支行授信用信统计-编辑", notes="支行授信用信统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Tjfx_shsxyxtj_zhmx tjfx_shsxyxtj_zhmx) {
		tjfx_shsxyxtj_zhmxService.updateById(tjfx_shsxyxtj_zhmx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行授信用信统计-通过id删除")
	@ApiOperation(value="支行授信用信统计-通过id删除", notes="支行授信用信统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tjfx_shsxyxtj_zhmxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "支行授信用信统计-批量删除")
	@ApiOperation(value="支行授信用信统计-批量删除", notes="支行授信用信统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tjfx_shsxyxtj_zhmxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行授信用信统计-通过id查询")
	@ApiOperation(value="支行授信用信统计-通过id查询", notes="支行授信用信统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Tjfx_shsxyxtj_zhmx tjfx_shsxyxtj_zhmx = tjfx_shsxyxtj_zhmxService.getById(id);
		return Result.ok(tjfx_shsxyxtj_zhmx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param tjfx_shsxyxtj_zhmx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Tjfx_shsxyxtj_zhmx tjfx_shsxyxtj_zhmx) {
      return super.exportXls(request, tjfx_shsxyxtj_zhmx, Tjfx_shsxyxtj_zhmx.class, "支行授信用信统计");
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
      return super.importExcel(request, response, Tjfx_shsxyxtj_zhmx.class);
  }

}
