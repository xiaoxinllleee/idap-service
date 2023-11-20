package org.cmms.modules.ckjkpt.jcyj.debdcx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ckjkpt.jcyj.debdcx.entity.CkjkptDebdcx;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-10
 * @Version: V1.0
 */
public interface CkjkptDebdcxMapper extends BaseMapper<CkjkptDebdcx> {
    public void extract(String tjyf);
}
