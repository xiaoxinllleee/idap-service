package org.cmms.modules.dkjkpt.dksjjk.dksjahtj.mapper;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.dksjjk.dksjahtj.entity.Dkyeb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Date;
import java.util.List;

/**
 * @Description: 贷款余额表
 * @Author: Penghr
 * @Date:   2020-03-31
 * @Version: V1.0
 */
public interface DkyebMapper extends BaseMapper<Dkyeb> {

    List<Dkyeb> queryDhdksjmx(@Param("tjrq") String tjrq,
                              @Param("jgdm") String jgdm,
                              @Param("zjhm") String zjhm);

}
