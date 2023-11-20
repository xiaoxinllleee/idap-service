package org.cmms.modules.dkjkpt.dkglqsckqsfx.zjfhqkjcajfp.controller;

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
import org.cmms.modules.dkjkpt.dkglqsckqsfx.zjfhqkjcajfp.entity.DkjkptDkglckqsfxbAjfp;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.zjfhqkjcajfp.service.IDkjkptDkglckqsfxbAjfpService;
import java.util.Date;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 资金返还情况监测（按揭、扶贫）
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="资金返还情况监测（按揭、扶贫）")
@RestController
@RequestMapping("/dkjkpt.dkglqsckqsfx.zjfhqkjcajfp/dkjkptDkglckqsfxbAjfp")
public class DkjkptDkglckqsfxbAjfpController {
	@Autowired
	private IDkjkptDkglckqsfxbAjfpService dkjkptDkglckqsfxbAjfpService;

	 @Autowired
	private IDkjkptDkglckqsfxbAjfpService IDkjkptDkglckqsfxbAjfpService;
	
	/**
	  * 分页列表查询
	 * @param dkjkptDkglckqsfxbAjfp
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "资金返还情况监测（按揭、扶贫）-分页列表查询")
	@ApiOperation(value="资金返还情况监测（按揭、扶贫）-分页列表查询", notes="资金返还情况监测（按揭、扶贫）-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<DkjkptDkglckqsfxbAjfp>> queryPageList(DkjkptDkglckqsfxbAjfp dkjkptDkglckqsfxbAjfp,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<DkjkptDkglckqsfxbAjfp>> result = new Result<IPage<DkjkptDkglckqsfxbAjfp>>();
		QueryWrapper<DkjkptDkglckqsfxbAjfp> queryWrapper = QueryGenerator.initQueryWrapper(dkjkptDkglckqsfxbAjfp, req.getParameterMap());
		Page<DkjkptDkglckqsfxbAjfp> page = new Page<DkjkptDkglckqsfxbAjfp>(pageNo, pageSize);
		IPage<DkjkptDkglckqsfxbAjfp> pageList = dkjkptDkglckqsfxbAjfpService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param dkjkptDkglckqsfxbAjfp
	 * @return
	 */
	@AutoLog(value = "资金返还情况监测（按揭、扶贫）-添加")
	@ApiOperation(value="资金返还情况监测（按揭、扶贫）-添加", notes="资金返还情况监测（按揭、扶贫）-添加")
	@PostMapping(value = "/add")
	public Result<DkjkptDkglckqsfxbAjfp> add(@RequestBody DkjkptDkglckqsfxbAjfp dkjkptDkglckqsfxbAjfp) {
		Result<DkjkptDkglckqsfxbAjfp> result = new Result<DkjkptDkglckqsfxbAjfp>();
		try {
			dkjkptDkglckqsfxbAjfpService.save(dkjkptDkglckqsfxbAjfp);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param dkjkptDkglckqsfxbAjfp
	 * @return
	 *//*
	@AutoLog(value = "资金返还情况监测（按揭、扶贫）-编辑")
	@ApiOperation(value="资金返还情况监测（按揭、扶贫）-编辑", notes="资金返还情况监测（按揭、扶贫）-编辑")
	@PutMapping(value = "/edit")
	public Result<DkjkptDkglckqsfxbAjfp> edit(@RequestBody DkjkptDkglckqsfxbAjfp dkjkptDkglckqsfxbAjfp) {
		Result<DkjkptDkglckqsfxbAjfp> result = new Result<DkjkptDkglckqsfxbAjfp>();
		DkjkptDkglckqsfxbAjfp dkjkptDkglckqsfxbAjfpEntity = dkjkptDkglckqsfxbAjfpService.getById(dkjkptDkglckqsfxbAjfp.getId());
		if(dkjkptDkglckqsfxbAjfpEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dkjkptDkglckqsfxbAjfpService.updateById(dkjkptDkglckqsfxbAjfp);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}

		return result;
	}*/
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "资金返还情况监测（按揭、扶贫）-通过id删除")
	@ApiOperation(value="资金返还情况监测（按揭、扶贫）-通过id删除", notes="资金返还情况监测（按揭、扶贫）-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			dkjkptDkglckqsfxbAjfpService.removeById(id);
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
	@AutoLog(value = "资金返还情况监测（按揭、扶贫）-批量删除")
	@ApiOperation(value="资金返还情况监测（按揭、扶贫）-批量删除", notes="资金返还情况监测（按揭、扶贫）-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<DkjkptDkglckqsfxbAjfp> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<DkjkptDkglckqsfxbAjfp> result = new Result<DkjkptDkglckqsfxbAjfp>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.dkjkptDkglckqsfxbAjfpService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "资金返还情况监测（按揭、扶贫）-通过id查询")
	@ApiOperation(value="资金返还情况监测（按揭、扶贫）-通过id查询", notes="资金返还情况监测（按揭、扶贫）-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<DkjkptDkglckqsfxbAjfp> queryById(@RequestParam(name="id",required=true) String id) {
		Result<DkjkptDkglckqsfxbAjfp> result = new Result<DkjkptDkglckqsfxbAjfp>();
		DkjkptDkglckqsfxbAjfp dkjkptDkglckqsfxbAjfp = dkjkptDkglckqsfxbAjfpService.getById(id);
		if(dkjkptDkglckqsfxbAjfp==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(dkjkptDkglckqsfxbAjfp);
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
      QueryWrapper<DkjkptDkglckqsfxbAjfp> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              DkjkptDkglckqsfxbAjfp dkjkptDkglckqsfxbAjfp = JSON.parseObject(deString, DkjkptDkglckqsfxbAjfp.class);
              queryWrapper = QueryGenerator.initQueryWrapper(dkjkptDkglckqsfxbAjfp, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<DkjkptDkglckqsfxbAjfp> pageList = dkjkptDkglckqsfxbAjfpService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "资金返还情况监测（按揭、扶贫）列表");
      mv.addObject(NormalExcelConstants.CLASS, DkjkptDkglckqsfxbAjfp.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("资金返还情况监测（按揭、扶贫）列表数据", "导出人:Jeecg", "导出信息"));
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
              List<DkjkptDkglckqsfxbAjfp> listDkjkptDkglckqsfxbAjfps = ExcelImportUtil.importExcel(file.getInputStream(), DkjkptDkglckqsfxbAjfp.class, params);
              dkjkptDkglckqsfxbAjfpService.saveBatch(listDkjkptDkglckqsfxbAjfps);
              return Result.ok("文件导入成功！数据行数:" + listDkjkptDkglckqsfxbAjfps.size());
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
	  *   通过id删除
	  * @param tjyf
	  * @return
	  */
	 @RequestMapping(value = "/extract" , method = RequestMethod.PUT)
	 public Result<?> extract(@RequestParam(name = "TJYF") String tjyf) {
		 try {
			 IDkjkptDkglckqsfxbAjfpService.extract(tjyf);
		 } catch (Exception e) {
			 log.error("提取失败",e.getMessage());
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功!");
	 }


 }
