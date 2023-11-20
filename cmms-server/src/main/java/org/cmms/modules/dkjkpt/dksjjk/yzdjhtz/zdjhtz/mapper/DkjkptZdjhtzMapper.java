package org.cmms.modules.dkjkpt.dksjjk.yzdjhtz.zdjhtz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.dkjkpt.dksjjk.yzdjhtz.zdjhtz.entity.DkjkptZdjhtz;

/**
 * @Description: 已制定计划台账
 * @Author: jeecg-boot
 * @Date:   2023-08-24
 * @Version: V1.0
 */
public interface DkjkptZdjhtzMapper extends BaseMapper<DkjkptZdjhtz> {
    public void init(String tjrq);
}
