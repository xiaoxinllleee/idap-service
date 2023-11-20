package org.cmms.modules.tjfx.xdgtzytjbb.tjfxsfdpjsxwcqkmxb.controller;

import java.net.URL;
import java.net.URLEncoder;
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
import org.cmms.modules.tjfx.xdgtzytjbb.tjfxsfdpjsxwcqkmxb.entity.TjfxSfdpjsxwcqkmxb;
import org.cmms.modules.tjfx.xdgtzytjbb.tjfxsfdpjsxwcqkmxb.entity.TjfxSfdpjsxwcqkmxbImport;
import org.cmms.modules.tjfx.xdgtzytjbb.tjfxsfdpjsxwcqkmxb.service.ITjfxSfdpjsxwcqkmxbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.entity.TjfxPjsxwcqkmxb;
import org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.service.ITjfxPjsxwcqkmxbService;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 示范点评级授信完成情况明细表
 * @Author: cmms
 * @Date:   2019-12-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="示范点评级授信完成情况明细表")
@RestController
@RequestMapping("/tjfx.xdgtzytjbb.tjfxsfdpjsxwcqkmxb/tjfxSfdpjsxwcqkmxb")
public class TjfxSfdpjsxwcqkmxbController {
	@Autowired
	private ITjfxSfdpjsxwcqkmxbService tjfxSfdpjsxwcqkmxbService;

	 @Autowired
	 private ITjfxZhbyService tjfxZhbyService;

	 @Autowired
	 private ITjfxPjsxwcqkmxbService tjfxPjsxwcqkmxbService;
	
	/**
	  * 分页列表查询
	 * @param tjfxSfdpjsxwcqkmxb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "示范点评级授信完成情况明细表-分页列表查询")
	@ApiOperation(value="示范点评级授信完成情况明细表-分页列表查询", notes="示范点评级授信完成情况明细表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<TjfxSfdpjsxwcqkmxb>> queryPageList(TjfxSfdpjsxwcqkmxb tjfxSfdpjsxwcqkmxb,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<TjfxSfdpjsxwcqkmxb>> result = new Result<IPage<TjfxSfdpjsxwcqkmxb>>();
		QueryWrapper<TjfxSfdpjsxwcqkmxb> queryWrapper = QueryGenerator.initQueryWrapper(tjfxSfdpjsxwcqkmxb, req.getParameterMap());
		Page<TjfxSfdpjsxwcqkmxb> page = new Page<TjfxSfdpjsxwcqkmxb>(pageNo, pageSize);
		IPage<TjfxSfdpjsxwcqkmxb> pageList = tjfxSfdpjsxwcqkmxbService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param tjfxSfdpjsxwcqkmxb
	 * @return
	 */
	@AutoLog(value = "示范点评级授信完成情况明细表-添加")
	@ApiOperation(value="示范点评级授信完成情况明细表-添加", notes="示范点评级授信完成情况明细表-添加")
	@PostMapping(value = "/add")
	public Result<TjfxSfdpjsxwcqkmxb> add(@RequestBody TjfxSfdpjsxwcqkmxb tjfxSfdpjsxwcqkmxb) {
		Result<TjfxSfdpjsxwcqkmxb> result = new Result<TjfxSfdpjsxwcqkmxb>();
		try {
			tjfxSfdpjsxwcqkmxbService.save(tjfxSfdpjsxwcqkmxb);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param tjfxSfdpjsxwcqkmxb
	 * @return
	 */
	@AutoLog(value = "示范点评级授信完成情况明细表-编辑")
	@ApiOperation(value="示范点评级授信完成情况明细表-编辑", notes="示范点评级授信完成情况明细表-编辑")
	@PutMapping(value = "/edit")
	public Result<TjfxSfdpjsxwcqkmxb> edit(@RequestBody TjfxSfdpjsxwcqkmxb tjfxSfdpjsxwcqkmxb) {
		Result<TjfxSfdpjsxwcqkmxb> result = new Result<TjfxSfdpjsxwcqkmxb>();
		TjfxSfdpjsxwcqkmxb tjfxSfdpjsxwcqkmxbEntity = tjfxSfdpjsxwcqkmxbService.getById(tjfxSfdpjsxwcqkmxb.getBzcymc());
		if(tjfxSfdpjsxwcqkmxbEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = tjfxSfdpjsxwcqkmxbService.updateById(tjfxSfdpjsxwcqkmxb);
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
	@AutoLog(value = "示范点评级授信完成情况明细表-通过id删除")
	@ApiOperation(value="示范点评级授信完成情况明细表-通过id删除", notes="示范点评级授信完成情况明细表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			tjfxSfdpjsxwcqkmxbService.removeById(id);
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
	@AutoLog(value = "示范点评级授信完成情况明细表-批量删除")
	@ApiOperation(value="示范点评级授信完成情况明细表-批量删除", notes="示范点评级授信完成情况明细表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<TjfxSfdpjsxwcqkmxb> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<TjfxSfdpjsxwcqkmxb> result = new Result<TjfxSfdpjsxwcqkmxb>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.tjfxSfdpjsxwcqkmxbService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "示范点评级授信完成情况明细表-通过id查询")
	@ApiOperation(value="示范点评级授信完成情况明细表-通过id查询", notes="示范点评级授信完成情况明细表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<TjfxSfdpjsxwcqkmxb> queryById(@RequestParam(name="id",required=true) String id) {
		Result<TjfxSfdpjsxwcqkmxb> result = new Result<TjfxSfdpjsxwcqkmxb>();
		TjfxSfdpjsxwcqkmxb tjfxSfdpjsxwcqkmxb = tjfxSfdpjsxwcqkmxbService.getById(id);
		if(tjfxSfdpjsxwcqkmxb==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(tjfxSfdpjsxwcqkmxb);
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
      QueryWrapper<TjfxSfdpjsxwcqkmxb> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              TjfxSfdpjsxwcqkmxb tjfxSfdpjsxwcqkmxb = JSON.parseObject(deString, TjfxSfdpjsxwcqkmxb.class);
              queryWrapper = QueryGenerator.initQueryWrapper(tjfxSfdpjsxwcqkmxb, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<TjfxSfdpjsxwcqkmxb> pageList = tjfxSfdpjsxwcqkmxbService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "示范点评级授信完成情况明细表列表");
      mv.addObject(NormalExcelConstants.CLASS, TjfxSfdpjsxwcqkmxb.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("示范点评级授信完成情况明细表列表数据", "导出人:Jeecg", "导出信息"));
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
              List<TjfxSfdpjsxwcqkmxb> listTjfxSfdpjsxwcqkmxbs = ExcelImportUtil.importExcel(file.getInputStream(), TjfxSfdpjsxwcqkmxb.class, params);
              tjfxSfdpjsxwcqkmxbService.saveBatch(listTjfxSfdpjsxwcqkmxbs);
              return Result.ok("文件导入成功！数据行数:" + listTjfxSfdpjsxwcqkmxbs.size());
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
	 public ModelAndView exportTemplateXls(TjfxSfdpjsxwcqkmxb tjfxSfdpjsxwcqkmxb, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		 // Step.1 组装查询条件
		 QueryWrapper<TjfxSfdpjsxwcqkmxb>queryWrapper = QueryGenerator.initQueryWrapper(tjfxSfdpjsxwcqkmxb, request.getParameterMap());
		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new TemplateExcelView());
		 Date ksrq = tjfxSfdpjsxwcqkmxb.getKsrq();
		 Date jsrq = tjfxSfdpjsxwcqkmxb.getJsrq();
		 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
		 String ksqr1 = "";
		 String jsrq2 = "";
		 if (tjfxSfdpjsxwcqkmxb.getKsrq() != null && tjfxSfdpjsxwcqkmxb.getJsrq()!= null)
		 {
			 ksqr1 = sdf.format(ksrq);
			 jsrq2 = sdf.format(jsrq);
		 }
		 Map<String,Object> map = new HashMap<String, Object>();
		 map.put("ksrq",ksqr1 );
		 map.put("jsrq",jsrq2 );
		 List<TjfxSfdpjsxwcqkmxb> pageList = tjfxSfdpjsxwcqkmxbService.list(queryWrapper);
		 List<TjfxSfdpjsxwcqkmxbImport> pageList1 = new ArrayList<>();
		 for (TjfxSfdpjsxwcqkmxb sfdpjsxwcqkmxb : pageList) {
			 sfdpjsxwcqkmxb.setBzcymc(sfdpjsxwcqkmxb.getBzcymc()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",sfdpjsxwcqkmxb.getBzcymc()));
			 sfdpjsxwcqkmxb.setJgdm(sfdpjsxwcqkmxb.getJgdm()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",sfdpjsxwcqkmxb.getJgdm()));
			 TjfxSfdpjsxwcqkmxbImport import1 = new TjfxSfdpjsxwcqkmxbImport();
			 BeanUtil.copyProperties(sfdpjsxwcqkmxb,import1);
			 import1.setKsrq(sdf.format(sfdpjsxwcqkmxb.getKsrq()));
			 import1.setJsrq(sdf.format(sfdpjsxwcqkmxb.getJsrq()));
			 pageList1.add(import1);
		 }
		 map.put("list", pageList1);
		 String port = environment.getProperty("common.path.export");
		 //导出文件名称
		 mv.addObject(JxlsConstants.FILE_NAME, "示范点评级授信完成情况明细表");
		 mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME,FileUtil.getTempFilePath("示范点评级授信完成情况明细表.xls"));
		 mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"示范点评级授信完成情况明细表.xls");
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
			 tjfxSfdpjsxwcqkmxbService.extract(parm);
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
				 pageList = tjfxPjsxwcqkmxbService.querysjpjhs(param.getDate("ksrq"),param.getDate("jsrq"),param.getString("xzc"),"2");
			 }else {
				 pageList = tjfxPjsxwcqkmxbService.querysjzfhs(param.getDate("ksrq"),param.getDate("jsrq"),param.getString("xzc"),"2",param.getString("zdmc"));
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
			 pageList = tjfxPjsxwcqkmxbService.querysjpjhs(DateUtil.parseDateFormat(ksrq,"yyyy-MM-dd"),DateUtil.parseDateFormat(jsrq,"yyyy-MM-dd"),xzc,"2");
		 }else {
			 pageList = tjfxPjsxwcqkmxbService.querysjzfhs(DateUtil.parseDateFormat(ksrq,"yyyy-MM-dd"),DateUtil.parseDateFormat(jsrq,"yyyy-MM-dd"),xzc,"2",zdmc);
		 }

		 for (TjfxPjsxwcqkmxb tjfxPjsxwcqkmxb : pageList) {
			 tjfxPjsxwcqkmxb.setBzcy(tjfxPjsxwcqkmxb.getBzcy()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxPjsxwcqkmxb.getBzcy()));
			 tjfxPjsxwcqkmxb.setZkhjl(tjfxPjsxwcqkmxb.getZkhjl()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxPjsxwcqkmxb.getZkhjl()));
			 tjfxPjsxwcqkmxb.setSszh(tjfxPjsxwcqkmxb.getSszh()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",tjfxPjsxwcqkmxb.getSszh()));
		 }

		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "示范点评级授信完成情况明细表");
		 mv.addObject(NormalExcelConstants.CLASS,  TjfxPjsxwcqkmxb.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("示范点评级授信完成情况明细表", "导出人:Jeecg", "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		 return mv;
	 }



 }
