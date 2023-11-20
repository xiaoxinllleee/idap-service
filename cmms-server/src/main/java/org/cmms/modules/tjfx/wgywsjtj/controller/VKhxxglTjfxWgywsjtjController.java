package org.cmms.modules.tjfx.wgywsjtj.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.sjxf.hxxt.jjk.kjbxx.entity.Kjbxx;
import org.cmms.modules.tjfx.wgtjfx.bkbpymx.entity.Bkbpymx;
import org.cmms.modules.tjfx.wgywsjtj.entity.VKhxxglTjfxWgywsjtj;
import org.cmms.modules.tjfx.wgywsjtj.service.IVKhxxglTjfxWgywsjtjService;
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
 * @Description: 网格业务数据统计
 * @Author: jeecg-boot
 * @Date:   2022-06-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="网格业务数据统计")
@RestController
@RequestMapping("/tjfx/vKhxxglTjfxWgywsjtj")
public class VKhxxglTjfxWgywsjtjController extends JeecgController<VKhxxglTjfxWgywsjtj, IVKhxxglTjfxWgywsjtjService> {

	/**
	 * 分页列表查询
	 *
	 * @param vKhxxglTjfxWgywsjtj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "网格业务数据统计-分页列表查询")
	@ApiOperation(value="网格业务数据统计-分页列表查询", notes="网格业务数据统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VKhxxglTjfxWgywsjtj vKhxxglTjfxWgywsjtj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String wgbh=vKhxxglTjfxWgywsjtj.getWgbh();
		vKhxxglTjfxWgywsjtj.setWgbh(null);
		QueryWrapper<VKhxxglTjfxWgywsjtj> queryWrapper = QueryGenerator.initQueryWrapper(vKhxxglTjfxWgywsjtj, req.getParameterMap());
		if (StringUtils.isNotBlank(wgbh)) {
			queryWrapper.inSql("wgbh", "select menu_id from YXDYGL_PQQXGL where khjl = '" + getLoginUser().getWorkNo() + "' and  " +
					"menu_id in (" +
					"select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id )");
		} else {
			queryWrapper.inSql("wgbh", "select menu_id from YXDYGL_PQQXGL where khjl = '" + getLoginUser().getWorkNo() + "'");
		}
		queryWrapper.orderByAsc("wgxz");
		Page<VKhxxglTjfxWgywsjtj> page = new Page<VKhxxglTjfxWgywsjtj>(pageNo, pageSize);
		IPage<VKhxxglTjfxWgywsjtj> pageList = service.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 @RequestMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, VKhxxglTjfxWgywsjtj vKhxxglTjfxWgywsjtj) {
		 String wgbh = "";
		 if (StringUtils.isNotBlank(vKhxxglTjfxWgywsjtj.getWgbh())){
			 wgbh =vKhxxglTjfxWgywsjtj.getWgbh();
			 vKhxxglTjfxWgywsjtj.setWgbh(null);
		 }
		 QueryWrapper<VKhxxglTjfxWgywsjtj> queryWrapper = QueryGenerator.initQueryWrapper(vKhxxglTjfxWgywsjtj, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String selections = request.getParameter("selections");
		 String rowKey = request.getParameter("rowKey");

		 //20211201 过滤选中数据
		 //20211201 BY LIUXIANGQUN 过滤选择的数据改为执行接sql  in  查询
		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 if(oConvertUtils.isNotEmpty(rowKey)){
				 queryWrapper.in(rowKey,selectionList);
			 }else{
				 queryWrapper.in("ID",selectionList);
			 }
		 }
		 if (StringUtils.isNotBlank(wgbh)) {
			 queryWrapper.inSql("wgbh", "select menu_id from YXDYGL_PQQXGL where khjl = '" + getLoginUser().getWorkNo() + "' and  " +
					 "menu_id in (" +
					 "select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id )");

		 } else {
			 queryWrapper.inSql("wgbh", "select menu_id from YXDYGL_PQQXGL where khjl = '" + getLoginUser().getWorkNo() + "'");
		 }
		 List<VKhxxglTjfxWgywsjtj> exportList = service.list(queryWrapper);


		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, "网格业务数据统计"); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, VKhxxglTjfxWgywsjtj.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("网格业务数据统计" + "报表", "导出人:" + sysUser.getRealname(), "网格业务数据统计"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
	 }


	 @RequestMapping("/getKjbxxListByWgbh")
	 public Result<?> getKjbxxListByWgbh(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
										 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
										 String wgbh){
		Page<Kjbxx> page = new Page<Kjbxx>(pageNo, pageSize);
		IPage<Kjbxx> kjbxxListByWgbh = service.getKjbxxListByWgbh(page,wgbh);
		return Result.ok(kjbxxListByWgbh);
	 }
}
