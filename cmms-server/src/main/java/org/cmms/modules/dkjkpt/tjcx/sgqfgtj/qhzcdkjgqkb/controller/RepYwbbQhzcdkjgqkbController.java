package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhzcdkjgqkb.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.JxlsConstants;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.FileUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhzcdkjgqkb.entity.RepYwbbQhzcdkjgqkb;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhzcdkjgqkb.entity.RepYwbbQhzcdkjgqkbImport;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhzcdkjgqkb.service.IRepYwbbQhzcdkjgqkbService;
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
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 全行正常贷款结构情况表
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags="全行正常贷款结构情况表")
@RestController
@RequestMapping("/dkjkpt.tjcx.sgqfgtj.qhzcdkjgqkb/repYwbbQhzcdkjgqkb")
public class RepYwbbQhzcdkjgqkbController {
	@Autowired
	private IRepYwbbQhzcdkjgqkbService repYwbbQhzcdkjgqkbService;
	
	/**
	  * 分页列表查询
	 * @param repYwbbQhzcdkjgqkb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "全行正常贷款结构情况表-分页列表查询")
	@ApiOperation(value="全行正常贷款结构情况表-分页列表查询", notes="全行正常贷款结构情况表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<RepYwbbQhzcdkjgqkb>> queryPageList(RepYwbbQhzcdkjgqkb repYwbbQhzcdkjgqkb,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<RepYwbbQhzcdkjgqkb>> result = new Result<IPage<RepYwbbQhzcdkjgqkb>>();
		QueryWrapper<RepYwbbQhzcdkjgqkb> queryWrapper = QueryGenerator.initQueryWrapper(repYwbbQhzcdkjgqkb, req.getParameterMap());
		Page<RepYwbbQhzcdkjgqkb> page = new Page<RepYwbbQhzcdkjgqkb>(pageNo, pageSize);
		IPage<RepYwbbQhzcdkjgqkb> pageList = repYwbbQhzcdkjgqkbService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param repYwbbQhzcdkjgqkb
	 * @return
	 */
	@AutoLog(value = "全行正常贷款结构情况表-添加")
	@ApiOperation(value="全行正常贷款结构情况表-添加", notes="全行正常贷款结构情况表-添加")
	@PostMapping(value = "/add")
	public Result<RepYwbbQhzcdkjgqkb> add(@RequestBody RepYwbbQhzcdkjgqkb repYwbbQhzcdkjgqkb) {
		Result<RepYwbbQhzcdkjgqkb> result = new Result<RepYwbbQhzcdkjgqkb>();
		try {
			repYwbbQhzcdkjgqkbService.save(repYwbbQhzcdkjgqkb);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param repYwbbQhzcdkjgqkb
	 * @return
	 */
	/*@AutoLog(value = "全行正常贷款结构情况表-编辑")
	@ApiOperation(value="全行正常贷款结构情况表-编辑", notes="全行正常贷款结构情况表-编辑")
	@PutMapping(value = "/edit")
	public Result<RepYwbbQhzcdkjgqkb> edit(@RequestBody RepYwbbQhzcdkjgqkb repYwbbQhzcdkjgqkb) {
		Result<RepYwbbQhzcdkjgqkb> result = new Result<RepYwbbQhzcdkjgqkb>();
		RepYwbbQhzcdkjgqkb repYwbbQhzcdkjgqkbEntity = repYwbbQhzcdkjgqkbService.getById(repYwbbQhzcdkjgqkb.getId());
		if(repYwbbQhzcdkjgqkbEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = repYwbbQhzcdkjgqkbService.updateById(repYwbbQhzcdkjgqkb);
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
	@AutoLog(value = "全行正常贷款结构情况表-通过id删除")
	@ApiOperation(value="全行正常贷款结构情况表-通过id删除", notes="全行正常贷款结构情况表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			repYwbbQhzcdkjgqkbService.removeById(id);
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
	@AutoLog(value = "全行正常贷款结构情况表-批量删除")
	@ApiOperation(value="全行正常贷款结构情况表-批量删除", notes="全行正常贷款结构情况表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<RepYwbbQhzcdkjgqkb> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<RepYwbbQhzcdkjgqkb> result = new Result<RepYwbbQhzcdkjgqkb>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.repYwbbQhzcdkjgqkbService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全行正常贷款结构情况表-通过id查询")
	@ApiOperation(value="全行正常贷款结构情况表-通过id查询", notes="全行正常贷款结构情况表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<RepYwbbQhzcdkjgqkb> queryById(@RequestParam(name="id",required=true) String id) {
		Result<RepYwbbQhzcdkjgqkb> result = new Result<RepYwbbQhzcdkjgqkb>();
		RepYwbbQhzcdkjgqkb repYwbbQhzcdkjgqkb = repYwbbQhzcdkjgqkbService.getById(id);
		if(repYwbbQhzcdkjgqkb==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(repYwbbQhzcdkjgqkb);
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
      QueryWrapper<RepYwbbQhzcdkjgqkb> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              RepYwbbQhzcdkjgqkb repYwbbQhzcdkjgqkb = JSON.parseObject(deString, RepYwbbQhzcdkjgqkb.class);
              queryWrapper = QueryGenerator.initQueryWrapper(repYwbbQhzcdkjgqkb, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<RepYwbbQhzcdkjgqkb> pageList = repYwbbQhzcdkjgqkbService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "全行正常贷款结构情况表列表");
      mv.addObject(NormalExcelConstants.CLASS, RepYwbbQhzcdkjgqkb.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("全行正常贷款结构情况表列表数据", "导出人:Jeecg", "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
  }

	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @Autowired
	 private Environment environment;
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(RepYwbbQhzcdkjgqkb repYwbbQhzcdkjgqkb,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		 // Step.1 组装查询条件
		 QueryWrapper<RepYwbbQhzcdkjgqkb> queryWrapper = QueryGenerator.initQueryWrapper(repYwbbQhzcdkjgqkb, request.getParameterMap());

		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new TemplateExcelView());
		 Map<String,Object> map = new HashMap<String, Object>();
		 map.put("tbsj", DateUtil.formatDateTime("yyyy-MM-dd"));
		 List<RepYwbbQhzcdkjgqkb> pageList = repYwbbQhzcdkjgqkbService.list(queryWrapper);
		 List<RepYwbbQhzcdkjgqkbImport> pageList1 = new ArrayList<>();
		 String port = environment.getProperty("common.path.export");
		 for (RepYwbbQhzcdkjgqkb ywbbQhzcdkjgqkb : pageList) {
			 RepYwbbQhzcdkjgqkbImport import1 = new RepYwbbQhzcdkjgqkbImport();
			 BeanUtil.copyProperties(ywbbQhzcdkjgqkb,import1);
			 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
			 String tjyf1 = sdf.format(ywbbQhzcdkjgqkb.getTjyf());
			 import1.setTjyf(tjyf1);
			 pageList1.add(import1);
		 }
		 map.put("list", pageList1);
		 //导出文件名称
		 mv.addObject(JxlsConstants.FILE_NAME, "全行正常贷款结构情况表");
		 mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("全行正常贷款结构情况表_template.xls"));
		 mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"/全行正常贷款结构情况表.xls");
		 mv.addObject(JxlsConstants.MAP_DATA, map);
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
              List<RepYwbbQhzcdkjgqkb> listRepYwbbQhzcdkjgqkbs = ExcelImportUtil.importExcel(file.getInputStream(), RepYwbbQhzcdkjgqkb.class, params);
              repYwbbQhzcdkjgqkbService.saveBatch(listRepYwbbQhzcdkjgqkbs);
              return Result.ok("文件导入成功！数据行数:" + listRepYwbbQhzcdkjgqkbs.size());
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
	  *   通过tjyf提取
	  * @param tjyf
	  * @return
	  */
	 @RequestMapping(value = "/extract" , method = RequestMethod.PUT)
	 public Result<?> extract(@RequestParam(name = "TJYF") String tjyf) {
		 try {
			 repYwbbQhzcdkjgqkbService.extract(tjyf);
		 } catch (Exception e) {
			 log.error("提取失败",e.getMessage());
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功!");
	 }

}
