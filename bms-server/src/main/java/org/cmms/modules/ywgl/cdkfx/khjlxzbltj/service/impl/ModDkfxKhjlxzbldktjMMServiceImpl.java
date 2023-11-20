package org.cmms.modules.ywgl.cdkfx.khjlxzbltj.service.impl;

import org.cmms.modules.ywgl.cdkfx.khjlxzbltj.entity.ModDkfxKhjlxzbldktjMM;
import org.cmms.modules.ywgl.cdkfx.khjlxzbltj.mapper.ModDkfxKhjlxzbldktjMMMapper;
import org.cmms.modules.ywgl.cdkfx.khjlxzbltj.service.IModDkfxKhjlxzbldktjMMService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户经理新增不良统计
 * @Author: jeecg-boot
 * @Date:   2021-06-21
 * @Version: V1.0
 */
@Service
public class ModDkfxKhjlxzbldktjMMServiceImpl extends ServiceImpl<ModDkfxKhjlxzbldktjMMMapper, ModDkfxKhjlxzbldktjMM> implements IModDkfxKhjlxzbldktjMMService {


    @Override
    public String getCustidByName(String ygxm) {
        return baseMapper.getCustidByName(ygxm);
    }
}
