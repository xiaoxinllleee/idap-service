package org.cmms.modules.khgl.jzyx.yxzfqd.controller;

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
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khgl.jzyx.yxzfqd.entity.VYxzfqd;
import org.cmms.modules.khgl.jzyx.yxzfqd.entity.Yxzfqd;
import org.cmms.modules.khgl.jzyx.yxzfqd.entity.YxzfqdVo;
import org.cmms.modules.khgl.jzyx.yxzfqd.service.IVYxzfqdService;
import org.cmms.modules.khgl.jzyx.yxzfqd.service.IYxzfqdService;
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
 * @Description: 优先走访清单视图
 * @Author: jeecg-boot
 * @Date:   2023-05-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="优先走访清单视图")
@RestController
@RequestMapping("/jzyx/vYxzfqd")
public class VYxzfqdController extends JeecgController<VYxzfqd, IVYxzfqdService> {
	@Autowired
	private IVYxzfqdService vYxzfqdService;
	@Autowired
	private IYxzfqdService yxzfqdService;
	
	/**
	 * 分页列表查询
	 *
	 * @param vYxzfqd
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "优先走访清单视图-分页列表查询")
	@ApiOperation(value="优先走访清单视图-分页列表查询", notes="优先走访清单视图-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VYxzfqd vYxzfqd,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VYxzfqd> queryWrapper = QueryGenerator.initQueryWrapper(vYxzfqd, req.getParameterMap());
		Page<VYxzfqd> page = new Page<VYxzfqd>(pageNo, pageSize);
		IPage<VYxzfqd> pageList = vYxzfqdService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param vYxzfqd
	 * @return
	 */
	@AutoLog(value = "优先走访清单视图-添加")
	@ApiOperation(value="优先走访清单视图-添加", notes="优先走访清单视图-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody VYxzfqd vYxzfqd) {
		vYxzfqdService.save(vYxzfqd);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param vYxzfqd
	 * @return
	 */
	@AutoLog(value = "优先走访清单视图-编辑")
	@ApiOperation(value="优先走访清单视图-编辑", notes="优先走访清单视图-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody VYxzfqd vYxzfqd) {
		vYxzfqdService.updateById(vYxzfqd);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "优先走访清单视图-通过id删除")
	@ApiOperation(value="优先走访清单视图-通过id删除", notes="优先走访清单视图-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		vYxzfqdService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "优先走访清单视图-批量删除")
	@ApiOperation(value="优先走访清单视图-批量删除", notes="优先走访清单视图-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.vYxzfqdService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "优先走访清单视图-通过id查询")
	@ApiOperation(value="优先走访清单视图-通过id查询", notes="优先走访清单视图-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VYxzfqd vYxzfqd = vYxzfqdService.getById(id);
		return Result.ok(vYxzfqd);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param vYxzfqd
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, VYxzfqd vYxzfqd) {
      return super.exportXls(request, vYxzfqd, VYxzfqd.class, "优先走访清单");
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
		  File file = new File(baseFilePath);
		  ImportParams params = new ImportParams();
		  params.setTitleRows(1);
		  params.setHeadRows(1);
		  params.setNeedSave(false);
		  FileOutputStream fos = null;
		  FileInputStream fis = null;
		  try {
			  fis = new FileInputStream(file);
			  boolean checkResult = ExcelImportCheckUtil.check(fis, YxzfqdVo.class, params);
			  if (!checkResult) {
				  return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
			  }
			  ExcelImportResult<YxzfqdVo> importResult = ExcelImportUtil.importExcelVerify(file, YxzfqdVo.class, params);
			  List<YxzfqdVo> list = importResult.getList();
			  List<Yxzfqd> list1 =new ArrayList<>();
			  for(YxzfqdVo yxzfqdVo : list){
				  Yxzfqd yxzfqd = new Yxzfqd();
				  BeanUtils.copyProperties(yxzfqdVo, yxzfqd);
				  QueryWrapper<Yxzfqd> queryWrapper=new QueryWrapper();
				  queryWrapper.eq("zjhm",yxzfqd.getZjhm());
				  yxzfqdService.remove(queryWrapper);
				  list1.add(yxzfqd);
			  }
			  yxzfqdService.saveBatch(list1);
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "优先走访清单导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, YxzfqdVo.class);
		 ExportParams exportParams = new ExportParams("优先走访清单导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
	 }

}
