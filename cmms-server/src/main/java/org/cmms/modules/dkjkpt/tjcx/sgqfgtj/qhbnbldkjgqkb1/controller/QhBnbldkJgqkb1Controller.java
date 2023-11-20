package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhbnbldkjgqkb1.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.JxlsConstants;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.FileUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhbnbldkjgqkb1.entity.QhBnbldkJgqkb1;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhbnbldkjgqkb1.entity.QhBnbldkJgqkb1Import;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhbnbldkjgqkb1.service.IQhBnbldkJgqkb1Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
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
 * @Description: 全行表内不良贷款结构情况表1
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags="全行表内不良贷款结构情况表1")
@RestController
@RequestMapping("/dkjkpt.tjcx.sgqfgtj.qhbnbldkjgqkb1/qhBnbldkJgqkb1")
public class QhBnbldkJgqkb1Controller {
	@Autowired
	private IQhBnbldkJgqkb1Service qhBnbldkJgqkb1Service;
	
	/**
	  * 分页列表查询
	 * @param qhBnbldkJgqkb1
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "全行表内不良贷款结构情况表1-分页列表查询")
	@ApiOperation(value="全行表内不良贷款结构情况表1-分页列表查询", notes="全行表内不良贷款结构情况表1-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<QhBnbldkJgqkb1>> queryPageList(QhBnbldkJgqkb1 qhBnbldkJgqkb1,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<QhBnbldkJgqkb1>> result = new Result<IPage<QhBnbldkJgqkb1>>();
		QueryWrapper<QhBnbldkJgqkb1> queryWrapper = QueryGenerator.initQueryWrapper(qhBnbldkJgqkb1, req.getParameterMap());
		Page<QhBnbldkJgqkb1> page = new Page<QhBnbldkJgqkb1>(pageNo, pageSize);
		IPage<QhBnbldkJgqkb1> pageList = qhBnbldkJgqkb1Service.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param qhBnbldkJgqkb1
	 * @return
	 */
	@AutoLog(value = "全行表内不良贷款结构情况表1-添加")
	@ApiOperation(value="全行表内不良贷款结构情况表1-添加", notes="全行表内不良贷款结构情况表1-添加")
	@PostMapping(value = "/add")
	public Result<QhBnbldkJgqkb1> add(@RequestBody QhBnbldkJgqkb1 qhBnbldkJgqkb1) {
		Result<QhBnbldkJgqkb1> result = new Result<QhBnbldkJgqkb1>();
		try {
			qhBnbldkJgqkb1Service.save(qhBnbldkJgqkb1);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param qhBnbldkJgqkb1
	 * @return
	 */
	/*@AutoLog(value = "全行表内不良贷款结构情况表1-编辑")
	@ApiOperation(value="全行表内不良贷款结构情况表1-编辑", notes="全行表内不良贷款结构情况表1-编辑")
	@PutMapping(value = "/edit")
	public Result<QhBnbldkJgqkb1> edit(@RequestBody QhBnbldkJgqkb1 qhBnbldkJgqkb1) {
		Result<QhBnbldkJgqkb1> result = new Result<QhBnbldkJgqkb1>();
		QhBnbldkJgqkb1 qhBnbldkJgqkb1Entity = qhBnbldkJgqkb1Service.getById(qhBnbldkJgqkb1.getId());
		if(qhBnbldkJgqkb1Entity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = qhBnbldkJgqkb1Service.updateById(qhBnbldkJgqkb1);
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
	@AutoLog(value = "全行表内不良贷款结构情况表1-通过id删除")
	@ApiOperation(value="全行表内不良贷款结构情况表1-通过id删除", notes="全行表内不良贷款结构情况表1-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			qhBnbldkJgqkb1Service.removeById(id);
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
	@AutoLog(value = "全行表内不良贷款结构情况表1-批量删除")
	@ApiOperation(value="全行表内不良贷款结构情况表1-批量删除", notes="全行表内不良贷款结构情况表1-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<QhBnbldkJgqkb1> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<QhBnbldkJgqkb1> result = new Result<QhBnbldkJgqkb1>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.qhBnbldkJgqkb1Service.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全行表内不良贷款结构情况表1-通过id查询")
	@ApiOperation(value="全行表内不良贷款结构情况表1-通过id查询", notes="全行表内不良贷款结构情况表1-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<QhBnbldkJgqkb1> queryById(@RequestParam(name="id",required=true) String id) {
		Result<QhBnbldkJgqkb1> result = new Result<QhBnbldkJgqkb1>();
		QhBnbldkJgqkb1 qhBnbldkJgqkb1 = qhBnbldkJgqkb1Service.getById(id);
		if(qhBnbldkJgqkb1==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(qhBnbldkJgqkb1);
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
      QueryWrapper<QhBnbldkJgqkb1> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              QhBnbldkJgqkb1 qhBnbldkJgqkb1 = JSON.parseObject(deString, QhBnbldkJgqkb1.class);
              queryWrapper = QueryGenerator.initQueryWrapper(qhBnbldkJgqkb1, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<QhBnbldkJgqkb1> pageList = qhBnbldkJgqkb1Service.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "全行表内不良贷款结构情况表1列表");
      mv.addObject(NormalExcelConstants.CLASS, QhBnbldkJgqkb1.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("全行表内不良贷款结构情况表1列表数据", "导出人:Jeecg", "导出信息"));
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
	 public ModelAndView exportTemplateXlS(QhBnbldkJgqkb1 qhBnbldkJgqkb1,HttpServletRequest request ,HttpServletResponse response) throws UnsupportedEncodingException {
		 // Step.1 组装查询条件
	 	QueryWrapper<QhBnbldkJgqkb1> queryWrapper = QueryGenerator.initQueryWrapper(qhBnbldkJgqkb1,request.getParameterMap());

		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new TemplateExcelView());
		 Map<String,Object> map = new HashMap<>();
		 List<QhBnbldkJgqkb1> pageList = qhBnbldkJgqkb1Service.list(queryWrapper);
		 List<QhBnbldkJgqkb1Import> pageList1 = new ArrayList<>();
		 for (QhBnbldkJgqkb1 bnbldkJgqkb1 : pageList) {
			 QhBnbldkJgqkb1Import import1 =new QhBnbldkJgqkb1Import();
			 BeanUtils.copyProperties(bnbldkJgqkb1,import1);
			 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
			 String tjyf1 = sdf.format(bnbldkJgqkb1.getTjyf());
			 import1.setTjyf(tjyf1);
			 pageList1.add(import1);
		 }
		 map.put("list",pageList1);
		 String port = environment.getProperty("common.path.export");
		 //导出文件名称
		 mv.addObject(JxlsConstants.FILE_NAME,"全行表内不良贷款结构情况表1");
		 mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("全行表内不良贷款结构情况表1_template.xls"));
		 mv.addObject(JxlsConstants.SAVE_FILE_NAME,port+"/全行表内不良贷款结构情况表1.xls");
		 mv.addObject(JxlsConstants.MAP_DATA,map);
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
              List<QhBnbldkJgqkb1> listQhBnbldkJgqkb1s = ExcelImportUtil.importExcel(file.getInputStream(), QhBnbldkJgqkb1.class, params);
              qhBnbldkJgqkb1Service.saveBatch(listQhBnbldkJgqkb1s);
              return Result.ok("文件导入成功！数据行数:" + listQhBnbldkJgqkb1s.size());
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
			 qhBnbldkJgqkb1Service.extract(tjyf);
		 } catch (Exception e) {
			 log.error("提取失败",e.getMessage());
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功!");
	 }

}
