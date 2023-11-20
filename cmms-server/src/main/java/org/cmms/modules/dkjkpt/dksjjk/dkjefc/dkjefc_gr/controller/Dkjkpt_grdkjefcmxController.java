package org.cmms.modules.dkjkpt.dksjjk.dkjefc.dkjefc_gr.controller;

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
import org.cmms.modules.dkjkpt.dksjjk.dkjefc.dkjefc_gr.entity.Dkjkpt_grdkjefcmx;
import org.cmms.modules.dkjkpt.dksjjk.dkjefc.dkjefc_gr.service.IDkjkpt_grdkjefcmxService;
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
 * @Description: 贷款金额分层_个人
 * @Author: jeecg-boot
 * @Date:   2020-11-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款金额分层_个人")
@RestController
@RequestMapping("/dkjkpt.dksjjk.dkjefc.dkjefc_gr/dkjkpt_grdkjefcmx")
public class Dkjkpt_grdkjefcmxController extends JeecgController<Dkjkpt_grdkjefcmx, IDkjkpt_grdkjefcmxService> {
	@Autowired
	private IDkjkpt_grdkjefcmxService dkjkpt_grdkjefcmxService;

	/**
	 * 分页列表查询
	 *
	 * @param dkjkpt_grdkjefcmx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款金额分层_个人-分页列表查询")
	@ApiOperation(value="贷款金额分层_个人-分页列表查询", notes="贷款金额分层_个人-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dkjkpt_grdkjefcmx dkjkpt_grdkjefcmx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dkjkpt_grdkjefcmx> queryWrapper = QueryGenerator.initQueryWrapper(dkjkpt_grdkjefcmx, req.getParameterMap());
		Page<Dkjkpt_grdkjefcmx> page = new Page<Dkjkpt_grdkjefcmx>(pageNo, pageSize);
		queryWrapper.orderByDesc("tjyf");
		IPage<Dkjkpt_grdkjefcmx> pageList = dkjkpt_grdkjefcmxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dkjkpt_grdkjefcmx
	 * @return
	 */
	@AutoLog(value = "贷款金额分层_个人-添加")
	@ApiOperation(value="贷款金额分层_个人-添加", notes="贷款金额分层_个人-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dkjkpt_grdkjefcmx dkjkpt_grdkjefcmx) {
		dkjkpt_grdkjefcmxService.save(dkjkpt_grdkjefcmx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dkjkpt_grdkjefcmx
	 * @return
	 */
	@AutoLog(value = "贷款金额分层_个人-编辑")
	@ApiOperation(value="贷款金额分层_个人-编辑", notes="贷款金额分层_个人-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dkjkpt_grdkjefcmx dkjkpt_grdkjefcmx) {
		dkjkpt_grdkjefcmxService.updateById(dkjkpt_grdkjefcmx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款金额分层_个人-通过id删除")
	@ApiOperation(value="贷款金额分层_个人-通过id删除", notes="贷款金额分层_个人-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkjkpt_grdkjefcmxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款金额分层_个人-批量删除")
	@ApiOperation(value="贷款金额分层_个人-批量删除", notes="贷款金额分层_个人-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkjkpt_grdkjefcmxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款金额分层_个人-通过id查询")
	@ApiOperation(value="贷款金额分层_个人-通过id查询", notes="贷款金额分层_个人-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dkjkpt_grdkjefcmx dkjkpt_grdkjefcmx = dkjkpt_grdkjefcmxService.getById(id);
		return Result.ok(dkjkpt_grdkjefcmx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkjkpt_grdkjefcmx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dkjkpt_grdkjefcmx dkjkpt_grdkjefcmx) {
      return super.exportXls(request, dkjkpt_grdkjefcmx, Dkjkpt_grdkjefcmx.class, "贷款金额分层_个人");
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
      return super.importExcel(request, response, Dkjkpt_grdkjefcmx.class);
  }

}
