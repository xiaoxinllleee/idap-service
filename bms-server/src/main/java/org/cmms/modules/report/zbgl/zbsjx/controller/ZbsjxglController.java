package org.cmms.modules.report.zbgl.zbsjx.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.report.sgtzgl.dkffdjbw.entity.DkffdjbW;
import org.cmms.modules.report.zbgl.zbsjx.entity.Zbsjxgl;
import org.cmms.modules.report.zbgl.zbsjx.entity.ZbsjxglVo;
import org.cmms.modules.report.zbgl.zbsjx.service.IZbsjxglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
 * @Description: 指标数据项管理
 * @Author: jeecg-boot
 * @Date:   2022-03-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="指标数据项管理")
@RestController
@RequestMapping("/zbgl/zbsjxgl")
public class ZbsjxglController extends JeecgController<Zbsjxgl, IZbsjxglService> {
	@Autowired
	private IZbsjxglService zbsjxglService;

	/**
	 * 分页列表查询
	 *
	 * @param zbsjxgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "指标数据项管理-分页列表查询")
	@ApiOperation(value="指标数据项管理-分页列表查询", notes="指标数据项管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zbsjxgl zbsjxgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Zbsjxgl> queryWrapper = QueryGenerator.initQueryWrapper(zbsjxgl, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IZbsjxglService.class,zbsjxglService,pageNo,pageSize,queryWrapper,"zbid");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param zbsjxgl
	 * @return
	 */
	@AutoLog(value = "指标数据项管理-添加")
	@ApiOperation(value="指标数据项管理-添加", notes="指标数据项管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zbsjxgl zbsjxgl) {
		zbsjxglService.save(zbsjxgl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param zbsjxgl
	 * @return
	 */
	@AutoLog(value = "指标数据项管理-编辑")
	@ApiOperation(value="指标数据项管理-编辑", notes="指标数据项管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zbsjxgl zbsjxgl) {
		zbsjxglService.updateById(zbsjxgl);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "指标数据项管理-通过id删除")
	@ApiOperation(value="指标数据项管理-通过id删除", notes="指标数据项管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zbsjxglService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "指标数据项管理-批量删除")
	@ApiOperation(value="指标数据项管理-批量删除", notes="指标数据项管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zbsjxglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "指标数据项管理-通过id查询")
	@ApiOperation(value="指标数据项管理-通过id查询", notes="指标数据项管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zbsjxgl zbsjxgl = zbsjxglService.getById(id);
		return Result.ok(zbsjxgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zbsjxgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zbsjxgl zbsjxgl) {
      return super.exportXls(request, zbsjxgl, Zbsjxgl.class, "指标数据项管理");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "指标数据项管理导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, ZbsjxglVo.class);
		 ExportParams exportParams = new ExportParams("指标数据项管理导入模板", "模板信息");
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
				 ExcelImportResult<Zbsjxgl> importResult = ExcelImportUtil.importExcelVerify(file, Zbsjxgl.class,ZbsjxglVo.class, params);
				 List<Zbsjxgl> list = importResult.getList();
				 List<Zbsjxgl> qkmbList = new ArrayList<>();
				 for (Zbsjxgl qkmb : list) {
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
