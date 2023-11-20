package org.cmms.modules.ywgl.cdkfx.xzblkh.controller;

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
import org.cmms.modules.ywgl.cdkfx.util.mapper.HrbasStaffToolMapper;
import org.cmms.modules.ywgl.cdkfx.xzblkh.entity.VKhjlxzblkhVo;
import org.cmms.modules.ywgl.cdkfx.xzblkh.service.IVKhjlxzblkhVoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 新增不良考核
 * @Author: jeecg-boot
 * @Date:   2021-06-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="新增不良考核")
@RestController
@RequestMapping("/xzblkh/vKhjlxzblkhVo")
public class VKhjlxzblkhVoController extends JeecgController<VKhjlxzblkhVo, IVKhjlxzblkhVoService> {
	@Autowired
	private IVKhjlxzblkhVoService vKhjlxzblkhVoService;
	/**
	 * 分页列表查询
	 *
	 * @param vKhjlxzblkhVo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "新增不良考核-分页列表查询")
	@ApiOperation(value="新增不良考核-分页列表查询", notes="新增不良考核-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VKhjlxzblkhVo vKhjlxzblkhVo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VKhjlxzblkhVo> queryWrapper = QueryGenerator.initQueryWrapper(vKhjlxzblkhVo, req.getParameterMap());
		IPage pageList = PageUtil.toPage(IVKhjlxzblkhVoService.class,vKhjlxzblkhVoService, pageNo, pageSize, queryWrapper, "jgdm","custid");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param vKhjlxzblkhVo
	 * @return
	 */
	@AutoLog(value = "新增不良考核-添加")
	@ApiOperation(value="新增不良考核-添加", notes="新增不良考核-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody VKhjlxzblkhVo vKhjlxzblkhVo) {
		vKhjlxzblkhVoService.save(vKhjlxzblkhVo);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param vKhjlxzblkhVo
	 * @return
	 */
	@AutoLog(value = "新增不良考核-编辑")
	@ApiOperation(value="新增不良考核-编辑", notes="新增不良考核-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody VKhjlxzblkhVo vKhjlxzblkhVo) {
		vKhjlxzblkhVoService.updateById(vKhjlxzblkhVo);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "新增不良考核-通过id删除")
	@ApiOperation(value="新增不良考核-通过id删除", notes="新增不良考核-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		vKhjlxzblkhVoService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "新增不良考核-批量删除")
	@ApiOperation(value="新增不良考核-批量删除", notes="新增不良考核-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.vKhjlxzblkhVoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "新增不良考核-通过id查询")
	@ApiOperation(value="新增不良考核-通过id查询", notes="新增不良考核-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VKhjlxzblkhVo vKhjlxzblkhVo = vKhjlxzblkhVoService.getById(id);
		return Result.ok(vKhjlxzblkhVo);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param vKhjlxzblkhVo
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, VKhjlxzblkhVo vKhjlxzblkhVo) {
	return super.exportXls(request, vKhjlxzblkhVo, VKhjlxzblkhVo.class, "新增不良考核");
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
      return super.importExcel(request, response, VKhjlxzblkhVo.class);
  }

}
