package org.cmms.modules.wggl.wgzbxx.controller;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.poi.ss.formula.functions.T;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;

import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.wgtjfx.wghztj.entity.Wghztj;
import org.cmms.modules.wggl.wgzbxx.entity.Wgzbxx;
import org.cmms.modules.wggl.wgzbxx.service.IWgzbxxService;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.service.IYxdyglMainService;
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
 * @Description: 网格坐标信息
 * @Author: jeecg-boot
 * @Date:   2021-11-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="网格坐标信息")
@RestController
@RequestMapping("/wghgl/wgzbxx")
public class WgzbxxController extends JeecgController<Wgzbxx, IWgzbxxService> {
	@Autowired
	private IWgzbxxService wgzbxxService;
	@Autowired
	private IYxdyglMainService yxdyglMainService;
	/**
	 * 分页列表查询
	 *
	 * @param wgzbxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "网格坐标信息-分页列表查询")
	@ApiOperation(value="网格坐标信息-分页列表查询", notes="网格坐标信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Wgzbxx wgzbxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Wgzbxx> queryWrapper = QueryGenerator.initQueryWrapper(wgzbxx, req.getParameterMap());
		Page<Wgzbxx> page = new Page<Wgzbxx>(pageNo, pageSize);
		IPage<Wgzbxx> pageList = wgzbxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param wgzbxx
	 * @return
	 */
	@AutoLog(value = "网格坐标信息-添加")
	@ApiOperation(value="网格坐标信息-添加", notes="网格坐标信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Wgzbxx wgzbxx) {
		QueryWrapper<Wgzbxx> wgzbxxQueryWrapper = new QueryWrapper<>();
		wgzbxxQueryWrapper.eq("wgbh", wgzbxx.getWgbh());
		List<Wgzbxx> wgzbxxList = wgzbxxService.list(wgzbxxQueryWrapper);
		if(!wgzbxxList.isEmpty()) {
			return Result.error("已经存在此网格编号的坐标信息");
		}
		wgzbxxService.save(wgzbxx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param wgzbxx
	 * @return
	 */
	@AutoLog(value = "网格坐标信息-编辑")
	@ApiOperation(value="网格坐标信息-编辑", notes="网格坐标信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Wgzbxx wgzbxx) {
		wgzbxxService.updateById(wgzbxx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "网格坐标信息-通过id删除")
	@ApiOperation(value="网格坐标信息-通过id删除", notes="网格坐标信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		wgzbxxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "网格坐标信息-批量删除")
	@ApiOperation(value="网格坐标信息-批量删除", notes="网格坐标信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.wgzbxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "网格坐标信息-通过id查询")
	@ApiOperation(value="网格坐标信息-通过id查询", notes="网格坐标信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Wgzbxx wgzbxx = wgzbxxService.getById(id);
		return Result.ok(wgzbxx);
	}

	 @GetMapping(value = "/queryByWgbh")
	 public Result<?> queryByWgbh(@RequestParam(name="wgbh",required=true) String wgbh) {
		 QueryWrapper<Wgzbxx> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("wgbh", wgbh);
		 Wgzbxx wgzbxx = wgzbxxService.getOne(queryWrapper);
		 return Result.ok(wgzbxx);
	 }
	 /**
	  * 通过id查询
	  *
	  * @param wglx
	  * @return
	  */
	 @AutoLog(value = "网格坐标信息-通过wglx查询")
	 @ApiOperation(value="网格坐标信息-通过wglx查询", notes="网格坐标信息-通过wglx查询")
	 @GetMapping(value = "/queryByWglx")
	 public Result<?> queryByWglx(@RequestParam(name="wglx",required=true) String wglx,
								  @RequestParam(name="sjwgbh", required = false) String sjwgbh) {
		 QueryWrapper<Wgzbxx> queryWrapper = new QueryWrapper<>();
		 List<String> wgbhList = new ArrayList<>();
		 if(StringUtils.isNotEmpty(sjwgbh)) {
	 	 	//获取网格所有下级
			 QueryWrapper<YxdyglMain> yxdyglMainQueryWrapper = new QueryWrapper<>();
			 yxdyglMainQueryWrapper.eq("parent_id", sjwgbh);
			 List<YxdyglMain> list = yxdyglMainService.list(yxdyglMainQueryWrapper);
			 wgbhList = list.stream().map(YxdyglMain::getWgbh).collect(Collectors.toList());
			 queryWrapper.in("wgbh", wgbhList);
		 }
	 	 queryWrapper.eq("wglx", wglx);
		 List<Wgzbxx> wgzbxxList = wgzbxxService.list(queryWrapper);
		 return Result.ok(wgzbxxList);
	 }

  /**
   * 导出excel
   *
   * @param request
   * @param wgzbxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Wgzbxx wgzbxx) {
      return super.exportXls(request, wgzbxx, Wgzbxx.class, "网格坐标信息");
  }

	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 return super.exportTemplateXls(Wgzbxx.class, "网格坐标信息导入模板");
	 }

	 /**
	  * 通过excel导入数据
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 return super.importExcelByTemplate(jsonObject, request, response, Wgzbxx.class, null);
	 }

	 @RequestMapping("getZhenByWgbj")
	 public Result<?> getZhenByWgbj (String wgbh,String level,String wgxz,Integer currentPage,Integer pageSize){
	 	JSONObject jsonObject=new JSONObject();
	 	 if("1".equals(wgxz)){
			 QueryWrapper<Wgzbxx> wgzbxxQueryWrapper=new QueryWrapper<>();
			 wgzbxxQueryWrapper.inSql("wgbh","select  wgbh from yxdygl_main where parent_id= "+wgbh);
			 wgzbxxQueryWrapper.orderByAsc("wgbh");
			 Page<Wgzbxx> page = new Page<Wgzbxx>(currentPage, pageSize);
			 Page<Wgzbxx> page1 = wgzbxxService.page(page, wgzbxxQueryWrapper);
			 jsonObject.put("cunPage",page1);
			 LambdaQueryWrapper<Wgzbxx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
			 lambdaQueryWrapper.eq(Wgzbxx::getWgbh,wgbh);
			 List<Wgzbxx> list = service.list(lambdaQueryWrapper);
			 if (CollUtil.isNotEmpty(list)){
				 jsonObject.put("town",list.get(0));
			 }
		 }else if("2".equals(wgxz)){
	 	 	 QueryWrapper<YxdyglMain>  queryWrapper=new QueryWrapper<>();
			 queryWrapper.eq("wgbh",wgbh);
			 QueryWrapper<Wgzbxx> wgzbxxQueryWrapper=new QueryWrapper<>();
			 wgzbxxQueryWrapper.eq("wgbh",wgbh);
			 wgzbxxQueryWrapper.orderByAsc("wgbh");
			 Page<Wgzbxx> page = new Page<Wgzbxx>(currentPage, pageSize);
			 Page<Wgzbxx> page1 = wgzbxxService.page(page, wgzbxxQueryWrapper);
			 jsonObject.put("cunPage",page1);
			 List<YxdyglMain> yxdyglMainList = yxdyglMainService.list(queryWrapper);
			 if(yxdyglMainList!=null&&yxdyglMainList.size()>0){
				 LambdaQueryWrapper<Wgzbxx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
				 lambdaQueryWrapper.eq(Wgzbxx::getWgbh,yxdyglMainList.get(0).getParentId());
				 List<Wgzbxx> list = service.list(lambdaQueryWrapper);
				 if (CollUtil.isNotEmpty(list)){
					 jsonObject.put("town",list.get(0));
				 }
			 }
		 }else{
//			 return Result.error("请选择村或者组");
			 String zhenByNoRoot = yxdyglMainService.getZhenByNoRoot(wgbh, level);
			 if (StringUtils.isNotEmpty(zhenByNoRoot)) {
				 LambdaQueryWrapper<Wgzbxx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
				 lambdaQueryWrapper.eq(Wgzbxx::getWgbh,zhenByNoRoot);
				 List<Wgzbxx> list = service.list(lambdaQueryWrapper);
				 if (CollUtil.isNotEmpty(list))
					 return Result.ok(list.get(0));
			 }
		 }
		 return Result.ok(jsonObject);
	 }
}
