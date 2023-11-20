package org.cmms.modules.tjfx.xdgtzyb.xzcxdgtzy2.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.tjfx.xdgtzyb.xzcxdgtzy2.entity.Xzcxdgtzy2;
import org.cmms.modules.tjfx.xdgtzyb.xzcxdgtzy2.service.IXzcxdgtzy2Service;
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
 * @Description: 行政村行动挂图作业表2
 * @Author: jeecg-boot
 * @Date:   2020-03-13
 * @Version: V1.0
 */
@Slf4j
@Api(tags="行政村行动挂图作业表2")
@RestController
@RequestMapping("/tjfx.xdgtzyb.xczxdgtzy2/xzcxdgtzy2")
public class Xzcxdgtzy2Controller extends JeecgController<Xzcxdgtzy2, IXzcxdgtzy2Service> {
	@Autowired
	private IXzcxdgtzy2Service xzcxdgtzy2Service;
	
	/**
	 * 分页列表查询
	 *
	 * @param xzcxdgtzy2
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "行政村行动挂图作业表2-分页列表查询")
	@ApiOperation(value="行政村行动挂图作业表2-分页列表查询", notes="行政村行动挂图作业表2-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Xzcxdgtzy2 xzcxdgtzy2,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Xzcxdgtzy2> queryWrapper = QueryGenerator.initQueryWrapper(xzcxdgtzy2, req.getParameterMap());
		Page<Xzcxdgtzy2> page = new Page<Xzcxdgtzy2>(pageNo, pageSize);
		IPage<Xzcxdgtzy2> pageList = xzcxdgtzy2Service.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param xzcxdgtzy2
	 * @return
	 */
	@AutoLog(value = "行政村行动挂图作业表2-添加")
	@ApiOperation(value="行政村行动挂图作业表2-添加", notes="行政村行动挂图作业表2-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Xzcxdgtzy2 xzcxdgtzy2) {
		xzcxdgtzy2Service.save(xzcxdgtzy2);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param xzcxdgtzy2
	 * @return
	 */
	@AutoLog(value = "行政村行动挂图作业表2-编辑")
	@ApiOperation(value="行政村行动挂图作业表2-编辑", notes="行政村行动挂图作业表2-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Xzcxdgtzy2 xzcxdgtzy2) {
		xzcxdgtzy2Service.updateById(xzcxdgtzy2);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "行政村行动挂图作业表2-通过id删除")
	@ApiOperation(value="行政村行动挂图作业表2-通过id删除", notes="行政村行动挂图作业表2-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		xzcxdgtzy2Service.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "行政村行动挂图作业表2-批量删除")
	@ApiOperation(value="行政村行动挂图作业表2-批量删除", notes="行政村行动挂图作业表2-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.xzcxdgtzy2Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "行政村行动挂图作业表2-通过id查询")
	@ApiOperation(value="行政村行动挂图作业表2-通过id查询", notes="行政村行动挂图作业表2-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Xzcxdgtzy2 xzcxdgtzy2 = xzcxdgtzy2Service.getById(id);
		return Result.ok(xzcxdgtzy2);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param xzcxdgtzy2
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Xzcxdgtzy2 xzcxdgtzy2) {
      return super.exportXls(request, xzcxdgtzy2, Xzcxdgtzy2.class, "行政村行动挂图作业表2");
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
      return super.importExcel(request, response, Xzcxdgtzy2.class);
  }

}
