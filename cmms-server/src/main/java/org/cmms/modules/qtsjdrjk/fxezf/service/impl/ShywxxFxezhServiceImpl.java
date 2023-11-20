package org.cmms.modules.qtsjdrjk.fxezf.service.impl;

import org.cmms.modules.qtsjdrjk.fxezf.entity.ShywxxFxezh;
import org.cmms.modules.qtsjdrjk.fxezf.mapper.ShywxxFxezhMapper;
import org.cmms.modules.qtsjdrjk.fxezf.service.IShywxxFxezhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 福祥E支付
 * @Author: jeecg-boot
 * @Date:   2020-11-03
 * @Version: V1.0
 */
@Service
public class ShywxxFxezhServiceImpl extends ServiceImpl<ShywxxFxezhMapper, ShywxxFxezh> implements IShywxxFxezhService {

    @Autowired
    private ShywxxFxezhMapper shywxxFxezhMapper;

    @Override
    public Integer deleteAll() {
        return shywxxFxezhMapper.deleteAll();
    }
}
