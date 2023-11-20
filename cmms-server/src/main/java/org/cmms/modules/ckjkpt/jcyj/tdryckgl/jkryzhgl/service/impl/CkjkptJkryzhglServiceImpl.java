package org.cmms.modules.ckjkpt.jcyj.tdryckgl.jkryzhgl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.jkryzhgl.entity.CkjkptJkryzhgl;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.jkryzhgl.mapper.CkjkptJkryzhglMapper;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.jkryzhgl.service.ICkjkptJkryzhglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-10
 * @Version: V1.0
 */
@Service
public class CkjkptJkryzhglServiceImpl extends ServiceImpl<CkjkptJkryzhglMapper, CkjkptJkryzhgl> implements ICkjkptJkryzhglService {
    @Autowired
    private CkjkptJkryzhglMapper ckjkptZhckpldglMapper;

    public CkjkptJkryzhgl queryByZjhm(String zjhm) {
        return ckjkptZhckpldglMapper.queryByZjhm(zjhm);
    }

    public void extract(Map<String,String> sql) {
        ckjkptZhckpldglMapper.extract(sql);
    }
}
