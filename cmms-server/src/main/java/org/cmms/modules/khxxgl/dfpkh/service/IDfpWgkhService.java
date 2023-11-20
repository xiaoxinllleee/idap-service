package org.cmms.modules.khxxgl.dfpkh.service;

import org.cmms.modules.khxxgl.dfpkh.entity.DfpWgkh;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 待分配网格客户
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
public interface IDfpWgkhService extends IService<DfpWgkh> {
    public void extract();
}
