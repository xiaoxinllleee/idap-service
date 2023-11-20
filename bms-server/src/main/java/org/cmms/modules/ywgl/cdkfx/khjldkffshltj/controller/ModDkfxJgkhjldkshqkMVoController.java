package org.cmms.modules.ywgl.cdkfx.khjldkffshltj.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.ywgl.cdkfx.khjldkffshltj.entity.ModDkfxJgkhjldkshqkMVo;
import org.cmms.modules.ywgl.cdkfx.khjldkffshltj.service.IModDkfxJgkhjldkshqkMVoService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ywgl.cdkfx.util.mapper.HrbasStaffToolMapper;
import org.cmms.modules.ywgl.cdkfx.xzblkh.entity.VKhjlxzblkhVo;
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
 * @Description: 客户经理贷款发放收回统计
 * @Author: jeecg-boot
 * @Date:   2021-07-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户经理贷款发放收回统计")
@RestController
@RequestMapping("/khjldkffshltj/modDkfxJgkhjldkshqkMVo")
public class ModDkfxJgkhjldkshqkMVoController extends JeecgController<ModDkfxJgkhjldkshqkMVo, IModDkfxJgkhjldkshqkMVoService> {
	@Autowired
	private IModDkfxJgkhjldkshqkMVoService modDkfxJgkhjldkshqkMVoService;

	@Autowired(required = false)
	private HrbasStaffToolMapper hrbasStaffToolMapper;
	/**
	 * 分页列表查询
	 *
	 * @param modDkfxJgkhjldkshqkMVo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户经理贷款发放收回统计-分页列表查询")
	@ApiOperation(value="客户经理贷款发放收回统计-分页列表查询", notes="客户经理贷款发放收回统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ModDkfxJgkhjldkshqkMVo modDkfxJgkhjldkshqkMVo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ModDkfxJgkhjldkshqkMVo> queryWrapper = QueryGenerator.initQueryWrapper(modDkfxJgkhjldkshqkMVo, req.getParameterMap());
		IPage pageList = PageUtil.toPage(IModDkfxJgkhjldkshqkMVoService.class,modDkfxJgkhjldkshqkMVoService, pageNo, pageSize, queryWrapper, "jgdm","custid","tjyf");
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param modDkfxJgkhjldkshqkMVo
	 * @return
	 */
	@AutoLog(value = "客户经理贷款发放收回统计-添加")
	@ApiOperation(value="客户经理贷款发放收回统计-添加", notes="客户经理贷款发放收回统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ModDkfxJgkhjldkshqkMVo modDkfxJgkhjldkshqkMVo) {
		modDkfxJgkhjldkshqkMVoService.save(modDkfxJgkhjldkshqkMVo);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param modDkfxJgkhjldkshqkMVo
	 * @return
	 */
	@AutoLog(value = "客户经理贷款发放收回统计-编辑")
	@ApiOperation(value="客户经理贷款发放收回统计-编辑", notes="客户经理贷款发放收回统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ModDkfxJgkhjldkshqkMVo modDkfxJgkhjldkshqkMVo) {
		modDkfxJgkhjldkshqkMVoService.updateById(modDkfxJgkhjldkshqkMVo);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理贷款发放收回统计-通过id删除")
	@ApiOperation(value="客户经理贷款发放收回统计-通过id删除", notes="客户经理贷款发放收回统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		modDkfxJgkhjldkshqkMVoService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户经理贷款发放收回统计-批量删除")
	@ApiOperation(value="客户经理贷款发放收回统计-批量删除", notes="客户经理贷款发放收回统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.modDkfxJgkhjldkshqkMVoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理贷款发放收回统计-通过id查询")
	@ApiOperation(value="客户经理贷款发放收回统计-通过id查询", notes="客户经理贷款发放收回统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ModDkfxJgkhjldkshqkMVo modDkfxJgkhjldkshqkMVo = modDkfxJgkhjldkshqkMVoService.getById(id);
		return Result.ok(modDkfxJgkhjldkshqkMVo);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param modDkfxJgkhjldkshqkMVo
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ModDkfxJgkhjldkshqkMVo modDkfxJgkhjldkshqkMVo,String custidName) {
	  // Step.1 组装查询条件
	  QueryWrapper<ModDkfxJgkhjldkshqkMVo> queryWrapper = QueryGenerator.initQueryWrapper(modDkfxJgkhjldkshqkMVo, request.getParameterMap());
	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	  if (custidName!=null){
		  String custid = hrbasStaffToolMapper.getCustidByName(custidName);
		  if (custid==null){
			  queryWrapper.eq("custid",custid+"null");
		  }else {
			  queryWrapper.eq("custid",custid);
		  }
	  }
	  // Step.2 获取导出数据
	  List<ModDkfxJgkhjldkshqkMVo> pageList = service.list(queryWrapper);
	  // 过滤选中数据
	  // Step.3 AutoPoi 导出Excel
	  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
	  mv.addObject(NormalExcelConstants.FILE_NAME, "客户经理贷款发放收回统计"); //此处设置的filename无效 ,前端会重更新设置一下
	  mv.addObject(NormalExcelConstants.CLASS, ModDkfxJgkhjldkshqkMVo.class);
	  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户经理贷款发放收回统计" + "报表", "导出人:" + sysUser.getRealname(), "客户经理贷款发放收回统计"));
	  mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
	  return mv;
      //return super.exportXls(request, modDkfxJgkhjldkshqkMVo, ModDkfxJgkhjldkshqkMVo.class, "客户经理贷款发放收回统计");
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
      return super.importExcel(request, response, ModDkfxJgkhjldkshqkMVo.class);
  }

}
