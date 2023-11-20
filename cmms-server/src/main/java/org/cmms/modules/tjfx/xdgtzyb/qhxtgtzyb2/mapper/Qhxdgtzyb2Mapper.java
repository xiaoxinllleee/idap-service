package org.cmms.modules.tjfx.xdgtzyb.qhxtgtzyb2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.xdgtzyb.qhxtgtzyb2.entity.Qhxdgtzyb2;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 全行行动挂图作业表2
 * @Author: jeecg-boot
 * @Date:   2020-03-13
 * @Version: V1.0
 */
public interface Qhxdgtzyb2Mapper extends BaseMapper<Qhxdgtzyb2> {
    public void extract(String tjyf);

}
