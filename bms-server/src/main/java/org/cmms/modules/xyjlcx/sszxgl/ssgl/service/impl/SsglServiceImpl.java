package org.cmms.modules.xyjlcx.sszxgl.ssgl.service.impl;

import org.cmms.modules.xyjlcx.sszxgl.ssgl.entity.Ssgl;
import org.cmms.modules.xyjlcx.sszxgl.ssgl.mapper.SsglMapper;
import org.cmms.modules.xyjlcx.sszxgl.ssgl.service.ISsglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 诉讼管理
 * @Author: jeecg-boot
 * @Date:   2021-08-16
 * @Version: V1.0
 */
@Service
public class SsglServiceImpl extends ServiceImpl<SsglMapper, Ssgl> implements ISsglService {

    @Override
    public void pSsgl() {
        baseMapper.pSsgl();
    }
}
