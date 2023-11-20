package org.cmms.modules.report.tzsjgl.sgtzxyk.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.keyinfo.KeyValue;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.annotations.Param;
import org.checkerframework.checker.units.qual.A;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.modules.report.tzsjgl.sgtzxyk.entity.SgtzXyk;
import org.cmms.modules.report.tzsjgl.sgtzxyk.entity.SgtzXykVO;
import org.cmms.modules.report.tzsjgl.sgtzxyk.service.ISgtzXykService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 信用卡_湘潭
 * @Author: jeecg-boot
 * @Date:   2023-03-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags="信用卡_湘潭")
@RestController
@RequestMapping("/sgtzxyk/sgtzXyk")
public class SgtzXykController extends JeecgController<SgtzXyk, ISgtzXykService> {
	@Autowired
	private ISgtzXykService sgtzXykService;

	/**
	 * 分页列表查询
	 *
	 * @param sgtzXyk
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "信用卡_湘潭-分页列表查询")
	@ApiOperation(value="信用卡_湘潭-分页列表查询", notes="信用卡_湘潭-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SgtzXyk sgtzXyk,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgtzXyk> queryWrapper = QueryGenerator.initQueryWrapper(sgtzXyk, req.getParameterMap());
		Page<SgtzXyk> page = new Page<SgtzXyk>(pageNo, pageSize);
		IPage<SgtzXyk> pageList = sgtzXykService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param sgtzXyk
	 * @return
	 */
	@AutoLog(value = "信用卡_湘潭-添加")
	@ApiOperation(value="信用卡_湘潭-添加", notes="信用卡_湘潭-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SgtzXyk sgtzXyk) {
		sgtzXykService.save(sgtzXyk);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sgtzXyk
	 * @return
	 */
	@AutoLog(value = "信用卡_湘潭-编辑")
	@ApiOperation(value="信用卡_湘潭-编辑", notes="信用卡_湘潭-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SgtzXyk sgtzXyk) {
		QueryWrapper<SgtzXyk> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("sjrq",sgtzXyk.getSjrq());
		queryWrapper.eq("zjhm",sgtzXyk.getZjhm());
		sgtzXykService.update(sgtzXyk,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过xh删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "信用卡_湘潭-通过id删除")
	@ApiOperation(value="信用卡_湘潭-通过id删除", notes="信用卡_湘潭-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("sjrq") String sjrq,@Param("zjhm")String zjhm) {
		System.out.println(DateUtil.parse(sjrq)+"====");
		QueryWrapper<SgtzXyk> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("sjrq",DateUtil.parse(sjrq));
		queryWrapper.eq("zjhm",zjhm);
		sgtzXykService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}
	 /**
	  * 批量删除
	  *
	  * @param
	  * @return
	  */
	 @AutoLog(value = "信用卡-批量删除")
	 @ApiOperation(value="信用卡_湘潭-批量删除", notes="信用卡_湘潭-批量删除")
	 @DeleteMapping(value = "/deleteByBatch")
	 public Result<?> deleteByBatch(@Param("sjrq") String sjrq) {
		 QueryWrapper<SgtzXyk> queryWrapper = new QueryWrapper<>();
		 DateTime parse = DateUtil.parse(sjrq,"yyyy-MM-dd");
		 queryWrapper.eq("sjrq",parse);
		 sgtzXykService.remove(queryWrapper);
		 return Result.ok("批量删除成功！");
	 }
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "信用卡_湘潭-批量删除")
	@ApiOperation(value="信用卡_湘潭-批量删除", notes="信用卡_湘潭-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgtzXykService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param xh
	 * @return
	 */
	@AutoLog(value = "信用卡_湘潭-通过id查询")
	@ApiOperation(value="信用卡_湘潭-通过id查询", notes="信用卡_湘潭-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="xh",required=true) String xh) {
		SgtzXyk sgtzXyk = sgtzXykService.getById(xh);
		return Result.ok(sgtzXyk);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sgtzXyk
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SgtzXyk sgtzXyk) {
      return super.exportXls(request, sgtzXyk, SgtzXyk.class, "信用卡_湘潭");
  }

	 /**
	  * 导出模板
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "信用卡导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, SgtzXykVO.class);
		 ExportParams exportParams = new ExportParams("信用卡导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
	 }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
	  String sjrq = request.getParameter("sjrq");
	  System.out.println(sjrq + "----sjrq----");
	  Date parse = DateUtil.parse(sjrq,"yyyy-MM-dd");
	  String filePaths = jsonObject.getString("filePaths");
	  if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
		  return Result.error("请先上传文件！");
	  }
	  String[] filePathList = filePaths.split(",");
	  JSONObject obj = new JSONObject();
	  for (String filePath : filePathList) {
		  String baseFilePath = uploadpath + File.separator + filePath;
		  File file = new File(baseFilePath);
		  ImportParams params = new ImportParams();
		  params.setTitleRows(1);
		  params.setHeadRows(1);
		  params.setNeedSave(false);
		/*  if (dkqmxImportVerify != null) {
			  params.setVerifyHanlder(dkqmxImportVerify);
		  }*/
		  FileOutputStream fos = null;
		  ObjectMapper objectMapper = new ObjectMapper();
		  HashMap<String, Object> map = new HashMap<>();
		  try {
			  ExcelImportResult<SgtzXyk> importResult = ExcelImportUtil.importExcelVerify(file, SgtzXyk.class,SgtzXykVO.class, params);
			  List<SgtzXyk> list = importResult.getList();
			  List<SgtzXyk> qkmbList = new ArrayList<>();
			  for (SgtzXyk ywzkb : list) {
				  SgtzXyk sgtzXyk = new SgtzXyk();
				  ywzkb.setSjrq(parse);
				  try {
					  Class<?> aClass = ywzkb.getClass();
					  Field[] declaredFields = aClass.getDeclaredFields();
					  for (Field declaredField : declaredFields) {
						  Field privateFielld = aClass.getDeclaredField(declaredField.getName());
						  privateFielld.setAccessible(true);
						  //处理字符串类型包含空格
						  if(declaredField.getGenericType().toString().equals("class java.lang.String")){
							  Object o = privateFielld.get(ywzkb);
							  map.put(declaredField.getName(),o);
							  //处理集合value值中去除空格
							  for (Map.Entry<String, Object> entry : map.entrySet()) {
								  Object value = entry.getValue();
								  if (value instanceof String) {
									  String trimmedValue = ((String) value).trim();
									  entry.setValue(trimmedValue);
								  }
							  	}
							  }
						  Object o = privateFielld.get(ywzkb);
						  map.put(declaredField.getName(),o);

					  }
				  } catch (Exception e) {
					  e.printStackTrace();
				  }
				  SgtzXyk user = objectMapper.convertValue(map, SgtzXyk.class);
				  BeanUtil.copyProperties(user,sgtzXyk);
				  qkmbList.add(sgtzXyk);
			  }
			  service.saveBatch(qkmbList);
			  obj.put("filePath", filePath);
			  fos = new FileOutputStream(baseFilePath);
			  importResult.getWorkbook().write(fos);
			  fos.flush();
			  fos.close();
			  return Result.ok("文件导入完成！成功导入数据行数:" + list.size(), obj);
		  } catch (Exception e) {
			  log.error(e.getMessage(),e);
			  return Result.error("文件导入失败:"+e.getMessage());
		  } finally {
			  IoUtil.close(fos);
		  }
	  }
	  return Result.ok("文件导入失败！");
  }
	 /**
	  * 首字母转大写
	  * @param s
	  * @return
	  */
	 public static String toUpperFirstOne(String s) {
		 if (Character.isUpperCase(s.charAt(0))) {
			 return s;
		 } else {
			 return (new StringBuilder())
					 .append(Character.toUpperCase(s.charAt(0)))
					 .append(s.substring(1))
					 .toString();
		 }
	 }
 }
