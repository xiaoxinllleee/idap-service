package org.cmms.modules.hr.djpd.khjlxjpd.djpd.khjldjpd.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.hr.djpd.khjlxjpd.djpd.khjldjpd.entity.Khjldjpd;
import org.cmms.modules.hr.djpd.khjlxjpd.djpd.khjldjpd.entity.KhjldjpdHz;
import org.cmms.modules.hr.djpd.khjlxjpd.djpd.khjldjpd.mapper.KhjldjpdMapper;
import org.cmms.modules.hr.djpd.khjlxjpd.djpd.khjldjpd.service.IKhjldjpdHzService;
import org.cmms.modules.hr.djpd.khjlxjpd.djpd.khjldjpd.service.IKhjldjpdService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
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
 * @Description: 客户经理等级评定
 * @Author: jeecg-boot
 * @Date:   2021-09-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户经理等级评定")
@RestController
@RequestMapping("/khjldjpd/khjldjpd")
public class KhjldjpdController extends JeecgController<Khjldjpd, IKhjldjpdService> {
	@Autowired
	private IKhjldjpdService khjldjpdService;
	@Autowired
	private IKhjldjpdHzService khjldjpdHzService;

	@Autowired(required = false)
	private KhjldjpdMapper khjldjpdMapper;

	/**
	 * 分页列表查询
	 *
	 * @param khjldjpd
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户经理等级评定-分页列表查询")
	@ApiOperation(value="客户经理等级评定-分页列表查询", notes="客户经理等级评定-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Khjldjpd khjldjpd,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Khjldjpd> queryWrapper = QueryGenerator.initQueryWrapper(khjldjpd, req.getParameterMap());
		Page<Khjldjpd> page = new Page<Khjldjpd>(pageNo, pageSize);
		IPage<Khjldjpd> pageList = khjldjpdService.page(page, queryWrapper);
		List<Khjldjpd> records = pageList.getRecords();
		if (CollUtil.isNotEmpty(records)){
			for (int i = 0; i < records.size(); i++) {
				records.get(i).setZhmc(records.get(i).getZzbz());
			}
		}
		return Result.ok(pageList);
	}

	 /**
	  * 提取
	  */
	 @DS("eweb")
	 @RequestMapping(value = "/init")
	 public Result<?> init(@RequestBody JSONObject jsonObject){
		 String pdzq = jsonObject.getString("pdzq");
		 String pdrq = jsonObject.getString("pdrq");
		 log.info(pdzq);
		 log.info(pdrq.replaceAll("-",""));
		 try{
			 khjldjpdMapper.pKhjldjpd(pdzq,pdrq.replaceAll("-",""));
		 }catch (Throwable e){
			 e.printStackTrace();
			 return Result.error("提取失败，请查看系统监控日志信息");
		 }
		 return Result.ok("提取成功");
	 }

	/**
	 * 添加
	 *
	 * @param khjldjpd
	 * @return
	 */
	@AutoLog(value = "客户经理等级评定-添加")
	@ApiOperation(value="客户经理等级评定-添加", notes="客户经理等级评定-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Khjldjpd khjldjpd) {
		khjldjpdService.save(khjldjpd);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param khjldjpd
	 * @return
	 */
	@AutoLog(value = "客户经理等级评定-编辑")
	@ApiOperation(value="客户经理等级评定-编辑", notes="客户经理等级评定-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhjldjpdHz khjldjpd) {
		QueryWrapper<KhjldjpdHz> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("yggh",khjldjpd.getYggh());
		queryWrapper.eq("pdrq",khjldjpd.getPdrq());
		queryWrapper.eq("zzbz",khjldjpd.getZzbz());
		queryWrapper.eq("pdzq",khjldjpd.getPdzq());
		queryWrapper.eq("gwbz",khjldjpd.getGwbz());
		KhjldjpdHz check = khjldjpdHzService.getOne(queryWrapper);
		if (check == null){
			return Result.error("数据不存在！");
		}
		check.setSsdj(khjldjpd.getSsdj());
		check.setLrbz(2);
		check.setLrsj(new Date());

		khjldjpdHzService.update(khjldjpd,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理等级评定-通过id删除")
	@ApiOperation(value="客户经理等级评定-通过id删除", notes="客户经理等级评定-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khjldjpdService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户经理等级评定-批量删除")
	@ApiOperation(value="客户经理等级评定-批量删除", notes="客户经理等级评定-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khjldjpdService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户经理等级评定-通过id查询")
	@ApiOperation(value="客户经理等级评定-通过id查询", notes="客户经理等级评定-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Khjldjpd khjldjpd = khjldjpdService.getById(id);
		return Result.ok(khjldjpd);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khjldjpd
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Khjldjpd khjldjpd) {

	  QueryWrapper<Khjldjpd> queryWrapper = QueryGenerator.initQueryWrapper(khjldjpd, request.getParameterMap());
	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	  String selections = request.getParameter("selections");
	  String rowKey = request.getParameter("rowKey");


	  if (oConvertUtils.isNotEmpty(selections)) {
		  List<String> selectionList = Arrays.asList(selections.split(","));
		  if(oConvertUtils.isNotEmpty(rowKey)){
			  queryWrapper.in(rowKey,selectionList);
		  }else{
			  queryWrapper.in("ID",selectionList);
		  }
	  }

	  List<Khjldjpd> exportList = service.list(queryWrapper);
	  if (CollUtil.isNotEmpty(exportList)){
		  for (int i = 0; i < exportList.size(); i++) {
			  exportList.get(i).setZhmc(exportList.get(i).getZzbz());
		  }
	  }


	  // Step.3 AutoPoi 导出Excel
	  String title= "客户经理等级评定";
	  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
	  mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
	  mv.addObject(NormalExcelConstants.CLASS, Khjldjpd.class);
	  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
	  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
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
      return super.importExcel(request, response, Khjldjpd.class);
  }

}
