package org.cmms.modules.dkjkpt.dksjjk.zhdkshtj.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dksjjk.zhdkshtj.entity.Zhdkshtj;
import org.cmms.modules.dkjkpt.dksjjk.zhdkshtj.mapper.ZhdkshtjMapper;
import org.cmms.modules.dkjkpt.dksjjk.zhdkshtj.service.IZhdkshtjService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javax.annotation.Resource;

/**
 * @Description: 支行贷款收回统计
 * @Author: cmms
 * @Date:   2019-09-10
 * @Version: V1.0
 */
@Service
public class ZhdkshtjServiceImpl extends ServiceImpl<ZhdkshtjMapper, Zhdkshtj> implements IZhdkshtjService {
    @Resource
    private ZhdkshtjMapper mapper;

    @Override
    public void init(String tjyf) {
        mapper.init(tjyf);
    }


}
