package org.cmms.modules.khxxgl.khgs.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khxxgl.dfpkh.entity.Khwgfpls;
import org.cmms.modules.khxxgl.khgs.entity.khsskhjl;
import org.cmms.modules.khxxgl.khgs.service.IkhsskhjlService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khxxgl.khgs.verify.KhsskhjlImportVerify;
import org.cmms.modules.khxxgl.khgs.vo.KhsskhjlVO;
import org.cmms.modules.khxxgl.khjbzl.entity.Khjbzl;
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
 * @Description: 客户归属客户经理
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户归属客户经理")
@RestController
@RequestMapping("/khgs/khsskhjl")
public class khsskhjlController extends JeecgController<khsskhjl, IkhsskhjlService> {
	@Autowired
	private IkhsskhjlService khsskhjlService;
	@Autowired
	private KhsskhjlImportVerify khsskhjlImportVerify;
	/**
	 * 分页列表查询
	 *
	 * @param khsskhjl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户归属客户经理-分页列表查询")
	@ApiOperation(value="客户归属客户经理-分页列表查询", notes="客户归属客户经理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(khsskhjl khsskhjl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<khsskhjl> queryWrapper = QueryGenerator.initQueryWrapper(khsskhjl, req.getParameterMap());
		Page<khsskhjl> page = new Page<khsskhjl>(pageNo, pageSize);
		IPage<khsskhjl> pageList = khsskhjlService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param khsskhjl
	 * @return
	 */
	@AutoLog(value = "客户归属客户经理-添加")
	@ApiOperation(value="客户归属客户经理-添加", notes="客户归属客户经理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody khsskhjl khsskhjl) {
		khsskhjlService.save(khsskhjl);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param khsskhjl
	 * @return
	 */
	@AutoLog(value = "客户归属客户经理-编辑")
	@ApiOperation(value="客户归属客户经理-编辑", notes="客户归属客户经理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody khsskhjl khsskhjl) {
		khsskhjlService.updateById(khsskhjl);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户归属客户经理-通过id删除")
	@ApiOperation(value="客户归属客户经理-通过id删除", notes="客户归属客户经理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khsskhjlService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户归属客户经理-批量删除")
	@ApiOperation(value="客户归属客户经理-批量删除", notes="客户归属客户经理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khsskhjlService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}


	 @AutoLog(value = "客户网格分配")
	 @ApiOperation(value="客户网格分配", notes="客户网格分配")
	 @PutMapping(value = "/khjlfp")
	 public Result<?> wgfpAction(@RequestBody JSONObject para) {
		 try {
			 List<Map<String,String>> rows = (List<Map<String,String>> )para.get("rows");
			 String sslx =para.get("sslx").toString();
			 String yggh =para.get("yggh").toString();
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 for (int i = 0; i < rows.size(); i++) {
				 System.out.println(rows.get(i).get("zjhm"));
				 QueryWrapper queryWrapper =new QueryWrapper<>();
				 queryWrapper.eq("zjhm",rows.get(i).get("zjhm"));
				 queryWrapper.eq("sskhjl",yggh);
				 queryWrapper.eq("sslx",sslx);
				 khsskhjlService.remove(queryWrapper);
				 khsskhjl khsskhjl =new khsskhjl();
				 khsskhjl.setDabh(rows.get(i).get("dabh"));
				 khsskhjl.setZjhm(rows.get(i).get("zjhm"));
				 khsskhjl.setKhmc(rows.get(i).get("khmc"));
				 khsskhjl.setSslx(sslx);
				 khsskhjl.setSskhjl(yggh);
				 khsskhjl.setCreateBy(loginUser.getUsername());
				 khsskhjl.setCreateTime(new Date());
				 khsskhjlService.save(khsskhjl);
			 }
			 //dfpWgkhService.extract();
			 return Result.ok("分配成功");
		 } catch (Exception e) {
			 log.error(e.getMessage(),e);
			 return Result.error("分配失败");
		 }
	 }

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户归属客户经理-通过id查询")
	@ApiOperation(value="客户归属客户经理-通过id查询", notes="客户归属客户经理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		khsskhjl khsskhjl = khsskhjlService.getById(id);
		return Result.ok(khsskhjl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khsskhjl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, khsskhjl khsskhjl) {
      return super.exportXls(request, khsskhjl, khsskhjl.class, "客户归属客户经理");
  }

	 /**
	  * 导出模板Excel
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "客户归属管理导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, KhsskhjlVO.class);
		 ExportParams exportParams = new ExportParams("客户归属管理导入模板", "客户归属管理");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return mv;
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
		 return super.importExcelByTemplate(jsonObject, request, response, khsskhjl.class, KhsskhjlVO.class, khsskhjlImportVerify);
	 }
}
