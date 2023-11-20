package org.cmms.modules.performance.loancustomer.dkhtzhxx.mapper;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.performance.loancustomer.dkhtzhxx.entity.Dkkhxxgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 贷款客户综合信息
 * @Author: jeecg-boot
 * @Date:   2023-04-03
 * @Version: V1.0
 */
public interface DkkhxxglMapper extends BaseMapper<Dkkhxxgl> {
    public void extract();

    void rldkkhxx(@Param("jgdm") String jgdm);
}
