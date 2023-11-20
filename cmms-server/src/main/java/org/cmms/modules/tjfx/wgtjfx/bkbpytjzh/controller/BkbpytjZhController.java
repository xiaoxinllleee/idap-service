package org.cmms.modules.tjfx.wgtjfx.bkbpytjzh.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.tjfx.wgtjfx.bkbpytjzh.entity.BkbpytjZh;
import org.cmms.modules.tjfx.wgtjfx.bkbpytjzh.service.IBkbpytjZhService;
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
 * @Description: 背靠背评议统计_支行
 * @Author: jeecg-boot
 * @Date:   2022-05-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="背靠背评议统计_支行")
@RestController
@RequestMapping("/wgtjfx/bkbpytjZh")
public class BkbpytjZhController extends JeecgController<BkbpytjZh, IBkbpytjZhService> {
	@Autowired
	private IBkbpytjZhService bkbpytjZhService;
	 @Autowired
	 private ISysDictService iSysDictService;
	/**
	 * 分页列表查询
	 *
	 * @param bkbpytjZh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "背靠背评议统计_支行-分页列表查询")
	@ApiOperation(value="背靠背评议统计_支行-分页列表查询", notes="背靠背评议统计_支行-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BkbpytjZh bkbpytjZh,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   @RequestParam(name="sszhStr", required = false) String sszhStr,
								   HttpServletRequest req) {
		SqlInjectionUtil.filterContent(req);
		QueryWrapper<BkbpytjZh> queryWrapper = QueryGenerator.initQueryWrapper(bkbpytjZh, req.getParameterMap());
		Page<BkbpytjZh> page = new Page<BkbpytjZh>(pageNo, pageSize);
		Date tjrq = bkbpytjZhService.getMaxTjrq();
		if (tjrq != null) {
			queryWrapper.eq("tjrq", tjrq);
		}
		if (StringUtils.isNotEmpty(sszhStr)) {
			queryWrapper.in("sszh", Arrays.asList(sszhStr.split(",")));
		}
		IPage<BkbpytjZh> pageList = bkbpytjZhService.page(page, queryWrapper);
		List<BkbpytjZh> list = pageList.getRecords();
		list.forEach(item -> {
			item.setJgdm(iSysDictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION","YWJGDM","ZZBZ",item.getSszh()));
		});
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param bkbpytjZh
	 * @return
	 */
	@AutoLog(value = "背靠背评议统计_支行-添加")
	@ApiOperation(value="背靠背评议统计_支行-添加", notes="背靠背评议统计_支行-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BkbpytjZh bkbpytjZh) {
		bkbpytjZhService.save(bkbpytjZh);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param bkbpytjZh
	 * @return
	 */
	@AutoLog(value = "背靠背评议统计_支行-编辑")
	@ApiOperation(value="背靠背评议统计_支行-编辑", notes="背靠背评议统计_支行-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BkbpytjZh bkbpytjZh) {
		bkbpytjZhService.updateById(bkbpytjZh);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "背靠背评议统计_支行-通过id删除")
	@ApiOperation(value="背靠背评议统计_支行-通过id删除", notes="背靠背评议统计_支行-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		bkbpytjZhService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "背靠背评议统计_支行-批量删除")
	@ApiOperation(value="背靠背评议统计_支行-批量删除", notes="背靠背评议统计_支行-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.bkbpytjZhService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "背靠背评议统计_支行-通过id查询")
	@ApiOperation(value="背靠背评议统计_支行-通过id查询", notes="背靠背评议统计_支行-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BkbpytjZh bkbpytjZh = bkbpytjZhService.getById(id);
		return Result.ok(bkbpytjZh);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param bkbpytjZh
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, BkbpytjZh bkbpytjZh) {
  	  String title = "背靠背评议统计_支行";
	  // Step.1 组装查询条件
	  QueryWrapper<BkbpytjZh> queryWrapper = QueryGenerator.initQueryWrapper(bkbpytjZh, request.getParameterMap());
	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	  String selections = request.getParameter("selections");
	  String rowKey = request.getParameter("rowKey");
	  Date tjrq = bkbpytjZhService.getMaxTjrq();
	  if (tjrq != null) {
		  queryWrapper.eq("tjrq", tjrq);
	  }
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
	  List<BkbpytjZh> exportList = service.list(queryWrapper);

	  // Step.3 AutoPoi 导出Excel
	  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
	  mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
	  mv.addObject(NormalExcelConstants.CLASS, BkbpytjZh.class);
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
      return super.importExcel(request, response, BkbpytjZh.class);
  }

}
