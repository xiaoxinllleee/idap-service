package org.cmms.modules.dkjkpt.dkjkptdjkjk.controller;

import java.io.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dkjkpt.dkjkptdjkjk.entity.DkjkptDjkJk;
import org.cmms.modules.dkjkpt.dkjkptdjkjk.entity.DkjkptDjkJkVO;
import org.cmms.modules.dkjkpt.dkjkptdjkjk.service.IDkjkptDjkJkService;
import org.cmms.modules.dkjkpt.dksjjk.dkqbqxdjb.entity.Dkqbqxdjb;
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
 * @Description: 金卡
 * @Author: jeecg-boot
 * @Date:   2023-09-21
 * @Version: V1.0
 */
@Slf4j
@Api(tags="金卡")
@RestController
@RequestMapping("/djkjk/dkjkptDjkJk")
public class DkjkptDjkJkController extends JeecgController<DkjkptDjkJk, IDkjkptDjkJkService> {
	@Autowired
	private IDkjkptDjkJkService dkjkptDjkJkService;

	/**
	 * 分页列表查询
	 *
	 * @param dkjkptDjkJk
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "金卡-分页列表查询")
	@ApiOperation(value="金卡-分页列表查询", notes="金卡-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DkjkptDjkJk dkjkptDjkJk,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DkjkptDjkJk> queryWrapper = QueryGenerator.initQueryWrapper(dkjkptDjkJk, req.getParameterMap());
		Page<DkjkptDjkJk> page = new Page<DkjkptDjkJk>(pageNo, pageSize);
		IPage<DkjkptDjkJk> pageList = dkjkptDjkJkService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dkjkptDjkJk
	 * @return
	 */
	@AutoLog(value = "金卡-添加")
	@ApiOperation(value="金卡-添加", notes="金卡-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DkjkptDjkJk dkjkptDjkJk) {
		dkjkptDjkJkService.save(dkjkptDjkJk);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dkjkptDjkJk
	 * @return
	 */
	@AutoLog(value = "金卡-编辑")
	@ApiOperation(value="金卡-编辑", notes="金卡-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DkjkptDjkJk dkjkptDjkJk) {
		dkjkptDjkJkService.updateById(dkjkptDjkJk);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "金卡-通过id删除")
	@ApiOperation(value="金卡-通过id删除", notes="金卡-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkjkptDjkJkService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "金卡-批量删除")
	@ApiOperation(value="金卡-批量删除", notes="金卡-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkjkptDjkJkService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "金卡-通过id查询")
	@ApiOperation(value="金卡-通过id查询", notes="金卡-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DkjkptDjkJk dkjkptDjkJk = dkjkptDjkJkService.getById(id);
		return Result.ok(dkjkptDjkJk);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkjkptDjkJk
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DkjkptDjkJk dkjkptDjkJk) {
      return super.exportXls(request, dkjkptDjkJk, DkjkptDjkJk.class, "标准金卡报表");
  }


	 /**
	  * 批量删除
	  *
	  * @param tjyf
	  * @return
	  */
	 @AutoLog(value = "批量删除")
	 @ApiOperation(value="批量删除", notes="批量删除")
	 @DeleteMapping(value = "/deleteBatchAll")
	 public Result<?> deleteBatchAll(@Param("tjyf") String tjyf) {
		 QueryWrapper<DkjkptDjkJk> queryWrapper = new QueryWrapper<>();
		 DateTime parse = DateUtil.parse(tjyf);
		 queryWrapper.eq("tjyf",parse);
		 this.dkjkptDjkJkService.remove(queryWrapper);
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
		 mv.addObject(NormalExcelConstants.FILE_NAME, "标准金卡管理导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, DkjkptDjkJkVO.class);
		 ExportParams exportParams = new ExportParams("标准金卡管理导入模板", "模板信息");
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
	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
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
			  ExcelImportResult<DkjkptDjkJk> importResult = ExcelImportUtil.importExcelVerify(file, DkjkptDjkJk.class, DkjkptDjkJkVO.class, params,false);
			  List<DkjkptDjkJk> list = importResult.getList();
			  List<DkjkptDjkJk> qkmbList = new ArrayList<>();
			  for (DkjkptDjkJk qkmb : list) {
				  qkmb.setTjyf(parse);
				  qkmb.setLrr(sysUser.getUsername());
				  qkmb.setLrsj(new Date());
				  qkmbList.add(qkmb);
			  }
			  service.saveBatch(qkmbList);
			  //导入的姓名有乱码 进行批量更新
			  service.djkjkUpdate();
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

}
