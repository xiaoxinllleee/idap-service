package org.cmms.modules.tjfx.jcsjgl.xxnyztyxzfsz.controller;

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
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.jcsjgl.jzyxgzsz.entity.TjfxJzyxzbpzNh;
import org.cmms.modules.tjfx.jcsjgl.xxnyztyxzfsz.entity.TjfxXxnyztYxzfzbsz;
import org.cmms.modules.tjfx.jcsjgl.xxnyztyxzfsz.service.ITjfxXxnyztYxzfzbszService;
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
 * @Description: 新型农业主体走访有效走访指标设置
 * @Author: jeecg-boot
 * @Date:   2023-06-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="新型农业主体走访有效走访指标设置")
@RestController
@RequestMapping("/jcsjgl/tjfxXxnyztYxzfzbsz")
public class TjfxXxnyztYxzfzbszController extends JeecgController<TjfxXxnyztYxzfzbsz, ITjfxXxnyztYxzfzbszService> {
	@Autowired
	private ITjfxXxnyztYxzfzbszService tjfxXxnyztYxzfzbszService;
	
	/**
	 * 分页列表查询
	 *
	 * @param tjfxXxnyztYxzfzbsz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "新型农业主体走访有效走访指标设置-分页列表查询")
	@ApiOperation(value="新型农业主体走访有效走访指标设置-分页列表查询", notes="新型农业主体走访有效走访指标设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TjfxXxnyztYxzfzbsz tjfxXxnyztYxzfzbsz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TjfxXxnyztYxzfzbsz> queryWrapper = QueryGenerator.initQueryWrapper(tjfxXxnyztYxzfzbsz, req.getParameterMap());
		Page<TjfxXxnyztYxzfzbsz> page = new Page<TjfxXxnyztYxzfzbsz>(pageNo, pageSize);
		IPage<TjfxXxnyztYxzfzbsz> pageList = tjfxXxnyztYxzfzbszService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param tjfxXxnyztYxzfzbsz
	 * @return
	 */
	@AutoLog(value = "新型农业主体走访有效走访指标设置-添加")
	@ApiOperation(value="新型农业主体走访有效走访指标设置-添加", notes="新型农业主体走访有效走访指标设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TjfxXxnyztYxzfzbsz tjfxXxnyztYxzfzbsz) {
		tjfxXxnyztYxzfzbszService.save(tjfxXxnyztYxzfzbsz);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param tjfxXxnyztYxzfzbsz
	 * @return
	 */
	@AutoLog(value = "新型农业主体走访有效走访指标设置-编辑")
	@ApiOperation(value="新型农业主体走访有效走访指标设置-编辑", notes="新型农业主体走访有效走访指标设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TjfxXxnyztYxzfzbsz tjfxXxnyztYxzfzbsz) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		tjfxXxnyztYxzfzbsz.setUpdateBy(sysUser.getUsername());
		tjfxXxnyztYxzfzbsz.setUpdateTime(new Date());
		UpdateWrapper<TjfxXxnyztYxzfzbsz> updateWrapper=new UpdateWrapper<>();
		updateWrapper.eq("zbid",tjfxXxnyztYxzfzbsz.getZbid());
		tjfxXxnyztYxzfzbszService.update(tjfxXxnyztYxzfzbsz,updateWrapper);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "新型农业主体走访有效走访指标设置-通过id删除")
	@ApiOperation(value="新型农业主体走访有效走访指标设置-通过id删除", notes="新型农业主体走访有效走访指标设置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		QueryWrapper<TjfxXxnyztYxzfzbsz> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("zbid",id);
		tjfxXxnyztYxzfzbszService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "新型农业主体走访有效走访指标设置-批量删除")
	@ApiOperation(value="新型农业主体走访有效走访指标设置-批量删除", notes="新型农业主体走访有效走访指标设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tjfxXxnyztYxzfzbszService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "新型农业主体走访有效走访指标设置-通过id查询")
	@ApiOperation(value="新型农业主体走访有效走访指标设置-通过id查询", notes="新型农业主体走访有效走访指标设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TjfxXxnyztYxzfzbsz tjfxXxnyztYxzfzbsz = tjfxXxnyztYxzfzbszService.getById(id);
		return Result.ok(tjfxXxnyztYxzfzbsz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param tjfxXxnyztYxzfzbsz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, TjfxXxnyztYxzfzbsz tjfxXxnyztYxzfzbsz) {
      return super.exportXls(request, tjfxXxnyztYxzfzbsz, TjfxXxnyztYxzfzbsz.class, "新型农业主体走访有效走访指标设置");
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
      return super.importExcel(request, response, TjfxXxnyztYxzfzbsz.class);
  }

}
