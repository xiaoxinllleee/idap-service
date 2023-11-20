package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhzcdkjgqkb.controller;


import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.ibatis.annotations.Update;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.JxlsConstants;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.FileUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhzcdkjgqkb.entity.RepYwbbZhzcdkjgqkb;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhzcdkjgqkb.entity.RepYwbbZhzcdkjgqkbImport;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhzcdkjgqkb.service.IRepYwbbZhzcdkjgqkbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 支行正常贷款结构情况表
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行正常贷款结构情况表")
@RestController
@RequestMapping("/dkjkpt.tjcx.sgqfgtj.zhzcdkjgqkb/repYwbbZhzcdkjgqkb")
public class RepYwbbZhzcdkjgqkbController {
	@Autowired
	private IRepYwbbZhzcdkjgqkbService repYwbbZhzcdkjgqkbService;

	 @Autowired
	 private ITjfxZhbyService tjfxZhbyService;
	
	/**
	  * 分页列表查询
	 * @param repYwbbZhzcdkjgqkb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行正常贷款结构情况表-分页列表查询")
	@ApiOperation(value="支行正常贷款结构情况表-分页列表查询", notes="支行正常贷款结构情况表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<RepYwbbZhzcdkjgqkb>> queryPageList(RepYwbbZhzcdkjgqkb repYwbbZhzcdkjgqkb,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<RepYwbbZhzcdkjgqkb>> result = new Result<IPage<RepYwbbZhzcdkjgqkb>>();
		QueryWrapper<RepYwbbZhzcdkjgqkb> queryWrapper = QueryGenerator.initQueryWrapper(repYwbbZhzcdkjgqkb, req.getParameterMap());
		Page<RepYwbbZhzcdkjgqkb> page = new Page<RepYwbbZhzcdkjgqkb>(pageNo, pageSize);
		IPage<RepYwbbZhzcdkjgqkb> pageList = repYwbbZhzcdkjgqkbService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param repYwbbZhzcdkjgqkb
	 * @return
	 */
	@AutoLog(value = "支行正常贷款结构情况表-添加")
	@ApiOperation(value="支行正常贷款结构情况表-添加", notes="支行正常贷款结构情况表-添加")
	@PostMapping(value = "/add")
	public Result<RepYwbbZhzcdkjgqkb> add(@RequestBody RepYwbbZhzcdkjgqkb repYwbbZhzcdkjgqkb) {
		Result<RepYwbbZhzcdkjgqkb> result = new Result<RepYwbbZhzcdkjgqkb>();
		try {
			repYwbbZhzcdkjgqkbService.save(repYwbbZhzcdkjgqkb);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param repYwbbZhzcdkjgqkb
	 * @return
	 */
	/*@AutoLog(value = "支行正常贷款结构情况表-编辑")
	@ApiOperation(value="支行正常贷款结构情况表-编辑", notes="支行正常贷款结构情况表-编辑")
	@PutMapping(value = "/edit")
	public Result<RepYwbbZhzcdkjgqkb> edit(@RequestBody RepYwbbZhzcdkjgqkb repYwbbZhzcdkjgqkb) {
		Result<RepYwbbZhzcdkjgqkb> result = new Result<RepYwbbZhzcdkjgqkb>();
		RepYwbbZhzcdkjgqkb repYwbbZhzcdkjgqkbEntity = repYwbbZhzcdkjgqkbService.getById(repYwbbZhzcdkjgqkb.getId());
		if(repYwbbZhzcdkjgqkbEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = repYwbbZhzcdkjgqkbService.updateById(repYwbbZhzcdkjgqkb);
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
	@AutoLog(value = "支行正常贷款结构情况表-通过id删除")
	@ApiOperation(value="支行正常贷款结构情况表-通过id删除", notes="支行正常贷款结构情况表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			repYwbbZhzcdkjgqkbService.removeById(id);
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
	@AutoLog(value = "支行正常贷款结构情况表-批量删除")
	@ApiOperation(value="支行正常贷款结构情况表-批量删除", notes="支行正常贷款结构情况表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<RepYwbbZhzcdkjgqkb> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<RepYwbbZhzcdkjgqkb> result = new Result<RepYwbbZhzcdkjgqkb>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.repYwbbZhzcdkjgqkbService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行正常贷款结构情况表-通过id查询")
	@ApiOperation(value="支行正常贷款结构情况表-通过id查询", notes="支行正常贷款结构情况表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<RepYwbbZhzcdkjgqkb> queryById(@RequestParam(name="id",required=true) String id) {
		Result<RepYwbbZhzcdkjgqkb> result = new Result<RepYwbbZhzcdkjgqkb>();
		RepYwbbZhzcdkjgqkb repYwbbZhzcdkjgqkb = repYwbbZhzcdkjgqkbService.getById(id);
		if(repYwbbZhzcdkjgqkb==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(repYwbbZhzcdkjgqkb);
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
      QueryWrapper<RepYwbbZhzcdkjgqkb> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
		  if (oConvertUtils.isNotEmpty(paramsStr)) {
			  String deString = URLDecoder.decode(paramsStr, "UTF-8");
			  RepYwbbZhzcdkjgqkb repYwbbZhzcdkjgqkb = JSON.parseObject(deString, RepYwbbZhzcdkjgqkb.class);
			  queryWrapper = QueryGenerator.initQueryWrapper(repYwbbZhzcdkjgqkb, request.getParameterMap());
		  }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<RepYwbbZhzcdkjgqkb> pageList = repYwbbZhzcdkjgqkbService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "支行正常贷款结构情况表列表");
      mv.addObject(NormalExcelConstants.CLASS, RepYwbbZhzcdkjgqkb.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("支行正常贷款结构情况表列表数据", "导出人:Jeecg", "导出信息"));
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
	 public ModelAndView exportTemplateXls(RepYwbbZhzcdkjgqkb repYwbbZhzcdkjgqkb,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		 // Step.1 组装查询条件
		 QueryWrapper<RepYwbbZhzcdkjgqkb> queryWrapper = QueryGenerator.initQueryWrapper(repYwbbZhzcdkjgqkb, request.getParameterMap());
		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new TemplateExcelView());
		 Map<String,Object> map = new HashMap<String, Object>();
		 map.put("tbsj", DateUtil.formatDateTime("yyyy-MM-dd"));
		 List<RepYwbbZhzcdkjgqkb> pageList = repYwbbZhzcdkjgqkbService.list(queryWrapper);
		 List<RepYwbbZhzcdkjgqkbImport> pageList1 = new ArrayList<>();
		 for (RepYwbbZhzcdkjgqkb ywbbZhzcdkjgqkb : pageList) {
			 ywbbZhzcdkjgqkb.setJgdm(ywbbZhzcdkjgqkb.getJgdm()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","ywjgdm",ywbbZhzcdkjgqkb.getJgdm()));
			 RepYwbbZhzcdkjgqkbImport  import1 = new RepYwbbZhzcdkjgqkbImport();
			 BeanUtil.copyProperties(ywbbZhzcdkjgqkb,import1);
			 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
			 String tjyf1 = sdf.format(ywbbZhzcdkjgqkb.getTjyf());
			 import1.setTjyf(tjyf1);
			 pageList1.add(import1);
		 }
		 map.put("list", pageList1);
		 String port = environment.getProperty("common.path.export");
		 String path= URLDecoder.decode(this.getClass().getClassLoader().getResource("excel/支行正常贷款结构情况表_template.xls").getPath(),"UTF-8");

		 //导出文件名称
		 mv.addObject(JxlsConstants.FILE_NAME, "支行正常贷款结构情况表");
		 mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("支行正常贷款结构情况表_template.xls"));
		 mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"/支行正常贷款结构情况表.xls");
		 mv.addObject(JxlsConstants.MAP_DATA, map);
		 return mv;
	 }





	 /**
	  * 导出模板excel
	  *

	  */
	 /*@RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(@RequestBody JSONObject jsonObject) {
		 // Step.1 组装查询条件
		 //QueryWrapper<RepYwbbZhzcdkjgqkb> queryWrapper = null;
		 *//*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 try{
			 Date date = sdf.parse(jsonObject.getString("tjyf") );
		 } catch (ParseException e) {
			 e.printStackTrace();
		 }*//*
		 UpdateWrapper<RepYwbbZhzcdkjgqkb> userUpdateWrapper = new UpdateWrapper<>();
		 userUpdateWrapper.eq("tjyf", jsonObject.getString("tjyf"));
		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new TemplateExcelView());
		 Map<String,Object> map = new HashMap<String, Object>();
		 map.put("tbsj", DateUtil.formatDateTime("yyyy-MM-dd"));
		 List<RepYwbbZhzcdkjgqkb> pageList = repYwbbZhzcdkjgqkbService.list(userUpdateWrapper);
		 map.put("list", pageList);
		 //导出文件名称
		 mv.addObject(JxlsConstants.FILE_NAME, "支行正常贷款结构情况表");
		 mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, "d:/三个全覆盖报表模板/支行正常贷款结构情况表_template.xls");
		 mv.addObject(JxlsConstants.SAVE_FILE_NAME, "d:/三个全覆盖报表模板/支行正常贷款结构情况表.xls");
		 mv.addObject(JxlsConstants.MAP_DATA, map);
		 return mv;
	 }*/


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
              List<RepYwbbZhzcdkjgqkb> listRepYwbbZhzcdkjgqkbs = ExcelImportUtil.importExcel(file.getInputStream(), RepYwbbZhzcdkjgqkb.class, params);
              repYwbbZhzcdkjgqkbService.saveBatch(listRepYwbbZhzcdkjgqkbs);
              return Result.ok("文件导入成功！数据行数:" + listRepYwbbZhzcdkjgqkbs.size());
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
			 repYwbbZhzcdkjgqkbService.extract(tjyf);
		 } catch (Exception e) {
			 log.error("提取失败",e.getMessage());
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功!");
	 }

}
