package org.cmms.modules.sjxf.hxxt.nbzjymx_36.controller;

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
import org.cmms.modules.sjxf.hxxt.nbzjymx_36.entity.Nbzjymx_36;
import org.cmms.modules.sjxf.hxxt.nbzjymx_36.service.INbzjymx_36Service;
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
 * @Description: 内部帐交易明细_36
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="内部帐交易明细_36")
@RestController
@RequestMapping("/nbzjymx_36/nbzjymx_36")
public class Nbzjymx_36Controller extends JeecgController<Nbzjymx_36, INbzjymx_36Service> {
	@Autowired
	private INbzjymx_36Service nbzjymx_36Service;

	/**
	 * 分页列表查询
	 *
	 * @param nbzjymx_36
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_36-分页列表查询")
	@ApiOperation(value="内部帐交易明细_36-分页列表查询", notes="内部帐交易明细_36-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Nbzjymx_36 nbzjymx_36,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Nbzjymx_36> queryWrapper = QueryGenerator.initQueryWrapper(nbzjymx_36, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(INbzjymx_36Service.class,nbzjymx_36Service,pageNo,pageSize,queryWrapper,"acct_No","trn_Date","jrnl_No");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param nbzjymx_36
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_36-添加")
	@ApiOperation(value="内部帐交易明细_36-添加", notes="内部帐交易明细_36-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Nbzjymx_36 nbzjymx_36) {
		nbzjymx_36Service.save(nbzjymx_36);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param nbzjymx_36
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_36-编辑")
	@ApiOperation(value="内部帐交易明细_36-编辑", notes="内部帐交易明细_36-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Nbzjymx_36 nbzjymx_36) {
		nbzjymx_36Service.updateById(nbzjymx_36);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_36-通过id删除")
	@ApiOperation(value="内部帐交易明细_36-通过id删除", notes="内部帐交易明细_36-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		nbzjymx_36Service.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_36-批量删除")
	@ApiOperation(value="内部帐交易明细_36-批量删除", notes="内部帐交易明细_36-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.nbzjymx_36Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_36-通过id查询")
	@ApiOperation(value="内部帐交易明细_36-通过id查询", notes="内部帐交易明细_36-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Nbzjymx_36 nbzjymx_36 = nbzjymx_36Service.getById(id);
		return Result.ok(nbzjymx_36);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param nbzjymx_36
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Nbzjymx_36 nbzjymx_36) {
      return super.exportXls(request, nbzjymx_36, Nbzjymx_36.class, "内部帐交易明细_36");
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
      return super.importExcel(request, response, Nbzjymx_36.class);
  }

}
