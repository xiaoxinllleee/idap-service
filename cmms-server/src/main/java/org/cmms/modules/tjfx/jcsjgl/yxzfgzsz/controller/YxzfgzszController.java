package org.cmms.modules.tjfx.jcsjgl.yxzfgzsz.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzsz.entity.Yxzfgzsz;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzsz.service.IYxzfgzszService;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzsz.vo.Yxzfmx;
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
 * @Description: 农户有效走访规则设置
 * @Author: jeecg-boot
 * @Date:   2022-01-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags="农户有效走访规则设置")
@RestController
@RequestMapping("/jcsjgl/nhyxzfgzsz")
public class YxzfgzszController extends JeecgController<Yxzfgzsz, IYxzfgzszService> {
	@Autowired
	private IYxzfgzszService yxzfgzszService;
	
	/**
	 * 分页列表查询
	 *
	 * @param yxzfgzsz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "农户有效走访规则设置-分页列表查询")
	@ApiOperation(value="农户有效走访规则设置-分页列表查询", notes="农户有效走访规则设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Yxzfgzsz yxzfgzsz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Yxzfgzsz> queryWrapper = QueryGenerator.initQueryWrapper(yxzfgzsz, req.getParameterMap());
		Page<Yxzfgzsz> page = new Page<Yxzfgzsz>(pageNo, pageSize);
		IPage<Yxzfgzsz> pageList = yxzfgzszService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param yxzfgzsz
	 * @return
	 */
	@AutoLog(value = "农户有效走访规则设置-添加")
	@ApiOperation(value="农户有效走访规则设置-添加", notes="农户有效走访规则设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Yxzfgzsz yxzfgzsz) {
		QueryWrapper<Yxzfgzsz> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("zbid", yxzfgzsz.getZbid());
		List<Yxzfgzsz> yxzfgzszList = yxzfgzszService.list(queryWrapper);
		if (!yxzfgzszList.isEmpty()) {
			return Result.error("已经存在的指标ID");
		}
		yxzfgzszService.save(yxzfgzsz);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param yxzfgzsz
	 * @return
	 */
	@AutoLog(value = "农户有效走访规则设置-编辑")
	@ApiOperation(value="农户有效走访规则设置-编辑", notes="农户有效走访规则设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Yxzfgzsz yxzfgzsz) {
		UpdateWrapper<Yxzfgzsz> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("zbid", yxzfgzsz.getZbid());
		yxzfgzszService.update(yxzfgzsz, updateWrapper);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "农户有效走访规则设置-通过id删除")
	@ApiOperation(value="农户有效走访规则设置-通过id删除", notes="农户有效走访规则设置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		QueryWrapper<Yxzfgzsz> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("zbid", id);
		yxzfgzszService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "农户有效走访规则设置-批量删除")
	@ApiOperation(value="农户有效走访规则设置-批量删除", notes="农户有效走访规则设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.yxzfgzszService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "农户有效走访规则设置-通过id查询")
	@ApiOperation(value="农户有效走访规则设置-通过id查询", notes="农户有效走访规则设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Yxzfgzsz yxzfgzsz = yxzfgzszService.getById(id);
		return Result.ok(yxzfgzsz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param yxzfgzsz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Yxzfgzsz yxzfgzsz) {
      return super.exportXls(request, yxzfgzsz, Yxzfgzsz.class, "农户有效走访规则设置");
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
      return super.importExcel(request, response, Yxzfgzsz.class);
  }

	 /**
	  * 根据户号编码获取有效走访的指标信息-农户
	  *
	  * @param hhbm
	  * @return
	  */
	 @RequestMapping(value = "/getZfzbxxByHhbm", method = RequestMethod.GET)
	 public Result<?> getZfzbxxByHhbm(@RequestParam("hhbm") String hhbm,
									  @RequestParam(name = "tjrq",required = false)String tjrq) {
	 	if (StringUtils.isNotBlank(tjrq)){
	 		tjrq=tjrq.replace("-","");
		}
		 List<Yxzfmx> list = yxzfgzszService.getZfzbxxByHhbm(hhbm,tjrq);
		 return Result.ok(list);
	 }
}
