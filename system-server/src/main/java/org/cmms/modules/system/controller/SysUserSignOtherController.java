package org.cmms.modules.system.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.utils.Base64Util;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.entity.SysUserSignOther;
import org.cmms.modules.system.service.ISysUserService;
import org.cmms.modules.system.service.ISysUserSignOtherService;
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
 * @Description: 用户签名信息表
 * @Author: jeecg-boot
 * @Date:   2023-10-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags="用户签名信息表")
@RestController
@RequestMapping("/sys/sysUserSignOther")
public class SysUserSignOtherController extends JeecgController<SysUserSignOther, ISysUserSignOtherService> {
	@Autowired
	private ISysUserSignOtherService sysUserSignOtherService;
	 @Autowired
	 private ISysUserService iSysUserService;
	
	/**
	 * 分页列表查询
	 *
	 * @param sysUserSignOther
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "用户签名信息表-分页列表查询")
	@ApiOperation(value="用户签名信息表-分页列表查询", notes="用户签名信息表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SysUserSignOther sysUserSignOther,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String yhmc=sysUserSignOther.getUserId();
		sysUserSignOther.setUserId(null);
		QueryWrapper<SysUserSignOther> queryWrapper = QueryGenerator.initQueryWrapper(sysUserSignOther, req.getParameterMap());
		if (StringUtils.isNotBlank(yhmc)){
			queryWrapper.inSql("yggh","select yggh from hr_bas_staff where ygxm like '%"+yhmc+"%'");
		}
		queryWrapper.orderByDesc("scsj");
		Page<SysUserSignOther> page = new Page<SysUserSignOther>(pageNo, pageSize);
		IPage<SysUserSignOther> pageList = sysUserSignOtherService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param sysUserSignOther
	 * @return
	 */
	@AutoLog(value = "用户签名信息表-添加")
	@ApiOperation(value="用户签名信息表-添加", notes="用户签名信息表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SysUserSignOther sysUserSignOther) {
		if (sysUserSignOther!=null && StringUtils.isNotBlank(sysUserSignOther.getUserId())) {
			sysUserSignOther.setYggh(sysUserSignOther.getUserId());
			QueryWrapper<SysUser> sysUserQueryWrapper=new QueryWrapper<>();
			sysUserQueryWrapper.eq("work_no",sysUserSignOther.getUserId());
			SysUser user=iSysUserService.getOne(sysUserQueryWrapper,false);

			QueryWrapper<SysUserSignOther> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("user_id", user.getId());
			sysUserSignOtherService.remove(queryWrapper);
			LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			sysUserSignOther.setUserId(user.getId());
			sysUserSignOther.setScsj(new Date());
			sysUserSignOther.setScr(loginUser.getWorkNo());
			sysUserSignOther.setZllj(uploadpath+ File.separator+sysUserSignOther.getFwlj());
			sysUserSignOtherService.save(sysUserSignOther);
			return Result.ok("添加成功！");
		}
		return Result.error("参数错误！！");
	}
	
	/**
	 * 编辑
	 *
	 * @param sysUserSignOther
	 * @return
	 */
	@AutoLog(value = "用户签名信息表-编辑")
	@ApiOperation(value="用户签名信息表-编辑", notes="用户签名信息表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SysUserSignOther sysUserSignOther) {
		sysUserSignOtherService.updateById(sysUserSignOther);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "用户签名信息表-通过id删除")
	@ApiOperation(value="用户签名信息表-通过id删除", notes="用户签名信息表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sysUserSignOtherService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "用户签名信息表-批量删除")
	@ApiOperation(value="用户签名信息表-批量删除", notes="用户签名信息表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sysUserSignOtherService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "用户签名信息表-通过id查询")
	@ApiOperation(value="用户签名信息表-通过id查询", notes="用户签名信息表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SysUserSignOther sysUserSignOther = sysUserSignOtherService.getById(id);
		return Result.ok(sysUserSignOther);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sysUserSignOther
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SysUserSignOther sysUserSignOther) {
      return super.exportXls(request, sysUserSignOther, SysUserSignOther.class, "用户签名信息表");
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
      return super.importExcel(request, response, SysUserSignOther.class);
  }

}
