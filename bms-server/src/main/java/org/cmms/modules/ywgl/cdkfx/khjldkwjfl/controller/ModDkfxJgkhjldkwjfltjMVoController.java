package org.cmms.modules.ywgl.cdkfx.khjldkwjfl.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.ywgl.cdkfx.khjldkwjfl.entity.ModDkfxJgkhjldkwjfltjM;
import org.cmms.modules.ywgl.cdkfx.khjldkwjfl.service.IModDkfxJgkhjldkwjfltjMVoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 客户经理贷款五级分类
 * @Author: jeecg-boot
 * @Date:   2021-06-29
 * @Version: V1.0
 */

@Slf4j
@Api(tags="客户经理贷款五级分类")
@RestController
@RequestMapping("/khjldkwjfl/modDkfxJgkhjldkwjfltjMVo")
public class ModDkfxJgkhjldkwjfltjMVoController extends JeecgController<ModDkfxJgkhjldkwjfltjM, IModDkfxJgkhjldkwjfltjMVoService> {
	@Autowired
	private IModDkfxJgkhjldkwjfltjMVoService modDkfxJgkhjldkwjfltjMVoService;

/**
	 * 分页列表查询
	 *
	 * @param modDkfxJgkhjldkwjfltjM
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */

	@AutoLog(value = "客户经理贷款五级分类-分页列表查询")
	@ApiOperation(value="客户经理贷款五级分类-分页列表查询", notes="客户经理贷款五级分类-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ModDkfxJgkhjldkwjfltjM modDkfxJgkhjldkwjfltjM,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ModDkfxJgkhjldkwjfltjM> queryWrapper = QueryGenerator.initQueryWrapper(modDkfxJgkhjldkwjfltjM, req.getParameterMap());
		IPage pageList = PageUtil.toPage(IModDkfxJgkhjldkwjfltjMVoService.class,modDkfxJgkhjldkwjfltjMVoService, pageNo, pageSize, queryWrapper, "jgdm","custid","tjyf");
		return Result.ok(pageList);
	}

/**
	 * 添加
	 *
	 * @param modDkfxJgkhjldkwjfltjM
	 * @return
	 */

	@AutoLog(value = "客户经理贷款五级分类-添加")
	@ApiOperation(value="客户经理贷款五级分类-添加", notes="客户经理贷款五级分类-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ModDkfxJgkhjldkwjfltjM modDkfxJgkhjldkwjfltjM) {
		modDkfxJgkhjldkwjfltjMVoService.save(modDkfxJgkhjldkwjfltjM);
		return Result.ok("添加成功！");
	}

/**
	 * 编辑
	 *
	 * @param modDkfxJgkhjldkwjfltjM
	 * @return
	 */

	@AutoLog(value = "客户经理贷款五级分类-编辑")
	@ApiOperation(value="客户经理贷款五级分类-编辑", notes="客户经理贷款五级分类-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ModDkfxJgkhjldkwjfltjM modDkfxJgkhjldkwjfltjM) {
		modDkfxJgkhjldkwjfltjMVoService.updateById(modDkfxJgkhjldkwjfltjM);
		return Result.ok("编辑成功!");
	}

/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */

	@AutoLog(value = "客户经理贷款五级分类-通过id删除")
	@ApiOperation(value="客户经理贷款五级分类-通过id删除", notes="客户经理贷款五级分类-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		modDkfxJgkhjldkwjfltjMVoService.removeById(id);
		return Result.ok("删除成功!");
	}

/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */

	@AutoLog(value = "客户经理贷款五级分类-批量删除")
	@ApiOperation(value="客户经理贷款五级分类-批量删除", notes="客户经理贷款五级分类-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.modDkfxJgkhjldkwjfltjMVoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

/**
	 * 通过id查询
	 *
	 * @param id
	 * @return

 */

	@AutoLog(value = "客户经理贷款五级分类-通过id查询")
	@ApiOperation(value="客户经理贷款五级分类-通过id查询", notes="客户经理贷款五级分类-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ModDkfxJgkhjldkwjfltjM modDkfxJgkhjldkwjfltjM = modDkfxJgkhjldkwjfltjMVoService.getById(id);
		return Result.ok(modDkfxJgkhjldkwjfltjM);
	}
/**
   * 导出excel
   *
   * @param request
   * @param modDkfxJgkhjldkwjfltjMVo
   */

  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ModDkfxJgkhjldkwjfltjM modDkfxJgkhjldkwjfltjMVo) {
    return super.exportXls(request, modDkfxJgkhjldkwjfltjMVo, ModDkfxJgkhjldkwjfltjM.class, "客户经理贷款五级分类");
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
      return super.importExcel(request, response, ModDkfxJgkhjldkwjfltjM.class);
  }

}
