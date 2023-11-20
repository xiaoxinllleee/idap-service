package org.cmms.modules.xdgl.grdkgl.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.Grdkcjxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 个人贷款采集信息
 * @Author: jeecg-boot
 * @Date:   2020-08-17
 * @Version: V1.0
 */
public interface GrdkcjxxMapper extends BaseMapper<Grdkcjxx> {

    public void updateSxed(@Param("pddj") String pddj,@Param("zzsxed") BigDecimal zzsxed, @Param("yj") String yj, @Param("zjhm") String zjhm);

    public void updateSpjled(@Param("pddj") String pddj, @Param("zzsxed") BigDecimal zzsxed, @Param("yj") String yj, @Param("spid") String spid);
}
