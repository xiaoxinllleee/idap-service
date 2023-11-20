package org.cmms.modules.dkjkpt.dksjjk.dksjjktz.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.entity.Dkjkptbwblxt;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.mapper.DkjkptbwblxtMapper;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.service.IDkjkptbwblxtService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款监控平台表外不良_湘潭
 * @Author: jeecg-boot
 * @Date:   2023-06-06
 * @Version: V1.0
 */
@Service
public class DkjkptbwblxtServiceImpl extends ServiceImpl<DkjkptbwblxtMapper, Dkjkptbwblxt> implements IDkjkptbwblxtService {

    @Override
    public void init() {
        baseMapper.init();
    }
}
