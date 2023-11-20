package org.cmms.modules.khxxgl.khflgl.nhxq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.KhxxglHnkd;

/**
 * @Description: 惠农快贷
 * @Author: jeecg-boot
 * @Date:   2022-01-07
 * @Version: V1.0
 */
public interface IKhxxglHnkdService extends IService<KhxxglHnkd> {
    void updateHongMingDan();

    String hnkdMessage(KhxxglHnkd khxxglHnkd);

    void updateResult(String id,String impResult);
}
