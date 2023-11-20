package org.cmms.modules.ywgl.etcyxgl.zhetcyxtj.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ywgl.etcyxgl.zhetcyxtj.entity.Zhetcyxtj;
import org.cmms.modules.ywgl.etcyxgl.zhetcyxtj.mapper.ZhetcyxtjMapper;
import org.cmms.modules.ywgl.etcyxgl.zhetcyxtj.service.IZhetcyxtjService;
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
 * @Description: 支行etc营销统计
 * @Author: jeecg-boot
 * @Date:   2021-09-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行etc营销统计")
@RestController
@RequestMapping("/zhetcyxtj/zhetcyxtj")
public class ZhetcyxtjController extends JeecgController<Zhetcyxtj, IZhetcyxtjService> {
	@Autowired
	private IZhetcyxtjService zhetcyxtjService;

	@Autowired(required = false)
	private ZhetcyxtjMapper zhetcyxtjMapper;
	
	/**
	 * 分页列表查询
	 *
	 * @param zhetcyxtj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行etc营销统计-分页列表查询")
	@ApiOperation(value="支行etc营销统计-分页列表查询", notes="支行etc营销统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zhetcyxtj zhetcyxtj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Zhetcyxtj> queryWrapper = QueryGenerator.initQueryWrapper(zhetcyxtj, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IZhetcyxtjService.class,zhetcyxtjService,pageNo,pageSize,queryWrapper,"tjyf","jgdm");
		return Result.ok(pageList);
	}

	 /**
	  * 提取
	  */
	 @DS("cdkyw")
	 @RequestMapping(value = "/init")
	 public Result<?> init(String tjyf){
	 	if (tjyf == null || tjyf == "")return Result.error("日期格式错误！");
		 if (-1 == DateUtil.getCurrentTS().compareTo(DateUtil.parseDateFormat(tjyf,"yyyy-MM-dd")))
			 return Result.error("选择月份必须小于当前系统月份！");
		 tjyf = tjyf.replaceAll("-","");
		 try {
		 	zhetcyxtjMapper.pZhetcyxtj(tjyf);
		 }catch (Throwable e){
		 	e.printStackTrace();
		 	return Result.error("提取失败，请查看系统监控日志信息");
		 }
		 return Result.ok("提取成功！");
	 }
	
	/**
	 * 添加
	 *
	 * @param zhetcyxtj
	 * @return
	 */
	@AutoLog(value = "支行etc营销统计-添加")
	@ApiOperation(value="支行etc营销统计-添加", notes="支行etc营销统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zhetcyxtj zhetcyxtj) {
		zhetcyxtjService.save(zhetcyxtj);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param zhetcyxtj
	 * @return
	 */
	@AutoLog(value = "支行etc营销统计-编辑")
	@ApiOperation(value="支行etc营销统计-编辑", notes="支行etc营销统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zhetcyxtj zhetcyxtj) {
		zhetcyxtjService.updateById(zhetcyxtj);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行etc营销统计-通过id删除")
	@ApiOperation(value="支行etc营销统计-通过id删除", notes="支行etc营销统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zhetcyxtjService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "支行etc营销统计-批量删除")
	@ApiOperation(value="支行etc营销统计-批量删除", notes="支行etc营销统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zhetcyxtjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行etc营销统计-通过id查询")
	@ApiOperation(value="支行etc营销统计-通过id查询", notes="支行etc营销统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zhetcyxtj zhetcyxtj = zhetcyxtjService.getById(id);
		return Result.ok(zhetcyxtj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zhetcyxtj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zhetcyxtj zhetcyxtj) {
      return super.exportXls(request, zhetcyxtj, Zhetcyxtj.class, "支行etc营销统计");
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
      return super.importExcel(request, response, Zhetcyxtj.class);
  }

}
