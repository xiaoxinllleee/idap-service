package org.cmms.modules.dkjkpt.dksjjk.zhdkshtj.mapper;

import org.cmms.modules.dkjkpt.dksjjk.zhdkshtj.entity.Zhdkshtj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行贷款收回统计
 * @Author: cmms
 * @Date:   2019-09-10
 * @Version: V1.0
 */
public interface ZhdkshtjMapper extends BaseMapper<Zhdkshtj> {
    public void init(String tjyf);

}
