package org.cmms.modules.tjfx.tjbb.shpjtj.sxyxmx_gr.controller;

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
import org.cmms.modules.tjfx.tjbb.shpjtj.sxyxmx_gr.entity.Tjfx_shsxyxtj;
import org.cmms.modules.tjfx.tjbb.shpjtj.sxyxmx_gr.service.ITjfx_shsxyxtjService;
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
 * @Description: 商户授信用信明细
 * @Author: jeecg-boot
 * @Date:   2020-11-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="商户授信用信明细")
@RestController
@RequestMapping("/tjbb.shpjtj.sxyxmx_gr/tjfx_shsxyxtj")
public class Tjfx_shsxyxtjController extends JeecgController<Tjfx_shsxyxtj, ITjfx_shsxyxtjService> {
	@Autowired
	private ITjfx_shsxyxtjService tjfx_shsxyxtjService;

	/**
	 * 分页列表查询
	 *
	 * @param tjfx_shsxyxtj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "商户授信用信明细-分页列表查询")
	@ApiOperation(value="商户授信用信明细-分页列表查询", notes="商户授信用信明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Tjfx_shsxyxtj tjfx_shsxyxtj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Tjfx_shsxyxtj> queryWrapper = QueryGenerator.initQueryWrapper(tjfx_shsxyxtj, req.getParameterMap());
		Page<Tjfx_shsxyxtj> page = new Page<Tjfx_shsxyxtj>(pageNo, pageSize);
		IPage<Tjfx_shsxyxtj> pageList = tjfx_shsxyxtjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param tjfx_shsxyxtj
	 * @return
	 */
	@AutoLog(value = "商户授信用信明细-添加")
	@ApiOperation(value="商户授信用信明细-添加", notes="商户授信用信明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Tjfx_shsxyxtj tjfx_shsxyxtj) {
		tjfx_shsxyxtjService.save(tjfx_shsxyxtj);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param tjfx_shsxyxtj
	 * @return
	 */
	@AutoLog(value = "商户授信用信明细-编辑")
	@ApiOperation(value="商户授信用信明细-编辑", notes="商户授信用信明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Tjfx_shsxyxtj tjfx_shsxyxtj) {
		tjfx_shsxyxtjService.updateById(tjfx_shsxyxtj);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商户授信用信明细-通过id删除")
	@ApiOperation(value="商户授信用信明细-通过id删除", notes="商户授信用信明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tjfx_shsxyxtjService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "商户授信用信明细-批量删除")
	@ApiOperation(value="商户授信用信明细-批量删除", notes="商户授信用信明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tjfx_shsxyxtjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商户授信用信明细-通过id查询")
	@ApiOperation(value="商户授信用信明细-通过id查询", notes="商户授信用信明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Tjfx_shsxyxtj tjfx_shsxyxtj = tjfx_shsxyxtjService.getById(id);
		return Result.ok(tjfx_shsxyxtj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param tjfx_shsxyxtj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Tjfx_shsxyxtj tjfx_shsxyxtj) {
      return super.exportXls(request, tjfx_shsxyxtj, Tjfx_shsxyxtj.class, "商户授信用信明细");
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
      return super.importExcel(request, response, Tjfx_shsxyxtj.class);
  }

}
