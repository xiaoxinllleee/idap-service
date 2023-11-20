package org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.zh.zhkhjlzftjhz.controller;

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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.zh.zhkhjlzftjhz.entity.Zhkhjlzftjhz;
import org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.zh.zhkhjlzftjhz.service.IZhkhjlzftjhzService;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 支行客户经理走访统计汇总
 * @Author: jeecg-boot
 * @Date:   2022-05-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行客户经理走访统计汇总")
@RestController
@RequestMapping("/zftjhz/zhkhjlzftjhz")
public class ZhkhjlzftjhzController extends JeecgController<Zhkhjlzftjhz, IZhkhjlzftjhzService> {
	@Autowired
	private IZhkhjlzftjhzService zhkhjlzftjhzService;

	/**
	 * 分页列表查询
	 *
	 * @param zhkhjlzftjhz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行客户经理走访统计汇总-分页列表查询")
	@ApiOperation(value="支行客户经理走访统计汇总-分页列表查询", notes="支行客户经理走访统计汇总-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zhkhjlzftjhz zhkhjlzftjhz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Zhkhjlzftjhz> queryWrapper = QueryGenerator.initQueryWrapper(zhkhjlzftjhz, req.getParameterMap());
		queryWrapper.eq("khlx",zhkhjlzftjhz.getKhlx());
		Page<Zhkhjlzftjhz> page = new Page<Zhkhjlzftjhz>(pageNo, pageSize);
		IPage<Zhkhjlzftjhz> pageList = zhkhjlzftjhzService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param zhkhjlzftjhz
	 * @return
	 */
	@AutoLog(value = "支行客户经理走访统计汇总-添加")
	@ApiOperation(value="支行客户经理走访统计汇总-添加", notes="支行客户经理走访统计汇总-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zhkhjlzftjhz zhkhjlzftjhz) {
		zhkhjlzftjhzService.save(zhkhjlzftjhz);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param zhkhjlzftjhz
	 * @return
	 */
	@AutoLog(value = "支行客户经理走访统计汇总-编辑")
	@ApiOperation(value="支行客户经理走访统计汇总-编辑", notes="支行客户经理走访统计汇总-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zhkhjlzftjhz zhkhjlzftjhz) {
		zhkhjlzftjhzService.updateById(zhkhjlzftjhz);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行客户经理走访统计汇总-通过id删除")
	@ApiOperation(value="支行客户经理走访统计汇总-通过id删除", notes="支行客户经理走访统计汇总-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zhkhjlzftjhzService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "支行客户经理走访统计汇总-批量删除")
	@ApiOperation(value="支行客户经理走访统计汇总-批量删除", notes="支行客户经理走访统计汇总-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zhkhjlzftjhzService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行客户经理走访统计汇总-通过id查询")
	@ApiOperation(value="支行客户经理走访统计汇总-通过id查询", notes="支行客户经理走访统计汇总-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zhkhjlzftjhz zhzftjhz = zhkhjlzftjhzService.getById(id);
		return Result.ok(zhzftjhz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zhkhjlzftjhz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zhkhjlzftjhz zhkhjlzftjhz) {
      return super.exportXls(request, zhkhjlzftjhz, Zhkhjlzftjhz.class, "支行客户经理走访统计汇总");
  }
  @RequestMapping(value = "/exportXlszhzf")
  public ModelAndView exportXlszhzf(HttpServletRequest request,HttpServletResponse response, Zhkhjlzftjhz zhkhjlzftjhz) {
	  QueryWrapper<Zhkhjlzftjhz> queryWrapper = new QueryWrapper<>();
	  System.out.println(zhkhjlzftjhz);
	  try {
		  String paramsStr = request.getParameter("paramsStr");
		  if (oConvertUtils.isNotEmpty(paramsStr)){
			  String deString = URLDecoder.decode(paramsStr, "UTF-8");
			  zhkhjlzftjhz = JSON.parseObject(deString, Zhkhjlzftjhz.class);
			  queryWrapper =QueryGenerator.initQueryWrapper(zhkhjlzftjhz,request.getParameterMap());
		  }
	  } catch (UnsupportedEncodingException e) {
		  e.printStackTrace();
	  }
	  if (queryWrapper == null) {
		  queryWrapper = new QueryWrapper<>();
	  }
	  queryWrapper.eq("khlx",zhkhjlzftjhz.getKhlx());
	  if ("1".equals(zhkhjlzftjhz.getKhlx())){
		  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		  List<Zhkhjlzftjhz> list = service.list(queryWrapper);
		  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		  mv.addObject(NormalExcelConstants.FILE_NAME,"农户支行客户经理走访(汇总)列表");
		  mv.addObject(NormalExcelConstants.CLASS,Zhkhjlzftjhz.class);
		  mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("农户支行客户经理走访(汇总)列表","导出人"+sysUser.getRealname(),"导出信息"));
		  mv.addObject(NormalExcelConstants.DATA_LIST,list);
		  return mv;
	  }else {
		  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		  List<Zhkhjlzftjhz> list = service.list(queryWrapper);
		  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		  mv.addObject(NormalExcelConstants.FILE_NAME,"商户支行客户经理走访(汇总)列表");
		  mv.addObject(NormalExcelConstants.CLASS,Zhkhjlzftjhz.class);
		  mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("商户支行客户经理走访(汇总)列表","导出人"+sysUser.getRealname(),"导出信息"));
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
      return super.importExcel(request, response, Zhkhjlzftjhz.class);
  }

}
