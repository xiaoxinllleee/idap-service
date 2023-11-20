package org.cmms.modules.ygjx.service;

import org.cmms.modules.ygjx.entity.BasicWageAllowabces;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ygjx.entity.GzMxVO;

import java.math.BigDecimal;

/**
 * @Description: 津贴
 * @Author: jeecg-boot
 * @Date:   2022-03-02
 * @Version: V1.0
 */
public interface IBasicWageAllowabcesService extends IService<BasicWageAllowabces> {

    GzMxVO getByYggh(String yggh,String date);

    BigDecimal jbgz(String yggh,String date);
}
