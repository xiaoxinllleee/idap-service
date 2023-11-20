package org.cmms.modules.gzap.jhxf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.gzap.jhxf.entity.jhxf_khjl;
import org.cmms.modules.gzap.jhxf.mapper.jhxf_khjlMapper;
import org.cmms.modules.gzap.jhxf.service.Ijhxf_khjlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 计划下发_客户经理
 * @Author: cmms
 * @Date:   2019-09-25
 * @Version: V1.0
 */
@Service
public class jhxf_khjlServiceImpl extends ServiceImpl<jhxf_khjlMapper, jhxf_khjl> implements Ijhxf_khjlService {

	@Autowired
	private jhxf_khjlMapper jhxf_khjlMapper;

	@Override
	public List<jhxf_khjl> selectByMainId(String mainId) {
		return jhxf_khjlMapper.selectByMainId(mainId);
	}
}
