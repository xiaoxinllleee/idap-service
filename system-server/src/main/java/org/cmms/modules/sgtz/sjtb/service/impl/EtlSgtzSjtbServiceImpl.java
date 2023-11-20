package org.cmms.modules.sgtz.sjtb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.sgtz.sjtb.entity.EtlSgtzSjtb;
import org.cmms.modules.sgtz.sjtb.mapper.EtlSgtzSjtbMapper;
import org.cmms.modules.sgtz.sjtb.service.IEtlSgtzSjtbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: ETL手工台账数据同步
 * @Author: Penghr
 * @Date:   2022-10-19
 * @Version: V1.0
 */
@Service
public class EtlSgtzSjtbServiceImpl extends ServiceImpl<EtlSgtzSjtbMapper, EtlSgtzSjtb> implements IEtlSgtzSjtbService {
    @Override
    public void trimData(String tableName) {
        baseMapper.trimData(tableName);
    }


    @Override
    @DS("wetori")
    public String getZdrkrq(String etlName,String dagName) {
       return baseMapper.getZdrkrq(etlName,dagName);
    }
}
