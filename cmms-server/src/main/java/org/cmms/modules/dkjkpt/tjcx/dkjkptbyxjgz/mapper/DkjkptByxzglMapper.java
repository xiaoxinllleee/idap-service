package org.cmms.modules.dkjkpt.tjcx.dkjkptbyxjgz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.dkjkpt.tjcx.dkjkptbyxjgz.entity.DkjkptByxzgl;

/**
 * @Description: 本月新进关注
 * @Author: jeecg-boot
 * @Date:   2023-08-30
 * @Version: V1.0
 */
public interface DkjkptByxzglMapper extends BaseMapper<DkjkptByxzgl> {
    public void extract(String tjyf);
}
