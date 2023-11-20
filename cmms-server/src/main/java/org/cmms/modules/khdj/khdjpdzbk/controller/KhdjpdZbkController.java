package org.cmms.modules.khdj.khdjpdzbk.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khdj.khdjpdzbk.entity.KhdjpdZbk;
import org.cmms.modules.khdj.khdjpdzbk.service.IKhdjpdZbkService;
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
 * @Description: 客户等级评定指标库
 * @Author: cmms
 * @Date:   2019-10-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户等级评定指标库")
@RestController
@RequestMapping("/khdjpdzbk/khdjpdZbk")
public class KhdjpdZbkController {
	@Autowired
	private IKhdjpdZbkService khdjpdZbkService;
	
	/**
	  * 分页列表查询
	 * @param khdjpdZbk
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户等级评定指标库-分页列表查询")
	@ApiOperation(value="客户等级评定指标库-分页列表查询", notes="客户等级评定指标库-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<KhdjpdZbk>> queryPageList(KhdjpdZbk khdjpdZbk,
                                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                  HttpServletRequest req) {
		Result<IPage<KhdjpdZbk>> result = new Result<IPage<KhdjpdZbk>>();
		QueryWrapper<KhdjpdZbk> queryWrapper = QueryGenerator.initQueryWrapper(khdjpdZbk, req.getParameterMap());
		Page<KhdjpdZbk> page = new Page<KhdjpdZbk>(pageNo, pageSize);
		IPage<KhdjpdZbk> pageList = khdjpdZbkService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param khdjpdZbk
	 * @return
	 */
	@AutoLog(value = "客户等级评定指标库-添加")
	@ApiOperation(value="客户等级评定指标库-添加", notes="客户等级评定指标库-添加")
	@PostMapping(value = "/add")
	public Result<KhdjpdZbk> add(@RequestBody KhdjpdZbk khdjpdZbk) {
		Result<KhdjpdZbk> result = new Result<KhdjpdZbk>();
		try {
			khdjpdZbkService.save(khdjpdZbk);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param khdjpdZbk
	 * @return
	 */
	@AutoLog(value = "客户等级评定指标库-编辑")
	@ApiOperation(value="客户等级评定指标库-编辑", notes="客户等级评定指标库-编辑")
	@PutMapping(value = "/edit")
	public Result<KhdjpdZbk> edit(@RequestBody KhdjpdZbk khdjpdZbk) {
		Result<KhdjpdZbk> result = new Result<KhdjpdZbk>();
		KhdjpdZbk khdjpdZbkEntity = khdjpdZbkService.getById(khdjpdZbk.getId());
		if(khdjpdZbkEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = khdjpdZbkService.updateById(khdjpdZbk);
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
	@AutoLog(value = "客户等级评定指标库-通过id删除")
	@ApiOperation(value="客户等级评定指标库-通过id删除", notes="客户等级评定指标库-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			khdjpdZbkService.removeById(id);
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
	@AutoLog(value = "客户等级评定指标库-批量删除")
	@ApiOperation(value="客户等级评定指标库-批量删除", notes="客户等级评定指标库-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<KhdjpdZbk> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<KhdjpdZbk> result = new Result<KhdjpdZbk>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.khdjpdZbkService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户等级评定指标库-通过id查询")
	@ApiOperation(value="客户等级评定指标库-通过id查询", notes="客户等级评定指标库-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<KhdjpdZbk> queryById(@RequestParam(name="id",required=true) String id) {
		Result<KhdjpdZbk> result = new Result<KhdjpdZbk>();
		KhdjpdZbk khdjpdZbk = khdjpdZbkService.getById(id);
		if(khdjpdZbk==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(khdjpdZbk);
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
      QueryWrapper<KhdjpdZbk> queryWrapper = null;
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              KhdjpdZbk khdjpdZbk = JSON.parseObject(deString, KhdjpdZbk.class);
              queryWrapper = QueryGenerator.initQueryWrapper(khdjpdZbk, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<KhdjpdZbk> pageList = khdjpdZbkService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "客户等级评定指标库列表");
      mv.addObject(NormalExcelConstants.CLASS, KhdjpdZbk.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户等级评定指标库", "导出人:"+sysUser.getRealname(), "导出信息"));
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
              List<KhdjpdZbk> listKhdjpdZbks = ExcelImportUtil.importExcel(file.getInputStream(), KhdjpdZbk.class, params);
              khdjpdZbkService.saveBatch(listKhdjpdZbks);
              return Result.ok("文件导入成功！数据行数:" + listKhdjpdZbks.size());
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

}
