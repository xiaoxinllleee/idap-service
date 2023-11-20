package org.cmms.modules.dkjkpt.zjghqk.zhzjfhltj.controller;

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
import org.cmms.modules.dkjkpt.zjghqk.zhzjfhltj.entity.Dkjkpt_zhzjfhtj;
import org.cmms.modules.dkjkpt.zjghqk.zhzjfhltj.service.IDkjkpt_zhzjfhtjService;
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
 * @Author: jeecg-boot
 * @Date:   2019-09-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行资金返还率统计")
@RestController
@RequestMapping("/dkjkpt.zjghqk.zhzjfhltj/dkjkpt_zhzjfhtj")
public class Dkjkpt_zhzjfhtjController {
	@Autowired
	private IDkjkpt_zhzjfhtjService dkjkpt_zhzjfhtjService;
	
	/**
	  * 分页列表查询
	 * @param dkjkpt_zhzjfhtj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行资金返还率统计-分页列表查询")
	@ApiOperation(value="支行资金返还率统计-分页列表查询", notes="支行资金返还率统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Dkjkpt_zhzjfhtj>> queryPageList(Dkjkpt_zhzjfhtj dkjkpt_zhzjfhtj,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Dkjkpt_zhzjfhtj>> result = new Result<IPage<Dkjkpt_zhzjfhtj>>();
		QueryWrapper<Dkjkpt_zhzjfhtj> queryWrapper = QueryGenerator.initQueryWrapper(dkjkpt_zhzjfhtj, req.getParameterMap());
		Page<Dkjkpt_zhzjfhtj> page = new Page<Dkjkpt_zhzjfhtj>(pageNo, pageSize);
		IPage<Dkjkpt_zhzjfhtj> pageList = dkjkpt_zhzjfhtjService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param dkjkpt_zhzjfhtj
	 * @return
	 */
	@AutoLog(value = "支行资金返还率统计-添加")
	@ApiOperation(value="支行资金返还率统计-添加", notes="支行资金返还率统计-添加")
	@PostMapping(value = "/add")
	public Result<Dkjkpt_zhzjfhtj> add(@RequestBody Dkjkpt_zhzjfhtj dkjkpt_zhzjfhtj) {
		Result<Dkjkpt_zhzjfhtj> result = new Result<Dkjkpt_zhzjfhtj>();
		try {
			dkjkpt_zhzjfhtjService.save(dkjkpt_zhzjfhtj);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param dkjkpt_zhzjfhtj
	 * @return
	 */
	@AutoLog(value = "支行资金返还率统计-编辑")
	@ApiOperation(value="支行资金返还率统计-编辑", notes="支行资金返还率统计-编辑")
	@PutMapping(value = "/edit")
	public Result<Dkjkpt_zhzjfhtj> edit(@RequestBody Dkjkpt_zhzjfhtj dkjkpt_zhzjfhtj) {
		Result<Dkjkpt_zhzjfhtj> result = new Result<Dkjkpt_zhzjfhtj>();
		Dkjkpt_zhzjfhtj dkjkpt_zhzjfhtjEntity = dkjkpt_zhzjfhtjService.getById(dkjkpt_zhzjfhtj.getJgdm());
		if(dkjkpt_zhzjfhtjEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dkjkpt_zhzjfhtjService.updateById(dkjkpt_zhzjfhtj);
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
	@AutoLog(value = "支行资金返还率统计-通过id删除")
	@ApiOperation(value="支行资金返还率统计-通过id删除", notes="支行资金返还率统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			dkjkpt_zhzjfhtjService.removeById(id);
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
	public Result<Dkjkpt_zhzjfhtj> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Dkjkpt_zhzjfhtj> result = new Result<Dkjkpt_zhzjfhtj>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.dkjkpt_zhzjfhtjService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<Dkjkpt_zhzjfhtj> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Dkjkpt_zhzjfhtj> result = new Result<Dkjkpt_zhzjfhtj>();
		Dkjkpt_zhzjfhtj dkjkpt_zhzjfhtj = dkjkpt_zhzjfhtjService.getById(id);
		if(dkjkpt_zhzjfhtj==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(dkjkpt_zhzjfhtj);
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
      QueryWrapper<Dkjkpt_zhzjfhtj> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Dkjkpt_zhzjfhtj dkjkpt_zhzjfhtj = JSON.parseObject(deString, Dkjkpt_zhzjfhtj.class);
              queryWrapper = QueryGenerator.initQueryWrapper(dkjkpt_zhzjfhtj, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Dkjkpt_zhzjfhtj> pageList = dkjkpt_zhzjfhtjService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "支行资金返还率统计列表");
      mv.addObject(NormalExcelConstants.CLASS, Dkjkpt_zhzjfhtj.class);
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
              List<Dkjkpt_zhzjfhtj> listDkjkpt_zhzjfhtjs = ExcelImportUtil.importExcel(file.getInputStream(), Dkjkpt_zhzjfhtj.class, params);
              dkjkpt_zhzjfhtjService.saveBatch(listDkjkpt_zhzjfhtjs);
              return Result.ok("文件导入成功！数据行数:" + listDkjkpt_zhzjfhtjs.size());
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
