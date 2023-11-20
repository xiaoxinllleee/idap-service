package org.cmms.modules.tjfx.zcsxtj.zcsxczjdb.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.tjfxHnkd.entity.TjfxHnkd;
import org.cmms.modules.tjfx.zcsxtj.zcsxczjdb.entity.TjfxZcsxjdbXzdk;
import org.cmms.modules.tjfx.zcsxtj.zcsxczjdb.entity.Zcsxczjdb;
import org.cmms.modules.tjfx.zcsxtj.zcsxczjdb.mapper.ZcsxczjdbMapper;
import org.cmms.modules.tjfx.zcsxtj.zcsxczjdb.service.IZcsxczjdbService;
import org.cmms.modules.tjfx.zcsxtj.zcsxxzcjdb.entity.Zcsxxzcjdb;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 整村授信村组进度表
 * @Author: jeecg-boot
 * @Date:   2023-05-20
 * @Version: V1.0
 */
@Slf4j
@Api(tags="整村授信村组进度表")
@RestController
@RequestMapping("/zcsxczjdb/zcsxczjdb")
public class ZcsxczjdbController extends JeecgController<Zcsxczjdb, IZcsxczjdbService> {
	@Autowired
	private IZcsxczjdbService zcsxczjdbService;
	@Autowired
	private ZcsxczjdbMapper zcsxczjdbMapper;

	/**
	 * 分页列表查询
	 *
	 * @param zcsxczjdb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "整村授信村组进度表-分页列表查询")
	@ApiOperation(value="整村授信村组进度表-分页列表查询", notes="整村授信村组进度表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zcsxczjdb zcsxczjdb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String zkhjlmc=zcsxczjdb.getZkhjl();
		String wgmc=zcsxczjdb.getWgbh();
		zcsxczjdb.setZkhjl(null);
		zcsxczjdb.setWgbh(null);
		QueryWrapper<Zcsxczjdb> queryWrapper = QueryGenerator.initQueryWrapper(zcsxczjdb, req.getParameterMap());
		//责任人模糊查询
		if (StringUtils.isNotBlank(zkhjlmc)){
			queryWrapper.inSql("zkhjl","select yggh from Hr_bas_staff where ygxm like '%"+zkhjlmc+"%'");
		}
		//网格模糊查询
		if (StringUtils.isNotBlank(wgmc)){
			queryWrapper.inSql("wgbh","select wgbh from YXDYGL_MAIN where wgmc like '%"+wgmc+"%'");
		}
		if (!getUsername().equals("admin")) {
			//in  用 list有个数限制问题， 此处改为inSql
			String sqlSswg = "select menu_id from YXDYGL_PQQXGL t where khjl ='" + getWorkNo() + "'";
			queryWrapper.and(i -> i.inSql("wgbh", sqlSswg));
		}
		Page<Zcsxczjdb> page = new Page<Zcsxczjdb>(pageNo, pageSize);
		IPage<Zcsxczjdb> pageList = zcsxczjdbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	 @GetMapping(value = "/maxDate")
	 public Result<?> maxDate(){
		 String substring = zcsxczjdbMapper.maxDate();
		 String maxDate = substring.substring(0, 10);
		 return Result.ok("查询成功!",maxDate);
	 }
	/**
	 * 添加
	 *
	 * @param zcsxczjdb
	 * @return
	 */
	@AutoLog(value = "整村授信村组进度表-添加")
	@ApiOperation(value="整村授信村组进度表-添加", notes="整村授信村组进度表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zcsxczjdb zcsxczjdb) {
		zcsxczjdbService.save(zcsxczjdb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param zcsxczjdb
	 * @return
	 */
	@AutoLog(value = "整村授信村组进度表-编辑")
	@ApiOperation(value="整村授信村组进度表-编辑", notes="整村授信村组进度表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zcsxczjdb zcsxczjdb) {
		zcsxczjdbService.updateById(zcsxczjdb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "整村授信村组进度表-通过id删除")
	@ApiOperation(value="整村授信村组进度表-通过id删除", notes="整村授信村组进度表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zcsxczjdbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "整村授信村组进度表-批量删除")
	@ApiOperation(value="整村授信村组进度表-批量删除", notes="整村授信村组进度表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zcsxczjdbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "整村授信村组进度表-通过id查询")
	@ApiOperation(value="整村授信村组进度表-通过id查询", notes="整村授信村组进度表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zcsxczjdb zcsxczjdb = zcsxczjdbService.getById(id);
		return Result.ok(zcsxczjdb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zcsxczjdb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zcsxczjdb zcsxczjdb) {
	  QueryWrapper<Zcsxczjdb> queryWrapper = QueryGenerator.initQueryWrapper(zcsxczjdb, request.getParameterMap());
	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	  String selections = request.getParameter("selections");
	  String rowKey = request.getParameter("rowKey");

	  if (oConvertUtils.isNotEmpty(selections)) {
		  List<String> selectionList = Arrays.asList(selections.split(","));
		  if(oConvertUtils.isNotEmpty(rowKey)){
			  queryWrapper.in(rowKey,selectionList);
		  }else{
			  queryWrapper.in("ID",selectionList);
		  }
	  }

	  List<Zcsxczjdb> exportList = service.list(queryWrapper);
	  //金额由元变成万元
	  exportList.forEach(e -> {
		  e.setBkbcpEdhz(e.getBkbcpEdhz()==null || e.getBkbcpEdhz().compareTo(new BigDecimal("0")) < 1 ? e.getBkbcpEdhz() :e.getBkbcpEdhz().divide(new BigDecimal("10000"),4,BigDecimal.ROUND_HALF_UP));
		  e.setBkbfpEdhz(e.getBkbfpEdhz()==null || e.getBkbfpEdhz().compareTo(new BigDecimal("0")) < 1 ? e.getBkbfpEdhz() :e.getBkbfpEdhz().divide(new BigDecimal("10000"),4,BigDecimal.ROUND_HALF_UP));
		  e.setZhsdEdhz(e.getZhsdEdhz()==null || e.getZhsdEdhz().compareTo(new BigDecimal("0")) < 1 ? e.getZhsdEdhz() :e.getZhsdEdhz().divide(new BigDecimal("10000"),4,BigDecimal.ROUND_HALF_UP));
		  e.setHnkdEdhz(e.getHnkdEdhz()==null || e.getHnkdEdhz().compareTo(new BigDecimal("0")) < 1 ? e.getHnkdEdhz() :e.getHnkdEdhz().divide(new BigDecimal("10000"),4,BigDecimal.ROUND_HALF_UP));
		  e.setHnkdQyedhz(e.getHnkdQyedhz()==null || e.getHnkdQyedhz().compareTo(new BigDecimal("0")) < 1 ? e.getHnkdQyedhz() :e.getHnkdQyedhz().divide(new BigDecimal("10000"),4,BigDecimal.ROUND_HALF_UP));
		  e.setDkhtQyedhz(e.getDkhtQyedhz()==null || e.getDkhtQyedhz().compareTo(new BigDecimal("0")) < 1 ? e.getDkhtQyedhz() :e.getDkhtQyedhz().divide(new BigDecimal("10000"),4,BigDecimal.ROUND_HALF_UP));
		  e.setDkyxEdhz(e.getDkyxEdhz()==null || e.getDkyxEdhz().compareTo(new BigDecimal("0")) < 1 ? e.getDkyxEdhz() :e.getDkyxEdhz().divide(new BigDecimal("10000"),4,BigDecimal.ROUND_HALF_UP));
		  e.setDkhtNqkhQyedhz(e.getDkhtNqkhQyedhz()==null || e.getDkhtNqkhQyedhz().compareTo(new BigDecimal("0")) < 1 ? e.getDkhtNqkhQyedhz() :e.getDkhtNqkhQyedhz().divide(new BigDecimal("10000"),4,BigDecimal.ROUND_HALF_UP));
	  });

	  String t = "";
	  if (!exportList.isEmpty()) {
		  t = "贷款数据日期:" + DateUtil.format(exportList.get(0).getRkrq(), "yyyy-MM-dd") + "   惠农快贷数据日期:" + DateUtil.format(exportList.get(0).getHnkdSjrq(), "yyyy-MM-dd") + "   ";
	  }
	  // Step.3 AutoPoi 导出Excel
	  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
	  mv.addObject(NormalExcelConstants.FILE_NAME, "整村授信村组进度表"); //此处设置的filename无效 ,前端会重更新设置一下
	  mv.addObject(NormalExcelConstants.CLASS, Zcsxczjdb.class);
	  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("整村授信村组进度表" + "报表", t + "导出人:" + sysUser.getRealname()+"   导出时间:"+ DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"), "整村授信村组进度表"));
	  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
	  return mv;
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
      return super.importExcel(request, response, Zcsxczjdb.class);
  }

	 /**
	  * 天易-获取整村授信报表数据明细
	  *
	  * @param wgbh     网格编号
	  * @param tjrq     统计日期
	  * @param lx       数据查询类型
	  * @param otherlx   其他类型
	  * @param  type   报表类型（zu:村组；cun:行政村;zh:支行）
	  * @param sszh   所属支行
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @GetMapping("getZcsxMx")
	 public Result<?> getZcsxMx(String wgbh, String tjrq, String lx, String otherlx,String type,String sszh, String nf, String pc,
								@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
								@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
								HttpServletRequest req) {
		 tjrq = tjrq.replace("-", "");
		 Page<TjfxHnkd> page1 = new Page<>(pageNo, pageSize);
		 Page<TjfxZcsxjdbXzdk> page2 = new Page<>(pageNo, pageSize);
		 IPage<TjfxHnkd> tjfxHnkdIPage = null;
		 IPage<TjfxZcsxjdbXzdk> zcsxjdbXzdkIPage = null;
		 //惠农快贷导入数据明细/惠农快贷电话回访数据明细
		 if ("1".equals(lx) || "2".equals(lx)) {
			 tjfxHnkdIPage = zcsxczjdbService.getHnkdDrxxPage(page1, wgbh, lx, type, sszh, tjrq, nf, pc);
			 return Result.ok(tjfxHnkdIPage);
			 //惠农快贷签约客户数据明细
		 } else if ("3".equals(lx)) {
			 zcsxjdbXzdkIPage = zcsxczjdbService.getHnkdQyxxPage(page2, wgbh, tjrq, type, sszh, nf, pc);
			 return Result.ok(zcsxjdbXzdkIPage);
			 //新增贷款合客户数、本月本周新增客户数据明细
		 } else if ("4".equals(lx)) {
			 zcsxjdbXzdkIPage = zcsxczjdbService.getDkhtxxPage(page2, wgbh, tjrq, otherlx, type, sszh, nf, pc);
			 return Result.ok(zcsxjdbXzdkIPage);
			 //新增用信数据明细
		 } else if ("5".equals(lx)) {
			 zcsxjdbXzdkIPage = zcsxczjdbService.getYXPage(page2, wgbh, tjrq, otherlx, type, sszh, nf, pc);
			 return Result.ok(zcsxjdbXzdkIPage);
		 }
		 return Result.ok();
	 }

	 /**
	  * 整村授信明细-导出excel
	  *
	  * @param request
	  */
	 @RequestMapping(value = "/exportZcsxMx")
	 public ModelAndView exportXls(HttpServletRequest request, String lx, String wgbh, String filename, String tjrq, String otherlx,
								   String type,String sszh, String nf, String pc) {
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 tjrq = tjrq.replace("-", "");
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, "整村授信村组进度表");
		 if ("1".equals(lx) || "2".equals(lx)) {
			 List<TjfxHnkd> hnkdList = zcsxczjdbService.getHnkdDrxxList(wgbh,lx,type,sszh,tjrq, nf, pc);
			 mv.addObject(NormalExcelConstants.CLASS, TjfxHnkd.class);
			 mv.addObject(NormalExcelConstants.DATA_LIST, hnkdList);
		 } else if ("3".equals(lx)) {
			 List<TjfxZcsxjdbXzdk> hnkdList = zcsxczjdbService.getHnkdQyxxList(wgbh, tjrq,type,sszh, nf, pc);
			 mv.addObject(NormalExcelConstants.CLASS, TjfxZcsxjdbXzdk.class);
			 mv.addObject(NormalExcelConstants.DATA_LIST, hnkdList);
		 }else if ("4".equals(lx)) {
			 List<TjfxZcsxjdbXzdk> hnkdList = zcsxczjdbService.getDkhtxxList(wgbh,tjrq,otherlx,type,sszh, nf, pc);
			 mv.addObject(NormalExcelConstants.CLASS, TjfxZcsxjdbXzdk.class);
			 mv.addObject(NormalExcelConstants.DATA_LIST, hnkdList);
		 }else if("5".equals(lx)){
			 List<TjfxZcsxjdbXzdk> hnkdList = zcsxczjdbService.getYxList(wgbh,tjrq,otherlx,type,sszh, nf, pc);
			 mv.addObject(NormalExcelConstants.CLASS, TjfxZcsxjdbXzdk.class);
			 mv.addObject(NormalExcelConstants.DATA_LIST, hnkdList);
		 }
		 if (StringUtils.isNotBlank(filename)) {
			 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(filename, "导出人:" + sysUser.getRealname() + "   导出时间:" + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"), "整村授信明细表"));
		 } else {
			 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("整村授信明细表", "导出人:" + sysUser.getRealname() + "   导出时间:" + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"), "整村授信明细表"));
		 }
		 return mv;
	 }
 }
