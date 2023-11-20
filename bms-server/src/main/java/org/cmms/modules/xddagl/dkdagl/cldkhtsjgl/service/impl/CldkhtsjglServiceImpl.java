package org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.service.impl;

import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.entity.Cldkhtsjgl;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.mapper.CldkhtsjglMapper;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.service.ICldkhtsjglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 存量贷款合同数据管理
 * @Author: jeecg-boot
 * @Date:   2022-01-06
 * @Version: V1.0
 */
@Service
public class CldkhtsjglServiceImpl extends ServiceImpl<CldkhtsjglMapper, Cldkhtsjgl> implements ICldkhtsjglService {

    @Override
    public void pCldkhtsjgl() {
        baseMapper.pCldkhtsjgl();
    }
}
