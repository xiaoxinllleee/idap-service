package org.cmms.modules.dkjkpt.dhgl.dhjcbg.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.entity.DkjkptDhjcbgfjxx;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.mapper.DkjkptDhjcbgfjxxMapper;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.service.IDkjkptDhjcbgfjxxService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 附件信息
 * @Author: cmms
 * @Date:   2019-09-10
 * @Version: V1.0
 */
@Service
public class Dkjkpt_dhjcbgfjxxServiceImpl extends ServiceImpl<DkjkptDhjcbgfjxxMapper, DkjkptDhjcbgfjxx> implements IDkjkptDhjcbgfjxxService {
	
	@Autowired
	private DkjkptDhjcbgfjxxMapper dkjkpt_dhjcbgfjxxMapper;
	
	@Override
	public List<DkjkptDhjcbgfjxx> selectByMainId(String mainId) {
		return dkjkpt_dhjcbgfjxxMapper.selectByMainId(mainId);
	}
}
