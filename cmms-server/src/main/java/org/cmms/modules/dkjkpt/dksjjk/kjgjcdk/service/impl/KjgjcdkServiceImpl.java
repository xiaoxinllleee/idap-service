package org.cmms.modules.dkjkpt.dksjjk.kjgjcdk.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dksjjk.kjgjcdk.entity.Kjgjcdk;
import org.cmms.modules.dkjkpt.dksjjk.kjgjcdk.mapper.KjgjcdkMapper;
import org.cmms.modules.dkjkpt.dksjjk.kjgjcdk.service.IKjgjcdkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 跨机构交叉贷款
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
@Service
public class KjgjcdkServiceImpl extends ServiceImpl<KjgjcdkMapper, Kjgjcdk> implements IKjgjcdkService {

    @Autowired
    private KjgjcdkMapper mapper;

    @Override
    public void InitData() {
        mapper.InitData();
    }
}
