package org.cmms.modules.tjfx.jcsjgl.zfrwsz.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.jcsjgl.cssz.entity.TjfxCssz;
import org.cmms.modules.tjfx.jcsjgl.cssz.entity.TjfxCsszVO;
import org.cmms.modules.tjfx.jcsjgl.zfrwsz.entity.TjfxZfrwsz;
import org.cmms.modules.tjfx.jcsjgl.zfrwsz.entity.TjfxZfrwszVO;
import org.cmms.modules.tjfx.jcsjgl.zfrwsz.service.ITjfxZfrwszService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.jcsjgl.zfrwsz.verify.TjfxZfrwszImportVerify;
import org.cmms.modules.tjfx.khjbfctj.entity.KhjbfctjKhjl;
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

import static org.cmms.common.system.query.QueryGenerator.installMplus;

/**
 * @Description: 走访任务设置
 * @Author: jeecg-boot
 * @Date:   2022-08-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags="走访任务设置")
@RestController
@RequestMapping("/zfrwsz/tjfxZfrwsz")
public class TjfxZfrwszController extends JeecgController<TjfxZfrwsz, ITjfxZfrwszService> {
	@Autowired
	private ITjfxZfrwszService tjfxZfrwszService;
	@Autowired
	private TjfxZfrwszImportVerify tjfxZfrwszImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param tjfxZfrwsz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "走访任务设置-分页列表查询")
	@ApiOperation(value="走访任务设置-分页列表查询", notes="走访任务设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TjfxZfrwsz tjfxZfrwsz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		Result<IPage<TjfxZfrwsz>> result = new Result<IPage<TjfxZfrwsz>>();
		QueryWrapper<TjfxZfrwsz> queryWrapper =QueryGenerator.initQueryWrapper(tjfxZfrwsz, req.getParameterMap());
		if ("Q".equals(tjfxZfrwsz.getTjwd())){
			Date beginOfQuarter = DateUtil.beginOfQuarter(tjfxZfrwsz.getTjrq());
			Date endOfQuarter = DateUtil.endOfQuarter(tjfxZfrwsz.getTjrq());
//			queryWrapper.ge("tjrq",beginOfQuarter);
//			queryWrapper.le("tjrq",endOfQuarter);
			queryWrapper.between("tjrq",beginOfQuarter,endOfQuarter);
		}
		Page<TjfxZfrwsz> page = new Page<TjfxZfrwsz>(pageNo, pageSize);
		IPage<TjfxZfrwsz> pageList = tjfxZfrwszService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	 * 添加
	 *
	 * @param tjfxZfrwsz
	 * @return
	 */
	@AutoLog(value = "走访任务设置-添加")
	@ApiOperation(value="走访任务设置-添加", notes="走访任务设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TjfxZfrwsz tjfxZfrwsz) {
		QueryWrapper<TjfxZfrwsz> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("tjrq",tjfxZfrwsz.getTjrq());
		queryWrapper.eq("tjwd",tjfxZfrwsz.getTjwd());
		queryWrapper.eq("jgdm",tjfxZfrwsz.getJgdm());
		queryWrapper.eq("khlx",tjfxZfrwsz.getKhlx());
		if (tjfxZfrwszService.getOne(queryWrapper) !=null){
			return Result.error("该信息已存在");
		}
		if ("YYYY".equals(tjfxZfrwsz.getTjwd())){
			System.out.println(tjfxZfrwsz.getTjrq());
			tjfxZfrwsz.setTjrq(DateUtil.beginOfYear(tjfxZfrwsz.getTjrq()));
		}else if ("Q".equals(tjfxZfrwsz.getTjwd())){
			String format = DateUtil.format(tjfxZfrwsz.getTjrq(),"yyyy-MM-dd");
			Date parse = DateUtil.parse(format);
			tjfxZfrwsz.setTjrq(parse);
		}else {
			tjfxZfrwsz.setTjrq(DateUtil.beginOfMonth(tjfxZfrwsz.getTjrq()));
		}
		tjfxZfrwsz.setLrsj(new Date());
		tjfxZfrwsz.setLrr(getUsername());
		tjfxZfrwsz.setLrbz(1);
		tjfxZfrwszService.save(tjfxZfrwsz);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param tjfxZfrwsz
	 * @return
	 */
	@AutoLog(value = "走访任务设置-编辑")
	@ApiOperation(value="走访任务设置-编辑", notes="走访任务设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TjfxZfrwsz tjfxZfrwsz) {
		tjfxZfrwszService.updateById(tjfxZfrwsz);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "走访任务设置-通过id删除")
	@ApiOperation(value="走访任务设置-通过id删除", notes="走访任务设置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tjfxZfrwszService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "走访任务设置-批量删除")
	@ApiOperation(value="走访任务设置-批量删除", notes="走访任务设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tjfxZfrwszService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "走访任务设置-通过id查询")
	@ApiOperation(value="走访任务设置-通过id查询", notes="走访任务设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TjfxZfrwsz tjfxZfrwsz = tjfxZfrwszService.getById(id);
		return Result.ok(tjfxZfrwsz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param tjfxZfrwsz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, TjfxZfrwsz tjfxZfrwsz) {
      return super.exportXls(request, tjfxZfrwsz, TjfxZfrwsz.class, "走访任务设置");
  }

	 /**
	  * 导出模板
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "走访任务设置导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, TjfxZfrwszVO.class);
		 ExportParams exportParams = new ExportParams("走访任务设置导入模板", "模板信息");
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
/*  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, TjfxZfrwsz.class);
  }*/
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
	  return super.importExcelByTemplate(jsonObject, request, response, TjfxZfrwsz.class,TjfxZfrwszVO.class,tjfxZfrwszImportVerify);
  }
}
