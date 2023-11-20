package org.cmms.modules.system.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.system.entity.DpJdgl;
import org.cmms.modules.system.model.DpJdglTreeModel;
import org.cmms.modules.system.model.HrBasOrganizationTreeModel;
import org.cmms.modules.system.service.IDpJdglService;
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
 * @Description: 绩效自动任务节点管理
 * @Author: jeecg-boot
 * @Date:   2021-01-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="绩效自动任务节点管理")
@RestController
@RequestMapping("/system/dpJdgl")
public class DpJdglController extends JeecgController<DpJdgl, IDpJdglService> {
	@Autowired
	private IDpJdglService dpJdglService;
	
	/**
	 * 分页列表查询
	 *
	 * @param dpJdgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "绩效自动任务节点管理-分页列表查询")
	@ApiOperation(value="绩效自动任务节点管理-分页列表查询", notes="绩效自动任务节点管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DpJdgl dpJdgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DpJdgl> queryWrapper = QueryGenerator.initQueryWrapper(dpJdgl, req.getParameterMap());
		Page<DpJdgl> page = new Page<DpJdgl>(pageNo, pageSize);
		IPage<DpJdgl> pageList = dpJdglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}



	 @RequestMapping(value = "/queryTreeList", method = RequestMethod.GET)
	 public Result<List<DpJdglTreeModel>> queryTreeList() {
		 Result<List<DpJdglTreeModel>> result = new Result<>();
		 try {
			 List<DpJdglTreeModel> list = dpJdglService.queryTreeList();
			 System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@DpJdglTreeModel=="+list);
			 result.setResult(list);
			 result.setSuccess(true);
		 } catch (Exception e) {
			 log.error(e.getMessage(),e);
		 }
		 return result;
	 }


	 /**
	  * <p>
	  * 部门搜索功能方法,根据关键字模糊搜索相关部门
	  * </p>
	  *
	  * @param keyWord
	  * @return
	  */
	 @RequestMapping(value = "/searchBy", method = RequestMethod.GET)
	 public Result<List<DpJdglTreeModel>> searchBy(@RequestParam(name = "keyWord", required = true) String keyWord) {
		 Result<List<DpJdglTreeModel>> result = new Result<List<DpJdglTreeModel>>();
		 try {
			 List<DpJdglTreeModel> treeList = this.dpJdglService.searhBy(keyWord);
			 if (treeList.size() == 0 || treeList == null) {
				 throw new Exception();
			 }
			 result.setSuccess(true);
			 result.setResult(treeList);
			 return result;
		 } catch (Exception e) {
			 e.fillInStackTrace();
			 result.setSuccess(false);
			 result.setMessage("查询失败或没有您想要的任何数据!");
			 return result;
		 }
	 }



	 /**
	 * 添加
	 *
	 * @param dpJdgl
	 * @return
	 */
	@AutoLog(value = "绩效自动任务节点管理-添加")
	@ApiOperation(value="绩效自动任务节点管理-添加", notes="绩效自动任务节点管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DpJdgl dpJdgl) {
		dpJdglService.save(dpJdgl);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param dpJdgl
	 * @return
	 */
	@AutoLog(value = "绩效自动任务节点管理-编辑")
	@ApiOperation(value="绩效自动任务节点管理-编辑", notes="绩效自动任务节点管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DpJdgl dpJdgl) {
		dpJdglService.updateById(dpJdgl);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "绩效自动任务节点管理-通过id删除")
	@ApiOperation(value="绩效自动任务节点管理-通过id删除", notes="绩效自动任务节点管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dpJdglService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "绩效自动任务节点管理-批量删除")
	@ApiOperation(value="绩效自动任务节点管理-批量删除", notes="绩效自动任务节点管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dpJdglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "绩效自动任务节点管理-通过id查询")
	@ApiOperation(value="绩效自动任务节点管理-通过id查询", notes="绩效自动任务节点管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DpJdgl dpJdgl = dpJdglService.getById(id);
		return Result.ok(dpJdgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dpJdgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DpJdgl dpJdgl) {
      return super.exportXls(request, dpJdgl, DpJdgl.class, "绩效自动任务节点管理");
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
      return super.importExcel(request, response, DpJdgl.class);
  }

}
