package org.cmms.modules.tjfx.tpcfsctj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.tjfx.tpcfsctj.entity.Tpcfsctj;

/**
 * @Description: 图片重复上传统计
 * @Author: jeecg-boot
 * @Date:   2021-06-10
 * @Version: V1.0
 */
public interface TpcfsctjMapper extends BaseMapper<Tpcfsctj> {
    void init();
}
