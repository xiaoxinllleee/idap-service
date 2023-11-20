package org.cmms.modules.tjfx.wgtjfx.bkbpytj.service.impl;

import org.cmms.modules.tjfx.wgtjfx.bkbpytj.entity.Bkbpytj;
import org.cmms.modules.tjfx.wgtjfx.bkbpytj.mapper.BkbpytjMapper;
import org.cmms.modules.tjfx.wgtjfx.bkbpytj.service.IBkbpytjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 背靠背评议统计
 * @Author: jeecg-boot
 * @Date:   2022-04-18
 * @Version: V1.0
 */
@Service
public class BkbpytjServiceImpl extends ServiceImpl<BkbpytjMapper, Bkbpytj> implements IBkbpytjService {
    @Autowired
    private BkbpytjMapper bkbpytjMapper;
    public Date getMaxTjrq() {
        return bkbpytjMapper.getMaxTjrq();
    }
}
