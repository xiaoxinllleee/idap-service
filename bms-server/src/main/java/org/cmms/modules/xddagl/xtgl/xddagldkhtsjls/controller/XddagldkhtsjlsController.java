package org.cmms.modules.xddagl.xtgl.xddagldkhtsjls.controller;

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
import org.cmms.modules.xddagl.xtgl.xddagldkhtsjls.entity.Xddagldkhtsjls;
import org.cmms.modules.xddagl.xtgl.xddagldkhtsjls.service.IXddagldkhtsjlsService;
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
 * @Description: 贷款合同数据流水
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款合同数据流水")
@RestController
@RequestMapping("/xddagldkhtsjls/xddagldkhtsjls")
public class XddagldkhtsjlsController extends JeecgController<Xddagldkhtsjls, IXddagldkhtsjlsService> {
	@Autowired
	private IXddagldkhtsjlsService xddagldkhtsjlsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param xddagldkhtsjls
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款合同数据流水-分页列表查询")
	@ApiOperation(value="贷款合同数据流水-分页列表查询", notes="贷款合同数据流水-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Xddagldkhtsjls xddagldkhtsjls,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Xddagldkhtsjls> queryWrapper = QueryGenerator.initQueryWrapper(xddagldkhtsjls, req.getParameterMap());
		Page<Xddagldkhtsjls> page = new Page<Xddagldkhtsjls>(pageNo, pageSize);
		IPage<Xddagldkhtsjls> pageList = xddagldkhtsjlsService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param xddagldkhtsjls
	 * @return
	 */
	@AutoLog(value = "贷款合同数据流水-添加")
	@ApiOperation(value="贷款合同数据流水-添加", notes="贷款合同数据流水-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Xddagldkhtsjls xddagldkhtsjls) {
		xddagldkhtsjlsService.save(xddagldkhtsjls);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param xddagldkhtsjls
	 * @return
	 */
	@AutoLog(value = "贷款合同数据流水-编辑")
	@ApiOperation(value="贷款合同数据流水-编辑", notes="贷款合同数据流水-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Xddagldkhtsjls xddagldkhtsjls) {
		xddagldkhtsjlsService.updateById(xddagldkhtsjls);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款合同数据流水-通过id删除")
	@ApiOperation(value="贷款合同数据流水-通过id删除", notes="贷款合同数据流水-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		xddagldkhtsjlsService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款合同数据流水-批量删除")
	@ApiOperation(value="贷款合同数据流水-批量删除", notes="贷款合同数据流水-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.xddagldkhtsjlsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款合同数据流水-通过id查询")
	@ApiOperation(value="贷款合同数据流水-通过id查询", notes="贷款合同数据流水-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Xddagldkhtsjls xddagldkhtsjls = xddagldkhtsjlsService.getById(id);
		return Result.ok(xddagldkhtsjls);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param xddagldkhtsjls
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Xddagldkhtsjls xddagldkhtsjls) {
      return super.exportXls(request, xddagldkhtsjls, Xddagldkhtsjls.class, "贷款合同数据流水");
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
      return super.importExcel(request, response, Xddagldkhtsjls.class);
  }

}
