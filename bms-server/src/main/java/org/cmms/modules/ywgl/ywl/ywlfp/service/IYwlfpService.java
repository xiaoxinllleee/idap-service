package org.cmms.modules.ywgl.ywl.ywlfp.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.ywl.ywlfp.entity.Ywlfp;
import org.cmms.modules.ywgl.ywl.ywlfpday.entity.YwlfpVo;

import java.math.BigDecimal;

/**
 * @Description: 业务量分配
 * @Author: jeecg-boot
 * @Date: 2021-09-30
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IYwlfpService extends IService<Ywlfp> {
    public Integer insertYwlmxBYday(YwlfpVo ywlfpVo);

    public Integer insertYwlmxQhBYMonth(String atmywbs, String atmxjll, String qtywbs, String qtxjll, String fpczy, String gwxx, String fprq);

    public Integer insertYwlmxZhBYMonth(String atmywbs, String atmxjll, String qtywbs, String qtxjll, Integer rs, String fpczy, String zzbz, String fprq, String ygghListString);
}
