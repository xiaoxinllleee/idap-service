package org.cmms.modules.khgl.nh.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khgl.nh.entity.CamsPlpyYsxx;
import org.cmms.modules.khgl.nh.service.ICamsPlpyYsxxService;
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
 * @Description: 批量评议验收信息
 * @Author: jeecg-boot
 * @Date:   2022-04-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="批量评议验收信息")
@RestController
@RequestMapping("/nh/camsPlpyYsxx")
public class CamsPlpyYsxxController extends JeecgController<CamsPlpyYsxx, ICamsPlpyYsxxService> {
	@Autowired
	private ICamsPlpyYsxxService camsPlpyYsxxService;
	
	/**
	 * 分页列表查询
	 *
	 * @param camsPlpyYsxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "批量评议验收信息-分页列表查询")
	@ApiOperation(value="批量评议验收信息-分页列表查询", notes="批量评议验收信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CamsPlpyYsxx camsPlpyYsxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CamsPlpyYsxx> queryWrapper = QueryGenerator.initQueryWrapper(camsPlpyYsxx, req.getParameterMap());
		Page<CamsPlpyYsxx> page = new Page<CamsPlpyYsxx>(pageNo, pageSize);
		IPage<CamsPlpyYsxx> pageList = camsPlpyYsxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param camsPlpyYsxx
	 * @return
	 */
	@AutoLog(value = "批量评议验收信息-添加")
	@ApiOperation(value="批量评议验收信息-添加", notes="批量评议验收信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CamsPlpyYsxx camsPlpyYsxx) {
		camsPlpyYsxxService.save(camsPlpyYsxx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param camsPlpyYsxx
	 * @return
	 */
	@AutoLog(value = "批量评议验收信息-编辑")
	@ApiOperation(value="批量评议验收信息-编辑", notes="批量评议验收信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CamsPlpyYsxx camsPlpyYsxx) {
		camsPlpyYsxxService.updateById(camsPlpyYsxx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "批量评议验收信息-通过id删除")
	@ApiOperation(value="批量评议验收信息-通过id删除", notes="批量评议验收信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		camsPlpyYsxxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "批量评议验收信息-批量删除")
	@ApiOperation(value="批量评议验收信息-批量删除", notes="批量评议验收信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.camsPlpyYsxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "批量评议验收信息-通过id查询")
	@ApiOperation(value="批量评议验收信息-通过id查询", notes="批量评议验收信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CamsPlpyYsxx camsPlpyYsxx = camsPlpyYsxxService.getById(id);
		return Result.ok(camsPlpyYsxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param camsPlpyYsxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, CamsPlpyYsxx camsPlpyYsxx) {
      return super.exportXls(request, camsPlpyYsxx, CamsPlpyYsxx.class, "批量评议验收信息");
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
      return super.importExcel(request, response, CamsPlpyYsxx.class);
  }

  //获取网格验收轮数
  @RequestMapping("/getYsls")
  public Result<?> getYsls(String wgbh){
	  QueryWrapper queryWrapper = new QueryWrapper();
	  queryWrapper.eq("wgbh",wgbh);
	  List list = service.list(queryWrapper);
	  if (CollUtil.isNotEmpty(list))
		  return Result.ok(list.size()+1);
	  return Result.ok(1);

  }
}
