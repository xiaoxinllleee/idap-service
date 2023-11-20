package org.cmms.modules.ywgl.cdkfx.khjlxzbltj.controller;

import java.util.Arrays;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.ywgl.cdkfx.khjlxzbltj.entity.ModDkfxKhjlxzbldktjMM;
import org.cmms.modules.ywgl.cdkfx.khjlxzbltj.service.IModDkfxKhjlxzbldktjMMService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ywgl.cdkfx.util.mapper.HrbasStaffToolMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 客户经理新增不良统计
 * @Author: jeecg-boot
 * @Date:   2021-06-21
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户经理新增不良统计")
@RestController
@RequestMapping("/khjlxzbltj/modDkfxKhjlxzbldktjMM")
public class ModDkfxKhjlxzbldktjMMController extends JeecgController<ModDkfxKhjlxzbldktjMM, IModDkfxKhjlxzbldktjMMService> {
	@Autowired
	private IModDkfxKhjlxzbldktjMMService modDkfxKhjlxzbldktjMMService;


	 @Value("${com.etl.sfdsjpt}")
	 private String sfdsjpt;
	/**
	 * 分页列表查询
	 *
	 * @param modDkfxKhjlxzbldktjMM
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户经理新增不良统计-分页列表查询")
	@ApiOperation(value="客户经理新增不良统计-分页列表查询", notes="客户经理新增不良统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ModDkfxKhjlxzbldktjMM modDkfxKhjlxzbldktjMM,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ModDkfxKhjlxzbldktjMM> queryWrapper = QueryGenerator.initQueryWrapper(modDkfxKhjlxzbldktjMM, req.getParameterMap());
		IPage pageList = PageUtil.toPage(IModDkfxKhjlxzbldktjMMService.class,modDkfxKhjlxzbldktjMMService, pageNo, pageSize, queryWrapper, "jgdm","custid","tjyf");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param modDkfxKhjlxzbldktjMM
	 * @return
	 */
	@AutoLog(value = "客户经理新增不良统计-添加")
	@ApiOperation(value="客户经理新增不良统计-添加", notes="客户经理新增不良统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ModDkfxKhjlxzbldktjMM modDkfxKhjlxzbldktjMM) {
		modDkfxKhjlxzbldktjMMService.save(modDkfxKhjlxzbldktjMM);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param modDkfxKhjlxzbldktjMM
	 * @return
	 */
	@AutoLog(value = "客户经理新增不良统计-编辑")
	@ApiOperation(value="客户经理新增不良统计-编辑", notes="客户经理新增不良统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ModDkfxKhjlxzbldktjMM modDkfxKhjlxzbldktjMM) {
		modDkfxKhjlxzbldktjMMService.updateById(modDkfxKhjlxzbldktjMM);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理新增不良统计-通过id删除")
	@ApiOperation(value="客户经理新增不良统计-通过id删除", notes="客户经理新增不良统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		modDkfxKhjlxzbldktjMMService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户经理新增不良统计-批量删除")
	@ApiOperation(value="客户经理新增不良统计-批量删除", notes="客户经理新增不良统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.modDkfxKhjlxzbldktjMMService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理新增不良统计-通过id查询")
	@ApiOperation(value="客户经理新增不良统计-通过id查询", notes="客户经理新增不良统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ModDkfxKhjlxzbldktjMM modDkfxKhjlxzbldktjMM = modDkfxKhjlxzbldktjMMService.getById(id);
		return Result.ok(modDkfxKhjlxzbldktjMM);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param modDkfxKhjlxzbldktjMM
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ModDkfxKhjlxzbldktjMM modDkfxKhjlxzbldktjMM) {
      return super.exportXls(request, modDkfxKhjlxzbldktjMM, ModDkfxKhjlxzbldktjMM.class, "客户经理新增不良统计");
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
      return super.importExcel(request, response, ModDkfxKhjlxzbldktjMM.class);
  }
/*
	 param = new HashMap<>();
            param.put("i_tjyf", tjyf);
            param.put("i_jgdm", jgdm);
	 flag = EtlUtil.callEtl("count_ywgl_cdkfx_n_dkyeb_xzbldk", param, 15);*/



	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/count")
	 public Result<?> count(@RequestParam  String tjyf) {
		 Result result = new Result<>();
		 if ("true".equalsIgnoreCase(sfdsjpt)) {
			 HashMap<String, String> parm = new HashMap<>();
			 parm.put("i_tjyf", tjyf.replaceAll("-", ""));
			 parm.put("etl_task","kiss.domain.application.cdkyw.proc_ywgl_cdkfx_n_dkyeb_xzbldk");
			 // count_ywgl_cdkfx_n_dkyeb_xzbldk
			 boolean falg = EtlUtil.callEtl("cdkyw_common_init", parm, 15);
			 result.setSuccess(falg);
		 }
		 return result;
	 }


 }
