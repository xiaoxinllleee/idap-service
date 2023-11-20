package org.cmms.modules.xdgl.grdkgl.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.PermissionData;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.utils.ListToDictUtil;

import org.cmms.modules.activiti.entity.ActBusiness;
import org.cmms.modules.activiti.entity.ActXendSpls;
import org.cmms.modules.activiti.service.ActBusinessService;
import org.cmms.modules.activiti.service.IActXendSplsService;
import org.cmms.modules.dklldj.jbxxgl.khzyjl.service.IRateKhjbxxbLsService;
import org.cmms.modules.dklldj.lldjgl.lldjjs.entity.RateLldjjsVO;
import org.cmms.modules.dklldj.lldjgl.lldjsq.entity.RateDjsqxq;
import org.cmms.modules.dklldj.lldjgl.lldjsq.service.IRateDjsqxqService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;
import org.cmms.modules.word.entity.CamsZcsxFxpjsc;
import org.cmms.modules.word.service.ICamsZcsxFxpjscService;
import org.cmms.modules.xdgl.grdkgl.entity.*;
import org.cmms.modules.xdgl.grdkgl.mapper.RateLldjZhckrpMapper;
import org.cmms.modules.xdgl.grdkgl.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.xdgl.grdkgl.vo.GrdkglPage;
import org.cmms.modules.xdgl.grkhpjsx.service.IGrkhpjsxService;
import org.cmms.modules.xdgl.grkhpjsx.service.IGrpjsxspjlService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 个人贷款审批流程
 * @Author: jeecg-boot
 * @Date:   2020-08-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="个人贷款审批流程")
@RestController
@RequestMapping("/xdgl/grdkgl/grdksplc")
public class GrdksplcController extends JeecgController<Grdksplc, IGrdksplcService> {
	 @Autowired
	 private IGrdksplcService grdksplcService;
	 @Autowired
	 private IGrdkcjxxService grdkcjxxService;
	 @Autowired
	 private IGrkhpjsxService grkhpjsxService;
	 @Autowired
	 private ActBusinessService actBusinessService;
	 @Autowired
	 private IGrdkspjlService grdkspjlService;
	 @Autowired
	 ICamsZcsxFxpjscService camsZcsxFxpjscService;
	 @Autowired
	 private IGrpjsxspjlService grkhspjlService;
	 @Autowired
	 RateLldjZhckrpMapper rateLldjZhckrpMapper;
	 @Autowired
	 private IRateZxlldjbService rateZxlldjbService;
	 @Autowired
	 private IRateDjsqxxService rateDjsqxxService;
	 @Autowired
	 private IRateDbxxglService rateDbxxglService;
	 @Autowired
	 private  IRateDjsqmxService rateDjsqmxService;
	 @Autowired
	 private IRateGzbdsxxService rateGzbdsxxService;
	 @Autowired
	 private IRateLldjZhckrpService rateLldjZhckrpService;
	 @Autowired
	 private IRateLldjZhckrpAllService rateLldjZhckrpAllService;
	 @Autowired
	 private IRateZbgzxxbService rateZbgzxxbService;
	 @Autowired
	 private IRateZbkxxbService rateZbkxxbService;
	 @Autowired
	 private  IRateCsszService rateCsszService;
	 @Autowired
	 private IRateKhjbxxbLsService rateKhjbxxbLsService;
	 @Autowired
	 private ISysDictService sysDictService;
	 @Autowired
	 private ITjfxZhbyService tjfxZhbyService;
	 @Autowired
	 private ListToDictUtil  listToDictUtil;
	 @Autowired
	 private ICbsGldmService cbsGldmService;
	 @Autowired
	 private IActXendSplsService iActXendSplsService;
	 @Autowired
	 private IGrdkJtspService grdkJtspService;
	 @Autowired
	 IRateDjsqxqService rateDjsqxqService;
	 
	 /**
	 * 分页列表查询
	 *
	 * @param grdksplc
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "个人贷款审批流程-分页列表查询")
	@ApiOperation(value="个人贷款审批流程-分页列表查询", notes="个人贷款审批流程-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData(pageComponent = "xdgl/grdkgl/GrdkSplcList")
	public Result<?> queryPageList(Grdksplc grdksplc,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Grdksplc> queryWrapper = QueryGenerator.initQueryWrapper(grdksplc, req.getParameterMap());
		queryWrapper.orderByDesc("create_time");
		Page<Grdksplc> page = new Page<Grdksplc>(pageNo, pageSize);
		IPage<Grdksplc> pageList = grdksplcService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param grdksplc
	 * @return
	 */
	@AutoLog(value = "个人贷款审批流程-添加")
	@ApiOperation(value="个人贷款审批流程-添加", notes="个人贷款审批流程-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Grdksplc grdksplc) {
		grdksplcService.save(grdksplc);
		return Result.ok("添加成功！");
	}

	 /**
	  * 编辑
	  *
	  * @param grdkglPage
	  * @return
	  */
	 @PutMapping(value = "/edit")
	 public Result<?> edit(@RequestBody GrdkglPage grdkglPage) {
	     String spid="";
		 Grdkcjxx cjxx=new Grdkcjxx();
		 Grdkspjl grdkspjl=new Grdkspjl();
		 String  uid=UUID.randomUUID().toString().substring(0,32);
		 BeanUtils.copyProperties(grdkglPage, cjxx);
		 BeanUtils.copyProperties(grdkglPage, grdkspjl);
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 System.out.println("STATUX-----"+grdkspjl.getStatus());
		 if (!(grdkspjl.getStatus() == 3 || grdkspjl.getStatus() == -2)) {
			 ActBusiness actBusiness = new ActBusiness();
			 actBusiness.setUserId(sysUser.getId());
			 actBusiness.setTableId(grdkspjl.getId());
			 actBusiness.setProcDefId(grkhpjsxService.querySplcProcessId("grdksplc"));
			 actBusiness.setTitle(grdkspjl.getKhmc() + "的个人贷款申请");
			 actBusiness.setCreateBy(sysUser.getRealname());
			 actBusiness.setCreateTime(new Date());
			 actBusiness.setApplyTime(new Date());
			 actBusinessService.save(actBusiness);
			 grdkspjl.setId(uid);
			 spid=actBusiness.getId();
			 grdkspjl.setSpid(actBusiness.getId());
			 grdkspjl.setBussinessId(actBusiness.getId());
			 grdkspjl.setSqr(sysUser.getRealname());
			 grdkspjl.setUserId(grdkspjl.getId());
			 grdkspjl.setSqrq(new Date());
			 grdkspjl.setUserId(sysUser.getId());
			 grdkspjl.setProcDefId(grkhpjsxService.querySplcProcessId("grdksplc"));
			 grdkspjlService.save(grdkspjl);
			 ActXendSpls spls= new ActXendSpls();
			 spls.setSpid(actBusiness.getId());
			 spls.setPddj(grdkspjl.getPddj());
			 spls.setHhbm(grdkspjl.getHhbm());
			 spls.setZjhm(grdkspjl.getZjhm());
			 spls.setJyed(grdkglPage.getSxje());
			 spls.setSpyj(grdkglPage.getDcjl());
			 spls.setUserid(sysUser.getId());
			 spls.setYggh(sysUser.getWorkNo());
			 iActXendSplsService.save(spls);

			 cjxx.setSpid(actBusiness.getId());
		 } else {
			 spid = grdkspjl.getBussinessId();
			 grkhspjlService.deleteGrdkByspid(spid);
			 grdkspjl.setId(uid);
			 grdkspjl.setSpid(spid);
			 grdkspjl.setBussinessId(spid);
			 grdkspjl.setSqr(sysUser.getRealname());
			 grdkspjl.setUserId(sysUser.getId());
			 grdkspjl.setSqrq(new Date());
			 cjxx.setSpid(spid);
			 grdkspjlService.save(grdkspjl);
			 ActXendSpls spls= new ActXendSpls();
			 spls.setSpid(spid);
			 spls.setHhbm(grdkspjl.getHhbm());
			 spls.setZjhm(grdkspjl.getZjhm());
			 spls.setJyed(grdkglPage.getSxje());
			 spls.setPddj(grdkspjl.getPddj());
			 spls.setSpyj(grdkglPage.getDcjl());
			 spls.setUserid(sysUser.getId());
			 spls.setYggh(sysUser.getWorkNo());
			 iActXendSplsService.save(spls);
		 }
		 grdkcjxxService.updateById(cjxx);
		 grdkspjlService.extractJtspxx(uid,grdkspjl.getZjhm());
		 QueryWrapper queryWrapper = new QueryWrapper();
		 queryWrapper.eq("bussiness_id", spid);
		 Grdksplc grdksplc=grdksplcService.getOne(queryWrapper);
		 return Result.ok(grdksplc);
	 }



	 /**
	  * 编辑
	  *
	  * @param grdkglPage
	  * @return
	  */
	 @PutMapping(value = "/editFxsp")
	 public Result<?> editFxsp(@RequestBody GrdkglPage grdkglPage) {
		 String uid=UUID.randomUUID().toString().substring(0,32);
		 Grdkcjxx cjxx=new Grdkcjxx();
		 BeanUtils.copyProperties(grdkglPage, cjxx);
		 cjxx.setSpid(uid);
		 grdkcjxxService.updateById(cjxx);
		 grdksplcService.updateMain(grdkglPage,grdkglPage.getBzxxList(),grdkglPage.getDbxxList());
		 CamsZcsxFxpjsc fxpjsc =new CamsZcsxFxpjsc();
		 fxpjsc.setTjspzt("1");
		 fxpjsc.setZjhm(grdkglPage.getZjhm());
		 fxpjsc.setHhbm(grdkglPage.getHhbm());
		 fxpjsc.setSpid(uid);
		 camsZcsxFxpjscService.save(fxpjsc);
		 Grdkspjl grdkspjl=new Grdkspjl();
		 BeanUtils.copyProperties(grdkglPage, grdkspjl);
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 grdkspjl.setId(uid);
		 grdkspjl.setSpid(uid);
		 grdkspjl.setBussinessId(uid);
		 grdkspjl.setSqr(sysUser.getRealname());
		 grdkspjl.setUserId(grdkspjl.getId());
		 grdkspjl.setSqrq(new Date());
		 grdkspjl.setUserId(sysUser.getId());
		 grdkspjl.setProcDefId(grkhpjsxService.querySplcProcessId("grdksplc"));
		 grdkspjl.setStatus(1);
		 grdkspjlService.save(grdkspjl);
		 grdkspjlService.extractJtspxx(uid,grdkspjl.getZjhm());


		 return Result.ok("提交风险审批成功!");
	 }



	 /**
	  * 编辑
	  *
	  * @param grdkglPage
	  * @return
	  */
	 @PutMapping(value = "/editCxFxsp")
	 public Result<?> editCxFxsp(@RequestBody GrdkglPage grdkglPage) {
		 Grdkcjxx cjxx=new Grdkcjxx();
		 BeanUtils.copyProperties(grdkglPage, cjxx);
		 grdkcjxxService.updateById(cjxx);
		 grdksplcService.updateMain(grdkglPage,grdkglPage.getBzxxList(),grdkglPage.getDbxxList());
		 QueryWrapper queryWrapper  =new QueryWrapper();
		 queryWrapper.eq("zjhm",grdkglPage.getZjhm());
		 CamsZcsxFxpjsc fxpjsc = camsZcsxFxpjscService.getOne(queryWrapper);
		 fxpjsc.setTjspzt("1");
		 fxpjsc.setScjl(null);
		 camsZcsxFxpjscService.remove(queryWrapper);
		 camsZcsxFxpjscService.save(fxpjsc);
		 grdkspjlService.extractJtspxx(cjxx.getSpid(),grdkglPage.getZjhm());

		 return Result.ok("提交风险审批成功!");
	 }

	 /**
	  * 编辑
	  *
	  * @param grdkglPage
	  * @return
	  */
	 @PutMapping(value = "/editFxjlsp")
	 public Result<?> editFxjlsp(@RequestBody GrdkglPage grdkglPage) {
		 Grdkcjxx cjxx=new Grdkcjxx();
		 BeanUtils.copyProperties(grdkglPage, cjxx);
		 grdkcjxxService.updateById(cjxx);
		 grdksplcService.updateMain(grdkglPage,grdkglPage.getBzxxList(),grdkglPage.getDbxxList());

		 QueryWrapper queryWrapper  =new QueryWrapper();
		 queryWrapper.eq("spid",grdkglPage.getSpid());
		 CamsZcsxFxpjsc camsZcsxFxpjsc = camsZcsxFxpjscService.getOne(queryWrapper);

		 Grdkspjl grdkspjl= grdkspjlService.getById(grdkglPage.getSpid());
		 if(camsZcsxFxpjsc.getScjl().equals("1")||camsZcsxFxpjsc.getScjl().equals("2")){
			 grdkspjl.setStatus(2);
		 }else{
			 grdkspjl.setStatus(-2);
		 }
		 grdkspjl.setFxjlspyj(camsZcsxFxpjsc.getFxdwzsm());
		 grdkspjl.setFxjlspzt(camsZcsxFxpjsc.getTjspzt());
		 grdkspjlService.updateById(grdkspjl);
		 grdkspjlService.extractJtspxx(grdkglPage.getSpid(),grdkglPage.getZjhm());

		 return Result.ok("提交风险审批成功!");
	 }

	 /**
	  * 编辑
	  *
	  * @param
	  * @return
	  */
	 @RequestMapping(value = "/fxpjsc")
	 public Result<?> lldjPrint(CamsZcsxFxpjsc fxpjsc) {
         QueryWrapper queryWrapper  =new QueryWrapper();
		 queryWrapper.eq("spid",fxpjsc.getSpid());
		 CamsZcsxFxpjsc camsZcsxFxpjsc = camsZcsxFxpjscService.getOne(queryWrapper);
		 return Result.ok(camsZcsxFxpjsc);
	 }


	 /**
	  * 添加
	  *
	  * @param
	  * @return
	  */
	 @AutoLog(value = "个人贷款审批流程-添加")
	 @ApiOperation(value="个人贷款审批流程-添加", notes="个人贷款审批流程-添加")
	 @RequestMapping(value = "/addfxsc")
	 public Result<?> addfxsc(@RequestBody CamsZcsxFxpjsc fxpjsc) {
		 QueryWrapper queryWrapper  =new QueryWrapper();
		 queryWrapper.eq("zjhm",fxpjsc.getZjhm());
		 camsZcsxFxpjscService.remove(queryWrapper);
		 camsZcsxFxpjscService.save(fxpjsc);
		 return Result.ok("操作成功！");
	 }

	 /**
	  * 编辑
	  *
	  * @param
	  * @return
	  */
	 @RequestMapping(value = "/editfxsc")
	 public Result<?> edit(@RequestBody CamsZcsxFxpjsc fxpjsc) {
		 camsZcsxFxpjscService.updateById(fxpjsc);
		 return Result.ok("操作成功！");
	 }


	 /**
	  * 编辑
	  * @param
	  * @return
	  */
	 @PutMapping(value = "/editFxpjzt")
	 public Result<?> editFxpjzt(@RequestBody CamsZcsxFxpjsc fxpjsc) {
		 fxpjsc.setTjspzt("1");
		 QueryWrapper queryWrapper  =new QueryWrapper();
		 queryWrapper.eq("zjhm",fxpjsc.getZjhm());
		 camsZcsxFxpjscService.remove(queryWrapper);
		 camsZcsxFxpjscService.save(fxpjsc);
		 return Result.ok("操作成功！");
	 }



	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "个人贷款审批流程-通过id删除")
	@ApiOperation(value="个人贷款审批流程-通过id删除", notes="个人贷款审批流程-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		grdksplcService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "个人贷款审批流程-批量删除")
	@ApiOperation(value="个人贷款审批流程-批量删除", notes="个人贷款审批流程-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.grdksplcService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "个人贷款审批流程-通过id查询")
	@ApiOperation(value="个人贷款审批流程-通过id查询", notes="个人贷款审批流程-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Grdksplc grdksplc = grdksplcService.getById(id);
		return Result.ok(grdksplc);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param grdksplc
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Grdksplc grdksplc) {
      return super.exportXls(request, grdksplc, Grdksplc.class, "个人贷款授信");
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
      return super.importExcel(request, response, Grdksplc.class);
  }



	 @RequestMapping(value = "/lldjjs/add")
	 public Result<?> lldjjsAdd(@RequestBody JSONObject jsonObject){
  	     String zjhm = jsonObject.getString("zjhm");
  	     String isPrint = jsonObject.getString("print");
  	     String reCalc = jsonObject.getString("reCalc");
  	     Date djnf = jsonObject.getObject("djnf",new Date().getClass());
		 String yyyy = DateUtil.format(djnf, "yyyy");

		 //先去校验已经定价没有
		 QueryWrapper jy = new QueryWrapper();
		 jy.eq("zjhm",zjhm);
		 jy.eq("djnf",DateUtil.beginOfYear(djnf));

		 List<RateZxlldjb> list1 = rateZxlldjbService.list(jy);
		 RateLldjjsVO rateLldjjsVO = new RateLldjjsVO();

		 if (StringUtils.isBlank(reCalc)){

			 if (CollUtil.isNotEmpty(list1) && org.apache.commons.lang3.StringUtils.isBlank(isPrint)){
				 return Result.error("该客户于本年度已有定价信息！");
			 }

		 }else {
		 	rateLldjjsVO.setReCalc(reCalc);
		 }


		 Result<?> querylldjxq = querylldjxq(jsonObject);
		 if (querylldjxq.isSuccess()){


			 if (list1 != null && list1.size() > 0){
			 	rateLldjjsVO.setRateZxlldjb(list1.get(0));
			 }
			 QueryWrapper queryWrapper = new QueryWrapper();
			 queryWrapper.eq("ZJHM",zjhm);
			 queryWrapper.eq("djnf",yyyy);

			 List<RateDjsqxq> list = rateDjsqxqService.list(queryWrapper);
			 RateDjsqxq rateDjsqxq = new RateDjsqxq();
			 if (CollUtil.isNotEmpty(list)){
				 rateDjsqxq = list.get(0);
				 BeanUtil.copyProperties(rateDjsqxq,rateLldjjsVO);
			 }else {
			 	return Result.error("请先去提交利率定价申请");
			 }


			 JSONObject result = (JSONObject)querylldjxq.getResult();
			 //信用等级
			 String xydjdf = result.getJSONObject("zbxx").getString("DF_KH00001");
			 rateLldjjsVO.setXydjdf(xydjdf);
			 //扣分项目
			 String dfkh00002 = result.getJSONObject("zbxx").getString("DF_KH00002");
			 rateLldjjsVO.setDfkh00002(dfkh00002);
			 String dfkh00003 = result.getJSONObject("zbxx").getString("DF_KH00003");
			 rateLldjjsVO.setDfkh00003(dfkh00003);
			 String dfkh00004 = result.getJSONObject("zbxx").getString("DF_KH00004");
			 rateLldjjsVO.setDfkh00004(dfkh00004);
			 String dfkh00005 = result.getJSONObject("zbxx").getString("DF_KH00005");
			 rateLldjjsVO.setDfkh00005(dfkh00005);

			 String dfkh00007 = result.getJSONObject("zbxx").getString("DF_KH00007");
			 rateLldjjsVO.setDfkh00007(dfkh00007);
			 String dfkh00008 = result.getJSONObject("zbxx").getString("DF_KH00008");
			 rateLldjjsVO.setDfkh00008(dfkh00008);
			 String dfkh00009 = result.getJSONObject("zbxx").getString("DF_KH00009");
			 rateLldjjsVO.setDfkh00009(dfkh00009);
			 String dfkh00010 = result.getJSONObject("zbxx").getString("DF_KH00010");
			 rateLldjjsVO.setDfkh00010(dfkh00010);
			 String dbzdf = result.getJSONObject("zbxx").getString("dbzdf");
			 rateLldjjsVO.setDbzdf(dbzdf);
			 String dfhj = result.getJSONObject("zbxx").getString("dfhj");
			 rateLldjjsVO.setDfhj(new BigDecimal(dfhj));
			 //查表幅度
			 String cbfd = result.getJSONObject("zbxx").getString("cbfd");
			 rateLldjjsVO.setCbfd(new BigDecimal(cbfd));
			 String jyhfd = result.getJSONObject("zbxx").getString("jyhfd");
			 rateLldjjsVO.setJyhfd(new BigDecimal(jyhfd));
			 String sffd = result.getJSONObject("zbxx").getString("sffd");
			 rateLldjjsVO.setSffd(new BigDecimal(sffd));
			 String dyjzlv = result.getString("dyjzlv");
			 rateLldjjsVO.setDyjzlv(new BigDecimal(dyjzlv));
			 //执行利率
			 String zxll = result.getJSONObject("zbxx").getString("zxll");
			 rateLldjjsVO.setZxll(new BigDecimal(zxll));
			 //客户存款贡献
			 String khgxdf = result.getJSONObject("zbxx").getString("KHGXDF");
			 rateLldjjsVO.setKhgxdf(khgxdf);

			 String sndkjdbp = result.getJSONObject("zbxx").getString("sndkjdbp");
			 if (org.apache.commons.lang3.StringUtils.isBlank(sndkjdbp)){
			 	rateLldjjsVO.setSndkjdbp(new BigDecimal(0));
			 }else {
				 rateLldjjsVO.setSndkjdbp(new BigDecimal(sndkjdbp));
			 }

			 String jdbp = result.getJSONObject("zbxx").getString("lprjd");
			 rateLldjjsVO.setJdbp(new BigDecimal(jdbp));
			 String YhhLprjd = result.getJSONObject("zbxx").getString("YhhLprjd");
			 rateLldjjsVO.setYhhjdbp(new BigDecimal(YhhLprjd));
			 String Dyyhjdcs = result.getJSONObject("zbxx").getString("Dyyhjdcs");
			 rateLldjjsVO.setLprll(new BigDecimal(Dyyhjdcs));
			 String YhhZxll = result.getJSONObject("zbxx").getString("YhhZxll");
			 rateLldjjsVO.setYhhzxll(new BigDecimal(YhhZxll));

			 String jzll = result.getJSONObject("zbxx").getString("jzll");
			 rateLldjjsVO.setJjll(new BigDecimal(jzll));

			 //获取打印的字典的中文值
			 if(isPrint != null){
				 rateLldjjsVO.setDkqxVal(sysDictService.queryDictTextByKey("dkqx", rateLldjjsVO.getDkqx().toString()));
				 rateLldjjsVO.setXydjVal(sysDictService.queryDictTextByKey("rate_xydj", rateLldjjsVO.getXydj()));
				 rateLldjjsVO.setJyqxVal(sysDictService.queryDictTextByKey("rate_jyqx", rateLldjjsVO.getJyqx()));
				 rateLldjjsVO.setGz00031Val(sysDictService.queryDictTextByKey("lldj_khnx", rateLldjjsVO.getGz00031()));
				 rateLldjjsVO.setGz00047Val(sysDictService.queryDictTextByKey("lldj_gjye", rateLldjjsVO.getGz00047()));
				 rateLldjjsVO.setKhlxVal(sysDictService.queryDictTextByKey("lldj_khlx", rateLldjjsVO.getKhlx()));
			 }

			 return Result.ok(rateLldjjsVO);
		 }

		 return Result.error(querylldjxq.getMessage());
	 }




	 /**
	  * 查询利率定价详情
	  *
	  * @return
	  */
	 @RequestMapping(value = "/querylldjxq",method = RequestMethod.PUT)
	 public Result<?> querylldjxq(@RequestBody JSONObject jsonObject){
		 String qydm = sysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101001");
		 JSONObject jsonObject1 = new JSONObject();
		 JSONObject jsonObject2 = new JSONObject();
		 String isSkip = jsonObject.getString("skip");
		 String reCalc = jsonObject.getString("reCalc");
		 String isPrint = jsonObject.getString("print");
		 HashMap hashMap = new HashMap();
		 try {

			 Date djnf = jsonObject.getObject("djnf",new Date().getClass());
			 Map<String,String[]> map = new HashMap<>();

			 Date date = new Date();
			 String qybm = qydm;
			 Date ncrq= org.cmms.common.util.DateUtil.getFirstMonth_Year(date,0);
			 RateZxlldjb  rateZxlldjb = rateZxlldjbService.queryzxlldjb(jsonObject.getString("zjhm"),ncrq);

			 if (StringUtils.isBlank(reCalc)){

				 if (rateZxlldjb != null && org.apache.commons.lang3.StringUtils.isBlank(isPrint)){
					 return Result.error("该用户本年度已经定价，请查看！");
				 }
			 }


			 if (StringUtils.isBlank(isSkip))
				 return Result.error("该客户暂无定价信息！");

			 jsonObject1.put("rateZxlldjb",rateZxlldjb);

			 RateDjsqxx rateDjsqxx = rateDjsqxxService.querydjsqxx(jsonObject.getString("zjhm"),ncrq);
			 if (rateDjsqxx==null) {
				 return Result.error("未查询到对应的申请信息！");
			 }
			 jsonObject1.put("dkqxOptions",rateDjsqxx.getDkqx()== null ? "" : sysDictService.queryDictTextByKey("dkqx",rateDjsqxx.getDkqx().toString()));
			 jsonObject1.put("khlxOptions",rateDjsqxx.getKhlx()== null ? "" : sysDictService.queryDictTextByKey("lldjkhlx",rateDjsqxx.getKhlx().toString()));
			 jsonObject1.put("sfbmkOptions",rateDjsqxx.getSfbmk()== null ? "" : sysDictService.queryDictTextByKey("sfbz",rateDjsqxx.getSfbmk().toString()));
			 jsonObject1.put("sfbzbxdkOptions",rateDjsqxx.getSfbzbxdk()== null ? "" : sysDictService.queryDictTextByKey("sfbz",rateDjsqxx.getSfbzbxdk().toString()));
			 jsonObject1.put("rateDjsqxx",rateDjsqxx);

			 //查询指标库信息
			 List<RateZbkxxb> zbkxxbList = rateZbkxxbService.queryzbkxxb(qydm);
			 jsonObject1.put("zbkxxbList",zbkxxbList);
			 //查询指标规则信息
			 RateZbgzxxb rateZbgzxxb = new RateZbgzxxb();
			 rateZbgzxxb.setQydm(qydm);
			 QueryWrapper<RateZbgzxxb> queryWrapper = QueryGenerator.initQueryWrapper(rateZbgzxxb,map);
			 List<RateZbgzxxb> rateZbgzxxbList = rateZbgzxxbService.list(queryWrapper);
			 jsonObject1.put("rateZbgzxxbList",rateZbgzxxbList);

			 //查询规则表达式信息
			 RateGzbdsxx rateGzbdsxx = new RateGzbdsxx();
			 rateGzbdsxx.setQydm(qydm);
			 QueryWrapper<RateGzbdsxx> queryWrapper1 = QueryGenerator.initQueryWrapper(rateGzbdsxx,map);
			 List<RateGzbdsxx> rateGzbdsxxList = rateGzbdsxxService.list(queryWrapper1);
			 jsonObject1.put("rateGzbdsxxList",rateGzbdsxxList);

			 //查询担保信息
			 RateDbxxgl dbxxgl = new RateDbxxgl();
			 dbxxgl.setZjhm(jsonObject.getString("zjhm"));
			 dbxxgl.setDjnf(ncrq);
			 QueryWrapper<RateDbxxgl> queryWrapper2 = QueryGenerator.initQueryWrapper(dbxxgl,map);
			 List<RateDbxxgl> rateDbxxglList = rateDbxxglService.list(queryWrapper2);
			 rateDbxxglList = listToDictUtil.parseDictText(rateDbxxglList);
			 jsonObject1.put("rateDbxxglList",rateDbxxglList);

			 //查询活期期存款明细
			 RateLldjZhckrpAll hqckrp = new RateLldjZhckrpAll();
			 hqckrp.setTjyf(ncrq);
			 hqckrp.setZjhm(jsonObject.getString("zjhm"));
			 hqckrp.setZhlx(1);
			 QueryWrapper<RateLldjZhckrpAll> queryWrapper3 = QueryGenerator.initQueryWrapper(hqckrp,map);
			 List<RateLldjZhckrpAll> hqcqLldjZhckrpList = rateLldjZhckrpAllService.list(queryWrapper3);
			 hqcqLldjZhckrpList = listToDictUtil.parseDictText(hqcqLldjZhckrpList);
			 jsonObject1.put("rateLldjZhckrpList",hqcqLldjZhckrpList);


			 //查询定期存款明细
			 RateLldjZhckrpAll dqckrp = new RateLldjZhckrpAll();
			 dqckrp.setTjyf(ncrq);
			 dqckrp.setZjhm(jsonObject.getString("zjhm"));
			 dqckrp.setZhlx(2);
			 QueryWrapper<RateLldjZhckrpAll> queryWrapper4 = QueryGenerator.initQueryWrapper(dqckrp,map);
			 List<RateLldjZhckrpAll> dqZhckrpList = rateLldjZhckrpAllService.list(queryWrapper4);
			 dqZhckrpList = listToDictUtil.parseDictText(dqZhckrpList);
			 jsonObject1.put("dqZhckrpList",dqZhckrpList);


			 //查询保证金存款明细
			 BigDecimal bzjckrp = new BigDecimal(0);
			 RateLldjZhckrpAll bzjckmx = new RateLldjZhckrpAll();
			 bzjckmx.setTjyf(ncrq);
			 bzjckmx.setZjhm(jsonObject.getString("zjhm"));
			 bzjckmx.setZhlx(3);
			 QueryWrapper<RateLldjZhckrpAll> queryWrapper5 = QueryGenerator.initQueryWrapper(bzjckmx,map);
			 List<RateLldjZhckrpAll> bzjckmxlist = rateLldjZhckrpAllService.list(queryWrapper4);
			 bzjckmxlist = listToDictUtil.parseDictText(bzjckmxlist);

			 for (RateLldjZhckrpAll rateLldjZhckrp : bzjckmxlist) {
				 rateLldjZhckrp.setQsndynckrp(null);
				 rateLldjZhckrp.setQsndenckrp(null);
				 rateLldjZhckrp.setQsndsnckrp(null);
				 if (rateLldjZhckrp.getCkzh().endsWith("8")){
					 CbsGldm cbsGldm = new CbsGldm();
					 cbsGldm.setKey1(rateLldjZhckrp.getCkzh());
					 QueryWrapper<CbsGldm> queryWrapper6 = QueryGenerator.initQueryWrapper(cbsGldm,map);
					 CbsGldm cbsGldm1=cbsGldmService.getOne(queryWrapper6);
					 if (cbsGldm1!=null){
						 String kmh = "";
//                                LangUtil.coverNull(lyzh_nbfhb.getKmh());
						 if (kmh.equalsIgnoreCase("201401")
								 || kmh.equalsIgnoreCase("201402")
								 || kmh.equalsIgnoreCase("201403")
								 || kmh.equalsIgnoreCase("201404")
								 || kmh.equalsIgnoreCase("201499")
						 ){

						 }
					 }
				 }
			 }
			 jsonObject1.put("rateLldjZhckrp",bzjckmxlist);

			 RateZbgzxxb gzxxb = new  RateZbgzxxb();
			 if(rateDjsqxx.getDkqx()==1){
				 gzxxb = rateZbgzxxbService.queryzbgzxxb(qydm,"KH00011","GZ00052");
			 }else if(rateDjsqxx.getDkqx()==2){
				 gzxxb = rateZbgzxxbService.queryzbgzxxb(qydm,"KH00011","GZ00051");
			 }else{
				 gzxxb = rateZbgzxxbService.queryzbgzxxb(qydm,"KH00011","GZ00050");
			 }
			 jsonObject1.put("dyjzlv",gzxxb.getZbjg());

			 jsonObject2.put("hqckrp", hqcqLldjZhckrpList);
			 jsonObject2.put("dqckrp", dqZhckrpList);
			 jsonObject2.put("bzjckmx", bzjckmxlist);
			 jsonObject2.put("bzjckrp", bzjckrp);
			 jsonObject2.put("zbkxxb",zbkxxbList);
			 jsonObject2.put("gzxxb",rateZbgzxxbList);
			 jsonObject2.put("gzbds",rateGzbdsxxList);
			 jsonObject2.put("dbxxTable",rateDbxxglList);

			 RateDjsqmx rateDjsqmx = new RateDjsqmx();
			 rateDjsqmx.setDjnf(ncrq);
			 rateDjsqmx.setZjhm(jsonObject.getString("zjhm"));
			 QueryWrapper<RateDjsqmx> queryWrapper6 = QueryGenerator.initQueryWrapper(rateDjsqmx,map);
			 List<RateDjsqmx> rateDjsqmxList = rateDjsqmxService.list(queryWrapper6);
			 for (RateDjsqmx djsqmx : rateDjsqmxList) {
				 jsonObject2.put(djsqmx.getZbgzid(),djsqmx.getZbgzjg());
			 }
			 jsonObject2.put("table", rateDjsqxx);
			 jsonObject2.put("zxlldjbTable", rateZxlldjb);
			 LinkedHashMap linkedHashMap = new LinkedHashMap();
			 linkedHashMap.put("1","40");
			 linkedHashMap.put("2","32");
			 linkedHashMap.put("9","36");
			 linkedHashMap.put("10","28");
			 linkedHashMap.put("3","24");
			 linkedHashMap.put("4","20");
			 linkedHashMap.put("5","16");
			 linkedHashMap.put("6","12");
			 linkedHashMap.put("7","8");
			 linkedHashMap.put("8","0");
			 try {
				 compute(ncrq,jsonObject.getString("zjhm"), jsonObject2, rateDjsqxx,linkedHashMap);
				 jsonObject1.put("zbxx",jsonObject2);
			 } catch (Throwable throwable) {
				 throwable.printStackTrace();
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 return Result.ok(jsonObject1);
	 }






	 /**
	  * 计算得分
	  */
	 public void compute(Date djnf, String zjhm, JSONObject view, RateDjsqxx djsqxx, LinkedHashMap dbdfMap) throws Throwable{
		 String qydm = sysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101001");
		 double dfhj = 0d; //得分合计
		 DecimalFormat format0Decimal = new DecimalFormat("#0");
		 DecimalFormat format2Decimal = new DecimalFormat("#0.00");
		 DecimalFormat format4Decimal = new DecimalFormat("#0.0000");

		 double zcfzl = 0d; //资产负债率
		 int zxbljl = 0; //征信不良记录
		 int sfyqzj = 0;
		 double xypj=0;
		 double khsygxdf=0d;
		 double khckgxdf=0d;
		 double khdlywdf=0d;

		 Map<String,String[]> map = new HashMap<>();

		 try {
			 RateZbkxxb zbkxxb = new RateZbkxxb();
			 zbkxxb.setQydm(qydm);
			 QueryWrapper<RateZbkxxb> queryWrapper = QueryGenerator.initQueryWrapper(zbkxxb,map);
			 List<RateZbkxxb> rateZbkxxbList = rateZbkxxbService.list(queryWrapper);
			 for (RateZbkxxb rateZbkxxb : rateZbkxxbList) {
				 log.debug("LLDJJS:::::::3" + zbkxxb.getZbid());
				 double zbgzfzKH = 0d;
				 String zbid = rateZbkxxb.getZbid();
				 int llfs =  Integer.valueOf(rateZbkxxb.getLlfs()).intValue();
				 if (llfs == 1) { //单选框
					 //获取选择的选项对应的分值
					 RateDjsqmx djsqmx = new RateDjsqmx();
					 djsqmx.setDjnf(djnf);
					 djsqmx.setZjhm(zjhm);
					 djsqmx.setZbgzid(zbid);
					 QueryWrapper<RateDjsqmx> queryWrapper1 = QueryGenerator.initQueryWrapper(djsqmx,map);
					 RateDjsqmx rateDjsqmx = rateDjsqmxService.getOne(queryWrapper1);
					 if (rateDjsqmx!= null) {
						 String zbgzjg = rateDjsqmx.getZbgzjg();
						 RateZbgzxxb zbgzxxb = new RateZbgzxxb();
						 zbgzxxb.setQydm(qydm);
						 zbgzxxb.setZbid(zbid);
						 zbgzxxb.setZbgzid(zbgzjg);
						 QueryWrapper<RateZbgzxxb> queryWrapper2 = QueryGenerator.initQueryWrapper(zbgzxxb,map);
						 RateZbgzxxb rateZbgzxxb = rateZbgzxxbService.getOne(queryWrapper2);
						 if (rateZbgzxxb !=null) {
							 double zbabs =Double.parseDouble(rateZbgzxxb.getZbabs());
							 zbgzfzKH = rateZbgzxxb.getZbgzfz();
							 zbgzfzKH = zbabs*zbgzfzKH;
							 view.put(zbid,rateZbgzxxb.getZbgzmc());
						 }
					 }

				 } else if (llfs == 2) {//文本框
					 if ("KH00002".equalsIgnoreCase(zbid)) {//扣分项
						 RateZbgzxxb zbgzxxb = new RateZbgzxxb();
						 zbgzxxb.setQydm(qydm);
						 zbgzxxb.setZbid(zbid);
						 QueryWrapper<RateZbgzxxb> queryWrapper2 = QueryGenerator.initQueryWrapper(zbgzxxb,map);
						 List<RateZbgzxxb> rateZbgzxxb = rateZbgzxxbService.list(queryWrapper2);
						 for (RateZbgzxxb rateZbgzxxb1 : rateZbgzxxb) {
							 String zbgzid = rateZbgzxxb1.getZbgzid();
							 double zbabs = Double.parseDouble(rateZbgzxxb1.getZbabs());
							 double zbgzfz = rateZbgzxxb1.getZbgzfz();
							 RateDjsqmx djsqmx = new RateDjsqmx();
							 djsqmx.setDjnf(djnf);
							 djsqmx.setZjhm(zjhm);
							 djsqmx.setZbgzid(zbgzid);
							 QueryWrapper<RateDjsqmx> queryWrapper1 = QueryGenerator.initQueryWrapper(djsqmx,map);
							 RateDjsqmx rateDjsqmx = rateDjsqmxService.getOne(queryWrapper1);

							 if (rateDjsqmx != null) {
								 double zbgzjg = Double.valueOf(rateDjsqmx.getZbgzjg() == null ? "0" : rateDjsqmx.getZbgzjg());
								 if ("GZ00009".equalsIgnoreCase(zbgzid)) {
									 zxbljl = Double.valueOf(zbgzjg).intValue();
								 }
								 if(zbgzjg==0){
									 zbgzfz = 0;
								 }else{
									 zbgzfz = zbabs*zbgzfz*zbgzjg;
								 }
								 //zbgzfzKH += zbgzfz; 20180302修改 只保留两项纪录 所以只加这两项的份
								 if ("GZ00009".equalsIgnoreCase(zbgzid)||"GZ00010".equalsIgnoreCase(zbgzid)) {
									 zbgzfzKH += zbgzfz;
								 }
								 view.put(zbgzid, zbgzfz);
								 view.put("CS_" + zbgzid, Double.valueOf(zbgzjg).intValue());
							 }
						 }
						 if(zbgzfzKH==0d){
							 view.put("KFX_KH00002", "0.0");
						 }else{
							 view.put("KFX_KH00002",Double.valueOf(format2Decimal.format(zbgzfzKH)).doubleValue());
						 }
						 xypj+=zbgzfzKH;
					 } else if ("KH00003".equalsIgnoreCase(zbid)) {//资产负债率


						 RateZbgzxxb zbgzxxb = new RateZbgzxxb();
						 zbgzxxb.setQydm(qydm);
						 zbgzxxb.setZbid(zbid);
						 zbgzxxb.setZbgzid("GZ00015");
						 QueryWrapper<RateZbgzxxb> queryWrapper2 = QueryGenerator.initQueryWrapper(zbgzxxb,map);
						 RateZbgzxxb rateZbgzxxb = rateZbgzxxbService.getOne(queryWrapper2);

						 if (rateZbgzxxb!=null) {
							 String zbgzid = rateZbgzxxb.getZbgzid();
							 double zbabs = Double.parseDouble(rateZbgzxxb.getZbabs());
							 double zbgzfz = rateZbgzxxb.getZbgzfz();
							 RateDjsqmx djsqmx = new RateDjsqmx();
							 djsqmx.setDjnf(djnf);
							 djsqmx.setZjhm(zjhm);
							 djsqmx.setZbgzid(zbgzid);
							 QueryWrapper<RateDjsqmx> queryWrapper1 = QueryGenerator.initQueryWrapper(djsqmx,map);
							 RateDjsqmx rateDjsqmx = rateDjsqmxService.getOne(queryWrapper1);
							 if (rateDjsqmx !=null) {
								 double zbgzjg = Double.parseDouble(rateDjsqmx.getZbgzjg() == null ? "0" : rateDjsqmx.getZbgzjg());
								 view.put("CS_GZ00015", Double.valueOf(zbgzjg).doubleValue());
								 zcfzl = zbgzjg;
								 //modify by liuwei 2019-3-7 14:09:09 浏阳需求变动
								 //资产负债率总分2分，资产负债率在30%以内按分值100%计分，
								 // 30-50%按分值60%计分，50-70%按分值30%计分，70%以上不计分。
								 if (zbgzjg <= 30) {
									 zbgzfzKH = zbgzfz;
								 } else if (zbgzjg > 30 && zbgzjg <= 50) {
									 zbgzfzKH = zbgzfz * 0.6;
								 } else if (zbgzjg > 50 && zbgzjg <= 70) {
									 zbgzfzKH = zbgzfz * 0.3;
								 } else {
									 zbgzfzKH = 0;
								 }
							 }
						 }
						 view.put("KFX_KH00003",zbgzfzKH);
						 xypj+=zbgzfzKH;

						 RateDjsqmx djsqmx = new RateDjsqmx();
						 djsqmx.setDjnf(djnf);
						 djsqmx.setZjhm(zjhm);
						 djsqmx.setZbgzid("GZ00013");
						 QueryWrapper<RateDjsqmx> queryWrapper1 = QueryGenerator.initQueryWrapper(djsqmx,map);
						 RateDjsqmx rateDjsqmx = rateDjsqmxService.getOne(queryWrapper1);
						 if (rateDjsqmx != null) {
							 double zbgzjg = Double.parseDouble(rateDjsqmx.getZbgzjg() == null ? "0" : rateDjsqmx.getZbgzjg());
							 view.put("CS_GZ00013", Double.valueOf(zbgzjg).intValue());
						 }

						 RateDjsqmx djsqmx2 = new RateDjsqmx();
						 djsqmx2.setDjnf(djnf);
						 djsqmx2.setZjhm(zjhm);
						 djsqmx2.setZbgzid("GZ00014");
						 QueryWrapper<RateDjsqmx> queryWrapper3 = QueryGenerator.initQueryWrapper(djsqmx2,map);
						 RateDjsqmx rateDjsqmx2 = rateDjsqmxService.getOne(queryWrapper3);
						 if (rateDjsqmx2 != null) {
							 double zbgzjg = Double.parseDouble(rateDjsqmx2.getZbgzjg() == null ? "0" : rateDjsqmx2.getZbgzjg());
							 view.put("CS_GZ00014", Double.valueOf(zbgzjg).intValue());
						 }
					 }else if ("KH00005".equalsIgnoreCase(zbid)) {//销售收入
						 //个人客户不计分

						 RateDjsqmx xssrh1 = new RateDjsqmx();
						 xssrh1.setDjnf(djnf);
						 xssrh1.setZjhm(zjhm);
						 xssrh1.setZbgzid("GZ00021");
						 QueryWrapper<RateDjsqmx> queryWrapper1 = QueryGenerator.initQueryWrapper(xssrh1,map);
						 RateDjsqmx rateDjsqmx = rateDjsqmxService.getOne(queryWrapper1);
						 if (rateDjsqmx != null) {
							 double zbgzjg = Double.parseDouble(rateDjsqmx.getZbgzjg() == null ? "0" : rateDjsqmx.getZbgzjg());
							 view.put("CS_GZ00021", Double.valueOf(zbgzjg).intValue());
						 }


						 RateDjsqmx xssrh2 = new RateDjsqmx();
						 xssrh2.setDjnf(djnf);
						 xssrh2.setZjhm(zjhm);
						 xssrh2.setZbgzid("GZ00022");
						 QueryWrapper<RateDjsqmx> queryWrapper4 = QueryGenerator.initQueryWrapper(xssrh2,map);
						 RateDjsqmx rateDjsqmx2 = rateDjsqmxService.getOne(queryWrapper4);
						 if (rateDjsqmx2 != null) {
							 double zbgzjg = Double.parseDouble(rateDjsqmx2.getZbgzjg() == null ? "0" : rateDjsqmx2.getZbgzjg());
							 view.put("CS_GZ00022", Double.valueOf(zbgzjg).intValue());
						 }

						 RateDjsqmx xssrh3 = new RateDjsqmx();
						 xssrh3.setDjnf(djnf);
						 xssrh3.setZjhm(zjhm);
						 xssrh3.setZbgzid("GZ00023");
						 QueryWrapper<RateDjsqmx> queryWrapper5 = QueryGenerator.initQueryWrapper(xssrh3,map);
						 RateDjsqmx rateDjsqmx3 = rateDjsqmxService.getOne(queryWrapper5);
						 if (rateDjsqmx3 != null ) {
							 double zbgzjg = Double.parseDouble(rateDjsqmx3.getZbgzjg() == null ? "0" : rateDjsqmx3.getZbgzjg());
							 view.put("CS_GZ00023", Double.valueOf(zbgzjg).doubleValue());
						 }

						 int khlx = djsqxx.getKhlx().intValue();
						 if(khlx == 2) {
							 RateZbgzxxb zbgzxxb = new RateZbgzxxb();
							 zbgzxxb.setQydm(qydm);
							 zbgzxxb.setZbid(zbid);
							 zbgzxxb.setZbgzid("GZ00023");
							 QueryWrapper<RateZbgzxxb> queryWrapper2 = QueryGenerator.initQueryWrapper(zbgzxxb,map);
							 RateZbgzxxb rateZbgzxxb = rateZbgzxxbService.getOne(queryWrapper2);


							 if (rateZbgzxxb!=null) {
								 String zbgzid = rateZbgzxxb.getZbgzid();
								 double zbabs = Double.valueOf(rateZbgzxxb.getZbabs());
								 double zbgzfz = rateZbgzxxb.getZbgzfz();

								 RateDjsqmx djsqmx = new RateDjsqmx();
								 djsqmx.setDjnf(djnf);
								 djsqmx.setZjhm(zjhm);
								 djsqmx.setZbgzid(zbgzid);
								 QueryWrapper<RateDjsqmx> queryWrapper6 = QueryGenerator.initQueryWrapper(djsqmx,map);
								 RateDjsqmx rateDjsqmx4 = rateDjsqmxService.getOne(queryWrapper6);
								 if (rateDjsqmx4 != null) {
									 double zbgzjg = Double.parseDouble(rateDjsqmx4.getZbgzjg() == null ? "0" : rateDjsqmx4.getZbgzjg());
									 //modify by liuwei 2019-3-7 14:12:01 浏阳需求变动
									 //销售收入归行总分2分，公司客户年销售收入在其流动负债的5倍以上按分值100%计分，
									 // 4倍以上按分值60%计分，3倍以上按分值30%计分。个人贷款该项目不计分。
									 if (zbgzjg >= 3 && zbgzjg < 4) {
										 zbgzfzKH = zbgzfz * 0.3;
									 } else if (zbgzjg >= 4 && zbgzjg < 5) {
										 zbgzfzKH = zbgzfz * 0.6;
									 } else if (zbgzjg >= 5) {
										 zbgzfzKH = zbgzfz;
									 } else {
										 zbgzfzKH = 0;
									 }
								 }
							 }
						 }
					 } else if ("KH00007".equalsIgnoreCase(zbid)) {//客户存款贡献

						 RateDjsqmx ckgx1 = new RateDjsqmx();
						 ckgx1.setDjnf(djnf);
						 ckgx1.setZjhm(zjhm);
						 ckgx1.setZbgzid("GZ00031");
						 QueryWrapper<RateDjsqmx> queryWrapper4 = QueryGenerator.initQueryWrapper(ckgx1,map);
						 RateDjsqmx rateDjsqmx = rateDjsqmxService.getOne(queryWrapper4);
						 if (rateDjsqmx != null) {
							 double zbgzjg = Double.parseDouble(rateDjsqmx.getZbgzjg() == null ? "0" : rateDjsqmx.getZbgzjg());
							 if(Double.valueOf(zbgzjg).intValue()==1){
								 view.put("CS_GZ00031", "一年以内。");
							 }else{
								 view.put("CS_GZ00031", "一年以上。");
							 }
						 }

						 RateDjsqmx ckgx3 = new RateDjsqmx();
						 ckgx3.setDjnf(djnf);
						 ckgx3.setZjhm(zjhm);
						 ckgx3.setZbgzid("GZ00033");
						 QueryWrapper<RateDjsqmx> queryWrapper5 = QueryGenerator.initQueryWrapper(ckgx3,map);
						 RateDjsqmx rateDjsqmx1 = rateDjsqmxService.getOne(queryWrapper5);
						 if (rateDjsqmx1 != null) {
							 double zbgzjg = Double.parseDouble(rateDjsqmx1.getZbgzjg() == null ? "0" : rateDjsqmx1.getZbgzjg());
							 view.put("CS_GZ00033", Double.valueOf(zbgzjg).doubleValue());
						 }

						 RateDjsqmx ckgx4 = new RateDjsqmx();
						 ckgx4.setDjnf(djnf);
						 ckgx4.setZjhm(zjhm);
						 ckgx4.setZbgzid("GZ00033");
						 QueryWrapper<RateDjsqmx> queryWrapper6 = QueryGenerator.initQueryWrapper(ckgx4,map);
						 RateDjsqmx rateDjsqmx2 = rateDjsqmxService.getOne(queryWrapper6);
						 if (rateDjsqmx2 != null) {
							 double zbgzjg = Double.parseDouble(rateDjsqmx2.getZbgzjg() == null ? "0" : rateDjsqmx2.getZbgzjg());
							 view.put("CS_GZ00034", Double.valueOf(zbgzjg).doubleValue());
						 }

						 RateDjsqmx ckgx5 = new RateDjsqmx();
						 ckgx5.setDjnf(djnf);
						 ckgx5.setZjhm(zjhm);
						 ckgx5.setZbgzid("GZ00035");
						 QueryWrapper<RateDjsqmx> queryWrapper7 = QueryGenerator.initQueryWrapper(ckgx5,map);
						 RateDjsqmx rateDjsqmx3 = rateDjsqmxService.getOne(queryWrapper7);
						 if (rateDjsqmx3 != null) {
							 double zbgzjg = Double.parseDouble(ckgx5.getZbgzjg() == null ? "0" : ckgx5.getZbgzjg());
							 view.put("CS_GZ00035", Double.valueOf(zbgzjg).doubleValue());
						 }


						 RateDjsqmx ckgx6 = new RateDjsqmx();
						 ckgx6.setDjnf(djnf);
						 ckgx6.setZjhm(zjhm);
						 ckgx6.setZbgzid("GZ00036");
						 QueryWrapper<RateDjsqmx> queryWrapper8 = QueryGenerator.initQueryWrapper(ckgx6,map);
						 RateDjsqmx rateDjsqmx4 = rateDjsqmxService.getOne(queryWrapper8);
						 if (rateDjsqmx4 != null) {
							 double zbgzjg = Double.parseDouble(rateDjsqmx4.getZbgzjg() == null ? "0" : rateDjsqmx4.getZbgzjg());
							 view.put("CS_GZ00036", Double.valueOf(zbgzjg).doubleValue());
						 }

						 RateDjsqmx ckgx7 = new RateDjsqmx();
						 ckgx7.setDjnf(djnf);
						 ckgx7.setZjhm(zjhm);
						 ckgx7.setZbgzid("GZ00037");
						 QueryWrapper<RateDjsqmx> queryWrapper9 = QueryGenerator.initQueryWrapper(ckgx7,map);
						 RateDjsqmx rateDjsqmx5 = rateDjsqmxService.getOne(queryWrapper9);
						 if (rateDjsqmx5 != null) {
							 double zbgzjg = Double.parseDouble(rateDjsqmx5.getZbgzjg() == null ? "0" : rateDjsqmx5.getZbgzjg());
							 view.put("CS_GZ00037", Double.valueOf(zbgzjg).doubleValue());
						 }

						 RateDjsqmx ckgx8 = new RateDjsqmx();
						 ckgx8.setDjnf(djnf);
						 ckgx8.setZjhm(zjhm);
						 ckgx8.setZbgzid("GZ00038");
						 QueryWrapper<RateDjsqmx> queryWrapper10 = QueryGenerator.initQueryWrapper(ckgx8,map);
						 RateDjsqmx rateDjsqmx6 = rateDjsqmxService.getOne(queryWrapper10);
						 if (rateDjsqmx6 != null) {
							 double zbgzjg = Double.parseDouble(rateDjsqmx6.getZbgzjg() == null ? "0" : rateDjsqmx6.getZbgzjg());
							 view.put("CS_GZ00038", Double.valueOf(zbgzjg).doubleValue());
						 }

						 RateZbgzxxb zbgzxxb = new RateZbgzxxb();
						 zbgzxxb.setQydm(qydm);
						 zbgzxxb.setZbid(zbid);
						 zbgzxxb.setZbgzid("GZ00032");
						 QueryWrapper<RateZbgzxxb> queryWrapper2 = QueryGenerator.initQueryWrapper(zbgzxxb,map);
						 RateZbgzxxb rateZbgzxxb = rateZbgzxxbService.getOne(queryWrapper2);
						 if (rateZbgzxxb!=null) {
							 String zbgzid = rateZbgzxxb.getZbgzid();
							 double zbabs = Double.valueOf(rateZbgzxxb.getZbabs());
							 double zbgzfz = rateZbgzxxb.getZbgzfz();
							 RateDjsqmx djsqmx = new RateDjsqmx();
							 djsqmx.setDjnf(djnf);
							 djsqmx.setZjhm(zjhm);
							 djsqmx.setZbgzid(zbgzid);
							 QueryWrapper<RateDjsqmx> queryWrapper11 = QueryGenerator.initQueryWrapper(djsqmx,map);
							 RateDjsqmx rateDjsqmx7 = rateDjsqmxService.getOne(queryWrapper11);
							 if (rateDjsqmx7 != null) {
								 double zbgzjg = Double.parseDouble(rateDjsqmx7.getZbgzjg() == null ? "0" : rateDjsqmx7.getZbgzjg());
								 view.put("CS_GZ00032", Double.valueOf(zbgzjg));
								/* *
								  * 浏阳利率定价测算方案41
								  * Ⅰ.日平存款占贷款授信比例低于15%（含）时，按每1%对应分值2分，最高30分。
								  * Ⅱ.日平存款占贷款授信比例高于15%时，其中15%-40%（含）部分按每1%对应分值1分，高于40%以上部分按每1%对应分值0.8分。
								  */
								 if(zbgzjg <= 15) {
									 zbgzfzKH = zbgzjg*2;
								 }else if(zbgzjg > 15 && zbgzjg <= 40) {
									 zbgzfzKH = (zbgzjg-15)*1 + 15*2;
								 }else if(zbgzjg > 40) {
									 zbgzfzKH = (zbgzjg-40)*0.8 + (40-15)*1 + 15*2;
								 }
							 }
							 khckgxdf = zbgzfzKH;
							 khckgxdf = Double.valueOf(format2Decimal.format(khckgxdf));
						 }

					 }else if ("KH00008".equalsIgnoreCase(zbid)) {//客户收益贡献
						 RateDjsqmx khsygx1 = new RateDjsqmx();
						 khsygx1.setDjnf(djnf);
						 khsygx1.setZjhm(zjhm);
						 khsygx1.setZbgzid("GZ00039");
						 QueryWrapper<RateDjsqmx> queryWrapper3 = QueryGenerator.initQueryWrapper(khsygx1,map);
						 RateDjsqmx Djsqmx = rateDjsqmxService.getOne(queryWrapper3);
						 if (Djsqmx != null) {
							 double zbgzjg = Double.parseDouble(Djsqmx.getZbgzjg() == null ? "0" : Djsqmx.getZbgzjg());
							 view.put("CS_GZ00039", Double.valueOf(zbgzjg).doubleValue());
						 }
						 RateDjsqmx khsygx2 = new RateDjsqmx();
						 khsygx2.setDjnf(djnf);
						 khsygx2.setZjhm(zjhm);
						 khsygx2.setZbgzid("GZ00040");
						 QueryWrapper<RateDjsqmx> queryWrapper4 = QueryGenerator.initQueryWrapper(khsygx2,map);
						 RateDjsqmx Djsqmx1 = rateDjsqmxService.getOne(queryWrapper4);
						 if (Djsqmx1 != null) {
							 double zbgzjg = Double.parseDouble(Djsqmx1.getZbgzjg() == null ? "0" : Djsqmx1.getZbgzjg());
							 view.put("CS_GZ00040", Double.valueOf(zbgzjg).doubleValue());
						 }
						 RateDjsqmx khsygx3 = new RateDjsqmx();
						 khsygx3.setDjnf(djnf);
						 khsygx3.setZjhm(zjhm);
						 khsygx3.setZbgzid("GZ00041");
						 QueryWrapper<RateDjsqmx> queryWrapper5 = QueryGenerator.initQueryWrapper(khsygx3,map);
						 RateDjsqmx Djsqmx2 = rateDjsqmxService.getOne(queryWrapper5);
						 if (Djsqmx2 != null) {
							 double zbgzjg = Double.parseDouble(Djsqmx2.getZbgzjg() == null ? "0" : Djsqmx2.getZbgzjg());
							 view.put("CS_GZ00041", Double.valueOf(zbgzjg).doubleValue());
						 }

						 RateZbgzxxb zbgzxxb = new RateZbgzxxb();
						 zbgzxxb.setQydm(qydm);
						 zbgzxxb.setZbid(zbid);
						 zbgzxxb.setZbgzid("GZ00042");//日平存款占贷款比例
						 QueryWrapper<RateZbgzxxb> queryWrapper2 = QueryGenerator.initQueryWrapper(zbgzxxb,map);
						 RateZbgzxxb rateZbgzxxb = rateZbgzxxbService.getOne(queryWrapper2);
						 if (rateZbgzxxb != null) {
							 String zbgzid = rateZbgzxxb.getZbgzid();
							 double zbabs = Double.valueOf(rateZbgzxxb.getZbabs());
							 double zbgzfz = rateZbgzxxb.getZbgzfz();
							 RateDjsqmx djsqmx = new RateDjsqmx();
							 djsqmx.setDjnf(djnf);
							 djsqmx.setZjhm(zjhm);
							 djsqmx.setZbgzid(zbgzid);
							 QueryWrapper<RateDjsqmx> queryWrapper6 = QueryGenerator.initQueryWrapper(djsqmx,map);
							 RateDjsqmx Djsqmx3 = rateDjsqmxService.getOne(queryWrapper6);
							 if (Djsqmx3 != null) {
								 double zbgzjg = Double.parseDouble(Djsqmx3.getZbgzjg() == null ? "0" : Djsqmx3.getZbgzjg());
								 view.put("CS_GZ00042", Double.valueOf(zbgzjg).doubleValue());
								 if (zbgzjg > 0) {
									 if (zbgzjg > 50) {
										 zbgzfzKH = 5;
									 } else if (zbgzjg > 40 && zbgzjg <= 50) {
										 zbgzfzKH = 4;
									 } else if (zbgzjg > 30 && zbgzjg <= 40) {
										 zbgzfzKH = 3;
									 } else if (zbgzjg > 20 && zbgzjg <= 30) {
										 zbgzfzKH = 2;
									 } else if (zbgzjg > 5 && zbgzjg <= 20) {
										 zbgzfzKH = 1;
									 } else {
										 zbgzfzKH = 0;
									 }
								 }
								/* *
								  * 浏阳利率定价测算方案40、41
								  * 删除客户收益贡献得分
								  * zbgzfzKH = 0;*/

								 zbgzfzKH = 0;
								 khsygxdf = zbgzfzKH;
								 khsygxdf = Double.valueOf(format2Decimal.format(khsygxdf));

							 }

						 }
					 } else if ("KH00009".equalsIgnoreCase(zbid)) {//客户代理业务
						 RateZbgzxxb zbgzxxb = new RateZbgzxxb();
						 zbgzxxb.setQydm(qydm);
						 zbgzxxb.setZbid(zbid);
						 QueryWrapper<RateZbgzxxb> queryWrapper2 = QueryGenerator.initQueryWrapper(zbgzxxb,map);
						 List<RateZbgzxxb> rateZbgzxxb = rateZbgzxxbService.list(queryWrapper2);
						 for (RateZbgzxxb rateZbgzxxb1 : rateZbgzxxb) {
							 String zbgzid = rateZbgzxxb1.getZbgzid();
							 double zbabs = Double.valueOf(rateZbgzxxb1.getZbabs());
							 double zbgzfz = rateZbgzxxb1.getZbgzfz();
							 RateDjsqmx djsqmx = new RateDjsqmx();
							 djsqmx.setDjnf(djnf);
							 djsqmx.setZjhm(zjhm);
							 djsqmx.setZbgzid(zbgzid);
							 QueryWrapper<RateDjsqmx> queryWrapper3 = QueryGenerator.initQueryWrapper(djsqmx,map);
							 RateDjsqmx rateZbgzxxb2 = rateDjsqmxService.getOne(queryWrapper3);
							 if (rateZbgzxxb2!=null) {
								 int zbgzjg = Double.valueOf(rateZbgzxxb2.getZbgzjg() == null ? "0" : rateZbgzxxb2.getZbgzjg()).intValue();
								 if ("GZ00054".equalsIgnoreCase(zbgzid)) {
									 sfyqzj = zbgzjg;
								 }
								 if(zbgzjg == 1){
									 view.put("CSZ_"+zbgzid,"是");
									 zbgzfz = zbabs*zbgzfz;
								 } else {
									 view.put("CSZ_"+zbgzid,"否");
									 zbgzfz = 0;
								 }
								 view.put("CS_" + zbgzid,Double.valueOf(format2Decimal.format(zbgzfz)));
								 zbgzfzKH += zbgzfz;
							 }
						 }
					 } else if ("KH00010".equalsIgnoreCase(zbid)) {//其他业务
						 RateZbgzxxb zbgzxxb = new RateZbgzxxb();
						 zbgzxxb.setQydm(qydm);
						 zbgzxxb.setZbid(zbid);
						 QueryWrapper<RateZbgzxxb> queryWrapper1 = QueryGenerator.initQueryWrapper(zbgzxxb,map);
						 List<RateZbgzxxb> rateZbgzxxb = rateZbgzxxbService.list(queryWrapper1);
						 for (RateZbgzxxb rateZbgzxxb1 : rateZbgzxxb) {
							 String zbgzid = rateZbgzxxb1.getZbgzid();
							 double zbabs = Double.valueOf(rateZbgzxxb1.getZbabs());
							 double zbgzfz = rateZbgzxxb1.getZbgzfz();

							 RateDjsqmx djsqmx = new RateDjsqmx();
							 djsqmx.setDjnf(djnf);
							 djsqmx.setZjhm(zjhm);
							 djsqmx.setZbgzid(zbgzid);
							 QueryWrapper<RateDjsqmx> queryWrapper3 = QueryGenerator.initQueryWrapper(djsqmx,map);
							 RateDjsqmx rateZbgzxxb2 = rateDjsqmxService.getOne(queryWrapper3);
							 if (rateZbgzxxb2 != null) {
								 if ("GZ00048".equalsIgnoreCase(zbgzid)) {
									 double zbgzjg = Double.valueOf(rateZbgzxxb2.getZbgzjg() == null ? "0" : rateZbgzxxb2.getZbgzjg());
									 if(zbgzjg > 0){
										 if(zbgzjg>100){
											 zbgzfz = -3;
										 }else if(zbgzjg>50&&zbgzjg<=100){
											 zbgzfz = -2;
										 }else if(zbgzjg>20&&zbgzjg<=50){
											 zbgzfz = -1;
										 }else{
											 zbgzfz = 0;
										 }
									 }else{
										 zbgzfz = 0;
									 }
									 view.put("CSZ_"+zbgzid,zbgzjg);
									 view.put("CS_"+zbgzid,zbgzfz);
								 } else if ("GZ00057".equalsIgnoreCase(zbgzid)) {
									 double zbgzjg = Double.valueOf(rateZbgzxxb2.getZbgzjg() == null ? "0" : rateZbgzxxb2.getZbgzjg());
									 if(zbgzjg==0){
										 zbgzfz = 0;
									 }else{
										 zbgzfz = zbabs*zbgzfz*zbgzjg;
									 }
									 view.put("CSZ_"+zbgzid,zbgzjg);
									 view.put("CS_"+zbgzid,zbgzfz);
								 } else {
									 int zbgzjg = Double.valueOf(rateZbgzxxb2.getZbgzjg() == null ? "0" : rateZbgzxxb2.getZbgzjg()).intValue();
									 RateGzbdsxx gzbdsxx = new RateGzbdsxx();
									 gzbdsxx.setQydm(qydm);
									 gzbdsxx.setZbgzid(zbgzid);
									 gzbdsxx.setBdskey(zbgzjg);
									 QueryWrapper<RateGzbdsxx> queryWrapper2 = QueryGenerator.initQueryWrapper(gzbdsxx,map);
									 RateGzbdsxx rategzbdsxx = rateGzbdsxxService.getOne(queryWrapper2);
									 if (rategzbdsxx!= null) {
										 double bdsfz = rategzbdsxx.getBdsfz().doubleValue();
										 if(bdsfz==0){
											 zbgzfz=0;
										 }else{
											 zbgzfz = bdsfz*zbabs;
										 }
									 }
									 view.put("CSZ_"+zbgzid,rategzbdsxx.getBdsvalue());
									 view.put("CS_"+zbgzid,zbgzfz);
								 }
								 zbgzfzKH += zbgzfz;
							 }
						 }
					 }else if ("KH00012".equalsIgnoreCase(zbid)) {//本行资金成本率
						 RateZbgzxxb zbgzxxb = new RateZbgzxxb();
						 zbgzxxb.setQydm(qydm);
						 zbgzxxb.setZbid(zbid);
						 QueryWrapper<RateZbgzxxb> queryWrapper1 = QueryGenerator.initQueryWrapper(zbgzxxb,map);
						 List<RateZbgzxxb> rateZbgzxxb = rateZbgzxxbService.list(queryWrapper1);
						 for (RateZbgzxxb rateZbgzxxb1 : rateZbgzxxb) {
							 String zbgzid = rateZbgzxxb1.getZbgzid();
							 RateDjsqmx djsqmx = new RateDjsqmx();
							 djsqmx.setDjnf(djnf);
							 djsqmx.setZjhm(zjhm);
							 djsqmx.setZbgzid(zbgzid);
							 QueryWrapper<RateDjsqmx> queryWrapper3 = QueryGenerator.initQueryWrapper(djsqmx,map);
							 RateDjsqmx rateDjsqmx = rateDjsqmxService.getOne(queryWrapper3);
							 if (rateDjsqmx!= null) {
								 double zbgzjg = Double.valueOf(rateDjsqmx.getZbgzjg() == null ? "0" : rateDjsqmx.getZbgzjg()).doubleValue();
								 view.put("CSZ_"+zbgzid,Double.valueOf(format2Decimal.format(zbgzjg)));
							 }
						 }
					 }
				 }
				 log.debug("LLDJJS:::::::4" + zbkxxb.getZbid());
				 dfhj += zbgzfzKH;

				 if(!"KH00011".equalsIgnoreCase(zbid) && !"KH00012".equalsIgnoreCase(zbid)) {
					 view.put("DF_" + zbid, format2Decimal.format(zbgzfzKH));
				 }
			 }
			 log.debug("LLDJJS:::::::5");

			 //扣分项不能超过信用等级得分
			 String xydjdf = (String)view.getString("DF_KH00001");
			 String zkf = (String)view.getString("DF_KH00002");
			 double  xydjdfDouble = Double.parseDouble(xydjdf);
			 double zkfDouble = Double.parseDouble(zkf);
			 double zhgxf = khsygxdf+khckgxdf;
			 view.put("KHGXDF", zhgxf);

			 log.debug("LLDJJS:::::::6");
			 if (zkfDouble * -1 > xydjdfDouble) {
				 view.put("DF_KH00002", xydjdfDouble * -1);
				 dfhj = dfhj + (zkfDouble * -1 - xydjdfDouble);
			 }


			 double cdck = 0d; //贷款授信+承兑敞口
			 double temp = 0d;
			 double cbfd = 0d; //查表幅度
			 int sfbmk = 0;    //是否便民卡
			 int sfbzbxdk = 0;    //是否保证保险贷款
			 int dkqx = 0;  //贷款期限
			 if (djsqxx != null) {
				 cdck = djsqxx.getCdck().doubleValue();
				 sfbmk = djsqxx.getSfbmk() == null ? 0 : djsqxx.getSfbmk().intValue();
				 sfbzbxdk = djsqxx.getSfbzbxdk() == null ? 0 : djsqxx.getSfbzbxdk().intValue();
				 dkqx = djsqxx.getDkqx() == null ? 0 : djsqxx.getDkqx().intValue();
			 }
			 log.debug("LLDJJS:::::::7");
			 //计算担保得分

			/*
			  * 浏阳利率定价测算方案41
			  * Ⅰ.担保类型得分40分
			  * Ⅰ.担保类型为组合担保，且一类担保占比超过60%的，则非一类担保部分按同一类B计算分值
			  */
			 boolean isHyldb = false; // 是否含一类担保
			 boolean isHeldb = false; // 是否含二类担保
			 boolean isHsldb = false; // 是否含三类担保
			 boolean isHqtdb = false; // 是否含其它担保
			 boolean iscyldb = false; // 是否纯一类担保
			 double yldbjezh = 0d;    // 总一类担保金额总和
			 double yldbzb   = 0d;    // 一类担保占比

			 double yljdbje = 0d; //已累计担保金额
			 double dbzdf = 0d; //担保总得分
			 RateDbxxgl dbxxgl = new RateDbxxgl();
			 dbxxgl.setDjnf(djnf);
			 dbxxgl.setZjhm(zjhm);
			 QueryWrapper<RateDbxxgl> queryWrapper1 = QueryGenerator.initQueryWrapper(dbxxgl,map);
			 List<RateDbxxgl> rateDbxxglList = rateDbxxglService.list(queryWrapper1);
			 int RowCount = rateDbxxglList.size();
			 log.debug("LLDJJS:::::::8");
			 int rowNum = 0 ;
			 for (RateDbxxgl rateDbxxgl : rateDbxxglList) {
				 rowNum = rowNum+1;
				 log.debug("LLDJJS:::::::9" + rowNum);
				 double sjdbje = rateDbxxgl.getSjdbje();
				 String dblx   = rateDbxxgl.getDblx();

				 // Step1.计算总一类担保金额
				 if ("1".equals(dblx) || "2".equals(dblx) || "9".equals(dblx) || "10".equals(dblx)) {
					 yldbjezh += sjdbje;
				 }
				 // Step2.判断是否含有一类担保
				 if ("1".equals(dblx) || "2".equals(dblx) || "9".equals(dblx) || "10".equals(dblx)) {
					 isHyldb = true;
				 } else if ("3".equals(dblx) || "4".equals(dblx)) {// Ⅰ.判断是否含有二类担保
					 isHeldb = true;
				 } else if ("5".equals(dblx)) {// Ⅱ.判断是否含有三类担保
					 isHsldb = true;
				 } else if ("6".equals(dblx) || "7".equals(dblx) || "8".equals(dblx)) {// Ⅲ.判断是否含有其它担保
					 isHqtdb= true;
				 }

				 double tmpdf = 0d;
				 if(!StringUtils.isEmpty(dblx)) {
					 String dbdf = (String)dbdfMap.get(dblx);
					 if(cdck>0) {
						 if(yljdbje==0 && rowNum == 1) {
							 yljdbje = cdck - sjdbje ;
						 } else {
							 if(yljdbje>0) {
								 if(sjdbje>=yljdbje){
									 sjdbje = yljdbje;
								 }else{
									 yljdbje = yljdbje - sjdbje ;
								 }
							 } else {
								 sjdbje = 0;
							 }
						 }
						 double jfbfb = Double.parseDouble(dbdf);
						 tmpdf = jfbfb * (sjdbje / cdck); //担保类型得分=实际担保金额/授信额度*对应分数(AAA类担保30分)
						 if(RowCount == 1) {
							 if(tmpdf > jfbfb){
								 tmpdf = jfbfb;
							 }
						 }
						 if(tmpdf > 40) {
							 tmpdf = 40;
						 }
					 }
				 }
				 dbzdf += tmpdf;
			 }

			 if(isHyldb && !isHeldb && !isHsldb && !isHqtdb){// Ⅳ.判断是否为纯一类担保
				 iscyldb = true;
				 log.debug("判断是否为纯一类担保::::"+iscyldb);
			 }
			 log.debug("是否含一类担保：：：：："+isHyldb);
			 log.debug("是否含二类担保：：：：："+isHeldb);
			 log.debug("是否含三类担保：：：：："+isHsldb);
			 log.debug("是否含其它担保：：：：："+isHqtdb);
			 log.debug("是否纯一类担保：：：：："+iscyldb);

			 // Step3. 计算一类担保占比：一类担保金额总和 / 承兑敞口(授信金额) * 100
			 yldbzb = (yldbjezh/cdck)*100;
			 // Step4.判断是否为纯一类担保
			 // 担保类型为组合担保，且一类担保占比超过60%的，则非一类担保部分按同一类B计算分值
			 // Ⅰ.必须含一类担保且包含其它任一类型担保
			 if (isHyldb && (isHeldb || isHsldb || isHqtdb)) {
				 if (yldbzb >= 60) {// 一类担保占比超过60%
					 double dbzdf_tmp = 0d;
					 for (RateDbxxgl rateDbxxgl : rateDbxxglList) {
						 double tmpdblxdf  = 0d;                    // 担保类型得分
						 double sjdbje = rateDbxxgl.getSjdbje();        // 客户实际担保金额
						 String khsxdblx = rateDbxxgl.getDblx();          // 客户所选担保类型
						 String dbdf = (String)dbdfMap.get(khsxdblx); // 对应担保类型所对应分数
						 double dydbdf = Double.parseDouble(dbdf);  // 担保类型对应担保得分
						 double ylbdbdf = Double.parseDouble((String)dbdfMap.get("10")); // 一类B担保(借用)得分
						 log.debug("客户所选担保类型：：：：：：：：：：："+khsxdblx);
						 log.debug("对应担保类型所对应分数(String)dbdfMap.get(khsxdblx)：：：：：：：：：：："+dbdf);
						 log.debug("担保类型对应担保得分：：：：：：：：：：："+dydbdf);
						 if(!("1".equals(khsxdblx) || "2".equals(khsxdblx) || "9".equals(khsxdblx) || "10".equals(khsxdblx))){// Ⅱ.当担保类型为非一类时，担保得分=一类B担保(借用)=28
							 dydbdf = ylbdbdf;
						 }
						 tmpdblxdf = dydbdf * (sjdbje / cdck); // Ⅲ.担保类型得分=对应分数*（实际担保金额/授信额度）
						 dbzdf_tmp += tmpdblxdf;
						 dbzdf = dbzdf_tmp;
					 }
				 }
			 }
			 if(dbzdf > 40) {
				 dbzdf = 40;
			 }
			 log.debug("担保类型总得分：：：：：：：：：：："+dbzdf);
			 log.debug("客户证件号码：：：：：：：：：：：："+zjhm);
			 log.debug("LLDJJS:::::::10");
			 view.put("dbzdf", format2Decimal.format(dbzdf));
			 //得分合计
			 dfhj += dbzdf;
			 dfhj = Double.parseDouble(format0Decimal.format(dfhj));
			 if (dfhj < 0) {
				 dfhj = 0;
			 }
			 log.debug("得分合计：：：：：：：：：：："+dfhj);
			 log.debug("LLDJJS:::::::11");
			 //查表幅度
			 if (cdck > 10 && cdck <= 50) {
				 temp = 70 - dfhj;
			 } else if (cdck > 50 && cdck <= 200) {
				 temp = 65 - dfhj;
			 } else if (cdck > 200 && cdck <= 500) {
				 temp = 60 - dfhj;
			 } else if (cdck > 500 && cdck <= 1000) {
				 temp = 55 - dfhj;
			 } else if (cdck > 1000) {
				 temp = 50 - dfhj;
			 }
			 //modify by liuwei 2019年3月5日16:39:51
			 //由50%修改为55%
			 cbfd = 55 + temp;
			 if (cbfd < 0) {
				 cbfd = 0;
			 }
			 view.put("dfhj", format0Decimal.format(dfhj));
			 view.put("cbfd", format0Decimal.format(cbfd));
			 log.debug("LLDJJS:::::::12");

			 //十五条
			 boolean ishasyldb = false; //是否有一类担保
			 boolean ishaseldb = false; //是否有二类担保
			 boolean ishassldb = false; //是否有三类担保
			 boolean ishasqtdb = false; //是否有其它担保
			 boolean isyldb = false;    //是否是一类担保
			 boolean isbfyldb = false;  //是否是部分一类担保
			 BigDecimal yldbl = new BigDecimal(0);   //一类担保率
			 BigDecimal zsjdbje = new BigDecimal(0); //总实际担保金额
			 BigDecimal zyldbje = new BigDecimal(0); //总一类担保金额
			 log.debug("LLDJJS:::::::13");
			 for (RateDbxxgl rateDbxxgl : rateDbxxglList) {
				 log.debug("LLDJJS:::::::14" );
				 String dblx = rateDbxxgl.getDblx();
				 log.debug("DBLX::" + dblx);
				 BigDecimal sjdbje = new BigDecimal(rateDbxxgl.getSjdbje());
				 zsjdbje = zsjdbje.add(sjdbje);
				 BigDecimal dbl = new BigDecimal(rateDbxxgl.getDbl());
				 if("1".equals(dblx) || "2".equals(dblx) || "9".equals(dblx) || "10".equals(dblx)){
					 zyldbje = zyldbje.add(sjdbje);
				 }
				 if("5".equals(dblx) || "6".equals(dblx) || "7".equals(dblx)){
					 ishassldb = true;
					 continue;
				 }else if("1".equals(dblx) || "2".equals(dblx) || "9".equals(dblx) || "10".equals(dblx)){
					 ishasyldb = true;
					 continue;
				 }else if("3".equals(dblx) || "4".equals(dblx)){
					 ishaseldb = true;
					 continue;
				 }else if("8".equals(dblx)) {
					 //信用担保
					 ishasqtdb = true;
				 }
			 }
			 if(ishasyldb && !ishaseldb && !ishassldb && !ishasqtdb){
				 isyldb = true;
			 }
			 if(ishasyldb && (ishaseldb || ishassldb || ishasqtdb)){
				 isbfyldb = true;
			 }
			 if(zsjdbje.compareTo(new BigDecimal(0)) > 0) {
				 yldbl = zyldbje.divide(zsjdbje, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
			 }
			 log.debug("isyldb::" + isyldb);
			 log.debug("isbfyldb::" + isbfyldb);
			 log.debug("ishaseldb::" + ishaseldb);
			 log.debug("ishassldb::" + ishassldb);
			 log.debug("ishasqtdb::" + ishasqtdb);

			 double yldbsffb   = 50;  //文件第十五条限制第一点：担保物全部为一类担保时，贷款利率的最高上浮幅度
			 double bfyldbsffb = 60;  //文件第十五条限制第二点：担保物不完全是一类担保但符合情况时，贷款利率的最高上浮幅度
			 double sltsffd    = 55;  //文件第十六条限制，贷款利率的最高上浮幅度
			 double bmksffd    = 10;  //便民卡上浮幅度
			 double bzbxdksffd = 10;  //保证保险贷款上浮幅度
			 double zdsffd     = 90;  //最大上浮幅度
			 double LPRCS1     = 0d;  //1年期LPR参数
			 double LPRCS2     = 0d;  //1~5年期LPR参数
			 double LPRCS3     = 0d;  //5年以上LPR参数
			 double LPRYhjdcs  = 0d;  //LPR优惠基点参数
			 double LPRBZZ     = 0d;  //LPR标准参数
			 double thckrpjsbl = 0d;

			 RateCssz rateCssz = new RateCssz();
			 List<RateCssz> csszList = rateCsszService.querycssz();
			 for (RateCssz cssz : csszList) {
				 String rateCsid = cssz.getCsid();
				 double rateCsvalue = Double.parseDouble(cssz.getCsvalue());

				 if ("CS0005".equalsIgnoreCase(rateCsid)) {
					 yldbsffb = rateCsvalue;
				 } else if ("CS0006".equalsIgnoreCase(rateCsid)) {
					 bfyldbsffb = rateCsvalue;
				 } else if ("CS0007".equalsIgnoreCase(rateCsid)) {
					 sltsffd = rateCsvalue;
				 } else if ("CS0008".equalsIgnoreCase(rateCsid)) {
					 bmksffd = rateCsvalue;
				 } else if ("CS0009".equalsIgnoreCase(rateCsid)) {
					 bzbxdksffd = rateCsvalue;
				 } else if ("CS0010".equalsIgnoreCase(rateCsid)) {
					 zdsffd = rateCsvalue;
				 } else if ("CS0011".equalsIgnoreCase(rateCsid)) {
					 LPRCS1 = rateCsvalue;
				 } else if ("CS0012".equalsIgnoreCase(rateCsid)) {
					 LPRCS2 = rateCsvalue;
				 } else if ("CS0013".equalsIgnoreCase(rateCsid)) {
					 LPRCS3 = rateCsvalue;
				 } else if ("CS0014".equalsIgnoreCase(rateCsid)) {
					 LPRYhjdcs = rateCsvalue;
				 } else if ("CS0015".equalsIgnoreCase(rateCsid)) {
					 thckrpjsbl = rateCsvalue;
				 }
			 }

			 double yldblDouble = yldbl.doubleValue();
			 double jyhfd = 0d;
			 boolean sffh15t = false;
			 if (isyldb) {
				 log.debug("yldbsffb:::" + yldbsffb);
				 if(cbfd < yldbsffb) {
					 jyhfd = cbfd;
				 } else {
					 jyhfd = yldbsffb;
				 }
			 } else if(isbfyldb) {
				 if(cdck > 100 && cdck <= 200 && yldblDouble > 80) {
					 sffh15t = true;
					 if (cbfd < bfyldbsffb) {
						 jyhfd = cbfd;
					 } else {
						 jyhfd = bfyldbsffb;
					 }
				 } else if (cdck > 200 && cdck <= 500 && yldblDouble >= 70) {
					 sffh15t = true;
					 if (cbfd < bfyldbsffb) {
						 jyhfd = cbfd;
					 } else {
						 jyhfd = bfyldbsffb;
					 }
				 } else if (cdck > 500 && cdck <= 1000 && yldblDouble >= 60) {
					 sffh15t = true;
					 if (cbfd < bfyldbsffb) {
						 jyhfd = cbfd;
					 } else {
						 jyhfd = bfyldbsffb;
					 }
				 } else if (cdck > 1000 && yldblDouble >= 50) {
					 sffh15t = true;
					 if (cbfd < bfyldbsffb) {
						 jyhfd = cbfd;
					 } else {
						 jyhfd = bfyldbsffb;
					 }
				 } else {
					 jyhfd = cbfd;
				 }
			 } else {
				 jyhfd = cbfd;
			 }
			 log.debug("jyhfd::" + jyhfd);
			 log.debug("cbfd::" + cbfd);

			 //十六条
			 int kljbzhwl = 0;
			 RateDjsqmx djsqmx = new RateDjsqmx();
			 djsqmx.setDjnf(djnf);
			 djsqmx.setZjhm(zjhm);
			 djsqmx.setZbgzid("GZ00031");
			 QueryWrapper<RateDjsqmx> queryWrapper2 = QueryGenerator.initQueryWrapper(djsqmx,map);
			 RateDjsqmx rateDjsqmx = rateDjsqmxService.getOne(queryWrapper2);
			 if (rateDjsqmx!= null) {
				 kljbzhwl = Double.valueOf(rateDjsqmx.getZbgzjg()).intValue();
			 }

			 String xydj = "";
			 RateDjsqmx djsqmx1 = new RateDjsqmx();
			 djsqmx1.setDjnf(djnf);
			 djsqmx1.setZjhm(zjhm);
			 djsqmx1.setZbgzid("GZ00031");
			 QueryWrapper<RateDjsqmx> queryWrapper3 = QueryGenerator.initQueryWrapper(djsqmx1,map);
			 RateDjsqmx rateDjsqmx1 = rateDjsqmxService.getOne(queryWrapper2);
			 if (rateDjsqmx1!= null) {
				 xydj = rateDjsqmx1.getZbgzjg();
			 }
			 if (isbfyldb && jyhfd < 55){
				 if (!(kljbzhwl == 2
						 && (xydj != "GZ00005" && xydj != "GZ00006" && xydj != "GZ00056")
						 && zcfzl <= 30
						 && zxbljl == 0
						 && (ishasyldb == true || ishaseldb == true))
				 ) {
					 jyhfd = jyhfd + 5;
					 if (jyhfd > sltsffd) {
						 jyhfd = sltsffd;
					 }
				 }
			 }
			 //新增文件第二十条限制
			 //除全部为一类担保的外，在利率定价计算出浮动幅度的基础上增加5%
			 //如果不全部为一类担保，但是符合十五条限制中A、B、C、D四种条件，最高上浮不超过70%
			 /*
			  * 浏阳利率定价测算方案41
			  * 2020年5月7日
			  * Ⅰ.去除 新增文件第二十条限制-除全部为一类担保的外，在利率定价计算出浮动幅度的基础上增加5%
			  */


            if (!isyldb) {
                jyhfd = jyhfd + 5;
            }
            if (isbfyldb && sffh15t) {
                if (jyhfd > bfyldbsffb) {
                    jyhfd = bfyldbsffb;
                }
            }
			 view.put("jyhfd", format0Decimal.format(jyhfd));
			 double sffd = jyhfd;
            if (sfyqzj == 1) {
                sffd = sffd + 10;
            }
			 //如果是便民卡，则上浮幅度增加10
			 if(sfbmk == 1){
				 sffd = sffd + bmksffd;
			 }
            //如果是保证保险贷款，则上浮幅度增加10
            // 2020年5月7日 ： 浏阳未包含此方案，暂注释掉
            if(sfbzbxdk == 1){
                sffd = sffd + bzbxdksffd;
            }
			 //是否大于最大上浮幅度
			 if(sffd > zdsffd) {
				 sffd = zdsffd;
			 }

			 //获取贷款期限对应的基准利率
			 String zbgzid;
			 if(dkqx == 1){//五年以上
				 zbgzid = "GZ00052";
				 LPRBZZ = LPRCS3;
			 }else if(dkqx == 2){//一至五年（含）
				 zbgzid = "GZ00051";
				 LPRBZZ = LPRCS2;
			 }else {//一年（含）以内
				 zbgzid = "GZ00050";
				 LPRBZZ = LPRCS1;
			 }
			 double jzll = 0.00;
			 RateZbgzxxb zbgzxxb = new RateZbgzxxb();
			 zbgzxxb.setQydm(qydm);
			 zbgzxxb.setZbid("KH00011");
			 zbgzxxb.setZbgzid(zbgzid);
			 QueryWrapper<RateZbgzxxb> queryWrapper4 = QueryGenerator.initQueryWrapper(zbgzxxb,map);
			 RateZbgzxxb zbgzxxb1 = rateZbgzxxbService.getOne(queryWrapper4);
			 if(zbgzxxb1 != null) {
				 jzll = Double.valueOf(zbgzxxb1.getZbjg()) / 100;
			 }

			 // double zxll = jzll/12*10*(1+(sffd/100))*100;

			 /*
			  * 2020-05-07 计算LPR基点、优惠后LPR基点、优惠后执行利率
			  * 如果是便民卡，则不区分贷款期限: 计算公式=(贷款一年(含)以内基准利率 * (1+上浮幅度) - 1年期LPR参数)*100
			  */
			 if(sfbmk==1) {
				 RateZbgzxxb zbgzxxb2 = new RateZbgzxxb();
				 zbgzxxb2.setQydm(qydm);
				 zbgzxxb2.setZbid("KH00011");
				 zbgzxxb2.setZbgzid(zbgzid);
				 QueryWrapper<RateZbgzxxb> queryWrapper5 = QueryGenerator.initQueryWrapper(zbgzxxb2,map);
				 RateZbgzxxb rateZbgzxxb = rateZbgzxxbService.getOne(queryWrapper5);
				 if(rateZbgzxxb != null) {
					 jzll = Double.valueOf(rateZbgzxxb.getZbjg()) / 100;
				 }
				 LPRBZZ = LPRCS1;
			 }
			 double zxll = (jzll/12*10*(1+(sffd/100))*100)*12/10;
			 view.put("jzll", format4Decimal.format(jzll*100));
			 view.put("sffd", format0Decimal.format(sffd));
			 view.put("zxll", format4Decimal.format(zxll));

			 double LPRjd    = ((jzll*100) * (1 + (sffd / 100)) - LPRBZZ) * 100; //LPR(加/减)基点BP
			 double YhhLPRjd = LPRjd - LPRYhjdcs;                                //优惠后LPR(加/减)基点BP
			 double YhhSffd  = ((YhhLPRjd/100)+LPRBZZ-(jzll*100))/jzll;          //优惠后上浮幅度
			 double YhhZxll  = (jzll/12*10*(1+(YhhSffd/100))*100)*12/10;         //优惠后执行利率
			 double Dyyhjdcs = LPRBZZ;                                           //对应优惠基点参数
			 view.put("lprjd", format2Decimal.format(LPRjd));
			 view.put("YhhLprjd", format2Decimal.format(YhhLPRjd));
			 view.put("YhhZxll", format4Decimal.format(YhhZxll));
			 view.put("Dyyhjdcs", Dyyhjdcs);
			 view.put("thckrpjsbl", thckrpjsbl); // 他行存款日平计算比例
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	 }


}
