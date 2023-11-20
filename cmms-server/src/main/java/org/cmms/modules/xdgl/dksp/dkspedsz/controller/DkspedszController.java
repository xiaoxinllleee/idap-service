package org.cmms.modules.xdgl.dksp.dkspedsz.controller;

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
import org.apache.commons.lang.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.xdgl.dksp.dkspedsz.entity.Dkspedsz;
import org.cmms.modules.xdgl.dksp.dkspedsz.entity.DkspedszImport;
import org.cmms.modules.xdgl.dksp.dkspedsz.service.IDkspedszService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 贷款审批额度设置
 * @Author: jeecg-boot
 * @Date:   2021-11-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款审批额度设置")
@RestController
@RequestMapping("/dksp/edsz")
public class DkspedszController extends JeecgController<Dkspedsz, IDkspedszService> {
	@Autowired
	private IDkspedszService dkspedszService;
	 @Value(value = "${common.path.upload}")
	 private String uploadpath;
	/**
	 * 分页列表查询
	 *
	 * @param dkspedsz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款审批额度设置-分页列表查询")
	@ApiOperation(value="贷款审批额度设置-分页列表查询", notes="贷款审批额度设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dkspedsz dkspedsz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dkspedsz> queryWrapper = QueryGenerator.initQueryWrapper(dkspedsz, req.getParameterMap());
		Page<Dkspedsz> page = new Page<Dkspedsz>(pageNo, pageSize);
		IPage<Dkspedsz> pageList = dkspedszService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dkspedsz
	 * @return
	 */
	@AutoLog(value = "贷款审批额度设置-添加")
	@ApiOperation(value="贷款审批额度设置-添加", notes="贷款审批额度设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dkspedsz dkspedsz) {
		dkspedszService.save(dkspedsz);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dkspedsz
	 * @return
	 */
	@AutoLog(value = "贷款审批额度设置-编辑")
	@ApiOperation(value="贷款审批额度设置-编辑", notes="贷款审批额度设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dkspedsz dkspedsz) {
		dkspedszService.updateById(dkspedsz);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款审批额度设置-通过id删除")
	@ApiOperation(value="贷款审批额度设置-通过id删除", notes="贷款审批额度设置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkspedszService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款审批额度设置-批量删除")
	@ApiOperation(value="贷款审批额度设置-批量删除", notes="贷款审批额度设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkspedszService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款审批额度设置-通过id查询")
	@ApiOperation(value="贷款审批额度设置-通过id查询", notes="贷款审批额度设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dkspedsz dkspedsz = dkspedszService.getById(id);
		return Result.ok(dkspedsz);
	}

	 /**
	  * 通过zzbz查询
	  *
	  * @param zzbz
	  * @return
	  */
	 @ApiOperation(value="贷款审批额度设置-通过zzbz查询", notes="贷款审批额度设置-通过zzbz查询")
	 @GetMapping(value = "/queryByZzbz")
	 public Result<?> queryByZzbz(@RequestParam(name="zzbz",required=true) String zzbz) {
	 	 QueryWrapper<Dkspedsz> queryWrapper = new QueryWrapper<>();
	 	 queryWrapper.eq("zzbz", zzbz);
		 Dkspedsz dkspedsz = dkspedszService.getOne(queryWrapper);
		 return Result.ok(dkspedsz);
	 }

  /**
   * 导出excel
   *
   * @param request
   * @param dkspedsz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dkspedsz dkspedsz) {
      return super.exportXls(request, dkspedsz, Dkspedsz.class, "贷款审批额度设置");
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
		  params.setNeedSave(true);
		  FileOutputStream fos = null;
		  try {
//              List<Khdjpd> listKhdjpds = ExcelImportUtil.importExcel(file.getInputStream(), Khdjpd.class, params);
			  ExcelImportResult<Dkspedsz> importResult = ExcelImportUtil.importExcelVerify(file, Dkspedsz.class,DkspedszImport.class, params);
			  List<Dkspedsz> list = importResult.getList();
			  dkspedszService.saveBatch(list);
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

	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "贷款审批额度设置导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, DkspedszImport.class);
		 ExportParams exportParams = new ExportParams("贷款审批额度设置导入模板", "模板信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<DkspedszImport>());
		 return mv;
	 }
}
