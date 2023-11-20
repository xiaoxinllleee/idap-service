package org.cmms.modules.ywgl.ywl.ywlfpday.service.impl;

import org.cmms.modules.ywgl.ywl.ywlfpday.entity.Ywlfpday;
import org.cmms.modules.ywgl.ywl.ywlfpday.mapper.YwlfpdayMapper;
import org.cmms.modules.ywgl.ywl.ywlfpday.service.IYwlfpdayService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 业务量分配
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@Service
public class YwlfpdayServiceImpl extends ServiceImpl<YwlfpdayMapper, Ywlfpday> implements IYwlfpdayService {

    @Override
    public Integer updateStatus(String fpid) {
        return baseMapper.updateStatus(fpid);
    }

    @Override
    public Integer updateYwlfpStatus(String fpid, String fprq, String fpczy,String type,String zzbz) {
        return baseMapper.updateYwlfpStatus(fpid,fprq,fpczy,type,zzbz);
    }

    @Override
    public Ywlfpday getFpxxByMonth(String zzbz, String tjrq) {
        return baseMapper.getFpxxByMonth(zzbz,tjrq);
    }
}
