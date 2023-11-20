package org.cmms.modules.dkjkpt.bldkftjk.qhbldkftjk.controller;

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
import org.cmms.modules.dkjkpt.bldkftjk.qhbldkftjk.entity.DkjkptQhbldkftjkBzr;
import org.cmms.modules.dkjkpt.bldkftjk.qhbldkftjk.service.IDkjkptQhbldkftjkBzrService;
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
 * @Description: 全行不良贷款反弹监控（比昨日）
 * @Author: cmms
 * @Date:   2019-09-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="全行不良贷款反弹监控比昨日")
@RestController
@RequestMapping("/dkjkpt.bldkftjk.qhbldkftjk/dkjkpt_qhbldkftjk_bzr")
public class DkjkptQhbldkftjkBzrController {
	@Autowired
	private IDkjkptQhbldkftjkBzrService iDkjkptQhbldkftjkBzrService;
	
	/**
	  * 分页列表查询
	 * @param dkjkpt_qhbldkftjk_bzr
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "全行不良贷款反弹监控比昨日-分页列表查询")
	@ApiOperation(value="全行不良贷款反弹监控比昨日-分页列表查询", notes="全行不良贷款反弹监控比昨日-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<DkjkptQhbldkftjkBzr>> queryPageList(DkjkptQhbldkftjkBzr dkjkpt_qhbldkftjk_bzr,
                                                            @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                            @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                            HttpServletRequest req) {
		Result<IPage<DkjkptQhbldkftjkBzr>> result = new Result<IPage<DkjkptQhbldkftjkBzr>>();
		QueryWrapper<DkjkptQhbldkftjkBzr> queryWrapper = QueryGenerator.initQueryWrapper(dkjkpt_qhbldkftjk_bzr, req.getParameterMap());
		Page<DkjkptQhbldkftjkBzr> page = new Page<DkjkptQhbldkftjkBzr>(pageNo, pageSize);
		IPage<DkjkptQhbldkftjkBzr> pageList = iDkjkptQhbldkftjkBzrService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param dkjkpt_qhbldkftjk_bzr
	 * @return
	 */
	@AutoLog(value = "全行不良贷款反弹监控比昨日-添加")
	@ApiOperation(value="全行不良贷款反弹监控比昨日-添加", notes="全行不良贷款反弹监控比昨日-添加")
	@PostMapping(value = "/add")
	public Result<DkjkptQhbldkftjkBzr> add(@RequestBody DkjkptQhbldkftjkBzr dkjkpt_qhbldkftjk_bzr) {
		Result<DkjkptQhbldkftjkBzr> result = new Result<DkjkptQhbldkftjkBzr>();
		try {
			iDkjkptQhbldkftjkBzrService.save(dkjkpt_qhbldkftjk_bzr);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param dkjkpt_qhbldkftjk_bzr
	 * @return
	 */
	@AutoLog(value = "全行不良贷款反弹监控比昨日-编辑")
	@ApiOperation(value="全行不良贷款反弹监控比昨日-编辑", notes="全行不良贷款反弹监控比昨日-编辑")
	@PutMapping(value = "/edit")
	public Result<DkjkptQhbldkftjkBzr> edit(@RequestBody DkjkptQhbldkftjkBzr dkjkpt_qhbldkftjk_bzr) {
		Result<DkjkptQhbldkftjkBzr> result = new Result<DkjkptQhbldkftjkBzr>();
		DkjkptQhbldkftjkBzr dkjkpt_qhbldkftjk_bzrEntity = iDkjkptQhbldkftjkBzrService.getById(dkjkpt_qhbldkftjk_bzr.getTjyf());
		if(dkjkpt_qhbldkftjk_bzrEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = iDkjkptQhbldkftjkBzrService.updateById(dkjkpt_qhbldkftjk_bzr);
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
	@AutoLog(value = "全行不良贷款反弹监控比昨日-通过id删除")
	@ApiOperation(value="全行不良贷款反弹监控比昨日-通过id删除", notes="全行不良贷款反弹监控比昨日-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			iDkjkptQhbldkftjkBzrService.removeById(id);
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
	@AutoLog(value = "全行不良贷款反弹监控比昨日-批量删除")
	@ApiOperation(value="全行不良贷款反弹监控比昨日-批量删除", notes="全行不良贷款反弹监控比昨日-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<DkjkptQhbldkftjkBzr> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<DkjkptQhbldkftjkBzr> result = new Result<DkjkptQhbldkftjkBzr>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.iDkjkptQhbldkftjkBzrService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全行不良贷款反弹监控比昨日-通过id查询")
	@ApiOperation(value="全行不良贷款反弹监控比昨日-通过id查询", notes="全行不良贷款反弹监控比昨日-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<DkjkptQhbldkftjkBzr> queryById(@RequestParam(name="id",required=true) String id) {
		Result<DkjkptQhbldkftjkBzr> result = new Result<DkjkptQhbldkftjkBzr>();
		DkjkptQhbldkftjkBzr dkjkpt_qhbldkftjk_bzr = iDkjkptQhbldkftjkBzrService.getById(id);
		if(dkjkpt_qhbldkftjk_bzr==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(dkjkpt_qhbldkftjk_bzr);
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
      QueryWrapper<DkjkptQhbldkftjkBzr> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              DkjkptQhbldkftjkBzr dkjkpt_qhbldkftjk_bzr = JSON.parseObject(deString, DkjkptQhbldkftjkBzr.class);
              queryWrapper = QueryGenerator.initQueryWrapper(dkjkpt_qhbldkftjk_bzr, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<DkjkptQhbldkftjkBzr> pageList = iDkjkptQhbldkftjkBzrService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "全行不良贷款反弹监控比昨日列表");
      mv.addObject(NormalExcelConstants.CLASS, DkjkptQhbldkftjkBzr.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("全行不良贷款反弹监控比昨日列表数据", "导出人:Jeecg", "导出信息"));
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
              List<DkjkptQhbldkftjkBzr> listDkjkpt_qhbldkftjk_bzrs = ExcelImportUtil.importExcel(file.getInputStream(), DkjkptQhbldkftjkBzr.class, params);
              iDkjkptQhbldkftjkBzrService.saveBatch(listDkjkpt_qhbldkftjk_bzrs);
              return Result.ok("文件导入成功！数据行数:" + listDkjkpt_qhbldkftjk_bzrs.size());
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
