package org.cmms.modules.tjbb.dkywfx.dklxmx.czbxyflxmx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.dkywfx.dklxmx.czbxyflxmx.entity.Czbxyflxmx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 财政保险应付利息明细
 * @Author: jeecg-boot
 * @Date:   2021-08-23
 * @Version: V1.0
 */
public interface CzbxyflxmxMapper extends BaseMapper<Czbxyflxmx> {
    void pCzbxyflxmx(@Param("ksrq")String ksrq,@Param("jsrq")String jsrq);
}
