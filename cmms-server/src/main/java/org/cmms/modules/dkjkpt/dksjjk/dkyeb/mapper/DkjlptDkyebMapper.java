package org.cmms.modules.dkjkpt.dksjjk.dkyeb.mapper;

import org.cmms.modules.dkjkpt.dksjjk.dkyeb.entity.DkjlptDkyeb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 贷款余额表
 * @Author: Penghr
 * @Date:   2020-03-31
 * @Version: V1.0
 */
public interface DkjlptDkyebMapper extends BaseMapper<DkjlptDkyeb> {

    void InitData(String tjrq);

}
