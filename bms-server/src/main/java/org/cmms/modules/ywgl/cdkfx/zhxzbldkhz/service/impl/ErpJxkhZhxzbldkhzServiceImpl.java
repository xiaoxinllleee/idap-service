package org.cmms.modules.ywgl.cdkfx.zhxzbldkhz.service.impl;

import org.cmms.modules.ywgl.cdkfx.zhxzbldkhz.entity.ErpJxkhZhxzbldkhz;
import org.cmms.modules.ywgl.cdkfx.zhxzbldkhz.mapper.ErpJxkhZhxzbldkhzMapper;
import org.cmms.modules.ywgl.cdkfx.zhxzbldkhz.service.IErpJxkhZhxzbldkhzService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 支行新增不良贷款汇总
 * @Author: jeecg-boot
 * @Date:   2021-06-24
 * @Version: V1.0
 */
@Service
public class ErpJxkhZhxzbldkhzServiceImpl extends ServiceImpl<ErpJxkhZhxzbldkhzMapper, ErpJxkhZhxzbldkhz> implements IErpJxkhZhxzbldkhzService {

    @Override
    public void pJxkhZhxzbldkhz(String tjyf) {
        baseMapper.pJxkhZhxzbldkhz(tjyf);
    }
}
