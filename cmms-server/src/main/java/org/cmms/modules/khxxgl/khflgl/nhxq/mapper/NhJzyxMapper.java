package org.cmms.modules.khxxgl.khflgl.nhxq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.NhJzyx;

/**
 * @Description: 农户精准营销试图
 * @Author: jeecg-boot
 * @Date:   2023-05-19
 * @Version: V1.0
 */
public interface NhJzyxMapper extends BaseMapper<NhJzyx> {
      void jzyxInit(String nhid);
}
