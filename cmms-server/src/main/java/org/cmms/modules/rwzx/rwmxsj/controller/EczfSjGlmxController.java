package org.cmms.modules.rwzx.rwmxsj.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.rwzx.rwmxsj.entity.EczfSjGlmx;
import org.cmms.modules.rwzx.rwmxsj.service.IEczfSjGlmxService;
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
 * @Description: 二次走访过滤原始数据
 * @Author: jeecg-boot
 * @Date:   2023-07-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="二次走访过滤原始数据")
@RestController
@RequestMapping("/rwmxjs/eczfSjGlmx")
public class EczfSjGlmxController extends JeecgController<EczfSjGlmx, IEczfSjGlmxService> {
	@Autowired
	private IEczfSjGlmxService eczfSjGlmxService;
	
	/**
	 * 分页列表查询
	 *
	 * @param eczfSjGlmx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "二次走访过滤原始数据-分页列表查询")
	@ApiOperation(value="二次走访过滤原始数据-分页列表查询", notes="二次走访过滤原始数据-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(EczfSjGlmx eczfSjGlmx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {

		String wgbh=eczfSjGlmx.getWgbh();
		eczfSjGlmx.setWgbh(null);
		QueryWrapper<EczfSjGlmx> queryWrapper = QueryGenerator.initQueryWrapper(eczfSjGlmx, req.getParameterMap());
		if (StringUtils.isNotBlank(wgbh)) {
			queryWrapper.inSql("wgbh", "select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id");
		}
		Page<EczfSjGlmx> page = new Page<EczfSjGlmx>(pageNo, pageSize);
		IPage<EczfSjGlmx> pageList = eczfSjGlmxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param eczfSjGlmx
	 * @return
	 */
	@AutoLog(value = "二次走访过滤原始数据-添加")
	@ApiOperation(value="二次走访过滤原始数据-添加", notes="二次走访过滤原始数据-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody EczfSjGlmx eczfSjGlmx) {
		eczfSjGlmxService.save(eczfSjGlmx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param eczfSjGlmx
	 * @return
	 */
	@AutoLog(value = "二次走访过滤原始数据-编辑")
	@ApiOperation(value="二次走访过滤原始数据-编辑", notes="二次走访过滤原始数据-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody EczfSjGlmx eczfSjGlmx) {
		eczfSjGlmxService.updateById(eczfSjGlmx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "二次走访过滤原始数据-通过id删除")
	@ApiOperation(value="二次走访过滤原始数据-通过id删除", notes="二次走访过滤原始数据-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		eczfSjGlmxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "二次走访过滤原始数据-批量删除")
	@ApiOperation(value="二次走访过滤原始数据-批量删除", notes="二次走访过滤原始数据-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.eczfSjGlmxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "二次走访过滤原始数据-通过id查询")
	@ApiOperation(value="二次走访过滤原始数据-通过id查询", notes="二次走访过滤原始数据-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		EczfSjGlmx eczfSjGlmx = eczfSjGlmxService.getById(id);
		return Result.ok(eczfSjGlmx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param eczfSjGlmx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, EczfSjGlmx eczfSjGlmx) {
      return super.exportXls(request, eczfSjGlmx, EczfSjGlmx.class, "二次走访过滤原始数据");
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
      return super.importExcel(request, response, EczfSjGlmx.class);
  }

}
