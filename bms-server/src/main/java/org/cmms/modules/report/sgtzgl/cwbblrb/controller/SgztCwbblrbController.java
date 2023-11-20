package org.cmms.modules.report.sgtzgl.cwbblrb.controller;

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
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.cwbbbwkmbWzrmb.service.ISgtzcwbwkmbWzrmbService;
import org.cmms.modules.report.sgtzgl.cwbblrb.entity.SgztCwbblrb;
import org.cmms.modules.report.sgtzgl.cwbblrb.entity.SgztCwbblrbVO;
import org.cmms.modules.report.sgtzgl.cwbblrb.service.ISgztCwbblrbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.cwbblrb.verify.CwbblrbImportVerify;
import org.cmms.modules.report.sgtzgl.cwbblrbRmb.entity.SgtzCwbblrbRmb;
import org.cmms.modules.report.sgtzgl.dbpgldkdjb.entity.SgztDbpgldkdjb;
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
 * @Description: 财务报表利润表
 * @Author: jeecg-boot
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="财务报表利润表")
@RestController
@RequestMapping("/cwbblrb/sgztCwbblrb")
public class SgztCwbblrbController extends JeecgController<SgztCwbblrb, ISgztCwbblrbService> {
	@Autowired
	private ISgztCwbblrbService sgztCwbblrbService;
	@Autowired
	private CwbblrbImportVerify cwbblrbImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param sgztCwbblrb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "财务报表利润表-分页列表查询")
	@ApiOperation(value="财务报表利润表-分页列表查询", notes="财务报表利润表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgztCwbblrb sgztCwbblrb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgztCwbblrb> queryWrapper = QueryGenerator.initQueryWrapper(sgztCwbblrb, req.getParameterMap());
		Page<SgztCwbblrb> page = new Page<SgztCwbblrb>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgztCwbblrbService.class,sgztCwbblrbService,pageNo,pageSize,queryWrapper,"sjrq","xm");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgztCwbblrb
	 * @return
	 */
	@AutoLog(value = "财务报表利润表-添加")
	@ApiOperation(value="财务报表利润表-添加", notes="财务报表利润表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgztCwbblrb sgztCwbblrb) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		String sjrq = sgztCwbblrb.getSjrq().replace("-", "").substring(0, 8);
		sgztCwbblrb.setSjrq(sjrq);
		sgztCwbblrb.setCreateTime(new Date());
		sgztCwbblrbService.save(sgztCwbblrb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgztCwbblrb
	 * @return
	 */
	@AutoLog(value = "财务报表利润表-编辑")
	@ApiOperation(value="财务报表利润表-编辑", notes="财务报表利润表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgztCwbblrb sgztCwbblrb) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		String sjrq = sgztCwbblrb.getSjrq().replace("-", "").substring(0, 8);
		sgztCwbblrb.setSjrq(sjrq);
		sgztCwbblrbService.updateById(sgztCwbblrb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "财务报表利润表-通过id删除")
	@ApiOperation(value="财务报表利润表-通过id删除", notes="财务报表利润表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgztCwbblrbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "财务报表利润表-批量删除")
	@ApiOperation(value="财务报表利润表-批量删除", notes="财务报表利润表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgztCwbblrbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
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
		 QueryWrapper<SgztCwbblrb> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("sjrq",sjrq);
		 this.sgztCwbblrbService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "财务报表利润表-通过id查询")
	@ApiOperation(value="财务报表利润表-通过id查询", notes="财务报表利润表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgztCwbblrb sgztCwbblrb = sgztCwbblrbService.getById(id);
		return Result.ok(sgztCwbblrb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgztCwbblrb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgztCwbblrb sgztCwbblrb) {
      return super.exportXls(request, sgztCwbblrb, SgztCwbblrb.class, "财务报表利润表");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "财务报表利润表-本外币导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgztCwbblrbVO.class);
		 ExportParams exportParams = new ExportParams("财务报表利润表-本外币导入模板", "模板信息");
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
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
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
			 if (cwbblrbImportVerify != null) {
				 params.setVerifyHanlder(cwbblrbImportVerify);
			 }
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<SgztCwbblrb> importResult = ExcelImportUtil.importExcelVerify(file, SgztCwbblrb.class,SgztCwbblrbVO.class, params);
				 List<SgztCwbblrb> list = importResult.getList();
				 List<SgztCwbblrb> qkmbList = new ArrayList<>();
				 for (SgztCwbblrb ywzkb : list) {
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
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败:" + e.getMessage());
			 } finally {
				 IoUtil.close(fos);
			 }
		 }
		 return Result.ok("文件导入失败！");
	 }

}
