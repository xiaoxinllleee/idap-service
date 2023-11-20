package org.cmms.modules.tjfx.khjdfgl.controller;

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
import org.cmms.modules.tjfx.khjdfgl.entity.TjfxJdfgltj;
import org.cmms.modules.tjfx.khjdfgl.service.ITjfxJdfgltjService;
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
 * @Description: 建档覆盖率统计
 * @Author: cmms
 * @Date:   2019-09-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags="建档覆盖率统计")
@RestController
@RequestMapping("/tjfx/khjdfgl/tjfxJdfgltj")
public class TjfxJdfgltjController {
	@Autowired
	private ITjfxJdfgltjService tjfxJdfgltjService;
	
	/**
	  * 分页列表查询
	 * @param tjfxJdfgltj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "建档覆盖率统计-分页列表查询")
	@ApiOperation(value="建档覆盖率统计-分页列表查询", notes="建档覆盖率统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<TjfxJdfgltj>> queryPageList(TjfxJdfgltj tjfxJdfgltj,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<TjfxJdfgltj>> result = new Result<IPage<TjfxJdfgltj>>();
		QueryWrapper<TjfxJdfgltj> queryWrapper = QueryGenerator.initQueryWrapper(tjfxJdfgltj, req.getParameterMap());
		Page<TjfxJdfgltj> page = new Page<TjfxJdfgltj>(pageNo, pageSize);
		IPage<TjfxJdfgltj> pageList = tjfxJdfgltjService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param tjfxJdfgltj
	 * @return
	 */
	@AutoLog(value = "建档覆盖率统计-添加")
	@ApiOperation(value="建档覆盖率统计-添加", notes="建档覆盖率统计-添加")
	@PostMapping(value = "/add")
	public Result<TjfxJdfgltj> add(@RequestBody TjfxJdfgltj tjfxJdfgltj) {
		Result<TjfxJdfgltj> result = new Result<TjfxJdfgltj>();
		try {
			tjfxJdfgltjService.save(tjfxJdfgltj);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param tjfxJdfgltj
	 * @return
	/* *//*
	@AutoLog(value = "建档覆盖率统计-编辑")
	@ApiOperation(value="建档覆盖率统计-编辑", notes="建档覆盖率统计-编辑")
	@PutMapping(value = "/edit")
	public Result<TjfxJdfgltj> edit(@RequestBody TjfxJdfgltj tjfxJdfgltj) {
		Result<TjfxJdfgltj> result = new Result<TjfxJdfgltj>();
		TjfxJdfgltj tjfxJdfgltjEntity = tjfxJdfgltjService.getById(tjfxJdfgltj.getId());
		if(tjfxJdfgltjEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = tjfxJdfgltjService.updateById(tjfxJdfgltj);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		
		return result;
	}
	*/
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "建档覆盖率统计-通过id删除")
	@ApiOperation(value="建档覆盖率统计-通过id删除", notes="建档覆盖率统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			tjfxJdfgltjService.removeById(id);
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
	@AutoLog(value = "建档覆盖率统计-批量删除")
	@ApiOperation(value="建档覆盖率统计-批量删除", notes="建档覆盖率统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<TjfxJdfgltj> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<TjfxJdfgltj> result = new Result<TjfxJdfgltj>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.tjfxJdfgltjService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "建档覆盖率统计-通过id查询")
	@ApiOperation(value="建档覆盖率统计-通过id查询", notes="建档覆盖率统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<TjfxJdfgltj> queryById(@RequestParam(name="id",required=true) String id) {
		Result<TjfxJdfgltj> result = new Result<TjfxJdfgltj>();
		TjfxJdfgltj tjfxJdfgltj = tjfxJdfgltjService.getById(id);
		if(tjfxJdfgltj==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(tjfxJdfgltj);
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
      QueryWrapper<TjfxJdfgltj> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              TjfxJdfgltj tjfxJdfgltj = JSON.parseObject(deString, TjfxJdfgltj.class);
              queryWrapper = QueryGenerator.initQueryWrapper(tjfxJdfgltj, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<TjfxJdfgltj> pageList = tjfxJdfgltjService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "建档覆盖率统计列表");
      mv.addObject(NormalExcelConstants.CLASS, TjfxJdfgltj.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("建档覆盖率统计列表数据", "导出人:Jeecg", "导出信息"));
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
              List<TjfxJdfgltj> listTjfxJdfgltjs = ExcelImportUtil.importExcel(file.getInputStream(), TjfxJdfgltj.class, params);
              tjfxJdfgltjService.saveBatch(listTjfxJdfgltjs);
              return Result.ok("文件导入成功！数据行数:" + listTjfxJdfgltjs.size());
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
