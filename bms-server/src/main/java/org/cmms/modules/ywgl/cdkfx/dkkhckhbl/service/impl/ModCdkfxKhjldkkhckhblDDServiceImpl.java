package org.cmms.modules.ywgl.cdkfx.dkkhckhbl.service.impl;

import org.cmms.modules.ywgl.cdkfx.dkkhckhbl.entity.ModCdkfxKhjldkkhckhblD;
import org.cmms.modules.ywgl.cdkfx.dkkhckhbl.mapper.ModCdkfxKhjldkkhckhblDMapper;
import org.cmms.modules.ywgl.cdkfx.dkkhckhbl.service.IModCdkfxKhjldkkhckhblDService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款客户存款回报率
 * @Author: jeecg-boot
 * @Date:   2021-07-01
 * @Version: V1.0
 */
@Service
public class ModCdkfxKhjldkkhckhblDDServiceImpl extends ServiceImpl<ModCdkfxKhjldkkhckhblDMapper, ModCdkfxKhjldkkhckhblD> implements IModCdkfxKhjldkkhckhblDService {

    @Override
    public void pDkfxDkkhckhbl(String tjyf) {
        baseMapper.pDkfxDkkhckhbl(tjyf);
    }
}
