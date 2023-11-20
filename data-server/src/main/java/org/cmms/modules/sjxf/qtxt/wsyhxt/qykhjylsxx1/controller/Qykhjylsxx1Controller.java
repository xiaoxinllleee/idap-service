package org.cmms.modules.sjxf.qtxt.wsyhxt.qykhjylsxx1.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.sjxf.qtxt.wsyhxt.qykhjylsxx1.entity.Qykhjylsxx1;
import org.cmms.modules.sjxf.qtxt.wsyhxt.qykhjylsxx1.service.IQykhjylsxx1Service;
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
 * @Description: 企业客户交易流水信息(当天未经查证)
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Slf4j
@Api(tags="企业客户交易流水信息(当天未经查证)")
@RestController
@RequestMapping("/qykhjylsxx1/qykhjylsxx1")
public class Qykhjylsxx1Controller extends JeecgController<Qykhjylsxx1, IQykhjylsxx1Service> {
	@Autowired
	private IQykhjylsxx1Service qykhjylsxx1Service;

	/**
	 * 分页列表查询
	 *
	 * @param qykhjylsxx1
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "企业客户交易流水信息(当天未经查证)-分页列表查询")
	@ApiOperation(value="企业客户交易流水信息(当天未经查证)-分页列表查询", notes="企业客户交易流水信息(当天未经查证)-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Qykhjylsxx1 qykhjylsxx1,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Qykhjylsxx1> queryWrapper = QueryGenerator.initQueryWrapper(qykhjylsxx1, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IQykhjylsxx1Service.class,qykhjylsxx1Service,pageNo,pageSize,queryWrapper,"tfl_Flowno","tfl_Batchno","tfl_Submittime");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param qykhjylsxx1
	 * @return
	 */
	@AutoLog(value = "企业客户交易流水信息(当天未经查证)-添加")
	@ApiOperation(value="企业客户交易流水信息(当天未经查证)-添加", notes="企业客户交易流水信息(当天未经查证)-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Qykhjylsxx1 qykhjylsxx1) {
		qykhjylsxx1Service.save(qykhjylsxx1);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param qykhjylsxx1
	 * @return
	 */
	@AutoLog(value = "企业客户交易流水信息(当天未经查证)-编辑")
	@ApiOperation(value="企业客户交易流水信息(当天未经查证)-编辑", notes="企业客户交易流水信息(当天未经查证)-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Qykhjylsxx1 qykhjylsxx1) {
		qykhjylsxx1Service.updateById(qykhjylsxx1);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "企业客户交易流水信息(当天未经查证)-通过id删除")
	@ApiOperation(value="企业客户交易流水信息(当天未经查证)-通过id删除", notes="企业客户交易流水信息(当天未经查证)-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		qykhjylsxx1Service.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "企业客户交易流水信息(当天未经查证)-批量删除")
	@ApiOperation(value="企业客户交易流水信息(当天未经查证)-批量删除", notes="企业客户交易流水信息(当天未经查证)-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.qykhjylsxx1Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "企业客户交易流水信息(当天未经查证)-通过id查询")
	@ApiOperation(value="企业客户交易流水信息(当天未经查证)-通过id查询", notes="企业客户交易流水信息(当天未经查证)-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Qykhjylsxx1 qykhjylsxx1 = qykhjylsxx1Service.getById(id);
		return Result.ok(qykhjylsxx1);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param qykhjylsxx1
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Qykhjylsxx1 qykhjylsxx1) {
      return super.exportXls(request, qykhjylsxx1, Qykhjylsxx1.class, "企业客户交易流水信息(当天未经查证)");
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
      return super.importExcel(request, response, Qykhjylsxx1.class);
  }

}
