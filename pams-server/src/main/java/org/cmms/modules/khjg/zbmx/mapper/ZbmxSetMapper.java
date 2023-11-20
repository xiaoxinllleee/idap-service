package org.cmms.modules.khjg.zbmx.mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.cmms.modules.khjg.zbmx.entity.ZbmxSet;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 指标明细设置
 * @Author: jeecg-boot
 * @Date:   2023-04-10
 * @Version: V1.0
 */
public interface ZbmxSetMapper extends BaseMapper<ZbmxSet> {
    @Select("${sqlStr}")
    List<LinkedHashMap> execZbmx(@Param("sqlStr") String sqlStr);

    @Select("${sqlStr}")
    Long execCount(@Param("sqlStr") String sqlStr);
}
