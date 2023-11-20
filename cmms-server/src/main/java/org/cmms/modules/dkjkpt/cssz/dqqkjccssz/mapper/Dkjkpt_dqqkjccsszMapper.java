package org.cmms.modules.dkjkpt.cssz.dqqkjccssz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.cssz.dkqxcssz.entity.Dkjkpt_dkqxcssz;
import org.cmms.modules.dkjkpt.cssz.dqqkjccssz.entity.Dkjkpt_dqqkjccssz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 到期情况监测参数设置
 * @Author: jeecg-boot
 * @Date:   2020-11-06
 * @Version: V1.0
 */
public interface Dkjkpt_dqqkjccsszMapper extends BaseMapper<Dkjkpt_dqqkjccssz> {
    public Integer deleteByCsbh(@Param("csbh")String csbh);

    public Integer updateByCsbh(@Param("cs") Dkjkpt_dkqxcssz cs);
}
