package org.cmms.modules.system.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.entity.SysRoleSubSystem;
import org.cmms.modules.system.service.ISysRoleSubSystemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 子系统角色授权管理
 * @Author: jeecg-boot
 * @Date:   2021-01-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags="子系统角色授权管理")
@RestController
@RequestMapping("/sysRoleSubSystem")
public class SysRoleSubSystemController extends JeecgController<SysRoleSubSystem, ISysRoleSubSystemService> {
	@Autowired
	private ISysRoleSubSystemService sysRoleSubSystemService;
	
	/**
	 * 分页列表查询
	 *
	 * @param sysRoleSubSystem
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "子系统角色授权管理-分页列表查询")
	@ApiOperation(value="子系统角色授权管理-分页列表查询", notes="子系统角色授权管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SysRoleSubSystem sysRoleSubSystem,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SysRoleSubSystem> queryWrapper = QueryGenerator.initQueryWrapper(sysRoleSubSystem, req.getParameterMap());
		Page<SysRoleSubSystem> page = new Page<SysRoleSubSystem>(pageNo, pageSize);
		IPage<SysRoleSubSystem> pageList = sysRoleSubSystemService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param sysRoleSubSystem
	 * @return
	 */
	@AutoLog(value = "子系统角色授权管理-添加")
	@ApiOperation(value="子系统角色授权管理-添加", notes="子系统角色授权管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SysRoleSubSystem sysRoleSubSystem) {
		sysRoleSubSystemService.save(sysRoleSubSystem);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param sysRoleSubSystem
	 * @return
	 */
	@AutoLog(value = "子系统角色授权管理-编辑")
	@ApiOperation(value="子系统角色授权管理-编辑", notes="子系统角色授权管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SysRoleSubSystem sysRoleSubSystem) {
		sysRoleSubSystemService.updateById(sysRoleSubSystem);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "子系统角色授权管理-通过id删除")
	@ApiOperation(value="子系统角色授权管理-通过id删除", notes="子系统角色授权管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sysRoleSubSystemService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "子系统角色授权管理-批量删除")
	@ApiOperation(value="子系统角色授权管理-批量删除", notes="子系统角色授权管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sysRoleSubSystemService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "子系统角色授权管理-通过id查询")
	@ApiOperation(value="子系统角色授权管理-通过id查询", notes="子系统角色授权管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SysRoleSubSystem sysRoleSubSystem = sysRoleSubSystemService.getById(id);
		return Result.ok(sysRoleSubSystem);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sysRoleSubSystem
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SysRoleSubSystem sysRoleSubSystem) {
      return super.exportXls(request, sysRoleSubSystem, SysRoleSubSystem.class, "子系统角色授权管理");
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
      return super.importExcel(request, response, SysRoleSubSystem.class);
  }

}
