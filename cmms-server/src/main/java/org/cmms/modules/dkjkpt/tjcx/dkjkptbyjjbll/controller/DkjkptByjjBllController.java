package org.cmms.modules.dkjkpt.tjcx.dkjkptbyjjbll.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
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
import org.cmms.modules.dkjkpt.tjcx.dkjkptbyjjbll.entity.DkjkptByjjBll;
import org.cmms.modules.dkjkpt.tjcx.dkjkptbyjjbll.service.IDkjkptByjjBllService;
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
 * @Description: 本月将进关注
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
@Slf4j
@Api(tags="本月将进关注")
@RestController
@RequestMapping("/dkjkptbyjjbll/dkjkptByjjBll")
public class DkjkptByjjBllController extends JeecgController<DkjkptByjjBll, IDkjkptByjjBllService> {
	@Autowired
	private IDkjkptByjjBllService dkjkptByjjBllService;

	/**
	 * 分页列表查询
	 *
	 * @param dkjkptByjjBll
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "本月将进关注-分页列表查询")
	@ApiOperation(value="本月将进关注-分页列表查询", notes="本月将进关注-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DkjkptByjjBll dkjkptByjjBll,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DkjkptByjjBll> queryWrapper = QueryGenerator.initQueryWrapper(dkjkptByjjBll, req.getParameterMap());
		Page<DkjkptByjjBll> page = new Page<DkjkptByjjBll>(pageNo, pageSize);
		queryWrapper.orderByAsc("dqrq");
		IPage<DkjkptByjjBll> pageList = dkjkptByjjBllService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dkjkptByjjBll
	 * @return
	 */
	@AutoLog(value = "本月将进关注-添加")
	@ApiOperation(value="本月将进关注-添加", notes="本月将进关注-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DkjkptByjjBll dkjkptByjjBll) {
		dkjkptByjjBllService.save(dkjkptByjjBll);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dkjkptByjjBll
	 * @return
	 */
	@AutoLog(value = "本月将进关注-编辑")
	@ApiOperation(value="本月将进关注-编辑", notes="本月将进关注-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DkjkptByjjBll dkjkptByjjBll) {
		dkjkptByjjBllService.updateById(dkjkptByjjBll);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "本月将进关注-通过id删除")
	@ApiOperation(value="本月将进关注-通过id删除", notes="本月将进关注-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkjkptByjjBllService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "本月将进关注-批量删除")
	@ApiOperation(value="本月将进关注-批量删除", notes="本月将进关注-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkjkptByjjBllService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "本月将进关注-通过id查询")
	@ApiOperation(value="本月将进关注-通过id查询", notes="本月将进关注-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DkjkptByjjBll dkjkptByjjBll = dkjkptByjjBllService.getById(id);
		return Result.ok(dkjkptByjjBll);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkjkptByjjBll
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DkjkptByjjBll dkjkptByjjBll) {
      return super.exportXls(request, dkjkptByjjBll, DkjkptByjjBll.class, "本月将进不良");
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
      return super.importExcel(request, response, DkjkptByjjBll.class);
  }
	 /**
	  *   统计
	  * @return
	  */
	 @PutMapping(value = "/extract")
	 public Result<?> extract1(@RequestBody JSONObject jsonObject) {
		 String tjyf = jsonObject.getString("tjyf");
		 tjyf = tjyf.replace("-", "");
		 try {
			 dkjkptByjjBllService.extract(tjyf);
		 } catch (Exception e) {
			 log.error("提取失败",e.getMessage());
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功!");
	 }
}
