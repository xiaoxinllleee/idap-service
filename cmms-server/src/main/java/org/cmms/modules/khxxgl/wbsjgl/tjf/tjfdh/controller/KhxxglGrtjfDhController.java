package org.cmms.modules.khxxgl.wbsjgl.tjf.tjfdh.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khxxgl.wbsjgl.tjf.grtjf.entity.Grtjf;
import org.cmms.modules.khxxgl.wbsjgl.tjf.tjfdh.entity.KhxxglGrtjfDh;
import org.cmms.modules.khxxgl.wbsjgl.tjf.tjfdh.service.IKhxxglGrtjfDhService;
import java.util.Date;
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
 * @Description: 个人碳积分兑换
 * @Author: jeecg-boot
 * @Date:   2022-11-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="个人碳积分兑换")
@RestController
@RequestMapping("/khxxgl.wbsjgl.tjf.tjfdh/khxxglGrtjfDh")
public class KhxxglGrtjfDhController extends JeecgController<KhxxglGrtjfDh, IKhxxglGrtjfDhService> {
	@Autowired
	private IKhxxglGrtjfDhService khxxglGrtjfDhService;
	
	/**
	 * 分页列表查询
	 *
	 * @param khxxglGrtjfDh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "个人碳积分兑换-分页列表查询")
	@ApiOperation(value="个人碳积分兑换-分页列表查询", notes="个人碳积分兑换-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KhxxglGrtjfDh khxxglGrtjfDh,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<KhxxglGrtjfDh> queryWrapper = QueryGenerator.initQueryWrapper(khxxglGrtjfDh, req.getParameterMap());
		Page<KhxxglGrtjfDh> page = new Page<KhxxglGrtjfDh>(pageNo, pageSize);
		IPage<KhxxglGrtjfDh> pageList = khxxglGrtjfDhService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
	  * 根据证件号码查询个人碳积分
	  */
	 @AutoLog(value = "根据证件号码查询个人碳积分")
	 @ApiOperation(value="根据证件号码查询个人碳积分", notes="根据证件号码查询个人碳积分")
	 @GetMapping(value = "/queryInfoByZjhm")
	 public Result<?> queryInfoByZjhm(@RequestParam("zjhm") String zjhm){
		 QueryWrapper<KhxxglGrtjfDh> queryWrapper=new QueryWrapper<>();
		 queryWrapper.eq("zjhm",zjhm);
		 List<KhxxglGrtjfDh> list=khxxglGrtjfDhService.list(queryWrapper);
		 return Result.ok(list);
	 }

	/**
	 * 添加
	 *
	 * @param khxxglGrtjfDh
	 * @return
	 */
	@AutoLog(value = "个人碳积分兑换-添加")
	@ApiOperation(value="个人碳积分兑换-添加", notes="个人碳积分兑换-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody KhxxglGrtjfDh khxxglGrtjfDh) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		khxxglGrtjfDh.setCreateBy(sysUser.getWorkNo());
		khxxglGrtjfDh.setCreateTime(new Date());
		khxxglGrtjfDhService.save(khxxglGrtjfDh);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param khxxglGrtjfDh
	 * @return
	 */
	@AutoLog(value = "个人碳积分兑换-编辑")
	@ApiOperation(value="个人碳积分兑换-编辑", notes="个人碳积分兑换-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhxxglGrtjfDh khxxglGrtjfDh) {
		khxxglGrtjfDhService.updateById(khxxglGrtjfDh);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "个人碳积分兑换-通过id删除")
	@ApiOperation(value="个人碳积分兑换-通过id删除", notes="个人碳积分兑换-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khxxglGrtjfDhService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "个人碳积分兑换-批量删除")
	@ApiOperation(value="个人碳积分兑换-批量删除", notes="个人碳积分兑换-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khxxglGrtjfDhService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "个人碳积分兑换-通过id查询")
	@ApiOperation(value="个人碳积分兑换-通过id查询", notes="个人碳积分兑换-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		KhxxglGrtjfDh khxxglGrtjfDh = khxxglGrtjfDhService.getById(id);
		return Result.ok(khxxglGrtjfDh);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khxxglGrtjfDh
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, KhxxglGrtjfDh khxxglGrtjfDh) {
      return super.exportXls(request, khxxglGrtjfDh, KhxxglGrtjfDh.class, "个人碳积分兑换");
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
	  return super.importExcelByTemplate(jsonObject, request, response, KhxxglGrtjfDh.class, null);
  }

	 /**
	  * 导入模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 return super.exportTemplateXls(KhxxglGrtjfDh.class, "个人碳积分兑换导入模板");
	 }

}
