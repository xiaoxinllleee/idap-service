package org.cmms.modules.sjxf.qtxt.czzb.bdgxxx.controller;

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
import org.cmms.modules.sjxf.qtxt.czzb.bdgxxx.entity.Bdgxxx;
import org.cmms.modules.sjxf.qtxt.czzb.bdgxxx.service.IBdgxxxService;
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
 * @Description: 财政惠农补贴直补绑定关系信息
 * @Author: jeecg-boot
 * @Date:   2021-12-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags="财政惠农补贴直补绑定关系信息")
@RestController
@RequestMapping("/bdgxxx/bdgxxx")
public class BdgxxxController extends JeecgController<Bdgxxx, IBdgxxxService> {
	@Autowired
	private IBdgxxxService bdgxxxService;

	/**
	 * 分页列表查询
	 *
	 * @param bdgxxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "财政惠农补贴直补绑定关系信息-分页列表查询")
	@ApiOperation(value="财政惠农补贴直补绑定关系信息-分页列表查询", notes="财政惠农补贴直补绑定关系信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Bdgxxx bdgxxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Bdgxxx> queryWrapper = QueryGenerator.initQueryWrapper(bdgxxx, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IBdgxxxService.class,bdgxxxService,pageNo,pageSize,queryWrapper,"acct");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param bdgxxx
	 * @return
	 */
	@AutoLog(value = "财政惠农补贴直补绑定关系信息-添加")
	@ApiOperation(value="财政惠农补贴直补绑定关系信息-添加", notes="财政惠农补贴直补绑定关系信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Bdgxxx bdgxxx) {
		bdgxxxService.save(bdgxxx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param bdgxxx
	 * @return
	 */
	@AutoLog(value = "财政惠农补贴直补绑定关系信息-编辑")
	@ApiOperation(value="财政惠农补贴直补绑定关系信息-编辑", notes="财政惠农补贴直补绑定关系信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Bdgxxx bdgxxx) {
		bdgxxxService.updateById(bdgxxx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "财政惠农补贴直补绑定关系信息-通过id删除")
	@ApiOperation(value="财政惠农补贴直补绑定关系信息-通过id删除", notes="财政惠农补贴直补绑定关系信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		bdgxxxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "财政惠农补贴直补绑定关系信息-批量删除")
	@ApiOperation(value="财政惠农补贴直补绑定关系信息-批量删除", notes="财政惠农补贴直补绑定关系信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.bdgxxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "财政惠农补贴直补绑定关系信息-通过id查询")
	@ApiOperation(value="财政惠农补贴直补绑定关系信息-通过id查询", notes="财政惠农补贴直补绑定关系信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Bdgxxx bdgxxx = bdgxxxService.getById(id);
		return Result.ok(bdgxxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param bdgxxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Bdgxxx bdgxxx) {
      return super.exportXls(request, bdgxxx, Bdgxxx.class, "财政惠农补贴直补绑定关系信息");
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
      return super.importExcel(request, response, Bdgxxx.class);
  }

}
