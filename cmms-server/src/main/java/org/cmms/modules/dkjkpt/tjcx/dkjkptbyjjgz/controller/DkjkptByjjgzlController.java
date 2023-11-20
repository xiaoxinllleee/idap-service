package org.cmms.modules.dkjkpt.tjcx.dkjkptbyjjgz.controller;

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
import org.cmms.modules.dkjkpt.tjcx.dkjkptbyjjgz.entity.DkjkptByjjgzl;
import org.cmms.modules.dkjkpt.tjcx.dkjkptbyjjgz.service.IDkjkptByjjgzlService;
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
@RequestMapping("/dkjkptbyjjgzl/dkjkptByjjgzl")
public class DkjkptByjjgzlController extends JeecgController<DkjkptByjjgzl, IDkjkptByjjgzlService> {
	@Autowired
	private IDkjkptByjjgzlService dkjkptByjjgzlService;

	/**
	 * 分页列表查询
	 *
	 * @param dkjkptByjjgzl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "本月将进关注-分页列表查询")
	@ApiOperation(value="本月将进关注-分页列表查询", notes="本月将进关注-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DkjkptByjjgzl dkjkptByjjgzl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DkjkptByjjgzl> queryWrapper = QueryGenerator.initQueryWrapper(dkjkptByjjgzl, req.getParameterMap());
		queryWrapper.orderByAsc("dqrq");
		Page<DkjkptByjjgzl> page = new Page<DkjkptByjjgzl>(pageNo, pageSize);
		IPage<DkjkptByjjgzl> pageList = dkjkptByjjgzlService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dkjkptByjjgzl
	 * @return
	 */
	@AutoLog(value = "本月将进关注-添加")
	@ApiOperation(value="本月将进关注-添加", notes="本月将进关注-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DkjkptByjjgzl dkjkptByjjgzl) {
		dkjkptByjjgzlService.save(dkjkptByjjgzl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dkjkptByjjgzl
	 * @return
	 */
	@AutoLog(value = "本月将进关注-编辑")
	@ApiOperation(value="本月将进关注-编辑", notes="本月将进关注-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DkjkptByjjgzl dkjkptByjjgzl) {
		dkjkptByjjgzlService.updateById(dkjkptByjjgzl);
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
		dkjkptByjjgzlService.removeById(id);
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
		this.dkjkptByjjgzlService.removeByIds(Arrays.asList(ids.split(",")));
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
		DkjkptByjjgzl dkjkptByjjgzl = dkjkptByjjgzlService.getById(id);
		return Result.ok(dkjkptByjjgzl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkjkptByjjgzl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DkjkptByjjgzl dkjkptByjjgzl) {
      return super.exportXls(request, dkjkptByjjgzl, DkjkptByjjgzl.class, "本月将进关注");
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
      return super.importExcel(request, response, DkjkptByjjgzl.class);
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
			 dkjkptByjjgzlService.extract(tjyf);
		 } catch (Exception e) {
			 log.error("提取失败",e.getMessage());
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功!");
	 }
}
