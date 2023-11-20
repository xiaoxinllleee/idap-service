package org.cmms.modules.dkjkpt.cssz.dkqxcssz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.cssz.dkqxcssz.entity.Dkjkpt_dkqxcssz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 贷款期限参数设置
 * @Author: jeecg-boot
 * @Date:   2020-11-06
 * @Version: V1.0
 */
public interface Dkjkpt_dkqxcsszMapper extends BaseMapper<Dkjkpt_dkqxcssz> {

    public Dkjkpt_dkqxcssz selectByCsbh(@Param("csbh")String csbh);

    public Integer deleteByCsbh(@Param("csbh")String csbh);

    public Integer updateByCsbh(@Param("cs")Dkjkpt_dkqxcssz cs);

}
