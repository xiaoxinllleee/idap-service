package org.cmms.modules.tjbb.dkywfx.dklxmx.khlxmx.mapper;


import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.dkywfx.dklxmx.khlxmx.entity.Khlxmx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户利息明细
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
public interface KhlxmxMapper extends BaseMapper<Khlxmx> {
    void pKhlxmx(@Param("ksrq")String ksrq,@Param("jsrq")String jsrq);
}
