package org.cmms.modules.khdj.khdjcsgl.controller;

import java.io.File;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khdj.khdjcsgl.entity.KhdjCsgl;
import org.cmms.modules.khdj.khdjcsgl.service.IKhdjCsglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 客户等级评定参数管理
 * @Author: cmms
 * @Date:   2019-10-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户等级评定参数管理")
@RestController
@RequestMapping("/khdjcsgl/khdjCsgl")
public class KhdjCsglController {
	@Autowired
	private IKhdjCsglService iKhdjCsglService;
	@Value(value = "${common.path.upload}")
    private String uploadPath;
	private boolean status;
	
	/**
	  * 分页列表查询
	 * @param khdjCsgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户等级评定参数管理-分页列表查询")
	@ApiOperation(value="客户等级评定参数管理-分页列表查询", notes="客户等级评定参数管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<KhdjCsgl>> queryPageList(KhdjCsgl khdjCsgl,
                                                 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                 HttpServletRequest req) {
		Result<IPage<KhdjCsgl>> result = new Result<IPage<KhdjCsgl>>();
		QueryWrapper<KhdjCsgl> queryWrapper = QueryGenerator.initQueryWrapper(khdjCsgl, req.getParameterMap());
		Page<KhdjCsgl> page = new Page<KhdjCsgl>(pageNo, pageSize);
		IPage<KhdjCsgl> pageList = iKhdjCsglService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param khdjCsgl
	 * @return
	 */
	@AutoLog(value = "客户等级评定参数管理-添加")
	@ApiOperation(value="客户等级评定参数管理-添加", notes="客户等级评定参数管理-添加")
	@PostMapping(value = "/add")
	public Result<KhdjCsgl> add(@RequestBody KhdjCsgl khdjCsgl) {
		Result<KhdjCsgl> result = new Result<KhdjCsgl>();
		try {
            iKhdjCsglService.save(khdjCsgl);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param khdjCsgl
	 * @return
	 */
	@AutoLog(value = "客户等级评定参数管理-编辑")
	@ApiOperation(value="客户等级评定参数管理-编辑", notes="客户等级评定参数管理-编辑")
	@PutMapping(value = "/edit")
	public Result<KhdjCsgl> edit(@RequestBody KhdjCsgl khdjCsgl) {
		Result<KhdjCsgl> result = new Result<KhdjCsgl>();
		KhdjCsgl khdjCsglEntity = iKhdjCsglService.getById(khdjCsgl.getId());
		if(khdjCsglEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = iKhdjCsglService.updateById(khdjCsgl);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		
		return result;
	}
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户等级评定参数管理-通过id删除")
	@ApiOperation(value="客户等级评定参数管理-通过id删除", notes="客户等级评定参数管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
            iKhdjCsglService.removeById(id);
		} catch (Exception e) {
			log.error("删除失败",e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}
	
	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户等级评定参数管理-批量删除")
	@ApiOperation(value="客户等级评定参数管理-批量删除", notes="客户等级评定参数管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<KhdjCsgl> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<KhdjCsgl> result = new Result<KhdjCsgl>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.iKhdjCsglService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户等级评定参数管理-通过id查询")
	@ApiOperation(value="客户等级评定参数管理-通过id查询", notes="客户等级评定参数管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<KhdjCsgl> queryById(@RequestParam(name="id",required=true) String id) {
		Result<KhdjCsgl> result = new Result<KhdjCsgl>();
		KhdjCsgl khdjCsgl = iKhdjCsglService.getById(id);
		if(khdjCsgl==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(khdjCsgl);
			result.setSuccess(true);
		}
		return result;
	}

     /**
      * 导出excel
      * @param request
      * @param response
      */
     @RequestMapping(value = "/exportXls")
     public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
         // Step.1 组装查询条件
         QueryWrapper<KhdjCsgl> queryWrapper = null;
         LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
         try {
             String paramsStr = request.getParameter("paramsStr");
             if (oConvertUtils.isNotEmpty(paramsStr)) {
                 String deString = URLDecoder.decode(paramsStr, "UTF-8");
                 KhdjCsgl khdjCsgl = JSON.parseObject(deString, KhdjCsgl.class);
                 queryWrapper = QueryGenerator.initQueryWrapper(khdjCsgl, request.getParameterMap());
             }
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }

         //Step.2 AutoPoi 导出Excel
         ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
         List<KhdjCsgl> pageList = iKhdjCsglService.list(queryWrapper);
         //导出文件名称
         mv.addObject(NormalExcelConstants.FILE_NAME, "客户等级评定参数管理列表");
         mv.addObject(NormalExcelConstants.CLASS, KhdjCsgl.class);
         mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户等级评定参数", "导出人:"+sysUser.getRealname(), "导出信息"));
         mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
         return mv;
     }

     /**
      * 导出Excel模板
      * @param request
      * @param response
      * @return
      */
     @RequestMapping(value = "/exportTemplateXls")
     public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
         // AutoPoi 导出Excel
         ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
         // 导出文件名称
         modelAndView.addObject(NormalExcelConstants.FILE_NAME, "等级评定参数导入模板");
         modelAndView.addObject(NormalExcelConstants.CLASS, KhdjCsgl.class);
         ExportParams exportParams = new ExportParams("等级评定参数导入模板", "模板信息");
         modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
         modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<KhdjCsgl>());
         return modelAndView;
     }

     /**
      * 通过excel导入数据
      * @param request
      * @param response
      * @return
      */
     @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
     public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject object) {
         String filePaths = object.getString("filePaths");
         if (StringUtils.isEmpty(filePaths)) {
             return Result.error("请先上传文件！");
         }
         String[] filePathList = filePaths.split(",");
         for (String filePath : filePathList) {
             filePath = uploadPath + File.separator + filePath;
             File file = new File(filePath);
             ImportParams importParams = new ImportParams();
             importParams.setTitleRows(1);
             importParams.setHeadRows(1);
             importParams.setNeedSave(true);
             try {
                 List<KhdjCsgl> khdjCsglList = ExcelImportUtil.importExcel(file, KhdjCsgl.class, importParams);
                 List<String> list = new ArrayList<String>();
                 for (KhdjCsgl csgl : khdjCsglList) {
                     status = csgl.getPid().contains(".");
                     if (status) {
                         csgl.setPid(csgl.getPid().split("\\.")[0]);
                     }
                     Map<String, String> param = new HashMap<>();
                     param.put("pid", csgl.getPid());
                     KhdjCsgl khdjCsgl = iKhdjCsglService.queryByPid(param);
                     if (khdjCsgl != null) {
                         list.add(khdjCsgl.getPid());
                     }
                 }
                 if (!list.isEmpty()) {
                     for (String data : list) {
                         String pid = data;
                         QueryWrapper<KhdjCsgl> queryWrapper = new QueryWrapper<>();
                         queryWrapper.eq("pid", pid);
                         iKhdjCsglService.remove(queryWrapper);
                     }
                 }
                 iKhdjCsglService.saveBatch(khdjCsglList);
                 return Result.ok("文件导入成功！共[ " + khdjCsglList.size() + " ]条数据！");
             } catch (Exception e) {
                 log.error(e.getMessage(), e);
                 return Result.error("文件导入失败！" + e.getMessage());
             }
         }
         return Result.ok("文件导入失败！");
     }

}
