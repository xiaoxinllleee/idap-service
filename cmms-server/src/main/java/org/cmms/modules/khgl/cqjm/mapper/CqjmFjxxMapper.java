package org.cmms.modules.khgl.cqjm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.cqjm.entity.CqjmFjxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 城区居民附件信息
 * @Author: jeecg-boot
 * @Date:   2020-02-22
 * @Version: V1.0
 */
public interface CqjmFjxxMapper extends BaseMapper<CqjmFjxx> {

    void deleteByZjhm(@Param("zjhm") String zjhm);

}
