package org.cmms.modules.khxxgl.khflgl.nhxq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.KhxxglHnkd;

/**
 * @Description: 惠农快贷
 * @Author: jeecg-boot
 * @Date:   2022-01-07
 * @Version: V1.0
 */
public interface KhxxglHnkdMapper extends BaseMapper<KhxxglHnkd> {
    void updateHongMingDan();

    public void updateResult(@Param("id") String id,@Param("impResult") String impResult);
}
