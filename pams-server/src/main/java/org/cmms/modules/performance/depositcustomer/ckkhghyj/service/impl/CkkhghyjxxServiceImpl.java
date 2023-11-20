package org.cmms.modules.performance.depositcustomer.ckkhghyj.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.performance.depositcustomer.ckkhghyj.entity.Ckkhghyjxx;
import org.cmms.modules.performance.depositcustomer.ckkhghyj.mapper.CkkhghyjxxMapper;
import org.cmms.modules.performance.depositcustomer.ckkhghyj.service.ICkkhghyjxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 存款客户管户移交
 * @Author: Penghr
 * @Date:   2021-03-25
 * @Version: V1.0
 */
@Service

public class CkkhghyjxxServiceImpl extends ServiceImpl<CkkhghyjxxMapper, Ckkhghyjxx> implements ICkkhghyjxxService {

    @Autowired
    private CkkhghyjxxMapper mapper;

    @Override
    public String getProcessIdByProcessKey(String processKey) {
        return mapper.getProcessIdByProcessKey(processKey);
    }

    @Override
    public List<Ckkhghyjxx> getListByIds(List<String> ids) {
        return mapper.getListByIds(ids);
    }
}
