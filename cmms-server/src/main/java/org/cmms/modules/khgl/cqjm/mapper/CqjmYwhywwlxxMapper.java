package org.cmms.modules.khgl.cqjm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.cqjm.entity.CqjmYwhywwlxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 城区居民与我行业务往来信息
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
public interface CqjmYwhywwlxxMapper extends BaseMapper<CqjmYwhywwlxx> {

    /**
     * 城区居民与我行业务往来信息-删除
     * @param zjhm
     */
    void deleteYwwlxxByZjhm(@Param("zjhm") String zjhm);

}
