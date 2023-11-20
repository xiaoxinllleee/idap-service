package org.cmms.modules.khpjsx.pjzxmsz.service.impl;

import org.cmms.modules.khpjsx.pjzxmsz.entity.pjsxPjzxmgzszQj;
import org.cmms.modules.khpjsx.pjzxmsz.mapper.pjsxPjzxmgzszQjMapper;
import org.cmms.modules.khpjsx.pjzxmsz.service.IpjsxPjzxmgzszQjService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 金融业务信息
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Service
public class pjsxPjzxmgzszQjServiceImpl extends ServiceImpl<pjsxPjzxmgzszQjMapper, pjsxPjzxmgzszQj> implements IpjsxPjzxmgzszQjService {

	@Autowired
	private pjsxPjzxmgzszQjMapper pjsxPjzxmgzszQjMapper;

	@Override
	public List<pjsxPjzxmgzszQj> selectByMainId(String mainId,String khlx) {
		return pjsxPjzxmgzszQjMapper.selectByMainId(mainId,khlx);
	}
}
