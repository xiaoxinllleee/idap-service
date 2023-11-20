package org.cmms.modules.dkjkpt.bldkftjk.qhbldkftjk.controller;

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
import org.cmms.modules.dkjkpt.bldkftjk.qhbldkftjk.entity.DkjkptQhbldkftjkBsnm;
import org.cmms.modules.dkjkpt.bldkftjk.qhbldkftjk.service.IDkjkptQhbldkftjkBsnmService;
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
 * @Description: 全行不良贷款反弹监控比上年末
 * @Author: cmms
 * @Date:   2019-09-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="全行不良贷款反弹监控比上年末")
@RestController
@RequestMapping("/dkjkpt.bldkftjk.qhbldkftjk/dkjkpt_qhbldkftjk_bsnm")
public class DkjkptQhbldkftjkBsnmController {
	@Autowired
	private IDkjkptQhbldkftjkBsnmService dkjkpt_qhbldkftjk_bsnmService;

	/**
	  * 分页列表查询
	 * @param dkjkpt_qhbldkftjk_bsnm
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "全行不良贷款反弹监控比上年末-分页列表查询")
	@ApiOperation(value="全行不良贷款反弹监控比上年末-分页列表查询", notes="全行不良贷款反弹监控比上年末-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<DkjkptQhbldkftjkBsnm>> queryPageList(DkjkptQhbldkftjkBsnm dkjkpt_qhbldkftjk_bsnm,
                                                             @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                             @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                             HttpServletRequest req) {
		Result<IPage<DkjkptQhbldkftjkBsnm>> result = new Result<IPage<DkjkptQhbldkftjkBsnm>>();
		QueryWrapper<DkjkptQhbldkftjkBsnm> queryWrapper = QueryGenerator.initQueryWrapper(dkjkpt_qhbldkftjk_bsnm, req.getParameterMap());
		Page<DkjkptQhbldkftjkBsnm> page = new Page<DkjkptQhbldkftjkBsnm>(pageNo, pageSize);
		IPage<DkjkptQhbldkftjkBsnm> pageList = dkjkpt_qhbldkftjk_bsnmService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param dkjkpt_qhbldkftjk_bsnm
	 * @return
	 */
	@AutoLog(value = "全行不良贷款反弹监控比上年末-添加")
	@ApiOperation(value="全行不良贷款反弹监控比上年末-添加", notes="全行不良贷款反弹监控比上年末-添加")
	@PostMapping(value = "/add")
	public Result<DkjkptQhbldkftjkBsnm> add(@RequestBody DkjkptQhbldkftjkBsnm dkjkpt_qhbldkftjk_bsnm) {
		Result<DkjkptQhbldkftjkBsnm> result = new Result<DkjkptQhbldkftjkBsnm>();
		try {
			dkjkpt_qhbldkftjk_bsnmService.save(dkjkpt_qhbldkftjk_bsnm);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param dkjkpt_qhbldkftjk_bsnm
	 * @return
	 */
	@AutoLog(value = "全行不良贷款反弹监控比上年末-编辑")
	@ApiOperation(value="全行不良贷款反弹监控比上年末-编辑", notes="全行不良贷款反弹监控比上年末-编辑")
	@PutMapping(value = "/edit")
	public Result<DkjkptQhbldkftjkBsnm> edit(@RequestBody DkjkptQhbldkftjkBsnm dkjkpt_qhbldkftjk_bsnm) {
		Result<DkjkptQhbldkftjkBsnm> result = new Result<DkjkptQhbldkftjkBsnm>();
		DkjkptQhbldkftjkBsnm dkjkpt_qhbldkftjk_bsnmEntity = dkjkpt_qhbldkftjk_bsnmService.getById(dkjkpt_qhbldkftjk_bsnm.getTjyf());
		if(dkjkpt_qhbldkftjk_bsnmEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dkjkpt_qhbldkftjk_bsnmService.updateById(dkjkpt_qhbldkftjk_bsnm);
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
	@AutoLog(value = "全行不良贷款反弹监控比上年末-通过id删除")
	@ApiOperation(value="全行不良贷款反弹监控比上年末-通过id删除", notes="全行不良贷款反弹监控比上年末-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			dkjkpt_qhbldkftjk_bsnmService.removeById(id);
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
	@AutoLog(value = "全行不良贷款反弹监控比上年末-批量删除")
	@ApiOperation(value="全行不良贷款反弹监控比上年末-批量删除", notes="全行不良贷款反弹监控比上年末-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<DkjkptQhbldkftjkBsnm> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<DkjkptQhbldkftjkBsnm> result = new Result<DkjkptQhbldkftjkBsnm>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.dkjkpt_qhbldkftjk_bsnmService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全行不良贷款反弹监控比上年末-通过id查询")
	@ApiOperation(value="全行不良贷款反弹监控比上年末-通过id查询", notes="全行不良贷款反弹监控比上年末-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<DkjkptQhbldkftjkBsnm> queryById(@RequestParam(name="id",required=true) String id) {
		Result<DkjkptQhbldkftjkBsnm> result = new Result<DkjkptQhbldkftjkBsnm>();
		DkjkptQhbldkftjkBsnm dkjkpt_qhbldkftjk_bsnm = dkjkpt_qhbldkftjk_bsnmService.getById(id);
		if(dkjkpt_qhbldkftjk_bsnm==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(dkjkpt_qhbldkftjk_bsnm);
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
      QueryWrapper<DkjkptQhbldkftjkBsnm> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              DkjkptQhbldkftjkBsnm dkjkpt_qhbldkftjk_bsnm = JSON.parseObject(deString, DkjkptQhbldkftjkBsnm.class);
              queryWrapper = QueryGenerator.initQueryWrapper(dkjkpt_qhbldkftjk_bsnm, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<DkjkptQhbldkftjkBsnm> pageList = dkjkpt_qhbldkftjk_bsnmService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "全行不良贷款反弹监控比上年末列表");
      mv.addObject(NormalExcelConstants.CLASS, DkjkptQhbldkftjkBsnm.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("全行不良贷款反弹监控比上年末列表数据", "导出人:"+sysUser.getRealname(), "导出信息"));
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
              List<DkjkptQhbldkftjkBsnm> listDkjkpt_qhbldkftjk_bsnms = ExcelImportUtil.importExcel(file.getInputStream(), DkjkptQhbldkftjkBsnm.class, params);
              dkjkpt_qhbldkftjk_bsnmService.saveBatch(listDkjkpt_qhbldkftjk_bsnms);
              return Result.ok("文件导入成功！数据行数:" + listDkjkpt_qhbldkftjk_bsnms.size());
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
