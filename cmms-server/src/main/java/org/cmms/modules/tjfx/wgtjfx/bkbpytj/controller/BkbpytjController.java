package org.cmms.modules.tjfx.wgtjfx.bkbpytj.controller;

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
import org.cmms.common.util.SqlInjectionUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.wgtjfx.bkbpytj.entity.Bkbpytj;
import org.cmms.modules.tjfx.wgtjfx.bkbpytj.service.IBkbpytjService;
import org.cmms.modules.yxdygl.yxdyglmain.service.IYxdyglMainService;
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
 * @Description: 背靠背评议统计
 * @Author: jeecg-boot
 * @Date:   2022-04-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="背靠背评议统计")
@RestController
@RequestMapping("/wgtjfx/bkbpytj")
public class BkbpytjController extends JeecgController<Bkbpytj, IBkbpytjService> {
	@Autowired
	private IBkbpytjService bkbpytjService;
	@Autowired
	private IYxdyglMainService yxdyglMainService;
	
	/**
	 * 分页列表查询
	 *
	 * @param bkbpytj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "背靠背评议统计-分页列表查询")
	@ApiOperation(value="背靠背评议统计-分页列表查询", notes="背靠背评议统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Bkbpytj bkbpytj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   @RequestParam(name="wgmc", required = false) String wgmc, String wgxz,
								   HttpServletRequest req) {
		SqlInjectionUtil.filterContent(req);
		QueryWrapper<Bkbpytj> queryWrapper = QueryGenerator.initQueryWrapper(bkbpytj, req.getParameterMap());
		if(!getUsername().equals("admin")) {
			/*queryWrapper.inSql("sszh", "select zzbz from hr_bas_organization " +
					"start with zzbz in (select dep_id from sys_user_depart where user_id=" + "'" + getLoginUser().getId() + "') connect by prior zzbz = sjzzbz");*/
			//2023-02-03 by liuxiangqun
			//修改成只要有网格权限就能查询
			String sqlSswg = "select  menu_id from YXDYGL_PQQXGL t where khjl =" + "'" + getUsername() + "'";
			queryWrapper.and(i -> i.inSql("wgbh", sqlSswg));
		}
		if (StringUtils.isNotEmpty(bkbpytj.getWgbh())) {
			String sqlSswg = "select wgbh from yxdygl_main t where wgbh ='" + bkbpytj.getWgbh() + "' or parent_id='" + bkbpytj.getWgbh() + "'";
			queryWrapper.and(i -> i.inSql("wgbh", sqlSswg));
		}
		if (StringUtils.isNotEmpty(wgxz)) {
			queryWrapper.inSql("wgbh", "select wgbh from yxdygl_main where wgxz = '" + wgxz + "'");
		}
		Date tjrq = bkbpytjService.getMaxTjrq();
		if (tjrq != null) {
			queryWrapper.eq("tjrq", tjrq);
		}
		queryWrapper.orderByAsc("sszh");
		Page<Bkbpytj> page = new Page<Bkbpytj>(pageNo, pageSize);
		IPage<Bkbpytj> pageList = bkbpytjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param bkbpytj
	 * @return
	 */
	@AutoLog(value = "背靠背评议统计-添加")
	@ApiOperation(value="背靠背评议统计-添加", notes="背靠背评议统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Bkbpytj bkbpytj) {
		bkbpytjService.save(bkbpytj);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param bkbpytj
	 * @return
	 */
	@AutoLog(value = "背靠背评议统计-编辑")
	@ApiOperation(value="背靠背评议统计-编辑", notes="背靠背评议统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Bkbpytj bkbpytj) {
		bkbpytjService.updateById(bkbpytj);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "背靠背评议统计-通过id删除")
	@ApiOperation(value="背靠背评议统计-通过id删除", notes="背靠背评议统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		bkbpytjService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "背靠背评议统计-批量删除")
	@ApiOperation(value="背靠背评议统计-批量删除", notes="背靠背评议统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.bkbpytjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "背靠背评议统计-通过id查询")
	@ApiOperation(value="背靠背评议统计-通过id查询", notes="背靠背评议统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Bkbpytj bkbpytj = bkbpytjService.getById(id);
		return Result.ok(bkbpytj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param bkbpytj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Bkbpytj bkbpytj, @RequestParam(name="wgmc", required = false) String wgmc) {
  	  String title = "背靠背评议统计";
	  // Step.1 组装查询条件
	  QueryWrapper<Bkbpytj> queryWrapper = QueryGenerator.initQueryWrapper(bkbpytj, request.getParameterMap());
	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	  String selections = request.getParameter("selections");
	  String rowKey = request.getParameter("rowKey");
	  if(!getUsername().equals("admin")) {
		  queryWrapper.inSql("sszh", "select zzbz from hr_bas_organization " +
				  "start with zzbz in (select dep_id from sys_user_depart where user_id=" + "'" + getLoginUser().getId() + "') connect by prior zzbz = sjzzbz");
	  }
	  if (StringUtils.isNotEmpty(wgmc)) {
		  queryWrapper.inSql("wgbh", "select wgbh from yxdygl_main where wgmc like '%" + wgmc + "%'");
	  }
	  Date tjrq = bkbpytjService.getMaxTjrq();
	  queryWrapper.eq("tjrq", tjrq);
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
	  List<Bkbpytj> exportList = service.list(queryWrapper);


	  // Step.3 AutoPoi 导出Excel
	  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
	  mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
	  mv.addObject(NormalExcelConstants.CLASS, Bkbpytj.class);
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
      return super.importExcel(request, response, Bkbpytj.class);
  }

}
