package org.cmms.modules.ywgl.cdkfx.khjldkdqshltj.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.ywgl.cdkfx.khjldkdqshltj.entity.ModDkfxJgkhjldkdqshlkhM;
import org.cmms.modules.ywgl.cdkfx.khjldkdqshltj.service.IModDkfxJgkhjldkdqshlkhMService;
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
 * @Description: 客户经理贷款到期收回率统计
 * @Author: jeecg-boot
 * @Date:   2021-06-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户经理贷款到期收回率统计")
@RestController
@RequestMapping("/khjldkdqshltj/modDkfxJgkhjldkdqshlkhM")
public class ModDkfxJgkhjldkdqshlkhMController extends JeecgController<ModDkfxJgkhjldkdqshlkhM, IModDkfxJgkhjldkdqshlkhMService> {
	@Autowired
	private IModDkfxJgkhjldkdqshlkhMService modDkfxJgkhjldkdqshlkhMService;

	/**
	 * 分页列表查询
	 *
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户经理贷款到期收回率统计-分页列表查询")
	@ApiOperation(value="客户经理贷款到期收回率统计-分页列表查询", notes="客户经理贷款到期收回率统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ModDkfxJgkhjldkdqshlkhM modDkfxJgkhjldkdqshlkhM,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ModDkfxJgkhjldkdqshlkhM> queryWrapper = QueryGenerator.initQueryWrapper(modDkfxJgkhjldkdqshlkhM, req.getParameterMap());
		IPage pageList = PageUtil.toPage(IModDkfxJgkhjldkdqshlkhMService.class,modDkfxJgkhjldkdqshlkhMService, pageNo, pageSize, queryWrapper, "jgdm","custid","tjyf");
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param modDkfxJgkhjldkdqshlkhM
	 * @return
	 */
	@AutoLog(value = "客户经理贷款到期收回率统计-添加")
	@ApiOperation(value="客户经理贷款到期收回率统计-添加", notes="客户经理贷款到期收回率统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ModDkfxJgkhjldkdqshlkhM modDkfxJgkhjldkdqshlkhM) {
		modDkfxJgkhjldkdqshlkhMService.save(modDkfxJgkhjldkdqshlkhM);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param modDkfxJgkhjldkdqshlkhM
	 * @return
	 */
	@AutoLog(value = "客户经理贷款到期收回率统计-编辑")
	@ApiOperation(value="客户经理贷款到期收回率统计-编辑", notes="客户经理贷款到期收回率统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ModDkfxJgkhjldkdqshlkhM modDkfxJgkhjldkdqshlkhM) {
		modDkfxJgkhjldkdqshlkhMService.updateById(modDkfxJgkhjldkdqshlkhM);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理贷款到期收回率统计-通过id删除")
	@ApiOperation(value="客户经理贷款到期收回率统计-通过id删除", notes="客户经理贷款到期收回率统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		modDkfxJgkhjldkdqshlkhMService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户经理贷款到期收回率统计-批量删除")
	@ApiOperation(value="客户经理贷款到期收回率统计-批量删除", notes="客户经理贷款到期收回率统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.modDkfxJgkhjldkdqshlkhMService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理贷款到期收回率统计-通过id查询")
	@ApiOperation(value="客户经理贷款到期收回率统计-通过id查询", notes="客户经理贷款到期收回率统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ModDkfxJgkhjldkdqshlkhM modDkfxJgkhjldkdqshlkhM = modDkfxJgkhjldkdqshlkhMService.getById(id);
		return Result.ok(modDkfxJgkhjldkdqshlkhM);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param modDkfxJgkhjldkdqshlkhM
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ModDkfxJgkhjldkdqshlkhM modDkfxJgkhjldkdqshlkhM) {
      return super.exportXls(request, modDkfxJgkhjldkdqshlkhM, ModDkfxJgkhjldkdqshlkhM.class, "客户经理贷款到期收回率统计");
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
      return super.importExcel(request, response, ModDkfxJgkhjldkdqshlkhM.class);
  }

}
