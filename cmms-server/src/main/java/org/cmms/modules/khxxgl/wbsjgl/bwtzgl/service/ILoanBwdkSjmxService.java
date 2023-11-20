package org.cmms.modules.khxxgl.wbsjgl.bwtzgl.service;

import org.cmms.modules.khxxgl.wbsjgl.bwtzgl.entity.LoanBwdkSjmx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 表外台账管理 
 * @Author: jeecg-boot
 * @Date:   2020-09-16
 * @Version: V1.0
 */
public interface ILoanBwdkSjmxService extends IService<LoanBwdkSjmx> {
    public int deleteByDkzh(Integer xh);

    public LoanBwdkSjmx queryByDkzh(Integer xh);

    public Integer queryXh();

}
