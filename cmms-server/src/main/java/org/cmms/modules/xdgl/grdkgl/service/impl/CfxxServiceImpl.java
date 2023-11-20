package org.cmms.modules.xdgl.grdkgl.service.impl;

import org.cmms.modules.xdgl.grdkgl.entity.Cfxx;
import org.cmms.modules.xdgl.grdkgl.mapper.CfxxMapper;
import org.cmms.modules.xdgl.grdkgl.service.ICfxxService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 厂房信息
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Service
public class CfxxServiceImpl extends ServiceImpl<CfxxMapper, Cfxx> implements ICfxxService {

	@Autowired
	private CfxxMapper cfxxMapper;

	@Override
	public List<Cfxx> selectByMainId(String mainId) {
		return cfxxMapper.selectByMainId(mainId);
	}

	@Override
	public List<Cfxx> queryFactoryInfoById(String id) {
		return cfxxMapper.queryFactoryInfoById(id);
	}

	@Override
	public List<Cfxx> queryFactoryDataById(String id) {
		return cfxxMapper.queryFactoryDataById(id);
	}
}
