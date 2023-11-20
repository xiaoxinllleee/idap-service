package org.cmms.modules.dkjkpt.dksjjk.dksjjktz.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.entity.DkjkptBndksjjktz;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.mapper.DkjkptBndksjjktzMapper;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.service.IDkjkptBndksjjktzService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description: 贷款数据监控台账
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@Service
public class DkjkptBndksjjktzServiceImpl extends ServiceImpl<DkjkptBndksjjktzMapper, DkjkptBndksjjktz> implements IDkjkptBndksjjktzService {
    @Resource
    private DkjkptBndksjjktzMapper mapper;

    @Override
    public DkjkptBndksjjktz queryByDkzh(String dkzh) {
        return mapper.queryByDkzh(dkzh);
    }

    @Override
    public void init() {
        mapper.init();
    }

    @Override
    public DkjkptBndksjjktz queryDkrqq(String dkzh) {
        return mapper.queryDkrqq(dkzh);
    }

    @Override
    public String queryDkxt(String table,String dkzh) {
        try {
            String s = mapper.queryDkxt(table, dkzh);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
