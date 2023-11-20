package org.cmms.modules.khxxgl.wbsjgl.bwtzgl.service.impl;

import org.cmms.modules.khxxgl.wbsjgl.bwtzgl.entity.LoanBwdkSjmx;
import org.cmms.modules.khxxgl.wbsjgl.bwtzgl.mapper.LoanBwdkSjmxMapper;
import org.cmms.modules.khxxgl.wbsjgl.bwtzgl.service.ILoanBwdkSjmxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 表外台账管理 
 * @Author: jeecg-boot
 * @Date:   2020-09-16
 * @Version: V1.0
 */
@Service
public class LoanBwdkSjmxServiceImpl extends ServiceImpl<LoanBwdkSjmxMapper, LoanBwdkSjmx> implements ILoanBwdkSjmxService {

    @Autowired
    private LoanBwdkSjmxMapper loanBwdkSjmxMapper;
    @Override
    public int deleteByDkzh(Integer xh) {
        return loanBwdkSjmxMapper.deleteByDkzh(xh);
    }

    @Override
    public LoanBwdkSjmx queryByDkzh(Integer xh) {
        return loanBwdkSjmxMapper.queryByDkzh(xh);
    }

    @Override
    public Integer queryXh() {
        return loanBwdkSjmxMapper.queryXh();
    }
}
