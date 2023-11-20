package org.cmms.modules.dkjkpt.dksjjk.dedkjc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.dkjkpt.dksjjk.dedkjc.entity.DkjkptDedkjc;

/**
 * @Description: 大额贷款监测
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
public interface DkjkptDedkjcMapper extends BaseMapper<DkjkptDedkjc> {
    public void extract(String tjyf);
}
