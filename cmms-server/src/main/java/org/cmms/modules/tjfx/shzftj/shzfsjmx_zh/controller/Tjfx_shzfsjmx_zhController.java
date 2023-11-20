package org.cmms.modules.tjfx.shzftj.shzfsjmx_zh.controller;

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
import org.cmms.modules.tjfx.shzftj.shzfsjmx_zh.entity.Tjfx_shzfsjmx_zh;
import org.cmms.modules.tjfx.shzftj.shzfsjmx_zh.service.ITjfx_shzfsjmx_zhService;
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
 * @Description: 商户走访数据明细统计_支行
 * @Author: jeecg-boot
 * @Date:   2020-11-04
 * @Version: V1.0
 */
@Slf4j
@Api(tags="商户走访数据明细统计_支行")
@RestController
@RequestMapping("/TJFX_SHZFSJMX_ZH/tjfx_shzfsjmx_zh")
public class Tjfx_shzfsjmx_zhController extends JeecgController<Tjfx_shzfsjmx_zh, ITjfx_shzfsjmx_zhService> {
	@Autowired
	private ITjfx_shzfsjmx_zhService tjfx_shzfsjmx_zhService;

	/**
	 * 分页列表查询
	 *
	 * @param tjfx_shzfsjmx_zh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "商户走访数据明细统计_支行-分页列表查询")
	@ApiOperation(value="商户走访数据明细统计_支行-分页列表查询", notes="商户走访数据明细统计_支行-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Tjfx_shzfsjmx_zh tjfx_shzfsjmx_zh,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Tjfx_shzfsjmx_zh> queryWrapper = QueryGenerator.initQueryWrapper(tjfx_shzfsjmx_zh, req.getParameterMap());
		Page<Tjfx_shzfsjmx_zh> page = new Page<Tjfx_shzfsjmx_zh>(pageNo, pageSize);
		IPage<Tjfx_shzfsjmx_zh> pageList = tjfx_shzfsjmx_zhService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param tjfx_shzfsjmx_zh
	 * @return
	 */
	@AutoLog(value = "商户走访数据明细统计_支行-添加")
	@ApiOperation(value="商户走访数据明细统计_支行-添加", notes="商户走访数据明细统计_支行-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Tjfx_shzfsjmx_zh tjfx_shzfsjmx_zh) {
		tjfx_shzfsjmx_zhService.save(tjfx_shzfsjmx_zh);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param tjfx_shzfsjmx_zh
	 * @return
	 */
	@AutoLog(value = "商户走访数据明细统计_支行-编辑")
	@ApiOperation(value="商户走访数据明细统计_支行-编辑", notes="商户走访数据明细统计_支行-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Tjfx_shzfsjmx_zh tjfx_shzfsjmx_zh) {
		tjfx_shzfsjmx_zhService.updateById(tjfx_shzfsjmx_zh);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商户走访数据明细统计_支行-通过id删除")
	@ApiOperation(value="商户走访数据明细统计_支行-通过id删除", notes="商户走访数据明细统计_支行-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tjfx_shzfsjmx_zhService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "商户走访数据明细统计_支行-批量删除")
	@ApiOperation(value="商户走访数据明细统计_支行-批量删除", notes="商户走访数据明细统计_支行-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tjfx_shzfsjmx_zhService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商户走访数据明细统计_支行-通过id查询")
	@ApiOperation(value="商户走访数据明细统计_支行-通过id查询", notes="商户走访数据明细统计_支行-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Tjfx_shzfsjmx_zh tjfx_shzfsjmx_zh = tjfx_shzfsjmx_zhService.getById(id);
		return Result.ok(tjfx_shzfsjmx_zh);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param tjfx_shzfsjmx_zh
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Tjfx_shzfsjmx_zh tjfx_shzfsjmx_zh) {
      return super.exportXls(request, tjfx_shzfsjmx_zh, Tjfx_shzfsjmx_zh.class, "商户走访数据明细统计_支行");
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
      return super.importExcel(request, response, Tjfx_shzfsjmx_zh.class);
  }

}
