package org.cmms.modules.tjfx.shzftj.shzfsjmx_qh.controller;

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
import org.cmms.modules.tjfx.shzftj.shzfsjmx_qh.entity.Tjfx_shzfsjmx_qh;
import org.cmms.modules.tjfx.shzftj.shzfsjmx_qh.service.ITjfx_shzfsjmx_qhService;
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
 * @Description: 商户走访数据明细统计_全行
 * @Author: jeecg-boot
 * @Date:   2020-11-04
 * @Version: V1.0
 */
@Slf4j
@Api(tags="商户走访数据明细统计_全行")
@RestController
@RequestMapping("/TJFX_SHZFSJMX_QH/tjfx_shzfsjmx_qh")
public class Tjfx_shzfsjmx_qhController extends JeecgController<Tjfx_shzfsjmx_qh, ITjfx_shzfsjmx_qhService> {
	@Autowired
	private ITjfx_shzfsjmx_qhService tjfx_shzfsjmx_qhService;

	/**
	 * 分页列表查询
	 *
	 * @param tjfx_shzfsjmx_qh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "商户走访数据明细统计_全行-分页列表查询")
	@ApiOperation(value="商户走访数据明细统计_全行-分页列表查询", notes="商户走访数据明细统计_全行-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Tjfx_shzfsjmx_qh tjfx_shzfsjmx_qh,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Tjfx_shzfsjmx_qh> queryWrapper = QueryGenerator.initQueryWrapper(tjfx_shzfsjmx_qh, req.getParameterMap());
		Page<Tjfx_shzfsjmx_qh> page = new Page<Tjfx_shzfsjmx_qh>(pageNo, pageSize);
		IPage<Tjfx_shzfsjmx_qh> pageList = tjfx_shzfsjmx_qhService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param tjfx_shzfsjmx_qh
	 * @return
	 */
	@AutoLog(value = "商户走访数据明细统计_全行-添加")
	@ApiOperation(value="商户走访数据明细统计_全行-添加", notes="商户走访数据明细统计_全行-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Tjfx_shzfsjmx_qh tjfx_shzfsjmx_qh) {
		tjfx_shzfsjmx_qhService.save(tjfx_shzfsjmx_qh);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param tjfx_shzfsjmx_qh
	 * @return
	 */
	@AutoLog(value = "商户走访数据明细统计_全行-编辑")
	@ApiOperation(value="商户走访数据明细统计_全行-编辑", notes="商户走访数据明细统计_全行-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Tjfx_shzfsjmx_qh tjfx_shzfsjmx_qh) {
		tjfx_shzfsjmx_qhService.updateById(tjfx_shzfsjmx_qh);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商户走访数据明细统计_全行-通过id删除")
	@ApiOperation(value="商户走访数据明细统计_全行-通过id删除", notes="商户走访数据明细统计_全行-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tjfx_shzfsjmx_qhService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "商户走访数据明细统计_全行-批量删除")
	@ApiOperation(value="商户走访数据明细统计_全行-批量删除", notes="商户走访数据明细统计_全行-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tjfx_shzfsjmx_qhService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商户走访数据明细统计_全行-通过id查询")
	@ApiOperation(value="商户走访数据明细统计_全行-通过id查询", notes="商户走访数据明细统计_全行-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Tjfx_shzfsjmx_qh tjfx_shzfsjmx_qh = tjfx_shzfsjmx_qhService.getById(id);
		return Result.ok(tjfx_shzfsjmx_qh);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param tjfx_shzfsjmx_qh
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Tjfx_shzfsjmx_qh tjfx_shzfsjmx_qh) {
      return super.exportXls(request, tjfx_shzfsjmx_qh, Tjfx_shzfsjmx_qh.class, "商户走访数据明细统计_全行");
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
      return super.importExcel(request, response, Tjfx_shzfsjmx_qh.class);
  }

	 /**
	  * 全行商户走访数据统计-提取
	  * @param object
	  * @return
	  */
	 @PutMapping(value = "/init")
	 public Result<?> InitDataToQh(@RequestBody JSONObject object) {
		 System.out.println("tjyf-----"+object.getString("tjyf"));
		 try {
			 Map<String, String> param = new HashMap<>();
			 param.put("tjyf", object.getString("tjyf"));
			 tjfx_shzfsjmx_qhService.InitDataToQh(param);
		 } catch (Exception e) {
			 log.error(e.getMessage(), "提取失败！");
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功！");
	 }
}
