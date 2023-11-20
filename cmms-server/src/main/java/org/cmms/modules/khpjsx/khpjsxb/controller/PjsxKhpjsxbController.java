package org.cmms.modules.khpjsx.khpjsxb.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.JxlsConstants;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.FileUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khpjsx.khpjsxb.entity.PjsxKhpjsxb;
import org.cmms.modules.khpjsx.khpjsxb.entity.PjsxPjjgmxb;
import org.cmms.modules.khpjsx.khpjsxb.entity.Pjsx_sjxdata;
import org.cmms.modules.khpjsx.khpjsxb.service.IPjsxKhpjsxbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khpjsx.khpjsxb.service.IPjsxPjjgmxbService;
import org.cmms.modules.khpjsx.khpjsxb.service.IPjsx_sjxdataService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 客户评级授信表
 * @Author: jeecg-boot
 * @Date:   2020-01-13
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户评级授信表")
@RestController
@RequestMapping("/khpjsx.khpjsxb/pjsxKhpjsxb")
public class PjsxKhpjsxbController extends JeecgController<PjsxKhpjsxb, IPjsxKhpjsxbService> {
	@Autowired
	private IPjsxKhpjsxbService pjsxKhpjsxbService;

	 @Autowired
     private IPjsxPjjgmxbService pjsxPjjgmxbService;

     @Autowired
     private IPjsx_sjxdataService iPjsx_sjxdataService;
	/**
	 * 分页列表查询
	 *
	 * @param pjsxKhpjsxb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户评级授信表-分页列表查询")
	@ApiOperation(value="客户评级授信表-分页列表查询", notes="客户评级授信表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PjsxKhpjsxb pjsxKhpjsxb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PjsxKhpjsxb> queryWrapper = QueryGenerator.initQueryWrapper(pjsxKhpjsxb, req.getParameterMap());
		Page<PjsxKhpjsxb> page = new Page<PjsxKhpjsxb>(pageNo, pageSize);
		IPage<PjsxKhpjsxb> pageList = pjsxKhpjsxbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param pjsxKhpjsxb
	 * @return
	 */
	@AutoLog(value = "客户评级授信表-添加")
	@ApiOperation(value="客户评级授信表-添加", notes="客户评级授信表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PjsxKhpjsxb pjsxKhpjsxb) {
		pjsxKhpjsxbService.save(pjsxKhpjsxb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param pjsxKhpjsxb
	 * @return
	 */
	@AutoLog(value = "客户评级授信表-编辑")
	@ApiOperation(value="客户评级授信表-编辑", notes="客户评级授信表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PjsxKhpjsxb pjsxKhpjsxb) {
		pjsxKhpjsxbService.updateById(pjsxKhpjsxb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户评级授信表-通过id删除")
	@ApiOperation(value="客户评级授信表-通过id删除", notes="客户评级授信表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		pjsxKhpjsxbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户评级授信表-批量删除")
	@ApiOperation(value="客户评级授信表-批量删除", notes="客户评级授信表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pjsxKhpjsxbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户评级授信表-通过id查询")
	@ApiOperation(value="客户评级授信表-通过id查询", notes="客户评级授信表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PjsxKhpjsxb pjsxKhpjsxb = pjsxKhpjsxbService.getById(id);
		return Result.ok(pjsxKhpjsxb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param pjsxKhpjsxb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, PjsxKhpjsxb pjsxKhpjsxb) {
      return super.exportXls(request, pjsxKhpjsxb, PjsxKhpjsxb.class, "客户评级授信表");
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
      return super.importExcel(request, response, PjsxKhpjsxb.class);
  }

	 /**
	  * 查询评级明细信息
	  * @param param
	  * @return
	  */
	 @ApiOperation(value="查询评级明细信息", notes="查询评级明细信息")
	 @RequestMapping(value = "/pymx",method = RequestMethod.PUT)
	 public Result<?> a(@RequestBody JSONObject param) {
		 Result<List<Pjsx_sjxdata>> result = new Result<List<Pjsx_sjxdata>>();
		 List<Pjsx_sjxdata> pjsxKhpjsxbList = new ArrayList<>();
		 try {
             Pjsx_sjxdata kg = new Pjsx_sjxdata();
             kg.setTjrq(param.getDate("tjrq"));
			 kg.setKhlx(param.getString("khlx"));
			 kg.setZjhm(param.getString("zjhm"));
			 Map<String, String[]> map = new HashMap<>();
			 QueryWrapper<Pjsx_sjxdata> queryWrapper1 = QueryGenerator.initQueryWrapper(kg, map);
			 pjsxKhpjsxbList = iPjsx_sjxdataService.list(queryWrapper1);
			 if(pjsxKhpjsxbList.size()!=0) {
				 result.setResult(pjsxKhpjsxbList);
			 }
			 result.success("操作成功！");
		 } catch (Exception e) {
			 log.error(e.getMessage(),e);
			 result.error500("操作失败");
		 }
		return result;
	 }
	 @RequestMapping(value = "/extractPjsx" , method = RequestMethod.PUT)
	 public Result<?> extractPjsx(@RequestBody JSONObject  param) {
		 Result<List<PjsxKhpjsxb>> result =new Result<>();
		 try {
			 String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
			 pjsxKhpjsxbService.extractPjsx(param.getString("khlx"),date);
			 result.setSuccess(true);
			 return  result;
		 } catch (Exception e) {
			 System.out.println(e);
			 log.error("提取失败",e.getMessage());
			 result.setMessage(e.getMessage());
			 result.setSuccess(false);
		 }
		 return result;
	 }
	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @Autowired
	 private Environment environment;
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(Pjsx_sjxdata pjsxPjjgmxb, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		 // Step.1 组装查询条件
		 QueryWrapper<Pjsx_sjxdata>queryWrapper = QueryGenerator.initQueryWrapper(pjsxPjjgmxb, request.getParameterMap());
		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<Pjsx_sjxdata> pageList = iPjsx_sjxdataService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "评级结果明细列表");
		 mv.addObject(NormalExcelConstants.CLASS, Pjsx_sjxdata.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("评级结果明细列表数据", "导出人:Jeecg", "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		 return mv;
	 }

}
