package org.cmms.modules.tjfx.wgtjfx.bkbpytjzh.service.impl;

import org.cmms.modules.tjfx.wgtjfx.bkbpytjzh.entity.BkbpytjZh;
import org.cmms.modules.tjfx.wgtjfx.bkbpytjzh.mapper.BkbpytjZhMapper;
import org.cmms.modules.tjfx.wgtjfx.bkbpytjzh.service.IBkbpytjZhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 背靠背评议统计_支行
 * @Author: jeecg-boot
 * @Date:   2022-05-09
 * @Version: V1.0
 */
@Service
public class BkbpytjZhServiceImpl extends ServiceImpl<BkbpytjZhMapper, BkbpytjZh> implements IBkbpytjZhService {
    @Autowired
    private BkbpytjZhMapper bkbpytjZhMapper;
    public Date getMaxTjrq() {
        return bkbpytjZhMapper.getMaxTjrq();
    }
}
