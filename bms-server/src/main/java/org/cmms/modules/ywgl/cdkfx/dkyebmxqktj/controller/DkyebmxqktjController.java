package org.cmms.modules.ywgl.cdkfx.dkyebmxqktj.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.utils.PageUtil;
import org.cmms.config.MybatisPlusConfig;
import org.cmms.modules.system.controller.IdentEncryptionController;
import org.cmms.modules.ywgl.cdkfx.dkyebmxqktj.entity.Dkyebmxqktj;
import org.cmms.modules.ywgl.cdkfx.dkyebmxqktj.service.IDkyebmxqktjService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 贷款余额表明细情况统计
 * @Author: jeecg-boot
 * @Date:   2021-06-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款余额表明细情况统计")
@RestController
@RequestMapping("/dkyebmxqktj")
public class DkyebmxqktjController extends JeecgController<Dkyebmxqktj, IDkyebmxqktjService> {
	@Autowired
	private IDkyebmxqktjService iDkyebmxqktjService;


	/**
	 * 分页列表查询
	 *
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款余额表明细情况统计-分页列表查询")
	@ApiOperation(value="贷款余额表明细情况统计-分页列表查询", notes="贷款余额表明细情况统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dkyebmxqktj midDmpmDkyebmxqktj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dkyebmxqktj> queryWrapper = QueryGenerator.initQueryWrapper(midDmpmDkyebmxqktj, req.getParameterMap());
		if (DateUtil.getMonthBeginDay(new Date()).equals(midDmpmDkyebmxqktj.getTjyf())){
			queryWrapper.isNotNull("fiscal_date");
		}else {
			queryWrapper.eq("fiscal_date",midDmpmDkyebmxqktj.getTjyf());
		}
		//queryWrapper.eq("shbz",0).gt("blbz",0);
		IPage pageList = PageUtil.toPage(IDkyebmxqktjService.class,iDkyebmxqktjService, pageNo, pageSize, queryWrapper, "jgdm","custid");
		return Result.ok(pageList);
	}
	/**
	 * 添加
	 *
	 * @param midDmpmDkyebmxqktj
	 * @return
	 */
	@AutoLog(value = "贷款余额表明细情况统计-添加")
	@ApiOperation(value="贷款余额表明细情况统计-添加", notes="贷款余额表明细情况统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dkyebmxqktj midDmpmDkyebmxqktj) {
		iDkyebmxqktjService.save(midDmpmDkyebmxqktj);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param midDmpmDkyebmxqktj
	 * @return
	 */
	@AutoLog(value = "贷款余额表明细情况统计-编辑")
	@ApiOperation(value="贷款余额表明细情况统计-编辑", notes="贷款余额表明细情况统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dkyebmxqktj midDmpmDkyebmxqktj) {
		iDkyebmxqktjService.updateById(midDmpmDkyebmxqktj);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款余额表明细情况统计-通过id删除")
	@ApiOperation(value="贷款余额表明细情况统计-通过id删除", notes="贷款余额表明细情况统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		iDkyebmxqktjService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款余额表明细情况统计-批量删除")
	@ApiOperation(value="贷款余额表明细情况统计-批量删除", notes="贷款余额表明细情况统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.iDkyebmxqktjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款余额表明细情况统计-通过id查询")
	@ApiOperation(value="贷款余额表明细情况统计-通过id查询", notes="贷款余额表明细情况统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dkyebmxqktj midDmpmDkyebmxqktj = iDkyebmxqktjService.getById(id);
		return Result.ok(midDmpmDkyebmxqktj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param midDmpmDkyebmxqktj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request,
								Dkyebmxqktj midDmpmDkyebmxqktj,
								String begindayString,
								String enddayString) {
	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	  // Step.1 组装查询条件
	  QueryWrapper<Dkyebmxqktj> queryWrapper = QueryGenerator.initQueryWrapper(midDmpmDkyebmxqktj, request.getParameterMap());
	  try {
		  if (begindayString != null) {
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			  String[] split = begindayString.split(",");
			  queryWrapper.between("BEGINDAY", sdf.parse(split[0]), sdf.parse(split[1]));
		  }
		  if (enddayString != null) {
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			  String[] split = enddayString.split(",");
			  queryWrapper.between("ENDDAY", sdf.parse(split[0]), sdf.parse(split[1]));
		  }
	  } catch (ParseException p) {
		  p.printStackTrace();
	  }
	  queryWrapper.eq("shbz", 0).gt("blbz", 0).orderByDesc("jgdm");
	  List<Dkyebmxqktj> pageList = iDkyebmxqktjService.list(queryWrapper);
	  //Step.2 AutoPoi 导出Excel
	  ModelAndView mv = new ModelAndView(new IdentEncryptionController());
	  //导出文件名称
	  mv.addObject(NormalExcelConstants.FILE_NAME, "贷款余额表明细情况统计");
	  mv.addObject(NormalExcelConstants.CLASS, Dkyebmxqktj.class);
	  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("贷款余额表明细情况统计", "导出人:"+sysUser.getRealname(), "导出信息"));
	  mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
	  return mv;
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
      return super.importExcel(request, response, Dkyebmxqktj.class);
  }

}
