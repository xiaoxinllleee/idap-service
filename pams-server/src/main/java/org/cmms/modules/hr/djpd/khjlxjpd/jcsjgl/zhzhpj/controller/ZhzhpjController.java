package org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zhzhpj.controller;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zhzhpj.entity.Zhzhpj;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zhzhpj.entity.ZhzhpjVo;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zhzhpj.service.IZhzhpjService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zhzhpj.verify.ZhzhpjImportVerify;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 支行综合评价
 * @Author: jeecg-boot
 * @Date:   2021-09-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行综合评价")
@RestController
@RequestMapping("/zhzhpj/zhzhpj")
public class ZhzhpjController extends JeecgController<Zhzhpj, IZhzhpjService> {
	@Autowired
	private IZhzhpjService zhzhpjService;
	@Autowired
	private ZhzhpjImportVerify zhzhpjImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param zhzhpj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行综合评价-分页列表查询")
	@ApiOperation(value="支行综合评价-分页列表查询", notes="支行综合评价-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zhzhpj zhzhpj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Zhzhpj> queryWrapper = QueryGenerator.initQueryWrapper(zhzhpj, req.getParameterMap());
		Page<Zhzhpj> page = new Page<Zhzhpj>(pageNo, pageSize);
		IPage<Zhzhpj> pageList = zhzhpjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param zhzhpj
	 * @return
	 */
	@AutoLog(value = "支行综合评价-添加")
	@ApiOperation(value="支行综合评价-添加", notes="支行综合评价-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zhzhpj zhzhpj) {
		zhzhpjService.save(zhzhpj);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param zhzhpj
	 * @return
	 */
	@AutoLog(value = "支行综合评价-编辑")
	@ApiOperation(value="支行综合评价-编辑", notes="支行综合评价-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zhzhpj zhzhpj) {
		QueryWrapper<Zhzhpj> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("zzbz",zhzhpj.getZzbz());
		queryWrapper.eq("yggh",zhzhpj.getYggh());
		queryWrapper.eq("gwbz",zhzhpj.getGwbz());
		queryWrapper.eq("pjnf",zhzhpj.getPjnf());
		zhzhpjService.update(zhzhpj,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "支行综合评价-通过id删除")
	@ApiOperation(value="支行综合评价-通过id删除", notes="支行综合评价-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("zzbz")String zzbz,@Param("gwbz")String gwbz,@Param("yggh")String yggh) {
		QueryWrapper<Zhzhpj> queryWrapper = new QueryWrapper<Zhzhpj>();
		queryWrapper.eq("zzbz",zzbz);
		queryWrapper.eq("gwbz",gwbz);
		queryWrapper.eq("yggh",yggh);
		zhzhpjService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "支行综合评价-批量删除")
	@ApiOperation(value="支行综合评价-批量删除", notes="支行综合评价-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zhzhpjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行综合评价-通过id查询")
	@ApiOperation(value="支行综合评价-通过id查询", notes="支行综合评价-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zhzhpj zhzhpj = zhzhpjService.getById(id);
		return Result.ok(zhzhpj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zhzhpj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zhzhpj zhzhpj) {
      return super.exportXls(request, zhzhpj, Zhzhpj.class, "支行综合评价");
  }
	 /**
	  * 导入模板
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "支行综合评价导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, ZhzhpjVo.class);
		 ExportParams exportParams = new ExportParams("支行综合评价导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
	 }
  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 return super.importExcelByTemplate(jsonObject, request, response, Zhzhpj.class,ZhzhpjVo.class,zhzhpjImportVerify);
	 }
}
