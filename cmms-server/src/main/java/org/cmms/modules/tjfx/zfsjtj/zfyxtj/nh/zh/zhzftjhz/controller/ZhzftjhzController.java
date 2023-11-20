package org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.zh.zhzftjhz.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.zh.zhzftj.entity.Zhzftj;
import org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.zh.zhzftjhz.entity.Zhzftjhz;
import org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.zh.zhzftjhz.service.IZhzftjhzService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 支行走访统计汇总
 * @Author: jeecg-boot
 * @Date:   2022-05-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行走访统计汇总")
@RestController
@RequestMapping("/zhzftjhz/zhzftjhz")
public class ZhzftjhzController extends JeecgController<Zhzftjhz, IZhzftjhzService> {
	@Autowired
	private IZhzftjhzService zhzftjhzService;

	/**
	 * 分页列表查询
	 *
	 * @param zhzftjhz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行走访统计汇总-分页列表查询")
	@ApiOperation(value="支行走访统计汇总-分页列表查询", notes="支行走访统计汇总-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zhzftjhz zhzftjhz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Zhzftjhz> queryWrapper = QueryGenerator.initQueryWrapper(zhzftjhz, req.getParameterMap());
		System.out.println(zhzftjhz);
		queryWrapper.eq("khlx",zhzftjhz.getKhlx());
		queryWrapper.orderByDesc("ljyxzfs");
		Page<Zhzftjhz> page = new Page<Zhzftjhz>(pageNo, pageSize);
		IPage<Zhzftjhz> pageList = zhzftjhzService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param zhzftjhz
	 * @return
	 */
	@AutoLog(value = "支行走访统计汇总-添加")
	@ApiOperation(value="支行走访统计汇总-添加", notes="支行走访统计汇总-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zhzftjhz zhzftjhz) {
		zhzftjhzService.save(zhzftjhz);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param zhzftjhz
	 * @return
	 */
	@AutoLog(value = "支行走访统计汇总-编辑")
	@ApiOperation(value="支行走访统计汇总-编辑", notes="支行走访统计汇总-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zhzftjhz zhzftjhz) {
		zhzftjhzService.updateById(zhzftjhz);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行走访统计汇总-通过id删除")
	@ApiOperation(value="支行走访统计汇总-通过id删除", notes="支行走访统计汇总-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zhzftjhzService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "支行走访统计汇总-批量删除")
	@ApiOperation(value="支行走访统计汇总-批量删除", notes="支行走访统计汇总-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zhzftjhzService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行走访统计汇总-通过id查询")
	@ApiOperation(value="支行走访统计汇总-通过id查询", notes="支行走访统计汇总-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zhzftjhz zhzftjhz = zhzftjhzService.getById(id);
		return Result.ok(zhzftjhz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zhzftjhz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zhzftjhz zhzftjhz) {
      return super.exportXls(request, zhzftjhz, Zhzftjhz.class, "支行走访统计汇总");
  }
  @RequestMapping(value = "/exportXlszhzf")
  public ModelAndView exportXlszhzf(HttpServletRequest request,HttpServletResponse response, Zhzftjhz zhzftjhz) {
	  QueryWrapper<Zhzftjhz> queryWrapper = new QueryWrapper<>();
	  System.out.println(zhzftjhz);
	  try {
		  String paramsStr = request.getParameter("paramsStr");
		  if (oConvertUtils.isNotEmpty(paramsStr)){
			  String deString = URLDecoder.decode(paramsStr, "UTF-8");
			  zhzftjhz = JSON.parseObject(deString, Zhzftjhz.class);
			  queryWrapper =QueryGenerator.initQueryWrapper(zhzftjhz,request.getParameterMap());
		  }
	  } catch (UnsupportedEncodingException e) {
		  e.printStackTrace();
	  }
	  queryWrapper.eq("khlx",zhzftjhz.getKhlx());
	  if ("1".equals(zhzftjhz.getKhlx())){
		  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		  List<Zhzftjhz> list = service.list(queryWrapper);
		  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		  mv.addObject(NormalExcelConstants.FILE_NAME,"农户支行走访(汇总)列表");
		  mv.addObject(NormalExcelConstants.CLASS,Zhzftjhz.class);
		  mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("农户支行走访(汇总)列表","导出人"+sysUser.getRealname(),"导出信息"));
		  mv.addObject(NormalExcelConstants.DATA_LIST,list);
		  return mv;
	  }else {
		  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		  List<Zhzftjhz> list = service.list(queryWrapper);
		  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		  mv.addObject(NormalExcelConstants.FILE_NAME,"商户支行走访(汇总)列表");
		  mv.addObject(NormalExcelConstants.CLASS,Zhzftjhz.class);
		  mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("商户支行走访(汇总)列表","导出人"+sysUser.getRealname(),"导出信息"));
		  mv.addObject(NormalExcelConstants.DATA_LIST,list);
		  return mv;
	  }

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
      return super.importExcel(request, response, Zhzftjhz.class);
  }

}
