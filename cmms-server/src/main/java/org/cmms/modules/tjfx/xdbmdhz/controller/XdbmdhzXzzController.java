package org.cmms.modules.tjfx.xdbmdhz.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import org.cmms.modules.tjfx.xdbmdhz.entity.XdbmdhzXzz;
import org.cmms.modules.tjfx.xdbmdhz.service.IXdbmdhzXzzService;
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
 * @Description: 信贷白名单汇总
 * @Author: jeecg-boot
 * @Date:   2022-07-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="信贷白名单汇总")
@RestController
@RequestMapping("/tjfx/xdbmdhzXzz")
public class XdbmdhzXzzController extends JeecgController<XdbmdhzXzz, IXdbmdhzXzzService> {
	@Autowired
	private IXdbmdhzXzzService xdbmdhzXzzService;
	
	/**
	 * 分页列表查询
	 *
	 * @param xdbmdhzXzz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "信贷白名单汇总-分页列表查询")
	@ApiOperation(value="信贷白名单汇总-分页列表查询", notes="信贷白名单汇总-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(XdbmdhzXzz xdbmdhzXzz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String wgbh = xdbmdhzXzz.getWgbh();
		xdbmdhzXzz.setWgbh(null);
		QueryWrapper<XdbmdhzXzz> queryWrapper = QueryGenerator.initQueryWrapper(xdbmdhzXzz, req.getParameterMap());
		//查询网格时，同时查询下级网格的数据
		if (StringUtils.isNotEmpty(wgbh)) {
			String sqlSswg = "select wgbh from yxdygl_main t where wgbh ='" + wgbh + "' or parent_id='" + wgbh + "'";
			queryWrapper.and(i -> i.inSql("wgbh", sqlSswg));
		}
		Page<XdbmdhzXzz> page = new Page<XdbmdhzXzz>(pageNo, pageSize);
		IPage<XdbmdhzXzz> pageList = xdbmdhzXzzService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param xdbmdhzXzz
	 * @return
	 */
	@AutoLog(value = "信贷白名单汇总-添加")
	@ApiOperation(value="信贷白名单汇总-添加", notes="信贷白名单汇总-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody XdbmdhzXzz xdbmdhzXzz) {
		xdbmdhzXzzService.save(xdbmdhzXzz);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param xdbmdhzXzz
	 * @return
	 */
	@AutoLog(value = "信贷白名单汇总-编辑")
	@ApiOperation(value="信贷白名单汇总-编辑", notes="信贷白名单汇总-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody XdbmdhzXzz xdbmdhzXzz) {
		xdbmdhzXzzService.updateById(xdbmdhzXzz);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "信贷白名单汇总-通过id删除")
	@ApiOperation(value="信贷白名单汇总-通过id删除", notes="信贷白名单汇总-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		xdbmdhzXzzService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "信贷白名单汇总-批量删除")
	@ApiOperation(value="信贷白名单汇总-批量删除", notes="信贷白名单汇总-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.xdbmdhzXzzService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "信贷白名单汇总-通过id查询")
	@ApiOperation(value="信贷白名单汇总-通过id查询", notes="信贷白名单汇总-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		XdbmdhzXzz xdbmdhzXzz = xdbmdhzXzzService.getById(id);
		return Result.ok(xdbmdhzXzz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param xdbmdhzXzz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, XdbmdhzXzz xdbmdhzXzz) {
      return super.exportXls(request, xdbmdhzXzz, XdbmdhzXzz.class, "信贷白名单汇总");
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
      return super.importExcel(request, response, XdbmdhzXzz.class);
  }

}
