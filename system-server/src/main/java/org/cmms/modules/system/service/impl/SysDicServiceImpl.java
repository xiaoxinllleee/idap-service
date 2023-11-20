package org.cmms.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.common.constant.CacheConstant;
import org.cmms.modules.system.entity.SysDic;
import org.cmms.modules.system.mapper.SysDicMapper;
import org.cmms.modules.system.service.ISysDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统配置表 服务实现类
 * </p>
 *
 * @Author scott
 * @since 2018-12-19
 */
@Service
public class SysDicServiceImpl extends ServiceImpl<SysDicMapper, SysDic> implements ISysDicService {
    @Autowired
    private SysDicMapper sysDicMapper;

    @Override
    @Cacheable(value = CacheConstant.QYBM,key = "#code")
    public SysDic queryByCode(String code) {
        return sysDicMapper.queryByCode(code);
    }

}
