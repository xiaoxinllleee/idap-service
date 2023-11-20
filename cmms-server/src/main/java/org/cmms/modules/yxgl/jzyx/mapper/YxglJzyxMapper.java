package org.cmms.modules.yxgl.jzyx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.yxgl.jzyx.entity.YxglJzyx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 精准营销
 * @Author: cmms
 * @Date:   2019-12-17
 * @Version: V1.0
 */
public interface YxglJzyxMapper extends BaseMapper<YxglJzyx> {

    public void init();

}
