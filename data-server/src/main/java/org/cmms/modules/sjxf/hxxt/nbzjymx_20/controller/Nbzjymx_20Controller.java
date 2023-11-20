package org.cmms.modules.sjxf.hxxt.nbzjymx_20.controller;

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
import org.cmms.modules.sjxf.hxxt.nbzjymx_20.entity.Nbzjymx_20;
import org.cmms.modules.sjxf.hxxt.nbzjymx_20.service.INbzjymx_20Service;
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
 * @Description: 内部帐交易明细_20
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="内部帐交易明细_20")
@RestController
@RequestMapping("/nbzjymx_20/nbzjymx_20")
public class Nbzjymx_20Controller extends JeecgController<Nbzjymx_20, INbzjymx_20Service> {
	@Autowired
	private INbzjymx_20Service nbzjymx_20Service;

	/**
	 * 分页列表查询
	 *
	 * @param nbzjymx_20
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_20-分页列表查询")
	@ApiOperation(value="内部帐交易明细_20-分页列表查询", notes="内部帐交易明细_20-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Nbzjymx_20 nbzjymx_20,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Nbzjymx_20> queryWrapper = QueryGenerator.initQueryWrapper(nbzjymx_20, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(INbzjymx_20Service.class,nbzjymx_20Service,pageNo,pageSize,queryWrapper,"acct_No","trn_Date","jrnl_No");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param nbzjymx_20
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_20-添加")
	@ApiOperation(value="内部帐交易明细_20-添加", notes="内部帐交易明细_20-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Nbzjymx_20 nbzjymx_20) {
		nbzjymx_20Service.save(nbzjymx_20);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param nbzjymx_20
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_20-编辑")
	@ApiOperation(value="内部帐交易明细_20-编辑", notes="内部帐交易明细_20-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Nbzjymx_20 nbzjymx_20) {
		nbzjymx_20Service.updateById(nbzjymx_20);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_20-通过id删除")
	@ApiOperation(value="内部帐交易明细_20-通过id删除", notes="内部帐交易明细_20-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		nbzjymx_20Service.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_20-批量删除")
	@ApiOperation(value="内部帐交易明细_20-批量删除", notes="内部帐交易明细_20-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.nbzjymx_20Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "内部帐交易明细_20-通过id查询")
	@ApiOperation(value="内部帐交易明细_20-通过id查询", notes="内部帐交易明细_20-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Nbzjymx_20 nbzjymx_20 = nbzjymx_20Service.getById(id);
		return Result.ok(nbzjymx_20);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param nbzjymx_20
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Nbzjymx_20 nbzjymx_20) {
      return super.exportXls(request, nbzjymx_20, Nbzjymx_20.class, "内部帐交易明细_20");
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
      return super.importExcel(request, response, Nbzjymx_20.class);
  }

}
