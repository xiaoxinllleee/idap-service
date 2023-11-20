package org.cmms.modules.ywgl.cdkfx.qhdkkhckhbl.service.impl;

import org.cmms.modules.ywgl.cdkfx.qhdkkhckhbl.entity.ModCdkfxQhdkkhckhbl;
import org.cmms.modules.ywgl.cdkfx.qhdkkhckhbl.mapper.ModCdkfxQhdkkhckhblMapper;
import org.cmms.modules.ywgl.cdkfx.qhdkkhckhbl.service.IModCdkfxQhdkkhckhblService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 全行贷款客户存款回报率
 * @Author: jeecg-boot
 * @Date:   2021-07-02
 * @Version: V1.0
 */
@Service
public class ModCdkfxQhdkkhckhblServiceImpl extends ServiceImpl<ModCdkfxQhdkkhckhblMapper, ModCdkfxQhdkkhckhbl> implements IModCdkfxQhdkkhckhblService {

    @Override
    public void pDkfxDkkhckhbl(String tjyf) {
        baseMapper.pDkfxDkkhckhbl(tjyf);
    }
}
