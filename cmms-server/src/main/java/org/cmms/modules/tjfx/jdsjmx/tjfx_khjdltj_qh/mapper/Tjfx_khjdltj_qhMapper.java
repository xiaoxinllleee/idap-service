package org.cmms.modules.tjfx.jdsjmx.tjfx_khjdltj_qh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.jdsjmx.tjfx_khjdltj_qh.entity.Tjfx_khjdltj_qh;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-20
 * @Version: V1.0
 */
public interface Tjfx_khjdltj_qhMapper extends BaseMapper<Tjfx_khjdltj_qh> {
    public void extract(String tjyf);
}
