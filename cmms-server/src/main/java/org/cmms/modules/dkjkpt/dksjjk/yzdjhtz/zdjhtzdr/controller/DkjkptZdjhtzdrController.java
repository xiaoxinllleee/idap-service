package org.cmms.modules.dkjkpt.dksjjk.yzdjhtz.zdjhtzdr.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dkjkpt.dksjjk.yzdjhtz.zdjhtzdr.entity.DkjkptZdjhtzdr;
import org.cmms.modules.dkjkpt.dksjjk.yzdjhtz.zdjhtzdr.entity.DkjkptZdjhtzdrVO;
import org.cmms.modules.dkjkpt.dksjjk.yzdjhtz.zdjhtzdr.service.IDkjkptZdjhtzdrService;
import org.cmms.modules.dkjkpt.dksjjk.yzdjhtz.zdjhtzdr.verify.ZdjhtzImportVerify;
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
 * @Description: 已制定计划台账导入
 * @Author: jeecg-boot
 * @Date:   2023-08-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags="已制定计划台账导入")
@RestController
@RequestMapping("/zdjhtzdr/dkjkptZdjhtzdr")
public class DkjkptZdjhtzdrController extends JeecgController<DkjkptZdjhtzdr, IDkjkptZdjhtzdrService> {
	@Autowired
	private IDkjkptZdjhtzdrService dkjkptZdjhtzdrService;
	@Autowired
	private ZdjhtzImportVerify zdjhtzImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param dkjkptZdjhtzdr
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "已制定计划台账导入-分页列表查询")
	@ApiOperation(value="已制定计划台账导入-分页列表查询", notes="已制定计划台账导入-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DkjkptZdjhtzdr dkjkptZdjhtzdr,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DkjkptZdjhtzdr> queryWrapper = QueryGenerator.initQueryWrapper(dkjkptZdjhtzdr, req.getParameterMap());
		Page<DkjkptZdjhtzdr> page = new Page<DkjkptZdjhtzdr>(pageNo, pageSize);
		IPage<DkjkptZdjhtzdr> pageList = dkjkptZdjhtzdrService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dkjkptZdjhtzdr
	 * @return
	 */
	@AutoLog(value = "已制定计划台账导入-添加")
	@ApiOperation(value="已制定计划台账导入-添加", notes="已制定计划台账导入-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DkjkptZdjhtzdr dkjkptZdjhtzdr) {
		QueryWrapper<DkjkptZdjhtzdr> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("sjrq",dkjkptZdjhtzdr.getSjrq());
		queryWrapper.eq("jgdm",dkjkptZdjhtzdr.getJgdm());
		queryWrapper.eq("zjhm",dkjkptZdjhtzdr.getZjhm());
		queryWrapper.eq("qdlx",dkjkptZdjhtzdr.getQdlx());
		queryWrapper.eq("hkpl",dkjkptZdjhtzdr.getHkpl());
		DkjkptZdjhtzdr one = dkjkptZdjhtzdrService.getOne(queryWrapper);
		if (one != null){
			return Result.error("该客户信息已存在！");
		}
		dkjkptZdjhtzdr.setLrsj(new Date());
		dkjkptZdjhtzdr.setLrr(getUsername());
		dkjkptZdjhtzdrService.save(dkjkptZdjhtzdr);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dkjkptZdjhtzdr
	 * @return
	 */
	@AutoLog(value = "已制定计划台账导入-编辑")
	@ApiOperation(value="已制定计划台账导入-编辑", notes="已制定计划台账导入-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DkjkptZdjhtzdr dkjkptZdjhtzdr) {
		QueryWrapper<DkjkptZdjhtzdr> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("sjrq",dkjkptZdjhtzdr.getSjrq());
		queryWrapper.eq("jgdm",dkjkptZdjhtzdr.getJgdm());
		queryWrapper.eq("zjhm",dkjkptZdjhtzdr.getZjhm());
		dkjkptZdjhtzdrService.update(dkjkptZdjhtzdr,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "已制定计划台账导入-通过id删除")
	@ApiOperation(value="已制定计划台账导入-通过id删除", notes="已制定计划台账导入-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("sjrq") String sjrq,@Param("jgdm") String jgdm,@Param("zjhm") String zjhm,@Param("qdlx") String qdlx,@Param("hkpl") String hkpl) {
		System.out.println("日期=="+DateUtil.parse(sjrq));
		QueryWrapper<DkjkptZdjhtzdr> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("sjrq",DateUtil.parse(sjrq));
		queryWrapper.eq("jgdm",jgdm);
		queryWrapper.eq("zjhm",zjhm);
		queryWrapper.eq("qdlx",qdlx);
		queryWrapper.eq("hkpl",hkpl);
		dkjkptZdjhtzdrService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "已制定计划台账导入-批量删除")
	@ApiOperation(value="已制定计划台账导入-批量删除", notes="已制定计划台账导入-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkjkptZdjhtzdrService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "已制定计划台账导入-通过id查询")
	@ApiOperation(value="已制定计划台账导入-通过id查询", notes="已制定计划台账导入-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DkjkptZdjhtzdr dkjkptZdjhtzdr = dkjkptZdjhtzdrService.getById(id);
		return Result.ok(dkjkptZdjhtzdr);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkjkptZdjhtzdr
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DkjkptZdjhtzdr dkjkptZdjhtzdr) {
      return super.exportXls(request, dkjkptZdjhtzdr, DkjkptZdjhtzdr.class, "已制定计划台账导入");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "已制定计划台账导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, DkjkptZdjhtzdrVO.class);
		 ExportParams exportParams = new ExportParams("已制定计划台账导入模板", "模板信息");
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
		  File file = new File(baseFilePath);
		  ImportParams params = new ImportParams();
		  params.setTitleRows(1);
		  params.setHeadRows(1);
		  params.setNeedSave(false);
		  if (zdjhtzImportVerify != null) {
			  params.setVerifyHanlder(zdjhtzImportVerify);
		  }
		  FileOutputStream fos = null;
		  try {
			  ExcelImportResult<DkjkptZdjhtzdr> importResult = ExcelImportUtil.importExcelVerify(file, DkjkptZdjhtzdr.class, DkjkptZdjhtzdrVO.class, params);
			  List<DkjkptZdjhtzdr> list = importResult.getList();
			  List<DkjkptZdjhtzdr> qkmbList = new ArrayList<>();
			  for (DkjkptZdjhtzdr ywzkb : list) {
				  ywzkb.setLrr(getUsername());
				  ywzkb.setLrsj(new Date());
				  qkmbList.add(ywzkb);
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
