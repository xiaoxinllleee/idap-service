package org.cmms.modules.performance.loancustomer.dkkhInformation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.performance.loancustomer.dkkhInformation.entity.DkkhInforMation;

/**
 * @Description: 贷款客户综合信息
 * @Author: jeecg-boot
 * @Date:   2023-04-03
 * @Version: V1.0
 */
public interface IDkkhInforMationService extends IService<DkkhInforMation> {
    public void extract();
}
