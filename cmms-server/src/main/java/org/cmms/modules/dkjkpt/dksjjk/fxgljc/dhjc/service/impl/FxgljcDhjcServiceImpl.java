package org.cmms.modules.dkjkpt.dksjjk.fxgljc.dhjc.service.impl;

import org.cmms.modules.dkjkpt.dksjjk.fxgljc.dhjc.entity.FxgljcDhjc;
import org.cmms.modules.dkjkpt.dksjjk.fxgljc.dhjc.mapper.FxgljcDhjcMapper;
import org.cmms.modules.dkjkpt.dksjjk.fxgljc.dhjc.service.IFxgljcDhjcService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷后检查
 * @Author: jeecg-boot
 * @Date:   2023-08-24
 * @Version: V1.0
 */
@Service
public class FxgljcDhjcServiceImpl extends ServiceImpl<FxgljcDhjcMapper, FxgljcDhjc> implements IFxgljcDhjcService {

    @Override
    public void init() {
        baseMapper.init();
    }
}
