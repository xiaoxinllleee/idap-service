package org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.entity.Khjljcsjsz;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.entity.KhjljcsjszVO;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.service.IKhjljcsjszService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.verify.KhjljcsjszImportVerify;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zhdjgl.entity.ZhdjglVO;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasStaffPostVo;
import org.cmms.modules.hr.zzgl.gwxxgl.service.IHrBasPostService;
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
 * @Description: 客户经理基础数据设置
 * @Author: jeecg-boot
 * @Date:   2021-09-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户经理基础数据设置")
@RestController
@RequestMapping("/khjljcsjsz/khjljcsjsz")
public class KhjljcsjszController extends JeecgController<Khjljcsjsz, IKhjljcsjszService> {
	 @Autowired
	 private IKhjljcsjszService khjljcsjszService;
	 @Autowired
	 private IHrBasPostService hrBasPostService;
	 @Autowired
	 private KhjljcsjszImportVerify khjljcsjszImportVerify;

	 /**
	  * 分页列表查询
	  *
	  * @param khjljcsjsz
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "客户经理基础数据设置-分页列表查询")
	 @ApiOperation(value = "客户经理基础数据设置-分页列表查询", notes = "客户经理基础数据设置-分页列表查询")
	 @GetMapping(value = "/list")
	 public Result<?> queryPageList(Khjljcsjsz khjljcsjsz,
									@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
									@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
									HttpServletRequest req) {
		 QueryWrapper<Khjljcsjsz> queryWrapper = QueryGenerator.initQueryWrapper(khjljcsjsz, req.getParameterMap());
		 Page<Khjljcsjsz> page = new Page<Khjljcsjsz>(pageNo, pageSize);
		 IPage<Khjljcsjsz> pageList = khjljcsjszService.page(page, queryWrapper);
		 return Result.ok(pageList);
	 }

	 /**
	  * 添加
	  *
	  * @param khjljcsjsz
	  * @return
	  */
	 @AutoLog(value = "客户经理基础数据设置-添加")
	 @ApiOperation(value = "客户经理基础数据设置-添加", notes = "客户经理基础数据设置-添加")
	 @PostMapping(value = "/add")
	 public Result<?> add(@RequestBody Khjljcsjsz khjljcsjsz) {
		 System.out.println(khjljcsjsz);
		 QueryWrapper<Khjljcsjsz> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("pdzq", khjljcsjsz.getPdzq());
		 queryWrapper.eq("pdrq", khjljcsjsz.getPdrq());
		 queryWrapper.eq("zzbz", khjljcsjsz.getZzbz());
		 queryWrapper.eq("gwbz", khjljcsjsz.getGwbz());
		 queryWrapper.eq("yggh", khjljcsjsz.getYggh());
		 Khjljcsjsz check = khjljcsjszService.getOne(queryWrapper);
		 if (check != null) {
			 return Result.error("该记录已存在，请选择记录执行修改！");
		 }
		 khjljcsjsz.setLrbz(1);
		 khjljcsjsz.setLrr(getUsername());
		 khjljcsjsz.setLrsj(new Date());
		 khjljcsjszService.save(khjljcsjsz);
		 return Result.ok("添加成功！");
	 }

	 /**
	  * 编辑
	  *
	  * @param khjljcsjsz
	  * @return
	  */
	 @AutoLog(value = "客户经理基础数据设置-编辑")
	 @ApiOperation(value = "客户经理基础数据设置-编辑", notes = "客户经理基础数据设置-编辑")
	 @PutMapping(value = "/edit")
	 public Result<?> edit(@RequestBody Khjljcsjsz khjljcsjsz) {
		 QueryWrapper<Khjljcsjsz> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("pdzq", khjljcsjsz.getPdzq());
		 queryWrapper.eq("pdrq", khjljcsjsz.getPdrq());
		 queryWrapper.eq("zzbz", khjljcsjsz.getZzbz());
		 queryWrapper.eq("gwbz", khjljcsjsz.getGwbz());
		 queryWrapper.eq("yggh", khjljcsjsz.getYggh());
		 khjljcsjszService.update(khjljcsjsz, queryWrapper);
		 return Result.ok("编辑成功!");
	 }

	 /**
	  * 通过id删除
	  *
	  * @param
	  * @return
	  */
	 @AutoLog(value = "客户经理基础数据设置-通过id删除")
	 @ApiOperation(value = "客户经理基础数据设置-通过id删除", notes = "客户经理基础数据设置-通过id删除")
	 @DeleteMapping(value = "/delete")
	 public Result<?> delete(@Param("pdzq") String pdzq, @Param("pdrq") String pdrq,
							 @Param("zzbz") String zzbz, @Param("gwbz") String gwbz, @Param("yggh") String yggh) {
		 Date parse = DateUtil.parse(pdrq);
		 QueryWrapper<Khjljcsjsz> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("pdzq", pdzq);
		 queryWrapper.eq("pdrq", parse);
		 queryWrapper.eq("zzbz", zzbz);
		 queryWrapper.eq("gwbz", gwbz);
		 queryWrapper.eq("yggh", yggh);
		 khjljcsjszService.remove(queryWrapper);
		 return Result.ok("删除成功!");
	 }

	 /**
	  * 获取认领列表
	  *
	  * @param jsonObject
	  * @return
	  */
	 @AutoLog(value = "认领列表")
	 @ApiOperation(value = "认领", notes = "待分配存款帐号管理")
	 @PostMapping(value = "/getListClaim")
	 public Result<?> getListClaim(@RequestBody JSONObject jsonObject) {
		 System.out.println(jsonObject);
		 String ywjgdm = jsonObject.getString("zzbz");
		 String khjlbz = jsonObject.getString("khjlbz");
		 String rglx = jsonObject.getString("rglx");
		 String gwbz = jsonObject.getString("gwbz");
		 String yggh = jsonObject.getString("yggh");
		 List<HrBasStaffPostVo> list = hrBasPostService.getListClaim(ywjgdm, rglx, gwbz, khjlbz, yggh);
		 return Result.ok(list);
	 }


	 /**
	  * 批量删除
	  *
	  * @param ids
	  * @return
	  */
	 @AutoLog(value = "客户经理基础数据设置-批量删除")
	 @ApiOperation(value = "客户经理基础数据设置-批量删除", notes = "客户经理基础数据设置-批量删除")
	 @DeleteMapping(value = "/deleteBatch")
	 public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
		 this.khjljcsjszService.removeByIds(Arrays.asList(ids.split(",")));
		 return Result.ok("批量删除成功！");
	 }

	 /**
	  * 通过id查询
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "客户经理基础数据设置-通过id查询")
	 @ApiOperation(value = "客户经理基础数据设置-通过id查询", notes = "客户经理基础数据设置-通过id查询")
	 @GetMapping(value = "/queryById")
	 public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
		 Khjljcsjsz khjljcsjsz = khjljcsjszService.getById(id);
		 return Result.ok(khjljcsjsz);
	 }

	 /**
	  * 导出excel
	  *
	  * @param request
	  * @param khjljcsjsz
	  */
	 @RequestMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, Khjljcsjsz khjljcsjsz) {
		 return super.exportXls(request, khjljcsjsz, Khjljcsjsz.class, "客户经理基础数据设置");
	 }

	 /**
	  * 通过excel导入数据
	  *
	  * @param request
	  * @param response
	  * @return
	  */
  /*@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, Khjljcsjsz.class);
  }*/

	 /**
	  * 导入模板
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "客户经理基础数据设置导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, KhjljcsjszVO.class);
		 ExportParams exportParams = new ExportParams("客户经理基础数据设置导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
	 }

	 /**
	  * 通过excel导入数据
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		return super.importExcelByTemplate(jsonObject, request, response, Khjljcsjsz.class,KhjljcsjszVO.class,khjljcsjszImportVerify);
	 }
 }
