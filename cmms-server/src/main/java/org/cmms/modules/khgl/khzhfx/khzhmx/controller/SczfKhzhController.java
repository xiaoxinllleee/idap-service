package org.cmms.modules.khgl.khzhfx.khzhmx.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.khgl.khzhfx.khzhmx.entity.SczfKhzh;
import org.cmms.modules.khgl.khzhfx.khzhmx.service.ISczfKhzhService;
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
 * @Description: 首次走访明细
 * @Author: jeecg-boot
 * @Date:   2023-04-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="首次走访明细")
@RestController
@RequestMapping("/khzhmx/sczf")
public class SczfKhzhController extends JeecgController<SczfKhzh, ISczfKhzhService> {
	@Autowired
	private ISczfKhzhService sczfService;
	
	/**
	 * 分页列表查询
	 *
	 * @param sczf
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "首次走访明细-分页列表查询")
	@ApiOperation(value="首次走访明细-分页列表查询", notes="首次走访明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SczfKhzh sczf,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SczfKhzh> queryWrapper = QueryGenerator.initQueryWrapper(sczf, req.getParameterMap());
		Page<SczfKhzh> page = new Page<SczfKhzh>(pageNo, pageSize);
		IPage<SczfKhzh> pageList = sczfService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param sczf
	 * @return
	 */
	@AutoLog(value = "首次走访明细-添加")
	@ApiOperation(value="首次走访明细-添加", notes="首次走访明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SczfKhzh sczf) {
		sczfService.save(sczf);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param sczf
	 * @return
	 */
	@AutoLog(value = "首次走访明细-编辑")
	@ApiOperation(value="首次走访明细-编辑", notes="首次走访明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SczfKhzh sczf) {
		sczfService.updateById(sczf);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "首次走访明细-通过id删除")
	@ApiOperation(value="首次走访明细-通过id删除", notes="首次走访明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sczfService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "首次走访明细-批量删除")
	@ApiOperation(value="首次走访明细-批量删除", notes="首次走访明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sczfService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "首次走访明细-通过id查询")
	@ApiOperation(value="首次走访明细-通过id查询", notes="首次走访明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SczfKhzh sczf = sczfService.getById(id);
		return Result.ok(sczf);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sczf
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SczfKhzh sczf) {
      return super.exportXls(request, sczf, SczfKhzh.class, "首次走访明细");
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
      return super.importExcel(request, response, SczfKhzh.class);
  }

}
