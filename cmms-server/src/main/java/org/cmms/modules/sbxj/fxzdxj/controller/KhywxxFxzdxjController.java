package org.cmms.modules.sbxj.fxzdxj.controller;

import java.io.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.UUIDGenerator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.sbxj.fxzdxj.entity.KhywxxFxzdxj;
import org.cmms.modules.sbxj.fxzdxj.entity.KhywxxFxzdxjImportVO;
import org.cmms.modules.sbxj.fxzdxj.service.IKhywxxFxzdxjService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 福祥站点主表
 * @Author: jeecg-boot
 * @Date:   2023-06-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="福祥站点主表")
@RestController
@RequestMapping("/fxzdxj/khywxxFxzdxj")
public class KhywxxFxzdxjController extends JeecgController<KhywxxFxzdxj, IKhywxxFxzdxjService> {
	@Autowired
	private IKhywxxFxzdxjService khywxxFxzdxjService;
	 @Autowired
	 IDictValueQuery iDictValueQuery;

	/**
	 * 分页列表查询
	 *
	 * @param khywxxFxzdxj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "福祥站点主表-分页列表查询")
	@ApiOperation(value="福祥站点主表-分页列表查询", notes="福祥站点主表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KhywxxFxzdxj khywxxFxzdxj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<KhywxxFxzdxj> queryWrapper = QueryGenerator.initQueryWrapper(khywxxFxzdxj, req.getParameterMap());
		Page<KhywxxFxzdxj> page = new Page<KhywxxFxzdxj>(pageNo, pageSize);
		IPage<KhywxxFxzdxj> pageList = khywxxFxzdxjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
	  * pad
	  * @param khywxxFxzdxj
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	@AutoLog(value = "福祥站点主表-分页列表查询")
	@ApiOperation(value="福祥站点主表-分页列表查询", notes="福祥站点主表-分页列表查询")
	@GetMapping(value = "/appList")
	public Result<?> queryAppList(KhywxxFxzdxj khywxxFxzdxj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<KhywxxFxzdxj> queryWrapper = QueryGenerator.initQueryWrapper(khywxxFxzdxj, req.getParameterMap());
		Page<KhywxxFxzdxj> page = new Page<KhywxxFxzdxj>(pageNo, pageSize);
		IPage<KhywxxFxzdxj> pageList = khywxxFxzdxjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param khywxxFxzdxj
	 * @return
	 */
	@AutoLog(value = "福祥站点主表-添加")
	@ApiOperation(value="福祥站点主表-添加", notes="福祥站点主表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody KhywxxFxzdxj khywxxFxzdxj) {
		khywxxFxzdxj.setZdbh(iDictValueQuery.getSeqNextval("SEQ_FXZDXJ_ZDBH.nextval"));
		khywxxFxzdxj.setCreateBy(getUsername());
		khywxxFxzdxj.setCreateTime(new Date());
		khywxxFxzdxjService.save(khywxxFxzdxj);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param khywxxFxzdxj
	 * @return
	 */
	@AutoLog(value = "福祥站点主表-编辑")
	@ApiOperation(value="福祥站点主表-编辑", notes="福祥站点主表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhywxxFxzdxj khywxxFxzdxj) {
		khywxxFxzdxj.setUpdateBy(getUsername());
		khywxxFxzdxj.setUpdateTime(new Date());
		khywxxFxzdxjService.updateById(khywxxFxzdxj);
		return Result.ok("编辑成功!");
	}
	 /**
	  * 编辑
	  *
	  * @param khywxxFxzdxj
	  * @return
	  */
	 @AutoLog(value = "福祥站点主表-编辑")
	 @ApiOperation(value="福祥站点主表-编辑", notes="福祥站点主表-编辑")
	 @PutMapping(value = "/editApp")
	 public Result<?> editApp(KhywxxFxzdxj khywxxFxzdxj) {
		 khywxxFxzdxj.setUpdateBy(getUsername());
		 khywxxFxzdxj.setUpdateTime(new Date());
		 khywxxFxzdxjService.updateById(khywxxFxzdxj);
		 return Result.ok("编辑成功!");
	 }




	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "福祥站点主表-通过id删除")
	@ApiOperation(value="福祥站点主表-通过id删除", notes="福祥站点主表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khywxxFxzdxjService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "福祥站点主表-批量删除")
	@ApiOperation(value="福祥站点主表-批量删除", notes="福祥站点主表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khywxxFxzdxjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "福祥站点主表-通过id查询")
	@ApiOperation(value="福祥站点主表-通过id查询", notes="福祥站点主表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		KhywxxFxzdxj khywxxFxzdxj = khywxxFxzdxjService.getById(id);
		return Result.ok(khywxxFxzdxj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khywxxFxzdxj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, KhywxxFxzdxj khywxxFxzdxj) {
      return super.exportXls(request, khywxxFxzdxj, KhywxxFxzdxj.class, "福祥站点主表");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "福祥站点巡检导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, KhywxxFxzdxjImportVO.class);
		 ExportParams exportParams = new ExportParams("福祥站点巡检导入模板", "模板信息");
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
/*  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
	  return super.importExcelByTemplate(jsonObject, request, response, KhywxxFxzdxj.class, fxzdxjImportVerify);
  }*/
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
//          MultipartFile file = entity.getValue();// 获取上传文件对象
		  File file = new File(baseFilePath);
		  ImportParams params = new ImportParams();
		  params.setTitleRows(1);
		  params.setHeadRows(1);
		  params.setNeedSave(false);

		  FileOutputStream fos = null;
		  FileInputStream fis = null;
		  try {
			  fis = new FileInputStream(file);
			  boolean checkResult = ExcelImportCheckUtil.check(fis, KhywxxFxzdxjImportVO.class, params,1.0);
			  if (!checkResult) {
				  return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
			  }
			  ExcelImportResult<KhywxxFxzdxjImportVO> importResult = ExcelImportUtil.importExcelVerify(file, KhywxxFxzdxjImportVO.class, params);
			  List<KhywxxFxzdxj> records = new ArrayList<>();
			  List<KhywxxFxzdxjImportVO> list = importResult.getList();
			  for (KhywxxFxzdxjImportVO khsz : list) {
				  KhywxxFxzdxj record = new KhywxxFxzdxj();
				  BeanUtil.copyPropertiesIgnoreNull(khsz, record);
				  record.setId(UUIDGenerator.generate());
				  record.setZdbh(iDictValueQuery.getSeqNextval("SEQ_FXZDXJ_ZDBH.nextval"));
				  record.setCreateTime(new Date());
				  record.setCreateBy(getLoginUser().getUsername());
				  records.add(record);
			  }
			  if (CollUtil.isNotEmpty(records)) {
				  for (KhywxxFxzdxj record : records) {
					  UpdateWrapper<KhywxxFxzdxj> queryWrapper = new UpdateWrapper<>();
					  queryWrapper.eq("sszh",record.getSszh());
					  queryWrapper.eq("zdbh",record.getZdbh());
					  queryWrapper.eq("zdmc",record.getZdmc());
					  queryWrapper.eq("rwsj",record.getRwsj());
					  khywxxFxzdxjService.remove(queryWrapper);
				  }
				  khywxxFxzdxjService.saveBatch(records);
			  }
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
