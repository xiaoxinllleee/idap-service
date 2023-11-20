package org.cmms.modules.xdgl.grdkgl.service.impl;

import org.cmms.modules.xdgl.grdkgl.entity.Dydb;
import org.cmms.modules.xdgl.grdkgl.mapper.DydbMapper;
import org.cmms.modules.xdgl.grdkgl.service.IDydbService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 抵押担保
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Service
public class DydbServiceImpl extends ServiceImpl<DydbMapper, Dydb> implements IDydbService {

	@Autowired
	private DydbMapper dydbMapper;

	@Override
	public List<Dydb> selectByMainId(String mainId) {
		return dydbMapper.selectByMainId(mainId);
	}

	@Override
	public List<Dydb> queryMortgageGuaranteeInfoById(String id) {
		return dydbMapper.queryMortgageGuaranteeInfoById(id);
	}

	@Override
	public List<Dydb> queryMortgageGuaranteeDataById(String id) {
		return dydbMapper.queryMortgageGuaranteeDataById(id);
	}
}
