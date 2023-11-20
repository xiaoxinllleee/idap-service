package org.cmms.modules.jx.ckyj.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.jx.ckyj.entity.AppCkkhWatch;
import org.cmms.modules.jx.ckyj.entity.TbTjfxCkyjxx;
import org.cmms.modules.jx.ckyj.entity.TbTjfxCkyjxxVo;
import org.cmms.modules.jx.ckyj.mapper.AppCkkhWatchMapper;
import org.cmms.modules.jx.ckyj.service.AppCkkhWatchService;
import org.cmms.modules.jx.ckyj.service.ITbTjfxCkyjxxService;
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
 * @Description: 存款预警信息表
 * @Author: jeecg-boot
 * @Date:   2021-06-01
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存款预警信息表")
@RestController
@RequestMapping("/mobile/tbTjfxCkyjxxBankPmRest")
public class TbTjfxCkyjxxController extends JeecgController<TbTjfxCkyjxx, ITbTjfxCkyjxxService> {
	@Autowired
	private ITbTjfxCkyjxxService tbTjfxCkyjxxService;

	@Autowired
	private AppCkkhWatchService appCkkhWatchService;
	/**
	 * 分页列表查
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@AutoLog(value = "存款预警信息表-分页列表查询")
	@ApiOperation(value="存款预警信息表-分页列表查询", notes="存款预警信息表-分页列表查询")
	@GetMapping(value = "/page")
	public Result<?> queryPageList(	@RequestParam(value="yggh",required=false) String yggh,
									   @RequestParam(value="pageNo",required=false,defaultValue="1") int pageNo,
										   @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize) {
		if (StringUtils.isBlank(yggh)){
			LoginUser loginUser = getLoginUser();
			String id = loginUser.getWorkNo();
			yggh = id;
		}
		Page<TbTjfxCkyjxxVo> page=new Page<>(pageNo,pageSize);
		IPage<TbTjfxCkyjxxVo> list = tbTjfxCkyjxxService.queryListByYggh(page, yggh);

		return Result.ok(list);
	}
	 @AutoLog(value = "关注客户列表-分页查询")
	 @ApiOperation(value = "关注客户列表", notes = "关注客户列表")
	 @RequestMapping(value="/watchList",method=RequestMethod.GET)
	 public Result<?> watchList(
			 @RequestParam(value="yggh",required=true) String yggh,
			 @RequestParam(value="khbh",required=false) String khbh,
			 @RequestParam(value="pageNo",required=false,defaultValue="1") int pageNo,
			 @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize
	 ){
		 if (StringUtils.isBlank(yggh)){
			 LoginUser loginUser = getLoginUser();
			 String id = loginUser.getWorkNo();
			 yggh = id;
		 }
		Page<TbTjfxCkyjxxVo> page=new Page<>();
	IPage<TbTjfxCkyjxxVo> list=tbTjfxCkyjxxService.getWatchList(page,yggh,khbh);
		 return Result.ok(list);
	 }
	 @AutoLog(value = "关注客户")
	 @ApiOperation(value = "关注客户", notes = "关注客户")
	 @RequestMapping(value="/watch",method=RequestMethod.POST)
	 public Result<?> watch(@RequestParam(value="yggh",required=true) String yggh,
							 @RequestParam(value="khbh",required=true) String khbh){
		 if (StringUtils.isBlank(yggh)){
			 LoginUser loginUser = getLoginUser();
			 String id = loginUser.getWorkNo();
			 yggh = id;
		 }
		 AppCkkhWatch appCkkhWatch=new AppCkkhWatch();
		 appCkkhWatch.setYggh(yggh).setKhbh(khbh);
		 	QueryWrapper queryWrapper=new QueryWrapper();
		 	queryWrapper.eq("yggh",yggh);
		 	queryWrapper.eq("khbh",khbh);
		 List<AppCkkhWatch> list = appCkkhWatchService.list(queryWrapper);
		 if (list.size()>0)
		 		return Result.error(200,"操作失败,客户已关注");

	 		if (!appCkkhWatchService.save(appCkkhWatch))
				return Result.error("系统繁忙请稍后再试");

			return Result.ok();
	 }
	 @AutoLog(value = "取消关注客户")
	 @ApiOperation(value = "取消关注客户", notes = "取消关注客户")
	 @RequestMapping(value="/unwatch",method=RequestMethod.POST)
	 public Result<Object> unwatch(@RequestParam(value="yggh",required=true) String yggh,
									 @RequestParam(value="khbh",required=true) String khbh
	 ){
		 if (StringUtils.isBlank(yggh)){
			 LoginUser loginUser = getLoginUser();
			 String id = loginUser.getWorkNo();
			 yggh = id;
		 }
		 QueryWrapper queryWrapper=new QueryWrapper();
		 queryWrapper.eq("yggh",yggh);
		 queryWrapper.eq("khbh",khbh);

		 boolean flag = appCkkhWatchService.remove(queryWrapper);
		 	if (!flag)
				return Result.error("操作失败,客户已解除关注");

		 	return Result.ok();
	 }

}
