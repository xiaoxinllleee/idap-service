package org.cmms.modules.qtsjdrjk.xyk.service.impl;

import org.cmms.modules.qtsjdrjk.xyk.entity.ShywxxXyk;
import org.cmms.modules.qtsjdrjk.xyk.mapper.ShywxxXykMapper;
import org.cmms.modules.qtsjdrjk.xyk.service.IShywxxXykService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 信用卡
 * @Author: jeecg-boot
 * @Date:   2020-11-03
 * @Version: V1.0
 */
@Service
public class ShywxxXykServiceImpl extends ServiceImpl<ShywxxXykMapper, ShywxxXyk> implements IShywxxXykService {

    @Autowired
    private ShywxxXykMapper shywxxXykMapper;

    @Override
    public Integer deleteAll() {
        return shywxxXykMapper.deleteAll();
    }
}
