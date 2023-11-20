package org.cmms.modules.dkjkpt.tjcx.ygghdkxxtj.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dkjkpt.tjcx.ygghdkxxtj.entity.YgghdkxxtjJb;
import org.cmms.modules.dkjkpt.tjcx.ygghdkxxtj.service.IYgghdkxxtjJbService;
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
 * @Description: 员工管户贷款信息统计季报
 * @Author: jeecg-boot
 * @Date:   2022-09-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="员工管户贷款信息统计季报")
@RestController
@RequestMapping("/ygghdkxxtj/ygghdkxxtjJb")
public class YgghdkxxtjJbController extends JeecgController<YgghdkxxtjJb, IYgghdkxxtjJbService> {
	@Autowired
	private IYgghdkxxtjJbService ygghdkxxtjJbService;

	/**
	 * 分页列表查询
	 *
	 * @param ygghdkxxtjJb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "员工管户贷款信息统计季报-分页列表查询")
	@ApiOperation(value="员工管户贷款信息统计季报-分页列表查询", notes="员工管户贷款信息统计季报-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(YgghdkxxtjJb ygghdkxxtjJb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<YgghdkxxtjJb> queryWrapper = QueryGenerator.initQueryWrapper(ygghdkxxtjJb, req.getParameterMap());
		Page<YgghdkxxtjJb> page = new Page<YgghdkxxtjJb>(pageNo, pageSize);
		IPage<YgghdkxxtjJb> pageList = ygghdkxxtjJbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param ygghdkxxtjJb
	 * @return
	 */
	@AutoLog(value = "员工管户贷款信息统计季报-添加")
	@ApiOperation(value="员工管户贷款信息统计季报-添加", notes="员工管户贷款信息统计季报-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody YgghdkxxtjJb ygghdkxxtjJb) {
		ygghdkxxtjJbService.save(ygghdkxxtjJb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param ygghdkxxtjJb
	 * @return
	 */
	@AutoLog(value = "员工管户贷款信息统计季报-编辑")
	@ApiOperation(value="员工管户贷款信息统计季报-编辑", notes="员工管户贷款信息统计季报-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody YgghdkxxtjJb ygghdkxxtjJb) {
		ygghdkxxtjJbService.updateById(ygghdkxxtjJb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工管户贷款信息统计季报-通过id删除")
	@ApiOperation(value="员工管户贷款信息统计季报-通过id删除", notes="员工管户贷款信息统计季报-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ygghdkxxtjJbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "员工管户贷款信息统计季报-批量删除")
	@ApiOperation(value="员工管户贷款信息统计季报-批量删除", notes="员工管户贷款信息统计季报-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ygghdkxxtjJbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工管户贷款信息统计季报-通过id查询")
	@ApiOperation(value="员工管户贷款信息统计季报-通过id查询", notes="员工管户贷款信息统计季报-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		YgghdkxxtjJb ygghdkxxtjJb = ygghdkxxtjJbService.getById(id);
		return Result.ok(ygghdkxxtjJb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ygghdkxxtjJb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, YgghdkxxtjJb ygghdkxxtjJb) {
      return super.exportXls(request, ygghdkxxtjJb, YgghdkxxtjJb.class, "员工管户贷款信息统计季报");
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
      return super.importExcel(request, response, YgghdkxxtjJb.class);
  }

	 /**
	  * 提取
	  * @param object
	  * @return
	  */
	 @RequestMapping(value = "/init", method = RequestMethod.PUT)
	 public Result<?> InitData(@RequestBody JSONObject object) {
		 System.out.println("tjrq-----"+object.getString("tjrq"));
		 String tjrq = object.getString("tjrq");
		 tjrq = tjrq.replace("-", "");
		 try {
			 ygghdkxxtjJbService.InitData(tjrq);
		 } catch (Exception e) {
			 log.error("提取失败！",e.getMessage());
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功！");
	 }

}
