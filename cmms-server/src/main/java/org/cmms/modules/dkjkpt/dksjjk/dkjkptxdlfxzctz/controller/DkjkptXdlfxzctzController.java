package org.cmms.modules.dkjkpt.dksjjk.dkjkptxdlfxzctz.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
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
import org.cmms.modules.dkjkpt.dksjjk.dkjkptxdlfxzctz.entity.DkjkptXdlfxzctz;
import org.cmms.modules.dkjkpt.dksjjk.dkjkptxdlfxzctz.entity.DkjkptXdlfxzctzVO;
import org.cmms.modules.dkjkpt.dksjjk.dkjkptxdlfxzctz.service.IDkjkptXdlfxzctzService;
import org.cmms.modules.dkjkpt.dksjjk.dkjkptxdlfxzctz.verify.XdlfxzctzImportVerify;
import org.cmms.modules.khxxgl.wbsjgl.tpjchmd.entity.Tpjchmd;
import org.cmms.modules.tjfx.qhywjd.qhsxqk.entity.TjfxQhsxqk;
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
 * @Description: 信贷类风险资产台账
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
@Slf4j
@Api(tags="信贷类风险资产台账")
@RestController
@RequestMapping("/dkjkptxdlfxzctz/dkjkptXdlfxzctz")
public class DkjkptXdlfxzctzController extends JeecgController<DkjkptXdlfxzctz, IDkjkptXdlfxzctzService> {
	@Autowired
	private IDkjkptXdlfxzctzService dkjkptXdlfxzctzService;
	@Autowired
	private XdlfxzctzImportVerify importVerify;

	/**
	 * 分页列表查询
	 *
	 * @param dkjkptXdlfxzctz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "信贷类风险资产台账-分页列表查询")
	@ApiOperation(value="信贷类风险资产台账-分页列表查询", notes="信贷类风险资产台账-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DkjkptXdlfxzctz dkjkptXdlfxzctz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DkjkptXdlfxzctz> queryWrapper = QueryGenerator.initQueryWrapper(dkjkptXdlfxzctz, req.getParameterMap());
		Page<DkjkptXdlfxzctz> page = new Page<DkjkptXdlfxzctz>(pageNo, pageSize);
		IPage<DkjkptXdlfxzctz> pageList = dkjkptXdlfxzctzService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dkjkptXdlfxzctz
	 * @return
	 */
	@AutoLog(value = "信贷类风险资产台账-添加")
	@ApiOperation(value="信贷类风险资产台账-添加", notes="信贷类风险资产台账-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DkjkptXdlfxzctz dkjkptXdlfxzctz) {
		dkjkptXdlfxzctzService.save(dkjkptXdlfxzctz);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dkjkptXdlfxzctz
	 * @return
	 */
	@AutoLog(value = "信贷类风险资产台账-编辑")
	@ApiOperation(value="信贷类风险资产台账-编辑", notes="信贷类风险资产台账-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DkjkptXdlfxzctz dkjkptXdlfxzctz) {
		dkjkptXdlfxzctzService.updateById(dkjkptXdlfxzctz);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "信贷类风险资产台账-通过id删除")
	@ApiOperation(value="信贷类风险资产台账-通过id删除", notes="信贷类风险资产台账-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkjkptXdlfxzctzService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "信贷类风险资产台账-批量删除")
	@ApiOperation(value="信贷类风险资产台账-批量删除", notes="信贷类风险资产台账-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkjkptXdlfxzctzService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "信贷类风险资产台账-通过id查询")
	@ApiOperation(value="信贷类风险资产台账-通过id查询", notes="信贷类风险资产台账-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DkjkptXdlfxzctz dkjkptXdlfxzctz = dkjkptXdlfxzctzService.getById(id);
		return Result.ok(dkjkptXdlfxzctz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkjkptXdlfxzctz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DkjkptXdlfxzctz dkjkptXdlfxzctz) {
      return super.exportXls(request, dkjkptXdlfxzctz, DkjkptXdlfxzctz.class, "信贷类风险资产台账");
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
		 mv.addObject(NormalExcelConstants.FILE_NAME, "信贷类风险资产台账导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, DkjkptXdlfxzctzVO.class);
		 ExportParams exportParams = new ExportParams("信贷类风险资产台账导入模板", "模板信息");
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
//          MultipartFile file = entity.getValue();// 获取上传文件对象
		  File file = new File(baseFilePath);
		  ImportParams params = new ImportParams();
		  params.setTitleRows(1);
		  params.setHeadRows(1);
		  params.setNeedSave(false);
		  if (importVerify != null) {
			  params.setVerifyHanlder(importVerify);
		  }
		  FileOutputStream fos = null;
		  FileInputStream fis = null;
		  try {
			  fis = new FileInputStream(file);
			  ExcelImportResult<DkjkptXdlfxzctz> importResult = ExcelImportUtil.importExcelVerify(file, DkjkptXdlfxzctz.class, DkjkptXdlfxzctzVO.class,params ,false);
			  List<DkjkptXdlfxzctz> list = importResult.getList();
			  List<DkjkptXdlfxzctz> dkjkptXdlfxzctzs = new ArrayList<>();
			  for (DkjkptXdlfxzctz zzsfpxx : list) {
				  dkjkptXdlfxzctzs.add(zzsfpxx);
			  }
			  service.saveBatch(list);
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
	  return Result.ok("文件导入失败！");
  }

}
