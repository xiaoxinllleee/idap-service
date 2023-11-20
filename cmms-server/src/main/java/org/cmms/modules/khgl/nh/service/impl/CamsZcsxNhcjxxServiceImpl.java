package org.cmms.modules.khgl.nh.service.impl;

import org.cmms.modules.khgl.nh.entity.CamsZcsxNhcjxx;
import org.cmms.modules.khgl.nh.mapper.CamsZcsxNhcjxxMapper;
import org.cmms.modules.khgl.nh.service.ICamsZcsxNhcjxxService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 农户采集信息表
 * @Author: jeecg-boot
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@Service
public class CamsZcsxNhcjxxServiceImpl extends ServiceImpl<CamsZcsxNhcjxxMapper, CamsZcsxNhcjxx> implements ICamsZcsxNhcjxxService {
	
	@Autowired
	private CamsZcsxNhcjxxMapper camsZcsxNhcjxxMapper;
	
	@Override
	public List<CamsZcsxNhcjxx> selectByMainId(String mainId) {
		return camsZcsxNhcjxxMapper.selectByMainId(mainId);
	}
}
