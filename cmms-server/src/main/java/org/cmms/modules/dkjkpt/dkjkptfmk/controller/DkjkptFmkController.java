package org.cmms.modules.dkjkpt.dkjkptfmk.controller;

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
import org.cmms.modules.dkjkpt.dkjkptfmk.entity.DkjkptFmk;
import org.cmms.modules.dkjkpt.dkjkptfmk.entity.DkjkptFmkVO;
import org.cmms.modules.dkjkpt.dkjkptfmk.service.IDkjkptFmkService;
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
 * @Description: 福民卡
 * @Author: jeecg-boot
 * @Date:   2023-09-21
 * @Version: V1.0
 */
@Slf4j
@Api(tags="福民卡")
@RestController
@RequestMapping("/fmk/dkjkptFmk")
public class DkjkptFmkController extends JeecgController<DkjkptFmk, IDkjkptFmkService> {
	@Autowired
	private IDkjkptFmkService dkjkptFmkService;

	/**
	 * 分页列表查询
	 *
	 * @param dkjkptFmk
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "福民卡-分页列表查询")
	@ApiOperation(value="福民卡-分页列表查询", notes="福民卡-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DkjkptFmk dkjkptFmk,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DkjkptFmk> queryWrapper = QueryGenerator.initQueryWrapper(dkjkptFmk, req.getParameterMap());
		Page<DkjkptFmk> page = new Page<DkjkptFmk>(pageNo, pageSize);
		IPage<DkjkptFmk> pageList = dkjkptFmkService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dkjkptFmk
	 * @return
	 */
	@AutoLog(value = "福民卡-添加")
	@ApiOperation(value="福民卡-添加", notes="福民卡-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DkjkptFmk dkjkptFmk) {
		dkjkptFmkService.save(dkjkptFmk);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dkjkptFmk
	 * @return
	 */
	@AutoLog(value = "福民卡-编辑")
	@ApiOperation(value="福民卡-编辑", notes="福民卡-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DkjkptFmk dkjkptFmk) {
		dkjkptFmkService.updateById(dkjkptFmk);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "福民卡-通过id删除")
	@ApiOperation(value="福民卡-通过id删除", notes="福民卡-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkjkptFmkService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "福民卡-批量删除")
	@ApiOperation(value="福民卡-批量删除", notes="福民卡-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkjkptFmkService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "福民卡-通过id查询")
	@ApiOperation(value="福民卡-通过id查询", notes="福民卡-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DkjkptFmk dkjkptFmk = dkjkptFmkService.getById(id);
		return Result.ok(dkjkptFmk);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkjkptFmk
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DkjkptFmk dkjkptFmk) {
      return super.exportXls(request, dkjkptFmk, DkjkptFmk.class, "福民卡");
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
		 QueryWrapper<DkjkptFmk> queryWrapper = new QueryWrapper<>();
		 DateTime parse = DateUtil.parse(tjyf);
		 queryWrapper.eq("tjyf",parse);
		 this.dkjkptFmkService.remove(queryWrapper);
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
		 mv.addObject(NormalExcelConstants.FILE_NAME, "福民贷记卡管理导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, DkjkptFmkVO.class);
		 ExportParams exportParams = new ExportParams("福民贷记卡管理导入模板", "模板信息");
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
			  ExcelImportResult<DkjkptFmk> importResult = ExcelImportUtil.importExcelVerify(file, DkjkptFmk.class, DkjkptFmkVO.class, params,false);
			  List<DkjkptFmk> list = importResult.getList();
			  List<DkjkptFmk> qkmbList = new ArrayList<>();
			  for (DkjkptFmk qkmb : list) {
				  qkmb.setTjyf(parse);
				  qkmb.setLrr(sysUser.getUsername());
				  qkmb.setLrsj(new Date());
				  qkmbList.add(qkmb);
			  }
			  service.saveBatch(qkmbList);
			  //导入的姓名有乱码 进行批量更新
			  service.fmkUpdate();
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
