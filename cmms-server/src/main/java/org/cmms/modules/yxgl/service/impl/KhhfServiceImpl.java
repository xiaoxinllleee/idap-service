package org.cmms.modules.yxgl.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtils;
import org.cmms.modules.yxgl.entity.Khhf;
import org.cmms.modules.yxgl.entity.Khhffjxx;
import org.cmms.modules.yxgl.mapper.KhhffjxxMapper;
import org.cmms.modules.yxgl.mapper.KhhfMapper;
import org.cmms.modules.yxgl.service.IKhhfService;
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
 * @Description: 客户回访
 * @Author: cmms
 * @Date:   2019-09-17
 * @Version: V1.0
 */
@Service
public class KhhfServiceImpl extends ServiceImpl<KhhfMapper, Khhf> implements IKhhfService {

	@Autowired
	private KhhfMapper khhfMapper;
	@Autowired
	private KhhffjxxMapper khhffjxxMapper;
	@Value(value = "${common.path.upload}")
	private String uploadpath;

	@Override
	@Transactional
	public void saveMain(Khhf khhf, List<Khhffjxx> khhffjxxList) {
		khhfMapper.insert(khhf);
		if (khhffjxxList != null) {
			for(Khhffjxx entity:khhffjxxList) {
				//外键设置
				entity.setHfId(khhf.getId());
				LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
				entity.setScr(loginUser.getUsername());
				String fwlj = entity.getFwlj();
				String fjlj = uploadpath + File.separator + fwlj;
				File file = new File(fjlj);
				entity.setFjmc(file.getName());
				entity.setFjdx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
				entity.setFjlj(fjlj);
				entity.setScsj(DateUtils.getDate());
				khhffjxxMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(Khhf khhf,List<Khhffjxx> khhffjxxList) {
		khhfMapper.updateById(khhf);

		//1.先删除子表数据
//		khhffjxxMapper.deleteByMainId(khhf.getId());

		//2.子表数据重新插入
		for(Khhffjxx entity:khhffjxxList) {
			if (StringUtils.isEmpty(entity.getId())) {
				//新增的图片
				//外键设置
				entity.setHfId(khhf.getId());
				LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
				entity.setScr(loginUser.getUsername());
				String fwlj = entity.getFwlj();
				String fjlj = uploadpath + File.separator + fwlj;
				File file = new File(fjlj);
				entity.setFjmc(file.getName());
				entity.setFjdx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
				entity.setFjlj(fjlj);
				entity.setScsj(DateUtils.getDate());
				khhffjxxMapper.insert(entity);
			} else {
				//删除
				if (entity.getLrbz().equalsIgnoreCase("3")) {
					khhffjxxMapper.deleteById(entity.getId());
				}
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		khhffjxxMapper.deleteByMainId(id);
		khhfMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			khhffjxxMapper.deleteByMainId(id.toString());
			khhfMapper.deleteById(id);
		}
	}

	@Override
	@Transactional
	public List<Khhf> selectByZjhm(String zjhm) {
		return khhfMapper.selectByZjhm(zjhm);
	}

}
