package org.cmms.modules.system.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CacheConstant;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.api.ISysBaseAPI;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.util.JwtUtil;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.*;
import org.cmms.common.util.encryption.EncryptedString;
import org.cmms.common.util.encryption.RSAEncryptUtil;
import org.cmms.common.utils.ListToDictUtil;
import org.cmms.config.EtlConfig;
import org.cmms.config.properties.ActivitiExtendProperties;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;
import org.cmms.modules.hr.yggl.ygxxgl.service.IVhrbasstaffpostService;
import org.cmms.modules.sgtz.sjtb.entity.EtlSgtzSjtb;
import org.cmms.modules.sgtz.sjtb.service.IEtlSgtzSjtbService;
import org.cmms.modules.shiro.vo.DefContants;
import org.cmms.modules.system.entity.*;
import org.cmms.modules.system.model.SysLoginModel;
import org.cmms.modules.system.service.*;
import org.cmms.modules.util.CryptoUtils;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @Author scott
 * @since 2018-12-17
 */
@RestController
@RequestMapping("/sys")
@Api(tags="用户登录")
@Slf4j
public class LoginController extends JeecgController<SysUser,ISysUserService> {
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private ISysBaseAPI sysBaseAPI;
	@Autowired
	private ISysLogService logService;
	@Autowired
	private ISysLogMxService logMxService;
	@Autowired
    private RedisUtil redisUtil;
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	@Autowired
	private ISysDicService  sysDicService;
	@Autowired
	private IHrBasOrganizationService  hrBasOrganizationService;

	@Autowired
	private IVhrbasstaffpostService vhrbasstaffpostService;

	@Autowired
	ISysBasUserService sysBasUserService;
	@Autowired
	ISysRoleService sysRoleService;

	@Autowired
	IEtlSgtzSjtbService etlSgtzSjtbService;

	@Autowired
	ISysLogService sysLogService;
	@Autowired
	ISysLoginInfoLogService sysLoginInfoLogService;

	@Value("${com.etl.etlName:数据下发ETL任务}")
	private  String etlName;

	@Value("${com.etl.dagName:etl_day调度}")
	private  String dagName;

	@Value("${com.etl.sfdsjpt}")
	private String sfdsjpt;

	@Autowired
	ListToDictUtil listToDictUtil;



	private static final String BASE_CHECK_CODES = "qwertyuiplkjhgfdsazxcvbnmQWERTYUPLKJHGFDSAZXCVBNM1234567890";


	@ApiOperation("密码输入校验规则")
	@RequestMapping(value = "/getValidatePassWordRule",method = RequestMethod.GET)
	public Result<JSONObject>  initGetPassWordValidateRule()
	{
		Result<JSONObject> result =sysUserService.selectPassWordVaildateRule();
		result.setCode(200);
		return result;

	}

	@ApiOperation("登录接口")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Result<JSONObject> login(@RequestBody SysLoginModel sysLoginModel){
		System.out.println(sysLoginModel.toString());
		Result<JSONObject> result = new Result<JSONObject>();
		String usernameEncrypt = sysLoginModel.getUsernameEncrypt();
		String passwordEncrypt = sysLoginModel.getPasswordEncrypt();
		String username = sysLoginModel.getUsername();
		String password = sysLoginModel.getPassword();

		//兼容APP  未加密的密码进行加密
		if (StringUtils.isNotEmpty(password) && password.length() != 32){
			password = MD5Util.MD5(password).toUpperCase();
		}

		//加密后的用户名与密码
		if (!StringUtils.isEmpty(usernameEncrypt) && !StringUtils.isEmpty(passwordEncrypt)) {
			//解密
			username = RSAEncryptUtil.desEncrypt(usernameEncrypt);
			password = RSAEncryptUtil.desEncrypt(passwordEncrypt);
		}
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return result.error500("用户名或密码不正确！");
		}
		password = password.toUpperCase();
		//update-begin--Author:scott  Date:20190805 for：暂时注释掉密码加密逻辑，有点问题
		//前端密码加密，后端进行密码解密
		//password = AesEncryptUtil.desEncrypt(sysLoginModel.getPassword().replaceAll("%2B", "\\+")).trim();//密码解密
		//update-begin--Author:scott  Date:20190805 for：暂时注释掉密码加密逻辑，有点问题

		//1. 校验用户是否有效
		SysUser sysUser = sysUserService.getUserByName(username);
		result = sysUserService.checkUserIsEffective(sysUser);
		if(!result.isSuccess()) {
			return result;
		}

		//2. 校验用户名或密码是否正确
		result = sysUserService.checkUserPassword(sysUser, password);
		if (!result.isSuccess()) {
			return result;
		}
		if(result.getMessage().equals("已超过六个月没修改密码,请修改密码"))
		{
			return  result;
		}

		session.setAttribute("sys_user",sysUser);

		//用户登录信息
		userInfo(sysUser, result);
		if (!result.isSuccess()) {
			return result;
		}
		logService.addLog("用户名: " + username + ",登录成功！", CommonConstant.LOG_TYPE_1, null);
		return result;
	}




	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public Result<Object> logout(HttpServletRequest request,HttpServletResponse response) {
		//用户退出逻辑
	    String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
	    if(oConvertUtils.isEmpty(token)) {
	    	return Result.error("退出登录失败！");
	    }
		SysDic sysDic = sysDicService.queryByCode("101001");
		String userAgent = SpringContextUtils.getHttpServletRequest().getHeader("User-Agent");
		UserAgent ua = UserAgentUtil.parse(userAgent);
	    String username = JwtUtil.getUsername(token);
		LoginUser sysUser = sysBaseAPI.getUserByName(username);
	    if(sysUser!=null) {
	    	logService.addLog("用户名: "+sysUser.getRealname()+",退出成功！", CommonConstant.LOG_TYPE_1, null);

			//移动端才进行校验
			if (ua.isMobile()){
				logMxService.addLogoutMxLog(username, "2", new Date());
				if ("350".equals(sysDic.getValue())) {
					sysLoginInfoLogService.addLogoutInfoLog(username, "2", new Date());
				}
			}else {
				logMxService.addLogoutMxLog(username, "1", new Date());
				if ("350".equals(sysDic.getValue())) {
					sysLoginInfoLogService.addLogoutInfoLog(username, "1", new Date());
				}
			}

	    	log.info(" 用户名:  "+sysUser.getRealname()+",退出成功！ ");
	    	//清空用户登录Token缓存
	    	redisUtil.del(CommonConstant.PREFIX_USER_TOKEN + token);
	    	redisUtil.del(CommonConstant.PREFIX_USER_TOKEN + username);
	    	//清空用户登录Shiro权限缓存
			redisUtil.del(CommonConstant.PREFIX_USER_SHIRO_CACHE + sysUser.getId());
			//清空用户的缓存信息（包括部门信息），例如sys:cache:user::<username>
			redisUtil.del(String.format("%s::%s", CacheConstant.SYS_USERS_CACHE, sysUser.getUsername()));

			redisUtil.del(CommonConstant.PREFIX_USER_YJYXDY + username);
			redisUtil.del(CommonConstant.PREFIX_USER_EJYXDY + username);
			redisUtil.del(CommonConstant.PREFIX_USER_QYBM + username);
			//调用shiro的logout
			SecurityUtils.getSubject().logout();
	    	return Result.ok("退出登录成功！");
	    }else {
	    	return Result.error("Token无效!");
	    }
	}

	/**
	 * 获取访问量
	 * @return
	 */
	@GetMapping("loginfo")
	public Result<JSONObject> loginfo() {
		Result<JSONObject> result = new Result<JSONObject>();
		JSONObject obj = new JSONObject();
		//update-begin--Author:zhangweijian  Date:20190428 for：传入开始时间，结束时间参数
		// 获取一天的开始和结束时间
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date dayStart = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date dayEnd = calendar.getTime();
		// 获取系统访问记录
		Long totalVisitCount = logService.findTotalVisitCount();
		obj.put("totalVisitCount", totalVisitCount);
		Long todayVisitCount = logService.findTodayVisitCount(dayStart,dayEnd);
		obj.put("todayVisitCount", todayVisitCount);
		Long todayIp = logService.findTodayIp(dayStart,dayEnd);
		//update-end--Author:zhangweijian  Date:20190428 for：传入开始时间，结束时间参数
		obj.put("todayIp", todayIp);
		result.setResult(obj);
		result.success("登录成功");
		return result;
	}

	/**
	 * 移动段获取用户信息
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping(value = "/appUserInfo")
	public Result<Object> appUserInfo(HttpServletRequest request,HttpServletResponse response) {
		LoginUser loginUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();

		Vhrbasstaffpost vhrbasstaffpost = vhrbasstaffpostService.selectByYggh(loginUser.getWorkNo());


		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		jsonArray.add("admin");
		List<VsysUserRole> roles = null;
		try {
			roles = sysUserRoleService.findByUserId(loginUser.getId());
			obj.put("roles", roles);
		} catch (Exception e) {
			obj.put("roles", jsonArray);
			e.printStackTrace();
		}
		if (roles == null || roles.size()==0 ){
			obj.put("roles", jsonArray);
		}

		List<HrBasOrganization> departs = hrBasOrganizationService.queryUserDeparts(loginUser.getId());
		obj.put("departs", departs);
		JSONObject pubInfoObject = new JSONObject();
		//查询是否开通三级营销单元
		//String sfkt = tjfxcsszService.selectcsz(); //1是 2否
		//if (StringUtils.isEmpty(sfkt)) {
		String sfkt = "2";

		QueryWrapper<SysLogMx> wrapper = new QueryWrapper<>();
		DateTime rq = cn.hutool.core.date.DateUtil.beginOfDay(new Date());
		wrapper.eq("rq",rq);
		wrapper.eq("userid",loginUser.getUsername());
		wrapper.eq("login_device",2);
		SysLogMx sysLogMx = logMxService.getOne(wrapper,false);
		if(sysLogMx!=null&&sysLogMx.getNumberOfLogins()==1){
			pubInfoObject.put("sfscdl", 1);
		}else{
			pubInfoObject.put("sfscdl", 2);
		}


		//}
		//查询区域编码
		SysDic sysDic = sysDicService.queryByCode("101001");
		pubInfoObject.put("sjyxdyFlag", sfkt);
		pubInfoObject.put("qybm", sysDic.getValue());
		pubInfoObject.put("workNo",loginUser.getWorkNo());
		pubInfoObject.put("zzbz",vhrbasstaffpost.getZzbz());
		pubInfoObject.put("zzmc",vhrbasstaffpost.getZzjc());
		pubInfoObject.put("gwbz",vhrbasstaffpost.getGwbz());
		pubInfoObject.put("gwbz",vhrbasstaffpost.getGwbz());
		pubInfoObject.put("gwmc",vhrbasstaffpost.getGwmc());
		pubInfoObject.put("ygxm",vhrbasstaffpost.getYgxm());
//		pubInfoObject.put("qybm", "405");
		obj.put("pubInfo", pubInfoObject);
		redisUtil.set(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername(), sysDic.getValue());
		//获取村社权限列表
		return Result.ok("获取用户信息成功！", obj);
	}


	/**
	 * 获取客户数
	 * @return
	 */
	@GetMapping("khCount")
	public Result<List<Map<String,Object>>> khCount(@RequestParam(name="jgdm", defaultValue="1") String zzbz) {
		Result<List<Map<String,Object>>> result = new Result<List<Map<String,Object>>>();
		// 获取系统访问记录
		String ywjgdm=logService.getywjgdm(zzbz);
		String zzlb=logService.getzzlb(zzbz);

		if(zzbz.equals("1")||zzlb.equals("3")||zzlb.equals("1")){
			List<Map<String,Object>> list = logService.zhkhCount();
			result.setResult(oConvertUtils.toLowerCasePageList(list));
		}else{
			List<Map<String,Object>> list = logService.khCount(ywjgdm);
			result.setResult(oConvertUtils.toLowerCasePageList(list));

		}
		result.success("获取成功");
		return result;
	}

	@GetMapping("getcdkye")
	public Result<JSONObject> ckye(@RequestParam(name="jgdm", defaultValue="1") String zzbz) {
		Result<JSONObject> result = new Result<JSONObject>();
		JSONObject obj = new JSONObject();
		String ywjgdm=logService.getywjgdm(zzbz);
		String sjzzbz=logService.getsjzzbz(zzbz);
		String zzlb=logService.getzzlb(zzbz);
		String zrckrq=logService.zrcksjrq().substring(2);
		String zrdkrq=logService.zrdksjrq().substring(2);
		Long ckye=0l;
		Long dkye=0l;
		Long shlx=0l;
		Long zrshlx=0l;
		Long zrckye=0l;
		Long zrdkye=0l;
		if(zzbz.equals("1")||zzlb.equals("3")){
			ckye = logService.zhckye()!=null?logService.zhckye():0;
			dkye = logService.zhdkye()!=null?logService.zhdkye():0;
			shlx = logService.zhshlx(logService.dksjrq())!=null?logService.zhshlx(logService.dksjrq()):0;
			zrckye = logService.zrzhckye("ZDCBSINVMBASE"+zrckrq)!=null?logService.zrzhckye("ZDCBSINVMBASE"+zrckrq):0;
			zrdkye = logService.zrzhdkye("ZDCBSBORMBASE"+zrdkrq)!=null?logService.zrzhdkye("ZDCBSBORMBASE"+zrdkrq):0;
			zrshlx = logService.zhshlx(DateUtil.addDays(logService.dksjrq(),-1))!=null?logService.zhshlx(DateUtil.addDays(logService.dksjrq(),-1)):0;
		}else{
			ckye = logService.ckye(ywjgdm)!=null?logService.ckye(ywjgdm):0;
			dkye = logService.dkye(ywjgdm)!=null?logService.dkye(ywjgdm):0;
			shlx = logService.shlx(logService.dksjrq(),ywjgdm)!=null?logService.shlx(logService.dksjrq(),ywjgdm):0;
			zrshlx = logService.shlx(DateUtil.addDays(logService.dksjrq(),-1),ywjgdm)!=null?logService.shlx(DateUtil.addDays(logService.dksjrq(),-1),ywjgdm):0;
			zrckye = logService.zrckye("ZDCBSINVMBASE"+zrckrq,ywjgdm)!=null?logService.zrckye("ZDCBSINVMBASE"+zrckrq,ywjgdm):0;
			zrdkye = logService.zrdkye("ZDCBSBORMBASE"+zrdkrq,ywjgdm)!=null?logService.zrdkye("ZDCBSBORMBASE"+zrdkrq,ywjgdm):0;

		}
		Long bzrckye=ckye-zrckye;
		Long bzrdkye=dkye-zrdkye;
		Long bzrshlx=shlx-zrshlx;

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
		if(bzrshlx<0){
			obj.put("lxbs", "down");
			bzrshlx=-1*bzrshlx;
		}else{
			obj.put("lxbs", "up");
		}
		obj.put("ckye", ckye);
		obj.put("dkye", dkye);
		obj.put("shlx", shlx);
		obj.put("zrckye", zrckye);
		obj.put("zrdkye", zrdkye);
		obj.put("zrshlx", zrshlx);
		obj.put("bzrdkye", bzrdkye);
		obj.put("bzrckye", bzrckye);
		obj.put("bzrshlx", bzrshlx);
		obj.put("dksjrq", DateUtil.format(logService.dksjrq(),"yyyy-MM-dd"));
		// 获取系统访问记录
		result.setResult(obj);
		result.success("登录成功");
		return result;
	}

	/*@GetMapping("getHomePageInfo")
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
*/

	/**
	 * 获取访问量
	 * @return
	 */
	@GetMapping("visitInfo")
	public Result<List<Map<String,Object>>> visitInfo() {
		Result<List<Map<String,Object>>> result = new Result<List<Map<String,Object>>>();
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date dayEnd = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        Date dayStart = calendar.getTime();
        List<Map<String,Object>> list = logService.findVisitCount(dayStart, dayEnd);
		result.setResult(oConvertUtils.toLowerCasePageList(list));
		return result;
	}


	/**
	 * 登陆成功选择用户当前部门
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/selectDepart", method = RequestMethod.PUT)
	public Result<JSONObject> selectDepart(@RequestBody SysUser user) {
		Result<JSONObject> result = new Result<JSONObject>();
		String username = user.getUsername();
		if(oConvertUtils.isEmpty(username)) {
			LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
			username = sysUser.getUsername();
		}
		JSONObject obj = new JSONObject();
		String orgCode= user.getOrgCode();
		this.sysUserService.updateUserDepart(username, orgCode);
		SysUser sysUser = sysUserService.getUserByName(username);
		SysDic sysDic = sysDicService.queryByCode("101001");
		HrBasOrganization organization = hrBasOrganizationService.queryByZzbz(orgCode);
		if(sysDic != null && sysDic.getValue().equalsIgnoreCase(organization.getYwjgdm())) {
			obj.put("isRoot", true);
		} else {
			obj.put("isRoot", false);
		}
		obj.put("userInfo", sysUser);
		result.setResult(obj);
		return result;
	}

	/**
	 * 短信登录接口
	 *
	 * @param jsonObject
	 * @return
	 */
	@PostMapping(value = "/sms")
	public Result<String> sms(@RequestBody JSONObject jsonObject) {
		Result<String> result = new Result<String>();
		String mobile = jsonObject.get("mobile").toString();
		String smsmode=jsonObject.get("smsmode").toString();
		log.info(mobile);
		Object object = redisUtil.get(mobile);
		if (object != null) {
			result.setMessage("验证码10分钟内，仍然有效！");
			result.setSuccess(false);
			return result;
		}

		//随机数
		String captcha = RandomUtil.randomNumbers(6);
		JSONObject obj = new JSONObject();
    	obj.put("code", captcha);
		try {
			boolean b = false;
			//注册模板
			if (CommonConstant.SMS_TPL_TYPE_1.equals(smsmode)) {
				SysUser sysUser = sysUserService.getUserByPhone(mobile);
				if(sysUser!=null) {
					result.error500(" 手机号已经注册，请直接登录！");
					logService.addLog("手机号已经注册，请直接登录！", CommonConstant.LOG_TYPE_1, null);
					return result;
				}
				b = DySmsHelper.sendSms(mobile, obj, DySmsEnum.REGISTER_TEMPLATE_CODE);
			}else {
				//登录模式，校验用户有效性
				SysUser sysUser = sysUserService.getUserByPhone(mobile);
				result = sysUserService.checkUserIsEffective(sysUser);
				if(!result.isSuccess()) {
					return result;
				}

				/**
				 * smsmode 短信模板方式  0 .登录模板、1.注册模板、2.忘记密码模板
				 */
				if (CommonConstant.SMS_TPL_TYPE_0.equals(smsmode)) {
					//登录模板
					b = DySmsHelper.sendSms(mobile, obj, DySmsEnum.LOGIN_TEMPLATE_CODE);
				} else if(CommonConstant.SMS_TPL_TYPE_2.equals(smsmode)) {
					//忘记密码模板
					b = DySmsHelper.sendSms(mobile, obj, DySmsEnum.FORGET_PASSWORD_TEMPLATE_CODE);
				}
			}

			if (b == false) {
				result.setMessage("短信验证码发送失败,请稍后重试");
				result.setSuccess(false);
				return result;
			}
			//验证码10分钟内有效
			redisUtil.set(mobile, captcha, 600);
			//update-begin--Author:scott  Date:20190812 for：issues#391
			//result.setResult(captcha);
			//update-end--Author:scott  Date:20190812 for：issues#391
			result.setSuccess(true);

		} catch (ClientException e) {
			e.printStackTrace();
			result.error500(" 短信接口未配置，请联系管理员！");
			return result;
		}
		return result;
	}


	/**
	 * 手机号登录接口
	 *
	 * @param jsonObject
	 * @return
	 */
	@PostMapping("/phoneLogin")
	public Result<JSONObject> phoneLogin(@RequestBody JSONObject jsonObject) {
		Result<JSONObject> result = new Result<JSONObject>();
		String phone = jsonObject.getString("mobile");

		//校验用户有效性
		SysUser sysUser = sysUserService.getUserByPhone(phone);

		result = sysUserService.checkUserIsEffective(sysUser);
		if(!result.isSuccess()) {
			return result;
		}

		String smscode = jsonObject.getString("captcha");
		Object code = redisUtil.get(phone);
		if (!smscode.equals(code)) {
			result.setMessage("手机验证码错误");
			return result;
		}
		//用户信息
		userInfo(sysUser, result);
		//添加日志
		logService.addLog("用户名: " + sysUser.getUsername() + ",登录成功！", CommonConstant.LOG_TYPE_1, null);

		return result;
	}

	/**
	 * ETL调用接口
	 *
	 * @param jsonObject
	 * @return
	 */
	@PostMapping("/calletl")
	public Result<JSONObject> calletl(@RequestBody JSONObject jsonObject) {
		Boolean sftbcg =true;
		Result result = new Result<>();
		Map<String, String> params = JSONObject.parseObject(jsonObject.toJSONString(),new TypeReference<Map<String, String>>(){});
		if(StringUtils.isEmpty(params.get("app"))){
			params.put("app","ads");
		}
		String fiscalDate = params.get("fiscal_date");
		QueryWrapper<EtlSgtzSjtb> queryWrapper=new QueryWrapper();
		queryWrapper.eq("etl_code",params.get("etl_task"));
		EtlSgtzSjtb etlSgtzSjtb= etlSgtzSjtbService.getOne(queryWrapper);
		if(StringUtils.isNotEmpty(fiscalDate)){
			log.info("etlName======================"+etlName);
			log.info("dagName======================"+dagName);

			String zdrkrq = etlSgtzSjtbService.getZdrkrq(etlName==null?"数据下发ETL任务":etlName, dagName==null?"etl_day调度":dagName);
			if(StringUtils.isNotEmpty(zdrkrq)){
				String tjrq= fiscalDate.replace("-","");
				String rkrq= zdrkrq.replace("-","");
				Date  tjrqDate=DateUtil.string2Date(tjrq,"yyyyMMdd");
				Date  rkrqDate=DateUtil.string2Date(rkrq,"yyyyMMdd");
				if (etlSgtzSjtb!=null&&StringUtils.isNotBlank(etlSgtzSjtb.getTqrqpdtsbs())) {
					if (etlSgtzSjtb.getTqrqpdtsbs().equals("1")) {
						if (rkrqDate.before(tjrqDate)) {
							result.setMessage("提取失败，计算日期不能大于最大入库日期[" + zdrkrq + "]");
							result.setSuccess(false);
							return result;
						}
					}
				}

			}else{
				result.setMessage("提取失败，未获取到最大入库日期！");
				result.setSuccess(false);
				return result;
			}

		}


		if(StringUtils.isEmpty(params.get("etl_task"))){
			result.setMessage("提取失败，ETL_TASK为空！");
			result.setSuccess(false);
			return result;
		}




 		if(etlSgtzSjtb!=null&&StringUtils.isNotEmpty(etlSgtzSjtb.getEtlCode())){
			if(StringUtils.isNotEmpty(etlSgtzSjtb.getSgtzTablename())){
				 log.info("==========需同步的表========="+etlSgtzSjtb.getSgtzTablename());
				 sftbcg = doCallEtl(etlSgtzSjtb.getSgtzTablename());
			}
		}
 		if(sftbcg){
 			HashMap hashMap=new HashMap();
			hashMap.put("etl_task","kiss.domain.ads.load_wljrb_sgtz_zhxzmxcx_update_wd_code");
			Boolean flag=EtlUtil.callEtl("tjbb_common_init", hashMap, 10);
			//Boolean flag=EtlUtil.SHcallEtl(10, false, params.get("app"),"kiss.domain.ads.load_wljrb_sgtz_zhxzmxcx_update_wd_code");

			if(flag){
				flag=EtlUtil.callEtl("tjbb_common_init", (HashMap) params, 10);
				//flag=EtlUtil.SHcallEtl(10,false,params.get("app"),params.get("etl_task"),params.get("fiscal_date"));
				if(flag){
					result.setMessage("提取成功");
					result.setSuccess(true);
					return result;
				}else{
					result.setMessage("提取失败，请联系管理员！");
					result.setSuccess(false);
					return result;
				}
			}else{
				result.setMessage("提取失败，公共区域代码更新失败！");
				result.setSuccess(false);
				return result;
			}

		}else{
			result.setMessage("提取失败，手工台账数据同步失败！");
			result.setSuccess(false);
			return result;
		}
	}


	public Boolean doCallEtl(String tables) {
		if(tables.indexOf(",")>0){
			String[] split = tables.split(",");
			for(String sgtzTable:split){
				HashMap hashMap=new HashMap();
				hashMap.put("table",sgtzTable);
				hashMap.put("schema","ads");
				log.info("==========正在同步的表========="+sgtzTable);
				etlSgtzSjtbService.trimData(sgtzTable);
				boolean flag = EtlUtil.callEtl("table_sync", hashMap, 10);
				//boolean flag = EtlUtil.SHcallEtl(10,true,sgtzTable,"ads");
				if(flag){
					log.info("==========同步成功的表========="+sgtzTable);
					continue;
				}else{
					log.info("==========同步失败的表========="+sgtzTable);
					return false;
				}
			}
			return true;
		}else{
			HashMap hashMap=new HashMap();
			hashMap.put("table",tables);
			hashMap.put("schema","ads");
			log.info("==========正在同步的表========="+tables);
			etlSgtzSjtbService.trimData(tables);
			boolean flag = EtlUtil.callEtl("table_sync", hashMap, 10);
			//boolean flag = EtlUtil.SHcallEtl(10,true,tables,"ads");

			if(flag){
				log.info("==========同步成功的表========="+tables);
				return true;
			}else{
				log.info("==========同步失败的表========="+tables);
				return false;
			}
		}
	}
	/**
	 * 用户信息
	 *
	 * @param sysUser
	 * @param result
	 * @return
	 */
	private Result<JSONObject> userInfo(SysUser sysUser, Result<JSONObject> result) {
		String syspassword = sysUser.getPassword();
		String username = sysUser.getUsername();
		SysDic sysDic = sysDicService.queryByCode("101001");
		if (StringUtils.isNotBlank(sysDic.getValue())){
			log.info("===当前机构代码为{}===",sysDic.getValue());
			session.setAttribute("qydm",sysDic.getValue());
		}

		JSONObject obj = new JSONObject();

		// 生成token
		String token = JwtUtil.sign(username, syspassword);
		redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
		// 设置超时时间 如果是移动端登录就不超时
		String userAgent = SpringContextUtils.getHttpServletRequest().getHeader("User-Agent");
		UserAgent ua = UserAgentUtil.parse(userAgent);
		//移动端才进行校验
		if (ua.isMobile()) {
			redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, -1);
			logMxService.addLoginMxLog(username,sysUser.getRealname(),"2");
			if ("350".equals(sysDic.getValue())){
				sysLoginInfoLogService.addLoginInfoLog(username,sysUser.getRealname(),"2");
			}
		}else {
		//PC端
			//耒阳跟祁阳增加用户第一次登录强制修改密码
			if (sysDic != null && org.apache.commons.lang3.StringUtils.isNotBlank(sysDic.getValue()) && ("150".equals(sysDic.getValue())|| "130".equals(sysDic.getValue()))){
				SysBasUser byUserId = sysBasUserService.getByUserId(sysUser.getId());
				if (byUserId != null && byUserId.getLastlogintime() == null){
					result.setMessage("请修改密码");
					return result;
				}
			}
			redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME * 2 / 1000);
			logMxService.addLoginMxLog(username,sysUser.getRealname(),"1");
			if ("350".equals(sysDic.getValue())) {
				sysLoginInfoLogService.addLoginInfoLog(username, sysUser.getRealname(), "1");
			}

		}
		//保存用户对应的token信息，单点登录校验使用
		redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + username, token);
		// 获取用户部门信息
		//JSONObject obj = new JSONObject();
		List<HrBasOrganization> departs = hrBasOrganizationService.queryUserDeparts(sysUser.getId());
		obj.put("departs", departs);
		obj.put("isRoot", false);

		//如果角色是管理员也给root角色
		List<SysRole> listByUserId = sysRoleService.getListByUserId(sysUser.getId());
		if (CollUtil.isNotEmpty(listByUserId)) {
			String s = "";
			for (int i = 0; i < listByUserId.size(); i++) {
				SysRole sysRole = listByUserId.get(i);
				if (org.apache.commons.lang3.StringUtils.isNotBlank(sysRole.getRoleCode()) && "admin".equalsIgnoreCase(sysRole.getRoleCode())){
					obj.put("isRoot", true);
				}
				//直接把中文名称存起来
				if (org.apache.commons.lang3.StringUtils.isNotBlank(sysRole.getRoleName())){
					s += sysRole.getRoleName()+",";
				}
			}
			redisUtil.set(CommonConstant.PREFIX_USER_ROLE_NAME + username, s);


			if (org.apache.commons.lang3.StringUtils.isNotBlank(listByUserId.get(0).getRoleCode())) {
				redisUtil.set(CommonConstant.PREFIX_USER_ROLE_CODE + username, listByUserId.get(0).getRoleCode());
			}
		}

		if (departs == null || departs.size() == 0) {
			obj.put("multi_depart", 0);
		} else if (departs.size() == 1) {
			sysUserService.updateUserDepart(username, departs.get(0).getZzbz());
			HrBasOrganization organization = hrBasOrganizationService.queryByZzbz(departs.get(0).getZzbz());
			redisUtil.set(CommonConstant.PREFIX_USER_JGDM + username, organization.getYwjgdm());
			if(sysDic != null && sysDic.getValue().equalsIgnoreCase(organization.getYwjgdm())) {
				obj.put("isRoot", true);
			}
			obj.put("multi_depart", 1);
		} else {
			obj.put("multi_depart", 2);
		}
		redisUtil.set(CommonConstant.PREFIX_USER_QYBM + username, sysDic.getValue());
		obj.put("token", token);
		//加上字段转换
		Object o1 = listToDictUtil.parseDictText(sysUser);
		if (o1 != null){
			obj.put("userInfo", o1);
		}else {
			obj.put("userInfo", sysUser);
		}
		obj.put("qybm",sysDic.getValue());
		//判断是不是我要的角色
		boolean flag = false;
		List<SysRole> list = sysUserRoleService.getUserRoleCode(sysUser.getId());
		for (int i = 0; i < list.size(); i++) {
			SysRole sysRole = list.get(i);
			if (sysRole.getRoleCode().equals("zhjhy")){
				flag = true;
				break;
			}
		}
		obj.put("isZhjhy", flag);

		try {
		String zdrkrq = "";
		if ("true".equalsIgnoreCase(sfdsjpt)) {
			zdrkrq = etlSgtzSjtbService.getZdrkrq(etlName == null ? "数据下发ETL任务" : etlName, dagName == null ? "etl_day调度" : dagName);
			log.info("最大入库日期："+zdrkrq);
		}else {
			Date ckrkrq = DateUtil.string2Date(sysLogService.dksjrkrq(),"yyyyMMdd");
			Date dkrkrq = DateUtil.string2Date(sysLogService.dksjrkrq(),"yyyyMMdd");
			Date rkrq = (ckrkrq.before(dkrkrq)?ckrkrq:dkrkrq);
			zdrkrq = DateUtil.format(rkrq,DatePattern.NORM_DATE_PATTERN);
			log.info("最大入库日期："+zdrkrq);
		}
		obj.put("zdrkrq",zdrkrq);
		}catch (Exception e){
			e.printStackTrace();
		}

		//保存乡镇权限列表
		/*areaUtil.yjyxdyqxSave(username);
		//保存村权限列表
		areaUtil.ejyxdyqxSave(username);
		//保存组权限列表
		areaUtil.sjyxdyqxSave(username);*/
		result.setResult(obj);
		result.success("登录成功");
		return result;
	}

	/**
	 * 获取加密字符串
	 * @return
	 */
	@GetMapping(value = "/getEncryptedString")
	public Result<Map<String,String>> getEncryptedString(){
		Result<Map<String,String>> result = new Result<Map<String,String>>();
		Map<String,String> map = new HashMap<String,String>();
		map.put("key", EncryptedString.key);
		map.put("iv",EncryptedString.iv);
		result.setResult(map);
		return result;
	}

	@RequestMapping("/jymm")
	public Result<?> jymm(String password){
		boolean b = jyMm(password);
		if (b)
			return Result.ok();
		return Result.error("密码错误");
	}


	@RequestMapping("/updatePassword")
	public Result<?> jymm(String old,String newPass1,String newPass2){
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		boolean b = jyMm(old);
		if (!b){
			return Result.error("密码错误");
		}

		if (!newPass1.equals(newPass2)){
			return Result.error("新密码不一致");
		}

		String newPass = MD5Util.MD5(newPass1).toUpperCase();

		SysUser sysUser = new SysUser();
		sysUser.setId(loginUser.getId());
		sysUser.setPassword(newPass);
		sysUser.setDelFlag("0");
		sysUserService.updateById(sysUser);

		return Result.ok();
	}


	public boolean jyMm(String password){
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		password = MD5Util.MD5(password).toUpperCase();
		if (sysUser.getPassword().equals(password)){
			return true;
		}
		return false;
	}



	public StringBuffer getlogin(ViewBrowers vb){
		StringBuffer buf=new StringBuffer();
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("User-Agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET4.0E; .NET4.0C)");
		headerMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		headerMap.put("Accept-Encoding","gzip, deflate");
		headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
		headerMap.put("Connection","keep-alive");
		headerMap.put("Content-Type","application/x-www-form-urlencoded");
		try {
			buf = vb.sendForm("http://edqpcsapp.hnrcc.bank:17011/QPCS/logon.action", new HashMap(), headerMap, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return buf;
	}
	public StringBuffer tologin(ViewBrowers vb){
		StringBuffer buf=new StringBuffer();
		HashMap<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("userId","3650137");
		paramMap.put("password","Jj202304@");
		paramMap.put("resUrl","null");
		paramMap.put("tlrFingerValue","");
		paramMap.put("name","null");
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("User-Agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET4.0E; .NET4.0C)");
		headerMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		headerMap.put("Accept-Encoding","gzip, deflate");
		headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
		headerMap.put("Connection","keep-alive");
		headerMap.put("Content-Type","application/x-www-form-urlencoded");
		headerMap.put("Host","edqpcsapp.hnrcc.bank:17011");
		headerMap.put("Origin","http://edqpcsapp.hnrcc.bank:17011");
		headerMap.put("Referer","http://edqpcsapp.hnrcc.bank:17011/QPCS/logout.action0");
		try {
			buf = vb.sendForm("http://edqpcsapp.hnrcc.bank:17011/QPCS/logon.action",paramMap, headerMap, "utf-8");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return buf;
	}

	public StringBuffer getzzbglb(ViewBrowers vb,Long nodate){
		StringBuffer buf=new StringBuffer();
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("User-Agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET4.0E; .NET4.0C)");
		headerMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		headerMap.put("Accept-Encoding","gzip, deflate");
		headerMap.put("Accept-Language","zh-CN");
		headerMap.put("Connection","keep-alive");
		headerMap.put("Content-Type"," text/html; charset=UTF-8");
		headerMap.put("Host","edqpcsapp.hnrcc.bank:17011");
		headerMap.put("Origin","http://edqpcsapp.hnrcc.bank:17011");
		headerMap.put("Referer","http://edqpcsapp.hnrcc.bank:17011/QPCS/logon.action");
		try {
			buf = vb.sendForm("http://edqpcsapp.hnrcc.bank:17011/QPCS/qryPersonCreditApply.action?customerQuery.creditType=0&nodate="+nodate,new HashMap(), headerMap, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return buf;
	}

	public StringBuffer getzzbglbBycx(ViewBrowers vb,Long nodate){
		StringBuffer buf=new StringBuffer();
		HashMap<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("customerQuery.creditType","0");
		paramMap.put("customerQuery.workflowState","");
		paramMap.put("customerQuery.customName","");
		paramMap.put("customerQuery.workflowStateDesc","");
		paramMap.put("customerQuery.queryStartDate",DateUtil.getWebDateString(new Date()));
		paramMap.put("customerQuery.queryEndDate",DateUtil.getWebDateString(new Date()));

		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("User-Agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET4.0E; .NET4.0C)");
		headerMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		headerMap.put("Accept-Encoding","gzip, deflate");
		headerMap.put("Accept-Language","zh-CN");
		headerMap.put("Connection","keep-alive");
		headerMap.put("Content-Type","application/x-www-form-urlencoded");
		headerMap.put("Host","edqpcsapp.hnrcc.bank:17011");
		headerMap.put("Origin","http://edqpcsapp.hnrcc.bank:17011");
		headerMap.put("Referer","http://edqpcsapp.hnrcc.bank:17011/QPCS/qryPersonCreditApply.action?customerQuery.creditType=0&nodate="+nodate);

		try {
			buf = vb.sendForm("http://edqpcsapp.hnrcc.bank:17011/QPCS/qryPersonCreditApply.action",paramMap, headerMap, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return buf;
	}





	public void  getZxbghtml(String html,List<Map<String,String>> mapList){
		List<Element> list=new ArrayList<>();
		Document document = Jsoup.parse(html);
		Element body = document.body();
		Elements elementsByClass = body.getElementsByClass("c-info-list");
		for(Element tables :elementsByClass){
			Elements trs = tables.getElementsByTag("tr");
			for(Element tr: trs ){
				Elements tds = tr.getElementsByTag("td");
				for(int i=0;i<tds.size();i++){
					Element td=tds.get(i);
					if(i==tds.size()-1){
						Elements img = td.getElementsByTag("img");
						if(img.size()>=2){
							Element element = img.get(1);
							String title = element.attr("title");
							if("查看影像".equals(title)){
								System.out.println("==================================");
								list.add(tr);
							}
						}
					}
				}
			}
		}
        System.out.println("ZZZZZZZZZZZZZZZZZList"+list.size());
		for(Element tr:list){
			Elements tds = tr.getElementsByTag("td");
			Map map =new HashMap();
			for(int i=0;i<tds.size();i++){
				if(i==1){
					map.put("xm",tds.get(i).text());
				}else if(i==3){
					map.put("sfzh",tds.get(i).text());
				}else if(i==5){
					map.put("rq",tds.get(i).text());
				}else if(i==tds.size()-1){
					Elements img = tds.get(i).getElementsByTag("img");
					if(img.size()>=2){
						Element element = img.get(1);
						String onclick = element.attr("onclick");
						String substring = onclick.substring(onclick.indexOf("(")+1, onclick.indexOf(")")).replaceAll("'","");
						map.put("cs",substring);
					}
				}
			}
			mapList.add(map);
		}
	}

	public StringBuffer tozxbb1(ViewBrowers vb,String id){
		StringBuffer buf=new StringBuffer();
		HashMap<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("customerQuery.creditType","0");
		paramMap.put("customerQuery.workflowState","");
		paramMap.put("customerQuery.id",id);
		paramMap.put("customerQuery.customName","");
		paramMap.put("customerQuery.workflowStateDesc","");


		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("User-Agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET4.0E; .NET4.0C)");
		headerMap.put("Content-Type","application/x-www-form-urlencoded");
		headerMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		headerMap.put("Accept-Encoding","gzip, deflate");
		headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
		headerMap.put("Connection","keep-alive");
		headerMap.put("Host","edqpcsapp.hnrcc.bank:17011");
		headerMap.put("Origin","http://edqpcsapp.hnrcc.bank:17011");
		headerMap.put("Referer","http://edqpcsapp.hnrcc.bank:17011/QPCS/qpcs/creditApply/qryPersonCreditApply.action?customerQuery.creditType=0");

		try {
			buf = vb.sendForm("http://edqpcsapp.hnrcc.bank:17011/QPCS/qpcs/creditApply/getValidCreditReportInfo.action",paramMap, headerMap, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return buf;
	}
	public StringBuffer tozxbb2Referer(ViewBrowers vb,String id){
		String[] cs =id.split(",");
		StringBuffer buf=new StringBuffer();

		HashMap<String,String> paramMap = new HashMap<String,String>();
	/*	paramMap.put("customerQueryId",cs[0]);
		paramMap.put("fileGroupTypeId",cs[1]);
		paramMap.put("mainId",cs[2]);
		paramMap.put("creditType","0");
		paramMap.put("returnUrl","qryPersonCreditApply");
		paramMap.put("activeId",cs[4]);
		paramMap.put("realMainId",cs[2]);
		paramMap.put("isCreditQuery","true");
*/
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("User-Agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET4.0E; .NET4.0C)");
		headerMap.put("Content-Type","application/x-www-form-urlencoded");
		headerMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		headerMap.put("Accept-Encoding","gzip, deflate");
		headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
		headerMap.put("Connection","keep-alive");
		headerMap.put("Host","edqpcsapp.hnrcc.bank:17011");
		headerMap.put("Origin","http://edqpcsapp.hnrcc.bank:17011");
		headerMap.put("Referer","http://edqpcsapp.hnrcc.bank:17011/QPCS/qpcs/creditApply/getValidCreditReportInfo.action");

		try {
			buf = vb.sendForm("http://edqpcsapp.hnrcc.bank:17011/QPCS/qpcs/creditApply/viewCreditApplyImage.jsp?customerQueryId="+cs[0]+"&fileGroupTypeId="+cs[1]+"&mainId="+cs[2]+"&creditType=0&returnUrl=qryPersonCreditApply&activeId="+cs[4]+"&realMainId="+cs[2]+"&isCreditQuery=true",
					paramMap, headerMap, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return buf;
	}

	public StringBuffer tozxbb2(ViewBrowers vb,String id){
		String[] cs =id.split(",");
		StringBuffer buf=new StringBuffer();
		HashMap<String,String> paramMap = new HashMap<String,String>();
		/*paramMap.put("cmd","{creditQry_Id:'"+cs[0]+"',creditType:'0',nocache:'78795886'}");*/

		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("User-Agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET4.0E; .NET4.0C)");
		headerMap.put("Content-Type","application/x-www-form-urlencoded");
		headerMap.put("Accept","application/json, text/javascript, */*");
		headerMap.put("Accept-Encoding","gzip, deflate");
		headerMap.put("Cache-Control","no-cache");
		headerMap.put("Accept-Language","zh-CN");
		headerMap.put("Connection","keep-alive");
		headerMap.put("Host","edqpcsapp.hnrcc.bank:17011");
		headerMap.put("Origin","http://edqpcsapp.hnrcc.bank:17011");
		headerMap.put("Referer","http://edqpcsapp.hnrcc.bank:17011/QPCS/qpcs/creditApply/viewCreditApplyImage.jsp?customerQueryId="+cs[0]+"&fileGroupTypeId="+cs[1]+"&mainId="+cs[2]+"&creditType=0&returnUrl=qryPersonCreditApply&activeId="+cs[4]+"&realMainId="+cs[2]+"&isCreditQuery=true");

		try {
			buf = vb.sendForm("http://edqpcsapp.hnrcc.bank:17011/QPCS/qpcs/creditApply/getKeywords.action?cmd="+URLEncoder.encode("{creditQry_Id:'"+cs[0]+"',creditType:'0',nocache:'78795886'}", "utf-8"),paramMap, headerMap, "utf-8");


		} catch (Exception e) {
			e.printStackTrace();
		}

		return buf;
	}



	public StringBuffer tozxbb3(ViewBrowers vb,String id){
		String[] cs =id.split(",");
		StringBuffer buf=new StringBuffer();
		HashMap<String,String> paramMap = new HashMap<String,String>();
		/*paramMap.put("cmd","{docId:'"+cs[2]+"',typeId:'"+cs[1]+"',activeId:'"+cs[4]+"',nocache:'73262601'}");*/

		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("User-Agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET4.0E; .NET4.0C)");
		headerMap.put("Content-Type","application/x-www-form-urlencoded");
		headerMap.put("Accept","application/json, text/javascript, */*");
		headerMap.put("Accept-Encoding","gzip, deflate");
		headerMap.put("Cache-Control","no-cache");
		headerMap.put("Accept-Language","zh-CN");
		headerMap.put("Connection","keep-alive");
		headerMap.put("Host","edqpcsapp.hnrcc.bank:17011");
		headerMap.put("Origin","http://edqpcsapp.hnrcc.bank:17011");
		headerMap.put("Referer","http://edqpcsapp.hnrcc.bank:17011/QPCS/qpcs/creditApply/viewCreditApplyImage.jsp?customerQueryId="+cs[0]+"&fileGroupTypeId="+cs[1]+"&mainId="+cs[2]+"&creditType=0&returnUrl=qryPersonCreditApply&activeId="+cs[4]+"&realMainId="+cs[2]+"&isCreditQuery=true");

		try {
			buf = vb.sendForm("http://edqpcsapp.hnrcc.bank:17011/QPCS/qpcs/creditApply/getDocListByParentCode.action?cmd="+URLEncoder.encode("{docId:'"+cs[2]+"',typeId:'"+cs[1]+"',activeId:'"+cs[4]+"',nocache:'73262601'}", "utf-8"),paramMap, headerMap, "utf-8");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return buf;
	}



	public StringBuffer tozxbb6(ViewBrowers vb,String id,String typeCode){
		StringBuffer buf=new StringBuffer();
		String[] cs =id.split(",");
		HashMap<String,String> paramMap = new HashMap<String,String>();
		/*paramMap.put("cmd","{docId:'"+cs[2]+"',typeCode:'"+typeCode+"',types:'',activeId:'"+cs[4]+"',nocache:'89313252'}");*/

		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("User-Agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET4.0E; .NET4.0C)");
		headerMap.put("Content-Type","application/x-www-form-urlencoded");
		headerMap.put("Accept","application/json, text/javascript, */*");
		headerMap.put("Accept-Encoding","gzip, deflate");
		headerMap.put("Cache-Control","no-cache");
		headerMap.put("Accept-Language","zh-CN");
		headerMap.put("Connection","keep-alive");
		headerMap.put("Host","edqpcsapp.hnrcc.bank:17011");
		headerMap.put("Origin","http://edqpcsapp.hnrcc.bank:17011");
		headerMap.put("Referer","http://edqpcsapp.hnrcc.bank:17011/QPCS/qpcs/creditApply/viewCreditApplyImage.jsp?customerQueryId="+cs[0]+"&fileGroupTypeId="+cs[1]+"&mainId="+cs[2]+"&creditType=0&returnUrl=qryPersonCreditApply&activeId="+cs[4]+"&realMainId="+cs[2]+"&isCreditQuery=true");

		try {
			buf = vb.sendForm("http://edqpcsapp.hnrcc.bank:17011/QPCS/qpcs/creditApply/getDocListByParentCode.action?cmd="+URLEncoder.encode("{docId:'"+cs[2]+"',typeCode:'"+typeCode+"',types:'',activeId:'"+cs[4]+"',nocache:'89313252'}", "utf-8"),paramMap, headerMap, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return buf;
	}
	/**
	 * @return
	 */
	@GetMapping(value = "/getzxbg")
	public Result<?> getzxbb(){
		ViewBrowers vb = new  ViewBrowers(null);
		List<Map<String, String>> zxbghtml =new ArrayList<>();

		try {
			//1.访问登陆页面
			StringBuffer loginBuf = getlogin(vb);
			//2.进行登陆
			StringBuffer loginbuf = tologin(vb);
			log.info("=========loginbuf:" + loginbuf);
			log.info("=========cookie:" + vb.getCookieStr());

			//3.访问征信列表页面
			Long nodate=System.currentTimeMillis();
			StringBuffer zxbblb = getzzbglb(vb,nodate);
			log.info("=========访问征信列表页面:" + zxbblb);
			//4.根据查询条件查询当日的征信报告 (需要扩展分页请求)
			StringBuffer zxbblbBycx = getzzbglbBycx(vb,nodate);//分页的话再这套一层循环
			log.info("=========根据查询条件查询当日的征信报告:" + zxbblbBycx);
			List<Map<String, String>> zxbginfo =new ArrayList<>();
			getZxbghtml(zxbblbBycx.toString(),zxbginfo);
			for (Map<String,String> map:zxbginfo){
				for (String key : map.keySet()) {
					System.out.println("===================key= "+ key + " and ====================value= " + map.get(key));
				}
			}


			for(Map<String, String> map:zxbginfo){
				String cs = map.get("cs");
				if(StringUtils.isNotEmpty(cs)){
					//接口1
					StringBuffer info1 = tozxbb1(vb, cs.split(",")[0]);
					System.out.println("接口1返回信息===================="+info1);
					//接口2referer
					StringBuffer info2refere = tozxbb2Referer(vb, cs);
					System.out.println("接口2前置refre===================="+info2refere);
					//接口2
					StringBuffer info2 = tozxbb2(vb, cs);
					System.out.println("接口2返回信息===================="+info2);
					//接口3
					StringBuffer info3 = tozxbb3(vb, cs);
					System.out.println("接口3返回信息===================="+info3);
					JSONObject jsonObject = JSON.parseObject(info3.toString());
					JSONArray subList = (JSONArray) jsonObject.get("subList");
					JSONObject  jsonObjectTypeCode = (JSONObject) subList.get(1);
					String typeCode=jsonObjectTypeCode.getString("TYPE_CODE");

					StringBuffer info6 = tozxbb6(vb, cs, typeCode);
					JSONObject jsonObject6 = JSON.parseObject(info6.toString());
					JSONArray attachList = (JSONArray) jsonObject6.get("attachList");
					JSONObject  jsonObjectHttpUrl = (JSONObject) attachList.get(0);
					String httpUrl=jsonObjectHttpUrl.getString("httpUrl");
					System.out.println("接口6返回信息===================="+info6);
					System.out.println("最终征信html地址===================="+httpUrl);
					StringBuffer stringBuffer = vb.sendForm(httpUrl, new HashMap(), "utf-8");
					System.out.println("征信报告html===================="+stringBuffer);
					log.info("征信报告html===================={}",stringBuffer);
					//把征信报告写入系统
					String path = uploadpath+"/zxbg/"+System.currentTimeMillis()+".html";
					File touch = FileUtil.touch(path);
					File file1 = FileUtil.appendString(stringBuffer.toString(), touch, "UTF-8");

					Map<String, String> zxbbmap =new HashMap<>();
					zxbbmap.put("sfzh",map.get("sfzh"));
					zxbbmap.put("xm",map.get("xm"));
					zxbbmap.put("rq",map.get("rq"));
					zxbbmap.put("html",stringBuffer.toString());
					zxbghtml.add(zxbbmap);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Result.ok("操作成功！");
	}

	/**
	 * @return
	 */
	@PostMapping(value = "/getLogincs")
	public Result<?> getLogincs(@RequestBody JSONObject  json){
		String secretKey = "B70E4921CD3AF568";
		try {
			log.info("=======================================json"+json.toJSONString());
			String yzmCode = CryptoUtils.decryptAES(secretKey, json.getString("text"));
			JSONObject jsonObject =JSON.parseObject(yzmCode);
			String uuid=jsonObject.getString("uuid");

			JSONObject jsonObject1=new JSONObject(new LinkedHashMap());
			jsonObject1.put("username","3650065");
			jsonObject1.put("password","MTIzNDU2Nzg=");
			jsonObject1.put("code",json.getString("code"));
			jsonObject1.put("uuid", uuid);
			jsonObject1.put("isAndroid","0");
			jsonObject1.put("imei","");
			log.info("=======================================登陆请求参数"+jsonObject1.toJSONString());
			String postString = CryptoUtils.encryptAES("B70E4921CD3AF568", jsonObject1.toJSONString());
			return Result.ok(postString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.error("解密失败");
	}


	/**
	 * @return
	 */
	@GetMapping(value = "/toYdyxpt")
	public Result<?> toYdyxpt(@RequestParam(name="code", defaultValue="1") String code){
		ViewBrowers vb = new  ViewBrowers(null);
		//StringBuffer buffer1 = goYdyxptLogin(vb);
		String token;
		JSONObject parse = null;
		try {
			long begin=System.currentTimeMillis();
			while (true){
				//获取验证码
				StringBuffer yzmbuff = toCaptchaImage(vb);
				String yzmCode = CryptoUtils.decryptAES("B70E4921CD3AF568", yzmbuff.toString());
				JSONObject jsonObject =JSON.parseObject(yzmCode);
				String uuid=jsonObject.getString("uuid");
				log.info("=======================================uuid"+uuid);
				StringBuffer  buffer = toYdyxptLogin(vb,String.valueOf(2),uuid);
				String jiemizfc= CryptoUtils.decryptAES("B70E4921CD3AF568", buffer.toString());
				log.info("=======================================登陆返回解密参数"+jiemizfc);
				parse =JSONObject.parseObject(jiemizfc);
				if(parse!=null){
					token=parse.get("token")==null?"":parse.get("token").toString();
					if(!token.isEmpty()){
						log.info("=================登陆成功==================");
						log.info("=================登陆返回的解密结果"+parse.toJSONString());
						StringBuffer stringBuffer = GoBusinessApplyInfo(vb, token);
						log.info("=================GoBusinessApplyInfo返回加密信息"+stringBuffer.toString());
						String businessApplyInfo = CryptoUtils.decryptAES("B70E4921CD3AF568", stringBuffer.toString());
						log.info("=================GoBusinessApplyInfo返回解密信息"+businessApplyInfo);
						if(StringUtils.isNotEmpty(businessApplyInfo)){
							JSONObject businessApplyInfoJson = JSON.parseObject(businessApplyInfo);
							JSONArray rows = businessApplyInfoJson.getJSONArray("rows");
							for (Object a : rows){
								JSONObject row= (JSONObject) a;
								String applyId=row.getString("applyId");
								String offlineId=row.getString("offlineId");
								StringBuffer stringBuffer1 = GetBusinessMenu(vb, token, applyId);
								log.info("=================GetBusinessMenu返回加密信息"+stringBuffer1.toString());
								String businessMenu = CryptoUtils.decryptAES("B70E4921CD3AF568", stringBuffer1.toString());
								log.info("=================GetBusinessMenu返回解密信息"+businessMenu);

								if(StringUtils.isNotEmpty(businessMenu)){
									JSONObject businessMenuJson = JSON.parseObject(businessMenu);
									JSONArray datas = businessMenuJson.getJSONArray("data");
									for (Object b : datas){
										JSONObject data= (JSONObject) b;
										String docName=data.getString("docName");
										if(StringUtils.isNotEmpty(docName)&&docName.indexOf("信贷单元")>-1){
											String docPath = data.getString("docPath");
											if(StringUtils.isNotEmpty(docPath)){
												String docPathAll="http://ifmcms.hnrcc.bank/XDXT/profile"+docPath;
												log.info("=================PDFpath=================="+docPathAll);
												FileUtils.fileUrl(docPathAll,docName,"D:\\ydyxpt","pdf");
											}

										}
									}
								}
							}
						}
						break;
					}
				}
				long end=System.currentTimeMillis();
				if(end-begin>5*60*1000){
					log.info("=================超过五分钟退出==================");
					break;
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Result.ok(parse.toJSONString());
	}

	public StringBuffer toCaptchaImage(ViewBrowers vb){
		StringBuffer buf=new StringBuffer();
		try {
			HashMap<String,String> paramMap = new HashMap<String,String>();
			HashMap<String,String> headerMap = new HashMap<String,String>();
			headerMap.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36");
			headerMap.put("Accept","application/json, text/plain, */*");
			headerMap.put("Accept-Encoding","gzip, deflate");
			headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
			headerMap.put("Connection","keep-alive");
			headerMap.put("Referer","http://ifmcms.hnrcc.bank/");

			buf = vb.sendForm("http://ifmcms.hnrcc.bank/XDXT/captchaImage",paramMap, headerMap, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buf;
	}

	public StringBuffer goYdyxptLogin(ViewBrowers vb){
		StringBuffer buf=new StringBuffer();
		try {
			HashMap<String,String> paramMap = new HashMap<String,String>();

			HashMap<String,String> headerMap = new HashMap<String,String>();
			headerMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			headerMap.put("Accept-Encoding","gzip, deflate");
			headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
			headerMap.put("Connection","keep-alive");
			headerMap.put("Host","ifmcms.hnrcc.bank");
			headerMap.put("Upgrade-Insecure-Requests","1");
			headerMap.put("Referer","http://ifmcms.hnrcc.bank/");
			headerMap.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36");

			buf = vb.sendProxyForm("http://ifmcms.hnrcc.bank",paramMap, headerMap, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}


		return buf;
	}


	public StringBuffer toYdyxptLogin(ViewBrowers vb,String code,String uuid){
		StringBuffer buf =new StringBuffer();
		okhttp3.Response response = null;
		try {
			/*HashMap<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("username","3650065");
			paramMap.put("password","MTIzNDU2Nzg=");
			paramMap.put("code",code);
			paramMap.put("uuid", uuid);
			paramMap.put("isAndroid","0");
			paramMap.put("imei","");*/
			JSONObject jsonObject=new JSONObject(new LinkedHashMap());
			jsonObject.put("username","3650065");
			jsonObject.put("password","MTIzNDU2Nzg=");
			jsonObject.put("code",code);
			jsonObject.put("uuid", uuid);
			jsonObject.put("isAndroid","0");
			jsonObject.put("imei","");
			log.info("=======================================登陆请求参数"+jsonObject.toJSONString());
			String postString = CryptoUtils.encryptAES("B70E4921CD3AF568", jsonObject.toJSONString());
			log.info("=======================================登陆请求加密参数"+postString);

			OkHttpClient client = new OkHttpClient().newBuilder()
					.build();
			MediaType mediaType = MediaType.parse("application/json");
			okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, postString);
			okhttp3.Request request = new okhttp3.Request.Builder()
					.url("http://10.8.9.61/XDXT/login")
					.method("POST", body)
					.addHeader("Content-Type", "application/json")
					.build();
			response = client.newCall(request).execute();
			String responseString = response.body().string();
			log.info("=======================================登陆返回加密参数"+responseString);
			buf=new StringBuffer(responseString);
			//HashMap<String,String> headerMap = new HashMap<String,String>();
			//headerMap.put("Accept","*/*");
			//headerMap.put("Accept-Encoding","gzip, deflate, br");
			//headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
			//headerMap.put("Connection","keep-alive");
			//headerMap.put("Content-Length", String.valueOf(postString.length()));
			//headerMap.put("Content-Type","application/json");
			//headerMap.put("Host","ifmcms.hnrcc.bank");
			//headerMap.put("Origin","http://ifmcms.hnrcc.bank");
			//headerMap.put("Referer","http://ifmcms.hnrcc.bank/");
			//headerMap.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36");
			//buf = vb.sendProxyForm("http://ifmcms.hnrcc.bank/XDXT/login/",postString, headerMap, "utf-8");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			e.printStackTrace();
		}finally {
			response.close();
		}
		return buf;
	}

	public StringBuffer GoBusinessApplyInfo(ViewBrowers vb,String token){
		StringBuffer buf=new StringBuffer();
		try {
			HashMap<String,String> paramMap = new HashMap<String,String>();

			HashMap<String,String> headerMap = new HashMap<String,String>();
			headerMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			headerMap.put("Accept-Encoding","gzip, deflate");
			headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
			headerMap.put("Authorization","Bearer"+" "+token);
			headerMap.put("Cookie","Admin-Token="+token);
			headerMap.put("Connection","keep-alive");
			headerMap.put("Host","ifmcms.hnrcc.bank");
			headerMap.put("Referer","http://ifmcms.hnrcc.bank/");
			headerMap.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36");
			String  post="{\"pageNum\":1,\"pageSize\":10,\"custSfz\":null,\"custName\":null,\"qryStatus\":\"1\",\"businessType\":\"\",\"validDays\":7,\"proId\":\"\"}";
			String postString = CryptoUtils.encryptAES("B70E4921CD3AF568", post);
			vb.setCookieStr("Admin-Token="+token);
			buf = vb.sendProxyForm("http://ifmcms.hnrcc.bank/XDXT/crm/businessApplyInfo/list?0="+URLEncoder.encode(postString),paramMap, headerMap, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buf;
	}


	public StringBuffer GetBusinessMenu(ViewBrowers vb,String token,String applyId){
		StringBuffer buf=new StringBuffer();
		try {
			HashMap<String,String> paramMap = new HashMap<String,String>();

			HashMap<String,String> headerMap = new HashMap<String,String>();
			headerMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			headerMap.put("Accept-Encoding","gzip, deflate");
			headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
			headerMap.put("Authorization","Bearer"+" "+token);
			headerMap.put("Cookie","Admin-Token="+token);
			headerMap.put("Connection","keep-alive");
			headerMap.put("Host","ifmcms.hnrcc.bank");
			headerMap.put("Referer","http://ifmcms.hnrcc.bank/");
			headerMap.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36");
			String  post="{\"applyId\":"+Integer.valueOf(applyId)+"}";
			String postString = CryptoUtils.encryptAES("B70E4921CD3AF568", post);
			vb.setCookieStr("Admin-Token="+token);
			buf = vb.sendProxyForm("http://ifmcms.hnrcc.bank/XDXT/crm/menu/getBusinessMenu?0="+URLEncoder.encode(postString),paramMap, headerMap, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buf;
	}

	public static void main(String[] args) {
		LoginController loginController=new LoginController();
		String html="<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n" +
				"<html>\n" +
				" <head>\n" +
				"  <title>征信用户页面</title>\n" +
				"  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
				"  <meta http-equiv=\"pragma\" content=\"no-cache\" />\n" +
				"  <meta http-equiv=\"Cache-Control\" content=\"no-store, must-revalidate\" />\n" +
				"  <meta http-equiv=\"expires\" content=\"0\" />\n" +
				"  <script type=\"text/javascript\"\n" +
				"   src=\"/QPCS/resources/js/jquery-1.4.2.min.js\">\n" +
				"</script>\n" +
				"  <script type=\"text/javascript\" src=\"/QPCS/resources/js/json.js\">\n" +
				"</script>\n" +
				"  <script language=\"javascript\"\n" +
				"   src=\"/QPCS/resources/tree/js/dhtmlXCommon.js\">\n" +
				"</script>\n" +
				"  <script language=\"javascript\"\n" +
				"   src=\"/QPCS/resources/tree/js/dhtmlXTree.js\">\n" +
				"</script>\n" +
				"  <script language=\"javascript\"\n" +
				"   src=\"/QPCS/resources/tree/js/dhtmlXTreeExtend.js\">\n" +
				"</script>\n" +
				"  <script language=\"javascript\"\n" +
				"   src=\"/QPCS/resources/plug/jwindow/jquery.jWindow.js\">\n" +
				"</script>\n" +
				"  <script language=\"javascript\"\n" +
				"   src=\"/QPCS/resources/plug/jwindow/jquery.pngFix.pack.js\">\n" +
				"</script>\n" +
				"  <link type=\"text/css\" rel=\"stylesheet\"\n" +
				"   href=\"/QPCS/resources/plug/jwindow/jwindow.css\">\n" +
				"<script type=\"text/javascript\" src=\"/QPCS/resources/My97DatePicker/WdatePicker.js\"></script>\n" +
				"  \n" +
				"  <link type=\"text/css\" rel=\"stylesheet\"\n" +
				"   href=\"/QPCS/resources/css/alliance.css\" />\n" +
				"  <link type=\"text/css\" rel=\"stylesheet\"\n" +
				"   href=\"/QPCS/resources/plug/jselect/jselect.css\" />\n" +
				"<script type=\"text/javascript\" src=\"/QPCS/resources/plug/keyword-zjg-query.js\"></script>\n" +
				"<script type=\"text/javascript\" src=\"/QPCS/resources/ext2.1/adapter/ext/ext-base.js\"></script>\n" +
				"        <script type=\"text/javascript\" src=\"/QPCS/resources/ext2.1/ext-all.js\"></script>\n" +
				"        <script type=\"text/javascript\" src=\"/QPCS/resources/ext2.1/locale/ext-lang-zh_CN.js\"></script>\n" +
				"  <link rel=\"stylesheet\" type=\"text/css\" href=\"/QPCS/resources/ext2.1/resources/css/ext-all.css\" />\n" +
				"  \n" +
				"\n" +
				"  <script type=\"text/javascript\">\n" +
				"\n" +
				"$(function() {\n" +
				" //选项卡\n" +
				" var url = \"/QPCS/resources/plug/qpcs/commonDicTree.jsp?hiddenId=workflowState&viewId=workflowStateDesc&code=creditApplyStatus\";\n" +
				" $(\"#workflowStateDesc\").showJWindow(\"选择信用报告查询申请状态\", url,\n" +
				"   \"keyword-input-select\");\n" +
				" \n" +
				" $(\"#uploadExcel\").showJWindow(\"批量导入申请\", \"toUpload.action\");\n" +
				"});\n" +
				"\n" +
				"function closeComonDicJWindow(returnValue) {\n" +
				" $('#fancy_wrap').hide();\n" +
				" $.each(returnValue, function(i, n) {\n" +
				"  $('#' + n.hiddenId).val(n.code);\n" +
				"  $('#' + n.viewId).val(n.name);\n" +
				" });\n" +
				"}\n" +
				"\n" +
				"function goSubmit() {\n" +
				"    document.forms[0].action=\"qryPersonCreditApply.action\";\n" +
				" document.forms[0].submit();\n" +
				"}\n" +
				"\n" +
				"function isDelete(id) {\n" +
				" if (confirm(\"您确定要删除该记录吗？\")) {\n" +
				"  location.href = \"deletePersonCreditApply.action?customerQuery.id=\" + id\n" +
				"    + \"&customerQuery.creditType=\" + $(\"#creditType\").val();\n" +
				" }\n" +
				"}\n" +
				"\n" +
				"function loactionAdd() {\n" +
				" var channelAndDesc ='';\n" +
				" if(channelAndDesc){\n" +
				"  var channelJson = JSON.parse(channelAndDesc);\n" +
				"  var channel = channelJson.channel;\n" +
				"  var desc = channelJson.desc;\n" +
				"  if(channel != '000000'){\n" +
				"   alert(\"您已绑定渠道【\"+desc+\"】，禁止在本地新建申请！\");\n" +
				"   return;\n" +
				"  }\n" +
				" }\n" +
				" location.href = \"addPersonCreditApply.action\";\n" +
				"}\n" +
				"\n" +
				"function view(id) {\n" +
				" location.href = \"viewPersonCreditApply.action?customerQuery.id=\" + id;\n" +
				"}\n" +
				"\n" +
				"function edit(id) {\n" +
				" location.href = \"editPersonCreditApply.action?customerQuery.id=\" + id;\n" +
				"}\n" +
				"\n" +
				"function doReset() {\n" +
				" var customName = document.getElementById(\"customName\");\n" +
				" customName.value = \"\";\n" +
				" var workflowStateDesc = document.getElementById(\"workflowStateDesc\");\n" +
				" workflowStateDesc.value = \"\";\n" +
				" var workflowState = document.getElementById(\"workflowState\");\n" +
				" workflowState.value = \"\";\n" +
				"}\n" +
				"\n" +
				"function viewCreditApplyImage(id,fileGroupTypeId,mainId,creditType,\n" +
				"  activetaskId,realId){\n" +
				"     var myMask = new Ext.LoadMask(Ext.getBody(), {msg: '数据加载中，请稍后...'});\n" +
				"        myMask.show();\n" +
				"        document.myForm.action=\"getValidCreditReportInfo.action\";\n" +
				"     document.getElementById(\"customerQuery.id\").value = realId;//原申请id\n" +
				"        Ext.Ajax.request({\n" +
				"                 form: 'myForm',\n" +
				"                 method: 'post',\n" +
				"                 success: function(response, option){\n" +
				"                  myMask.hide();\n" +
				"                   var results = Ext.util.JSON.decode(response.responseText);\n" +
				"          if(results.succ == 'true'){\n" +
				"         location.href = \"/QPCS/qpcs/creditApply/viewCreditApplyImage.jsp?customerQueryId=\"+id\n" +
				"                      +\"&fileGroupTypeId=\"+fileGroupTypeId\n" +
				"                      +\"&mainId=\"+mainId\n" +
				"                      +\"&creditType=\"+creditType\n" +
				"                      +\"&returnUrl=qryPersonCreditApply\"\n" +
				"                      +\"&activeId=\" + results.activeId\n" +
				"                      +\"&realMainId=\" + results.mainId\n" +
				"        +\"&isCreditQuery=\" + results.isCreditQuery;\n" +
				"\n" +
				"        }else{\n" +
				"            Ext.MessageBox.alert(\"信息提示\",results.msg); \n" +
				"        }\n" +
				"     },\n" +
				"     failure: function(response,option){\n" +
				"        myMask.hide();\n" +
				"        Ext.MessageBox.alert(\"信息提示\",\"【数据加载超时，请稍候再试...】\");\n" +
				"     }\n" +
				"       }); \n" +
				"  }\n" +
				"\n" +
				"function toUpload() {\n" +
				" location.href = \"toUpload.action\";\n" +
				"}\n" +
				"//批量提交\n" +
				"function submitDataForAll(){\n" +
				" var ids = \"\";\n" +
				" var msg = \"\";\n" +
				" $(\".ckx:checked\").each(function(){\n" +
				"    if(ids.length>0){\n" +
				"     ids =ids+\";\"+$(this).val();\n" +
				"    }else{\n" +
				"    ids =  $(this).val();\n" +
				"    }\n" +
				"    //验证信息是否 合法\n" +
				"    if(!$(this).attr(\"certTypeDesc\")){\n" +
				"     msg +=\"提交的数据中含有非法证件类型！\";\n" +
				"    return false;\n" +
				"     }\n" +
				"     if(!$(this).attr(\"queryReasonDesc\")){\n" +
				"      msg +=\"提交的数据中含有非法查询原因！\";\n" +
				"    return false;\n" +
				"   }\n" +
				"   if(!$(this).attr(\"queryTypeDesc\")){\n" +
				"     msg +=\"提交的数据中含有非法查询类型！\";\n" +
				"     return false;\n" +
				"   }\n" +
				" });\n" +
				" if(msg.length>0){\n" +
				"  alert(msg);\n" +
				"  return false;\n" +
				" }\n" +
				" //alert(ids);\n" +
				" var tmp = new Date();\n" +
				" if(ids.length>0){\n" +
				"  $.getJSON(\"submitCreditApplyForAll.action?tmp=\"+tmp,{id:ids},function(json){\n" +
				"   if(json.rst == 1){\n" +
				"    alert(\"提交成功！\");\n" +
				"    goSubmit();\n" +
				"   }else{\n" +
				"    alert(json.msg);\n" +
				"    goSubmit();\n" +
				"   }\n" +
				"  });\n" +
				" }else{\n" +
				"  alert(\"请选择要提交的数据！\");\n" +
				" }\n" +
				"}\n" +
				"//收回 任务\n" +
				"function redoWorkitem(queryId,caseId){\n" +
				" var tmp = new Date();\n" +
				" $.getJSON(\"redoWorkFlow.action?tmp=\"+tmp,{json:\"{id:\"+queryId+\",caseId:\"+caseId+\"}\"},function(json){\n" +
				"  if(json.rst == 1){\n" +
				"   alert(\"收回成功！\");\n" +
				"   goSubmit();\n" +
				"  }else{\n" +
				"   alert(json.msg);\n" +
				"   goSubmit();\n" +
				"  }\n" +
				" });\n" +
				"}\n" +
				"function viewAuditMind(aduitMain){//查看那审核意见\n" +
				"  alert(aduitMain);\n" +
				"}\n" +
				"//建立档案\n" +
				"function newScanAction(id){ \n" +
				" var dWidth = document.documentElement.clientWidth?document.documentElement.clientWidth:$(document).width();\n" +
				" var dHeight = document.documentElement.clientHeight?document.documentElement.clientHeight:$(document).height();\n" +
				" //跳转到扫描，信息录入页面 个人征信扫描队列ID  bcf0e280be5146568f6e0843e51a6de0\n" +
				" window.location.href=\"newDocActiveTaskApply.action?taskId=bcf0e280be5146568f6e0843e51a6de0&dWidth=\"+dWidth+\"&dHeight=\"+dHeight+\"&id=\"+id;\n" +
				"}\n" +
				"\n" +
				"</script>\n" +
				" </head>\n" +
				" <body>\n" +
				"\n" +
				"\n" +
				"     \n" +
				"<form id=\"myForm\" name=\"myForm\" onsubmit=\"customOnsubmit_myForm(); return true;\" action=\"qryPersonCreditApply.action\" method=\"post\">\n" +
				"\n" +
				"   <input type=\"hidden\" id=\"creditType\" name=\"customerQuery.creditType\"\n" +
				"    value=\"0\" />\n" +
				"   <input type=\"hidden\" id=\"workflowState\"\n" +
				"    name=\"customerQuery.workflowState\"\n" +
				"    value=\"\" />\n" +
				"   <input type=\"hidden\" id=\"customerQuery.id\"  name=\"customerQuery.id\"/>\n" +
				"   <div id=location>\n" +
				"    您的位置：查询申请>>个人信用报告查询\n" +
				"   </div>\n" +
				"   <div id=main>\n" +
				"    <!--操作条区域-->\n" +
				"    <div class=\"grid-button-panel\">\n" +
				"     <table>\n" +
				"      <tr width='100%'>\n" +
				"       <td width='20%'>\n" +
				"        客户姓名：\n" +
				"        <input type=\"text\" name=\"customerQuery.customName\"\n" +
				"         id=\"customName\" value=\"\"\n" +
				"         STYLE=\"width: 165px;\" />\n" +
				"       </td>\n" +
				"       <td width='20%'>\n" +
				"        &nbsp;&nbsp;&nbsp;状态：\n" +
				"        <input type=\"text\" name=\"customerQuery.workflowStateDesc\"\n" +
				"         id=\"workflowStateDesc\"\n" +
				"         value=\"\"\n" +
				"         STYLE=\"width: 165px;\" />\n" +
				"       </td>\n" +
				"       <td width=\"18%\" >开始时间：\n" +
				"        <input id='starttime' name='customerQuery.queryStartDate' class=\"Wdate\" onfocus=\"WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})\" value=''/>\n" +
				"          </td>\n" +
				"          <td width=\"18%\">&nbsp;结束时间：\n" +
				"        <input id='endtime' name='customerQuery.queryEndDate' class=\"Wdate\" onfocus=\"WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})\" value=''/>\n" +
				"          </td>\n" +
				"       <td width='25%' align=\"left\">\n" +
				"        <button class=\"s-button\" onclick=\"goSubmit();\">\n" +
				"         <span class=\"s-icon-search\">&nbsp;</span>查询\n" +
				"        </button>\n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"        <button class=\"s-button\" onclick=\"doReset();\">\n" +
				"         <span class=\"s-icon-reset\">&nbsp;</span>重置\n" +
				"        </button>\n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"        <button class=\"s-button\" onclick=\"document.location.reload();\">\n" +
				"         刷新页面\n" +
				"        </button>\n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"        <!--  <button class=\"s-button\" id=\"uploadExcel\" >\n" +
				"         批量上传\n" +
				"        </button>\n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"        <button class=\"s-button\" id=\"submitAll\" onclick=\"submitDataForAll();\" >\n" +
				"         批量提交\n" +
				"        </button>-->\n" +
				"       </td>\n" +
				"      </tr>\n" +
				"     </table>\n" +
				"    </div>\n" +
				"  </form>\n" +
				"\n" +
				"\n" +
				"<script type=\"text/javascript\">\n" +
				" function customOnsubmit_myForm() {\n" +
				" \n" +
				" }\n" +
				"</script>\n" +
				"\n" +
				"\n" +
				"\n" +
				"  <!--信息提示区域-->\n" +
				"  \n" +
				"  <button class=\"s-button\" onclick=\"loactionAdd();\">\n" +
				"   <span class=\"s-icon-add\">&nbsp;</span>添加\n" +
				"  </button>\n" +
				"\n" +
				"  <!--信息列表区域-->\n" +
				"  <table class=\"c-info-list\">\n" +
				"   <thead style=\"height: 20px;\">\n" +
				"    <tr>\n" +
				"     <th width=\"4%\">\n" +
				"      序号\n" +
				"     </th>\n" +
				"     <th width=\"8%\">\n" +
				"      客户姓名\n" +
				"     </th>\n" +
				"     <th width=\"8%\">\n" +
				"      证件类型\n" +
				"     </th>\n" +
				"     <th width=\"12%\">\n" +
				"      证件号码\n" +
				"     </th>\n" +
				"     <th width=\"10%\">\n" +
				"      查询原因\n" +
				"     </th>\n" +
				"     <!-- <th width=\"8%\">\n" +
				"      查询类型\n" +
				"     </th> -->\n" +
				"     <th width=\"7%\">\n" +
				"      授权日期\n" +
				"     </th>\n" +
				"     <th width=\"12%\">\n" +
				"      查询申请时间\n" +
				"     </th>\n" +
				"     <th width=\"12%\">\n" +
				"      审批时间\n" +
				"     </th>\n" +
				"     <th width=\"6%\">\n" +
				"      渠道\n" +
				"     </th>\n" +
				"     <th width=\"6%\">\n" +
				"      报告状态\n" +
				"     </th>\n" +
				"     <th width=\"10%\">\n" +
				"      操作\n" +
				"     </th>\n" +
				"    </tr>\n" +
				"   </thead>\n" +
				"   <tbody>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       1\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       刘鑫\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       43070319941009685X\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       资信审查\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-19\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-19 09:12:21\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-19 09:15:30\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093948595e86801879711d56c2d71');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        <!-- 可以查看报告 -->\n" +
				"         <img src=\"/QPCS/resources/images/alliance/icon/yuanwen.gif\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:viewCreditApplyImage('8b8093948595e86801879711d56c2d71','8a8b800d3ee3bc9f013ee3c53b1a0002','8b8093948595e86801879710271f2cad','0','2f3ddf5107ed499e8c4f6ede1551b3a1','8b8093948595e86801879711d56c2d71');\"\n" +
				"         title=\"查看影像\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       2\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       陈建兰\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       432827197310261669\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       信用卡审批\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17 11:16:39\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17 11:17:43\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093948595e86801878d36ead22a64');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       3\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       王石生\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       432827197206077634\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       贷款审批\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17 09:38:42\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17 09:39:43\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093938595e6db01878cdd3d217fff');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       4\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       王尤成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       432827195707120013\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       担保资格审查\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17 09:38:16\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17 09:40:08\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093938595e6db01878cdcd8b17fd5');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       5\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       王尤仁\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       432827196001177610\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       担保资格审查\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17 09:37:47\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17 09:40:19\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093938595e6db01878cdc69547f93');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       6\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       郭湘洪\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       432827196303296818\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       担保资格审查\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17 09:37:27\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17 09:40:32\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093938595e6db01878cdc18ab7f61');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       7\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       曹华春\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       431025198705086815\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       担保资格审查\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-12\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-12 17:34:01\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-12 17:34:15\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093948595e868018774d09d462177');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       8\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       马强\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       152104199603101916\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       贷款审批\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-11\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-11 17:02:35\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-11 17:03:00\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093948595e86801876f8d794351d8');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       9\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       周继孝\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       431022198308106094\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       贷后管理\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-11\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-11 10:54:28\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-11 10:55:07\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093948595e86801876e3c73ae5927');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       10\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       邝跃兵\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       432827197909021231\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       贷款审批\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10 15:19:44\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10 15:20:06\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093938595e6db01876a08f3c774b4');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       11\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       刘小美\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       432827197603011225\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       客户准入资格审查\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10 14:55:29\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10 15:00:04\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093938595e6db018769f2c0ad634d');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       12\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       雷龙\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       431025199108176032\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       贷款审批\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10 11:11:58\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10 11:12:25\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093938595e6db018769261fc325f9');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       13\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       王莉媛\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       431025199509026027\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       贷款审批\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10 11:11:36\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10 11:13:23\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093938595e6db01876925c9ba25b8');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       14\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       曾萍萍\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       431025198408200220\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       贷后管理\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-06\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-06 14:44:08\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-06 14:44:48\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093948595e8680187554eee714917');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       15\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       李俊锋\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       431025198911070419\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       贷后管理\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-06\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-06 14:43:46\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-06 14:44:57\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093948595e8680187554e95f348eb');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"   </tbody>\n" +
				"  </table>\n" +
				"  <div style=\"text-align: right; width: 100%;\">\n" +
				"   <!--分页信息区域-->\n" +
				"   <script type=\"text/javascript\">\n" +
				"var curpage = +'1'.replace(',','');\n" +
				"var pagecount = 60;\n" +
				"//向指定页码翻页\n" +
				"function turnPage(pageNumber){\n" +
				" //document.forms[0].reset();\n" +
				" document.forms[0].action = document.forms[0].action + \"?paginationList.currentPage=\" + pageNumber;\n" +
				" document.forms[0].submit()\n" +
				"}\n" +
				"\n" +
				"function turnFirst(){\n" +
				" if(curpage>1) turnPage(1);\n" +
				"}\n" +
				"\n" +
				"function turnEnd(){\n" +
				" if(pagecount!=0 && pagecount!=curpage) turnPage(pagecount);\n" +
				"}\n" +
				"\n" +
				"function turnNext(){\n" +
				" if(curpage<pagecount) turnPage(curpage+1);\n" +
				"}\n" +
				"\n" +
				"function turnPre(){\n" +
				" if(curpage>1) turnPage(curpage-1);\n" +
				"}\n" +
				"\n" +
				"function jumpPage(obj){\n" +
				" if(event.keyCode==13){\n" +
				"  event.keyCode = 9;\n" +
				"  if(isDigital(obj.value)){\n" +
				"   var pagenum = obj.value;\n" +
				"   if(pagenum!=curpage){\n" +
				"    if(pagenum>pagecount) pagenum=pagecount;\n" +
				"    turnPage(pagenum);\n" +
				"   }\n" +
				"  }else{\n" +
				"   obj.value = curpage;\n" +
				"  }\n" +
				" }\n" +
				" obj.focus()\n" +
				"}\n" +
				"//判断是否为正整数\n" +
				"function isDigital(str){\n" +
				" var r = /^[0-9]*[1-9][0-9]*$/;\n" +
				" return r.test(str);\n" +
				"}\n" +
				"\n" +
				"</script>\n" +
				"<table class=\"page\">\n" +
				" <tr>\n" +
				"  <td style=\"text-align: left; font: verdana\">\n" +
				"   <strong>1-15</strong> 条 共<strong>897 </strong>条\n" +
				"  </td>\n" +
				"  <td style=\"text-align: right\">\n" +
				"   <img src=\"/QPCS/resources/images/alliance/common/back_b.gif\" class=\"image-link\" align=\"absmiddle\" onclick=\"turnFirst()\" />\n" +
				"   <img src=\"/QPCS/resources/images/alliance/common/back.gif\" class=\"image-link\" align=\"absmiddle\" onclick=\"turnPre()\" />\n" +
				"   <img src=\"/QPCS/resources/images/alliance/common/line.gif\" align=\"absmiddle\" />\n" +
				"   第 <label><input id=\"testinout\" type=\"text\" onfocus=\"this.select()\" size=\"2\" value=\"1\" onkeydown=\"jumpPage(this)\" /></label>\n" +
				"   页/共<strong>60 </strong>页\n" +
				"   <img src=\"/QPCS/resources/images/alliance/common/line.gif\" align=\"absmiddle\" />\n" +
				"   <img src=\"/QPCS/resources/images/alliance/common/front.gif\" class=\"image-link\" align=\"absmiddle\" onclick=\"turnNext()\" />\n" +
				"   <img src=\"/QPCS/resources/images/alliance/common/front_f.gif\" class=\"image-link\" align=\"absmiddle\" onclick=\"turnEnd()\" />\n" +
				"  </td>\n" +
				" </tr>\n" +
				"</table>\n" +
				"  </div>\n" +
				"  \n" +
				"\n" +
				" </body>\n" +
				"</html>";
		List<Map<String, String>> zxbginfo =new ArrayList<>();

		loginController.getZxbghtml(html,zxbginfo);

		for (Map<String,String> map:zxbginfo){
			for (String key : map.keySet()) {
				System.out.println("key= "+ key + " and value= " + map.get(key));
			}
		}


	}
}
