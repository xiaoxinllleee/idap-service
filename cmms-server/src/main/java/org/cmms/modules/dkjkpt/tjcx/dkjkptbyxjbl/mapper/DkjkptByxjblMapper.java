package org.cmms.modules.dkjkpt.tjcx.dkjkptbyxjbl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.dkjkpt.tjcx.dkjkptbyxjbl.entity.DkjkptByxjbl;

/**
 * @Description: 本月新进不良
 * @Author: jeecg-boot
 * @Date:   2023-08-30
 * @Version: V1.0
 */
public interface DkjkptByxjblMapper extends BaseMapper<DkjkptByxjbl> {
    public void extract(String tjyf);
}
