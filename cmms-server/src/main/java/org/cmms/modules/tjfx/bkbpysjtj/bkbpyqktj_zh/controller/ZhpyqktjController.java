package org.cmms.modules.tjfx.bkbpysjtj.bkbpyqktj_zh.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.tjfx.bkbpysjtj.bkbpyqktj_zh.entity.Zhpyqktj;
import org.cmms.modules.tjfx.bkbpysjtj.bkbpyqktj_zh.service.IzhpyqktjService;
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
 * @Description: 评议情况统计(支行)
 * @Author: jeecg-boot
 * @Date:   2022-11-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="评议情况统计(支行)")
@RestController
@RequestMapping("/bkbpyqktj_zh/zhpyqktj")
public class ZhpyqktjController extends JeecgController<Zhpyqktj, IzhpyqktjService> {
	@Autowired
	private IzhpyqktjService zhpyqktjService;
	
	/**
	 * 分页列表查询
	 *
	 * @param zhpyqktj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "评议情况统计(支行)-分页列表查询")
	@ApiOperation(value="评议情况统计(支行)-分页列表查询", notes="评议情况统计(支行)-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zhpyqktj zhpyqktj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Zhpyqktj> queryWrapper = QueryGenerator.initQueryWrapper(zhpyqktj, req.getParameterMap());
		Page<Zhpyqktj> page = new Page<Zhpyqktj>(pageNo, pageSize);
		IPage<Zhpyqktj> pageList = zhpyqktjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param zhpyqktj
	 * @return
	 */
	@AutoLog(value = "评议情况统计(支行)-添加")
	@ApiOperation(value="评议情况统计(支行)-添加", notes="评议情况统计(支行)-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zhpyqktj zhpyqktj) {
		zhpyqktjService.save(zhpyqktj);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param zhpyqktj
	 * @return
	 */
	@AutoLog(value = "评议情况统计(支行)-编辑")
	@ApiOperation(value="评议情况统计(支行)-编辑", notes="评议情况统计(支行)-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zhpyqktj zhpyqktj) {
		zhpyqktjService.updateById(zhpyqktj);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评议情况统计(支行)-通过id删除")
	@ApiOperation(value="评议情况统计(支行)-通过id删除", notes="评议情况统计(支行)-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zhpyqktjService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "评议情况统计(支行)-批量删除")
	@ApiOperation(value="评议情况统计(支行)-批量删除", notes="评议情况统计(支行)-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zhpyqktjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评议情况统计(支行)-通过id查询")
	@ApiOperation(value="评议情况统计(支行)-通过id查询", notes="评议情况统计(支行)-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zhpyqktj zhpyqktj = zhpyqktjService.getById(id);
		return Result.ok(zhpyqktj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zhpyqktj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zhpyqktj zhpyqktj) {
      return super.exportXls(request, zhpyqktj, Zhpyqktj.class, "评议情况统计(支行)");
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
      return super.importExcel(request, response, Zhpyqktj.class);
  }

}
