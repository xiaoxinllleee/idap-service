package org.cmms.modules.tjfx.qhywjd.qhsxqk.service.impl;

import org.cmms.modules.tjfx.qhywjd.qhsxqk.entity.TjfxQhsxqk;
import org.cmms.modules.tjfx.qhywjd.qhsxqk.mapper.TjfxQhsxqkMapper;
import org.cmms.modules.tjfx.qhywjd.qhsxqk.service.ITjfxQhsxqkService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 全行授信情况
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
@Service
public class TjfxQhsxqkServiceImpl extends ServiceImpl<TjfxQhsxqkMapper, TjfxQhsxqk> implements ITjfxQhsxqkService {

    @Override
    public void initData(String yggh) {
        baseMapper.initData(yggh);
    }
}
