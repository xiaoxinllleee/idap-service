package org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.JxlsConstants;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.FileUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.entity.TjfxPjsxwcqkmxb;
import org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.entity.TjfxZhpjsxwcqkmxb;
import org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.entity.TjfxZhpjsxwcqkmxbImport;
import org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.service.ITjfxPjsxwcqkmxbService;
import org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.service.ITjfxZhpjsxwcqkmxbService;
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
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 支行评级授信完成情况明细表
 * @Author: cmms
 * @Date:   2019-12-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行评级授信完成情况明细表")
@RestController
@RequestMapping("/tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb/tjfxZhpjsxwcqkmxb")
public class TjfxZhpjsxwcqkmxbController {
	@Autowired
	private ITjfxZhpjsxwcqkmxbService tjfxZhpjsxwcqkmxbService;

	 @Autowired
	 private ITjfxZhbyService tjfxZhbyService;

	 @Autowired
	 private ITjfxPjsxwcqkmxbService tjfxPjsxwcqkmxbService;
	
	/**
	  * 分页列表查询
	 * @param tjfxZhpjsxwcqkmxb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行评级授信完成情况明细表-分页列表查询")
	@ApiOperation(value="支行评级授信完成情况明细表-分页列表查询", notes="支行评级授信完成情况明细表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<TjfxZhpjsxwcqkmxb>> queryPageList(TjfxZhpjsxwcqkmxb tjfxZhpjsxwcqkmxb,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<TjfxZhpjsxwcqkmxb>> result = new Result<IPage<TjfxZhpjsxwcqkmxb>>();
		QueryWrapper<TjfxZhpjsxwcqkmxb> queryWrapper = QueryGenerator.initQueryWrapper(tjfxZhpjsxwcqkmxb, req.getParameterMap());
		Page<TjfxZhpjsxwcqkmxb> page = new Page<TjfxZhpjsxwcqkmxb>(pageNo, pageSize);
		IPage<TjfxZhpjsxwcqkmxb> pageList = tjfxZhpjsxwcqkmxbService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param tjfxZhpjsxwcqkmxb
	 * @return
	 */
	@AutoLog(value = "支行评级授信完成情况明细表-添加")
	@ApiOperation(value="支行评级授信完成情况明细表-添加", notes="支行评级授信完成情况明细表-添加")
	@PostMapping(value = "/add")
	public Result<TjfxZhpjsxwcqkmxb> add(@RequestBody TjfxZhpjsxwcqkmxb tjfxZhpjsxwcqkmxb) {
		Result<TjfxZhpjsxwcqkmxb> result = new Result<TjfxZhpjsxwcqkmxb>();
		try {
			tjfxZhpjsxwcqkmxbService.save(tjfxZhpjsxwcqkmxb);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param tjfxZhpjsxwcqkmxb
	 * @return
	 */
	@AutoLog(value = "支行评级授信完成情况明细表-编辑")
	@ApiOperation(value="支行评级授信完成情况明细表-编辑", notes="支行评级授信完成情况明细表-编辑")
	@PutMapping(value = "/edit")
	public Result<TjfxZhpjsxwcqkmxb> edit(@RequestBody TjfxZhpjsxwcqkmxb tjfxZhpjsxwcqkmxb) {
		Result<TjfxZhpjsxwcqkmxb> result = new Result<TjfxZhpjsxwcqkmxb>();
		TjfxZhpjsxwcqkmxb tjfxZhpjsxwcqkmxbEntity = tjfxZhpjsxwcqkmxbService.getById(tjfxZhpjsxwcqkmxb.getJgdm());
		if(tjfxZhpjsxwcqkmxbEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = tjfxZhpjsxwcqkmxbService.updateById(tjfxZhpjsxwcqkmxb);
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
	@AutoLog(value = "支行评级授信完成情况明细表-通过id删除")
	@ApiOperation(value="支行评级授信完成情况明细表-通过id删除", notes="支行评级授信完成情况明细表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			tjfxZhpjsxwcqkmxbService.removeById(id);
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
	@AutoLog(value = "支行评级授信完成情况明细表-批量删除")
	@ApiOperation(value="支行评级授信完成情况明细表-批量删除", notes="支行评级授信完成情况明细表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<TjfxZhpjsxwcqkmxb> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<TjfxZhpjsxwcqkmxb> result = new Result<TjfxZhpjsxwcqkmxb>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.tjfxZhpjsxwcqkmxbService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行评级授信完成情况明细表-通过id查询")
	@ApiOperation(value="支行评级授信完成情况明细表-通过id查询", notes="支行评级授信完成情况明细表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<TjfxZhpjsxwcqkmxb> queryById(@RequestParam(name="id",required=true) String id) {
		Result<TjfxZhpjsxwcqkmxb> result = new Result<TjfxZhpjsxwcqkmxb>();
		TjfxZhpjsxwcqkmxb tjfxZhpjsxwcqkmxb = tjfxZhpjsxwcqkmxbService.getById(id);
		if(tjfxZhpjsxwcqkmxb==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(tjfxZhpjsxwcqkmxb);
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
      QueryWrapper<TjfxZhpjsxwcqkmxb> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              TjfxZhpjsxwcqkmxb tjfxZhpjsxwcqkmxb = JSON.parseObject(deString, TjfxZhpjsxwcqkmxb.class);
              queryWrapper = QueryGenerator.initQueryWrapper(tjfxZhpjsxwcqkmxb, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<TjfxZhpjsxwcqkmxb> pageList = tjfxZhpjsxwcqkmxbService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "支行评级授信完成情况明细表列表");
      mv.addObject(NormalExcelConstants.CLASS, TjfxZhpjsxwcqkmxb.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("支行评级授信完成情况明细表列表数据", "导出人:Jeecg", "导出信息"));
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
              List<TjfxZhpjsxwcqkmxb> listTjfxZhpjsxwcqkmxbs = ExcelImportUtil.importExcel(file.getInputStream(), TjfxZhpjsxwcqkmxb.class, params);
              tjfxZhpjsxwcqkmxbService.saveBatch(listTjfxZhpjsxwcqkmxbs);
              return Result.ok("文件导入成功！数据行数:" + listTjfxZhpjsxwcqkmxbs.size());
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
	 public ModelAndView exportTemplateXls(TjfxZhpjsxwcqkmxb tjfxZhpjsxwcqkmxb, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		 // Step.1 组装查询条件
		 QueryWrapper<TjfxZhpjsxwcqkmxb>queryWrapper = QueryGenerator.initQueryWrapper(tjfxZhpjsxwcqkmxb, request.getParameterMap());
		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new TemplateExcelView());
		 Date ksrq = tjfxZhpjsxwcqkmxb.getKsrq();
		 Date jsrq = tjfxZhpjsxwcqkmxb.getJsrq();
		 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
		 String ksqr1 = "";
		 String jsrq2 = "";
		 if (tjfxZhpjsxwcqkmxb.getKsrq() != null && tjfxZhpjsxwcqkmxb.getJsrq()!= null)
		 {
			 ksqr1 = sdf.format(ksrq);
			 jsrq2 = sdf.format(jsrq);
		 }
		 Map<String,Object> map = new HashMap<String, Object>();
		 map.put("ksrq",ksqr1 );
		 map.put("jsrq",jsrq2 );
		 List<TjfxZhpjsxwcqkmxb> pageList = tjfxZhpjsxwcqkmxbService.list(queryWrapper);
		 List<TjfxZhpjsxwcqkmxbImport>pageList1 = new ArrayList<>();
		 for (TjfxZhpjsxwcqkmxb zhpjsxwcqkmxb : pageList) {
			 //zhpjsxwcqkmxb.setXzc(zhpjsxwcqkmxb.getXzc()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",zhpjsxwcqkmxb.getXzc()));
			 zhpjsxwcqkmxb.setJgdm(zhpjsxwcqkmxb.getJgdm()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",zhpjsxwcqkmxb.getJgdm()));
			 TjfxZhpjsxwcqkmxbImport import1 = new TjfxZhpjsxwcqkmxbImport();
			 BeanUtil.copyProperties(zhpjsxwcqkmxb,import1);
			 import1.setKsrq(sdf.format(zhpjsxwcqkmxb.getKsrq()));
			 import1.setJsrq(sdf.format(zhpjsxwcqkmxb.getJsrq()));
			 pageList1.add(import1);
		 }
		 map.put("list", pageList1);
		 String port = environment.getProperty("common.path.export");
		 //导出文件名称
		 mv.addObject(JxlsConstants.FILE_NAME, "支行评级授信完成情况明细表");
		 mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("支行评级授信完成情况明细表.xls"));
		 mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"/支行评级授信完成情况明细表.xls");
		 mv.addObject(JxlsConstants.MAP_DATA, map);
		 return mv;
	 }


	 /**
	  * 提取
	  * @param jsonObject
	  * @return
	  */
	 @PutMapping(value = "/extract")
	 public Result<?> extract1(@RequestBody JSONObject jsonObject) {
		 try {
			 Map<String,String > parm = new HashMap<String,String>();
			 parm.put("ksrq",jsonObject.getString("ksrq"));
			 parm.put("jsrq",jsonObject.getString("jsrq"));
			 tjfxZhpjsxwcqkmxbService.extract(parm);
		 } catch (Exception e) {
			 log.error(e.getMessage(), "提取失败");
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功");
	 }


	 /**
	  * 户数明细
	  * @param param
	  * @return
	  */
	 @ApiOperation(value="户数明细", notes="户数明细")
	 @RequestMapping(value = "/pjsxhsmx",method = RequestMethod.PUT)
	 public Result<?> pjsxhsmx(@RequestBody JSONObject param) {
		 Result<List<TjfxPjsxwcqkmxb>> result = new Result<List<TjfxPjsxwcqkmxb>>();
		 List<TjfxPjsxwcqkmxb> pageList = new ArrayList<>();
		 try {
			 if (param.getString("gnmc").equals("sjpjhs")) {
				 pageList = tjfxPjsxwcqkmxbService.querysjpjhs(param.getDate("ksrq"),param.getDate("jsrq"),param.getString("xzc"),"1");
			 }else {
				 pageList = tjfxPjsxwcqkmxbService.querysjzfhs(param.getDate("ksrq"),param.getDate("jsrq"),param.getString("xzc"),"1",param.getString("zdmc"));
			 }
			 if(pageList.size()!=0) {
				 result.setResult(pageList);
			 }
			 result.success("操作成功！");
		 } catch (Exception e) {
			 log.error(e.getMessage(),e);
			 result.error500("操作失败");
		 }
		 return result;
	 }

	 /**
	  * 导出户数明细
	  */
	 @RequestMapping(value = "/exportpjsxhsmxXls")
	 public ModelAndView exportpjsxhsmxXls(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		 // Step.1 组装查询条件
		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 String ksrq = request.getParameter("ksrq");
		 String jsrq = request.getParameter("jsrq");
		 String xzc = request.getParameter("xzc");
		 String zdmc = request.getParameter("zdmc");
		 String gnmc = request.getParameter("gnmc");
		 List<TjfxPjsxwcqkmxb> pageList =  	new  ArrayList<TjfxPjsxwcqkmxb>();

		 if (gnmc.equals("sjpjhs")) {
			 pageList = tjfxPjsxwcqkmxbService.querysjpjhs(DateUtil.parseDateFormat(ksrq,"yyyy-MM-dd"),DateUtil.parseDateFormat(jsrq,"yyyy-MM-dd"),xzc,"1");
		 }else {
			 pageList = tjfxPjsxwcqkmxbService.querysjzfhs(DateUtil.parseDateFormat(ksrq,"yyyy-MM-dd"),DateUtil.parseDateFormat(jsrq,"yyyy-MM-dd"),xzc,"1",zdmc);
		 }

		 for (TjfxPjsxwcqkmxb tjfxPjsxwcqkmxb : pageList) {
			 tjfxPjsxwcqkmxb.setBzcy(tjfxPjsxwcqkmxb.getBzcy()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxPjsxwcqkmxb.getBzcy()));
			 tjfxPjsxwcqkmxb.setZkhjl(tjfxPjsxwcqkmxb.getZkhjl()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxPjsxwcqkmxb.getZkhjl()));
			 tjfxPjsxwcqkmxb.setSszh(tjfxPjsxwcqkmxb.getSszh()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",tjfxPjsxwcqkmxb.getSszh()));
		 }

		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "支行评级授信完成情况明细表");
		 mv.addObject(NormalExcelConstants.CLASS,  TjfxPjsxwcqkmxb.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("支行评级授信完成情况明细表", "导出人:Jeecg", "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		 return mv;
	 }



}
