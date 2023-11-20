package org.cmms.modules.tjfx.zfsjtj.sxyxtj.nhsxmx.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.tjfx.zfsjtj.sxyxtj.nhsxmx.entity.Nhsxmx;
import org.cmms.modules.tjfx.zfsjtj.sxyxtj.nhsxmx.service.INhsxmxService;
import org.cmms.modules.tjfx.zfsjtj.sxyxtj.nhsxmx.vo.NhsxmxExp;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description: 农户授信明细
 * @Author: jeecg-boot
 * @Date:   2022-09-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="农户授信明细")
@RestController
@RequestMapping("/sxyxtj/nhsxmx")
public class NhsxmxController extends JeecgController<Nhsxmx, INhsxmxService> {
	@Autowired
	private INhsxmxService nhsxmxService;
	 @Autowired
	 private ISysDictService sysDictService;
	
	/**
	 * 分页列表查询
	 *
	 * @param nhsxmx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "农户授信明细-分页列表查询")
	@ApiOperation(value="农户授信明细-分页列表查询", notes="农户授信明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Nhsxmx nhsxmx, String lx, String zzbz, String wgbh,String sxlx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Nhsxmx> queryWrapper = this.initQueryWrapper(nhsxmx, lx, zzbz, wgbh, sxlx);

		Page<Nhsxmx> page = new Page<Nhsxmx>(pageNo, pageSize);
		IPage<Nhsxmx> pageList = nhsxmxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 private QueryWrapper<Nhsxmx> initQueryWrapper(Nhsxmx nhsxmx, String lx, String zzbz, String wgbh, String sxlx) {
		 QueryWrapper<Nhsxmx> queryWrapper = new QueryWrapper<>();
		 if (StringUtils.isNotEmpty(zzbz)) {
			 String ywjgdm = sysDictService.queryTableDictTextByKey("hr_bas_organization", "ywjgdm", "zzbz", zzbz);
			 queryWrapper.eq("jgdm", ywjgdm);
		 }
		 if (StringUtils.isNotEmpty(wgbh)) {
			 queryWrapper.eq("wgbh", wgbh);
		 }
		 if ("1".equals(sxlx)) {
		 	//查询全部
		 } else if ("2".equals(sxlx)) {
		 	//存量客户
			 queryWrapper.eq("xlkhbz", "2");
		 } else if ("3".equals(sxlx)) {
		 	//新客户
			 queryWrapper.eq("xlkhbz", "1");
		 } else if ("4".equals(sxlx)) {
			 //惠农快贷
			 queryWrapper.eq("sfhnkd", "1");
		 }
		 Date tjrq = nhsxmx.getTjrq();
		 Date start = null;
		 Date end = tjrq;
		 if ("1".equals(lx)) {
			 //日 等于统计日期
			 start = tjrq;
		 } else if ("2".equals(lx)) {
			 //周 大于等于周初小于等于统计日期
			 start = cn.hutool.core.date.DateUtil.beginOfWeek(tjrq);
			 end = cn.hutool.core.date.DateUtil.endOfWeek(tjrq);
		 } else if ("3".equals(lx)) {
			 //月 大于等于月初小于等于月末
			 start = DateUtil.getMonthBeginDay(tjrq);
			 end = DateUtil.getMonthEndDay(tjrq);
		 }else if ("4".equals(lx)) {
			 //季 大于等于季初小于等于季末
			 start = DateUtil.getQBeginDay(tjrq);
			 end = DateUtil.getQEndDay(tjrq);
		 }else if ("5".equals(lx)) {
			 //年 大于等于年初小于等于年末
			 start = DateUtil.getYBeginDay(tjrq);
			 end = DateUtil.getYEndDay(tjrq);
		 }else if ("6".equals(lx)) {
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
	 * @param nhsxmx
	 * @return
	 */
	@AutoLog(value = "农户授信明细-添加")
	@ApiOperation(value="农户授信明细-添加", notes="农户授信明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Nhsxmx nhsxmx) {
		nhsxmxService.save(nhsxmx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param nhsxmx
	 * @return
	 */
	@AutoLog(value = "农户授信明细-编辑")
	@ApiOperation(value="农户授信明细-编辑", notes="农户授信明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Nhsxmx nhsxmx) {
		nhsxmxService.updateById(nhsxmx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "农户授信明细-通过id删除")
	@ApiOperation(value="农户授信明细-通过id删除", notes="农户授信明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		nhsxmxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "农户授信明细-批量删除")
	@ApiOperation(value="农户授信明细-批量删除", notes="农户授信明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.nhsxmxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "农户授信明细-通过id查询")
	@ApiOperation(value="农户授信明细-通过id查询", notes="农户授信明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Nhsxmx nhsxmx = nhsxmxService.getById(id);
		return Result.ok(nhsxmx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param nhsxmx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Nhsxmx nhsxmx) {
      return super.exportXls(request, nhsxmx, Nhsxmx.class, "农户授信明细");
  }

	@RequestMapping(value = "/exportNhsxmxXls")
	public ModelAndView exportZfsjmxXls(Nhsxmx nhsxmx, String lx, String zzbz, String wgbh, String sxlx,
										HttpServletRequest request) {
		// Step.1 组装查询条件
		QueryWrapper<Nhsxmx> queryWrapper = this.initQueryWrapper(nhsxmx, lx, zzbz, wgbh, sxlx);
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
		List<Nhsxmx> nhsxmxList = service.list(queryWrapper);
		List<NhsxmxExp> exportList = new ArrayList<>();
		nhsxmxList.forEach(e -> {
			NhsxmxExp nhsxmxExp = new NhsxmxExp();
			BeanUtil.copyProperties(e, nhsxmxExp);
			exportList.add(nhsxmxExp);
		});

		String title = "农户授信明细";
		// Step.3 AutoPoi 导出Excel
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
		mv.addObject(NormalExcelConstants.CLASS, NhsxmxExp.class);
		mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title, "导出人:" + sysUser.getRealname(), title));
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
      return super.importExcel(request, response, Nhsxmx.class);
  }

}
