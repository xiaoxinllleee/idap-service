package org.cmms.modules.xddagl.dqdagl.dqdazlgl.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
import org.cmms.modules.xddagl.dqdagl.dqdazlgl.entity.DqdazlglFjxx;
import org.cmms.modules.xddagl.dqdagl.dqdazlgl.service.IDqdazlglFjxxService;
import org.cmms.modules.xddagl.dqdagl.dqdazlgl.vo.DqdazlglVO;
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
 * @Description: 贷前档案资料管理附件信息
 * @Author: jeecg-boot
 * @Date:   2022-01-12
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷前档案资料管理附件信息")
@RestController
@RequestMapping("/dqdazlglfjxx/dqdazlglFjxx")
public class DqdazlglFjxxController extends JeecgController<DqdazlglFjxx, IDqdazlglFjxxService> {
	@Autowired
	private IDqdazlglFjxxService dqdazlglFjxxService;
	
	/**
	 * 分页列表查询
	 *
	 * @param dqdazlglFjxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷前档案资料管理附件信息-分页列表查询")
	@ApiOperation(value="贷前档案资料管理附件信息-分页列表查询", notes="贷前档案资料管理附件信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DqdazlglFjxx dqdazlglFjxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DqdazlglFjxx> queryWrapper = QueryGenerator.initQueryWrapper(dqdazlglFjxx, req.getParameterMap());
		Page<DqdazlglFjxx> page = new Page<DqdazlglFjxx>(pageNo, pageSize);
		IPage<DqdazlglFjxx> pageList = dqdazlglFjxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param dqdazlglFjxx
	 * @return
	 */
	@AutoLog(value = "贷前档案资料管理附件信息-添加")
	@ApiOperation(value="贷前档案资料管理附件信息-添加", notes="贷前档案资料管理附件信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DqdazlglFjxx dqdazlglFjxx) {
		dqdazlglFjxxService.save(dqdazlglFjxx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "贷前档案资料管理附件信息-编辑")
	@ApiOperation(value="贷前档案资料管理附件信息-编辑", notes="贷前档案资料管理附件信息-编辑")
	@RequestMapping(value = "/edit",method = RequestMethod.PUT)
	public Result<?> edit(@RequestBody DqdazlglVO dqdazlglVO) {
		UpdateWrapper<DqdazlglFjxx> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("hth",dqdazlglVO.getHth());
		dqdazlglFjxxService.remove(updateWrapper);
		for (DqdazlglFjxx dqdazlglFjxx : dqdazlglVO.getDqdazlglFjxxList()){
//			dqdazlglFjxx.setWjid();
		}
//		dqdazlglFjxxService.updateById(dqdazlglFjxx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷前档案资料管理附件信息-通过id删除")
	@ApiOperation(value="贷前档案资料管理附件信息-通过id删除", notes="贷前档案资料管理附件信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dqdazlglFjxxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷前档案资料管理附件信息-批量删除")
	@ApiOperation(value="贷前档案资料管理附件信息-批量删除", notes="贷前档案资料管理附件信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dqdazlglFjxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷前档案资料管理附件信息-通过id查询")
	@ApiOperation(value="贷前档案资料管理附件信息-通过id查询", notes="贷前档案资料管理附件信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DqdazlglFjxx dqdazlglFjxx = dqdazlglFjxxService.getById(id);
		return Result.ok(dqdazlglFjxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dqdazlglFjxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DqdazlglFjxx dqdazlglFjxx) {
      return super.exportXls(request, dqdazlglFjxx, DqdazlglFjxx.class, "贷前档案资料管理附件信息");
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
      return super.importExcel(request, response, DqdazlglFjxx.class);
  }

}
