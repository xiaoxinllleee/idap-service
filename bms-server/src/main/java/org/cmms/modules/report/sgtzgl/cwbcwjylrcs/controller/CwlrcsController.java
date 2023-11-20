package org.cmms.modules.report.sgtzgl.cwbcwjylrcs.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.cwbcwjylrcs.entity.Cwlrcs;
import org.cmms.modules.report.sgtzgl.cwbcwjylrcs.entity.CwlrcsVo;
import org.cmms.modules.report.sgtzgl.cwbcwjylrcs.service.ICwlrcsService;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 财务部_手工台账_经营利润测算
 * @Author: jeecg-boot
 * @Date:   2022-12-04
 * @Version: V1.0
 */
@Slf4j
@Api(tags="财务部_手工台账_经营利润测算")
@RestController
@RequestMapping("/report/sgtzgl/cwbcwjylrcs/cwlrcs")
public class CwlrcsController extends JeecgController<Cwlrcs, ICwlrcsService> {
	@Autowired
	private ICwlrcsService cwlrcsService;

	/**
	 * 分页列表查询
	 *
	 * @param cwlrcs
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "财务部_手工台账_经营利润测算-分页列表查询")
	@ApiOperation(value="财务部_手工台账_经营利润测算-分页列表查询", notes="财务部_手工台账_经营利润测算-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Cwlrcs cwlrcs,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Cwlrcs> queryWrapper = QueryGenerator.initQueryWrapper(cwlrcs, req.getParameterMap());
		Page<Cwlrcs> page = new Page<Cwlrcs>(pageNo, pageSize);
		IPage pageList= PageUtil.toPage(ICwlrcsService.class,cwlrcsService,pageNo,pageSize,queryWrapper,"fiscal_date","jgdm");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param cwlrcs
	 * @return
	 */
	@AutoLog(value = "财务部_手工台账_经营利润测算-添加")
	@ApiOperation(value="财务部_手工台账_经营利润测算-添加", notes="财务部_手工台账_经营利润测算-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Cwlrcs cwlrcs) {
		cwlrcsService.save(cwlrcs);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param cwlrcs
	 * @return
	 */
	@AutoLog(value = "财务部_手工台账_经营利润测算-编辑")
	@ApiOperation(value="财务部_手工台账_经营利润测算-编辑", notes="财务部_手工台账_经营利润测算-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Cwlrcs cwlrcs) {
		QueryWrapper<Cwlrcs> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("FISCAL_DATE",cwlrcs.getFiscalDate()).eq("JGDM",cwlrcs.getJgdm());
		cwlrcsService.update(cwlrcs,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "财务部_手工台账_经营利润测算-通过数据日期和机构代码删除")
	@ApiOperation(value="财务部_手工台账_经营利润测算-通过数据日期和机构代码删除", notes="财务部_手工台账_经营利润测算-通过数据日期和机构代码删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="fiscalDate") String fiscalDate,@RequestParam(name ="jgdm") String jgdm) {
		QueryWrapper<Cwlrcs> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("FISCAL_DATE",fiscalDate).eq("JGDM",jgdm);
		cwlrcsService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "财务部_手工台账_经营利润测算-批量删除")
	@ApiOperation(value="财务部_手工台账_经营利润测算-批量删除", notes="财务部_手工台账_经营利润测算-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.cwlrcsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	 /**
	  * 根据日期批量删除
	  *
	  * @param
	  * @return
	  */
	 @AutoLog(value = "财务部_手工台账_经营利润测算-批量删除")
	 @ApiOperation(value="财务部_手工台账_经营利润测算-批量删除", notes="财务部_手工台账_经营利润测算-批量删除")
	 @DeleteMapping(value = "/deleteByBatch")
	 public Result<?> deleteByBatch(@Param("fiscalDate") String fiscalDate) {
		 QueryWrapper<Cwlrcs> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("FISCAL_DATE",fiscalDate);
		 this.cwlrcsService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "财务部_手工台账_经营利润测算-通过id查询")
	@ApiOperation(value="财务部_手工台账_经营利润测算-通过id查询", notes="财务部_手工台账_经营利润测算-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Cwlrcs cwlrcs = cwlrcsService.getById(id);
		return Result.ok(cwlrcs);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param cwlrcs
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Cwlrcs cwlrcs) {
      return super.exportXls(request, cwlrcs, Cwlrcs.class, "财务部_手工台账_经营利润测算");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "财务经营利润测试导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, CwlrcsVo.class);
		 ExportParams exportParams = new ExportParams("财务经营利润测试导入模板", "模板信息");
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
				 ExcelImportResult<Cwlrcs> importResult = ExcelImportUtil.importExcelVerify(file, Cwlrcs.class,CwlrcsVo.class, params);
				 List<Cwlrcs> list = importResult.getList();
				 List<Cwlrcs> qkmbList = new ArrayList<>();
				 for (Cwlrcs qkmb : list) {
					 qkmb.setFiscalDate(fiscalDate);
					 qkmbList.add(qkmb);
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
