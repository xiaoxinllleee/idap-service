package org.cmms.modules.xyjlcx.hmdgl.hmdgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.xyjlcx.hmdgl.hmdgl.entity.Hmdgl;

/**
 * @Description: 黑名单管理
 * @Author: jeecg-boot
 * @Date:   2021-08-04
 * @Version: V1.0
 */
public interface HmdglMapper extends BaseMapper<Hmdgl> {
    void pHmdgl();
}
