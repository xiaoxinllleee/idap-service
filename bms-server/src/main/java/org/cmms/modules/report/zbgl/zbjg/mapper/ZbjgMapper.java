package org.cmms.modules.report.zbgl.zbjg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.cmms.modules.report.zbgl.zbjg.entity.Zbjg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 指标结果
 * @Author: jeecg-boot
 * @Date:   2022-03-29
 * @Version: V1.0
 */
public interface ZbjgMapper extends BaseMapper<Zbjg> {
    @Select("${sqlStr}")
    Double dynamicSql(@Param("sqlStr") String sql);

    @Insert("${sqlStr}")
    long dynamicDwSql(@Param("sqlStr") String sql);


    void dynamicCallSql(@Param("sqlStr") String sql);

}
