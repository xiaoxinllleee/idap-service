package org.cmms.modules.ckjkpt.jcyj.zhckpldyj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ckjkpt.jcyj.zhckpldyj.entity.CkjkptZhckpldgl;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
public interface CkjkptZhckpldglMapper extends BaseMapper<CkjkptZhckpldgl> {
    public void extract(String tjyf);
}
