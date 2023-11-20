package org.cmms.modules.gzap.jhxf_real.service.impl;

import org.cmms.modules.gzap.jhxf_real.entity.Gzap_jhxf_real;
import org.cmms.modules.gzap.jhxf_real.entity.Gzap_jhxf_khjl_real;
import org.cmms.modules.gzap.jhxf_real.mapper.Gzap_jhxf_khjl_realMapper;
import org.cmms.modules.gzap.jhxf_real.mapper.Gzap_jhxf_realMapper;
import org.cmms.modules.gzap.jhxf_real.service.IGzap_jhxf_realService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 计划下发
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
@Service
public class Gzap_jhxf_realServiceImpl extends ServiceImpl<Gzap_jhxf_realMapper, Gzap_jhxf_real> implements IGzap_jhxf_realService {

	@Autowired
	private Gzap_jhxf_realMapper gzap_jhxf_realMapper;
	@Autowired
	private Gzap_jhxf_khjl_realMapper gzap_jhxf_khjl_realMapper;

	@Override
	@Transactional
	public void saveMain(Gzap_jhxf_real gzap_jhxf_real, List<Gzap_jhxf_khjl_real> gzap_jhxf_khjl_realList) {
		gzap_jhxf_realMapper.insert(gzap_jhxf_real);
		for(Gzap_jhxf_khjl_real entity:gzap_jhxf_khjl_realList) {
			//外键设置
			entity.setOrderId(gzap_jhxf_real.getId());
			gzap_jhxf_khjl_realMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(Gzap_jhxf_real gzap_jhxf_real,List<Gzap_jhxf_khjl_real> gzap_jhxf_khjl_realList) {
		gzap_jhxf_realMapper.updateById(gzap_jhxf_real);

		//1.先删除子表数据
		gzap_jhxf_khjl_realMapper.deleteByMainId(gzap_jhxf_real.getId());

		//2.子表数据重新插入
		for(Gzap_jhxf_khjl_real entity:gzap_jhxf_khjl_realList) {
			//外键设置
			entity.setOrderId(gzap_jhxf_real.getId());
			gzap_jhxf_khjl_realMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		gzap_jhxf_khjl_realMapper.deleteByMainId(id);
		gzap_jhxf_realMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			gzap_jhxf_khjl_realMapper.deleteByMainId(id.toString());
			gzap_jhxf_realMapper.deleteById(id);
		}
	}

}
