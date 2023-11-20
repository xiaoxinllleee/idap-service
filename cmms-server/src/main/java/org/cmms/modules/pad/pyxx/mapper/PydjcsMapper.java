package org.cmms.modules.pad.pyxx.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.pad.pyxx.entity.Pydjcs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 评议得分对应等级额度
 * @Author: jeecg-boot
 * @Date:   2020-07-31
 * @Version: V1.0
 */
public interface PydjcsMapper extends BaseMapper<Pydjcs> {
    public Pydjcs getPddjAndJysxde(@Param("pydf") BigDecimal pydf);
}
