package org.cmms.modules.xdgl.grdkgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.GrdkDbxx;
import org.cmms.modules.xdgl.grdkgl.entity.GrdkJtsp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: 集体审批书
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
@Component
public interface GrdkJtspMapper extends BaseMapper<GrdkJtsp> {

    public Integer updById(@Param("id")String id);

    public List<GrdkDbxx> getDbxx(@Param("zjhm")String zjhm);
}
