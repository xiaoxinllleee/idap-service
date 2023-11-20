package org.cmms.modules.xddaglxt.dksjgl.dkhtsjglbc.controller;

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
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.entity.DkhtsjglVo;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjglbc.entity.Dkhtsjglbc;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjglbc.entity.DkhtsjglbcVo;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjglbc.service.IDkhtsjglbcService;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjglbc.verify.DkhtsjglBcImportVerify;
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
 * @Description: 贷款合同数据管理(补充)
 * @Author: jeecg-boot
 * @Date:   2021-11-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款合同数据管理(补充)")
@RestController
@RequestMapping("/dkhtsjglbc/dkhtsjglbc")
public class DkhtsjglbcController extends JeecgController<Dkhtsjglbc, IDkhtsjglbcService> {
	@Autowired
	private IDkhtsjglbcService dkhtsjglbcService;
	@Autowired
	private DkhtsjglBcImportVerify dkhtsjglBcImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param dkhtsjglbc
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款合同数据管理(补充)-分页列表查询")
	@ApiOperation(value="贷款合同数据管理(补充)-分页列表查询", notes="贷款合同数据管理(补充)-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dkhtsjglbc dkhtsjglbc,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dkhtsjglbc> queryWrapper = QueryGenerator.initQueryWrapper(dkhtsjglbc, req.getParameterMap());
		Page<Dkhtsjglbc> page = new Page<Dkhtsjglbc>(pageNo, pageSize);
		IPage<Dkhtsjglbc> pageList = dkhtsjglbcService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dkhtsjglbc
	 * @return
	 */
	@AutoLog(value = "贷款合同数据管理(补充)-添加")
	@ApiOperation(value="贷款合同数据管理(补充)-添加", notes="贷款合同数据管理(补充)-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dkhtsjglbc dkhtsjglbc) {
		dkhtsjglbcService.save(dkhtsjglbc);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dkhtsjglbc
	 * @return
	 */
	@AutoLog(value = "贷款合同数据管理(补充)-编辑")
	@ApiOperation(value="贷款合同数据管理(补充)-编辑", notes="贷款合同数据管理(补充)-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dkhtsjglbc dkhtsjglbc) {
		QueryWrapper<Dkhtsjglbc> queryWrapper = new QueryWrapper<Dkhtsjglbc>();
		queryWrapper.eq("hth",dkhtsjglbc.getHth());
		dkhtsjglbcService.update(dkhtsjglbc,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款合同数据管理(补充)-通过id删除")
	@ApiOperation(value="贷款合同数据管理(补充)-通过id删除", notes="贷款合同数据管理(补充)-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkhtsjglbcService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款合同数据管理(补充)-批量删除")
	@ApiOperation(value="贷款合同数据管理(补充)-批量删除", notes="贷款合同数据管理(补充)-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkhtsjglbcService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款合同数据管理(补充)-通过id查询")
	@ApiOperation(value="贷款合同数据管理(补充)-通过id查询", notes="贷款合同数据管理(补充)-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dkhtsjglbc dkhtsjglbc = dkhtsjglbcService.getById(id);
		return Result.ok(dkhtsjglbc);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkhtsjglbc
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dkhtsjglbc dkhtsjglbc) {
      return super.exportXls(request, dkhtsjglbc, Dkhtsjglbc.class, "贷款合同数据管理(补充)");
  }


	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 return super.exportTemplateXls(DkhtsjglbcVo.class, "贷款合同数据管理(补充)导入模板");
	 }
  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(@RequestBody JSONObject jsonObject ,HttpServletRequest request, HttpServletResponse response) {
      return super.importExcelByTemplate(jsonObject,request, response, Dkhtsjglbc.class,DkhtsjglbcVo.class,dkhtsjglBcImportVerify);
  }

}
