package org.cmms.modules.report.cwbb.bwkmb.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.report.cwbb.bwkmb.entity.Bwkmb;
import org.cmms.modules.report.cwbb.bwkmb.service.IBwkmbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.cwbb.bwkmb.verify.BwkmbImportVerify;
import org.cmms.modules.report.cwbb.ywzkb.entity.Ywzkb;
import org.cmms.modules.report.cwbb.ywzkb.entity.YwzkbVo;
import org.cmms.modules.report.tzsjgl.xtdkqmx.entity.Xtdkqmx;
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
 * @Description: 表外科目表
 * @Author: jeecg-boot
 * @Date:   2022-05-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="表外科目表")
@RestController
@RequestMapping("/bwkmb/bwkmb")
public class BwkmbController extends JeecgController<Bwkmb, IBwkmbService> {
	@Autowired
	private IBwkmbService bwkmbService;
	 @Autowired
	 private BwkmbImportVerify bwkmbImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param bwkmb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "表外科目表-分页列表查询")
	@ApiOperation(value="表外科目表-分页列表查询", notes="表外科目表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Bwkmb bwkmb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Bwkmb> queryWrapper = QueryGenerator.initQueryWrapper(bwkmb, req.getParameterMap());
		Page<Bwkmb> page = new Page<Bwkmb>(pageNo, pageSize);
		IPage<Bwkmb> pageList = bwkmbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param bwkmb
	 * @return
	 */
	@AutoLog(value = "表外科目表-添加")
	@ApiOperation(value="表外科目表-添加", notes="表外科目表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Bwkmb bwkmb) {
		bwkmbService.save(bwkmb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param bwkmb
	 * @return
	 */
	@AutoLog(value = "表外科目表-编辑")
	@ApiOperation(value="表外科目表-编辑", notes="表外科目表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Bwkmb bwkmb) {
		bwkmbService.updateById(bwkmb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "表外科目表-通过id删除")
	@ApiOperation(value="表外科目表-通过id删除", notes="表外科目表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		bwkmbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param sjrq
	 * @return
	 */
	@AutoLog(value = "表外科目表-批量删除")
	@ApiOperation(value="表外科目表-批量删除", notes="表外科目表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@Param("sjrq") String sjrq) {
		QueryWrapper<Bwkmb> queryWrapper = new QueryWrapper<>();
		DateTime parse = DateUtil.parse(sjrq);
		queryWrapper.eq("sjrq",parse);
		bwkmbService.remove(queryWrapper);
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "表外科目表-通过id查询")
	@ApiOperation(value="表外科目表-通过id查询", notes="表外科目表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Bwkmb bwkmb = bwkmbService.getById(id);
		return Result.ok(bwkmb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param bwkmb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Bwkmb bwkmb) {
      return super.exportXls(request, bwkmb, Bwkmb.class, "表外科目表");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "表外科目表导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, Bwkmb.class);
		 ExportParams exportParams = new ExportParams("表外科目表导入模板", "模板信息");
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
 /* @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
	  String sjrq = request.getParameter("sjrq");
	  DateTime parse = DateUtil.parse(sjrq);
	  MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	  Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
	  for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
		  MultipartFile file = entity.getValue();// 获取上传文件对象
		  ImportParams params = new ImportParams();
		  params.setTitleRows(2);
		  params.setHeadRows(1);
		  params.setLastOfInvalidRow(2);
		  params.setNeedSave(true);
		  try {
			  List<Bwkmb> list = ExcelImportUtil.importExcel(file.getInputStream(), Bwkmb.class, params);
			  List<Bwkmb> bwkmbList=new ArrayList<>();
			  for(Bwkmb bwkmb:list){
				  bwkmb.setSjrq(parse);
				  bwkmbList.add(bwkmb);
			  }
			  //update-begin-author:taoyan date:20190528 for:批量插入数据
			  long start = System.currentTimeMillis();
			  service.saveBatch(bwkmbList);
			  log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
			  //update-end-author:taoyan date:20190528 for:批量插入数据
			  return Result.ok("文件导入成功！数据行数：" + list.size());
		  } catch (Exception e) {
			  log.error(e.getMessage(), e);
			  return Result.error("文件导入失败:" + e.getMessage());
		  } finally {
			  try {
				  file.getInputStream().close();
			  } catch (IOException e) {
				  e.printStackTrace();
			  }
		  }
	  }
	  return Result.error("文件导入失败！");
  }
*/
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
	  String sjrq = request.getParameter("sjrq");
	  System.out.println(sjrq + "----sjrq----");
	  Date parse = DateUtil.parse(sjrq);
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
		  if (bwkmbImportVerify != null) {
			  params.setVerifyHanlder(bwkmbImportVerify);
		  }
		  FileOutputStream fos = null;
		  try {
			  ExcelImportResult<Bwkmb> importResult = ExcelImportUtil.importExcelVerify(file, Bwkmb.class, params);
			  List<Bwkmb> list = importResult.getList();
			  List<Bwkmb> qkmbList = new ArrayList<>();
			  for (Bwkmb bwkmb : list) {
				  bwkmb.setSjrq(parse);
				  qkmbList.add(bwkmb);
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
