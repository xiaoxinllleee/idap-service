package org.cmms.modules.gzap.rwxf.service.impl;

import org.cmms.modules.gzap.rwxf.entity.GzapRwxfJxz;
import org.cmms.modules.gzap.rwxf.entity.GzapRwxf_Rwgl;
import org.cmms.modules.gzap.rwxf.mapper.GzapRwxf_RwglMapper;
import org.cmms.modules.gzap.rwxf.mapper.GzapRwxfJxzMapper;
import org.cmms.modules.gzap.rwxf.service.IGzapRwxfJxzService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 工作日志
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
@Service
public class GzapRwxfJxzServiceImpl extends ServiceImpl<GzapRwxfJxzMapper, GzapRwxfJxz> implements IGzapRwxfJxzService {

	@Autowired
	private GzapRwxfJxzMapper gzapRwxfJxzMapper;
	@Autowired
	private GzapRwxf_RwglMapper gzapRwxf_RwglMapper;
	
	@Override
	@Transactional
	public void saveMain(GzapRwxfJxz gzapRwxfJxz, List<GzapRwxf_Rwgl> gzapRwxf_RwglList) {
		gzapRwxfJxzMapper.insert(gzapRwxfJxz);
		for(GzapRwxf_Rwgl entity:gzapRwxf_RwglList) {
			//外键设置
			entity.setOrderId(gzapRwxfJxz.getId());
			gzapRwxf_RwglMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(GzapRwxfJxz gzapRwxfJxz,List<GzapRwxf_Rwgl> gzapRwxf_RwglList) {
		gzapRwxfJxzMapper.updateById(gzapRwxfJxz);
		
		//1.先删除子表数据
		gzapRwxf_RwglMapper.deleteByMainId(gzapRwxfJxz.getId());
		
		//2.子表数据重新插入
		for(GzapRwxf_Rwgl entity:gzapRwxf_RwglList) {
			//外键设置
			entity.setOrderId(gzapRwxfJxz.getId());
			gzapRwxf_RwglMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		gzapRwxf_RwglMapper.deleteByMainId(id);
		gzapRwxfJxzMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			gzapRwxf_RwglMapper.deleteByMainId(id.toString());
			gzapRwxfJxzMapper.deleteById(id);
		}
	}
	
}
