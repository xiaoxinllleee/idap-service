package org.cmms.modules.tjbb.ywltj.kqzzzsbywl.service.impl;

import org.cmms.modules.tjbb.ywltj.kqzzzsbywl.entity.Kqzzzsbywl;
import org.cmms.modules.tjbb.ywltj.kqzzzsbywl.mapper.KqzzzsbywlMapper;
import org.cmms.modules.tjbb.ywltj.kqzzzsbywl.service.IKqzzzsbywlService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 卡前置自助设备业务量
 * @Author: jeecg-boot
 * @Date:   2021-08-25
 * @Version: V1.0
 */
@Service
public class KqzzzsbywlServiceImpl extends ServiceImpl<KqzzzsbywlMapper, Kqzzzsbywl> implements IKqzzzsbywlService {

    @Override
    public void pKqzzzsbywl(String tjwd, String tjyf) {
        baseMapper.pKqzzzsbywl(tjwd, tjyf);
    }
}
