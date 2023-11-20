package org.cmms.modules.xdgl.grdkgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.RateZbgzxxb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: 指标工资信息表
 * @Author: jeecg-boot
 * @Date:   2020-08-28
 * @Version: V1.0
 */
@Component
public interface RateZbgzxxbMapper extends BaseMapper<RateZbgzxxb> {

    public RateZbgzxxb queryzbgzxxb(String qydm,String zbid,String zbgzid);

    List<RateZbgzxxb> getPreData();
}
