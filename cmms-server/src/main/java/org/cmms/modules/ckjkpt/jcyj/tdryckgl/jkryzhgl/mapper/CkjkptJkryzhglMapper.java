package org.cmms.modules.ckjkpt.jcyj.tdryckgl.jkryzhgl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.jkryzhgl.entity.CkjkptJkryzhgl;

import java.util.Map;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-10
 * @Version: V1.0
 */
public interface CkjkptJkryzhglMapper extends BaseMapper<CkjkptJkryzhgl> {
    public void extract(Map<String,String> sql);
    public CkjkptJkryzhgl queryByZjhm(String zjhm);
}
