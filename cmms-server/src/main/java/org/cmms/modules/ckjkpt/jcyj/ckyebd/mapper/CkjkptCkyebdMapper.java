package org.cmms.modules.ckjkpt.jcyj.ckyebd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ckjkpt.jcyj.ckyebd.entity.CkjkptCkyebd;

import java.util.Map;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
public interface CkjkptCkyebdMapper extends BaseMapper<CkjkptCkyebd> {
    public void extract(Map<String,String> sql);
}
