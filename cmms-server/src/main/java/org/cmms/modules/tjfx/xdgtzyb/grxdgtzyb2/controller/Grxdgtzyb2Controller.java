package org.cmms.modules.tjfx.xdgtzyb.grxdgtzyb2.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.xdgtzyb.grxdgtzyb2.entity.Grxdgtzyb2;
import org.cmms.modules.tjfx.xdgtzyb.grxdgtzyb2.service.IGrxdgtzyb2Service;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 个人行动挂图作业表2
 * @Author: jeecg-boot
 * @Date:   2020-03-13
 * @Version: V1.0
 */
@Slf4j
@Api(tags="个人行动挂图作业表2")
@RestController
@RequestMapping("/tjfx.xdgtzyb.grxdgtzyb2/grxdgtzyb2")
public class Grxdgtzyb2Controller extends JeecgController<Grxdgtzyb2, IGrxdgtzyb2Service> {
	@Autowired
	private IGrxdgtzyb2Service grxdgtzyb2Service;
	
	/**
	 * 分页列表查询
	 *
	 * @param grxdgtzyb2
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "个人行动挂图作业表2-分页列表查询")
	@ApiOperation(value="个人行动挂图作业表2-分页列表查询", notes="个人行动挂图作业表2-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Grxdgtzyb2 grxdgtzyb2,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Grxdgtzyb2> queryWrapper = QueryGenerator.initQueryWrapper(grxdgtzyb2, req.getParameterMap());
		Page<Grxdgtzyb2> page = new Page<Grxdgtzyb2>(pageNo, pageSize);
		IPage<Grxdgtzyb2> pageList = grxdgtzyb2Service.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param grxdgtzyb2
	 * @return
	 */
	@AutoLog(value = "个人行动挂图作业表2-添加")
	@ApiOperation(value="个人行动挂图作业表2-添加", notes="个人行动挂图作业表2-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Grxdgtzyb2 grxdgtzyb2) {
		grxdgtzyb2Service.save(grxdgtzyb2);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param grxdgtzyb2
	 * @return
	 */
	@AutoLog(value = "个人行动挂图作业表2-编辑")
	@ApiOperation(value="个人行动挂图作业表2-编辑", notes="个人行动挂图作业表2-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Grxdgtzyb2 grxdgtzyb2) {
		grxdgtzyb2Service.updateById(grxdgtzyb2);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "个人行动挂图作业表2-通过id删除")
	@ApiOperation(value="个人行动挂图作业表2-通过id删除", notes="个人行动挂图作业表2-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		grxdgtzyb2Service.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "个人行动挂图作业表2-批量删除")
	@ApiOperation(value="个人行动挂图作业表2-批量删除", notes="个人行动挂图作业表2-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.grxdgtzyb2Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "个人行动挂图作业表2-通过id查询")
	@ApiOperation(value="个人行动挂图作业表2-通过id查询", notes="个人行动挂图作业表2-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Grxdgtzyb2 grxdgtzyb2 = grxdgtzyb2Service.getById(id);
		return Result.ok(grxdgtzyb2);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param grxdgtzyb2
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Grxdgtzyb2 grxdgtzyb2) {
      return super.exportXls(request, grxdgtzyb2, Grxdgtzyb2.class, "个人行动挂图作业表2");
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
      return super.importExcel(request, response, Grxdgtzyb2.class);
  }

}
