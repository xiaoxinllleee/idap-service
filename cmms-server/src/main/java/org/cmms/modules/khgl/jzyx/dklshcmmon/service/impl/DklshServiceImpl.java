package org.cmms.modules.khgl.jzyx.dklshcmmon.service.impl;

import org.cmms.modules.khgl.jzyx.dklshcmmon.entity.Dklsh;
import org.cmms.modules.khgl.jzyx.dklshcmmon.mapper.DklshMapper;
import org.cmms.modules.khgl.jzyx.dklshcmmon.service.IDklshService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 贷款流失户
 * @Author: jeecg-boot
 * @Date:   2023-07-10
 * @Version: V1.0
 */
@Service
public class DklshServiceImpl extends ServiceImpl<DklshMapper, Dklsh> implements IDklshService {

    @Override
    @Transactional
    public void extract(){
        baseMapper.extract();
    }
}
