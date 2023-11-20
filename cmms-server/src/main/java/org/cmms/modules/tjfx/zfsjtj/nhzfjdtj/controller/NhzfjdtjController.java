package org.cmms.modules.tjfx.zfsjtj.nhzfjdtj.controller;

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

import org.cmms.modules.tjfx.zfsjtj.nhzfjdtj.entity.Nhzfjdtj;
import org.cmms.modules.tjfx.zfsjtj.nhzfjdtj.service.INhzfjdtjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 农户走访进度统计
 * @Author: jeecg-boot
 * @Date:   2023-02-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="农户走访进度统计")
@RestController
@RequestMapping("/zfsjtj/nhzfjdtj")
public class NhzfjdtjController extends JeecgController<Nhzfjdtj, INhzfjdtjService> {
	@Autowired
	private INhzfjdtjService nhzfjdtjService;
	
	/**
	 * 分页列表查询
	 *
	 * @param nhzfjdtj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "农户走访进度统计-分页列表查询")
	@ApiOperation(value="农户走访进度统计-分页列表查询", notes="农户走访进度统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Nhzfjdtj nhzfjdtj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Nhzfjdtj> queryWrapper = QueryGenerator.initQueryWrapper(nhzfjdtj, req.getParameterMap());
		Page<Nhzfjdtj> page = new Page<Nhzfjdtj>(pageNo, pageSize);
		IPage<Nhzfjdtj> pageList = nhzfjdtjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param nhzfjdtj
	 * @return
	 */
	@AutoLog(value = "农户走访进度统计-添加")
	@ApiOperation(value="农户走访进度统计-添加", notes="农户走访进度统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Nhzfjdtj nhzfjdtj) {
		nhzfjdtjService.save(nhzfjdtj);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param nhzfjdtj
	 * @return
	 */
	@AutoLog(value = "农户走访进度统计-编辑")
	@ApiOperation(value="农户走访进度统计-编辑", notes="农户走访进度统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Nhzfjdtj nhzfjdtj) {
		nhzfjdtjService.updateById(nhzfjdtj);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "农户走访进度统计-通过id删除")
	@ApiOperation(value="农户走访进度统计-通过id删除", notes="农户走访进度统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		nhzfjdtjService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "农户走访进度统计-批量删除")
	@ApiOperation(value="农户走访进度统计-批量删除", notes="农户走访进度统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.nhzfjdtjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "农户走访进度统计-通过id查询")
	@ApiOperation(value="农户走访进度统计-通过id查询", notes="农户走访进度统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Nhzfjdtj nhzfjdtj = nhzfjdtjService.getById(id);
		return Result.ok(nhzfjdtj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param nhzfjdtj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Nhzfjdtj nhzfjdtj) {
      return super.exportXls(request, nhzfjdtj, Nhzfjdtj.class, "农户走访进度统计");
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
      return super.importExcel(request, response, Nhzfjdtj.class);
  }

}
