package org.cmms.modules.ckjkpt.dqckdqyj.controller;

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
import org.cmms.modules.ckjkpt.dqckdqyj.entity.CkjkptDqckdqyj;
import org.cmms.modules.ckjkpt.dqckdqyj.service.ICkjkptDqckdqyjService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

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
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 定期存款到期预警
 * @Author: cmms
 * @Date:   2019-09-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="定期存款到期预警")
@RestController
@RequestMapping("/ckjkpt.dqckdqyj/ckjkptDqckdqyj")
public class CkjkptDqckdqyjController {
	@Autowired
	private ICkjkptDqckdqyjService ckjkptDqckdqyjService;

	/**
	  * 分页列表查询
	 * @param ckjkptDqckdqyj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "定期存款到期预警-分页列表查询")
	@ApiOperation(value="定期存款到期预警-分页列表查询", notes="定期存款到期预警-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CkjkptDqckdqyj ckjkptDqckdqyj,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(ckjkptDqckdqyj, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkjkptDqckdqyjService.class,ckjkptDqckdqyjService,pageNo,pageSize,queryWrapper,"zzbz","yggh","zjhm");
		return Result.ok(pageList);
	}

	/**
	  *   添加
	 * @param ckjkptDqckdqyj
	 * @return
	 */
	@AutoLog(value = "定期存款到期预警-添加")
	@ApiOperation(value="定期存款到期预警-添加", notes="定期存款到期预警-添加")
	@PostMapping(value = "/add")
	public Result<CkjkptDqckdqyj> add(@RequestBody CkjkptDqckdqyj ckjkptDqckdqyj) {
		Result<CkjkptDqckdqyj> result = new Result<CkjkptDqckdqyj>();
		try {
			ckjkptDqckdqyjService.save(ckjkptDqckdqyj);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param ckjkptDqckdqyj
	 * @return
	 */
	@AutoLog(value = "定期存款到期预警-编辑")
	@ApiOperation(value="定期存款到期预警-编辑", notes="定期存款到期预警-编辑")
	@PutMapping(value = "/edit")
	public Result<CkjkptDqckdqyj> edit(@RequestBody CkjkptDqckdqyj ckjkptDqckdqyj) {
		Result<CkjkptDqckdqyj> result = new Result<CkjkptDqckdqyj>();
		CkjkptDqckdqyj ckjkptDqckdqyjEntity = ckjkptDqckdqyjService.getById(ckjkptDqckdqyj.getCkzh());
		if(ckjkptDqckdqyjEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = ckjkptDqckdqyjService.updateById(ckjkptDqckdqyj);
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
	@AutoLog(value = "定期存款到期预警-通过id删除")
	@ApiOperation(value="定期存款到期预警-通过id删除", notes="定期存款到期预警-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="ckzh",required=true) String id) {
		try {
			ckjkptDqckdqyjService.removeById(id);
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
	@AutoLog(value = "定期存款到期预警-批量删除")
	@ApiOperation(value="定期存款到期预警-批量删除", notes="定期存款到期预警-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<CkjkptDqckdqyj> deleteBatch(@RequestParam(name="ckzhs",required=true) String ids) {
		Result<CkjkptDqckdqyj> result = new Result<CkjkptDqckdqyj>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.ckjkptDqckdqyjService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "定期存款到期预警-通过id查询")
	@ApiOperation(value="定期存款到期预警-通过id查询", notes="定期存款到期预警-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<CkjkptDqckdqyj> queryById(@RequestParam(name="ckzh",required=true) String id) {
		Result<CkjkptDqckdqyj> result = new Result<CkjkptDqckdqyj>();
		CkjkptDqckdqyj ckjkptDqckdqyj = ckjkptDqckdqyjService.getById(id);
		if(ckjkptDqckdqyj==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(ckjkptDqckdqyj);
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
      QueryWrapper<CkjkptDqckdqyj> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              CkjkptDqckdqyj ckjkptDqckdqyj = JSON.parseObject(deString, CkjkptDqckdqyj.class);
              queryWrapper = QueryGenerator.initQueryWrapper(ckjkptDqckdqyj, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<CkjkptDqckdqyj> pageList = ckjkptDqckdqyjService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "定期存款到期预警列表");
      mv.addObject(NormalExcelConstants.CLASS, CkjkptDqckdqyj.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("定期存款到期预警列表数据", "导出人:"+sysUser.getRealname(), "导出信息"));
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
              List<CkjkptDqckdqyj> listCkjkptDqckdqyjs = ExcelImportUtil.importExcel(file.getInputStream(), CkjkptDqckdqyj.class, params);
              ckjkptDqckdqyjService.saveBatch(listCkjkptDqckdqyjs);
              return Result.ok("文件导入成功！数据行数:" + listCkjkptDqckdqyjs.size());
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
