package org.cmms.modules.pad.qyxxgl.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;
import org.cmms.modules.hr.yggl.ygxxgl.service.IVhrbasstaffpostService;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.khgl.nh.service.IYwhywwlxxService;
import org.cmms.modules.khgl.sh.service.IShglYwhywwlxxService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.khxxgl.khflgl.qyxx.entity.*;
import org.cmms.modules.khxxgl.khflgl.qyxx.service.*;
import org.cmms.modules.khxxgl.khflgl.shxq.entity.Shxq;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzxxgl;
import org.cmms.modules.pad.nhxxgl.entity.NhJtcyxx;
import org.cmms.modules.pad.nhxxgl.service.IKhglNhhzxxglService;
import org.cmms.modules.pad.qyxxgl.entity.VKhglQyxxgl;
import org.cmms.modules.pad.qyxxgl.service.IKhglQyxxglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;


import org.cmms.modules.pad.shxxgl.entity.*;
import org.cmms.modules.pad.shxxgl.service.ICamsJbxxShzllbService;
import org.cmms.modules.pad.shxxgl.service.ICamsZcsxShfcxxService;
import org.cmms.modules.pad.shxxgl.service.ICamsZcsxShpjsxxxService;
import org.cmms.modules.pad.shxxgl.service.IShhfxxbService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysDictService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 企业信息管理_pad
 * @Author: jeecg-boot
 * @Date:   2022-11-04
 * @Version: V1.0
 */
@Slf4j
@Api(tags="企业信息管理_pad")
@RestController
@RequestMapping("/qyxxgl/khglQyxxgl")
public class KhglQyxxglController extends JeecgController<VKhglQyxxgl, IKhglQyxxglService> {
	@Autowired
	private IKhglQyxxglService khglQyxxglService;
	@Autowired
	private IHrBasOrganizationService hrBasOrganizationService;
	@Autowired
	private IQyxxglService qyxxglService;
	@Autowired
	private IVhrbasstaffpostService vhrbasstaffpostService;
	@Autowired
	private IShhfxxbService shhfxxbService;
	@Autowired
	private IKhglQyglrxxService khglQyglrxxService;
	 @Autowired
	 private INhxqService nhxqService;
	 @Autowired
	 private ISysDictService sysDictService;
	 @Autowired
	 private IKhglNhhzxxglService khglNhhzxxglService;
	 @Autowired
	 private IYwhywwlxxService ywhywwlxxService;
	 @Autowired
	 private ICamsZcsxQypjsxxxService camsZcsxQypjsxxxService;
	 @Autowired
	 private ICamsZcsxQyfcxxService camsZcsxQyfcxxService;
	 @Autowired
	 private ICamsJbxxQyzllbService camsJbxxQyzllbService;


	/**
	 * 分页列表查询
	 *
	 * @param khglQyxxgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "企业信息管理_pad-分页列表查询")
	@ApiOperation(value="企业信息管理_pad-分页列表查询", notes="企业信息管理_pad-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VKhglQyxxgl khglQyxxgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   String startDate, String endDate,
								   HttpServletRequest req) {
		QueryWrapper<VKhglQyxxgl> queryWrapper = QueryGenerator.initQueryWrapper(khglQyxxgl, req.getParameterMap());
		/*Page<VKhglQyxxgl> page = new Page<VKhglQyxxgl>(pageNo, pageSize);
		IPage<VKhglQyxxgl> pageList = khglQyxxglService.page(page, queryWrapper);
		return Result.ok(pageList);*/
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
			try {
				queryWrapper.between("zcsj", sdf.parse(startDate), sdf.parse(endDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		//由于有的用户分配的权限过多，使用in会报错，暂改成直接通过语句查询
		if (!sysUser.getUsername().equals("admin")) {
			//in  用 list有个数限制问题， 此处改为inSql
			String sqlSswg = "select  menu_id from YXDYGL_PQQXGL t where khjl =" + "'" + sysUser.getWorkNo() + "'";
			queryWrapper.and(i -> i.inSql("ssyxdy", sqlSswg).or().eq("zkhjl", sysUser.getWorkNo()));
		}

		Page<VKhglQyxxgl> page = new Page<VKhglQyxxgl>(pageNo, pageSize);
		IPage<VKhglQyxxgl> pageList = khglQyxxglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
	  * 通过id查询
	  *
	  * @param qyid
	  * @return
	  */
	 @AutoLog(value = "企业信息管理-通过id查询")
	 @ApiOperation(value = "企业信息管理-通过id查询", notes = "企业信息管理-通过id查询")
	 @GetMapping(value = "/queryQyxxById")
	 public Result<?> queryQyxxById(@RequestParam(name = "qyid", required = true) String qyid) {
		 try {
			 //查询企业基本信息
			 Qyxxgl khglShhmcxx = qyxxglService.getById(qyid);
			 JSONObject shhmcJsonObject = JSONObject.parseObject(JSONObject.toJSON(khglShhmcxx).toString());
			 shhmcJsonObject.put("qyid", qyid);
			 shhmcJsonObject.put("frzjhmflag", khglShhmcxx.getFrzjhm());
			 //查询企业采集信息
			 if (StringUtils.isNotEmpty(khglShhmcxx.getSszh())) {
				 HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByZzbz(khglShhmcxx.getSszh());
				 if (hrBasOrganization != null) {
					 shhmcJsonObject.put("sszh_dictText", hrBasOrganization.getZzjc());
				 }
			 }
			 //如果授信对象为空页面授信对象下拉则默认展示企业名称
			 if (khglShhmcxx != null) {
				 if (khglShhmcxx.getSxdxzjh() == null) {
					 shhmcJsonObject.put("sxdxzjhflag", khglShhmcxx.getTyshxydm());
				 } else {
					 shhmcJsonObject.put("sxdxzjhflag", khglShhmcxx.getSxdxzjh());
				 }
			 }
			 return Result.ok(shhmcJsonObject);
		 } catch (Exception e) {
			 e.printStackTrace();
			 log.error("查询企业信息错误！", e);
			 return Result.error("查询企业信息错误！");
		 }

	 }


	 /**
	  * 修改关联人信息
	  *
	  * @param khglShglrxxUpdate
	  * @return
	  */
	 @AutoLog(value = "1-修改关联人信息")
	 @ApiOperation(value = "1-修改关联人信息", notes = "1-修改关联人信息")
	 @RequestMapping(value = "/EditGlrxx", method = RequestMethod.POST)
	 public Result<?> EditGlrxx(@RequestBody KhglQyglrxx khglShglrxxUpdate) {
		 try {
			 QueryWrapper<KhglQyglrxx> khglShglrxxQueryWrapper = new QueryWrapper<>();
			 khglShglrxxQueryWrapper.eq("qy_id", khglShglrxxUpdate.getQyId());
			 List<KhglQyglrxx> khglShglrxxList = khglQyglrxxService.list(khglShglrxxQueryWrapper);
			 if (khglShglrxxList.size() == 0) {
				 return Result.error("未找到对应的关联人信息！");
			 } else {
				 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
				 khglShglrxxUpdate.setLrbz("1");
				 khglShglrxxUpdate.setXgr(sysUser.getUsername());
				 khglShglrxxUpdate.setXgsj(new Date());
				 khglQyglrxxService.updateById(khglShglrxxUpdate);
				 return Result.ok("修改成功");
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
			 log.error("修改关联人信息", e);
			 return Result.error("系统错误，修改关联人信息！");
		 }
	 }

	 /**
	  * 删除商户附件信息
	  * @param jsonObject
	  * @return
	  */
	 @RequestMapping(value = "/deleteShfjImage",method = RequestMethod.POST)
	 public Result<?> deleteShfjImage(@RequestBody  List<CamsJbxxQyzllb> jsonObject) {
		 try {
			 if (jsonObject!=null  && jsonObject.size()>0){
				 for (int i = 0; i < jsonObject.size(); i++) {
					 if (!StringUtils.isEmpty(jsonObject.get(i).getId())){
						 UpdateWrapper<CamsJbxxQyzllb> camsJbxxShzllbUpdateWrapper=new UpdateWrapper<>();
						 camsJbxxShzllbUpdateWrapper.eq("id",jsonObject.get(i).getId());
						 camsJbxxQyzllbService.remove(camsJbxxShzllbUpdateWrapper);
					 }
				 }
			 }

		 }catch (Exception e){
			 return  Result.error(e.toString());
		 }
		 return Result.ok("添加成功");
	 }

	 /**
	  * 修改企业信息
	  *
	  * @param jsonObject
	  * @return
	  */
	 @RequestMapping(value = "/EditShxx", method = RequestMethod.POST)
	 public Result<?> EditJtxx(@RequestBody JSONObject jsonObject) {
		 try {
			 Qyxxgl khglShhmcxx = new Qyxxgl();
			 khglShhmcxx = JSONObject.toJavaObject(jsonObject, Qyxxgl.class);
			 Qyxxgl check = qyxxglService.getById(khglShhmcxx.getId());
			 if (check == null) {
				 return Result.error("请求参数错误！");
			 }
			 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

			 //更新商户信息
			 qyxxglService.updateById(khglShhmcxx);

			 saveYxglShhfxxb(khglShhmcxx);

			 List<KhglQyglrxx> gxrxxList = JSONArray.parseArray(JSON.toJSONString(jsonObject.getJSONArray("gxrxxList")), KhglQyglrxx.class);
			 if (CollUtil.isNotEmpty(gxrxxList)) {
				 for (int i = 0; i < gxrxxList.size(); i++) {
					 KhglQyglrxx khglShglrxx = gxrxxList.get(i);
					 UpdateWrapper<KhglQyglrxx> updateWrapper = new UpdateWrapper<>();
					 updateWrapper.eq("id", khglShglrxx.getId());
					 khglQyglrxxService.update(khglShglrxx, updateWrapper);
				 }
			 }
			 //提取是否有效走访
			 //khglQyxxglService.init(khglShhmcxx.getId(), sysUser.getWorkNo(), sysUser.getUsername());

//	 		saveYxglKhhfxxb(page.getSsyxdy(), page.getHzxm(), page.getHzzjhm());
		 } catch (Exception e) {
			 log.error("修改企业信息失败", e);
			 return Result.error("系统错误，修改企业信息失败！");
		 }
		 return Result.ok("修改成功");
	 }
	 /**
	  * 保存回访信息
	  */
	 protected void saveYxglShhfxxb(Qyxxgl khglShhmcxx) {
		 Shhfxxb shhfxxb = new Shhfxxb();
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 Vhrbasstaffpost vhrbasstaffpost = vhrbasstaffpostService.selectByYggh(sysUser.getWorkNo());
		 if (vhrbasstaffpost != null) {
			 shhfxxb.setZzbz(vhrbasstaffpost.getZzbz());
			 shhfxxb.setYggh(vhrbasstaffpost.getYggh());
			 shhfxxb.setKhjlbh(vhrbasstaffpost.getKhjlbz());
		 }
		 shhfxxb.setId(UUID.randomUUID().toString().substring(0, 32));
		 shhfxxb.setShid(khglShhmcxx.getId());
		 shhfxxb.setYxdy(khglShhmcxx.getWgbh());
		 shhfxxb.setKhmc(khglShhmcxx.getQymc());
		 shhfxxb.setZjhm(khglShhmcxx.getTyshxydm());
		 shhfxxb.setHfrq(DateUtil.parseDateFormat(DateUtil.formatDateTime("yyyy-MM-dd"), "yyyy-MM-dd"));
		 shhfxxb.setSjly("2");
		 shhfxxb.setLrr(sysUser.getUsername());
		 shhfxxbService.save(shhfxxb);
	 }

	 /**
	  *移除关系人
	  * @param id
	  * @return
	  */
	 @RequestMapping(value = "DeleterGlrxxId", method = RequestMethod.GET)
	 public Result<?> DeleterGlrxxId(@RequestParam(name = "id", required = true) String id) {
		 try {
			 khglQyglrxxService.removeById(id);
		 } catch (Exception e) {
			 e.printStackTrace();
			 log.error("删除失败", e);
			 return Result.error("系统错误，删除失败！");
		 }
		 return Result.ok("删除成功");
	 }

	 /*
         通过证件号码去花名册查询关联人信息
        */
	 @GetMapping(value = "/queryglrxxZjhm")
	 public Result<?> queryglrxxZjhm(@Param("zjhm") String zjhm) {
		 try {
			 if (zjhm != null) {
				 zjhm = Base64.decodeStr(zjhm);
				 QueryWrapper<Nhxq> khhmcQueryWrapper = new QueryWrapper<>();
				 khhmcQueryWrapper.eq("zjhm", zjhm);
				 List<Nhxq> list = nhxqService.list(khhmcQueryWrapper);
				 if (list != null && list.size() > 0) {
					 Nhxq khhmcxx = list.get(0);
					 JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSON(khhmcxx).toString());
					 return Result.ok(jsonObject);
				 }
			 } else {
				 return Result.ok(false);
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
			 log.error("查询关联人信息错误！", e);
			 return Result.error("查询关联人信息错误！");
		 }
		 return Result.ok(false);
	 }

	 /**
	  * 通过法人证件号码查询查询法人家庭成员
	  */
	 @RequestMapping(value = "/queryJtcyxxByzjhm", method = RequestMethod.GET)
	 public Result<?> queryJtcyxxByzjhm(@RequestParam(name = "zjhm", required = true) String zjhm, @RequestParam(name = "id", required = true) String id) {

		 JSONArray jsonArray = new JSONArray();
		 JSONObject jsonObject = new JSONObject();

		 //通过id查询法人证件号码
		 QueryWrapper<Qyxxgl> shhmcxxQueryWrapper = new QueryWrapper<>();
		 shhmcxxQueryWrapper.eq("id", id);
		 Qyxxgl khglShhmcxx = qyxxglService.getOne(shhmcxxQueryWrapper);

		 JSONObject json = new JSONObject();
		 json.put("id", khglShhmcxx.getId());
		 json.put("zjhm", khglShhmcxx.getTyshxydm());
		 json.put("khmc", khglShhmcxx.getQymc());
		 jsonArray.add(json);

		 if (!StringUtils.isEmpty(zjhm)) {
			 zjhm = Base64.decodeStr(zjhm);
		 }
		 QueryWrapper<KhglQyglrxx> khglShglrxxQueryWrapper = new QueryWrapper<>();
		 khglShglrxxQueryWrapper.eq("qy_id", id);
		 List<KhglQyglrxx> khglShglrxxList = khglQyglrxxService.list(khglShglrxxQueryWrapper);
		 for (KhglQyglrxx khglShglrxx : khglShglrxxList) {
			 JSONObject jo = new JSONObject();
			 khglShglrxx.setXb(khglShglrxx.getXb() == null ? " " : sysDictService.queryDictTextByKey("sex", khglShglrxx.getXb()));
			 khglShglrxx.setHyzk(khglShglrxx.getHyzk() == null ? " " : sysDictService.queryDictTextByKey("hyzk", khglShglrxx.getHyzk()));
			 if (khglShglrxx.getZjhm() != zjhm) {
				 jo.put("id", khglShglrxx.getId());
				 jo.put("zjhm", khglShglrxx.getZjhm());
				 jo.put("khmc", khglShglrxx.getKhmc());
				 jsonArray.add(jo);
			 }
		 }

		 //通过法人证件号码查询户号编码
		 QueryWrapper<Nhxq> hmcQueryWrapper = new QueryWrapper<>();
		 hmcQueryWrapper.eq("zjhm", zjhm);
		 Nhxq khhmcxx = nhxqService.getOne(hmcQueryWrapper);
		 try {
			 if (khhmcxx != null) {
				 //查询授信对象证件号码，如果法人家庭成员中在农户中存在授信对象，则商户授信对象下拉列表中只展示授信对象的证件号码，其余家庭成员不展示,反之则全部展示
				 QueryWrapper<KhglNhhzxxgl> khglNhhzxxglQueryWrapper = new QueryWrapper<>();
				 khglNhhzxxglQueryWrapper.eq("hhbm", khhmcxx.getHhbm());
				 KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getOne(khglNhhzxxglQueryWrapper);
				 if (khglNhhzxxgl != null) {
					 if (khglNhhzxxgl.getSxdxzjh() != null) {
						 JSONObject jo = new JSONObject();
						 jo.put("id", khglNhhzxxgl.getId());
						 jo.put("zjhm", khglNhhzxxgl.getSxdxzjh());
						 jo.put("khmc", khglNhhzxxgl.getSxdx());
						 jsonArray.add(jo);
					 }
				 }
				 QueryWrapper<Nhxq> khhmcQueryWrapper = new QueryWrapper<>();
				 khhmcQueryWrapper.eq("hhbm", khhmcxx.getHhbm());
				 khhmcQueryWrapper.orderByAsc("yhzgx");
				 List<Nhxq> list = nhxqService.list(khhmcQueryWrapper);
				 List<NhJtcyxx> nhJtcyxx = new ArrayList<NhJtcyxx>();
				 for (int i = 0; i < list.size(); i++) {
					 JSONObject jo = new JSONObject();
					 NhJtcyxx nhJtcyxx1 = new NhJtcyxx();
					 BeanUtils.copyProperties(list.get(i), nhJtcyxx1);
					 String yhzgx = nhJtcyxx1.getYhzgx() == null ? " " : sysDictService.queryDictTextByKey("yhzgx", nhJtcyxx1.getYhzgx());
					 nhJtcyxx1.setYhzgx(yhzgx);
					 String xb = nhJtcyxx1.getXb() == null ? " " : sysDictService.queryDictTextByKey("sex", nhJtcyxx1.getXb());
					 nhJtcyxx1.setXb(xb);
					 if (khglNhhzxxgl != null) {
						 if (khglNhhzxxgl.getSxdxzjh() == null) {
							 jo.put("id", nhJtcyxx1.getId());
							 jo.put("zjhm", nhJtcyxx1.getZjhm());
							 jo.put("khmc", nhJtcyxx1.getKhmc());
							 jsonArray.add(jo);
						 }
					 } else {
						 jo.put("id", nhJtcyxx1.getId());
						 jo.put("zjhm", nhJtcyxx1.getZjhm());
						 jo.put("khmc", nhJtcyxx1.getKhmc());
						 jsonArray.add(jo);
					 }
						/*if (!list.get(i).getZjhm().equals(zjhm)) {
							jo.put("id", nhJtcyxx1.getId());
							jo.put("zjhm", nhJtcyxx1.getZjhm());
							jo.put("khmc", nhJtcyxx1.getKhmc());
							jsonArray.add(jo);
						}*/
					 nhJtcyxx.add(nhJtcyxx1);
					 List<Ywhywwlxx> ywhywwlxxes = ywhywwlxxService.selectByMainId(list.get(i).getZjhm());
					 if (ywhywwlxxes != null && ywhywwlxxes.size() > 0) {
						 if (nhJtcyxx.size() > 0) {
							 ywhywwlxxes.get(0).setId(list.get(i).getId());
							 BeanUtils.copyProperties(ywhywwlxxes.get(0), nhJtcyxx.get(i));
						 }
					 }
				 }
				 jsonObject.put("nhJtcyxx", nhJtcyxx);
			 } else {
				 jsonObject.put("nhJtcyxx", "");
			 }
			 jsonObject.put("sxdx", jsonArray);
			 return Result.ok(jsonObject);
		 } catch (Exception e) {
			 e.printStackTrace();
			 return Result.error(e.toString());
		 }
	 }

	 @Transactional
	 @RequestMapping(value = "/AddPjsxxx", method = RequestMethod.POST)
	 public Result<?> AddPjsxxx(@RequestBody CamsZcsxQypjsxxx jsonObject) {
		 try {
			 QueryWrapper<CamsZcsxQypjsxxx> pjsxxx = new QueryWrapper<>();
			 pjsxxx.eq("qyid", jsonObject.getQyid());
			 List<CamsZcsxQypjsxxx> list = camsZcsxQypjsxxxService.list(pjsxxx);
			 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 if (list != null && list.size() > 0) {
				 UpdateWrapper<CamsZcsxQypjsxxx> update = new UpdateWrapper<>();
				 update.eq("qyid", jsonObject.getQyid());
				 jsonObject.setLrr(sysUser.getUsername());
				 jsonObject.setXgr(sysUser.getUsername());
				 jsonObject.setXgsj(new Date());
				 camsZcsxQypjsxxxService.update(jsonObject, update);
			 } else {
				 Vhrbasstaffpost vhrbasstaffpost = vhrbasstaffpostService.selectByYggh(sysUser.getWorkNo());
				 if (vhrbasstaffpost != null) {
					 jsonObject.setSxdxsszh(vhrbasstaffpost.getZzbz());
				 }
				 jsonObject.setLrbz("1");
				 jsonObject.setLrr(sysUser.getUsername());
				 camsZcsxQypjsxxxService.save(jsonObject);
			 }
		 } catch (Exception e) {
			 log.error("编辑评级授信信息失败", e);
			 return Result.error("系统错误，编辑评级授信信息失败！");
		 }
		 return Result.ok("添加成功");
	 }
	 @Transactional
	 @RequestMapping(value = "/AddFcxx", method = RequestMethod.POST)
	 public Result<?> AddFcxx(@RequestBody List<CamsZcsxQyfcxx> shfcxxList) {
		 try {
			 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 if (shfcxxList != null && shfcxxList.size() > 0) {
				 UpdateWrapper<CamsZcsxQyfcxx> updateWrapper = new UpdateWrapper<>();
				 updateWrapper.eq("qyid", shfcxxList.get(0).getQyid());
				 //先删除数据
				 camsZcsxQyfcxxService.remove(updateWrapper);
				 for (int i = 0; i < shfcxxList.size(); i++) {
					 shfcxxList.get(i).setLrsj(new Date());
					 shfcxxList.get(i).setLrr(sysUser.getUsername());
					 shfcxxList.get(i).setFcbm(UUID.randomUUID().toString().substring(0, 32));
				 }
				 camsZcsxQyfcxxService.saveBatch(shfcxxList);
			 }
		 } catch (Exception e) {
			 log.error("编辑房产信息失败", e);
			 return Result.error("系统错误，编辑房产信息失败！");
		 }
		 return Result.ok("添加成功");
	 }

	 @RequestMapping(value = "/queryByShidAndZllx",method = RequestMethod.GET)
	 public Result<?> queryByHhbmAndZllx(@RequestParam(name = "qyid") String qyid,
										 @RequestParam(name = "zllx") String zllx) {
		 try {
			 QueryWrapper<CamsJbxxQyzllb> fjxxQueryWrapper=new QueryWrapper<>();
			 fjxxQueryWrapper.eq("qyid", qyid);
			 fjxxQueryWrapper.eq("zllx", zllx);
			 List<CamsJbxxQyzllb> list = camsJbxxQyzllbService.list(fjxxQueryWrapper);
			 if (list!=null && list.size()>0){
				 list.get(0).setZllj("");
				 return Result.ok(list.get(0));
			 }
		 }catch (Exception e){
			 log.error("查询企业附件信息失败", e);
			 return  Result.error("查询企业附件信息失败");
		 }
		 return Result.ok("查询成功");
	 }

	/**
	 * 添加
	 *
	 * @param khglQyxxgl
	 * @return
	 */
	@AutoLog(value = "企业信息管理_pad-添加")
	@ApiOperation(value="企业信息管理_pad-添加", notes="企业信息管理_pad-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody VKhglQyxxgl khglQyxxgl) {
		khglQyxxglService.save(khglQyxxgl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param khglQyxxgl
	 * @return
	 */
	@AutoLog(value = "企业信息管理_pad-编辑")
	@ApiOperation(value="企业信息管理_pad-编辑", notes="企业信息管理_pad-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody VKhglQyxxgl khglQyxxgl) {
		khglQyxxglService.updateById(khglQyxxgl);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "企业信息管理_pad-通过id删除")
	@ApiOperation(value="企业信息管理_pad-通过id删除", notes="企业信息管理_pad-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khglQyxxglService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "企业信息管理_pad-批量删除")
	@ApiOperation(value="企业信息管理_pad-批量删除", notes="企业信息管理_pad-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khglQyxxglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "企业信息管理_pad-通过id查询")
	@ApiOperation(value="企业信息管理_pad-通过id查询", notes="企业信息管理_pad-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VKhglQyxxgl khglQyxxgl = khglQyxxglService.getById(id);
		return Result.ok(khglQyxxgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khglQyxxgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, VKhglQyxxgl khglQyxxgl) {
      return super.exportXls(request, khglQyxxgl, VKhglQyxxgl.class, "企业信息管理_pad");
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
      return super.importExcel(request, response, VKhglQyxxgl.class);
  }

}
