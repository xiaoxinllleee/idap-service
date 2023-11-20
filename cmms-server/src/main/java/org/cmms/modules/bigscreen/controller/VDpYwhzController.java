package org.cmms.modules.bigscreen.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.bigscreen.entity.VDpYwhz;
import org.cmms.modules.bigscreen.service.IVDpYwhzService;
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
 * @Description: 大屏业务汇总视图
 * @Author: jeecg-boot
 * @Date:   2023-10-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="大屏业务汇总视图")
@RestController
@RequestMapping("/bigscreen/vDpYwhz")
public class VDpYwhzController extends JeecgController<VDpYwhz, IVDpYwhzService> {
	@Autowired
	private IVDpYwhzService vDpYwhzService;
	
	/**
	 * 分页列表查询
	 *
	 * @param vDpYwhz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "大屏业务汇总视图-分页列表查询")
	@ApiOperation(value="大屏业务汇总视图-分页列表查询", notes="大屏业务汇总视图-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VDpYwhz vDpYwhz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VDpYwhz> queryWrapper = QueryGenerator.initQueryWrapper(vDpYwhz, req.getParameterMap());
		Page<VDpYwhz> page = new Page<VDpYwhz>(pageNo, pageSize);
		IPage<VDpYwhz> pageList = vDpYwhzService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param vDpYwhz
	 * @return
	 */
	@AutoLog(value = "大屏业务汇总视图-添加")
	@ApiOperation(value="大屏业务汇总视图-添加", notes="大屏业务汇总视图-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody VDpYwhz vDpYwhz) {
		vDpYwhzService.save(vDpYwhz);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param vDpYwhz
	 * @return
	 */
	@AutoLog(value = "大屏业务汇总视图-编辑")
	@ApiOperation(value="大屏业务汇总视图-编辑", notes="大屏业务汇总视图-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody VDpYwhz vDpYwhz) {
		vDpYwhzService.updateById(vDpYwhz);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "大屏业务汇总视图-通过id删除")
	@ApiOperation(value="大屏业务汇总视图-通过id删除", notes="大屏业务汇总视图-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		vDpYwhzService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "大屏业务汇总视图-批量删除")
	@ApiOperation(value="大屏业务汇总视图-批量删除", notes="大屏业务汇总视图-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.vDpYwhzService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "大屏业务汇总视图-通过id查询")
	@ApiOperation(value="大屏业务汇总视图-通过id查询", notes="大屏业务汇总视图-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VDpYwhz vDpYwhz = vDpYwhzService.getById(id);
		return Result.ok(vDpYwhz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param vDpYwhz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, VDpYwhz vDpYwhz) {
      return super.exportXls(request, vDpYwhz, VDpYwhz.class, "大屏业务汇总视图");
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
      return super.importExcel(request, response, VDpYwhz.class);
  }


  @RequestMapping("/index")
  public Result<?> index(){
	  JSONObject jsonObject = new JSONObject();

	  LambdaQueryWrapper<VDpYwhz> lambdaQueryWrapper = new LambdaQueryWrapper<>();
	  /*lambdaQueryWrapper.eq(VDpYwhz::getYwyjfl,"1");
	  List<VDpYwhz> list = service.list(lambdaQueryWrapper);
	  List<VDpYwhz> list2 = new ArrayList<>();
	  List<VDpYwhz> list3 = new ArrayList<>();

	  for (int i = 0; i < list.size(); i++) {
		  VDpYwhz vDpYwhz = list.get(i);

		  if (StringUtils.isNotBlank(vDpYwhz.getYwejfl())){
			  if ("wjfl".equals(vDpYwhz.getYwejfl())){
				  list2.add(vDpYwhz);
				  continue;
			  }

			  if ("dkkhfl".equals(vDpYwhz.getYwejfl())){
				  list3.add(vDpYwhz);
				  continue;
			  }
		  }

		  if (vDpYwhz.getYwid().equals("1")){
			  if (vDpYwhz.getSjrq() != null){
				  String yyyymmdd = DateUtil.format(vDpYwhz.getSjrq(), "yyyy年MM月dd日");
				  jsonObject.set("dksjrq",yyyymmdd);
				  //金额进行处理 带小数点最多6位
			  }

			  if (StringUtils.isNotBlank(vDpYwhz.getYwjg())){
				  String ywjg = vDpYwhz.getYwjg();
				  if (ywjg.length() > 6){
					  String substring = ywjg.substring(0, 6);
					  char[] charArray = substring.toCharArray();
					  jsonObject.set("dkjesz",charArray);
				  }
			  }
		  }
		  else if (vDpYwhz.getYwid().equals("2"))
		  {
			  if (StringUtils.isNotBlank(vDpYwhz.getYwjg())){
				  String ywjg = vDpYwhz.getYwjg();
				  if (ywjg.length() > 6){
					  String substring = ywjg.substring(0, 5);
					  char[] charArray = substring.toCharArray();
					  jsonObject.set("dkkh",charArray);
				  }
			  }
		  }
		  else if (vDpYwhz.getYwid().equals("3"))
		  {
			  if (StringUtils.isNotBlank(vDpYwhz.getYwjg())){
				  jsonObject.set("dkjejsy",vDpYwhz.getYwjg());
			  }
		  }
		  else if (vDpYwhz.getYwid().equals("4"))
		  {
			  if (StringUtils.isNotBlank(vDpYwhz.getYwjg())){
				  jsonObject.set("dkkhjsy",vDpYwhz.getYwjg());
			  }
		  }

		  else if (vDpYwhz.getYwid().equals("5"))
		  {
			  if (StringUtils.isNotBlank(vDpYwhz.getYwjg())){
				  jsonObject.set("dkjejnc",vDpYwhz.getYwjg());
			  }
		  }

		  else if (vDpYwhz.getYwid().equals("6"))
		  {
			  if (StringUtils.isNotBlank(vDpYwhz.getYwjg())){
				  jsonObject.set("dkkhjnc",vDpYwhz.getYwjg());
			  }
		  }

	  }

	  jsonObject.set("wjfl",list2);
	  jsonObject.set("dkkhfl",list3);*/

	  List<VDpYwhz> list = service.list();
	  //
	  Map<String, VDpYwhz> collect = list.stream().collect(Collectors.toMap(VDpYwhz::getYwid, p -> p));


	  return Result.ok(jsonObject);
  }


	 @RequestMapping("/wgsj")
	 public Result<?> wgsj(){


	  return Result.ok();
	 }





}
