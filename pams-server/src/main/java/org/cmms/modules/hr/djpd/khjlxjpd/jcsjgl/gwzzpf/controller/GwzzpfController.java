package org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.gwzzpf.controller;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.gwzzpf.entity.Gwzzpf;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.gwzzpf.entity.GwzzpfVO;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.gwzzpf.service.IGwzzpfService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.gwzzpf.verify.GwzzpfImportVerify;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 岗位资质评分
 * @Author: jeecg-boot
 * @Date:   2021-09-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="岗位资质评分")
@RestController
@RequestMapping("/gwzzpf/gwzzpf")
public class GwzzpfController extends JeecgController<Gwzzpf, IGwzzpfService> {
	@Autowired
	private IGwzzpfService gwzzpfService;
	@Autowired
	private GwzzpfImportVerify gwzzpfImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param gwzzpf
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "岗位资质评分-分页列表查询")
	@ApiOperation(value="岗位资质评分-分页列表查询", notes="岗位资质评分-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Gwzzpf gwzzpf,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Gwzzpf> queryWrapper = QueryGenerator.initQueryWrapper(gwzzpf, req.getParameterMap());
		Page<Gwzzpf> page = new Page<Gwzzpf>(pageNo, pageSize);
		IPage<Gwzzpf> pageList = gwzzpfService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param gwzzpf
	 * @return
	 */
	@AutoLog(value = "岗位资质评分-添加")
	@ApiOperation(value="岗位资质评分-添加", notes="岗位资质评分-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Gwzzpf gwzzpf) {
		gwzzpfService.save(gwzzpf);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param gwzzpf
	 * @return
	 */
	@AutoLog(value = "岗位资质评分-编辑")
	@ApiOperation(value="岗位资质评分-编辑", notes="岗位资质评分-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Gwzzpf gwzzpf) {
		QueryWrapper<Gwzzpf> queryWrapper = new QueryWrapper<Gwzzpf>();
		queryWrapper.eq("zzbz",gwzzpf.getZzbz());
		queryWrapper.eq("gwbz",gwzzpf.getGwbz());
		queryWrapper.eq("yggh",gwzzpf.getYggh());
		gwzzpfService.update(gwzzpf,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "岗位资质评分-通过id删除")
	@ApiOperation(value="岗位资质评分-通过id删除", notes="岗位资质评分-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("zzbz")String zzbz,@Param("gwbz")String gwbz,@Param("yggh")String yggh) {
		QueryWrapper<Gwzzpf> queryWrapper = new QueryWrapper<Gwzzpf>();
		queryWrapper.eq("zzbz",zzbz);
		queryWrapper.eq("gwbz",gwbz);
		queryWrapper.eq("yggh",yggh);
		gwzzpfService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "岗位资质评分-批量删除")
	@ApiOperation(value="岗位资质评分-批量删除", notes="岗位资质评分-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.gwzzpfService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "岗位资质评分-通过id查询")
	@ApiOperation(value="岗位资质评分-通过id查询", notes="岗位资质评分-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Gwzzpf gwzzpf = gwzzpfService.getById(id);
		return Result.ok(gwzzpf);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param gwzzpf
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Gwzzpf gwzzpf) {
      return super.exportXls(request, gwzzpf, Gwzzpf.class, "岗位资质评分");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "岗位资质评分导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, GwzzpfVO.class);
		 ExportParams exportParams = new ExportParams("岗位资质评分导入模板", "模板信息");
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
		 return super.importExcelByTemplate(jsonObject, request, response, Gwzzpf.class,GwzzpfVO.class,gwzzpfImportVerify);
	 }
}
