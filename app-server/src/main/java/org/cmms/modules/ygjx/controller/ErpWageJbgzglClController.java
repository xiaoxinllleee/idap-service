package org.cmms.modules.ygjx.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ygjx.entity.ErpWageJbgzglCl;
import org.cmms.modules.ygjx.service.IErpWageJbgzglClService;
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
 * @Description: 基本工资管理慈利
 * @Author: jeecg-boot
 * @Date:   2022-08-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="基本工资管理慈利")
@RestController
@RequestMapping("/erpWageJbgzglCl")
public class ErpWageJbgzglClController extends JeecgController<ErpWageJbgzglCl, IErpWageJbgzglClService> {
	
	/**
	 * 分页列表查询
	 *
	 * @param erpWageJbgzglCl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "基本工资管理慈利-分页列表查询")
	@ApiOperation(value="基本工资管理慈利-分页列表查询", notes="基本工资管理慈利-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ErpWageJbgzglCl erpWageJbgzglCl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ErpWageJbgzglCl> queryWrapper = QueryGenerator.initQueryWrapper(erpWageJbgzglCl, req.getParameterMap());
		Page<ErpWageJbgzglCl> page = new Page<ErpWageJbgzglCl>(pageNo, pageSize);
		IPage<ErpWageJbgzglCl> pageList = service.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 @GetMapping(value = "/applist")
	 public Result<?> queryPageAppList(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		 Page<ErpWageJbgzglCl> page = new Page<ErpWageJbgzglCl>(pageNo, pageSize);
		 LambdaQueryWrapper<ErpWageJbgzglCl> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		 lambdaQueryWrapper.eq(ErpWageJbgzglCl::getYggh,getWorkNo());
		 lambdaQueryWrapper.orderByDesc(ErpWageJbgzglCl::getGzyf);
		 IPage<ErpWageJbgzglCl> pageList = service.page(page, lambdaQueryWrapper);
		 return Result.ok(pageList);
	 }


	 @GetMapping(value = "/getGzt")
	 public Result<?> getGzt(String xh) {
		ErpWageJbgzglCl byId = service.getById(xh);
		return Result.ok(byId);
	 }

}
