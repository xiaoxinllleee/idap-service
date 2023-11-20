package org.cmms.modules.tjfx.zcsxtj.zcsxxzcjdb.controller;

import java.math.BigDecimal;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.zcsxtj.zcsxxzcjdb.entity.Zcsxxzcjdb;
import org.cmms.modules.tjfx.zcsxtj.zcsxxzcjdb.mapper.ZcsxxzcjdbMapper;
import org.cmms.modules.tjfx.zcsxtj.zcsxxzcjdb.service.IZcsxxzcjdbService;
import org.cmms.modules.tjfx.zcsxtjfw.entity.ZcsxTjfw;
import org.cmms.modules.tjfx.zcsxtjfw.service.IZcsxTjfwService;
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
 * @Description: 整村授信行政村进度表
 * @Author: jeecg-boot
 * @Date:   2023-05-20
 * @Version: V1.0
 */
@Slf4j
@Api(tags="整村授信行政村进度表")
@RestController
@RequestMapping("/zcsxxzcjdb/zcsxxzcjdb")
public class ZcsxxzcjdbController extends JeecgController<Zcsxxzcjdb, IZcsxxzcjdbService> {
	@Autowired
	private IZcsxxzcjdbService zcsxxzcjdbService;
	 @Autowired
	 private IZcsxTjfwService zcsxTjfwService;
	 @Autowired
	 private ZcsxxzcjdbMapper zcsxxzcjdbMapper;
	/**
	 * 分页列表查询
	 *
	 * @param zcsxxzcjdb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "整村授信行政村进度表-分页列表查询")
	@ApiOperation(value="整村授信行政村进度表-分页列表查询", notes="整村授信行政村进度表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zcsxxzcjdb zcsxxzcjdb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req,String tjnf,String pc) {
		String zkhjlmc=zcsxxzcjdb.getZkhjl();
		String wgmc=zcsxxzcjdb.getWgbh();
		zcsxxzcjdb.setZkhjl(null);
		zcsxxzcjdb.setWgbh(null);
		QueryWrapper<Zcsxxzcjdb> queryWrapper = QueryGenerator.initQueryWrapper(zcsxxzcjdb, req.getParameterMap());
		//统计范围
		if (StringUtils.isNotBlank(tjnf) && StringUtils.isNotBlank(pc)){
			QueryWrapper<ZcsxTjfw> queryWrapper1 = new QueryWrapper<ZcsxTjfw>();
			queryWrapper1.eq("nf",tjnf);
			queryWrapper1.eq("pc",pc);
			List<ZcsxTjfw> list = zcsxTjfwService.list(queryWrapper1);
			List<String> wgbh = new ArrayList<>();
			for (ZcsxTjfw zcsxTjfw : list) {
				wgbh.add(zcsxTjfw.getWgbh());
			}
			queryWrapper.in("wgbh",wgbh);
		}
		//责任人模糊查询
		if (StringUtils.isNotBlank(zkhjlmc)){
			queryWrapper.inSql("zkhjl","select yggh from Hr_bas_staff where ygxm like '%"+zkhjlmc+"%'");
		}
		if (!getUsername().equals("admin")) {
			queryWrapper.exists("select 1 from (" +
							"select  to_char(menu_id) menu_id from YXDYGL_PQQXGL t where khjl ='" + getWorkNo() + "'" +
							" union all " +
							" select parent_id menu_id from yxdygl_main where wgbh in (" +
							"   select  menu_id from YXDYGL_PQQXGL t where khjl ='" + getWorkNo() + "')" +
					" ) t2 where tjfx_zcsxjdb_cun.wgbh=t2.menu_id");

			//in  用 list有个数限制问题， 此处改为inSql
			/**
			String sqlSswg = "select  to_char(menu_id) from YXDYGL_PQQXGL t where khjl ='" + getWorkNo() + "'" +
					" union all " +
					" select parent_id from yxdygl_main where wgbh in (" +
					"   select  menu_id from YXDYGL_PQQXGL t where khjl ='" + getWorkNo() + "'" +
					" )";
			queryWrapper.and(i -> i.inSql("wgbh", sqlSswg)); */
		}
		//网格模糊查询
		if (StringUtils.isNotBlank(wgmc)){
			queryWrapper.inSql("wgbh","select wgbh from YXDYGL_MAIN where wgmc like '%"+wgmc+"%'");
		}

		Page<Zcsxxzcjdb> page = new Page<Zcsxxzcjdb>(pageNo, pageSize);
		IPage<Zcsxxzcjdb> pageList = zcsxxzcjdbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	 @GetMapping(value = "/maxDate")
	 public Result<?> maxDate(){
		 String substring = zcsxxzcjdbMapper.maxDate();
		 String maxDate = substring.substring(0, 10);
		 return Result.ok("查询成功!",maxDate);
	 }

	/**
	 * 添加
	 *
	 * @param zcsxxzcjdb
	 * @return
	 */
	@AutoLog(value = "整村授信行政村进度表-添加")
	@ApiOperation(value="整村授信行政村进度表-添加", notes="整村授信行政村进度表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zcsxxzcjdb zcsxxzcjdb) {
		zcsxxzcjdbService.save(zcsxxzcjdb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param zcsxxzcjdb
	 * @return
	 */
	@AutoLog(value = "整村授信行政村进度表-编辑")
	@ApiOperation(value="整村授信行政村进度表-编辑", notes="整村授信行政村进度表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zcsxxzcjdb zcsxxzcjdb) {
		zcsxxzcjdbService.updateById(zcsxxzcjdb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "整村授信行政村进度表-通过id删除")
	@ApiOperation(value="整村授信行政村进度表-通过id删除", notes="整村授信行政村进度表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zcsxxzcjdbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "整村授信行政村进度表-批量删除")
	@ApiOperation(value="整村授信行政村进度表-批量删除", notes="整村授信行政村进度表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zcsxxzcjdbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "整村授信行政村进度表-通过id查询")
	@ApiOperation(value="整村授信行政村进度表-通过id查询", notes="整村授信行政村进度表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zcsxxzcjdb zcsxxzcjdb = zcsxxzcjdbService.getById(id);
		return Result.ok(zcsxxzcjdb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zcsxxzcjdb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zcsxxzcjdb zcsxxzcjdb, String tjnf,String pc) {
	  // Step.1 组装查询条件
	  String zkhjlmc=zcsxxzcjdb.getZkhjl();
	  String wgmc=zcsxxzcjdb.getWgbh();
	  zcsxxzcjdb.setZkhjl(null);
	  zcsxxzcjdb.setWgbh(null);
	  QueryWrapper<Zcsxxzcjdb> queryWrapper = QueryGenerator.initQueryWrapper(zcsxxzcjdb, request.getParameterMap());
	  //统计范围
	  if (StringUtils.isNotBlank(tjnf) && StringUtils.isNotBlank(pc)){
		  QueryWrapper<ZcsxTjfw> queryWrapper1 = new QueryWrapper<ZcsxTjfw>();
		  queryWrapper1.eq("nf",tjnf);
		  queryWrapper1.eq("pc",pc);
		  List<ZcsxTjfw> list = zcsxTjfwService.list(queryWrapper1);
		  List<String> wgbh = new ArrayList<>();
		  for (ZcsxTjfw zcsxTjfw : list) {
			  wgbh.add(zcsxTjfw.getWgbh());
		  }
		  queryWrapper.in("wgbh",wgbh);
	  }
	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	  String selections = request.getParameter("selections");
	  String rowKey = request.getParameter("rowKey");

	  //20211201 过滤选中数据
	  //20211201 BY LIUXIANGQUN 过滤选择的数据改为执行接sql  in  查询
	  if (oConvertUtils.isNotEmpty(selections)) {
		  List<String> selectionList = Arrays.asList(selections.split(","));
		  if(oConvertUtils.isNotEmpty(rowKey)){
			  queryWrapper.in(rowKey,selectionList);
		  }else{
			  queryWrapper.in("ID",selectionList);
		  }
	  }

	  //责任人模糊查询
	  if (StringUtils.isNotBlank(zkhjlmc)){
		  queryWrapper.inSql("zkhjl","select yggh from Hr_bas_staff where ygxm like '%"+zkhjlmc+"%'");
	  }
	  if (!getUsername().equals("admin")) {
		  //in  用 list有个数限制问题， 此处改为inSql
		  String sqlSswg = "select  to_char(menu_id) from YXDYGL_PQQXGL t where khjl ='" + getWorkNo() + "'" +
				  " union all " +
				  " select parent_id from yxdygl_main where wgbh in (" +
				  "   select  menu_id from YXDYGL_PQQXGL t where khjl ='" + getWorkNo() + "'" +
				  " )";
		  queryWrapper.and(i -> i.inSql("wgbh", sqlSswg));
	  }
	  //网格模糊查询
	  if (StringUtils.isNotBlank(wgmc)){
		  queryWrapper.inSql("wgbh","select wgbh from YXDYGL_MAIN where wgmc like '%"+wgmc+"%'");
	  }

	  List<Zcsxxzcjdb> exportList = service.list(queryWrapper);
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
	  String title = "整村授信行政村进度表";
	  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
	  mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
	  mv.addObject(NormalExcelConstants.CLASS, Zcsxxzcjdb.class);
	  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", t + "导出人:" + sysUser.getRealname()+"   导出时间:"+ DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"), title));
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
      return super.importExcel(request, response, Zcsxxzcjdb.class);
  }

}
