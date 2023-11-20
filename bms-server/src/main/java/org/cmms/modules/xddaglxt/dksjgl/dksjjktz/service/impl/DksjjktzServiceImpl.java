package org.cmms.modules.xddaglxt.dksjgl.dksjjktz.service.impl;

import org.cmms.modules.xddaglxt.dksjgl.dksjjktz.entity.Dksjjktz;
import org.cmms.modules.xddaglxt.dksjgl.dksjjktz.mapper.DksjjktzMapper;
import org.cmms.modules.xddaglxt.dksjgl.dksjjktz.service.IDksjjktzService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款数据监控台账
 * @Author: jeecg-boot
 * @Date:   2021-11-22
 * @Version: V1.0
 */
@Service
public class DksjjktzServiceImpl extends ServiceImpl<DksjjktzMapper, Dksjjktz> implements IDksjjktzService {

    @Override
    public void pDksjjktz() {
        baseMapper.pDksjjktz();
    }
}
