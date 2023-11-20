package org.cmms.modules.xddagl.xdhc.xdhc01.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.xddagl.xdhc.xdhc01.entity.Xdhc01;
import org.cmms.modules.xddagl.xdhc.xdhc01.mapper.Xdhc01Mapper;
import org.cmms.modules.xddagl.xdhc.xdhc01.service.IXdhc01Service;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 信贷T+1核查
 * @Author: jeecg-boot
 * @Date:   2022-01-18
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class Xdhc01ServiceImpl extends ServiceImpl<Xdhc01Mapper, Xdhc01> implements IXdhc01Service {

    @Override
    public void pXdhc01() {
        baseMapper.pXdhc01();
    }
}
