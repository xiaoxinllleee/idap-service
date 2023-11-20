package org.cmms.modules.khlc.dkqjjj.controller;

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
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khlc.dkqjjj.entity.Dkqjjj;
import org.cmms.modules.khlc.dkqjjj.service.IDkqjjjService;
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
 * @Description: 贷款区间计价
 * @Author: jeecg-boot
 * @Date:   2023-06-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款区间计价")
@RestController
@RequestMapping("/khlc/dkqjjj")
public class DkqjjjController extends JeecgController<Dkqjjj, IDkqjjjService> {
	@Autowired
	private IDkqjjjService dkqjjjService;
	
	/**
	 * 分页列表查询
	 *
	 * @param dkqjjj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款区间计价-分页列表查询")
	@ApiOperation(value="贷款区间计价-分页列表查询", notes="贷款区间计价-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dkqjjj dkqjjj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dkqjjj> queryWrapper = QueryGenerator.initQueryWrapper(dkqjjj, req.getParameterMap());
		Page<Dkqjjj> page = new Page<Dkqjjj>(pageNo, pageSize);
		IPage<Dkqjjj> pageList = dkqjjjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param dkqjjj
	 * @return
	 */
	@AutoLog(value = "贷款区间计价-添加")
	@ApiOperation(value="贷款区间计价-添加", notes="贷款区间计价-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dkqjjj dkqjjj) {
		QueryWrapper<Dkqjjj> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("qjbh", dkqjjj.getQjbh());
		List<Dkqjjj> dkqjjjList = dkqjjjService.list(queryWrapper);
		if (!dkqjjjList.isEmpty()) {
			return Result.error("已经存在的区间编号");
		}
		dkqjjjService.save(dkqjjj);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param dkqjjj
	 * @return
	 */
	@AutoLog(value = "贷款区间计价-编辑")
	@ApiOperation(value="贷款区间计价-编辑", notes="贷款区间计价-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dkqjjj dkqjjj) {
		UpdateWrapper<Dkqjjj> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("qjbh", dkqjjj.getQjbh());
		dkqjjjService.update(dkqjjj, updateWrapper);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过qjbh删除
	 *
	 * @param qjbh
	 * @return
	 */
	@AutoLog(value = "贷款区间计价-通过qjbh删除")
	@ApiOperation(value="贷款区间计价-通过qjbh删除", notes="贷款区间计价-通过qjbh删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="qjbh",required=true) String qjbh) {
		UpdateWrapper<Dkqjjj> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("qjbh", qjbh);
		dkqjjjService.remove(updateWrapper);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款区间计价-批量删除")
	@ApiOperation(value="贷款区间计价-批量删除", notes="贷款区间计价-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkqjjjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款区间计价-通过id查询")
	@ApiOperation(value="贷款区间计价-通过id查询", notes="贷款区间计价-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dkqjjj dkqjjj = dkqjjjService.getById(id);
		return Result.ok(dkqjjj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkqjjj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dkqjjj dkqjjj) {
      return super.exportXls(request, dkqjjj, Dkqjjj.class, "贷款区间计价");
  }

	 /**
	  * 导出模板Excel
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "贷款区间计价");
		 mv.addObject(NormalExcelConstants.CLASS, Dkqjjj.class);
		 ExportParams exportParams = new ExportParams("贷款区间计价导入模板", "贷款区间计价");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return mv;
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
			 FileInputStream fis = null;
			 FileOutputStream fos = null;
			 try {
				 fis = new FileInputStream(file);
				 ExcelImportResult<Dkqjjj> importResult = ExcelImportUtil.importExcelVerify(file, Dkqjjj.class, params);
				 List<Dkqjjj> list = importResult.getList();
				 List<Dkqjjj> dkqjjjList = new ArrayList<>();
				 for (Dkqjjj dkqjjj : dkqjjjList) {
					 Dkqjjj insert = new Dkqjjj();
					 BeanUtil.copyPropertiesIgnoreNull(dkqjjj, insert);
					 dkqjjjList.add(insert);
					 UpdateWrapper<Dkqjjj> updateWrapper = new UpdateWrapper<>();
					 updateWrapper.eq("qjbh",dkqjjj.getQjbh());
					 dkqjjjService.remove(updateWrapper);
				 }
				 if (!dkqjjjList.isEmpty()) {
					 dkqjjjService.saveBatch(dkqjjjList);
				 }
				 obj.put("filePath", filePath);
				 fos = new FileOutputStream(baseFilePath);
				 importResult.getWorkbook().write(fos);
				 fos.flush();
				 fos.close();
				 return Result.ok("文件导入完成！成功导入数据行数: " + list.size(), obj);
			 } catch (Exception e) {
				 e.printStackTrace();
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
