package org.cmms.modules.tjfx.xdgtzytjbb.nshbe.controller;

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
import org.cmms.modules.tjfx.xdgtzytjbb.nshbe.entity.TjfxNshbe;
import org.cmms.modules.tjfx.xdgtzytjbb.nshbe.entity.TjfxNshbeImport;
import org.cmms.modules.tjfx.xdgtzytjbb.nshbe.service.ITjfxNshbeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.entity.TjfxZhbemxb;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.entity.TjfxZhbemxbImport;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.service.ITjfxZhbemxbService;
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
 * @Description: 农商行统计报表二
 * @Author: cmms
 * @Date:   2019-12-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="农商行统计报表二")
@RestController
@RequestMapping("/tjfx.xdgtzytjbb.nshbe/tjfxNshbe")
public class TjfxNshbeController {
	@Autowired
	private ITjfxNshbeService tjfxNshbeService;
	 @Autowired
	 private ITjfxZhbyService tjfxZhbyService;

	 @Autowired
	 private ITjfxZhbemxbService tjfxZhbemxbService;


	 /**
	  * 分页列表查询
	 * @param tjfxNshbe
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "农商行统计报表二-分页列表查询")
	@ApiOperation(value="农商行统计报表二-分页列表查询", notes="农商行统计报表二-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<TjfxNshbe>> queryPageList(TjfxNshbe tjfxNshbe,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<TjfxNshbe>> result = new Result<IPage<TjfxNshbe>>();
		QueryWrapper<TjfxNshbe> queryWrapper = QueryGenerator.initQueryWrapper(tjfxNshbe, req.getParameterMap());
		Page<TjfxNshbe> page = new Page<TjfxNshbe>(pageNo, pageSize);
		IPage<TjfxNshbe> pageList = tjfxNshbeService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param tjfxNshbe
	 * @return
	 */
	@AutoLog(value = "农商行统计报表二-添加")
	@ApiOperation(value="农商行统计报表二-添加", notes="农商行统计报表二-添加")
	@PostMapping(value = "/add")
	public Result<TjfxNshbe> add(@RequestBody TjfxNshbe tjfxNshbe) {
		Result<TjfxNshbe> result = new Result<TjfxNshbe>();
		try {
			tjfxNshbeService.save(tjfxNshbe);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param tjfxNshbe
	 * @return
	 */
	@AutoLog(value = "农商行统计报表二-编辑")
	@ApiOperation(value="农商行统计报表二-编辑", notes="农商行统计报表二-编辑")
	@PutMapping(value = "/edit")
	public Result<TjfxNshbe> edit(@RequestBody TjfxNshbe tjfxNshbe) {
		Result<TjfxNshbe> result = new Result<TjfxNshbe>();
		TjfxNshbe tjfxNshbeEntity = tjfxNshbeService.getById(tjfxNshbe.getTjyf());
		if(tjfxNshbeEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = tjfxNshbeService.updateById(tjfxNshbe);
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
	@AutoLog(value = "农商行统计报表二-通过id删除")
	@ApiOperation(value="农商行统计报表二-通过id删除", notes="农商行统计报表二-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			tjfxNshbeService.removeById(id);
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
	@AutoLog(value = "农商行统计报表二-批量删除")
	@ApiOperation(value="农商行统计报表二-批量删除", notes="农商行统计报表二-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<TjfxNshbe> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<TjfxNshbe> result = new Result<TjfxNshbe>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.tjfxNshbeService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "农商行统计报表二-通过id查询")
	@ApiOperation(value="农商行统计报表二-通过id查询", notes="农商行统计报表二-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<TjfxNshbe> queryById(@RequestParam(name="id",required=true) String id) {
		Result<TjfxNshbe> result = new Result<TjfxNshbe>();
		TjfxNshbe tjfxNshbe = tjfxNshbeService.getById(id);
		if(tjfxNshbe==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(tjfxNshbe);
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
      QueryWrapper<TjfxNshbe> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              TjfxNshbe tjfxNshbe = JSON.parseObject(deString, TjfxNshbe.class);
              queryWrapper = QueryGenerator.initQueryWrapper(tjfxNshbe, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<TjfxNshbe> pageList = tjfxNshbeService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "农商行统计报表二列表");
      mv.addObject(NormalExcelConstants.CLASS, TjfxNshbe.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("农商行统计报表二列表数据", "导出人:Jeecg", "导出信息"));
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
              List<TjfxNshbe> listTjfxNshbes = ExcelImportUtil.importExcel(file.getInputStream(), TjfxNshbe.class, params);
              tjfxNshbeService.saveBatch(listTjfxNshbes);
              return Result.ok("文件导入成功！数据行数:" + listTjfxNshbes.size());
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
	 public ModelAndView exportTemplateXls(TjfxNshbe tjfxNshbe,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		 // Step.1 组装查询条件
		 QueryWrapper<TjfxNshbe>queryWrapper = QueryGenerator.initQueryWrapper(tjfxNshbe, request.getParameterMap());
		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new TemplateExcelView());
		 Date tjyf = tjfxNshbe.getTjyf();
		 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
		 String tjyf1 = "";
		 if (tjfxNshbe.getTjyf() != null )
		 {
			 tjyf1 = sdf.format(tjyf);
		 }
		 Map<String,Object> map = new HashMap<String, Object>();
		 map.put("tjyf", tjyf1);
		 List<TjfxNshbe> pageList = tjfxNshbeService.list(queryWrapper);
		 List<TjfxNshbeImport> pageList1 = new ArrayList<>();
		 for (TjfxNshbe nshbe : pageList) {
			 nshbe.setGpldgh(nshbe.getGpldgh()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",nshbe.getGpldgh()));
			 nshbe.setJgdm(nshbe.getJgdm()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",nshbe.getJgdm()));
			 TjfxNshbeImport import1 = new TjfxNshbeImport();
			 BeanUtil.copyProperties(nshbe,import1);
			 import1.setTjyf(sdf.format(nshbe.getTjyf()));
			 pageList1.add(import1);
		 }
		 map.put("list", pageList1);
		 String port = environment.getProperty("common.path.export");
		 //导出文件名称
		 mv.addObject(JxlsConstants.FILE_NAME, "农商行表2 ");
		 mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("农商行表2.xls"));
		 mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"/农商行表2 .xls");
		 mv.addObject(JxlsConstants.MAP_DATA, map);
		 return mv;
	 }

	 /**
	  *   通过tjyf提取
	  * @param tjyf
	  * @return
	  */
	 @RequestMapping(value = "/extract" , method = RequestMethod.PUT)
	 public Result<?> extract(@RequestParam(name = "TJYF") String tjyf) {
		 try {
			 tjfxNshbeService.extract(tjyf);
		 } catch (Exception e) {
			 log.error(e.getMessage(), "提取失败");
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功");
	 }


	 /**
	  * 存款余额明细
	  * @param param
	  * @return
	  */
	 @ApiOperation(value="存款余额明细", notes="存款余额明细")
	 @RequestMapping(value = "/ckyemx",method = RequestMethod.PUT)
	 public Result<?> ckyemx(@RequestBody JSONObject param) {
		 Result<List<TjfxZhbemxb>> result = new Result<List<TjfxZhbemxb>>();
		 List<TjfxZhbemxb> tjfxZhbemxbs = new ArrayList<>();
		 try {
			 if (param.getString("sjlx").equals("ye")) {
				 tjfxZhbemxbs = tjfxZhbemxbService.queryckyemx(param.getDate("tjyf"),"sszh",param.getString("xzc"), "gpld",param.getString("zkhjl"),"2");
			 }else{
				 //tjfxZhbemxbs = tjfxZhbemxbService.csqueryckyemx((param.getDate("tjyf"),"xzc",param.getString("xzc"), "zkhjl",param.getString("zkhjl"));
			 }
			 if(tjfxZhbemxbs.size()!=0) {
				 result.setResult(tjfxZhbemxbs);
			 }
			 result.success("操作成功！");
		 } catch (Exception e) {
			 log.error(e.getMessage(),e);
			 result.error500("操作失败");
		 }
		 return result;
	 }

	 /**
	  * 导出存款余额明细
	  */
	 @RequestMapping(value = "/exportckyemxXls")
	 public ModelAndView exportckyemxXls(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		 // Step.1 组装查询条件
		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 String tjyf = request.getParameter("tjyf");
		 String xzc = request.getParameter("xzc");
		 String zkhjl = request.getParameter("zkhjl");
		 String sjlx = request.getParameter("sjlx");
		 List<TjfxZhbemxb> pageList =  	new  ArrayList<TjfxZhbemxb>();
		 List<TjfxZhbemxbImport> pageList1 = new ArrayList<>();

		 if (sjlx.equals("ye")) {
			 pageList = tjfxZhbemxbService.queryckyemx(DateUtil.parseDateFormat(tjyf,"yyyy-MM-dd"),"sszh",xzc, "gpld",zkhjl,"2");
		 }else{
			 pageList = tjfxZhbemxbService.queryckyemx(DateUtil.parseDateFormat(tjyf,"yyyy-MM-dd"),"sszh",xzc, "gpld",zkhjl,"2");
		 }

		 for (TjfxZhbemxb tjfxZhbemxb : pageList) {
			 tjfxZhbemxb.setGpld(tjfxZhbemxb.getGpld()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxZhbemxb.getGpld()));
			 tjfxZhbemxb.setZkhjl(tjfxZhbemxb.getZkhjl()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxZhbemxb.getZkhjl()));
			 tjfxZhbemxb.setSszh(tjfxZhbemxb.getSszh()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",tjfxZhbemxb.getSszh()));
			 TjfxZhbemxbImport import1 = new TjfxZhbemxbImport();
			 BeanUtil.copyProperties(tjfxZhbemxb,import1);
			 pageList1.add(import1);
		  }

		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "全行动挂图作业表二明细列表");
		 mv.addObject(NormalExcelConstants.CLASS,  TjfxZhbemxbImport.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("全行行动挂图作业表一明细列表", "导出人:Jeecg", "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList1);
		 return mv;
	 }


	 /**
	  * 户数明细
	  * @param param
	  * @return
	  */
	 @ApiOperation(value="户数明细", notes="户数明细")
	 @RequestMapping(value = "/hsmx",method = RequestMethod.PUT)
	 public Result<?> hsmx(@RequestBody JSONObject param) {
		 Result<List<TjfxZhbemxb>> result = new Result<List<TjfxZhbemxb>>();
		 List<TjfxZhbemxb> pageList = new ArrayList<>();
		 try {
			 if (param.getString("sjlx").equals("cl")) {
				 pageList = tjfxZhbemxbService.queryclhsmx(param.getDate("tjyf"),"sszh",param.getString("xzc"), "gpld",param.getString("zkhjl"),param.getString("zdmc1"),"2");
			 }else if (param.getString("sjlx").equals("zl")){
				 pageList = tjfxZhbemxbService.queryzlhsmx(param.getDate("tjyf"),"sszh",param.getString("xzc"), "gpld",param.getString("zkhjl"),param.getString("zdmc1"),"2");
			 }else{
				 pageList = tjfxZhbemxbService.queryckyemx(param.getDate("tjyf"),"sszh",param.getString("xzc"), "gpld",param.getString("zkhjl"),"2");
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
	 @RequestMapping(value = "/exporthsmxXls")
	 public ModelAndView exporthsmxXls(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		 // Step.1 组装查询条件
		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 String tjyf = request.getParameter("tjyf");
		 String xzc = request.getParameter("xzc");
		 String zkhjl = request.getParameter("zkhjl");
		 String zdmc1 = request.getParameter("zdmc1");
		 String sjlx = request.getParameter("sjlx");
		 List<TjfxZhbemxb> pageList =  	new  ArrayList<TjfxZhbemxb>();
		 List<TjfxZhbemxbImport> pageList1 = new ArrayList<>();

		 if (sjlx.equals("cl")) {
			 pageList = tjfxZhbemxbService.queryclhsmx(DateUtil.parseDateFormat(tjyf,"yyyy-MM-dd"),"sszh",xzc, "gpld",zkhjl,zdmc1,"2");
		 }else if (sjlx.equals("zl")){
			 pageList = tjfxZhbemxbService.queryzlhsmx(DateUtil.parseDateFormat(tjyf,"yyyy-MM-dd"),"sszh",xzc, "gpld",zkhjl,zdmc1,"2");
		 }else{
			 pageList = tjfxZhbemxbService.queryckyemx(DateUtil.parseDateFormat(tjyf,"yyyy-MM-dd"),"sszh",xzc, "gpld",zkhjl,"2");
		 }

		 for (TjfxZhbemxb tjfxZhbemxb : pageList) {
			 tjfxZhbemxb.setGpld(tjfxZhbemxb.getGpld()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxZhbemxb.getGpld()));
			 tjfxZhbemxb.setZkhjl(tjfxZhbemxb.getZkhjl()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxZhbemxb.getZkhjl()));
			 tjfxZhbemxb.setSszh(tjfxZhbemxb.getSszh()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",tjfxZhbemxb.getSszh()));
			 TjfxZhbemxbImport import1 = new TjfxZhbemxbImport();
			 BeanUtil.copyProperties(tjfxZhbemxb,import1);
			 pageList1.add(import1);
		 }

		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "全行行动挂图作业表二明细列表");
		 mv.addObject(NormalExcelConstants.CLASS,  TjfxZhbemxbImport.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("全行行动挂图作业表一明细列表", "导出人:Jeecg", "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList1);
		 return mv;
	 }



}
