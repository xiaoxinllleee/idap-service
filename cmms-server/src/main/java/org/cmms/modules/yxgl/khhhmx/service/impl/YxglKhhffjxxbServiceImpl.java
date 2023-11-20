package org.cmms.modules.yxgl.khhhmx.service.impl;

import org.cmms.modules.yxgl.khhhmx.entity.YxglKhhffjxxb;
import org.cmms.modules.yxgl.khhhmx.mapper.YxglKhhffjxxbMapper;
import org.cmms.modules.yxgl.khhhmx.service.IYxglKhhffjxxbService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 客户回访附件信息
 * @Author: cmms
 * @Date:   2019-12-23
 * @Version: V1.0
 */
@Service
public class YxglKhhffjxxbServiceImpl extends ServiceImpl<YxglKhhffjxxbMapper, YxglKhhffjxxb> implements IYxglKhhffjxxbService {
	
	@Autowired
	private YxglKhhffjxxbMapper yxglKhhffjxxbMapper;
	
	@Override
	public List<YxglKhhffjxxb> selectByMainId(String mainId) {
		return yxglKhhffjxxbMapper.selectByMainId(mainId);
	}
}
