package org.cmms.modules.khpjsx.pjxmsz.controller;

import java.io.File;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.khpjsx.pjxmsz.entity.PjsxPjxmsz;
import org.cmms.modules.khpjsx.pjxmsz.entity.PjsxPjxmszImport;
import org.cmms.modules.khpjsx.pjxmsz.service.IPjsxPjxmszService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.service.ISysDictService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 评级项目设置
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="评级项目设置")
@RestController
@RequestMapping("/khpjsx.pjxmsz/pjsxPjxmsz")
public class PjsxPjxmszController extends JeecgController<PjsxPjxmsz, IPjsxPjxmszService> {
	@Autowired
	private IPjsxPjxmszService pjsxPjxmszService;

	 @Value(value = "${common.path.upload}")
	 private String uploadpath;

	 @Autowired
	 private ISysDictService iSysDictService;

	/**
	 * 分页列表查询
	 * @param pjsxPjxmsz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "评级项目设置-分页列表查询")
	@ApiOperation(value="评级项目设置-分页列表查询", notes="评级项目设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PjsxPjxmsz pjsxPjxmsz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PjsxPjxmsz> queryWrapper = QueryGenerator.initQueryWrapper(pjsxPjxmsz, req.getParameterMap());
		Page<PjsxPjxmsz> page = new Page<PjsxPjxmsz>(pageNo, pageSize);
		IPage<PjsxPjxmsz> pageList = pjsxPjxmszService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 * @param pjsxPjxmsz
	 * @return
	 */
	@AutoLog(value = "评级项目设置-添加")
	@ApiOperation(value="评级项目设置-添加", notes="评级项目设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PjsxPjxmsz pjsxPjxmsz) {
		pjsxPjxmsz.setQydm(iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101001"));
		pjsxPjxmszService.save(pjsxPjxmsz);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 * @param pjsxPjxmsz
	 * @return
	 */
	@AutoLog(value = "评级项目设置-编辑")
	@ApiOperation(value="评级项目设置-编辑", notes="评级项目设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PjsxPjxmsz pjsxPjxmsz) {
		Result<PjsxPjxmsz> result = new Result<PjsxPjxmsz>();
		UpdateWrapper<PjsxPjxmsz> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("qydm",pjsxPjxmsz.getQydm()).eq("xmbh",pjsxPjxmsz.getXmbh());
		pjsxPjxmszService.update(pjsxPjxmsz,updateWrapper);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 * @param jsonObject
	 * @return
	 */
	@PutMapping(value = "/delete")
	public Result<?> delete(@RequestBody JSONObject jsonObject ) {
		try{
			UpdateWrapper<PjsxPjxmsz> updateWrapper = new UpdateWrapper();
			updateWrapper.eq("qydm",jsonObject.getString("qydm")).eq("xmbh",jsonObject.getString("xmbh"));
			pjsxPjxmszService.remove(updateWrapper);
		} catch (Exception e) {
			log.error("删除失败",e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "评级项目设置-批量删除")
	@ApiOperation(value="评级项目设置-批量删除", notes="评级项目设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pjsxPjxmszService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评级项目设置-通过id查询")
	@ApiOperation(value="评级项目设置-通过id查询", notes="评级项目设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PjsxPjxmsz pjsxPjxmsz = pjsxPjxmszService.getById(id);
		return Result.ok(pjsxPjxmsz);
	}

     /**
      * 导出excel
      * @param request
      * @param pjsxPjxmsz
      */
     @RequestMapping(value = "/exportXls")
     public ModelAndView exportXls(HttpServletRequest request, PjsxPjxmsz pjsxPjxmsz) {
         return super.exportXls(request, pjsxPjxmsz, PjsxPjxmsz.class, "评级项目设置");
     }

	 /**
	  * 导出模板excel
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "评级项目设置导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, PjsxPjxmszImport.class);
		 ExportParams exportParams = new ExportParams("评级项目设置导入模板", "模板信息");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<PjsxPjxmszImport>());
		 return mv;
	 }

	 /**
	  * 通过excel导入数据
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 String filePaths = jsonObject.getString("filePaths");
		 if (StringUtils.isEmpty(filePaths)) {
			 return Result.error("请先上传文件！");
		 }
		 String[] filePathList = filePaths.split(",");
		 for (String filePath : filePathList) {
			 filePath = uploadpath + File.separator + filePath;
			 File file = new File(filePath);
			 ImportParams params = new ImportParams();
			 params.setTitleRows(1);
			 params.setHeadRows(1);
			 params.setNeedSave(true);
			 try {
				 List<PjsxPjxmsz> pjsxPjxmszList = ExcelImportUtil.importExcel(file, PjsxPjxmsz.class, params);
				 List<String> ids = new ArrayList<String>();

				 for (PjsxPjxmsz pjsxPjxmsz : pjsxPjxmszList) {
					 Map<String,String> parm = new HashMap<>();
					 pjsxPjxmsz.setQydm(iSysDictService.queryTableDictTextByKey("sys_dic","value","code","101001"));
					 parm.put("qydm",pjsxPjxmsz.getQydm());
					 parm.put("xmbh",pjsxPjxmsz.getXmbh());
					 PjsxPjxmsz pjsxPjxmsz1 = pjsxPjxmszService.queryxmbh(parm);
					 if (pjsxPjxmsz1 != null) {
						 ids.add(pjsxPjxmsz1.getQydm()+"-"+pjsxPjxmsz1.getXmbh());
					 }
				 }
				 //先删除已经存在的数据
				 if (!ids.isEmpty()) {
					 for (String id : ids) {
						 String[] split = id.split("-");
						 String qydm =split[0];
						 String xmbh =split[1];
						 QueryWrapper<PjsxPjxmsz> userUpdateWrapper=new QueryWrapper<>();
						 userUpdateWrapper.eq("qydm", qydm).eq("xmbh",xmbh);
						 pjsxPjxmszService.remove(userUpdateWrapper);
					 }
				 }
				 pjsxPjxmszService.saveBatch(pjsxPjxmszList);

				 return Result.ok("文件导入成功！数据行数:" + pjsxPjxmszList.size());
			 } catch (Exception e) {
				 log.error(e.getMessage(),e);
				 return Result.error("文件导入失败:"+e.getMessage());
			 }
		 }
		 return Result.ok("文件导入失败！");
	 }

}
