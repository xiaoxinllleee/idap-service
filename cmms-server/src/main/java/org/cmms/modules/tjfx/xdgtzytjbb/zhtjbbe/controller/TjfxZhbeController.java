package org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
//import org.apache.poi.hmef.attribute.MAPIAttribute;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.JxlsConstants;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.FileUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.entity.TjfxZhbe;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.entity.TjfxZhbeImport;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.entity.TjfxZhbemxb;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.entity.TjfxZhbemxbImport;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.service.ITjfxZhbeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

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
 * @Description: 支行统计报表二
 * @Author: cmms
 * @Date:   2019-12-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行统计报表二")
@RestController
@RequestMapping("/tjfx.xdgtzytjbb.zhtjbbe/tjfxZhbe")
public class TjfxZhbeController {
	@Autowired
	private ITjfxZhbeService tjfxZhbeService;
	 @Autowired
	 private ITjfxZhbyService tjfxZhbyService;
	 @Autowired
	 private ITjfxZhbemxbService tjfxZhbemxbService;

	/**
	  * 分页列表查询
	 * @param tjfxZhbe
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行统计报表二-分页列表查询")
	@ApiOperation(value="支行统计报表二-分页列表查询", notes="支行统计报表二-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<TjfxZhbe>> queryPageList(TjfxZhbe tjfxZhbe,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<TjfxZhbe>> result = new Result<IPage<TjfxZhbe>>();
		QueryWrapper<TjfxZhbe> queryWrapper = QueryGenerator.initQueryWrapper(tjfxZhbe, req.getParameterMap());
		Page<TjfxZhbe> page = new Page<TjfxZhbe>(pageNo, pageSize);
		IPage<TjfxZhbe> pageList = tjfxZhbeService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param tjfxZhbe
	 * @return
	 */
	@AutoLog(value = "支行统计报表二-添加")
	@ApiOperation(value="支行统计报表二-添加", notes="支行统计报表二-添加")
	@PostMapping(value = "/add")
	public Result<TjfxZhbe> add(@RequestBody TjfxZhbe tjfxZhbe) {
		Result<TjfxZhbe> result = new Result<TjfxZhbe>();
		try {
			tjfxZhbeService.save(tjfxZhbe);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param tjfxZhbe
	 * @return
	 */
	@AutoLog(value = "支行统计报表二-编辑")
	@ApiOperation(value="支行统计报表二-编辑", notes="支行统计报表二-编辑")
	@PutMapping(value = "/edit")
	public Result<TjfxZhbe> edit(@RequestBody TjfxZhbe tjfxZhbe) {
		Result<TjfxZhbe> result = new Result<TjfxZhbe>();
		TjfxZhbe tjfxZhbeEntity = tjfxZhbeService.getById(tjfxZhbe.getTjyf());
		if(tjfxZhbeEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = tjfxZhbeService.updateById(tjfxZhbe);
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
	@AutoLog(value = "支行统计报表二-通过id删除")
	@ApiOperation(value="支行统计报表二-通过id删除", notes="支行统计报表二-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			tjfxZhbeService.removeById(id);
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
	@AutoLog(value = "支行统计报表二-批量删除")
	@ApiOperation(value="支行统计报表二-批量删除", notes="支行统计报表二-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<TjfxZhbe> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<TjfxZhbe> result = new Result<TjfxZhbe>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.tjfxZhbeService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行统计报表二-通过id查询")
	@ApiOperation(value="支行统计报表二-通过id查询", notes="支行统计报表二-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<TjfxZhbe> queryById(@RequestParam(name="id",required=true) String id) {
		Result<TjfxZhbe> result = new Result<TjfxZhbe>();
		TjfxZhbe tjfxZhbe = tjfxZhbeService.getById(id);
		if(tjfxZhbe==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(tjfxZhbe);
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
      QueryWrapper<TjfxZhbe> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              TjfxZhbe tjfxZhbe = JSON.parseObject(deString, TjfxZhbe.class);
              queryWrapper = QueryGenerator.initQueryWrapper(tjfxZhbe, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<TjfxZhbe> pageList = tjfxZhbeService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "支行统计报表二列表");
      mv.addObject(NormalExcelConstants.CLASS, TjfxZhbe.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("支行统计报表二列表数据", "导出人:Jeecg", "导出信息"));
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
              List<TjfxZhbe> listTjfxZhbes = ExcelImportUtil.importExcel(file.getInputStream(), TjfxZhbe.class, params);
              tjfxZhbeService.saveBatch(listTjfxZhbes);
              return Result.ok("文件导入成功！数据行数:" + listTjfxZhbes.size());
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
	 public ModelAndView exportTemplateXls(TjfxZhbe tjfxZhbe,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		 // Step.1 组装查询条件
		 QueryWrapper<TjfxZhbe>queryWrapper = QueryGenerator.initQueryWrapper(tjfxZhbe, request.getParameterMap());
		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new TemplateExcelView());

		 Date tjyf = tjfxZhbe.getTjyf();
		 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
		 String tjyf1 = "";
		 if (tjfxZhbe.getTjyf() != null )
		 {
			 tjyf1 = sdf.format(tjyf);
		 }
		 String jgdm = (tjfxZhbe.getJgdm()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",tjfxZhbe.getJgdm()));
		 Map<String,Object> map = new HashMap<String, Object>();
		 map.put("tjyf", tjyf1);
		 map.put("jgmc",jgdm);
		 List<TjfxZhbe> pageList = tjfxZhbeService.list(queryWrapper);
		 List<TjfxZhbeImport> pageList1 = new ArrayList<>();
		 for (TjfxZhbe zhbe : pageList) {
			 zhbe.setZrre(zhbe.getZrre()== null ?"" : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",zhbe.getZrre()));
			 zhbe.setZxx(zhbe.getZxx()==null ? "" :tjfxZhbyService.queryTableDictTextByKey("yxdygl_czxxgl","organize","qybm",zhbe.getZxx()));
			 zhbe.setJgdm(zhbe.getJgdm()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",zhbe.getJgdm()));
			 TjfxZhbeImport import1 = new TjfxZhbeImport();
			 BeanUtil.copyProperties(zhbe,import1);
			 String tjyf2 = sdf.format(zhbe.getTjyf());
			 import1.setTjyf(tjyf2);
			 pageList1.add(import1);
		 }
		 map.put("list", pageList1);
		 String port = environment.getProperty("common.path.export");
		 //导出文件名称
		 mv.addObject(JxlsConstants.FILE_NAME, "支行表2");
		 mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("支行表2.xls"));
		 mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"/支行表2.xls");
		 mv.addObject(JxlsConstants.MAP_DATA, map);
		 return mv;
	 }


	 /**
	  *   通过tjyf提取
	  * @param jsonObject
	  * @return
	  */
	 @PutMapping(value = "/extract")
	 public Result<?> extract1(@RequestBody JSONObject jsonObject) {
		 try {
		 	Map<String,String> map = new HashMap<>();
			 map.put("tjyf",jsonObject.getString("tjyf"));
			 map.put("jgdm",jsonObject.getString("jgdm"));
			 tjfxZhbeService.extract(map);
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
				 tjfxZhbemxbs = tjfxZhbemxbService.queryckyemx(param.getDate("tjyf"),"xzc",param.getString("xzc"), "zkhjl",param.getString("zkhjl"),"1");
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
			 pageList = tjfxZhbemxbService.queryckyemx(DateUtil.parseDateFormat(tjyf,"yyyy-MM-dd"),"xzc",xzc, "zkhjl",zkhjl,"1");
		 }else{
			 pageList = tjfxZhbemxbService.queryckyemx(DateUtil.parseDateFormat(tjyf,"yyyy-MM-dd"),"xzc",xzc, "zkhjl",zkhjl,"1");
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
		 mv.addObject(NormalExcelConstants.FILE_NAME, "支行行动挂图作业表二明细列表");
		 mv.addObject(NormalExcelConstants.CLASS,  TjfxZhbemxbImport.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("支行行动挂图作业表一明细列表", "导出人:Jeecg", "导出信息"));
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
				 pageList = tjfxZhbemxbService.queryclhsmx(param.getDate("tjyf"),"xzc",param.getString("xzc"), "zkhjl",param.getString("zkhjl"),param.getString("zdmc1"),"1");
			 }else if (param.getString("sjlx").equals("zl")){
				 pageList = tjfxZhbemxbService.queryzlhsmx(param.getDate("tjyf"),"xzc",param.getString("xzc"), "zkhjl",param.getString("zkhjl"),param.getString("zdmc1"),"1");
			 }else{
				 pageList = tjfxZhbemxbService.queryckyemx(param.getDate("tjyf"),"xzc",param.getString("xzc"), "zkhjl",param.getString("zkhjl"),"1");
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
		 String zdmc2 = request.getParameter("zdmc2");
		 String sjlx = request.getParameter("sjlx");
		 List<TjfxZhbemxb> pageList =  	new  ArrayList<TjfxZhbemxb>();
		 List<TjfxZhbemxbImport> pageList1 = new ArrayList<>();

		 if (sjlx.equals("cl")) {
			 pageList = tjfxZhbemxbService.queryclhsmx(DateUtil.parseDateFormat(tjyf,"yyyy-MM-dd"),"xzc",xzc, "zkhjl",zkhjl,zdmc1,"1");
		 }else if (sjlx.equals("zl")){
			 pageList = tjfxZhbemxbService.queryzlhsmx(DateUtil.parseDateFormat(tjyf,"yyyy-MM-dd"),"xzc",xzc, "zkhjl",zkhjl,zdmc1,"1");
		 }else{
			 pageList = tjfxZhbemxbService.queryckyemx(DateUtil.parseDateFormat(tjyf,"yyyy-MM-dd"),"xzc",xzc, "zkhjl",zkhjl,"1");
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
		 mv.addObject(NormalExcelConstants.FILE_NAME, "支行行动挂图作业表二明细列表");
		 mv.addObject(NormalExcelConstants.CLASS,  TjfxZhbemxbImport.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("支行行动挂图作业表一明细列表", "导出人:Jeecg", "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList1);
		 return mv;
	 }

}
