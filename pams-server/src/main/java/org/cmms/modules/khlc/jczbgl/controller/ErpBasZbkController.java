package org.cmms.modules.khlc.jczbgl.controller;

import java.util.Arrays;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.khlc.jczbgl.entity.ErpBasZbk;
import org.cmms.modules.khlc.jczbgl.service.IErpBasZbkService;
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
 * @Description: 指标库管理
 * @Author: jeecg-boot
 * @Date:   2023-02-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="指标库管理")
@RestController
@RequestMapping("/jczbgl/erpBasZbk")
public class ErpBasZbkController extends JeecgController<ErpBasZbk, IErpBasZbkService> {
	@Autowired
	private IErpBasZbkService erpBasZbkService;

	/**
	 * 分页列表查询
	 *
	 * @param erpBasZbk
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "指标库管理-分页列表查询")
	@ApiOperation(value="指标库管理-分页列表查询", notes="指标库管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ErpBasZbk erpBasZbk,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String zbmc = erpBasZbk.getZbmc();
		erpBasZbk.setZbmc(null);
		QueryWrapper<ErpBasZbk> queryWrapper = QueryGenerator.initQueryWrapper(erpBasZbk, req.getParameterMap());
		if (StringUtils.isNotBlank(zbmc)){
			queryWrapper.like("zbmc",zbmc);
		}
		queryWrapper.orderByDesc("zbid");
		Page<ErpBasZbk> page = new Page<ErpBasZbk>(pageNo, pageSize);
		IPage<ErpBasZbk> pageList = erpBasZbkService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param erpBasZbk
	 * @return
	 */
	@AutoLog(value = "指标库管理-添加")
	@ApiOperation(value="指标库管理-添加", notes="指标库管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ErpBasZbk erpBasZbk) {
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		erpBasZbk.setCreateBy(loginUser.getUsername());
		erpBasZbk.setCreateTime(new Date());
		erpBasZbkService.save(erpBasZbk);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param erpBasZbk
	 * @return
	 */
	@AutoLog(value = "指标库管理-编辑")
	@ApiOperation(value="指标库管理-编辑", notes="指标库管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ErpBasZbk erpBasZbk) {
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		QueryWrapper<ErpBasZbk> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("zbid",erpBasZbk.getZbid());
		ErpBasZbk one = erpBasZbkService.getOne(queryWrapper);
		if (one != null){
			erpBasZbk.setUpdateBy(loginUser.getUsername());
			erpBasZbk.setUpdateTime(new Date());
			erpBasZbkService.update(erpBasZbk,queryWrapper);
			return Result.ok("编辑成功!");
		}
		return Result.error("编辑失败！");
	}

	/**
	 * 通过id删除
	 *
	 * @param zbid
	 * @return
	 */
	@AutoLog(value = "指标库管理-通过id删除")
	@ApiOperation(value="指标库管理-通过id删除", notes="指标库管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="zbid",required=true) String zbid) {
		erpBasZbkService.deleteByZbid(zbid);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "指标库管理-批量删除")
	@ApiOperation(value="指标库管理-批量删除", notes="指标库管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.erpBasZbkService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "指标库管理-通过id查询")
	@ApiOperation(value="指标库管理-通过id查询", notes="指标库管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ErpBasZbk erpBasZbk = erpBasZbkService.getById(id);
		return Result.ok(erpBasZbk);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param erpBasZbk
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ErpBasZbk erpBasZbk) {
      return super.exportXls(request, erpBasZbk, ErpBasZbk.class, "指标库管理");
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
      return super.importExcel(request, response, ErpBasZbk.class);
  }

}
