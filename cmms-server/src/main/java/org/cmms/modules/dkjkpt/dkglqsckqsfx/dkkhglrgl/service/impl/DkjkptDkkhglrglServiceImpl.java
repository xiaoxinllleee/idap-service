package org.cmms.modules.dkjkpt.dkglqsckqsfx.dkkhglrgl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.dkkhglrgl.entity.DkjkptDkkhglrgl;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.dkkhglrgl.mapper.DkjkptDkkhglrglMapper;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.dkkhglrgl.service.IDkjkptDkkhglrglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Description: 贷款客户关联人管理
 * @Author: cmms
 * @Date:   2019-10-11
 * @Version: V1.0
 */
@Service
public class DkjkptDkkhglrglServiceImpl extends ServiceImpl<DkjkptDkkhglrglMapper, DkjkptDkkhglrgl> implements IDkjkptDkkhglrglService {
    @Autowired
    private DkjkptDkkhglrglMapper dkjkptDkkhglrglMapper;

    public  DkjkptDkkhglrgl deleteByMainId(String jkrzjhm,String glrzjhm ){
        return dkjkptDkkhglrglMapper.deleteByMainId(jkrzjhm,glrzjhm);
    }

    public DkjkptDkkhglrgl queryByZjhm (Map<String,String>sql) {
        return dkjkptDkkhglrglMapper.queryByZjhm(sql);
    }
    public void extract(){
        dkjkptDkkhglrglMapper.extract();
    }
}
