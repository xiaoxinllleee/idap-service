package org.cmms.modules.xdgl.grdkgl.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtils;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.xdgl.grdkgl.entity.*;
import org.cmms.modules.xdgl.grdkgl.mapper.*;
import org.cmms.modules.xdgl.grdkgl.service.IGrdkglService;
import org.cmms.modules.yxdygl.sjyxdygl.service.IVSjyxdyglService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 个人贷款
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Service
public class GrdkglServiceImpl extends ServiceImpl<GrdkglMapper, Grdkgl> implements IGrdkglService {

	@Autowired
	private GrdkglMapper grdkglMapper;
	@Autowired
	private GrdkcjxxMapper grdkcjxxMapper;
	@Autowired
	private KhglKhhmcxxGrxdMapper khhmcxxGrxdMapper;
	@Autowired
	private JtcyxxMapper jtcyxxMapper;
	@Autowired
	private GlqyMapper glqyMapper;
	@Autowired
	private FwxxMapper fwxxMapper;
	@Autowired
	private CfxxMapper cfxxMapper;
	@Autowired
	private ClxxMapper clxxMapper;
	@Autowired
	private QtglzcMapper qtglzcMapper;
	@Autowired
	private YhdkMapper yhdkMapper;
	@Autowired
	private BzdbMapper bzdbMapper;
	@Autowired
	private DydbMapper dydbMapper;
	@Autowired
	private ZydbMapper zydbMapper;
	@Autowired
	private XydbMapper xydbMapper;
	@Autowired
	private GrxdzllbMapper grxdzllbMapper;
	@Autowired
	private GrxdzllbServiceImpl grxdzllbService;
	@Autowired
	private IVSjyxdyglService iSjyxdyglService;
	@Autowired
	private ISysDictService iSysDictService;
	@Value(value = "${common.path.upload}")
	private String uploadpath;

	@Override
	@Transactional
	public void saveMain(Grdkcjxx grdkcjxx,Jtcyxx hmcxx, List<Jtcyxx> jtcyxxList,List<Glqy> glqyList,List<Fwxx> fwxxList,List<Cfxx> cfxxList,List<Clxx> clxxList,List<Qtglzc> qtglzcList,List<Yhdk> yhdkList,List<Bzdb> bzdbList,List<Dydb> dydbList,List<Zydb> zydbList,List<Xydb> xydbList,JSONObject imgdate) {
		grdkcjxxMapper.insert(grdkcjxx);
		jtcyxxMapper.insert(hmcxx);
		for(Jtcyxx entity:jtcyxxList) {
			//外键设置
			entity.setHhbm(hmcxx.getHhbm());
			jtcyxxMapper.insert(entity);
		}
		for(Glqy entity:glqyList) {
			//外键设置
			entity.setZjhm(hmcxx.getZjhm());
			glqyMapper.insert(entity);
		}
		for(Fwxx entity:fwxxList) {
			//外键设置
			entity.setZjhm(hmcxx.getZjhm());
			fwxxMapper.insert(entity);
		}
		for(Cfxx entity:cfxxList) {
			//外键设置
			entity.setZjhm(hmcxx.getZjhm());
			cfxxMapper.insert(entity);
		}
		for(Clxx entity:clxxList) {
			//外键设置
			entity.setZjhm(hmcxx.getZjhm());
			clxxMapper.insert(entity);
		}
		for(Qtglzc entity:qtglzcList) {
			//外键设置
			entity.setZjhm(hmcxx.getZjhm());
			qtglzcMapper.insert(entity);
		}
		for(Yhdk entity:yhdkList) {
			//外键设置
			entity.setZjhm(hmcxx.getZjhm());
			yhdkMapper.insert(entity);
		}
		for(Bzdb entity:bzdbList) {
			//外键设置
			entity.setZjhm(hmcxx.getZjhm());
			bzdbMapper.insert(entity);
		}
		for(Dydb entity:dydbList) {
			//外键设置
			entity.setZjhm(hmcxx.getZjhm());
			dydbMapper.insert(entity);
		}
		for(Zydb entity:zydbList) {
			//外键设置
			entity.setZjhm(hmcxx.getZjhm());
			zydbMapper.insert(entity);
		}
		for(Xydb entity:xydbList) {
			//外键设置
			entity.setZjhm(hmcxx.getZjhm());
			xydbMapper.insert(entity);
		}
		//新增附件
		grxdzllbService.savefjxxMain(imgdate,"bzrgdhfileList",hmcxx,"1");
		/*Grxdzllb grxdzllb = new Grxdzllb();
		JSONArray jsonArray = imgdate.getJSONArray("bzrgdhfileList");
		if (imgdate.getJSONArray("bzrgdhfileList")!=null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				String id = UUIDGenerator.generate();
				if (jsonArray.getJSONObject(i).getJSONObject("response")!=null) {
					grxdzllb.setQydm(hmcxx.getSsyxdy());
					grxdzllb.setHhbm(hmcxx.getHhbm());
					grxdzllb.setZjhm(hmcxx.getZjhm());
					grxdzllb.setZlmc(jsonArray.getJSONObject(i).getString("name"));
					String fwlj = jsonArray.getJSONObject(i).getJSONObject("response").getString("message");
					grxdzllb.setFwlj(fwlj);
					String zjlj = uploadpath + File.separator + jsonArray.getJSONObject(i).getJSONObject("response").getString("message");
					grxdzllb.setZllj(zjlj);
					grxdzllb.setZllx("1");
					grxdzllb.setZlbh(id);
					grxdzllb.setZlmc(jsonArray.getJSONObject(i).getString("name"));
					File file = new File(zjlj);
					grxdzllb.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
					LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
					grxdzllb.setScr(loginUser.getUsername());
					grxdzllb.setLrr(loginUser.getUsername());
					grxdzllb.setScsj(DateUtils.getDate());
					grxdzllbMapper.insert(grxdzllb);
				}else{
					grxdzllb.setQydm(hmcxx.getSsyxdy());
					grxdzllb.setHhbm(hmcxx.getHhbm());
					grxdzllb.setZjhm(hmcxx.getZjhm());
					grxdzllb.setFwlj(jsonArray.getJSONObject(i).getString("url"));
					String zllj = uploadpath + File.separator + jsonArray.getJSONObject(i).getString("url");
					grxdzllb.setZllj(zllj);
					grxdzllb.setZllx("1");
					grxdzllb.setZlbh(id);
					grxdzllb.setZlmc(jsonArray.getJSONObject(i).getString("name"));
					File file = new File(zllj);
					grxdzllb.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
					LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
					grxdzllb.setScr(loginUser.getUsername());
					grxdzllb.setLrr(loginUser.getUsername());
					grxdzllb.setScsj(DateUtils.getDate());
					grxdzllbMapper.insert(grxdzllb);
				}
			}
		}*/
	}

	@Override
	public void saveMainPad(Grdkcjxx cjxx, KhglKhhmcxxGrxd grxd, List<Jtcyxx> jtcyxxList, List<Glqy> glqyList, List<Fwxx> fwxxList, List<Cfxx> cfxxList, List<Clxx> clxxList, List<Qtglzc> qtgdzcList, List<Yhdk> yhdkList, List<Bzdb> bzdbList, List<Dydb> dydbList, List<Zydb> zydbList, List<Xydb> xydbList) {
		/*String sszh = iSjyxdyglService.querySszhBySjyxdybh(grxd.getSsyxdy());*/
		String jgdm = iSysDictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION","YWJGDM","ZZBZ",grxd.getSszh());
		LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		// 个人贷款:贷前调查 - 个人贷款采集信息
		cjxx.setSskhjl(user.getWorkNo());
		cjxx.setCreateBy(user.getUsername());
		cjxx.setCreateTime(new Date());
		grdkcjxxMapper.insert(cjxx);

		// 个人贷款:贷前调查 - 个人信贷花名册
		grxd.setSszh(grxd.getSszh());
		grxd.setJgdm(jgdm);
		grxd.setSfhz("1");
		grxd.setCsny(cjxx.getZjhm().substring(6,14));
		grxd.setLrr(user.getUsername());
		grxd.setLrsj(new Date());
		khhmcxxGrxdMapper.insert(grxd);

		// 个人贷款:贷前调查 - 家庭成员
		for (Jtcyxx jtcyxx : jtcyxxList) {
			jtcyxx.setSszh(grxd.getSszh());
			jtcyxx.setJgdm(jgdm);
			jtcyxx.setSsyxdy(grxd.getSsyxdy());
			jtcyxx.setHhbm(cjxx.getHhbm());
			jtcyxx.setCsny(cjxx.getZjhm().length()!=18?"":cjxx.getZjhm().substring(6,14));
			jtcyxx.setLrr(user.getUsername());
			jtcyxx.setLrsj(new Date());
			jtcyxxMapper.insert(jtcyxx);
		}
		// 个人贷款:贷前调查 - 关联企业
		for (Glqy glqy : glqyList) {
			glqy.setKhmc(cjxx.getKhmc());
			glqy.setZjhm(cjxx.getZjhm());
			glqy.setCreateBy(user.getUsername());
			glqy.setCreateTime(new Date());
			glqyMapper.insert(glqy);
		}
		// 资产负债:固定资产 - 房屋
		for (Fwxx fwxx : fwxxList) {
			fwxx.setKhmc(cjxx.getKhmc());
			fwxx.setZjhm(cjxx.getZjhm());
			fwxx.setCreateBy(user.getUsername());
			fwxx.setCreateTime(new Date());
			fwxxMapper.insert(fwxx);
		}
		// 资产负债:固定资产 - 厂房
		for (Cfxx cfxx : cfxxList) {
			cfxx.setKhmc(cjxx.getKhmc());
			cfxx.setZjhm(cjxx.getZjhm());
			cfxx.setCreateBy(user.getUsername());
			cfxx.setCreateTime(new Date());
			cfxxMapper.insert(cfxx);
		}
		// 资产负债:固定资产 - 车辆
		for (Clxx clxx : clxxList) {
			clxx.setKhmc(cjxx.getKhmc());
			clxx.setZjhm(cjxx.getZjhm());
			clxx.setCreateBy(user.getUsername());
			clxx.setCreateTime(new Date());
			clxxMapper.insert(clxx);
		}
		// 资产负债:固定资产 - 其它固定资产
		for (Qtglzc qtgdzc : qtgdzcList) {
			qtgdzc.setKhmc(cjxx.getKhmc());
			qtgdzc.setZjhm(cjxx.getZjhm());
			qtgdzc.setCreateBy(user.getUsername());
			qtgdzc.setCreateTime(new Date());
			qtglzcMapper.insert(qtgdzc);
		}
		// 资产负债:固定资产 - 银行贷款
		for (Yhdk yhdk : yhdkList) {
			yhdk.setKhmc(cjxx.getKhmc());
			yhdk.setZjhm(cjxx.getZjhm());
			yhdk.setCreateBy(user.getUsername());
			yhdk.setCreateTime(new Date());
			yhdkMapper.insert(yhdk);
		}
		// 担保信息 - 保证担保
		for (Bzdb bzdb : bzdbList) {
			bzdb.setKhmc(cjxx.getKhmc());
			bzdb.setZjhm(cjxx.getZjhm());
			bzdb.setCreateBy(user.getUsername());
			bzdb.setCreateTime(new Date());
			bzdbMapper.insert(bzdb);
		}
		// 担保信息 - 抵押担保
		for (Dydb dydb : dydbList) {
			dydb.setKhmc(cjxx.getKhmc());
			dydb.setZjhm(cjxx.getZjhm());
			dydb.setCreateBy(user.getUsername());
			dydb.setCreateTime(new Date());
			dydbMapper.insert(dydb);
		}
		// 担保信息 - 质押担保
		for (Zydb zydb : zydbList) {
			zydb.setKhmc(cjxx.getKhmc());
			zydb.setZjhm(cjxx.getZjhm());
			zydb.setCreateBy(user.getUsername());
			zydb.setCreateTime(new Date());
			zydbMapper.insert(zydb);
		}
		// 担保信息 - 信用担保
		for (Xydb xydb : xydbList) {
			xydb.setKhmc(cjxx.getKhmc());
			xydb.setZjhm(cjxx.getZjhm());
			xydb.setCreateBy(user.getUsername());
			xydb.setCreateTime(new Date());
			xydbMapper.insert(xydb);
		}

	}

	@Override
	@Transactional
	public void updateMain(Grdkcjxx grdkcjxx, Jtcyxx hmcxx, List<Jtcyxx> jtcyxxList, List<Glqy> glqyList, List<Fwxx> fwxxList, List<Cfxx> cfxxList, List<Clxx> clxxList, List<Qtglzc> qtglzcList, List<Yhdk> yhdkList, List<Bzdb> bzdbList, List<Dydb> dydbList, List<Zydb> zydbList, List<Xydb> xydbList, JSONObject imgdate) {
		grdkcjxxMapper.updateById(grdkcjxx);
		QueryWrapper<Jtcyxx> updateWrapper =new QueryWrapper<>();
		updateWrapper.eq("zjhm",hmcxx.getZjhm());
		jtcyxxMapper.update(hmcxx,updateWrapper);
		//1.先删除子表数据
		jtcyxxMapper.deleteByMainHhbmAzjhm(hmcxx.getHhbm(),grdkcjxx.getZjhm());
		glqyMapper.deleteByMainId(hmcxx.getZjhm());
		fwxxMapper.deleteByMainId(hmcxx.getZjhm());
		cfxxMapper.deleteByMainId(hmcxx.getZjhm());
		clxxMapper.deleteByMainId(hmcxx.getZjhm());
		qtglzcMapper.deleteByMainId(hmcxx.getZjhm());
		yhdkMapper.deleteByMainId(hmcxx.getZjhm());
		bzdbMapper.deleteByMainId(hmcxx.getZjhm());
		dydbMapper.deleteByMainId(hmcxx.getZjhm());
		zydbMapper.deleteByMainId(hmcxx.getZjhm());
		xydbMapper.deleteByMainId(hmcxx.getZjhm());

		//2.子表数据重新插入
		for(Jtcyxx entity:jtcyxxList) {
			//外键设置
			entity.setHhbm(hmcxx.getHhbm());
			jtcyxxMapper.insert(entity);
		}
		for(Glqy entity:glqyList) {
			//外键设置
			entity.setZjhm(hmcxx.getZjhm());
			glqyMapper.insert(entity);
		}
		for(Fwxx entity:fwxxList) {
			//外键设置
			entity.setZjhm(hmcxx.getZjhm());
			fwxxMapper.insert(entity);
		}
		for(Cfxx entity:cfxxList) {
			//外键设置
			entity.setZjhm(hmcxx.getZjhm());
			cfxxMapper.insert(entity);
		}
		for(Clxx entity:clxxList) {
			//外键设置
			entity.setZjhm(hmcxx.getZjhm());
			clxxMapper.insert(entity);
		}
		for(Qtglzc entity:qtglzcList) {
			//外键设置
			entity.setZjhm(hmcxx.getZjhm());
			qtglzcMapper.insert(entity);
		}
		for(Yhdk entity:yhdkList) {
			//外键设置
			entity.setZjhm(hmcxx.getZjhm());
			yhdkMapper.insert(entity);
		}
		for(Bzdb entity:bzdbList) {
			//外键设置
			entity.setZjhm(hmcxx.getZjhm());
			bzdbMapper.insert(entity);
		}
		for(Dydb entity:dydbList) {
			//外键设置
			entity.setZjhm(hmcxx.getZjhm());
			dydbMapper.insert(entity);
		}
		for(Zydb entity:zydbList) {
			//外键设置
			entity.setZjhm(hmcxx.getZjhm());
			zydbMapper.insert(entity);
		}
		for(Xydb entity:xydbList) {
			//外键设置
			entity.setZjhm(hmcxx.getZjhm());
			xydbMapper.insert(entity);
		}
	}

	@Override
	public void updateMainPad(Grdkcjxx cjxx, KhglKhhmcxxGrxd grxd, List<Jtcyxx> jtcyxxList, List<Glqy> glqyList, List<Fwxx> fwxxList, List<Cfxx> cfxxList, List<Clxx> clxxList, List<Qtglzc> qtgdzcList, List<Yhdk> yhdkList, List<Bzdb> bzdbList, List<Dydb> dydbList, List<Zydb> zydbList, List<Xydb> xydbList) {
		String jgdm = iSysDictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION","YWJGDM","ZZBZ",grxd.getSszh());
		LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		Grdkcjxx grdkcjxx = grdkcjxxMapper.selectById(cjxx.getId());
		boolean isTrue = cjxx.getGtjkrzjhm().contains("*");
		if (isTrue) cjxx.setGtjkrzjhm(grdkcjxx.getGtjkrzjhm());

		// 个人贷款:贷前调查 - 个人贷款采集信息
		cjxx.setZjhm(grdkcjxx.getZjhm());
		cjxx.setUpdateBy(user.getUsername());
		cjxx.setUpdateTime(new Date());
		UpdateWrapper<Grdkcjxx> cjxxUpdateWrapper = new UpdateWrapper<>();
		cjxxUpdateWrapper.eq("zjhm", cjxx.getZjhm());
		grdkcjxxMapper.update(cjxx,cjxxUpdateWrapper);

		// 个人贷款:贷前调查 - 个人信贷花名册
		grxd.setZjhm(grdkcjxx.getZjhm());
		grxd.setSszh(grxd.getSszh());
		grxd.setJgdm(jgdm);
		grxd.setXgr(user.getUsername());
		grxd.setXgsj(new Date());
		UpdateWrapper<KhglKhhmcxxGrxd> grxdUpdateWrapper = new UpdateWrapper<>();
		grxdUpdateWrapper.eq("zjhm",grdkcjxx.getZjhm());
		khhmcxxGrxdMapper.update(grxd,grxdUpdateWrapper);

		for (Jtcyxx jtcyxx : jtcyxxList) {
			if (jtcyxx.getId() != null && jtcyxx.getId().length() > 0) {
				Jtcyxx jtcyxx1 = jtcyxxMapper.selectById(jtcyxx.getId());
				jtcyxx.setZjhm(jtcyxx1.getZjhm());
				jtcyxx.setHhbm(cjxx.getHhbm());
				jtcyxx.setSszh(grxd.getSszh());
				jtcyxx.setJgdm(jgdm);
				jtcyxx.setXgr(user.getUsername());
				jtcyxx.setXgsj(new Date());
				Jtcyxx jtcyxx2 = jtcyxxMapper.selectById(jtcyxx.getId());
				if (jtcyxx2 == null) {
					jtcyxxMapper.insert(jtcyxx);
				} else {
					QueryWrapper<Jtcyxx> queryWrapper = new QueryWrapper<>();
					queryWrapper.eq("zjhm", jtcyxx.getZjhm());
					jtcyxxMapper.update(jtcyxx, queryWrapper);
				}
			} else {
				jtcyxx.setHhbm(cjxx.getHhbm());
				jtcyxx.setSszh(grxd.getSszh());
				jtcyxx.setJgdm(jgdm);
				jtcyxx.setXgr(user.getUsername());
				jtcyxx.setXgsj(new Date());
				Jtcyxx jtcyxx2 = jtcyxxMapper.selectById(jtcyxx.getZjhm());
				if (jtcyxx2 == null) {
					jtcyxxMapper.insert(jtcyxx);
				} else {
					QueryWrapper<Jtcyxx> queryWrapper = new QueryWrapper<>();
					queryWrapper.eq("zjhm", jtcyxx.getZjhm());
					jtcyxxMapper.update(jtcyxx, queryWrapper);
				}
			}
		}

		// Step1.先删除子表数据
		glqyMapper.deleteByMainId(cjxx.getZjhm());
		fwxxMapper.deleteByMainId(cjxx.getZjhm());
		cfxxMapper.deleteByMainId(cjxx.getZjhm());
		clxxMapper.deleteByMainId(cjxx.getZjhm());
		qtglzcMapper.deleteByMainId(cjxx.getZjhm());
		yhdkMapper.deleteByMainId(cjxx.getZjhm());
		// bzdbMapper.deleteByMainId(cjxx.getZjhm());
		dydbMapper.deleteByMainId(cjxx.getZjhm());
		zydbMapper.deleteByMainId(cjxx.getZjhm());
		xydbMapper.deleteByMainId(cjxx.getZjhm());

		// Step2.再插入子表数据
		for (Glqy glqy : glqyList) {
			glqy.setKhmc(cjxx.getKhmc());
			glqy.setZjhm(cjxx.getZjhm());
			glqy.setUpdateBy(user.getUsername());
			glqy.setUpdateTime(new Date());
			glqyMapper.insert(glqy);
		}
		for (Fwxx fwxx : fwxxList) {
			fwxx.setKhmc(cjxx.getKhmc());
			fwxx.setZjhm(cjxx.getZjhm());
			fwxx.setUpdateBy(user.getUsername());
			fwxx.setUpdateTime(new Date());
			fwxxMapper.insert(fwxx);
		}
		for (Cfxx cfxx : cfxxList) {
			cfxx.setKhmc(cjxx.getKhmc());
			cfxx.setZjhm(cjxx.getZjhm());
			cfxx.setUpdateBy(user.getUsername());
			cfxx.setUpdateTime(new Date());
			cfxxMapper.insert(cfxx);
		}
		for (Clxx clxx : clxxList) {
			clxx.setKhmc(cjxx.getKhmc());
			clxx.setZjhm(cjxx.getZjhm());
			clxx.setUpdateBy(user.getUsername());
			clxx.setUpdateTime(new Date());
			clxxMapper.insert(clxx);
		}
		for (Qtglzc qtgdzc : qtgdzcList) {
			qtgdzc.setKhmc(cjxx.getKhmc());
			qtgdzc.setZjhm(cjxx.getZjhm());
			qtgdzc.setUpdateBy(user.getUsername());
			qtgdzc.setUpdateTime(new Date());
			qtglzcMapper.insert(qtgdzc);
		}
		for (Yhdk yhdk : yhdkList) {
			yhdk.setKhmc(cjxx.getKhmc());
			yhdk.setZjhm(cjxx.getZjhm());
			yhdk.setUpdateBy(user.getUsername());
			yhdk.setUpdateTime(new Date());
			yhdkMapper.insert(yhdk);
		}
		for (Bzdb bzdb : bzdbList) {
			if (bzdb.getId() != null && bzdb.getId().length() > 0) {
				Bzdb form = bzdbMapper.selectById(bzdb.getId());
				bzdb.setZjhm(cjxx.getZjhm());
				bzdb.setKhmc(cjxx.getKhmc());
				bzdb.setDbrzjhm(form.getDbrzjhm());
				if ("20".equalsIgnoreCase(form.getDbrhyzk())) bzdb.setDbrpozjhm(form.getDbrpozjhm());
				bzdb.setUpdateBy(user.getUsername());
				bzdb.setUpdateTime(new Date());
				Bzdb check = bzdbMapper.selectById(bzdb.getId());
				if (check == null) {
					bzdbMapper.insert(bzdb);
				} else {
					QueryWrapper<Bzdb> queryWrapper = new QueryWrapper<>();
					queryWrapper.eq("id", bzdb.getId());
					bzdbMapper.update(bzdb,queryWrapper);
				}
			} else {
				bzdb.setKhmc(cjxx.getKhmc());
				bzdb.setZjhm(cjxx.getZjhm());
				bzdb.setUpdateBy(user.getUsername());
				bzdb.setUpdateTime(new Date());
				bzdbMapper.insert(bzdb);
			}
		}
		for (Dydb dydb : dydbList) {
			dydb.setKhmc(cjxx.getKhmc());
			dydb.setZjhm(cjxx.getZjhm());
			dydb.setUpdateBy(user.getUsername());
			dydb.setUpdateTime(new Date());
			dydbMapper.insert(dydb);
		}
		for (Zydb zydb : zydbList) {
			zydb.setKhmc(cjxx.getKhmc());
			zydb.setZjhm(cjxx.getZjhm());
			zydb.setUpdateBy(user.getUsername());
			zydb.setUpdateTime(new Date());
			zydbMapper.insert(zydb);
		}
		for (Xydb xydb : xydbList) {
			xydb.setKhmc(cjxx.getKhmc());
			xydb.setZjhm(cjxx.getZjhm());
			xydb.setUpdateBy(user.getUsername());
			xydb.setUpdateTime(new Date());
			xydbMapper.insert(xydb);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		jtcyxxMapper.deleteByMainId(id);
		glqyMapper.deleteByMainId(id);
		fwxxMapper.deleteByMainId(id);
		cfxxMapper.deleteByMainId(id);
		clxxMapper.deleteByMainId(id);
		qtglzcMapper.deleteByMainId(id);
		yhdkMapper.deleteByMainId(id);
		bzdbMapper.deleteByMainId(id);
		dydbMapper.deleteByMainId(id);
		zydbMapper.deleteByMainId(id);
		xydbMapper.deleteByMainId(id);
		grdkglMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			jtcyxxMapper.deleteByMainId(id.toString());
			glqyMapper.deleteByMainId(id.toString());
			fwxxMapper.deleteByMainId(id.toString());
			cfxxMapper.deleteByMainId(id.toString());
			clxxMapper.deleteByMainId(id.toString());
			qtglzcMapper.deleteByMainId(id.toString());
			yhdkMapper.deleteByMainId(id.toString());
			bzdbMapper.deleteByMainId(id.toString());
			dydbMapper.deleteByMainId(id.toString());
			zydbMapper.deleteByMainId(id.toString());
			xydbMapper.deleteByMainId(id.toString());
			grdkglMapper.deleteById(id);
		}
	}

	@Override
	public void CountYwhywxxDataByHhbm(String hhbm) {
		grdkglMapper.CountYwhywxxDataByHhbm(hhbm);
	}
}
