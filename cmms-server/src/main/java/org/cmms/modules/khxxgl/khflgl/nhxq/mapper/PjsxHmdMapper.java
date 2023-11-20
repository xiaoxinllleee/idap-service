package org.cmms.modules.khxxgl.khflgl.nhxq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.PjsxHmd;

/**
 * @Description: 评级授信黑名单
 * @Author: jeecg-boot
 * @Date:   2023-11-15
 * @Version: V1.0
 */
public interface PjsxHmdMapper extends BaseMapper<PjsxHmd> {

    void updateStatus(@Param("id") String id,@Param("status") String status);
}
