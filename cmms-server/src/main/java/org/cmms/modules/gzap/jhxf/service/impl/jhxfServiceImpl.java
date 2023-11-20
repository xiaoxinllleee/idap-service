package org.cmms.modules.gzap.jhxf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.gzap.jhxf.entity.jhxf;
import org.cmms.modules.gzap.jhxf.entity.jhxf_khjl;
import org.cmms.modules.gzap.jhxf.mapper.jhxfMapper;
import org.cmms.modules.gzap.jhxf.mapper.jhxf_khjlMapper;
import org.cmms.modules.gzap.jhxf.service.IjhxfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 计划下发
 * @Author: cmms
 * @Date:   2019-09-25
 * @Version: V1.0
 */
@Service
public class jhxfServiceImpl extends ServiceImpl<jhxfMapper, jhxf> implements IjhxfService {

	@Autowired
	private jhxfMapper jhxfMapper;
	@Autowired
	private jhxf_khjlMapper jhxf_khjlMapper;

	@Override
	@Transactional
	public void saveMain(jhxf jhxf, List<jhxf_khjl> jhxf_khjlList) {
		jhxfMapper.insert(jhxf);
		for(jhxf_khjl entity:jhxf_khjlList) {
			//外键设置
			entity.setOrderId(jhxf.getId());
			jhxf_khjlMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(jhxf jhxf,List<jhxf_khjl> jhxf_khjlList) {
		jhxfMapper.updateById(jhxf);

		//1.先删除子表数据
		jhxf_khjlMapper.deleteByMainId(jhxf.getId());

		//2.子表数据重新插入
		for(jhxf_khjl entity:jhxf_khjlList) {
			//外键设置
			entity.setOrderId(jhxf.getId());
			jhxf_khjlMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		jhxf_khjlMapper.deleteByMainId(id);
		jhxfMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			jhxf_khjlMapper.deleteByMainId(id.toString());
			jhxfMapper.deleteById(id);
		}
	}

}
