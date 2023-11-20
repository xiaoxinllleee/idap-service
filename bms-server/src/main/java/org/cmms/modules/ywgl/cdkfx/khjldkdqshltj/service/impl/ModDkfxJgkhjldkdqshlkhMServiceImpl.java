package org.cmms.modules.ywgl.cdkfx.khjldkdqshltj.service.impl;

import org.cmms.modules.ywgl.cdkfx.khjldkdqshltj.entity.ModDkfxJgkhjldkdqshlkhM;
import org.cmms.modules.ywgl.cdkfx.khjldkdqshltj.mapper.ModDkfxJgkhjldkdqshlkhMMapper;
import org.cmms.modules.ywgl.cdkfx.khjldkdqshltj.service.IModDkfxJgkhjldkdqshlkhMService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户经理贷款到期收回率统计
 * @Author: jeecg-boot
 * @Date:   2021-06-16
 * @Version: V1.0
 */
@Service
public class ModDkfxJgkhjldkdqshlkhMServiceImpl extends ServiceImpl<ModDkfxJgkhjldkdqshlkhMMapper, ModDkfxJgkhjldkdqshlkhM> implements IModDkfxJgkhjldkdqshlkhMService {

    @Override
    public String getByName(String custName) {
        return baseMapper.getByName(custName);
    }
}
