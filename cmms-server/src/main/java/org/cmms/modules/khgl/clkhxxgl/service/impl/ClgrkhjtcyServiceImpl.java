package org.cmms.modules.khgl.clkhxxgl.service.impl;

import org.cmms.modules.khgl.clkhxxgl.entity.Clgrkhjtcy;
import org.cmms.modules.khgl.clkhxxgl.mapper.ClgrkhjtcyMapper;
import org.cmms.modules.khgl.clkhxxgl.service.IClgrkhjtcyService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 存量个人客户家庭成员
 * @Author: cmms
 * @Date:   2019-09-20
 * @Version: V1.0
 */
@Service
public class ClgrkhjtcyServiceImpl extends ServiceImpl<ClgrkhjtcyMapper, Clgrkhjtcy> implements IClgrkhjtcyService {

	@Autowired
	private ClgrkhjtcyMapper clgrkhjtcyMapper;

	@Override
	public List<Clgrkhjtcy> selectByMainId(String mainId) {
		return clgrkhjtcyMapper.selectByMainId(mainId);
	}
}
