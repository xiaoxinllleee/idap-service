package org.cmms.modules.hr.yggl.ygxxgl.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;
import org.cmms.modules.hr.yggl.ygxxgl.service.IVhrbasstaffpostService;
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
 * @Description: 岗位表
 * @Author: jeecg-boot
 * @Date:   2020-08-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="岗位表")
@RestController
@RequestMapping("/hr/vhrbasstaffpost")
public class VhrbasstaffpostController extends JeecgController<Vhrbasstaffpost, IVhrbasstaffpostService> {
	@Autowired
	private IVhrbasstaffpostService vhrbasstaffpostService;
	
	/**
	 * 分页列表查询
	 *
	 * @param vhrbasstaffpost
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "岗位表-分页列表查询")
	@ApiOperation(value="岗位表-分页列表查询", notes="岗位表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Vhrbasstaffpost vhrbasstaffpost,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Vhrbasstaffpost> queryWrapper = QueryGenerator.initQueryWrapper(vhrbasstaffpost, req.getParameterMap());
		Page<Vhrbasstaffpost> page = new Page<Vhrbasstaffpost>(pageNo, pageSize);
		IPage<Vhrbasstaffpost> pageList = vhrbasstaffpostService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	@GetMapping(value = "/listAll")
	public Result<?> listAll(Vhrbasstaffpost vhrbasstaffpost,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   String gwbzs,
								   HttpServletRequest req) {
		QueryWrapper<Vhrbasstaffpost> queryWrapper = QueryGenerator.initQueryWrapper(vhrbasstaffpost, req.getParameterMap());
		if (StringUtils.isNotEmpty(gwbzs)) {
			queryWrapper.in("gwbz", Arrays.asList(gwbzs.split(",")));
		}
		List<Vhrbasstaffpost> list = vhrbasstaffpostService.list(queryWrapper);
		return Result.ok(list);
	}
	
	/**
	 * 添加
	 *
	 * @param vhrbasstaffpost
	 * @return
	 */
	@AutoLog(value = "岗位表-添加")
	@ApiOperation(value="岗位表-添加", notes="岗位表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Vhrbasstaffpost vhrbasstaffpost) {
		vhrbasstaffpostService.save(vhrbasstaffpost);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param vhrbasstaffpost
	 * @return
	 */
	@AutoLog(value = "岗位表-编辑")
	@ApiOperation(value="岗位表-编辑", notes="岗位表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Vhrbasstaffpost vhrbasstaffpost) {
		vhrbasstaffpostService.updateById(vhrbasstaffpost);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "岗位表-通过id删除")
	@ApiOperation(value="岗位表-通过id删除", notes="岗位表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		vhrbasstaffpostService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "岗位表-批量删除")
	@ApiOperation(value="岗位表-批量删除", notes="岗位表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.vhrbasstaffpostService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "岗位表-通过id查询")
	@ApiOperation(value="岗位表-通过id查询", notes="岗位表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Vhrbasstaffpost vhrbasstaffpost = vhrbasstaffpostService.getById(id);
		return Result.ok(vhrbasstaffpost);
	}

	 /**
	  * 通过yggh查询
	  *
	  * @param yggh
	  * @return
	  */
	 @AutoLog(value = "岗位表-通过yggh查询")
	 @ApiOperation(value="岗位表-通过yggh查询", notes="岗位表-通过yggh查询")
	 @GetMapping(value = "/queryByYggh")
	 public Result<?> queryByYggh(@RequestParam(name="yggh", required = false) String yggh) {
		 if (StringUtils.isEmpty(yggh)) {
		 	LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 	yggh = sysUser.getWorkNo();
		 }
		 Vhrbasstaffpost vhrbasstaffpost = vhrbasstaffpostService.selectByYggh(yggh);
		 return Result.ok(vhrbasstaffpost);
	 }

	 /**
	  * 通过yggh查询
	  *
	  * @return
	  */
	 @AutoLog(value = "岗位表-通过yggh查询")
	 @ApiOperation(value="岗位表-通过yggh查询", notes="岗位表-通过yggh查询")
	 @GetMapping(value = "/getZhYgxxByZzbz")
	 public Result<?> getZhYgxxByZzbz(@RequestParam(name="zzbz", required = false) String zzbz) {
		 if (StringUtils.isEmpty(zzbz)) {
			 Vhrbasstaffpost vhrbasstaffpost = vhrbasstaffpostService.selectByYggh(getLoginUser().getWorkNo());
			 if (vhrbasstaffpost != null) {
			 	zzbz = vhrbasstaffpost.getZzbz();
			 }
		 }
		 List<Vhrbasstaffpost> vhrbasstaffpostList = vhrbasstaffpostService.getZhYgxxByZzbz(zzbz);
		 return Result.ok(vhrbasstaffpostList);
	 }

  /**
   * 导出excel
   *
   * @param request
   * @param vhrbasstaffpost
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Vhrbasstaffpost vhrbasstaffpost) {
      return super.exportXls(request, vhrbasstaffpost, Vhrbasstaffpost.class, "岗位表");
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
      return super.importExcel(request, response, Vhrbasstaffpost.class);
  }

}
