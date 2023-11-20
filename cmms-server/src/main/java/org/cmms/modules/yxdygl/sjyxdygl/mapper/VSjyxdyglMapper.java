package org.cmms.modules.yxdygl.sjyxdygl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.yxdygl.sjyxdygl.entity.VSjyxdygl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 三级营销单元管理
 * @Author: Penghr
 * @Date:   2020-07-18
 * @Version: V1.0
 */
public interface VSjyxdyglMapper extends BaseMapper<VSjyxdygl> {

    /**
     * 根据"三级营销单元编号"获取"所属支行"
     * @param dybh
     * @return
     */
    String querySszhBySjyxdybh(@Param("dybh") String dybh);

}
