package org.cmms.modules.khgl.grkhgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.grkhgl.entity.ZcsxNhcjxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

/**
 * @Description: 农户采集信息
 * @Author: jeecg-boot
 * @Date:   2020-07-06
 * @Version: V1.0
 */
@Component
public interface ZcsxNhcjxxMapper extends BaseMapper<ZcsxNhcjxx> {

    public boolean deleteByMainId(@Param("zjhm")String zjhm);

    public int updateCjzt(@Param("id")String id);
}
