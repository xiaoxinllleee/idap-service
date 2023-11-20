package org.cmms.modules.tjfx.tjbb.khqzywbb.controller;

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
import org.cmms.modules.tjfx.tjbb.khqzywbb.entity.Qhkhqzywbb;
import org.cmms.modules.tjfx.tjbb.khqzywbb.service.IQhkhqzywbbService;
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
 * @Description: 客户潜在业务报表(全行)
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户潜在业务报表(全行)")
@RestController
@RequestMapping("/tjfx.tjbb.khqzywbb/qhkhqzywbb")
public class QhkhqzywbbController extends JeecgController<Qhkhqzywbb, IQhkhqzywbbService> {
	@Autowired
	private IQhkhqzywbbService qhkhqzywbbService;
	
	/**
	 * 分页列表查询
	 *
	 * @param qhkhqzywbb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户潜在业务报表(全行)-分页列表查询")
	@ApiOperation(value="客户潜在业务报表(全行)-分页列表查询", notes="客户潜在业务报表(全行)-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Qhkhqzywbb qhkhqzywbb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Qhkhqzywbb> queryWrapper = QueryGenerator.initQueryWrapper(qhkhqzywbb, req.getParameterMap());
		Page<Qhkhqzywbb> page = new Page<Qhkhqzywbb>(pageNo, pageSize);
		IPage<Qhkhqzywbb> pageList = qhkhqzywbbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param qhkhqzywbb
	 * @return
	 */
	@AutoLog(value = "客户潜在业务报表(全行)-添加")
	@ApiOperation(value="客户潜在业务报表(全行)-添加", notes="客户潜在业务报表(全行)-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Qhkhqzywbb qhkhqzywbb) {
		qhkhqzywbbService.save(qhkhqzywbb);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param qhkhqzywbb
	 * @return
	 */
	@AutoLog(value = "客户潜在业务报表(全行)-编辑")
	@ApiOperation(value="客户潜在业务报表(全行)-编辑", notes="客户潜在业务报表(全行)-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Qhkhqzywbb qhkhqzywbb) {
		qhkhqzywbbService.updateById(qhkhqzywbb);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户潜在业务报表(全行)-通过id删除")
	@ApiOperation(value="客户潜在业务报表(全行)-通过id删除", notes="客户潜在业务报表(全行)-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		qhkhqzywbbService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户潜在业务报表(全行)-批量删除")
	@ApiOperation(value="客户潜在业务报表(全行)-批量删除", notes="客户潜在业务报表(全行)-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.qhkhqzywbbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户潜在业务报表(全行)-通过id查询")
	@ApiOperation(value="客户潜在业务报表(全行)-通过id查询", notes="客户潜在业务报表(全行)-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Qhkhqzywbb qhkhqzywbb = qhkhqzywbbService.getById(id);
		return Result.ok(qhkhqzywbb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param qhkhqzywbb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Qhkhqzywbb qhkhqzywbb) {
      return super.exportXls(request, qhkhqzywbb, Qhkhqzywbb.class, "客户潜在业务报表(全行)");
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
      return super.importExcel(request, response, Qhkhqzywbb.class);
  }


	 @RequestMapping(value = "/extract" , method = RequestMethod.PUT)
	 public Result<?> extract() {
		 try {
			 qhkhqzywbbService.extract();
		 } catch (Exception e) {
			 log.error(e.getMessage(), "提取失败");
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功");
	 }

}
