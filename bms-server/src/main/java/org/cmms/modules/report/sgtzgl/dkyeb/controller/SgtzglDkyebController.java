package org.cmms.modules.report.sgtzgl.dkyeb.controller;

import java.io.*;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.dkxttzdjb.service.ISgtzglDkxttzdjbService;
import org.cmms.modules.report.sgtzgl.dkyeb.entity.SgtzglDkyeb;
import org.cmms.modules.report.sgtzgl.dkyeb.entity.SgtzglDkyebVO;
import org.cmms.modules.report.sgtzgl.dkyeb.service.ISgtzglDkyebService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.dkyeb.verify.DkyebImportVerify;
import org.cmms.modules.report.sgtzgl.txdjb.entity.SgtzglTxdjb;
import org.cmms.modules.report.sgtzgl.txdjb.entity.SgtzglTxdjbVO;
import org.cmms.modules.tjbb.tjfz.sgtzdr.syb.entity.Syb;
import org.cmms.modules.tjbb.tjfz.sgtzdr.syb.vo.SybImportVO;
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
 * @Description: 贷款余额表
 * @Author: jeecg-boot
 * @Date:   2022-09-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款余额表")
@RestController
@RequestMapping("/dkyeb/sgtzglDkyeb")
public class SgtzglDkyebController extends JeecgController<SgtzglDkyeb, ISgtzglDkyebService> {
	@Autowired
	private ISgtzglDkyebService sgtzglDkyebService;
	@Autowired
	private DkyebImportVerify dkyebImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param sgtzglDkyeb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款余额表-分页列表查询")
	@ApiOperation(value="贷款余额表-分页列表查询", notes="贷款余额表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgtzglDkyeb sgtzglDkyeb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgtzglDkyeb> queryWrapper = QueryGenerator.initQueryWrapper(sgtzglDkyeb, req.getParameterMap());
		Page<SgtzglDkyeb> page = new Page<SgtzglDkyeb>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgtzglDkyebService.class,sgtzglDkyebService,pageNo,pageSize,queryWrapper,"sjrq");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgtzglDkyeb
	 * @return
	 */
	@AutoLog(value = "贷款余额表-添加")
	@ApiOperation(value="贷款余额表-添加", notes="贷款余额表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgtzglDkyeb sgtzglDkyeb) {
		String jkrq = sgtzglDkyeb.getJkrq().replace("-", "").substring(0,8);
		String dqrq = sgtzglDkyeb.getDqrq().replace("-", "").substring(0,8);
		String sjrq = sgtzglDkyeb.getSjrq().replace("-", "").substring(0,8);
		sgtzglDkyeb.setJkrq(jkrq);
		sgtzglDkyeb.setDqrq(dqrq);
		sgtzglDkyeb.setSjrq(sjrq);
		sgtzglDkyebService.save(sgtzglDkyeb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgtzglDkyeb
	 * @return
	 */
	@AutoLog(value = "贷款余额表-编辑")
	@ApiOperation(value="贷款余额表-编辑", notes="贷款余额表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgtzglDkyeb sgtzglDkyeb) {
		String jkrq = sgtzglDkyeb.getJkrq().replace("-", "").substring(0,8);
		String dqrq = sgtzglDkyeb.getDqrq().replace("-", "").substring(0,8);
		String sjrq = sgtzglDkyeb.getSjrq().replace("-", "").substring(0,8);
		sgtzglDkyeb.setJkrq(jkrq);
		sgtzglDkyeb.setDqrq(dqrq);
		sgtzglDkyeb.setSjrq(sjrq);
		sgtzglDkyebService.updateById(sgtzglDkyeb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款余额表-通过id删除")
	@ApiOperation(value="贷款余额表-通过id删除", notes="贷款余额表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgtzglDkyebService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款余额表-批量删除")
	@ApiOperation(value="贷款余额表-批量删除", notes="贷款余额表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgtzglDkyebService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款余额表-通过id查询")
	@ApiOperation(value="贷款余额表-通过id查询", notes="贷款余额表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgtzglDkyeb sgtzglDkyeb = sgtzglDkyebService.getById(id);
		return Result.ok(sgtzglDkyeb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgtzglDkyeb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgtzglDkyeb sgtzglDkyeb) {
      return super.exportXls(request, sgtzglDkyeb, SgtzglDkyeb.class, "贷款余额表");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "贷款余额表导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgtzglDkyebVO.class);
		 ExportParams exportParams = new ExportParams("贷款余额表导入模板", "模板信息");
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
		 //return super.importExcel(request, response, Syb.class);
		 String fiscalDate = request.getParameter("fiscalDate");
		 String filePaths = jsonObject.getString("filePaths");
		 if (StringUtils.isEmpty(filePaths)) {
			 return Result.error("请先上传文件！");
		 }
		 String[] filePathList = filePaths.split(",");
		 JSONObject obj = new JSONObject();
		 for (String filePath : filePathList) {
			 String baseFilePath = uploadpath + File.separator + filePath;
			 File file = new File(baseFilePath);
			 ImportParams params = new ImportParams();
			 params.setTitleRows(1);
			 params.setHeadRows(1);
			 params.setNeedSave(false);
			 if (dkyebImportVerify != null) {
				 params.setVerifyHanlder(dkyebImportVerify);
			 }
			 FileInputStream fis = null;
			 FileOutputStream fos = null;
			 try {
				 fis = new FileInputStream(file);
				 boolean checkResult = ExcelImportCheckUtil.check(fis, SgtzglDkyeb.class, params, 1.0);
				 if (!checkResult) {
					 return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
				 }
				 ExcelImportResult<SgtzglDkyeb> importResult = ExcelImportUtil.importExcelVerify(file, SgtzglDkyeb.class,SgtzglDkyebVO.class, params);

				 QueryWrapper<SgtzglDkyeb> wrapper = new QueryWrapper<>();
				 wrapper.eq("fiscal_date", fiscalDate);
				 sgtzglDkyebService.remove(wrapper);

				 List<SgtzglDkyeb> list = importResult.getList();
				 List<SgtzglDkyeb> sybList = new ArrayList<>();
				 for (SgtzglDkyeb sybImportVO : list) {
					 sybImportVO.setFiscalDate(fiscalDate);
					 sybList.add(sybImportVO);
				 }
				 sgtzglDkyebService.saveBatch(sybList);

				 obj.put("filePath", filePath);
				 fos = new FileOutputStream(baseFilePath);
				 importResult.getWorkbook().write(fos);
				 fos.flush();
				 fos.close();
				 return Result.ok("文件导入完成！成功导入数据行数: " + list.size(), obj);
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败:" + e.getMessage());
			 } finally {
				 IoUtil.close(fis);
				 IoUtil.close(fos);
			 }
		 }
		 return Result.ok("文件导入失败！");
	 }
}
