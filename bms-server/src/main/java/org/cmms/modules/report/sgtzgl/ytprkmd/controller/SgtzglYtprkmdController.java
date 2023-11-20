package org.cmms.modules.report.sgtzgl.ytprkmd.controller;

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
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.byyzprkmd.entity.SgtzglByyzprkmd;
import org.cmms.modules.report.sgtzgl.byyzprkmd.entity.SgtzglByyzprkmdVO;
import org.cmms.modules.report.sgtzgl.dbpgldkdjb.entity.SgztDbpgldkdjb;
import org.cmms.modules.report.sgtzgl.yhcdhpdjb.service.ISgtzglYhcdhpdjbService;
import org.cmms.modules.report.sgtzgl.ytprkmd.entity.SgtzglYtprkmd;
import org.cmms.modules.report.sgtzgl.ytprkmd.entity.SgtzglYtprkmdVO;
import org.cmms.modules.report.sgtzgl.ytprkmd.service.ISgtzglYtprkmdService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.ytprkmd.verify.YtprkmdImportVerify;
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
 * @Description: 已脱贫人口名单
 * @Author: jeecg-boot
 * @Date:   2022-10-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="已脱贫人口名单")
@RestController
@RequestMapping("/ytprkmd/sgtzglYtprkmd")
public class SgtzglYtprkmdController extends JeecgController<SgtzglYtprkmd, ISgtzglYtprkmdService> {
	@Autowired
	private ISgtzglYtprkmdService sgtzglYtprkmdService;
	@Autowired
	private YtprkmdImportVerify ytprkmdImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param sgtzglYtprkmd
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "已脱贫人口名单-分页列表查询")
	@ApiOperation(value="已脱贫人口名单-分页列表查询", notes="已脱贫人口名单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgtzglYtprkmd sgtzglYtprkmd,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgtzglYtprkmd> queryWrapper = QueryGenerator.initQueryWrapper(sgtzglYtprkmd, req.getParameterMap());
		Page<SgtzglYtprkmd> page = new Page<SgtzglYtprkmd>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgtzglYtprkmdService.class,sgtzglYtprkmdService,pageNo,pageSize,queryWrapper,"fiscal_date","zjhm");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgtzglYtprkmd
	 * @return
	 */
	@AutoLog(value = "已脱贫人口名单-添加")
	@ApiOperation(value="已脱贫人口名单-添加", notes="已脱贫人口名单-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgtzglYtprkmd sgtzglYtprkmd) {
		String fiscalDate = sgtzglYtprkmd.getFiscalDate().replace("-", "").substring(0,8);
		sgtzglYtprkmd.setFiscalDate(fiscalDate);
		sgtzglYtprkmdService.save(sgtzglYtprkmd);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgtzglYtprkmd
	 * @return
	 */
	@AutoLog(value = "已脱贫人口名单-编辑")
	@ApiOperation(value="已脱贫人口名单-编辑", notes="已脱贫人口名单-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgtzglYtprkmd sgtzglYtprkmd) {
		String fiscalDate = sgtzglYtprkmd.getFiscalDate().replace("-", "").substring(0, 8);
		sgtzglYtprkmd.setFiscalDate(fiscalDate);
		sgtzglYtprkmdService.updateById(sgtzglYtprkmd);
		return Result.ok("编辑成功!");
	}
	 /**
	  * 根据日期批量删除
	  * @param fiscalDate
	  * @return
	  */
	 @AutoLog(value = "已脱贫人口名单-批量删除")
	 @ApiOperation(value="已脱贫人口名单-批量删除", notes="已脱贫人口名单-批量删除")
	 @DeleteMapping(value = "/deleteByBatch")
	 public Result<?> deleteByBatch(@Param("fiscalDate") String fiscalDate) {
		 QueryWrapper<SgtzglYtprkmd> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("fiscal_date",fiscalDate);
		 sgtzglYtprkmdService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "已脱贫人口名单-通过id删除")
	@ApiOperation(value="已脱贫人口名单-通过id删除", notes="已脱贫人口名单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgtzglYtprkmdService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "已脱贫人口名单-批量删除")
	@ApiOperation(value="已脱贫人口名单-批量删除", notes="已脱贫人口名单-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgtzglYtprkmdService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "已脱贫人口名单-通过id查询")
	@ApiOperation(value="已脱贫人口名单-通过id查询", notes="已脱贫人口名单-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgtzglYtprkmd sgtzglYtprkmd = sgtzglYtprkmdService.getById(id);
		return Result.ok(sgtzglYtprkmd);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgtzglYtprkmd
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgtzglYtprkmd sgtzglYtprkmd) {
      return super.exportXls(request, sgtzglYtprkmd, SgtzglYtprkmd.class, "已脱贫人口名单");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "已脱贫人口名单导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgtzglYtprkmdVO.class);
		 ExportParams exportParams = new ExportParams("已脱贫人口名单表导入模板", "模板信息");
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
		 System.out.println(fiscalDate + "----sjrq----");
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
			 if (ytprkmdImportVerify != null) {
				 params.setVerifyHanlder(ytprkmdImportVerify);
			 }
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<SgtzglYtprkmd> importResult = ExcelImportUtil.importExcelVerify(file, SgtzglYtprkmd.class,SgtzglYtprkmdVO.class, params);
				 List<SgtzglYtprkmd> list = importResult.getList();
				 List<SgtzglYtprkmd> qkmbList = new ArrayList<>();
				 for (SgtzglYtprkmd qkmb : list) {
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

}
