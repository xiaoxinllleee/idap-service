package org.cmms.modules.ywgl.cdkfx.khjldkkhckhbl.service.impl;

import org.cmms.modules.ywgl.cdkfx.khjldkkhckhbl.entity.ModCdkfxKhjldkkhckhbl;
import org.cmms.modules.ywgl.cdkfx.khjldkkhckhbl.mapper.ModCdkfxKhjldkkhckhblMapper;
import org.cmms.modules.ywgl.cdkfx.khjldkkhckhbl.service.IModCdkfxKhjldkkhckhblService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户经理贷款客户存款回报率
 * @Author: jeecg-boot
 * @Date:   2021-06-30
 * @Version: V1.0
 */
@Service
public class ModCdkfxKhjldkkhckhblServiceImpl extends ServiceImpl<ModCdkfxKhjldkkhckhblMapper, ModCdkfxKhjldkkhckhbl> implements IModCdkfxKhjldkkhckhblService {

    @Override
    public void pDkfxDkkhckhbl(String tjyf) {
        baseMapper.pDkfxDkkhckhbl(tjyf);
    }

    @Override
    public String getYgghByName(String ygxm) {
        return baseMapper.getYgghByName(ygxm);
    }
}
