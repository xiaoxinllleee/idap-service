package org.cmms.modules.xyjlcx.sszxgl.ssgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xyjlcx.sszxgl.ssgl.entity.Ssgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 诉讼管理
 * @Author: jeecg-boot
 * @Date:   2021-08-16
 * @Version: V1.0
 */
public interface SsglMapper extends BaseMapper<Ssgl> {
    void pSsgl();
}
