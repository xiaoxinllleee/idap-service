package org.cmms.modules.khpjsx.pjzxmsz.service.impl;

import org.cmms.modules.khpjsx.pjzxmsz.entity.pjsxPjzxmsz;
import org.cmms.modules.khpjsx.pjzxmsz.entity.pjsxPjzxmgzszXl;
import org.cmms.modules.khpjsx.pjzxmsz.entity.pjsxPjzxmgzszQj;
import org.cmms.modules.khpjsx.pjzxmsz.entity.PJSX_PJZXMGZSZ_GS;
import org.cmms.modules.khpjsx.pjzxmsz.mapper.pjsxPjzxmgzszXlMapper;
import org.cmms.modules.khpjsx.pjzxmsz.mapper.pjsxPjzxmgzszQjMapper;
import org.cmms.modules.khpjsx.pjzxmsz.mapper.PJSX_PJZXMGZSZ_GSMapper;
import org.cmms.modules.khpjsx.pjzxmsz.mapper.pjsxPjzxmszMapper;
import org.cmms.modules.khpjsx.pjzxmsz.service.IpjsxPjzxmszService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 评级子项目设置
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Service
public class pjsxPjzxmszServiceImpl extends ServiceImpl<pjsxPjzxmszMapper, pjsxPjzxmsz> implements IpjsxPjzxmszService {

	@Autowired
	private pjsxPjzxmszMapper pjsxPjzxmszMapper;
	@Autowired
	private pjsxPjzxmgzszXlMapper pjsxPjzxmgzszXlMapper;
	@Autowired
	private pjsxPjzxmgzszQjMapper pjsxPjzxmgzszQjMapper;
	@Autowired
	private PJSX_PJZXMGZSZ_GSMapper PJSX_PJZXMGZSZ_GSMapper;

	@Override
	@Transactional
	public void saveMain(pjsxPjzxmsz pjsxPjzxmsz, List<pjsxPjzxmgzszXl> pjsxPjzxmgzszXlList,List<pjsxPjzxmgzszQj> pjsxPjzxmgzszQjList,List<PJSX_PJZXMGZSZ_GS> PJSX_PJZXMGZSZ_GSList) {
		pjsxPjzxmszMapper.insert(pjsxPjzxmsz);
		for(pjsxPjzxmgzszXl entity:pjsxPjzxmgzszXlList) {
			//外键设置
			entity.setXmbhId(pjsxPjzxmsz.getXmbh());
			pjsxPjzxmgzszXlMapper.insert(entity);
		}
		for(pjsxPjzxmgzszQj entity:pjsxPjzxmgzszQjList) {
			//外键设置
			entity.setXmbhId(pjsxPjzxmsz.getXmbh());
			pjsxPjzxmgzszQjMapper.insert(entity);
		}
		for(PJSX_PJZXMGZSZ_GS entity:PJSX_PJZXMGZSZ_GSList) {
			//外键设置
			entity.setXmbhId(pjsxPjzxmsz.getXmbh());
			PJSX_PJZXMGZSZ_GSMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(pjsxPjzxmsz pjsxPjzxmsz,List<pjsxPjzxmgzszXl> pjsxPjzxmgzszXlList,List<pjsxPjzxmgzszQj> pjsxPjzxmgzszQjList,List<PJSX_PJZXMGZSZ_GS> PJSX_PJZXMGZSZ_GSList) {
		pjsxPjzxmszMapper.updateById(pjsxPjzxmsz);

		//1.先删除子表数据
		pjsxPjzxmgzszXlMapper.deleteByMainId(pjsxPjzxmsz.getXmbh(),pjsxPjzxmsz.getKhlx());
		pjsxPjzxmgzszQjMapper.deleteByMainId(pjsxPjzxmsz.getXmbh(),pjsxPjzxmsz.getKhlx());
		PJSX_PJZXMGZSZ_GSMapper.deleteByMainId(pjsxPjzxmsz.getXmbh(),pjsxPjzxmsz.getKhlx());

		//2.子表数据重新插入
		for(pjsxPjzxmgzszXl entity:pjsxPjzxmgzszXlList) {
			//外键设置
			entity.setXmbhId(pjsxPjzxmsz.getXmbh());
			pjsxPjzxmgzszXlMapper.insert(entity);
		}
		for(pjsxPjzxmgzszQj entity:pjsxPjzxmgzszQjList) {
			//外键设置
			entity.setXmbhId(pjsxPjzxmsz.getXmbh());
			pjsxPjzxmgzszQjMapper.insert(entity);
		}
		for(PJSX_PJZXMGZSZ_GS entity:PJSX_PJZXMGZSZ_GSList) {
			//外键设置
			entity.setXmbhId(pjsxPjzxmsz.getXmbh());
			PJSX_PJZXMGZSZ_GSMapper.insert(entity);
		}
	}

	@Override
	public void delMain(String id) {

	}


	@Transactional
	public void delMain(String id,String khlx) {
		pjsxPjzxmgzszXlMapper.deleteByMainId(id,khlx);
		pjsxPjzxmgzszQjMapper.deleteByMainId(id,khlx);
		PJSX_PJZXMGZSZ_GSMapper.deleteByMainId(id,khlx);
		pjsxPjzxmszMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
	/*	for(Serializable id:idList) {
			pjsxPjzxmgzszXlMapper.deleteByMainId(id.toString());
			pjsxPjzxmgzszQjMapper.deleteByMainId(id.toString());
			PJSX_PJZXMGZSZ_GSMapper.deleteByMainId(id.toString());
			pjsxPjzxmszMapper.deleteById(id);
		}*/
	}
	@Override
	@Transactional
	public boolean insertXLInfoBatch(List<pjsxPjzxmgzszXl> pjsxPjzxmszs){
		try {
			pjsxPjzxmszMapper.insertXLInfoBatch(pjsxPjzxmszs);
			return  true;
		}catch (Throwable e){
			return false;
		}

	};
}
