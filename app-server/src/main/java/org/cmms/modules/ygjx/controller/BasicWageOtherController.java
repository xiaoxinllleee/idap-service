package org.cmms.modules.ygjx.controller;

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
import org.cmms.modules.ygjx.entity.BasicWageOther;
import org.cmms.modules.ygjx.service.IBasicWageOtherService;
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
 * @Description: 其他工资
 * @Author: jeecg-boot
 * @Date:   2022-03-02
 * @Version: V1.0
 */
@Slf4j
@Api(tags="其他工资")
@RestController
@RequestMapping("/ygjx/basicWageOther")
public class BasicWageOtherController extends JeecgController<BasicWageOther, IBasicWageOtherService> {
	@Autowired
	private IBasicWageOtherService basicWageOtherService;
	
	/**
	 * 分页列表查询
	 *
	 * @param basicWageOther
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "其他工资-分页列表查询")
	@ApiOperation(value="其他工资-分页列表查询", notes="其他工资-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BasicWageOther basicWageOther,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BasicWageOther> queryWrapper = QueryGenerator.initQueryWrapper(basicWageOther, req.getParameterMap());
		Page<BasicWageOther> page = new Page<BasicWageOther>(pageNo, pageSize);
		IPage<BasicWageOther> pageList = basicWageOtherService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param basicWageOther
	 * @return
	 */
	@AutoLog(value = "其他工资-添加")
	@ApiOperation(value="其他工资-添加", notes="其他工资-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BasicWageOther basicWageOther) {
		basicWageOtherService.save(basicWageOther);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param basicWageOther
	 * @return
	 */
	@AutoLog(value = "其他工资-编辑")
	@ApiOperation(value="其他工资-编辑", notes="其他工资-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BasicWageOther basicWageOther) {
		basicWageOtherService.updateById(basicWageOther);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "其他工资-通过id删除")
	@ApiOperation(value="其他工资-通过id删除", notes="其他工资-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		basicWageOtherService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "其他工资-批量删除")
	@ApiOperation(value="其他工资-批量删除", notes="其他工资-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.basicWageOtherService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "其他工资-通过id查询")
	@ApiOperation(value="其他工资-通过id查询", notes="其他工资-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BasicWageOther basicWageOther = basicWageOtherService.getById(id);
		return Result.ok(basicWageOther);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param basicWageOther
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, BasicWageOther basicWageOther) {
      return super.exportXls(request, basicWageOther, BasicWageOther.class, "其他工资");
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
      return super.importExcel(request, response, BasicWageOther.class);
  }

}
