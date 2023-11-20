package org.cmms.modules.dkjkpt.zjghqk.zjghqkjc.controller;

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
import org.cmms.modules.dkjkpt.zjghqk.zjghqkjc.entity.Dkjkpt_dkglckqsfxb;
import org.cmms.modules.dkjkpt.zjghqk.zjghqkjc.service.IDkjkpt_dkglckqsfxbService;
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
 * @Description: 资金归行情况监测
 * @Author: jeecg-boot
 * @Date:   2019-09-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="资金归行情况监测")
@RestController
@RequestMapping("/dkjkpt.zjghqk.zjghqkjc/dkjkpt_dkglckqsfxb")
public class Dkjkpt_dkglckqsfxbController {
	@Autowired
	private IDkjkpt_dkglckqsfxbService dkjkpt_dkglckqsfxbService;
	
	/**
	  * 分页列表查询
	 * @param dkjkpt_dkglckqsfxb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "资金归行情况监测-分页列表查询")
	@ApiOperation(value="资金归行情况监测-分页列表查询", notes="资金归行情况监测-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Dkjkpt_dkglckqsfxb>> queryPageList(Dkjkpt_dkglckqsfxb dkjkpt_dkglckqsfxb,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Dkjkpt_dkglckqsfxb>> result = new Result<IPage<Dkjkpt_dkglckqsfxb>>();
		QueryWrapper<Dkjkpt_dkglckqsfxb> queryWrapper = QueryGenerator.initQueryWrapper(dkjkpt_dkglckqsfxb, req.getParameterMap());
		Page<Dkjkpt_dkglckqsfxb> page = new Page<Dkjkpt_dkglckqsfxb>(pageNo, pageSize);
		IPage<Dkjkpt_dkglckqsfxb> pageList = dkjkpt_dkglckqsfxbService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param dkjkpt_dkglckqsfxb
	 * @return
	 */
	@AutoLog(value = "资金归行情况监测-添加")
	@ApiOperation(value="资金归行情况监测-添加", notes="资金归行情况监测-添加")
	@PostMapping(value = "/add")
	public Result<Dkjkpt_dkglckqsfxb> add(@RequestBody Dkjkpt_dkglckqsfxb dkjkpt_dkglckqsfxb) {
		Result<Dkjkpt_dkglckqsfxb> result = new Result<Dkjkpt_dkglckqsfxb>();
		try {
			dkjkpt_dkglckqsfxbService.save(dkjkpt_dkglckqsfxb);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param dkjkpt_dkglckqsfxb
	 * @return
	 */
	@AutoLog(value = "资金归行情况监测-编辑")
	@ApiOperation(value="资金归行情况监测-编辑", notes="资金归行情况监测-编辑")
	@PutMapping(value = "/edit")
	public Result<Dkjkpt_dkglckqsfxb> edit(@RequestBody Dkjkpt_dkglckqsfxb dkjkpt_dkglckqsfxb) {
		Result<Dkjkpt_dkglckqsfxb> result = new Result<Dkjkpt_dkglckqsfxb>();
		Dkjkpt_dkglckqsfxb dkjkpt_dkglckqsfxbEntity = dkjkpt_dkglckqsfxbService.getById(dkjkpt_dkglckqsfxb.getZjhm());
		if(dkjkpt_dkglckqsfxbEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dkjkpt_dkglckqsfxbService.updateById(dkjkpt_dkglckqsfxb);
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
	@AutoLog(value = "资金归行情况监测-通过id删除")
	@ApiOperation(value="资金归行情况监测-通过id删除", notes="资金归行情况监测-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			dkjkpt_dkglckqsfxbService.removeById(id);
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
	@AutoLog(value = "资金归行情况监测-批量删除")
	@ApiOperation(value="资金归行情况监测-批量删除", notes="资金归行情况监测-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<Dkjkpt_dkglckqsfxb> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Dkjkpt_dkglckqsfxb> result = new Result<Dkjkpt_dkglckqsfxb>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.dkjkpt_dkglckqsfxbService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "资金归行情况监测-通过id查询")
	@ApiOperation(value="资金归行情况监测-通过id查询", notes="资金归行情况监测-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Dkjkpt_dkglckqsfxb> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Dkjkpt_dkglckqsfxb> result = new Result<Dkjkpt_dkglckqsfxb>();
		Dkjkpt_dkglckqsfxb dkjkpt_dkglckqsfxb = dkjkpt_dkglckqsfxbService.getById(id);
		if(dkjkpt_dkglckqsfxb==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(dkjkpt_dkglckqsfxb);
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
      QueryWrapper<Dkjkpt_dkglckqsfxb> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Dkjkpt_dkglckqsfxb dkjkpt_dkglckqsfxb = JSON.parseObject(deString, Dkjkpt_dkglckqsfxb.class);
              queryWrapper = QueryGenerator.initQueryWrapper(dkjkpt_dkglckqsfxb, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Dkjkpt_dkglckqsfxb> pageList = dkjkpt_dkglckqsfxbService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "资金归行情况监测列表");
      mv.addObject(NormalExcelConstants.CLASS, Dkjkpt_dkglckqsfxb.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("资金归行情况监测列表数据", "导出人:Jeecg", "导出信息"));
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
              List<Dkjkpt_dkglckqsfxb> listDkjkpt_dkglckqsfxbs = ExcelImportUtil.importExcel(file.getInputStream(), Dkjkpt_dkglckqsfxb.class, params);
              dkjkpt_dkglckqsfxbService.saveBatch(listDkjkpt_dkglckqsfxbs);
              return Result.ok("文件导入成功！数据行数:" + listDkjkpt_dkglckqsfxbs.size());
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
