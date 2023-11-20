package org.cmms.modules.khgl.cqjm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.cqjm.entity.CqjmZcfzqk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 城区居民资产负债情况
 * @Author: jeecg-boot
 * @Date:   2020-02-24
 * @Version: V1.0
 */
public interface CqjmZcfzqkMapper extends BaseMapper<CqjmZcfzqk> {

    List<CqjmZcfzqk> selectByZjhm(String zjhm);

    void deleteZcfzqkByZjhm(@Param("zjhm") String zjhm);

}
