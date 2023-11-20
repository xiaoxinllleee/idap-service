package org.cmms.modules.dkjkpt.dksjjk.zhdkshtj.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dkjkpt.dksjjk.zhdkshtj.entity.Zhdkshtj;
import org.cmms.modules.dkjkpt.dksjjk.zhdkshtj.service.IZhdkshtjService;
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
 * @Description: 支行贷款收回统计
 * @Author: cmms
 * @Date:   2019-09-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行贷款收回统计")
@RestController
@RequestMapping("/zhdkshtj/zhdkshtj")
public class ZhdkshtjController {
	@Autowired
	private IZhdkshtjService zhdkshtjService;
	
	/**
	  * 分页列表查询
	 * @param zhdkshtj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行贷款收回统计-分页列表查询")
	@ApiOperation(value="支行贷款收回统计-分页列表查询", notes="支行贷款收回统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Zhdkshtj>> queryPageList(Zhdkshtj zhdkshtj,
                                                 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                 HttpServletRequest req) {
		Result<IPage<Zhdkshtj>> result = new Result<IPage<Zhdkshtj>>();
		QueryWrapper<Zhdkshtj> queryWrapper = QueryGenerator.initQueryWrapper(zhdkshtj, req.getParameterMap());
		Page<Zhdkshtj> page = new Page<Zhdkshtj>(pageNo, pageSize);
		IPage<Zhdkshtj> pageList = zhdkshtjService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param zhdkshtj
	 * @return
	 */
	@AutoLog(value = "支行贷款收回统计-添加")
	@ApiOperation(value="支行贷款收回统计-添加", notes="支行贷款收回统计-添加")
	@PostMapping(value = "/add")
	public Result<Zhdkshtj> add(@RequestBody Zhdkshtj zhdkshtj) {
		Result<Zhdkshtj> result = new Result<Zhdkshtj>();
		try {
			zhdkshtjService.save(zhdkshtj);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param zhdkshtj
	 * @return
	 */
	@AutoLog(value = "支行贷款收回统计-编辑")
	@ApiOperation(value="支行贷款收回统计-编辑", notes="支行贷款收回统计-编辑")
	@PutMapping(value = "/edit")
	public Result<Zhdkshtj> edit(@RequestBody Zhdkshtj zhdkshtj) {
		Result<Zhdkshtj> result = new Result<Zhdkshtj>();
		Zhdkshtj zhdkshtjEntity = zhdkshtjService.getById(zhdkshtj.getJgdm());
		if(zhdkshtjEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = zhdkshtjService.updateById(zhdkshtj);
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
	@AutoLog(value = "支行贷款收回统计-通过id删除")
	@ApiOperation(value="支行贷款收回统计-通过id删除", notes="支行贷款收回统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			zhdkshtjService.removeById(id);
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
	@AutoLog(value = "支行贷款收回统计-批量删除")
	@ApiOperation(value="支行贷款收回统计-批量删除", notes="支行贷款收回统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<Zhdkshtj> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Zhdkshtj> result = new Result<Zhdkshtj>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.zhdkshtjService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行贷款收回统计-通过id查询")
	@ApiOperation(value="支行贷款收回统计-通过id查询", notes="支行贷款收回统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Zhdkshtj> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Zhdkshtj> result = new Result<Zhdkshtj>();
		Zhdkshtj zhdkshtj = zhdkshtjService.getById(id);
		if(zhdkshtj==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(zhdkshtj);
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
      QueryWrapper<Zhdkshtj> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Zhdkshtj zhdkshtj = JSON.parseObject(deString, Zhdkshtj.class);
              queryWrapper = QueryGenerator.initQueryWrapper(zhdkshtj, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Zhdkshtj> pageList = zhdkshtjService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "支行贷款收回统计列表");
      mv.addObject(NormalExcelConstants.CLASS, Zhdkshtj.class);
	  LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("支行贷款收回统计列表数据", "导出人:"+loginUser.getRealname(), "导出信息"));
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
              List<Zhdkshtj> listZhdkshtjs = ExcelImportUtil.importExcel(file.getInputStream(), Zhdkshtj.class, params);
              zhdkshtjService.saveBatch(listZhdkshtjs);
              return Result.ok("文件导入成功！数据行数:" + listZhdkshtjs.size());
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
	  * 提取花名册客户信息
	  * @return
	  */
	 @AutoLog(value = "提取")
	 @ApiOperation(value="提取", notes="提取")
	 @RequestMapping(value = "/init" , method = RequestMethod.PUT)
	 public Result<Zhdkshtj> init(@RequestBody JSONObject object) {
		 String tjyf = object.getString("tjyf");
		 tjyf = tjyf.replace("-", "");
		 Result<Zhdkshtj> result = new Result<Zhdkshtj>();
		 try {
			 zhdkshtjService.init(tjyf);
			 result.setSuccess(true);
			 result.setMessage("提取成功");
			 return result;
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 result.setSuccess(false);
			 result.setMessage("提取失败");
			 return result;
		 }

	 }

}
