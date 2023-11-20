package org.cmms.modules.hr.zzgl.zzgwgl.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasPost;
import org.cmms.modules.hr.zzgl.zzgwgl.entity.HrBasOrganPost;
import org.cmms.modules.hr.zzgl.zzgwgl.entity.RelationDTO;
import org.cmms.modules.hr.zzgl.zzgwgl.service.IHrBasOrganPostService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 组织岗位管理
 * @Author: jeecg-boot
 * @Date:   2020-11-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="组织岗位管理")
@RestController
@RequestMapping("/zzgwgl/hrBasOrganPost")
public class HrBasOrganPostController extends JeecgController<HrBasOrganPost, IHrBasOrganPostService> {
	
	/**
	 * 分页列表查询
	 *
	 * @param hrBasOrganPost
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "组织岗位管理-分页列表查询")
	@ApiOperation(value="组织岗位管理-分页列表查询", notes="组织岗位管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(HrBasOrganPost hrBasOrganPost,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<HrBasOrganPost> queryWrapper = QueryGenerator.initQueryWrapper(hrBasOrganPost, req.getParameterMap());
		Page<HrBasOrganPost> page = new Page<HrBasOrganPost>(pageNo, pageSize);
		IPage<HrBasOrganPost> pageList = service.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param hrBasOrganPost
	 * @return
	 */
	@AutoLog(value = "组织岗位管理-添加")
	@ApiOperation(value="组织岗位管理-添加", notes="组织岗位管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody HrBasOrganPost hrBasOrganPost) {
		service.save(hrBasOrganPost);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param hrBasOrganPost
	 * @return
	 */
	@AutoLog(value = "组织岗位管理-编辑")
	@ApiOperation(value="组织岗位管理-编辑", notes="组织岗位管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody HrBasOrganPost hrBasOrganPost) {
		service.updateById(hrBasOrganPost);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @return
	 */
	@AutoLog(value = "组织岗位管理-通过id删除")
	@ApiOperation(value="组织岗位管理-通过id删除", notes="组织岗位管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="zzbz",required=true) String zzbz,
							@RequestParam(name="gwbz",required=true) String gwbz) {
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq("zzbz",zzbz);
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
	@AutoLog(value = "组织岗位管理-批量删除")
	@ApiOperation(value="组织岗位管理-批量删除", notes="组织岗位管理-批量删除")
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
	@AutoLog(value = "组织岗位管理-通过id查询")
	@ApiOperation(value="组织岗位管理-通过id查询", notes="组织岗位管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		HrBasOrganPost hrBasOrganPost = service.getById(id);
		return Result.ok(hrBasOrganPost);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param hrBasOrganPost
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HrBasOrganPost hrBasOrganPost) {
      return super.exportXls(request, hrBasOrganPost, HrBasOrganPost.class, "组织岗位管理");
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
      return super.importExcel(request, response, HrBasOrganPost.class);
  }


	 /**
	  * 角色关联岗位
	  *
	  * @param relationDTO
	  * @return
	  */
	 @RequestMapping(value = "/relation", method = RequestMethod.POST)
	 public Result<?> relation(@RequestBody RelationDTO relationDTO) {
	 	if (StringUtils.isBlank(relationDTO.getRadioValue()))
	 		return Result.error("请选择角色");

	 	if (relationDTO.getCheckedKeys() == null || relationDTO.getCheckedKeys().size() == 0 )
	 		return Result.error("请选择银行");

		 int i = service.relationByRolesAndBank(relationDTO);

		 return Result.ok("关联成功，新关联"+i+"条数据");
	 }

}
