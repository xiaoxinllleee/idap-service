package org.cmms.modules.ywgl.dkyw.lsdksjgl.service.impl;

import org.cmms.modules.ywgl.dkyw.lsdksjgl.entity.Lsdksjgl;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.mapper.LsdksjglMapper;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.service.ILsdksjglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 历史贷款数据管理
 * @Author: jeecg-boot
 * @Date:   2021-09-24
 * @Version: V1.0
 */
@Service
public class LsdksjglServiceImpl extends ServiceImpl<LsdksjglMapper, Lsdksjgl> implements ILsdksjglService {

    @Override
    public String getByName(String custManagerName) {
        return baseMapper.getByName(custManagerName);
    }
}
