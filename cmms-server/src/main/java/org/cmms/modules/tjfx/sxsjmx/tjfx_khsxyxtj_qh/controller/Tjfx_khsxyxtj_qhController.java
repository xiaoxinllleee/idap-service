package org.cmms.modules.tjfx.sxsjmx.tjfx_khsxyxtj_qh.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khgl.grkhgl.entity.ZcsxNhcjxx;
import org.cmms.modules.tjfx.sxsjmx.tjfx_khsxyxtj_qh.entity.Tjfx_khsxyxtj_qh;
import org.cmms.modules.tjfx.sxsjmx.tjfx_khsxyxtj_qh.service.ITjfx_khsxyxtj_qhService;
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
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags="1")
@RestController
@RequestMapping("/tjfx_khsxyxtj_qh/tjfx_khsxyxtj_qh")
public class Tjfx_khsxyxtj_qhController extends JeecgController<Tjfx_khsxyxtj_qh, ITjfx_khsxyxtj_qhService> {
	@Autowired
	private ITjfx_khsxyxtj_qhService tjfx_khsxyxtj_qhService;

	/**
	 * 分页列表查询
	 *
	 * @param tjfx_khsxyxtj_qh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "1-分页列表查询")
	@ApiOperation(value="1-分页列表查询", notes="1-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Tjfx_khsxyxtj_qh tjfx_khsxyxtj_qh,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Tjfx_khsxyxtj_qh> queryWrapper = QueryGenerator.initQueryWrapper(tjfx_khsxyxtj_qh, req.getParameterMap());
		Page<Tjfx_khsxyxtj_qh> page = new Page<Tjfx_khsxyxtj_qh>(pageNo, pageSize);
		IPage<Tjfx_khsxyxtj_qh> pageList = tjfx_khsxyxtj_qhService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param tjfx_khsxyxtj_qh
	 * @return
	 */
	@AutoLog(value = "1-添加")
	@ApiOperation(value="1-添加", notes="1-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Tjfx_khsxyxtj_qh tjfx_khsxyxtj_qh) {
		tjfx_khsxyxtj_qhService.save(tjfx_khsxyxtj_qh);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param tjfx_khsxyxtj_qh
	 * @return
	 */
	@AutoLog(value = "1-编辑")
	@ApiOperation(value="1-编辑", notes="1-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Tjfx_khsxyxtj_qh tjfx_khsxyxtj_qh) {
		tjfx_khsxyxtj_qhService.updateById(tjfx_khsxyxtj_qh);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "1-通过id删除")
	@ApiOperation(value="1-通过id删除", notes="1-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tjfx_khsxyxtj_qhService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "1-批量删除")
	@ApiOperation(value="1-批量删除", notes="1-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tjfx_khsxyxtj_qhService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	 /**
	  *   通过tjyf提取
	  * @param tjyf
	  * @return
	  */
	 @RequestMapping(value = "/extract" , method = RequestMethod.PUT)
	 public Result<?> extract(@RequestParam(name = "TJYF") String tjyf) {
		 try {
			 tjfx_khsxyxtj_qhService.extract(tjyf);
		 } catch (Exception e) {
			 log.error(e.getMessage(), "提取失败");
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功");
	 }

	 /**
	  *   通过tjyf提取
	  * @param jsonObject
	  * @return
	  */
	 @RequestMapping(value = "/ipadinit", method = RequestMethod.PUT)
	 public Result<?> ipadinit(@RequestBody JSONObject jsonObject ) {
		 try {
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			 String tjyf = sdf.format(jsonObject.getDate("tjyf"));
			 tjfx_khsxyxtj_qhService.extract(tjyf);
		 } catch (Exception e) {
			 log.error(e.getMessage(), "提取失败");
			 return Result.error(e.getMessage());
		 }
			 return Result.ok("提取成功");
	 }

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "1-通过id查询")
	@ApiOperation(value="1-通过id查询", notes="1-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Tjfx_khsxyxtj_qh tjfx_khsxyxtj_qh = tjfx_khsxyxtj_qhService.getById(id);
		return Result.ok(tjfx_khsxyxtj_qh);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param tjfx_khsxyxtj_qh
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Tjfx_khsxyxtj_qh tjfx_khsxyxtj_qh) {
      return super.exportXls(request, tjfx_khsxyxtj_qh, Tjfx_khsxyxtj_qh.class, "全行授信用信统计");
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
      return super.importExcel(request, response, Tjfx_khsxyxtj_qh.class);
  }

}
