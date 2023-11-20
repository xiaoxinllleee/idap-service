package org.cmms.modules.tjfx.zfsjtj.shzfsjmx.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.tjfx.zfsjtj.shzfsjmx.entity.Shzfsjmx;
import org.cmms.modules.tjfx.zfsjtj.shzfsjmx.service.IShzfsjmxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.zfsjtj.shzfsjmx.vo.ShzfsjmxExp;
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
 * @Description: 商户走访数据明细
 * @Author: jeecg-boot
 * @Date:   2022-06-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="商户走访数据明细")
@RestController
@RequestMapping("/shzfsjmx/shzfsjmx")
public class ShzfsjmxController extends JeecgController<Shzfsjmx, IShzfsjmxService> {
	@Autowired
	private IShzfsjmxService shzfsjmxService;
	 @Autowired
	 private ISysDictService sysDictService;

	/**
	 * 分页列表查询
	 *
	 * @param shzfsjmx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "商户走访数据明细-分页列表查询")
	@ApiOperation(value="商户走访数据明细-分页列表查询", notes="商户走访数据明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Shzfsjmx shzfsjmx,String lx, String zzbz, String wgbh, String hzfs, String sjlx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Shzfsjmx> queryWrapper = this.initQueryWrapper(shzfsjmx, lx, zzbz, wgbh, hzfs, sjlx);
		Page<Shzfsjmx> page = new Page<Shzfsjmx>(pageNo, pageSize);
		IPage<Shzfsjmx> pageList = shzfsjmxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 private QueryWrapper<Shzfsjmx> initQueryWrapper(Shzfsjmx shzfsjmx, String lx, String zzbz, String wgbh, String hzfs, String sjlx) {
		 QueryWrapper<Shzfsjmx> queryWrapper = new QueryWrapper<>();
		 if (StringUtils.isNotEmpty(shzfsjmx.getYggh())) {
			 //个人查询
			 queryWrapper.eq("yggh", shzfsjmx.getYggh());
		 }
		 if (StringUtils.isNotEmpty(zzbz)) {
			 String tjrqStr = DateUtil.format(shzfsjmx.getTjrq(), "yyyyMMdd");
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

				 queryWrapper.exists("select 1 from hr_bas_staff_post t2 where tjfx_zfsjmx_sh.yggh=t2.yggh  " +
						 "and t2.zzbz=" + zzbz + " and " +
						 "tjfx_zfsjmx_sh.tjrq>=t2.rgrq and (t2.lgrq>=tjfx_zfsjmx_sh.tjrq or t2.lgrq is null)");
			 }
		 }
		 if (StringUtils.isNotEmpty(wgbh)) {
			// queryWrapper.eq("wgbh", wgbh);
			 //网格查询同时查询所有下级的数据
			 queryWrapper.inSql("wgbh", "select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id ");
		 }
		 Date tjrq = shzfsjmx.getTjrq();
		 Date start = null;
		 Date end = tjrq;
		 Integer integer = Integer.valueOf(lx);
		 if (integer <= 6){
			 queryWrapper.eq("sfyxtj","1");
		 }else {
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
	/**
	 * 添加
	 *
	 * @param shzfsjmx
	 * @return
	 */
	@AutoLog(value = "商户走访数据明细-添加")
	@ApiOperation(value="商户走访数据明细-添加", notes="商户走访数据明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Shzfsjmx shzfsjmx) {
		shzfsjmxService.save(shzfsjmx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param shzfsjmx
	 * @return
	 */
	@AutoLog(value = "商户走访数据明细-编辑")
	@ApiOperation(value="商户走访数据明细-编辑", notes="商户走访数据明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Shzfsjmx shzfsjmx) {
		shzfsjmxService.updateById(shzfsjmx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商户走访数据明细-通过id删除")
	@ApiOperation(value="商户走访数据明细-通过id删除", notes="商户走访数据明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		shzfsjmxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "商户走访数据明细-批量删除")
	@ApiOperation(value="商户走访数据明细-批量删除", notes="商户走访数据明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.shzfsjmxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商户走访数据明细-通过id查询")
	@ApiOperation(value="商户走访数据明细-通过id查询", notes="商户走访数据明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Shzfsjmx shzfsjmx = shzfsjmxService.getById(id);
		return Result.ok(shzfsjmx);
	}

	 /**
	  * 根据商户ID查询有效走访信息
	  *
	  * @param shid
	  * @return
	  */
	 @ApiOperation(value="走访数据明细-根据商户ID查询有效走访信息", notes="走访数据明细-根据商户ID查询有效走访信息")
	 @GetMapping(value = "/queryYxzfByShid")
	 public Result<?> queryYxzfByShid(@RequestParam(name="shid",required=true) String shid) {
		 QueryWrapper<Shzfsjmx> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("shid", shid);
		 queryWrapper.eq("sfyxzf", "1");
		 queryWrapper.orderByAsc("tjrq");
		 List<Shzfsjmx> zfsjmxList = shzfsjmxService.list(queryWrapper);
		 if (!zfsjmxList.isEmpty()) {
			 return Result.ok(zfsjmxList.get(0));
		 }
		 return Result.ok("未找到有效走访信息");
	 }

  /**
   * 导出excel
   *
   * @param request
   * @param shzfsjmx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Shzfsjmx shzfsjmx) {
      return super.exportXls(request, shzfsjmx, Shzfsjmx.class, "商户走访数据明细");
  }

	 @RequestMapping(value = "/exportZfsjmxXls")
	 public ModelAndView exportZfsjmxXls(Shzfsjmx shzfsjmx, String lx, String zzbz, String wgbh, String hzfs, String sjlx,
										 HttpServletRequest request) {
		 // Step.1 组装查询条件
		 QueryWrapper<Shzfsjmx> queryWrapper = this.initQueryWrapper(shzfsjmx, lx, zzbz, wgbh, hzfs, sjlx);
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
		 List<Shzfsjmx> shzfsjmxList = service.list(queryWrapper);
		 List<ShzfsjmxExp> exportList = new ArrayList<>();
		 shzfsjmxList.forEach(e -> {
			 ShzfsjmxExp shzfsjmxExp = new ShzfsjmxExp();
			 BeanUtil.copyProperties(e, shzfsjmxExp);
			 exportList.add(shzfsjmxExp);
		 });

		 String title = "商户走访数据明细";
		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, ShzfsjmxExp.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
	 }

	 @RequestMapping(value = "/exportZfsjmxQlXls")
	 public ModelAndView exportZfsjmxQlXls(Shzfsjmx zfsjmx, String lx, String zzbz, String wgbh, String hzfs, String sjlx,
										   HttpServletRequest request) {
		 // Step.1 组装查询条件
		 QueryWrapper<Shzfsjmx> queryWrapper = this.initExportQueryWrapper(zfsjmx, lx, zzbz, wgbh, hzfs, sjlx);
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 queryWrapper.orderByDesc("tjrq");
		 List<Shzfsjmx> zfsjmxList = service.list(queryWrapper);
		 List<ShzfsjmxExp> exportList = new ArrayList<>();
		 zfsjmxList.forEach(e -> {
			 ShzfsjmxExp zfsjmxExp = new ShzfsjmxExp();
			 BeanUtil.copyProperties(e, zfsjmxExp);
			 exportList.add(zfsjmxExp);
		 });
		 String title = "走访数据明细";
		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, ShzfsjmxExp.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
	 }

	 private QueryWrapper<Shzfsjmx> initExportQueryWrapper(Shzfsjmx shzfsjmx, String lx, String zzbz, String wgbh, String hzfs, String sjlx) {
		 QueryWrapper<Shzfsjmx> queryWrapper = new QueryWrapper<>();
		 if (StringUtils.isNotEmpty(shzfsjmx.getYggh())) {
			 //个人查询
			 queryWrapper.eq("yggh", shzfsjmx.getYggh());
		 }
		 if (StringUtils.isNotEmpty(zzbz)) {
			 String tjrqStr = DateUtil.format(shzfsjmx.getTjrq(), "yyyyMMdd");
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

				 queryWrapper.exists("select 1 from hr_bas_staff_post t2 where tjfx_zfsjmx_sh.yggh=t2.yggh  " +
						 "and t2.zzbz=" + zzbz + " and " +
						 "tjfx_zfsjmx_sh.tjrq>=t2.rgrq and (t2.lgrq>=tjfx_zfsjmx_sh.tjrq or t2.lgrq is null)");
			 }
		 }
		 if (StringUtils.isNotEmpty(wgbh)) {
			 // queryWrapper.eq("wgbh", wgbh);
			 //网格查询同时查询所有下级的数据
			 queryWrapper.inSql("wgbh", "select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id ");
		 }
		 Date tjrq = shzfsjmx.getTjrq();
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
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, Shzfsjmx.class);
  }

	 //得到指定日期当月的第一天的日期
	 protected String getMinDateByDate(Date date) {
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(date);
		 int year = calendar.get(Calendar.YEAR);
		 String month = (calendar.get(Calendar.MONTH) + 1) < 10 ? "0" + (calendar.get(Calendar.MONTH) + 1) : (calendar.get(Calendar.MONTH) + 1) + "";
		 int minDay = calendar.getActualMinimum(Calendar.DATE);
		 String backMinDate = year + "-" + month + "-" + minDay;
		 return backMinDate;
	 }
	 //得到指定日期当月的最后一天的日期
	 protected String getMaxDateByDate(Date date){
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(date);
		 int year            = calendar.get(Calendar.YEAR);
		 String month        = (calendar.get(Calendar.MONTH)+1) < 10?"0"+(calendar.get(Calendar.MONTH)+1):(calendar.get(Calendar.MONTH)+1)+"";
		 int maxDay          = calendar.getActualMaximum(Calendar.DATE);
		 String backMaxDate  = year+"-"+month+"-"+maxDay;
		 return backMaxDate;
	 }

	 /**
	  * 得到本年第一天的日期
	  * @param date
	  * @return
	  */
	 public static String getFirstDayOfYear(Date date) {
		 Calendar cDay = Calendar.getInstance();
		 cDay.setTime(date);
		 int curMonth = cDay.get(Calendar.MONTH);
		 if (curMonth >= Calendar.JANUARY && curMonth <= Calendar.DECEMBER){
			 cDay.set(Calendar.MONTH, Calendar.JANUARY);
		 }
		 cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMinimum(Calendar.DAY_OF_MONTH));
		 SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
		 String getTime = formatter.format(cDay.getTime().getTime());
		 System.out.println(getTime);
		 return getTime;
	 }
	 /**
	  * 得到本年最后一天的日期
	  */
	 public static String getLastDayOfYear(Date date) {
		 Calendar cDay = Calendar.getInstance();
		 cDay.setTime(date);
		 int curMonth = cDay.get(Calendar.MONTH);
		 if (curMonth >= Calendar.JANUARY && curMonth <= Calendar.DECEMBER) {
			 cDay.set(Calendar.MONTH, Calendar.DECEMBER);
		 }
		 cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));
		 SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
		 String getTime = formatter.format(cDay.getTime().getTime());
		 System.out.println(getTime);
		 return getTime;
	 }
	 /**
	  * 本季度第一天
	  * @param date
	  * @return
	  */
	 public static String getFirstDayOfQuarter(Date date) {
		 Calendar cDay = Calendar.getInstance();
		 cDay.setTime(date);
		 int curMonth = cDay.get(Calendar.MONTH);
		 if (curMonth >= Calendar.JANUARY && curMonth <= Calendar.MARCH) {
			 cDay.set(Calendar.MONTH, Calendar.JANUARY);
		 }
		 if (curMonth >= Calendar.APRIL && curMonth <= Calendar.JUNE) {
			 cDay.set(Calendar.MONTH, Calendar.APRIL);
		 }
		 if (curMonth >= Calendar.JULY && curMonth <= Calendar.SEPTEMBER) {
			 cDay.set(Calendar.MONTH, Calendar.JULY);
		 }
		 if (curMonth >= Calendar.OCTOBER && curMonth <= Calendar.DECEMBER) {
			 cDay.set(Calendar.MONTH, Calendar.OCTOBER);
		 }
		 cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMinimum(Calendar.DAY_OF_MONTH));
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 String getTime = formatter.format(cDay.getTime().getTime());
		 System.out.println(getTime);
		 return getTime;
	 }

	 /**
	  * 本季度最后一天
	  * @param date
	  * @return
	  */
	 public static String getLastDayOfQuarter(Date date) {
		 Calendar cDay = Calendar.getInstance();
		 cDay.setTime(date);
		 int curMonth = cDay.get(Calendar.MONTH);
		 if (curMonth >= Calendar.JANUARY && curMonth <= Calendar.MARCH){
			 cDay.set(Calendar.MONTH, Calendar.MARCH);
		 }
		 if (curMonth >= Calendar.APRIL && curMonth <= Calendar.JUNE){
			 cDay.set(Calendar.MONTH, Calendar.JUNE);
		 }
		 if (curMonth >= Calendar.JULY && curMonth <= Calendar.SEPTEMBER) {
			 cDay.set(Calendar.MONTH, Calendar.SEPTEMBER);
		 }
		 if (curMonth >= Calendar.OCTOBER && curMonth <= Calendar.DECEMBER) {
			 cDay.set(Calendar.MONTH, Calendar.DECEMBER);
		 }
		 cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));
		 SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
		 String getTime = formatter.format(cDay.getTime().getTime());
		 System.out.println(getTime);
		 return getTime;
	 }
	 /**
	  * 获取一周的日期
	  * @param date
	  * @return
	  */
	 public static List<String> getWeekByDate(Date date) {
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 List<String> dateList = new ArrayList<String>();
		 Calendar calendar = Calendar.getInstance();
		 if (date != null) {
			 calendar.setTime(date);
		 }
		 calendar.setFirstDayOfWeek(Calendar.MONDAY);
		 calendar.set(Calendar.HOUR_OF_DAY, 0); //小时
		 calendar.set(Calendar.MINUTE, 0); //分钟
		 calendar.set(Calendar.SECOND, 0); //秒
		 calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		 for (int i = 0; i < 7; i++) {
			 dateList.add(format.format(calendar.getTime()));
			 calendar.add(Calendar.DAY_OF_MONTH, 1);
		 }
		 return dateList;
	 }

}
