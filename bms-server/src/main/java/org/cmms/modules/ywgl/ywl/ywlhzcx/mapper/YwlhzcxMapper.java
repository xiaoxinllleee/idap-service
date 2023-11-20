package org.cmms.modules.ywgl.ywl.ywlhzcx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.ywl.ywlhzcx.entity.Ywlhzcx;

/**
 * @Description: 业务量汇总查询
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
public interface YwlhzcxMapper extends BaseMapper<Ywlhzcx> {
    void pYwlhzcx(@Param("tjyf")String tjyf);
}
