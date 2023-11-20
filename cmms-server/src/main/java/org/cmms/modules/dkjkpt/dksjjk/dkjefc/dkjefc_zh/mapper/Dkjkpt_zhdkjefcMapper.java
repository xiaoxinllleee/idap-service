package org.cmms.modules.dkjkpt.dksjjk.dkjefc.dkjefc_zh.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.dksjjk.dkjefc.dkjefc_zh.entity.Dkjkpt_zhdkjefc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 贷款金额分成_支行
 * @Author: jeecg-boot
 * @Date:   2020-11-06
 * @Version: V1.0
 */
public interface Dkjkpt_zhdkjefcMapper extends BaseMapper<Dkjkpt_zhdkjefc> {
    void InitDataToQh(Map<String, String> sql);

}
