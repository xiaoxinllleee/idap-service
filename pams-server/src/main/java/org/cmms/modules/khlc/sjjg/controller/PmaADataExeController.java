package org.cmms.modules.khlc.sjjg.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khlc.sjjg.entity.PmaADataExe;
import org.cmms.modules.khlc.sjjg.entity.PmaADataExeLog;
import org.cmms.modules.khlc.sjjg.entity.PmaADataExeVO;
import org.cmms.modules.khlc.sjjg.service.IPmaADataExeLogService;
import org.cmms.modules.khlc.sjjg.service.IPmaADataExeService;
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
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 数据加工功能
 * @Author: jeecg-boot
 * @Date:   2023-03-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="数据加工功能")
@RestController
@RequestMapping("/sjjg/pmaADataExe")
public class PmaADataExeController extends JeecgController<PmaADataExe, IPmaADataExeService>  {
	 @Autowired
	 private IPmaADataExeService pmaADataExeService;

	 @Autowired
	 private IPmaADataExeLogService pmaADataExeLogService;
	/**
	 * 分页列表查询
	 *
	 * @param pmaADataExe
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "数据加工功能-分页列表查询")
	@ApiOperation(value="数据加工功能-分页列表查询", notes="数据加工功能-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PmaADataExe pmaADataExe,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PmaADataExe> queryWrapper = QueryGenerator.initQueryWrapper(pmaADataExe, req.getParameterMap());
		Page<PmaADataExe> page = new Page<PmaADataExe>(pageNo, pageSize);
		queryWrapper.orderByAsc("zxsx");
		IPage<PmaADataExe> pageList = pmaADataExeService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param pmaADataExe
	 * @return
	 */
	@AutoLog(value = "数据加工功能-添加")
	@ApiOperation(value="数据加工功能-添加", notes="数据加工功能-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PmaADataExe pmaADataExe) {
		pmaADataExeService.save(pmaADataExe);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param pmaADataExe
	 * @return
	 */
	@AutoLog(value = "数据加工功能-编辑")
	@ApiOperation(value="数据加工功能-编辑", notes="数据加工功能-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PmaADataExe pmaADataExe) {
		pmaADataExeService.updateById(pmaADataExe);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "数据加工功能-通过id删除")
	@ApiOperation(value="数据加工功能-通过id删除", notes="数据加工功能-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		pmaADataExeService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "数据加工功能-批量删除")
	@ApiOperation(value="数据加工功能-批量删除", notes="数据加工功能-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pmaADataExeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "数据加工功能-通过id查询")
	@ApiOperation(value="数据加工功能-通过id查询", notes="数据加工功能-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PmaADataExe pmaADataExe = pmaADataExeService.getById(id);
		return Result.ok(pmaADataExe);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param pmaADataExe
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, PmaADataExe pmaADataExe) {
      return super.exportXls(request, pmaADataExe, PmaADataExe.class, "数据加工功能");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "数据加工功能导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, PmaADataExeVO.class);
		 ExportParams exportParams = new ExportParams("数据加工功能导入模板", "模板信息");
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
	  String filePaths = jsonObject.getString("filePaths");
	  if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
		  return Result.error("请先上传文件！");
	  }
	  String[] filePathList = filePaths.split(",");
	  JSONObject obj = new JSONObject();
	  for (String filePath : filePathList) {
		  String baseFilePath = uploadpath + File.separator + filePath;
//          MultipartFile file = entity.getValue();// 获取上传文件对象
		  File file = new File(baseFilePath);
		  ImportParams params = new ImportParams();
		  params.setTitleRows(1);
		  params.setHeadRows(1);
		  params.setNeedSave(false);
		  FileOutputStream fos = null;
		  try {
			  ExcelImportResult<PmaADataExe> importResult = ExcelImportUtil.importExcelVerify(file, PmaADataExe.class,PmaADataExeVO.class, params);
			  List<PmaADataExe> list = importResult.getList();
			  List<PmaADataExe> pmaADataExes = new ArrayList<>();
			  for (PmaADataExe pmaADataExe : list) {
				  pmaADataExe.setId(UUIDGenerator.generate());
				  pmaADataExe.setCreateBy(getRealname());
				  pmaADataExe.setCreateTime(new Date());
				  pmaADataExes.add(pmaADataExe);
			  }
			  service.saveBatch(pmaADataExes);
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
 /*public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, PmaADataExe.class);
  }*/

	 /**
	  * 冻结&解冻用户
	  * @param jsonObject
	  * @return
	  */
	 @RequestMapping(value = "/sfqyBatch", method = RequestMethod.PUT)
	 public Result<PmaADataExe> sfqyBatch(@RequestBody JSONObject jsonObject) {
		 Result<PmaADataExe> result = new Result<PmaADataExe>();
		 try {
			 String id = jsonObject.getString("id");
			 String sfqy = jsonObject.getString("sfqy");
			 if(oConvertUtils.isNotEmpty(id)) {
				 pmaADataExeService.update(new PmaADataExe().setSfqy(sfqy), new UpdateWrapper<PmaADataExe>().lambda().eq(PmaADataExe::getId,id));
			 }
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 result.error500("操作失败"+e.getMessage());
		 }
		 result.success("操作成功!");
		 return result;
	 }

	 @RequestMapping(value = "/extractBatchs", method = RequestMethod.PUT)
	 public Result<PmaADataExe> extractBatchs(@RequestBody JSONObject jsonObject) {
		 Result<PmaADataExe> result= new Result<>();
		 String ksrq = jsonObject.getString("ksrq");
		 String jsrq = jsonObject.getString("jsrq");
		 JSONArray jsonArray = jsonObject.getJSONArray("rwid");
		 List<String> rwids = jsonArray.toJavaList(String.class);
		 QueryWrapper<PmaADataExe> queryWrapper=new QueryWrapper<>();
		 queryWrapper.in("id",rwids);
		 queryWrapper.orderByAsc("zxsx");
		 List<PmaADataExe> list = pmaADataExeService.list(queryWrapper);
		 for(PmaADataExe  pmaADataExe:list ){
			 JSONObject extractJson =new JSONObject();
			 extractJson.put("ksrq",ksrq);
			 extractJson.put("jsrq",jsrq);
			 extractJson.put("rwid",pmaADataExe.getId());
			 Result<PmaADataExe> pmaADataExeResult = extractBatch(extractJson);
			 if(!pmaADataExeResult.isSuccess()){
			 	return pmaADataExeResult;
			 }
		 }
		 return result;
	 }
	 /**
	  * 提取绩效数据
	  * @param jsonObject
	  * @return
	  */
	 @RequestMapping(value = "/extractBatch", method = RequestMethod.PUT)
	 public Result<PmaADataExe> extractBatch(@RequestBody JSONObject jsonObject) {
		 Result<PmaADataExe> result= new Result<>();
		 boolean flag=true;
		 try {
			 String ksrq = jsonObject.getString("ksrq");
			 String jsrq = jsonObject.getString("jsrq");
			 String rwid = jsonObject.getString("rwid");

			 String maxTjyfByRwid = pmaADataExeLogService.getMaxTjyfByRwid(rwid);
			 //先查询顺序在前面的任务的最大成功日期
			 QueryWrapper<PmaADataExe> queryWrapper=new QueryWrapper<>();
			 queryWrapper.eq("id",rwid);
			 PmaADataExe pmaADataExe = pmaADataExeService.getOne(queryWrapper);
			 pmaADataExe.setDqzxzt("1");
			 pmaADataExe.setZdcgrq(DateUtil.parseDateFormat(maxTjyfByRwid,"yyyyMMdd"));
			 pmaADataExeService.saveOrUpdate(pmaADataExe,queryWrapper);
			 String spname  =pmaADataExe.getJsjb();



			 Integer zxsx =pmaADataExe.getZxsx();
			 //查询前置任务最大成功日期
			 Integer zxzxss = pmaADataExeLogService.getZxzxss();
			 String maxTjyf = pmaADataExeLogService.getMaxTjyf(zxsx);
			 if(maxTjyf==null){
			 	if(zxzxss!=pmaADataExe.getZxsx()){
					result.setSuccess(false);
					result.setMessage("请先提取前置任务!");
					return result;
				}
			 }
			 //如果是最先执行的任务，则不做前置任务执行时间的判断
			 if(zxzxss==pmaADataExe.getZxsx()){
				 SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
				 Date dateBegin = formater.parse(ksrq);
				 Date dateEnd=formater.parse(jsrq);
				 Calendar ca=Calendar.getInstance();
				 while(dateBegin.compareTo(dateEnd)<=0){
					 ca.setTime(dateBegin);
					 result = doInsertLog(rwid, DateUtil.getDateString(dateBegin), spname);
					 ca.add(ca.DATE,1);//把dateBegin加上1天然后重新赋值给date1
					 dateBegin=ca.getTime();
				 }
				 dateBegin = formater.parse(ksrq);
				 while(dateBegin.compareTo(dateEnd)<=0){
					 ca.setTime(dateBegin);
					 result = doIint(rwid, DateUtil.getDateString(dateBegin), spname);
					 if(!result.isSuccess()){
						 flag=false;
					 }
					 ca.add(ca.DATE,1);//把dateBegin加上1天然后重新赋值给date1
					 dateBegin=ca.getTime();
				 }
				 if(flag){
					 pmaADataExe.setDqzxzt("2");
				 }else{
					 pmaADataExe.setDqzxzt("3");
				 }
				 maxTjyfByRwid = pmaADataExeLogService.getMaxTjyfByRwid(rwid);
				 pmaADataExe.setZdcgrq(DateUtil.parseDateFormat(maxTjyfByRwid,"yyyyMMdd"));
				 pmaADataExeService.saveOrUpdate(pmaADataExe,queryWrapper);
			 }else{
				 System.out.println("maxTjyf:"+maxTjyf);
				 //判断是不是大于或等于当前时间，是的话执行， 不是的话提示：提取时间不能大于前置任务的最大成功日期
				 SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
				 Date zdsjrq=formater.parse(maxTjyf);
				 Date dateBegin = formater.parse(ksrq);
				 Date dateEnd=formater.parse(jsrq);
				 Calendar ca=Calendar.getInstance();
				 while(dateBegin.compareTo(dateEnd)<=0){
					 ca.setTime(dateBegin);
					 if(zdsjrq.compareTo(dateBegin)>=0){
						 result = doInsertLog(rwid, DateUtil.getDateString(dateBegin), spname);
					 }
					 ca.add(ca.DATE,1);//把dateBegin加上1天然后重新赋值给date1
					 dateBegin=ca.getTime();
				 }
				 dateBegin = formater.parse(ksrq);
				 while(dateBegin.compareTo(dateEnd)<=0){
					 ca.setTime(dateBegin);
					 if(zdsjrq.compareTo(dateBegin)>=0){
						 result = doIint(rwid, DateUtil.getDateString(dateBegin), spname);
						 if(!result.isSuccess()){
							 flag=false;
							 break;
						 }
					 }
					 ca.add(ca.DATE,1);//把dateBegin加上1天然后重新赋值给date1
					 dateBegin=ca.getTime();
				 }

				 if(flag){
					 pmaADataExe.setDqzxzt("2");
				 }else{
					 pmaADataExe.setDqzxzt("3");
				 }
				 maxTjyfByRwid = pmaADataExeLogService.getMaxTjyfByRwid(rwid);
				 pmaADataExe.setZdcgrq(DateUtil.parseDateFormat(maxTjyfByRwid,"yyyyMMdd"));
				 pmaADataExeService.saveOrUpdate(pmaADataExe,queryWrapper);
			 }

		 } catch (ParseException e) {
			 e.printStackTrace();
		 }
		 return result;
	 }

	 public Result<PmaADataExe> doInsertLog(String rwid,String sjrq,String spname) {
		 Result<PmaADataExe> result = new Result<PmaADataExe>();
		 QueryWrapper queryWrapper= new QueryWrapper();
		 queryWrapper.eq("rwid",rwid);
		 queryWrapper.eq("sjrq",DateUtil.string2Date(sjrq,"yyyyMMdd"));
		 queryWrapper.eq("zt","1");

		 PmaADataExeLog one = pmaADataExeLogService.getOne(queryWrapper);
		 if(one!=null&&"1".equals(one.getZt())){
			 result.setSuccess(false);
			 result.setMessage("当前数据日期["+sjrq+"],任务正在进行中");
			 return result;
		 }

		 QueryWrapper delete= new QueryWrapper();
		 delete.eq("rwid",rwid);
		 delete.eq("sjrq",DateUtil.string2Date(sjrq,"yyyyMMdd"));
		 pmaADataExeLogService.remove(delete);
		 PmaADataExeLog pmaADataExeLog= new PmaADataExeLog();
		 pmaADataExeLog.setSjrq(DateUtil.parseDateFormat(sjrq,"yyyyMMdd"));
		 pmaADataExeLog.setRwid(rwid);
		 pmaADataExeLog.setZt("0");
		 pmaADataExeLogService.saveOrUpdate(pmaADataExeLog);

		 return result;
	 }

	 public Result<PmaADataExe> doIint(String rwid,String sjrq,String spname) {
		 Result<PmaADataExe> result = new Result<PmaADataExe>();

		 QueryWrapper queryWrapper= new QueryWrapper();
		 queryWrapper.eq("rwid",rwid);
		 queryWrapper.eq("sjrq",DateUtil.string2Date(sjrq,"yyyyMMdd"));
		 queryWrapper.eq("zt","1");

		 PmaADataExeLog one = pmaADataExeLogService.getOne(queryWrapper);
		 if(one!=null&&"1".equals(one.getZt())){
			 result.setSuccess(false);
			 result.setMessage("当前数据日期["+sjrq+"],任务正在进行中");
			 return result;
		 }

		 QueryWrapper queryWrapper1= new QueryWrapper();
		 queryWrapper1.eq("rwid",rwid);
		 queryWrapper1.eq("sjrq",DateUtil.string2Date(sjrq,"yyyyMMdd"));
		 PmaADataExeLog pmaADataExeLog = pmaADataExeLogService.getOne(queryWrapper1);
		 Date ksrj=new Date();
		 try {
			 pmaADataExeLog.setKssj(ksrj);
			 pmaADataExeLog.setZt("1");
			 pmaADataExeLogService.saveOrUpdate(pmaADataExeLog);
			 pmaADataExeService.extract(spname,sjrq,rwid);
			 pmaADataExeLog.setZt("2");
			 Date jsrq =new Date();
			 pmaADataExeLog.setJssj(jsrq);
			 pmaADataExeLog.setHs((jsrq.getTime()-ksrj.getTime())/1000);
			 pmaADataExeLogService.saveOrUpdate(pmaADataExeLog);
			 result.setSuccess(true);
		 } catch (Exception e) {
			 Date jsrq =new Date();
			 pmaADataExeLog.setJssj(jsrq);
			 pmaADataExeLog.setHs((jsrq.getTime()-ksrj.getTime())/1000);
			 pmaADataExeLog.setZt("3");
			 String message = e.getMessage();
			 if (StringUtils.isNotEmpty(message) && message.length() > 2000) {
				 message = message.substring(0, 2000);
			 }
			 pmaADataExeLog.setZxxx(message);
			 pmaADataExeLogService.saveOrUpdate(pmaADataExeLog);
			 log.error(e.getMessage(), e);
			 result.setSuccess(false);
			 result.error500("操作失败"+e.getMessage());
		 }
	 	 return result;
	 }

	 public static void main(String[] args) {
		 JSONArray rwids =new JSONArray();
		 rwids.add("1");
		 rwids.add("2");
		 rwids.add("3");
		 List<String> arrayLists = rwids.toJavaList(String.class);
		 for(String a:arrayLists){
		 	System.out.println(a);
		 }

	 }

 }
