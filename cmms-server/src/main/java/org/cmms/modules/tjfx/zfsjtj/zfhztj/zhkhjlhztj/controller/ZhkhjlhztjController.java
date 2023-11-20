package org.cmms.modules.tjfx.zfsjtj.zfhztj.zhkhjlhztj.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import lombok.extern.slf4j.Slf4j;

import org.cmms.modules.tjfx.zfsjtj.zfhztj.zhkhjlhztj.entity.Zhkhjlhztj;
import org.cmms.modules.tjfx.zfsjtj.zfhztj.zhkhjlhztj.service.IZhkhjlhztjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 支行客户经理汇总统计
 * @Author: jeecg-boot
 * @Date:   2023-02-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行客户经理汇总统计")
@RestController
@RequestMapping("/zfhztj/zhkhjlhztj")
public class ZhkhjlhztjController extends JeecgController<Zhkhjlhztj, IZhkhjlhztjService> {
	@Autowired
	private IZhkhjlhztjService zhkhjlhztjService;
	
	/**
	 * 分页列表查询
	 *
	 * @param zhkhjlhztj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行客户经理汇总统计-分页列表查询")
	@ApiOperation(value="支行客户经理汇总统计-分页列表查询", notes="支行客户经理汇总统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zhkhjlhztj zhkhjlhztj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Zhkhjlhztj> queryWrapper = QueryGenerator.initQueryWrapper(zhkhjlhztj, req.getParameterMap());
		Page<Zhkhjlhztj> page = new Page<Zhkhjlhztj>(pageNo, pageSize);
		IPage<Zhkhjlhztj> pageList = zhkhjlhztjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param zhkhjlhztj
	 * @return
	 */
	@AutoLog(value = "支行客户经理汇总统计-添加")
	@ApiOperation(value="支行客户经理汇总统计-添加", notes="支行客户经理汇总统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zhkhjlhztj zhkhjlhztj) {
		zhkhjlhztjService.save(zhkhjlhztj);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param zhkhjlhztj
	 * @return
	 */
	@AutoLog(value = "支行客户经理汇总统计-编辑")
	@ApiOperation(value="支行客户经理汇总统计-编辑", notes="支行客户经理汇总统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zhkhjlhztj zhkhjlhztj) {
		zhkhjlhztjService.updateById(zhkhjlhztj);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行客户经理汇总统计-通过id删除")
	@ApiOperation(value="支行客户经理汇总统计-通过id删除", notes="支行客户经理汇总统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zhkhjlhztjService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "支行客户经理汇总统计-批量删除")
	@ApiOperation(value="支行客户经理汇总统计-批量删除", notes="支行客户经理汇总统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zhkhjlhztjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行客户经理汇总统计-通过id查询")
	@ApiOperation(value="支行客户经理汇总统计-通过id查询", notes="支行客户经理汇总统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zhkhjlhztj zhkhjlhztj = zhkhjlhztjService.getById(id);
		return Result.ok(zhkhjlhztj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zhkhjlhztj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zhkhjlhztj zhkhjlhztj) {
      return super.exportXls(request, zhkhjlhztj, Zhkhjlhztj.class, "支行客户经理汇总统计");
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
      return super.importExcel(request, response, Zhkhjlhztj.class);
  }

}
