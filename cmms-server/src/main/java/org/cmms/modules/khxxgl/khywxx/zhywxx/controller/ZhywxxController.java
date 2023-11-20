package org.cmms.modules.khxxgl.khywxx.zhywxx.controller;

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
import org.cmms.modules.khxxgl.khywxx.zhywxx.entity.Zhywxx;
import org.cmms.modules.khxxgl.khywxx.zhywxx.service.IZhywxxService;
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
 * @Description: 支行业务信息
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行业务信息")
@RestController
@RequestMapping("/zhywxx/zhywxx")
public class ZhywxxController extends JeecgController<Zhywxx, IZhywxxService> {
	@Autowired
	private IZhywxxService zhywxxService;
	
	/**
	 * 分页列表查询
	 *
	 * @param zhywxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行业务信息-分页列表查询")
	@ApiOperation(value="支行业务信息-分页列表查询", notes="支行业务信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zhywxx zhywxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Zhywxx> queryWrapper = QueryGenerator.initQueryWrapper(zhywxx, req.getParameterMap());
		Page<Zhywxx> page = new Page<Zhywxx>(pageNo, pageSize);
		IPage<Zhywxx> pageList = zhywxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param zhywxx
	 * @return
	 */
	@AutoLog(value = "支行业务信息-添加")
	@ApiOperation(value="支行业务信息-添加", notes="支行业务信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zhywxx zhywxx) {
		zhywxxService.save(zhywxx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param zhywxx
	 * @return
	 */
	@AutoLog(value = "支行业务信息-编辑")
	@ApiOperation(value="支行业务信息-编辑", notes="支行业务信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zhywxx zhywxx) {
		zhywxxService.updateById(zhywxx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行业务信息-通过id删除")
	@ApiOperation(value="支行业务信息-通过id删除", notes="支行业务信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zhywxxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "支行业务信息-批量删除")
	@ApiOperation(value="支行业务信息-批量删除", notes="支行业务信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zhywxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行业务信息-通过id查询")
	@ApiOperation(value="支行业务信息-通过id查询", notes="支行业务信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zhywxx zhywxx = zhywxxService.getById(id);
		return Result.ok(zhywxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zhywxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zhywxx zhywxx) {
      return super.exportXls(request, zhywxx, Zhywxx.class, "支行业务信息");
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
      return super.importExcel(request, response, Zhywxx.class);
  }

}
