package org.cmms.modules.xdgl.grdkgl.service.impl;

import org.cmms.modules.xdgl.grdkgl.entity.Xydb;
import org.cmms.modules.xdgl.grdkgl.mapper.XydbMapper;
import org.cmms.modules.xdgl.grdkgl.service.IXydbService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 信用担保
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Service
public class XydbServiceImpl extends ServiceImpl<XydbMapper, Xydb> implements IXydbService {

	@Autowired
	private XydbMapper xydbMapper;

	@Override
	public List<Xydb> selectByMainId(String mainId) {
		return xydbMapper.selectByMainId(mainId);
	}

	@Override
	public List<Xydb> queryCreditGuaranteeInfoById(String id) {
		return xydbMapper.queryCreditGuaranteeInfoById(id);
	}

	@Override
	public List<Xydb> queryCreditGuaranteeDataById(String id) {
		return xydbMapper.queryCreditGuaranteeDataById(id);
	}
}
