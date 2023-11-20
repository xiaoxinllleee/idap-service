package org.cmms.modules.performance.loancustomer.dkhtzhxx.service.impl;

import org.cmms.modules.performance.loancustomer.dkhtzhxx.vo.CldkkhxxImp;
import org.cmms.modules.performance.loancustomer.dkhtzhxx.mapper.CldkkhxxImpMapper;
import org.cmms.modules.performance.loancustomer.dkhtzhxx.service.ICldkkhxxImpService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 存量贷款客户信息导入
 * @Author: jeecg-boot
 * @Date:   2023-04-12
 * @Version: V1.0
 */
@Service
public class CldkkhxxImpServiceImpl extends ServiceImpl<CldkkhxxImpMapper, CldkkhxxImp> implements ICldkkhxxImpService {

    @Override
    public void pRldkkhxxImp(String username) {
        baseMapper.pRldkkhxxImp(username);
    }
}
