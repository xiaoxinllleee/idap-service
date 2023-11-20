package org.cmms.modules.performance.depositcustomer.ckkhxxgl.service.impl;

import org.cmms.modules.performance.depositcustomer.ckkhxxgl.vo.ClckkhxxImp;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.mapper.ClckkhxxImpMapper;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.service.IClckkhxxImpService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 存量存款客户信息（数据导入临时表）
 * @Author: jeecg-boot
 * @Date:   2023-04-11
 * @Version: V1.0
 */
@Service
public class ClckkhxxImpServiceImpl extends ServiceImpl<ClckkhxxImpMapper, ClckkhxxImp> implements IClckkhxxImpService {

    @Override
    public void ClckkhxxImpDelete() {
        baseMapper.ClckkhxxImpDelete();
    }
}
