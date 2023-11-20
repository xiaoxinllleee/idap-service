package org.cmms.modules.khxxgl.wbsjgl.xyksj.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
import org.cmms.modules.khxxgl.wbsjgl.xyksj.entity.KhxxglWbsjglXyksj;
import org.cmms.modules.khxxgl.wbsjgl.xyksj.service.IKhxxglWbsjglXyksjService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khxxgl.wbsjgl.xyksj.verify.XyksjImportVerify;
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
 * @Description: 信用卡数据
 * @Author: jeecg-boot
 * @Date:   2022-04-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags="信用卡数据")
@RestController
@RequestMapping("/xyksj/khxxglWbsjglXyksj")
public class KhxxglWbsjglXyksjController extends JeecgController<KhxxglWbsjglXyksj, IKhxxglWbsjglXyksjService> {
	@Autowired
	private IKhxxglWbsjglXyksjService khxxglWbsjglXyksjService;

	@Autowired
	private XyksjImportVerify xyksjImportVerify;
	
	/**
	 * 分页列表查询
	 *
	 * @param khxxglWbsjglXyksj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "信用卡数据-分页列表查询")
	@ApiOperation(value="信用卡数据-分页列表查询", notes="信用卡数据-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KhxxglWbsjglXyksj khxxglWbsjglXyksj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<KhxxglWbsjglXyksj> queryWrapper = QueryGenerator.initQueryWrapper(khxxglWbsjglXyksj, req.getParameterMap());
		Page<KhxxglWbsjglXyksj> page = new Page<KhxxglWbsjglXyksj>(pageNo, pageSize);
		IPage<KhxxglWbsjglXyksj> pageList = khxxglWbsjglXyksjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param khxxglWbsjglXyksj
	 * @return
	 */
	@AutoLog(value = "信用卡数据-添加")
	@ApiOperation(value="信用卡数据-添加", notes="信用卡数据-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody KhxxglWbsjglXyksj khxxglWbsjglXyksj) {
		khxxglWbsjglXyksjService.save(khxxglWbsjglXyksj);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param khxxglWbsjglXyksj
	 * @return
	 */
	@AutoLog(value = "信用卡数据-编辑")
	@ApiOperation(value="信用卡数据-编辑", notes="信用卡数据-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhxxglWbsjglXyksj khxxglWbsjglXyksj) {
		khxxglWbsjglXyksjService.updateById(khxxglWbsjglXyksj);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "信用卡数据-通过id删除")
	@ApiOperation(value="信用卡数据-通过id删除", notes="信用卡数据-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khxxglWbsjglXyksjService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "信用卡数据-批量删除")
	@ApiOperation(value="信用卡数据-批量删除", notes="信用卡数据-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khxxglWbsjglXyksjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "信用卡数据-通过id查询")
	@ApiOperation(value="信用卡数据-通过id查询", notes="信用卡数据-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		KhxxglWbsjglXyksj khxxglWbsjglXyksj = khxxglWbsjglXyksjService.getById(id);
		return Result.ok(khxxglWbsjglXyksj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khxxglWbsjglXyksj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, KhxxglWbsjglXyksj khxxglWbsjglXyksj) {
      return super.exportXls(request, khxxglWbsjglXyksj, KhxxglWbsjglXyksj.class, "信用卡数据");
  }

	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 return super.exportTemplateXls(KhxxglWbsjglXyksj.class, "信用卡数据导入模板");
	 }

	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 return super.importExcelByTemplate(jsonObject, request, response, KhxxglWbsjglXyksj.class, xyksjImportVerify);
	 }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
//  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
//  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
//      return super.importExcel(request, response, KhxxglWbsjglXyksj.class);
//  }

  @GetMapping(value = "/queryByZjhm")
  public Result<?> queryByZjhm(@RequestParam(name="zjhm",required=true) String zjhm) {
	  QueryWrapper<KhxxglWbsjglXyksj> queryWrapper = new QueryWrapper<>();
	  queryWrapper.eq("zjhm",zjhm);
	  List<KhxxglWbsjglXyksj> list = khxxglWbsjglXyksjService.list(queryWrapper);
	  return Result.ok(list);
  }
}
