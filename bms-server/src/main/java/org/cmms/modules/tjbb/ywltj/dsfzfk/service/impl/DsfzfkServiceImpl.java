package org.cmms.modules.tjbb.ywltj.dsfzfk.service.impl;

import org.cmms.modules.tjbb.ywltj.dsfzfk.entity.Dsfzfk;
import org.cmms.modules.tjbb.ywltj.dsfzfk.mapper.DsfzfkMapper;
import org.cmms.modules.tjbb.ywltj.dsfzfk.service.IDsfzfkService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 第三方支付卡
 * @Author: jeecg-boot
 * @Date:   2021-08-25
 * @Version: V1.0
 */
@Service
public class DsfzfkServiceImpl extends ServiceImpl<DsfzfkMapper, Dsfzfk> implements IDsfzfkService {

    @Override
    public void pDsfzfk(String tjyf) {
        baseMapper.pDsfzfk(tjyf);
    }
}
