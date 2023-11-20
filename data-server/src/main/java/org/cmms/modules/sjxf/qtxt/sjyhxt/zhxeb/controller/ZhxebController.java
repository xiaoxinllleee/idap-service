package org.cmms.modules.sjxf.qtxt.sjyhxt.zhxeb.controller;

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
import org.cmms.modules.sjxf.qtxt.sjyhxt.zhxeb.entity.Zhxeb;
import org.cmms.modules.sjxf.qtxt.sjyhxt.zhxeb.service.IZhxebService;
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
 * @Description: 账户限额表
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Slf4j
@Api(tags="账户限额表")
@RestController
@RequestMapping("/zhxeb/zhxeb")
public class ZhxebController extends JeecgController<Zhxeb, IZhxebService> {
	@Autowired
	private IZhxebService zhxebService;

	/**
	 * 分页列表查询
	 *
	 * @param zhxeb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "账户限额表-分页列表查询")
	@ApiOperation(value="账户限额表-分页列表查询", notes="账户限额表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zhxeb zhxeb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Zhxeb> queryWrapper = QueryGenerator.initQueryWrapper(zhxeb, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IZhxebService.class,zhxebService,pageNo,pageSize,queryWrapper,"accno","cstno","opentime");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param zhxeb
	 * @return
	 */
	@AutoLog(value = "账户限额表-添加")
	@ApiOperation(value="账户限额表-添加", notes="账户限额表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zhxeb zhxeb) {
		zhxebService.save(zhxeb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param zhxeb
	 * @return
	 */
	@AutoLog(value = "账户限额表-编辑")
	@ApiOperation(value="账户限额表-编辑", notes="账户限额表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zhxeb zhxeb) {
		zhxebService.updateById(zhxeb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "账户限额表-通过id删除")
	@ApiOperation(value="账户限额表-通过id删除", notes="账户限额表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zhxebService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "账户限额表-批量删除")
	@ApiOperation(value="账户限额表-批量删除", notes="账户限额表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zhxebService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "账户限额表-通过id查询")
	@ApiOperation(value="账户限额表-通过id查询", notes="账户限额表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zhxeb zhxeb = zhxebService.getById(id);
		return Result.ok(zhxeb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zhxeb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zhxeb zhxeb) {
      return super.exportXls(request, zhxeb, Zhxeb.class, "账户限额表");
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
      return super.importExcel(request, response, Zhxeb.class);
  }

}
