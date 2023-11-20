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
import org.cmms.modules.dkjkpt.bldkftjk.qhbldkftjk.entity.DkjkptQhbldkftjkBsy;
import org.cmms.modules.dkjkpt.bldkftjk.qhbldkftjk.service.IDkjkptQhbldkftjkBsyService;
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
 * @Description: 全行不良贷款反弹监控比上月
 * @Author: cmms
 * @Date:   2019-09-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="全行不良贷款反弹监控比上月")
@RestController
@RequestMapping("/dkjkpt.bldkftjk.qhbldkftjk/dkjkpt_qhbldkftjk_bsy")
public class DkjkptQhbldkftjkBsyController {
	@Autowired
	private IDkjkptQhbldkftjkBsyService dkjkpt_qhbldkftjk_bsyService;

	/**
	  * 分页列表查询
	 * @param dkjkpt_qhbldkftjk_bsy
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "全行不良贷款反弹监控比上月-分页列表查询")
	@ApiOperation(value="全行不良贷款反弹监控比上月-分页列表查询", notes="全行不良贷款反弹监控比上月-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<DkjkptQhbldkftjkBsy>> queryPageList(DkjkptQhbldkftjkBsy dkjkpt_qhbldkftjk_bsy,
                                                            @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                            @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                            HttpServletRequest req) {
		Result<IPage<DkjkptQhbldkftjkBsy>> result = new Result<IPage<DkjkptQhbldkftjkBsy>>();
		QueryWrapper<DkjkptQhbldkftjkBsy> queryWrapper = QueryGenerator.initQueryWrapper(dkjkpt_qhbldkftjk_bsy, req.getParameterMap());
		Page<DkjkptQhbldkftjkBsy> page = new Page<DkjkptQhbldkftjkBsy>(pageNo, pageSize);
		IPage<DkjkptQhbldkftjkBsy> pageList = dkjkpt_qhbldkftjk_bsyService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param dkjkpt_qhbldkftjk_bsy
	 * @return
	 */
	@AutoLog(value = "全行不良贷款反弹监控比上月-添加")
	@ApiOperation(value="全行不良贷款反弹监控比上月-添加", notes="全行不良贷款反弹监控比上月-添加")
	@PostMapping(value = "/add")
	public Result<DkjkptQhbldkftjkBsy> add(@RequestBody DkjkptQhbldkftjkBsy dkjkpt_qhbldkftjk_bsy) {
		Result<DkjkptQhbldkftjkBsy> result = new Result<DkjkptQhbldkftjkBsy>();
		try {
			dkjkpt_qhbldkftjk_bsyService.save(dkjkpt_qhbldkftjk_bsy);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param dkjkpt_qhbldkftjk_bsy
	 * @return
	 */
	@AutoLog(value = "全行不良贷款反弹监控比上月-编辑")
	@ApiOperation(value="全行不良贷款反弹监控比上月-编辑", notes="全行不良贷款反弹监控比上月-编辑")
	@PutMapping(value = "/edit")
	public Result<DkjkptQhbldkftjkBsy> edit(@RequestBody DkjkptQhbldkftjkBsy dkjkpt_qhbldkftjk_bsy) {
		Result<DkjkptQhbldkftjkBsy> result = new Result<DkjkptQhbldkftjkBsy>();
		DkjkptQhbldkftjkBsy dkjkpt_qhbldkftjk_bsyEntity = dkjkpt_qhbldkftjk_bsyService.getById(dkjkpt_qhbldkftjk_bsy.getTjyf());
		if(dkjkpt_qhbldkftjk_bsyEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dkjkpt_qhbldkftjk_bsyService.updateById(dkjkpt_qhbldkftjk_bsy);
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
	@AutoLog(value = "全行不良贷款反弹监控比上月-通过id删除")
	@ApiOperation(value="全行不良贷款反弹监控比上月-通过id删除", notes="全行不良贷款反弹监控比上月-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			dkjkpt_qhbldkftjk_bsyService.removeById(id);
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
	@AutoLog(value = "全行不良贷款反弹监控比上月-批量删除")
	@ApiOperation(value="全行不良贷款反弹监控比上月-批量删除", notes="全行不良贷款反弹监控比上月-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<DkjkptQhbldkftjkBsy> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<DkjkptQhbldkftjkBsy> result = new Result<DkjkptQhbldkftjkBsy>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.dkjkpt_qhbldkftjk_bsyService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全行不良贷款反弹监控比上月-通过id查询")
	@ApiOperation(value="全行不良贷款反弹监控比上月-通过id查询", notes="全行不良贷款反弹监控比上月-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<DkjkptQhbldkftjkBsy> queryById(@RequestParam(name="id",required=true) String id) {
		Result<DkjkptQhbldkftjkBsy> result = new Result<DkjkptQhbldkftjkBsy>();
		DkjkptQhbldkftjkBsy dkjkpt_qhbldkftjk_bsy = dkjkpt_qhbldkftjk_bsyService.getById(id);
		if(dkjkpt_qhbldkftjk_bsy==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(dkjkpt_qhbldkftjk_bsy);
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
      QueryWrapper<DkjkptQhbldkftjkBsy> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              DkjkptQhbldkftjkBsy dkjkpt_qhbldkftjk_bsy = JSON.parseObject(deString, DkjkptQhbldkftjkBsy.class);
              queryWrapper = QueryGenerator.initQueryWrapper(dkjkpt_qhbldkftjk_bsy, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<DkjkptQhbldkftjkBsy> pageList = dkjkpt_qhbldkftjk_bsyService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "全行不良贷款反弹监控比上月列表");
      mv.addObject(NormalExcelConstants.CLASS, DkjkptQhbldkftjkBsy.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("全行不良贷款反弹监控比上月列表数据", "导出人:"+sysUser.getRealname(), "导出信息"));
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
              List<DkjkptQhbldkftjkBsy> listDkjkpt_qhbldkftjk_bsys = ExcelImportUtil.importExcel(file.getInputStream(), DkjkptQhbldkftjkBsy.class, params);
              dkjkpt_qhbldkftjk_bsyService.saveBatch(listDkjkpt_qhbldkftjk_bsys);
              return Result.ok("文件导入成功！数据行数:" + listDkjkpt_qhbldkftjk_bsys.size());
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
