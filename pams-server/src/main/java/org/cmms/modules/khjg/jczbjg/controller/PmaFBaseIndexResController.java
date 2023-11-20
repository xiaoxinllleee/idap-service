package org.cmms.modules.khjg.jczbjg.controller;

import java.util.Arrays;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.khjg.jczbjg.entity.PmaFBaseIndexRes;
import org.cmms.modules.khjg.jczbjg.service.IPmaFBaseIndexResService;
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
 * @Description: 基础指标结果
 * @Author: jeecg-boot
 * @Date:   2021-05-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="基础指标结果")
@RestController
@RequestMapping("/jczbjg/pmaFBaseIndexRes")
public class PmaFBaseIndexResController extends JeecgController<PmaFBaseIndexRes, IPmaFBaseIndexResService> {
	@Autowired
	private IPmaFBaseIndexResService pmaFBaseIndexResService;

	/**
	 * 分页列表查询
	 *
	 * @param pmaFBaseIndexRes
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "基础指标结果-分页列表查询")
	@ApiOperation(value="基础指标结果-分页列表查询", notes="基础指标结果-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PmaFBaseIndexRes pmaFBaseIndexRes,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req,
								   String startTime,String endTime) {
		QueryWrapper<PmaFBaseIndexRes> queryWrapper = QueryGenerator.initQueryWrapper(pmaFBaseIndexRes, req.getParameterMap());
		Page<PmaFBaseIndexRes> page = new Page<PmaFBaseIndexRes>(pageNo, pageSize);
		if (startTime != null && endTime !=null) {
			queryWrapper.between("STAT_DATE", startTime, endTime);
		}
		IPage<PmaFBaseIndexRes> pageList = pmaFBaseIndexResService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param pmaFBaseIndexRes
	 * @return
	 */
	@AutoLog(value = "基础指标结果-添加")
	@ApiOperation(value="基础指标结果-添加", notes="基础指标结果-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PmaFBaseIndexRes pmaFBaseIndexRes) {
		pmaFBaseIndexResService.save(pmaFBaseIndexRes);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param pmaFBaseIndexRes
	 * @return
	 */
	@AutoLog(value = "基础指标结果-编辑")
	@ApiOperation(value="基础指标结果-编辑", notes="基础指标结果-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PmaFBaseIndexRes pmaFBaseIndexRes) {
		pmaFBaseIndexResService.updateById(pmaFBaseIndexRes);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "基础指标结果-通过id删除")
	@ApiOperation(value="基础指标结果-通过id删除", notes="基础指标结果-通过id删除")
	@DeleteMapping(value = "/delete")

	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		pmaFBaseIndexResService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "基础指标结果-批量删除")
	@ApiOperation(value="基础指标结果-批量删除", notes="基础指标结果-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pmaFBaseIndexResService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "基础指标结果-通过id查询")
	@ApiOperation(value="基础指标结果-通过id查询", notes="基础指标结果-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PmaFBaseIndexRes pmaFBaseIndexRes = pmaFBaseIndexResService.getById(id);
		return Result.ok(pmaFBaseIndexRes);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param pmaFBaseIndexRes
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, PmaFBaseIndexRes pmaFBaseIndexRes) {
      return super.exportXls(request, pmaFBaseIndexRes, PmaFBaseIndexRes.class, "基础指标结果");
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
      return super.importExcel(request, response, PmaFBaseIndexRes.class);
  }

}
