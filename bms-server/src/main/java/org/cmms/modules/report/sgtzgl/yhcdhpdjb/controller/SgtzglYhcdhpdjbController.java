package org.cmms.modules.report.sgtzgl.yhcdhpdjb.controller;

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
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.dkhsdjb.entity.SgtzglDkhsdjb;
import org.cmms.modules.report.sgtzgl.dkhsdjb.entity.SgtzglDkhsdjbVO;
import org.cmms.modules.report.sgtzgl.dkqmx.entity.SgtzglDkqmx;
import org.cmms.modules.report.sgtzgl.xdhtlprxxcx.service.ISgtzglXdhtlprxxcxService;
import org.cmms.modules.report.sgtzgl.yhcdhpdjb.entity.SgtzglYhcdhpdjb;
import org.cmms.modules.report.sgtzgl.yhcdhpdjb.entity.SgtzglYhcdhpdjbVO;
import org.cmms.modules.report.sgtzgl.yhcdhpdjb.service.ISgtzglYhcdhpdjbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.yhcdhpdjb.verify.YhcdhpdjbImportVerify;
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
 * @Description: 银行承兑汇票登记薄
 * @Author: jeecg-boot
 * @Date:   2022-09-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="银行承兑汇票登记薄")
@RestController
@RequestMapping("/yhcdhpdjb/sgtzglYhcdhpdjb")
public class SgtzglYhcdhpdjbController extends JeecgController<SgtzglYhcdhpdjb, ISgtzglYhcdhpdjbService> {
	@Autowired
	private ISgtzglYhcdhpdjbService sgtzglYhcdhpdjbService;
	@Autowired
	private YhcdhpdjbImportVerify yhcdhpdjbImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param sgtzglYhcdhpdjb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "银行承兑汇票登记薄-分页列表查询")
	@ApiOperation(value="银行承兑汇票登记薄-分页列表查询", notes="银行承兑汇票登记薄-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgtzglYhcdhpdjb sgtzglYhcdhpdjb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgtzglYhcdhpdjb> queryWrapper = QueryGenerator.initQueryWrapper(sgtzglYhcdhpdjb, req.getParameterMap());
		Page<SgtzglYhcdhpdjb> page = new Page<SgtzglYhcdhpdjb>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgtzglYhcdhpdjbService.class,sgtzglYhcdhpdjbService,pageNo,pageSize,queryWrapper,"fiscal_date","xh");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgtzglYhcdhpdjb
	 * @return
	 */
	@AutoLog(value = "银行承兑汇票登记薄-添加")
	@ApiOperation(value="银行承兑汇票登记薄-添加", notes="银行承兑汇票登记薄-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgtzglYhcdhpdjb sgtzglYhcdhpdjb) {
		String fiscalDate = sgtzglYhcdhpdjb.getFiscalDate().replace("-", "").substring(0,8);
		String qfrq = sgtzglYhcdhpdjb.getQfrq().replace("-", "").substring(0,8);
		String dqrq = sgtzglYhcdhpdjb.getDqrq().replace("-", "").substring(0,8);
		String zxrq = sgtzglYhcdhpdjb.getWyzxrq().replace("-", "").substring(0,8);
		String dfrq = sgtzglYhcdhpdjb.getDfrq().replace("-", "").substring(0,8);
		sgtzglYhcdhpdjb.setFiscalDate(fiscalDate);
		sgtzglYhcdhpdjb.setQfrq(qfrq);
		sgtzglYhcdhpdjb.setDqrq(dqrq);
		sgtzglYhcdhpdjb.setWyzxrq(zxrq);
		sgtzglYhcdhpdjb.setDfrq(dfrq);
		sgtzglYhcdhpdjb.setCreateTime(new Date());
		sgtzglYhcdhpdjbService.save(sgtzglYhcdhpdjb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgtzglYhcdhpdjb
	 * @return
	 */
	@AutoLog(value = "银行承兑汇票登记薄-编辑")
	@ApiOperation(value="银行承兑汇票登记薄-编辑", notes="银行承兑汇票登记薄-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgtzglYhcdhpdjb sgtzglYhcdhpdjb) {
		String fiscalDate = sgtzglYhcdhpdjb.getFiscalDate().replace("-", "").substring(0,8);
		String qfrq = sgtzglYhcdhpdjb.getQfrq().replace("-", "").substring(0,8);
		String dqrq = sgtzglYhcdhpdjb.getDqrq().replace("-", "").substring(0,8);
		String zxrq = sgtzglYhcdhpdjb.getWyzxrq().replace("-", "").substring(0,8);
		String dfrq = sgtzglYhcdhpdjb.getDfrq().replace("-", "").substring(0,8);
		sgtzglYhcdhpdjb.setFiscalDate(fiscalDate);
		sgtzglYhcdhpdjb.setQfrq(qfrq);
		sgtzglYhcdhpdjb.setDqrq(dqrq);
		sgtzglYhcdhpdjb.setWyzxrq(zxrq);
		sgtzglYhcdhpdjb.setDfrq(dfrq);
		sgtzglYhcdhpdjbService.updateById(sgtzglYhcdhpdjb);
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
		 QueryWrapper<SgtzglYhcdhpdjb> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("fiscal_date",fiscalDate);
		 sgtzglYhcdhpdjbService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "银行承兑汇票登记薄-通过id删除")
	@ApiOperation(value="银行承兑汇票登记薄-通过id删除", notes="银行承兑汇票登记薄-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgtzglYhcdhpdjbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "银行承兑汇票登记薄-批量删除")
	@ApiOperation(value="银行承兑汇票登记薄-批量删除", notes="银行承兑汇票登记薄-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgtzglYhcdhpdjbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "银行承兑汇票登记薄-通过id查询")
	@ApiOperation(value="银行承兑汇票登记薄-通过id查询", notes="银行承兑汇票登记薄-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgtzglYhcdhpdjb sgtzglYhcdhpdjb = sgtzglYhcdhpdjbService.getById(id);
		return Result.ok(sgtzglYhcdhpdjb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgtzglYhcdhpdjb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgtzglYhcdhpdjb sgtzglYhcdhpdjb) {
      return super.exportXls(request, sgtzglYhcdhpdjb, SgtzglYhcdhpdjb.class, "银行承兑汇票登记薄");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "银行承兑汇票登记薄导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgtzglYhcdhpdjbVO.class);
		 ExportParams exportParams = new ExportParams("银行承兑汇票登记薄导入模板", "模板信息");
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
		 //return super.importExcelByTemplate(jsonObject, request, response, SgtzglYhcdhpdjb.class,yhcdhpdjbImportVerify);
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
			 if (yhcdhpdjbImportVerify != null) {
				 params.setVerifyHanlder(yhcdhpdjbImportVerify);
			 }
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<SgtzglYhcdhpdjb> importResult = ExcelImportUtil.importExcelVerify(file, SgtzglYhcdhpdjb.class,SgtzglYhcdhpdjbVO.class, params);
				 List<SgtzglYhcdhpdjb> list = importResult.getList();
				 List<SgtzglYhcdhpdjb> qkmbList = new ArrayList<>();
				 for (SgtzglYhcdhpdjb qkmb : list) {
					 Boolean con1= StringUtils.isBlank(qkmb.getXh());
					 Boolean con2= StringUtils.isNotBlank(qkmb.getXh()) && (qkmb.getXh().contains("合计") || qkmb.getXh().contains("条数"));
					 if (con1 || con2 ) {
						 continue;
					 }
					 qkmb.setFiscalDate(fiscalDate);
					 qkmb.setCreateTime(new Date());
					 qkmbList.add(qkmb);
				 }
				 service.saveBatch(qkmbList);
				 obj.put("filePath", filePath);
				 fos = new FileOutputStream(baseFilePath);
				 importResult.getWorkbook().write(fos);
				 fos.flush();
				 fos.close();
				 return Result.ok("文件导入完成！成功导入数据行数:" + qkmbList.size(), obj);
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
