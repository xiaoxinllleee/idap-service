package org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqckzhgl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqckzhgl.entity.CkjkptTdrqckzhgl;

import java.util.Map;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-10
 * @Version: V1.0
 */
public interface CkjkptTdrqckzhglMapper extends BaseMapper<CkjkptTdrqckzhgl> {
    public void extract(Map<String,String> sql);
    public CkjkptTdrqckzhgl queryByZjhm(String zjhm);
}
