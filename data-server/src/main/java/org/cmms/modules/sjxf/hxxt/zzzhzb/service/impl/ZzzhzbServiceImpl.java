package org.cmms.modules.sjxf.hxxt.zzzhzb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.sjxf.hxxt.zzzhzb.entity.Zzzhzb;
import org.cmms.modules.sjxf.hxxt.zzzhzb.mapper.ZzzhzbMapper;
import org.cmms.modules.sjxf.hxxt.zzzhzb.service.IZzzhzbService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 总账账户主表
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@Service

public class ZzzhzbServiceImpl extends ServiceImpl<ZzzhzbMapper, Zzzhzb> implements IZzzhzbService {

    @Override
    public Zzzhzb queryZzzhzbOracle(String ckzh) {
        return baseMapper.queryZzzhzbOracle(ckzh);
    }

    @Override
    public Zzzhzb queryZzzhzbHive(String ckzh) {
        return baseMapper.queryZzzhzbHive(ckzh);
    }
}
