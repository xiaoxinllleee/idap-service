package org.cmms.modules.xddagl.dkdaglfjxx.controller;

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
import org.cmms.modules.xddagl.dkdaglfjxx.entity.DkdaglFjxx;
import org.cmms.modules.xddagl.dkdaglfjxx.entity.Xdhcfjxx;
import org.cmms.modules.xddagl.dkdaglfjxx.service.IDkdaglFjxxService;
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
 * @Description: 贷款档案管理附件信息
 * @Author: jeecg-boot
 * @Date:   2022-01-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款档案管理附件信息")
@RestController
@RequestMapping("/dkdaglfjxx/dkdaglFjxx")
public class DkdaglFjxxController extends JeecgController<DkdaglFjxx, IDkdaglFjxxService> {
	@Autowired
	private IDkdaglFjxxService dkdaglFjxxService;
	
	/**
	 * 分页列表查询
	 *
	 * @param dkdaglFjxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款档案管理附件信息-分页列表查询")
	@ApiOperation(value="贷款档案管理附件信息-分页列表查询", notes="贷款档案管理附件信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DkdaglFjxx dkdaglFjxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DkdaglFjxx> queryWrapper = QueryGenerator.initQueryWrapper(dkdaglFjxx, req.getParameterMap());
		Page<DkdaglFjxx> page = new Page<DkdaglFjxx>(pageNo, pageSize);
		IPage<DkdaglFjxx> pageList = dkdaglFjxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
	  * 查询附件
	  */
	 @RequestMapping(value = "/queryFjxx",method = RequestMethod.GET)
	 public Result<?> queryFjxx(@RequestParam(name = "hth",required = true)String hth){
		 QueryWrapper<DkdaglFjxx> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("hth",hth);
		 List<DkdaglFjxx> list = dkdaglFjxxService.list(queryWrapper);
		 return Result.ok(list);
	 }
	
	/**
	 * 添加
	 *
	 * @param dkdaglFjxx
	 * @return
	 */
	@AutoLog(value = "贷款档案管理附件信息-添加")
	@ApiOperation(value="贷款档案管理附件信息-添加", notes="贷款档案管理附件信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DkdaglFjxx dkdaglFjxx) {
		dkdaglFjxxService.save(dkdaglFjxx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param dkdaglFjxx
	 * @return
	 */
	@AutoLog(value = "贷款档案管理附件信息-编辑")
	@ApiOperation(value="贷款档案管理附件信息-编辑", notes="贷款档案管理附件信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DkdaglFjxx dkdaglFjxx) {
		dkdaglFjxxService.updateById(dkdaglFjxx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "贷款档案管理附件信息-通过id删除")
	@ApiOperation(value="贷款档案管理附件信息-通过id删除", notes="贷款档案管理附件信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="wjid",required=true) String wjid) {
		QueryWrapper<DkdaglFjxx> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("wjid",wjid);
		dkdaglFjxxService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	 @AutoLog(value = "贷款档案管理附件信息-通过id删除")
	 @ApiOperation(value="贷款档案管理附件信息-通过id删除", notes="贷款档案管理附件信息-通过id删除")
	 @DeleteMapping(value = "/deleteByWjid")
	 public Result<?> deleteByWjid(@RequestParam(name="wjid",required=true) String wjid) {
		 QueryWrapper<DkdaglFjxx> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("wjid",wjid);
		 dkdaglFjxxService.remove(queryWrapper);
		 return Result.ok("删除成功!");
	 }
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款档案管理附件信息-批量删除")
	@ApiOperation(value="贷款档案管理附件信息-批量删除", notes="贷款档案管理附件信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkdaglFjxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款档案管理附件信息-通过id查询")
	@ApiOperation(value="贷款档案管理附件信息-通过id查询", notes="贷款档案管理附件信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DkdaglFjxx dkdaglFjxx = dkdaglFjxxService.getById(id);
		return Result.ok(dkdaglFjxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkdaglFjxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DkdaglFjxx dkdaglFjxx) {
      return super.exportXls(request, dkdaglFjxx, DkdaglFjxx.class, "贷款档案管理附件信息");
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
      return super.importExcel(request, response, DkdaglFjxx.class);
  }

}
