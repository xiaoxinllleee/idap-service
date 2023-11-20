package org.cmms.modules.hr.yggl.yggwxxytj.controller;

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
import org.cmms.modules.hr.yggl.yggwxxytj.entity.HrBasStaffpostM;
import org.cmms.modules.hr.yggl.yggwxxytj.service.IHrBasStaffpostMService;
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
 * @Description: 员工岗位信息月统计
 * @Author: jeecg-boot
 * @Date:   2020-11-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="员工岗位信息月统计")
@RestController
@RequestMapping("/yggwxxytj/hrBasStaffpostM")
public class HrBasStaffpostMController extends JeecgController<HrBasStaffpostM, IHrBasStaffpostMService> {
	@Autowired
	private IHrBasStaffpostMService hrBasStaffpostMService;
	
	/**
	 * 分页列表查询
	 *
	 * @param hrBasStaffpostM
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "员工岗位信息月统计-分页列表查询")
	@ApiOperation(value="员工岗位信息月统计-分页列表查询", notes="员工岗位信息月统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(HrBasStaffpostM hrBasStaffpostM,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<HrBasStaffpostM> queryWrapper = QueryGenerator.initQueryWrapper(hrBasStaffpostM, req.getParameterMap());
		Page<HrBasStaffpostM> page = new Page<HrBasStaffpostM>(pageNo, pageSize);
		IPage<HrBasStaffpostM> pageList = hrBasStaffpostMService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param hrBasStaffpostM
	 * @return
	 */
	@AutoLog(value = "员工岗位信息月统计-添加")
	@ApiOperation(value="员工岗位信息月统计-添加", notes="员工岗位信息月统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody HrBasStaffpostM hrBasStaffpostM) {
		hrBasStaffpostMService.save(hrBasStaffpostM);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param hrBasStaffpostM
	 * @return
	 */
	@AutoLog(value = "员工岗位信息月统计-编辑")
	@ApiOperation(value="员工岗位信息月统计-编辑", notes="员工岗位信息月统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody HrBasStaffpostM hrBasStaffpostM) {
		hrBasStaffpostMService.updateById(hrBasStaffpostM);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工岗位信息月统计-通过id删除")
	@ApiOperation(value="员工岗位信息月统计-通过id删除", notes="员工岗位信息月统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		hrBasStaffpostMService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "员工岗位信息月统计-批量删除")
	@ApiOperation(value="员工岗位信息月统计-批量删除", notes="员工岗位信息月统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.hrBasStaffpostMService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工岗位信息月统计-通过id查询")
	@ApiOperation(value="员工岗位信息月统计-通过id查询", notes="员工岗位信息月统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		HrBasStaffpostM hrBasStaffpostM = hrBasStaffpostMService.getById(id);
		return Result.ok(hrBasStaffpostM);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param hrBasStaffpostM
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HrBasStaffpostM hrBasStaffpostM) {
      return super.exportXls(request, hrBasStaffpostM, HrBasStaffpostM.class, "员工岗位信息月统计");
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
      return super.importExcel(request, response, HrBasStaffpostM.class);
  }

}
