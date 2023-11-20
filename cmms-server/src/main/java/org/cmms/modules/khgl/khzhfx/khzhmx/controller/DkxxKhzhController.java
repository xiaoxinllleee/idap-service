package org.cmms.modules.khgl.khzhfx.khzhmx.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.khgl.khzhfx.khzhmx.entity.DkxxKhzh;
import org.cmms.modules.khgl.khzhfx.khzhmx.service.IDkxxKhzhService;
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
 * @Description: 贷款信息明细
 * @Author: jeecg-boot
 * @Date:   2023-04-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款信息明细")
@RestController
@RequestMapping("/khzhmx/dkxx")
public class DkxxKhzhController extends JeecgController<DkxxKhzh, IDkxxKhzhService> {
	@Autowired
	private IDkxxKhzhService dkxxService;
	
	/**
	 * 分页列表查询
	 *
	 * @param dkxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款信息明细-分页列表查询")
	@ApiOperation(value="贷款信息明细-分页列表查询", notes="贷款信息明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DkxxKhzh dkxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DkxxKhzh> queryWrapper = QueryGenerator.initQueryWrapper(dkxx, req.getParameterMap());
		Page<DkxxKhzh> page = new Page<DkxxKhzh>(pageNo, pageSize);
		IPage<DkxxKhzh> pageList = dkxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param dkxx
	 * @return
	 */
	@AutoLog(value = "贷款信息明细-添加")
	@ApiOperation(value="贷款信息明细-添加", notes="贷款信息明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DkxxKhzh dkxx) {
		dkxxService.save(dkxx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param dkxx
	 * @return
	 */
	@AutoLog(value = "贷款信息明细-编辑")
	@ApiOperation(value="贷款信息明细-编辑", notes="贷款信息明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DkxxKhzh dkxx) {
		dkxxService.updateById(dkxx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款信息明细-通过id删除")
	@ApiOperation(value="贷款信息明细-通过id删除", notes="贷款信息明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkxxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款信息明细-批量删除")
	@ApiOperation(value="贷款信息明细-批量删除", notes="贷款信息明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款信息明细-通过id查询")
	@ApiOperation(value="贷款信息明细-通过id查询", notes="贷款信息明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DkxxKhzh dkxx = dkxxService.getById(id);
		return Result.ok(dkxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DkxxKhzh dkxx) {
      return super.exportXls(request, dkxx, DkxxKhzh.class, "贷款信息明细");
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
      return super.importExcel(request, response, DkxxKhzh.class);
  }

}
