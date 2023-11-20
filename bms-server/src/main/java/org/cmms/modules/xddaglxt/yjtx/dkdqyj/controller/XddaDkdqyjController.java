package org.cmms.modules.xddaglxt.yjtx.dkdqyj.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.cmms.modules.xddaglxt.yjtx.dkdqyj.entity.XddaDkdqyj;
import org.cmms.modules.xddaglxt.yjtx.dkdqyj.service.IXddaDkdqyjService;
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
 * @Description: 贷款到期预警
 * @Author: jeecg-boot
 * @Date:   2021-12-01
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款到期预警")
@RestController
@RequestMapping("/dkdqyj/xddaDkdqyj")
public class XddaDkdqyjController extends JeecgController<XddaDkdqyj, IXddaDkdqyjService> {
	@Autowired
	private IXddaDkdqyjService xddaDkdqyjService;
	
	/**
	 * 分页列表查询
	 *
	 * @param xddaDkdqyj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款到期预警-分页列表查询")
	@ApiOperation(value="贷款到期预警-分页列表查询", notes="贷款到期预警-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(XddaDkdqyj xddaDkdqyj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   String jkrqString,String dqrqString,
								   HttpServletRequest req) {
		QueryWrapper<XddaDkdqyj> queryWrapper = QueryGenerator.initQueryWrapper(xddaDkdqyj, req.getParameterMap());
		if (jkrqString != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] date = jkrqString.split(",");
			try {
				queryWrapper.between("jkrq",sdf.parse(date[0]),sdf.parse(date[1]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (dqrqString != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] date = dqrqString.split(",");
			try {
				queryWrapper.between("dqrq",sdf.parse(date[0]),sdf.parse(date[1]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Page<XddaDkdqyj> page = new Page<XddaDkdqyj>(pageNo, pageSize);
		IPage<XddaDkdqyj> pageList = xddaDkdqyjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param xddaDkdqyj
	 * @return
	 */
	@AutoLog(value = "贷款到期预警-添加")
	@ApiOperation(value="贷款到期预警-添加", notes="贷款到期预警-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody XddaDkdqyj xddaDkdqyj) {
		xddaDkdqyjService.save(xddaDkdqyj);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param xddaDkdqyj
	 * @return
	 */
	@AutoLog(value = "贷款到期预警-编辑")
	@ApiOperation(value="贷款到期预警-编辑", notes="贷款到期预警-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody XddaDkdqyj xddaDkdqyj) {
		xddaDkdqyjService.updateById(xddaDkdqyj);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款到期预警-通过id删除")
	@ApiOperation(value="贷款到期预警-通过id删除", notes="贷款到期预警-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		xddaDkdqyjService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款到期预警-批量删除")
	@ApiOperation(value="贷款到期预警-批量删除", notes="贷款到期预警-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.xddaDkdqyjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款到期预警-通过id查询")
	@ApiOperation(value="贷款到期预警-通过id查询", notes="贷款到期预警-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		XddaDkdqyj xddaDkdqyj = xddaDkdqyjService.getById(id);
		return Result.ok(xddaDkdqyj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param xddaDkdqyj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, XddaDkdqyj xddaDkdqyj) {
      return super.exportXls(request, xddaDkdqyj, XddaDkdqyj.class, "贷款到期预警");
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
      return super.importExcel(request, response, XddaDkdqyj.class);
  }

}
