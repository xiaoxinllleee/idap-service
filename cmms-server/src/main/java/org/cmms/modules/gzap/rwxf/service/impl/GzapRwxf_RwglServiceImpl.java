package org.cmms.modules.gzap.rwxf.service.impl;

import org.cmms.modules.gzap.rwxf.entity.GzapRwxf_Rwgl;
import org.cmms.modules.gzap.rwxf.mapper.GzapRwxf_RwglMapper;
import org.cmms.modules.gzap.rwxf.service.IGzapRwxf_RwglService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 任务对象管理
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
@Service
public class GzapRwxf_RwglServiceImpl extends ServiceImpl<GzapRwxf_RwglMapper, GzapRwxf_Rwgl> implements IGzapRwxf_RwglService {
	
	@Autowired
	private GzapRwxf_RwglMapper gzapRwxf_RwglMapper;
	
	@Override
	public List<GzapRwxf_Rwgl> selectByMainId(String mainId) {
		return gzapRwxf_RwglMapper.selectByMainId(mainId);
	}
}
