package org.cmms.modules.tjfx.sxsjmx.tjfx_khsxyxtj_qh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.sxsjmx.tjfx_khsxyxtj_qh.entity.Tjfx_khsxyxtj_qh;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-19
 * @Version: V1.0
 */
public interface Tjfx_khsxyxtj_qhMapper extends BaseMapper<Tjfx_khsxyxtj_qh> {
    public void extract(String tjyf);
}
