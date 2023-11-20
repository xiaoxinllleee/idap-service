package org.cmms.modules.tjfx.sjhztj.khjlhztj.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.tjfx.sjhztj.khjlhztj.entity.VTjfxHztjKhjl;
import org.cmms.modules.tjfx.sjhztj.khjlhztj.service.IVTjfxHztjKhjlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 全行汇总统计
 * @Author: jeecg-boot
 * @Date:   2023-09-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="全行汇总统计")
@RestController
@RequestMapping("/khjlhztj/vTjfxHztjKhjl")
public class VTjfxHztjKhjlController extends JeecgController<VTjfxHztjKhjl, IVTjfxHztjKhjlService> {
	@Autowired
	private IVTjfxHztjKhjlService vTjfxHztjKhjlService;
	
	/**
	 * 分页列表查询
	 *
	 * @param vTjfxHztjKhjl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "全行汇总统计-分页列表查询")
	@ApiOperation(value="全行汇总统计-分页列表查询", notes="全行汇总统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VTjfxHztjKhjl vTjfxHztjKhjl,
								   @RequestParam(name = "isPc",required = false) String isPc,
								   @RequestParam(name = "column",required = false) String column,
								   @RequestParam(name = "order",required = false) String order,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String ygxm=vTjfxHztjKhjl.getYggh();
		vTjfxHztjKhjl.setYggh(null);
		QueryWrapper<VTjfxHztjKhjl> queryWrapper = QueryGenerator.initQueryWrapper(vTjfxHztjKhjl, req.getParameterMap());
		if(StringUtils.isNotBlank(ygxm)){
			queryWrapper.inSql("yggh","select yggh from Hr_bas_staff where ygxm like '%"+ygxm+"%'");
		}
		Page<VTjfxHztjKhjl> page = new Page<VTjfxHztjKhjl>(pageNo, pageSize);
		Integer[] arr = {1};
		arr[0]=arr[0]+(pageNo-1)*pageSize;
		if (StringUtils.isBlank(column) || StringUtils.isBlank(order)){
			queryWrapper.orderByDesc("bzyxzfs");
			if (StringUtils.isNotBlank(isPc) && "2".equals(isPc)) {
				IPage<VTjfxHztjKhjl> pageList = vTjfxHztjKhjlService.page(page, queryWrapper);
				if (pageNo==1) {
					List<VTjfxHztjKhjl> list = pageList.getRecords().stream().peek(e -> e.setPm(arr[0]++)).collect(Collectors.toList());
					List<VTjfxHztjKhjl> list1 = vTjfxHztjKhjlService.list(queryWrapper);
					list1.set(3, list1.get(list1.size() - 3));
					list1.set(4, list1.get(list1.size() - 2));
					list1.set(5, list1.get(list1.size() - 1));
					list.get(3).setPm(list1.size() - 3);
					list.get(4).setPm(list1.size() - 2);
					list.get(5).setPm(list1.size() - 1);
					pageList.setRecords(list);
				}else{
					Integer[] arr1 = {4};
					arr1[0]=arr1[0]+(pageNo-2)*pageSize;
					List<VTjfxHztjKhjl> list = pageList.getRecords().stream().peek(e -> e.setPm(arr1[0]++)).collect(Collectors.toList());
					pageList.setRecords(list);}
				return Result.ok(pageList);
			}
		}
		IPage<VTjfxHztjKhjl> pageList = vTjfxHztjKhjlService.page(page, queryWrapper);
		pageList.setRecords(pageList.getRecords().stream().peek(e->e.setPm(arr[0]++)).collect(Collectors.toList()));
		return Result.ok(pageList);
	}
	 /**
	  * 获取当前登录的客户经理的汇总统计信息
	  * @return
	  */
	@GetMapping("getHztjInfoByOne")
	public Result<?> getHztjInfoByOne(){
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		QueryWrapper<VTjfxHztjKhjl> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("yggh",sysUser.getWorkNo());
		Page<VTjfxHztjKhjl> page = new Page<VTjfxHztjKhjl>(1, 1);
		IPage<VTjfxHztjKhjl> pageList = vTjfxHztjKhjlService.page(page, queryWrapper);
		return Result.ok(page.getRecords());
	}
	
	/**
	 * 添加
	 *
	 * @param vTjfxHztjKhjl
	 * @return
	 */
	@AutoLog(value = "全行汇总统计-添加")
	@ApiOperation(value="全行汇总统计-添加", notes="全行汇总统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody VTjfxHztjKhjl vTjfxHztjKhjl) {
		vTjfxHztjKhjlService.save(vTjfxHztjKhjl);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param vTjfxHztjKhjl
	 * @return
	 */
	@AutoLog(value = "全行汇总统计-编辑")
	@ApiOperation(value="全行汇总统计-编辑", notes="全行汇总统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody VTjfxHztjKhjl vTjfxHztjKhjl) {
		vTjfxHztjKhjlService.updateById(vTjfxHztjKhjl);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全行汇总统计-通过id删除")
	@ApiOperation(value="全行汇总统计-通过id删除", notes="全行汇总统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		vTjfxHztjKhjlService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "全行汇总统计-批量删除")
	@ApiOperation(value="全行汇总统计-批量删除", notes="全行汇总统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.vTjfxHztjKhjlService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全行汇总统计-通过id查询")
	@ApiOperation(value="全行汇总统计-通过id查询", notes="全行汇总统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VTjfxHztjKhjl vTjfxHztjKhjl = vTjfxHztjKhjlService.getById(id);
		return Result.ok(vTjfxHztjKhjl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param vTjfxHztjKhjl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, VTjfxHztjKhjl vTjfxHztjKhjl) {
      return super.exportXls(request, vTjfxHztjKhjl, VTjfxHztjKhjl.class, "全行汇总统计");
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
      return super.importExcel(request, response, VTjfxHztjKhjl.class);
  }

}
