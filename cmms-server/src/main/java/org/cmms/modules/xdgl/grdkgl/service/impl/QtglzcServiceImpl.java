package org.cmms.modules.xdgl.grdkgl.service.impl;

import org.cmms.modules.xdgl.grdkgl.entity.Qtglzc;
import org.cmms.modules.xdgl.grdkgl.mapper.QtglzcMapper;
import org.cmms.modules.xdgl.grdkgl.service.IQtglzcService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 其他固定资产
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Service
public class QtglzcServiceImpl extends ServiceImpl<QtglzcMapper, Qtglzc> implements IQtglzcService {

	@Autowired
	private QtglzcMapper qtgdzcMapper;

	@Override
	public List<Qtglzc> selectByMainId(String mainId) {
		return qtgdzcMapper.selectByMainId(mainId);
	}

	@Override
	public List<Qtglzc> queryOtherFixedAssetsInfoById(String id) {
		return qtgdzcMapper.queryOtherFixedAssetsInfoById(id);
	}

	@Override
	public List<Qtglzc> queryOtherFixedAssetsDataById(String id) {
		return qtgdzcMapper.queryOtherFixedAssetsDataById(id);
	}
}
