package org.cmms.modules.yxdygl.yxdyglmain.controller;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMenu;
import org.cmms.modules.yxdygl.yxdyglmain.service.IYxdyglMainService;
import org.cmms.modules.yxdygl.yxdyglmain.service.IYxdyglMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 营销单元菜单
 * @Author: jeecg-boot
 * @Date:   2021-11-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags="营销单元菜单")
@RestController
@RequestMapping("/meun/yxdyglMenu")
public class YxdyglMenuController extends JeecgController<YxdyglMenu, IYxdyglMenuService> {
	@Autowired
	IYxdyglMainService yxdyglMainService;
	/**
	 * 分页列表查询
	 *
	 * @param yxdyglMenu
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "营销单元菜单-分页列表查询")
	@ApiOperation(value="营销单元菜单-分页列表查询", notes="营销单元菜单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(YxdyglMenu yxdyglMenu,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<YxdyglMenu> queryWrapper = QueryGenerator.initQueryWrapper(yxdyglMenu, req.getParameterMap());
		Page<YxdyglMenu> page = new Page<YxdyglMenu>(pageNo, pageSize);
		IPage<YxdyglMenu> pageList = service.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param yxdyglMenu
	 * @return
	 */
	@AutoLog(value = "营销单元菜单-添加")
	@ApiOperation(value="营销单元菜单-添加", notes="营销单元菜单-添加")
	@PostMapping(value = "/add")
	@Transactional
	public Result<?> add(@RequestBody YxdyglMenu yxdyglMenu) {
		service.save(yxdyglMenu);

		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param yxdyglMenu
	 * @return
	 */
	@AutoLog(value = "营销单元菜单-编辑")
	@ApiOperation(value="营销单元菜单-编辑", notes="营销单元菜单-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody YxdyglMenu yxdyglMenu) {
		service.updateById(yxdyglMenu);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "营销单元菜单-通过id删除")
	@ApiOperation(value="营销单元菜单-通过id删除", notes="营销单元菜单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		LambdaQueryWrapper<YxdyglMain> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(YxdyglMain::getParentId,id);
		List<YxdyglMain> list = yxdyglMainService.list(lambdaQueryWrapper);
		if (CollUtil.isNotEmpty(list)){
			return Result.error("有子单元存在，请先删除子单元后删除！");
		}
		service.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "营销单元菜单-批量删除")
	@ApiOperation(value="营销单元菜单-批量删除", notes="营销单元菜单-批量删除")
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
	@AutoLog(value = "营销单元菜单-通过id查询")
	@ApiOperation(value="营销单元菜单-通过id查询", notes="营销单元菜单-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		YxdyglMenu yxdyglMenu = service.getById(id);
		return Result.ok(yxdyglMenu);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param yxdyglMenu
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, YxdyglMenu yxdyglMenu) {
      return super.exportXls(request, yxdyglMenu, YxdyglMenu.class, "营销单元菜单");
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
      return super.importExcel(request, response, YxdyglMenu.class);
  }


  @RequestMapping(value = "getYxdyglMenuTreeDate")
  public Result<?> getYxdyglMenuTreeDate(String khjl){
	  return Result.ok(service.listTree(khjl));
  }

}
