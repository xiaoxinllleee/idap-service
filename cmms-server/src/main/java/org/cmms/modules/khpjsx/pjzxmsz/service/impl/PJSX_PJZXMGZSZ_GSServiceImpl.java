package org.cmms.modules.khpjsx.pjzxmsz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.khpjsx.pjzxmsz.entity.PJSX_PJZXMGZSZ_GS;
import org.cmms.modules.khpjsx.pjzxmsz.mapper.PJSX_PJZXMGZSZ_GSMapper;
import org.cmms.modules.khpjsx.pjzxmsz.service.IPJSX_PJZXMGZSZ_GSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 金融业务信息
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Service
public class PJSX_PJZXMGZSZ_GSServiceImpl extends ServiceImpl<PJSX_PJZXMGZSZ_GSMapper, PJSX_PJZXMGZSZ_GS> implements IPJSX_PJZXMGZSZ_GSService {

	@Autowired
	private PJSX_PJZXMGZSZ_GSMapper pjsxPjzxmgzszGsMappe1r;

	@Override
	public List<PJSX_PJZXMGZSZ_GS> selectByMainId(String mainId,String khlx) {
		return pjsxPjzxmgzszGsMappe1r.selectByMainId(mainId,khlx);
	}

}
