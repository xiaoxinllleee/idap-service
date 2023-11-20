package org.cmms.modules.ywgl.ywl.ywlfpday.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.ywl.ywlfpday.entity.Ywlfpday;

/**
 * @Description: 业务量分配
 * @Author: jeecg-boot
 * @Date: 2021-12-07
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IYwlfpdayService extends IService<Ywlfpday> {
    public Integer updateStatus(String fpid);
    public Integer updateYwlfpStatus(String fpid,String fprq,String fpczy,String type,String zzbz);
    public Ywlfpday getFpxxByMonth(String zzbz, String tjrq);
}
