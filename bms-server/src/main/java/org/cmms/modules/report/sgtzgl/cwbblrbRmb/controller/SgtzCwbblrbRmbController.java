package org.cmms.modules.report.sgtzgl.cwbblrbRmb.controller;

import java.io.*;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.cwbblrb.entity.SgztCwbblrb;
import org.cmms.modules.report.sgtzgl.cwbblrb.entity.SgztCwbblrbVO;
import org.cmms.modules.report.sgtzgl.cwbblrb.service.ISgztCwbblrbService;
import org.cmms.modules.report.sgtzgl.cwbblrbRmb.entity.SgtzCwbblrbRmb;
import org.cmms.modules.report.sgtzgl.cwbblrbRmb.service.ISgtzCwbblrbRmbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.cwbblrbRmb.verify.CwbblrbRmbImportVerify;
import org.cmms.modules.report.sgtzgl.cwbbqkmb.entity.SgtzCwbbqkmb;
import org.cmms.modules.ywgl.nxt.shpj.glzhxx.entity.Glzhxx;
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
 * @Description: 财务报表利润表-人民币
 * @Author: jeecg-boot
 * @Date:   2022-10-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="财务报表利润表-人民币")
@RestController
@RequestMapping("/cwbblrbRmb/sgtzCwbblrbRmb")
public class SgtzCwbblrbRmbController extends JeecgController<SgtzCwbblrbRmb, ISgtzCwbblrbRmbService> {
	@Autowired
	private ISgtzCwbblrbRmbService sgtzCwbblrbRmbService;
	@Autowired
	private CwbblrbRmbImportVerify cwbblrbRmbImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param sgtzCwbblrbRmb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "财务报表利润表-人民币-分页列表查询")
	@ApiOperation(value="财务报表利润表-人民币-分页列表查询", notes="财务报表利润表-人民币-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgtzCwbblrbRmb sgtzCwbblrbRmb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgtzCwbblrbRmb> queryWrapper = QueryGenerator.initQueryWrapper(sgtzCwbblrbRmb, req.getParameterMap());
		Page<SgtzCwbblrbRmb> page = new Page<SgtzCwbblrbRmb>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgtzCwbblrbRmbService.class,sgtzCwbblrbRmbService,pageNo,pageSize,queryWrapper,"sjrq","xm");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgtzCwbblrbRmb
	 * @return
	 */
	@AutoLog(value = "财务报表利润表-人民币-添加")
	@ApiOperation(value="财务报表利润表-人民币-添加", notes="财务报表利润表-人民币-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgtzCwbblrbRmb sgtzCwbblrbRmb) {
		String sjrq = sgtzCwbblrbRmb.getSjrq().replace("-", "").substring(0, 8);
		sgtzCwbblrbRmb.setSjrq(sjrq);
		sgtzCwbblrbRmb.setCreateTime(new Date());
		sgtzCwbblrbRmbService.save(sgtzCwbblrbRmb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgtzCwbblrbRmb
	 * @return
	 */
	@AutoLog(value = "财务报表利润表-人民币-编辑")
	@ApiOperation(value="财务报表利润表-人民币-编辑", notes="财务报表利润表-人民币-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgtzCwbblrbRmb sgtzCwbblrbRmb) {
		String sjrq = sgtzCwbblrbRmb.getSjrq().replace("-", "").substring(0, 8);
		sgtzCwbblrbRmb.setSjrq(sjrq);
		sgtzCwbblrbRmbService.updateById(sgtzCwbblrbRmb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "财务报表利润表-人民币-通过id删除")
	@ApiOperation(value="财务报表利润表-人民币-通过id删除", notes="财务报表利润表-人民币-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgtzCwbblrbRmbService.removeById(id);
		return Result.ok("删除成功!");
	}

	 /**
	  * 根据日期批量删除
	  *
	  * @param sjrq
	  * @return
	  */
	 @AutoLog(value = "财务报表全科目表-批量删除")
	 @ApiOperation(value="财务报表全科目表-批量删除", notes="财务报表全科目表-批量删除")
	 @DeleteMapping(value = "/deleteByBatch")
	 public Result<?> deleteByBatch(@Param("sjrq") String sjrq) {
		 QueryWrapper<SgtzCwbblrbRmb> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("sjrq",sjrq);
		 this.sgtzCwbblrbRmbService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "财务报表利润表-人民币-批量删除")
	@ApiOperation(value="财务报表利润表-人民币-批量删除", notes="财务报表利润表-人民币-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgtzCwbblrbRmbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "财务报表利润表-人民币-通过id查询")
	@ApiOperation(value="财务报表利润表-人民币-通过id查询", notes="财务报表利润表-人民币-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgtzCwbblrbRmb sgtzCwbblrbRmb = sgtzCwbblrbRmbService.getById(id);
		return Result.ok(sgtzCwbblrbRmb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgtzCwbblrbRmb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgtzCwbblrbRmb sgtzCwbblrbRmb) {
      return super.exportXls(request, sgtzCwbblrbRmb, SgtzCwbblrbRmb.class, "财务报表利润表-人民币");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "财务报表利润表-人民币导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgztCwbblrbVO.class);
		 ExportParams exportParams = new ExportParams("财务报表利润表-人民币导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
	 }
	 /**
	  * 通过excel导入数据
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 String filePaths = jsonObject.getString("filePaths");
		 String sjrq = request.getParameter("sjrq");
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
			 if (cwbblrbRmbImportVerify != null) {
				 params.setVerifyHanlder(cwbblrbRmbImportVerify);
			 }
			 FileOutputStream fos = null;
			 FileInputStream fis = null;
			 try {
				 fis = new FileInputStream(file);
				 boolean checkResult = ExcelImportCheckUtil.check(fis, SgztCwbblrbVO.class, params);
				 if (!checkResult) {
					 return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
				 }
				 ExcelImportResult<SgtzCwbblrbRmb> importResult = ExcelImportUtil.importExcelVerify(file, SgtzCwbblrbRmb.class,SgztCwbblrbVO.class, params);
				 List<SgtzCwbblrbRmb> list = importResult.getList();
				 List<SgtzCwbblrbRmb> qkmbList = new ArrayList<>();
				 for (SgtzCwbblrbRmb ywzkb : list) {
					 ywzkb.setSjrq(sjrq);
					 ywzkb.setCreateTime(new Date());
					 qkmbList.add(ywzkb);
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
				 IoUtil.close(fis);
				 IoUtil.close(fos);
			 }
		 }
		 return Result.ok("文件导入失败！");
	 }

}
