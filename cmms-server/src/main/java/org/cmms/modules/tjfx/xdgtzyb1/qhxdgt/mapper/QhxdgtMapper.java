package org.cmms.modules.tjfx.xdgtzyb1.qhxdgt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.xdgtzyb1.qhxdgt.entity.Qhxdgt;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-16
 * @Version: V1.0
 */
public interface QhxdgtMapper extends BaseMapper<Qhxdgt> {
    public void extract(String ksrq,String jsrq,String zzbz);
}
