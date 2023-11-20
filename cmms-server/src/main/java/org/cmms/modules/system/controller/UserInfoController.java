package org.cmms.modules.system.controller;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CacheConstant;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.api.ISysBaseAPI;
import org.cmms.common.system.util.JwtUtil;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.*;
import org.cmms.common.util.encryption.EncryptedString;
import org.cmms.common.util.encryption.RSAEncryptUtil;
import org.cmms.common.utils.AreaUtil;
import org.cmms.modules.shiro.vo.DefContants;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysDic;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.model.SysLoginModel;
import org.cmms.modules.system.service.*;
import org.cmms.modules.tjfx.zhcdksjmx.service.IZhcksjmxService;
import org.cmms.modules.yxdygl.pqzrrgl.service.ITjfxcsszService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;

/**
 * @Author scott
 * @since 2018-12-17
 */
@RestController
@RequestMapping("/userinfo")
@Api(tags="用户登录")
@Slf4j
public class UserInfoController {
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private ISysBaseAPI sysBaseAPI;
	@Autowired
	private ISysLogService logService;
	@Autowired
    private RedisUtil redisUtil;
	@Autowired
    private ISysDepartService sysDepartService;
	@Autowired
	private IZhcksjmxService  zhcksjmxService;
	@Autowired
	private ISysDicService  sysDicService;
	@Autowired
	private IHrBasOrganizationService  hrBasOrganizationService;
	@Autowired
	private AreaUtil areaUtil;
	@Autowired
	private ITjfxcsszService tjfxcsszService;

	private static final String BASE_CHECK_CODES = "qwertyuiplkjhgfdsazxcvbnmQWERTYUPLKJHGFDSAZXCVBNM1234567890";


	/**
	 * 获取区域代码
	 * @return
	 */
	@RequestMapping(value = "/yxdyqx", method = RequestMethod.PUT)
	public Result<?> getuserInfo() {
		Result<JSONObject> result = new Result<JSONObject>();
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		String username=sysUser.getUsername();
		//保存乡镇权限列表
		areaUtil.yjyxdyqxSave(username);
		//保存村权限列表
		areaUtil.ejyxdyqxSave(username);
		//保存组权限列表
		areaUtil.sjyxdyqxSave(username);
		result.success("获取成功");
		return result;
	}


	@GetMapping("/getHomePageInfo")
	public Result<JSONObject> getHomePageInfo(@RequestParam(name="jgdm", defaultValue="1") String zzbz) {
		Result<JSONObject> result = new Result<JSONObject>();
		JSONObject obj = new JSONObject();
		boolean isQhqx=true;
		String ywjgdm=logService.getywjgdm(zzbz);
		String sjzzbz=logService.getsjzzbz(zzbz);
		String zzlb=logService.getzzlb(zzbz);
		String zzjb=logService.getZzjb(zzbz);
		String zrckrq=logService.zrcksjrq().substring(2);
		String zrdkrq=logService.zrdksjrq().substring(2);
		String ncckrq=logService.nccksjrq().substring(2);
		String ncdkrq=logService.ncdksjrq().substring(2);

		Long ckye=0l;
		Long dkye=0l;
		Long ckrpye=0l;
		Long ncyrpye=0l;
		Long grckrpye=0l;
		Long dgckrpye=0l;
		Long ckrpyerw=0l;
		Long ckyerw=0l;
		Long dkyerw=0l;
		int xzckhsrw=0;
		int xzdkhsrw  =0;
		double dkbllrw = 0;

		Long zfhs=0l;
		Long jdrs=0l;
		int xzckhs=0;
		int xzckbs=0;
		int xzdkhs  =0;
		int xzdkbs = 0;
		int bncxzckhs=0;
		int bncxzckbs=0;
		int bncxzdkhs  =0;
		int bncxzdkbs = 0;
		double dkbll = 0;
		double sydkbll = 0;
		double ncdkbll = 0;
		double bsydkbll = 0;
		double bncdkbll = 0;

		Long shlx=0l;
		Long zrshlx=0l;
		Long zrckye=0l;
		Long zrdkye=0l;
		Long ncckye=0l;
		Long ncdkye=0l;
		List<Map> list =null;
		List<Map> listcdkxzs =null;
		Map<String, Object> pjsxxxMap=new HashMap<>();
		Map<String, Object> ndzbrwMap=new HashMap<>();

		if(zzbz.equals("1")||zzlb.equals("3")){
			//ckye = logService.zhckye()!=null?logService.zhckye():0;
			try {
				dkye = logService.zhdkye()!=null?logService.zhdkye():0;
			}catch (Exception e){
				log.warn("获取全行贷款余额异常！");
				e.printStackTrace();
			}
			try {
				zrckye = logService.zrzhckye("ZDCBSINVMBASE"+zrckrq)!=null?logService.zrzhckye("ZDCBSINVMBASE"+zrckrq):0;
			}catch (Exception e){
				log.warn("获取全行昨日存款余额异常！");
				e.printStackTrace();
			}
			try {
				zrdkye = logService.zrzhdkye("ZDCBSBORMBASE"+zrdkrq)!=null?logService.zrzhdkye("ZDCBSBORMBASE"+zrdkrq):0;
			}catch (Exception e){
				log.warn("获取全行昨日贷款余额异常！");
				e.printStackTrace();
			}

			try {
				ncckye = logService.zrzhckye("ZDCBSINVMBASE"+ncckrq)!=null?logService.zrzhckye("ZDCBSINVMBASE"+ncckrq):0;
			}catch (Exception e){
				log.warn("获取全行年初存款余额异常！");
				e.printStackTrace();
			}
			try {
				ncdkye = logService.zrzhdkye("ZDCBSBORMBASE"+ncdkrq)!=null?logService.zrzhdkye("ZDCBSBORMBASE"+ncdkrq):0;
			}catch (Exception e){
				log.warn("获取全行年初贷款余额异常！");
				e.printStackTrace();
			}

			try {
				list = zhcksjmxService.getqhcksj(zhcksjmxService.getZhcksjrq());
			}catch (Exception e){
				log.warn("获取存款数据日期异常！");
				e.printStackTrace();
			}
			try {
				listcdkxzs=zhcksjmxService.getqhcdkxzs();
			}catch (Exception e){
				log.warn("获取全行存贷款数据异常！");
				e.printStackTrace();
			}
			try {
				zfhs=logService.getQhZfhs(DateUtil.getMonthBeginDay(new Date()));
			}catch (Exception e){
				log.warn("获取全行走访户数异常！");
				e.printStackTrace();
			}
			try {
				jdrs=logService.getQhJdrs(DateUtil.getMonthBeginDay(new Date()));
			}catch (Exception e){
				log.warn("获取全行建档人数异常！");
				e.printStackTrace();
			}
			try {
				pjsxxxMap = logService.getQhPjsxxx(DateUtil.getMonthBeginDay(new Date()));
			}catch (Exception e){
				log.warn("获取全行评级授信信息异常！");
				e.printStackTrace();
			}
		}else{
			isQhqx=false;
			try {
				dkye = logService.dkye(ywjgdm)!=null?logService.dkye(ywjgdm):0;
			}catch (Exception e){
				log.warn("获取支行行贷款余额异常！");
				e.printStackTrace();
			}
			try {
				zrckye = logService.zrckye("ZDCBSINVMBASE"+zrckrq,ywjgdm)!=null?logService.zrckye("ZDCBSINVMBASE"+zrckrq,ywjgdm):0;
			}catch (Exception e){
				log.warn("获取支行昨日存款余额异常！");
				e.printStackTrace();
			}
			try {
				zrdkye = logService.zrdkye("ZDCBSBORMBASE"+zrdkrq,ywjgdm)!=null?logService.zrdkye("ZDCBSBORMBASE"+zrdkrq,ywjgdm):0;
			}catch (Exception e){
				log.warn("获取支行昨日贷款余额异常！");
				e.printStackTrace();
			}


			try {
				ncckye = logService.zrckye("ZDCBSINVMBASE"+ncckrq,ywjgdm)!=null?logService.zrckye("ZDCBSINVMBASE"+ncckrq,ywjgdm):0;
			}catch (Exception e){
				log.warn("获取全行年初存款余额异常！");
				e.printStackTrace();
			}
			try {
				ncdkye = logService.zrdkye("ZDCBSBORMBASE"+ncdkrq,ywjgdm)!=null?logService.zrdkye("ZDCBSBORMBASE"+ncdkrq,ywjgdm):0;
			}catch (Exception e){
				log.warn("获取全行年初贷款余额异常！");
				e.printStackTrace();
			}

			try {
				list = zhcksjmxService.getzhcksj(zhcksjmxService.getZhcksjrq(),ywjgdm);
			}catch (Exception e){
				log.warn("获取支行存款数据日期异常！");
				e.printStackTrace();
			}
			try {
				listcdkxzs=zhcksjmxService.getcdkxzs(ywjgdm);
			}catch (Exception e){
				log.warn("获取支行存贷款数据异常！");
				e.printStackTrace();
			}
			try {
				zfhs=logService.getZhZfhs(DateUtil.getMonthBeginDay(new Date()),zzbz);
			}catch (Exception e){
				log.warn("获取支行走访户数异常！");
				e.printStackTrace();
			}

			try {
				jdrs=logService.getZhJdrs(DateUtil.getMonthBeginDay(new Date()),zzbz);
			}catch (Exception e){
				log.warn("获取支行建档人数异常！");
				e.printStackTrace();
			}

			try {
				pjsxxxMap = logService.getZhPjsxxx(DateUtil.getMonthBeginDay(new Date()),zzbz);
			}catch (Exception e){
				log.warn("获取支行评级授信信息异常！");
				e.printStackTrace();
			}

			//ckye = logService.ckye(ywjgdm)!=null?logService.ckye(ywjgdm):0;

		}
		try {
			Date ncrq=DateUtil.getFirstMonth_Year(logService.dksjrq(),0);
			System.out.println("年度指标任务时间："+ncrq+" 贷款数据日期："+logService.dksjrq());
			ndzbrwMap = logService.getNdzbrw(ncrq,zzbz);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Long bzrdkye=dkye-zrdkye;
		Long bzrshlx=shlx-zrshlx;
		Long bncdkye=dkye-ncdkye;

		if(list!=null&&list.size()>0){
			BigDecimal yrp=(BigDecimal)list.get(0).get("YRP");
			BigDecimal ncyrp=(BigDecimal)list.get(0).get("NCYRP");
			BigDecimal dsyrp=(BigDecimal)list.get(0).get("DSYRP");
			BigDecimal dgyrp=(BigDecimal)list.get(0).get("DGYRP");
			BigDecimal ye=(BigDecimal)list.get(0).get("YE");
			ckrpye=yrp.longValue();
			ncyrpye=ncyrp.longValue();
			grckrpye=dsyrp.longValue();
			dgckrpye=dgyrp.longValue();
			ckye=ye.longValue();
		}
		Long bzrckye=ckye-zrckye;
		Long bncckye=ckye-ncckye;
		Long bncyrp=ckrpye-ncyrpye;

		if(listcdkxzs!=null&&listcdkxzs.size()>0){
			BigDecimal ckhs=(BigDecimal)listcdkxzs.get(0).get("CKKH");
			BigDecimal ckzh=(BigDecimal)listcdkxzs.get(0).get("CKZH");
			BigDecimal dkhs=(BigDecimal)listcdkxzs.get(0).get("DKKH");
			BigDecimal dkzh=(BigDecimal)listcdkxzs.get(0).get("DKZH");
			BigDecimal bncckhs=(BigDecimal)listcdkxzs.get(0).get("BNCCKKH");
			BigDecimal bncckzh=(BigDecimal)listcdkxzs.get(0).get("BNCCKZH");
			BigDecimal bncdkhs=(BigDecimal)listcdkxzs.get(0).get("BNCDKKH");
			BigDecimal bncdkzh=(BigDecimal)listcdkxzs.get(0).get("BNCDKZH");

			BigDecimal bll=(BigDecimal)listcdkxzs.get(0).get("DKBLL");
			BigDecimal sybll=(BigDecimal)listcdkxzs.get(0).get("SYBLL");
			BigDecimal ncbll=(BigDecimal)listcdkxzs.get(0).get("NCBLL");
			xzckhs=ckhs.intValue();
			xzckbs=ckzh.intValue();
			xzdkhs=dkhs.intValue();
			xzdkbs=dkzh.intValue();
			dkbll=bll.doubleValue();
			sydkbll=sybll.doubleValue();

			bncxzckhs=bncckhs.intValue();
			bncxzckbs=bncckzh.intValue();
			bncxzdkhs=bncdkhs.intValue();
			bncxzdkbs=bncdkzh.intValue();
			ncdkbll=ncbll.doubleValue();


		}

		bsydkbll = dkbll - sydkbll;
		bncdkbll = dkbll - ncdkbll;

		if(bzrckye<0){
			obj.put("ckbs", "down");
			bzrckye=-1*bzrckye;
		}else{
			obj.put("ckbs", "up");
		}


		if(bzrdkye<0){
			obj.put("dkbs", "down");
			bzrdkye=-1*bzrdkye;
		}else{
			obj.put("dkbs", "up");
		}

		if(bncckye<0){
			obj.put("bncckbs", "down");
			bncckye=-1*bncckye;
		}else{
			obj.put("bncckbs", "up");
		}
		if(bncdkye<0){
			obj.put("bncdkbs", "down");
			bncdkye=-1*bncdkye;
		}else{
			obj.put("bncdkbs", "up");
		}
		if(bncyrp<0){
			obj.put("bncyrpbs", "down");
			bncyrp=-1*bncyrp;
		}else{
			obj.put("bncyrpbs", "up");
		}

		if(bzrshlx<0){
			obj.put("lxbs", "down");
			bzrshlx=-1*bzrshlx;
		}else{
			obj.put("lxbs", "up");
		}
		if (bsydkbll < 0) {
			obj.put("bllbs", "down");
			bsydkbll = -1 * bsydkbll;
		} else {
			obj.put("bllbs", "up");
		}

		if (bncdkbll < 0) {
			obj.put("bncbllbs", "down");
			bncdkbll = -1 * bncdkbll;
		} else {
			obj.put("bncbllbs", "up");
		}

		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		String bsydkbllStr = df.format(bsydkbll);
		String bncdkbllStr = df.format(bncdkbll);

		obj.put("ckye", ckye/10000 +" 万元");
		obj.put("dkye", dkye/10000+" 万元");
		obj.put("zrckye", zrckye/10000+" 万元");
		obj.put("zrdkye", zrdkye/10000+" 万元");
		obj.put("ncckye", ncckye/10000+" 万元");
		obj.put("ncdkye", ncdkye/10000+" 万元");
		obj.put("bzrdkye", bzrdkye/10000+" 万元");
		obj.put("bzrckye", bzrckye/10000+" 万元");
		obj.put("bncdkye", bncdkye/10000+" 万元");
		obj.put("bncckye", bncckye/10000+" 万元");
		obj.put("ckrpye", ckrpye/10000+" 万元");
		obj.put("ncyrp", ncyrpye/10000+" 万元");
		obj.put("bncyrp", bncyrp/10000+" 万元");
		obj.put("grckrpye", grckrpye/10000+" 万元");
		obj.put("dgckrpye", dgckrpye/10000+" 万元");
		obj.put("xzckhs", xzckhs);
		obj.put("xzckbs", xzckbs);
		obj.put("xzdkhs", xzdkhs);
		obj.put("xzdkbs", xzdkbs);
		obj.put("bncxzckhs", bncxzckhs);
		obj.put("bncxzckbs", bncxzckbs);
		obj.put("bncxzdkhs", bncxzdkhs);
		obj.put("bncxzdkbs", bncxzdkbs);
		obj.put("dkbll", dkbll);
		obj.put("sydkbll", sydkbll);
		obj.put("ncdkbll", ncdkbll);
		obj.put("bsybldkl", bsydkbllStr);
		obj.put("bncbldkl", bncdkbllStr);
		obj.put("dksjrq", DateUtil.format(logService.dksjrq(),"yyyy-MM-dd"));

		// 获取系统访问记录
		obj.put("zfhs", zfhs!=null?zfhs:0);
		obj.put("jdrs", jdrs!=null?jdrs:0);
		obj.put("isQhqx", isQhqx);
		if(pjsxxxMap!=null){
			obj.put("sxje", pjsxxxMap.get("YSXED"));
			obj.put("sxhs", pjsxxxMap.get("YSXHS"));
			obj.put("yxje", pjsxxxMap.get("YXED"));
			obj.put("yxhs", pjsxxxMap.get("YXHS"));
		}

		if(ndzbrwMap!=null){
			BigDecimal ckrpyerwz=(BigDecimal)ndzbrwMap.get("CKRPYE");
			BigDecimal ckyerwz=(BigDecimal)ndzbrwMap.get("CKYE");
			BigDecimal dkyerwz=(BigDecimal)ndzbrwMap.get("DKYE");
			BigDecimal ckhsjzrwz=(BigDecimal)ndzbrwMap.get("CKHSJZ");
			BigDecimal dkhsjzrwz=(BigDecimal)ndzbrwMap.get("DKHSJZ");
			BigDecimal dkbllrwz=(BigDecimal)ndzbrwMap.get("DKBLL");
			obj.put("ckrpyerw", ckrpyerwz+" 万元");
			obj.put("ckyerw", ckyerwz+" 万元");
			obj.put("dkyerw", dkyerwz+" 万元");
			obj.put("xzckhsrw", ckhsjzrwz);
			obj.put("xzdkhsrw", dkhsjzrwz);
			obj.put("dkbllrw", dkbllrwz);

			ckrpyerw=ckrpyerwz.longValue();
			ckyerw=ckyerwz.longValue();
			dkyerw=dkyerwz.longValue();
			xzckhsrw=ckhsjzrwz.intValue();
			xzdkhsrw=dkhsjzrwz.intValue();
			dkbllrw=dkbllrwz.doubleValue();

			Double ckrpwcl=(double)ckrpye/10000/ckrpyerw*100;
			Double ckyewcl=(double)ckye/10000/ckyerw*100;
			Double dkyewcl=(double)dkye/10000/dkyerw*100;
			Double ckhsjzwcl=(double)bncxzckhs/xzckhsrw*100;
			Double dkhsjzwcl=(double)bncxzdkhs/xzckhsrw*100;

			DecimalFormat rwqk = new DecimalFormat();
			rwqk.setMaximumFractionDigits(2);
			String ckrpwclStr = df.format(ckrpwcl);
			String ckyewclStr = df.format(ckyewcl);
			String dkyewclStr = df.format(dkyewcl);
			String ckhsjzwclStr = df.format(ckhsjzwcl);
			String dkhsjzwclStr = df.format(dkhsjzwcl);
			obj.put("ckrpwcl", ckrpwclStr);
			obj.put("ckyewcl", ckyewclStr);
			obj.put("dkyewcl", dkyewclStr);
			obj.put("ckhsjzwcl", ckhsjzwclStr);
			obj.put("dkhsjzwcl", dkhsjzwclStr);
		}


		result.setResult(obj);
		result.success("登录成功");
		return result;
	}

	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping(value = "/appUserInfo")
	public Result<Object> appUserInfo(HttpServletRequest request,HttpServletResponse response) {
		LoginUser loginUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		jsonArray.add("admin");
		obj.put("roles", jsonArray);
		JSONObject pubInfoObject = new JSONObject();
		//查询是否开通三级营销单元
		String sfkt = tjfxcsszService.selectcsz(); //1是 2否
		if (StringUtils.isEmpty(sfkt)) {
			sfkt = "2";
		}
//		sfkt = "2";
		//查询区域编码
		SysDic sysDic = sysDicService.queryByCode("101001");
		pubInfoObject.put("sjyxdyFlag", sfkt);
		pubInfoObject.put("qybm", sysDic.getValue());
		obj.put("pubInfo", pubInfoObject);
		redisUtil.set(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername(), sysDic.getValue());
		//获取村社权限列表
		return Result.ok("获取用户信息成功！", obj);
	}


}
