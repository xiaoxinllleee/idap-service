package org.cmms.modules.gzap.gzjh.service.impl;

import org.cmms.modules.gzap.gzjh.entity.GzapJhxfKhjl;
import org.cmms.modules.gzap.gzjh.mapper.GzapJhxfKhjlMapper;
import org.cmms.modules.gzap.gzjh.service.IGzapJhxfKhjlService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 任务对象管理
 * @Author: cmms
 * @Date:   2019-09-26
 * @Version: V1.0
 */
@Service
public class GzapJhxfKhjlServiceImpl extends ServiceImpl<GzapJhxfKhjlMapper, GzapJhxfKhjl> implements IGzapJhxfKhjlService {
	
	@Autowired
	private GzapJhxfKhjlMapper gzapJhxfKhjlMineMapper;
	
	@Override
	public List<GzapJhxfKhjl> selectByMainId(String mainId) {
		return gzapJhxfKhjlMineMapper.selectByMainId(mainId);
	}
}
