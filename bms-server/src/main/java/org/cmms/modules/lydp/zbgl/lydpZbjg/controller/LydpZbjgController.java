package org.cmms.modules.lydp.zbgl.lydpZbjg.controller;

import java.io.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.DateUtils;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.common.async.service.FtpClientManager;
import org.cmms.modules.lydp.zbgl.log.entity.LydpLog;
import org.cmms.modules.lydp.zbgl.log.service.ILydpLogService;
import org.cmms.modules.lydp.zbgl.lydPzbsjx.entity.LydpZbsjx;
import org.cmms.modules.lydp.zbgl.lydPzbsjx.service.ILydpZbsjxService;
import org.cmms.modules.lydp.zbgl.lydpZbjg.entity.LydpZbjg;
import org.cmms.modules.lydp.zbgl.lydpZbjg.service.ILydpZbjgService;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.modules.lydp.zbgl.lydpZbtqrzcx.entity.LydpZbtqrzcx;
import org.cmms.modules.lydp.zbgl.lydpZbtqrzcx.service.ILydpZbtqrzcxService;
import org.cmms.modules.report.util.ReportDateDin;
import org.cmms.modules.sgtz.sjtb.service.IEtlSgtzSjtbService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.system.service.ISysLogService;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 浏阳大屏指标结果表
 * @Author: jeecg-boot
 * @Date:   2023-02-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags="浏阳大屏指标结果表")
@RestController
@RequestMapping("/lydpZbjg/lydpZbjg")
public class LydpZbjgController implements Job {
	@Autowired
	private ILydpZbjgService lydpZbjgService;
	 @Value("${com.etl.sfdsjpt}")
	 private String sfdsjpt;
	 @Autowired
	 private ISysDictService iSysDictService;
	 @Autowired
	 private ILydpZbsjxService lydpZbsjxService;
	 @Autowired
	 private ILydpZbtqrzcxService lydpZbzxrzService;
	 @Autowired
	 IEtlSgtzSjtbService etlSgtzSjtbService;
	 @Value("${com.etl.etlName:数据下发ETL任务}")
	 private  String etlName;

	 @Value("${com.etl.dagName:etl_day调度}")
	 private  String dagName;
	 @Autowired
	 ISysLogService sysLogService;
	 @Autowired
	 ILydpZbtqrzcxService lydpZbtqrzcxService;
	 @Autowired
	 ILydpLogService lydpLogService;
	 //服务器IP 10.18.10.90
	 @Value("${common.ftp.ip:10.18.10.90}")
	 private String ip;
	 //服务器IP
	 @Value("${common.ftp.port:21}")
	 private int port;
	 //用户名
	 @Value("${common.ftp.username:dpsj}")
	 private String userName;
	 //密码
	 @Value("${common.ftp.password:12345678}")
	 private String password;

	 @Value(value = "${common.path.upload}")
	 protected String uploadpath;
	/**
	 * 分页列表查询
	 *
	 * @param lydpZbjg
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "浏阳大屏指标结果表-分页列表查询")
	@ApiOperation(value="浏阳大屏指标结果表-分页列表查询", notes="浏阳大屏指标结果表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(LydpZbjg lydpZbjg,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<LydpZbjg> queryWrapper = QueryGenerator.initQueryWrapper(lydpZbjg, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(ILydpZbjgService.class,lydpZbjgService,pageNo,pageSize,queryWrapper,"sjrq","zbid","zblx");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param lydpZbjg
	 * @return
	 */
	@AutoLog(value = "浏阳大屏指标结果表-添加")
	@ApiOperation(value="浏阳大屏指标结果表-添加", notes="浏阳大屏指标结果表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody LydpZbjg lydpZbjg) {
		lydpZbjgService.save(lydpZbjg);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param lydpZbjg
	 * @return
	 */
	@AutoLog(value = "浏阳大屏指标结果表-编辑")
	@ApiOperation(value="浏阳大屏指标结果表-编辑", notes="浏阳大屏指标结果表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody LydpZbjg lydpZbjg) {
		lydpZbjgService.updateById(lydpZbjg);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "浏阳大屏指标结果表-通过id删除")
	@ApiOperation(value="浏阳大屏指标结果表-通过id删除", notes="浏阳大屏指标结果表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		lydpZbjgService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "浏阳大屏指标结果表-批量删除")
	@ApiOperation(value="浏阳大屏指标结果表-批量删除", notes="浏阳大屏指标结果表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.lydpZbjgService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "浏阳大屏指标结果表-通过id查询")
	@ApiOperation(value="浏阳大屏指标结果表-通过id查询", notes="浏阳大屏指标结果表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		LydpZbjg lydpZbjg = lydpZbjgService.getById(id);
		return Result.ok(lydpZbjg);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param lydpZbjg
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, LydpZbjg lydpZbjg) {
	  // Step.1 组装查询条件
	  QueryWrapper<LydpZbjg> queryWrapper = QueryGenerator.initQueryWrapper(lydpZbjg, request.getParameterMap());
	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	  String selections = request.getParameter("selections");
	  String rowKey = request.getParameter("rowKey");

	  if (oConvertUtils.isNotEmpty(selections)) {
		  List<String> selectionList = Arrays.asList(selections.split(","));
		  if(oConvertUtils.isNotEmpty(rowKey)){
			  queryWrapper.in(rowKey,selectionList);
		  }else{
			  queryWrapper.in("ID",selectionList);
		  }
	  }

	  // Step.2 获取导出数据
	  //List<T> pageList = service.list(queryWrapper);
	  //List<T> exportList = null;
	  List<LydpZbjg> exportList = lydpZbjgService.list(queryWrapper);
	  // Step.3 AutoPoi 导出Excel
	  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
	  mv.addObject(NormalExcelConstants.FILE_NAME, "浏阳大屏指标结果表"); //此处设置的filename无效 ,前端会重更新设置一下
	  mv.addObject(NormalExcelConstants.CLASS, LydpZbjg.class);
	  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("浏阳大屏指标结果表" + "报表", "导出人:" + sysUser.getRealname(), "导出信息"));
	  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
	  return mv;
     // return super.exportXls(request, lydpZbjg, LydpZbjg.class, "浏阳大屏指标结果表");
  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
 /* @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, LydpZbjg.class);
  }*/

	 /**
	  * 提取指标结果
	  * @param object
	  * @return
	  */
	 @PutMapping(value = "/init")
	 public Result<?> InitZbxx(@RequestBody JSONObject object) {
		 System.out.println("sjrq-----"+object.getString("sjrq"));
		 try {
			 String zbwd = object.getString("zbwd");
			 String zbid = object.getString("zbid");

			 ReportDateDin reportDate = new ReportDateDin(DateUtil.string2Date(object.getString("sjrq"),"yyyy-MM-dd"),sfdsjpt);

			 String qydm = iSysDictService.queryTableDictTextByKey("SYS_DIC", "VALUE", "CODE", "101001");
			 QueryWrapper<LydpZbjg> queryWrapper=new QueryWrapper();
			 queryWrapper.eq("zbwd", zbwd);
			 queryWrapper.eq("sjrq",reportDate.getDateBq());
			 if(StringUtils.isNotEmpty(zbid)){
				 queryWrapper.eq("zbid",zbid);
			 }
			 lydpZbjgService.remove(queryWrapper);
			 QueryWrapper<LydpZbtqrzcx> queryWrapperLog=new QueryWrapper();
			 queryWrapperLog.eq("zbwd", zbwd);
			 queryWrapperLog.eq("sjrq",reportDate.getDateBq());
			 if(StringUtils.isNotEmpty(zbid)){
				 queryWrapperLog.eq("zbid",zbid);
			 }
			 lydpZbzxrzService.remove(queryWrapperLog);

             //普通指标
             List<LydpZbsjx> listByQydmPtzb = lydpZbsjxService.getListByQydm(qydm, "1", zbwd,zbid);
             List<LydpZbsjx> listByQydmDwzb = lydpZbsjxService.getListByQydm(qydm, "2", zbwd,zbid);
             List<LydpZbsjx> sqlListPtzb= new LinkedList<>();
             List<LydpZbsjx> sqlListDwzb= new LinkedList<>();
             for(LydpZbsjx zbsjxgl :listByQydmPtzb){
                 try {
                     String sql =reportDate.replaceStringVal(zbsjxgl.getJssql(),zbsjxgl.getZbwd());
                     log.info("================待执行的单值指标sql========"+sql);
                     zbsjxgl.setJssql(sql);
                     sqlListPtzb.add(zbsjxgl);

                 }catch (Exception e){
                     log.error("-------------替换sql失败："+e.getMessage());
                 }

             }
             for(LydpZbsjx zbsjxgl :listByQydmDwzb){
                 try {
                     String sql =reportDate.replaceStringVal(zbsjxgl.getJssql(),zbsjxgl.getZbwd());
                     log.info("================待执行的多维指标sql========"+sql);
                     zbsjxgl.setJssql(sql);
                     sqlListDwzb.add(zbsjxgl);
                 }catch (Exception e){
                     log.error("-------------替换sql失败："+e.getMessage());
                 }


             }
             log.info("================开始执行==================");
             if(sqlListPtzb!=null&&sqlListPtzb.size()>0){
				 String ptzb = lydpZbjgService.executePcSql(sqlListPtzb, DateUtil.string2Date(object.getString("sjrq"), "yyyy-MM-dd"));
             }

             if(sqlListDwzb!=null&&sqlListDwzb.size()>0){
				 String dwzb = lydpZbjgService.executeDwzbSql(sqlListDwzb, DateUtil.string2Date(object.getString("sjrq"), "yyyy-MM-dd"), sfdsjpt);
             }


			 return Result.ok("提取成功！");
		 } catch (Exception e) {
			 log.error(e.getMessage(), "提取失败！");
			 return Result.error(e.getMessage());
		 }
	 }

	 /**
	  *  每天定时跑提取指标：
	  * 	新增一个日历表：记录跑的数据日期
	  * 	通过查询日历表来判断是否提取指标（定时任务（每天一次就行））：
	  * 	1.比如说日历表中存在202301的记录 则定时任务去跑 202302的指标。跑的时候判断2月份的数据入库是否完成了，完成了的话开始跑，没有的话跳过当前任务。
	  *
	  * 	   指标：月指标和年指标。
	  * @param jobExecutionContext
	  * @throws JobExecutionException
	  */
	 @Override
	 public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		 log.info(String.format("浏阳大屏指标提取时间-"+ DateUtils.getTimestamp()));
		 //入库日期
		 String zdrkrq = "";
		 if ("true".equalsIgnoreCase(sfdsjpt)) {
			 zdrkrq = etlSgtzSjtbService.getZdrkrq(etlName == null ? "数据下发ETL任务" : etlName, dagName == null ? "etl_day调度" : dagName);
			 log.info("最大入库日期："+zdrkrq);
		 }else {
			 Date ckrkrq = DateUtil.string2Date(sysLogService.cksjrkrq(),"yyyyMMdd");
			 Date dkrkrq = DateUtil.string2Date(sysLogService.dksjrkrq(),"yyyyMMdd");
			 Date rkrq = (ckrkrq.before(dkrkrq)?ckrkrq:dkrkrq);
			 zdrkrq = DateUtil.format(rkrq, DatePattern.NORM_DATE_PATTERN);
			 log.info("最大入库日期："+zdrkrq);
		 }
		 String sjrq = cn.hutool.core.date.DateUtil.format(lydpLogService.dateMax(), "yyyy-MM-dd");
		 log.info("大屏日志表日期-"+sjrq);
		 List<CompletableFuture<String>> isdoneList= new ArrayList();
		 try {
			 //存在就执行下一天
			 Date dzxrq = cn.hutool.core.date.DateUtil.offsetDay(cn.hutool.core.date.DateUtil.parse(sjrq), 1);
			 log.info("待执行实际日期-"+DateUtil.getDateString(dzxrq));

			 // DateTime dateTime1 = cn.hutool.core.date.DateUtil.endOfMonth(dateTime2);
			// DateTime offset = cn.hutool.core.date.DateUtil.beginOfDay(dateTime1);
			 //log.info("===下个月日期==="+ offset);

			 //把入库日期转为date
			 DateTime dateTime = cn.hutool.core.date.DateUtil.parse(zdrkrq);
			 DateTime parse = cn.hutool.core.date.DateUtil.parse(sjrq);
			 ReportDateDin reportDate = new ReportDateDin(dzxrq,sfdsjpt);
			 //待执行日期小于入库日期就继续跑
			 if (parse.before(dateTime)) {
				 String qydm = iSysDictService.queryTableDictTextByKey("SYS_DIC", "VALUE", "CODE", "101001");


				 //普通指标
				 List<LydpZbsjx> listByQydmPtzb = lydpZbsjxService.getListByQydm(qydm,"1");
				 List<LydpZbsjx> listByQydmDwzb = lydpZbsjxService.getListByQydm(qydm,"2");
				 List<LydpZbsjx> sqlListPtzb= new LinkedList<>();
				 List<LydpZbsjx> sqlListDwzb= new LinkedList<>();
				 for(LydpZbsjx zbsjxgl :listByQydmPtzb){
					 try {
						 String sql =reportDate.replaceStringVal(zbsjxgl.getJssql(),zbsjxgl.getZbwd());
						 zbsjxgl.setJssql(sql);
						 sqlListPtzb.add(zbsjxgl);
					 }catch (Exception e){
						 log.error("-------------替换sql失败："+e.getMessage());
					 }

				 }
				 for(LydpZbsjx zbsjxgl :listByQydmDwzb){
					 try {
						 String sql =reportDate.replaceStringVal(zbsjxgl.getJssql(),zbsjxgl.getZbwd());
						 zbsjxgl.setJssql(sql);
						 sqlListDwzb.add(zbsjxgl);
					 }catch (Exception e){
						 log.error("-------------替换sql失败："+e.getMessage());
					 }



				 }

				 log.info("================开始执行==================");
				 if (sqlListPtzb != null && sqlListPtzb.size() > 0) {
					 QueryWrapper<LydpZbjg> queryWrapper=new QueryWrapper();
					 queryWrapper.eq("sjrq",dzxrq);
					 queryWrapper.eq("zblx",1);
					 lydpZbjgService.remove(queryWrapper);
					 String ptzb = lydpZbjgService.executePcSql(sqlListPtzb, dzxrq);
				 }

				 if (sqlListDwzb != null && sqlListDwzb.size() > 0) {
					 String dwzb = lydpZbjgService.executeDwzbSql(sqlListDwzb,dzxrq, sfdsjpt);
			 }


				 QueryWrapper<LydpZbtqrzcx> queryWrapperLog=new QueryWrapper();
				 queryWrapperLog.eq("sjrq",dzxrq);
				 lydpZbzxrzService.remove(queryWrapperLog);


				 QueryWrapper<LydpZbtqrzcx> queryWrapperLog2=new QueryWrapper();
				 queryWrapperLog2.eq("sjrq",dzxrq);
				 queryWrapperLog2.eq("estat","2");

				 QueryWrapper<LydpZbtqrzcx> queryWrapperLog1=new QueryWrapper();
				 queryWrapperLog1.eq("sjrq",dzxrq);
				 queryWrapperLog1.eq("estat","1");

				 List<LydpZbtqrzcx> list = lydpZbzxrzService.list(queryWrapperLog2);
				 List<LydpZbtqrzcx> list1 = lydpZbzxrzService.list(queryWrapperLog1);
				 String msg="失败指标数："+list.size()+",成功指标数："+list1.size();
				 if (list == null || list.size() == 0) {

					 QueryWrapper queryWrapperZt=new QueryWrapper();
					 queryWrapperZt.eq("sjrq",dzxrq);
					 LydpLog one = lydpLogService.getOne(queryWrapperZt, false);
					 if(one ==null){
						 LydpLog lydpZbtqrzcx = new LydpLog();
						 lydpZbtqrzcx.setSjrq(dzxrq);  //20230228
						 lydpZbtqrzcx.setZt("1");//成功
						 lydpLogService.save(lydpZbtqrzcx);
					 }else{
						 one.setZt("1");
						 lydpLogService.update(one,queryWrapperZt);
					 }



					 QueryWrapper<LydpZbjg> queryWrapper1 = new QueryWrapper();
					 queryWrapper1.eq("sjrq", dzxrq);
					 List<LydpZbjg> list2 = lydpZbjgService.list(queryWrapper1);
					 //日期文件夹
					 String rqwj = uploadpath + File.separator + DateUtil.getDateString(dzxrq).replaceAll("-", "");
					 String rq =  DateUtil.getDateString(dzxrq).replaceAll("-", "");
					 //上传文件
					 String ftpPath = uploadpath + File.separator + DateUtil.getDateString(dzxrq).replaceAll("-", "") +File.separator+ "020-BIG_SCREEN_DATA-" + DateUtil.getDateString(dzxrq).replaceAll("-", "") + ".txt";
					 String ok = uploadpath + File.separator + DateUtil.getDateString(dzxrq).replaceAll("-", "") +File.separator+ "020-" + DateUtil.getDateString(dzxrq).replaceAll("-", "") +"-OK"+ ".txt";
					 File file = new File(ftpPath);
					 File file2 = new File(rqwj);
					 File file3 = new File(ok);
					 if (!file2.exists()) {
						 file2.mkdirs();
						 boolean file1 = file.createNewFile();
						 boolean okwj = file3.createNewFile();
						 if (!file1){
							 log.info("创建失败");
						 }
						 if (!okwj){
							 log.info("创建失败");
						 }
					 }

					 BufferedWriter bw;
					 try {
						 bw = new BufferedWriter(new FileWriter(file));
						 if (CollUtil.isNotEmpty(list2)){
							 for (int j = 0; j < list2.size(); j++) {
								 String date = cn.hutool.core.date.DateUtil.format(list2.get(j).getSjrq(), "yyyyMMdd");
								 String createTime = cn.hutool.core.date.DateUtil.format(list2.get(j).getCreateTime(), "yyyy-MM-dd");
								 String str = "\""+date +"\""+"@|@"+"\""+list2.get(j).getZbwd() +"\""+"@|@"+"\""+list2.get(j).getZbid() +"\""+"@|@"+"\""+list2.get(j).getZbmc() +"\""+"@|@"+"\""+list2.get(j).getZbjg() +"\""+"@|@"+"\""+createTime +"\"";
								 bw.write(str);
								 bw.newLine();//换行
								 bw.flush();
							 }
							 bw.close();
						 }
					 } catch (IOException e) {
						 e.printStackTrace();
						 log.info(e.getMessage());
					 }
					 FtpClientManager.initClient(rq,ftpPath,ok,ip,port, userName, password);
				 } else {
					 QueryWrapper queryWrapperZt=new QueryWrapper();
					 queryWrapperZt.eq("sjrq",dzxrq);
					 LydpLog one = lydpLogService.getOne(queryWrapperZt, false);
					 if(one ==null){
						 LydpLog lydpZbtqrzcx = new LydpLog();
						 lydpZbtqrzcx.setSjrq(dzxrq);  //20230228
						 lydpZbtqrzcx.setZt("2");//成功
						 lydpZbtqrzcx.setMsg(msg);

						 lydpLogService.save(lydpZbtqrzcx);
					 }else{
						 one.setZt("2");
						 one.setMsg(msg);
						 lydpLogService.update(one,queryWrapperZt);
					 }
				 }

			 }else {
				/* QueryWrapper<LydpLog> queryWrapperLog=new QueryWrapper();
				 queryWrapperLog.eq("sjrq",reportDate.getDateBq());
				 lydpLogService.remove(queryWrapperLog);*/
				 log.info("================当日入库日期未完成==================");
			 }


		 } catch (Exception e) {
			 log.error(e.getMessage(), "提取失败！");
		 }



	 }
 }
