package org.cmms.modules.sjxf.qtxt.khxxst.gskhjl.controller;

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
import org.cmms.modules.sjxf.qtxt.khxxst.gskhjl.entity.Gskhjl;
import org.cmms.modules.sjxf.qtxt.khxxst.gskhjl.service.IGskhjlService;
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
 * @Description: 归属客户经理
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Slf4j
@Api(tags="归属客户经理")
@RestController
@RequestMapping("/gskhjl/gskhjl")
public class GskhjlController extends JeecgController<Gskhjl, IGskhjlService> {
	@Autowired
	private IGskhjlService gskhjlService;

	/**
	 * 分页列表查询
	 *
	 * @param gskhjl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "归属客户经理-分页列表查询")
	@ApiOperation(value="归属客户经理-分页列表查询", notes="归属客户经理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Gskhjl gskhjl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Gskhjl> queryWrapper = QueryGenerator.initQueryWrapper(gskhjl, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IGskhjlService.class,gskhjlService,pageNo,pageSize,queryWrapper,"belong_Manager_Id","cust_Id");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param gskhjl
	 * @return
	 */
	@AutoLog(value = "归属客户经理-添加")
	@ApiOperation(value="归属客户经理-添加", notes="归属客户经理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Gskhjl gskhjl) {
		gskhjlService.save(gskhjl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param gskhjl
	 * @return
	 */
	@AutoLog(value = "归属客户经理-编辑")
	@ApiOperation(value="归属客户经理-编辑", notes="归属客户经理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Gskhjl gskhjl) {
		gskhjlService.updateById(gskhjl);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "归属客户经理-通过id删除")
	@ApiOperation(value="归属客户经理-通过id删除", notes="归属客户经理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		gskhjlService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "归属客户经理-批量删除")
	@ApiOperation(value="归属客户经理-批量删除", notes="归属客户经理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.gskhjlService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "归属客户经理-通过id查询")
	@ApiOperation(value="归属客户经理-通过id查询", notes="归属客户经理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Gskhjl gskhjl = gskhjlService.getById(id);
		return Result.ok(gskhjl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param gskhjl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Gskhjl gskhjl) {
      return super.exportXls(request, gskhjl, Gskhjl.class, "归属客户经理");
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
      return super.importExcel(request, response, Gskhjl.class);
  }

}
