package org.cmms.modules.system.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.entity.SysDepartmentSign;
import org.cmms.modules.system.service.ISysDepartmentSignService;
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
 * @Description: 部门印章管理
 * @Author: jeecg-boot
 * @Date:   2023-09-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="部门印章管理")
@RestController
@RequestMapping("/sys/sysDepartmentSign")
public class SysDepartmentSignController extends JeecgController<SysDepartmentSign, ISysDepartmentSignService> {
	@Autowired
	private ISysDepartmentSignService sysDepartmentSignService;
	
	/**
	 * 分页列表查询
	 *
	 * @param sysDepartmentSign
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "部门印章管理-分页列表查询")
	@ApiOperation(value="部门印章管理-分页列表查询", notes="部门印章管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SysDepartmentSign sysDepartmentSign,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SysDepartmentSign> queryWrapper = QueryGenerator.initQueryWrapper(sysDepartmentSign, req.getParameterMap());
		queryWrapper.orderByDesc("scsj");
		Page<SysDepartmentSign> page = new Page<SysDepartmentSign>(pageNo, pageSize);
		IPage<SysDepartmentSign> pageList = sysDepartmentSignService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param sysDepartmentSign
	 * @return
	 */
	@AutoLog(value = "部门印章管理-添加")
	@ApiOperation(value="部门印章管理-添加", notes="部门印章管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SysDepartmentSign sysDepartmentSign) {
		QueryWrapper<SysDepartmentSign> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("depart_id",sysDepartmentSign.getDepartId());
		sysDepartmentSignService.remove(queryWrapper);
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		sysDepartmentSign.setScsj(new Date());
		sysDepartmentSign.setScr(sysUser.getWorkNo());
		sysDepartmentSign.setZllj(uploadpath+ File.separator+sysDepartmentSign.getFwlj());
		sysDepartmentSignService.save(sysDepartmentSign);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param sysDepartmentSign
	 * @return
	 */
	@AutoLog(value = "部门印章管理-编辑")
	@ApiOperation(value="部门印章管理-编辑", notes="部门印章管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SysDepartmentSign sysDepartmentSign) {
		sysDepartmentSignService.updateById(sysDepartmentSign);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "部门印章管理-通过id删除")
	@ApiOperation(value="部门印章管理-通过id删除", notes="部门印章管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sysDepartmentSignService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "部门印章管理-批量删除")
	@ApiOperation(value="部门印章管理-批量删除", notes="部门印章管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sysDepartmentSignService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "部门印章管理-通过id查询")
	@ApiOperation(value="部门印章管理-通过id查询", notes="部门印章管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SysDepartmentSign sysDepartmentSign = sysDepartmentSignService.getById(id);
		return Result.ok(sysDepartmentSign);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sysDepartmentSign
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SysDepartmentSign sysDepartmentSign) {
      return super.exportXls(request, sysDepartmentSign, SysDepartmentSign.class, "部门印章管理");
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
      return super.importExcel(request, response, SysDepartmentSign.class);
  }

}
