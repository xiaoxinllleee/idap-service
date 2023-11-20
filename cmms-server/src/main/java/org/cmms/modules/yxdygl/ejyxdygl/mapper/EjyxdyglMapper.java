package org.cmms.modules.yxdygl.ejyxdygl.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.yxdygl.ejyxdygl.entity.Ejyxdygl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: 二级营销单元管理
 * @Author: Penghr
 * @Date:   2020-07-17
 * @Version: V1.0
 */
@Component
public interface EjyxdyglMapper extends BaseMapper<Ejyxdygl> {

    Ejyxdygl queryDataByDybh(Map<String,String> sql);

    List<Ejyxdygl> queryDataByUser(String username);

    String queryDybhBySsyxdy(String ssyxdy);
}
