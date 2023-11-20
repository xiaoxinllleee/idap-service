package org.cmms.modules.xddagl.dkdagl.dhjczllr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.xddagl.dkdagl.dhjczllr.entity.Dhjcbgfjxx;
import org.cmms.modules.xddagl.dkdagl.dhjczllr.mapper.DhjcbgfjxxMapper;
import org.cmms.modules.xddagl.dkdagl.dhjczllr.service.IDhjcbgfjxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 附件信息
 * @Author: cmms
 * @Date:   2019-09-10
 * @Version: V1.0
 */
@Service
public class DhjcbgfjxxServiceImpl extends ServiceImpl<DhjcbgfjxxMapper, Dhjcbgfjxx> implements IDhjcbgfjxxService {
	
	@Autowired
	private DhjcbgfjxxMapper dkjkptDhjcbgfjxxMapper;
	
	@Override
	public List<Dhjcbgfjxx> selectByMainId(String mainId) {
		return dkjkptDhjcbgfjxxMapper.selectByMainId(mainId);
	}
}
