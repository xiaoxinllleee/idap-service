package org.cmms.modules.xdgl.grdkgl.service.impl;

import org.cmms.modules.xdgl.grdkgl.entity.CamsZcsxFw;
import org.cmms.modules.xdgl.grdkgl.entity.Fwxx;
import org.cmms.modules.xdgl.grdkgl.mapper.CamsZcsxFwMapper;
import org.cmms.modules.xdgl.grdkgl.mapper.FwxxMapper;
import org.cmms.modules.xdgl.grdkgl.service.IFwxxService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 房产信息
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Service
public class FwxxServiceImpl extends ServiceImpl<FwxxMapper, Fwxx> implements IFwxxService {

	@Autowired
	private FwxxMapper fwxxMapper;

	@Override
	public List<Fwxx> selectByMainId(String mainId) {
		return fwxxMapper.selectByMainId(mainId);
	}

	@Override
	public List<Fwxx> queryHouseInfoById(String id) {
		return fwxxMapper.queryHouseInfoById(id);
	}

	@Override
	public List<Fwxx> queryHouseDataById(String id) {
		return fwxxMapper.queryHouseDataById(id);
	}
}
