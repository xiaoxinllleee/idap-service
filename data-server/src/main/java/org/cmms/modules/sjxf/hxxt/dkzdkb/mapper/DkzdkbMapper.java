package org.cmms.modules.sjxf.hxxt.dkzdkb.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.sjxf.hxxt.dkzdkb.entity.Dkzdkb;

/**
 * @Description: 贷款主档宽表
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
public interface DkzdkbMapper extends BaseMapper<Dkzdkb> {
    String queryMaxDataDate();

    double queryDkye(@Param("zjhm") String zjhm);
}
