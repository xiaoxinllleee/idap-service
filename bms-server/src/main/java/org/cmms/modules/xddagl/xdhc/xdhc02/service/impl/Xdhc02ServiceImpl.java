package org.cmms.modules.xddagl.xdhc.xdhc02.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.xddagl.xdhc.xdhc02.entity.Xdhc02;
import org.cmms.modules.xddagl.xdhc.xdhc02.mapper.Xdhc02Mapper;
import org.cmms.modules.xddagl.xdhc.xdhc02.service.IXdhc02Service;
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
public class Xdhc02ServiceImpl extends ServiceImpl<Xdhc02Mapper, Xdhc02> implements IXdhc02Service {

    @Override
    public void pXdhc02() {
        baseMapper.pXdhc02();
    }
}
