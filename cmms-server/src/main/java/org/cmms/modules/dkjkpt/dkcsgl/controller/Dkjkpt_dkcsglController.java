package org.cmms.modules.dkjkpt.dkcsgl.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dkjkpt.dkcsgl.entity.Dkjkpt_dkcsgl;
import org.cmms.modules.dkjkpt.dkcsgl.service.IDkjkpt_dkcsglService;
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
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-12-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="1")
@RestController
@RequestMapping("/dkjkpt_dkcsgl/dkjkpt_dkcsgl")
public class Dkjkpt_dkcsglController {
	@Autowired
	private IDkjkpt_dkcsglService dkjkpt_dkcsglService;

	/**
	  * 分页列表查询
	 * @param dkjkpt_dkcsgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "1-分页列表查询")
	@ApiOperation(value="1-分页列表查询", notes="1-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Dkjkpt_dkcsgl>> queryPageList(Dkjkpt_dkcsgl dkjkpt_dkcsgl,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Dkjkpt_dkcsgl>> result = new Result<IPage<Dkjkpt_dkcsgl>>();
		QueryWrapper<Dkjkpt_dkcsgl> queryWrapper = QueryGenerator.initQueryWrapper(dkjkpt_dkcsgl, req.getParameterMap());
		Page<Dkjkpt_dkcsgl> page = new Page<Dkjkpt_dkcsgl>(pageNo, pageSize);
		IPage<Dkjkpt_dkcsgl> pageList = dkjkpt_dkcsglService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param dkjkpt_dkcsgl
	 * @return
	 */
	@AutoLog(value = "1-添加")
	@ApiOperation(value="1-添加", notes="1-添加")
	@PostMapping(value = "/add")
	public Result<Dkjkpt_dkcsgl> add(@RequestBody Dkjkpt_dkcsgl dkjkpt_dkcsgl) {
		Result<Dkjkpt_dkcsgl> result = new Result<Dkjkpt_dkcsgl>();
		try {
			dkjkpt_dkcsglService.save(dkjkpt_dkcsgl);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param dkjkpt_dkcsgl
	 * @return
	 */
	@AutoLog(value = "1-编辑")
	@ApiOperation(value="1-编辑", notes="1-编辑")
	@PutMapping(value = "/edit")
	public Result<Dkjkpt_dkcsgl> edit(@RequestBody Dkjkpt_dkcsgl dkjkpt_dkcsgl) {
		Result<Dkjkpt_dkcsgl> result = new Result<Dkjkpt_dkcsgl>();
		Dkjkpt_dkcsgl dkjkpt_dkcsglEntity = dkjkpt_dkcsglService.getById(dkjkpt_dkcsgl.getAcctNo());
		if(dkjkpt_dkcsglEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dkjkpt_dkcsglService.updateById(dkjkpt_dkcsgl);
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
			dkjkpt_dkcsglService.removeById(id);
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
	public Result<Dkjkpt_dkcsgl> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Dkjkpt_dkcsgl> result = new Result<Dkjkpt_dkcsgl>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.dkjkpt_dkcsglService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<Dkjkpt_dkcsgl> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Dkjkpt_dkcsgl> result = new Result<Dkjkpt_dkcsgl>();
		Dkjkpt_dkcsgl dkjkpt_dkcsgl = dkjkpt_dkcsglService.getById(id);
		if(dkjkpt_dkcsgl==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(dkjkpt_dkcsgl);
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
      QueryWrapper<Dkjkpt_dkcsgl> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Dkjkpt_dkcsgl dkjkpt_dkcsgl = JSON.parseObject(deString, Dkjkpt_dkcsgl.class);
              queryWrapper = QueryGenerator.initQueryWrapper(dkjkpt_dkcsgl, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Dkjkpt_dkcsgl> pageList = dkjkpt_dkcsglService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "贷款催收管理列表");
      mv.addObject(NormalExcelConstants.CLASS, Dkjkpt_dkcsgl.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("贷款催收管理列表数据", "导出人:Jeecg", "导出信息"));
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
              List<Dkjkpt_dkcsgl> listDkjkpt_dkcsgls = ExcelImportUtil.importExcel(file.getInputStream(), Dkjkpt_dkcsgl.class, params);
              dkjkpt_dkcsglService.saveBatch(listDkjkpt_dkcsgls);
              return Result.ok("文件导入成功！数据行数:" + listDkjkpt_dkcsgls.size());
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
	 @RequestMapping(value = "/extract" , method = RequestMethod.PUT)
	 public Result<?> extract() {
		 Result<List<Dkjkpt_dkcsgl>> result =new Result<>();
		 try {
			 dkjkpt_dkcsglService.extract();
			 result.setSuccess(true);
		 } catch (Exception e) {
			 log.error("提取失败",e.getMessage());
			 result.setSuccess(false);
		 }
		 return  result;
	 }
}
