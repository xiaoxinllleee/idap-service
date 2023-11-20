package org.cmms.modules.tjfx.xdgtzyb.dhxdgtzyb2.controller;

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
import org.cmms.modules.tjfx.xdgtzyb.dhxdgtzyb2.entity.Dhxdgtzyb2;
import org.cmms.modules.tjfx.xdgtzyb.dhxdgtzyb2.service.IDhxdgtzyb2Service;
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
 * @Description: 户行动挂图作业表2
 * @Author: jeecg-boot
 * @Date:   2020-03-13
 * @Version: V1.0
 */
@Slf4j
@Api(tags="户行动挂图作业表2")
@RestController
@RequestMapping("/tjfx.xdgtzyb.dhxdgtzyb2/dhxdgtzyb2")
public class Dhxdgtzyb2Controller extends JeecgController<Dhxdgtzyb2, IDhxdgtzyb2Service> {
	@Autowired
	private IDhxdgtzyb2Service dhxdgtzyb2Service;
	
	/**
	 * 分页列表查询
	 *
	 * @param dhxdgtzyb2
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "户行动挂图作业表2-分页列表查询")
	@ApiOperation(value="户行动挂图作业表2-分页列表查询", notes="户行动挂图作业表2-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dhxdgtzyb2 dhxdgtzyb2,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dhxdgtzyb2> queryWrapper = QueryGenerator.initQueryWrapper(dhxdgtzyb2, req.getParameterMap());
		Page<Dhxdgtzyb2> page = new Page<Dhxdgtzyb2>(pageNo, pageSize);
		IPage<Dhxdgtzyb2> pageList = dhxdgtzyb2Service.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param dhxdgtzyb2
	 * @return
	 */
	@AutoLog(value = "户行动挂图作业表2-添加")
	@ApiOperation(value="户行动挂图作业表2-添加", notes="户行动挂图作业表2-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dhxdgtzyb2 dhxdgtzyb2) {
		dhxdgtzyb2Service.save(dhxdgtzyb2);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param dhxdgtzyb2
	 * @return
	 */
	@AutoLog(value = "户行动挂图作业表2-编辑")
	@ApiOperation(value="户行动挂图作业表2-编辑", notes="户行动挂图作业表2-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dhxdgtzyb2 dhxdgtzyb2) {
		dhxdgtzyb2Service.updateById(dhxdgtzyb2);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "户行动挂图作业表2-通过id删除")
	@ApiOperation(value="户行动挂图作业表2-通过id删除", notes="户行动挂图作业表2-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dhxdgtzyb2Service.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "户行动挂图作业表2-批量删除")
	@ApiOperation(value="户行动挂图作业表2-批量删除", notes="户行动挂图作业表2-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dhxdgtzyb2Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "户行动挂图作业表2-通过id查询")
	@ApiOperation(value="户行动挂图作业表2-通过id查询", notes="户行动挂图作业表2-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dhxdgtzyb2 dhxdgtzyb2 = dhxdgtzyb2Service.getById(id);
		return Result.ok(dhxdgtzyb2);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dhxdgtzyb2
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dhxdgtzyb2 dhxdgtzyb2) {
      return super.exportXls(request, dhxdgtzyb2, Dhxdgtzyb2.class, "户行动挂图作业表2");
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
      return super.importExcel(request, response, Dhxdgtzyb2.class);
  }

}
