package org.cmms.modules.khlc.sjjg.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khlc.sjjg.entity.PmaADataExe;
import org.cmms.modules.khlc.sjjg.entity.PmaADataExeLog;
import org.cmms.modules.khlc.sjjg.service.IPmaADataExeLogService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khlc.sjjg.service.IPmaADataExeService;
import org.cmms.modules.system.entity.DpJdrwgl;
import org.cmms.modules.system.service.ISysLogService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 数据加工日志
 * @Author: jeecg-boot
 * @Date:   2023-03-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="数据加工日志")
@RestController
@RequestMapping("/sjjg/pmaADataExeLog")
public class PmaADataExeLogController extends JeecgController<PmaADataExeLog, IPmaADataExeLogService> implements Job {
	 @Autowired
	 private IPmaADataExeLogService pmaADataExeLogService;
	 @Autowired
	 private IPmaADataExeService pmaADataExeService;
	 @Autowired
	 ISysLogService sysLogService;
	/**
	 * 分页列表查询
	 *
	 * @param pmaADataExeLog
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "数据加工日志-分页列表查询")
	@ApiOperation(value="数据加工日志-分页列表查询", notes="数据加工日志-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PmaADataExeLog pmaADataExeLog,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req,String tjrq_begin,String tjrq_end) {
		log.info("pmaADataExeLog===="+pmaADataExeLog);
		log.info("tjrq_begin===="+tjrq_begin);
		log.info("tjrq_end===="+tjrq_end);
		QueryWrapper<PmaADataExeLog> queryWrapper = QueryGenerator.initQueryWrapper(pmaADataExeLog, req.getParameterMap());
		if (StringUtils.isNotBlank(tjrq_begin) && StringUtils.isNotBlank(tjrq_end)){
			queryWrapper.between("sjrq", cn.hutool.core.date.DateUtil.parse(tjrq_begin), cn.hutool.core.date.DateUtil.parse(tjrq_end));
		}
		queryWrapper.orderByDesc("sjrq");
		Page<PmaADataExeLog> page = new Page<PmaADataExeLog>(pageNo, pageSize);
		IPage<PmaADataExeLog> pageList = pmaADataExeLogService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param pmaADataExeLog
	 * @return
	 */
	@AutoLog(value = "数据加工日志-添加")
	@ApiOperation(value="数据加工日志-添加", notes="数据加工日志-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PmaADataExeLog pmaADataExeLog) {
		pmaADataExeLogService.save(pmaADataExeLog);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param pmaADataExeLog
	 * @return
	 */
	@AutoLog(value = "数据加工日志-编辑")
	@ApiOperation(value="数据加工日志-编辑", notes="数据加工日志-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PmaADataExeLog pmaADataExeLog) {
		pmaADataExeLogService.updateById(pmaADataExeLog);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "数据加工日志-通过id删除")
	@ApiOperation(value="数据加工日志-通过id删除", notes="数据加工日志-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		pmaADataExeLogService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "数据加工日志-批量删除")
	@ApiOperation(value="数据加工日志-批量删除", notes="数据加工日志-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pmaADataExeLogService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}


	 /**
	  * 提取绩效数据
	  * @param jsonObject
	  * @return
	  */
	 @RequestMapping(value = "/updatezt", method = RequestMethod.PUT)
	 public Result<PmaADataExeLog> updatezt(@RequestBody JSONObject jsonObject) {
		 Result<PmaADataExeLog> result = new Result<PmaADataExeLog>();
		 String tjrq = jsonObject.getString("tjrq").replace("-","");
		 String rwid = jsonObject.getString("rwid");
		 try {
			 QueryWrapper queryWrapper= new QueryWrapper();
			 queryWrapper.eq("rwid",rwid);
			 queryWrapper.eq("sjrq",DateUtil.string2Date(tjrq,"yyyyMMdd"));
			 queryWrapper.eq("zt","1");
			 PmaADataExeLog one = pmaADataExeLogService.getOne(queryWrapper);
			 if(one!=null&&"1".equals(one.getZt())){
				 result.setSuccess(false);
				 result.setMessage("执行失败,当前数据日期["+tjrq+"],任务正在进行中!");
				 return result;
			 }

			 QueryWrapper update= new QueryWrapper();
			 update.eq("rwid",rwid);
			 update.eq("sjrq", DateUtil.string2Date(tjrq,"yyyyMMdd"));
			 pmaADataExeLogService.remove(update);
			 PmaADataExeLog pmaADataExeLog =new PmaADataExeLog();
			 Date ksrj=new Date();
			 pmaADataExeLog.setRwid(rwid);
			 pmaADataExeLog.setSjrq(DateUtil.string2Date(tjrq,"yyyyMMdd"));
			 pmaADataExeLog.setKssj(ksrj);
			 pmaADataExeLog.setZt("1");
			 pmaADataExeLogService.save(pmaADataExeLog);
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 result.error500("操作失败"+e.getMessage());
		 }
		 result.success("操作成功!");
		 return result;
	 }

	 /**
	  * 提取绩效数据
	  * @param jsonObject
	  * @return
	  */
	 @RequestMapping(value = "/extract", method = RequestMethod.PUT)
	 public Result<PmaADataExeLog> extract(@RequestBody JSONObject jsonObject) {
		 Result<PmaADataExeLog> result = new Result<PmaADataExeLog>();
		 String tjrq = jsonObject.getString("tjrq").replace("-","");
		 String rwid = jsonObject.getString("rwid");
		 String maxTjyfByRwid = pmaADataExeLogService.getMaxTjyfByRwid(rwid);
		 //先查询顺序在前面的任务的最大成功日期
		 QueryWrapper<PmaADataExe> queryWrapper=new QueryWrapper<>();
		 queryWrapper.eq("id",rwid);
		 PmaADataExe pmaADataExe = pmaADataExeService.getOne(queryWrapper);
		 //更新主任务表
		 pmaADataExe.setDqzxzt("1");
		 pmaADataExe.setZdcgrq(DateUtil.parseDateFormat(maxTjyfByRwid,"yyyyMMdd"));
		 pmaADataExeService.saveOrUpdate(pmaADataExe,queryWrapper);
		 String spname = pmaADataExe.getJsjb();

		 QueryWrapper update= new QueryWrapper();
		 update.eq("rwid",rwid);
		 update.eq("sjrq", DateUtil.string2Date(tjrq,"yyyyMMdd"));
		 PmaADataExeLog pmaADataExeLog = pmaADataExeLogService.getOne(update);
		 Date ksrj=new Date();
		 try {
			 pmaADataExeService.extract(spname,tjrq,rwid);
			 pmaADataExeLog.setZt("2");
			 Date jsrq =new Date();
			 pmaADataExeLog.setJssj(jsrq);
			 pmaADataExeLog.setHs((jsrq.getTime()-ksrj.getTime())/1000);
			 pmaADataExeLogService.update(pmaADataExeLog,update);

			 //更新主任务表
			 maxTjyfByRwid = pmaADataExeLogService.getMaxTjyfByRwid(rwid);
			 pmaADataExe.setDqzxzt("2");
			 pmaADataExe.setZdcgrq(DateUtil.parseDateFormat(maxTjyfByRwid,"yyyyMMdd"));
			 pmaADataExeService.saveOrUpdate(pmaADataExe,queryWrapper);


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
			 pmaADataExeLogService.update(pmaADataExeLog,update);

			 //更新主任务表
			 maxTjyfByRwid = pmaADataExeLogService.getMaxTjyfByRwid(rwid);
			 pmaADataExe.setDqzxzt("3");
			 pmaADataExe.setZdcgrq(DateUtil.parseDateFormat(maxTjyfByRwid,"yyyyMMdd"));
			 pmaADataExeService.saveOrUpdate(pmaADataExe,queryWrapper);

			 log.error(e.getMessage(), e);
			 result.setSuccess(false);
			 result.error500("操作失败"+e.getMessage());
		 }
		 result.success("操作成功!");
		 return result;
	 }
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "数据加工日志-通过id查询")
	@ApiOperation(value="数据加工日志-通过id查询", notes="数据加工日志-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PmaADataExeLog pmaADataExeLog = pmaADataExeLogService.getById(id);
		return Result.ok(pmaADataExeLog);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param pmaADataExeLog
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, PmaADataExeLog pmaADataExeLog) {
      return super.exportXls(request, pmaADataExeLog, PmaADataExeLog.class, "数据加工日志");
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
      return super.importExcel(request, response, PmaADataExeLog.class);
  }


  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
	  log.info("=============绩效每日定时数据加工开始=============");
	  String maxCgrq = pmaADataExeLogService.getMaxCgrq();
	  Date ckrkrq = DateUtil.string2Date(sysLogService.cksjrkrq(),"yyyyMMdd");
	  Date dkrkrq = DateUtil.string2Date(sysLogService.dksjrkrq(),"yyyyMMdd");
	  Date zdrkrq = (ckrkrq.before(dkrkrq)?ckrkrq:dkrkrq);
	  Date zdcgrq=DateUtil.string2Date(maxCgrq,"yyyyMMdd");
	  //待执行日期等于最大成功日期+1天
	  Date dzxrq = cn.hutool.core.date.DateUtil.offsetDay(zdcgrq, 1);
	  //待执行的数据日期小于等于入库日期才执行
	  if (dzxrq.before(zdrkrq) || dzxrq.equals(zdrkrq)) {
		  QueryWrapper<PmaADataExe> queryWrapper=new QueryWrapper<>();
		  queryWrapper.orderByAsc("zxsx");
		  List<PmaADataExe> list = pmaADataExeService.list(queryWrapper);
		  for(PmaADataExe  pmaADataExe:list ){
			  QueryWrapper<PmaADataExeLog> queryWrapper1= new QueryWrapper();
			  queryWrapper1.eq("rwid",pmaADataExe.getId());
			  queryWrapper1.eq("sjrq",dzxrq);
			  queryWrapper1.in("zt","1","2");
			  PmaADataExeLog one = pmaADataExeLogService.getOne(queryWrapper1,false);
			  if(one==null||StringUtils.isEmpty(one.getZt())||"3".equals(one.getZt())){
				  QueryWrapper update= new QueryWrapper();
				  update.eq("rwid",pmaADataExe.getId());
				  update.eq("sjrq", dzxrq);
				  pmaADataExeLogService.remove(update);
				  PmaADataExeLog pmaADataExeLog =new PmaADataExeLog();
				  Date ksrj=new Date();
				  pmaADataExeLog.setRwid(pmaADataExe.getId());
				  pmaADataExeLog.setSjrq(dzxrq);
				  pmaADataExeLog.setKssj(ksrj);
				  pmaADataExeLog.setZt("1");
				  pmaADataExeLogService.save(pmaADataExeLog);
				  JSONObject extractJson =new JSONObject();
				  extractJson.put("tjrq",DateUtil.format(dzxrq,"yyyyMMdd"));
				  extractJson.put("rwid",pmaADataExe.getId());
				  log.info("=============绩效每日定时数据加工开始=============rwid:"+pmaADataExe.getId()+",tjrq:"+DateUtil.format(dzxrq,"yyyyMMdd"));
				  extract(extractJson);
			  }
		  }
		  log.info("=============绩效每日定时数据加工完成=============");
	  }
  }

 }
