package org.cmms.modules.xyjlcx.bwdkgl.sbgxmx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xyjlcx.bwdkgl.sbgxmx.entity.Sbgxmx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 收本挂息明细
 * @Author: jeecg-boot
 * @Date:   2021-08-16
 * @Version: V1.0
 */
public interface SbgxmxMapper extends BaseMapper<Sbgxmx> {
    void pSbgxmx();
}
