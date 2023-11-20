package org.cmms.modules.khgl.clkhxxgl.service.impl;

import org.cmms.modules.khgl.clkhxxgl.entity.Clgrkhjbxx;
import org.cmms.modules.khgl.clkhxxgl.entity.Clgrkhjtcy;
import org.cmms.modules.khgl.clkhxxgl.entity.Clkhywwlxx;
import org.cmms.modules.khgl.clkhxxgl.entity.Clkhzlxx;
import org.cmms.modules.khgl.clkhxxgl.mapper.ClgrkhjtcyMapper;
import org.cmms.modules.khgl.clkhxxgl.mapper.ClkhzlxxMapper;
import org.cmms.modules.khgl.clkhxxgl.mapper.ClkhywwlxxMapper;
import org.cmms.modules.khgl.clkhxxgl.mapper.ClgrkhjbxxMapper;
import org.cmms.modules.khgl.clkhxxgl.service.IClgrkhjbxxService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 存量个人客户基本信息
 * @Author: cmms
 * @Date:   2019-09-20
 * @Version: V1.0
 */
@Service
public class ClgrkhjbxxServiceImpl extends ServiceImpl<ClgrkhjbxxMapper, Clgrkhjbxx> implements IClgrkhjbxxService {

	@Autowired
	private ClgrkhjbxxMapper clgrkhjbxxMapper;
	@Autowired
	private ClgrkhjtcyMapper clgrkhjtcyMapper;
	@Autowired
	private ClkhzlxxMapper clgrkhzlxxMapper;
	@Autowired
	private ClkhywwlxxMapper clgrkhywwlxxMapper;

	@Override
	@Transactional
	public void saveMain(Clgrkhjbxx clgrkhjbxx, List<Clgrkhjtcy> clgrkhjtcyList, List<Clkhzlxx> clgrkhzlxxList, Clkhywwlxx clgrkhywwlxx) {
		clgrkhjbxxMapper.insert(clgrkhjbxx);
		for(Clgrkhjtcy entity:clgrkhjtcyList) {
			//外键设置
			entity.setKhId(clgrkhjbxx.getId());
			clgrkhjtcyMapper.insert(entity);
		}
		for(Clkhzlxx entity:clgrkhzlxxList) {
			//外键设置
			entity.setKhId(clgrkhjbxx.getId());
			clgrkhzlxxMapper.insert(entity);
		}
		//外键设置
		clgrkhywwlxx.setKhId(clgrkhjbxx.getId());
		clgrkhywwlxxMapper.insert(clgrkhywwlxx);
	}

	@Override
	@Transactional
	public void updateMain(Clgrkhjbxx clgrkhjbxx, List<Clgrkhjtcy> clgrkhjtcyList, List<Clkhzlxx> clgrkhzlxxList, Clkhywwlxx clgrkhywwlxx) {
		clgrkhjbxxMapper.updateById(clgrkhjbxx);

		//1.先删除子表数据
		clgrkhjtcyMapper.deleteByMainId(clgrkhjbxx.getId());
		clgrkhzlxxMapper.deleteByMainId(clgrkhjbxx.getId());
		clgrkhywwlxxMapper.deleteByMainId(clgrkhjbxx.getId());

		//2.子表数据重新插入
		for(Clgrkhjtcy entity:clgrkhjtcyList) {
			//外键设置
			entity.setKhId(clgrkhjbxx.getId());
			clgrkhjtcyMapper.insert(entity);
		}
		for(Clkhzlxx entity:clgrkhzlxxList) {
			//外键设置
			entity.setKhId(clgrkhjbxx.getId());
			clgrkhzlxxMapper.insert(entity);
		}
		//外键设置
		clgrkhywwlxx.setKhId(clgrkhjbxx.getId());
		clgrkhywwlxxMapper.insert(clgrkhywwlxx);
	}

	@Override
	@Transactional
	public void delMain(String id) {
		clgrkhjtcyMapper.deleteByMainId(id);
		clgrkhzlxxMapper.deleteByMainId(id);
		clgrkhywwlxxMapper.deleteByMainId(id);
		clgrkhjbxxMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			clgrkhjtcyMapper.deleteByMainId(id.toString());
			clgrkhzlxxMapper.deleteByMainId(id.toString());
			clgrkhywwlxxMapper.deleteByMainId(id.toString());
			clgrkhjbxxMapper.deleteById(id);
		}
	}

	@Override
	public int syncYesClgrxx() {
		return clgrkhjbxxMapper.syncYesClgrxx();
	}

}
