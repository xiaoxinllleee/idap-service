package org.cmms.modules.ywgl.dkyw.dkmzdjb.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ywgl.dkyw.dkmzdjb.entity.Dkmzdjb;
import org.cmms.modules.ywgl.dkyw.dkmzdjb.service.IDkmzdjbService;
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
 * @Description: 贷款免责登记簿
 * @Author: jeecg-boot
 * @Date:   2021-09-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款免责登记簿")
@RestController
@RequestMapping("/dkmzdjb/dkmzdjb")
public class DkmzdjbController extends JeecgController<Dkmzdjb, IDkmzdjbService> {
	@Autowired
	private IDkmzdjbService dkmzdjbService;
	
	/**
	 * 分页列表查询
	 *
	 * @param dkmzdjb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款免责登记簿-分页列表查询")
	@ApiOperation(value="贷款免责登记簿-分页列表查询", notes="贷款免责登记簿-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dkmzdjb dkmzdjb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dkmzdjb> queryWrapper = QueryGenerator.initQueryWrapper(dkmzdjb, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IDkmzdjbService.class,dkmzdjbService,pageNo,pageSize,queryWrapper,"ywjgdm","dkzh");
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param dkmzdjb
	 * @return
	 */
	@AutoLog(value = "贷款免责登记簿-添加")
	@ApiOperation(value="贷款免责登记簿-添加", notes="贷款免责登记簿-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dkmzdjb dkmzdjb) {
		dkmzdjb.setLrbz(1);
		dkmzdjbService.save(dkmzdjb);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param dkmzdjb
	 * @return
	 */
	@AutoLog(value = "贷款免责登记簿-编辑")
	@ApiOperation(value="贷款免责登记簿-编辑", notes="贷款免责登记簿-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dkmzdjb dkmzdjb) {
		dkmzdjb.setLrbz(2);
		QueryWrapper<Dkmzdjb> queryWrapper = new QueryWrapper<Dkmzdjb>();
		queryWrapper.eq("acctNo",dkmzdjb.getAcctNo());
		dkmzdjbService.update(dkmzdjb,queryWrapper);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "贷款免责登记簿-通过id删除")
	@ApiOperation(value="贷款免责登记簿-通过id删除", notes="贷款免责登记簿-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("acctNo")String acctNo) {
		QueryWrapper<Dkmzdjb> queryWrapper = new QueryWrapper<Dkmzdjb>();
		queryWrapper.eq("acctNo",acctNo);
		dkmzdjbService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款免责登记簿-批量删除")
	@ApiOperation(value="贷款免责登记簿-批量删除", notes="贷款免责登记簿-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkmzdjbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款免责登记簿-通过id查询")
	@ApiOperation(value="贷款免责登记簿-通过id查询", notes="贷款免责登记簿-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dkmzdjb dkmzdjb = dkmzdjbService.getById(id);
		return Result.ok(dkmzdjb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkmzdjb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dkmzdjb dkmzdjb) {
      return super.exportXls(request, dkmzdjb, Dkmzdjb.class, "贷款免责登记簿");
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
      return super.importExcel(request, response, Dkmzdjb.class);
  }

}
