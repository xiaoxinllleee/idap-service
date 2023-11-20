package org.cmms.modules.khlc.khfagl.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.khlc.khfagl.entity.PmaASchemeMenu;
import org.cmms.modules.khlc.khfagl.model.SchemeMenulTreeModel;
import org.cmms.modules.khlc.khfagl.service.IPmaASchemeMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 考核方案目录
 * @Author: jeecg-boot
 * @Date:   2021-01-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="考核方案目录")
@RestController
@RequestMapping("/khlc/khfagl/pmaASchemeMenu")
public class PmaASchemeMenuController extends JeecgController<PmaASchemeMenu, IPmaASchemeMenuService> {
	@Autowired
	private IPmaASchemeMenuService pmaASchemeMenuService;
	
	/**
	 * 分页列表查询
	 *
	 * @param pmaASchemeMenu
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "考核方案目录-分页列表查询")
	@ApiOperation(value="考核方案目录-分页列表查询", notes="考核方案目录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PmaASchemeMenu pmaASchemeMenu,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PmaASchemeMenu> queryWrapper = QueryGenerator.initQueryWrapper(pmaASchemeMenu, req.getParameterMap());
		Page<PmaASchemeMenu> page = new Page<PmaASchemeMenu>(pageNo, pageSize);
		IPage<PmaASchemeMenu> pageList = pmaASchemeMenuService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param pmaASchemeMenu
	 * @return
	 */
	@AutoLog(value = "考核方案目录-添加")
	@ApiOperation(value="考核方案目录-添加", notes="考核方案目录-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PmaASchemeMenu pmaASchemeMenu) {
		pmaASchemeMenuService.save(pmaASchemeMenu);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param pmaASchemeMenu
	 * @return
	 */
	@AutoLog(value = "考核方案目录-编辑")
	@ApiOperation(value="考核方案目录-编辑", notes="考核方案目录-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PmaASchemeMenu pmaASchemeMenu) {
		QueryWrapper<PmaASchemeMenu> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("menu_id", pmaASchemeMenu.getMenuId());
		pmaASchemeMenuService.update(pmaASchemeMenu,queryWrapper);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "考核方案目录-通过id删除")
	@ApiOperation(value="考核方案目录-通过id删除", notes="考核方案目录-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		pmaASchemeMenuService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "考核方案目录-批量删除")
	@ApiOperation(value="考核方案目录-批量删除", notes="考核方案目录-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pmaASchemeMenuService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "考核方案目录-通过id查询")
	@ApiOperation(value="考核方案目录-通过id查询", notes="考核方案目录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PmaASchemeMenu pmaASchemeMenu = pmaASchemeMenuService.getById(id);
		return Result.ok(pmaASchemeMenu);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param pmaASchemeMenu
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, PmaASchemeMenu pmaASchemeMenu) {
      return super.exportXls(request, pmaASchemeMenu, PmaASchemeMenu.class, "考核方案目录");
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
      return super.importExcel(request, response, PmaASchemeMenu.class);
  }




	 @RequestMapping(value = "/queryTreeList", method = RequestMethod.GET)
	 public Result<List<SchemeMenulTreeModel>> queryTreeList() {
		 Result<List<SchemeMenulTreeModel>> result = new Result<>();
		 try {
			 List<SchemeMenulTreeModel> list = pmaASchemeMenuService.queryTreeList();
			 result.setResult(list);
			 result.setSuccess(true);
		 } catch (Exception e) {
			 log.error(e.getMessage(),e);
		 }
		 return result;
	 }

}
