package org.cmms.modules.ckjkpt.jcyj.qhckpldgl.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.ckjkpt.jcyj.qhckpldgl.entity.CkjkptQhckpldgl;
import org.cmms.modules.ckjkpt.jcyj.qhckpldgl.service.ICkjkptQhckpldglService;
import org.cmms.modules.util.PageUtil;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

 /**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="1")
@RestController
@RequestMapping("/CkjkptQhckpldgl/ckjkptQhckpldgl")
public class CkjkptQhckpldglController {
	@Autowired
	private ICkjkptQhckpldglService ckjkptQhckpldglService;

	/**
	  * 分页列表查询
	 * @param ckjkptQhckpldgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "1-分页列表查询")
	@ApiOperation(value="1-分页列表查询", notes="1-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CkjkptQhckpldgl ckjkptQhckpldgl,
														@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
														@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
														HttpServletRequest req) {
//		QueryWrapper<CkjkptQhckpldgl> queryWrapper = QueryGenerator.initQueryWrapper(ckjkptQhckpldgl, req.getParameterMap());
//		List<CkjkptQhckpldgl> list = ckjkptQhckpldglService.list(queryWrapper);
//		IPage<CkjkptQhckpldgl> pageList = PageUtil.getPages(list, list == null ? 0 : list.size(), pageSize, pageNo);
//		return Result.ok(pageList);
		QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(ckjkptQhckpldgl, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkjkptQhckpldglService.class,ckjkptQhckpldglService,pageNo,pageSize,queryWrapper,"tjyf","jgdm");
		return Result.ok(pageList);
	}

	/**
	  *   添加
	 * @param ckjkptQhckpldgl
	 * @return
	 */
	@AutoLog(value = "1-添加")
	@ApiOperation(value="1-添加", notes="1-添加")
	@PostMapping(value = "/add")
	public Result<CkjkptQhckpldgl> add(@RequestBody CkjkptQhckpldgl ckjkptQhckpldgl) {
		Result<CkjkptQhckpldgl> result = new Result<CkjkptQhckpldgl>();
		try {
			ckjkptQhckpldglService.save(ckjkptQhckpldgl);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param ckjkptQhckpldgl
	 * @return
	 *//*
	@AutoLog(value = "1-编辑")
	@ApiOperation(value="1-编辑", notes="1-编辑")
	@PutMapping(value = "/edit")
	public Result<CkjkptQhckpldgl> edit(@RequestBody CkjkptQhckpldgl ckjkptQhckpldgl) {
		Result<CkjkptQhckpldgl> result = new Result<CkjkptQhckpldgl>();
		CkjkptQhckpldgl ckjkptQhckpldglEntity = ckjkptQhckpldglService.getById(ckjkptQhckpldgl.getId());
		if(ckjkptQhckpldglEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = ckjkptQhckpldglService.updateById(ckjkptQhckpldgl);
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
	@AutoLog(value = "1-通过id删除")
	@ApiOperation(value="1-通过id删除", notes="1-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			ckjkptQhckpldglService.removeById(id);
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
	public Result<CkjkptQhckpldgl> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<CkjkptQhckpldgl> result = new Result<CkjkptQhckpldgl>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.ckjkptQhckpldglService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<CkjkptQhckpldgl> queryById(@RequestParam(name="id",required=true) String id) {
		Result<CkjkptQhckpldgl> result = new Result<CkjkptQhckpldgl>();
		CkjkptQhckpldgl ckjkptQhckpldgl = ckjkptQhckpldglService.getById(id);
		if(ckjkptQhckpldgl==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(ckjkptQhckpldgl);
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
	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
      // Step.1 组装查询条件
      QueryWrapper<CkjkptQhckpldgl> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              CkjkptQhckpldgl ckjkptQhckpldgl = JSON.parseObject(deString, CkjkptQhckpldgl.class);
              queryWrapper = QueryGenerator.initQueryWrapper(ckjkptQhckpldgl, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<CkjkptQhckpldgl> pageList = ckjkptQhckpldglService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "全行存款偏离度管理列表");
      mv.addObject(NormalExcelConstants.CLASS, CkjkptQhckpldgl.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("全行存款偏离度管理列表数据", "导出人:"+sysUser.getRealname(), "导出信息"));
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
              List<CkjkptQhckpldgl> listCkjkptQhckpldgls = ExcelImportUtil.importExcel(file.getInputStream(), CkjkptQhckpldgl.class, params);
              ckjkptQhckpldglService.saveBatch(listCkjkptQhckpldgls);
              return Result.ok("文件导入成功！数据行数:" + listCkjkptQhckpldgls.size());
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
