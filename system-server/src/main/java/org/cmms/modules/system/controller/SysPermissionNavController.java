package org.cmms.modules.system.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.system.entity.SysPermission;
import org.cmms.modules.system.entity.SysPermissionNav;
import org.cmms.modules.system.service.ISysPermissionNavService;
import java.util.Date;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.service.ISysPermissionService;
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
 * @Description: 快速导航
 * @Author: jeecg-boot
 * @Date:   2022-01-12
 * @Version: V1.0
 */
@Slf4j
@Api(tags="快速导航")
@RestController
@RequestMapping("/system/sysPermissionNav")
public class SysPermissionNavController extends JeecgController<SysPermissionNav, ISysPermissionNavService> {
	@Autowired
	private ISysPermissionNavService sysPermissionNavService;
	@Autowired
	ISysPermissionService sysPermissionService;
	/**
	 * 分页列表查询
	 *
	 * @param sysPermissionNav
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "快速导航-分页列表查询")
	@ApiOperation(value="快速导航-分页列表查询", notes="快速导航-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SysPermissionNav sysPermissionNav,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SysPermissionNav> queryWrapper = QueryGenerator.initQueryWrapper(sysPermissionNav, req.getParameterMap());
		Page<SysPermissionNav> page = new Page<SysPermissionNav>(pageNo, pageSize);
		IPage<SysPermissionNav> pageList = sysPermissionNavService.page(page, queryWrapper);
		return Result.ok(pageList);
	}


	 @GetMapping(value = "/listAll")
	 public Result<?> listAll(String sfly ,String zyid) {
		 LambdaQueryWrapper<SysPermissionNav> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		 lambdaQueryWrapper.eq(SysPermissionNav::getUsername,getUsername());
		 if (StringUtils.isNotBlank(sfly) && "1".equals(sfly))
		 	lambdaQueryWrapper.eq(SysPermissionNav::getSfly,"1");
		 lambdaQueryWrapper.orderByDesc(SysPermissionNav::getSort);
		 List<SysPermissionNav> list = sysPermissionNavService.list(lambdaQueryWrapper);
		 if (StringUtils.isNotBlank(zyid) && "1".equals(zyid) && CollUtil.isNotEmpty(list)){
			 List<String> ids = list.stream().map(SysPermissionNav::getPermissionId).collect(Collectors.toList());
			 return Result.ok(ids);
		 }
		 if (StringUtils.isNotBlank(sfly) && "1".equals(sfly) && CollUtil.isNotEmpty(list) && list.size()>10){
			 return Result.ok(list.subList(0,10));
		 }
		 return Result.ok(list);
	 }

	 @RequestMapping("/saveNav")
	 public Result<?> saveNav(@RequestBody SysPermissionNav sysPermissionNav){
		 System.out.println(sysPermissionNav);
		 if (CollUtil.isNotEmpty(sysPermissionNav.getPermissionIds())){
			 //先删除以前的
			 LambdaQueryWrapper<SysPermissionNav> lambdaQueryWrapper = new LambdaQueryWrapper<>();
			 lambdaQueryWrapper.eq(SysPermissionNav::getUsername,getUsername());
			 lambdaQueryWrapper.eq(SysPermissionNav::getSubSystemId,sysPermissionNav.getSubSystemId());
			 sysPermissionNavService.remove(lambdaQueryWrapper);

			  List<String> permissionIds = sysPermissionNav.getPermissionIds();
			  //找到是字节的的
			  List<String> list = sysPermissionService.queryNavList(permissionIds);

			 for (int i = 0; i < permissionIds.size(); i++) {
				 String s = permissionIds.get(i);

				 SysPermissionNav  save = new SysPermissionNav();
				 save.setUsername(getUsername());
				 save.setSubSystemId(sysPermissionNav.getSubSystemId());
				 save.setPermissionId(s);
				 save.setPermissionIdPath(s);
				 save.setSort(i);
				 save.setSfly("2");
				 if (list.contains(s)){
					 save.setSfly("1");
				 }
				 sysPermissionNavService.save(save);
			 }
		 }
		return Result.ok();
	 }
	/**
	 * 添加
	 *
	 * @param sysPermissionNav
	 * @return
	 */
	@AutoLog(value = "快速导航-添加")
	@ApiOperation(value="快速导航-添加", notes="快速导航-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SysPermissionNav sysPermissionNav) {
		sysPermissionNavService.save(sysPermissionNav);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param sysPermissionNav
	 * @return
	 */
	@AutoLog(value = "快速导航-编辑")
	@ApiOperation(value="快速导航-编辑", notes="快速导航-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SysPermissionNav sysPermissionNav) {
		sysPermissionNavService.updateById(sysPermissionNav);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "快速导航-通过id删除")
	@ApiOperation(value="快速导航-通过id删除", notes="快速导航-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sysPermissionNavService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "快速导航-批量删除")
	@ApiOperation(value="快速导航-批量删除", notes="快速导航-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sysPermissionNavService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "快速导航-通过id查询")
	@ApiOperation(value="快速导航-通过id查询", notes="快速导航-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SysPermissionNav sysPermissionNav = sysPermissionNavService.getById(id);
		return Result.ok(sysPermissionNav);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sysPermissionNav
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SysPermissionNav sysPermissionNav) {
      return super.exportXls(request, sysPermissionNav, SysPermissionNav.class, "快速导航");
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
      return super.importExcel(request, response, SysPermissionNav.class);
  }

  @RequestMapping("/queryBySubId")
  public Result<?> queryBySubId(String subSystemId){
	  if (StringUtils.isBlank(subSystemId))
		  return Result.ok();
	  LambdaQueryWrapper<SysPermissionNav> lambdaQueryWrapper = new LambdaQueryWrapper<>();
	  lambdaQueryWrapper.eq(SysPermissionNav::getUsername,getUsername());
	  lambdaQueryWrapper.eq(SysPermissionNav::getSubSystemId,subSystemId);
	  List<SysPermissionNav> list = sysPermissionNavService.list(lambdaQueryWrapper);
	  if (CollUtil.isNotEmpty(list)){
		  List<String> ids = list.stream().map(SysPermissionNav::getPermissionId).collect(Collectors.toList());
		  return Result.ok(ids);
	  }
	  return Result.ok(list);
  }

  @RequestMapping("/delAll")
  public Result<?> delAll(){
	  LambdaQueryWrapper<SysPermissionNav> lambdaQueryWrapper = new LambdaQueryWrapper<>();
	  lambdaQueryWrapper.eq(SysPermissionNav::getUsername,getUsername());
	  sysPermissionNavService.remove(lambdaQueryWrapper);
	  return Result.ok();
  }


}
