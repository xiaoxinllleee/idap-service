package org.cmms.modules.khxxgl.khflgl.nhxq.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.*;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IKhxxglKhxqXxnyztService;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IXxnyztTjfxService;
import org.cmms.modules.tjfx.pjsxtjbb.vo.NhPjsxxxMx;
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
 * @Description: 新型农业主体-统计分析
 * @Author: jeecg-boot
 * @Date:   2022-12-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="新型农业主体-统计分析")
@RestController
@RequestMapping("/tjfx/xxnyztTjfx")
public class XxnyztTjfxController extends JeecgController<XxnyztTjfx, IXxnyztTjfxService> {
	@Autowired
	private IXxnyztTjfxService xxnyztTjfxService;
	@Autowired
	IKhxxglKhxqXxnyztService khxxglKhxqXxnyztService;

	@Autowired
	RedisUtil redisUtil;
	/**
	 * ��ҳ�б��ѯ
	 *
	 * @param xxnyztTjfx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "新型农业主体-统计分析")
	@ApiOperation(value="新型农业主体-统计分析-", notes="新型农业主体-统计分析")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(XxnyztTjfx xxnyztTjfx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		//每天更新一次
		Object isUpdateClje = redisUtil.get("isUpdateClje");
		if (isUpdateClje == null){
			log.info("===每次更新一次存量金额===");
			//service.updateClje();
//			service.initSfysx();
//			log.info("===更新家庭的存量金额===");
//			service.updateClje3();
//			log.info("===更新家庭的不良存量金额===");
//			service.updateClje4();
//			log.info("===更新主体的存量金额===");
//			service.updateClje5();
//			log.info("===更新主体的不良存量金额===");
//			service.updateClje6();
			service.xxnyztTjfx();
			redisUtil.set("isUpdateClje",1,60*60*24);
		}else {
			log.info("===今天已经更新过存量金额===");
		}

		Page<XxnyztTjfx> page = new Page<XxnyztTjfx>(pageNo, pageSize);
		QueryWrapper<XxnyztTjfx> queryWrapper = QueryGenerator.initQueryWrapper(xxnyztTjfx, req.getParameterMap());
		if ("1".equals(xxnyztTjfx.getTjlx()) || StringUtils.isBlank(xxnyztTjfx.getTjlx())){

			if (getRedisRoleCode().contains("RC002") || getRedisRoleCode().contains("RZ002")){
				IPage<XxnyztTjfx> xxnyztTjfxIPage = service.tjfx1(page, getLoginUser().getOrgCode());
				return Result.ok(xxnyztTjfxIPage);
			}else {
				if (StringUtils.isBlank(xxnyztTjfx.getSszh())){
					IPage<XxnyztTjfx> xxnyztTjfxIPage = service.tjfx1(page, null);
					return Result.ok(xxnyztTjfxIPage);
				}else {
					IPage<XxnyztTjfx> xxnyztTjfxIPage = service.tjfx1(page, xxnyztTjfx.getSszh());
					return Result.ok(xxnyztTjfxIPage);
				}
			}

		}
		if ("2".equals(xxnyztTjfx.getTjlx())){
			if (getRedisRoleCode().contains("RC002") || getRedisRoleCode().contains("RZ002")){
				IPage<XxnyztTjfx> xxnyztTjfxIPage = service.tjfx2And4(page, getLoginUser().getOrgCode());
				return Result.ok(xxnyztTjfxIPage);
			}else {
				if (StringUtils.isBlank(xxnyztTjfx.getSszh())){
					IPage<XxnyztTjfx> xxnyztTjfxIPage = service.tjfx2And4(page, null);
					return Result.ok(xxnyztTjfxIPage);
				}else {
					IPage<XxnyztTjfx> xxnyztTjfxIPage = service.tjfx2And4(page, xxnyztTjfx.getSszh());
					return Result.ok(xxnyztTjfxIPage);
				}
			}
		}
		if ("3".equals(xxnyztTjfx.getTjlx())){
			if (getRedisRoleCode().contains("RC002") || getRedisRoleCode().contains("RZ002")){
				IPage<XxnyztTjfx> xxnyztTjfxIPage = service.tjfx3(page, getLoginUser().getOrgCode());
				return Result.ok(xxnyztTjfxIPage);
			}else {
				if (StringUtils.isBlank(xxnyztTjfx.getSszh())){
					IPage<XxnyztTjfx> xxnyztTjfxIPage = service.tjfx3(page, null);
					return Result.ok(xxnyztTjfxIPage);
				}else {
					IPage<XxnyztTjfx> xxnyztTjfxIPage = service.tjfx3(page, xxnyztTjfx.getSszh());
					return Result.ok(xxnyztTjfxIPage);
				}
			}
		}
		if ("4".equals(xxnyztTjfx.getTjlx())){
			IPage<XxnyztTjfx> xxnyztTjfxIPage = service.tjfx4(page);
			return Result.ok(xxnyztTjfxIPage);
		}
		IPage<XxnyztTjfx> pageList = xxnyztTjfxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}


	/**
	 * ���
	 *
	 * @param xxnyztTjfx
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody XxnyztTjfx xxnyztTjfx) {
		xxnyztTjfxService.save(xxnyztTjfx);
		return Result.ok("");
	}

	/**
	 * �༭
	 *
	 * @param xxnyztTjfx
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody XxnyztTjfx xxnyztTjfx) {
		xxnyztTjfxService.updateById(xxnyztTjfx);
		return Result.ok("");
	}

	/**
	 * ͨ��idɾ��
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		xxnyztTjfxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * ����ɾ��
	 *
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.xxnyztTjfxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * ͨ��id��ѯ
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "新型农业主体-统计分析-ͨ��id��ѯ")
	@ApiOperation(value="新型农业主体-统计分析-ͨ��id��ѯ", notes="新型农业主体-统计分析-ͨ��id��ѯ")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		XxnyztTjfx xxnyztTjfx = xxnyztTjfxService.getById(id);
		return Result.ok(xxnyztTjfx);
	}

  /**
   * ����excel
   *
   * @param request
   * @param xxnyztTjfx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, XxnyztTjfx xxnyztTjfx) {
	  System.out.println(xxnyztTjfx+"=====导出======");
	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	  List<XxnyztTjfx> xxnyztTjfxList = new ArrayList<>();
	  if ("1".equals(xxnyztTjfx.getTjlx()) || StringUtils.isBlank(xxnyztTjfx.getTjlx())) {
		  // Step.1 组装查询条件
		  QueryWrapper<XxnyztTjfx> queryWrapper = QueryGenerator.initQueryWrapper(xxnyztTjfx, request.getParameterMap());
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
		  if (getRedisRoleCode().contains("RC002") || getRedisRoleCode().contains("RZ002")){
			  xxnyztTjfxList = service.tjfx1(getLoginUser().getOrgCode());
		  }else {
			  if (StringUtils.isBlank(xxnyztTjfx.getSszh())){
				  xxnyztTjfxList = service.tjfx1(null);
			  }else {
				  xxnyztTjfxList = service.tjfx1(xxnyztTjfx.getSszh());
			  }
		  }
		  List<XxnyztTjfxZhagh> exportList = new ArrayList<>();
		  xxnyztTjfxList.forEach(e -> {
			  XxnyztTjfxZhagh xxnyztTjfx1 = new XxnyztTjfxZhagh();
			  BeanUtil.copyProperties(e, xxnyztTjfx1);
			  exportList.add(xxnyztTjfx1);
		  });
		  String title = "支行按管户";
		  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		  mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
		  mv.addObject(NormalExcelConstants.CLASS, XxnyztTjfxZhagh.class);
		  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
		  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		  return mv;
	  }else if ("2".equals(xxnyztTjfx.getTjlx())){
		  // Step.1 组装查询条件
		  QueryWrapper<XxnyztTjfx> queryWrapper = QueryGenerator.initQueryWrapper(xxnyztTjfx, request.getParameterMap());
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
		  if (getRedisRoleCode().contains("RC002") || getRedisRoleCode().contains("RZ002")){
			  xxnyztTjfxList = service.tjfx2And4(getLoginUser().getOrgCode());
		  }else {
			  if (StringUtils.isBlank(xxnyztTjfx.getSszh())){
				  xxnyztTjfxList = service.tjfx2And4(null);
			  }else {
				  xxnyztTjfxList = service.tjfx2And4(xxnyztTjfx.getSszh());
			  }
		  }
		  List<XxnyztTjfxZhaztfl> exportList = new ArrayList<>();
		  xxnyztTjfxList.forEach(e -> {
			  XxnyztTjfxZhaztfl xxnyztTjfx1 = new XxnyztTjfxZhaztfl();
			  BeanUtil.copyProperties(e, xxnyztTjfx1);
			  exportList.add(xxnyztTjfx1);
		  });
		  String title = "支行按主体分类";
		  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		  mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
		  mv.addObject(NormalExcelConstants.CLASS, XxnyztTjfxZhaztfl.class);
		  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
		  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		  return mv;
	  }else if ("3".equals(xxnyztTjfx.getTjlx())){
		  QueryWrapper<XxnyztTjfx> queryWrapper = QueryGenerator.initQueryWrapper(xxnyztTjfx, request.getParameterMap());
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
		  if (getRedisRoleCode().contains("RC002") || getRedisRoleCode().contains("RZ002")){
			  xxnyztTjfxList = service.tjfx3( getLoginUser().getOrgCode());
		  }else {
			  if (StringUtils.isBlank(xxnyztTjfx.getSszh())){
				  xxnyztTjfxList = service.tjfx3(null);
			  }else {
				  xxnyztTjfxList = service.tjfx3(xxnyztTjfx.getSszh());
			  }
		  }
		  List<XxnyztTjfxZhhz> exportList = new ArrayList<>();
		  xxnyztTjfxList.forEach(e -> {
			  XxnyztTjfxZhhz xxnyztTjfx1 = new XxnyztTjfxZhhz();
			  BeanUtil.copyProperties(e, xxnyztTjfx1);
			  exportList.add(xxnyztTjfx1);
		  });
		  String title = "支行汇总";
		  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		  mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
		  mv.addObject(NormalExcelConstants.CLASS, XxnyztTjfxZhhz.class);
		  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
		  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		  return mv;
	  } else {
		  QueryWrapper<XxnyztTjfx> queryWrapper = QueryGenerator.initQueryWrapper(xxnyztTjfx, request.getParameterMap());
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
		  xxnyztTjfxList = service.tjfx4();
		  List<XxnyztTjfxQhhz> exportList = new ArrayList<>();
		  xxnyztTjfxList.forEach(e -> {
			  XxnyztTjfxQhhz xxnyztTjfx1 = new XxnyztTjfxQhhz();
			  BeanUtil.copyProperties(e, xxnyztTjfx1);
			  exportList.add(xxnyztTjfx1);
		  });
		  String title = "全行汇总";
		  ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		  mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
		  mv.addObject(NormalExcelConstants.CLASS, XxnyztTjfxQhhz.class);
		  mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
		  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		  return mv;
	  }
  }

  /**
   * ͨ��excel��������
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, XxnyztTjfx.class);
  }


  @GetMapping("/getTjfxmx")
  public Result<?> getTjfxmx(XxnyztTjfx xxnyztTjfx, @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
							 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize){
	  log.info("===统计明细查询条件{}===",xxnyztTjfx.toString());
	  LambdaQueryWrapper<KhxxglKhxqXxnyzt> lambdaQueryWrapper = new LambdaQueryWrapper<>();
	  if (StringUtils.isNotBlank(xxnyztTjfx.getSszh())){
		  lambdaQueryWrapper.eq(KhxxglKhxqXxnyzt::getSszh,xxnyztTjfx.getSszh());
	  }
	  if (StringUtils.isNotBlank(xxnyztTjfx.getGhzrr())){
		  lambdaQueryWrapper.eq(KhxxglKhxqXxnyzt::getGhzrr,xxnyztTjfx.getGhzrr());
	  }
	  if (StringUtils.isNotBlank(xxnyztTjfx.getZtfl())){
		  lambdaQueryWrapper.eq(KhxxglKhxqXxnyzt::getZtfl,xxnyztTjfx.getZtfl());
	  }

	  if (xxnyztTjfx.getClkhs() != null){
		  lambdaQueryWrapper.eq(KhxxglKhxqXxnyzt::getSfysx,"1");
		  lambdaQueryWrapper.and(i->i.ne(KhxxglKhxqXxnyzt::getSfhmdkh,"1").or().isNull(KhxxglKhxqXxnyzt::getSfhmdkh));

	  }
	  if(xxnyztTjfx.getHmdhs()  != null){
		  lambdaQueryWrapper.eq(KhxxglKhxqXxnyzt::getSfhmdkh,"1");
		  //lambdaQueryWrapper.and(i->i.ne(KhxxglKhxqXxnyzt::getSfysx,"1").or().isNull(KhxxglKhxqXxnyzt::getSfysx));
	  }
	  if (xxnyztTjfx.getWxh() != null){
		  //lambdaQueryWrapper.eq(KhxxglKhxqXxnyzt::getSfzcjy,"2").and(i->i.ne(KhxxglKhxqXxnyzt::getSfysx,"1").or().isNull(KhxxglKhxqXxnyzt::getSfysx))
		//		  .and(i->i.ne(KhxxglKhxqXxnyzt::getSfhmdkh,"1").or().isNull(KhxxglKhxqXxnyzt::getSfhmdkh).or().eq(KhxxglKhxqXxnyzt::getPdfl,"4"));
//		  lambdaQueryWrapper.and(i->i.ne(KhxxglKhxqXxnyzt::getSfysx,"1").or().isNull(KhxxglKhxqXxnyzt::getSfysx));
		  lambdaQueryWrapper.apply("  ( (sfzcjy = 2 or pdfl = 4 ) and (sfysx != 1 or sfysx is null ) and (sfhmdkh != 1 or sfhmdkh is null) ) ");
	  }
	  if (xxnyztTjfx.getDczpdhs() != null){
			//非存量客户 非黑名单客户 非无效经营 已经村组评定
		  lambdaQueryWrapper.and(i->i.ne(KhxxglKhxqXxnyzt::getSfysx,"1").or().isNull(KhxxglKhxqXxnyzt::getSfysx));
		  lambdaQueryWrapper.and(i->i.ne(KhxxglKhxqXxnyzt::getSfhmdkh,"1").or().isNull(KhxxglKhxqXxnyzt::getSfhmdkh));
		  lambdaQueryWrapper.and(i->i.ne(KhxxglKhxqXxnyzt::getSfzcjy,"2").or().isNull(KhxxglKhxqXxnyzt::getSfzcjy));

//		  lambdaQueryWrapper.ne(KhxxglKhxqXxnyzt::getSfysx,"1").or().isNull(KhxxglKhxqXxnyzt::getSfysx);
//		  lambdaQueryWrapper.ne(KhxxglKhxqXxnyzt::getSfhmdkh,"1").or().isNull(KhxxglKhxqXxnyzt::getSfhmdkh);
//		  lambdaQueryWrapper.ne(KhxxglKhxqXxnyzt::getSfzcjy,"2").or().isNull(KhxxglKhxqXxnyzt::getSfzcjy);
		  lambdaQueryWrapper.isNull(KhxxglKhxqXxnyzt::getPdfl);

	  }
	  if (xxnyztTjfx.getDrhhdhs() != null){
		  lambdaQueryWrapper.and(i->i.ne(KhxxglKhxqXxnyzt::getSfysx,"1").or().isNull(KhxxglKhxqXxnyzt::getSfysx));
		  lambdaQueryWrapper.and(i->i.ne(KhxxglKhxqXxnyzt::getSfhmdkh,"1").or().isNull(KhxxglKhxqXxnyzt::getSfhmdkh));
		  //lambdaQueryWrapper.and(i->i.ne(KhxxglKhxqXxnyzt::getSfzcjy,"2").or().isNull(KhxxglKhxqXxnyzt::getSfzcjy));
		  lambdaQueryWrapper.eq(KhxxglKhxqXxnyzt::getSfzcjy,"1");
		  lambdaQueryWrapper.isNull(KhxxglKhxqXxnyzt::getZhfl);
		  lambdaQueryWrapper.in(KhxxglKhxqXxnyzt::getPdfl,"1","2","3");
	  }

	  if (xxnyztTjfx.getBmdhs() != null){
		  lambdaQueryWrapper.eq(KhxxglKhxqXxnyzt::getZhfl,"1");
		  lambdaQueryWrapper.and(i->i.ne(KhxxglKhxqXxnyzt::getSfysx,"1").or().isNull(KhxxglKhxqXxnyzt::getSfysx));
		  lambdaQueryWrapper.eq(KhxxglKhxqXxnyzt::getSfzcjy,"1");
		  lambdaQueryWrapper.and(i->i.ne(KhxxglKhxqXxnyzt::getSfhmdkh,"1").or().isNull(KhxxglKhxqXxnyzt::getSfhmdkh));
		  lambdaQueryWrapper.ne(KhxxglKhxqXxnyzt::getPdfl,"4");

	  }

	  if (xxnyztTjfx.getHuimdhs() != null){
		  lambdaQueryWrapper.eq(KhxxglKhxqXxnyzt::getZhfl,"2");
		  lambdaQueryWrapper.and(i->i.ne(KhxxglKhxqXxnyzt::getSfysx,"1").or().isNull(KhxxglKhxqXxnyzt::getSfysx));
		  lambdaQueryWrapper.eq(KhxxglKhxqXxnyzt::getSfzcjy,"1");
		  lambdaQueryWrapper.and(i->i.ne(KhxxglKhxqXxnyzt::getSfhmdkh,"1").or().isNull(KhxxglKhxqXxnyzt::getSfhmdkh));
		  lambdaQueryWrapper.ne(KhxxglKhxqXxnyzt::getPdfl,"4");
	  }
	  Page<KhxxglKhxqXxnyzt> page = new Page<KhxxglKhxqXxnyzt>(pageNo, pageSize);
	  IPage<KhxxglKhxqXxnyzt> pageList = khxxglKhxqXxnyztService.page(page, lambdaQueryWrapper);
	  return Result.ok(pageList);
  }


}
