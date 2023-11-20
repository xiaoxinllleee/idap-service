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
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khlc.khfagl.entity.PmaASchemePostRel;
import org.cmms.modules.khlc.khfagl.service.IPmaASchemePostRelService;
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
 * @Description: 考核方案岗位关系表
 * @Author: jeecg-boot
 * @Date:   2021-02-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags="考核方案岗位关系表")
@RestController
@RequestMapping("/khfagl/pmaASchemePostRel")
public class PmaASchemePostRelController extends JeecgController<PmaASchemePostRel, IPmaASchemePostRelService> {
	@Autowired
	private IPmaASchemePostRelService pmaASchemePostRelService;
	
	/**
	 * 分页列表查询
	 *
	 * @param pmaASchemePostRel
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "考核方案岗位关系表-分页列表查询")
	@ApiOperation(value="考核方案岗位关系表-分页列表查询", notes="考核方案岗位关系表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PmaASchemePostRel pmaASchemePostRel,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PmaASchemePostRel> queryWrapper = QueryGenerator.initQueryWrapper(pmaASchemePostRel, req.getParameterMap());
		Page<PmaASchemePostRel> page = new Page<PmaASchemePostRel>(pageNo, pageSize);
		IPage<PmaASchemePostRel> pageList = pmaASchemePostRelService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param pmaASchemePostRel
	 * @return
	 */
	@AutoLog(value = "考核方案岗位关系表-添加")
	@ApiOperation(value="考核方案岗位关系表-添加", notes="考核方案岗位关系表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PmaASchemePostRel pmaASchemePostRel) {
		pmaASchemePostRelService.save(pmaASchemePostRel);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param pmaASchemePostRel
	 * @return
	 */
	@AutoLog(value = "考核方案岗位关系表-编辑")
	@ApiOperation(value="考核方案岗位关系表-编辑", notes="考核方案岗位关系表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PmaASchemePostRel pmaASchemePostRel) {
		pmaASchemePostRelService.updateById(pmaASchemePostRel);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "考核方案岗位关系表-通过id删除")
	@ApiOperation(value="考核方案岗位关系表-通过id删除", notes="考核方案岗位关系表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		pmaASchemePostRelService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "考核方案岗位关系表-批量删除")
	@ApiOperation(value="考核方案岗位关系表-批量删除", notes="考核方案岗位关系表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pmaASchemePostRelService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "考核方案岗位关系表-通过id查询")
	@ApiOperation(value="考核方案岗位关系表-通过id查询", notes="考核方案岗位关系表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PmaASchemePostRel pmaASchemePostRel = pmaASchemePostRelService.getById(id);
		return Result.ok(pmaASchemePostRel);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param pmaASchemePostRel
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, PmaASchemePostRel pmaASchemePostRel) {
      return super.exportXls(request, pmaASchemePostRel, PmaASchemePostRel.class, "考核方案岗位关系表");
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
      return super.importExcel(request, response, PmaASchemePostRel.class);
  }

}
