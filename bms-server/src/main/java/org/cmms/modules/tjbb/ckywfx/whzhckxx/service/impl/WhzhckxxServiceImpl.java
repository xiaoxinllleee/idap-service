package org.cmms.modules.tjbb.ckywfx.whzhckxx.service.impl;

import org.cmms.modules.tjbb.ckywfx.whzhckxx.entity.Whzhckxx;
import org.cmms.modules.tjbb.ckywfx.whzhckxx.mapper.WhzhckxxMapper;
import org.cmms.modules.tjbb.ckywfx.whzhckxx.service.IWhzhckxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 外汇账户存款信息
 * @Author: jeecg-boot
 * @Date:   2021-08-20
 * @Version: V1.0
 */
@Service
public class WhzhckxxServiceImpl extends ServiceImpl<WhzhckxxMapper, Whzhckxx> implements IWhzhckxxService {

    @Override
    public void pWhzhckxx(String tjyf) {
        baseMapper.pWhzhckxx(tjyf);
    }
}
