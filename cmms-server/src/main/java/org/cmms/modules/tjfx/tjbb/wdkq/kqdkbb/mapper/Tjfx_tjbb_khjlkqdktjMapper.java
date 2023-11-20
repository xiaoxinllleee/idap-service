package org.cmms.modules.tjfx.tjbb.wdkq.kqdkbb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.tjbb.wdkq.kqdkbb.entity.Tjfx_tjbb_khjlkqdktj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-04-17
 * @Version: V1.0
 */
public interface Tjfx_tjbb_khjlkqdktjMapper extends BaseMapper<Tjfx_tjbb_khjlkqdktj> {
    public void extract(String tjyf);
}
