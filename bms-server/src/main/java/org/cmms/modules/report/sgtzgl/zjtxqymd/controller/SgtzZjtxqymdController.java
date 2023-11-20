package org.cmms.modules.report.sgtzgl.zjtxqymd.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.util.StringUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.cfywmxb.entity.SgtzglCfywmxb;
import org.cmms.modules.report.sgtzgl.cfywmxb.entity.SgtzglCfywmxbVO;
import org.cmms.modules.report.sgtzgl.zhsdhqktjb.service.ISgztZhsdhqktjbService;
import org.cmms.modules.report.sgtzgl.zjtxqymd.entity.SgtzZjtxqymd;
import org.cmms.modules.report.sgtzgl.zjtxqymd.entity.SgtzZjtxqymdVO;
import org.cmms.modules.report.sgtzgl.zjtxqymd.service.ISgtzZjtxqymdService;
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
 * @Description: 专精特性企业名单
 * @Author: jeecg-boot
 * @Date:   2023-08-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="专精特性企业名单")
@RestController
@RequestMapping("/zjtxqymd/sgtzZjtxqymd")
public class SgtzZjtxqymdController extends JeecgController<SgtzZjtxqymd, ISgtzZjtxqymdService> {
	@Autowired
	private ISgtzZjtxqymdService sgtzZjtxqymdService;

	/**
	 * 分页列表查询
	 *
	 * @param sgtzZjtxqymd
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "专精特性企业名单-分页列表查询")
	@ApiOperation(value="专精特性企业名单-分页列表查询", notes="专精特性企业名单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgtzZjtxqymd sgtzZjtxqymd,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgtzZjtxqymd> queryWrapper = QueryGenerator.initQueryWrapper(sgtzZjtxqymd, req.getParameterMap());
		Page<SgtzZjtxqymd> page = new Page<SgtzZjtxqymd>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ISgtzZjtxqymdService.class,sgtzZjtxqymdService,pageNo,pageSize,queryWrapper,"fiscal_date");

		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgtzZjtxqymd
	 * @return
	 */
	@AutoLog(value = "专精特性企业名单-添加")
	@ApiOperation(value="专精特性企业名单-添加", notes="专精特性企业名单-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgtzZjtxqymd sgtzZjtxqymd) {
		sgtzZjtxqymdService.save(sgtzZjtxqymd);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgtzZjtxqymd
	 * @return
	 */
	@AutoLog(value = "专精特性企业名单-编辑")
	@ApiOperation(value="专精特性企业名单-编辑", notes="专精特性企业名单-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgtzZjtxqymd sgtzZjtxqymd) {
		sgtzZjtxqymdService.updateById(sgtzZjtxqymd);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "专精特性企业名单-通过id删除")
	@ApiOperation(value="专精特性企业名单-通过id删除", notes="专精特性企业名单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sgtzZjtxqymdService.removeById(id);
		return Result.ok("删除成功!");
	}
	 /**
	  * 根据日期批量删除
	  * @param fiscalDate
	  * @return
	  */
	 @AutoLog(value = "专精特性企业名单-批量删除")
	 @ApiOperation(value="专精特性企业名单-批量删除", notes="专精特性企业名单-批量删除")
	 @DeleteMapping(value = "/deleteByBatch")
	 public Result<?> deleteByBatch(@Param("fiscalDate") String fiscalDate) {
		 QueryWrapper<SgtzZjtxqymd> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("fiscal_date",fiscalDate);
		 sgtzZjtxqymdService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "专精特性企业名单-批量删除")
	@ApiOperation(value="专精特性企业名单-批量删除", notes="专精特性企业名单-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgtzZjtxqymdService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "专精特性企业名单-通过id查询")
	@ApiOperation(value="专精特性企业名单-通过id查询", notes="专精特性企业名单-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SgtzZjtxqymd sgtzZjtxqymd = sgtzZjtxqymdService.getById(id);
		return Result.ok(sgtzZjtxqymd);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgtzZjtxqymd
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgtzZjtxqymd sgtzZjtxqymd) {
      return super.exportXls(request, sgtzZjtxqymd, SgtzZjtxqymd.class, "专精特性企业名单");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "专精特性企业名单导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgtzZjtxqymdVO.class);
		 ExportParams exportParams = new ExportParams("专精特性企业名单导入模板", "模板信息");
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
		  FileOutputStream fos = null;
		  try {
			  ExcelImportResult<SgtzZjtxqymd> importResult = ExcelImportUtil.importExcelVerify(file, SgtzZjtxqymd.class, SgtzZjtxqymdVO.class, params);
			  List<SgtzZjtxqymd> list = importResult.getList();
			  List<SgtzZjtxqymd> qkmbList = new ArrayList<>();
			  for (SgtzZjtxqymd qkmb : list) {
				  Boolean con1= StringUtils.isBlank(qkmb.getTyshxydm());
				  Boolean con2=StringUtils.isNotBlank(qkmb.getXh()) && (qkmb.getXh().contains("合计") || qkmb.getXh().contains("条数"));
				  if (con1 || con2 ) {
					  continue;
				  }
				  qkmb.setFiscalDate(fiscalDate);
				  qkmbList.add(qkmb);
			  }
			  service.saveBatch(qkmbList);
			  obj.put("filePath", filePath);
			  fos = new FileOutputStream(baseFilePath);
			  importResult.getWorkbook().write(fos);
			  fos.flush();
			  fos.close();
			  return Result.ok("文件导入完成！成功导入数据行数:" + qkmbList.size(), obj);
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
