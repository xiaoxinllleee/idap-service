package org.cmms.modules.xdgl.grdkgl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.KhglKhhmcxxGrxd;
import org.springframework.stereotype.Component;


/**
 * @Description: 个人贷款花名册
 * @Author: jeecg-boot
 * @Date:   2020-08-22
 * @Version: V1.0
 */
@Component
public interface KhglKhhmcxxGrxdMapper extends BaseMapper<KhglKhhmcxxGrxd> {
    int updateDzByZjhm(@Param("zjhm")String zjhm, @Param("dz")String dz);
}
