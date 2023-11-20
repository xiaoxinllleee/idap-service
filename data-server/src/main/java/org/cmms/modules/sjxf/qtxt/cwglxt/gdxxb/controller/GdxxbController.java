package org.cmms.modules.sjxf.qtxt.cwglxt.gdxxb.controller;

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
import org.cmms.modules.sjxf.qtxt.cwglxt.gdxxb.entity.Gdxxb;
import org.cmms.modules.sjxf.qtxt.cwglxt.gdxxb.service.IGdxxbService;
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
 * @Description: 股东信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags="股东信息表")
@RestController
@RequestMapping("/gdxxb/gdxxb")
public class GdxxbController extends JeecgController<Gdxxb, IGdxxbService> {
	@Autowired
	private IGdxxbService gdxxbService;

	/**
	 * 分页列表查询
	 *
	 * @param gdxxb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "股东信息表-分页列表查询")
	@ApiOperation(value="股东信息表-分页列表查询", notes="股东信息表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Gdxxb gdxxb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Gdxxb> queryWrapper = QueryGenerator.initQueryWrapper(gdxxb, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IGdxxbService.class,gdxxbService,pageNo,pageSize,queryWrapper,"certifno");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param gdxxb
	 * @return
	 */
	@AutoLog(value = "股东信息表-添加")
	@ApiOperation(value="股东信息表-添加", notes="股东信息表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Gdxxb gdxxb) {
		gdxxbService.save(gdxxb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param gdxxb
	 * @return
	 */
	@AutoLog(value = "股东信息表-编辑")
	@ApiOperation(value="股东信息表-编辑", notes="股东信息表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Gdxxb gdxxb) {
		gdxxbService.updateById(gdxxb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "股东信息表-通过id删除")
	@ApiOperation(value="股东信息表-通过id删除", notes="股东信息表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		gdxxbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "股东信息表-批量删除")
	@ApiOperation(value="股东信息表-批量删除", notes="股东信息表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.gdxxbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "股东信息表-通过id查询")
	@ApiOperation(value="股东信息表-通过id查询", notes="股东信息表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Gdxxb gdxxb = gdxxbService.getById(id);
		return Result.ok(gdxxb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param gdxxb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Gdxxb gdxxb) {
      return super.exportXls(request, gdxxb, Gdxxb.class, "股东信息表");
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
      return super.importExcel(request, response, Gdxxb.class);
  }

}
