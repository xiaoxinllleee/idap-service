package org.cmms.modules.khlc.csgl.csml.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.khlc.csgl.csml.entity.PmaFParamMenu;
import org.cmms.modules.khlc.csgl.csml.model.PmaFParamMenuTreeModel;
import org.cmms.modules.khlc.csgl.csml.service.IPmaFParamMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khlc.khfagl.entity.PmaASchemeMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 参数目录
 * @Author: jeecg-boot
 * @Date:   2021-03-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="参数目录")
@RestController
@RequestMapping("/csgl/csml/pmaFParamMenu")
public class PmaFParamMenuController extends JeecgController<PmaFParamMenu, IPmaFParamMenuService> {
	@Autowired
	private IPmaFParamMenuService pmaFParamMenuService;
	
	/**
	 * 分页列表查询
	 *
	 * @param pmaFParamMenu
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "参数目录-分页列表查询")
	@ApiOperation(value="参数目录-分页列表查询", notes="参数目录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PmaFParamMenu pmaFParamMenu,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PmaFParamMenu> queryWrapper = QueryGenerator.initQueryWrapper(pmaFParamMenu, req.getParameterMap());
		Page<PmaFParamMenu> page = new Page<PmaFParamMenu>(pageNo, pageSize);
		IPage<PmaFParamMenu> pageList = pmaFParamMenuService.page(page, queryWrapper);
		return Result.ok(pageList);
	}


	 @RequestMapping(value = "/queryTreeList", method = RequestMethod.GET)
	 public Result<List<PmaFParamMenuTreeModel>> queryTreeList() {
		 Result<List<PmaFParamMenuTreeModel>> result = new Result<>();
		 try {
		 	 System.out.println("1111");
			 List<PmaFParamMenuTreeModel> list = pmaFParamMenuService.queryTreeList();
			 result.setResult(list);
			 result.setSuccess(true);
		 } catch (Exception e) {
			 log.error(e.getMessage(),e);
		 }
		 return result;
	 }

	/**
	 * 添加
	 *
	 * @param pmaFParamMenu
	 * @return
	 */
	@AutoLog(value = "参数目录-添加")
	@ApiOperation(value="参数目录-添加", notes="参数目录-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PmaFParamMenu pmaFParamMenu) {
		pmaFParamMenuService.save(pmaFParamMenu);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param pmaFParamMenu
	 * @return
	 */
	@AutoLog(value = "参数目录-编辑")
	@ApiOperation(value="参数目录-编辑", notes="参数目录-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PmaFParamMenu pmaFParamMenu) {
		pmaFParamMenuService.updateById(pmaFParamMenu);
		return Result.ok("编辑成功!");
	}


	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "参数目录-通过id删除")
	@ApiOperation(value="参数目录-通过id删除", notes="参数目录-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		pmaFParamMenuService.removeById(id);
		return Result.ok("删除成功!");
	}



	 /**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "参数目录-批量删除")
	@ApiOperation(value="参数目录-批量删除", notes="参数目录-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pmaFParamMenuService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "参数目录-通过id查询")
	@ApiOperation(value="参数目录-通过id查询", notes="参数目录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PmaFParamMenu pmaFParamMenu = pmaFParamMenuService.getById(id);
		return Result.ok(pmaFParamMenu);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param pmaFParamMenu
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, PmaFParamMenu pmaFParamMenu) {
      return super.exportXls(request, pmaFParamMenu, PmaFParamMenu.class, "参数目录");
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
      return super.importExcel(request, response, PmaFParamMenu.class);
  }

}
