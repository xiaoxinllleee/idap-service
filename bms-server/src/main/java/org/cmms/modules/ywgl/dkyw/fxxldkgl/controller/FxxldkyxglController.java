package org.cmms.modules.ywgl.dkyw.fxxldkgl.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.ywgl.dkyw.fxxldkgl.entity.Fxxldkyxgl;
import org.cmms.modules.ywgl.dkyw.fxxldkgl.service.IFxxldkyxglService;
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
 * @Description: 福祥系列贷款营销管理
 * @Author: jeecg-boot
 * @Date:   2023-09-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="福祥系列贷款营销管理")
@RestController
@RequestMapping("/fxxldkgl/fxxldkyxgl")
public class FxxldkyxglController extends JeecgController<Fxxldkyxgl, IFxxldkyxglService> {
	@Autowired
	private IFxxldkyxglService fxxldkyxglService;
	
	/**
	 * 分页列表查询
	 *
	 * @param fxxldkyxgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "福祥系列贷款营销管理-分页列表查询")
	@ApiOperation(value="福祥系列贷款营销管理-分页列表查询", notes="福祥系列贷款营销管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Fxxldkyxgl fxxldkyxgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Fxxldkyxgl> queryWrapper = QueryGenerator.initQueryWrapper(fxxldkyxgl, req.getParameterMap());
		Page<Fxxldkyxgl> page = new Page<Fxxldkyxgl>(pageNo, pageSize);
		IPage<Fxxldkyxgl> pageList = fxxldkyxglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param fxxldkyxgl
	 * @return
	 */
	@AutoLog(value = "福祥系列贷款营销管理-添加")
	@ApiOperation(value="福祥系列贷款营销管理-添加", notes="福祥系列贷款营销管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Fxxldkyxgl fxxldkyxgl) {
		fxxldkyxglService.save(fxxldkyxgl);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param fxxldkyxgl
	 * @return
	 */
	@AutoLog(value = "福祥系列贷款营销管理-编辑")
	@ApiOperation(value="福祥系列贷款营销管理-编辑", notes="福祥系列贷款营销管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Fxxldkyxgl fxxldkyxgl) {
		fxxldkyxglService.updateById(fxxldkyxgl);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "福祥系列贷款营销管理-通过id删除")
	@ApiOperation(value="福祥系列贷款营销管理-通过id删除", notes="福祥系列贷款营销管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		fxxldkyxglService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "福祥系列贷款营销管理-批量删除")
	@ApiOperation(value="福祥系列贷款营销管理-批量删除", notes="福祥系列贷款营销管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.fxxldkyxglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "福祥系列贷款营销管理-通过id查询")
	@ApiOperation(value="福祥系列贷款营销管理-通过id查询", notes="福祥系列贷款营销管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Fxxldkyxgl fxxldkyxgl = fxxldkyxglService.getById(id);
		return Result.ok(fxxldkyxgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param fxxldkyxgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Fxxldkyxgl fxxldkyxgl) {
      return super.exportXls(request, fxxldkyxgl, Fxxldkyxgl.class, "福祥系列贷款营销管理");
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
      return super.importExcel(request, response, Fxxldkyxgl.class);
  }

}
