package org.cmms.modules.hr.zzgl.gwxxgl.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasPost;
import org.cmms.modules.hr.zzgl.gwxxgl.service.IHrBasPostService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.hr.zzgl.gwxxgl.verify.HrbasPostImportVerify;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 岗位信息管理
 * @Author: jeecg-boot
 * @Date:   2020-11-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="岗位信息管理")
@RestController
@RequestMapping("/gwxxgl/hrBasPost")
public class HrBasPostController extends JeecgController<HrBasPost, IHrBasPostService> {
	@Autowired
	private HrbasPostImportVerify importVerify;
	/**
	 * 分页列表查询
	 *
	 * @param hrBasPost
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "岗位信息管理-分页列表查询")
	@ApiOperation(value="岗位信息管理-分页列表查询", notes="岗位信息管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(HrBasPost hrBasPost,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<HrBasPost> queryWrapper = QueryGenerator.initQueryWrapper(hrBasPost, req.getParameterMap());
		Page<HrBasPost> page = new Page<HrBasPost>(pageNo, pageSize);
		IPage<HrBasPost> pageList = service.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
	  * 分页列表查询
	  *
	  * @return
	  */
	 @AutoLog(value = "岗位信息管理-列表查询")
	 @ApiOperation(value="岗位信息管理-列表查询", notes="岗位信息管理-列表查询")
	 @GetMapping(value = "/listAll")
	 public Result<?> queryPageListAll() {
		 return Result.ok(service.list());
	 }
	
	/**
	 * 添加
	 *
	 * @param hrBasPost
	 * @return
	 */
	@AutoLog(value = "岗位信息管理-添加")
	@ApiOperation(value="岗位信息管理-添加", notes="岗位信息管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody HrBasPost hrBasPost) {
		service.save(hrBasPost);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param hrBasPost
	 * @return
	 */
	@AutoLog(value = "岗位信息管理-编辑")
	@ApiOperation(value="岗位信息管理-编辑", notes="岗位信息管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody HrBasPost hrBasPost) {
		service.updateById(hrBasPost);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param gwbz
	 * @return
	 */
	@AutoLog(value = "岗位信息管理-通过id删除")
	@ApiOperation(value="岗位信息管理-通过id删除", notes="岗位信息管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="gwbz",required=true) String gwbz) {
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq("gwbz",gwbz);
		service.remove(queryWrapper);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "岗位信息管理-批量删除")
	@ApiOperation(value="岗位信息管理-批量删除", notes="岗位信息管理-批量删除")
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
	@AutoLog(value = "岗位信息管理-通过id查询")
	@ApiOperation(value="岗位信息管理-通过id查询", notes="岗位信息管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		HrBasPost hrBasPost = service.getById(id);
		return Result.ok(hrBasPost);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param hrBasPost
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HrBasPost hrBasPost) {
      return super.exportXls(request, hrBasPost, HrBasPost.class, "岗位信息管理");
  }

	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 return super.exportTemplateXls(HrBasPost.class, "岗位信息导入模板");
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
      return super.importExcelByTemplate(jsonObject, request, response, HrBasPost.class, importVerify);
  }


}
