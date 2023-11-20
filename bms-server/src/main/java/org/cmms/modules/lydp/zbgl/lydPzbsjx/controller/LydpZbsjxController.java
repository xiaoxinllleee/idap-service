package org.cmms.modules.lydp.zbgl.lydPzbsjx.controller;

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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.lydp.zbgl.lydPzbsjx.entity.LydpZbsjx;
import org.cmms.modules.lydp.zbgl.lydPzbsjx.entity.LydpZbsjxVO;
import org.cmms.modules.lydp.zbgl.lydPzbsjx.service.ILydpZbsjxService;
import org.cmms.modules.lydp.zbgl.lydPzbsjx.verify.ZbsjxImportVerify;
import org.cmms.modules.report.zbgl.zbsjx.entity.Zbsjxgl;
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
 * @Description: 浏阳大屏指标数据项
 * @Author: jeecg-boot
 * @Date:   2023-02-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags="浏阳大屏指标数据项")
@RestController
@RequestMapping("/lydpZbsjx/lydpZbsjx")
public class LydpZbsjxController extends JeecgController<LydpZbsjx, ILydpZbsjxService> {
	@Autowired
	private ILydpZbsjxService lydpZbsjxService;
	@Autowired
	private ZbsjxImportVerify zbsjxImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param lydpZbsjx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "浏阳大屏指标数据项-分页列表查询")
	@ApiOperation(value="浏阳大屏指标数据项-分页列表查询", notes="浏阳大屏指标数据项-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(LydpZbsjx lydpZbsjx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<LydpZbsjx> queryWrapper = QueryGenerator.initQueryWrapper(lydpZbsjx, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(ILydpZbsjxService.class,lydpZbsjxService,pageNo,pageSize,queryWrapper,"zbid");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param lydpZbsjx
	 * @return
	 */
	@AutoLog(value = "浏阳大屏指标数据项-添加")
	@ApiOperation(value="浏阳大屏指标数据项-添加", notes="浏阳大屏指标数据项-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody LydpZbsjx lydpZbsjx) {
		lydpZbsjxService.save(lydpZbsjx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param lydpZbsjx
	 * @return
	 */
	@AutoLog(value = "浏阳大屏指标数据项-编辑")
	@ApiOperation(value="浏阳大屏指标数据项-编辑", notes="浏阳大屏指标数据项-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody LydpZbsjx lydpZbsjx) {
		lydpZbsjxService.updateById(lydpZbsjx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "浏阳大屏指标数据项-通过id删除")
	@ApiOperation(value="浏阳大屏指标数据项-通过id删除", notes="浏阳大屏指标数据项-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		lydpZbsjxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "浏阳大屏指标数据项-批量删除")
	@ApiOperation(value="浏阳大屏指标数据项-批量删除", notes="浏阳大屏指标数据项-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.lydpZbsjxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "浏阳大屏指标数据项-通过id查询")
	@ApiOperation(value="浏阳大屏指标数据项-通过id查询", notes="浏阳大屏指标数据项-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		LydpZbsjx lydpZbsjx = lydpZbsjxService.getById(id);
		return Result.ok(lydpZbsjx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param lydpZbsjx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, LydpZbsjx lydpZbsjx) {
      return super.exportXls(request, lydpZbsjx, LydpZbsjx.class, "浏阳大屏指标数据项");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "浏阳大屏指标数据项导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, LydpZbsjxVO.class);
		 ExportParams exportParams = new ExportParams("浏阳大屏指标数据项导入模板", "模板信息");
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
		  if (zbsjxImportVerify != null) {
			  params.setVerifyHanlder(zbsjxImportVerify);
		  }
		  FileOutputStream fos = null;
		  try {
			  ExcelImportResult<LydpZbsjx> importResult = ExcelImportUtil.importExcelVerify(file, LydpZbsjx.class, LydpZbsjxVO.class,params);
			  List<LydpZbsjx> list = importResult.getList();
			  List<LydpZbsjx> qkmbList = new ArrayList<>();
			  for (LydpZbsjx qkmb : list) {
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
