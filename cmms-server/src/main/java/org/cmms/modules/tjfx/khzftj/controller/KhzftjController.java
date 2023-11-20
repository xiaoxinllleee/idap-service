package org.cmms.modules.tjfx.khzftj.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.cmms.modules.tjfx.khzftj.entity.Khzftj;
import org.cmms.modules.tjfx.khzftj.service.IKhzftjService;
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
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 客户走访统计
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户走访统计")
@RestController
@RequestMapping("/khzftj/khzftj")
public class KhzftjController {
	@Autowired
	private IKhzftjService khzftjService;

	
	/**
	  * 分页列表查询
	 * @param khzftj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户走访统计-分页列表查询")
	@ApiOperation(value="客户走访统计-分页列表查询", notes="客户走访统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Khzftj>> queryPageList(Khzftj khzftj,
                                               @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                               @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                               HttpServletRequest req) {
		Result<IPage<Khzftj>> result = new Result<IPage<Khzftj>>();
		QueryWrapper<Khzftj> queryWrapper = QueryGenerator.initQueryWrapper(khzftj, req.getParameterMap());
		Page<Khzftj> page = new Page<Khzftj>(pageNo, pageSize);
		IPage<Khzftj> pageList = khzftjService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户走访统计-通过id查询")
	@ApiOperation(value="客户走访统计-通过id查询", notes="客户走访统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Khzftj> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Khzftj> result = new Result<Khzftj>();
		Khzftj khzftj = khzftjService.getById(id);
		if(khzftj==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(khzftj);
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
      QueryWrapper<Khzftj> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Khzftj khzftj = JSON.parseObject(deString, Khzftj.class);
              queryWrapper = QueryGenerator.initQueryWrapper(khzftj, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Khzftj> pageList = khzftjService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "客户走访统计列表");
      mv.addObject(NormalExcelConstants.CLASS, Khzftj.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户走访统计列表数据", "导出人:Jeecg", "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
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
              List<Khzftj> listKhzftjs = ExcelImportUtil.importExcel(file.getInputStream(), Khzftj.class, params);
              khzftjService.saveBatch(listKhzftjs);
              return Result.ok("文件导入成功！数据行数:" + listKhzftjs.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
  }

     /**
      *   通过tjyf提取
      * @param tjrq
      * @return
      */
     @RequestMapping(value = "/extract" , method = RequestMethod.PUT)
     public Result<?> extract(@RequestParam(name = "TJRQ") String tjrq) {
         try {
             khzftjService.extract(tjrq);
         } catch (Exception e) {
             log.error(e.getMessage(), "提取失败");
             return Result.error(e.getMessage());
         }
         return Result.ok("提取成功");
     }

}
