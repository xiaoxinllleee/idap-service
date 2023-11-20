package org.cmms.modules.ywgl.ywl.ywlfp.service.impl;

import org.cmms.modules.ywgl.ywl.ywlfp.entity.Ywlfp;
import org.cmms.modules.ywgl.ywl.ywlfp.mapper.YwlfpMapper;
import org.cmms.modules.ywgl.ywl.ywlfp.service.IYwlfpService;
import org.cmms.modules.ywgl.ywl.ywlfpday.entity.YwlfpVo;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;

/**
 * @Description: 业务量分配
 * @Author: jeecg-boot
 * @Date:   2021-09-30
 * @Version: V1.0
 */
@Service
public class YwlfpServiceImpl extends ServiceImpl<YwlfpMapper, Ywlfp> implements IYwlfpService {

    @Override
    public Integer insertYwlmxBYday(YwlfpVo ywlfpVo) {
        return baseMapper.insertYwlmxBYday(ywlfpVo);
    }

    @Override
    public Integer insertYwlmxQhBYMonth(String atmywbs, String atmxjll, String qtywbs, String qtxjll, String fpczy, String gwxx,String fprq) {
        return baseMapper.insertYwlmxQhBYMonth(atmywbs,atmxjll,qtywbs,qtxjll,fpczy,gwxx,fprq);
    }

    @Override
    public Integer insertYwlmxZhBYMonth(String atmywbs, String atmxjll, String qtywbs, String qtxjll, Integer rs, String fpczy, String zzbz, String fprq, String ygghListString) {
        return baseMapper.insertYwlmxZhBYMonth(atmywbs,atmxjll,qtywbs,qtxjll,rs,fpczy,zzbz,fprq,ygghListString);
    }
}
