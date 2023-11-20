package org.cmms.modules.tjfx.zfsjtj.zfhztj.zhhztj.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.zfsjtj.zfhztj.zhhztj.entity.Zhhztj;
import org.cmms.modules.tjfx.zfsjtj.zfhztj.zhhztj.service.IZhhztjService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 走访汇总统计
 * @Author: jeecg-boot
 * @Date:   2023-02-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="走访汇总统计")
@RestController
@RequestMapping("/zfhztj/zhhztj")
public class ZhhztjController extends JeecgController<Zhhztj, IZhhztjService> {
	@Autowired
	private IZhhztjService zhhztjService;
	
	/**
	 * 分页列表查询
	 *
	 * @param zhhztj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "走访汇总统计-分页列表查询")
	@ApiOperation(value="走访汇总统计-分页列表查询", notes="走访汇总统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zhhztj zhhztj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Zhhztj> queryWrapper = QueryGenerator.initQueryWrapper(zhhztj, req.getParameterMap());
		Page<Zhhztj> page = new Page<Zhhztj>(pageNo, pageSize);
		IPage<Zhhztj> pageList = zhhztjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param zhhztj
	 * @return
	 */
	@AutoLog(value = "走访汇总统计-添加")
	@ApiOperation(value="走访汇总统计-添加", notes="走访汇总统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zhhztj zhhztj) {
		zhhztjService.save(zhhztj);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param zhhztj
	 * @return
	 */
	@AutoLog(value = "走访汇总统计-编辑")
	@ApiOperation(value="走访汇总统计-编辑", notes="走访汇总统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zhhztj zhhztj) {
		zhhztjService.updateById(zhhztj);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "走访汇总统计-通过id删除")
	@ApiOperation(value="走访汇总统计-通过id删除", notes="走访汇总统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zhhztjService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "走访汇总统计-批量删除")
	@ApiOperation(value="走访汇总统计-批量删除", notes="走访汇总统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zhhztjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "走访汇总统计-通过id查询")
	@ApiOperation(value="走访汇总统计-通过id查询", notes="走访汇总统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zhhztj zhhztj = zhhztjService.getById(id);
		return Result.ok(zhhztj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zhhztj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zhhztj zhhztj) {
      return super.exportXls(request, zhhztj, Zhhztj.class, "走访汇总统计");
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
      return super.importExcel(request, response, Zhhztj.class);
  }

}
