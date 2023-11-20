package org.cmms.modules.tjfx.pyqktj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.tjfx.pyqktj.entity.Pyqktj;

/**
 * @Description: 评议情况统计
 * @Author: jeecg-boot
 * @Date:   2022-12-30
 * @Version: V1.0
 */
public interface PyqktjMapper extends BaseMapper<Pyqktj> {
    public void init(String tjrq);
}
