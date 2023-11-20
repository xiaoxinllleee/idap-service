package org.cmms.modules.khxxgl.khflgl.qyxx.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.khgl.nh.service.IYwhywwlxxService;
import org.cmms.modules.khgl.sh.entity.Dksjmx;
import org.cmms.modules.khgl.sh.entity.Etc;
import org.cmms.modules.khgl.sh.entity.ShglYwhywwlxx;
import org.cmms.modules.khgl.sh.entity.Sjyh;
import org.cmms.modules.khgl.sh.service.IDksjmxService;
import org.cmms.modules.khgl.sh.service.IEtcService;
import org.cmms.modules.khgl.sh.service.IShglYwhywwlxxService;
import org.cmms.modules.khgl.sh.service.ISjyhService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.khxxgl.khflgl.qyxx.entity.*;
import org.cmms.modules.khxxgl.khflgl.qyxx.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khxxgl.khflgl.qyxx.verify.QyxxImportVoVerify;
import org.cmms.modules.khxxgl.khflgl.shxq.entity.Shxq;
import org.cmms.modules.khxxgl.khflgl.shxq.entity.ShxqImportVo;
import org.cmms.modules.khxxgl.khjbzl.entity.Khjbzl;
import org.cmms.modules.khxxgl.khjbzl.service.IKhjbzlService;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzxxgl;
import org.cmms.modules.pad.nhxxgl.entity.NhJtcyxx;
import org.cmms.modules.pad.nhxxgl.service.IKhglNhhzxxglService;
import org.cmms.modules.pad.shxxgl.entity.*;
import org.cmms.modules.pad.shxxgl.service.IFxezhService;
import org.cmms.modules.pad.shxxgl.service.IXjlghjcService;
import org.cmms.modules.pad.shxxgl.service.IXykService;
import org.cmms.modules.qtsjdrjk.posjsh.entity.ShywxxPosjsh;
import org.cmms.modules.qtsjdrjk.posjsh.service.IShywxxPosjshService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 企业信息
 * @Author: jeecg-boot
 * @Date:   2022-11-02
 * @Version: V1.0
 */
@Slf4j
@Api(tags="企业信息")
@RestController
@RequestMapping("/qyxx/qyxxgl")
public class QyxxglController extends JeecgController<Qyxxgl, IQyxxglService> {
	@Autowired
	private IQyxxglService qyxxglService;
	 @Value(value = "${common.path.upload}")
	 private String uploadpath;
	 @Autowired
	 private IKhjbzlService khjbzlService;
	 @Autowired
	 private IHrBasOrganizationService hrBasOrganizationService;
	 @Autowired
	 private QyxxImportVoVerify qyxxImportVoVerify;
	 @Autowired
	 private IKhglQyglrxxService khglQyglrxxService;
	 @Autowired
	 private ISysDictService sysDictService;
	 @Autowired
	 private INhxqService nhxqService;
	 @Autowired
	 private IKhglNhhzxxglService khglNhhzxxglService;
	 @Autowired
	 private IYwhywwlxxService ywhywwlxxService;
	 @Autowired
	 private IShglYwhywwlxxService iShglYwhywwlxxService;
	 @Autowired
	 private IDksjmxService dksjmxService;
	 @Autowired
	 private ITjfxZhbyService tjfxZhbyService;
	 @Autowired
	 private ISjyhService sjyhService;
	 @Autowired
	 private IEtcService etcService;
	 @Autowired
	 private IFxezhService fxezhService;
	 @Autowired
	 private IShywxxPosjshService shywxxPosjshService;
	 @Autowired
	 private IXykService xykService;
	 @Autowired
	 private IXjlghjcService xjlghjcService;
	 @Autowired
	 private ICamsZcsxQypjsxxxService camsZcsxQypjsxxxService;
	 @Autowired
	 private ICamsZcsxQyfcxxService camsZcsxQyfcxxService;
 	@Autowired
	 private ICamsJbxxQyzllbService camsJbxxQyzllbService;



	/**
	 * 分页列表查询
	 *
	 * @param qyxxgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "企业信息-分页列表查询")
	@ApiOperation(value="企业信息-分页列表查询", notes="企业信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Qyxxgl qyxxgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Qyxxgl> queryWrapper = QueryGenerator.initQueryWrapper(qyxxgl, req.getParameterMap());
		Page<Qyxxgl> page = new Page<Qyxxgl>(pageNo, pageSize);
		IPage<Qyxxgl> pageList = qyxxglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param qyxxgl
	 * @return
	 */
	@AutoLog(value = "企业信息-添加")
	@ApiOperation(value="企业信息-添加", notes="企业信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Qyxxgl qyxxgl) {
		QueryWrapper queryWrapperZzbz =new QueryWrapper();
		queryWrapperZzbz.eq("ywjgdm",qyxxgl.getJgdm());
		HrBasOrganization hrBasOrganization = hrBasOrganizationService.getOne(queryWrapperZzbz);
		qyxxgl.setSszh(hrBasOrganization.getZzbz());
		qyxxglService.save(qyxxgl);
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		QueryWrapper<Khjbzl> queryWrapper =new QueryWrapper();
		queryWrapper.eq("zjhm",qyxxgl.getTyshxydm());
		Khjbzl khjbzl=khjbzlService.getOne(queryWrapper);
		if(khjbzl!=null){
			if(khjbzl.getKhxz()==null||khjbzl.getKhxz().isEmpty()){
				khjbzl.setKhxz("2");
			}else{
				Boolean sfysh=false;
				String[] split = khjbzl.getKhxz().split(",");
				for(String khxz:split){
					if(khxz.equals("2")){
						sfysh=true;
					};
				}
				if(!sfysh){
					khjbzl.setKhxz(khjbzl.getKhxz()+",2");
				}
			}
			if(StringUtils.isEmpty(khjbzl.getWgbh())){
				khjbzl.setWgbh(qyxxgl.getWgbh());
			}
			if(StringUtils.isEmpty(khjbzl.getJgdm())){
				khjbzl.setJgdm(qyxxgl.getJgdm());
			}
			if (StringUtils.isEmpty(khjbzl.getKhlx())) {
				khjbzl.setKhlx("2");
			}
			if(StringUtils.isEmpty(khjbzl.getLxfs())){
				khjbzl.setLxfs(qyxxgl.getQylxdh());
			}
			if(StringUtils.isEmpty(khjbzl.getDz())){
				khjbzl.setDz(qyxxgl.getZcdz());
			}
			khjbzlService.update(khjbzl,queryWrapper);

		}else{
			Khjbzl khjbzlSave =new Khjbzl();
			khjbzlSave.setWgbh(qyxxgl.getWgbh());
			khjbzlSave.setJgdm(qyxxgl.getJgdm());
			khjbzlSave.setKhmc(qyxxgl.getQymc());
			khjbzlSave.setZjlx("45");
			khjbzlSave.setZjhm(qyxxgl.getTyshxydm());
			khjbzlSave.setLxfs(qyxxgl.getQylxdh());
			khjbzlSave.setDz(qyxxgl.getZcdz());
			khjbzlSave.setKhxz("2");
			khjbzlSave.setKhlx("2");
			khjbzlSave.setKhlb("2");
			khjbzlSave.setDabh(UUIDGenerator.generate());
			khjbzlSave.setCreateTime(new Date());
			khjbzlSave.setCreateBy(sysUser.getUsername());
			khjbzlService.save(khjbzlSave);
		}
		//qyxxglService.save(qyxxgl);
		return Result.ok("添加成功！");
	}




	 /**
	  * pad端分页列表查询
	  *
	  * @param vKhglShxxgl
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
/*	 @AutoLog(value = "企业信息管理-分页列表查询")
	 @ApiOperation(value = "企业信息管理-分页列表查询", notes = "企业信息管理-分页列表查询")
	 @GetMapping(value = "/queryPageList")
	 public Result<?> queryPageList(VKhglShxxgl vKhglShxxgl,
									@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
									@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
									String startDate, String endDate,
									HttpServletRequest req) {
		 QueryWrapper<VKhglShxxgl> queryWrapper = QueryGenerator.initQueryWrapper(vKhglShxxgl, req.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 if (org.apache.commons.lang.StringUtils.isNotBlank(startDate) && org.apache.commons.lang.StringUtils.isNotBlank(endDate)) {
			 try {
				 queryWrapper.between("clrq", sdf.parse(startDate), sdf.parse(endDate));
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

		 Page<VKhglShxxgl> page = new Page<VKhglShxxgl>(pageNo, pageSize);
		 IPage<VKhglShxxgl> pageList = vKhglShxxglService.page(page, queryWrapper);
		 return Result.ok(pageList);
	 }*/




	 /**
	  * pad端添加企业信息
	  *
	  * @param khglQyhmcxx
	  * @return
	  */
	 @RequestMapping(value = "/AddQyxx", method = RequestMethod.POST)
	 public Result<?> AddQyxx(@RequestBody Qyxxgl khglQyhmcxx) {
		 try {
			 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 String ygjgdm = hrBasOrganizationService.getYwjgdmByZzbz(khglQyhmcxx.getSszh());
			 khglQyhmcxx.setJgdm(ygjgdm == null ? "" : ygjgdm);
			 QueryWrapper<Qyxxgl> qyhmcxxQueryWrapper = new QueryWrapper<>();
			 qyhmcxxQueryWrapper.eq("tyshxydm", khglQyhmcxx.getTyshxydm());
			 List<Qyxxgl> shhmcxxList = qyxxglService.list(qyhmcxxQueryWrapper);
			 if (shhmcxxList != null && shhmcxxList.size() > 0) {
				 return Result.error("企业信息已存在");
			 }
			 qyxxglService.save(khglQyhmcxx);
			 QueryWrapper<Khjbzl> queryWrapper = new QueryWrapper();
			 queryWrapper.eq("zjhm", khglQyhmcxx.getTyshxydm());
			 Khjbzl khjbzl = khjbzlService.getOne(queryWrapper);
			 if (khjbzl != null) {
				 if (khjbzl.getKhxz() == null || khjbzl.getKhxz().isEmpty()) {
					 khjbzl.setKhxz("2");
				 } else {
					 Boolean sfysh = false;
					 String[] split = khjbzl.getKhxz().split(",");
					 for (String khxz : split) {
						 if (khxz.equals("2")) {
							 sfysh = true;
						 }
						 ;
					 }
					 if (!sfysh) {
						 khjbzl.setKhxz(khjbzl.getKhxz() + ",2");
					 }
				 }
				 if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getWgbh())) {
					 khjbzl.setWgbh(khglQyhmcxx.getWgbh());
				 }
				 if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getJgdm())) {
					 khjbzl.setJgdm(khglQyhmcxx.getJgdm());
				 }
				 if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getKhlx())) {
					 khjbzl.setKhlx("2");
				 }
				 if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getLxfs())) {
					 khjbzl.setLxfs(khglQyhmcxx.getLxfs());
				 }
				 if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getDz())) {
					 khjbzl.setDz(khglQyhmcxx.getDz());
				 }
				 khjbzlService.update(khjbzl, queryWrapper);

			 } else {
				 Khjbzl khjbzlSave = new Khjbzl();
				 khjbzlSave.setWgbh(khglQyhmcxx.getWgbh());
				 khjbzlSave.setJgdm(khglQyhmcxx.getJgdm());
				 khjbzlSave.setKhmc(khglQyhmcxx.getQymc());
				 khjbzlSave.setZjlx("45");
				 khjbzlSave.setZjhm(khglQyhmcxx.getTyshxydm());
				 khjbzlSave.setLxfs(khglQyhmcxx.getLxfs());
				 khjbzlSave.setDz(khglQyhmcxx.getDz());
				 khjbzlSave.setKhxz("2");
				 khjbzlSave.setKhlx("2");
				 khjbzlSave.setKhlb("2");
				 khjbzlSave.setDabh(UUIDGenerator.generate());
				 khjbzlSave.setCreateTime(new Date());
				 khjbzlSave.setCreateBy(sysUser.getUsername());
				 khjbzlService.save(khjbzlSave);
			 }

	/*		 saveYxglShhfxxb(khglShhmcxx);*/
			 //提取是否有效走访
/*			 qyxxglService.init(khglShhmcxx.getId(), sysUser.getWorkNo(), sysUser.getUsername());*/
		 } catch (Exception e) {
			 log.error("添加商户信息失败", e);
			 return Result.error("系统错误，添加失败！");
		 }
		 return Result.ok("添加成功");
	 }


/*
	 */
/**
	  * 保存回访信息
	  *//*

	 protected void saveYxglShhfxxb(Qyxq khglShhmcxx) {
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
		 shhfxxb.setKhmc(khglShhmcxx.getShmc());
		 shhfxxb.setZjhm(khglShhmcxx.getTyshxydm());
		 shhfxxb.setHfrq(DateUtil.parseDateFormat(DateUtil.formatDateTime("yyyy-MM-dd"), "yyyy-MM-dd"));
		 shhfxxb.setSjly("2");
		 shhfxxb.setLrr(sysUser.getUsername());
		 shhfxxbService.save(shhfxxb);
	 }
*/




	 /**
	 * 编辑
	 *
	 * @param qyxxgl
	 * @return
	 */
	@AutoLog(value = "企业信息-编辑")
	@ApiOperation(value="企业信息-编辑", notes="企业信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Qyxxgl qyxxgl) {
		QueryWrapper queryWrapperZzbz =new QueryWrapper();
		queryWrapperZzbz.eq("ywjgdm",qyxxgl.getJgdm());
		HrBasOrganization hrBasOrganization = hrBasOrganizationService.getOne(queryWrapperZzbz);
		qyxxgl.setSszh(hrBasOrganization.getZzbz());

		qyxxglService.updateById(qyxxgl);


		QueryWrapper<Khjbzl> queryWrapper =new QueryWrapper();
		queryWrapper.eq("zjhm",qyxxgl.getTyshxydm());
		Khjbzl khjbzl=khjbzlService.getOne(queryWrapper);
		if(khjbzl!=null) {
			if (StringUtils.isEmpty(khjbzl.getWgbh())) {
				khjbzl.setWgbh(qyxxgl.getWgbh());
			}
			if (StringUtils.isEmpty(khjbzl.getJgdm())) {
				khjbzl.setJgdm(qyxxgl.getJgdm());
			}
			if (StringUtils.isEmpty(khjbzl.getKhlx())) {
				khjbzl.setKhlx("2");
			}
			if (StringUtils.isEmpty(khjbzl.getLxfs())) {
				khjbzl.setLxfs(khjbzl.getLxfs());
			}
			if (StringUtils.isEmpty(khjbzl.getDz())) {
				khjbzl.setDz(khjbzl.getDz());
			}

			khjbzlService.update(khjbzl, queryWrapper);
		}

		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "企业信息-通过id删除")
	@ApiOperation(value="企业信息-通过id删除", notes="企业信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		qyxxglService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "企业信息-批量删除")
	@ApiOperation(value="企业信息-批量删除", notes="企业信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.qyxxglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "企业信息-通过id查询")
	@ApiOperation(value="企业信息-通过id查询", notes="企业信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Qyxxgl qyxxgl = qyxxglService.getById(id);
		return Result.ok(qyxxgl);
	}

	 /**
	  * 查询关系人信息
	  * @param id
	  * @return
	  */
	 @RequestMapping(value = "/querygxrxx", method = RequestMethod.GET)
	 public Result<?> querygxrxx(@RequestParam(name = "id", required = true) String id) {
		 try {
			 JSONArray jsonArray = new JSONArray();
			 JSONObject jsonObject = new JSONObject();
			 //通过id查询法人证件号码
			 QueryWrapper<Qyxxgl> queryWrapper = new QueryWrapper<>();
			 queryWrapper.eq("id", id);
			 Qyxxgl qyxxgl = service.getOne(queryWrapper);

			 JSONObject json = new JSONObject();
			 json.put("id", qyxxgl.getId());
			 json.put("zjhm", qyxxgl.getTyshxydm());
			 json.put("khmc", qyxxgl.getQymc());
			 jsonArray.add(json);
			 QueryWrapper<KhglQyglrxx> khglQyglrxxQueryWrapper = new QueryWrapper<>();
			 khglQyglrxxQueryWrapper.eq("qy_id", id);
			 List<KhglQyglrxx> khglQyglrxxList = khglQyglrxxService.list(khglQyglrxxQueryWrapper);
			 List<QyGlrxx> glrxxList = new ArrayList<>();
			 for (KhglQyglrxx khglQyglrxx : khglQyglrxxList) {
				 QyGlrxx qyglrxx = new QyGlrxx();
				 BeanUtils.copyProperties(khglQyglrxx, qyglrxx);
				 JSONObject jo = new JSONObject();
				 qyglrxx.setXb_dictText(khglQyglrxx.getXb() == null ? " " : sysDictService.queryDictTextByKey("sex", khglQyglrxx.getXb()));
				 qyglrxx.setHyzk_dictText(khglQyglrxx.getHyzk() == null ? " " : sysDictService.queryDictTextByKey("hyzk", khglQyglrxx.getHyzk()));
				 if (!khglQyglrxx.getZjhm().equals(qyxxgl.getFrzjhm())) {
					 jo.put("id", khglQyglrxx.getId());
					 jo.put("zjhm", khglQyglrxx.getZjhm());
					 jo.put("khmc", khglQyglrxx.getKhmc());
					 jsonArray.add(jo);
				 }
				 glrxxList.add(qyglrxx);
			 }
			 jsonObject.put("glrxxList", glrxxList);

			 //通过法人证件号码查询户号编码
			 if (qyxxgl.getFrzjhm() != null) {
				 QueryWrapper<Nhxq> hmcQueryWrapper = new QueryWrapper<>();
				 hmcQueryWrapper.eq("zjhm", qyxxgl.getFrzjhm());
				 Nhxq khhmcxx = nhxqService.getOne(hmcQueryWrapper);
				 //通过户号编码查询家庭成员信息
				 if (khhmcxx != null) {
					 QueryWrapper<Nhxq> khhmcQueryWrapper = new QueryWrapper<>();
					 khhmcQueryWrapper.eq("hhbm", khhmcxx.getHhbm());
					 khhmcQueryWrapper.orderByAsc("yhzgx");
					 List<Nhxq> list = nhxqService.list(khhmcQueryWrapper);

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

					 List<NhJtcyxx> nhJtcyxx = new ArrayList<NhJtcyxx>();
					 for (int i = 0; i < list.size(); i++) {
						 JSONObject jo = new JSONObject();
						 NhJtcyxx nhJtcyxx1 = new NhJtcyxx();
						 BeanUtils.copyProperties(list.get(i), nhJtcyxx1);
						 String yhzgx = nhJtcyxx1.getYhzgx() == null ? " " : sysDictService.queryDictTextByKey("yhzgx", nhJtcyxx1.getYhzgx());
						 nhJtcyxx1.setYhzgx(yhzgx);
						 String xb = nhJtcyxx1.getXb() == null ? " " : sysDictService.queryDictTextByKey("sex", nhJtcyxx1.getXb());
						 nhJtcyxx1.setXb(xb);
						 nhJtcyxx.add(nhJtcyxx1);
						/*if (!list.get(i).getZjhm().equals(khglShhmcxx.getFrzjhm())) {
							jo.put("id", nhJtcyxx1.getId());
							jo.put("zjhm", nhJtcyxx1.getZjhm());
							jo.put("khmc", nhJtcyxx1.getKhmc());
							jsonArray.add(jo);
						}*/
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

						 List<Ywhywwlxx> ywhywwlxxes = ywhywwlxxService.selectByMainId(list.get(i).getZjhm());
						 if (ywhywwlxxes != null && ywhywwlxxes.size() > 0) {
							 if (nhJtcyxx.size() > 0) {
								 ywhywwlxxes.get(0).setId(list.get(i).getId());
								 BeanUtils.copyProperties(ywhywwlxxes.get(0), nhJtcyxx.get(i));
							 }
						 }
					 }
					 jsonObject.put("nhJtcyxx", nhJtcyxx);
				 }
			 } else {
				 JSONArray jsonArray1 = new JSONArray();
				 jsonObject.put("nhJtcyxx", jsonArray1);
			 }
			 jsonObject.put("sxdx", jsonArray);
			 return Result.ok(jsonObject);
		 } catch (Exception e) {
			 e.printStackTrace();
			 log.error("查询关系人信息错误！", e);
			 return Result.error("查询关系人信息错误！");
		 }

	 }

	 @RequestMapping(value = "/AddGlrxx", method = RequestMethod.POST)
	 public Result<?> AddGlrxx(@RequestBody KhglQyglrxx khglShglrxx) {
		 QueryWrapper<KhglQyglrxx> khglShglrxxQueryWrapper = new QueryWrapper<>();
		 khglShglrxxQueryWrapper.eq("qy_id", khglShglrxx.getQyId()).eq("zjhm", khglShglrxx.getZjhm());
		 KhglQyglrxx khglShglrxx1 = khglQyglrxxService.getOne(khglShglrxxQueryWrapper);
		 try {
			 if (khglShglrxx1 != null) {
				 return Result.error("该关联人在该公司存在关联关系");
			 } else {
				 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
				 khglShglrxx.setLrbz("1");
				 khglShglrxx.setLrr(sysUser.getUsername());
				 khglQyglrxxService.save(khglShglrxx);
				 return Result.ok("添加成功");
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
			 log.error("添加成员失败", e);
			 return Result.error("系统错误，添加失败！");
		 }

	 }

	 //查询对公业务信息
	 @RequestMapping(value = "/queryDgywxx", method = RequestMethod.GET)
	 public Result<?> queryDgywxx(@RequestParam(name = "id", required = true) String id) {
		 try {
			 JSONObject jsonObject = new JSONObject();
			 QueryWrapper<Qyxxgl> shhmcxxQueryWrapper = new QueryWrapper<>();
			 shhmcxxQueryWrapper.eq("id", id);
			 Qyxxgl qyhmcxx = qyxxglService.getOne(shhmcxxQueryWrapper);

			 //业务往来信息
			 QueryWrapper<ShglYwhywwlxx> shglYwhywwlxxQueryWrapper = new QueryWrapper<>();
			 shglYwhywwlxxQueryWrapper.eq("zjhm", qyhmcxx.getTyshxydm());
			 List<ShglYwhywwlxx> shglYwhywwlxxList = iShglYwhywwlxxService.list(shglYwhywwlxxQueryWrapper);

			 //贷款业务信息
			 QueryWrapper<Dksjmx> dksjmxQueryWrapper = new QueryWrapper<>();
			 dksjmxQueryWrapper.eq("zjhm", qyhmcxx.getTyshxydm());
			 List<Dksjmx> dksjmxList = dksjmxService.list(dksjmxQueryWrapper);
			 for (Dksjmx dksjmx : dksjmxList) {
				 dksjmx.setDyzrr(dksjmx.getDyzrr() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", dksjmx.getDyzrr()));
				 dksjmx.setKhjlbz(dksjmx.getKhjlbz() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", dksjmx.getKhjlbz()));
				 dksjmx.setDkpz(dksjmx.getDkpz() == null ? "" : sysDictService.queryDictTextByKey("dkzl", dksjmx.getDkpz()));
				 dksjmx.setDkxt(dksjmx.getDkxt() == null ? "" : sysDictService.queryDictTextByKey("dkxt", dksjmx.getDkxt()));
				 dksjmx.setDbfs(dksjmx.getDbfs() == null ? "" : sysDictService.queryDictTextByKey("dbfs", dksjmx.getDbfs()));
			 }
			 //手机银行
			 QueryWrapper<Sjyh> sjyhQueryWrapper = new QueryWrapper<>();
			 sjyhQueryWrapper.eq("zjhm", qyhmcxx.getTyshxydm());
			 List<Sjyh> sjyhList = sjyhService.list(sjyhQueryWrapper);
			 for (Sjyh sjyh : sjyhList) {
				 sjyh.setCancelGyh(sjyh.getCancelGyh() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", sjyh.getCancelGyh()));
				 sjyh.setStatus(sjyh.getStatus() == null ? "" : sysDictService.queryDictTextByKey("khywxx_kxhzt", sjyh.getStatus()));
				 sjyh.setOpenType(sjyh.getOpenType() == null ? "" : sysDictService.queryDictTextByKey("sjyh_khlx", sjyh.getOpenType()));
			 }

			 //ETC
			 QueryWrapper<Etc> etcQueryWrapper = new QueryWrapper<>();
			 etcQueryWrapper.eq("zjhm", qyhmcxx.getTyshxydm());
			 List<Etc> etcList = etcService.list(etcQueryWrapper);

			 if (qyhmcxx.getFrzjhm() != null) {
				 //福祥E支付
				 QueryWrapper<Fxezh> fxezhQueryWrapper = new QueryWrapper<>();
				 fxezhQueryWrapper.eq("drzjhm", qyhmcxx.getFrzjhm());
				 List<Fxezh> fxezhList = fxezhService.list(fxezhQueryWrapper);

				 //POS机商户
				 QueryWrapper<ShywxxPosjsh> posjshQueryWrapper = new QueryWrapper<>();
				 posjshQueryWrapper.eq("drzjhm", qyhmcxx.getFrzjhm());
				 List<ShywxxPosjsh> posjshList = shywxxPosjshService.list(posjshQueryWrapper);


				 //信用卡
				 QueryWrapper<Xyk> xykQueryWrapper = new QueryWrapper<>();
				 xykQueryWrapper.eq("zjhm", qyhmcxx.getFrzjhm());
				 List<Xyk> xykList = xykService.list(xykQueryWrapper);

				 //现金流归行检测
				 QueryWrapper<Nhxq> khhmcxxQueryWrapper = new QueryWrapper<>();
				 khhmcxxQueryWrapper.eq("zjhm", qyhmcxx.getFrzjhm());
				 Nhxq khhmcxx = nhxqService.getOne(khhmcxxQueryWrapper);

				 if (khhmcxx != null) {
					 List<Xjlghjc> xjlghjcList = xjlghjcService.queryXjlGhjc(khhmcxx.getHhbm());
					 jsonObject.put("xjlghjcList", xjlghjcList);
				 }

				 jsonObject.put("fxezhList", fxezhList);
				 jsonObject.put("xykList", xykList);
				 jsonObject.put("posjshList", posjshList);
			 } else {
				 List<Xyk> xykList = new ArrayList<>();
				 List<Fxezh> fxezhList = new ArrayList<>();
				 List<Xjlghjc> xjlghjcList = new ArrayList<>();
				 List<ShywxxPosjsh> posjshList = new ArrayList<>();
				 jsonObject.put("fxezhList", fxezhList);
				 jsonObject.put("xykList", xykList);
				 jsonObject.put("xjlghjcList", xjlghjcList);
				 jsonObject.put("posjshList", posjshList);
			 }
			 jsonObject.put("ywhywwlxxList", shglYwhywwlxxList);
			 jsonObject.put("dksjmxList", dksjmxList);
			 jsonObject.put("sjyhList", sjyhList);
			 jsonObject.put("etcList", etcList);
			 return Result.ok(jsonObject);
		 } catch (Exception e) {
			 e.printStackTrace();
			 log.error("查询关系人信息错误！", e);
			 return Result.error("查询关系人信息错误！");
		 }

	 }


	 /**
	  * 获取评级授信信息
	  *
	  * @param qyid
	  * @return
	  */
	 @GetMapping(value = "/queryPjsxxxByQyid")
	 public Result<?> queryPjsxxxByQyid(@Param("qyid") String qyid) {
		 try {
			 if (!org.apache.commons.lang.StringUtils.isEmpty(qyid)) {
				 CamsZcsxQypjsxxx shpjsxxx = camsZcsxQypjsxxxService.getByQyid(qyid);
				 if (shpjsxxx != null) {
					 JSONObject shpjsxxxJo = JSONObject.parseObject(JSONObject.toJSON(shpjsxxx).toString());
					 //获取资产信息
					 JSONArray shzcxxJa = new JSONArray();
					 //资产情况
					 JSONObject dcJo = new JSONObject();
					 dcJo.put("zclx", "地产");
					 dcJo.put("zcsl", shpjsxxx.getDcsl());
					 dcJo.put("zcjz", shpjsxxx.getDcjz());
					 dcJo.put("zcsm", shpjsxxx.getDcxqsm());
					 JSONObject jtgjJo = new JSONObject();
					 jtgjJo.put("zclx", "交通工具");
					 jtgjJo.put("zcsl", shpjsxxx.getJtgjsl());
					 jtgjJo.put("zcjz", shpjsxxx.getJtgjjz());
					 jtgjJo.put("zcsm", shpjsxxx.getJtgjxqsm());
					 JSONObject ckJo = new JSONObject();
					 ckJo.put("zclx", "存款");
					 ckJo.put("zcsl", shpjsxxx.getCksl());
					 ckJo.put("zcjz", shpjsxxx.getCkjz());
					 ckJo.put("zcsm", shpjsxxx.getCkxqsm());
					 JSONObject yjdzJo = new JSONObject();
					 yjdzJo.put("zclx", "有价单证");
					 yjdzJo.put("zcsl", shpjsxxx.getYjdzsl());
					 yjdzJo.put("zcjz", shpjsxxx.getYjdzjz());
					 yjdzJo.put("zcsm", shpjsxxx.getYjdzxqsm());
					 JSONObject gqJo = new JSONObject();
					 gqJo.put("zclx", "股权");
					 gqJo.put("zcsl", shpjsxxx.getGqsl());
					 gqJo.put("zcjz", shpjsxxx.getGqjz());
					 gqJo.put("zcsm", shpjsxxx.getGqxqsm());
					 JSONObject qtzcJo = new JSONObject();
					 qtzcJo.put("zclx", "其他资产");
					 qtzcJo.put("zcsl", shpjsxxx.getQtzcsl());
					 qtzcJo.put("zcjz", shpjsxxx.getQtzcjz());
					 qtzcJo.put("zcsm", shpjsxxx.getQtzcxqsm());
					 JSONObject nzsrJo = new JSONObject();
					 nzsrJo.put("zclx", "年总收入");
					 nzsrJo.put("zcsl", shpjsxxx.getNzsrsl());
					 nzsrJo.put("zcjz", shpjsxxx.getNzsrjz());
					 nzsrJo.put("zcsm", shpjsxxx.getNzsrxqsm());
					 shzcxxJa.add(dcJo);
					 shzcxxJa.add(jtgjJo);
					 shzcxxJa.add(ckJo);
					 shzcxxJa.add(yjdzJo);
					 shzcxxJa.add(gqJo);
					 shzcxxJa.add(qtzcJo);
					 shzcxxJa.add(nzsrJo);
					 //负债情况
					 JSONArray shfzxxJa = new JSONArray();
					 JSONObject bxtJo = new JSONObject();
					 bxtJo.put("jkfs", "本系统");
					 bxtJo.put("zqr", shpjsxxx.getBxtjkzqr());
					 bxtJo.put("jkje", shpjsxxx.getBxtjksl());
					 bxtJo.put("jksm", shpjsxxx.getBxtjkxqsm());
					 JSONObject thJo = new JSONObject();
					 thJo.put("jkfs", "他行");
					 thJo.put("zqr", shpjsxxx.getThjkzqr());
					 thJo.put("jkje", shpjsxxx.getThjksl());
					 thJo.put("jksm", shpjsxxx.getThjkxqsm());
					 JSONObject xykJo = new JSONObject();
					 xykJo.put("jkfs", "信用卡");
					 xykJo.put("zqr", shpjsxxx.getXykzqr());
					 xykJo.put("jkje", shpjsxxx.getXyksl());
					 xykJo.put("jksm", shpjsxxx.getXykxqsm());
					 JSONObject qtfzJo = new JSONObject();
					 qtfzJo.put("jkfs", "其他负债");
					 qtfzJo.put("zqr", shpjsxxx.getQtfzzqr());
					 qtfzJo.put("jkje", shpjsxxx.getQtfzsl());
					 qtfzJo.put("jksm", shpjsxxx.getQtfzxqsm());
					 JSONObject jtnkzJo = new JSONObject();
					 jtnkzJo.put("jkfs", "家庭年开支");
					 jtnkzJo.put("zqr", shpjsxxx.getJtnkzzqr());
					 jtnkzJo.put("jkje", shpjsxxx.getJtnkzsl());
					 jtnkzJo.put("jksm", shpjsxxx.getJtnkzxqsm());

					 shfzxxJa.add(bxtJo);
					 shfzxxJa.add(thJo);
					 shfzxxJa.add(xykJo);
					 shfzxxJa.add(qtfzJo);
					 shfzxxJa.add(jtnkzJo);

					 shpjsxxxJo.put("shzcxx", shzcxxJa);
					 shpjsxxxJo.put("shfzxx", shfzxxJa);
					 return Result.ok(shpjsxxxJo);
				 }
			 } else {
				 return Result.error("请求参数错误！");
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
			 log.error("查询评级授信信息错误！", e);
			 return Result.error("查询评级授信信息错误！");
		 }
		 return Result.ok("没有评级授信数据");
	 }

	 /**
	  * 获取房产信息
	  *
	  * @param qyid
	  * @return
	  */
	 @GetMapping(value = "/queryFcxxByQyid")
	 public Result<?> queryFcxxByQyid(@Param("qyid") String qyid) {
		 try {
			 if (!org.apache.commons.lang.StringUtils.isEmpty(qyid)) {
				 List<CamsZcsxQyfcxx> list = camsZcsxQyfcxxService.getByQyid(qyid);
				 if (list != null && list.size() > 0) {
					 return Result.ok(list);
				 }
			 } else {
				 return Result.error("请求参数错误！");
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
			 log.error("查询房产信息错误！", e);
			 return Result.error("查询房产信息错误！");
		 }
		 return Result.ok("没有房产数据");
	 }
	 /**
	  * 获取附件信息
	  * @param qyid
	  * @return
	  */
	 @GetMapping(value = "/queryFjxxByQyid")
	 public Result<?> queryFjxxByQyid(@Param("qyid") String qyid) {
		 try {
			 if (!org.apache.commons.lang.StringUtils.isEmpty(qyid)) {
				 List<CamsJbxxQyzllb> list = camsJbxxQyzllbService.getByQyid(qyid);
				 if (list != null && list.size() > 0) {
					 return Result.ok(list);
				 }
			 } else {
				 return Result.error("请求参数错误！");
			 }
		 } catch (Exception e) {
			 return Result.error(e.toString());
		 }
		 return Result.ok("没有附件数据");
	 }
	 /**
	  * 保存商户附件信息
	  * @param jsonObject
	  * @return
	  */
	 @RequestMapping(value = "/saveShfjImage",method = RequestMethod.POST)
	 public Result<?> saveShfjImage(@RequestBody  List<CamsJbxxQyzllb>  jsonObject) {
		 try {
			 if (jsonObject!=null  && jsonObject.size()>0){
				 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
				 for (int i = 0; i < jsonObject.size(); i++) {
					 if (org.apache.commons.lang.StringUtils.isEmpty(jsonObject.get(i).getId())) {
						 CamsJbxxQyzllb fjgl = new CamsJbxxQyzllb();
						 fjgl.setQydm(jsonObject.get(i).getQydm());
						 fjgl.setQyid(jsonObject.get(i).getQyid());
						 fjgl.setQymc(jsonObject.get(i).getQymc());
						 fjgl.setZllx(jsonObject.get(i).getZllx());
						 fjgl.setZldx(jsonObject.get(i).getZldx());
						 fjgl.setFwlj(jsonObject.get(i).getFwlj());
						 fjgl.setZlmc(jsonObject.get(i).getZlmc());
						 fjgl.setZllj(uploadpath + "/" + jsonObject.get(i).getFwlj());
						 fjgl.setScsj(new Date());
						 fjgl.setScr(sysUser.getUsername());
						 fjgl.setLrsj(new Date());
						 fjgl.setLrr(sysUser.getUsername());
						 camsJbxxQyzllbService.save(fjgl);
					 }
				 }
			 }
		 }catch (Exception e){
			 return  Result.error(e.toString());
		 }
		 return Result.ok("添加成功");
	 }
	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/extract")
	 public Result<?> extract() {
		 Result result = new Result<>();
		 try {
			 qyxxglService.init();
			 result.setSuccess(true);
			 return result;
		 } catch (Exception e) {
			 System.out.println(e);
			 log.error("提取失败", e.getMessage());
			 result.setSuccess(false);
		 }
		 return result;
	 }
  /**
   * 导出excel
   *
   * @param request
   * @param qyxxgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Qyxxgl qyxxgl) {
      return super.exportXls(request, qyxxgl, Qyxxgl.class, "企业信息");
  }
	 /**
	  * 导出Excel模板
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls() {
		 return super.exportTemplateXls(QyxxImportVo.class, "企业信息导入模板");
	 }
	 /**
	  * 通过excel导入数据
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 String filePaths = jsonObject.getString("filePaths");
		 if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
			 return Result.error("请先上传文件！");
		 }
		 String[] filePathList = filePaths.split(",");
		 JSONObject obj = new JSONObject();
		 for (String filePath : filePathList) {
			 String baseFilePath = uploadpath + File.separator + filePath;
//          MultipartFile file = entity.getValue();// 获取上传文件对象
			 File file = new File(baseFilePath);
			 ImportParams params = new ImportParams();
			 params.setTitleRows(1);
			 params.setHeadRows(1);
			 params.setNeedSave(false);
			 if (qyxxImportVoVerify != null) {
				 params.setVerifyHanlder(qyxxImportVoVerify);
			 }
			 FileOutputStream fos = null;
			 FileInputStream fis = null;
			 try {
				 fis = new FileInputStream(file);
				 boolean checkResult = ExcelImportCheckUtil.check(fis, QyxxImportVo.class, params);
				 if (!checkResult) {
					 return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
				 }
				 ExcelImportResult<QyxxImportVo> importResult = ExcelImportUtil.importExcelVerify(file, QyxxImportVo.class, params);
				 List<QyxxImportVo> list = importResult.getList();
				 List<Qyxxgl> qyxxList = new ArrayList<>();
				 if (CollUtil.isNotEmpty(list)) {
					 for (int i = 0; i < list.size(); i++) {
						 QyxxImportVo qyxxImportVo = list.get(i);
						 Qyxxgl qyxx = new Qyxxgl();
						 BeanUtils.copyProperties(qyxxImportVo, qyxx);
						 QueryWrapper queryWrapperZzbz =new QueryWrapper();
						 queryWrapperZzbz.eq("ywjgdm",qyxx.getJgdm());
						 HrBasOrganization hrBasOrganization = hrBasOrganizationService.getOne(queryWrapperZzbz);
						 if (hrBasOrganization != null){
							 qyxx.setSszh(hrBasOrganization.getZzbz());
						 }
						 qyxxList.add(qyxx);
					 }
				 }
				 service.saveBatch(qyxxList);
				 obj.put("filePath", filePath);
				 fos = new FileOutputStream(baseFilePath);
				 importResult.getWorkbook().write(fos);
				 fos.flush();
				 fos.close();
				 return Result.ok("文件导入完成！成功导入数据行数:" + list.size(), obj);
			 } catch (Exception e) {
				 log.error(e.getMessage(),e);
				 return Result.error("文件导入失败:"+e.getMessage());
			 } finally {
				 IoUtil.close(fis);
				 IoUtil.close(fos);
			 }
		 }
		 return Result.ok("文件导入失败！");
	 }

}
