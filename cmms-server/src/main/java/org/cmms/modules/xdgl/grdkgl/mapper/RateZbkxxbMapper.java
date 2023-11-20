package org.cmms.modules.xdgl.grdkgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.RateZbkxxb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 指标库信息表
 * @Author: jeecg-boot
 * @Date:   2020-08-28
 * @Version: V1.0
 */
public interface RateZbkxxbMapper extends BaseMapper<RateZbkxxb> {

    public List<RateZbkxxb> queryzbkxxb(String qydm);
}
