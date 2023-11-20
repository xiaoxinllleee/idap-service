package org.cmms.modules.tjfx.khzftj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.tjfx.khzftj.entity.Khzftj;

/**
 * @Description: 客户走访统计
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
public interface KhzftjMapper extends BaseMapper<Khzftj> {

    public void extract(String tjrq);

}
