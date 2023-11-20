package org.cmms.modules.rwzx.rwcj.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.rwzx.rwcj.entity.*;
import org.cmms.modules.rwzx.rwcj.service.ITaskBfrwBaseService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 基础拜访任务
 * @Author: jeecg-boot
 * @Date:   2023-04-01
 * @Version: V1.0
 */
@Slf4j
@Api(tags="基础拜访任务")
@RestController
@RequestMapping("/task/taskBfrwBase")
public class TaskBfrwBaseController extends JeecgController<TaskBfrwBase, ITaskBfrwBaseService> {
	
	/**
	 * 分页列表查询
	 *
	 * @param taskBfrwBase
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "基础拜访任务-分页列表查询")
	@ApiOperation(value="基础拜访任务-分页列表查询", notes="基础拜访任务-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TaskBfrwBase taskBfrwBase,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String wgbh = null;
		if (StringUtils.isNotBlank(taskBfrwBase.getWgbh())){
			wgbh = taskBfrwBase.getWgbh();
			taskBfrwBase.setWgbh(null);
		}

		QueryWrapper<TaskBfrwBase> queryWrapper = QueryGenerator.initQueryWrapper(taskBfrwBase, req.getParameterMap());

		if (StringUtils.isNotBlank(wgbh)) {
			queryWrapper.inSql("wgbh", "select menu_id from YXDYGL_PQQXGL where khjl = '" + getWorkNo() + "' and  " +
					"menu_id in (" +
					"select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id )");

		} else {
			queryWrapper.inSql("wgbh", "select menu_id from YXDYGL_PQQXGL where khjl = '" + getWorkNo() + "'");
		}

		Page<TaskBfrwBase> page = new Page<TaskBfrwBase>(pageNo, pageSize);
		IPage<TaskBfrwBase> pageList = service.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 @GetMapping(value = "/list2")
	 public Result<?> list2(TaskBfrwBase taskBfrwBase,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		 QueryWrapper<TaskBfrwBase> queryWrapper = QueryGenerator.initQueryWrapper(taskBfrwBase, req.getParameterMap());
		 Page<TaskBfrwBase> page = new Page<TaskBfrwBase>(pageNo, pageSize);
		 IPage<TaskBfrwBase> pageList = service.page(page, queryWrapper);
		 return Result.ok(pageList);
	 }


	 @GetMapping(value = "/wdrw")
	 public Result<?> wdrw(
							@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
							@RequestParam(name="pageSize", defaultValue="10") Integer pageSize
							) {
		 Page<WdrwVO> page = new Page<WdrwVO>(pageNo, pageSize);
		 Page<WdrwVO> pageWdrw = service.getPageWdrw(page,getWorkNo());
		 if (pageWdrw.getTotal() > 0l){
			 for (int i = 0; i < pageWdrw.getRecords().size(); i++) {
				 WdrwVO wdrwVO = pageWdrw.getRecords().get(0);
				 Serier serier = new Serier();
				 Serier2 serier2 = new Serier2();
				 List<NameValueVO> list = new ArrayList<>();
				 NameValueVO nameValueVO = new NameValueVO();
				 nameValueVO.setName("已拜访 "+ wdrwVO.getYzf());
				 nameValueVO.setValue(wdrwVO.getYzf());
				 NameValueVO nameValueVO2 = new NameValueVO();
				 nameValueVO2.setName("未拜访 "+ wdrwVO.getWzf());
				 nameValueVO2.setValue(wdrwVO.getWzf());
				 list.add(nameValueVO);
				 list.add(nameValueVO2);
				 serier2.setData(list);
				 List<Serier2> list1 = new ArrayList<>();
				 list1.add(serier2);
				 serier.setSeries(list1);
				 pageWdrw.getRecords().get(i).setPie(serier);
			 }
		 }

		 return Result.ok(pageWdrw);
	 }


	
	/**
	 * 添加
	 *
	 * @param taskBfrwBase
	 * @return
	 */
	@AutoLog(value = "基础拜访任务-添加")
	@ApiOperation(value="基础拜访任务-添加", notes="基础拜访任务-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TaskBfrwBase taskBfrwBase) {
		service.save(taskBfrwBase);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param taskBfrwBase
	 * @return
	 */
	@AutoLog(value = "基础拜访任务-编辑")
	@ApiOperation(value="基础拜访任务-编辑", notes="基础拜访任务-编辑")
	@RequestMapping(value = "/edit")
	public Result<?> edit(@RequestBody TaskBfrwBase taskBfrwBase) {
		service.updateById(taskBfrwBase);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "基础拜访任务-通过id删除")
	@ApiOperation(value="基础拜访任务-通过id删除", notes="基础拜访任务-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		service.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "基础拜访任务-批量删除")
	@ApiOperation(value="基础拜访任务-批量删除", notes="基础拜访任务-批量删除")
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
	@AutoLog(value = "基础拜访任务-通过id查询")
	@ApiOperation(value="基础拜访任务-通过id查询", notes="基础拜访任务-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TaskBfrwBase taskBfrwBase = service.getById(id);
		return Result.ok(taskBfrwBase);
	}


}
