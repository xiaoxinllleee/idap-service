package org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqckzhgl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqckzhgl.entity.CkjkptTdrqckzhgl;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqckzhgl.mapper.CkjkptTdrqckzhglMapper;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqckzhgl.service.ICkjkptTdrqckzhglService;
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
public class CkjkptTdrqckzhglServiceImpl extends ServiceImpl<CkjkptTdrqckzhglMapper, CkjkptTdrqckzhgl> implements ICkjkptTdrqckzhglService {
    @Autowired
    private CkjkptTdrqckzhglMapper ckjkptZhckpldglMapper;

    public CkjkptTdrqckzhgl queryByZjhm(String zjhm) {
        return ckjkptZhckpldglMapper.queryByZjhm(zjhm);
    }

    public void extract(Map<String,String> sql) {
        ckjkptZhckpldglMapper.extract(sql);
    }
}
