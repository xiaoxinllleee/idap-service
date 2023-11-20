package org.cmms.modules.dkjkpt.bldkftjk.controller;

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
import org.cmms.modules.dkjkpt.bldkftjk.entity.DkjkptBldkftjkBsr;
import org.cmms.modules.dkjkpt.bldkftjk.service.IDkjkptBldkftjkBsrService;
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
 * @Description: 不良贷款反弹监控比昨日
 * @Author: cmms
 * @Date:   2019-09-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="不良贷款反弹监控比昨日")
@RestController
@RequestMapping("/dkjkpt/bldkftjk/dkjkptBldkftjkBsr")
public class DkjkptBldkftjkBsrController {
	@Autowired
	private IDkjkptBldkftjkBsrService dkjkptBldkftjkBsrService;

	/**
	  * 分页列表查询
	 * @param dkjkptBldkftjkBsr
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "不良贷款反弹监控比昨日-分页列表查询")
	@ApiOperation(value="不良贷款反弹监控比昨日-分页列表查询", notes="不良贷款反弹监控比昨日-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<DkjkptBldkftjkBsr>> queryPageList(DkjkptBldkftjkBsr dkjkptBldkftjkBsr,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<DkjkptBldkftjkBsr>> result = new Result<IPage<DkjkptBldkftjkBsr>>();
		QueryWrapper<DkjkptBldkftjkBsr> queryWrapper = QueryGenerator.initQueryWrapper(dkjkptBldkftjkBsr, req.getParameterMap());
		Page<DkjkptBldkftjkBsr> page = new Page<DkjkptBldkftjkBsr>(pageNo, pageSize);
		IPage<DkjkptBldkftjkBsr> pageList = dkjkptBldkftjkBsrService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param dkjkptBldkftjkBsr
	 * @return
	 */
	@AutoLog(value = "不良贷款反弹监控比昨日-添加")
	@ApiOperation(value="不良贷款反弹监控比昨日-添加", notes="不良贷款反弹监控比昨日-添加")
	@PostMapping(value = "/add")
	public Result<DkjkptBldkftjkBsr> add(@RequestBody DkjkptBldkftjkBsr dkjkptBldkftjkBsr) {
		Result<DkjkptBldkftjkBsr> result = new Result<DkjkptBldkftjkBsr>();
		try {
			dkjkptBldkftjkBsrService.save(dkjkptBldkftjkBsr);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param dkjkptBldkftjkBsr
	 * @return
	 */
	@AutoLog(value = "不良贷款反弹监控比昨日-编辑")
	@ApiOperation(value="不良贷款反弹监控比昨日-编辑", notes="不良贷款反弹监控比昨日-编辑")
	@PutMapping(value = "/edit")
	public Result<DkjkptBldkftjkBsr> edit(@RequestBody DkjkptBldkftjkBsr dkjkptBldkftjkBsr) {
		Result<DkjkptBldkftjkBsr> result = new Result<DkjkptBldkftjkBsr>();
		DkjkptBldkftjkBsr dkjkptBldkftjkBsrEntity = dkjkptBldkftjkBsrService.getById(dkjkptBldkftjkBsr.getDkzh());
		if(dkjkptBldkftjkBsrEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dkjkptBldkftjkBsrService.updateById(dkjkptBldkftjkBsr);
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
	@AutoLog(value = "不良贷款反弹监控比昨日-通过id删除")
	@ApiOperation(value="不良贷款反弹监控比昨日-通过id删除", notes="不良贷款反弹监控比昨日-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="dkzh",required=true) String id) {
		try {
			dkjkptBldkftjkBsrService.removeById(id);
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
	@AutoLog(value = "不良贷款反弹监控比昨日-批量删除")
	@ApiOperation(value="不良贷款反弹监控比昨日-批量删除", notes="不良贷款反弹监控比昨日-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<DkjkptBldkftjkBsr> deleteBatch(@RequestParam(name="dkzhs",required=true) String ids) {
		Result<DkjkptBldkftjkBsr> result = new Result<DkjkptBldkftjkBsr>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.dkjkptBldkftjkBsrService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "不良贷款反弹监控比昨日-通过id查询")
	@ApiOperation(value="不良贷款反弹监控比昨日-通过id查询", notes="不良贷款反弹监控比昨日-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<DkjkptBldkftjkBsr> queryById(@RequestParam(name="dkzh",required=true) String id) {
		Result<DkjkptBldkftjkBsr> result = new Result<DkjkptBldkftjkBsr>();
		DkjkptBldkftjkBsr dkjkptBldkftjkBsr = dkjkptBldkftjkBsrService.getById(id);
		if(dkjkptBldkftjkBsr==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(dkjkptBldkftjkBsr);
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
      QueryWrapper<DkjkptBldkftjkBsr> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              DkjkptBldkftjkBsr dkjkptBldkftjkBsr = JSON.parseObject(deString, DkjkptBldkftjkBsr.class);
              queryWrapper = QueryGenerator.initQueryWrapper(dkjkptBldkftjkBsr, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<DkjkptBldkftjkBsr> pageList = dkjkptBldkftjkBsrService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "不良贷款反弹监控比昨日列表");
      mv.addObject(NormalExcelConstants.CLASS, DkjkptBldkftjkBsr.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("不良贷款反弹监控比昨日列表数据", "导出人："+sysUser.getRealname(), "导出信息"));
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
              List<DkjkptBldkftjkBsr> listDkjkptBldkftjkBsrs = ExcelImportUtil.importExcel(file.getInputStream(), DkjkptBldkftjkBsr.class, params);
              dkjkptBldkftjkBsrService.saveBatch(listDkjkptBldkftjkBsrs);
              return Result.ok("文件导入成功！数据行数:" + listDkjkptBldkftjkBsrs.size());
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
	  *   统计
	  * @return
	  */
	 @PutMapping(value = "/extract")
	 public Result<?> extract1(@RequestBody JSONObject jsonObject) {
		 String tjyf = jsonObject.getString("tjyf");
		 tjyf = tjyf.replace("-", "");
		 try {
			 dkjkptBldkftjkBsrService.extract(tjyf);
		 } catch (Exception e) {
			 log.error("提取失败",e.getMessage());
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功!");
	 }

}
