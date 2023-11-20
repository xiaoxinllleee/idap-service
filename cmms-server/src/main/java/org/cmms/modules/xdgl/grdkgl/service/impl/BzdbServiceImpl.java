package org.cmms.modules.xdgl.grdkgl.service.impl;

import org.cmms.modules.xdgl.grdkgl.entity.Bzdb;
import org.cmms.modules.xdgl.grdkgl.mapper.BzdbMapper;
import org.cmms.modules.xdgl.grdkgl.service.IBzdbService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 保存担保
 * @Author: jeecg-boot
 * @Date:   2020-08-18
 * @Version: V1.0
 */
@Service
public class BzdbServiceImpl extends ServiceImpl<BzdbMapper, Bzdb> implements IBzdbService {

	@Autowired
	private BzdbMapper bzdbMapper;

	@Override
	public List<Bzdb> selectByMainId(String mainId) {
		return bzdbMapper.selectByMainId(mainId);
	}

	@Override
	public List<Bzdb> queryGuaranteeInfoById(String id) {
		return bzdbMapper.queryGuaranteeInfoById(id);
	}

	@Override
	public List<Bzdb> queryGuaranteeDataById(String id) {
		return bzdbMapper.queryGuaranteeDataById(id);
	}
}
