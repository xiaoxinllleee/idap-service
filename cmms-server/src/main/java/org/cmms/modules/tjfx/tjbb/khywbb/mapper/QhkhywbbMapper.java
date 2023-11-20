package org.cmms.modules.tjfx.tjbb.khywbb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.tjbb.khywbb.entity.Qhkhywbb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户业务报表(全行)
 * @Author: jeecg-boot
 * @Date:   2020-03-30
 * @Version: V1.0
 */
public interface QhkhywbbMapper extends BaseMapper<Qhkhywbb> {
    public void extractRC(String zxrkrq);
    public void extract();
}
