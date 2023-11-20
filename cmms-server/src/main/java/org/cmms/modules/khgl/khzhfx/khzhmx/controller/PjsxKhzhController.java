package org.cmms.modules.khgl.khzhfx.khzhmx.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.khgl.khzhfx.khzhmx.entity.PjsxKhzh;
import org.cmms.modules.khgl.khzhfx.khzhmx.service.IPjsxKhzhService;
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
 * @Description: 评级授信明细
 * @Author: jeecg-boot
 * @Date:   2023-04-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="评级授信明细")
@RestController
@RequestMapping("/khzhmx/pjsx")
public class PjsxKhzhController extends JeecgController<PjsxKhzh, IPjsxKhzhService> {
	@Autowired
	private IPjsxKhzhService pjsxService;
	
	/**
	 * 分页列表查询
	 *
	 * @param pjsx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "评级授信明细-分页列表查询")
	@ApiOperation(value="评级授信明细-分页列表查询", notes="评级授信明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PjsxKhzh pjsx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PjsxKhzh> queryWrapper = QueryGenerator.initQueryWrapper(pjsx, req.getParameterMap());
		Page<PjsxKhzh> page = new Page<PjsxKhzh>(pageNo, pageSize);
		IPage<PjsxKhzh> pageList = pjsxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param pjsx
	 * @return
	 */
	@AutoLog(value = "评级授信明细-添加")
	@ApiOperation(value="评级授信明细-添加", notes="评级授信明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PjsxKhzh pjsx) {
		pjsxService.save(pjsx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param pjsx
	 * @return
	 */
	@AutoLog(value = "评级授信明细-编辑")
	@ApiOperation(value="评级授信明细-编辑", notes="评级授信明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PjsxKhzh pjsx) {
		pjsxService.updateById(pjsx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评级授信明细-通过id删除")
	@ApiOperation(value="评级授信明细-通过id删除", notes="评级授信明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		pjsxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "评级授信明细-批量删除")
	@ApiOperation(value="评级授信明细-批量删除", notes="评级授信明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pjsxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评级授信明细-通过id查询")
	@ApiOperation(value="评级授信明细-通过id查询", notes="评级授信明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PjsxKhzh pjsx = pjsxService.getById(id);
		return Result.ok(pjsx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param pjsx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, PjsxKhzh pjsx) {
      return super.exportXls(request, pjsx, PjsxKhzh.class, "评级授信明细");
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
      return super.importExcel(request, response, PjsxKhzh.class);
  }

}
