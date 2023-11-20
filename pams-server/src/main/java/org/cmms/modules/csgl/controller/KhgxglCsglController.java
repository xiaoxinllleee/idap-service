package org.cmms.modules.csgl.controller;

import java.io.*;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.csgl.entity.KhgxglCsgl;
import org.cmms.modules.csgl.entity.KhgxglCsglVO;
import org.cmms.modules.csgl.service.IKhgxglCsglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 参数管理
 * @Author: jeecg-boot
 * @Date:   2023-03-31
 * @Version: V1.0
 */
@Slf4j
@Api(tags="参数管理")
@RestController
@RequestMapping("/csgl/khgxglCsgl")
public class KhgxglCsglController extends JeecgController<KhgxglCsgl, IKhgxglCsglService> {
	@Autowired
	private IKhgxglCsglService khgxglCsglService;

	/**
	 * 分页列表查询
	 *
	 * @param khgxglCsgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "参数管理-分页列表查询")
	@ApiOperation(value="参数管理-分页列表查询", notes="参数管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KhgxglCsgl khgxglCsgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String csmc = khgxglCsgl.getCsmc();
		khgxglCsgl.setCsmc(null);
		QueryWrapper<KhgxglCsgl> queryWrapper = QueryGenerator.initQueryWrapper(khgxglCsgl, req.getParameterMap());
		if (StringUtils.isNotBlank(csmc)){
			queryWrapper.like("csmc",csmc);
		}
		Page<KhgxglCsgl> page = new Page<KhgxglCsgl>(pageNo, pageSize);
		IPage<KhgxglCsgl> pageList = khgxglCsglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param khgxglCsgl
	 * @return
	 */
	@AutoLog(value = "参数管理-添加")
	@ApiOperation(value="参数管理-添加", notes="参数管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody KhgxglCsgl khgxglCsgl) {
		khgxglCsglService.save(khgxglCsgl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param khgxglCsgl
	 * @return
	 */
	@AutoLog(value = "参数管理-编辑")
	@ApiOperation(value="参数管理-编辑", notes="参数管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhgxglCsgl khgxglCsgl) {
		khgxglCsglService.updateById(khgxglCsgl);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "参数管理-通过id删除")
	@ApiOperation(value="参数管理-通过id删除", notes="参数管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khgxglCsglService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "参数管理-批量删除")
	@ApiOperation(value="参数管理-批量删除", notes="参数管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khgxglCsglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "参数管理-通过id查询")
	@ApiOperation(value="参数管理-通过id查询", notes="参数管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		KhgxglCsgl khgxglCsgl = khgxglCsglService.getById(id);
		return Result.ok(khgxglCsgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khgxglCsgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, KhgxglCsgl khgxglCsgl) {
      return super.exportXls(request, khgxglCsgl, KhgxglCsgl.class, "参数管理");
  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
/*
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, KhgxglCsgl.class);
  }
*/


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
			/* if (khxxglJzyxDklshVerify != null) {
				 params.setVerifyHanlder(khxxglJzyxDklshVerify);
			 }*/
			 FileOutputStream fos = null;
			 FileInputStream fis = null;
			 try {
				 fis = new FileInputStream(file);
				 boolean checkResult = ExcelImportCheckUtil.check(fis, KhgxglCsglVO.class, params);
				 ExcelImportResult<KhgxglCsglVO> importResult = ExcelImportUtil.importExcelVerify(file, KhgxglCsglVO.class, params);
				 List<KhgxglCsglVO> list = importResult.getList();
				 List<KhgxglCsgl> list1 =new ArrayList<>();
				 for(KhgxglCsglVO csglVO:list){
					 KhgxglCsgl csgl=new KhgxglCsgl();
					 BeanUtils.copyProperties(csglVO, csgl);
					 QueryWrapper<KhgxglCsgl> queryWrapper=new QueryWrapper();
					 queryWrapper.eq("csbh",csgl.getCsbm());
					 khgxglCsglService.remove(queryWrapper);
					 list1.add(csgl);
				 }
				 khgxglCsglService.saveBatch(list1);
				 obj.put("filePath", filePath);
				 fos = new FileOutputStream(baseFilePath);
				 importResult.getWorkbook().write(fos);
				 fos.flush();
				 fos.close();
				 return Result.ok("文件导入完成！成功导入数据行数:" + list1.size(), obj);
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

	 /**
	  * 导出模板
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "参数管理导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, KhgxglCsglVO.class);
		 ExportParams exportParams = new ExportParams("参数管理导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
	 }

 }
