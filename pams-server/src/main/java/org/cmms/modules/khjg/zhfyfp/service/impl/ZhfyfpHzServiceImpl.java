package org.cmms.modules.khjg.zhfyfp.service.impl;

import org.cmms.modules.khjg.zhfyfp.entity.ZhfyfpHz;
import org.cmms.modules.khjg.zhfyfp.mapper.ZhfyfpHzMapper;
import org.cmms.modules.khjg.zhfyfp.service.IZhfyfpHzService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 支行费用分配
 * @Author: jeecg-boot
 * @Date:   2023-04-04
 * @Version: V1.0
 */
@Service
public class ZhfyfpHzServiceImpl extends ServiceImpl<ZhfyfpHzMapper, ZhfyfpHz> implements IZhfyfpHzService {
    @Override
    @Transactional
    public void extract(String sql,String fpyf,String fylx) {
        baseMapper.extract(sql,fpyf,fylx);
    }
}
