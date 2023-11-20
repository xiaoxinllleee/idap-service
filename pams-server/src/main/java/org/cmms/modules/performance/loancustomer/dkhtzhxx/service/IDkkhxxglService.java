package org.cmms.modules.performance.loancustomer.dkhtzhxx.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.performance.loancustomer.dkhtzhxx.entity.Dkkhxxgl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 贷款客户综合信息
 * @Author: jeecg-boot
 * @Date:   2023-04-03
 * @Version: V1.0
 */
public interface IDkkhxxglService extends IService<Dkkhxxgl> {
    public void extract();

    void rldkkhxx(@Param("jgdm") String jgdm);

}
