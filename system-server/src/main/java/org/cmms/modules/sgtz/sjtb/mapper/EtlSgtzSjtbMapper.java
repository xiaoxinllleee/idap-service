package org.cmms.modules.sgtz.sjtb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.sgtz.sjtb.entity.EtlSgtzSjtb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: ETL手工台账数据同步
 * @Author: Penghr
 * @Date:   2022-10-19
 * @Version: V1.0
 */
public interface EtlSgtzSjtbMapper extends BaseMapper<EtlSgtzSjtb> {
    /**
     * 去除导入数据中的空格
     */
    void trimData(String tableName);

    String getZdrkrq(String etlName ,String dagName);
}
