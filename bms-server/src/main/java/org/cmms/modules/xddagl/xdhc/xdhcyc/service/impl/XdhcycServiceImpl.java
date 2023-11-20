package org.cmms.modules.xddagl.xdhc.xdhcyc.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.xddagl.xdhc.xdhcyc.entity.Xdhcyc;
import org.cmms.modules.xddagl.xdhc.xdhcyc.mapper.XdhcycMapper;
import org.cmms.modules.xddagl.xdhc.xdhcyc.service.IXdhcycService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 信贷T+1核查先隐藏
 * @Author: jeecg-boot
 * @Date:   2022-01-18
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class XdhcycServiceImpl extends ServiceImpl<XdhcycMapper, Xdhcyc> implements IXdhcycService {

    @Override
    public void pXdhcyc() {
        baseMapper.pXdhcyc();
    }
}
