package org.cmms.modules.lydp.zbgl.lydpZbjg.mapper;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.cmms.modules.lydp.zbgl.lydpZbjg.entity.LydpZbjg;

import javax.xml.crypto.Data;

/**
 * @Description: 浏阳大屏指标结果表
 * @Author: jeecg-boot
 * @Date:   2023-02-15
 * @Version: V1.0
 */
public interface LydpZbjgMapper extends BaseMapper<LydpZbjg> {
    @Select("${sqlStr}")
    Double dynamicSql(@Param("sqlStr") String sql);

    @Insert("${sqlStr}")
    long dynamicDwSql(@Param("sqlStr") String sql);


    void dynamicCallSql(@Param("sqlStr") String sql);

//    @Select("select max(sjrq) from LOG_LYDP t where zt='1'")
//    Date dateMax();
}
