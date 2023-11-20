package org.cmms.modules.dkjkpt.dksjjk.dksjahtj.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dksjjk.dksjahtj.entity.Dkyeb;
import org.cmms.modules.dkjkpt.dksjjk.dksjahtj.mapper.DkyebMapper;
import org.cmms.modules.dkjkpt.dksjjk.dksjahtj.service.IDkyebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 贷款余额表
 * @Author: Penghr
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@Service
public class DkyebServiceImpl extends ServiceImpl<DkyebMapper, Dkyeb> implements IDkyebService {

    @Autowired
    private DkyebMapper mapper;

    @Override
    public List<Dkyeb> queryDhdksjmx(String tjrq, String jgdm, String zjhm) {
        return mapper.queryDhdksjmx(tjrq, jgdm, zjhm);
    }
}
