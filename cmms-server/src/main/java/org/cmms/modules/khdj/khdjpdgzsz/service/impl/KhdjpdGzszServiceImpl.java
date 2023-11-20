package org.cmms.modules.khdj.khdjpdgzsz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.khdj.khdjpdgzsz.entity.KhdjpdGzszGzxx;
import org.cmms.modules.khdj.khdjpdgzsz.entity.KhdjpdGzsz;
import org.cmms.modules.khdj.khdjpdgzsz.mapper.KhdjpdGzszGzxxMapper;
import org.cmms.modules.khdj.khdjpdgzsz.mapper.KhdjpdGzszMapper;
import org.cmms.modules.khdj.khdjpdgzsz.service.IKhdjpdGzszService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 客户等级规则设置
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
@Service
public class KhdjpdGzszServiceImpl extends ServiceImpl<KhdjpdGzszMapper, KhdjpdGzsz> implements IKhdjpdGzszService {

	@Autowired
	private KhdjpdGzszMapper kHDJ_KHDJPDGZSZ_realMapper;
	@Autowired
	private KhdjpdGzszGzxxMapper gzap_jhxf_khjl_gzxx_realMapper;

	@Override
	@Transactional
	public void saveMain(KhdjpdGzsz kHDJ_KHDJPDGZSZ_real, List<KhdjpdGzszGzxx> gzap_jhxf_khjl_gzxx_realList) {
		kHDJ_KHDJPDGZSZ_realMapper.insert(kHDJ_KHDJPDGZSZ_real);
		for(KhdjpdGzszGzxx entity:gzap_jhxf_khjl_gzxx_realList) {
			//外键设置
			entity.setGzid(kHDJ_KHDJPDGZSZ_real.getId());
			gzap_jhxf_khjl_gzxx_realMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(KhdjpdGzsz kHDJ_KHDJPDGZSZ_real, List<KhdjpdGzszGzxx> gzap_jhxf_khjl_gzxx_realList) {
		kHDJ_KHDJPDGZSZ_realMapper.updateById(kHDJ_KHDJPDGZSZ_real);

		//1.先删除子表数据
		gzap_jhxf_khjl_gzxx_realMapper.deleteByMainId(kHDJ_KHDJPDGZSZ_real.getId());

		//2.子表数据重新插入
		for(KhdjpdGzszGzxx entity:gzap_jhxf_khjl_gzxx_realList) {
			//外键设置
			entity.setGzid(kHDJ_KHDJPDGZSZ_real.getId());
			gzap_jhxf_khjl_gzxx_realMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		gzap_jhxf_khjl_gzxx_realMapper.deleteByMainId(id);
		kHDJ_KHDJPDGZSZ_realMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			gzap_jhxf_khjl_gzxx_realMapper.deleteByMainId(id.toString());
			kHDJ_KHDJPDGZSZ_realMapper.deleteById(id);
		}
	}

}
