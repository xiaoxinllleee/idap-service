package org.cmms.modules.ywgl.ckyw.xyckjx.jxgzsz.controller;

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
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.ywgl.ckyw.xyckjx.jxgzsz.entity.Jxgzsz;
import org.cmms.modules.ywgl.ckyw.xyckjx.jxgzsz.service.IJxgzszService;
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
 * @Description: 计息规则设置
 * @Author: jeecg-boot
 * @Date:   2021-10-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="计息规则设置")
@RestController
@RequestMapping("/jxgzsz/jxgzsz")
public class JxgzszController extends JeecgController<Jxgzsz, IJxgzszService> {
	@Autowired
	private IJxgzszService jxgzszService;
	
	/**
	 * 分页列表查询
	 *
	 * @param jxgzsz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "计息规则设置-分页列表查询")
	@ApiOperation(value="计息规则设置-分页列表查询", notes="计息规则设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Jxgzsz jxgzsz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Jxgzsz> queryWrapper = QueryGenerator.initQueryWrapper(jxgzsz, req.getParameterMap());
		IPage pageList = org.cmms.common.utils.PageUtil.toPage(IJxgzszService.class,jxgzszService, pageNo, pageSize, queryWrapper, "gzbh","gzbh");
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param jxgzsz
	 * @return
	 */
	@AutoLog(value = "计息规则设置-添加")
	@ApiOperation(value="计息规则设置-添加", notes="计息规则设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Jxgzsz jxgzsz) {
		jxgzsz.setLrbz(1);
		jxgzszService.save(jxgzsz);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param jxgzsz
	 * @return
	 */
	@AutoLog(value = "计息规则设置-编辑")
	@ApiOperation(value="计息规则设置-编辑", notes="计息规则设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Jxgzsz jxgzsz) {
		jxgzsz.setLrbz(2);
		jxgzszService.updateById(jxgzsz);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "计息规则设置-通过id删除")
	@ApiOperation(value="计息规则设置-通过id删除", notes="计息规则设置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("cpbh")String cpbh,@Param("gzbh")String gzbh) {
		QueryWrapper<Jxgzsz> queryWrapper = new QueryWrapper<Jxgzsz>();
		queryWrapper.eq("cpbh",cpbh);
		queryWrapper.eq("gzbh",gzbh);
		jxgzszService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "计息规则设置-批量删除")
	@ApiOperation(value="计息规则设置-批量删除", notes="计息规则设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.jxgzszService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "计息规则设置-通过id查询")
	@ApiOperation(value="计息规则设置-通过id查询", notes="计息规则设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Jxgzsz jxgzsz = jxgzszService.getById(id);
		return Result.ok(jxgzsz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param jxgzsz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Jxgzsz jxgzsz) {
      return super.exportXls(request, jxgzsz, Jxgzsz.class, "计息规则设置");
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
      return super.importExcel(request, response, Jxgzsz.class);
  }

}
