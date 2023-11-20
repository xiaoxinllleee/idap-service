package org.cmms.modules.system.sjbd.controller;

import java.math.BigDecimal;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.system.sjbd.entity.SjxfCsjb;
import org.cmms.modules.system.sjbd.entity.SjxfJgbd;
import org.cmms.modules.system.sjbd.service.ISjxfCsjbService;
import org.cmms.modules.system.sjbd.service.ISjxfJgbdService;
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
 * @Description: 数据下发结果比对
 * @Author: jeecg-boot
 * @Date:   2022-06-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="数据下发结果比对")
@RestController
@RequestMapping("/system/sjbd/sjxfJgbd")
public class SjxfJgbdController extends JeecgController<SjxfJgbd, ISjxfJgbdService> {
	@Autowired
	private ISjxfJgbdService sjxfJgbdService;
	 @Autowired
	 private ISjxfCsjbService sjxfCsjbService;
	/**
	 * 分页列表查询
	 *
	 * @param sjxfJgbd
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "数据下发结果比对-分页列表查询")
	@ApiOperation(value="数据下发结果比对-分页列表查询", notes="数据下发结果比对-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SjxfJgbd sjxfJgbd,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {

		QueryWrapper<SjxfJgbd> queryWrapper = QueryGenerator.initQueryWrapper(sjxfJgbd, req.getParameterMap());
		Page<SjxfJgbd> page = new Page<SjxfJgbd>(pageNo, pageSize);
		IPage<SjxfJgbd> pageList = sjxfJgbdService.page(page, queryWrapper);
		return Result.ok(pageList);
	}


	 @GetMapping(value = "/init")
	 public Result<?> initSjbd(SjxfJgbd sjxfJgbd) {
		 List<SjxfCsjb> list = sjxfCsjbService.list();
		 QueryWrapper<SjxfJgbd> queryWrapper = new QueryWrapper();
		 queryWrapper.eq("sjrq", sjxfJgbd.getSjrq());
		 sjxfJgbdService.remove(queryWrapper);
		 List<SjxfJgbd> sjxfJgbdList = new ArrayList<>();
		 for (SjxfCsjb sjxfCsjb : list) {
			 SjxfJgbd sjxfJgbd1 = new SjxfJgbd();
			 sjxfJgbd1.setId(UUIDGenerator.generate());
			 sjxfJgbd1.setTablename(sjxfCsjb.getTablename());
			 sjxfJgbd1.setSjrq(sjxfJgbd.getSjrq());
			 log.info("########################计算的表名##########################" + sjxfCsjb.getTablename());
			 if (sjxfCsjb.getHzfs().equals("count")) {
				 long impalaCount = sjxfJgbdService.getCount(sjxfCsjb.getTablename(), "hive1");//sjxf
				 sjxfJgbd1.setImpalaCount(impalaCount);
				 long oracleCount = sjxfJgbdService.getCount(sjxfCsjb.getTablename(), "impala1");//eweb
				 sjxfJgbd1.setOracleCount(oracleCount);
				 if (impalaCount == oracleCount) {
					 sjxfJgbd1.setSlsfyz("是");
				 } else {
					 sjxfJgbd1.setSlsfyz("否");
				 }
			 } else if (sjxfCsjb.getHzfs().equals("sum")) {
				 Double impalaDouble = sjxfJgbdService.getSum(sjxfCsjb.getTablename(), sjxfCsjb.getFzzd(), sjxfCsjb.getHzzd(), "sjxf");
				 impalaDouble = impalaDouble == null ? 0 : impalaDouble;
				 BigDecimal impalaD = BigDecimal.valueOf(impalaDouble);
				 sjxfJgbd1.setImpalaSum(impalaD);
				 Double oracleDouble = sjxfJgbdService.getSum(sjxfCsjb.getTablename(), sjxfCsjb.getFzzd(), sjxfCsjb.getHzzd(), "eweb");
				 oracleDouble = oracleDouble == null ? 0 : oracleDouble;
				 BigDecimal oracleD = BigDecimal.valueOf(oracleDouble);
				 sjxfJgbd1.setOracleSum(oracleD);
				 if (impalaDouble == oracleDouble) {
					 sjxfJgbd1.setJesfyz("是");
				 } else {
					 sjxfJgbd1.setJesfyz("否");
				 }
				 sjxfJgbd1.setInfo("sum: 汇总字段：" + sjxfCsjb.getHzzd() + ",分组字段：" + sjxfCsjb.getFzzd());
			 } else if (sjxfCsjb.getHzfs().equals("avg")) {
				 Double impalaDouble = sjxfJgbdService.getAvg(sjxfCsjb.getTablename(), sjxfCsjb.getFzzd(), sjxfCsjb.getHzzd(), "sjxf");
				 impalaDouble = impalaDouble == null ? 0 : impalaDouble;
				 BigDecimal impalaD = BigDecimal.valueOf(impalaDouble);
				 sjxfJgbd1.setImpalaSum(impalaD);
				 Double oracleDouble = sjxfJgbdService.getAvg(sjxfCsjb.getTablename(), sjxfCsjb.getFzzd(), sjxfCsjb.getHzzd(), "eweb");
				 oracleDouble = oracleDouble == null ? 0 : oracleDouble;
				 BigDecimal oracleD = BigDecimal.valueOf(oracleDouble);
				 sjxfJgbd1.setOracleSum(oracleD);
				 if (impalaDouble == oracleDouble) {
					 sjxfJgbd1.setJesfyz("是");
				 } else {
					 sjxfJgbd1.setJesfyz("否");
				 }
				 sjxfJgbd1.setInfo("avg: 汇总字段：" + sjxfCsjb.getHzzd() + ",分组字段：" + sjxfCsjb.getFzzd());
			 }
			 sjxfJgbdList.add(sjxfJgbd1);
		 }
		 sjxfJgbdService.saveBatch(sjxfJgbdList);

		 return Result.ok("统计成功");
	 }

	/**
	 * 添加
	 *
	 * @param sjxfJgbd
	 * @return
	 */
	@AutoLog(value = "数据下发结果比对-添加")
	@ApiOperation(value="数据下发结果比对-添加", notes="数据下发结果比对-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SjxfJgbd sjxfJgbd) {
		sjxfJgbdService.save(sjxfJgbd);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sjxfJgbd
	 * @return
	 */
	@AutoLog(value = "数据下发结果比对-编辑")
	@ApiOperation(value="数据下发结果比对-编辑", notes="数据下发结果比对-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SjxfJgbd sjxfJgbd) {
		sjxfJgbdService.updateById(sjxfJgbd);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "数据下发结果比对-通过id删除")
	@ApiOperation(value="数据下发结果比对-通过id删除", notes="数据下发结果比对-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sjxfJgbdService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "数据下发结果比对-批量删除")
	@ApiOperation(value="数据下发结果比对-批量删除", notes="数据下发结果比对-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sjxfJgbdService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "数据下发结果比对-通过id查询")
	@ApiOperation(value="数据下发结果比对-通过id查询", notes="数据下发结果比对-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SjxfJgbd sjxfJgbd = sjxfJgbdService.getById(id);
		return Result.ok(sjxfJgbd);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sjxfJgbd
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SjxfJgbd sjxfJgbd) {
      return super.exportXls(request, sjxfJgbd, SjxfJgbd.class, "数据下发结果比对");
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
      return super.importExcel(request, response, SjxfJgbd.class);
  }

}
