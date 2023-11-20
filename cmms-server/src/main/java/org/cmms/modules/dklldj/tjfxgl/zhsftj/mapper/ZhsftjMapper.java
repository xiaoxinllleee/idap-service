package org.cmms.modules.dklldj.tjfxgl.zhsftj.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.tjfxgl.zhsftj.entity.Zhsftj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行上浮统计
 * @Author: Penghr
 * @Date:   2020-03-28
 * @Version: V1.0
 */
public interface ZhsftjMapper extends BaseMapper<Zhsftj> {

    void init(Map<String, String> sql);

}
