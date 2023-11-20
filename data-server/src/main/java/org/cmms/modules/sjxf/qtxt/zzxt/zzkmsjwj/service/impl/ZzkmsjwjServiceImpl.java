package org.cmms.modules.sjxf.qtxt.zzxt.zzkmsjwj.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.sjxf.qtxt.zzxt.zzkmsjwj.entity.Zzkmsjwj;
import org.cmms.modules.sjxf.qtxt.zzxt.zzkmsjwj.mapper.ZzkmsjwjMapper;
import org.cmms.modules.sjxf.qtxt.zzxt.zzkmsjwj.service.IZzkmsjwjService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 总帐科目数据文件
 * @Author: jeecg-boot
 * @Date:   2021-12-14
 * @Version: V1.0
 */
@Service

public class ZzkmsjwjServiceImpl extends ServiceImpl<ZzkmsjwjMapper, Zzkmsjwj> implements IZzkmsjwjService {

    @Override
    public String getMaxDataDateImpala() {
        return baseMapper.getMaxDataDateImpala();
    }

    // @Override
    // public String getMaxDataDateOracle() {
    //     return baseMapper.getMaxDataDateOracle();
    // }
}
