package org.cmms.modules.sjxf.hxxt.nbzjymx_01.controller;

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
import org.cmms.modules.sjxf.hxxt.nbzjymx_01.entity.Nbzjymx_01;
import org.cmms.modules.sjxf.hxxt.nbzjymx_01.service.INbzjymx_01Service;
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
 * @Description: 内部帐交易明细_01
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="内部帐交易明细_01")
@RestController
@RequestMapping("/nbzjymx_01/nbzjymx_01")
public class Nbzjymx_01Controller extends JeecgController<Nbzjymx_01, INbzjymx_01Service> {
	@Autowired
	private INbzjymx_01Service nbzjymx_01Service;

	/**
	 * 分页列表查询
	 *
	 * @param nbzjymx_01
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_01-分页列表查询")
	@ApiOperation(value="内部帐交易明细_01-分页列表查询", notes="内部帐交易明细_01-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Nbzjymx_01 nbzjymx_01,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Nbzjymx_01> queryWrapper = QueryGenerator.initQueryWrapper(nbzjymx_01, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(INbzjymx_01Service.class,nbzjymx_01Service,pageNo,pageSize,queryWrapper,"acct_No","trn_Date","jrnl_No");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param nbzjymx_01
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_01-添加")
	@ApiOperation(value="内部帐交易明细_01-添加", notes="内部帐交易明细_01-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Nbzjymx_01 nbzjymx_01) {
		nbzjymx_01Service.save(nbzjymx_01);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param nbzjymx_01
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_01-编辑")
	@ApiOperation(value="内部帐交易明细_01-编辑", notes="内部帐交易明细_01-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Nbzjymx_01 nbzjymx_01) {
		nbzjymx_01Service.updateById(nbzjymx_01);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_01-通过id删除")
	@ApiOperation(value="内部帐交易明细_01-通过id删除", notes="内部帐交易明细_01-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		nbzjymx_01Service.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_01-批量删除")
	@ApiOperation(value="内部帐交易明细_01-批量删除", notes="内部帐交易明细_01-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.nbzjymx_01Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_01-通过id查询")
	@ApiOperation(value="内部帐交易明细_01-通过id查询", notes="内部帐交易明细_01-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Nbzjymx_01 nbzjymx_01 = nbzjymx_01Service.getById(id);
		return Result.ok(nbzjymx_01);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param nbzjymx_01
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Nbzjymx_01 nbzjymx_01) {
      return super.exportXls(request, nbzjymx_01, Nbzjymx_01.class, "内部帐交易明细_01");
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
      return super.importExcel(request, response, Nbzjymx_01.class);
  }

}
