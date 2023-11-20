package org.cmms.modules.khgl.wyxkhxx.controller;

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
import org.cmms.modules.khgl.wyxkhxx.entity.Wyxkhxx;
import org.cmms.modules.khgl.wyxkhxx.service.IWyxkhxxService;
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
 * @Description: 未用信客户信息
 * @Author: jeecg-boot
 * @Date:   2019-09-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="未用信客户信息")
@RestController
@RequestMapping("/khgl/wyxkhxx")
public class WyxkhxxController {
	@Autowired
	private IWyxkhxxService wyxkhxxService;

	/**
	  * 分页列表查询
	 * @param wyxkhxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "未用信客户信息-分页列表查询")
	@ApiOperation(value="未用信客户信息-分页列表查询", notes="未用信客户信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Wyxkhxx>> queryPageList(Wyxkhxx wyxkhxx,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Wyxkhxx>> result = new Result<IPage<Wyxkhxx>>();
		QueryWrapper<Wyxkhxx> queryWrapper = QueryGenerator.initQueryWrapper(wyxkhxx, req.getParameterMap());
		Page<Wyxkhxx> page = new Page<Wyxkhxx>(pageNo, pageSize);
		IPage<Wyxkhxx> pageList = wyxkhxxService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param wyxkhxx
	 * @return
	 */
	@AutoLog(value = "未用信客户信息-添加")
	@ApiOperation(value="未用信客户信息-添加", notes="未用信客户信息-添加")
	@PostMapping(value = "/add")
	public Result<Wyxkhxx> add(@RequestBody Wyxkhxx wyxkhxx) {
		Result<Wyxkhxx> result = new Result<Wyxkhxx>();
		try {
			wyxkhxxService.save(wyxkhxx);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param wyxkhxx
	 * @return
	 */
//	@AutoLog(value = "未用信客户信息-编辑")
//	@ApiOperation(value="未用信客户信息-编辑", notes="未用信客户信息-编辑")
//	@PutMapping(value = "/edit")
//	public Result<Wyxkhxx> edit(@RequestBody Wyxkhxx wyxkhxx) {
//		Result<Wyxkhxx> result = new Result<Wyxkhxx>();
//		Wyxkhxx wyxkhxxEntity = wyxkhxxService.getById(wyxkhxx.getId());
//		if(wyxkhxxEntity==null) {
//			result.error500("未找到对应实体");
//		}else {
//			boolean ok = wyxkhxxService.updateById(wyxkhxx);
//			//TODO 返回false说明什么？
//			if(ok) {
//				result.success("修改成功!");
//			}
//		}
//
//		return result;
//	}

	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "未用信客户信息-通过id删除")
	@ApiOperation(value="未用信客户信息-通过id删除", notes="未用信客户信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			wyxkhxxService.removeById(id);
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
	@AutoLog(value = "未用信客户信息-批量删除")
	@ApiOperation(value="未用信客户信息-批量删除", notes="未用信客户信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<Wyxkhxx> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Wyxkhxx> result = new Result<Wyxkhxx>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.wyxkhxxService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "未用信客户信息-通过id查询")
	@ApiOperation(value="未用信客户信息-通过id查询", notes="未用信客户信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Wyxkhxx> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Wyxkhxx> result = new Result<Wyxkhxx>();
		Wyxkhxx wyxkhxx = wyxkhxxService.getById(id);
		if(wyxkhxx==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wyxkhxx);
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
      QueryWrapper<Wyxkhxx> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Wyxkhxx wyxkhxx = JSON.parseObject(deString, Wyxkhxx.class);
              queryWrapper = QueryGenerator.initQueryWrapper(wyxkhxx, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Wyxkhxx> pageList = wyxkhxxService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "未用信客户信息列表");
      mv.addObject(NormalExcelConstants.CLASS, Wyxkhxx.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("未用信客户信息列表数据", "导出人:Jeecg", "导出信息"));
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
              List<Wyxkhxx> listWyxkhxxs = ExcelImportUtil.importExcel(file.getInputStream(), Wyxkhxx.class, params);
              wyxkhxxService.saveBatch(listWyxkhxxs);
              return Result.ok("文件导入成功！数据行数:" + listWyxkhxxs.size());
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
	  * 提取未用信客户信息
	  * @return
	  */
	 @AutoLog(value = "未用信客户信息-提取")
	 @ApiOperation(value="未用信客户信息-提取", notes="未用信客户信息-提取")
	 @GetMapping(value = "/init")
	 public Result<Wyxkhxx> queryById(HttpServletRequest request, HttpServletResponse response) {
		 Result<Wyxkhxx> result = new Result<Wyxkhxx>();
		 wyxkhxxService.initWyxkhxx();
		 result.setSuccess(true);
		 return result;
	 }

	 /**
	  * 通过户主custid查询
	  * @param hzcustid
	  * @return
	  */
	 @AutoLog(value = "未用信客户信息-通过户主custid查询")
	 @ApiOperation(value="未用信客户信息-通过户主custid查询", notes="未用信客户信息-通过户主custid查询")
	 @GetMapping(value = "/queryByHzcustid")
	 public Result<List<Wyxkhxx>> queryByHzcustid(@RequestParam(name="hzcustid",required=true) String hzcustid) {
		 Result<List<Wyxkhxx>> result = new Result<List<Wyxkhxx>>();
		 List<Wyxkhxx> wyxkhxxList = wyxkhxxService.queryByHzcustid(hzcustid);
		 if(wyxkhxxList==null) {
			 result.error500("未找到对应实体");
		 }else {
			 result.setResult(wyxkhxxList);
			 result.setSuccess(true);
		 }
		 return result;
	 }



}
