package org.cmms.modules.ywgl.cdkfx.dqhslkh.controller;

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
import org.cmms.modules.ywgl.cdkfx.dqhslkh.entity.VModDkfxKhjldkdqshlkhMV;
import org.cmms.modules.ywgl.cdkfx.dqhslkh.service.IVModDkfxKhjldkdqshlkhMVService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
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
 * @Description: 到期收回率考核
 * @Author: jeecg-boot
 * @Date:   2021-06-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="到期收回率考核")
@RestController
@RequestMapping("/dqhslkh/vModDkfxKhjldkdqshlkhMV")
public class VModDkfxKhjldkdqshlkhMVController extends JeecgController<VModDkfxKhjldkdqshlkhMV, IVModDkfxKhjldkdqshlkhMVService> {
	@Autowired
	private IVModDkfxKhjldkdqshlkhMVService vModDkfxKhjldkdqshlkhMVService;
	/**
	 * 分页列表查询
	 *
	 * @param vModDkfxKhjldkdqshlkhMV
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "到期收回率考核-分页列表查询")
	@ApiOperation(value="到期收回率考核-分页列表查询", notes="到期收回率考核-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VModDkfxKhjldkdqshlkhMV vModDkfxKhjldkdqshlkhMV,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VModDkfxKhjldkdqshlkhMV> queryWrapper = QueryGenerator.initQueryWrapper(vModDkfxKhjldkdqshlkhMV, req.getParameterMap());
		IPage pageList = PageUtil.toPage(IVModDkfxKhjldkdqshlkhMVService.class,vModDkfxKhjldkdqshlkhMVService, pageNo, pageSize, queryWrapper, "custid","zzbz","jgdm","tjyf");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param vModDkfxKhjldkdqshlkhMV
	 * @return
	 */
	@AutoLog(value = "到期收回率考核-添加")
	@ApiOperation(value="到期收回率考核-添加", notes="到期收回率考核-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody VModDkfxKhjldkdqshlkhMV vModDkfxKhjldkdqshlkhMV) {
		vModDkfxKhjldkdqshlkhMVService.save(vModDkfxKhjldkdqshlkhMV);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param vModDkfxKhjldkdqshlkhMV
	 * @return
	 */
	@AutoLog(value = "到期收回率考核-编辑")
	@ApiOperation(value="到期收回率考核-编辑", notes="到期收回率考核-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody VModDkfxKhjldkdqshlkhMV vModDkfxKhjldkdqshlkhMV) {
		vModDkfxKhjldkdqshlkhMVService.updateById(vModDkfxKhjldkdqshlkhMV);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "到期收回率考核-通过id删除")
	@ApiOperation(value="到期收回率考核-通过id删除", notes="到期收回率考核-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		vModDkfxKhjldkdqshlkhMVService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "到期收回率考核-批量删除")
	@ApiOperation(value="到期收回率考核-批量删除", notes="到期收回率考核-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.vModDkfxKhjldkdqshlkhMVService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "到期收回率考核-通过id查询")
	@ApiOperation(value="到期收回率考核-通过id查询", notes="到期收回率考核-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VModDkfxKhjldkdqshlkhMV vModDkfxKhjldkdqshlkhMV = vModDkfxKhjldkdqshlkhMVService.getById(id);
		return Result.ok(vModDkfxKhjldkdqshlkhMV);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param vModDkfxKhjldkdqshlkhMV
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, VModDkfxKhjldkdqshlkhMV vModDkfxKhjldkdqshlkhMV) {
		  return super.exportXls(request, vModDkfxKhjldkdqshlkhMV, VModDkfxKhjldkdqshlkhMV.class, "到期收回率考核");
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
      return super.importExcel(request, response, VModDkfxKhjldkdqshlkhMV.class);
  }

}
