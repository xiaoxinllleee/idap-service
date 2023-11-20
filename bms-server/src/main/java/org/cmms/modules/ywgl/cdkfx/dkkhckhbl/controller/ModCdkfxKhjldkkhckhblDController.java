package org.cmms.modules.ywgl.cdkfx.dkkhckhbl.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.ywgl.cdkfx.dkkhckhbl.entity.ModCdkfxKhjldkkhckhblD;
import org.cmms.modules.ywgl.cdkfx.dkkhckhbl.service.IModCdkfxKhjldkkhckhblDService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.ywgl.cdkfx.util.mapper.CallToolMapper;
import org.cmms.modules.ywgl.cdkfx.util.mapper.HrbasStaffToolMapper;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 贷款客户存款回报率
 * @Author: jeecg-boot
 * @Date:   2021-07-01
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款客户存款回报率")
@RestController
@RequestMapping("/dkkhckhbl/modCdkfxKhjldkkhckhbld")
public class ModCdkfxKhjldkkhckhblDController extends JeecgController<ModCdkfxKhjldkkhckhblD, IModCdkfxKhjldkkhckhblDService> {
	@Autowired
	private IModCdkfxKhjldkkhckhblDService modCdkfxKhjldkkhckhblService;

	@Autowired(required = false)
	private HrbasStaffToolMapper hrbasStaffToolMapper;

	/**
	 * 分页列表查询
	 *
	 * @param modCdkfxKhjldkkhckhbl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款客户存款回报率-分页列表查询")
	@ApiOperation(value="贷款客户存款回报率-分页列表查询", notes="贷款客户存款回报率-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ModCdkfxKhjldkkhckhblD modCdkfxKhjldkkhckhbl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ModCdkfxKhjldkkhckhblD> queryWrapper = QueryGenerator.initQueryWrapper(modCdkfxKhjldkkhckhbl, req.getParameterMap());
		IPage pageList = PageUtil.toPage(IModCdkfxKhjldkkhckhblDService.class,modCdkfxKhjldkkhckhblService, 1, 10, queryWrapper, "tjyf","jgdm","yggh");
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param modCdkfxKhjldkkhckhbl
	 * @return
	 */
	@AutoLog(value = "贷款客户存款回报率-添加")
	@ApiOperation(value="贷款客户存款回报率-添加", notes="贷款客户存款回报率-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ModCdkfxKhjldkkhckhblD modCdkfxKhjldkkhckhbl) {
		modCdkfxKhjldkkhckhblService.save(modCdkfxKhjldkkhckhbl);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param modCdkfxKhjldkkhckhbl
	 * @return
	 */
	@AutoLog(value = "贷款客户存款回报率-编辑")
	@ApiOperation(value="贷款客户存款回报率-编辑", notes="贷款客户存款回报率-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ModCdkfxKhjldkkhckhblD modCdkfxKhjldkkhckhbl) {
		modCdkfxKhjldkkhckhblService.updateById(modCdkfxKhjldkkhckhbl);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款客户存款回报率-通过id删除")
	@ApiOperation(value="贷款客户存款回报率-通过id删除", notes="贷款客户存款回报率-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		modCdkfxKhjldkkhckhblService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款客户存款回报率-批量删除")
	@ApiOperation(value="贷款客户存款回报率-批量删除", notes="贷款客户存款回报率-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.modCdkfxKhjldkkhckhblService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款客户存款回报率-通过id查询")
	@ApiOperation(value="贷款客户存款回报率-通过id查询", notes="贷款客户存款回报率-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ModCdkfxKhjldkkhckhblD modCdkfxKhjldkkhckhbl = modCdkfxKhjldkkhckhblService.getById(id);
		return Result.ok(modCdkfxKhjldkkhckhbl);
	}
	 /**
	  * 提取
	  */
	 @RequestMapping(value="/count")
	 public Result<?> count(ModCdkfxKhjldkkhckhblD modCdkfxKhjldkkhckhblD,String tjyf){
		 tjyf = tjyf.replaceAll("-", "");
		 modCdkfxKhjldkkhckhblService.pDkfxDkkhckhbl(tjyf);
		 return Result.ok("提取成功");
	 }

	 /**
	  * 导出明细
	  * @param request
	  * @param modCdkfxKhjldkkhckhblD
	  * @return
	  */
	 @RequestMapping(value = "/exp")
	 public ModelAndView exportXlsExp(HttpServletRequest request, ModCdkfxKhjldkkhckhblD modCdkfxKhjldkkhckhblD) {
		 return super.exportXls(request, modCdkfxKhjldkkhckhblD, ModCdkfxKhjldkkhckhblD.class, "客户经理贷款客户存款回报率");
//		 // Step.1 组装查询条件
//		 QueryWrapper<ModCdkfxKhjldkkhckhblD> queryWrapper = QueryGenerator.initQueryWrapper(modCdkfxKhjldkkhckhblD, request.getParameterMap());
//		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//		 if (modCdkfxKhjldkkhckhblD.getTjyf()==null || modCdkfxKhjldkkhckhblD.getKhjl()==null){
//			 return null;
//		 }
//		 queryWrapper.eq("tjyj",modCdkfxKhjldkkhckhblD.getTjyf());
//		 queryWrapper.eq("khjl",modCdkfxKhjldkkhckhblD.getKhjl());
//		 if (ygxm!=null){
//			 String yggh = hrbasStaffToolMapper.getYgghByName(ygxm);
//			 if (yggh==null){
//				 queryWrapper.eq("yggh",yggh+"null");
//			 }else {
//				 queryWrapper.eq("yggh",yggh);
//			 }
//		 }
//		 // Step.2 获取导出数据
//		 List<ModCdkfxKhjldkkhckhblD> pageList = service.list(queryWrapper);
//		 // 过滤选中数据
//		 // Step.3 AutoPoi 导出Excel
//		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
//		 mv.addObject(NormalExcelConstants.FILE_NAME, "客户经理贷款客户存款回报率"); //此处设置的filename无效 ,前端会重更新设置一下
//		 mv.addObject(NormalExcelConstants.CLASS, ModCdkfxKhjldkkhckhblD.class);
//		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户经理贷款客户存款回报率" + "报表", "导出人:" + sysUser.getRealname(), "客户经理贷款客户存款回报率"));
//		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
	 }

  /**
   * 导出excel
   *
   * @param request
   * @param modCdkfxKhjldkkhckhbl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ModCdkfxKhjldkkhckhblD modCdkfxKhjldkkhckhbl,String ygxm) {
	  // Step.1 组装查询条件
	  QueryWrapper<ModCdkfxKhjldkkhckhblD> queryWrapper = QueryGenerator.initQueryWrapper(modCdkfxKhjldkkhckhbl, request.getParameterMap());
	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	  if (ygxm!=null){
		  String yggh = hrbasStaffToolMapper.getYgghByName(ygxm);
		  if (yggh==null){
			  queryWrapper.eq("yggh",yggh+"null");
		  }else {
			  queryWrapper.eq("yggh",yggh);
		  }
	  }
	  // Step.2 获取导出数据
	  List<ModCdkfxKhjldkkhckhblD> pageList = service.list(queryWrapper);
	  // 过滤选中数据
	  // Step.3 AutoPoi 导出Excel
	  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
	  mv.addObject(NormalExcelConstants.FILE_NAME, "贷款客户存款回报率"); //此处设置的filename无效 ,前端会重更新设置一下
	  mv.addObject(NormalExcelConstants.CLASS, ModCdkfxKhjldkkhckhblD.class);
	  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("贷款客户存款回报率" + "报表", "导出人:" + sysUser.getRealname(), "贷款客户存款回报率"));
	  mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
	  return mv;
//      return super.exportXls(request, modCdkfxKhjldkkhckhbl, ModCdkfxKhjldkkhckhblD.class, "贷款客户存款回报率");
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
      return super.importExcel(request, response, ModCdkfxKhjldkkhckhblD.class);
  }

}
