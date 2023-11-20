package org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqcklsjk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqcklsjk.entity.CkjkptTdrqcklsjk;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-10
 * @Version: V1.0
 */
public interface CkjkptTdrqcklsjkMapper extends BaseMapper<CkjkptTdrqcklsjk> {
    public void extract(String tjyf);
}
