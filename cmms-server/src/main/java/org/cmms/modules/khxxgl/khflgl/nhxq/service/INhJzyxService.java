package org.cmms.modules.khxxgl.khflgl.nhxq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.NhJzyx;

/**
 * @Description: 农户精准营销试图
 * @Author: jeecg-boot
 * @Date:   2023-05-19
 * @Version: V1.0
 */
public interface INhJzyxService extends IService<NhJzyx> {
    void jzyxInit(String nhid);
}
