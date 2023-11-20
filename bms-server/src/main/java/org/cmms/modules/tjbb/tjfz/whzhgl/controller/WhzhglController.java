package org.cmms.modules.tjbb.tjfz.whzhgl.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.RedisUtil;
import org.cmms.modules.tjbb.tjfz.whzhgl.entity.Whzhgl;
import org.cmms.modules.tjbb.tjfz.whzhgl.entity.WhzhglVO;
import org.cmms.modules.tjbb.tjfz.whzhgl.service.IWhzhglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjbb.tjfz.whzhgl.verify.WhzhglImportVerify;
import org.cmms.modules.util.PageUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 外汇账户管理
 * @Author: jeecg-boot
 * @Date:   2021-08-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="外汇账户管理")
@RestController
@RequestMapping("/whzhgl/whzhgl")
public class WhzhglController extends JeecgController<Whzhgl, IWhzhglService> {
	 @Autowired
	 private IWhzhglService whzhglService;
	 @Autowired
	 private WhzhglImportVerify whzhglImportVerify;
	 @Autowired
	 private RedisUtil redisUtil;
	/**
	 * 分页列表查询
	 *
	 * @param whzhgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "外汇账户管理-分页列表查询")
	@ApiOperation(value="外汇账户管理-分页列表查询", notes="外汇账户管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Whzhgl whzhgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(whzhgl, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IWhzhglService.class,whzhglService,pageNo,pageSize,queryWrapper,"jgdm","zh","zjhm");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param whzhgl
	 * @return
	 */
	@AutoLog(value = "外汇账户管理-添加")
	@ApiOperation(value="外汇账户管理-添加", notes="外汇账户管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Whzhgl whzhgl) {
		whzhgl.setLrbz(1);
		whzhglService.save(whzhgl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param whzhgl
	 * @return
	 */
	@AutoLog(value = "外汇账户管理-编辑")
	@ApiOperation(value="外汇账户管理-编辑", notes="外汇账户管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Whzhgl whzhgl) {
		whzhgl.setLrbz(2);
		QueryWrapper<Whzhgl> queryWrapper = new QueryWrapper<Whzhgl>();
		queryWrapper.eq("zh",whzhgl.getZh());
		whzhglService.update(whzhgl,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "外汇账户管理-通过id删除")
	@ApiOperation(value="外汇账户管理-通过id删除", notes="外汇账户管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("zh")String zh) {
		QueryWrapper<Whzhgl> queryWrapper = new QueryWrapper<Whzhgl>();
		queryWrapper.eq("zh",zh);
		whzhglService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "外汇账户管理-批量删除")
	@ApiOperation(value="外汇账户管理-批量删除", notes="外汇账户管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.whzhglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "外汇账户管理-通过id查询")
	@ApiOperation(value="外汇账户管理-通过id查询", notes="外汇账户管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Whzhgl whzhgl = whzhglService.getById(id);
		return Result.ok(whzhgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param whzhgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Whzhgl whzhgl) {
      return super.exportXls(request, whzhgl, Whzhgl.class, "外汇账户管理");
  }

	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "外汇账户管理导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, WhzhglVO.class);
		 ExportParams exportParams = new ExportParams("外汇账户管理导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
	 }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
      return super.importExcelByTemplate(jsonObject, request, response, Whzhgl.class,WhzhglVO.class,whzhglImportVerify);
  }

}
