package org.cmms.modules.ywgl.nxt.cssz.controller;

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
import org.cmms.modules.ywgl.nxt.cssz.entity.Shpjcssz;
import org.cmms.modules.ywgl.nxt.cssz.service.IShpjcsszService;
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
 * @Description: 商户评级参数设置
 * @Author: jeecg-boot
 * @Date:   2021-09-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="商户评级参数设置")
@RestController
@RequestMapping("/shpjcssz/shpjcssz")
public class ShpjcsszController extends JeecgController<Shpjcssz, IShpjcsszService> {
	@Autowired
	private IShpjcsszService shpjcsszService;
	
	/**
	 * 分页列表查询
	 *
	 * @param shpjcssz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "商户评级参数设置-分页列表查询")
	@ApiOperation(value="商户评级参数设置-分页列表查询", notes="商户评级参数设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Shpjcssz shpjcssz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Shpjcssz> queryWrapper = QueryGenerator.initQueryWrapper(shpjcssz, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IShpjcsszService.class,shpjcsszService,pageNo,pageSize,queryWrapper,"csbh");
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param shpjcssz
	 * @return
	 */
	@AutoLog(value = "商户评级参数设置-添加")
	@ApiOperation(value="商户评级参数设置-添加", notes="商户评级参数设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Shpjcssz shpjcssz) {
		shpjcsszService.save(shpjcssz);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param shpjcssz
	 * @return
	 */
	@AutoLog(value = "商户评级参数设置-编辑")
	@ApiOperation(value="商户评级参数设置-编辑", notes="商户评级参数设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Shpjcssz shpjcssz) {
		QueryWrapper<Shpjcssz> queryWrapper = new QueryWrapper<Shpjcssz>();
		queryWrapper.eq("csbh",shpjcssz.getCsbh());
		shpjcssz.setCsbh(null);
		shpjcsszService.update(shpjcssz,queryWrapper);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "商户评级参数设置-通过id删除")
	@ApiOperation(value="商户评级参数设置-通过id删除", notes="商户评级参数设置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("csbh")String csbh) {
		QueryWrapper<Shpjcssz> queryWrapper = new QueryWrapper<Shpjcssz>();
		queryWrapper.eq("csbh",csbh);
		shpjcsszService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "商户评级参数设置-批量删除")
	@ApiOperation(value="商户评级参数设置-批量删除", notes="商户评级参数设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.shpjcsszService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商户评级参数设置-通过id查询")
	@ApiOperation(value="商户评级参数设置-通过id查询", notes="商户评级参数设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Shpjcssz shpjcssz = shpjcsszService.getById(id);
		return Result.ok(shpjcssz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param shpjcssz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Shpjcssz shpjcssz) {
      return super.exportXls(request, shpjcssz, Shpjcssz.class, "商户评级参数设置");
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
      return super.importExcel(request, response, Shpjcssz.class);
  }

}
