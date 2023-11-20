package org.cmms.modules.rwzx.rwwcqk.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.qxfk.entity.QxfkPdfImg;
import org.cmms.modules.rwzx.rwcj.entity.TaskCreate;
import org.cmms.modules.rwzx.rwwcqk.entity.VtaskRwwcqk;
import org.cmms.modules.rwzx.rwwcqk.service.IVtaskRwwcqkService;
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
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 任务完成情况
 * @Author: jeecg-boot
 * @Date:   2023-07-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags="任务完成情况")
@RestController
@RequestMapping("/rwwcqk/vtaskRwwcqk")
public class VtaskRwwcqkController extends JeecgController<VtaskRwwcqk, IVtaskRwwcqkService> {
	@Autowired
	private IVtaskRwwcqkService vtaskRwwcqkService;
	
	/**
	 * 分页列表查询
	 *
	 * @param vtaskRwwcqk
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "任务完成情况-分页列表查询")
	@ApiOperation(value="任务完成情况-分页列表查询", notes="任务完成情况-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VtaskRwwcqk vtaskRwwcqk,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VtaskRwwcqk> queryWrapper = QueryGenerator.initQueryWrapper(vtaskRwwcqk, req.getParameterMap());
		Page<VtaskRwwcqk> page = new Page<VtaskRwwcqk>(pageNo, pageSize);
		IPage<VtaskRwwcqk> pageList = vtaskRwwcqkService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	 /**
	  * 分页列表查询
	  *
	  * @return
	  */
	 @GetMapping(value = "/getRwwcqkList")
	 public Result<?> getRwwcqkList() {
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 QueryWrapper<VtaskRwwcqk> queryWrapper =new QueryWrapper<>();
		 queryWrapper.and(i ->i.eq("fn_split_str_db(DXID,',','"+sysUser.getOrgCode()+"')","1").or().eq("DXID","1"));
		 queryWrapper.ge("end_time",DateUtil.beginOfDay(new Date()));
		 queryWrapper.le("start_Time",DateUtil.beginOfDay(new Date()));
		 queryWrapper.orderByDesc("create_Time");
		 List<VtaskRwwcqk> list = vtaskRwwcqkService.list(queryWrapper);
		 return Result.ok(list);
	 }
	/**
	 * 添加
	 *
	 * @param vtaskRwwcqk
	 * @return
	 */
	@AutoLog(value = "任务完成情况-添加")
	@ApiOperation(value="任务完成情况-添加", notes="任务完成情况-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody VtaskRwwcqk vtaskRwwcqk) {
		vtaskRwwcqkService.save(vtaskRwwcqk);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param vtaskRwwcqk
	 * @return
	 */
	@AutoLog(value = "任务完成情况-编辑")
	@ApiOperation(value="任务完成情况-编辑", notes="任务完成情况-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody VtaskRwwcqk vtaskRwwcqk) {
		vtaskRwwcqkService.updateById(vtaskRwwcqk);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "任务完成情况-通过id删除")
	@ApiOperation(value="任务完成情况-通过id删除", notes="任务完成情况-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		vtaskRwwcqkService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "任务完成情况-批量删除")
	@ApiOperation(value="任务完成情况-批量删除", notes="任务完成情况-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.vtaskRwwcqkService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "任务完成情况-通过id查询")
	@ApiOperation(value="任务完成情况-通过id查询", notes="任务完成情况-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VtaskRwwcqk vtaskRwwcqk = vtaskRwwcqkService.getById(id);
		return Result.ok(vtaskRwwcqk);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param vtaskRwwcqk
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, VtaskRwwcqk vtaskRwwcqk) {
      return super.exportXls(request, vtaskRwwcqk, VtaskRwwcqk.class, "任务完成情况");
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
      return super.importExcel(request, response, VtaskRwwcqk.class);
  }

}
