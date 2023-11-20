package org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.entity.Dkhtsjgl;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.entity.DkhtsjglVo;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.entity.HrBasStaffPostVo;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.service.IDkhtsjglService;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.verify.DkhtsjglImportVerify;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.entity.Lsdksjgl;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.entity.LsdksjglVo;
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
 * @Description: 贷款合同数据管理
 * @Author: jeecg-boot
 * @Date:   2021-11-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款合同数据管理")
@RestController
@RequestMapping("/dkhtsjgl/dkhtsjgl")
public class DkhtsjglController extends JeecgController<Dkhtsjgl, IDkhtsjglService> {
	@Autowired
	private IDkhtsjglService dkhtsjglService;
	 @Autowired
	 private DkhtsjglImportVerify dkhtsjglImportVerify;


	 /**
	 * 分页列表查询
	 *
	 * @param dkhtsjgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款合同数据管理-分页列表查询")
	@ApiOperation(value="贷款合同数据管理-分页列表查询", notes="贷款合同数据管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dkhtsjgl dkhtsjgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   String fkString,
								   HttpServletRequest req) {
		QueryWrapper<Dkhtsjgl> queryWrapper = QueryGenerator.initQueryWrapper(dkhtsjgl, req.getParameterMap());
		if (fkString != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] date = fkString.split(",");
			try {
				queryWrapper.between("fkrq",sdf.parse(date[0]),sdf.parse(date[1]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Page<Dkhtsjgl> page = new Page<Dkhtsjgl>(pageNo, pageSize);
		IPage<Dkhtsjgl> pageList = dkhtsjglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
	  * 移交
	  */
	 /*@AutoLog(value = "移交")
	 @ApiOperation(value = "移交",notes = "贷款合同数据管理")
	 @PostMapping(value = "/preservation")
	 public Result<?> preservation(@RequestBody JSONObject jsonObject){
	 }*/

	 /**
	  * 移交查找
	  */
	 @AutoLog(value = "查找带回")
	 @ApiOperation(value = "移交",notes = "贷款合同数据管理")
	 @PostMapping(value = "/getListClaim")
	 public Result<?> getListClaim(@RequestBody JSONObject jsonObject){
	 	 System.out.println(jsonObject);
		 String zzbz = jsonObject.getString("zzbz");
		 String khjlbz = jsonObject.getString("khjlbz");
		 String rglx = jsonObject.getString("rglx");
		 String gwbz = jsonObject.getString("gwbz");
		 String yggh = jsonObject.getString("yggh");
		 List<HrBasStaffPostVo> list = dkhtsjglService.getListClaim(zzbz, khjlbz, rglx, gwbz, yggh);
		 return Result.ok(list);
	 }

	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/init")
	 public Result<?> init(){
	 	Result result = new Result<>();
	 	try {
			dkhtsjglService.pDkhtsjgl();
			result.setSuccess(true);
			return result;
		}catch (Throwable e){
	 		System.out.println(e);
	 		log.error("提取失败",e.getMessage());
	 		result.setSuccess(false);
		}
	 	return result;
	 }

	/**
	 * 添加
	 *
	 * @param dkhtsjgl
	 * @return
	 */
	@AutoLog(value = "贷款合同数据管理-添加")
	@ApiOperation(value="贷款合同数据管理-添加", notes="贷款合同数据管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dkhtsjgl dkhtsjgl) {
		dkhtsjglService.save(dkhtsjgl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dkhtsjgl
	 * @return
	 */
	@AutoLog(value = "贷款合同数据管理-编辑")
	@ApiOperation(value="贷款合同数据管理-编辑", notes="贷款合同数据管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dkhtsjgl dkhtsjgl) {
		QueryWrapper<Dkhtsjgl> queryWrapper = new QueryWrapper<Dkhtsjgl>();
		queryWrapper.eq("hth",dkhtsjgl.getHth());
		dkhtsjglService.update(dkhtsjgl,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款合同数据管理-通过id删除")
	@ApiOperation(value="贷款合同数据管理-通过id删除", notes="贷款合同数据管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkhtsjglService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款合同数据管理-批量删除")
	@ApiOperation(value="贷款合同数据管理-批量删除", notes="贷款合同数据管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkhtsjglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款合同数据管理-通过id查询")
	@ApiOperation(value="贷款合同数据管理-通过id查询", notes="贷款合同数据管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dkhtsjgl dkhtsjgl = dkhtsjglService.getById(id);
		return Result.ok(dkhtsjgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkhtsjgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dkhtsjgl dkhtsjgl) {
      return super.exportXls(request, dkhtsjgl, Dkhtsjgl.class, "贷款合同数据管理");
  }


	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 return super.exportTemplateXls(DkhtsjglVo.class, "贷款合同数据管理导入模板");
	 }

	 /**
	  * 通过excel导入数据
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 return super.importExcelByTemplate(jsonObject, request, response, Dkhtsjgl.class,DkhtsjglVo.class, dkhtsjglImportVerify);
	 }


  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
 /* @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, Dkhtsjgl.class);
  }*/

}
