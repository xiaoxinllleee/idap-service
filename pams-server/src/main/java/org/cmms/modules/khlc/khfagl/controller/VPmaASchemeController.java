package org.cmms.modules.khlc.khfagl.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khlc.khfagl.entity.VPmaAScheme;
import org.cmms.modules.khlc.khfagl.service.IVPmaASchemeService;
import java.util.Date;
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
 * @Description: 考核方案管理
 * @Author: jeecg-boot
 * @Date:   2021-10-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="考核方案管理")
@RestController
@RequestMapping("/khlc/khfagl/vPmaAScheme")
public class VPmaASchemeController extends JeecgController<VPmaAScheme, IVPmaASchemeService> {
	@Autowired
	private IVPmaASchemeService vPmaASchemeService;
	
	/**
	 * 分页列表查询
	 *
	 * @param vPmaAScheme
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "考核方案管理-分页列表查询")
	@ApiOperation(value="考核方案管理-分页列表查询", notes="考核方案管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VPmaAScheme vPmaAScheme,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   @RequestParam(name="tjrqBegin",required = true ) String tjrqBegin,
								   @RequestParam(name="tjrqEnd" ,required = true) String tjrqEnd,
								   HttpServletRequest req) {
		QueryWrapper<VPmaAScheme> queryWrapper = QueryGenerator.initQueryWrapper(vPmaAScheme, req.getParameterMap());
		queryWrapper.le("TO_DATE(start_date,'YYYY-MM-DD') ", DateUtil.string2Date(tjrqBegin,"yyyy-MM-dd"));
		queryWrapper.ge("TO_DATE(end_date,'YYYY-MM-DD')", DateUtil.string2Date(tjrqEnd,"yyyy-MM-dd"));
		Page<VPmaAScheme> page = new Page<VPmaAScheme>(pageNo, pageSize);
		IPage<VPmaAScheme> pageList = vPmaASchemeService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param vPmaAScheme
	 * @return
	 */
	@AutoLog(value = "考核方案管理-添加")
	@ApiOperation(value="考核方案管理-添加", notes="考核方案管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody VPmaAScheme vPmaAScheme) {
		vPmaASchemeService.save(vPmaAScheme);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param vPmaAScheme
	 * @return
	 */
	@AutoLog(value = "考核方案管理-编辑")
	@ApiOperation(value="考核方案管理-编辑", notes="考核方案管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody VPmaAScheme vPmaAScheme) {
		vPmaASchemeService.updateById(vPmaAScheme);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "考核方案管理-通过id删除")
	@ApiOperation(value="考核方案管理-通过id删除", notes="考核方案管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		vPmaASchemeService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "考核方案管理-批量删除")
	@ApiOperation(value="考核方案管理-批量删除", notes="考核方案管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.vPmaASchemeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "考核方案管理-通过id查询")
	@ApiOperation(value="考核方案管理-通过id查询", notes="考核方案管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VPmaAScheme vPmaAScheme = vPmaASchemeService.getById(id);
		return Result.ok(vPmaAScheme);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param vPmaAScheme
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, VPmaAScheme vPmaAScheme) {
      return super.exportXls(request, vPmaAScheme, VPmaAScheme.class, "考核方案管理");
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
      return super.importExcel(request, response, VPmaAScheme.class);
  }

}
