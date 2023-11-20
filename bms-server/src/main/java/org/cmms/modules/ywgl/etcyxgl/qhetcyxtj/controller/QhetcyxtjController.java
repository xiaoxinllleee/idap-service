package org.cmms.modules.ywgl.etcyxgl.qhetcyxtj.controller;

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
import org.cmms.modules.ywgl.etcyxgl.qhetcyxtj.entity.Qhetcyxtj;
import org.cmms.modules.ywgl.etcyxgl.qhetcyxtj.mapper.QhetcyxtjMapper;
import org.cmms.modules.ywgl.etcyxgl.qhetcyxtj.service.IQhetcyxtjService;
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
 * @Description: 全行etc营销统计
 * @Author: jeecg-boot
 * @Date:   2021-09-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="全行etc营销统计")
@RestController
@RequestMapping("/qhetcyxtj/qhetcyxtj")
public class QhetcyxtjController extends JeecgController<Qhetcyxtj, IQhetcyxtjService> {
	@Autowired
	private IQhetcyxtjService qhetcyxtjService;

	@Autowired(required = false)
	private QhetcyxtjMapper qhetcyxtjMapper;
	
	/**
	 * 分页列表查询
	 *
	 * @param qhetcyxtj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "全行etc营销统计-分页列表查询")
	@ApiOperation(value="全行etc营销统计-分页列表查询", notes="全行etc营销统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Qhetcyxtj qhetcyxtj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Qhetcyxtj> queryWrapper = QueryGenerator.initQueryWrapper(qhetcyxtj, req.getParameterMap());

		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IQhetcyxtjService.class,qhetcyxtjService,pageNo,pageSize,queryWrapper,"tjyf");
		return Result.ok(pageList);
	}

	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/init")
	 public Result<?> init(String tjyf){
	 	if (tjyf == null || tjyf == "")return Result.error("日期格式错误！");
		 if (-1 == DateUtil.getCurrentTS().compareTo(DateUtil.parseDateFormat(tjyf,"yyyy-MM-dd")))
			 return Result.error("选择月份必须小于当前系统月份！");
		 tjyf = tjyf.replaceAll("-","");
		 try {
		 	qhetcyxtjMapper.pQhetcyxtj(tjyf);
		 }catch (Throwable e){
		 	e.printStackTrace();
		 	return Result.error("提取失败，请查看系统监控日志信息");
		 }
		 return Result.ok("提取成功！");
	 }

	
	/**
	 * 添加
	 *
	 * @param qhetcyxtj
	 * @return
	 */
	@AutoLog(value = "全行etc营销统计-添加")
	@ApiOperation(value="全行etc营销统计-添加", notes="全行etc营销统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Qhetcyxtj qhetcyxtj) {
		qhetcyxtjService.save(qhetcyxtj);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param qhetcyxtj
	 * @return
	 */
	@AutoLog(value = "全行etc营销统计-编辑")
	@ApiOperation(value="全行etc营销统计-编辑", notes="全行etc营销统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Qhetcyxtj qhetcyxtj) {
		qhetcyxtjService.updateById(qhetcyxtj);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全行etc营销统计-通过id删除")
	@ApiOperation(value="全行etc营销统计-通过id删除", notes="全行etc营销统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		qhetcyxtjService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "全行etc营销统计-批量删除")
	@ApiOperation(value="全行etc营销统计-批量删除", notes="全行etc营销统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.qhetcyxtjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全行etc营销统计-通过id查询")
	@ApiOperation(value="全行etc营销统计-通过id查询", notes="全行etc营销统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Qhetcyxtj qhetcyxtj = qhetcyxtjService.getById(id);
		return Result.ok(qhetcyxtj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param qhetcyxtj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Qhetcyxtj qhetcyxtj) {
      return super.exportXls(request, qhetcyxtj, Qhetcyxtj.class, "全行etc营销统计");
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
      return super.importExcel(request, response, Qhetcyxtj.class);
  }

}
