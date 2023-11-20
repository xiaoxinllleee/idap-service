package org.cmms.modules.tjbb.dkywfx.dkffmx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.dkywfx.dkffmx.entity.Dkffmx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 贷款发放明细
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
public interface DkffmxMapper extends BaseMapper<Dkffmx> {
    void pDkffmx(@Param("ksrq")String ksrq,@Param("jsrq")String jsrq);
}
