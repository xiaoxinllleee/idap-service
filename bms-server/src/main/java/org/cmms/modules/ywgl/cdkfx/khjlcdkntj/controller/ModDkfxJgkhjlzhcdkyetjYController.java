package org.cmms.modules.ywgl.cdkfx.khjlcdkntj.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.ywgl.cdkfx.khjlcdkntj.entity.ModDkfxJgkhjlzhcdkyetjY;
import org.cmms.modules.ywgl.cdkfx.khjlcdkntj.entity.ModDkfxJgkhjlzhcdkyetjYVo;
import org.cmms.modules.ywgl.cdkfx.khjlcdkntj.service.IModDkfxJgkhjlzhcdkyetjYService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ywgl.cdkfx.khjlcdkntj.service.IModDkfxJgkhjlzhcdkyetjYVoService;
import org.cmms.modules.ywgl.cdkfx.khjlcdkytj.entity.ModDkfxJgkhjlzhcdkyetjM;
import org.cmms.modules.ywgl.cdkfx.util.mapper.HrbasStaffToolMapper;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 客户经理存贷款年统计
 * @Author: jeecg-boot
 * @Date:   2021-06-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户经理存贷款年统计")
@RestController
@RequestMapping("/khjlcdkntj/modDkfxJgkhjlzhcdkyetjY")
public class ModDkfxJgkhjlzhcdkyetjYController extends JeecgController<ModDkfxJgkhjlzhcdkyetjY, IModDkfxJgkhjlzhcdkyetjYService> {
	@Autowired
	private IModDkfxJgkhjlzhcdkyetjYService modDkfxJgkhjlzhcdkyetjYService;

	@Autowired
	private IModDkfxJgkhjlzhcdkyetjYVoService modDkfxJgkhjlzhcdkyetjYVoService;

	 @Autowired(required = false)
	private HrbasStaffToolMapper HrBasStaffMapper;
	/**
	 * 分页列表查询
	 *
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户经理存贷款年统计-分页列表查询")
	@ApiOperation(value="客户经理存贷款年统计-分页列表查询", notes="客户经理存贷款年统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ModDkfxJgkhjlzhcdkyetjYVo modDkfxJgkhjlzhcdkyetjYVo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ModDkfxJgkhjlzhcdkyetjYVo> queryWrapper = QueryGenerator.initQueryWrapper(modDkfxJgkhjlzhcdkyetjYVo, req.getParameterMap());
		IPage pageList = PageUtil.toPage(IModDkfxJgkhjlzhcdkyetjYVoService.class,modDkfxJgkhjlzhcdkyetjYVoService, pageNo, pageSize, queryWrapper, "jgdm","tjyf","custid");
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param modDkfxJgkhjlzhcdkyetjY
	 * @return
	 */
	@AutoLog(value = "客户经理存贷款年统计-添加")
	@ApiOperation(value="客户经理存贷款年统计-添加", notes="客户经理存贷款年统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ModDkfxJgkhjlzhcdkyetjY modDkfxJgkhjlzhcdkyetjY) {
		modDkfxJgkhjlzhcdkyetjYService.save(modDkfxJgkhjlzhcdkyetjY);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param modDkfxJgkhjlzhcdkyetjY
	 * @return
	 */
	@AutoLog(value = "客户经理存贷款年统计-编辑")
	@ApiOperation(value="客户经理存贷款年统计-编辑", notes="客户经理存贷款年统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ModDkfxJgkhjlzhcdkyetjY modDkfxJgkhjlzhcdkyetjY) {
		modDkfxJgkhjlzhcdkyetjYService.updateById(modDkfxJgkhjlzhcdkyetjY);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理存贷款年统计-通过id删除")
	@ApiOperation(value="客户经理存贷款年统计-通过id删除", notes="客户经理存贷款年统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		modDkfxJgkhjlzhcdkyetjYService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户经理存贷款年统计-批量删除")
	@ApiOperation(value="客户经理存贷款年统计-批量删除", notes="客户经理存贷款年统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.modDkfxJgkhjlzhcdkyetjYService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理存贷款年统计-通过id查询")
	@ApiOperation(value="客户经理存贷款年统计-通过id查询", notes="客户经理存贷款年统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ModDkfxJgkhjlzhcdkyetjY modDkfxJgkhjlzhcdkyetjY = modDkfxJgkhjlzhcdkyetjYService.getById(id);
		return Result.ok(modDkfxJgkhjlzhcdkyetjY);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param modDkfxJgkhjlzhcdkyetjY
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ModDkfxJgkhjlzhcdkyetjY modDkfxJgkhjlzhcdkyetjY) {
      return super.exportXls(request, modDkfxJgkhjlzhcdkyetjY, ModDkfxJgkhjlzhcdkyetjY.class, "客户经理存贷款年统计");
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
      return super.importExcel(request, response, ModDkfxJgkhjlzhcdkyetjY.class);
  }

}
