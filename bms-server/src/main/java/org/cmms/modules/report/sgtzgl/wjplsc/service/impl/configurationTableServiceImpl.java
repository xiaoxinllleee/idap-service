package org.cmms.modules.report.sgtzgl.wjplsc.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.cmms.modules.report.sgtzgl.wjplsc.entity.configurationTable;
import org.cmms.modules.report.sgtzgl.wjplsc.mapper.configurationTableMapper;
import org.cmms.modules.report.sgtzgl.wjplsc.service.IconfigurationTableService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: dwd
 * @Author: jeecg-boot
 * @Date:   2022-10-27
 * @Version: V1.0
 */
@Service
public class configurationTableServiceImpl extends ServiceImpl<configurationTableMapper, configurationTable> implements IconfigurationTableService {

    @Override
    public configurationTable  selectTableCodeByTableName(String name) {
        LambdaQueryWrapper<configurationTable>lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(configurationTable::getTableName,name);
        configurationTable configurationTable=this.baseMapper.selectOne(lambdaQueryWrapper);
        return  configurationTable;
    }
}
