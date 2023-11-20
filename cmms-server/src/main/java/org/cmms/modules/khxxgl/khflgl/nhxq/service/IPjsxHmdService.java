package org.cmms.modules.khxxgl.khflgl.nhxq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.PjsxHmd;

/**
 * @Description: 评级授信黑名单
 * @Author: jeecg-boot
 * @Date:   2023-11-15
 * @Version: V1.0
 */
public interface IPjsxHmdService extends IService<PjsxHmd> {
    void updateStatus(String id, String status);

}
