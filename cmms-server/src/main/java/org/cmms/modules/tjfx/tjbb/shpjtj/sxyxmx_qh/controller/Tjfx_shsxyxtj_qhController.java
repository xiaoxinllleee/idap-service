package org.cmms.modules.tjfx.tjbb.shpjtj.sxyxmx_qh.controller;

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
import org.cmms.modules.tjfx.tjbb.shpjtj.sxyxmx_qh.entity.Tjfx_shsxyxtj_qh;
import org.cmms.modules.tjfx.tjbb.shpjtj.sxyxmx_qh.service.ITjfx_shsxyxtj_qhService;
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
 * @Description: 全行授信用信统计
 * @Author: jeecg-boot
 * @Date:   2020-11-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="全行授信用信统计")
@RestController
@RequestMapping("/tjbb.shpjtj.sxyxmx_qh/tjfx_shsxyxtj_qh")
public class Tjfx_shsxyxtj_qhController extends JeecgController<Tjfx_shsxyxtj_qh, ITjfx_shsxyxtj_qhService> {
	@Autowired
	private ITjfx_shsxyxtj_qhService tjfx_shsxyxtj_qhService;

	/**
	 * 分页列表查询
	 *
	 * @param tjfx_shsxyxtj_qh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "全行授信用信统计-分页列表查询")
	@ApiOperation(value="全行授信用信统计-分页列表查询", notes="全行授信用信统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Tjfx_shsxyxtj_qh tjfx_shsxyxtj_qh,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Tjfx_shsxyxtj_qh> queryWrapper = QueryGenerator.initQueryWrapper(tjfx_shsxyxtj_qh, req.getParameterMap());
		Page<Tjfx_shsxyxtj_qh> page = new Page<Tjfx_shsxyxtj_qh>(pageNo, pageSize);
		IPage<Tjfx_shsxyxtj_qh> pageList = tjfx_shsxyxtj_qhService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param tjfx_shsxyxtj_qh
	 * @return
	 */
	@AutoLog(value = "全行授信用信统计-添加")
	@ApiOperation(value="全行授信用信统计-添加", notes="全行授信用信统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Tjfx_shsxyxtj_qh tjfx_shsxyxtj_qh) {
		tjfx_shsxyxtj_qhService.save(tjfx_shsxyxtj_qh);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param tjfx_shsxyxtj_qh
	 * @return
	 */
	@AutoLog(value = "全行授信用信统计-编辑")
	@ApiOperation(value="全行授信用信统计-编辑", notes="全行授信用信统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Tjfx_shsxyxtj_qh tjfx_shsxyxtj_qh) {
		tjfx_shsxyxtj_qhService.updateById(tjfx_shsxyxtj_qh);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全行授信用信统计-通过id删除")
	@ApiOperation(value="全行授信用信统计-通过id删除", notes="全行授信用信统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tjfx_shsxyxtj_qhService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "全行授信用信统计-批量删除")
	@ApiOperation(value="全行授信用信统计-批量删除", notes="全行授信用信统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tjfx_shsxyxtj_qhService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全行授信用信统计-通过id查询")
	@ApiOperation(value="全行授信用信统计-通过id查询", notes="全行授信用信统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Tjfx_shsxyxtj_qh tjfx_shsxyxtj_qh = tjfx_shsxyxtj_qhService.getById(id);
		return Result.ok(tjfx_shsxyxtj_qh);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param tjfx_shsxyxtj_qh
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Tjfx_shsxyxtj_qh tjfx_shsxyxtj_qh) {
      return super.exportXls(request, tjfx_shsxyxtj_qh, Tjfx_shsxyxtj_qh.class, "全行授信用信统计");
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
      return super.importExcel(request, response, Tjfx_shsxyxtj_qh.class);
  }
	 /**
	  * 全行走访数据统计-提取
	  * @param object
	  * @return
	  */
	 @PutMapping(value = "/init")
	 public Result<?> InitDataToQh(@RequestBody JSONObject object) {
		 System.out.println("tjyf-----"+object.getString("tjyf"));
		 try {
			 Map<String, String> param = new HashMap<>();
			 param.put("tjyf", object.getString("tjyf"));
			 tjfx_shsxyxtj_qhService.InitDataToQh(param);
		 } catch (Exception e) {
			 log.error(e.getMessage(), "提取失败！");
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功！");
	 }
}
