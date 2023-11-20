package org.cmms.modules.ywgl.cdkfx.khjlcdkytj.service.impl;

import org.cmms.modules.ywgl.cdkfx.khjlcdkytj.entity.ModDkfxJgkhjlzhcdkyetjM;
import org.cmms.modules.ywgl.cdkfx.khjlcdkytj.mapper.ModDkfxJgkhjlzhcdkyetjMMapper;
import org.cmms.modules.ywgl.cdkfx.khjlcdkytj.service.IModDkfxJgkhjlzhcdkyetjMService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户经理存贷款月统计
 * @Author: jeecg-boot
 * @Date:   2021-06-25
 * @Version: V1.0
 */
@Service
public class ModDkfxJgkhjlzhcdkyetjMServiceImpl extends ServiceImpl<ModDkfxJgkhjlzhcdkyetjMMapper, ModDkfxJgkhjlzhcdkyetjM> implements IModDkfxJgkhjlzhcdkyetjMService {

    @Override
    public String getByName(String ygxm) {
        return baseMapper.getByName(ygxm);
    }
}
