package org.cmms.modules.xddaglxt.dksjgl.dksjjktz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.xddaglxt.dksjgl.dksjjktz.entity.Dksjjktz;

/**
 * @Description: 贷款数据监控台账
 * @Author: jeecg-boot
 * @Date:   2021-11-22
 * @Version: V1.0
 */
public interface DksjjktzMapper extends BaseMapper<Dksjjktz> {
    void pDksjjktz();
}
