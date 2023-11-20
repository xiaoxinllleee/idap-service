package org.cmms.modules.report.sgtzgl.cwbbzcfzbWzrmb.controller;

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
import org.cmms.modules.report.sgtzgl.cwbbsybbWzrmb.entity.SgtzCwxyssybWzrmb;
import org.cmms.modules.report.sgtzgl.cwbbzcfzb.entity.SgtzglCwbbzcfzbVO;
import org.cmms.modules.report.sgtzgl.cwbbzcfzbRmb.entity.SgtzCwxyszcfzbRmb;
import org.cmms.modules.report.sgtzgl.cwbbzcfzbRmb.service.ISgtzCwxyszcfzbRmbService;
import org.cmms.modules.report.sgtzgl.cwbbzcfzbWzrmb.entity.SgtzCwxyszcfzbWzrmb;
import org.cmms.modules.report.sgtzgl.cwbbzcfzbWzrmb.service.ISgtzCwxyszcfzbWzrmbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.cwbbzcfzbWzrmb.verify.CwbbzcfzbWzrmbImportVerify;
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
 * @Description: 财务报表资产负债表-外折人民币
 * @Author: jeecg-boot
 * @Date:   2022-10-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="财务报表资产负债表-外折人民币")
@RestController
@RequestMapping("/cwbbzcfzbWzrmb/sgtzCwxyszcfzbWzrmb")
public class SgtzCwxyszcfzbWzrmbController extends JeecgController<SgtzCwxyszcfzbWzrmb, ISgtzCwxyszcfzbWzrmbService> {
	@Autowired
	private ISgtzCwxyszcfzbWzrmbService sgtzCwxyszcfzbWzrmbService;
	@Autowired
	private CwbbzcfzbWzrmbImportVerify cwbbzcfzbWzrmbImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param sgtzCwxyszcfzbWzrmb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "财务报表资产负债表-外折人民币-分页列表查询")
	@ApiOperation(value="财务报表资产负债表-外折人民币-分页列表查询", notes="财务报表资产负债表-外折人民币-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgtzCwxyszcfzbWzrmb sgtzCwxyszcfzbWzrmb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgtzCwxyszcfzbWzrmb> queryWrapper = QueryGenerator.initQueryWrapper(sgtzCwxyszcfzbWzrmb, req.getParameterMap());
		Page<SgtzCwxyszcfzbWzrmb> page = new Page<SgtzCwxyszcfzbWzrmb>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgtzCwxyszcfzbWzrmbService.class,sgtzCwxyszcfzbWzrmbService,pageNo,pageSize,queryWrapper,"sjrq","id");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgtzCwxyszcfzbWzrmb
	 * @return
	 */
	@AutoLog(value = "财务报表资产负债表-外折人民币-添加")
	@ApiOperation(value="财务报表资产负债表-外折人民币-添加", notes="财务报表资产负债表-外折人民币-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgtzCwxyszcfzbWzrmb sgtzCwxyszcfzbWzrmb) {
		String sjrq = sgtzCwxyszcfzbWzrmb.getSjrq().replace("-", "").substring(0, 8);
		sgtzCwxyszcfzbWzrmb.setSjrq(sjrq);
		sgtzCwxyszcfzbWzrmb.setHc2(sgtzCwxyszcfzbWzrmb.getHc1());
		sgtzCwxyszcfzbWzrmb.setCreateTime(new Date());
		sgtzCwxyszcfzbWzrmbService.save(sgtzCwxyszcfzbWzrmb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgtzCwxyszcfzbWzrmb
	 * @return
	 */
	@AutoLog(value = "财务报表资产负债表-外折人民币-编辑")
	@ApiOperation(value="财务报表资产负债表-外折人民币-编辑", notes="财务报表资产负债表-外折人民币-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgtzCwxyszcfzbWzrmb sgtzCwxyszcfzbWzrmb) {
		String sjrq = sgtzCwxyszcfzbWzrmb.getSjrq().replace("-", "").substring(0, 8);
		sgtzCwxyszcfzbWzrmb.setSjrq(sjrq);
		sgtzCwxyszcfzbWzrmb.setHc2(sgtzCwxyszcfzbWzrmb.getHc1());
		sgtzCwxyszcfzbWzrmbService.updateById(sgtzCwxyszcfzbWzrmb);
		return Result.ok("编辑成功!");
	}
	 /**
	  * 根据日期批量删除
	  *
	  * @param sjrq
	  * @return
	  */
	 @AutoLog(value = "财务报表资产负债表-批量删除")
	 @ApiOperation(value="财务报表资产负债表-批量删除", notes="财务报表资产负债表-批量删除")
	 @DeleteMapping(value = "/deleteByBatch")
	 public Result<?> deleteByBatch(@Param("sjrq") String sjrq) {
		 QueryWrapper<SgtzCwxyszcfzbWzrmb> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("sjrq",sjrq);
		 this.sgtzCwxyszcfzbWzrmbService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "财务报表资产负债表-外折人民币-通过id删除")
	@ApiOperation(value="财务报表资产负债表-外折人民币-通过id删除", notes="财务报表资产负债表-外折人民币-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgtzCwxyszcfzbWzrmbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "财务报表资产负债表-外折人民币-批量删除")
	@ApiOperation(value="财务报表资产负债表-外折人民币-批量删除", notes="财务报表资产负债表-外折人民币-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgtzCwxyszcfzbWzrmbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "财务报表资产负债表-外折人民币-通过id查询")
	@ApiOperation(value="财务报表资产负债表-外折人民币-通过id查询", notes="财务报表资产负债表-外折人民币-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgtzCwxyszcfzbWzrmb sgtzCwxyszcfzbWzrmb = sgtzCwxyszcfzbWzrmbService.getById(id);
		return Result.ok(sgtzCwxyszcfzbWzrmb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgtzCwxyszcfzbWzrmb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgtzCwxyszcfzbWzrmb sgtzCwxyszcfzbWzrmb) {
      return super.exportXls(request, sgtzCwxyszcfzbWzrmb, SgtzCwxyszcfzbWzrmb.class, "财务报表资产负债表-外折人民币");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "财务报表资产负债表导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgtzglCwbbzcfzbVO.class);
		 ExportParams exportParams = new ExportParams("财务报表资产负债表导入模板", "模板信息");
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
		 String filePaths = jsonObject.getString("filePaths");
		 if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
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
			 if (cwbbzcfzbWzrmbImportVerify != null) {
				 params.setVerifyHanlder(cwbbzcfzbWzrmbImportVerify);
			 }
			 FileOutputStream fos = null;
			 FileInputStream fis = null;
			 try {
				 fis = new FileInputStream(file);
				 boolean checkResult = ExcelImportCheckUtil.check(fis, SgtzglCwbbzcfzbVO.class, params);
				 ExcelImportResult<SgtzCwxyszcfzbWzrmb> importResult = ExcelImportUtil.importExcelVerify(file, SgtzCwxyszcfzbWzrmb.class,SgtzglCwbbzcfzbVO.class, params);
				 List<SgtzCwxyszcfzbWzrmb> list = importResult.getList();
				 List<SgtzCwxyszcfzbWzrmb> qkmbList = new ArrayList<>();
				 for (SgtzCwxyszcfzbWzrmb sgtzglCwbbzcfzb : list) {
					 sgtzglCwbbzcfzb.setSjrq(sjrq);
					 sgtzglCwbbzcfzb.setHc1(sgtzglCwbbzcfzb.getHc2());
					 sgtzglCwbbzcfzb.setCreateTime(new Date());
					 qkmbList.add(sgtzglCwbbzcfzb);
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
