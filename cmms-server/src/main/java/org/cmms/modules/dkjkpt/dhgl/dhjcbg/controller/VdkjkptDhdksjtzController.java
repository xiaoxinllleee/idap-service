package org.cmms.modules.dkjkpt.dhgl.dhjcbg.controller;

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
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.entity.VdkjkptDhdksjtz;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.service.IVdkjkptDhdksjtzService;
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
 * @Description: 贷后检查报告
 * @Author: cmms
 * @Date:   2019-10-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷后检查报告")
@RestController
@RequestMapping("/dkjkpt/dhgl/dhjcbg/vdkjkptDhdksjtz")
public class VdkjkptDhdksjtzController {
	@Autowired
	private IVdkjkptDhdksjtzService vdkjkptDhdksjtzService;
	
	/**
	  * 分页列表查询
	 * @param vdkjkptDhdksjtz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷后检查报告-分页列表查询")
	@ApiOperation(value="贷后检查报告-分页列表查询", notes="贷后检查报告-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<VdkjkptDhdksjtz>> queryPageList(VdkjkptDhdksjtz vdkjkptDhdksjtz,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<VdkjkptDhdksjtz>> result = new Result<IPage<VdkjkptDhdksjtz>>();
		QueryWrapper<VdkjkptDhdksjtz> queryWrapper = QueryGenerator.initQueryWrapper(vdkjkptDhdksjtz, req.getParameterMap());
		Page<VdkjkptDhdksjtz> page = new Page<VdkjkptDhdksjtz>(pageNo, pageSize);
		IPage<VdkjkptDhdksjtz> pageList = vdkjkptDhdksjtzService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param vdkjkptDhdksjtz
	 * @return
	 */
	@AutoLog(value = "贷后检查报告-添加")
	@ApiOperation(value="贷后检查报告-添加", notes="贷后检查报告-添加")
	@PostMapping(value = "/add")
	public Result<VdkjkptDhdksjtz> add(@RequestBody VdkjkptDhdksjtz vdkjkptDhdksjtz) {
		Result<VdkjkptDhdksjtz> result = new Result<VdkjkptDhdksjtz>();
		try {
			vdkjkptDhdksjtzService.save(vdkjkptDhdksjtz);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param vdkjkptDhdksjtz
	 * @return
	 */
	@AutoLog(value = "贷后检查报告-编辑")
	@ApiOperation(value="贷后检查报告-编辑", notes="贷后检查报告-编辑")
	@PutMapping(value = "/edit")
	public Result<VdkjkptDhdksjtz> edit(@RequestBody VdkjkptDhdksjtz vdkjkptDhdksjtz) {
		Result<VdkjkptDhdksjtz> result = new Result<VdkjkptDhdksjtz>();
		VdkjkptDhdksjtz vdkjkptDhdksjtzEntity = vdkjkptDhdksjtzService.getById(vdkjkptDhdksjtz.getZjhm());
		if(vdkjkptDhdksjtzEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = vdkjkptDhdksjtzService.updateById(vdkjkptDhdksjtz);
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
	@AutoLog(value = "贷后检查报告-通过id删除")
	@ApiOperation(value="贷后检查报告-通过id删除", notes="贷后检查报告-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			vdkjkptDhdksjtzService.removeById(id);
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
	@AutoLog(value = "贷后检查报告-批量删除")
	@ApiOperation(value="贷后检查报告-批量删除", notes="贷后检查报告-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<VdkjkptDhdksjtz> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<VdkjkptDhdksjtz> result = new Result<VdkjkptDhdksjtz>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.vdkjkptDhdksjtzService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷后检查报告-通过id查询")
	@ApiOperation(value="贷后检查报告-通过id查询", notes="贷后检查报告-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<VdkjkptDhdksjtz> queryById(@RequestParam(name="id",required=true) String id) {
		Result<VdkjkptDhdksjtz> result = new Result<VdkjkptDhdksjtz>();
		VdkjkptDhdksjtz vdkjkptDhdksjtz = vdkjkptDhdksjtzService.getById(id);
		if(vdkjkptDhdksjtz==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(vdkjkptDhdksjtz);
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
      QueryWrapper<VdkjkptDhdksjtz> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              VdkjkptDhdksjtz vdkjkptDhdksjtz = JSON.parseObject(deString, VdkjkptDhdksjtz.class);
              queryWrapper = QueryGenerator.initQueryWrapper(vdkjkptDhdksjtz, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<VdkjkptDhdksjtz> pageList = vdkjkptDhdksjtzService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "贷后检查报告列表");
      mv.addObject(NormalExcelConstants.CLASS, VdkjkptDhdksjtz.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("贷后检查报告列表数据", "导出人:Jeecg", "导出信息"));
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
              List<VdkjkptDhdksjtz> listVdkjkptDhdksjtzs = ExcelImportUtil.importExcel(file.getInputStream(), VdkjkptDhdksjtz.class, params);
              vdkjkptDhdksjtzService.saveBatch(listVdkjkptDhdksjtzs);
              return Result.ok("文件导入成功！数据行数:" + listVdkjkptDhdksjtzs.size());
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
