package org.cmms.modules.sjxf.hxxt.ckzdkb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.sjxf.hxxt.ckzdkb.entity.Ckzdkb;

/**
 * @Description: 存款主档宽表
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
public interface CkzdkbMapper extends BaseMapper<Ckzdkb> {
    String queryMaxDataDate();

    String getMaxLoadDate();
}
