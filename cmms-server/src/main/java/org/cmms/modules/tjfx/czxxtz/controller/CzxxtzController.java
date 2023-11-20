package org.cmms.modules.tjfx.czxxtz.controller;

import java.io.*;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.czxxtz.entity.Czxxtz;
import org.cmms.modules.tjfx.czxxtz.service.ICzxxtzService;
import org.cmms.modules.util.WordUtils;
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
 * @Description: 村组信息台账
 * @Author: jeecg-boot
 * @Date:   2022-07-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="村组信息台账")
@RestController
@RequestMapping("/tjfx/czxxtz")
public class CzxxtzController extends JeecgController<Czxxtz, ICzxxtzService> {
	@Autowired
	private ICzxxtzService czxxtzService;
	
	/**
	 * 分页列表查询
	 *
	 * @param czxxtz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "村组信息台账-分页列表查询")
	@ApiOperation(value="村组信息台账-分页列表查询", notes="村组信息台账-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Czxxtz czxxtz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String wgbh = czxxtz.getWgbh();
		czxxtz.setWgbh(null);
		QueryWrapper<Czxxtz> queryWrapper = QueryGenerator.initQueryWrapper(czxxtz, req.getParameterMap());
		//查询网格时，同时查询下级网格的数据
		if (StringUtils.isNotEmpty(wgbh)) {
			String sqlSswg = "select wgbh from yxdygl_main t where wgbh ='" + wgbh + "' or parent_id='" + wgbh + "'";
			queryWrapper.and(i -> i.inSql("wgbh", sqlSswg));
		}
		Page<Czxxtz> page = new Page<Czxxtz>(pageNo, pageSize);
		IPage<Czxxtz> pageList = czxxtzService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param czxxtz
	 * @return
	 */
	@AutoLog(value = "村组信息台账-添加")
	@ApiOperation(value="村组信息台账-添加", notes="村组信息台账-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Czxxtz czxxtz) {
		czxxtzService.save(czxxtz);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param czxxtz
	 * @return
	 */
	@AutoLog(value = "村组信息台账-编辑")
	@ApiOperation(value="村组信息台账-编辑", notes="村组信息台账-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Czxxtz czxxtz) {
		czxxtzService.updateById(czxxtz);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "村组信息台账-通过id删除")
	@ApiOperation(value="村组信息台账-通过id删除", notes="村组信息台账-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		czxxtzService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "村组信息台账-批量删除")
	@ApiOperation(value="村组信息台账-批量删除", notes="村组信息台账-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.czxxtzService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "村组信息台账-通过id查询")
	@ApiOperation(value="村组信息台账-通过id查询", notes="村组信息台账-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Czxxtz czxxtz = czxxtzService.getById(id);
		return Result.ok(czxxtz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param czxxtz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Czxxtz czxxtz) {
      // return super.exportXls(request, czxxtz, Czxxtz.class, "村组信息台账");
	  // Step.1 组装查询条件
	  log.info("村组信息台账导出条件："+czxxtz.getWgbh());
	  String wgbh = czxxtz.getWgbh();
	  czxxtz.setWgbh(null);
	  QueryWrapper<Czxxtz> queryWrapper = QueryGenerator.initQueryWrapper(czxxtz, request.getParameterMap());
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
	  //查询网格时，同时查询下级网格的数据
	  if (StringUtils.isNotEmpty(wgbh)) {
		  String sqlSswg = "select wgbh from yxdygl_main t where wgbh ='" + wgbh + "' or parent_id='" + wgbh + "'";
		  queryWrapper.and(i -> i.inSql("wgbh", sqlSswg));
	  }
	  // Step.2 获取导出数据
	  List<Czxxtz> exportList = service.list(queryWrapper);
	  // Step.3 AutoPoi 导出Excel
	  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
	  mv.addObject(NormalExcelConstants.FILE_NAME, "村组信息台账"); //此处设置的filename无效 ,前端会重更新设置一下
	  mv.addObject(NormalExcelConstants.CLASS, Czxxtz.class);
	  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("村组信息台账" + "报表", "导出人:" + sysUser.getRealname(), "村组信息台账"));
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
      return super.importExcel(request, response, Czxxtz.class);
  }

}
