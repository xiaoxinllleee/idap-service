package org.cmms.modules.tjfx.zfsjtj.nhzfsjmx.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.zfsjtj.nhzfsjmx.entity.VNhzfsjmx;
import org.cmms.modules.tjfx.zfsjtj.nhzfsjmx.entity.Zfsjmx;
import org.cmms.modules.tjfx.zfsjtj.nhzfsjmx.service.IVNhzfsjmxService;
import org.cmms.modules.tjfx.zfsjtj.nhzfsjmx.service.IZfsjmxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.service.ISysDictService;

import org.cmms.modules.tjfx.zfsjtj.nhzfsjmx.vo.ZfsjmxExp;
import org.cmms.modules.tjfx.zfsjtj.nhzfsjmx.vo.ZfsjmxQlExp;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 走访数据明细
 * @Author: jeecg-boot
 * @Date:   2022-06-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="农户走访数据明细")
@RestController
@RequestMapping("/zfsjmx/zfsjmx")
public class ZfsjmxController extends JeecgController<Zfsjmx, IZfsjmxService> {
	@Autowired
	private IZfsjmxService zfsjmxService;
	@Autowired
	private IVNhzfsjmxService vNhzfsjmxService;
	@Autowired
	private ISysDictService sysDictService;
	/**
	 * 分页列表查询
	 *
	 * @param zfsjmx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "走访数据明细-分页列表查询")
	@ApiOperation(value="走访数据明细-分页列表查询", notes="走访数据明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VNhzfsjmx zfsjmx, String lx, String zzbz, String wgbh, String hzfs, String sjlx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VNhzfsjmx> queryWrapper = this.initQueryWrapper(zfsjmx, lx, zzbz, wgbh, hzfs, sjlx);
		Page<VNhzfsjmx> page = new Page<VNhzfsjmx>(pageNo, pageSize);
		IPage<VNhzfsjmx> pageList = vNhzfsjmxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 private QueryWrapper<VNhzfsjmx> initQueryWrapper(VNhzfsjmx zfsjmx, String lx, String zzbz, String wgbh, String hzfs, String sjlx) {
		 QueryWrapper<VNhzfsjmx> queryWrapper = new QueryWrapper<>();
		 if (StringUtils.isNotEmpty(zfsjmx.getYggh())) {
			 //个人查询
			 queryWrapper.eq("yggh", zfsjmx.getYggh());
		 }
		 if (StringUtils.isNotEmpty(zzbz)) {
		 	String tjrqStr = DateUtil.format(zfsjmx.getTjrq(), "yyyyMMdd");
			 //支行查询
			 if ("1".equals(hzfs)) {
				 //根据客户所在支行汇总
				 String ywjgdm = sysDictService.queryTableDictTextByKey("hr_bas_organization", "ywjgdm", "zzbz", zzbz);
				 queryWrapper.eq("jgdm", ywjgdm);
			 } else if ("2".equals(hzfs)) {
				 //根据客户经理所在支行汇总
//				 String sql = "select yggh from hr_bas_staff_post where zzbz=" + zzbz + " and rgrq<=to_date(" + tjrqStr + ",'yyyymmdd') " +
//						 "and (lgrq is null or  lgrq>=to_date(" + tjrqStr + ",'yyyymmdd'))";
//				 queryWrapper.inSql("yggh", sql);

				 queryWrapper.exists("select 1 from hr_bas_staff_post t2 where tjfx_zfsjmx_nh.yggh=t2.yggh  " +
						 "and t2.zzbz=" + zzbz + " and " +
						 "tjfx_zfsjmx_nh.tjrq>=t2.rgrq and (t2.lgrq>=tjfx_zfsjmx_nh.tjrq or t2.lgrq is null)");
			 }
		 }
		 if (StringUtils.isNotEmpty(wgbh)) {
		 	 //网格查询同时查询所有下级的数据
			 queryWrapper.inSql("wgbh", "select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id ");
		 }
		 if (StringUtils.isNotEmpty(zfsjmx.getSxlx())) {
			 if ("9".equals(zfsjmx.getSxlx())) {
			 	 //存量客户，包含两种类型 授信未用信 授信已用信
				 queryWrapper.in("sxlx","2","3");
			 } else {
				 queryWrapper.eq("sxlx", zfsjmx.getSxlx());
			 }
		 }
		 Date tjrq = zfsjmx.getTjrq();
		 Date start = null;
		 Date end = tjrq;
		 //sfyxzf sfyxtj
		 Integer integer = Integer.valueOf(lx);
		 if (integer <= 6 ){
			 //走访数明细
			 queryWrapper.eq("sfyxtj","1");
		 }else {
			 //有效走访数明细
			 queryWrapper.eq("sfyxzf","1");
		 }

		 //根据不同类型判断查询开始日期
		 if ("1".equals(lx) || "7".equals(lx)) {
			 //日 等于统计日期
			 start = tjrq;
		 } else if ("2".equals(lx) || "8".equals(lx)) {
			 //周 大于等于周初小于等于统计日期
			 start = cn.hutool.core.date.DateUtil.beginOfWeek(tjrq);
			 if ("2".equals(sjlx)) {
				 end = cn.hutool.core.date.DateUtil.endOfWeek(tjrq);
			 }
		 } else if ("3".equals(lx) || "9".equals(lx)) {
			 //月 大于等于月初小于等于统计日期
			 start = DateUtil.getMonthBeginDay(tjrq);
			 if ("2".equals(sjlx)) {
			 	end = DateUtil.getMonthEndDay(tjrq);
			 }
		 }else if ("4".equals(lx) || "10".equals(lx)) {
			 //季 大于等于季初小于等于统计日期
			 start = DateUtil.getQBeginDay(tjrq);
			 if ("2".equals(sjlx)) {
			 	end = DateUtil.getQEndDay(tjrq);
			 }
		 }else if ("5".equals(lx) || "11".equals(lx)) {
			 //年 大于等于年初小于等于统计日期
			 start = DateUtil.getYBeginDay(tjrq);
			 if ("2".equals(sjlx)) {
			 	end = DateUtil.getYEndDay(tjrq);
			 }
		 }else if ("6".equals(lx) || "12".equals(lx)) {
			 //累计 小于等于统计日期
		 }else if ("13".equals(lx)) {
			 //年 大于等于年初小于等于统计日期
			 start = DateUtil.getYBeginDay(tjrq);
			 end = DateUtil.getYEndDay(tjrq);
		 }
		 if (start != null) {
			 queryWrapper.ge("tjrq", start);
		 }
		 queryWrapper.le("tjrq", end);
		 return queryWrapper;
	 }


	 private QueryWrapper<VNhzfsjmx> initExportQueryWrapper(VNhzfsjmx zfsjmx, String lx, String zzbz, String wgbh, String hzfs, String sjlx) {
		 QueryWrapper<VNhzfsjmx> queryWrapper = new QueryWrapper<>();
		 if (StringUtils.isNotEmpty(zzbz)) {
			 String tjrqStr = DateUtil.format(zfsjmx.getTjrq(), "yyyyMMdd");
			 //支行查询
			 if ("1".equals(hzfs)) {
				 //根据客户所在支行汇总
				 String ywjgdm = sysDictService.queryTableDictTextByKey("hr_bas_organization", "ywjgdm", "zzbz", zzbz);
				 queryWrapper.eq("jgdm", ywjgdm);
			 } else if ("2".equals(hzfs)) {
				 //根据客户经理所在支行汇总
//				 String sql = "select yggh from hr_bas_staff_post where zzbz=" + zzbz + " and rgrq<=to_date(" + tjrqStr + ",'yyyymmdd') " +
//						 "and (lgrq is null or  lgrq>=to_date(" + tjrqStr + ",'yyyymmdd'))";
//				 queryWrapper.inSql("yggh", sql);

				 queryWrapper.exists("select 1 from hr_bas_staff_post t2 where tjfx_zfsjmx_nh.yggh=t2.yggh  " +
						 "and t2.zzbz=" + zzbz + " and " +
						 "tjfx_zfsjmx_nh.tjrq>=t2.rgrq and (t2.lgrq>=tjfx_zfsjmx_nh.tjrq or t2.lgrq is null)");
			 }
		 }
		 if (StringUtils.isNotEmpty(wgbh)) {
			 //网格查询同时查询所有下级的数据
			 queryWrapper.inSql("wgbh", "select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id ");
		 }

		 Date tjrq = zfsjmx.getTjrq();
		 Date start = null;
		 Date end = tjrq;

		 //根据不同类型判断查询开始日期
		 if ("1".equals(lx) || "7".equals(lx)) {
			 //日 等于统计日期
			 start = tjrq;
		 } else if ("2".equals(lx) || "8".equals(lx)) {
			 //周 大于等于周初小于等于统计日期
			 start = cn.hutool.core.date.DateUtil.beginOfWeek(tjrq);
			 if ("2".equals(sjlx)) {
				 end = cn.hutool.core.date.DateUtil.endOfWeek(tjrq);
			 }
		 } else if ("3".equals(lx) || "9".equals(lx)) {
			 //月 大于等于月初小于等于统计日期
			 start = DateUtil.getMonthBeginDay(tjrq);
			 if ("2".equals(sjlx)) {
				 end = DateUtil.getMonthEndDay(tjrq);
			 }
		 }else if ("4".equals(lx) || "10".equals(lx)) {
			 //季 大于等于季初小于等于统计日期
			 start = DateUtil.getQBeginDay(tjrq);
			 if ("2".equals(sjlx)) {
				 end = DateUtil.getQEndDay(tjrq);
			 }
		 }else if ("5".equals(lx) || "11".equals(lx)) {
			 //年 大于等于年初小于等于统计日期
			 start = DateUtil.getYBeginDay(tjrq);
			 if ("2".equals(sjlx)) {
				 end = DateUtil.getYEndDay(tjrq);
			 }
		 }else if ("6".equals(lx) || "12".equals(lx)) {
			 //累计 小于等于统计日期
		 }
		 if (start != null) {
			 queryWrapper.ge("tjrq", start);
		 }
		 queryWrapper.le("tjrq", end);
		 return queryWrapper;
	 }


	 /**
	 * 添加
	 *
	 * @param zfsjmx
	 * @return
	 */
	@AutoLog(value = "走访数据明细-添加")
	@ApiOperation(value="走访数据明细-添加", notes="走访数据明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zfsjmx zfsjmx) {
		zfsjmxService.save(zfsjmx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param zfsjmx
	 * @return
	 */
	@AutoLog(value = "走访数据明细-编辑")
	@ApiOperation(value="走访数据明细-编辑", notes="走访数据明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zfsjmx zfsjmx) {
		zfsjmxService.updateById(zfsjmx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "走访数据明细-通过id删除")
	@ApiOperation(value="走访数据明细-通过id删除", notes="走访数据明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zfsjmxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "走访数据明细-批量删除")
	@ApiOperation(value="走访数据明细-批量删除", notes="走访数据明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zfsjmxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "走访数据明细-通过id查询")
	@ApiOperation(value="走访数据明细-通过id查询", notes="走访数据明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zfsjmx zfsjmx = zfsjmxService.getById(id);
		return Result.ok(zfsjmx);
	}

	 /**
	  * 根据户号编码查询有效走访信息
	  *
	  * @param hhbm
	  * @return
	  */
	 @ApiOperation(value="走访数据明细-根据户号编码查询有效走访信息", notes="走访数据明细-根据户号编码查询有效走访信息")
	 @GetMapping(value = "/queryYxzfByHhbm")
	 public Result<?> queryYxzfByHhbm(@RequestParam(name="hhbm",required=true) String hhbm) {
	 	 QueryWrapper<Zfsjmx> queryWrapper = new QueryWrapper<>();
	 	 queryWrapper.eq("hhbm", hhbm);
	 	 queryWrapper.eq("sfyxzf", "1");
	 	 queryWrapper.orderByAsc("tjrq");
		 List<Zfsjmx> zfsjmxList = zfsjmxService.list(queryWrapper);
		 if (!zfsjmxList.isEmpty()) {
			 return Result.ok(zfsjmxList.get(0));
		 }
		 return Result.ok("未找到有效走访信息");
	 }

  /**
   * 导出excel
   *
   * @param request
   * @param zfsjmx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zfsjmx zfsjmx) {
      return super.exportXls(request, zfsjmx, Zfsjmx.class, "走访数据明细");
  }

	 @RequestMapping(value = "/exportZfsjmxXls")
	 public ModelAndView exportZfsjmxXls(VNhzfsjmx zfsjmx, String lx, String zzbz, String wgbh, String hzfs, String sjlx,
								   HttpServletRequest request) {
		 // Step.1 组装查询条件
		 QueryWrapper<VNhzfsjmx> queryWrapper = this.initQueryWrapper(zfsjmx, lx, zzbz, wgbh, hzfs, sjlx);
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

		 // Step.2 获取导出数据
		 //List<T> pageList = service.list(queryWrapper);
		 //List<T> exportList = null;
		 List<VNhzfsjmx> zfsjmxList = vNhzfsjmxService.list(queryWrapper);
		 List<ZfsjmxExp> exportList = new ArrayList<>();
		 zfsjmxList.forEach(e -> {
		 	 ZfsjmxExp zfsjmxExp = new ZfsjmxExp();
			 BeanUtil.copyProperties(e, zfsjmxExp);
			 exportList.add(zfsjmxExp);
		 });

		 String title = "走访数据明细";
		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, ZfsjmxExp.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
	 }


	 @RequestMapping(value = "/exportZfsjmxQlXls")
	 public ModelAndView exportZfsjmxQlXls(VNhzfsjmx zfsjmx, String lx, String zzbz, String wgbh, String hzfs, String sjlx,
										 HttpServletRequest request) {
		 // Step.1 组装查询条件
		 QueryWrapper<VNhzfsjmx> queryWrapper = this.initExportQueryWrapper(zfsjmx, lx, zzbz, wgbh, hzfs, sjlx);
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 queryWrapper.orderByDesc("tjrq");
		 List<VNhzfsjmx> zfsjmxList = vNhzfsjmxService.list(queryWrapper);
		 List<ZfsjmxQlExp> exportList = new ArrayList<>();
		 zfsjmxList.forEach(e -> {
			 ZfsjmxQlExp zfsjmxExp = new ZfsjmxQlExp();
			 BeanUtil.copyProperties(e, zfsjmxExp);
			 exportList.add(zfsjmxExp);
		 });
		 String title = "走访数据明细";
		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, ZfsjmxQlExp.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
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
      return super.importExcel(request, response, Zfsjmx.class);
  }

}
