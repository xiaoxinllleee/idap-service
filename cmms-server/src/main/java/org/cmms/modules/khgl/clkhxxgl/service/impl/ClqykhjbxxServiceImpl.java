package org.cmms.modules.khgl.clkhxxgl.service.impl;

import org.cmms.modules.khgl.clkhxxgl.entity.Clqykhjbxx;
import org.cmms.modules.khgl.clkhxxgl.entity.Clkhzlxx;
import org.cmms.modules.khgl.clkhxxgl.entity.Clkhywwlxx;
import org.cmms.modules.khgl.clkhxxgl.mapper.ClkhzlxxMapper;
import org.cmms.modules.khgl.clkhxxgl.mapper.ClkhywwlxxMapper;
import org.cmms.modules.khgl.clkhxxgl.mapper.ClqykhjbxxMapper;
import org.cmms.modules.khgl.clkhxxgl.service.IClqykhjbxxService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 存量企业客户基本信息
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
@Service
public class ClqykhjbxxServiceImpl extends ServiceImpl<ClqykhjbxxMapper, Clqykhjbxx> implements IClqykhjbxxService {

	@Autowired
	private ClqykhjbxxMapper clqykhjbxxMapper;
	@Autowired
	private ClkhzlxxMapper clkhzlxxMapper;
	@Autowired
	private ClkhywwlxxMapper clkhywwlxxMapper;

	@Override
	@Transactional
	public void saveMain(Clqykhjbxx clqykhjbxx, List<Clkhzlxx> clkhzlxxList,Clkhywwlxx clqykhywwlxx) {
		clqykhjbxxMapper.insert(clqykhjbxx);
		for(Clkhzlxx entity:clkhzlxxList) {
			//外键设置
			entity.setKhId(clqykhjbxx.getId());
			clkhzlxxMapper.insert(entity);
		}
		//外键设置
		clqykhywwlxx.setKhId(clqykhjbxx.getId());
		clkhywwlxxMapper.insert(clqykhywwlxx);
	}

	@Override
	@Transactional
	public void updateMain(Clqykhjbxx clqykhjbxx,List<Clkhzlxx> clkhzlxxList,Clkhywwlxx clqykhywwlxx) {
		clqykhjbxxMapper.updateById(clqykhjbxx);

		//1.先删除子表数据
		clkhzlxxMapper.deleteByMainId(clqykhjbxx.getId());
		clkhywwlxxMapper.deleteByMainId(clqykhjbxx.getId());

		//2.子表数据重新插入
		for(Clkhzlxx entity:clkhzlxxList) {
			//外键设置
			entity.setKhId(clqykhjbxx.getId());
			clkhzlxxMapper.insert(entity);
		}
		//外键设置
        clqykhywwlxx.setKhId(clqykhjbxx.getId());
		clkhywwlxxMapper.insert(clqykhywwlxx);
	}

	@Override
	@Transactional
	public void delMain(String id) {
		clkhzlxxMapper.deleteByMainId(id);
		clkhywwlxxMapper.deleteByMainId(id);
		clqykhjbxxMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			clkhzlxxMapper.deleteByMainId(id.toString());
			clkhywwlxxMapper.deleteByMainId(id.toString());
			clqykhjbxxMapper.deleteById(id);
		}
	}

}
