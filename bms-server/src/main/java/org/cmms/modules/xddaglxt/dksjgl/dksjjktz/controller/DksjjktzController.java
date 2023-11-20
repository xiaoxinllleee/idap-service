package org.cmms.modules.xddaglxt.dksjgl.dksjjktz.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.cmms.modules.xddaglxt.dksjgl.dksjjktz.entity.Dksjjktz;
import org.cmms.modules.xddaglxt.dksjgl.dksjjktz.entity.DksjjktzVO;
import org.cmms.modules.xddaglxt.dksjgl.dksjjktz.service.IDksjjktzService;
import org.cmms.modules.xddaglxt.dksjgl.dksjjktz.verify.DksjjktzImportVerify;
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
 * @Description: 贷款数据监控台账
 * @Author: jeecg-boot
 * @Date:   2021-11-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款数据监控台账")
@RestController
@RequestMapping("/dksjjktz/dksjjktz")
public class DksjjktzController extends JeecgController<Dksjjktz, IDksjjktzService> {
	@Autowired
	private IDksjjktzService dksjjktzService;
	@Autowired
	private DksjjktzImportVerify dksjjktzImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param dksjjktz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款数据监控台账-分页列表查询")
	@ApiOperation(value="贷款数据监控台账-分页列表查询", notes="贷款数据监控台账-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dksjjktz dksjjktz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   String jkString,String dqString,
								   HttpServletRequest req) {
		QueryWrapper<Dksjjktz> queryWrapper = QueryGenerator.initQueryWrapper(dksjjktz, req.getParameterMap());
		if (jkString != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] date = jkString.split(",");
			try {
				queryWrapper.between("jkrq",sdf.parse(date[0]),sdf.parse(date[1]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (dqString != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] date = dqString.split(",");
			try {
				queryWrapper.between("dqrq",sdf.parse(date[0]),sdf.parse(date[1]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Page<Dksjjktz> page = new Page<Dksjjktz>(pageNo, pageSize);
		IPage<Dksjjktz> pageList = dksjjktzService.page(page, queryWrapper);
		return Result.ok(pageList);
	}




	 /**
	  * 提取
	  */
	 @RequestMapping(value = "init")
	 public Result<?> init(){
	 	Result result = new Result<>();
		 try {
		 	 dksjjktzService.pDksjjktz();
		 	 result.setSuccess(true);
			 return result;
		 } catch (Throwable e) {
			 // e.printStackTrace();
			 // return Result.error("提取失败，请查看系统监控日志信息");
			 System.out.println(e);
			 log.error("提取失败", e.getMessage());
			 result.setSuccess(false);
		 }
	   return result;
	 }

	/**
	 * 添加
	 *
	 * @param dksjjktz
	 * @return
	 */
	@AutoLog(value = "贷款数据监控台账-添加")
	@ApiOperation(value="贷款数据监控台账-添加", notes="贷款数据监控台账-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dksjjktz dksjjktz) {
		dksjjktzService.save(dksjjktz);
		return Result.ok("添加成功！");
	}

	 /**
	  * 查询信息
	  */
	 @RequestMapping(value = "/query",method = RequestMethod.GET)
	 public Result<?> query(@RequestParam(name = "dkzh",required = true)String zjhm){
	 	QueryWrapper<Dksjjktz> queryWrapper = new QueryWrapper<Dksjjktz>();
	 	queryWrapper.eq("dkzh",zjhm);
		 List<Dksjjktz> list = dksjjktzService.list(queryWrapper);
		 if (list.size() > 0){
		 	return Result.ok(list.get(0));
		 }else {
		 	return Result.ok(new Dksjjktz());
		 }
	 }

	/**
	 * 编辑
	 *
	 * @param dksjjktz
	 * @return
	 */
	@AutoLog(value = "贷款数据监控台账-编辑")
	@ApiOperation(value="贷款数据监控台账-编辑", notes="贷款数据监控台账-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dksjjktz dksjjktz) {
		QueryWrapper<Dksjjktz> queryWrapper = new QueryWrapper<Dksjjktz>();
		queryWrapper.eq("dkzh",dksjjktz.getDkzh());
		dksjjktzService.update(dksjjktz,queryWrapper);
		return Result.ok("编辑成功!");
	}

	 /**
	  * 审核
	  */
	 @PutMapping(value = "/audit")
	 public Result<?> audit(@RequestBody Dksjjktz dksjjktz){
		 QueryWrapper<Dksjjktz> queryWrapper = new QueryWrapper<Dksjjktz>();
		 queryWrapper.eq("dkzh",dksjjktz.getDkzh());
		 dksjjktz.setShzt(2);
		 dksjjktzService.update(dksjjktz,queryWrapper);
		 return Result.ok("审核成功！");
	 }

	 /**
	  * 撤销审核
	  */
	 @PutMapping(value = "/unaudit")
	 public Result<?> unaudit(@RequestBody Dksjjktz dksjjktz){
		 QueryWrapper<Dksjjktz> queryWrapper = new QueryWrapper<Dksjjktz>();
		 queryWrapper.eq("dkzh",dksjjktz.getDkzh());
		 dksjjktz.setShzt(1);
		 dksjjktzService.update(dksjjktz,queryWrapper);
		 return Result.ok("撤销审核成功！");
	 }

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款数据监控台账-通过id删除")
	@ApiOperation(value="贷款数据监控台账-通过id删除", notes="贷款数据监控台账-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dksjjktzService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款数据监控台账-批量删除")
	@ApiOperation(value="贷款数据监控台账-批量删除", notes="贷款数据监控台账-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dksjjktzService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款数据监控台账-通过id查询")
	@ApiOperation(value="贷款数据监控台账-通过id查询", notes="贷款数据监控台账-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dksjjktz dksjjktz = dksjjktzService.getById(id);
		return Result.ok(dksjjktz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dksjjktz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dksjjktz dksjjktz) {
      return super.exportXls(request, dksjjktz, Dksjjktz.class, "贷款数据监控台账");
  }

	 /**
	  * 导出模板
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "贷款数据监控台账导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, DksjjktzVO.class);
		 ExportParams exportParams = new ExportParams("贷款数据监控台账导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
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
      return super.importExcelByTemplate(jsonObject, request, response, Dksjjktz.class,DksjjktzVO.class, dksjjktzImportVerify);
  }

}
