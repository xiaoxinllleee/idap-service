package org.cmms.modules.performance.depositcustomer.ckkhxxgl.service.impl;

import org.cmms.modules.performance.depositcustomer.ckkhxxgl.vo.Clckkhxx;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.mapper.ClckkhxxMapper;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.service.IClckkhxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 存量存款客户信息
 * @Author: jeecg-boot
 * @Date:   2023-04-11
 * @Version: V1.0
 */
@Service
public class ClckkhxxServiceImpl extends ServiceImpl<ClckkhxxMapper, Clckkhxx> implements IClckkhxxService {

    @Override
    public void pRlckkhxxImp(String username) {
        baseMapper.pRlckkhxxImp(username);
    }
}
