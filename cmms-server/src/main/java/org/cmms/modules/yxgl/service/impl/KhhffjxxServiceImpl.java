package org.cmms.modules.yxgl.service.impl;

import org.cmms.modules.yxgl.entity.Khhffjxx;
import org.cmms.modules.yxgl.mapper.KhhffjxxMapper;
import org.cmms.modules.yxgl.service.IKhhffjxxService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 客户回访附件信息
 * @Author: cmms
 * @Date:   2019-09-17
 * @Version: V1.0
 */
@Service
public class KhhffjxxServiceImpl extends ServiceImpl<KhhffjxxMapper, Khhffjxx> implements IKhhffjxxService {
	
	@Autowired
	private KhhffjxxMapper khhffjxxMapper;
	
	@Override
	public List<Khhffjxx> selectByMainId(String mainId) {
		return khhffjxxMapper.selectByMainId(mainId);
	}
}
