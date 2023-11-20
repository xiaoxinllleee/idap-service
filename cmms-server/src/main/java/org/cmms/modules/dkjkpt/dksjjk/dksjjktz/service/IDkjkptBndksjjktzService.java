package org.cmms.modules.dkjkpt.dksjjk.dksjjktz.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.entity.DkjkptBndksjjktz;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;

/**
 * @Description: 贷款数据监控台账
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IDkjkptBndksjjktzService extends IService<DkjkptBndksjjktz> {
    public DkjkptBndksjjktz queryByDkzh(String dkzh);

    public void init();
    //贷款日期起
    public DkjkptBndksjjktz queryDkrqq(String dkzh);
    //贷款形态
    public String queryDkxt(String table,String dkzh);
}
