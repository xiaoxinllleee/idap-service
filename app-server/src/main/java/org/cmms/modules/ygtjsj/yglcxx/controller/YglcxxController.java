package org.cmms.modules.ygtjsj.yglcxx.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.ygtjsj.yglcxx.entity.TbTjfxYglcxx;
import org.cmms.modules.ygtjsj.yglcxx.service.IYglcxxService;
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
 * @Description: 员工揽储信息
 * @Author: jeecg-boot
 * @Date:   2021-05-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags="员工揽储信息")
@RestController
@RequestMapping("/mobile/yglcxx")
public class YglcxxController extends JeecgController<TbTjfxYglcxx, IYglcxxService> {
	@Autowired
	private IYglcxxService yglcxxService;
	
	/**
	 * 分页列表查询
	 *
	 * @param yglcxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "员工揽储信息-分页列表查询")
	@ApiOperation(value="员工揽储信息-分页列表查询", notes="员工揽储信息-分页列表查询")
	@GetMapping(value = "/page")
	public Result<?> queryPageList(TbTjfxYglcxx yglcxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="5") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TbTjfxYglcxx> queryWrapper = QueryGenerator.initQueryWrapper(yglcxx, req.getParameterMap());
		Page<TbTjfxYglcxx> page = new Page<TbTjfxYglcxx>(pageNo, pageSize);
		queryWrapper.orderByDesc("lcrj");
		IPage<TbTjfxYglcxx> pageList = yglcxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param yglcxx
	 * @return
	 */
	@AutoLog(value = "员工揽储信息-添加")
	@ApiOperation(value="员工揽储信息-添加", notes="员工揽储信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TbTjfxYglcxx yglcxx) {
		yglcxxService.save(yglcxx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param yglcxx
	 * @return
	 */
	@AutoLog(value = "员工揽储信息-编辑")
	@ApiOperation(value="员工揽储信息-编辑", notes="员工揽储信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TbTjfxYglcxx yglcxx) {
		yglcxxService.updateById(yglcxx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工揽储信息-通过id删除")
	@ApiOperation(value="员工揽储信息-通过id删除", notes="员工揽储信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		yglcxxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "员工揽储信息-批量删除")
	@ApiOperation(value="员工揽储信息-批量删除", notes="员工揽储信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.yglcxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工揽储信息-通过id查询")
	@ApiOperation(value="员工揽储信息-通过id查询", notes="员工揽储信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TbTjfxYglcxx yglcxx = yglcxxService.getById(id);
		return Result.ok(yglcxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param yglcxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, TbTjfxYglcxx yglcxx) {
      return super.exportXls(request, yglcxx, TbTjfxYglcxx.class, "员工揽储信息");
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
      return super.importExcel(request, response, TbTjfxYglcxx.class);
  }

}
