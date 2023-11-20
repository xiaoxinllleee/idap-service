package org.cmms.modules.xdgl.dksp.dkspdywxx.controller;

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
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.xdgl.dksp.dkspdywxx.entity.DkspDywxx;
import org.cmms.modules.xdgl.dksp.dkspdywxx.service.IDkspDywxxService;
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
 * @Description: 抵押物信息
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags="抵押物信息")
@RestController
@RequestMapping("/dksp/dywxx")
public class DkspDywxxController extends JeecgController<DkspDywxx, IDkspDywxxService> {
	@Autowired
	private IDkspDywxxService dkspDywxxService;
	
	/**
	 * 分页列表查询
	 *
	 * @param dkspDywxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "抵押物信息-分页列表查询")
	@ApiOperation(value="抵押物信息-分页列表查询", notes="抵押物信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DkspDywxx dkspDywxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DkspDywxx> queryWrapper = QueryGenerator.initQueryWrapper(dkspDywxx, req.getParameterMap());
		Page<DkspDywxx> page = new Page<DkspDywxx>(pageNo, pageSize);
		IPage<DkspDywxx> pageList = dkspDywxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param dkspDywxx
	 * @return
	 */
	@AutoLog(value = "抵押物信息-添加")
	@ApiOperation(value="抵押物信息-添加", notes="抵押物信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DkspDywxx dkspDywxx) {
		dkspDywxxService.save(dkspDywxx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param dkspDywxx
	 * @return
	 */
	@AutoLog(value = "抵押物信息-编辑")
	@ApiOperation(value="抵押物信息-编辑", notes="抵押物信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DkspDywxx dkspDywxx) {
		dkspDywxxService.updateById(dkspDywxx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "抵押物信息-通过id删除")
	@ApiOperation(value="抵押物信息-通过id删除", notes="抵押物信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkspDywxxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "抵押物信息-批量删除")
	@ApiOperation(value="抵押物信息-批量删除", notes="抵押物信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkspDywxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "抵押物信息-通过id查询")
	@ApiOperation(value="抵押物信息-通过id查询", notes="抵押物信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DkspDywxx dkspDywxx = dkspDywxxService.getById(id);
		return Result.ok(dkspDywxx);
	}

	 /**
	  * 通过id查询
	  *
	  * @param khId
	  * @return
	  */
	 @AutoLog(value = "抵押物信息-通过khId查询")
	 @ApiOperation(value="抵押物信息-通过khId查询", notes="抵押物信息-通过khId查询")
	 @GetMapping(value = "/queryByKhId")
	 public Result<?> queryByKhId(@RequestParam(name="khId",required=true) String khId) {
	 	 QueryWrapper<DkspDywxx> queryWrapper = new QueryWrapper<>();
	 	 queryWrapper.eq("kh_id", khId);
		 List<DkspDywxx> dywxxList = dkspDywxxService.list(queryWrapper);
		 return Result.ok(dywxxList);
	 }

  /**
   * 导出excel
   *
   * @param request
   * @param dkspDywxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DkspDywxx dkspDywxx) {
      return super.exportXls(request, dkspDywxx, DkspDywxx.class, "抵押物信息");
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
      return super.importExcel(request, response, DkspDywxx.class);
  }

}
