package org.cmms.modules.yxgl.khhhmx.service.impl;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtils;
import org.cmms.modules.yxgl.khhhmx.entity.YxglKhhfmxb;
import org.cmms.modules.yxgl.khhhmx.entity.YxglKhhffjxxb;
import org.cmms.modules.yxgl.khhhmx.mapper.YxglKhhffjxxbMapper;
import org.cmms.modules.yxgl.khhhmx.mapper.YxglKhhfmxbMapper;
import org.cmms.modules.yxgl.khhhmx.service.IYxglKhhfmxbService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 客户回访明细
 * @Author: cmms
 * @Date:   2019-12-23
 * @Version: V1.0
 */
@Service
public class YxglKhhfmxbServiceImpl extends ServiceImpl<YxglKhhfmxbMapper, YxglKhhfmxb> implements IYxglKhhfmxbService {

	@Autowired
	private YxglKhhfmxbMapper yxglKhhfmxbMapper;
	@Autowired
	private YxglKhhffjxxbMapper yxglKhhffjxxbMapper;
	@Value(value = "${common.path.upload}")
	private String uploadpath;
	
	@Override
	@Transactional
	public void saveMain(YxglKhhfmxb yxglKhhfmxb, List<YxglKhhffjxxb> yxglKhhffjxxbList) {
		yxglKhhfmxbMapper.insert(yxglKhhfmxb);
		for(YxglKhhffjxxb entity:yxglKhhffjxxbList) {
			//外键设置
			entity.setId(yxglKhhfmxb.getId());
			LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			entity.setScr(loginUser.getUsername());
			String fwlj = entity.getFwlj();
			String fjlj = uploadpath + File.separator + fwlj;
			File file = new File(fjlj);
			entity.setFjmc(file.getName());
			entity.setFjdx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
			entity.setFjlj(fjlj);
			entity.setScsj(DateUtils.getDate());
			yxglKhhffjxxbMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(YxglKhhfmxb yxglKhhfmxb,List<YxglKhhffjxxb> yxglKhhffjxxbList) {
		yxglKhhfmxbMapper.updateById(yxglKhhfmxb);
		
		//1.先删除子表数据
		yxglKhhffjxxbMapper.deleteByMainId(yxglKhhfmxb.getId());

		//2.子表数据重新插入
		for(YxglKhhffjxxb entity:yxglKhhffjxxbList) {
			//外键设置
			entity.setHfId(yxglKhhfmxb.getId());
			LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			entity.setScr(loginUser.getUsername());
			String fwlj = entity.getFwlj();
			String fjlj = uploadpath + File.separator + fwlj;
			File file = new File(fjlj);
			entity.setFjmc(file.getName());
			entity.setFjdx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
			entity.setFjlj(fjlj);
			entity.setScsj(DateUtils.getDate());
			yxglKhhffjxxbMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		yxglKhhffjxxbMapper.deleteByMainId(id);
		yxglKhhfmxbMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			yxglKhhffjxxbMapper.deleteByMainId(id.toString());
			yxglKhhfmxbMapper.deleteById(id);
		}
	}
	
}
