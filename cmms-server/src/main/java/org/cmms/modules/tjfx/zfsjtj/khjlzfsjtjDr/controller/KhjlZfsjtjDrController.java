package org.cmms.modules.tjfx.zfsjtj.khjlzfsjtjDr.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import org.apache.commons.jexl2.internal.AbstractExecutor;
import org.apache.shiro.SecurityUtils;
import org.checkerframework.checker.units.qual.K;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.DateUtils;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.zfsjtj.khjlzfsjtjDr.entity.KhjlZfsjtjDr;
import org.cmms.modules.tjfx.zfsjtj.khjlzfsjtjDr.entity.KhjlZfsjtjDrVo;
import org.cmms.modules.tjfx.zfsjtj.khjlzfsjtjDr.service.IKhjlZfsjtjDrService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.zfsjtj.qhhfsjmx.entity.Hfsjmx;
import org.cmms.modules.tjfx.zfsjtj.qhhfsjmx.service.IHfsjmxService;
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
 * @Description: 客户经理走访统计-当日
 * @Author: jeecg-boot
 * @Date:   2023-05-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户经理走访统计-当日")
@RestController
@RequestMapping("/khjlzfsjtjDr/khjlZfsjtjDr")
public class KhjlZfsjtjDrController extends JeecgController<KhjlZfsjtjDr, IKhjlZfsjtjDrService> {
	@Autowired
	private IKhjlZfsjtjDrService khjlZfsjtjDrService;
	 @Autowired
	 private IHfsjmxService hfsjmxService;
	
	/**
	 * 分页列表查询
	 *
	 * @param khjlZfsjtjDr
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户经理走访统计-当日-分页列表查询")
	@ApiOperation(value="客户经理走访统计-当日-分页列表查询", notes="客户经理走访统计-当日-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KhjlZfsjtjDr khjlZfsjtjDr,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<KhjlZfsjtjDr> queryWrapper = QueryGenerator.initQueryWrapper(khjlZfsjtjDr, req.getParameterMap());
		Page<KhjlZfsjtjDr> page = new Page<KhjlZfsjtjDr>(pageNo, pageSize);
		IPage<KhjlZfsjtjDr> pageList = khjlZfsjtjDrService.page(page, queryWrapper);
		return Result.ok(pageList);
	}


	 /**
	  * 分页列表查询
	  * @return
	  */
	 @AutoLog(value = "客户经理走访统计-当日-分页列表查询")
	 @ApiOperation(value="客户经理走访统计-当日-分页列表查询", notes="客户经理走访统计-当日-分页列表查询")
	 @GetMapping(value = "/getkhjlzf")
	 public Result<?> getkhjlzf() {
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 QueryWrapper<KhjlZfsjtjDr> queryWrapper = new QueryWrapper<>();
		 queryWrapper.and((wrapper)->{
			 wrapper.eq("yggh",loginUser.getWorkNo()).or().eq("ygxm","全行");
		 });
		 queryWrapper.eq("tjrq", org.cmms.common.util.DateUtil.getDayBegin(new Date()));
//		 List<KhjlZfsjtjDr> list = new ArrayList<>();
//         khjlZfsjtjDrService.initKhjlZf(loginUser.getWorkNo());
		 List<KhjlZfsjtjDr> list = khjlZfsjtjDrService.list(queryWrapper);
		 return Result.ok(list);
	 }

	 /**
	  * 祁阳-每次打开首页时提取最新走访数据
	  * @return
	  */
	 @PostMapping("/initKhjlZf")
	 public Result<?> initKhjlZf(){
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 khjlZfsjtjDrService.initKhjlZf(loginUser.getWorkNo());
		 return Result.ok();
	 }

	 /**
	  * 祁阳
	  * @return
	  */
	 @AutoLog(value = "客户经理走访统计-当日-分页列表查询")
	 @ApiOperation(value="客户经理走访统计-当日-分页列表查询", notes="客户经理走访统计-当日-分页列表查询")
	 @GetMapping(value = "/getkhjlzfOther")
	 public Result<?> getkhjlzfOther() {
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 KhjlZfsjtjDrVo khjlZfsjtjDrVo= khjlZfsjtjDrService.getYxzfHj(loginUser.getWorkNo());
		 //无效走访数据汇总（商户+农户）
		 khjlZfsjtjDrVo.setWxzfs(khjlZfsjtjDrService.getWxzfxx(loginUser.getWorkNo()));
		 return Result.ok(khjlZfsjtjDrVo);
	 }

	 @GetMapping("/getBthzfxx")
	 public Result<?> getBthzfxx(){
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 KhjlZfsjtjDrVo khjlZfsjtjDrVo=khjlZfsjtjDrService.getBthzfxx(loginUser.getOrgCode());
		 return Result.ok(khjlZfsjtjDrVo);
	 }

	 /**
	 * 添加
	 *
	 * @param khjlZfsjtjDr
	 * @return
	 */
	@AutoLog(value = "客户经理走访统计-当日-添加")
	@ApiOperation(value="客户经理走访统计-当日-添加", notes="客户经理走访统计-当日-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody KhjlZfsjtjDr khjlZfsjtjDr) {
		khjlZfsjtjDrService.save(khjlZfsjtjDr);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param khjlZfsjtjDr
	 * @return
	 */
	@AutoLog(value = "客户经理走访统计-当日-编辑")
	@ApiOperation(value="客户经理走访统计-当日-编辑", notes="客户经理走访统计-当日-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhjlZfsjtjDr khjlZfsjtjDr) {
		khjlZfsjtjDrService.updateById(khjlZfsjtjDr);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理走访统计-当日-通过id删除")
	@ApiOperation(value="客户经理走访统计-当日-通过id删除", notes="客户经理走访统计-当日-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khjlZfsjtjDrService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户经理走访统计-当日-批量删除")
	@ApiOperation(value="客户经理走访统计-当日-批量删除", notes="客户经理走访统计-当日-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khjlZfsjtjDrService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理走访统计-当日-通过id查询")
	@ApiOperation(value="客户经理走访统计-当日-通过id查询", notes="客户经理走访统计-当日-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		KhjlZfsjtjDr khjlZfsjtjDr = khjlZfsjtjDrService.getById(id);
		return Result.ok(khjlZfsjtjDr);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khjlZfsjtjDr
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, KhjlZfsjtjDr khjlZfsjtjDr) {
      return super.exportXls(request, khjlZfsjtjDr, KhjlZfsjtjDr.class, "客户经理走访统计-当日");
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
      return super.importExcel(request, response, KhjlZfsjtjDr.class);
  }

}
