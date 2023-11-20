package org.cmms.modules.ywgl.ckyw.ckzhgl.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ywgl.ckyw.ckzhgl.entity.Ckzhgl;
import org.cmms.modules.ywgl.ckyw.ckzhgl.service.ICkzhglService;
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
 * @Description: 存款账号关联管理
 * @Author: jeecg-boot
 * @Date:   2021-10-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存款账号关联管理")
@RestController
@RequestMapping("/ckzhgl/ckzhgl")
public class CkzhglController extends JeecgController<Ckzhgl, ICkzhglService> {
	@Autowired
	private ICkzhglService ckzhglService;
	
	/**
	 * 分页列表查询
	 *
	 * @param ckzhgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "存款账号关联管理-分页列表查询")
	@ApiOperation(value="存款账号关联管理-分页列表查询", notes="存款账号关联管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ckzhgl ckzhgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   String clckyeS,String clckyeE,String clckrpyeS,String clckrpyeE,
								   String ckrpyeS,String ckrpyeE,String nckrpyeS,String nckrpyeE,
								   String ckyeS,String ckyeE,
								   HttpServletRequest req) {
		QueryWrapper<Ckzhgl> queryWrapper = QueryGenerator.initQueryWrapper(ckzhgl, req.getParameterMap());
		if (StringUtils.isBlank(clckyeE))queryWrapper.ge("clckye",clckyeS);
		if (StringUtils.isBlank(clckyeE))queryWrapper.le("clckye",clckyeE);
		if (StringUtils.isBlank(clckrpyeE))queryWrapper.ge("clckrpye",clckrpyeS);
		if (StringUtils.isBlank(clckrpyeE))queryWrapper.le("clckrpye",clckrpyeE);
		if (StringUtils.isBlank(ckrpyeE))queryWrapper.ge("ckrpye",ckrpyeS);
		if (StringUtils.isBlank(ckrpyeE))queryWrapper.le("ckrpye",ckrpyeE);
		if (StringUtils.isBlank(nckrpyeE))queryWrapper.ge("nckrpye",nckrpyeS);
		if (StringUtils.isBlank(nckrpyeE))queryWrapper.le("nckrpye",nckrpyeE);
		if (StringUtils.isBlank(ckyeE))queryWrapper.ge("ckye",ckyeS);
		if (StringUtils.isBlank(ckyeE))queryWrapper.le("ckye",ckyeE);
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkzhglService.class,ckzhglService,pageNo,pageSize,queryWrapper,"glid","ckzh");
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param ckzhgl
	 * @return
	 */
	@AutoLog(value = "存款账号关联管理-添加")
	@ApiOperation(value="存款账号关联管理-添加", notes="存款账号关联管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ckzhgl ckzhgl) {
		ckzhglService.save(ckzhgl);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param ckzhgl
	 * @return
	 */
	@AutoLog(value = "存款账号关联管理-编辑")
	@ApiOperation(value="存款账号关联管理-编辑", notes="存款账号关联管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ckzhgl ckzhgl) {
		ckzhglService.updateById(ckzhgl);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "存款账号关联管理-通过id删除")
	@ApiOperation(value="存款账号关联管理-通过id删除", notes="存款账号关联管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("glid")String glid) {
		QueryWrapper<Ckzhgl> queryWrapper = new QueryWrapper<Ckzhgl>();
		queryWrapper.eq("glid",glid);
		ckzhglService.removeById(queryWrapper);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "存款账号关联管理-批量删除")
	@ApiOperation(value="存款账号关联管理-批量删除", notes="存款账号关联管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ckzhglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款账号关联管理-通过id查询")
	@ApiOperation(value="存款账号关联管理-通过id查询", notes="存款账号关联管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ckzhgl ckzhgl = ckzhglService.getById(id);
		return Result.ok(ckzhgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ckzhgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ckzhgl ckzhgl) {
      return super.exportXls(request, ckzhgl, Ckzhgl.class, "存款账号关联管理");
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
      return super.importExcel(request, response, Ckzhgl.class);
  }

}
