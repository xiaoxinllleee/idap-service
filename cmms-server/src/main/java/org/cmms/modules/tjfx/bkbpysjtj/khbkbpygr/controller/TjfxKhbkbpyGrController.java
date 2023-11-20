package org.cmms.modules.tjfx.bkbpysjtj.khbkbpygr.controller;

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
import org.cmms.modules.tjfx.bkbpysjtj.khbkbpygr.entity.TjfxKhbkbpyGr;
import org.cmms.modules.tjfx.bkbpysjtj.khbkbpygr.service.ITjfxKhbkbpyGrService;
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
 * @Description: 个人背靠背评议明细
 * @Author: jeecg-boot
 * @Date:   2020-08-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags="个人背靠背评议明细")
@RestController
@RequestMapping("/TjfxKhbkbpyGr/tjfxKhbkbpyGr")
public class TjfxKhbkbpyGrController extends JeecgController<TjfxKhbkbpyGr, ITjfxKhbkbpyGrService> {
	@Autowired
	private ITjfxKhbkbpyGrService tjfxKhbkbpyGrService;
	
	/**
	 * 分页列表查询
	 *
	 * @param tjfxKhbkbpyGr
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "个人背靠背评议明细-分页列表查询")
	@ApiOperation(value="个人背靠背评议明细-分页列表查询", notes="个人背靠背评议明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TjfxKhbkbpyGr tjfxKhbkbpyGr,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TjfxKhbkbpyGr> queryWrapper = QueryGenerator.initQueryWrapper(tjfxKhbkbpyGr, req.getParameterMap());
		Page<TjfxKhbkbpyGr> page = new Page<TjfxKhbkbpyGr>(pageNo, pageSize);
		IPage<TjfxKhbkbpyGr> pageList = tjfxKhbkbpyGrService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param tjfxKhbkbpyGr
	 * @return
	 */
	@AutoLog(value = "个人背靠背评议明细-添加")
	@ApiOperation(value="个人背靠背评议明细-添加", notes="个人背靠背评议明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TjfxKhbkbpyGr tjfxKhbkbpyGr) {
		tjfxKhbkbpyGrService.save(tjfxKhbkbpyGr);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param tjfxKhbkbpyGr
	 * @return
	 */
	@AutoLog(value = "个人背靠背评议明细-编辑")
	@ApiOperation(value="个人背靠背评议明细-编辑", notes="个人背靠背评议明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TjfxKhbkbpyGr tjfxKhbkbpyGr) {
		tjfxKhbkbpyGrService.updateById(tjfxKhbkbpyGr);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "个人背靠背评议明细-通过id删除")
	@ApiOperation(value="个人背靠背评议明细-通过id删除", notes="个人背靠背评议明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tjfxKhbkbpyGrService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "个人背靠背评议明细-批量删除")
	@ApiOperation(value="个人背靠背评议明细-批量删除", notes="个人背靠背评议明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tjfxKhbkbpyGrService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "个人背靠背评议明细-通过id查询")
	@ApiOperation(value="个人背靠背评议明细-通过id查询", notes="个人背靠背评议明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TjfxKhbkbpyGr tjfxKhbkbpyGr = tjfxKhbkbpyGrService.getById(id);
		return Result.ok(tjfxKhbkbpyGr);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param tjfxKhbkbpyGr
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, TjfxKhbkbpyGr tjfxKhbkbpyGr) {
      return super.exportXls(request, tjfxKhbkbpyGr, TjfxKhbkbpyGr.class, "个人背靠背评议明细");
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
      return super.importExcel(request, response, TjfxKhbkbpyGr.class);
  }

}
