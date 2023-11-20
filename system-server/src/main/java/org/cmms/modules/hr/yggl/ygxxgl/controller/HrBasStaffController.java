package org.cmms.modules.hr.yggl.ygxxgl.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.common.api.vo.Result;
import org.cmms.common.api.vo.ResultConstant;
import org.cmms.common.constant.CacheConstant;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.util.RedisUtil;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.hr.yggl.ygxxgl.verify.HrbasStaffImportVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 员工信息管理
 * @Author: jeecg-boot
 * @Date:   2020-11-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="员工信息管理")
@RestController
@RequestMapping("/ygxxgl/hrBasStaff")
public class HrBasStaffController extends JeecgController<HrBasStaff, IHrBasStaffService> {
	@Autowired
	private IHrBasStaffService hrBasStaffService;
	@Autowired
	private HrbasStaffImportVerify importVerify;
	 @Autowired
	 public RedisTemplate<String, Object> redisTemplate;
	/**
	 * 分页列表查询
	 *
	 * @param hrBasStaff
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "员工信息管理-分页列表查询")
	@ApiOperation(value="员工信息管理-分页列表查询", notes="员工信息管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(HrBasStaff hrBasStaff,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<HrBasStaff> queryWrapper = QueryGenerator.initQueryWrapper(hrBasStaff, req.getParameterMap());
		Page<HrBasStaff> page = new Page<HrBasStaff>(pageNo, pageSize);
		IPage<HrBasStaff> pageList = hrBasStaffService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param hrBasStaff
	 * @return
	 */
	@AutoLog(value = "员工信息管理-添加")
	@ApiOperation(value="员工信息管理-添加", notes="员工信息管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody HrBasStaff hrBasStaff) {
		hrBasStaffService.save(hrBasStaff);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param hrBasStaff
	 * @return
	 */
	@AutoLog(value = "员工信息管理-编辑")
	@ApiOperation(value="员工信息管理-编辑", notes="员工信息管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody HrBasStaff hrBasStaff) {
		hrBasStaffService.updateById(hrBasStaff);
		Set keys = redisTemplate.keys(CacheConstant.SYS_DICT_TABLE_CACHE + "*" + "hr_bas_staff" + "*" + hrBasStaff.getYggh() + "*");
		redisTemplate.delete(keys);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工信息管理-通过id删除")
	@ApiOperation(value="员工信息管理-通过id删除", notes="员工信息管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		hrBasStaffService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "员工信息管理-批量删除")
	@ApiOperation(value="员工信息管理-批量删除", notes="员工信息管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.hrBasStaffService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工信息管理-通过id查询")
	@ApiOperation(value="员工信息管理-通过id查询", notes="员工信息管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		HrBasStaff hrBasStaff = hrBasStaffService.getById(id);
		return Result.ok(hrBasStaff);
	}

	 /**
	  * 通过员工工号查询
	  *
	  * @param yggh
	  * @return
	  */
	 @ApiOperation(value="员工信息管理-通过员工工号查询", notes="员工信息管理-通过员工工号查询")
	 @GetMapping(value = "/queryByYggh")
	 public Result<?> queryByYggh(@RequestParam(name="yggh", required=true) String yggh) {
	 	 QueryWrapper<HrBasStaff> queryWrapper = new QueryWrapper<>();
	 	 queryWrapper.eq("yggh", yggh);
		 List<HrBasStaff> hrBasStaffList = hrBasStaffService.list(queryWrapper);
		 if (!hrBasStaffList.isEmpty()) {
		 	return Result.ok(hrBasStaffList.get(0));
		 }
		 return Result.ok();
	 }

  /**
   * 导出excel
   *
   * @param request
   * @param hrBasStaff
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HrBasStaff hrBasStaff) {
      return super.exportXls(request, hrBasStaff, HrBasStaff.class, "员工信息管理");
  }

	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 return super.exportTemplateXls(HrBasStaff.class, "员工信息导入模板");
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
	  return super.importExcelByTemplate(jsonObject, request, response, HrBasStaff.class, importVerify);
  }

  /**
  * 提取
  * */
  @RequestMapping("/updateStaffIngo")
  public Result<?> updateStaffIngo(){
	  service.updateStaffInfo();
	  return Result.ok(ResultConstant.TQCG);
  }

  @RequestMapping("/getByYggh")
  public Result<?> getByYggh(){
	  LambdaQueryWrapper<HrBasStaff> lambdaQueryWrapper = new LambdaQueryWrapper<>();
	  lambdaQueryWrapper.eq(HrBasStaff::getYggh,getWorkNo());
	  lambdaQueryWrapper.orderByDesc(HrBasStaff::getRzrq);
	  List<HrBasStaff> list = service.list(lambdaQueryWrapper);
	  if (CollUtil.isNotEmpty(list))
		  return Result.ok(list.get(0));
	  return Result.ok();
  }
}
