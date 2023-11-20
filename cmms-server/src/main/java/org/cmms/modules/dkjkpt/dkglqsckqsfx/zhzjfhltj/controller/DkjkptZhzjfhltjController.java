package org.cmms.modules.dkjkpt.dkglqsckqsfx.zhzjfhltj.controller;

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
import org.cmms.modules.dkjkpt.dkglqsckqsfx.zhzjfhltj.entity.DkjkptZhzjfhltj;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.zhzjfhltj.service.IDkjkptZhzjfhltjService;
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
 * @Description: 支行资金返还率统计
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行资金返还率统计")
@RestController
@RequestMapping("/dkjkpt/dkjkptZhzjfhltj")
public class DkjkptZhzjfhltjController {
	@Autowired
	private IDkjkptZhzjfhltjService dkjkptZhzjfhltjService;
	
	/**
	  * 分页列表查询
	 * @param dkjkptZhzjfhltj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行资金返还率统计-分页列表查询")
	@ApiOperation(value="支行资金返还率统计-分页列表查询", notes="支行资金返还率统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<DkjkptZhzjfhltj>> queryPageList(DkjkptZhzjfhltj dkjkptZhzjfhltj,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<DkjkptZhzjfhltj>> result = new Result<IPage<DkjkptZhzjfhltj>>();
		QueryWrapper<DkjkptZhzjfhltj> queryWrapper = QueryGenerator.initQueryWrapper(dkjkptZhzjfhltj, req.getParameterMap());
		Page<DkjkptZhzjfhltj> page = new Page<DkjkptZhzjfhltj>(pageNo, pageSize);
		IPage<DkjkptZhzjfhltj> pageList = dkjkptZhzjfhltjService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param dkjkptZhzjfhltj
	 * @return
	 */
	@AutoLog(value = "支行资金返还率统计-添加")
	@ApiOperation(value="支行资金返还率统计-添加", notes="支行资金返还率统计-添加")
	@PostMapping(value = "/add")
	public Result<DkjkptZhzjfhltj> add(@RequestBody DkjkptZhzjfhltj dkjkptZhzjfhltj) {
		Result<DkjkptZhzjfhltj> result = new Result<DkjkptZhzjfhltj>();
		try {
			dkjkptZhzjfhltjService.save(dkjkptZhzjfhltj);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行资金返还率统计-通过id删除")
	@ApiOperation(value="支行资金返还率统计-通过id删除", notes="支行资金返还率统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			dkjkptZhzjfhltjService.removeById(id);
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
	@AutoLog(value = "支行资金返还率统计-批量删除")
	@ApiOperation(value="支行资金返还率统计-批量删除", notes="支行资金返还率统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<DkjkptZhzjfhltj> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<DkjkptZhzjfhltj> result = new Result<DkjkptZhzjfhltj>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.dkjkptZhzjfhltjService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行资金返还率统计-通过id查询")
	@ApiOperation(value="支行资金返还率统计-通过id查询", notes="支行资金返还率统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<DkjkptZhzjfhltj> queryById(@RequestParam(name="id",required=true) String id) {
		Result<DkjkptZhzjfhltj> result = new Result<DkjkptZhzjfhltj>();
		DkjkptZhzjfhltj dkjkptZhzjfhltj = dkjkptZhzjfhltjService.getById(id);
		if(dkjkptZhzjfhltj==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(dkjkptZhzjfhltj);
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
      QueryWrapper<DkjkptZhzjfhltj> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              DkjkptZhzjfhltj dkjkptZhzjfhltj = JSON.parseObject(deString, DkjkptZhzjfhltj.class);
              queryWrapper = QueryGenerator.initQueryWrapper(dkjkptZhzjfhltj, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<DkjkptZhzjfhltj> pageList = dkjkptZhzjfhltjService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "支行资金返还率统计列表");
      mv.addObject(NormalExcelConstants.CLASS, DkjkptZhzjfhltj.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("支行资金返还率统计列表数据", "导出人:Jeecg", "导出信息"));
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
              List<DkjkptZhzjfhltj> listDkjkptZhzjfhltjs = ExcelImportUtil.importExcel(file.getInputStream(), DkjkptZhzjfhltj.class, params);
              dkjkptZhzjfhltjService.saveBatch(listDkjkptZhzjfhltjs);
              return Result.ok("文件导入成功！数据行数:" + listDkjkptZhzjfhltjs.size());
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
