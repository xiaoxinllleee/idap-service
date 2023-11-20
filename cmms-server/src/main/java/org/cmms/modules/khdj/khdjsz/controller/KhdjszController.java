package org.cmms.modules.khdj.khdjsz.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khdj.khdjsz.entity.Khdjsz;
import org.cmms.modules.khdj.khdjsz.service.IkhdjszService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags="1")
@RestController
@RequestMapping("/khdj_khdjsz/khdj_khdjsz")
public class KhdjszController {
	@Autowired
	private IkhdjszService khdj_khdjszService;
	@Value(value = "${common.path.upload}")
    private String uploadPath;
	private boolean status;

	/**
	  * 分页列表查询
	 * @param Khdjsz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "1-分页列表查询")
	@ApiOperation(value="1-分页列表查询", notes="1-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Khdjsz>> queryPageList(Khdjsz Khdjsz,
                                               @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                               @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                               HttpServletRequest req) {
		Result<IPage<Khdjsz>> result = new Result<IPage<Khdjsz>>();
		QueryWrapper<Khdjsz> queryWrapper = QueryGenerator.initQueryWrapper(Khdjsz, req.getParameterMap());
		queryWrapper.orderByAsc("djbh");
		Page<Khdjsz> page = new Page<Khdjsz>(pageNo, pageSize);
		IPage<Khdjsz> pageList = khdj_khdjszService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param Khdjsz
	 * @return
	 */
	@AutoLog(value = "1-添加")
	@ApiOperation(value="1-添加", notes="1-添加")
	@PostMapping(value = "/add")
	public Result<Khdjsz> add(@RequestBody Khdjsz Khdjsz) {
		Result<Khdjsz> result = new Result<Khdjsz>();
		try {
			khdj_khdjszService.save(Khdjsz);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param Khdjsz
	 * @return
	 */
	@AutoLog(value = "1-编辑")
	@ApiOperation(value="1-编辑", notes="1-编辑")
	@PutMapping(value = "/edit")
	public Result<Khdjsz> edit(@RequestBody Khdjsz Khdjsz) {
		Result<Khdjsz> result = new Result<Khdjsz>();
		Khdjsz khdjszEntity = khdj_khdjszService.getById(Khdjsz.getId());
		if(khdjszEntity ==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = khdj_khdjszService.updateById(Khdjsz);
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
	@AutoLog(value = "1-通过id删除")
	@ApiOperation(value="1-通过id删除", notes="1-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			khdj_khdjszService.removeById(id);
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
	@AutoLog(value = "1-批量删除")
	@ApiOperation(value="1-批量删除", notes="1-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<Khdjsz> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Khdjsz> result = new Result<Khdjsz>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.khdj_khdjszService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "1-通过id查询")
	@ApiOperation(value="1-通过id查询", notes="1-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Khdjsz> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Khdjsz> result = new Result<Khdjsz>();
		Khdjsz Khdjsz = khdj_khdjszService.getById(id);
		if(Khdjsz ==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(Khdjsz);
			result.setSuccess(true);
		}
		return result;
	}

  /**
      * 导出excel
   *
   * @param request
   * @param response
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<Khdjsz> queryWrapper = null;
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Khdjsz Khdjsz = JSON.parseObject(deString, Khdjsz.class);
              queryWrapper = QueryGenerator.initQueryWrapper(Khdjsz, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Khdjsz> pageList = khdj_khdjszService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "客户等级设置");
      mv.addObject(NormalExcelConstants.CLASS, Khdjsz.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户等级设置", "导出人:"+sysUser.getRealname(), "导出信息"));
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
         modelAndView.addObject(NormalExcelConstants.FILE_NAME, "客户评定等级导入模板");
         modelAndView.addObject(NormalExcelConstants.CLASS, Khdjsz.class);
         ExportParams exportParams = new ExportParams("客户评定等级导入模板", "模板信息");
         modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
         modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<Khdjsz>());
         return  modelAndView;
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
                List<Khdjsz> khdjszList = ExcelImportUtil.importExcel(file, Khdjsz.class, importParams);
                List<String> list = new ArrayList<String>();
                 for (Khdjsz djsz : khdjszList) {
                     status = djsz.getDjbh().contains(".");
                     if (status) { djsz.setDjbh(djsz.getDjbh().split("\\.")[0]); }
                     Map<String, String> param = new HashMap<>();
                     param.put("djbh", djsz.getDjbh());
                     Khdjsz khdjszEntity = khdj_khdjszService.queryByDjbh(param);
                     if (khdjszEntity != null) {
                         list.add(khdjszEntity.getDjbh());
                     }
                 }
                 if (!list.isEmpty()) {
                     for (String data : list) {
                         String djbh = data;
                         QueryWrapper<Khdjsz> queryWrapper = new QueryWrapper<>();
                         queryWrapper.eq("djbh", djbh);
                         khdj_khdjszService.remove(queryWrapper);
                     }
                 }
                 khdj_khdjszService.saveBatch(khdjszList);
                 return Result.ok("文件导入成功！共[ "+khdjszList.size()+" ]条数据！");
             } catch (Exception e) {
                 log.error(e.getMessage(), e);
                 return Result.error("文件导入失败！"+e.getMessage());
             }
         }
         return Result.ok("文件导入失败！");
     }

}
