package org.cmms.modules.yxdygl.yxdyfjxx.service.impl;

import org.cmms.modules.yxdygl.yxdyfjxx.entity.Yxdyfjxx;
import org.cmms.modules.yxdygl.yxdyfjxx.mapper.YxdyfjxxMapper;
import org.cmms.modules.yxdygl.yxdyfjxx.service.IYxdyfjxxService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 营销单元附件信息
 * @Author: jeecg-boot
 * @Date:   2020-07-28
 * @Version: V1.0
 */
@Service
public class YxdyfjxxServiceImpl extends ServiceImpl<YxdyfjxxMapper, Yxdyfjxx> implements IYxdyfjxxService {
	
	@Override
	public List<Yxdyfjxx> selectByMainId(String mainId) {
		return baseMapper.selectByMainId(mainId);
	}
}
