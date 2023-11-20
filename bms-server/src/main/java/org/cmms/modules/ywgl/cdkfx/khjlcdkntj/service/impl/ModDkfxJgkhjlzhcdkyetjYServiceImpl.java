package org.cmms.modules.ywgl.cdkfx.khjlcdkntj.service.impl;

import org.cmms.modules.ywgl.cdkfx.khjlcdkntj.entity.ModDkfxJgkhjlzhcdkyetjY;
import org.cmms.modules.ywgl.cdkfx.khjlcdkntj.mapper.ModDkfxJgkhjlzhcdkyetjYMapper;
import org.cmms.modules.ywgl.cdkfx.khjlcdkntj.service.IModDkfxJgkhjlzhcdkyetjYService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户经理存贷款年统计
 * @Author: jeecg-boot
 * @Date:   2021-06-28
 * @Version: V1.0
 */
@Service
public class ModDkfxJgkhjlzhcdkyetjYServiceImpl extends ServiceImpl<ModDkfxJgkhjlzhcdkyetjYMapper, ModDkfxJgkhjlzhcdkyetjY> implements IModDkfxJgkhjlzhcdkyetjYService {

    @Override
    public String getByName(String ygxm) {
        return baseMapper.getByName(ygxm);
    }
}
