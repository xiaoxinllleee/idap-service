package org.cmms.modules.khlc.jczbgl.service.impl;

import org.cmms.modules.khlc.jczbgl.entity.ErpBasZbk;
import org.cmms.modules.khlc.jczbgl.mapper.ErpBasZbkMapper;
import org.cmms.modules.khlc.jczbgl.service.IErpBasZbkService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 指标库管理
 * @Author: jeecg-boot
 * @Date:   2023-02-22
 * @Version: V1.0
 */
@Service
public class ErpBasZbkServiceImpl extends ServiceImpl<ErpBasZbkMapper, ErpBasZbk> implements IErpBasZbkService {

    @Override
    public void deleteByZbid(String zbid) {
        baseMapper.deleteByZbid(zbid);
    }
}
