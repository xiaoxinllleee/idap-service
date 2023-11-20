package org.cmms.modules.khpjsx.pjzxmsz.service.impl;

import org.cmms.modules.khpjsx.pjzxmsz.entity.pjsxPjzxmgzszXl;
import org.cmms.modules.khpjsx.pjzxmsz.mapper.pjsxPjzxmgzszXlMapper;
import org.cmms.modules.khpjsx.pjzxmsz.service.IpjsxPjzxmgzszXlService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 与我行往来情况
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Service
public class pjsxPjzxmgzszXlServiceImpl extends ServiceImpl<pjsxPjzxmgzszXlMapper, pjsxPjzxmgzszXl> implements IpjsxPjzxmgzszXlService {

	@Autowired
	private pjsxPjzxmgzszXlMapper pjsxPjzxmgzszXlMapper;

	@Override
	public List<pjsxPjzxmgzszXl> selectByMainId(String mainId,String khlx) {
		return pjsxPjzxmgzszXlMapper.selectByMainId(mainId,khlx);
	}
}
