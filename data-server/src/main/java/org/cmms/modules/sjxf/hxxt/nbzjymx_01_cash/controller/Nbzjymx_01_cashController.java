package org.cmms.modules.sjxf.hxxt.nbzjymx_01_cash.controller;

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
import org.cmms.modules.sjxf.hxxt.nbzjymx_01_cash.entity.Nbzjymx_01_cash;
import org.cmms.modules.sjxf.hxxt.nbzjymx_01_cash.service.INbzjymx_01_cashService;
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
 * @Description: 内部帐交易明细_01_cash
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="内部帐交易明细_01_cash")
@RestController
@RequestMapping("/nbzjymx_01_cash/nbzjymx_01_cash")
public class Nbzjymx_01_cashController extends JeecgController<Nbzjymx_01_cash, INbzjymx_01_cashService> {
	@Autowired
	private INbzjymx_01_cashService nbzjymx_01_cashService;

	/**
	 * 分页列表查询
	 *
	 * @param nbzjymx_01_cash
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_01_cash-分页列表查询")
	@ApiOperation(value="内部帐交易明细_01_cash-分页列表查询", notes="内部帐交易明细_01_cash-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Nbzjymx_01_cash nbzjymx_01_cash,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Nbzjymx_01_cash> queryWrapper = QueryGenerator.initQueryWrapper(nbzjymx_01_cash, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(INbzjymx_01_cashService.class,nbzjymx_01_cashService,pageNo,pageSize,queryWrapper,"acct_No","trn_Date","jrnl_No");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param nbzjymx_01_cash
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_01_cash-添加")
	@ApiOperation(value="内部帐交易明细_01_cash-添加", notes="内部帐交易明细_01_cash-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Nbzjymx_01_cash nbzjymx_01_cash) {
		nbzjymx_01_cashService.save(nbzjymx_01_cash);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param nbzjymx_01_cash
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_01_cash-编辑")
	@ApiOperation(value="内部帐交易明细_01_cash-编辑", notes="内部帐交易明细_01_cash-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Nbzjymx_01_cash nbzjymx_01_cash) {
		nbzjymx_01_cashService.updateById(nbzjymx_01_cash);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_01_cash-通过id删除")
	@ApiOperation(value="内部帐交易明细_01_cash-通过id删除", notes="内部帐交易明细_01_cash-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		nbzjymx_01_cashService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_01_cash-批量删除")
	@ApiOperation(value="内部帐交易明细_01_cash-批量删除", notes="内部帐交易明细_01_cash-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.nbzjymx_01_cashService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_01_cash-通过id查询")
	@ApiOperation(value="内部帐交易明细_01_cash-通过id查询", notes="内部帐交易明细_01_cash-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Nbzjymx_01_cash nbzjymx_01_cash = nbzjymx_01_cashService.getById(id);
		return Result.ok(nbzjymx_01_cash);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param nbzjymx_01_cash
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Nbzjymx_01_cash nbzjymx_01_cash) {
      return super.exportXls(request, nbzjymx_01_cash, Nbzjymx_01_cash.class, "内部帐交易明细_01_cash");
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
      return super.importExcel(request, response, Nbzjymx_01_cash.class);
  }

}
