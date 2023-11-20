package org.cmms.modules.gzap.jhxf_real.service.impl;

import org.cmms.modules.gzap.jhxf_real.entity.Gzap_jhxf_khjl_real;
import org.cmms.modules.gzap.jhxf_real.mapper.Gzap_jhxf_khjl_realMapper;
import org.cmms.modules.gzap.jhxf_real.service.IGzap_jhxf_khjl_realService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 客户明细
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
@Service
public class Gzap_jhxf_khjl_realServiceImpl extends ServiceImpl<Gzap_jhxf_khjl_realMapper, Gzap_jhxf_khjl_real> implements IGzap_jhxf_khjl_realService {

	@Autowired
	private Gzap_jhxf_khjl_realMapper gzap_jhxf_khjl_realMapper;

	@Override
	public List<Gzap_jhxf_khjl_real> selectByMainId(String mainId) {
		return gzap_jhxf_khjl_realMapper.selectByMainId(mainId);
	}
}
