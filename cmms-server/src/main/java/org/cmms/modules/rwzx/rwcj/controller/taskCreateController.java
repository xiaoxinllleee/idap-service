package org.cmms.modules.rwzx.rwcj.controller;

import java.math.BigDecimal;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QybmEnum;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;
import org.cmms.modules.hr.yggl.ygxxgl.service.IVhrbasstaffpostService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.rwzx.rwcj.entity.DklshjTaskCreateQuery;
import org.cmms.modules.rwzx.rwcj.entity.TaskBfrwBase;
import org.cmms.modules.rwzx.rwcj.entity.TaskCreate;
import org.cmms.modules.rwzx.rwcj.entity.TaskCreateQuery;
import org.cmms.modules.rwzx.rwcj.service.ITaskBfrwBaseService;
import org.cmms.modules.rwzx.rwcj.service.ITaskRwmxService;
import org.cmms.modules.rwzx.rwcj.service.ItaskCreateService;

import org.cmms.modules.rwzx.zzrw.service.ITaskZzrwsjbRwfpService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysDicService;
import org.jetbrains.annotations.NotNull;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 任务创建
 * @Author: jeecg-boot
 * @Date:   2023-03-31
 * @Version: V1.0
 */
@Slf4j
@Api(tags="任务创建")
@RestController
@RequestMapping("/rwcj/taskCreate")
public class taskCreateController extends JeecgController<TaskCreate, ItaskCreateService> implements Job {
	 @Autowired
	 ITaskBfrwBaseService taskBfrwBaseService;

	 @Autowired
	 private ITaskRwmxService taskRwmxService;

	 @Autowired
	 private IVhrbasstaffpostService hrbasstaffpostService;
	 @Autowired
	 private IHrBasOrganizationService hrBasOrganizationService;
	 @Autowired
	 private ITaskZzrwsjbRwfpService taskZzrwsjbRwfpService;

	/**
	 * 分页列表查询
	 *
	 * @param taskCreate
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "任务创建-分页列表查询")
	@ApiOperation(value="任务创建-分页列表查询", notes="任务创建-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TaskCreate taskCreate,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TaskCreate> queryWrapper = QueryGenerator.initQueryWrapper(taskCreate, req.getParameterMap());
		Page<TaskCreate> page = new Page<TaskCreate>(pageNo, pageSize);
		IPage<TaskCreate> pageList = service.page(page, queryWrapper);

		/*if (pageList.getTotal() > 0l){
			for (int i = 0; i < pageList.getRecords().size(); i++) {
				int rs = taskBfrwBaseService.selectCountByYxid(pageList.getRecords().get(i).getId());
				pageList.getRecords().get(i).setYyx(rs);
				int wyx = taskBfrwBaseService.selectCountByYxid(pageList.getRecords().get(i).getId(),"2");
				pageList.getRecords().get(i).setWyx(wyx);
			}
		}*/

		return Result.ok(pageList);
	}


	 /**
	  * 分页列表查询
	  *
	  * @param taskCreate
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "任务创建-分页列表查询")
	 @ApiOperation(value="任务创建-分页列表查询", notes="任务创建-分页列表查询")
	 @GetMapping(value = "/getWdrwList")
	 public Result<?> getWdrwList(TaskCreate taskCreate,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {

		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@+"+sysUser.getWorkNo());
		 System.out.println(sysUser.getOrgCode());
		 QueryWrapper<TaskCreate> queryWrapper = QueryGenerator.initQueryWrapper(taskCreate, req.getParameterMap());
		 queryWrapper.and(i ->i.eq("fn_split_str_db(DXID,',','"+sysUser.getOrgCode()+"')","1")
				          .or().eq("DXID","1")
				          .or().eq("fn_split_str_db(DXID,',','"+sysUser.getWorkNo()+"')","1")
		 );
		 queryWrapper.orderByDesc("yxj");
		 Page<TaskCreate> page = new Page<TaskCreate>(pageNo, pageSize);
		 IPage<TaskCreate> pageList = service.page(page, queryWrapper);
		/* List<TaskCreate>  taskCreateList =new LinkedList<>();
		 if (pageList.getTotal() > 0l){
			 for (int i = 0; i < pageList.getRecords().size(); i++) {
				 TaskCreate taskCreateNew = pageList.getRecords().get(i);
				 String dxid = taskCreateNew.getDxid();
				 if("1".equals(dxid)){
					 taskCreateList.add(taskCreateNew);
				 }else{
					 String[] split = dxid.split(",");
					 for(String dxidval:split){
					 	if(dxidval.equals(sysUser.getOrgCode())){
							taskCreateList.add(taskCreateNew);
						}
					 }
				 }
			 }
		 }
		 pageList.setRecords(taskCreateList);
		 pageList.SET*/
		 return Result.ok(pageList);
	 }

	 @GetMapping(value = "/listNhxq")
	 public Result<?> listNhxq(TaskCreateQuery taskCreate,
							 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
							 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
							 HttpServletRequest req) {
		 System.out.println(taskCreate.toString());
		 Page<Nhxq> page = new Page<Nhxq>(pageNo, pageSize);
		 IPage<Nhxq> pageList = service.getPageTaskList(page, taskCreate);
		 return Result.ok(pageList);
	 }
	
	/**
	 * 添加
	 *
	 * @param taskCreate
	 * @return
	 */
	@AutoLog(value = "任务创建-添加")
	@ApiOperation(value="任务创建-添加", notes="任务创建-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody @NotNull TaskCreate taskCreate) {
		String id = IdUtil.fastSimpleUUID();
		String qybm= getRedisQydm();
		qybm="420";
		taskCreate.setId(id);
		if("1".equals(taskCreate.getRwlx())){
			DklshjTaskCreateQuery dklshjTaskCreateQuery =taskCreate.getDklshjTaskCreateQuery();
			dklshjTaskCreateQuery.setId(id);
			int i = service.insertDklshInfo(dklshjTaskCreateQuery);
			taskCreate.setRs(new BigDecimal(i));
		}else if("2".equals(taskCreate.getRwlx())){
			TaskCreateQuery taskCreateQuery = taskCreate.getTaskCreateQuery();
			taskCreateQuery.setId(id);
			taskCreateQuery.setRwlx(taskCreate.getRwlx());
			int i = service.insertNhxxInfo(taskCreateQuery);
			taskCreate.setRs(new BigDecimal(i));
		}else if("3".equals(taskCreate.getRwlx())){
			TaskCreateQuery taskCreateQuery = taskCreate.getTaskCreateQuery();
			taskCreateQuery.setId(id);
			if(taskCreate.getDxlx().equals("0")){
				taskCreate.setDxid("1");
			}
			taskCreateQuery.setRwlx(taskCreate.getRwlx());
			int i = service.insertZzrwInfo(taskCreateQuery);
			taskCreate.setRs(new BigDecimal(i));
			//蓝山-分配任务到管户人
			if (StringUtils.isNotBlank(qybm) && QybmEnum.LANSHAN.getQybm().equals(qybm) && taskCreate.getDxlx().equals("0")){
				service.initRwpfLs(id);
			}
		}
		service.save(taskCreate);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param taskCreate
	 * @return
	 */
	@AutoLog(value = "任务创建-编辑")
	@ApiOperation(value="任务创建-编辑", notes="任务创建-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TaskCreate taskCreate) {
		service.updateById(taskCreate);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "任务创建-通过id删除")
	@ApiOperation(value="任务创建-通过id删除", notes="任务创建-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		QueryWrapper queryWrapper =new QueryWrapper();
		queryWrapper.eq("rwid",id);
		taskRwmxService.remove(queryWrapper);
		service.removeById(id);
		taskZzrwsjbRwfpService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "任务创建-批量删除")
	@ApiOperation(value="任务创建-批量删除", notes="任务创建-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "任务创建-通过id查询")
	@ApiOperation(value="任务创建-通过id查询", notes="任务创建-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TaskCreate taskCreate = service.getById(id);
		return Result.ok(taskCreate);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param taskCreate
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, TaskCreate taskCreate) {
      return super.exportXls(request, taskCreate, TaskCreate.class, "任务创建");
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
      return super.importExcel(request, response, TaskCreate.class);
  }

	 @Override
	 public void execute(JobExecutionContext context) throws JobExecutionException {
		 QueryWrapper<TaskCreate> queryWrapper =new QueryWrapper();
		 queryWrapper.eq("rwlx",3);
		 List<TaskCreate> list = service.list(queryWrapper);
		 log.info("======================开始自主任务定时回收派发==================");
		 for(TaskCreate taskCreate:list){
		 	//1.任务回收
		 	 log.info("======================1.开始任务回收=================="+taskCreate.getTitle()+"-"+taskCreate.getId());
		 	 service.initRwsh(taskCreate.getId());
		 	 log.info("======================1.完成任务回收=================="+taskCreate.getTitle()+"-"+taskCreate.getId());
			 String dxlx = taskCreate.getDxlx();
			 if(dxlx.equals("1")){
				 if(taskCreate.getDxid().trim().equals("1")){
					 List<HrBasOrganization> list1 = hrBasOrganizationService.list();
					 for (HrBasOrganization hrBasOrganization:list1){
						 List<Vhrbasstaffpost> vhrbasstaffposts = hrbasstaffpostService.geYgxxByZzbz(hrBasOrganization.getZzbz());
						 for(Vhrbasstaffpost ygxx :vhrbasstaffposts){
							 String yggh=ygxx.getYggh();
							 log.info("======================2.开始任务派发=================="+ygxx.getYgxm()+"-"+ygxx.getYggh());
							 service.initRwpf(taskCreate.getId(),yggh);
							 log.info("======================2.完成任务派发=================="+ygxx.getYgxm()+"-"+ygxx.getYggh());
						 }
					 }

				 }else{
					 String[] zzbzs = taskCreate.getDxid().split(",");
					 for(String zzbz :zzbzs){
						 List<Vhrbasstaffpost> vhrbasstaffposts = hrbasstaffpostService.geYgxxByZzbz(zzbz);
						 for(Vhrbasstaffpost ygxx :vhrbasstaffposts){
							 String yggh=ygxx.getYggh();
							 log.info("======================2.开始任务派发=================="+ygxx.getYgxm()+"-"+ygxx.getYggh());
							 service.initRwpf(taskCreate.getId(),yggh);
							 log.info("======================2.完成任务派发=================="+ygxx.getYgxm()+"-"+ygxx.getYggh());
						 }
					 }
				 }


			 }else if(dxlx.equals("3")){
				 String[] ygghs = taskCreate.getDxid().split(",");
				 for(String yggh :ygghs){
					 log.info("======================2.开始任务派发=================="+yggh);
					 service.initRwpf(taskCreate.getId(),yggh);
					 log.info("======================2.完成任务派发=================="+yggh);
				 }
			 }else if(dxlx.equals("0")){//系统默认
				 List<Vhrbasstaffpost> vhrbasstaffposts = hrbasstaffpostService.list();
				 for(Vhrbasstaffpost ygxx :vhrbasstaffposts){
					 String yggh=ygxx.getYggh();
					 log.info("======================2.开始任务派发=================="+ygxx.getYgxm()+"-"+ygxx.getYggh());
					 service.initRwpf(taskCreate.getId(),yggh);
					 log.info("======================2.完成任务派发=================="+ygxx.getYgxm()+"-"+ygxx.getYggh());
				 }
			 }
		 }
	 }
 }
