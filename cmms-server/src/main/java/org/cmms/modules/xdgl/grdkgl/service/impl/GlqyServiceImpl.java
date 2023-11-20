package org.cmms.modules.xdgl.grdkgl.service.impl;

import org.cmms.modules.xdgl.grdkgl.entity.Glqy;
import org.cmms.modules.xdgl.grdkgl.mapper.GlqyMapper;
import org.cmms.modules.xdgl.grdkgl.service.IGlqyService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 关联企业
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Service
public class GlqyServiceImpl extends ServiceImpl<GlqyMapper, Glqy> implements IGlqyService {

	@Autowired
	private GlqyMapper glqyMapper;

	@Override
	public List<Glqy> selectByMainId(String mainId) {
		return glqyMapper.selectByMainId(mainId);
	}

	@Override
	public List<Glqy> queryCompanyListById(String id) {
		return glqyMapper.queryCompanyListById(id);
	}

	@Override
	public List<Glqy> queryCompanyDataById(String id) {
		return glqyMapper.queryCompanyDataById(id);
	}
}
