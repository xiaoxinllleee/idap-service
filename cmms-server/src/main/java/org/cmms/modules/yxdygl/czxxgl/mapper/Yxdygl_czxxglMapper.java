package org.cmms.modules.yxdygl.czxxgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.yxdygl.czxxgl.entity.Yxdygl_czxxgl;

/**
 * @Description: 村信息管理
 * @Author: cmms
 * @Date:   2019-11-05
 * @Version: V1.0
 */
public interface Yxdygl_czxxglMapper extends BaseMapper<Yxdygl_czxxgl> {
    public Yxdygl_czxxgl queryByQybm(String qybm);

    public List<Yxdygl_czxxgl> queryByCmc(String cmc);
}
