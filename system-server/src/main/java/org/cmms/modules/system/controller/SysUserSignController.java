package org.cmms.modules.system.controller;

import java.io.File;
import java.util.*;
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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.utils.Base64Util;
import org.cmms.modules.system.entity.SysUserSign;
import org.cmms.modules.system.service.ISysUserSignService;
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
 * @Description: 用户签名管理
 * @Author: jeecg-boot
 * @Date:   2023-09-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="用户签名管理")
@RestController
@RequestMapping("/sys/sysUserSign")
public class SysUserSignController extends JeecgController<SysUserSign, ISysUserSignService> {
	@Autowired
	private ISysUserSignService sysUserSignService;
	
	/**
	 * 分页列表查询
	 *
	 * @param sysUserSign
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "用户签名管理-分页列表查询")
	@ApiOperation(value="用户签名管理-分页列表查询", notes="用户签名管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SysUserSign sysUserSign,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String yhmc=sysUserSign.getUserId();
		sysUserSign.setUserId(null);
		QueryWrapper<SysUserSign> queryWrapper = QueryGenerator.initQueryWrapper(sysUserSign, req.getParameterMap());
		if (StringUtils.isNotBlank(yhmc)){
			queryWrapper.inSql("user_id","select yggh from hr_bas_staff where ygxm like '%"+yhmc+"%'");
		}
		Page<SysUserSign> page = new Page<SysUserSign>(pageNo, pageSize);
		IPage<SysUserSign> pageList = sysUserSignService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param sysUserSign
	 * @return
	 */
	@AutoLog(value = "用户签名管理-添加")
	@ApiOperation(value="用户签名管理-添加", notes="用户签名管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SysUserSign sysUserSign) {
		QueryWrapper<SysUserSign> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("user_id",sysUserSign.getUserId());
		sysUserSignService.remove(queryWrapper);
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		sysUserSign.setScsj(new Date());
		sysUserSign.setScr(sysUser.getWorkNo());
		String qz = "data:image/png;base64,";
		if (StringUtils.isNotBlank(sysUserSign.getFwlj()) && sysUserSign.getFwlj().startsWith(qz)){
			String fwlj = "/images/userSign/";
			if (!FileUtil.isDirectory(uploadpath+fwlj)){
				FileUtil.mkdir(uploadpath+fwlj);
			}
			String fileName = IdUtil.fastSimpleUUID() + ".png";
			String wjlj = uploadpath+fwlj+fileName;
			Base64Util.toImage(sysUserSign.getFwlj(), wjlj);
			sysUserSign.setFwlj(fwlj+fileName);
			sysUserSign.setZllj(uploadpath+File.separator+sysUserSign.getFwlj());
		}
		sysUserSignService.save(sysUserSign);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param sysUserSign
	 * @return
	 */
	@AutoLog(value = "用户签名管理-编辑")
	@ApiOperation(value="用户签名管理-编辑", notes="用户签名管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SysUserSign sysUserSign) {
		sysUserSignService.updateById(sysUserSign);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "用户签名管理-通过id删除")
	@ApiOperation(value="用户签名管理-通过id删除", notes="用户签名管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sysUserSignService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "用户签名管理-批量删除")
	@ApiOperation(value="用户签名管理-批量删除", notes="用户签名管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sysUserSignService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "用户签名管理-通过id查询")
	@ApiOperation(value="用户签名管理-通过id查询", notes="用户签名管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SysUserSign sysUserSign = sysUserSignService.getById(id);
		return Result.ok(sysUserSign);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sysUserSign
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SysUserSign sysUserSign) {
      return super.exportXls(request, sysUserSign, SysUserSign.class, "用户签名管理");
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
      return super.importExcel(request, response, SysUserSign.class);
  }

}
