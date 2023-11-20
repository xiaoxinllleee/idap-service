package org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.controller;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import cn.afterturn.easypoi.word.WordExportUtil;
import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.xmlbeans.XmlOptions;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.JxlsConstants;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.FileUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.khgl.nh.service.IKhYgxxService;
import org.cmms.modules.khpjsx.khpjsxb.entity.PjsxPjjgmxb;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.entity.TjfxZhby;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.entity.TjfxZhbyImport;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.entity.TjfxZhbymxb;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.entity.TjfxZhbymxbImport;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbymxbService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 支行统计表一
 * @Author: cmms
 * @Date:   2019-12-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行统计表一")
@RestController
@RequestMapping("/tjfx.xdgtzytjbb.zhtjbby/tjfxZhby")
public class TjfxZhbyController {
	@Autowired
	private ITjfxZhbyService tjfxZhbyService;
	 @Autowired
	 private IKhYgxxService iKhYgxxService;
	 @Autowired
	 private ITjfxZhbymxbService tjfxZhbymxbService;


	 @Value(value = "${common.path.wordTemplate}")
	 private String wordTemplatepath;

	 @Value(value = "${common.path.export}")
	 private String exportpath;

	/**
	  * 分页列表查询
	 * @param tjfxZhby
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行统计表一-分页列表查询")
	@ApiOperation(value="支行统计表一-分页列表查询", notes="支行统计表一-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<TjfxZhby>> queryPageList(TjfxZhby tjfxZhby,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<TjfxZhby>> result = new Result<IPage<TjfxZhby>>();
		QueryWrapper<TjfxZhby> queryWrapper = QueryGenerator.initQueryWrapper(tjfxZhby, req.getParameterMap());
		Page<TjfxZhby> page = new Page<TjfxZhby>(pageNo, pageSize);
		IPage<TjfxZhby> pageList = tjfxZhbyService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param tjfxZhby
	 * @return
	 */
	@AutoLog(value = "支行统计表一-添加")
	@ApiOperation(value="支行统计表一-添加", notes="支行统计表一-添加")
	@PostMapping(value = "/add")
	public Result<TjfxZhby> add(@RequestBody TjfxZhby tjfxZhby) {
		Result<TjfxZhby> result = new Result<TjfxZhby>();
		try {
			tjfxZhbyService.save(tjfxZhby);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param tjfxZhby
	 * @return
	 */
	@AutoLog(value = "支行统计表一-编辑")
	@ApiOperation(value="支行统计表一-编辑", notes="支行统计表一-编辑")
	@PutMapping(value = "/edit")
	public Result<TjfxZhby> edit(@RequestBody TjfxZhby tjfxZhby) {
		Result<TjfxZhby> result = new Result<TjfxZhby>();
		TjfxZhby tjfxZhbyEntity = tjfxZhbyService.getById(tjfxZhby.getKsrq());
		if(tjfxZhbyEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = tjfxZhbyService.updateById(tjfxZhby);
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
	@AutoLog(value = "支行统计表一-通过id删除")
	@ApiOperation(value="支行统计表一-通过id删除", notes="支行统计表一-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			tjfxZhbyService.removeById(id);
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
	@AutoLog(value = "支行统计表一-批量删除")
	@ApiOperation(value="支行统计表一-批量删除", notes="支行统计表一-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<TjfxZhby> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<TjfxZhby> result = new Result<TjfxZhby>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.tjfxZhbyService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行统计表一-通过id查询")
	@ApiOperation(value="支行统计表一-通过id查询", notes="支行统计表一-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<TjfxZhby> queryById(@RequestParam(name="id",required=true) String id) {
		Result<TjfxZhby> result = new Result<TjfxZhby>();
		TjfxZhby tjfxZhby = tjfxZhbyService.getById(id);
		if(tjfxZhby==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(tjfxZhby);
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
      QueryWrapper<TjfxZhby> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              TjfxZhby tjfxZhby = JSON.parseObject(deString, TjfxZhby.class);
              queryWrapper = QueryGenerator.initQueryWrapper(tjfxZhby, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<TjfxZhby> pageList = tjfxZhbyService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "支行统计表一列表");
      mv.addObject(NormalExcelConstants.CLASS, TjfxZhby.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("支行统计表一列表数据", "导出人:Jeecg", "导出信息"));
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
              List<TjfxZhby> listTjfxZhbys = ExcelImportUtil.importExcel(file.getInputStream(), TjfxZhby.class, params);
              tjfxZhbyService.saveBatch(listTjfxZhbys);
              return Result.ok("文件导入成功！数据行数:" + listTjfxZhbys.size());
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
	 public ModelAndView exportTemplateXls(TjfxZhby tjfxZhby,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		 // Step.1 组装查询条件
		 QueryWrapper<TjfxZhby>queryWrapper = QueryGenerator.initQueryWrapper(tjfxZhby, request.getParameterMap());
		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new TemplateExcelView());
		 Date ksrq = tjfxZhby.getKsrq();
		 Date jsrq = tjfxZhby.getJsrq();
		 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
		 String ksqr1 = "";
		 String jsrq2 = "";
		 if (tjfxZhby.getKsrq() != null && tjfxZhby.getJsrq()!= null)
		 {
			 ksqr1 = sdf.format(ksrq);
			 jsrq2 = sdf.format(jsrq);
		 }
		 String jgdm = (tjfxZhby.getJgdm()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",tjfxZhby.getJgdm()));

		 Map<String,Object> map = new HashMap<String, Object>();
		 map.put("ksrq", ksqr1);
		 map.put("jsrq", jsrq2);
		 map.put("jgmc",jgdm);
		 List<TjfxZhby> pageList = tjfxZhbyService.list(queryWrapper);
		 List<TjfxZhbyImport> pageList1 = new ArrayList<>();
		 for (TjfxZhby zhby : pageList) {
			 zhby.setJgdm(zhby.getJgdm()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",zhby.getJgdm()));
			 zhby.setZrre(zhby.getZrre()==null ? "" : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",zhby.getZrre()));
			 zhby.setZxx(zhby.getZxx()== null ? "" :tjfxZhbyService.queryTableDictTextByKey("yxdygl_czxxgl","organize","qybm",zhby.getZxx()));
			 TjfxZhbyImport import1 = new TjfxZhbyImport();
			 BeanUtil.copyProperties(zhby,import1);
			 String ksrq1 = sdf.format(zhby.getKsrq());
			 String jsrq1 = sdf.format(zhby.getJsrq());
			 import1.setKsrq(ksrq1);
			 import1.setJsrq(jsrq1);
			 pageList1.add(import1);

		 }
		 map.put("list", pageList1);
		 String port = environment.getProperty("common.path.export");
		 //导出文件名称
		 mv.addObject(JxlsConstants.FILE_NAME, "支行表1");
		 mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("支行表1.xls"));
		 mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"/支行表1.xls");
		 mv.addObject(JxlsConstants.MAP_DATA, map);
		 return mv;
	 }


	 @RequestMapping(value = "/exportTemplateWord")
	 public void exportTemplateWord(TjfxZhby tjfxZhby,HttpServletRequest request, HttpServletResponse response) throws IOException {
/*
		 Map<String, Object> wordDataMap = new HashMap<String, Object>();// 存储报表全部数据
		 Map<String, Object> parametersMap = new HashMap<String, Object>();// 存储报表中不循环的数据


		 List<Map<String, Object>> table1 = new ArrayList<Map<String, Object>>();
		 Map<String, Object> map1=new HashMap<>();
		 map1.put("name", "张三");
		 map1.put("age", "23");
		 map1.put("email", "12121@qq.com");
		 map1.put("cs1", "测试1");
		 map1.put("cs2", "测试2");
		 map1.put("cs3", "测试3");

		 Map<String, Object> map2=new HashMap<>();
		 map2.put("name", "李四");
		 map2.put("age", "45");
		 map2.put("email", "45445@qq.com");
		 map2.put("cs1", "测试12");
		 map2.put("cs2", "测试12");
		 map2.put("cs3", "测试12");

		 Map<String, Object> map3=new HashMap<>();
		 map3.put("name", "Tom");
		 map3.put("age", "34");
		 map3.put("email", "6767@qq.com");
		 map3.put("cs1", "测试13");
		 map3.put("cs2", "测试13");
		 map3.put("cs3", "测试13");


		 table1.add(map1);
		 table1.add(map2);
		 table1.add(map3);



		 List<Map<String, Object>> table2 = new ArrayList<Map<String, Object>>();
		 Map<String, Object> map4=new HashMap<>();
		 map4.put("name", "tom");
		 map4.put("number", "sd1234");
		 map4.put("address", "上海");

		 Map<String, Object> map5=new HashMap<>();
		 map5.put("name", "seven");
		 map5.put("number", "sd15678");
		 map5.put("address", "北京");

		 Map<String, Object> map6=new HashMap<>();
		 map6.put("name", "lisa");
		 map6.put("number", "sd9078");
		 map6.put("address", "广州");

		 table2.add(map4);
		 table2.add(map5);
		 table2.add(map6);



		 parametersMap.put("userName", "JUVENILESS");
		 parametersMap.put("time", "2018-03-24");
		 parametersMap.put("sum", "3");


		 wordDataMap.put("table1", table1);
		 wordDataMap.put("table2", table2);
		 wordDataMap.put("parametersMap", parametersMap);
		 File file = new File(wordTemplatepath+"//模板.docx");//改成你本地文件所在目录


		 // 读取word模板
		 FileInputStream fileInputStream = new FileInputStream(file);
		 WordTemplate template = new WordTemplate(fileInputStream);

		 // 替换数据
		 template.replaceDocument(wordDataMap);

		 //生成文件
		 File outputFile=new File(exportpath+"//输出.docx");//改成你本地文件所在目录
		 FileOutputStream fos  = new FileOutputStream(outputFile);
		 template.getDocument().write(fos);*/

/*
		 Map<String, Object> dataMap = new HashMap<>();

		 Map<String, String> user = new HashMap<>();
		 user.put("name", "张三");
		 user.put("age", "22");
		 user.put("address", "重庆渝北区");
		 user.put("other", "篮球");
		 dataMap.put("user", user);

		 List<Map<String, String>> jobs = new ArrayList<>();
		 Map<String, String> job;
		 for (int i = 0; i < 5; i++) {
			 job = new HashMap<>();
			 job.put("name", "公司名称-" + i);
			 job.put("address", "公司地址:" + i);
			 jobs.add(job);
		 }

		 dataMap.put("jobs",jobs);
		 try {
			 XWPFDocument doc = WordExportUtil.exportWord07(wordTemplatepath+"//模板.docx", dataMap);
			 FileOutputStream fos = new FileOutputStream(exportpath+"//输出.docx");
			 doc.write(fos);
			 fos.close();
		 } catch (Exception e) {
			 e.printStackTrace();
		 }*/
	 }



	 //word模板，两个对象进行追加

	 public XWPFDocument mergeWord(XWPFDocument document, XWPFDocument doucDocument2) throws Exception {

		 XWPFDocument src1Document = document;

		 XWPFParagraph p = src1Document.createParagraph();
         //设置分页符
		 p.setPageBreak(true);

		 CTBody src1Body = src1Document.getDocument().getBody();

		 XWPFDocument src2Document = doucDocument2;

		 CTBody src2Body = src2Document.getDocument().getBody();

		 XWPFParagraph p2 = src2Document.createParagraph();

		 XmlOptions optionsOuter = new XmlOptions();

		 optionsOuter.setSaveOuter();

		 String appendString = src2Body.xmlText(optionsOuter);

		 String srcString = src1Body.xmlText();

		 String prefix = srcString.substring(0, srcString.indexOf(">") + 1);

		 String mainPart = srcString.substring(srcString.indexOf(">") + 1, srcString.lastIndexOf("<"));

		 String sufix = srcString.substring(srcString.lastIndexOf("<"));

		 String addPart = appendString.substring(appendString.indexOf(">") + 1, appendString.lastIndexOf("<"));

		 CTBody makeBody = CTBody.Factory.parse(prefix + mainPart + addPart + sufix);

		 src1Body.set(makeBody);

		 return src1Document;

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
			 parm.put("jgdm",jsonObject.getString("jgdm"));
			 tjfxZhbyService.extract(parm);
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
			 pjsxKhpjsxbList = tjfxZhbymxbService.queryTableDictTextByKey(param.getDate("ksrq"),param.getDate("jsrq"),param.getString("xzc"),param.getString("zkhjl"),param.getString("khlx"),param.getString("zdmc"),"xzc","1");
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
	  *
	  */

	 @RequestMapping(value = "/exportTemplatemxXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		 // Step.1 组装查询条件
		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 String ksrq = request.getParameter("ksrq");
		 String jsrq = request.getParameter("jsrq");
		 String xzc = request.getParameter("xzc");
		 String zkhjl = request.getParameter("zkhjl");
		 String khlx = request.getParameter("khlx");

		 //Map map=request.getParameterMap();
		 //假如参数比较多的时候，遍历map 通过key 判断对时间格式的字段进行转换成日期格式，其余不需处理。

		 List<TjfxZhbymxb> pageList =  	new  ArrayList<TjfxZhbymxb>();
		 pageList = tjfxZhbymxbService.queryTableDictTextByKey(DateUtil.parseDateFormat(ksrq,"yyyy-MM-dd"),DateUtil.parseDateFormat(jsrq,"yyyy-MM-dd"),xzc,zkhjl,khlx,"zkhjl","xzc","1");
		 List<TjfxZhbymxbImport> pageList1 = new ArrayList<>();
		 for (TjfxZhbymxb tjfxZhbymxb : pageList) {
			 tjfxZhbymxb.setGpld(tjfxZhbymxb.getGpld()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxZhbymxb.getGpld()));
			 tjfxZhbymxb.setZkhjl(tjfxZhbymxb.getZkhjl()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxZhbymxb.getZkhjl()));
			 tjfxZhbymxb.setSszh(tjfxZhbymxb.getSszh()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",tjfxZhbymxb.getSszh()));
			 TjfxZhbymxbImport import1 = new TjfxZhbymxbImport();
			 BeanUtil.copyProperties(tjfxZhbymxb,import1);
			 pageList1.add(import1);
		 }
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "评级结果明细列表");
		 mv.addObject(NormalExcelConstants.CLASS,  TjfxZhbymxbImport.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("支行统计表一明细列表数据", "导出人:Jeecg", "导出信息"));
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
				  zhbymxbList = tjfxZhbymxbService.querykhzfmx(param.getDate("ksrq"), param.getDate("jsrq"), param.getString("xzc"), param.getString("zkhjl"), param.getString("khlx"),"zkhjl","xzc","1");
			  }else{
				  zhbymxbList = tjfxZhbymxbService.queryljkhzfmx(param.getDate("ksrq"), param.getDate("jsrq"), param.getString("xzc"), param.getString("zkhjl"), param.getString("khlx"),"zkhjl","xzc","1");
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
		 List<TjfxZhbymxbImport> pageList1 = new ArrayList<>();
		 if (sjlx.equals("bz")) {
			 pageList = tjfxZhbymxbService.querykhzfmx(DateUtil.parseDateFormat(ksrq,"yyyy-MM-dd"),DateUtil.parseDateFormat(jsrq,"yyyy-MM-dd"), xzc, zkhjl, khlx,"zkhjl","xzc","1");
		 }else{
			 pageList = tjfxZhbymxbService.queryljkhzfmx(DateUtil.parseDateFormat(ksrq,"yyyy-MM-dd"),DateUtil.parseDateFormat(jsrq,"yyyy-MM-dd"), xzc, zkhjl, khlx,"zkhjl","xzc","1");
		 }

		 for (TjfxZhbymxb tjfxZhbymxb : pageList) {
			 tjfxZhbymxb.setGpld(tjfxZhbymxb.getGpld()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxZhbymxb.getGpld()));
			 tjfxZhbymxb.setZkhjl(tjfxZhbymxb.getZkhjl()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxZhbymxb.getZkhjl()));
			 tjfxZhbymxb.setSszh(tjfxZhbymxb.getSszh()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",tjfxZhbymxb.getSszh()));
			 TjfxZhbymxbImport import1 = new TjfxZhbymxbImport();
			 BeanUtil.copyProperties(tjfxZhbymxb,import1);
			 pageList1.add(import1);
		 }
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "全行行动挂图作业表一明细列表");
		 mv.addObject(NormalExcelConstants.CLASS,  TjfxZhbymxbImport.class);
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
				 zhbymxbList = tjfxZhbymxbService.querypjsxjdmx(param.getDate("ksrq"), param.getDate("jsrq"), param.getString("xzc"), param.getString("zkhjl"),"zkhjl","xzc","1");
			 }else{
				 zhbymxbList = tjfxZhbymxbService.queryljpjsxjdmx(param.getDate("ksrq"), param.getDate("jsrq"),param.getString("xzc"), param.getString("zkhjl"),"zkhjl","xzc","1");
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
		 List<TjfxZhbymxbImport> pageList1 = new ArrayList<>();

		 if (sjlx.equals("bz"))  {
			 pageList = tjfxZhbymxbService.querypjsxjdmx(DateUtil.parseDateFormat(ksrq,"yyyy-MM-dd"),DateUtil.parseDateFormat(jsrq,"yyyy-MM-dd"),xzc, zkhjl,"zkhjl","xzc","1");
		 }else{
			 pageList = tjfxZhbymxbService.queryljpjsxjdmx(DateUtil.parseDateFormat(ksrq,"yyyy-MM-dd"),DateUtil.parseDateFormat(jsrq,"yyyy-MM-dd"), xzc,zkhjl,"zkhjl","xzc","1");
		 }

		 for (TjfxZhbymxb tjfxZhbymxb : pageList) {
			 tjfxZhbymxb.setGpld(tjfxZhbymxb.getGpld()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxZhbymxb.getGpld()));
			 tjfxZhbymxb.setZkhjl(tjfxZhbymxb.getZkhjl()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxZhbymxb.getZkhjl()));
			 tjfxZhbymxb.setSszh(tjfxZhbymxb.getSszh()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",tjfxZhbymxb.getSszh()));
			 TjfxZhbymxbImport import1 = new TjfxZhbymxbImport();
			 BeanUtil.copyProperties(tjfxZhbymxb,import1);
			 pageList1.add(import1);
		 }

		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "全行行动挂图作业表一明细列表");
		 mv.addObject(NormalExcelConstants.CLASS,  TjfxZhbymxbImport.class);
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
				 zhbymxbList = tjfxZhbymxbService.querypjsxjdyxjemx(param.getDate("ksrq"), param.getDate("jsrq"), param.getString("xzc"), param.getString("zkhjl"),"zkhjl","xzc","1");
			 }else{
				 zhbymxbList = tjfxZhbymxbService.queryljpjsxjdyxjemx(param.getDate("ksrq"), param.getDate("jsrq"), param.getString("xzc"), param.getString("zkhjl"),"zkhjl","xzc","1");
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
		 List<TjfxZhbymxbImport>pageList1 = new ArrayList<>();

		 if (sjlx.equals("bz")) {
			 pageList = tjfxZhbymxbService.querypjsxjdyxjemx(DateUtil.parseDateFormat(ksrq,"yyyy-MM-dd"),DateUtil.parseDateFormat(jsrq,"yyyy-MM-dd"), xzc, zkhjl,"zkhjl","xzc","1");
		 }else{
			 pageList = tjfxZhbymxbService.queryljpjsxjdyxjemx(DateUtil.parseDateFormat(ksrq,"yyyy-MM-dd"),DateUtil.parseDateFormat(jsrq,"yyyy-MM-dd"),xzc,zkhjl,"zkhjl","xzc","1");
		 }

		 for (TjfxZhbymxb tjfxZhbymxb : pageList) {
			 tjfxZhbymxb.setGpld(tjfxZhbymxb.getGpld()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxZhbymxb.getGpld()));
			 tjfxZhbymxb.setZkhjl(tjfxZhbymxb.getZkhjl()== null ? "":tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",tjfxZhbymxb.getZkhjl()));
			 tjfxZhbymxb.setSszh(tjfxZhbymxb.getSszh()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",tjfxZhbymxb.getSszh()));
			 TjfxZhbymxbImport import1 = new TjfxZhbymxbImport();
			 BeanUtil.copyProperties(tjfxZhbymxb,import1);
			 pageList1.add(import1);
		 }

		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "全行行动挂图作业表一明细列表");
		 mv.addObject(NormalExcelConstants.CLASS,  TjfxZhbymxbImport.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("全行行动挂图作业表一明细列表", "导出人:Jeecg", "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList1);
		 return mv;
	 }





 }
