package org.cmms.modules.sjxf.qtxt.dssf.dssfzdflb.controller;

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
import org.cmms.modules.sjxf.qtxt.dssf.dssfzdflb.entity.Dssfzdflb;
import org.cmms.modules.sjxf.qtxt.dssf.dssfzdflb.service.IDssfzdflbService;
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
 * @Description: 代收水费自动分录表
 * @Author: jeecg-boot
 * @Date:   2021-12-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags="代收水费自动分录表")
@RestController
@RequestMapping("/dssfzdflb/dssfzdflb")
public class DssfzdflbController extends JeecgController<Dssfzdflb, IDssfzdflbService> {
	@Autowired
	private IDssfzdflbService dssfzdflbService;

	/**
	 * 分页列表查询
	 *
	 * @param dssfzdflb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "代收水费自动分录表-分页列表查询")
	@ApiOperation(value="代收水费自动分录表-分页列表查询", notes="代收水费自动分录表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dssfzdflb dssfzdflb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dssfzdflb> queryWrapper = QueryGenerator.initQueryWrapper(dssfzdflb, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IDssfzdflbService.class,dssfzdflbService,pageNo,pageSize,queryWrapper,"flzh");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dssfzdflb
	 * @return
	 */
	@AutoLog(value = "代收水费自动分录表-添加")
	@ApiOperation(value="代收水费自动分录表-添加", notes="代收水费自动分录表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dssfzdflb dssfzdflb) {
		dssfzdflbService.save(dssfzdflb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dssfzdflb
	 * @return
	 */
	@AutoLog(value = "代收水费自动分录表-编辑")
	@ApiOperation(value="代收水费自动分录表-编辑", notes="代收水费自动分录表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dssfzdflb dssfzdflb) {
		dssfzdflbService.updateById(dssfzdflb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "代收水费自动分录表-通过id删除")
	@ApiOperation(value="代收水费自动分录表-通过id删除", notes="代收水费自动分录表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dssfzdflbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "代收水费自动分录表-批量删除")
	@ApiOperation(value="代收水费自动分录表-批量删除", notes="代收水费自动分录表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dssfzdflbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "代收水费自动分录表-通过id查询")
	@ApiOperation(value="代收水费自动分录表-通过id查询", notes="代收水费自动分录表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dssfzdflb dssfzdflb = dssfzdflbService.getById(id);
		return Result.ok(dssfzdflb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dssfzdflb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dssfzdflb dssfzdflb) {
      return super.exportXls(request, dssfzdflb, Dssfzdflb.class, "代收水费自动分录表");
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
      return super.importExcel(request, response, Dssfzdflb.class);
  }

}
