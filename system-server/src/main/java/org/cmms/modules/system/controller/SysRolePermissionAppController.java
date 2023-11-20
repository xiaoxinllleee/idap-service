package org.cmms.modules.system.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.entity.SysRolePermissionApp;
import org.cmms.modules.system.service.ISysRolePermissionAppService;
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
 * @Description: app权限
 * @Author: jeecg-boot
 * @Date:   2022-06-20
 * @Version: V1.0
 */
@Slf4j
@Api(tags="app权限")
@RestController
@RequestMapping("/system/sysRolePermissionApp")
public class SysRolePermissionAppController extends JeecgController<SysRolePermissionApp, ISysRolePermissionAppService> {
	@Autowired
	private ISysRolePermissionAppService sysRolePermissionAppService;
	
	/**
	 * 分页列表查询
	 *
	 * @param sysRolePermissionApp
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "app权限-分页列表查询")
	@ApiOperation(value="app权限-分页列表查询", notes="app权限-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SysRolePermissionApp sysRolePermissionApp,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SysRolePermissionApp> queryWrapper = QueryGenerator.initQueryWrapper(sysRolePermissionApp, req.getParameterMap());
		Page<SysRolePermissionApp> page = new Page<SysRolePermissionApp>(pageNo, pageSize);
		IPage<SysRolePermissionApp> pageList = sysRolePermissionAppService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param sysRolePermissionApp
	 * @return
	 */
	@AutoLog(value = "app权限-添加")
	@ApiOperation(value="app权限-添加", notes="app权限-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SysRolePermissionApp sysRolePermissionApp) {
		if (StringUtils.isNotBlank(sysRolePermissionApp.getPermissionId())){
			LambdaQueryWrapper<SysRolePermissionApp> lambdaQueryWrapper = new LambdaQueryWrapper<>();
			lambdaQueryWrapper.eq(SysRolePermissionApp::getRoleId,sysRolePermissionApp.getRoleId());
			service.remove(lambdaQueryWrapper);

			String[] split = sysRolePermissionApp.getPermissionId().split(",");
			for (int i = 0; i < split.length; i++) {
				SysRolePermissionApp app = new SysRolePermissionApp();
				app.setRoleId(sysRolePermissionApp.getRoleId());
				app.setPermissionId(split[i]);
				service.save(app);
			}
		}
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param sysRolePermissionApp
	 * @return
	 */
	@AutoLog(value = "app权限-编辑")
	@ApiOperation(value="app权限-编辑", notes="app权限-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SysRolePermissionApp sysRolePermissionApp) {
		sysRolePermissionAppService.updateById(sysRolePermissionApp);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "app权限-通过id删除")
	@ApiOperation(value="app权限-通过id删除", notes="app权限-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sysRolePermissionAppService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "app权限-批量删除")
	@ApiOperation(value="app权限-批量删除", notes="app权限-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sysRolePermissionAppService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "app权限-通过id查询")
	@ApiOperation(value="app权限-通过id查询", notes="app权限-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SysRolePermissionApp sysRolePermissionApp = sysRolePermissionAppService.getById(id);
		return Result.ok(sysRolePermissionApp);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sysRolePermissionApp
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SysRolePermissionApp sysRolePermissionApp) {
      return super.exportXls(request, sysRolePermissionApp, SysRolePermissionApp.class, "app权限");
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
      return super.importExcel(request, response, SysRolePermissionApp.class);
  }


  @RequestMapping("/getRoleAppList")
  public Result<?> getRoleAppList(String roleId){
	  LambdaQueryWrapper<SysRolePermissionApp> lambdaQueryWrapper = new LambdaQueryWrapper<>();
	  lambdaQueryWrapper.eq(SysRolePermissionApp::getRoleId,roleId);
	  List<SysRolePermissionApp> list = service.list(lambdaQueryWrapper);
	  return Result.ok(list.stream().map(SysRolePermission -> String.valueOf(SysRolePermission.getPermissionId())).collect(Collectors.toList()));
  }
}
