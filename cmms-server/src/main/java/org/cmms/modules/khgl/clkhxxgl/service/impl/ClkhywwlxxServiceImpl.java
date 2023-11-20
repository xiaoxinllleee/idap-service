package org.cmms.modules.khgl.clkhxxgl.service.impl;

import org.cmms.modules.khgl.clkhxxgl.entity.Clkhywwlxx;
import org.cmms.modules.khgl.clkhxxgl.mapper.ClkhywwlxxMapper;
import org.cmms.modules.khgl.clkhxxgl.service.IClkhywwlxxService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 存量个人客户业务往来信息
 * @Author: cmms
 * @Date:   2019-09-20
 * @Version: V1.0
 */
@Service
public class ClkhywwlxxServiceImpl extends ServiceImpl<ClkhywwlxxMapper, Clkhywwlxx> implements IClkhywwlxxService {

	@Autowired
	private ClkhywwlxxMapper clgrkhywwlxxMapper;

	@Override
	public Clkhywwlxx selectByMainId(String mainId) {
		return clgrkhywwlxxMapper.selectByMainId(mainId);
	}
}
