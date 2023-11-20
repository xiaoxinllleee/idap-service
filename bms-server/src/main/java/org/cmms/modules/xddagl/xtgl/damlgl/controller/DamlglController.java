package org.cmms.modules.xddagl.xtgl.damlgl.controller;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.api.vo.ResultConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.xddagl.xtgl.damlgl.entity.Damlgl;
import org.cmms.modules.xddagl.xtgl.damlgl.service.IDamlglService;
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
 * @Description: 档案目录管理(参数配置)
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="档案目录管理(参数配置)")
@RestController
@RequestMapping("/damlgl/damlgl")
public class DamlglController extends JeecgController<Damlgl, IDamlglService> {
	@Autowired
	private IDamlglService damlglService;
	
	/**
	 * 分页列表查询
	 *
	 * @param damlgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "档案目录管理(参数配置)-分页列表查询")
	@ApiOperation(value="档案目录管理(参数配置)-分页列表查询", notes="档案目录管理(参数配置)-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Damlgl damlgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Damlgl> queryWrapper = QueryGenerator.initQueryWrapper(damlgl, req.getParameterMap());
		Page<Damlgl> page = new Page<Damlgl>(pageNo, pageSize);
		IPage<Damlgl> pageList = damlglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param damlgl
	 * @return
	 */
	@AutoLog(value = "档案目录管理(参数配置)-添加")
	@ApiOperation(value="档案目录管理(参数配置)-添加", notes="档案目录管理(参数配置)-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Damlgl damlgl) {
		LambdaQueryWrapper<Damlgl> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(Damlgl::getDictCode,damlgl.getDictCode());
		lambdaQueryWrapper.eq(Damlgl::getDictCode,damlgl.getDictValue());
		Damlgl check = damlglService.getOne(lambdaQueryWrapper);
		if (check != null)
			return Result.error(ResultConstant.SJYQZQQR);
		damlgl.setLrbz(1);
		damlgl.setLrr(getUsername());
		boolean save = damlglService.save(damlgl);
		if (save)
			Result.ok(ResultConstant.TJCG);
		return Result.error(ResultConstant.TJSB);
	}
	
	/**
	 * 编辑
	 *
	 * @param damlgl
	 * @return
	 */
	@AutoLog(value = "档案目录管理(参数配置)-编辑")
	@ApiOperation(value="档案目录管理(参数配置)-编辑", notes="档案目录管理(参数配置)-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Damlgl damlgl) {
		damlgl.setLrsj(new Date());
		damlgl.setLrbz(2);
		damlgl.setLrr(getUsername());
		service.updateById(damlgl);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "档案目录管理(参数配置)-通过id删除")
	@ApiOperation(value="档案目录管理(参数配置)-通过id删除", notes="档案目录管理(参数配置)-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("dictCode") String dictCode, @Param("dictValue") String dictValue) {
		LambdaQueryWrapper<Damlgl> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(Damlgl::getDictCode,dictCode);
		lambdaQueryWrapper.eq(Damlgl::getDictValue,dictValue);
		damlglService.remove(lambdaQueryWrapper);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "档案目录管理(参数配置)-批量删除")
	@ApiOperation(value="档案目录管理(参数配置)-批量删除", notes="档案目录管理(参数配置)-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.damlglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "档案目录管理(参数配置)-通过id查询")
	@ApiOperation(value="档案目录管理(参数配置)-通过id查询", notes="档案目录管理(参数配置)-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Damlgl damlgl = damlglService.getById(id);
		return Result.ok(damlgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param damlgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Damlgl damlgl) {
      return super.exportXls(request, damlgl, Damlgl.class, "档案目录管理(参数配置)");
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
      return super.importExcel(request, response, Damlgl.class);
  }

}
