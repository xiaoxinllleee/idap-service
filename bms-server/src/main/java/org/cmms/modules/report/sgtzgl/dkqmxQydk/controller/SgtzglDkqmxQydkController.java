package org.cmms.modules.report.sgtzgl.dkqmxQydk.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.dkqmx.entity.SgtzglDkqmxVO;
import org.cmms.modules.report.sgtzgl.dkqmx.service.ISgtzglDkqmxService;
import org.cmms.modules.report.sgtzgl.dkqmxQydk.entity.SgtzglDkqmxQydk;
import org.cmms.modules.report.sgtzgl.dkqmxQydk.entity.SgtzglDkqmxQydkVO;
import org.cmms.modules.report.sgtzgl.dkqmxQydk.service.ISgtzglDkqmxQydkService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.dkqmxQydk.verify.SgtzglDkqmxQydkImportVerify;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 贷款全明细_企业贷款
 * @Author: Penghr
 * @Date:   2022-12-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款全明细_企业贷款")
@RestController
@RequestMapping("/dkqmxQydk/sgtzglDkqmxQydk")
public class SgtzglDkqmxQydkController extends JeecgController<SgtzglDkqmxQydk, ISgtzglDkqmxQydkService> {
	@Autowired
	private ISgtzglDkqmxQydkService sgtzglDkqmxQydkService;
	@Autowired
	private SgtzglDkqmxQydkImportVerify sgtzglDkqmxQydkImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param sgtzglDkqmxQydk
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款全明细_企业贷款-分页列表查询")
	@ApiOperation(value="贷款全明细_企业贷款-分页列表查询", notes="贷款全明细_企业贷款-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgtzglDkqmxQydk sgtzglDkqmxQydk,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgtzglDkqmxQydk> queryWrapper = QueryGenerator.initQueryWrapper(sgtzglDkqmxQydk, req.getParameterMap());
		Page<SgtzglDkqmxQydk> page = new Page<SgtzglDkqmxQydk>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgtzglDkqmxQydkService.class,sgtzglDkqmxQydkService,pageNo,pageSize,queryWrapper,"fiscal_date","dkzh");
//		IPage<SgtzglDkqmxQydk> pageList = sgtzglDkqmxQydkService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgtzglDkqmxQydk
	 * @return
	 */
	@AutoLog(value = "贷款全明细_企业贷款-添加")
	@ApiOperation(value="贷款全明细_企业贷款-添加", notes="贷款全明细_企业贷款-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgtzglDkqmxQydk sgtzglDkqmxQydk) {
		String fiscalDate = sgtzglDkqmxQydk.getFiscalDate().replace("-", "").substring(0,8);
		sgtzglDkqmxQydk.setFiscalDate(fiscalDate);
		sgtzglDkqmxQydkService.save(sgtzglDkqmxQydk);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgtzglDkqmxQydk
	 * @return
	 */
	@AutoLog(value = "贷款全明细_企业贷款-编辑")
	@ApiOperation(value="贷款全明细_企业贷款-编辑", notes="贷款全明细_企业贷款-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgtzglDkqmxQydk sgtzglDkqmxQydk) {
		sgtzglDkqmxQydk.setFiscalDate(sgtzglDkqmxQydk.getFiscalDate()==null ? null: sgtzglDkqmxQydk.getFiscalDate().replace("-", "").substring(0,8));
		QueryWrapper<SgtzglDkqmxQydk> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("fiscal_date",sgtzglDkqmxQydk.getFiscalDate()).eq("dkzh",sgtzglDkqmxQydk.getDkzh());
		sgtzglDkqmxQydkService.update(sgtzglDkqmxQydk,queryWrapper);
		return Result.ok("编辑成功!");
	}
	 /**
	  * 根据日期批量删除
	  * @param fiscalDate
	  * @return
	  */
	 @AutoLog(value = "贷款全明细-批量删除")
	 @ApiOperation(value="贷款全明细-批量删除", notes="贷款全明细-批量删除")
	 @DeleteMapping(value = "/deleteByBatch")
	 public Result<?> deleteByBatch(@Param("fiscalDate") String fiscalDate) {
		 QueryWrapper<SgtzglDkqmxQydk> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("fiscal_date",fiscalDate);
		 sgtzglDkqmxQydkService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }
	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "贷款全明细_企业贷款-通过id删除")
	@ApiOperation(value="贷款全明细_企业贷款-通过id删除", notes="贷款全明细_企业贷款-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="fiscalDate",required=true) String fiscalDate,@RequestParam(name = "dkzh",required = true) String dkzh) {
		QueryWrapper<SgtzglDkqmxQydk> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("FISCAL_DATE",fiscalDate).eq("dkzh",dkzh);
		sgtzglDkqmxQydkService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款全明细_企业贷款-批量删除")
	@ApiOperation(value="贷款全明细_企业贷款-批量删除", notes="贷款全明细_企业贷款-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgtzglDkqmxQydkService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款全明细_企业贷款-通过id查询")
	@ApiOperation(value="贷款全明细_企业贷款-通过id查询", notes="贷款全明细_企业贷款-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgtzglDkqmxQydk sgtzglDkqmxQydk = sgtzglDkqmxQydkService.getById(id);
		return Result.ok(sgtzglDkqmxQydk);
	}

    /**
     * 导出excel
     *
     * @param request
     * @param sgtzglDkqmxQydk
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SgtzglDkqmxQydk sgtzglDkqmxQydk) {
      return super.exportXls(request, sgtzglDkqmxQydk, SgtzglDkqmxQydk.class, "贷款全明细_企业贷款");
    }


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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "贷款全明细导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgtzglDkqmxQydkVO.class);
		 ExportParams exportParams = new ExportParams("贷款全明细_企业贷款导入模板", "模板信息");
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
		 String fiscalDate = request.getParameter("fiscalDate");
		 log.info(fiscalDate + "----fiscalDate----");
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
			 if (sgtzglDkqmxQydkImportVerify != null) {
				 params.setVerifyHanlder(sgtzglDkqmxQydkImportVerify);
			 }
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<SgtzglDkqmxQydk> importResult = ExcelImportUtil.importExcelVerify(file, SgtzglDkqmxQydk.class,SgtzglDkqmxQydkVO.class, params);
				 List<SgtzglDkqmxQydk> list = importResult.getList();
				 List<SgtzglDkqmxQydk> qkmbList = new ArrayList<>();
				 for (SgtzglDkqmxQydk qkmb : list) {
					 qkmb.setFiscalDate(fiscalDate);
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

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    /*@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, SgtzglDkqmxQydk.class);
    }*/

}
