package org.cmms.modules.xdgl.dksp.dkspkhzl.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
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
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.xdgl.dksp.dkspkhzl.entity.DkspKhzl;
import org.cmms.modules.xdgl.dksp.dkspkhzl.service.IDkspKhzlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 贷款审批客户资料
 * @Author: jeecg-boot
 * @Date:   2021-12-01
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款审批客户资料")
@RestController
@RequestMapping("/dksp/khzl")
public class DkspKhzlController extends JeecgController<DkspKhzl, IDkspKhzlService> {
	@Autowired
	private IDkspKhzlService dkspKhzlService;
	 @Autowired
	 private ISysDictService sysDictService;
	/**
	 * 分页列表查询
	 *
	 * @param dkspKhzl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款审批客户资料-分页列表查询")
	@ApiOperation(value="贷款审批客户资料-分页列表查询", notes="贷款审批客户资料-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DkspKhzl dkspKhzl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DkspKhzl> queryWrapper = QueryGenerator.initQueryWrapper(dkspKhzl, req.getParameterMap());
		Page<DkspKhzl> page = new Page<DkspKhzl>(pageNo, pageSize);
		IPage<DkspKhzl> pageList = dkspKhzlService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dkspKhzl
	 * @return
	 */
	@AutoLog(value = "贷款审批客户资料-添加")
	@ApiOperation(value="贷款审批客户资料-添加", notes="贷款审批客户资料-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DkspKhzl dkspKhzl) {
		dkspKhzlService.save(dkspKhzl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dkspKhzl
	 * @return
	 */
	@AutoLog(value = "贷款审批客户资料-编辑")
	@ApiOperation(value="贷款审批客户资料-编辑", notes="贷款审批客户资料-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DkspKhzl dkspKhzl) {
		dkspKhzlService.updateById(dkspKhzl);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款审批客户资料-通过id删除")
	@ApiOperation(value="贷款审批客户资料-通过id删除", notes="贷款审批客户资料-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkspKhzlService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款审批客户资料-批量删除")
	@ApiOperation(value="贷款审批客户资料-批量删除", notes="贷款审批客户资料-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkspKhzlService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款审批客户资料-通过id查询")
	@ApiOperation(value="贷款审批客户资料-通过id查询", notes="贷款审批客户资料-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DkspKhzl dkspKhzl = dkspKhzlService.getById(id);
		return Result.ok(dkspKhzl);
	}

	 /**
	  * 通过id查询
	  *
	  * @param khid
	  * @return
	  */
	 @ApiOperation(value="贷款审批客户资料-通过khid查询", notes="贷款审批客户资料-通过khid查询")
	 @GetMapping(value = "/queryByKhid")
	 public Result<?> queryByKhid(@RequestParam(name="khid",required=true) String khid) {
	 	 QueryWrapper<DkspKhzl> queryWrapper = new QueryWrapper<>();
	 	 queryWrapper.eq("khid", khid);
	 	 queryWrapper.orderByAsc("zllx");
		 List<DkspKhzl> dkspKhzlList = dkspKhzlService.list(queryWrapper);
		 return Result.ok(dkspKhzlList);
	 }

  /**
   * 导出excel
   *
   * @param request
   * @param dkspKhzl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DkspKhzl dkspKhzl) {
      return super.exportXls(request, dkspKhzl, DkspKhzl.class, "贷款审批客户资料");
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
      return super.importExcel(request, response, DkspKhzl.class);
  }

}
