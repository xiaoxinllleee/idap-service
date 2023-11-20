package org.cmms.modules.khgl.cqjm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.cqjm.entity.CqjmJbxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 城区居民功能包
 * @Author: jeecg-boot
 * @Date:   2020-02-20
 * @Version: V1.0
 */
public interface CqjmJbxxMapper extends BaseMapper<CqjmJbxx> {

    /**
     * 城区居民 基本信息删除
     * @param id
     * @param dabh
     * @param zjhm
     */
    void deleteByIdAndDabhAndZjhm(@Param("id") String id,@Param("dabh") String dabh,@Param("zjhm") String zjhm);

    /**
     * 城区居民 根据ID删除基本信息
     * @param zjhm
     */
    void deleteByZjhm(@Param("zjhm") String zjhm);

}
