package org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zbzhpj.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zbzhpj.entity.Zbzhpj;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zbzhpj.entity.ZbzhpjVo;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zbzhpj.service.IZbzhpjService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zbzhpj.verify.ZbzhpjImportVerify;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zhzhpj.entity.Zhzhpj;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zhzhpj.entity.ZhzhpjVo;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 总部综合评价
 * @Author: jeecg-boot
 * @Date:   2021-09-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="总部综合评价")
@RestController
@RequestMapping("/zbzhpj/zbzhpj")
public class ZbzhpjController extends JeecgController<Zbzhpj, IZbzhpjService> {
	@Autowired
	private IZbzhpjService zbzhpjService;
	@Autowired
	private ZbzhpjImportVerify zbzhpjImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param zbzhpj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "总部综合评价-分页列表查询")
	@ApiOperation(value="总部综合评价-分页列表查询", notes="总部综合评价-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zbzhpj zbzhpj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Zbzhpj> queryWrapper = QueryGenerator.initQueryWrapper(zbzhpj, req.getParameterMap());
		Page<Zbzhpj> page = new Page<Zbzhpj>(pageNo, pageSize);
		IPage<Zbzhpj> pageList = zbzhpjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param zbzhpj
	 * @return
	 */
	@AutoLog(value = "总部综合评价-添加")
	@ApiOperation(value="总部综合评价-添加", notes="总部综合评价-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zbzhpj zbzhpj) {
		zbzhpjService.save(zbzhpj);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param zbzhpj
	 * @return
	 */
	@AutoLog(value = "总部综合评价-编辑")
	@ApiOperation(value="总部综合评价-编辑", notes="总部综合评价-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zbzhpj zbzhpj) {
		QueryWrapper<Zbzhpj> queryWrapper = new QueryWrapper<Zbzhpj>();
		queryWrapper.eq("zzbz",zbzhpj.getZzbz());
		queryWrapper.eq("gwbz",zbzhpj.getGwbz());
		queryWrapper.eq("yggh",zbzhpj.getYggh());
		zbzhpjService.update(zbzhpj,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "总部综合评价-通过id删除")
	@ApiOperation(value="总部综合评价-通过id删除", notes="总部综合评价-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("zzbz")String zzbz,@Param("gwbz")String gwbz,@Param("yggh")String yggh,@Param("pjnf") String pjnf) {
		Date parse = DateUtil.parse(pjnf+"-01-01");
		QueryWrapper<Zbzhpj> queryWrapper = new QueryWrapper<Zbzhpj>();
		queryWrapper.eq("zzbz",zzbz);
		queryWrapper.eq("gwbz",gwbz);
		queryWrapper.eq("yggh",yggh);
		queryWrapper.eq("pjnf", parse);
		zbzhpjService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "总部综合评价-批量删除")
	@ApiOperation(value="总部综合评价-批量删除", notes="总部综合评价-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zbzhpjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "总部综合评价-通过id查询")
	@ApiOperation(value="总部综合评价-通过id查询", notes="总部综合评价-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zbzhpj zbzhpj = zbzhpjService.getById(id);
		return Result.ok(zbzhpj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zbzhpj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zbzhpj zbzhpj) {
      return super.exportXls(request, zbzhpj, Zbzhpj.class, "总部综合评价");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "总部综合评价导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, ZbzhpjVo.class);
		 ExportParams exportParams = new ExportParams("总部综合评价导入模板", "模板信息");
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
		 return super.importExcelByTemplate(jsonObject, request, response, Zbzhpj.class,ZbzhpjVo.class,zbzhpjImportVerify);
	 }
}
