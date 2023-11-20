package org.cmms.modules.dkjkpt.dhgztx.jcbgtx.controller;

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
import org.cmms.modules.dkjkpt.dhgztx.jcbgtx.entity.VdkjkptDhgztx;
import org.cmms.modules.dkjkpt.dhgztx.jcbgtx.service.IVdkjkptDhgztxService;
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
 * @Description: 检查报告提醒
 * @Author: cmms
 * @Date:   2019-10-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags="检查报告提醒")
@RestController
@RequestMapping("/dkjkpt/dhgztx/jcbgtx/vdkjkptDhgztx")
public class VdkjkptDhgztxController {
	@Autowired
	private IVdkjkptDhgztxService vdkjkptDhgztxService;

	/**
	  * 分页列表查询
	 * @param vdkjkptDhgztx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "检查报告提醒-分页列表查询")
	@ApiOperation(value="检查报告提醒-分页列表查询", notes="检查报告提醒-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<VdkjkptDhgztx>> queryPageList(VdkjkptDhgztx vdkjkptDhgztx,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<VdkjkptDhgztx>> result = new Result<IPage<VdkjkptDhgztx>>();
		QueryWrapper<VdkjkptDhgztx> queryWrapper = QueryGenerator.initQueryWrapper(vdkjkptDhgztx, req.getParameterMap());
		Page<VdkjkptDhgztx> page = new Page<VdkjkptDhgztx>(pageNo, pageSize);
		IPage<VdkjkptDhgztx> pageList = vdkjkptDhgztxService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param vdkjkptDhgztx
	 * @return
	 */
	@AutoLog(value = "检查报告提醒-添加")
	@ApiOperation(value="检查报告提醒-添加", notes="检查报告提醒-添加")
	@PostMapping(value = "/add")
	public Result<VdkjkptDhgztx> add(@RequestBody VdkjkptDhgztx vdkjkptDhgztx) {
		Result<VdkjkptDhgztx> result = new Result<VdkjkptDhgztx>();
		try {
			vdkjkptDhgztxService.save(vdkjkptDhgztx);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param vdkjkptDhgztx
	 * @return
	 */
	@AutoLog(value = "检查报告提醒-编辑")
	@ApiOperation(value="检查报告提醒-编辑", notes="检查报告提醒-编辑")
	@PutMapping(value = "/edit")
	public Result<VdkjkptDhgztx> edit(@RequestBody VdkjkptDhgztx vdkjkptDhgztx) {
		Result<VdkjkptDhgztx> result = new Result<VdkjkptDhgztx>();
		VdkjkptDhgztx vdkjkptDhgztxEntity = vdkjkptDhgztxService.getById(vdkjkptDhgztx.getZjhm());
		if(vdkjkptDhgztxEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = vdkjkptDhgztxService.updateById(vdkjkptDhgztx);
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
	@AutoLog(value = "检查报告提醒-通过id删除")
	@ApiOperation(value="检查报告提醒-通过id删除", notes="检查报告提醒-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			vdkjkptDhgztxService.removeById(id);
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
	@AutoLog(value = "检查报告提醒-批量删除")
	@ApiOperation(value="检查报告提醒-批量删除", notes="检查报告提醒-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<VdkjkptDhgztx> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<VdkjkptDhgztx> result = new Result<VdkjkptDhgztx>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.vdkjkptDhgztxService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "检查报告提醒-通过id查询")
	@ApiOperation(value="检查报告提醒-通过id查询", notes="检查报告提醒-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<VdkjkptDhgztx> queryById(@RequestParam(name="id",required=true) String id) {
		Result<VdkjkptDhgztx> result = new Result<VdkjkptDhgztx>();
		VdkjkptDhgztx vdkjkptDhgztx = vdkjkptDhgztxService.getById(id);
		if(vdkjkptDhgztx==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(vdkjkptDhgztx);
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
      QueryWrapper<VdkjkptDhgztx> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              VdkjkptDhgztx vdkjkptDhgztx = JSON.parseObject(deString, VdkjkptDhgztx.class);
              queryWrapper = QueryGenerator.initQueryWrapper(vdkjkptDhgztx, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<VdkjkptDhgztx> pageList = vdkjkptDhgztxService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "贷后跟踪提醒");
      mv.addObject(NormalExcelConstants.CLASS, VdkjkptDhgztx.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("贷后跟踪提醒列表数据", "导出人:Jeecg", "导出信息"));
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
              List<VdkjkptDhgztx> listVdkjkptDhgztxs = ExcelImportUtil.importExcel(file.getInputStream(), VdkjkptDhgztx.class, params);
              vdkjkptDhgztxService.saveBatch(listVdkjkptDhgztxs);
              return Result.ok("文件导入成功！数据行数:" + listVdkjkptDhgztxs.size());
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
