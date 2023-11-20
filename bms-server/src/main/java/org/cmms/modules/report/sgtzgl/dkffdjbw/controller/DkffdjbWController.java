package org.cmms.modules.report.sgtzgl.dkffdjbw.controller;

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
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.dkffdjb.entity.SgtzglDkffdjb;
import org.cmms.modules.report.sgtzgl.dkffdjb.entity.SgtzglDkffdjbVO;
import org.cmms.modules.report.sgtzgl.dkffdjb.service.ISgtzglDkffdjbService;
import org.cmms.modules.report.sgtzgl.dkffdjbw.entity.DkffdjbW;
import org.cmms.modules.report.sgtzgl.dkffdjbw.entity.DkffdjbWVo;
import org.cmms.modules.report.sgtzgl.dkffdjbw.service.IDkffdjbWService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.dkffdjbw.verify.DkffdjbWImportVerify;
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
 * @Description: 贷款发放登记簿（周）
 * @Author: jeecg-boot
 * @Date:   2022-11-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款发放登记簿（周）")
@RestController
@RequestMapping("/sgtzgl/dkffdjbW")
public class DkffdjbWController extends JeecgController<DkffdjbW, IDkffdjbWService> {
	@Autowired
	private IDkffdjbWService dkffdjbWService;
	@Autowired
	private DkffdjbWImportVerify dkffdjbImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param dkffdjbW
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款发放登记簿（周）-分页列表查询")
	@ApiOperation(value="贷款发放登记簿（周）-分页列表查询", notes="贷款发放登记簿（周）-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DkffdjbW dkffdjbW,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DkffdjbW> queryWrapper = QueryGenerator.initQueryWrapper(dkffdjbW, req.getParameterMap());
		Page<DkffdjbW> page = new Page<DkffdjbW>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(IDkffdjbWService.class,dkffdjbWService,pageNo,pageSize,queryWrapper,"fiscal_date","dkzh");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dkffdjbW
	 * @return
	 */
	@AutoLog(value = "贷款发放登记簿（周）-添加")
	@ApiOperation(value="贷款发放登记簿（周）-添加", notes="贷款发放登记簿（周）-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DkffdjbW dkffdjbW) {
		dkffdjbW.setFiscalDate(dkffdjbW.getFiscalDate()==null ? null: dkffdjbW.getFiscalDate().replace("-", "").substring(0,8));
		dkffdjbW.setJkrq(dkffdjbW.getJkrq()==null ? null:dkffdjbW.getJkrq().replace("-", "").substring(0,8));
		dkffdjbW.setDqrq(dkffdjbW.getDqrq()==null ? null:dkffdjbW.getDqrq().replace("-", "").substring(0,8));
		dkffdjbWService.save(dkffdjbW);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dkffdjbW
	 * @return
	 */
	@AutoLog(value = "贷款发放登记簿（周）-编辑")
	@ApiOperation(value="贷款发放登记簿（周）-编辑", notes="贷款发放登记簿（周）-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DkffdjbW dkffdjbW) {
		dkffdjbW.setFiscalDate(dkffdjbW.getFiscalDate()==null ? null: dkffdjbW.getFiscalDate().replace("-", "").substring(0,8));
		dkffdjbW.setJkrq(dkffdjbW.getJkrq()==null ? null:dkffdjbW.getJkrq().replace("-", "").substring(0,8));
		dkffdjbW.setDqrq(dkffdjbW.getDqrq()==null ? null:dkffdjbW.getDqrq().replace("-", "").substring(0,8));
		QueryWrapper<DkffdjbW> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("FISCAL_DATE",dkffdjbW.getFiscalDate()).eq("dkzh",dkffdjbW.getDkzh());
		dkffdjbW.setFiscalDate(null);
		dkffdjbW.setDkzh(null);
		dkffdjbWService.update(dkffdjbW,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "贷款发放登记簿（周）-通过数据日期和贷款账号删除")
	@ApiOperation(value="贷款发放登记簿（周）-通过数据日期和贷款账号删除", notes="贷款发放登记簿（周）-通过数据日期和贷款账号删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="fiscalDate",required=true) String fiscalDate,@RequestParam(name = "dkzh",required = true) String dkzh) {
		QueryWrapper<DkffdjbW> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("FISCAL_DATE",fiscalDate).eq("dkzh",dkzh);
		dkffdjbWService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款发放登记簿（周）-批量删除")
	@ApiOperation(value="贷款发放登记簿（周）-批量删除", notes="贷款发放登记簿（周）-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkffdjbWService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款发放登记簿（周）-通过id查询")
	@ApiOperation(value="贷款发放登记簿（周）-通过id查询", notes="贷款发放登记簿（周）-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DkffdjbW dkffdjbW = dkffdjbWService.getById(id);
		return Result.ok(dkffdjbW);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkffdjbW
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DkffdjbW dkffdjbW) {
      return super.exportXls(request, dkffdjbW, DkffdjbW.class, "贷款发放登记簿（周）");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "贷款发放登记簿（周）导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS,DkffdjbWVo.class);
		 ExportParams exportParams = new ExportParams("贷款发放登记簿（周）导入模板", "模板信息");
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
		 QueryWrapper<DkffdjbW> queryWrapper=new QueryWrapper<>();
		 queryWrapper.eq("fiscal_date",fiscalDate);
		 dkffdjbWService.remove(queryWrapper);
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
			 if (dkffdjbImportVerify != null) {
				 params.setVerifyHanlder(dkffdjbImportVerify);
			 }
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<DkffdjbWVo> importResult = ExcelImportUtil.importExcelVerify(file, DkffdjbWVo.class, DkffdjbWVo.class, params);
				 List<DkffdjbWVo> list = importResult.getList();
				 List<DkffdjbW> qkmbList = new ArrayList<>();
				 for (DkffdjbWVo qkmb : list) {
					 DkffdjbW dkffdjbW = new DkffdjbW();
					 BeanUtil.copyPropertiesIgnoreNull(qkmb,dkffdjbW);
					 if (dkffdjbW.getFiscalDate() == null || dkffdjbW.getDkzh() == null){
						 dkffdjbW.setFiscalDate(fiscalDate);
						 dkffdjbW.setDkzh(qkmb.getDkzh());
					 }
					 qkmbList.add(dkffdjbW);
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
