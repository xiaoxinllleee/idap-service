package org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.controller;

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
import org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.entity.DkjkptZhbldkftjkBsnm;
import org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.service.IDkjkptZhbldkftjkBsnmService;
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
 * @Description: 支行不良贷款反弹监控（比上年末）
 * @Author: cmms
 * @Date:   2019-09-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行不良贷款反弹监控比上年末")
@RestController
@RequestMapping("/dkjkpt.bldkftjk.zhbldkftjk/dkjkpt_zhbldkftjk_bsnm")
public class DkjkptZhbldkftjkBsnmController {
	@Autowired
	private IDkjkptZhbldkftjkBsnmService iDkjkptZhbldkftjkBsnmService;
	
	/**
	  * 分页列表查询
	 * @param dkjkpt_zhbldkftjk_bsnm
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行不良贷款反弹监控比上年末-分页列表查询")
	@ApiOperation(value="支行不良贷款反弹监控比上年末-分页列表查询", notes="支行不良贷款反弹监控比上年末-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<DkjkptZhbldkftjkBsnm>> queryPageList(DkjkptZhbldkftjkBsnm dkjkpt_zhbldkftjk_bsnm,
                                                             @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                             @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                             HttpServletRequest req) {
		Result<IPage<DkjkptZhbldkftjkBsnm>> result = new Result<IPage<DkjkptZhbldkftjkBsnm>>();
		QueryWrapper<DkjkptZhbldkftjkBsnm> queryWrapper = QueryGenerator.initQueryWrapper(dkjkpt_zhbldkftjk_bsnm, req.getParameterMap());
		Page<DkjkptZhbldkftjkBsnm> page = new Page<DkjkptZhbldkftjkBsnm>(pageNo, pageSize);
		IPage<DkjkptZhbldkftjkBsnm> pageList = iDkjkptZhbldkftjkBsnmService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param dkjkpt_zhbldkftjk_bsnm
	 * @return
	 */
	@AutoLog(value = "支行不良贷款反弹监控比上年末-添加")
	@ApiOperation(value="支行不良贷款反弹监控比上年末-添加", notes="支行不良贷款反弹监控比上年末-添加")
	@PostMapping(value = "/add")
	public Result<DkjkptZhbldkftjkBsnm> add(@RequestBody DkjkptZhbldkftjkBsnm dkjkpt_zhbldkftjk_bsnm) {
		Result<DkjkptZhbldkftjkBsnm> result = new Result<DkjkptZhbldkftjkBsnm>();
		try {
			iDkjkptZhbldkftjkBsnmService.save(dkjkpt_zhbldkftjk_bsnm);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param dkjkpt_zhbldkftjk_bsnm
	 * @return
	 */
	@AutoLog(value = "支行不良贷款反弹监控比上年末-编辑")
	@ApiOperation(value="支行不良贷款反弹监控比上年末-编辑", notes="支行不良贷款反弹监控比上年末-编辑")
	@PutMapping(value = "/edit")
	public Result<DkjkptZhbldkftjkBsnm> edit(@RequestBody DkjkptZhbldkftjkBsnm dkjkpt_zhbldkftjk_bsnm) {
		Result<DkjkptZhbldkftjkBsnm> result = new Result<DkjkptZhbldkftjkBsnm>();
		DkjkptZhbldkftjkBsnm dkjkpt_zhbldkftjk_bsnmEntity = iDkjkptZhbldkftjkBsnmService.getById(dkjkpt_zhbldkftjk_bsnm.getJgdm());
		if(dkjkpt_zhbldkftjk_bsnmEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = iDkjkptZhbldkftjkBsnmService.updateById(dkjkpt_zhbldkftjk_bsnm);
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
	@AutoLog(value = "支行不良贷款反弹监控比上年末-通过id删除")
	@ApiOperation(value="支行不良贷款反弹监控比上年末-通过id删除", notes="支行不良贷款反弹监控比上年末-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			iDkjkptZhbldkftjkBsnmService.removeById(id);
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
	@AutoLog(value = "支行不良贷款反弹监控比上年末-批量删除")
	@ApiOperation(value="支行不良贷款反弹监控比上年末-批量删除", notes="支行不良贷款反弹监控比上年末-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<DkjkptZhbldkftjkBsnm> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<DkjkptZhbldkftjkBsnm> result = new Result<DkjkptZhbldkftjkBsnm>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.iDkjkptZhbldkftjkBsnmService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行不良贷款反弹监控比上年末-通过id查询")
	@ApiOperation(value="支行不良贷款反弹监控比上年末-通过id查询", notes="支行不良贷款反弹监控比上年末-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<DkjkptZhbldkftjkBsnm> queryById(@RequestParam(name="id",required=true) String id) {
		Result<DkjkptZhbldkftjkBsnm> result = new Result<DkjkptZhbldkftjkBsnm>();
		DkjkptZhbldkftjkBsnm dkjkpt_zhbldkftjk_bsnm = iDkjkptZhbldkftjkBsnmService.getById(id);
		if(dkjkpt_zhbldkftjk_bsnm==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(dkjkpt_zhbldkftjk_bsnm);
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
      QueryWrapper<DkjkptZhbldkftjkBsnm> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              DkjkptZhbldkftjkBsnm dkjkpt_zhbldkftjk_bsnm = JSON.parseObject(deString, DkjkptZhbldkftjkBsnm.class);
              queryWrapper = QueryGenerator.initQueryWrapper(dkjkpt_zhbldkftjk_bsnm, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<DkjkptZhbldkftjkBsnm> pageList = iDkjkptZhbldkftjkBsnmService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "支行不良贷款反弹监控比上年末列表");
      mv.addObject(NormalExcelConstants.CLASS, DkjkptZhbldkftjkBsnm.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("支行不良贷款反弹监控比上年末列表数据", "导出人:Jeecg", "导出信息"));
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
              List<DkjkptZhbldkftjkBsnm> listDkjkpt_zhbldkftjk_bsnms = ExcelImportUtil.importExcel(file.getInputStream(), DkjkptZhbldkftjkBsnm.class, params);
              iDkjkptZhbldkftjkBsnmService.saveBatch(listDkjkpt_zhbldkftjk_bsnms);
              return Result.ok("文件导入成功！数据行数:" + listDkjkpt_zhbldkftjk_bsnms.size());
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
      * 支行不良贷款反弹监控（比上年末）数据提取
      * @param tjyf
      * @return
      */
     @RequestMapping(value = "/initData", method = RequestMethod.PUT)
     public Result<?> init(@RequestParam(name = "TJYF") String tjyf) {
        try {
            iDkjkptZhbldkftjkBsnmService.initData(tjyf);
        } catch (Exception e) {
            log.error("提取失败！",e.getMessage());
            return Result.error(e.getMessage());
        }
         return Result.ok("提取成功！");
     }

}
