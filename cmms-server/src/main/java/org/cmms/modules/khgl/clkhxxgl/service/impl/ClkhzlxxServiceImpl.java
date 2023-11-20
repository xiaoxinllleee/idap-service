package org.cmms.modules.khgl.clkhxxgl.service.impl;

import org.cmms.modules.khgl.clkhxxgl.entity.Clkhzlxx;
import org.cmms.modules.khgl.clkhxxgl.mapper.ClkhzlxxMapper;
import org.cmms.modules.khgl.clkhxxgl.service.IClkhzlxxService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 存量个人客户资料信息
 * @Author: cmms
 * @Date:   2019-09-20
 * @Version: V1.0
 */
@Service
public class ClkhzlxxServiceImpl extends ServiceImpl<ClkhzlxxMapper, Clkhzlxx> implements IClkhzlxxService {

	@Autowired
	private ClkhzlxxMapper clgrkhzlxxMapper;

	@Override
	public List<Clkhzlxx> selectByMainId(String mainId) {
		return clgrkhzlxxMapper.selectByMainId(mainId);
	}
}
