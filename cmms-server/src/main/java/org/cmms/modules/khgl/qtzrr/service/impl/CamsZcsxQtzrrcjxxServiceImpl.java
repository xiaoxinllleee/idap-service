package org.cmms.modules.khgl.qtzrr.service.impl;

import org.cmms.modules.khgl.qtzrr.entity.CamsZcsxQtzrrcjxx;
import org.cmms.modules.khgl.qtzrr.mapper.CamsZcsxQtzrrcjxxMapper;
import org.cmms.modules.khgl.qtzrr.service.ICamsZcsxQtzrrcjxxService;
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
public class CamsZcsxQtzrrcjxxServiceImpl extends ServiceImpl<CamsZcsxQtzrrcjxxMapper, CamsZcsxQtzrrcjxx> implements ICamsZcsxQtzrrcjxxService {
	
	@Autowired
	private CamsZcsxQtzrrcjxxMapper camsZcsxQtzrrcjxxMapper;
	
	@Override
	public List<CamsZcsxQtzrrcjxx> selectByMainId(String mainId) {
		return camsZcsxQtzrrcjxxMapper.selectByMainId(mainId);
	}
}
