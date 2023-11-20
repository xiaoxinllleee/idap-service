package org.cmms.modules.sjxf.qtxt.etcxx.bdxxb.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdcardUtil;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khgxgl.entity.KhgxglKhzlglGrkh;
import org.cmms.modules.khgxgl.entity.KhgxglKhzlglQykh;
import org.cmms.modules.khgxgl.service.IKhgxglKhzlglGrkhService;
import org.cmms.modules.khgxgl.service.IKhgxglKhzlglQykhService;
import org.cmms.modules.sjxf.qtxt.etcxx.bdxxb.entity.Bdxxb;
import org.cmms.modules.sjxf.qtxt.etcxx.bdxxb.service.IBdxxbService;
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
 * @Description: ETC绑定信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags="ETC绑定信息表")
@RestController
@RequestMapping("/bdxxb/bdxxb")
public class BdxxbController extends JeecgController<Bdxxb, IBdxxbService> {
	@Autowired
	private IBdxxbService bdxxbService;


	/**
	 * 分页列表查询
	 *
	 * @param bdxxb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "ETC绑定信息表-分页列表查询")
	@ApiOperation(value="ETC绑定信息表-分页列表查询", notes="ETC绑定信息表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Bdxxb bdxxb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Bdxxb> queryWrapper = QueryGenerator.initQueryWrapper(bdxxb, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IBdxxbService.class,bdxxbService,pageNo,pageSize,queryWrapper,"workdate","zjhm","xtkh" );
		return Result.ok(pageList);
	}


	 /**
	 * 添加
	 *
	 * @param bdxxb
	 * @return
	 */
	@AutoLog(value = "ETC绑定信息表-添加")
	@ApiOperation(value="ETC绑定信息表-添加", notes="ETC绑定信息表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Bdxxb bdxxb) {
		bdxxbService.save(bdxxb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param bdxxb
	 * @return
	 */
	@AutoLog(value = "ETC绑定信息表-编辑")
	@ApiOperation(value="ETC绑定信息表-编辑", notes="ETC绑定信息表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Bdxxb bdxxb) {
		bdxxbService.updateById(bdxxb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ETC绑定信息表-通过id删除")
	@ApiOperation(value="ETC绑定信息表-通过id删除", notes="ETC绑定信息表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		bdxxbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "ETC绑定信息表-批量删除")
	@ApiOperation(value="ETC绑定信息表-批量删除", notes="ETC绑定信息表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.bdxxbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ETC绑定信息表-通过id查询")
	@ApiOperation(value="ETC绑定信息表-通过id查询", notes="ETC绑定信息表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Bdxxb bdxxb = bdxxbService.getById(id);
		return Result.ok(bdxxb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param bdxxb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Bdxxb bdxxb) {
      return super.exportXls(request, bdxxb, Bdxxb.class, "ETC绑定信息表");
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
      return super.importExcel(request, response, Bdxxb.class);
  }

}
