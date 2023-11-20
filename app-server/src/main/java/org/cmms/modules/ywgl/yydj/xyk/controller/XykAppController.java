package org.cmms.modules.ywgl.yydj.xyk.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.ywgl.yydj.xyk.entity.XykApp;
import org.cmms.modules.ywgl.yydj.xyk.service.IXykAppService;
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
 * @Description: 信用卡App
 * @Author: jeecg-boot
 * @Date:   2022-03-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="信用卡App")
@RestController
@RequestMapping("/xyk/xykApp")
public class XykAppController extends JeecgController<XykApp, IXykAppService> {
	@Autowired
	private IXykAppService xykAppService;
	 @Autowired
	 IDictValueQuery iDictValueQuery;
	/**
	 * 分页列表查询
	 *
	 * @param xykApp
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "信用卡App-分页列表查询")
	@ApiOperation(value="信用卡App-分页列表查询", notes="信用卡App-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(XykApp xykApp,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<XykApp> queryWrapper = QueryGenerator.initQueryWrapper(xykApp, req.getParameterMap());
		Page<XykApp> page = new Page<XykApp>(pageNo, pageSize);
		IPage<XykApp> pageList = xykAppService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param xykApp
	 * @return
	 */
	@AutoLog(value = "信用卡App-添加")
	@ApiOperation(value="信用卡App-添加", notes="信用卡App-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody XykApp xykApp) {
		if (StringUtils.isBlank(xykApp.getJgdm()))
			return Result.error("业务网点不能为空");
		if (StringUtils.isBlank(xykApp.getZjhm()))
			return Result.error("证件号码不能为空");
		if (xykApp.getYyrq() == null){
			return Result.error("预约日期不能为空");
		}
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq("jgdm",xykApp.getJgdm());
		queryWrapper.eq("zjhm",xykApp.getZjhm());
		queryWrapper.eq("yyrq",xykApp.getYyrq());
		List list = service.list(queryWrapper);
		if (CollUtil.isNotEmpty(list)){
			return Result.error("同一机构一个客户一天只能预约一次！");
		}

		xykApp.setYybh(Long.parseLong(iDictValueQuery.getSeqRateZxlldjbDjidNextval("SEQ_PUBLIC_ID_SJYH.nextval")));
		xykApp.setLrr(getWorkNo());
		xykAppService.save(xykApp);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param xykApp
	 * @return
	 */
	@AutoLog(value = "信用卡App-编辑")
	@ApiOperation(value="信用卡App-编辑", notes="信用卡App-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody XykApp xykApp) {
		xykAppService.updateById(xykApp);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "信用卡App-通过id删除")
	@ApiOperation(value="信用卡App-通过id删除", notes="信用卡App-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		xykAppService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "信用卡App-批量删除")
	@ApiOperation(value="信用卡App-批量删除", notes="信用卡App-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.xykAppService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "信用卡App-通过id查询")
	@ApiOperation(value="信用卡App-通过id查询", notes="信用卡App-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		XykApp xykApp = xykAppService.getById(id);
		return Result.ok(xykApp);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param xykApp
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, XykApp xykApp) {
      return super.exportXls(request, xykApp, XykApp.class, "信用卡App");
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
      return super.importExcel(request, response, XykApp.class);
  }

}
