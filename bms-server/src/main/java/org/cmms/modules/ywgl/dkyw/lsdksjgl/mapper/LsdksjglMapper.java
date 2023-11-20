package org.cmms.modules.ywgl.dkyw.lsdksjgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.entity.Lsdksjgl;

/**
 * @Description: 历史贷款数据管理
 * @Author: jeecg-boot
 * @Date:   2021-09-24
 * @Version: V1.0
 */
public interface LsdksjglMapper extends BaseMapper<Lsdksjgl> {
    String getByName(@Param("ygxm") String ygxm);
}
