package org.cmms.modules.tjfx.xdgtzytjbb.nshby.controller;

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
import org.cmms.modules.tjfx.xdgtzytjbb.nshby.entity.TjfxNshby;
import org.cmms.modules.tjfx.xdgtzytjbb.nshby.entity.TjfxNshbyImport;
import org.cmms.modules.tjfx.xdgtzytjbb.nshby.service.ITjfxNshbyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.entity.TjfxZhbyImport;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.entity.TjfxZhbymxb;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbymxbService;
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
 * @Description: 农商行统计表一
 * @Author: cmms
 * @Date:   2019-12-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="农商行统计表一")
@RestController
@RequestMapping("/tjfx.xdgtzytjbb.nshby/tjfxNshby")
public class TjfxNshbyController {
	@Autowired
	private ITjfxNshbyService tjfxNshbyService;
	 @Autowired
	 private ITjfxZhbyService tjfxZhbyService;

	 @Autowired
	 private ITjfxZhbymxbService tjfxZhbymxbService;
	
	/**
	  * 分页列表查询
	 * @param tjfxNshby
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "农商行统计表一-分页列表查询")
	@ApiOperation(value="农商行统计表一-分页列表查询", notes="农商行统计表一-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<TjfxNshby>> queryPageList(TjfxNshby tjfxNshby,
												   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
												   HttpServletRequest req) {
		Result<IPage<TjfxNshby>> result = new Result<IPage<TjfxNshby>>();
		QueryWrapper<TjfxNshby> queryWrapper = QueryGenerator.initQueryWrapper(tjfxNshby, req.getParameterMap());
		Page<TjfxNshby> page = new Page<TjfxNshby>(pageNo, pageSize);
		IPage<TjfxNshby> pageList = tjfxNshbyService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param tjfxNshby
	 * @return
	 */
	@AutoLog(value = "农商行统计表一-添加")
	@ApiOperation(value="农商行统计表一-添加", notes="农商行统计表一-添加")
	@PostMapping(value = "/add")
	public Result<TjfxNshby> add(@RequestBody TjfxNshby tjfxNshby) {
		Result<TjfxNshby> result = new Result<TjfxNshby>();
		try {
			tjfxNshbyService.save(tjfxNshby);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param tjfxNshby
	 * @return
	 */
	@AutoLog(value = "农商行统计表一-编辑")
	@ApiOperation(value="农商行统计表一-编辑", notes="农商行统计表一-编辑")
	@PutMapping(value = "/edit")
	public Result<TjfxNshby> edit(@RequestBody TjfxNshby tjfxNshby) {
		Result<TjfxNshby> result = new Result<TjfxNshby>();
		TjfxNshby tjfxNshbyEntity = tjfxNshbyService.getById(tjfxNshby.getKsrq());
		if(tjfxNshbyEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = tjfxNshbyService.updateById(tjfxNshby);
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
	@AutoLog(value = "农商行统计表一-通过id删除")
	@ApiOperation(value="农商行统计表一-通过id删除", notes="农商行统计表一-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			tjfxNshbyService.removeById(id);
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
	@AutoLog(value = "农商行统计表一-批量删除")
	@ApiOperation(value="农商行统计表一-批量删除", notes="农商行统计表一-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<TjfxNshby> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<TjfxNshby> result = new Result<TjfxNshby>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.tjfxNshbyService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "农商行统计表一-通过id查询")
	@ApiOperation(value="农商行统计表一-通过id查询", notes="农商行统计表一-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<TjfxNshby> queryById(@RequestParam(name="id",required=true) String id) {
		Result<TjfxNshby> result = new Result<TjfxNshby>();
		TjfxNshby tjfxNshby = tjfxNshbyService.getById(id);
		if(tjfxNshby==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(tjfxNshby);
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
      QueryWrapper<TjfxNshby> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              TjfxNshby tjfxNshby = JSON.parseObject(deString, TjfxNshby.class);
              queryWrapper = QueryGenerator.initQueryWrapper(tjfxNshby, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<TjfxNshby> pageList = tjfxNshbyService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "农商行统计表一列表");
      mv.addObject(NormalExcelConstants.CLASS, TjfxNshby.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("农商行统计表一列表数据", "导出人:Jeecg", "导出信息"));
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
              List<TjfxNshby> listTjfxNshbys = ExcelImportUtil.importExcel(file.getInputStream(), TjfxNshby.class, params);
              tjfxNshbyService.saveBatch(listTjfxNshbys);
              return Result.ok("文件导入成功！数据行数:" + listTjfxNshbys.size());
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
	 public ModelAndView exportTemplateXls(TjfxNshby tjfxNshby, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		 // Step.1 组装查询条件
		 QueryWrapper<TjfxNshby>queryWrapper = QueryGenerator.initQueryWrapper(tjfxNshby, request.getParameterMap());
		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new TemplateExcelView());
		 Date ksrq = tjfxNshby.getKsrq();
		 Date jsrq = tjfxNshby.getJsrq();
		 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
		 String ksqr1 = "";
		 String jsrq2 = "";
		 if (tjfxNshby.getKsrq() != null && tjfxNshby.getJsrq()!= null)
		 {
			 ksqr1 = sdf.format(ksrq);
			 jsrq2 = sdf.format(jsrq);
		 }
		 Map<String,Object> map = new HashMap<String, Object>();
		 map.put("ksrq",ksqr1 );
		 map.put("jsrq",jsrq2 );
		 List<TjfxNshby> pageList = tjfxNshbyService.list(queryWrapper);
		 List<TjfxNshbyImport>pageList1 = new ArrayList<>();
		 for (TjfxNshby nshby : pageList) {
			 nshby.setGpld(nshby.getGpld()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",nshby.getGpld()));
			 nshby.setJgdm(nshby.getJgdm()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",nshby.getJgdm()));
			 TjfxNshbyImport import1 = new TjfxNshbyImport();
			 BeanUtil.copyProperties(nshby,import1);
			 String ksrq1 = sdf.format(nshby.getKsrq());
			 String jsrq1 = sdf.format(nshby.getJsrq());
			 import1.setKsrq(ksrq1);
			 import1.setJsrq(jsrq1);
			 pageList1.add(import1);
		 }
		 map.put("list", pageList1);
		 String port = environment.getProperty("common.path.export");
		 //导出文件名称
		 mv.addObject(JxlsConstants.FILE_NAME, "农商行表1");
		 mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("农商行表1.xls"));
		 mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"/农商行表1.xls");
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
			 tjfxNshbyService.extract(parm);
		 } catch (Exception e) {
			 log.error(e.getMessage(), "提取失败");
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功");
	 }

	 /**
	  * 查询辖内户数明细信息
	  * @param param
	  * @return
	  */
	 @ApiOperation(value="查询辖内户数明细信息", notes="查询辖内户数明细信息")
	 @RequestMapping(value = "/xnhsmx",method = RequestMethod.PUT)
	 public Result<?> a(@RequestBody JSONObject param) {
		 Result<List<TjfxZhbymxb>> result = new Result<List<TjfxZhbymxb>>();
		 List<TjfxZhbymxb> pjsxKhpjsxbList = new ArrayList<>();
		 try {
			 pjsxKhpjsxbList = tjfxZhbymxbService.queryTableDictTextByKey(param.getDate("ksrq"),param.getDate("jsrq"),param.getString("sszh"),param.getString("zkhjl"),param.getString("khlx"),"gpld","sszh","2");
			 if(pjsxKhpjsxbList.size()!=0) {
				 result.setResult(pjsxKhpjsxbList);
			 }
			 result.success("操作成功！");
		 } catch (Exception e) {
			 log.error(e.getMessage(),e);
			 result.error500("操作失败");
		 }
		 return result;
	 }

	 /**
	  * 导出辖内户数明细信息
	  */

	 @RequestMapping(value = "/exportTemplatemxXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		 // Step.1 组装查询条件
		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 String ksrq = request.getParameter("ksrq");
		 String jsrq = request.getParameter("jsrq");
		 String xzc = request.getParameter("sszh");
		 String zkhjl = request.getParameter("zkhjl");
		 String khlx = request.getParameter("khlx");
		 List<TjfxZhbymxb> pageList =  	new  ArrayList<TjfxZhbymxb>();
		 List<TjfxZhbyImport> pageList1 = new ArrayList<>();
		 pageList = tjfxZhbymxbService.queryTableDictTextByKey(DateUtil.parseDateFormat(ksrq,"yyyy-MM-dd"),DateUtil.parseDateFormat(jsrq,"yyyy-MM-dd"),xzc,zkhjl,khlx,"gpld","sszh","2");

		 for (TjfxZhbymxb tjfxZhbymxb : pageList) {
			 tjfxZhbymxb.setGpld(tjfxZhbymxb.getGpld()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxZhbymxb.getGpld()));
			 tjfxZhbymxb.setZkhjl(tjfxZhbymxb.getZkhjl()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxZhbymxb.getZkhjl()));
			 tjfxZhbymxb.setSszh(tjfxZhbymxb.getSszh()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",tjfxZhbymxb.getSszh()));
			 TjfxZhbyImport import1 = new TjfxZhbyImport();
			 BeanUtil.copyProperties(tjfxZhbymxb,import1);
			 pageList1.add(import1);
		 }
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "全行行动挂图作业表一明细列表");
		 mv.addObject(NormalExcelConstants.CLASS,  TjfxZhbyImport.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("全行行动挂图作业表一明细列表", "导出人:Jeecg", "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList1);
		 return mv;
	 }

	 /**
	  * 查询走访进度明细信息
	  * @param param
	  * @return
	  */
	 @ApiOperation(value="查询走访进度明细信息", notes="查询走访进度明细信息")
	 @RequestMapping(value = "/zfjdmx",method = RequestMethod.PUT)
	 public Result<?> zfjdmx(@RequestBody JSONObject param) {
		 Result<List<TjfxZhbymxb>> result = new Result<List<TjfxZhbymxb>>();
		 List<TjfxZhbymxb> zhbymxbList = new ArrayList<>();
		 try {
			 if (param.getString("sjlx").equals("bz")) {
				 zhbymxbList = tjfxZhbymxbService.querykhzfmx(param.getDate("ksrq"), param.getDate("jsrq"), param.getString("xzc"), param.getString("zkhjl"), param.getString("khlx"),"gpld","sszh","2");
			 }else{
				 zhbymxbList = tjfxZhbymxbService.queryljkhzfmx(param.getDate("ksrq"), param.getDate("jsrq"), param.getString("xzc"), param.getString("zkhjl"), param.getString("khlx"),"gpld","sszh","2");
			 }
			 if(zhbymxbList.size()!=0) {
				 result.setResult(zhbymxbList);
			 }
			 result.success("操作成功！");
		 } catch (Exception e) {
			 log.error(e.getMessage(),e);
			 result.error500("操作失败");
		 }
		 return result;
	 }
	 /**
	  * 导出走访进度明细信息
	  */

	 @RequestMapping(value = "/exportTemplatezfjdmxXls")
	 public ModelAndView exportTemplatezfjdmxXls(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		 // Step.1 组装查询条件
		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 String ksrq = request.getParameter("ksrq");
		 String jsrq = request.getParameter("jsrq");
		 String xzc = request.getParameter("xzc");
		 String zkhjl = request.getParameter("zkhjl");
		 String khlx = request.getParameter("khlx");
		 String sjlx = request.getParameter("sjlx");
		 List<TjfxZhbymxb> pageList =  	new  ArrayList<TjfxZhbymxb>();
		 List<TjfxZhbyImport> pageList1 = new ArrayList<>();
		 if (sjlx.equals("bz")) {
			 pageList = tjfxZhbymxbService.querykhzfmx(DateUtil.parseDateFormat(ksrq,"yyyy-MM-dd"),DateUtil.parseDateFormat(jsrq,"yyyy-MM-dd"), xzc, zkhjl, khlx,"gpld","sszh","2");
		 }else{
			 pageList = tjfxZhbymxbService.queryljkhzfmx(DateUtil.parseDateFormat(ksrq,"yyyy-MM-dd"),DateUtil.parseDateFormat(jsrq,"yyyy-MM-dd"), xzc, zkhjl, khlx,"gpld","sszh","2");
		 }
		 for (TjfxZhbymxb tjfxZhbymxb : pageList) {
			 tjfxZhbymxb.setGpld(tjfxZhbymxb.getGpld()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxZhbymxb.getGpld()));
			 tjfxZhbymxb.setZkhjl(tjfxZhbymxb.getZkhjl()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxZhbymxb.getZkhjl()));
			 tjfxZhbymxb.setSszh(tjfxZhbymxb.getSszh()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",tjfxZhbymxb.getSszh()));
			 TjfxZhbyImport import1 = new TjfxZhbyImport();
			 BeanUtil.copyProperties(tjfxZhbymxb,import1);
			 pageList1.add(import1);
		 }
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "全行行动挂图作业表一明细列表");
		 mv.addObject(NormalExcelConstants.CLASS,  TjfxZhbyImport.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("全行行动挂图作业表一明细列表", "导出人:Jeecg", "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList1);
		 return mv;
	 }

	 /**
	  * 查询评级授信进度信息
	  * @param param
	  * @return
	  */
	 @ApiOperation(value="查询评级授信进度信息", notes="查询评级授信进度信息")
	 @RequestMapping(value = "/pjsxjd",method = RequestMethod.PUT)
	 public Result<?> pjsxjd(@RequestBody JSONObject param) {
		 Result<List<TjfxZhbymxb>> result = new Result<List<TjfxZhbymxb>>();
		 List<TjfxZhbymxb> zhbymxbList = new ArrayList<>();
		 try {
			 if (param.getString("sjlx").equals("bz")) {
				 zhbymxbList = tjfxZhbymxbService.querypjsxjdmx(param.getDate("ksrq"), param.getDate("jsrq"), param.getString("xzc"), param.getString("zkhjl"),"gpld","sszh","2");
			 }else{
				 zhbymxbList = tjfxZhbymxbService.queryljpjsxjdmx(param.getDate("ksrq"), param.getDate("jsrq"), param.getString("xzc"), param.getString("zkhjl"),"gpld","sszh","2");
			 }
			 if(zhbymxbList.size()!=0) {
				 result.setResult(zhbymxbList);
			 }
			 result.success("操作成功！");
		 } catch (Exception e) {
			 log.error(e.getMessage(),e);
			 result.error500("操作失败");
		 }
		 return result;
	 }

	 /**
	  * 导出评级授信进度信息
	  */

	 @RequestMapping(value = "/exportTemplatepjsxmxXls")
	 public ModelAndView exportTemplatepjsxmxXls(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		 // Step.1 组装查询条件
		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 String ksrq = request.getParameter("ksrq");
		 String jsrq = request.getParameter("jsrq");
		 String xzc = request.getParameter("xzc");
		 String zkhjl = request.getParameter("zkhjl");
		 String khlx = request.getParameter("khlx");
		 String sjlx = request.getParameter("sjlx");
		 List<TjfxZhbymxb> pageList =  	new  ArrayList<TjfxZhbymxb>();
		 List<TjfxZhbyImport>pageList1 = new ArrayList<>();
		 if (sjlx.equals("bz")) {
			 pageList = tjfxZhbymxbService.querypjsxjdmx(DateUtil.parseDateFormat(ksrq,"yyyy-MM-dd"),DateUtil.parseDateFormat(jsrq,"yyyy-MM-dd"),xzc, zkhjl,"gpld","sszh","2");
		 }else{
			 pageList = tjfxZhbymxbService.queryljpjsxjdmx(DateUtil.parseDateFormat(ksrq,"yyyy-MM-dd"),DateUtil.parseDateFormat(jsrq,"yyyy-MM-dd"), xzc, zkhjl,"gpld","sszh","2");
		 }
		 for (TjfxZhbymxb tjfxZhbymxb : pageList) {
			 tjfxZhbymxb.setGpld(tjfxZhbymxb.getGpld()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxZhbymxb.getGpld()));
			 tjfxZhbymxb.setZkhjl(tjfxZhbymxb.getZkhjl()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxZhbymxb.getZkhjl()));
			 tjfxZhbymxb.setSszh(tjfxZhbymxb.getSszh()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",tjfxZhbymxb.getSszh()));
			 TjfxZhbyImport imports = new TjfxZhbyImport();
			 BeanUtil.copyProperties(tjfxZhbymxb,imports);
			 pageList1.add(imports);
		 }

		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "全行行动挂图作业表一明细列表");
		 mv.addObject(NormalExcelConstants.CLASS,  TjfxZhbyImport.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("全行行动挂图作业表一明细列表", "导出人:Jeecg", "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList1);
		 return mv;
	 }

	 /**
	  * 查询评级授信进度信息
	  * @param param
	  * @return
	  */
	 @ApiOperation(value="查询评级授信进度用信金额信息", notes="查询评级授信进度用信金额信息")
	 @RequestMapping(value = "/pjsxjdyxje",method = RequestMethod.PUT)
	 public Result<?> pjsxjdyxje(@RequestBody JSONObject param) {
		 Result<List<TjfxZhbymxb>> result = new Result<List<TjfxZhbymxb>>();
		 List<TjfxZhbymxb> zhbymxbList = new ArrayList<>();
		 try {
			 if (param.getString("sjlx").equals("bz")) {
				 zhbymxbList = tjfxZhbymxbService.querypjsxjdyxjemx(param.getDate("ksrq"), param.getDate("jsrq"), param.getString("xzc"), param.getString("zkhjl"),"gpld","sszh","2");
			 }else{
				 zhbymxbList = tjfxZhbymxbService.queryljpjsxjdyxjemx(param.getDate("ksrq"), param.getDate("jsrq"), param.getString("xzc"), param.getString("zkhjl"),"gpld","sszh","2");
			 }
			 if(zhbymxbList.size()!=0) {
				 result.setResult(zhbymxbList);
			 }
			 result.success("操作成功！");
		 } catch (Exception e) {
			 log.error(e.getMessage(),e);
			 result.error500("操作失败");
		 }
		 return result;
	 }

	 /**
	  * 导出评级授信进度信息
	  */
	 @RequestMapping(value = "/exportTemplatepjsxyxjemxXls")
	 public ModelAndView exportTemplatepjsxyxjemxXls(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		 // Step.1 组装查询条件
		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 String ksrq = request.getParameter("ksrq");
		 String jsrq = request.getParameter("jsrq");
		 String xzc = request.getParameter("xzc");
		 String zkhjl = request.getParameter("zkhjl");
		 String khlx = request.getParameter("khlx");
		 String sjlx = request.getParameter("sjlx");
		 List<TjfxZhbymxb> pageList =  	new  ArrayList<TjfxZhbymxb>();
		 List<TjfxZhbyImport> pageList1 = new ArrayList<>();

		 if (sjlx.equals("bz")) {
			 pageList = tjfxZhbymxbService.querypjsxjdyxjemx(DateUtil.parseDateFormat(ksrq,"yyyy-MM-dd"),DateUtil.parseDateFormat(jsrq,"yyyy-MM-dd"), xzc, zkhjl,"gpld","sszh","2");
		 }else{
			 pageList = tjfxZhbymxbService.queryljpjsxjdyxjemx(DateUtil.parseDateFormat(ksrq,"yyyy-MM-dd"),DateUtil.parseDateFormat(jsrq,"yyyy-MM-dd"), xzc, zkhjl,"gpld","sszh","2");
		 }
		 for (TjfxZhbymxb tjfxZhbymxb : pageList) {
			 tjfxZhbymxb.setGpld(tjfxZhbymxb.getGpld()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxZhbymxb.getGpld()));
			 tjfxZhbymxb.setZkhjl(tjfxZhbymxb.getZkhjl()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxZhbymxb.getZkhjl()));
			 tjfxZhbymxb.setSszh(tjfxZhbymxb.getSszh()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",tjfxZhbymxb.getSszh()));
			 TjfxZhbyImport import1 = new TjfxZhbyImport();
			 BeanUtil.copyProperties(tjfxZhbymxb,import1);
			 pageList1.add(import1);
		 }

		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "全行行动挂图作业表一明细列表");
		 mv.addObject(NormalExcelConstants.CLASS,  TjfxZhbyImport.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("全行行动挂图作业表一明细列表", "导出人:Jeecg", "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList1);
		 return mv;
	 }


}
