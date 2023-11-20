package org.cmms.modules.tjfx.jcsjgl.yxzfgzszgzry.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzszSh.entity.YxzfgzszSh;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzszgzry.entity.YxzfgzszGzry;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzszgzry.service.IYxzfgzszGzryService;
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
 * @Description: 公职人员有效走访指标配置
 * @Author: jeecg-boot
 * @Date:   2022-08-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="公职人员有效走访指标配置")
@RestController
@RequestMapping("/yxzfgzszgzry/yxzfgzszGzry")
public class YxzfgzszGzryController extends JeecgController<YxzfgzszGzry, IYxzfgzszGzryService> {
	@Autowired
	private IYxzfgzszGzryService yxzfgzszGzryService;
	
	/**
	 * 分页列表查询
	 *
	 * @param yxzfgzszGzry
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "公职人员有效走访指标配置-分页列表查询")
	@ApiOperation(value="公职人员有效走访指标配置-分页列表查询", notes="公职人员有效走访指标配置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(YxzfgzszGzry yxzfgzszGzry,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<YxzfgzszGzry> queryWrapper = QueryGenerator.initQueryWrapper(yxzfgzszGzry, req.getParameterMap());
		Page<YxzfgzszGzry> page = new Page<YxzfgzszGzry>(pageNo, pageSize);
		IPage<YxzfgzszGzry> pageList = yxzfgzszGzryService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param yxzfgzszGzry
	 * @return
	 */
	@AutoLog(value = "公职人员有效走访指标配置-添加")
	@ApiOperation(value="公职人员有效走访指标配置-添加", notes="公职人员有效走访指标配置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody YxzfgzszGzry yxzfgzszGzry) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		QueryWrapper<YxzfgzszGzry> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("zbid", yxzfgzszGzry.getZbid());
		List<YxzfgzszGzry> list=yxzfgzszGzryService.list(queryWrapper);
		if (!list.isEmpty()) {
			return Result.error("已经存在的指标ID");
		}
		yxzfgzszGzry.setCreateTime(new Date());
		yxzfgzszGzry.setCreateBy(sysUser.getUsername());
		yxzfgzszGzryService.save(yxzfgzszGzry);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param yxzfgzszGzry
	 * @return
	 */
	@AutoLog(value = "公职人员有效走访指标配置-编辑")
	@ApiOperation(value="公职人员有效走访指标配置-编辑", notes="公职人员有效走访指标配置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody YxzfgzszGzry yxzfgzszGzry) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		yxzfgzszGzry.setUpdateTime(new Date());
		yxzfgzszGzry.setUpdateBy(sysUser.getUsername());
		UpdateWrapper<YxzfgzszGzry> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("zbid", yxzfgzszGzry.getZbid());
		yxzfgzszGzryService.updateById(yxzfgzszGzry);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "公职人员有效走访指标配置-通过id删除")
	@ApiOperation(value="公职人员有效走访指标配置-通过id删除", notes="公职人员有效走访指标配置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		QueryWrapper<YxzfgzszGzry> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("zbid", id);
		yxzfgzszGzryService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "公职人员有效走访指标配置-批量删除")
	@ApiOperation(value="公职人员有效走访指标配置-批量删除", notes="公职人员有效走访指标配置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.yxzfgzszGzryService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "公职人员有效走访指标配置-通过id查询")
	@ApiOperation(value="公职人员有效走访指标配置-通过id查询", notes="公职人员有效走访指标配置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		YxzfgzszGzry yxzfgzszGzry = yxzfgzszGzryService.getById(id);
		return Result.ok(yxzfgzszGzry);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param yxzfgzszGzry
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, YxzfgzszGzry yxzfgzszGzry) {
      return super.exportXls(request, yxzfgzszGzry, YxzfgzszGzry.class, "公职人员有效走访指标配置");
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
      return super.importExcel(request, response, YxzfgzszGzry.class);
  }

}
