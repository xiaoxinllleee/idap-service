package org.cmms.modules.yxdygl.sjyxdygl.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.yxdygl.sjyxdygl.entity.EjyxdyglReuse;
import org.cmms.modules.yxdygl.sjyxdygl.entity.Sjyxdygl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: 三级营销单元管理
 * @Author: Penghr
 * @Date:   2020-07-18
 * @Version: V1.0
 */
@Component
public interface SjyxdyglMapper extends BaseMapper<Sjyxdygl> {

    List<EjyxdyglReuse> QuerySszhByYjyxdybh(@Param("ejyxdybh") String ejyxdybh);

    Sjyxdygl queryDataByDybh(Map<String,String> sql);

    String queryDybhBySsyxdy(String ssyxdy);

    List<Sjyxdygl> queryDataByUser(String username);
    /**
     * 根据所属支行，二级编号，一级编号去查询列表
     * */
    List<Sjyxdygl> queryDataByEjAndYjAndSszh(String yj,String ej,String sszh);
}
