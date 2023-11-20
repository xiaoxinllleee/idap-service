package org.cmms.modules.performance.loancustomer.dkhtzhxx.service.impl;

import org.cmms.modules.performance.loancustomer.dkhtzhxx.entity.Dkkhxxgl;
import org.cmms.modules.performance.loancustomer.dkhtzhxx.mapper.DkkhxxglMapper;
import org.cmms.modules.performance.loancustomer.dkhtzhxx.service.IDkkhxxglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 贷款客户综合信息
 * @Author: jeecg-boot
 * @Date:   2023-04-03
 * @Version: V1.0
 */
@Service
public class DkkhxxglServiceImpl extends ServiceImpl<DkkhxxglMapper, Dkkhxxgl> implements IDkkhxxglService {
    @Override
    @Transactional
    public void extract() {
        baseMapper.extract();
    }

    @Override
    public void rldkkhxx(String jgdm) {
        baseMapper.rldkkhxx(jgdm);
    }
}
