package org.cmms.modules.sjxf.hxxt.ckls_39.controller;

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
import org.cmms.modules.sjxf.hxxt.ckls_39.entity.Ckls_39;
import org.cmms.modules.sjxf.hxxt.ckls_39.service.ICkls_39Service;
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
 * @Description: 存款流水_39
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存款流水_39")
@RestController
@RequestMapping("/ckls_39/ckls_39")
public class Ckls_39Controller extends JeecgController<Ckls_39, ICkls_39Service> {
	@Autowired
	private ICkls_39Service ckls_39Service;

	/**
	 * 分页列表查询
	 *
	 * @param ckls_39
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "存款流水_39-分页列表查询")
	@ApiOperation(value="存款流水_39-分页列表查询", notes="存款流水_39-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ckls_39 ckls_39,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Ckls_39> queryWrapper = QueryGenerator.initQueryWrapper(ckls_39, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkls_39Service.class,ckls_39Service,pageNo,pageSize,queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param ckls_39
	 * @return
	 */
	@AutoLog(value = "存款流水_39-添加")
	@ApiOperation(value="存款流水_39-添加", notes="存款流水_39-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ckls_39 ckls_39) {
		ckls_39Service.save(ckls_39);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param ckls_39
	 * @return
	 */
	@AutoLog(value = "存款流水_39-编辑")
	@ApiOperation(value="存款流水_39-编辑", notes="存款流水_39-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ckls_39 ckls_39) {
		ckls_39Service.updateById(ckls_39);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款流水_39-通过id删除")
	@ApiOperation(value="存款流水_39-通过id删除", notes="存款流水_39-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ckls_39Service.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "存款流水_39-批量删除")
	@ApiOperation(value="存款流水_39-批量删除", notes="存款流水_39-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ckls_39Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款流水_39-通过id查询")
	@ApiOperation(value="存款流水_39-通过id查询", notes="存款流水_39-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ckls_39 ckls_39 = ckls_39Service.getById(id);
		return Result.ok(ckls_39);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ckls_39
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ckls_39 ckls_39) {
      return super.exportXls(request, ckls_39, Ckls_39.class, "存款流水_39");
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
      return super.importExcel(request, response, Ckls_39.class);
  }

}
