package org.cmms.modules.khxxgl.dfpkh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khxxgl.dfpkh.entity.DfpWgkh;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 待分配网格客户
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
public interface DfpWgkhMapper extends BaseMapper<DfpWgkh> {
    public void extract();
}
