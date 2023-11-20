package org.cmms.modules.dkjkpt.dksjjk.dkqbqxdjb.controller;

import java.io.*;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dkjkpt.dksjjk.dkqbqxdjb.entity.Dkqbqxdjb;
import org.cmms.modules.dkjkpt.dksjjk.dkqbqxdjb.entity.DkqbqxdjbVo;
import org.cmms.modules.dkjkpt.dksjjk.dkqbqxdjb.service.IDkqbqxdjbService;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.entity.DkjkptBndksjjktz;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.entity.DkjkptBndksjjktzImport;
import org.cmms.modules.gzap.gzrw.entity.GzapGzrw;
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
 * @Description: 贷款欠本欠息登记簿
 * @Author: jeecg-boot
 * @Date:   2023-08-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款欠本欠息登记簿")
@RestController
@RequestMapping("/dkqbqxdjb/dkqbqxdjb")
public class DkqbqxdjbController extends JeecgController<Dkqbqxdjb, IDkqbqxdjbService> {
	@Autowired
	private IDkqbqxdjbService dkqbqxdjbService;

	/**
	 * 分页列表查询
	 *
	 * @param dkqbqxdjb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款欠本欠息登记簿-分页列表查询")
	@ApiOperation(value="贷款欠本欠息登记簿-分页列表查询", notes="贷款欠本欠息登记簿-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dkqbqxdjb dkqbqxdjb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dkqbqxdjb> queryWrapper = QueryGenerator.initQueryWrapper(dkqbqxdjb, req.getParameterMap());
		Page<Dkqbqxdjb> page = new Page<Dkqbqxdjb>(pageNo, pageSize);
		IPage<Dkqbqxdjb> pageList = dkqbqxdjbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dkqbqxdjb
	 * @return
	 */
	@AutoLog(value = "贷款欠本欠息登记簿-添加")
	@ApiOperation(value="贷款欠本欠息登记簿-添加", notes="贷款欠本欠息登记簿-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dkqbqxdjb dkqbqxdjb) {
		dkqbqxdjbService.save(dkqbqxdjb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dkqbqxdjb
	 * @return
	 */
	@AutoLog(value = "贷款欠本欠息登记簿-编辑")
	@ApiOperation(value="贷款欠本欠息登记簿-编辑", notes="贷款欠本欠息登记簿-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dkqbqxdjb dkqbqxdjb) {
		dkqbqxdjbService.updateById(dkqbqxdjb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "贷款欠本欠息登记簿-通过id删除")
	@ApiOperation(value="贷款欠本欠息登记簿-通过id删除", notes="贷款欠本欠息登记簿-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("khjg") String khjg,@Param("zjhm") String zjhm,@Param("zh") String zh,@Param("dklx") String dklx) {
		QueryWrapper<Dkqbqxdjb> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("khjg",khjg);
		queryWrapper.eq("zjhm",zjhm);
		queryWrapper.eq("zh",zh);
		queryWrapper.eq("dklx",dklx);
		dkqbqxdjbService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款欠本欠息登记簿-批量删除")
	@ApiOperation(value="贷款欠本欠息登记簿-批量删除", notes="贷款欠本欠息登记簿-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkqbqxdjbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款欠本欠息登记簿-通过id查询")
	@ApiOperation(value="贷款欠本欠息登记簿-通过id查询", notes="贷款欠本欠息登记簿-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dkqbqxdjb dkqbqxdjb = dkqbqxdjbService.getById(id);
		return Result.ok(dkqbqxdjb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkqbqxdjb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dkqbqxdjb dkqbqxdjb) {
      return super.exportXls(request, dkqbqxdjb, Dkqbqxdjb.class, "贷款欠本欠息登记簿");
  }


	 /**
	  * 批量删除
	  *
	  * @param sjrq
	  * @return
	  */
	 @AutoLog(value = "批量删除")
	 @ApiOperation(value="批量删除", notes="批量删除")
	 @DeleteMapping(value = "/deleteBatchAll")
	 public Result<?> deleteBatchAll(@Param("sjrq") String sjrq) {
		 QueryWrapper<Dkqbqxdjb> queryWrapper = new QueryWrapper<>();
		 DateTime parse = DateUtil.parse(sjrq);
		 queryWrapper.eq("sjrq",parse);
		 this.dkqbqxdjbService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }

	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "贷款欠本欠息登记簿导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, DkqbqxdjbVo.class);
		 ExportParams exportParams = new ExportParams("贷款欠本欠息登记簿导入模板", "模板信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return mv;
	 }




  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return*/
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
		/*  if (zcfzbImportVerify != null) {
			  params.setVerifyHanlder(zcfzbImportVerify);
		  }*/
		  FileOutputStream fos = null;
		  FileInputStream fis = null;
		  try {
			  ExcelImportResult<Dkqbqxdjb> importResult = ExcelImportUtil.importExcelVerify(file, Dkqbqxdjb.class, DkqbqxdjbVo.class, params);
			  List<Dkqbqxdjb> list = importResult.getList();
			  List<Dkqbqxdjb> qkmbList = new ArrayList<>();
			  for (Dkqbqxdjb qkmb : list) {
				  qkmb.setSjrq(parse);
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
			  IoUtil.close(fos);
		  }
	  }
	  return Result.ok("文件导入失败！");
  }
  /*@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, Dkqbqxdjb.class);
  }*/

}
