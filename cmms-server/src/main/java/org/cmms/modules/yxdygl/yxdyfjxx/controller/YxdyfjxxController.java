package org.cmms.modules.yxdygl.yxdyfjxx.controller;

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
import org.cmms.modules.yxdygl.yxdyfjxx.entity.Yxdyfjxx;
import org.cmms.modules.yxdygl.yxdyfjxx.service.IYxdyfjxxService;
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
 * @Description: 营销单元附件信息
 * @Author: jeecg-boot
 * @Date:   2020-08-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="营销单元附件信息")
@RestController
@RequestMapping("/yxdygl/yxdyfjxx")
public class YxdyfjxxController extends JeecgController<Yxdyfjxx, IYxdyfjxxService> {
	@Autowired
	private IYxdyfjxxService yxdyfjxxService;
	
	/**
	 * 分页列表查询
	 *
	 * @param yxdyfjxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "营销单元附件信息-分页列表查询")
	@ApiOperation(value="营销单元附件信息-分页列表查询", notes="营销单元附件信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Yxdyfjxx yxdyfjxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Yxdyfjxx> queryWrapper = QueryGenerator.initQueryWrapper(yxdyfjxx, req.getParameterMap());
		Page<Yxdyfjxx> page = new Page<Yxdyfjxx>(pageNo, pageSize);
		IPage<Yxdyfjxx> pageList = yxdyfjxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param yxdyfjxx
	 * @return
	 */
	@AutoLog(value = "营销单元附件信息-添加")
	@ApiOperation(value="营销单元附件信息-添加", notes="营销单元附件信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Yxdyfjxx yxdyfjxx) {
		yxdyfjxxService.save(yxdyfjxx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param yxdyfjxx
	 * @return
	 */
	@AutoLog(value = "营销单元附件信息-编辑")
	@ApiOperation(value="营销单元附件信息-编辑", notes="营销单元附件信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Yxdyfjxx yxdyfjxx) {
		yxdyfjxxService.updateById(yxdyfjxx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "营销单元附件信息-通过id删除")
	@ApiOperation(value="营销单元附件信息-通过id删除", notes="营销单元附件信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		yxdyfjxxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "营销单元附件信息-批量删除")
	@ApiOperation(value="营销单元附件信息-批量删除", notes="营销单元附件信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.yxdyfjxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "营销单元附件信息-通过id查询")
	@ApiOperation(value="营销单元附件信息-通过id查询", notes="营销单元附件信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Yxdyfjxx yxdyfjxx = yxdyfjxxService.getById(id);
		return Result.ok(yxdyfjxx);
	}

	 /**
	  * 通过id查询
	  *
	  * @param dybh
	  * @return
	  */
	 @GetMapping(value = "/queryYxdyfjxxByMainId")
	 public Result<?> queryYxdyfjxxListByMainId(@RequestParam(name="dybh",required=true) String dybh) {
		 List<Yxdyfjxx> yxdyglYxdyfjxxList = yxdyfjxxService.selectByMainId(dybh);
		 return Result.ok(yxdyglYxdyfjxxList);
	 }

  /**
   * 导出excel
   *
   * @param request
   * @param yxdyfjxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Yxdyfjxx yxdyfjxx) {
      return super.exportXls(request, yxdyfjxx, Yxdyfjxx.class, "营销单元附件信息");
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
      return super.importExcel(request, response, Yxdyfjxx.class);
  }

}
