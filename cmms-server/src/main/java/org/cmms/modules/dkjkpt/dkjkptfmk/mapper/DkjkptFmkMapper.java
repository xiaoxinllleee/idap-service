package org.cmms.modules.dkjkpt.dkjkptfmk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.dkjkpt.dkjkptfmk.entity.DkjkptFmk;

/**
 * @Description: 福民卡
 * @Author: jeecg-boot
 * @Date:   2023-09-21
 * @Version: V1.0
 */
public interface DkjkptFmkMapper extends BaseMapper<DkjkptFmk> {
    public void fmkUpdate();
}
