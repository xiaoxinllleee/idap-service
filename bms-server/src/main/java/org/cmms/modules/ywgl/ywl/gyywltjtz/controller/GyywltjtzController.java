package org.cmms.modules.ywgl.ywl.gyywltjtz.controller;

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
import org.cmms.modules.ywgl.ywl.gyywltjtz.entity.Gyywltjtz;
import org.cmms.modules.ywgl.ywl.gyywltjtz.mapper.GyywltjtzMapper;
import org.cmms.modules.ywgl.ywl.gyywltjtz.service.IGyywltjtzService;
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
 * @Description: 柜员业务量统计调整
 * @Author: jeecg-boot
 * @Date:   2021-09-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="柜员业务量统计调整")
@RestController
@RequestMapping("/gyywltjtz/gyywltjtz")
public class GyywltjtzController extends JeecgController<Gyywltjtz, IGyywltjtzService> {
	@Autowired
	private IGyywltjtzService gyywltjtzService;

	@Autowired(required = false)
	private GyywltjtzMapper gyywltjtzMapper;
	
	/**
	 * 分页列表查询
	 *
	 * @param gyywltjtz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "柜员业务量统计调整-分页列表查询")
	@ApiOperation(value="柜员业务量统计调整-分页列表查询", notes="柜员业务量统计调整-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Gyywltjtz gyywltjtz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Gyywltjtz> queryWrapper = QueryGenerator.initQueryWrapper(gyywltjtz, req.getParameterMap());
		Page<Gyywltjtz> page = new Page<Gyywltjtz>(pageNo, pageSize);
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IGyywltjtzService.class,gyywltjtzService,pageNo,pageSize,queryWrapper,"tjyf", "zzbz","gwbz","yggh");
		return Result.ok(pageList);
	}

	 /**
	  * 提取
	  */
	 @DS("cdkyw")
	 @RequestMapping(value = "/init")
	 public Result<?> init(String tjyf){
	 	if (tjyf == null || tjyf == "")return Result.error("统计月份不能为空");
		 if (-1 == DateUtil.getCurrentTS().compareTo(DateUtil.parseDateFormat(tjyf,"yyyy-MM-dd")))
			 return Result.error("选择月份必须小于当前系统月份！");
		 tjyf = tjyf.replaceAll("-","");
		 try {
		 	gyywltjtzMapper.pGyywltjtz(tjyf);
		 }catch (Throwable e){
		 	e.printStackTrace();
		 	return Result.error("提取失败，请查看系统监控日志信息");
		 }
		 return Result.ok("提取成功！");
	 }
	
	/**
	 * 添加
	 *
	 * @param gyywltjtz
	 * @return
	 */
	@AutoLog(value = "柜员业务量统计调整-添加")
	@ApiOperation(value="柜员业务量统计调整-添加", notes="柜员业务量统计调整-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Gyywltjtz gyywltjtz) {
		gyywltjtzService.save(gyywltjtz);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param gyywltjtz
	 * @return
	 */
	@AutoLog(value = "柜员业务量统计调整-编辑")
	@ApiOperation(value="柜员业务量统计调整-编辑", notes="柜员业务量统计调整-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Gyywltjtz gyywltjtz) {
		gyywltjtzService.updateById(gyywltjtz);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "柜员业务量统计调整-通过id删除")
	@ApiOperation(value="柜员业务量统计调整-通过id删除", notes="柜员业务量统计调整-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		gyywltjtzService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "柜员业务量统计调整-批量删除")
	@ApiOperation(value="柜员业务量统计调整-批量删除", notes="柜员业务量统计调整-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.gyywltjtzService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "柜员业务量统计调整-通过id查询")
	@ApiOperation(value="柜员业务量统计调整-通过id查询", notes="柜员业务量统计调整-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Gyywltjtz gyywltjtz = gyywltjtzService.getById(id);
		return Result.ok(gyywltjtz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param gyywltjtz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Gyywltjtz gyywltjtz) {
      return super.exportXls(request, gyywltjtz, Gyywltjtz.class, "柜员业务量统计调整");
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
      return super.importExcel(request, response, Gyywltjtz.class);
  }

}
