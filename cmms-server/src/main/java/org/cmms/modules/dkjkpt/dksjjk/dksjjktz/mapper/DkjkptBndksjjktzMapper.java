package org.cmms.modules.dkjkpt.dksjjk.dksjjktz.mapper;


import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.entity.DkjkptBndksjjktz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Date;

/**
 * @Description: 贷款数据监控台账
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
public interface DkjkptBndksjjktzMapper extends BaseMapper<DkjkptBndksjjktz> {
    public DkjkptBndksjjktz queryByDkzh(String dkzh);

    public void init();
    //贷款日期起
    public DkjkptBndksjjktz queryDkrqq(String dkzh);
    //贷款形态
    public String queryDkxt(String table,String dkzh);
}
