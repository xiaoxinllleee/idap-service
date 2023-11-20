package org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphjs_khjl.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.JxlsConstants;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.FileUtil;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphjs_khjl.entity.TjfxJrphsjKhjl;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphjs_khjl.entity.TjfxJrphsjKhjlImport;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphjs_khjl.service.ITjfx_Jrphsj_KhjlService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 客户经理金融普汇
 * @Author: jeecg-boot
 * @Date:   2020-09-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户经理金融普汇")
@RestController
@RequestMapping("/Tjfx_Jrphsj_Khjl/tjfx_Jrphsj_Khjl")
public class Tjfx_Jrphsj_KhjlController extends JeecgController<TjfxJrphsjKhjl, ITjfx_Jrphsj_KhjlService> {
	@Autowired
	private ITjfx_Jrphsj_KhjlService tjfx_Jrphsj_KhjlService;
	 @Autowired
	 private Environment environment;
	/**
	 * 分页列表查询
	 *
	 * @param tjfx_Jrphsj_Khjl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户经理金融普汇-分页列表查询")
	@ApiOperation(value="客户经理金融普汇-分页列表查询", notes="客户经理金融普汇-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TjfxJrphsjKhjl tjfx_Jrphsj_Khjl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TjfxJrphsjKhjl> queryWrapper = QueryGenerator.initQueryWrapper(tjfx_Jrphsj_Khjl, req.getParameterMap());
		Page<TjfxJrphsjKhjl> page = new Page<TjfxJrphsjKhjl>(pageNo, pageSize);
		IPage<TjfxJrphsjKhjl> pageList = tjfx_Jrphsj_KhjlService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param tjfx_Jrphsj_Khjl
	 * @return
	 */
	@AutoLog(value = "客户经理金融普汇-添加")
	@ApiOperation(value="客户经理金融普汇-添加", notes="客户经理金融普汇-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TjfxJrphsjKhjl tjfx_Jrphsj_Khjl) {
		tjfx_Jrphsj_KhjlService.save(tjfx_Jrphsj_Khjl);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param tjfx_Jrphsj_Khjl
	 * @return
	 */
	@AutoLog(value = "客户经理金融普汇-编辑")
	@ApiOperation(value="客户经理金融普汇-编辑", notes="客户经理金融普汇-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TjfxJrphsjKhjl tjfx_Jrphsj_Khjl) {
		tjfx_Jrphsj_KhjlService.updateById(tjfx_Jrphsj_Khjl);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理金融普汇-通过id删除")
	@ApiOperation(value="客户经理金融普汇-通过id删除", notes="客户经理金融普汇-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tjfx_Jrphsj_KhjlService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户经理金融普汇-批量删除")
	@ApiOperation(value="客户经理金融普汇-批量删除", notes="客户经理金融普汇-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tjfx_Jrphsj_KhjlService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理金融普汇-通过id查询")
	@ApiOperation(value="客户经理金融普汇-通过id查询", notes="客户经理金融普汇-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TjfxJrphsjKhjl tjfx_Jrphsj_Khjl = tjfx_Jrphsj_KhjlService.getById(id);
		return Result.ok(tjfx_Jrphsj_Khjl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param tjfx_Jrphsj_Khjl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, TjfxJrphsjKhjl tjfx_Jrphsj_Khjl) {
	  // Step.1 组装查询条件
	  QueryWrapper<TjfxJrphsjKhjl> queryWrapper = QueryGenerator.initQueryWrapper(tjfx_Jrphsj_Khjl, request.getParameterMap());
	  //AutoPoi 导出Excel
	  ModelAndView mv = new ModelAndView(new TemplateExcelView());
	  Map<String,Object> map = new HashMap<String, Object>();
	  List<TjfxJrphsjKhjlImport> jrphsjKhjlImportList = new ArrayList<>();
	  List<TjfxJrphsjKhjl> pageList = tjfx_Jrphsj_KhjlService.list(queryWrapper);
	  for (TjfxJrphsjKhjl khjl : pageList) {
		  khjl.setSszh(khjl.getSszh()== null ? "" : tjfx_Jrphsj_KhjlService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",khjl.getSszh()));
		  khjl.setYggh(khjl.getYggh()== null ? "" : tjfx_Jrphsj_KhjlService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",khjl.getYggh()));
		  TjfxJrphsjKhjlImport jrphsjZhImport = new TjfxJrphsjKhjlImport();
		  BeanUtils.copyProperties(khjl,jrphsjZhImport);
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  jrphsjZhImport.setTjyf(sdf.format(khjl.getTjyf()));
		  jrphsjKhjlImportList.add(jrphsjZhImport);
	  }
	  map.put("list",jrphsjKhjlImportList);
	  String port = environment.getProperty("common.path.export");
	  //导出文件名称
	  mv.addObject(JxlsConstants.FILE_NAME, "客户经理金融普惠数据汇总");
	  mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("客户经理金融普惠数据汇总.xls"));
	  mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"/客户经理金融普惠数据汇总.xls");
	  mv.addObject(JxlsConstants.MAP_DATA, map);
	  return mv;
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
      return super.importExcel(request, response, TjfxJrphsjKhjl.class);
  }

}
