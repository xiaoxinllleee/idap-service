package org.cmms.modules.xdgl.grdkgl.service.impl;

import org.cmms.modules.xdgl.grdkgl.entity.Clxx;
import org.cmms.modules.xdgl.grdkgl.mapper.ClxxMapper;
import org.cmms.modules.xdgl.grdkgl.service.IClxxService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 车辆信息
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Service
public class ClxxServiceImpl extends ServiceImpl<ClxxMapper, Clxx> implements IClxxService {

	@Autowired
	private ClxxMapper clxxMapper;

	@Override
	public List<Clxx> selectByMainId(String mainId) {
		return clxxMapper.selectByMainId(mainId);
	}

	@Override
	public List<Clxx> queryVehicleInfoById(String id) {
		return clxxMapper.queryVehicleInfoById(id);
	}

	@Override
	public List<Clxx> queryVehicleDataById(String id) {
		return clxxMapper.queryVehicleDataById(id);
	}
}
