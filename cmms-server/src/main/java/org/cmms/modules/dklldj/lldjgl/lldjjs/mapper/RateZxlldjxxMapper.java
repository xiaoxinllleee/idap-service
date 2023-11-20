package org.cmms.modules.dklldj.lldjgl.lldjjs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.lldjgl.lldjjs.entity.RateZxlldjxx;
import org.cmms.modules.sjxf.hxxt.zzzhzb.entity.Zzzhzb;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface RateZxlldjxxMapper extends BaseMapper<RateZxlldjxx> {

    /*RateZxlldjxx queryZxlldjxxOracle(@Param("djnf") Date djnf,@Param("zjhm") String zjhm);
    RateZxlldjxx queryZxlldjxxHive(@Param("djnf") Date djnf,@Param("zjhm") String zjhm);*/

    /*int updateSpztOracle(@Param("djid") String djid,@Param("spzt") String spzt,@Param("note") String note);
    int updateSpztHive(@Param("djid") String djid,@Param("spzt") String spzt,@Param("note") String note);*/

    /*String queryALprJJdbpOracle(@Param("djdf") String djdf,@Param("dkqx") String dkqx,@Param("cdck") String cdck);
    String queryALprJJdbpHive(@Param("djdf") String djdf,@Param("dkqx") String dkqx,@Param("cdck") String cdck);*/

    String getMaxDjidHive();

}
