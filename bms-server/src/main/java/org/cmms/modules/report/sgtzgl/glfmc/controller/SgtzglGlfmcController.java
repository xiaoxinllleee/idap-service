package org.cmms.modules.report.sgtzgl.glfmc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.bhjymxb.entity.SgtzglBhjymxb;
import org.cmms.modules.report.sgtzgl.bhjymxb.entity.SgtzglBhjymxbVO;
import org.cmms.modules.report.sgtzgl.dkqmx.entity.SgtzglDkqmx;
import org.cmms.modules.report.sgtzgl.dkzqdjb.service.ISgtzglDkzqdjbService;
import org.cmms.modules.report.sgtzgl.glfmc.entity.SgtzglGlfmc;
import org.cmms.modules.report.sgtzgl.glfmc.entity.SgtzglGlfmcVO;
import org.cmms.modules.report.sgtzgl.glfmc.service.ISgtzglGlfmcService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.glfmc.verify.GlfmcImportVerify;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
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
 * @Description: 关联方名册
 * @Author: jeecg-boot
 * @Date:   2022-08-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="关联方名册")
@RestController
@RequestMapping("/glfmc/sgtzglGlfmc")
public class SgtzglGlfmcController extends JeecgController<SgtzglGlfmc, ISgtzglGlfmcService> {
	@Autowired
	private ISgtzglGlfmcService sgtzglGlfmcService;
	 @Autowired
	 private GlfmcImportVerify glfmcImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param sgtzglGlfmc
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "关联方名册-分页列表查询")
	@ApiOperation(value="关联方名册-分页列表查询", notes="关联方名册-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgtzglGlfmc sgtzglGlfmc,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgtzglGlfmc> queryWrapper = QueryGenerator.initQueryWrapper(sgtzglGlfmc, req.getParameterMap());
		Page<SgtzglGlfmc> page = new Page<SgtzglGlfmc>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgtzglGlfmcService.class,sgtzglGlfmcService,pageNo,pageSize,queryWrapper,"sjrq","glfzjhm");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgtzglGlfmc
	 * @return
	 */
	@AutoLog(value = "关联方名册-添加")
	@ApiOperation(value="关联方名册-添加", notes="关联方名册-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgtzglGlfmc sgtzglGlfmc) {
		String sjrq = sgtzglGlfmc.getSjrq().replace("-", "").substring(0,8);
		String glrq = sgtzglGlfmc.getGlrq().replace("-", "").substring(0,8);
		sgtzglGlfmc.setSjrq(sjrq);
		sgtzglGlfmc.setGlrq(glrq);
		sgtzglGlfmc.setId(UUIDGenerator.generate());
		sgtzglGlfmc.setCreateTime(new Date());
		sgtzglGlfmcService.save(sgtzglGlfmc);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgtzglGlfmc
	 * @return
	 */
	@AutoLog(value = "关联方名册-编辑")
	@ApiOperation(value="关联方名册-编辑", notes="关联方名册-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgtzglGlfmc sgtzglGlfmc) {
		String sjrq = sgtzglGlfmc.getSjrq().replace("-", "").substring(0,8);
		String glrq = sgtzglGlfmc.getGlrq().replace("-", "").substring(0,8);
		sgtzglGlfmc.setSjrq(sjrq);
		sgtzglGlfmc.setGlrq(glrq);
		sgtzglGlfmcService.updateById(sgtzglGlfmc);
		return Result.ok("编辑成功!");
	}
	 /**
	  * 根据日期批量删除
	  * @param fiscalDate
	  * @return
	  */
	 @AutoLog(value = "关联方名册-批量删除")
	 @ApiOperation(value="关联方名册-批量删除", notes="关联方名册-批量删除")
	 @DeleteMapping(value = "/deleteByBatch")
	 public Result<?> deleteByBatch(@Param("fiscalDate") String fiscalDate) {
		 QueryWrapper<SgtzglGlfmc> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("fiscal_date",fiscalDate);
		 sgtzglGlfmcService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "关联方名册-通过id删除")
	@ApiOperation(value="关联方名册-通过id删除", notes="关联方名册-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgtzglGlfmcService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "关联方名册-批量删除")
	@ApiOperation(value="关联方名册-批量删除", notes="关联方名册-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgtzglGlfmcService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "关联方名册-通过id查询")
	@ApiOperation(value="关联方名册-通过id查询", notes="关联方名册-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgtzglGlfmc sgtzglGlfmc = sgtzglGlfmcService.getById(id);
		return Result.ok(sgtzglGlfmc);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgtzglGlfmc
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgtzglGlfmc sgtzglGlfmc) {
      return super.exportXls(request, sgtzglGlfmc, SgtzglGlfmc.class, "关联方名册");
  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
/*  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, SgtzglGlfmc.class);
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "关联方名册导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgtzglGlfmcVO.class);
		 ExportParams exportParams = new ExportParams("关联方名册导入模板", "模板信息");
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
		 String sjrq = request.getParameter("sjrq");
		 System.out.println(sjrq + "----sjrq----");
		 String filePaths = jsonObject.getString("filePaths");
		 if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
			 return Result.error("请先上传文件！");
		 }
		 String[] filePathList = filePaths.split(",");
		 JSONObject obj = new JSONObject();
		 for (String filePath : filePathList) {
			 String baseFilePath = uploadpath + File.separator + filePath;
//          MultipartFile file = entity.getValue();// 获取上传文件对象
			 File file = new File(baseFilePath);
			 ImportParams params = new ImportParams();
			 params.setTitleRows(1);
			 params.setHeadRows(1);
			 params.setNeedSave(false);
			 if (glfmcImportVerify != null) {
				 params.setVerifyHanlder(glfmcImportVerify);
			 }
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<SgtzglGlfmc> importResult = ExcelImportUtil.importExcelVerify(file, SgtzglGlfmc.class,SgtzglGlfmcVO.class, params);
				 List<SgtzglGlfmc> list = importResult.getList();
				 List<SgtzglGlfmc> qkmbList = new ArrayList<>();
				 for (SgtzglGlfmc qkmb : list) {
					 qkmb.setSjrq(sjrq);
					 qkmb.setId(UUIDGenerator.generate());
					 qkmb.setCreateTime(new Date());
					 qkmbList.add(qkmb);
				 }
				 service.saveBatch(qkmbList);
				 obj.put("filePath", filePath);
				 fos = new FileOutputStream(baseFilePath);
				 importResult.getWorkbook().write(fos);
				 fos.flush();
				 fos.close();
				 return Result.ok("文件导入完成！成功导入数据行数:" + list.size(), obj);
			 } catch (Exception e) {
				 log.error(e.getMessage(),e);
				 return Result.error("文件导入失败:"+e.getMessage());
			 } finally {
				 IoUtil.close(fos);
			 }
		 }
		 return Result.ok("文件导入失败！");
	 }

}
