package org.cmms.modules.zhgl.jxtj.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.zhgl.jxtj.entity.EepWageGzhzbCl2;
import org.cmms.modules.zhgl.jxtj.mapper.EepWageGzhzbCl2Mapper;
import org.cmms.modules.zhgl.jxtj.service.IEepWageGzhzbCl2Service;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 慈利工资表
 * @Author: jeecg-boot
 * @Date:   2023-02-15
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class EepWageGzhzbCl2ServiceImpl extends ServiceImpl<EepWageGzhzbCl2Mapper, EepWageGzhzbCl2> implements IEepWageGzhzbCl2Service {

    @Override
    public Date getMaxImpday() {
        return baseMapper.getMaxImpday();
    }
}
