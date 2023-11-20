package org.cmms.modules.rwzx.zfyxmx.controller;

import java.math.BigDecimal;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.exception.JeecgBootException;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.modules.khgl.khhmc.entity.Khfjxxgl;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.khxxgl.khjbzl.entity.Khjbzl;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzxxgl;
import org.cmms.modules.pad.nhxxgl.entity.KhxxglGrsxlxmxNh;
import org.cmms.modules.rwzx.rwcj.entity.TaskCreate;
import org.cmms.modules.rwzx.rwcj.service.ItaskCreateService;
import org.cmms.modules.rwzx.rwmxsj.entity.TaskRwmxDklsh;
import org.cmms.modules.rwzx.rwmxsj.service.ITaskRwmxDklshService;
import org.cmms.modules.rwzx.yxzffjxx.entity.TaskYxzfFjxx;
import org.cmms.modules.rwzx.yxzffjxx.service.ITaskYxzfFjxxService;
import org.cmms.modules.rwzx.zfyxmx.entity.TaskZfyxmx;
import org.cmms.modules.rwzx.zfyxmx.entity.TaskZfyxmxVo;
import org.cmms.modules.rwzx.zfyxmx.service.ITaskZfyxmxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.rwzx.zzrw.entity.TaskZzrwsjbRwfp;
import org.cmms.modules.rwzx.zzrw.service.ITaskZzrwsjbRwfpService;
import org.cmms.modules.sbxj.fxzdfjxx.entity.KhywxxFjxx;
import org.cmms.modules.sbxj.fxzdxjjlb.entity.SbxxFjVO;
import org.cmms.modules.system.aspect.DataFilterAspect;
import org.cmms.modules.util.EtlUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 走访营销明细
 * @Author: jeecg-boot
 * @Date:   2023-07-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags="走访营销明细")
@RestController
@RequestMapping("/zfyxmx/taskZfyxmx")
public class TaskZfyxmxController extends JeecgController<TaskZfyxmx, ITaskZfyxmxService> {
	@Autowired
	private ITaskZfyxmxService taskZfyxmxService;
	 @Autowired
	 private ITaskYxzfFjxxService taskYxzfFjxxService;
	 @Autowired
	 private INhxqService nhxqService;
	 @Autowired
	 private ItaskCreateService itaskCreateService;
	 @Autowired
	 private ITaskZzrwsjbRwfpService taskZzrwsjbRwfpService;
	 @Autowired
	 private ITaskRwmxDklshService taskRwmxDklshService;
	 @Value(value = "${common.path.upload}")
	 private String uploadpath;
	 /**
	 * 分页列表查询
	 *
	 * @param taskZfyxmx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "走访营销明细-分页列表查询")
	@ApiOperation(value="走访营销明细-分页列表查询", notes="走访营销明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TaskZfyxmx taskZfyxmx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TaskZfyxmx> queryWrapper = QueryGenerator.initQueryWrapper(taskZfyxmx, req.getParameterMap());
		queryWrapper.orderByDesc("create_time");
		Page<TaskZfyxmx> page = new Page<TaskZfyxmx>(pageNo, pageSize);
		IPage<TaskZfyxmx> pageList = taskZfyxmxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param taskZfyxmx
	 * @return
	 */
	@AutoLog(value = "走访营销明细-添加")
	@ApiOperation(value="走访营销明细-添加", notes="走访营销明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TaskZfyxmx taskZfyxmx) {
		taskZfyxmxService.save(taskZfyxmx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param taskZfyxmx
	 * @return
	 */
	@AutoLog(value = "走访营销明细-编辑")
	@ApiOperation(value="走访营销明细-编辑", notes="走访营销明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TaskZfyxmx taskZfyxmx) {
		taskZfyxmxService.updateById(taskZfyxmx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "走访营销明细-通过id删除")
	@ApiOperation(value="走访营销明细-通过id删除", notes="走访营销明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		taskZfyxmxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "走访营销明细-批量删除")
	@ApiOperation(value="走访营销明细-批量删除", notes="走访营销明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.taskZfyxmxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "走访营销明细-通过id查询")
	@ApiOperation(value="走访营销明细-通过id查询", notes="走访营销明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TaskZfyxmx taskZfyxmx = taskZfyxmxService.getById(id);
		return Result.ok(taskZfyxmx);
	}

	 /**
	  *
	  * @return
	  */
	 @AutoLog(value = "福祥站点主表-编辑")
	 @ApiOperation(value="福祥站点主表-编辑", notes="福祥站点主表-编辑")
	 @RequestMapping(value = "/addApp")
	 public Result<?> addApp(TaskZfyxmxVo taskZfyxmxVo) {
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 TaskZfyxmx taskZfyxmx =new TaskZfyxmx();
		 BeanUtils.copyProperties(taskZfyxmxVo, taskZfyxmx);
		 QueryWrapper<TaskCreate> queryWrapperT = new QueryWrapper<>();
		 queryWrapperT.eq("id", taskZfyxmxVo.getRwid());
		 TaskCreate taskCreate = itaskCreateService.getOne(queryWrapperT, false);
		 taskZfyxmx.setYxzfr(loginUser.getWorkNo());
		 taskZfyxmx.setRwmc(taskCreate.getTitle());
		 Nhxq nhxq=null;
		 if(taskCreate.getRwlx().equals("3")){
			 QueryWrapper queryWrapper1=new QueryWrapper();
			 queryWrapper1.eq("rwid",taskZfyxmxVo.getRwid());
			 queryWrapper1.eq("id",taskZfyxmxVo.getMxsjid());
			 TaskZzrwsjbRwfp one = taskZzrwsjbRwfpService.getOne(queryWrapper1, false);
			 QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
			 queryWrapper.eq("zjhm", one.getZjhm());
			 nhxq = nhxqService.getOne(queryWrapper, false);

			 if (StringUtils.isNotEmpty(taskZfyxmxVo.getLxfs())) {
				 one.setLxfs(taskZfyxmxVo.getLxfs());
				 UpdateWrapper<TaskZzrwsjbRwfp> zzrwsjbRwfpUpdateWrapper = new UpdateWrapper<>();
				 zzrwsjbRwfpUpdateWrapper.eq("id", taskZfyxmxVo.getMxsjid());
				 zzrwsjbRwfpUpdateWrapper.eq("rwid", taskZfyxmxVo.getRwid());
				 taskZzrwsjbRwfpService.update(one, zzrwsjbRwfpUpdateWrapper);
			 }
		 }else{
			 QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
			 queryWrapper.eq("id", taskZfyxmxVo.getMxsjid());
			 nhxq = nhxqService.getOne(queryWrapper, false);
		 }

		 if(taskZfyxmxVo.getYxlx().equals("2")){
			 if (nhxq!=null) {
				 if (StringUtils.isNotEmpty(taskZfyxmxVo.getLxfs())) {
					 nhxq.setSjhm(taskZfyxmxVo.getLxfs());
				 }
				 nhxq.setGrnsr(taskZfyxmxVo.getGrnsr());
				 nhxq.setJtnsr(taskZfyxmxVo.getJtnsr());
				 nhxq.setZz(taskZfyxmxVo.getZz());
				 nhxq.setSfycdg(taskZfyxmxVo.getSfycdg());
				 nhxq.setWgcs(taskZfyxmxVo.getWgcs());
				 nhxq.setLrr(getUsername());
				 nhxq.setUpDt(DateUtil.formatDateTime("yyyyMMdd"));
				 nhxq.setUpTm(DateUtil.formatDateTime("HHmmss"));
				 nhxq.setUpdateBy(getUsername());
				 nhxq.setUpdateTime(new Date());
				 taskZfyxmx.setKhmc(nhxq.getKhmc());
				 taskZfyxmx.setZjhm(nhxq.getZjhm());
				 UpdateWrapper<Nhxq> khglKhhmcxxUpdateWrapper = new UpdateWrapper<>();
				 khglKhhmcxxUpdateWrapper.eq("id", nhxq.getId());
				 if (!"1".equals(nhxq.getKhlx2yx())) {
					 nhxq.setKhlx2yx(taskZfyxmxVo.getKhlx2yx());
				 }
				 nhxqService.update(nhxq, khglKhhmcxxUpdateWrapper);
			 } else {
				 return Result.error("农户采集信息不存在");
			 }
		 }else if(taskZfyxmxVo.getYxlx().equals("1")) {
			 if(taskCreate.getRwlx().equals("3")){
				 QueryWrapper queryWrapper1=new QueryWrapper();
				 queryWrapper1.eq("rwid",taskZfyxmxVo.getRwid());
				 queryWrapper1.eq("id",taskZfyxmxVo.getMxsjid());
				 TaskZzrwsjbRwfp one = taskZzrwsjbRwfpService.getOne(queryWrapper1, false);
				 if(one!=null){
					 taskZfyxmx.setKhmc(one.getKhmc());
					 taskZfyxmx.setZjhm(one.getZjhm());
				 }else{
					 return Result.error("自主任务分配数据不存在");
				 }
			 }else{
				 QueryWrapper<TaskRwmxDklsh> queryWrapper1 = new QueryWrapper<>();
				 queryWrapper1.eq("id", taskZfyxmxVo.getMxsjid());
				 TaskRwmxDklsh dklsh = taskRwmxDklshService.getOne(queryWrapper1, false);
				 taskZfyxmx.setKhmc(dklsh.getCustName());
				 taskZfyxmx.setZjhm(dklsh.getIdentNo());
			 }
			 if (nhxq!=null && StringUtils.isNotEmpty(taskZfyxmxVo.getLxfs())) {
				 nhxq.setSjhm(taskZfyxmxVo.getLxfs());
				 UpdateWrapper<Nhxq> khglKhhmcxxUpdateWrapper = new UpdateWrapper<>();
				 khglKhhmcxxUpdateWrapper.eq("id", nhxq.getId());
				 nhxqService.update(nhxq, khglKhhmcxxUpdateWrapper);
			 }
		 }

		 taskZfyxmx.setKhlx2(taskZfyxmxVo.getKhlx2yx());
		 taskZfyxmxService.save(taskZfyxmx);

		 //附件有变化时 才会传值进来 当有值 先删除 后新增即可
		 if (CollUtil.isNotEmpty(taskZfyxmxVo.getFjxxList())) {
			 List<SbxxFjVO> yxdyfjxxList = taskZfyxmxVo.getFjxxList();
			 List<TaskYxzfFjxx> list1 = new ArrayList<TaskYxzfFjxx>();
			 for (int i = 0; i < yxdyfjxxList.size(); i++) {
				 SbxxFjVO fjVO = yxdyfjxxList.get(i);
				 TaskYxzfFjxx taskYxzfFjxx = new TaskYxzfFjxx();
				 taskYxzfFjxx.setZfyxmxid(taskZfyxmx.getId());
				 taskYxzfFjxx.setRwid(taskZfyxmx.getRwid());
				 taskYxzfFjxx.setMxsjid(taskZfyxmx.getMxsjid());
				 taskYxzfFjxx.setFjlx(fjVO.getZllx() + "");
				 taskYxzfFjxx.setScsj(new Date());
				 taskYxzfFjxx.setScr(loginUser.getUsername());
				 taskYxzfFjxx.setFjdx(new BigDecimal(fjVO.getSize()));
				 taskYxzfFjxx.setFjlj("/" + fjVO.getFwlj());
				 taskYxzfFjxx.setFjfwlj("/"+fjVO.getFwlj());
				 list1.add(taskYxzfFjxx);
			 }
			 taskYxzfFjxxService.saveBatch(list1);
		 }
		 return Result.ok("编辑成功!");
	 }

	 /**
	  *
	  * @return
	  */
	 @AutoLog(value = "福祥站点主表-编辑")
	 @ApiOperation(value="福祥站点主表-编辑", notes="福祥站点主表-编辑")
	 @PutMapping(value = "/addLycl")
	 public Result<?> addLycl(@RequestBody TaskZfyxmxVo taskZfyxmxVo) {
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 TaskZfyxmx taskZfyxmx =new TaskZfyxmx();
		 BeanUtils.copyProperties(taskZfyxmxVo, taskZfyxmx);
		 //附件有变化时 才会传值进来 当有值 先删除 后新增即可
		 QueryWrapper queryWrapper=new QueryWrapper();
		 queryWrapper.eq("zfyxmxid",taskZfyxmx.getId());
		 queryWrapper.eq("fjlx","3");
		 taskYxzfFjxxService.remove(queryWrapper);
		 if (CollUtil.isNotEmpty(taskZfyxmxVo.getFjxxList())) {
			 List<SbxxFjVO> yxdyfjxxList = taskZfyxmxVo.getFjxxList();
			 List<TaskYxzfFjxx> list1 = new ArrayList<TaskYxzfFjxx>();
			 for (int i = 0; i < yxdyfjxxList.size(); i++) {
				 SbxxFjVO fjVO = yxdyfjxxList.get(i);
				 TaskYxzfFjxx taskYxzfFjxx = new TaskYxzfFjxx();
				 taskYxzfFjxx.setZfyxmxid(taskZfyxmx.getId());
				 taskYxzfFjxx.setRwid(taskZfyxmx.getRwid());
				 taskYxzfFjxx.setMxsjid(taskZfyxmx.getMxsjid());
				 taskYxzfFjxx.setFjlx(fjVO.getZllx() + "");
				 taskYxzfFjxx.setScsj(new Date());
				 taskYxzfFjxx.setScr(loginUser.getUsername());
				 taskYxzfFjxx.setFjlj("/" + fjVO.getFwlj());
				 taskYxzfFjxx.setFjfwlj("/"+fjVO.getFwlj());
				 list1.add(taskYxzfFjxx);
			 }
			 taskYxzfFjxxService.saveBatch(list1);
		 }
		 return Result.ok("编辑成功!");
	 }

	 /**
	  * 通过zdbh查询
	  * @return
	  */
	 @GetMapping(value = "/queryFjxx")
	 public Result<?> queryFjxxByMainId(@RequestParam(name="id",required=true) String id,@RequestParam(name="fjlx",required=true) String fjlx) {
		 QueryWrapper<TaskYxzfFjxx> queryWrapper=new QueryWrapper();
		 queryWrapper.eq("zfyxmxid",id);
		 queryWrapper.eq("fjlx",fjlx);
		 List<TaskYxzfFjxx> fjxxes = taskYxzfFjxxService.list(queryWrapper);
		 JSONArray jsonArray =new JSONArray();
		 if (CollUtil.isNotEmpty(fjxxes)){
			 for(TaskYxzfFjxx fjxx:fjxxes){
				 jsonArray.add(fjxx.getFjlj());
			 }
		 }
		 return Result.ok(jsonArray);
	 }

	 /**
	  * 通过id查询任务完成明细
	  */
	 @GetMapping("getRwmcMxInfoById")
	 public Result<?> getRwmcMxInfoById(@RequestParam("id")String id){
	 	if (StringUtils.isNotBlank(id)){
	 		return Result.ok(taskZfyxmxService.getById(id));
		}
	 	return Result.ok();
	 }

	 /**
   * 导出excel
   *
   * @param request
   * @param taskZfyxmx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, TaskZfyxmx taskZfyxmx) {
      return super.exportXls(request, taskZfyxmx, TaskZfyxmx.class, "走访营销明细");
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
      return super.importExcel(request, response, TaskZfyxmx.class);
  }

}
