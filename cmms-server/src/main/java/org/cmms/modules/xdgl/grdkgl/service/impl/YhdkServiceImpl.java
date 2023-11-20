package org.cmms.modules.xdgl.grdkgl.service.impl;

import org.cmms.modules.xdgl.grdkgl.entity.Yhdk;
import org.cmms.modules.xdgl.grdkgl.mapper.YhdkMapper;
import org.cmms.modules.xdgl.grdkgl.service.IYhdkService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 银行贷款
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Service
public class YhdkServiceImpl extends ServiceImpl<YhdkMapper, Yhdk> implements IYhdkService {

	@Autowired
	private YhdkMapper yhdkMapper;

	@Override
	public List<Yhdk> selectByMainId(String mainId) {
		return yhdkMapper.selectByMainId(mainId);
	}

	@Override
	public List<Yhdk> queryBankLoadInfoById(String id) {
		return yhdkMapper.queryBankLoadInfoById(id);
	}

	@Override
	public List<Yhdk> queryBankLoadDataById(String id) {
		return yhdkMapper.queryBankLoadDataById(id);
	}
}
