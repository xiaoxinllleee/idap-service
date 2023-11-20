package org.cmms.modules.gzap.gzrw.service.impl;

import org.cmms.modules.gzap.gzrw.entity.RwxfRwgl;
import org.cmms.modules.gzap.gzrw.mapper.RwxfRwglMapper;
import org.cmms.modules.gzap.gzrw.service.IRwxfRwglService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 任务对象管理
 * @Author: cmms
 * @Date:   2019-09-25
 * @Version: V1.0
 */
@Service
public class RwxfRwglServiceImpl extends ServiceImpl<RwxfRwglMapper, RwxfRwgl> implements IRwxfRwglService {
	
	@Autowired
	private RwxfRwglMapper rwxfRwglMapper;
	
	@Override
	public List<RwxfRwgl> selectByMainId(String mainId) {
		return rwxfRwglMapper.selectByMainId(mainId);
	}

}
