package org.cmms.modules.tjfx.tjbb.wdkq.hfkqbb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.tjbb.wdkq.hfkqbb.entity.Tjfx_tjbb_khjlhfkqtj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-04-18
 * @Version: V1.0
 */
public interface Tjfx_tjbb_khjlhfkqtjMapper extends BaseMapper<Tjfx_tjbb_khjlhfkqtj> {
    public void extract(String tjyf);

}
