package org.cmms.modules.zhgl.khrl.controller;

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
import org.cmms.modules.zhgl.khrl.entity.AppRlDsp;
import org.cmms.modules.zhgl.khrl.service.IAppRlDspService;
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
 * @Description: app认领待审批
 * @Author: jeecg-boot
 * @Date:   2022-03-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="app认领待审批")
@RestController
@RequestMapping("/khrl/appRlDsp")
public class AppRlDspController extends JeecgController<AppRlDsp, IAppRlDspService> {
	@Autowired
	private IAppRlDspService appRlDspService;
	
	/**
	 * 分页列表查询
	 *
	 * @param appRlDsp
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "app认领待审批-分页列表查询")
	@ApiOperation(value="app认领待审批-分页列表查询", notes="app认领待审批-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(AppRlDsp appRlDsp,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AppRlDsp> queryWrapper = QueryGenerator.initQueryWrapper(appRlDsp, req.getParameterMap());
		Page<AppRlDsp> page = new Page<AppRlDsp>(pageNo, pageSize);
		IPage<AppRlDsp> pageList = appRlDspService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param appRlDsp
	 * @return
	 */
	@AutoLog(value = "app认领待审批-添加")
	@ApiOperation(value="app认领待审批-添加", notes="app认领待审批-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody AppRlDsp appRlDsp) {
		appRlDspService.save(appRlDsp);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param appRlDsp
	 * @return
	 */
	@AutoLog(value = "app认领待审批-编辑")
	@ApiOperation(value="app认领待审批-编辑", notes="app认领待审批-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AppRlDsp appRlDsp) {
		appRlDspService.updateById(appRlDsp);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "app认领待审批-通过id删除")
	@ApiOperation(value="app认领待审批-通过id删除", notes="app认领待审批-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		appRlDspService.removeById(id);
		return Result.ok("删除成功!");
	}

	 @RequestMapping(value = "/deleteNotInRl")
	 public Result<?> deleteNotInRl(String id) {
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq("rl_id",id);
		appRlDspService.remove(queryWrapper);
		return Result.ok("删除成功!");
	 }
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "app认领待审批-批量删除")
	@ApiOperation(value="app认领待审批-批量删除", notes="app认领待审批-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.appRlDspService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "app认领待审批-通过id查询")
	@ApiOperation(value="app认领待审批-通过id查询", notes="app认领待审批-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AppRlDsp appRlDsp = appRlDspService.getById(id);
		return Result.ok(appRlDsp);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param appRlDsp
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, AppRlDsp appRlDsp) {
      return super.exportXls(request, appRlDsp, AppRlDsp.class, "app认领待审批");
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
      return super.importExcel(request, response, AppRlDsp.class);
  }

}
