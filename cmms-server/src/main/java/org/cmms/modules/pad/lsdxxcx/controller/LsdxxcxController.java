package org.cmms.modules.pad.lsdxxcx.controller;

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
import org.cmms.modules.pad.lsdxxcx.entity.Lsdxxcx;
import org.cmms.modules.pad.lsdxxcx.service.ILsdxxcxService;
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
 * @Description: 流水贷信息查询
 * @Author: jeecg-boot
 * @Date:   2023-05-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags="流水贷信息查询")
@RestController
@RequestMapping("/lsdxxcx/lsdxxcx")
public class LsdxxcxController extends JeecgController<Lsdxxcx, ILsdxxcxService> {
	@Autowired
	private ILsdxxcxService lsdxxcxService;

	/**
	 * 分页列表查询
	 *
	 * @param lsdxxcx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "流水贷信息查询-分页列表查询")
	@ApiOperation(value="流水贷信息查询-分页列表查询", notes="流水贷信息查询-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Lsdxxcx lsdxxcx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req,String khmc,String zjhm) {
		Page<Lsdxxcx> page = new Page<Lsdxxcx>(pageNo, pageSize);
		IPage<Lsdxxcx> pageList = lsdxxcxService.getMaxData(page, khmc,zjhm);
		return Result.ok(pageList);
	}
	 /**
	  * 分页列表查询
	  *
	  * @param lsdxxcx
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "流水贷信息查询-分页列表查询")
	 @ApiOperation(value="流水贷信息查询-分页列表查询", notes="流水贷信息查询-分页列表查询")
	 @GetMapping(value = "/list2")
	 public Result<?> queryPageList2(Lsdxxcx lsdxxcx,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		 QueryWrapper<Lsdxxcx> queryWrapper = QueryGenerator.initQueryWrapper(lsdxxcx, req.getParameterMap());
		 Page<Lsdxxcx> page = new Page<Lsdxxcx>(pageNo, pageSize);
		 IPage<Lsdxxcx> pageList = lsdxxcxService.page(page, queryWrapper);
		 return Result.ok(pageList);
	 }

	/**
	 * 添加
	 *
	 * @param lsdxxcx
	 * @return
	 */
	@AutoLog(value = "流水贷信息查询-添加")
	@ApiOperation(value="流水贷信息查询-添加", notes="流水贷信息查询-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Lsdxxcx lsdxxcx) {
		lsdxxcxService.save(lsdxxcx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param lsdxxcx
	 * @return
	 */
	@AutoLog(value = "流水贷信息查询-编辑")
	@ApiOperation(value="流水贷信息查询-编辑", notes="流水贷信息查询-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Lsdxxcx lsdxxcx) {
		lsdxxcxService.updateById(lsdxxcx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "流水贷信息查询-通过id删除")
	@ApiOperation(value="流水贷信息查询-通过id删除", notes="流水贷信息查询-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		lsdxxcxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "流水贷信息查询-批量删除")
	@ApiOperation(value="流水贷信息查询-批量删除", notes="流水贷信息查询-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.lsdxxcxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "流水贷信息查询-通过id查询")
	@ApiOperation(value="流水贷信息查询-通过id查询", notes="流水贷信息查询-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Lsdxxcx lsdxxcx = lsdxxcxService.getById(id);
		return Result.ok(lsdxxcx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param lsdxxcx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Lsdxxcx lsdxxcx) {
      return super.exportXls(request, lsdxxcx, Lsdxxcx.class, "流水贷信息查询");
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
      return super.importExcel(request, response, Lsdxxcx.class);
  }

}
