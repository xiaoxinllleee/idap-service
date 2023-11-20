package org.cmms.modules.khxxgl.wbsjgl.gjjsj.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.khxxgl.wbsjgl.gjjsj.entity.KhxxglWbsjglGjjsj;
import org.cmms.modules.khxxgl.wbsjgl.gjjsj.service.IKhxxglWbsjglGjjsjService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khxxgl.wbsjgl.gjjsj.verify.GjjglImportVerify;
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
 * @Description: 公积金数据
 * @Author: jeecg-boot
 * @Date:   2022-04-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags="公积金数据")
@RestController
@RequestMapping("/gjjsj/khxxglWbsjglGjjsj")
public class KhxxglWbsjglGjjsjController extends JeecgController<KhxxglWbsjglGjjsj, IKhxxglWbsjglGjjsjService> {
	@Autowired
	private IKhxxglWbsjglGjjsjService khxxglWbsjglGjjsjService;
	@Autowired
	private INhxqService nhxqService;
	@Autowired
	private GjjglImportVerify gjjglImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param khxxglWbsjglGjjsj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "公积金数据-分页列表查询")
	@ApiOperation(value="公积金数据-分页列表查询", notes="公积金数据-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KhxxglWbsjglGjjsj khxxglWbsjglGjjsj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<KhxxglWbsjglGjjsj> queryWrapper = QueryGenerator.initQueryWrapper(khxxglWbsjglGjjsj, req.getParameterMap());
		Page<KhxxglWbsjglGjjsj> page = new Page<KhxxglWbsjglGjjsj>(pageNo, pageSize);
		IPage<KhxxglWbsjglGjjsj> pageList = khxxglWbsjglGjjsjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param khxxglWbsjglGjjsj
	 * @return
	 */
	@AutoLog(value = "公积金数据-添加")
	@ApiOperation(value="公积金数据-添加", notes="公积金数据-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody KhxxglWbsjglGjjsj khxxglWbsjglGjjsj) {
		khxxglWbsjglGjjsjService.save(khxxglWbsjglGjjsj);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param khxxglWbsjglGjjsj
	 * @return
	 */
	@AutoLog(value = "公积金数据-编辑")
	@ApiOperation(value="公积金数据-编辑", notes="公积金数据-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhxxglWbsjglGjjsj khxxglWbsjglGjjsj) {
		khxxglWbsjglGjjsjService.updateById(khxxglWbsjglGjjsj);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "公积金数据-通过id删除")
	@ApiOperation(value="公积金数据-通过id删除", notes="公积金数据-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khxxglWbsjglGjjsjService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "公积金数据-批量删除")
	@ApiOperation(value="公积金数据-批量删除", notes="公积金数据-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khxxglWbsjglGjjsjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "公积金数据-通过id查询")
	@ApiOperation(value="公积金数据-通过id查询", notes="公积金数据-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		KhxxglWbsjglGjjsj khxxglWbsjglGjjsj = khxxglWbsjglGjjsjService.getById(id);
		return Result.ok(khxxglWbsjglGjjsj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khxxglWbsjglGjjsj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, KhxxglWbsjglGjjsj khxxglWbsjglGjjsj) {
      return super.exportXls(request, khxxglWbsjglGjjsj, KhxxglWbsjglGjjsj.class, "公积金数据");
  }

  @RequestMapping(value = "/exportTemplateXls")
  public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
  	return super.exportTemplateXls(KhxxglWbsjglGjjsj.class, "公积金数据导入模板");
  }

	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 return super.importExcelByTemplate(jsonObject, request, response, KhxxglWbsjglGjjsj.class, gjjglImportVerify);
	 }

	 /**
   * 通过excel导入数据
   *
   * @return
   */
//  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
//  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
//      return super.importExcel(request, response, KhxxglWbsjglGjjsj.class);
//  }


  @GetMapping(value = "/queryByNhId")
  public Result<?> queryByNhId(@RequestParam(name="id",required=true) String id) {
	  Nhxq byId = nhxqService.getById(id);
	  if (byId != null && StringUtils.isNotBlank(byId.getZjhm())){
		  QueryWrapper<KhxxglWbsjglGjjsj> queryWrapper = new QueryWrapper<>();
		  queryWrapper.eq("zjhm",byId.getZjhm());
		  List<KhxxglWbsjglGjjsj> list = service.list(queryWrapper);
		  return Result.ok(list);
	  }
	  return Result.ok();
  }

}
