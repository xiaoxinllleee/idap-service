package org.cmms.modules.ygjx.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ygjx.entity.ErpWageJbgz140;
import org.cmms.modules.ygjx.entity.ErpWageJbgzglCl;
import org.cmms.modules.ygjx.service.IErpWageJbgz140Service;
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
 * @Description: 祁东基本工资
 * @Author: jeecg-boot
 * @Date:   2023-03-01
 * @Version: V1.0
 */
@Slf4j
@Api(tags="祁东基本工资")
@RestController
@RequestMapping("/ygjx/erpWageJbgz140")
public class ErpWageJbgz140Controller extends JeecgController<ErpWageJbgz140, IErpWageJbgz140Service> {
	
	/**
	 * 分页列表查询
	 *
	 * @param ErpWageJbgz140
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "祁东基本工资-分页列表查询")
	@ApiOperation(value="祁东基本工资-分页列表查询", notes="祁东基本工资-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ErpWageJbgz140 ErpWageJbgz140,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ErpWageJbgz140> queryWrapper = QueryGenerator.initQueryWrapper(ErpWageJbgz140, req.getParameterMap());
		Page<ErpWageJbgz140> page = new Page<ErpWageJbgz140>(pageNo, pageSize);
		IPage<ErpWageJbgz140> pageList = service.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 @GetMapping(value = "/listAll")
	 public Result<?> listAll(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
							  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
							  HttpServletRequest req) {
		 Page<ErpWageJbgz140> page = new Page<ErpWageJbgz140>(pageNo, pageSize);
		 LambdaQueryWrapper<ErpWageJbgz140> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		 lambdaQueryWrapper.eq(ErpWageJbgz140::getYggh,getWorkNo());
		 lambdaQueryWrapper.orderByDesc(ErpWageJbgz140::getGzyf);
		 IPage<ErpWageJbgz140> pageList = service.page(page, lambdaQueryWrapper);
		 return Result.ok(pageList);
	 }
	
	/**
	 * 添加
	 *
	 * @param ErpWageJbgz140
	 * @return
	 */
	@AutoLog(value = "祁东基本工资-添加")
	@ApiOperation(value="祁东基本工资-添加", notes="祁东基本工资-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ErpWageJbgz140 ErpWageJbgz140) {
		service.save(ErpWageJbgz140);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param ErpWageJbgz140
	 * @return
	 */
	@AutoLog(value = "祁东基本工资-编辑")
	@ApiOperation(value="祁东基本工资-编辑", notes="祁东基本工资-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ErpWageJbgz140 ErpWageJbgz140) {
		service.updateById(ErpWageJbgz140);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "祁东基本工资-通过id删除")
	@ApiOperation(value="祁东基本工资-通过id删除", notes="祁东基本工资-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		service.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "祁东基本工资-批量删除")
	@ApiOperation(value="祁东基本工资-批量删除", notes="祁东基本工资-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "祁东基本工资-通过id查询")
	@ApiOperation(value="祁东基本工资-通过id查询", notes="祁东基本工资-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ErpWageJbgz140 ErpWageJbgz140 = service.getById(id);
		return Result.ok(ErpWageJbgz140);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ErpWageJbgz140
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ErpWageJbgz140 ErpWageJbgz140) {
      return super.exportXls(request, ErpWageJbgz140, ErpWageJbgz140.class, "祁东基本工资");
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
      return super.importExcel(request, response, ErpWageJbgz140.class);
  }



}
