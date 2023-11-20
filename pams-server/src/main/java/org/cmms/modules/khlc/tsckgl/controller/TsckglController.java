package org.cmms.modules.khlc.tsckgl.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khlc.tsckgl.entity.CbsInvmBase;
import org.cmms.modules.khlc.tsckgl.entity.Tsckgl;
import org.cmms.modules.khlc.tsckgl.entity.TsckglImp;
import org.cmms.modules.khlc.tsckgl.service.ICbsInvmBaseService;
import org.cmms.modules.khlc.tsckgl.service.ITsckglService;
import org.cmms.modules.khlc.tsckgl.verify.TsckglImportVerify;
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
 * @Description: 特殊存款管理
 * @Author: jeecg-boot
 * @Date:   2023-08-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="特殊存款管理")
@RestController
@RequestMapping("/tsckgl/tsckgl")
public class TsckglController extends JeecgController<Tsckgl, ITsckglService> {
	@Autowired
	private ITsckglService tsckglService;
	@Autowired
	private ICbsInvmBaseService cbsInvmBaseService;
	@Autowired
	private TsckglImportVerify tsckglImportVerify;
	/**
	 * 分页列表查询
	 *
	 * @param tsckgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "特殊存款管理-分页列表查询")
	@ApiOperation(value="特殊存款管理-分页列表查询", notes="特殊存款管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Tsckgl tsckgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Tsckgl> queryWrapper = QueryGenerator.initQueryWrapper(tsckgl, req.getParameterMap());
		Page<Tsckgl> page = new Page<Tsckgl>(pageNo, pageSize);
		IPage<Tsckgl> pageList = tsckglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param tsckgl
	 * @return
	 */
	@AutoLog(value = "特殊存款管理-添加")
	@ApiOperation(value="特殊存款管理-添加", notes="特殊存款管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Tsckgl tsckgl) {
		QueryWrapper<Tsckgl> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("ckzh", tsckgl.getCkzh());
		List<Tsckgl> list = tsckglService.list(queryWrapper);
		if (!list.isEmpty()) {
			return Result.error("存款账号已经存在！");
		}
		tsckglService.save(tsckgl);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param tsckgl
	 * @return
	 */
	@AutoLog(value = "特殊存款管理-编辑")
	@ApiOperation(value="特殊存款管理-编辑", notes="特殊存款管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Tsckgl tsckgl) {
		QueryWrapper<Tsckgl> queryWrapper = new QueryWrapper<Tsckgl>();
		queryWrapper.eq("ckzh", tsckgl.getCkzh());
		tsckglService.update(tsckgl, queryWrapper);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过ckzh删除
	 *
	 * @param ckzh
	 * @return
	 */
	@AutoLog(value = "特殊存款管理-通过id删除")
	@ApiOperation(value="特殊存款管理-通过id删除", notes="特殊存款管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String ckzh) {
		QueryWrapper<Tsckgl> queryWrapper = new QueryWrapper<Tsckgl>();
		queryWrapper.eq("ckzh", ckzh);
		tsckglService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "特殊存款管理-批量删除")
	@ApiOperation(value="特殊存款管理-批量删除", notes="特殊存款管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tsckglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "特殊存款管理-通过id查询")
	@ApiOperation(value="特殊存款管理-通过id查询", notes="特殊存款管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Tsckgl tsckgl = tsckglService.getById(id);
		return Result.ok(tsckgl);
	}

	@GetMapping(value ="/queryByCkzh")
	public Result<?> queryByCkzh(@RequestParam(name="ckzh",required=true) String ckzh) {
		QueryWrapper<CbsInvmBase> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("sub_acct_no", ckzh);
		CbsInvmBase cbsInvmBase = cbsInvmBaseService.getOne(queryWrapper);
		if (cbsInvmBase == null) {
			return Result.error("未找到对应的存款账号");
		}
		return Result.ok(cbsInvmBase);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param tsckgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Tsckgl tsckgl) {
      return super.exportXls(request, tsckgl, Tsckgl.class, "特殊存款管理");
  }

	 /**
	  * 导出模板Excel
	  * 特殊贷款管理（绩效考核/初始设置）
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "特殊存款管理");
		 mv.addObject(NormalExcelConstants.CLASS, TsckglImp.class);
		 ExportParams exportParams = new ExportParams("特殊存款管理导入模板", "特殊存款管理");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
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
  public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
      return super.importExcelByTemplate(jsonObject, request, response, Tsckgl.class, TsckglImp.class, tsckglImportVerify);
  }

}
