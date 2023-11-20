package org.cmms.modules.ywgl.ywl.ywlfpday.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.ywl.ywlfpday.entity.Ywlfpday;

/**
 * @Description: 业务量分配
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
public interface YwlfpdayMapper extends BaseMapper<Ywlfpday> {
    public Integer updateStatus(@Param("fpid") String fpid);
    public Integer updateYwlfpStatus(@Param("fpid") String fpid,@Param("fprq")String fprq,@Param("fpczy")String fpczy,@Param("type") String type,@Param("zzbz")String zzbz);
    public Ywlfpday getFpxxByMonth(@Param("zzbz")String zzbz,@Param("tjrq")String tjrq);

}
