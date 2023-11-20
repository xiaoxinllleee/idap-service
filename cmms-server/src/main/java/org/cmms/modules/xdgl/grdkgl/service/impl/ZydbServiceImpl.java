package org.cmms.modules.xdgl.grdkgl.service.impl;

import org.cmms.modules.xdgl.grdkgl.entity.Zydb;
import org.cmms.modules.xdgl.grdkgl.mapper.ZydbMapper;
import org.cmms.modules.xdgl.grdkgl.service.IZydbService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 质押担保
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Service
public class ZydbServiceImpl extends ServiceImpl<ZydbMapper, Zydb> implements IZydbService {

	@Autowired
	private ZydbMapper zydbMapper;

	@Override
	public List<Zydb> selectByMainId(String mainId) {
		return zydbMapper.selectByMainId(mainId);
	}

	@Override
	public List<Zydb> queryPledgeGuaranteeInfoById(String id) {
		return zydbMapper.queryPledgeGuaranteeInfoById(id);
	}

	@Override
	public List<Zydb> queryPledgeGuaranteeDataById(String id) {
		return zydbMapper.queryPledgeGuaranteeDataById(id);
	}
}
