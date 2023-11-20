package org.cmms.modules.khgl.nh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.shiro.crypto.hash.Hash;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.modules.khgl.nh.entity.*;
import org.cmms.modules.khgl.nh.mapper.*;
import org.cmms.modules.khgl.nh.service.IKhglKhhmcxxService;
import org.cmms.modules.system.service.ISysDictService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.*;

/**
 * @Description: 客户花名册
 * @Author: jeecg-boot
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@Service
public class KhglKhhmcxxServiceImpl extends ServiceImpl<KhglKhhmcxxMapper, KhglKhhmcxx> implements IKhglKhhmcxxService {

	@Autowired
	private KhglKhhmcxxMapper khglKhhmcxxMapper;
	@Autowired
	private CamsZcsxNhcjxxMapper camsZcsxNhcjxxMapper;
	@Autowired
	private NhfcxxMapper nhfcxxMapper;
	@Autowired
	private YwhywwlxxMapper ywhywwlxxMapper;
	@Autowired
	private NhPjsxxxMapper nhPjsxxxMapper;
	@Autowired
	private FjglMapper fjglMapper;
	@Autowired
	private NhbkbpyMapper nhbkbpyMapper;
	@Autowired
	private ISysDictService iSysDictService;




	@Override
	@Transactional
	public void saveMain(KhglKhhmcxx khglKhhmcxx, List<CamsZcsxNhcjxx> camsZcsxNhcjxxList, List<Nhfcxx> nhfcxxList, List<Ywhywwlxx> ywhywwlxxList, List<NhPjsxxx> nhPjsxxxList, List<Fjgl> fjglList){
		String qydm = iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101001");

		String id = UUIDGenerator.generate();
		khglKhhmcxx.setId(id);
		khglKhhmcxx.setKhlx("1");
		khglKhhmcxxMapper.insert(khglKhhmcxx);
		for(CamsZcsxNhcjxx entity:camsZcsxNhcjxxList) {
			//外键设置
			entity.setId(id);
			entity.setZjhm(khglKhhmcxx.getZjhm());
			camsZcsxNhcjxxMapper.insert(entity);
		}
		for (Ywhywwlxx ywhywwlxx : ywhywwlxxList) {
			ywhywwlxx.setHmcId(id);
			ywhywwlxx.setZjhm(khglKhhmcxx.getZjhm());
			ywhywwlxxMapper.insert(ywhywwlxx);
		}
		for(Nhfcxx entity:nhfcxxList) {
			//外键设置
			entity.setZjhm(khglKhhmcxx.getZjhm());
			entity.setQydm(qydm);
			nhfcxxMapper.insert(entity);
		}
		for(NhPjsxxx entity:nhPjsxxxList) {
			//外键设置
			entity.setZjhm(khglKhhmcxx.getZjhm());
			entity.setQydm(qydm);
			nhPjsxxxMapper.insert(entity);
		}
		for(Fjgl entity:fjglList) {
			//外键设置
			entity.setZjhm(khglKhhmcxx.getZjhm());
			entity.setQydm(qydm);
			fjglMapper.insert(entity);
		}
	}

	/**
	 * 修改一对多
	 *
	 */
	@Override
	@Transactional
	public void updateMain(KhglKhhmcxx khglKhhmcxx, List<CamsZcsxNhcjxx> camsZcsxNhcjxxList,List<Nhfcxx> nhfcxxList, List<Ywhywwlxx> ywhywwlxxList, List<NhPjsxxx> nhPjsxxxList, List<Fjgl> fjglList){
		UpdateWrapper<KhglKhhmcxx> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("zjhm",khglKhhmcxx.getZjhm());
		khglKhhmcxxMapper.update(khglKhhmcxx,updateWrapper);
		

		//2.修改子表数据
		for(CamsZcsxNhcjxx entity:camsZcsxNhcjxxList) {
			//外键设置
			/*CamsZcsxNhcjxx  camsZcsxNhcjxx = new CamsZcsxNhcjxx();
			BeanUtils.copyProperties(entity,camsZcsxNhcjxx);*/
			UpdateWrapper<CamsZcsxNhcjxx> updateWrapper1 = new UpdateWrapper<>();
			updateWrapper1.eq("zjhm",khglKhhmcxx.getZjhm());
			camsZcsxNhcjxxMapper.update(entity,updateWrapper1);
		}
		for (Ywhywwlxx ywhywwlxx : ywhywwlxxList) {
			ywhywwlxx.setHmcId(khglKhhmcxx.getId());
			UpdateWrapper<Ywhywwlxx> updateWrapper1 = new UpdateWrapper<>();
			updateWrapper1.eq("zjhm",khglKhhmcxx.getZjhm()).eq("hmc_id",khglKhhmcxx.getId());
			ywhywwlxxMapper.update(ywhywwlxx,updateWrapper1);

		}

		//1.先删除子表数据
		nhfcxxMapper.deleteByMainId(khglKhhmcxx.getZjhm());
		//2.子表数据重新插入
		for(Nhfcxx entity:nhfcxxList) {
			//外键设置
			entity.setZjhm(khglKhhmcxx.getZjhm());
			nhfcxxMapper.insert(entity);
		}


		for(NhPjsxxx entity:nhPjsxxxList) {
			//外键设置
			entity.setZjhm(khglKhhmcxx.getZjhm());
			UpdateWrapper<NhPjsxxx> updateWrapper1 = new UpdateWrapper<>();
			updateWrapper1.eq("zjhm",khglKhhmcxx.getZjhm());
			nhPjsxxxMapper.update(entity,updateWrapper1);
		}
		for(Fjgl entity:fjglList) {
			//外键设置
			entity.setZjhm(khglKhhmcxx.getZjhm());
			fjglMapper.insert(entity);
		}

	}

	@Override
	@Transactional
	public void delMain(String zjhm) {
		khglKhhmcxxMapper.deleteByMainId(zjhm);
		camsZcsxNhcjxxMapper.deleteByMainId(zjhm);
		nhfcxxMapper.deleteByMainId(zjhm);
		/*KhglKhhmcxx khhmcxx = new KhglKhhmcxx();
		khhmcxx.setZjhm(zjhm);
		Map<String,String[]> map = new HashMap<>();
		QueryWrapper<KhglKhhmcxx> queryWrapper = QueryGenerator.initQueryWrapper(khhmcxx,map);
		khglKhhmcxxMapper*/

		ywhywwlxxMapper.deleteByMainId(zjhm);
		nhPjsxxxMapper.deleteByMainId(zjhm);
		fjglMapper.deleteByMainId(zjhm);
		nhbkbpyMapper.deleteByMainId(zjhm);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			camsZcsxNhcjxxMapper.deleteByMainId(id.toString());
			nhfcxxMapper.deleteByMainId(id.toString());
			ywhywwlxxMapper.deleteByMainId(id.toString());
			nhPjsxxxMapper.deleteByMainId(id.toString());
			fjglMapper.deleteByMainId(id.toString());
			nhbkbpyMapper.deleteByMainId(id.toString());
		}
	}
	
}
