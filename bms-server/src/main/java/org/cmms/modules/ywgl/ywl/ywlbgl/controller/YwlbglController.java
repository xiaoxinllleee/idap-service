package org.cmms.modules.ywgl.ywl.ywlbgl.controller;

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
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ywgl.ywl.ywlbgl.entity.Ywlbgl;
import org.cmms.modules.ywgl.ywl.ywlbgl.entity.YwlbglVO;
import org.cmms.modules.ywgl.ywl.ywlbgl.service.IYwlbglService;
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
 * @Description: 业务类别管理
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags="业务类别管理")
@RestController
@RequestMapping("/ywlbgl/ywlbgl")
public class YwlbglController extends JeecgController<Ywlbgl, IYwlbglService> {
	@Autowired
	private IYwlbglService ywlbglService;

	/**
	 * 分页列表查询
	 *
	 * @param ywlbgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "业务类别管理-分页列表查询")
	@ApiOperation(value="业务类别管理-分页列表查询", notes="业务类别管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ywlbgl ywlbgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String ywlbmc= ywlbgl.getYwlbmc();
		ywlbgl.setYwlbmc(null);
		QueryWrapper<Ywlbgl> queryWrapper = QueryGenerator.initQueryWrapper(ywlbgl, req.getParameterMap());
		if (StringUtils.isNotBlank(ywlbmc)){
			queryWrapper.like("ywlbmc",ywlbmc);
		}
		Page<Ywlbgl> page = new Page<Ywlbgl>(pageNo, pageSize);

		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IYwlbglService.class,ywlbglService,pageNo,pageSize,queryWrapper,"ywlbdm" );
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param ywlbgl
	 * @return
	 */
	@AutoLog(value = "业务类别管理-添加")
	@ApiOperation(value="业务类别管理-添加", notes="业务类别管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ywlbgl ywlbgl) {
		QueryWrapper<Ywlbgl> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("ywlbdm", ywlbgl.getYwlbdm());
		List<Ywlbgl> list = ywlbglService.list(queryWrapper);
		if (!list.isEmpty()) {
			return Result.error("业务类别代码已经存在");
		}
		QueryWrapper<Ywlbgl> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.eq("dyjym", ywlbgl.getDyjym());
		List<Ywlbgl> list1 = ywlbglService.list(queryWrapper1);
		if (!list1.isEmpty()) {
			return Result.error("对应交易码已存在");
		}
		ywlbgl.setLrbz(1);
		ywlbgl.setLrsj(new Date());
		ywlbgl.setLrczy(getUsername());
		ywlbglService.save(ywlbgl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param ywlbgl
	 * @return
	 */
	@AutoLog(value = "业务类别管理-编辑")
	@ApiOperation(value="业务类别管理-编辑", notes="业务类别管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ywlbgl ywlbgl) {
		QueryWrapper<Ywlbgl> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("ywlbdm",ywlbgl.getYwlbdm());
		ywlbgl.setLrsj(new Date());
		ywlbgl.setLrczy(getUsername());
		ywlbgl.setLrbz(2);
		ywlbglService.update(ywlbgl,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "业务类别管理-通过id删除")
	@ApiOperation(value="业务类别管理-通过id删除", notes="业务类别管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("ywlbdm") String ywlbdm) {
		QueryWrapper<Ywlbgl> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("ywlbdm",ywlbdm);
		ywlbglService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "业务类别管理-批量删除")
	@ApiOperation(value="业务类别管理-批量删除", notes="业务类别管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		System.out.println(ids);
		List<String> list = Arrays.asList(ids.split(","));
		List<String> list1 = new ArrayList<>();
		for (String s : list) {
			list1.add(s);
		}
		System.out.println(list1+"===list1");
		QueryWrapper<Ywlbgl> queryWrapper = new QueryWrapper<>();
		queryWrapper.in("ywlbdm",list1);
		//this.ywlbglService.removeByIds(Arrays.asList(ids.split(",")));
		this.ywlbglService.remove(queryWrapper);
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "业务类别管理-通过id查询")
	@ApiOperation(value="业务类别管理-通过id查询", notes="业务类别管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ywlbgl ywlbgl = ywlbglService.getById(id);
		return Result.ok(ywlbgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ywlbgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ywlbgl ywlbgl) {
      return super.exportXls(request, ywlbgl, Ywlbgl.class, "业务类别管理");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "业务类别管理导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, YwlbglVO.class);
		 ExportParams exportParams = new ExportParams("业务类别管理导入模板", "模板信息");
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
			  ExcelImportResult<Ywlbgl> importResult = ExcelImportUtil.importExcelVerify(file, Ywlbgl.class,YwlbglVO.class, params);
			  List<Ywlbgl> list = importResult.getList();
			  List<Ywlbgl> ywlbgls = new ArrayList<>();
			  for (Ywlbgl ywlbgl : list) {
				  QueryWrapper<Ywlbgl> queryWrapper = new QueryWrapper<>();
				  queryWrapper.eq("ywlbdm", ywlbgl.getYwlbdm());
				  List<Ywlbgl> exist = ywlbglService.list(queryWrapper);
				  if (!exist.isEmpty()) {
					  ywlbglService.remove(queryWrapper);
				  }
			  	  ywlbgl.setLrsj(new Date());
				  ywlbgl.setLrbz(0);
				  ywlbgl.setLrczy(getUsername());
				  ywlbgls.add(ywlbgl);
			  }
			  service.saveBatch(ywlbgls);
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
  /*public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, Ywlbgl.class);
  }*/

}
