package org.cmms.modules.ywgl.ckyw.xyckjx.cplxgl.controller;

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
import org.cmms.modules.ywgl.ckyw.xyckjx.cplxgl.entity.Cplxgl;
import org.cmms.modules.ywgl.ckyw.xyckjx.cplxgl.service.ICplxglService;
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
 * @Description: 产品类型管理
 * @Author: jeecg-boot
 * @Date:   2021-10-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="产品类型管理")
@RestController
@RequestMapping("/cplxgl/cplxgl")
public class CplxglController extends JeecgController<Cplxgl, ICplxglService> {
	@Autowired
	private ICplxglService cplxglService;
	
	/**
	 * 分页列表查询
	 *
	 * @param cplxgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "产品类型管理-分页列表查询")
	@ApiOperation(value="产品类型管理-分页列表查询", notes="产品类型管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Cplxgl cplxgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Cplxgl> queryWrapper = QueryGenerator.initQueryWrapper(cplxgl, req.getParameterMap());
		IPage pageList = org.cmms.common.utils.PageUtil.toPage(ICplxglService.class,cplxglService, pageNo, pageSize, queryWrapper, "cpbh");
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param cplxgl
	 * @return
	 */
	@AutoLog(value = "产品类型管理-添加")
	@ApiOperation(value="产品类型管理-添加", notes="产品类型管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Cplxgl cplxgl) {
		cplxgl.setLrbz(1);
		cplxglService.save(cplxgl);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param cplxgl
	 * @return
	 */
	@AutoLog(value = "产品类型管理-编辑")
	@ApiOperation(value="产品类型管理-编辑", notes="产品类型管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Cplxgl cplxgl) {
		cplxgl.setLrbz(2);
		QueryWrapper<Cplxgl> queryWrapper = new QueryWrapper<Cplxgl>();
		queryWrapper.eq("cpbh",cplxgl.getCpbh());
		cplxglService.update(cplxgl,queryWrapper);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "产品类型管理-通过id删除")
	@ApiOperation(value="产品类型管理-通过id删除", notes="产品类型管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("cpbh") String cpbh) {
		QueryWrapper<Cplxgl> queryWrapper = new QueryWrapper<Cplxgl>();
		queryWrapper.eq("cpbh",cpbh);
		cplxglService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "产品类型管理-批量删除")
	@ApiOperation(value="产品类型管理-批量删除", notes="产品类型管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.cplxglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "产品类型管理-通过id查询")
	@ApiOperation(value="产品类型管理-通过id查询", notes="产品类型管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Cplxgl cplxgl = cplxglService.getById(id);
		return Result.ok(cplxgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param cplxgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Cplxgl cplxgl) {
      return super.exportXls(request, cplxgl, Cplxgl.class, "产品类型管理");
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
      return super.importExcel(request, response, Cplxgl.class);
  }

}
