package org.cmms.modules.tjbb.tjfz.sgtzdr.csgl.cssz.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.tjbb.tjfz.sgtzdr.csgl.cssz.entity.TjbbCsglCssz;
import org.cmms.modules.tjbb.tjfz.sgtzdr.csgl.cssz.service.ITjbbCsglCsszService;
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
 * @Description: 参数设置
 * @Author: jeecg-boot
 * @Date:   2023-01-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="参数设置")
@RestController
@RequestMapping("/tjbb/tjfz/csgl/cssz/tjbbCsglCssz")
public class TjbbCsglCsszController extends JeecgController<TjbbCsglCssz, ITjbbCsglCsszService> {
	@Autowired
	private ITjbbCsglCsszService tjbbCsglCsszService;
	
	/**
	 * 分页列表查询
	 *
	 * @param tjbbCsglCssz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "参数设置-分页列表查询")
	@ApiOperation(value="参数设置-分页列表查询", notes="参数设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TjbbCsglCssz tjbbCsglCssz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TjbbCsglCssz> queryWrapper = QueryGenerator.initQueryWrapper(tjbbCsglCssz, req.getParameterMap());
		Page<TjbbCsglCssz> page = new Page<TjbbCsglCssz>(pageNo, pageSize);
		IPage<TjbbCsglCssz> pageList = PageUtil.toPage(ITjbbCsglCsszService.class, tjbbCsglCsszService, pageNo, pageSize, queryWrapper, "csbm");
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param tjbbCsglCssz
	 * @return
	 */
	@AutoLog(value = "参数设置-添加")
	@ApiOperation(value="参数设置-添加", notes="参数设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TjbbCsglCssz tjbbCsglCssz) {
		tjbbCsglCssz.setLrbz(1);
		tjbbCsglCssz.setLrr(getLoginUser().getUsername());
		tjbbCsglCssz.setLrsj(new Date());
		tjbbCsglCsszService.save(tjbbCsglCssz);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param tjbbCsglCssz
	 * @return
	 */
	@AutoLog(value = "参数设置-编辑")
	@ApiOperation(value="参数设置-编辑", notes="参数设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TjbbCsglCssz tjbbCsglCssz) {
		QueryWrapper<TjbbCsglCssz> wrapper=new QueryWrapper<>();
		wrapper.eq("csbm",tjbbCsglCssz.getCsbm());
		tjbbCsglCssz.setCsbm(null);
		tjbbCsglCsszService.update(tjbbCsglCssz,wrapper);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过csbm删除
	 *
	 * @param csbm
	 * @return
	 */
	@AutoLog(value = "参数设置-通过csbm删除")
	@ApiOperation(value="参数设置-通过id删除", notes="参数设置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="csbm",required=true) String csbm) {
		QueryWrapper<TjbbCsglCssz> wrapper=new QueryWrapper<>();
		wrapper.eq("csbm",csbm);
		tjbbCsglCsszService.remove(wrapper);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param csbms
	 * @return
	 */
	@AutoLog(value = "参数设置-批量删除")
	@ApiOperation(value="参数设置-批量删除", notes="参数设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="csbms",required=true) String csbms) {
		QueryWrapper<TjbbCsglCssz> wrapper=new QueryWrapper<>();
		wrapper.in("csbm",Arrays.asList(csbms.split(",")));
		this.tjbbCsglCsszService.remove(wrapper);
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "参数设置-通过id查询")
	@ApiOperation(value="参数设置-通过id查询", notes="参数设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TjbbCsglCssz tjbbCsglCssz = tjbbCsglCsszService.getById(id);
		return Result.ok(tjbbCsglCssz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param tjbbCsglCssz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, TjbbCsglCssz tjbbCsglCssz) {
      return super.exportXls(request, tjbbCsglCssz, TjbbCsglCssz.class, "参数设置");
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
      return super.importExcel(request, response, TjbbCsglCssz.class);
  }

}
