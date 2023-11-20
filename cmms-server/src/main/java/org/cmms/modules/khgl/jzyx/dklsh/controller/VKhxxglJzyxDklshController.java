package org.cmms.modules.khgl.jzyx.dklsh.controller;

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
import org.cmms.common.utils.ListToDictUtil;
import org.cmms.modules.khgl.jzyx.dklsh.entity.KhxxglJzyxDklsh;
import org.cmms.modules.khgl.jzyx.dklsh.entity.KhxxglJzyxDklshVo;
import org.cmms.modules.khgl.jzyx.dklsh.entity.VKhxxglJzyxDklsh;
import org.cmms.modules.khgl.jzyx.dklsh.service.IKhxxglJzyxDklshService;
import org.cmms.modules.khgl.jzyx.dklsh.service.IVKhxxglJzyxDklshService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khgl.jzyx.dklsh.verify.KhxxglJzyxDklshVerify;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
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
 * @Description: 贷款流失户
 * @Author: jeecg-boot
 * @Date:   2023-03-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款流失户")
@RestController
@RequestMapping("/dklsh/vKhxxglJzyxDklsh")
public class VKhxxglJzyxDklshController extends JeecgController<VKhxxglJzyxDklsh, IVKhxxglJzyxDklshService> {
	 @Autowired
	 private IVKhxxglJzyxDklshService vKhxxglJzyxDklshService;
	 @Autowired
	 private IKhxxglJzyxDklshService khxxglJzyxDklshService;

	 @Autowired
	 private KhxxglJzyxDklshVerify khxxglJzyxDklshVerify;

	 @Autowired
	 private INhxqService nhxqService;
	 @Autowired
	 private ListToDictUtil listToDictUtil;
	/**
	 * 分页列表查询
	 *
	 * @param vKhxxglJzyxDklsh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款流失户-分页列表查询")
	@ApiOperation(value="贷款流失户-分页列表查询", notes="贷款流失户-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VKhxxglJzyxDklsh vKhxxglJzyxDklsh,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VKhxxglJzyxDklsh> queryWrapper = QueryGenerator.initQueryWrapper(vKhxxglJzyxDklsh, req.getParameterMap());
		Page<VKhxxglJzyxDklsh> page = new Page<VKhxxglJzyxDklsh>(pageNo, pageSize);
		IPage<VKhxxglJzyxDklsh> pageList = vKhxxglJzyxDklshService.page(page, queryWrapper);
		return Result.ok(pageList);
	}


	 /**
	  *
	  * @return
	  */
	 @AutoLog(value = "贷款流失户-分页列表查询")
	 @ApiOperation(value="贷款流失户-分页列表查询", notes="贷款流失户-分页列表查询")
	 @GetMapping(value = "/queryNhxqOne")
	 public Result<?> queryNhxqOne(VKhxxglJzyxDklsh vKhxxglJzyxDklsh) {
		 QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("zjhm",vKhxxglJzyxDklsh.getZjhm());
		 Nhxq one = nhxqService.getOne(queryWrapper,false);
		 List<Nhxq> nhxqList=new ArrayList<>();
		 nhxqList.add(one);
		 if(nhxqList!=null &&one!=null){
			 List list = listToDictUtil.parseDictText(nhxqList);
			 return Result.ok(list!=null?list.get(0):null);
		 }else{
			 return Result.error("农户信息中暂无该客户的采集信息");
		 }

	 }

	/**
	 * 添加
	 *
	 * @param vKhxxglJzyxDklsh
	 * @return
	 */
	@AutoLog(value = "贷款流失户-添加")
	@ApiOperation(value="贷款流失户-添加", notes="贷款流失户-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody VKhxxglJzyxDklsh vKhxxglJzyxDklsh) {
		vKhxxglJzyxDklshService.save(vKhxxglJzyxDklsh);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param vKhxxglJzyxDklsh
	 * @return
	 */
	@AutoLog(value = "贷款流失户-编辑")
	@ApiOperation(value="贷款流失户-编辑", notes="贷款流失户-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody VKhxxglJzyxDklsh vKhxxglJzyxDklsh) {
		vKhxxglJzyxDklshService.updateById(vKhxxglJzyxDklsh);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款流失户-通过id删除")
	@ApiOperation(value="贷款流失户-通过id删除", notes="贷款流失户-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khxxglJzyxDklshService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款流失户-批量删除")
	@ApiOperation(value="贷款流失户-批量删除", notes="贷款流失户-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khxxglJzyxDklshService.removeByIds(Arrays.asList(ids.split(",")));

		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款流失户-通过id查询")
	@ApiOperation(value="贷款流失户-通过id查询", notes="贷款流失户-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VKhxxglJzyxDklsh vKhxxglJzyxDklsh = vKhxxglJzyxDklshService.getById(id);
		return Result.ok(vKhxxglJzyxDklsh);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param vKhxxglJzyxDklsh
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, VKhxxglJzyxDklsh vKhxxglJzyxDklsh) {
      return super.exportXls(request, vKhxxglJzyxDklsh, VKhxxglJzyxDklsh.class, "贷款流失户");
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
			 if (khxxglJzyxDklshVerify != null) {
				 params.setVerifyHanlder(khxxglJzyxDklshVerify);
			 }
			 FileOutputStream fos = null;
			 FileInputStream fis = null;
			 try {
				 fis = new FileInputStream(file);
				 boolean checkResult = ExcelImportCheckUtil.check(fis, KhxxglJzyxDklshVo.class, params);
				 if (!checkResult) {
					 return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
				 }
				 ExcelImportResult<KhxxglJzyxDklshVo> importResult = ExcelImportUtil.importExcelVerify(file, KhxxglJzyxDklshVo.class, params);
				 List<KhxxglJzyxDklshVo> list = importResult.getList();
				 List<KhxxglJzyxDklsh> list1 =new ArrayList<>();
				 for(KhxxglJzyxDklshVo khxxglJzyxDklshVo:list){
					 KhxxglJzyxDklsh khxxglJzyxDklsh=new KhxxglJzyxDklsh();
					 BeanUtils.copyProperties(khxxglJzyxDklshVo, khxxglJzyxDklsh);
					 QueryWrapper<KhxxglJzyxDklsh> queryWrapper=new QueryWrapper();
					 queryWrapper.eq("zjhm",khxxglJzyxDklsh.getZjhm());
					 khxxglJzyxDklshService.remove(queryWrapper);
					 list1.add(khxxglJzyxDklsh);
				 }
				 khxxglJzyxDklshService.saveBatch(list1);
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "贷款流失户导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, KhxxglJzyxDklshVo.class);
		 ExportParams exportParams = new ExportParams("贷款流失户导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
	 }

}
