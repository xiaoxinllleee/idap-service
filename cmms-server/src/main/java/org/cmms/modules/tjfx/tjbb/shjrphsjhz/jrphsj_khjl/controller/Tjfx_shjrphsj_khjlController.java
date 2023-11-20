package org.cmms.modules.tjfx.tjbb.shjrphsjhz.jrphsj_khjl.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.JxlsConstants;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.FileUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphjs_khjl.entity.TjfxJrphsjKhjl;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphjs_khjl.entity.TjfxJrphsjKhjlImport;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphjs_khjl.service.ITjfx_Jrphsj_KhjlService;
import org.cmms.modules.tjfx.tjbb.shjrphsjhz.jrphsj_khjl.entity.Tjfx_shjrphsj_khjl;
import org.cmms.modules.tjfx.tjbb.shjrphsjhz.jrphsj_khjl.entity.Tjfx_shjrphsj_khjl_Import;
import org.cmms.modules.tjfx.tjbb.shjrphsjhz.jrphsj_khjl.service.ITjfx_shjrphsj_khjlService;
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

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 客户经理金融普惠数据
 * @Author: jeecg-boot
 * @Date:   2020-11-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户经理金融普惠数据")
@RestController
@RequestMapping("/tjbb.shjrphsjhz.jrphsj_khjl/tjfx_shjrphsj_khjl")
public class Tjfx_shjrphsj_khjlController extends JeecgController<Tjfx_shjrphsj_khjl, ITjfx_shjrphsj_khjlService> {
	@Autowired
	private ITjfx_shjrphsj_khjlService tjfx_shjrphsj_khjlService;
	 @Autowired
	 private ITjfx_Jrphsj_KhjlService tjfx_Jrphsj_KhjlService;
	 @Autowired
	 private Environment environment;
	/**
	 * 分页列表查询
	 *
	 * @param tjfx_shjrphsj_khjl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户经理金融普惠数据-分页列表查询")
	@ApiOperation(value="客户经理金融普惠数据-分页列表查询", notes="客户经理金融普惠数据-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Tjfx_shjrphsj_khjl tjfx_shjrphsj_khjl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Tjfx_shjrphsj_khjl> queryWrapper = QueryGenerator.initQueryWrapper(tjfx_shjrphsj_khjl, req.getParameterMap());
		Page<Tjfx_shjrphsj_khjl> page = new Page<Tjfx_shjrphsj_khjl>(pageNo, pageSize);
		IPage<Tjfx_shjrphsj_khjl> pageList = tjfx_shjrphsj_khjlService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param tjfx_shjrphsj_khjl
	 * @return
	 */
	@AutoLog(value = "客户经理金融普惠数据-添加")
	@ApiOperation(value="客户经理金融普惠数据-添加", notes="客户经理金融普惠数据-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Tjfx_shjrphsj_khjl tjfx_shjrphsj_khjl) {
		tjfx_shjrphsj_khjlService.save(tjfx_shjrphsj_khjl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param tjfx_shjrphsj_khjl
	 * @return
	 */
	@AutoLog(value = "客户经理金融普惠数据-编辑")
	@ApiOperation(value="客户经理金融普惠数据-编辑", notes="客户经理金融普惠数据-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Tjfx_shjrphsj_khjl tjfx_shjrphsj_khjl) {
		tjfx_shjrphsj_khjlService.updateById(tjfx_shjrphsj_khjl);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理金融普惠数据-通过id删除")
	@ApiOperation(value="客户经理金融普惠数据-通过id删除", notes="客户经理金融普惠数据-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tjfx_shjrphsj_khjlService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户经理金融普惠数据-批量删除")
	@ApiOperation(value="客户经理金融普惠数据-批量删除", notes="客户经理金融普惠数据-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tjfx_shjrphsj_khjlService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理金融普惠数据-通过id查询")
	@ApiOperation(value="客户经理金融普惠数据-通过id查询", notes="客户经理金融普惠数据-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Tjfx_shjrphsj_khjl tjfx_shjrphsj_khjl = tjfx_shjrphsj_khjlService.getById(id);
		return Result.ok(tjfx_shjrphsj_khjl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param
   */

  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Tjfx_shjrphsj_khjl tjfx_Jrphsj_Khjl) {
	  // Step.1 组装查询条件
	  QueryWrapper<Tjfx_shjrphsj_khjl> queryWrapper = QueryGenerator.initQueryWrapper(tjfx_Jrphsj_Khjl, request.getParameterMap());
	  //AutoPoi 导出Excel
	  ModelAndView mv = new ModelAndView(new TemplateExcelView());
	  Map<String,Object> map = new HashMap<String, Object>();
	  List<Tjfx_shjrphsj_khjl_Import> jrphsjKhjlImportList = new ArrayList<>();
	  List<Tjfx_shjrphsj_khjl> pageList = tjfx_shjrphsj_khjlService.list(queryWrapper);
	  for (Tjfx_shjrphsj_khjl khjl : pageList) {
		  khjl.setSszh(khjl.getSszh()== null ? "" : tjfx_Jrphsj_KhjlService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",khjl.getSszh()));
		  khjl.setYggh(khjl.getYggh()== null ? "" : tjfx_Jrphsj_KhjlService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",khjl.getYggh()));
		  Tjfx_shjrphsj_khjl_Import jrphsjZhImport = new Tjfx_shjrphsj_khjl_Import();
		  BeanUtils.copyProperties(khjl,jrphsjZhImport);
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  jrphsjZhImport.setTjyf(sdf.format(khjl.getTjyf()));
		  jrphsjKhjlImportList.add(jrphsjZhImport);
	  }
	  map.put("list",jrphsjKhjlImportList);
	  String port = environment.getProperty("common.path.export");
	  //导出文件名称
	  mv.addObject(JxlsConstants.FILE_NAME, "客户经理商户金融普惠数据汇总");
	  mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("客户经理商户金融普惠数据.xls"));
	  mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"/客户经理商户金融普惠数据汇总.xls");
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
      return super.importExcel(request, response, Tjfx_shjrphsj_khjl.class);
  }

}
