package org.cmms.modules.rwzx.rwmxsj.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.rwzx.rwmxsj.entity.TaskRwmxDklsh;
import org.cmms.modules.rwzx.rwmxsj.entity.TaskRwmxEczf;
import org.cmms.modules.rwzx.rwmxsj.service.ITaskRwmxDklshService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
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
 * @Description: 贷款流失户明细
 * @Author: jeecg-boot
 * @Date:   2023-07-13
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款流失户明细")
@RestController
@RequestMapping("/rwmxsj/taskRwmxDklsh")
public class TaskRwmxDklshController extends JeecgController<TaskRwmxDklsh, ITaskRwmxDklshService> {
	@Autowired
	private ITaskRwmxDklshService taskRwmxDklshService;
	
	/**
	 * 分页列表查询
	 *
	 * @param taskRwmxDklsh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款流失户明细-分页列表查询")
	@ApiOperation(value="贷款流失户明细-分页列表查询", notes="贷款流失户明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TaskRwmxDklsh taskRwmxDklsh,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {

		String ssyxdy=taskRwmxDklsh.getSsyxdy();
		taskRwmxDklsh.setSsyxdy(null);
		QueryWrapper<TaskRwmxDklsh> queryWrapper = QueryGenerator.initQueryWrapper(taskRwmxDklsh, req.getParameterMap());
		if (StringUtils.isNotEmpty(ssyxdy) ) {
			String sqlSswg = "select wgbh from yxdygl_main start with wgbh='" + ssyxdy + "' connect by prior wgbh=parent_id";
			queryWrapper.and(i -> i.inSql("wgbh", sqlSswg));
		}
		Page<TaskRwmxDklsh> page = new Page<TaskRwmxDklsh>(pageNo, pageSize);
		IPage<TaskRwmxDklsh> pageList = taskRwmxDklshService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param taskRwmxDklsh
	 * @return
	 */
	@AutoLog(value = "贷款流失户明细-添加")
	@ApiOperation(value="贷款流失户明细-添加", notes="贷款流失户明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TaskRwmxDklsh taskRwmxDklsh) {
		taskRwmxDklshService.save(taskRwmxDklsh);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param taskRwmxDklsh
	 * @return
	 */
	@AutoLog(value = "贷款流失户明细-编辑")
	@ApiOperation(value="贷款流失户明细-编辑", notes="贷款流失户明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TaskRwmxDklsh taskRwmxDklsh) {
		taskRwmxDklshService.updateById(taskRwmxDklsh);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款流失户明细-通过id删除")
	@ApiOperation(value="贷款流失户明细-通过id删除", notes="贷款流失户明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		taskRwmxDklshService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款流失户明细-批量删除")
	@ApiOperation(value="贷款流失户明细-批量删除", notes="贷款流失户明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.taskRwmxDklshService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款流失户明细-通过id查询")
	@ApiOperation(value="贷款流失户明细-通过id查询", notes="贷款流失户明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TaskRwmxDklsh taskRwmxDklsh = taskRwmxDklshService.getById(id);
		return Result.ok(taskRwmxDklsh);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param taskRwmxDklsh
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, TaskRwmxDklsh taskRwmxDklsh) {
      return super.exportXls(request, taskRwmxDklsh, TaskRwmxDklsh.class, "贷款流失户明细");
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
      return super.importExcel(request, response, TaskRwmxDklsh.class);
  }

}
