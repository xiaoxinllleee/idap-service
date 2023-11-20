package org.cmms.modules.pad.pyxx.service.impl;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.pad.pyxx.entity.Pydjcs;
import org.cmms.modules.pad.pyxx.mapper.PydjcsMapper;
import org.cmms.modules.pad.pyxx.service.IPydjcsService;
import org.cmms.modules.system.entity.SysDic;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;

/**
 * @Description: 评议得分对应等级额度
 * @Author: jeecg-boot
 * @Date:   2020-07-31
 * @Version: V1.0
 */
@Service
public class PydjcsServiceImpl extends ServiceImpl<PydjcsMapper, Pydjcs> implements IPydjcsService {
    @Override
    public Pydjcs getPddjAndJysxde(BigDecimal pydf) {
        return baseMapper.getPddjAndJysxde(pydf);
    }
}
