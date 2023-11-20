package org.cmms.modules.jylrhs.csgl.rwsz.controller;

import java.io.*;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.jylrhs.csgl.rwsz.entity.JylrhsRwsz;
import org.cmms.modules.jylrhs.csgl.rwsz.entity.JylrhsRwszVO;
import org.cmms.modules.jylrhs.csgl.rwsz.service.IJylrhsRwszService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.jylrhs.csgl.rwsz.verify.JylrhsRwszImportVerify;
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
 * @Description: 经营利润任务设置
 * @Author: jeecg-boot
 * @Date:   2023-11-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="经营利润任务设置")
@RestController
@RequestMapping("/jylrhs/csgl/rwsz")
public class JylrhsRwszController extends JeecgController<JylrhsRwsz, IJylrhsRwszService> {
	 @Autowired
	 private IJylrhsRwszService jylrhsRwszService;
	 @Autowired
	 private JylrhsRwszImportVerify importVerify;

	 /**
	  * 分页列表查询
	  *
	  * @param jylrhsRwsz
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "经营利润任务设置-分页列表查询")
	 @ApiOperation(value="经营利润任务设置-分页列表查询", notes="经营利润任务设置-分页列表查询")
	 @GetMapping(value = "/list")
	 public Result<?> queryPageList(JylrhsRwsz jylrhsRwsz,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		 QueryWrapper<JylrhsRwsz> queryWrapper = QueryGenerator.initQueryWrapper(jylrhsRwsz, req.getParameterMap());
//		Page<JylrhsRwsz> page = new Page<JylrhsRwsz>(pageNo, pageSize);
//		IPage<JylrhsRwsz> pageList = jylrhsRwszService.page(page, queryWrapper);
		 IPage pageList = PageUtil.toPage(IJylrhsRwszService.class, jylrhsRwszService, pageNo, pageSize, queryWrapper, "mission_year","jgdm");
		 return Result.ok(pageList);
	 }

	 /**
	  * 添加
	  *
	  * @param jylrhsRwsz
	  * @return
	  */
	 @AutoLog(value = "经营利润任务设置-添加")
	 @ApiOperation(value="经营利润任务设置-添加", notes="经营利润任务设置-添加")
	 @PostMapping(value = "/add")
	 public Result<?> add(@RequestBody JylrhsRwsz jylrhsRwsz) {
		 try {
			 QueryWrapper<JylrhsRwsz> queryWrapper = new QueryWrapper<>();
			 queryWrapper.eq("mission_year",jylrhsRwsz.getMissionYear());
			 queryWrapper.eq("jgdm",jylrhsRwsz.getJgdm());
			 JylrhsRwsz record = jylrhsRwszService.getOne(queryWrapper,false);
			 if (record == null) {
				 jylrhsRwsz.setOprationType("1");
				 jylrhsRwsz.setOperator(getUsername());
				 jylrhsRwsz.setOprationTime(new Date());
				 jylrhsRwszService.save(jylrhsRwsz);
				 return Result.ok("添加成功！");
			 } else {
				 return Result.error("该条记录已存在，请核实！");
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
			 log.error("添加失败！"+e.getMessage());
			 return Result.error("添加失败！");
		 }
	 }

	 /**
	  * 修改
	  *
	  * @param jylrhsRwsz
	  * @return
	  */
	 @AutoLog(value = "经营利润任务设置-修改")
	 @ApiOperation(value="经营利润任务设置-修改", notes="经营利润任务设置-修改")
	 @PutMapping(value = "/edit")
	 public Result<?> edit(@RequestBody JylrhsRwsz jylrhsRwsz) {
		 try {
			 UpdateWrapper<JylrhsRwsz> updateWrapper = new UpdateWrapper<>();
			 updateWrapper.eq("mission_year",jylrhsRwsz.getMissionYear());
			 updateWrapper.eq("jgdm",jylrhsRwsz.getJgdm());
			 jylrhsRwsz.setMissionYear(null);
			 jylrhsRwsz.setJgdm(null);
			 jylrhsRwsz.setOprationType("2");
			 jylrhsRwsz.setOperator(getUsername());
			 jylrhsRwsz.setOprationTime(new Date());
			 jylrhsRwszService.update(jylrhsRwsz,updateWrapper);
			 return Result.ok("修改成功!");
		 } catch (Exception e) {
			 e.printStackTrace();
			 log.error("修改失败！"+e.getMessage());
			 return Result.error("修改失败！");
		 }
	 }

	 /**
	  * 删除
	  *
	  * @param rwnf
	  * @param jgdm
	  * @return
	  */
	 @AutoLog(value = "经营利润任务设置-删除")
	 @ApiOperation(value="经营利润任务设置-删除", notes="经营利润任务设置-删除")
	 @DeleteMapping(value = "/delete")
	 public Result<?> delete(@RequestParam(name="rwnf",required=true) String rwnf,
							 @RequestParam(name="jgdm",required=true) String jgdm) {
		 try {
			 Date missionYear = DateUtil.string2Date(rwnf,"yyyy-MM-dd");
			 UpdateWrapper<JylrhsRwsz> deleteWrapper = new UpdateWrapper<>();
			 deleteWrapper.eq("mission_year",missionYear);
			 deleteWrapper.eq("jgdm",jgdm);
			 jylrhsRwszService.remove(deleteWrapper);
			 return Result.ok("删除成功!");
		 } catch (Exception e) {
			 e.printStackTrace();
			 log.error("删除失败！"+e.getMessage());
			 return Result.error("删除失败！");
		 }
	 }

	 /**
	  * 批量删除
	  *
	  * @param ids
	  * @return
	  */
	 @AutoLog(value = "经营利润任务设置-批量删除")
	 @ApiOperation(value="经营利润任务设置-批量删除", notes="经营利润任务设置-批量删除")
	 @DeleteMapping(value = "/deleteBatch")
	 public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		 this.jylrhsRwszService.removeByIds(Arrays.asList(ids.split(",")));
		 return Result.ok("批量删除成功！");
	 }

	 /**
	  * 通过id查询
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "经营利润任务设置-通过id查询")
	 @ApiOperation(value="经营利润任务设置-通过id查询", notes="经营利润任务设置-通过id查询")
	 @GetMapping(value = "/queryById")
	 public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		 JylrhsRwsz jylrhsRwsz = jylrhsRwszService.getById(id);
		 return Result.ok(jylrhsRwsz);
	 }

	 /**
	  * 导出excel
	  *
	  * @param request
	  * @param jylrhsRwsz
	  */
	 @RequestMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, JylrhsRwsz jylrhsRwsz) {
		 return super.exportXls(request, jylrhsRwsz, JylrhsRwsz.class, "经营利润任务设置");
	 }

	 /**
	  * 数据导入模板下载
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "经营利润任务设置导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, JylrhsRwszVO.class);
		 ExportParams exportParams = new ExportParams("经营利润任务设置", "任务设置");
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
		 //return super.importExcel(request, response, JylrhsRwsz.class);
		 JSONObject obj = new JSONObject();
		 String filePaths = jsonObject.getString("filePaths");
		 if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
			 return Result.error("请先上传文件！");
		 }
		 String[] filePathList = filePaths.split(",");
		 for (String filePath : filePathList) {
			 String baseFilePath = uploadpath + File.separator + filePath;
			 File file = new File(baseFilePath);
			 ImportParams params = new ImportParams();
			 params.setTitleRows(1);
			 params.setHeadRows(1);
			 params.setNeedSave(false);
			 if (importVerify != null) {
				 params.setVerifyHanlder(importVerify);
			 }
			 FileInputStream fis = null;
			 FileOutputStream fos = null;
			 try {
				 fis = new FileInputStream(file);
				 ExcelImportResult<JylrhsRwszVO> importResult = ExcelImportUtil.importExcelVerify(file, JylrhsRwszVO.class, JylrhsRwszVO.class, params);
				 List<JylrhsRwsz> records = new ArrayList<>();
				 List<JylrhsRwszVO> list = importResult.getList();
				 for (JylrhsRwszVO rwsz : list) {
					 JylrhsRwsz record = new JylrhsRwsz();
					 BeanUtil.copyPropertiesIgnoreNull(rwsz, record);
					 record.setOprationType("0");
					 record.setOprationTime(new Date());
					 record.setOperator(getLoginUser().getUsername());
					 records.add(record);
				 }
				 if (!records.isEmpty()) {
					 jylrhsRwszService.saveBatch(records);
				 }
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
				 IoUtil.close(fis);
				 IoUtil.close(fos);
			 }
		 }
		 return Result.ok("文件导入成功！");
	 }

}
