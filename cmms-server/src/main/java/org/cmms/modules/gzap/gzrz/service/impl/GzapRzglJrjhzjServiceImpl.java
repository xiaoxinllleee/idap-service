package org.cmms.modules.gzap.gzrz.service.impl;

import org.cmms.modules.gzap.gzrz.entity.GzapRzglJrjhzj;
import org.cmms.modules.gzap.gzrz.mapper.GzapRzglJrjhzjMapper;
import org.cmms.modules.gzap.gzrz.service.IGzapRzglJrjhzjService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 今天计划总结
 * @Author: cmms
 * @Date:   2019-09-08
 * @Version: V1.0
 */
@Service
public class GzapRzglJrjhzjServiceImpl extends ServiceImpl<GzapRzglJrjhzjMapper, GzapRzglJrjhzj> implements IGzapRzglJrjhzjService {
	
	@Autowired
	private GzapRzglJrjhzjMapper gzapRzglJrjhzjMapper;
	
	@Override
	public List<GzapRzglJrjhzj> selectByMainId(String mainId) {
		return gzapRzglJrjhzjMapper.selectByMainId(mainId);
	}
}
