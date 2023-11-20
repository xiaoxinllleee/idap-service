package org.cmms.modules.gzap.gzrz.service.impl;

import org.cmms.modules.gzap.gzrz.entity.GzapRzgl;
import org.cmms.modules.gzap.gzrz.entity.GzapRzglKhgh;
import org.cmms.modules.gzap.gzrz.entity.GzapRzglJrjhzj;
import org.cmms.modules.gzap.gzrz.mapper.GzapRzglKhghMapper;
import org.cmms.modules.gzap.gzrz.mapper.GzapRzglJrjhzjMapper;
import org.cmms.modules.gzap.gzrz.mapper.GzapRzglMapper;
import org.cmms.modules.gzap.gzrz.service.IGzapRzglService;
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
 * @Date:   2019-09-08
 * @Version: V1.0
 */
@Service
public class GzapRzglServiceImpl extends ServiceImpl<GzapRzglMapper, GzapRzgl> implements IGzapRzglService {

	@Autowired
	private GzapRzglMapper gzapRzglMapper;
	@Autowired
	private GzapRzglKhghMapper gzapRzglKhghMapper;
	@Autowired
	private GzapRzglJrjhzjMapper gzapRzglJrjhzjMapper;
	
	@Override
	@Transactional
	public void saveMain(GzapRzgl gzapRzgl, List<GzapRzglKhgh> gzapRzglKhghList,List<GzapRzglJrjhzj> gzapRzglJrjhzjList) {
		gzapRzglMapper.insert(gzapRzgl);
		for(GzapRzglKhgh entity:gzapRzglKhghList) {
			//外键设置
			entity.setOrderId(gzapRzgl.getId());
			gzapRzglKhghMapper.insert(entity);
		}
		for(GzapRzglJrjhzj entity:gzapRzglJrjhzjList) {
			//外键设置
			entity.setOrderId(gzapRzgl.getId());
			gzapRzglJrjhzjMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(GzapRzgl gzapRzgl,List<GzapRzglKhgh> gzapRzglKhghList,List<GzapRzglJrjhzj> gzapRzglJrjhzjList) {
		gzapRzglMapper.updateById(gzapRzgl);
		
		//1.先删除子表数据
		gzapRzglKhghMapper.deleteByMainId(gzapRzgl.getId());
		gzapRzglJrjhzjMapper.deleteByMainId(gzapRzgl.getId());
		
		//2.子表数据重新插入
		for(GzapRzglKhgh entity:gzapRzglKhghList) {
			//外键设置
			entity.setOrderId(gzapRzgl.getId());
			gzapRzglKhghMapper.insert(entity);
		}
		for(GzapRzglJrjhzj entity:gzapRzglJrjhzjList) {
			//外键设置
			entity.setOrderId(gzapRzgl.getId());
			gzapRzglJrjhzjMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		gzapRzglKhghMapper.deleteByMainId(id);
		gzapRzglJrjhzjMapper.deleteByMainId(id);
		gzapRzglMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			gzapRzglKhghMapper.deleteByMainId(id.toString());
			gzapRzglJrjhzjMapper.deleteByMainId(id.toString());
			gzapRzglMapper.deleteById(id);
		}
	}
	
}
