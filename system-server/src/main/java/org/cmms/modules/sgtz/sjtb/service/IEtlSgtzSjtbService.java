package org.cmms.modules.sgtz.sjtb.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.sgtz.sjtb.entity.EtlSgtzSjtb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: ETL手工台账数据同步
 * @Author: Penghr
 * @Date:   2022-10-19
 * @Version: V1.0
 */
public interface IEtlSgtzSjtbService extends IService<EtlSgtzSjtb> {
    void trimData(String tableName);

    @DS("wetori")
    String getZdrkrq(String etlName,String dagName);
}
