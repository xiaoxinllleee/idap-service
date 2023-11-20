package org.cmms.modules.khgl.khhmc.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.khgl.khhmc.entity.Khfjxxgl;
import org.cmms.modules.khgl.khhmc.entity.KhfjxxglExp;
import org.cmms.modules.khgl.khhmc.service.IKhfjxxglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khgl.khhmc.verify.KhfjxxglImportVerify;
import org.cmms.modules.khgl.khxx.entity.KhywxxSjyhPc;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.khxxgl.wbsjgl.sjxfsj.entity.Sjxfsj;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;

import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 客户附加信息管理
 * @Author: jeecg-boot
 * @Date:   2020-03-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户附加信息管理")
@RestController
@RequestMapping("/khgl.khhmc/khfjxxgl")
public class KhfjxxglController extends JeecgController<Khfjxxgl, IKhfjxxglService> {
	@Autowired
	private IKhfjxxglService khfjxxglService;
	@Autowired
	private KhfjxxglImportVerify khfjxxglImportVerify;
	 @Autowired
	 private INhxqService nhxqService;
	/**
	 * 分页列表查询
	 *
	 * @param khfjxxgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户附加信息管理-分页列表查询")
	@ApiOperation(value="客户附加信息管理-分页列表查询", notes="客户附加信息管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Khfjxxgl khfjxxgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Khfjxxgl> queryWrapper = QueryGenerator.initQueryWrapper(khfjxxgl, req.getParameterMap());
		Page<Khfjxxgl> page = new Page<Khfjxxgl>(pageNo, pageSize);
		IPage<Khfjxxgl> pageList = khfjxxglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param khfjxxgl
	 * @return
	 */
	@AutoLog(value = "客户附加信息管理-添加")
	@ApiOperation(value="客户附加信息管理-添加", notes="客户附加信息管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Khfjxxgl khfjxxgl) {
		khfjxxglService.save(khfjxxgl);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param khfjxxgl
	 * @return
	 */
	@AutoLog(value = "客户附加信息管理-编辑")
	@ApiOperation(value="客户附加信息管理-编辑", notes="客户附加信息管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Khfjxxgl khfjxxgl) {
		khfjxxglService.updateById(khfjxxgl);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户附加信息管理-通过id删除")
	@ApiOperation(value="客户附加信息管理-通过id删除", notes="客户附加信息管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khfjxxglService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户附加信息管理-批量删除")
	@ApiOperation(value="客户附加信息管理-批量删除", notes="客户附加信息管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khfjxxglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param zjhm
	 * @return
	 */
	@AutoLog(value = "客户附加信息管理-通过id查询")
	@ApiOperation(value="客户附加信息管理-通过id查询", notes="客户附加信息管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="zjhm",required=true) String zjhm) {
		Khfjxxgl khfjxxgl = khfjxxglService.getById(zjhm);
		return Result.ok(khfjxxgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khfjxxgl
   */
	 @RequestMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, Khfjxxgl khfjxxgl) {
		 return super.exportXls(request, khfjxxgl, Khfjxxgl.class, "客户附加信息管理");
	 }


	 @RequestMapping(value = "/exportXls2")
	 public ModelAndView exportXls2(HttpServletRequest request,String wgbh) {
		 log.info("===本次导出wgbh={}===",wgbh);
		 log.info("===开始查询数据===");
		 List<KhfjxxglExp> fjxxByWgbh = service.getFjxxByWgbh(wgbh,getLoginUser().getWorkNo());

		 if (CollUtil.isNotEmpty(fjxxByWgbh)){
			 log.info("===本次导出数据{}条===",fjxxByWgbh.size());
		 }
		 log.info("===查询结束 开始导出===");
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, "客户附加信息管理"); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, KhfjxxglExp.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户附加信息管理" + "报表", "导出人:" + getRealname(), "客户附加信息管理"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, fjxxByWgbh);
		 return mv;
	 }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  /*@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {

	  MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	  Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
	  for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
		  MultipartFile file = entity.getValue();// 获取上传文件对象
		  ImportParams params = new ImportParams();
		  params.setTitleRows(2);
		  params.setHeadRows(1);
		  params.setNeedSave(true);
		  try {
			  List<Khfjxxgl> list = ExcelImportUtil.importExcel(file.getInputStream(), Khfjxxgl.class, params);
			  long start = System.currentTimeMillis();
			  khfjxxglService.saveOrUpdateBatch(list);
			  //khfjxxglService.updateHzxx();
			  //khfjxxglService.updateKhhmc();
			  //khfjxxglService.updateywwl();

			  log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
			  return Result.ok("文件导入成功！数据行数：" + list.size());
		  } catch (Exception e) {
			  log.error(e.getMessage(), e);
			  return Result.error("文件导入失败:" + e.getMessage());
		  } finally {
			  try {
				  file.getInputStream().close();
			  } catch (IOException e) {
				  e.printStackTrace();
			  }
		  }
	  }
	  return Result.error("文件导入失败！");
  }*/


	 /**
	  * 通过excel导入数据
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 return super.importExcelByTemplate(jsonObject, request, response, Khfjxxgl.class, khfjxxglImportVerify);
	 }

	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 return super.exportTemplateXls(Khfjxxgl.class, "客户附加信息导入模板");
	 }


	 /**
	  * 更新花名册信息
	  * @return
	  */
	 @AutoLog(value = "花名册信息-更新")
	 @ApiOperation(value="花名册信息-更新", notes="花名册信息-更新")
	 @GetMapping(value = "/updateKhhmc")
	 public Result<?> updateKhhmc(HttpServletRequest request, HttpServletResponse response) {
		 try {
		 	 khfjxxglService.updateHzxx();
			 khfjxxglService.updateKhhmc();
			 khfjxxglService.updateywwl();
			 return Result.ok("更新成功");
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 return Result.error("更新失败:" + e.getMessage());
		 }
	 }

	 @RequestMapping("/getFjxxByWgbh")
	 public Result<?> getFjxxByWgbh(String wgbh,String type,@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize){
		 if (StringUtils.isBlank(wgbh))
			 return Result.ok("所属网格不能为空");
		 Page<Khfjxxgl> page = new Page<Khfjxxgl>(pageNo, pageSize);
		 IPage<Khfjxxgl> byWgbh = service.getByWgbh(page, wgbh, type);
		 return Result.ok(byWgbh);
	 }

	 /**
	  * 通过khid查询
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "客户附加数据-通过Khid查询")
	 @ApiOperation(value="客户附加数据-通过Khid查询", notes="客户附加数据-通过Khid查询")
	 @GetMapping(value = "/queryByKhid")
	 public Result<?> queryByKhid(@RequestParam(name="id",required=true) String id) {
		 //先根据客户id查询客户证件号码
		 QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
		 nhxqQueryWrapper.eq("id", id);
		 Nhxq nhxq = nhxqService.getOne(nhxqQueryWrapper);
		 if (nhxq == null) {
			 return Result.error("未找到客户信息");
		 }
		 QueryWrapper<Khfjxxgl> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("zjhm", nhxq.getZjhm());
		 List<Khfjxxgl> khfjxxglList = service.list(queryWrapper);
		 if (!khfjxxglList.isEmpty()) {
		 	return Result.ok(khfjxxglList.get(0));
		 }
		 return Result.ok("未找到数据");
	 }

}
