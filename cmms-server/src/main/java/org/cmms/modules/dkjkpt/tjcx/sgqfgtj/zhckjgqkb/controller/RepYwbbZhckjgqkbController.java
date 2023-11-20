package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhckjgqkb.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.JxlsConstants;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.FileUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhckjgqkb.entity.RepYwbbZhckjgqkb;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhckjgqkb.entity.RepYwbbZhckjgqkbImport;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhckjgqkb.service.IRepYwbbZhckjgqkbService;
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
 * @Description: 支行存款结构情况表
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行存款结构情况表")
@RestController
@RequestMapping("/dkjkpt.tjcx.sgqfgtj.zhckjgqkb/repYwbbZhckjgqkb")
public class RepYwbbZhckjgqkbController {
	@Autowired
	private IRepYwbbZhckjgqkbService repYwbbZhckjgqkbService;

	 @Autowired
	 private ITjfxZhbyService tjfxZhbyService;
	
	/**
	  * 分页列表查询
	 * @param repYwbbZhckjgqkb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行存款结构情况表-分页列表查询")
	@ApiOperation(value="支行存款结构情况表-分页列表查询", notes="支行存款结构情况表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<RepYwbbZhckjgqkb>> queryPageList(RepYwbbZhckjgqkb repYwbbZhckjgqkb,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<RepYwbbZhckjgqkb>> result = new Result<IPage<RepYwbbZhckjgqkb>>();
		QueryWrapper<RepYwbbZhckjgqkb> queryWrapper = QueryGenerator.initQueryWrapper(repYwbbZhckjgqkb, req.getParameterMap());
		Page<RepYwbbZhckjgqkb> page = new Page<RepYwbbZhckjgqkb>(pageNo, pageSize);
		IPage<RepYwbbZhckjgqkb> pageList = repYwbbZhckjgqkbService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param repYwbbZhckjgqkb
	 * @return
	 */
	@AutoLog(value = "支行存款结构情况表-添加")
	@ApiOperation(value="支行存款结构情况表-添加", notes="支行存款结构情况表-添加")
	@PostMapping(value = "/add")
	public Result<RepYwbbZhckjgqkb> add(@RequestBody RepYwbbZhckjgqkb repYwbbZhckjgqkb) {
		Result<RepYwbbZhckjgqkb> result = new Result<RepYwbbZhckjgqkb>();
		try {
			repYwbbZhckjgqkbService.save(repYwbbZhckjgqkb);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param repYwbbZhckjgqkb
	 * @return
	 */
	/*@AutoLog(value = "支行存款结构情况表-编辑")
	@ApiOperation(value="支行存款结构情况表-编辑", notes="支行存款结构情况表-编辑")
	@PutMapping(value = "/edit")
	public Result<RepYwbbZhckjgqkb> edit(@RequestBody RepYwbbZhckjgqkb repYwbbZhckjgqkb) {
		Result<RepYwbbZhckjgqkb> result = new Result<RepYwbbZhckjgqkb>();
		RepYwbbZhckjgqkb repYwbbZhckjgqkbEntity = repYwbbZhckjgqkbService.getById(repYwbbZhckjgqkb.getId());
		if(repYwbbZhckjgqkbEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = repYwbbZhckjgqkbService.updateById(repYwbbZhckjgqkb);
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
	@AutoLog(value = "支行存款结构情况表-通过id删除")
	@ApiOperation(value="支行存款结构情况表-通过id删除", notes="支行存款结构情况表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			repYwbbZhckjgqkbService.removeById(id);
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
	@AutoLog(value = "支行存款结构情况表-批量删除")
	@ApiOperation(value="支行存款结构情况表-批量删除", notes="支行存款结构情况表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<RepYwbbZhckjgqkb> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<RepYwbbZhckjgqkb> result = new Result<RepYwbbZhckjgqkb>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.repYwbbZhckjgqkbService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行存款结构情况表-通过id查询")
	@ApiOperation(value="支行存款结构情况表-通过id查询", notes="支行存款结构情况表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<RepYwbbZhckjgqkb> queryById(@RequestParam(name="id",required=true) String id) {
		Result<RepYwbbZhckjgqkb> result = new Result<RepYwbbZhckjgqkb>();
		RepYwbbZhckjgqkb repYwbbZhckjgqkb = repYwbbZhckjgqkbService.getById(id);
		if(repYwbbZhckjgqkb==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(repYwbbZhckjgqkb);
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
      QueryWrapper<RepYwbbZhckjgqkb> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              RepYwbbZhckjgqkb repYwbbZhckjgqkb = JSON.parseObject(deString, RepYwbbZhckjgqkb.class);
              queryWrapper = QueryGenerator.initQueryWrapper(repYwbbZhckjgqkb, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<RepYwbbZhckjgqkb> pageList = repYwbbZhckjgqkbService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "支行存款结构情况表列表");
      mv.addObject(NormalExcelConstants.CLASS, RepYwbbZhckjgqkb.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("支行存款结构情况表列表数据", "导出人:Jeecg", "导出信息"));
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
	 public ModelAndView exportTemplateXls(RepYwbbZhckjgqkb repYwbbZhckjgqkb,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
      // Step.1 组装查询条件
		 //QueryWrapper<RepYwbbZhckjgqkb>queryWrapper = null ;
		 QueryWrapper<RepYwbbZhckjgqkb>queryWrapper = QueryGenerator.initQueryWrapper(repYwbbZhckjgqkb, request.getParameterMap());
		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new TemplateExcelView());
		 Map<String,Object> map = new HashMap<String, Object>();
		 map.put("tbsj", DateUtil.formatDateTime("yyyy-MM-dd"));
		 List<RepYwbbZhckjgqkb> pageList = repYwbbZhckjgqkbService.list(queryWrapper);
		/* List<RepYwbbZhckjgqkb> list = new ArrayList<RepYwbbZhckjgqkb>();
		 RepYwbbZhckjgqkb zhckjgqkb = new RepYwbbZhckjgqkb();
		 zhckjgqkb.setCkzye(new BigDecimal(10000.00));
		 zhckjgqkb.setYyehs("123");
		 list.add(zhckjgqkb);*/
		List<RepYwbbZhckjgqkbImport> pageList1 = new ArrayList<>();
		 String port = environment.getProperty("common.path.export");
		 for (RepYwbbZhckjgqkb ywbbZhckjgqkb : pageList) {
			 ywbbZhckjgqkb.setJgdm(ywbbZhckjgqkb.getJgdm()==null ? " " : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","ywjgdm",ywbbZhckjgqkb.getJgdm()));
			 RepYwbbZhckjgqkbImport import1 = new RepYwbbZhckjgqkbImport();
			 BeanUtil.copyProperties(ywbbZhckjgqkb,import1);
			 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
			 String tjyf1 = sdf.format(ywbbZhckjgqkb.getTjyf());
			 import1.setTjyf(tjyf1);
			 pageList1.add(import1);
		 }

		 map.put("list", pageList1);
		 //导出文件名称
		 mv.addObject(JxlsConstants.FILE_NAME, "支行行存款结构情况表");
		 mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("支行存款结构情况表_template.xls"));
		 mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"/支行存款结构情况表.xls");
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
              List<RepYwbbZhckjgqkb> listRepYwbbZhckjgqkbs = ExcelImportUtil.importExcel(file.getInputStream(), RepYwbbZhckjgqkb.class, params);
              repYwbbZhckjgqkbService.saveBatch(listRepYwbbZhckjgqkbs);
              return Result.ok("文件导入成功！数据行数:" + listRepYwbbZhckjgqkbs.size());
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
			 repYwbbZhckjgqkbService.extract(tjyf);
		 } catch (Exception e) {
			 log.error("提取失败",e.getMessage());
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功!");
	 }

	 /**
	  *   查询储蓄户数
	  * @return
	  */
	 @RequestMapping(value = "/queryechart", method = RequestMethod.PUT)
	 public Result<?> queryechart(@RequestBody JSONObject jsonObject1) {

		 //查询按客户性质分
		 int cxhs = 0;int dghs = 0;
		 Double cxye = 0.0;Double dgye = 0.0;
		 JSONArray jsonArray = new JSONArray();
		 JSONObject jsonObject = new JSONObject();
		 JSONObject jo = new JSONObject();
		 JSONObject jo1 = new JSONObject();
		 Map<String,String> map1 = new HashMap<>();


		 //查询个人客户按年龄分类
		 int qj1hs = 0;int qj2hs = 0;
		 int qj3hs = 0;int qj4hs = 0;
		 Double qj1ye = 0.0;Double qj2ye = 0.0;
		 Double qj3ye = 0.0; Double qj4ye = 0.0;
		 JSONObject nffcjo = new JSONObject();
		 JSONObject nffcjo1 = new JSONObject();
		 JSONObject nffcjo2 = new JSONObject();
		 JSONObject nffcjo3 = new JSONObject();
		 JSONArray nlfcjsonArray = new JSONArray();

		 //查询按单户存款余额分类
		 int ckyeqj1hs = 0;int ckyeqj2hs = 0;
		 int ckyeqj3hs = 0;int ckyeqj4hs = 0;
		 int ckyeqj5hs = 0;
		 Double ckyeqj1ye = 0.0;Double ckyeqj2ye = 0.0;
		 Double ckyeqj3ye =0.0;Double ckyeqj4ye = 0.0;
		 Double ckyeqj5ye = 0.0;
		 JSONObject ckyejo = new JSONObject();
		 JSONObject ckyejo1 = new JSONObject();
		 JSONObject ckyejo2 = new JSONObject();
		 JSONObject ckyejo3 = new JSONObject();
		 JSONObject ckyejo4 = new JSONObject();
		 JSONArray ckyejsonArray = new JSONArray();

		 RepYwbbZhckjgqkb repYwbbZhckjgqkb = new RepYwbbZhckjgqkb();
		 repYwbbZhckjgqkb.setTjyf(jsonObject1.getDate("tjyf"));
		 repYwbbZhckjgqkb.setJgdm(jsonObject1.getString("jgdm"));
		 if (repYwbbZhckjgqkb.getTjyf()==null){
			 // 获取前月的第一天
			Date date = new Date();
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			 Calendar calendar = Calendar.getInstance();
			 calendar.setTime(date);
			 calendar.add(Calendar.MONTH, 0);
			 Date theDate = calendar.getTime();
			 GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
			 gcLast.setTime(theDate);
			 gcLast.set(Calendar.DAY_OF_MONTH, 1);
			 String day_first = df.format(gcLast.getTime());
			 StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
			 day_first = str.toString();
			 //把string类型转换date
			 Date ycrq =null;
			 SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
			 try {
				 ycrq =  formatter.parse(day_first);
			 } catch (ParseException e) {
				 e.printStackTrace();
			 }
			 // 获取前月的第一天
			 repYwbbZhckjgqkb.setTjyf(ycrq);
		 }
		 Map<String,String[]> map = new HashMap<>();
		 QueryWrapper<RepYwbbZhckjgqkb> queryWrapper = QueryGenerator.initQueryWrapper(repYwbbZhckjgqkb,map);
		 List<RepYwbbZhckjgqkb> list = repYwbbZhckjgqkbService.list(queryWrapper);
		 //根据前台传过来的数据类型判断查询余额还是户数 cxlx 1 查询户数 2 查询余额
		 if (jsonObject1.getString("cxlx").equals("1")){
			 for (RepYwbbZhckjgqkb ywbbZhckjgqkb : list) {
				 cxhs += Integer.parseInt(ywbbZhckjgqkb.getCxhs());
				 dghs += Integer.parseInt(ywbbZhckjgqkb.getDghs());
				 //查询个人客户按年龄分类 年龄阶梯户数
				 qj1hs += Integer.parseInt(ywbbZhckjgqkb.getSssyxhs());
				 qj2hs += Integer.parseInt(ywbbZhckjgqkb.getSszwsshs());
				 qj3hs += Integer.parseInt(ywbbZhckjgqkb.getWszlsshs());
				 qj4hs += Integer.parseInt(ywbbZhckjgqkb.getLswsyshs());

				 //查询按单户存款余额分类 余额阶梯户数
				 ckyeqj1hs += Integer.parseInt(ywbbZhckjgqkb.getYwyyxhs());
				 ckyeqj2hs += Integer.parseInt(ywbbZhckjgqkb.getYwzwwyhs());
				 ckyeqj3hs += Integer.parseInt(ywbbZhckjgqkb.getWwzswyhs());
				 ckyeqj4hs += Integer.parseInt(ywbbZhckjgqkb.getSwzwswyhs());
				 ckyeqj5hs += Integer.parseInt(ywbbZhckjgqkb.getWswyyshs());

			 }
			/* map1.put("储蓄户数",String.valueOf(cxhs));
			 map1.put("对公户数",String.valueOf(dghs));*/

			 jo.put("name","储蓄户数");
			 jo.put("value",cxhs);
			 jo1.put("name","对公户数");
			 jo1.put("value",dghs);

			 nffcjo.put("name","30岁以下户数");
			 nffcjo.put("value",qj1hs);
			 nffcjo1.put("name","30~50岁户数");
			 nffcjo1.put("value",qj2hs);
			 nffcjo2.put("name","50~65岁户数");
			 nffcjo2.put("value",qj3hs);
			 nffcjo3.put("name","65岁以上户数");
			 nffcjo3.put("value",qj4hs);

			 ckyejo.put("name","1万元以下户数");
			 ckyejo.put("value",ckyeqj1hs);
			 ckyejo1.put("name","1~5万元户数");
			 ckyejo1.put("value",ckyeqj2hs);
			 ckyejo2.put("name","5~10万元户数");
			 ckyejo2.put("value",ckyeqj3hs);
			 ckyejo3.put("name","10~50万元户数");
			 ckyejo3.put("value",ckyeqj4hs);
			 ckyejo4.put("name","50万元以上户数");
			 ckyejo4.put("value",ckyeqj5hs);
		 }else {
			 for (RepYwbbZhckjgqkb ywbbZhckjgqkb : list) {
				 cxye += ywbbZhckjgqkb.getCxye().doubleValue();
				 dgye += ywbbZhckjgqkb.getDgye().doubleValue();

				 //查询个人客户按年龄分类 年龄阶梯存款余额
				 qj1ye += ywbbZhckjgqkb.getSssyxye().doubleValue();
				 qj2ye += ywbbZhckjgqkb.getSszwssye().doubleValue();
				 qj3ye += ywbbZhckjgqkb.getWszlssye().doubleValue();
				 qj4ye += ywbbZhckjgqkb.getLswsysye().doubleValue();

				 //查询按单户存款余额分类 余额阶梯户数
				 ckyeqj1ye += ywbbZhckjgqkb.getYwyyxye().doubleValue();
				 ckyeqj2ye += ywbbZhckjgqkb.getYwzwwyye().doubleValue();
				 ckyeqj3ye += ywbbZhckjgqkb.getWwzswyye().doubleValue();
				 ckyeqj4ye += ywbbZhckjgqkb.getSwzwswyye().doubleValue();
				 ckyeqj5ye += ywbbZhckjgqkb.getWswyysye().doubleValue();
			 }
			 jo.put("name","储蓄余额");
			 jo.put("value",cxye);
			 jo1.put("name","对公余额");
			 jo1.put("value",dgye);

			 nffcjo.put("name","30岁以下存款余额");
			 nffcjo.put("value",qj1ye);
			 nffcjo1.put("name","30~50岁存款余额");
			 nffcjo1.put("value",qj2ye);
			 nffcjo2.put("name","50~65岁存款余额");
			 nffcjo2.put("value",qj3ye);
			 nffcjo3.put("name","65岁以上存款余额");
			 nffcjo3.put("value",qj4ye);

			 ckyejo.put("name","1万元以下余额");
			 ckyejo.put("value",ckyeqj1ye);
			 ckyejo1.put("name","1~5万元余额");
			 ckyejo1.put("value",ckyeqj2ye);
			 ckyejo2.put("name","5~10万元余额");
			 ckyejo2.put("value",ckyeqj3ye);
			 ckyejo3.put("name","10~50万元余额");
			 ckyejo3.put("value",ckyeqj4ye);
			 ckyejo4.put("name","50万元以上余额");
			 ckyejo4.put("value",ckyeqj5ye);
		 }

		 jsonArray.add(jo);
		 jsonArray.add(jo1);

		//jsonArray.add(map1);

		 nlfcjsonArray.add(nffcjo);
		 nlfcjsonArray.add(nffcjo1);
		 nlfcjsonArray.add(nffcjo2);
		 nlfcjsonArray.add(nffcjo3);

		 ckyejsonArray.add(ckyejo);
		 ckyejsonArray.add(ckyejo1);
		 ckyejsonArray.add(ckyejo2);
		 ckyejsonArray.add(ckyejo3);
		 ckyejsonArray.add(ckyejo4);

		 jsonObject.put("nlfclist",nlfcjsonArray);
		 jsonObject.put("ckyefclist",ckyejsonArray);
		 jsonObject.put("list",jsonArray);
	 	return  Result.ok(jsonObject);
	 }

	 /**
	  *   查询客户性质分类余额
	  * @return
	  */
	 @RequestMapping(value = "/queryechartye", method = RequestMethod.PUT)
	 public Result<?> queryechartye(@RequestBody RepYwbbZhckjgqkb repYwbbZhckjgqkb) {
		 JSONArray jsonArray = new JSONArray();
		 JSONObject jsonObject = new JSONObject();
		 JSONObject jo = new JSONObject();
		 JSONObject jo1 = new JSONObject();
		 int cxhs = 0;
		 int dghs = 0;
		 int qj1hs = 0;
		 int qj2hs = 0;
		 int qj3hs = 0;
		 int qj4hs = 0;
		 int qj1ye = 0;
		 int qj2ye = 0;
		 int qj3ye = 0;
		 int qj4ye = 0;
		 if (repYwbbZhckjgqkb.getTjyf()==null){
			 // 获取前月的第一天
			 Date date = new Date();
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			 Calendar calendar = Calendar.getInstance();
			 calendar.setTime(date);
			 calendar.add(Calendar.MONTH, 0);
			 Date theDate = calendar.getTime();
			 GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
			 gcLast.setTime(theDate);
			 gcLast.set(Calendar.DAY_OF_MONTH, 1);
			 String day_first = df.format(gcLast.getTime());
			 StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
			 day_first = str.toString();
			 //把string类型转换date
			 Date ycrq =null;
			 SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
			 try {
				 ycrq =  formatter.parse(day_first);
			 } catch (ParseException e) {
				 e.printStackTrace();
			 }
			 // 获取前月的第一天
			 repYwbbZhckjgqkb.setTjyf(ycrq);
		 }
		 Map<String,String[]> map = new HashMap<>();
		 QueryWrapper<RepYwbbZhckjgqkb> queryWrapper = QueryGenerator.initQueryWrapper(repYwbbZhckjgqkb,map);
		 List<RepYwbbZhckjgqkb> list = repYwbbZhckjgqkbService.list(queryWrapper);
		 for (RepYwbbZhckjgqkb ywbbZhckjgqkb : list) {
			 cxhs += Integer.parseInt(ywbbZhckjgqkb.getCxhs());
			 dghs += Integer.parseInt(ywbbZhckjgqkb.getDghs());

			 //查询个人客户按年龄分类 年龄阶梯户数
			 qj1hs += Integer.parseInt(ywbbZhckjgqkb.getSssyxhs());
			 qj2hs += Integer.parseInt(ywbbZhckjgqkb.getSszwsshs());
			 qj3hs += Integer.parseInt(ywbbZhckjgqkb.getWszlsshs());
			 qj4hs += Integer.parseInt(ywbbZhckjgqkb.getLswsyshs());
             //查询个人客户按年龄分类 年龄阶梯存款余额
			 qj1ye += Integer.parseInt(ywbbZhckjgqkb.getSssyxhs());
			 qj2ye += Integer.parseInt(ywbbZhckjgqkb.getSszwsshs());
			 qj3ye += Integer.parseInt(ywbbZhckjgqkb.getWszlsshs());
			 qj4ye += Integer.parseInt(ywbbZhckjgqkb.getLswsyshs());

		 }
		 jo.put("name","储蓄户数");
		 jo.put("value",cxhs);
		 jo1.put("name","对公户数");
		 jo1.put("value",dghs);
		 jsonArray.add(jo);
		 jsonArray.add(jo1);

		 /*for (RepYwbbZhckjgqkb ywbbZhckjgqkb : list) {
			 JSONObject jsonObject1 = new JSONObject();
			 jsonObject1.put("name","储蓄户数");
			 jsonObject1.put("value",ywbbZhckjgqkb.getCxhs());
			 num+= intarry[i]
			 JSONObject jsonObject2 = new JSONObject();
			 jsonObject2.put("name","对公户数");
			 jsonObject2.put("value",ywbbZhckjgqkb.getDghs());
			 jsonArray.add(jsonObject1);
			 jsonArray.add(jsonObject2);
		 }*/
		 jsonObject.put("list",jsonArray);
		 return  Result.ok(jsonObject);
	 }

	/* *//**
	  *   查询客户按年龄分类数据
	  * @return
	  *//*
	 @RequestMapping(value = "/nlflqueryechart", method = RequestMethod.PUT)
	 public Result<?> nlflqueryechart(@RequestBody JSONObject jsonObject1) {
		 JSONArray jsonArray = new JSONArray();
		 JSONObject jsonObject = new JSONObject();
		 JSONObject jo = new JSONObject();
		 JSONObject jo1 = new JSONObject();
		 int qj1hs = 0;
		 int qj2hs = 0;
		 int qj3hs = 0;
		 int qj4hs = 0;
		 int qj1ye = 0;
		 int qj2ye = 0;
		 int qj3ye = 0;
		 int qj4ye = 0;

		 RepYwbbZhckjgqkb repYwbbZhckjgqkb = new RepYwbbZhckjgqkb();
		 repYwbbZhckjgqkb.setTjyf(jsonObject1.getDate("tjyf"));
		 repYwbbZhckjgqkb.setJgdm(jsonObject1.getString("jgdm"));
		 if (repYwbbZhckjgqkb.getTjyf()==null){
			 // 获取前月的第一天
			 Date date = new Date();
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			 Calendar calendar = Calendar.getInstance();
			 calendar.setTime(date);
			 calendar.add(Calendar.MONTH, 0);
			 Date theDate = calendar.getTime();
			 GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
			 gcLast.setTime(theDate);
			 gcLast.set(Calendar.DAY_OF_MONTH, 1);
			 String day_first = df.format(gcLast.getTime());
			 StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
			 day_first = str.toString();
			 //把string类型转换date
			 Date ycrq =null;
			 SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
			 try {
				 ycrq =  formatter.parse(day_first);
			 } catch (ParseException e) {
				 e.printStackTrace();
			 }
			 // 获取前月的第一天
			 repYwbbZhckjgqkb.setTjyf(ycrq);
		 }
		 Map<String,String[]> map = new HashMap<>();
		 QueryWrapper<RepYwbbZhckjgqkb> queryWrapper = QueryGenerator.initQueryWrapper(repYwbbZhckjgqkb,map);
		 List<RepYwbbZhckjgqkb> list = repYwbbZhckjgqkbService.list(queryWrapper);
		 //根据前台传过来的数据类型判断查询余额还是户数 cxlx 1 查询户数 2 查询余额
		 if (jsonObject1.getString("cxlx").equals("1")){
			 for (RepYwbbZhckjgqkb ywbbZhckjgqkb : list) {
				 qj1hs += Integer.parseInt(ywbbZhckjgqkb.getCxhs());
				 qj2hs += Integer.parseInt(ywbbZhckjgqkb.getDghs());
				 qj2hs += Integer.parseInt(ywbbZhckjgqkb.getDghs());

				 qj2hs += Integer.parseInt(ywbbZhckjgqkb.getDghs());

			 }
			 jo.put("name","储蓄户数");
			 jo.put("value",cxhs);
			 jo1.put("name","对公户数");
			 jo1.put("value",dghs);
		 }else {
			 for (RepYwbbZhckjgqkb ywbbZhckjgqkb : list) {
				 cxye += Integer.parseInt(ywbbZhckjgqkb.getCxhs());
				 dgye += Integer.parseInt(ywbbZhckjgqkb.getDghs());
			 }
			 jo.put("name","储蓄余额");
			 jo.put("value",cxye);
			 jo1.put("name","对公余额");
			 jo1.put("value",dgye);
		 }

		 jsonArray.add(jo);
		 jsonArray.add(jo1);

		 jsonObject.put("list",jsonArray);
		 return  Result.ok(jsonObject);
	 }*/


	 }
