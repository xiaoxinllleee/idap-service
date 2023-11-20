package org.cmms.modules.performance.loancustomer.dkkhInformation.service.impl;

import org.cmms.modules.performance.loancustomer.dkkhInformation.entity.DkkhInforMation;
import org.cmms.modules.performance.loancustomer.dkkhInformation.mapper.DkkhInforMationMapper;
import org.cmms.modules.performance.loancustomer.dkkhInformation.service.IDkkhInforMationService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款客户综合信息
 * @Author: jeecg-boot
 * @Date:   2023-04-03
 * @Version: V1.0
 */
@Service
public class DkkhInforMationServiceImpl extends ServiceImpl<DkkhInforMationMapper, DkkhInforMation> implements IDkkhInforMationService {

    @Override
    public void extract() {
        baseMapper.extract();
    }
}
