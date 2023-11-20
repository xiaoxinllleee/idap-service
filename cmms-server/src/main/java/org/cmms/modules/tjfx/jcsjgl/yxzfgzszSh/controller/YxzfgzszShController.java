package org.cmms.modules.tjfx.jcsjgl.yxzfgzszSh.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzsz.vo.Yxzfmx;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzszSh.entity.YxzfgzszSh;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzszSh.service.IYxzfgzszShService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jsoup.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 有效走访规则设置_商户
 * @Author: jeecg-boot
 * @Date:   2022-05-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="有效走访规则设置_商户")
@RestController
@RequestMapping("/jcsjgl/shyxzfgzsz")
public class YxzfgzszShController extends JeecgController<YxzfgzszSh, IYxzfgzszShService> {
	@Autowired
	private IYxzfgzszShService yxzfgzszShService;
	
	/**
	 * 分页列表查询
	 *
	 * @param yxzfgzszSh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "有效走访规则设置_商户-分页列表查询")
	@ApiOperation(value="有效走访规则设置_商户-分页列表查询", notes="有效走访规则设置_商户-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(YxzfgzszSh yxzfgzszSh,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<YxzfgzszSh> queryWrapper = QueryGenerator.initQueryWrapper(yxzfgzszSh, req.getParameterMap());
		Page<YxzfgzszSh> page = new Page<YxzfgzszSh>(pageNo, pageSize);
		IPage<YxzfgzszSh> pageList = yxzfgzszShService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param yxzfgzszSh
	 * @return
	 */
	@AutoLog(value = "有效走访规则设置_商户-添加")
	@ApiOperation(value="有效走访规则设置_商户-添加", notes="有效走访规则设置_商户-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody YxzfgzszSh yxzfgzszSh) {
		QueryWrapper<YxzfgzszSh> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("zbid", yxzfgzszSh.getZbid());
		List<YxzfgzszSh> yxzfgzszList = yxzfgzszShService.list(queryWrapper);
		if (!yxzfgzszList.isEmpty()) {
			return Result.error("已经存在的指标ID");
		}
		yxzfgzszShService.save(yxzfgzszSh);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param yxzfgzszSh
	 * @return
	 */
	@AutoLog(value = "有效走访规则设置_商户-编辑")
	@ApiOperation(value="有效走访规则设置_商户-编辑", notes="有效走访规则设置_商户-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody YxzfgzszSh yxzfgzszSh) {
		UpdateWrapper<YxzfgzszSh> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("zbid", yxzfgzszSh.getZbid());
		yxzfgzszShService.update(yxzfgzszSh, updateWrapper);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "有效走访规则设置_商户-通过id删除")
	@ApiOperation(value="有效走访规则设置_商户-通过id删除", notes="有效走访规则设置_商户-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		QueryWrapper<YxzfgzszSh> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("zbid", id);
		yxzfgzszShService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "有效走访规则设置_商户-批量删除")
	@ApiOperation(value="有效走访规则设置_商户-批量删除", notes="有效走访规则设置_商户-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.yxzfgzszShService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "有效走访规则设置_商户-通过id查询")
	@ApiOperation(value="有效走访规则设置_商户-通过id查询", notes="有效走访规则设置_商户-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		YxzfgzszSh yxzfgzszSh = yxzfgzszShService.getById(id);
		return Result.ok(yxzfgzszSh);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param yxzfgzszSh
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, YxzfgzszSh yxzfgzszSh) {
      return super.exportXls(request, yxzfgzszSh, YxzfgzszSh.class, "有效走访规则设置_商户");
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
      return super.importExcel(request, response, YxzfgzszSh.class);
  }

	 /**
	  * 根据商户ID获取有效走访的指标信息
	  *
	  * @param shid
	  * @return
	  */
	 @RequestMapping(value = "/getZfzbxxByShid", method = RequestMethod.GET)
	 public Result<?> getZfzbxxByShid(@RequestParam("shid") String shid,
									  @RequestParam(name = "tjrq",required = false)String tjrq) {
	 	if (StringUtils.isNotBlank(tjrq)){
	 		tjrq=tjrq.replace("-","");
		}
		 List<Yxzfmx> list = yxzfgzszShService.getZfzbxxByShid(shid,tjrq);
		 return Result.ok(list);
	 }

}
