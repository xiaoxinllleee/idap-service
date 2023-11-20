package org.cmms.modules.tjfx.xdgtzytjbb.cscqzhby.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.JxlsConstants;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.FileUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.xdgtzytjbb.cscqzhby.entity.TjfxCscqzhby;
import org.cmms.modules.tjfx.xdgtzytjbb.cscqzhby.service.ITjfxCscqzhbyService;
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
 * @Description: 村社村前统计支行表一
 * @Author: cmms
 * @Date:   2019-12-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags="村社村前统计支行表一")
@RestController
@RequestMapping("/tjfx.xdgtzytjbb.cscqzhby/tjfxCscqzhby")
public class TjfxCscqzhbyController {
	@Autowired
	private ITjfxCscqzhbyService tjfxCscqzhbyService;
	
	/**
	  * 分页列表查询
	 * @param tjfxCscqzhby
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "村社村前统计支行表一-分页列表查询")
	@ApiOperation(value="村社村前统计支行表一-分页列表查询", notes="村社村前统计支行表一-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<TjfxCscqzhby>> queryPageList(TjfxCscqzhby tjfxCscqzhby,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<TjfxCscqzhby>> result = new Result<IPage<TjfxCscqzhby>>();
		QueryWrapper<TjfxCscqzhby> queryWrapper = QueryGenerator.initQueryWrapper(tjfxCscqzhby, req.getParameterMap());
		Page<TjfxCscqzhby> page = new Page<TjfxCscqzhby>(pageNo, pageSize);
		IPage<TjfxCscqzhby> pageList = tjfxCscqzhbyService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param tjfxCscqzhby
	 * @return
	 */
	@AutoLog(value = "村社村前统计支行表一-添加")
	@ApiOperation(value="村社村前统计支行表一-添加", notes="村社村前统计支行表一-添加")
	@PostMapping(value = "/add")
	public Result<TjfxCscqzhby> add(@RequestBody TjfxCscqzhby tjfxCscqzhby) {
		Result<TjfxCscqzhby> result = new Result<TjfxCscqzhby>();
		try {
			tjfxCscqzhbyService.save(tjfxCscqzhby);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param tjfxCscqzhby
	 * @return
	 */
	@AutoLog(value = "村社村前统计支行表一-编辑")
	@ApiOperation(value="村社村前统计支行表一-编辑", notes="村社村前统计支行表一-编辑")
	@PutMapping(value = "/edit")
	public Result<TjfxCscqzhby> edit(@RequestBody TjfxCscqzhby tjfxCscqzhby) {
		Result<TjfxCscqzhby> result = new Result<TjfxCscqzhby>();
		TjfxCscqzhby tjfxCscqzhbyEntity = tjfxCscqzhbyService.getById(tjfxCscqzhby.getZrre());
		if(tjfxCscqzhbyEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = tjfxCscqzhbyService.updateById(tjfxCscqzhby);
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
	@AutoLog(value = "村社村前统计支行表一-通过id删除")
	@ApiOperation(value="村社村前统计支行表一-通过id删除", notes="村社村前统计支行表一-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			tjfxCscqzhbyService.removeById(id);
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
	@AutoLog(value = "村社村前统计支行表一-批量删除")
	@ApiOperation(value="村社村前统计支行表一-批量删除", notes="村社村前统计支行表一-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<TjfxCscqzhby> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<TjfxCscqzhby> result = new Result<TjfxCscqzhby>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.tjfxCscqzhbyService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "村社村前统计支行表一-通过id查询")
	@ApiOperation(value="村社村前统计支行表一-通过id查询", notes="村社村前统计支行表一-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<TjfxCscqzhby> queryById(@RequestParam(name="id",required=true) String id) {
		Result<TjfxCscqzhby> result = new Result<TjfxCscqzhby>();
		TjfxCscqzhby tjfxCscqzhby = tjfxCscqzhbyService.getById(id);
		if(tjfxCscqzhby==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(tjfxCscqzhby);
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
      QueryWrapper<TjfxCscqzhby> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              TjfxCscqzhby tjfxCscqzhby = JSON.parseObject(deString, TjfxCscqzhby.class);
              queryWrapper = QueryGenerator.initQueryWrapper(tjfxCscqzhby, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<TjfxCscqzhby> pageList = tjfxCscqzhbyService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "村社村前统计支行表一列表");
      mv.addObject(NormalExcelConstants.CLASS, TjfxCscqzhby.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("村社村前统计支行表一列表数据", "导出人:Jeecg", "导出信息"));
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
              List<TjfxCscqzhby> listTjfxCscqzhbys = ExcelImportUtil.importExcel(file.getInputStream(), TjfxCscqzhby.class, params);
              tjfxCscqzhbyService.saveBatch(listTjfxCscqzhbys);
              return Result.ok("文件导入成功！数据行数:" + listTjfxCscqzhbys.size());
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
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @Autowired
	 private Environment environment;
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(TjfxCscqzhby tjfxCscqzhby,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		 // Step.1 组装查询条件
		 QueryWrapper<TjfxCscqzhby>queryWrapper = QueryGenerator.initQueryWrapper(tjfxCscqzhby, request.getParameterMap());
		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new TemplateExcelView());
		 Date ksrq = tjfxCscqzhby.getKsrq();
		 Date jsrq = tjfxCscqzhby.getJsrq();
		 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
		 String ksqr1 = "";
		 String jsrq2 = "";
		 if (tjfxCscqzhby.getKsrq() != null && tjfxCscqzhby.getJsrq()!= null)
		 {
			 ksqr1 = sdf.format(ksrq);
			 jsrq2 = sdf.format(jsrq);
		 }
		 Map<String,Object> map = new HashMap<String, Object>();
		 map.put("ksrq", ksqr1);
		 map.put("jsrq", jsrq2);
		 List<TjfxCscqzhby> pageList = tjfxCscqzhbyService.list(queryWrapper);
		 /*for (TjfxCscqzhby zhby : pageList) {
			 zhby.setZrre(zhby.getZrre()==null ? "" : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",zhby.getZrre()));
			 zhby.setZxx(zhby.getZxx()== null ? "" :tjfxZhbyService.queryTableDictTextByKey("yxdygl_czxxgl","organize","qybm",zhby.getZxx()));
		 }*/
		 map.put("list", pageList);
		 String port = environment.getProperty("common.path.export");
		 //导出文件名称
		 mv.addObject(JxlsConstants.FILE_NAME, "村社村前支行表1");
		 mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("村社村前支行表1.xls"));
		 mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"/村社村前支行表1.xls");
		 mv.addObject(JxlsConstants.MAP_DATA, map);
		 return mv;
	 }

	 /**
	  * 提取
	  * @param jsonObject
	  * @return
	  */
	 @RequestMapping(value = "/extract" , method = RequestMethod.PUT)
	 public Result<?> extract(@RequestBody JSONObject jsonObject) {
		 try {
		 	Map<String,String> parm = new HashMap<>();
			 parm.put("ksrq",jsonObject.getString("ksrq"));
			 parm.put("jsrq",jsonObject.getString("jsrq"));
			 tjfxCscqzhbyService.extract(parm);
		 } catch (Exception e) {
			 log.error(e.getMessage(), "提取失败");
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功");
	 }


 }
