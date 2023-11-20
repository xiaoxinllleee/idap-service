package org.cmms.modules.khxxgl.wbsjgl.bwtzgl.mapper;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khxxgl.wbsjgl.bwtzgl.entity.LoanBwdkSjmx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 表外台账管理 
 * @Author: jeecg-boot
 * @Date:   2020-09-16
 * @Version: V1.0
 */
public interface LoanBwdkSjmxMapper extends BaseMapper<LoanBwdkSjmx> {

    public int deleteByDkzh(@Param("xh") Integer xh);

    public LoanBwdkSjmx queryByDkzh(@Param("xh") Integer xh);

    public Integer queryXh();
}
