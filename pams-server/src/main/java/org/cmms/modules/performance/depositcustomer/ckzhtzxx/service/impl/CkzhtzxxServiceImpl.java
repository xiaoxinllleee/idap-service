package org.cmms.modules.performance.depositcustomer.ckzhtzxx.service.impl;

import org.cmms.modules.performance.depositcustomer.ckzhtzxx.entity.Ckzhtzxx;
import org.cmms.modules.performance.depositcustomer.ckzhtzxx.mapper.CkzhtzxxMapper;
import org.cmms.modules.performance.depositcustomer.ckzhtzxx.service.ICkzhtzxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 存款账户拓展信息
 * @Author: jeecg-boot
 * @Date:   2023-03-30
 * @Version: V1.0
 */
@Service
public class CkzhtzxxServiceImpl extends ServiceImpl<CkzhtzxxMapper, Ckzhtzxx> implements ICkzhtzxxService {

    @Override
    @Transactional
    public void extract() {
        baseMapper.extract();
    }
}
