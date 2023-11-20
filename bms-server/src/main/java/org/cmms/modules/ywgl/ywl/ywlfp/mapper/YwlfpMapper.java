package org.cmms.modules.ywgl.ywl.ywlfp.mapper;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.ywl.ywlfp.entity.Ywlfp;
import org.cmms.modules.ywgl.ywl.ywlfpday.entity.YwlfpVo;

/**
 * @Description: 业务量分配
 * @Author: jeecg-boot
 * @Date:   2021-09-30
 * @Version: V1.0
 */
public interface YwlfpMapper extends BaseMapper<Ywlfp> {
    public Integer insertYwlmxBYday(@Param("ywlfpVo") YwlfpVo ywlfpVo);

    public Integer insertYwlmxQhBYMonth(@Param("atmywbs")String atmywbs,
                                        @Param("atmxjll")String atmxjll,
                                        @Param("qtywbs")String qtywbs,
                                        @Param("qtxjll")String qtxjll,
                                        @Param("fpczy")String fpczy,
                                        @Param("gwxx")String gwxx,
                                        @Param("fprq")String fprq);

    public Integer insertYwlmxZhBYMonth(@Param("atmywbs")String atmywbs,
                                        @Param("atmxjll")String atmxjll,
                                        @Param("qtywbs")String qtywbs,
                                        @Param("qtxjll")String qtxjll,
                                        @Param("rs")Integer rs,
                                        @Param("fpczy")String fpczy,
                                        @Param("zzbz")String zzbz,
                                        @Param("fprq")String fprq,
                                        @Param("ygghListString")String ygghListString);
}
